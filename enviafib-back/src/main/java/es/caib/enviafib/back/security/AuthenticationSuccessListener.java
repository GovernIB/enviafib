package es.caib.enviafib.back.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.context.ApplicationListener;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import es.caib.enviafib.back.utils.EnviaFIBSessionLocaleResolver;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.EntitatService;
import es.caib.enviafib.ejb.IdiomaService;
import es.caib.enviafib.ejb.UsuariEntitatService;
import es.caib.enviafib.logic.UsuariLogicaService;
import es.caib.enviafib.logic.utils.EjbManager;
import es.caib.enviafib.logic.utils.EnviaFIBPluginsManager;
import es.caib.enviafib.model.entity.Entitat;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.model.fields.EntitatFields;
import es.caib.enviafib.model.fields.UsuariEntitatFields;
import es.caib.enviafib.persistence.UsuariJPA;

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

        log.info("Entram a AuthenticationSuccessListener");
        
        
        log.info("\n XXXXX Entra a AuthenticationSuccessListener  Configuracio.getDefaultLanguage() => '" +  Configuracio.getDefaultLanguage() + "' \n");
        
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();

        if (authentication == null) {
            String msg = I18NUtils.tradueix("error.authentication.isnull");
            throw new LoginException(msg);
        }

        User user = (User) authentication.getPrincipal();

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

        UsuariLogicaService usuariLogicaEjb;
        try {
        	usuariLogicaEjb = EjbManager.getUsuariEJB();
        } catch (Throwable e) {
            String msg = I18NUtils.tradueix("error.authentication.manager", username, e.getMessage());
            throw new LoginException(msg, e);
        }

        Usuari usuariPersona = usuariLogicaEjb.getUserByUsername(username);
        
        boolean necesitaConfigurar = false;
        
        // Check if Usuari trobat a BBDD
        if (usuariPersona == null) {
            // Revisar si és un Administrador que entra per primera vegada
            log.info("\n No s'ha trobat l'usuari " + username + " a la BBDD \n");
            try {
                IUserInformationPlugin plugin = EnviaFIBPluginsManager.getUserInformationPluginInstance();
                UserInfo info = plugin.getUserInfoByUserName(username);

                // Check if Usuari trobat a UserInformation
                if (info != null) {
                    log.info("\n Usuari trobat " + username + " a UserInformation\n");
                    // Sent UserInfo, sera el primer login, i sempre necesita configurar.
                    necesitaConfigurar = true;

                    // Check if DNI ja existeix al sistema
                    UsuariJPA persona = new UsuariJPA();
                    persona.setEmail(info.getEmail());
                    String lang = LocaleContextHolder.getLocale().getLanguage();
                    
                    log.info("\n XXXXX Entra a AuthenticationSuccessListener NEW Idioma '" + lang + "' \n");
                    if (lang == null) {
                        lang = Configuracio.getDefaultLanguage();
                        if (lang == null) {
                            lang = "ca";
                        }
                    }
                    log.info("\n XXXXX Entra a AuthenticationSuccessListener NEW Idioma POST getDefaultLanguage() '" + lang + "' \n");
                    
                    
                    IdiomaService idiomaEjb;
                    try {
                       idiomaEjb = EjbManager.getIdiomaEJB();
                       lang = EnviaFIBSessionLocaleResolver.checkLanguage(idiomaEjb, lang);
                       log.info("\n XXXXX Entra a AuthenticationSuccessListener NEW Idioma POST checkLanguage() '" + lang + "' \n");
                    } catch (Throwable e) {
                        String msg = I18NUtils.tradueix("comodi", "Error intentant validar l'idioma per defecte: " + e.getMessage());
                        throw new LoginException(msg, e);
                    }
                    
                    persona.setIdiomaID(lang);

                    // Omplir nom i llinatges segons info de userinfo
                    final String nom = calculateUsername(username, info);

                    persona.setNom(nom);
                    persona.setLlinatge1((info.getSurname1() == null ? "" : info.getSurname1()));
                    persona.setLlinatge2((info.getSurname2() == null ? "" : info.getSurname2()));

                    persona.setUsername(username);
                    persona.setNif(info.getAdministrationID());
                    persona.setEmail(info.getEmail() == null ? "" : info.getEmail());

                    
					EntitatService entitatEjb;
					try {
						entitatEjb = EjbManager.getEntitatEJB();
						
						//Si no troba el dir, torna el de govern.
						String dir3 = obtenirDIR3DePlugin(username);
						log.info("El dir3 de l'usuari es: " + dir3);
						//Si troba el dir3 de l'usuari, pero no està a la taula d'entitats, assignam l'usuari a l'entitat per defecte, que es govern
						String entitatID = entitatEjb.executeQueryOne(EntitatFields.ENTITATID,
								EntitatFields.DIR3.equal(dir3));						

						log.info("EntitatID: " + entitatID);
						if (entitatID == null) {
							log.info("No s'ha trobat l'entitat per defecte, assignam l'usuari a govern");
                            entitatID = "govern";
						}
						
						persona.setEntitatID(entitatID);

					} catch (Throwable e) {
						String msg = I18NUtils.tradueix("comodi","Error intentant obtenir l'entitat per defecte: " + e.getMessage());
						throw new LoginException(msg, e);
					}

                    try {
                        usuariPersona = usuariLogicaEjb.create(persona);
                        log.info("\n S'ha creat l'usuari " + username + " la BBDD \n");
                    } catch (Throwable e) {
                        usuariPersona = new UsuariJPA(persona);
                        usuariPersona.setUsuariID(0);
                        necesitaConfigurar = true;
                    }

                    /*
                     * persona.setNif(!info.getAttributes().containsKey("nif") ||
                     * info.getAttributes().get("nif") == null ||
                     * info.getAttributes().get("nif").isEmpty() ? "" :
                     * info.getAttributes().get("nif").toUpperCase());
                     * persona.setNif(info.getAttributes().get("nif") == null ? "" :
                     * info.getAttributes().get("nif").toUpperCase());
                     */

                    // Controlar excepció i redirigir a pantalla de creació d'usuari.

                    if (isDebug) {
                        log.debug("necesitaConfigurarUsuari = " + necesitaConfigurar);
                    }

                } else {
                    log.info("\n No s'ha trobat l'usuari " + username + " a UserInformation \n");

                }
                // if (usuariPersona.getNif() == null || usuariPersona.getNif().isEmpty())

            } catch (Throwable e) {
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

        if (usuariPersona == null) {
            usuariPersona = new UsuariJPA();
            usuariPersona.setUsername(username);
            necesitaConfigurar = true;

        } else if (usuariPersona.getNif() == null || usuariPersona.getNif().isEmpty()) {
            necesitaConfigurar = true;
        }

        {
			log.info("LoginInfo:\n" + "\tuser: " + user + "\n" + "\tusuariPersona: " + usuariPersona + "\n"
					+ "\tnecesitaConfigurar: " + necesitaConfigurar + "\tusuariID: " + usuariPersona.getUsuariID());

			String language = usuariPersona.getIdiomaID();

			
			
			Map<String, EntitatRoles> mapEntitats = new java.util.HashMap<String, EntitatRoles>();
			
			try {
				EntitatService entitatEjb = EjbManager.getEntitatEJB();

				// Entitat de l'usuari
				{
					String entitatID = usuariPersona.getEntitatID();
					Entitat entitatUser = entitatEjb.findByPrimaryKey(entitatID);

					mapEntitats.put(entitatID, new EntitatRoles(entitatUser));
					mapEntitats.get(entitatID).addRole(Constants.ROLE_USER);
				}

				// Entitaits administrades per l'usuari
				{
					UsuariEntitatService usuariEntitatEjb = EjbManager.getUsuariEntitatEJB();
					List<UsuariEntitat> llistat = usuariEntitatEjb
							.select(UsuariEntitatFields.USUARIID.equal(usuariPersona.getUsuariID()));

					for (UsuariEntitat usuariEntitat : llistat) {
						String entitatID = usuariEntitat.getEntitatid();
						Entitat entitatAden = entitatEjb.findByPrimaryKey(entitatID);

						if (mapEntitats.get(entitatID) == null) {
							mapEntitats.put(entitatID, new EntitatRoles(entitatAden));
						}
						mapEntitats.get(entitatID).addRole(Constants.ROLE_ADEN);
					}
				}

			} catch (Throwable e) {
				String msg = I18NUtils.tradueix("error.authentication.manager", username, e.getMessage());
				throw new LoginException(msg, e);
			}

//			LoginInfo loginInfo = new LoginInfo(user, username, usuariPersona, entitat,
//					new HashSet<GrantedAuthority>(realAuthorities), language, necesitaConfigurar);
			
			LoginInfo loginInfo = new LoginInfo(user, username, usuariPersona, mapEntitats, new HashSet<GrantedAuthority>(realAuthorities), language, necesitaConfigurar);

            // and set the authentication of the current Session context
            SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());

            log.info(">>>>>> Final del Process d'autenticació.");
            log.info(" =================================================================");

            log.info("Sortim de AuthenticationSuccessListener");
        }

    }

    private String calculateUsername(String username, UserInfo info) {
        final String nom;
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
        return nom;
    }

	private String obtenirDIR3DePlugin(String username) {
		String codiDIR3;
		try {

			IEstructuraOrganitzativaPlugin plugin = EjbManager.getPluginEstructuraOrganitzativa().getInstance();

			codiDIR3 = plugin.getDir3DepartamentDireccioGeneral(username);
			if (codiDIR3 != null && codiDIR3.trim().length() > 0) {
				log.info("El meu codiDIR3 es: " + codiDIR3);
			} else {
				log.error("El codi DIR3 de l'usuari " + username + " es null o buit ]" + codiDIR3 + "[");
				// Si no troba el dir3, retornam per defecte el de govern:
				codiDIR3 = "A04003003";
			}
		} catch (Throwable e) {
			log.error("Error obtenint el dir3:  " + e.getMessage(), e);
			codiDIR3 = "A04003003";
		}
		return codiDIR3;
	}
    
}
