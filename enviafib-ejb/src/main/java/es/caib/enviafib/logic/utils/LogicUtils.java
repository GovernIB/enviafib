package es.caib.enviafib.logic.utils;


import org.apache.log4j.Logger;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.StaticVersion;

/**
 * 
 * @author anadal
 *
 */
public class LogicUtils {

	protected static Logger log = Logger.getLogger(LogicUtils.class);

	public static String getVersio() {
		return StaticVersion.VERSION + (Configuracio.isCAIB() ? "-caib" : "");
	}

}
