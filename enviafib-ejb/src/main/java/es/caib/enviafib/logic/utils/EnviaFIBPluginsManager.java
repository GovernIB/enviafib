package es.caib.enviafib.logic.utils;


import java.util.Properties;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;

import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author anadal
 * 
 */
public class EnviaFIBPluginsManager implements Constants {

	protected static Logger log = Logger.getLogger(EnviaFIBPluginsManager.class);

	public static final String LOGIN_PLUGIN_KEY = ENVIAFIB_PROPERTY_BASE + "userinformationplugin";

	public static IUserInformationPlugin loginPlugin = null;

	/**
	 * 
	 * @return null si no existeix
	 * @throws Exception
	 */

	public static IUserInformationPlugin getUserInformationPluginInstance() throws I18NException {
		if (loginPlugin == null) {
			final String propertyPlugin = LOGIN_PLUGIN_KEY;

			Properties propTmp = Configuracio.getFilesProperties();

			String tmp = propTmp.getProperty(LOGIN_PLUGIN_KEY);

			if (tmp == null) {
				for (Object key : propTmp.keySet()) {
					log.error("XYZ " + key + ": " + propTmp.getProperty(key.toString()));
				}
			}

			log.info("Clase = " + tmp);

			Object pluginInstance = PluginsManager.instancePluginByProperty(propertyPlugin,
					Constants.ENVIAFIB_PROPERTY_BASE, propTmp);

			if (pluginInstance == null) {
				throw new I18NException("plugin.donotinstantiateplugin", new I18NArgumentCode("plugin.userinfo"));
			}
			loginPlugin = (IUserInformationPlugin) pluginInstance;
		}
		return loginPlugin;
	}

}
