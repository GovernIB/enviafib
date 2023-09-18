package es.caib.enviafib.back.utils;

import java.util.List;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.ejb.IdiomaEJB;
import es.caib.enviafib.ejb.IdiomaService;
import es.caib.enviafib.model.fields.IdiomaFields;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author anadal
 *
 */
public class EnviaFIBSessionLocaleResolver extends SessionLocaleResolver {

	protected final static Logger log = Logger.getLogger(EnviaFIBSessionLocaleResolver.class);
	
	@EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
	protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;
	

	@Override
	protected Locale determineDefaultLocale(HttpServletRequest request) {
	    
	    log.info("\n XXXXX Entra a determineDefaultLocale \n"); 

		String idioma;
		try {
			idioma = LoginInfo.getInstance().getLanguage();
		} catch (Throwable th) {
			idioma = null;
		}
		log.info("\n XXXXX Entra a determineDefaultLocale Idioma LoginINfo " + idioma + " \n");

		try {

			if (idioma == null) {
               idioma = es.caib.enviafib.commons.utils.Configuracio.getDefaultLanguage();
			}
			log.info("\n XXXXX Entra a determineDefaultLocale Idioma post Configuracio.getDefaultLanguage() " + idioma + " \n");
			
			idioma = checkLanguage(idiomaEjb, idioma);
			
			log.info("\n XXXXX Entra a determineDefaultLocale Idioma post checkLanguage() " + idioma + " \n");

			Locale loc = new Locale(idioma);
			LocaleContextHolder.setLocale(loc);
			try {
				this.setLocale(request, null, loc);
			} catch (Exception e) {
				WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, loc);
			}
			return loc;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return super.determineDefaultLocale(request);
		}

	}

	public static void setLocaleManually(HttpServletRequest request, String idioma) {
		Locale loc = new Locale(idioma);
		LocaleContextHolder.setLocale(loc);
		WebUtils.setSessionAttribute(request, LOCALE_SESSION_ATTRIBUTE_NAME, loc);
	}
	
	
	
	public static String checkLanguage(IdiomaService idiomaEjb, String idioma) {
	    if (idioma == null || idioma.trim().isEmpty() ) {
	       idioma = Configuracio.getDefaultLanguage();
	    }
	    
	    try {
            List<String> suportats = idiomaEjb.executeQuery(IdiomaEJB.IDIOMAID, IdiomaFields.SUPORTAT.equal(true));
            
            if (suportats.contains(idioma)) {
                return idioma;
            }
            String nouIdioma = idioma = Configuracio.getDefaultLanguage();
            log.warn("L'idioma '" + idioma + "' no està suportat. Activam '" + nouIdioma +"' com a idioma per defecte. ");
            
            idioma = nouIdioma;
            
        } catch (I18NException e) {
            log.error("Error intentant descobrir si l'idioma elegit està suportat: " + e.getMessage(), e);
        }
	    
	    return idioma;
	    
	    
	    
	}
	

}
