package es.caib.enviafib.back.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import es.caib.enviafib.model.entity.Usuari;


/**
 * Informació disponible durant el cicle de vida de l'aplicació en la Sessio
 * HTTP. Veure BasePreparer
 * 
 * @author anadal
 * 
 */
public class LoginInfo {

	final User springSecurityUser;

	Set<String> roles;

	Set<GrantedAuthority> grantedAuthorities;

	final String username;

	final Usuari usuari;
	
	final String language;
	
	protected boolean necesitaConfigurar;


	// TODO Add your fields HERE

	/**
	 * @param usuari
	 * @param entitatActual
	 * @param roles
	 */
	public LoginInfo(User springSecurityUser, String username, Usuari usuari, Set<GrantedAuthority> grantedAuthorities,
			String language, boolean necesitaConfigurar) {
		this.springSecurityUser = springSecurityUser;
		this.username = username;
		this.language = language;
		this.usuari = usuari;
		this.necesitaConfigurar = necesitaConfigurar;

		this.grantedAuthorities = grantedAuthorities;
		this.roles = new HashSet<String>();

		for (GrantedAuthority grantedAuthority : this.grantedAuthorities) {
			this.roles.add(grantedAuthority.getAuthority());
		}

	}

	public Set<String> getRoles() {
		return this.roles;
	}

	public Set<GrantedAuthority> getGrantedAuthorities() {
		return grantedAuthorities;
	}

    public static boolean hasRole(String role) {
        try {
            return LoginInfo.getInstance().getRoles().contains(role);
        } catch (Throwable th) {
            return false;
        }
    }

	public String getUsername() {
		return this.username;
	}
	
	public Usuari getUsuari() {
		return usuari;
	}

	public String getLanguage() {
		return this.language;
	}

	public UsernamePasswordAuthenticationToken generateToken() {
		UsernamePasswordAuthenticationToken authToken;
		Set<GrantedAuthority> roles = getGrantedAuthorities();
		authToken = new UsernamePasswordAuthenticationToken(this.springSecurityUser, "", roles);
		authToken.setDetails(this);
		return authToken;
	}

	public static LoginInfo getInstance() throws LoginException {
		Object obj;
		try {
			obj = SecurityContextHolder.getContext().getAuthentication().getDetails();
		} catch (Exception e) {
			// TODO traduccio
			throw new LoginException("Error intentant obtenir informació de Login.", e);
		}

		if (obj == null) {
			// TODO traduccio
			throw new LoginException("La informació de Login és buida");
		}

		if (obj instanceof LoginInfo) {
			return (LoginInfo) obj;
		} else {
			// TODO traduccio
			throw new LoginException("La informació de Login no és del tipus esperat." + " Hauria de ser de tipus "
					+ LoginInfo.class.getName() + " i és del tipus " + obj.getClass().getName());
		}
	}
	
	public boolean isNecesitaConfigurar() {
		return necesitaConfigurar;
	}

	public void setNecesitaConfigurar(boolean necesitaConfigurar) {
		this.necesitaConfigurar = necesitaConfigurar;
	}

}
