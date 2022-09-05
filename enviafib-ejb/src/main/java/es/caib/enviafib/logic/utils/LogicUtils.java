package es.caib.enviafib.logic.utils;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
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
                // XYZ ZZZ TRA - DONE
                // TODO Crec que no es cridarà mai. Pasar a log.error
                log.error("Error passant propietats a String: " + e.getMessage(), e);
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

    public static String stackTrace2String(Throwable th) {
        StringWriter sw = new StringWriter();
        th.printStackTrace(new PrintWriter(sw));
        String exceptionAsString = sw.toString();
        return exceptionAsString;
    }
    
    
    
    public static String split255(String value) {
        
        if (value == null) {
            return null;
        } else {
            return value.length() < 255? value : value.substring(0,254);
        }
        
    }
    

}
