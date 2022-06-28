package es.caib.enviafib.back.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import es.caib.enviafib.ejb.UsuariService;
import es.caib.enviafib.logic.utils.EjbManager;
import es.caib.enviafib.logic.utils.EnviaFIBPluginsManager;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

import es.caib.enviafib.commons.utils.Constants;

/**
 * 
 * @author anadal
 * 
 */
@Component
public class AuthenticationSuccessListener implements ApplicationListener<InteractiveAuthenticationSuccessEvent> {

	protected final Logger log = Logger.getLogger(getClass());

	@Override
	public synchronized void onApplicationEvent(InteractiveAuthenticationSuccessEvent event) {

		log.info("\n Entram a AuthenticationSuccessListener \n");

		SecurityContext sc = SecurityContextHolder.getContext();
		Authentication au = sc.getAuthentication();

		if (au == null) {
		    String msg = I18NUtils.tradueix("error.authentication.isnull");
			throw new LoginException(msg);
		}

		User user = (User) au.getPrincipal();

		String username = user.getUsername();
		log.info(" =================================================================");
		log.info(" ============ Login Usuari: " + username);

		// Cercam si té el ROLE_USER o ROLE_ADMIN
		Collection<GrantedAuthority> realAuthorities = user.getAuthorities();
		boolean containsRoleUser = false;
		boolean containsRoleAdmin = false;
		for (GrantedAuthority grantedAuthority : realAuthorities) {
			String rol = grantedAuthority.getAuthority();
			log.info("Rol REAL : " + rol);
			if (Constants.ROLE_USER.equals(rol)) {
				containsRoleUser = true;
			}
			if (Constants.ROLE_ADMIN.equals(rol)) {
				containsRoleAdmin = true;
			}
		}

		log.info(" ============ Login Usuari: " + username);
		log.info(" ============ containsRoleUser: " + containsRoleUser);
		log.info(" ============ containsRoleAdmin: " + containsRoleAdmin);

		try {
			LoginInfo loginInfo = LoginInfo.getInstance();

			if (!username.equals(loginInfo.getUsuari().getUsername())) {
				throw new LoginException("Amb aquest navegador ja s'ha autenticat amb un altre usuari."
						+ " Tanqui el navegador completament.");
			}
		} catch (Throwable e) {
		}

		final boolean isDebug = log.isDebugEnabled();

		UsuariService usuariEjb;
		try {
			usuariEjb = EjbManager.getUsuariEJB();
		} catch (Throwable e) {
            String msg = I18NUtils.tradueix("error.authentication.manager", username, e.getMessage());
            throw new LoginException(msg, e);
		}

		List<Usuari> listUsuariPersona;
		try {
			// Cerca de l'usuari que es conecta
			listUsuariPersona = usuariEjb.select(UsuariFields.USERNAME.equal(username));

		} catch (I18NException e1) {
			listUsuariPersona = null;
			log.error("Error llegint usuari " + username + " : " + e1.getMessage(), e1);
		}
		boolean necesitaConfigurar = false;
		Usuari usuariPersona = null;

		if (listUsuariPersona != null && !listUsuariPersona.isEmpty()) {
			usuariPersona = (Usuari) listUsuariPersona.get(0);
		}

		if (usuariPersona == null) {
			// Revisar si és un Administrador que entra per primera vegada
			log.info("\n No s'ha trobat l'usuari " + username + " a la BBDD \n");
			try {
				IUserInformationPlugin plugin = EnviaFIBPluginsManager.getUserInformationPluginInstance();
				UserInfo info = plugin.getUserInfoByUserName(username);
				if (info != null) {

					UsuariJPA persona = new UsuariJPA();
					persona.setEmail(info.getEmail());
					// persona.setIdiomaID(Configuracio.getDefaultLanguage()); Afegir idioma a
					// usuari o eliminar aquesta linia?
					final String nom;
					{
						String nomTmp = info.getName() == null ? username : info.getName();

						String llinatgesTmp = (info.getSurname1() == null ? "" : info.getSurname1())
								+ (info.getSurname2() == null ? "" : (" " + info.getSurname2()));
						llinatgesTmp = llinatgesTmp.trim();

						if (llinatgesTmp.length() == 0) {
							// Miram si podem xapar el nom
							int pos = nomTmp.indexOf(' ');
							if (pos == -1) {
								nom = nomTmp;
							} else {
								nom = nomTmp.substring(0, pos);
							}
						} else {
							nom = nomTmp;
						}
					}
					persona.setNom(nom);
					// TODO #103: Falta idioma d'usuari (idiomaId) Afegir IdiomaId al a taula efi_usuari 
					persona.setLlinatge1((info.getSurname1() == null ? "" : info.getSurname1()));
					persona.setLlinatge2((info.getSurname2() == null ? "" : info.getSurname2()));
					persona.setUsername(username);
					persona.setNif(!info.getAttributes().containsKey("nif") || info.getAttributes().get("nif") == null
							|| info.getAttributes().get("nif").isEmpty() ? ""
									: info.getAttributes().get("nif").toUpperCase());
					persona.setNif(info.getAttributes().get("nif") == null ? ""
							: info.getAttributes().get("nif").toUpperCase());
					persona.setEmail(info.getEmail() == null ? "" : info.getEmail());

					usuariPersona = usuariEjb.create(persona);
					log.info("\n S'ha creat l'usuari " + username + " la BBDD \n");

					if (isDebug) {
						log.debug("necesitaConfigurarUsuari = " + necesitaConfigurar);
					}

				}
				if (usuariPersona.getNif() == null || usuariPersona.getNif().isEmpty()) {
					necesitaConfigurar = true;

				}

			} catch (Throwable e) {
				usuariPersona = null;
				necesitaConfigurar = true;
				String msg;
				if (e instanceof I18NException) {
					msg = I18NUtils.getMessage((I18NException) e);
				} else if (e instanceof I18NValidationException) {
					msg = I18NUtils.getMessage((I18NValidationException) e);
				} else {
					msg = e.getMessage();
				}
				msg = "Error llegint informació del plugin de UserInformation: " + msg;
				log.error(msg, e);
				throw new LoginException(msg, e);

			}

		} else {
			log.info("\n Hem trobat l'usuari " + username + " a la BBDD \n");
		}

		if (usuariPersona.getNif() == null || usuariPersona.getNif().isEmpty()) {
			necesitaConfigurar = true;
		}

		log.info("LoginInfo:\n" + "\tuser: " + user + "\n" + "\tusuariPersona: " + usuariPersona + "\n"
				+ "\tnecesitaConfigurar: " + necesitaConfigurar + "\n"

		);

		// TODO #103: Obtenir idioma de l'usuari. Null = idioma per defecte. 
		String language = "ca";

		LoginInfo loginInfo = new LoginInfo(user, username, usuariPersona,
				new HashSet<GrantedAuthority>(realAuthorities), language, necesitaConfigurar);

		// and set the authentication of the current Session context
		SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

		log.info(">>>>>> Final del Process d'autenticació.");
		log.info(" =================================================================");

		log.info("\n Sortim de AuthenticationSuccessListener \n");

	}

}
