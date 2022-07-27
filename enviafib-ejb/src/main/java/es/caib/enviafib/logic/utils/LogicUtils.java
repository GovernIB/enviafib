package es.caib.enviafib.logic.utils;


import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
	
	public static Properties stringToProperties(String propertiesStr) {
	    Properties prop = new Properties();
	    if (propertiesStr != null && propertiesStr.trim().length() != 0) {
	      try {
	        prop.load(new StringReader(propertiesStr));
	      } catch (Exception e) {
	        // TODO Crec que no es cridarà mai
	        e.printStackTrace();
	      }
	    }
	    return prop;
	  }
	
	public static List<String> stringToListString(String listStr) {

	    List<String> list;
	    if (listStr != null && listStr.trim().length() != 0) {
	       String[] values = listStr.split(",");
	       list = new ArrayList<String>(Arrays.asList(values));
	    } else {
	      list = null;
	    }
	    
	   return list;
	  }

}
