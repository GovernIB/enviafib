package es.caib.enviafib.logic.utils;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.UsuariService;

/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);

	protected static UsuariService usuariEjb;

	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	public static UsuariService getUsuariEJB() throws I18NException {

		if (usuariEjb == null) {
			try {
				usuariEjb = (UsuariService) new InitialContext().lookup(UsuariService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariPersonaLogicaLocal");
			}
		}
		return usuariEjb;
	}

}