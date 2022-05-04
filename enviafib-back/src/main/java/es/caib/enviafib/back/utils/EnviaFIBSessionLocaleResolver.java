package es.caib.enviafib.back.utils;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import es.caib.enviafib.back.security.LoginInfo;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.util.WebUtils;

/**
 * 
 * @author anadal
 *
 */
public class EnviaFIBSessionLocaleResolver extends SessionLocaleResolver{

	protected final Logger log = Logger.getLogger(getClass());

	@Override
	protected Locale determineDefaultLocale(HttpServletRequest request) {

		String idioma;
		try {
			idioma = LoginInfo.getInstance().getLanguage();
		} catch (Throwable th) {
			idioma = null;
		}

		try {

			if (idioma == null) {
            idioma = es.caib.enviafib.commons.utils.Configuracio.getDefaultLanguage();
			}

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

}
