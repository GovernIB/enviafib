package es.caib.enviafib.commons.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 
 * @author anadal
 * 
 */
public class Configuracio implements Constants {

    private static final Logger LOG = LoggerFactory.getLogger(Configuracio.class);

    private static final Properties fileProperties = new Properties();
    
    private static final Properties fileAndSystemProperties = new Properties();

    /*
     * Agafa els fitxers de propietats definits a l'standalone
     *
     * Seguim els estandars de la CAIB 
     */
    public static Properties getFilesProperties() {
        
        if (fileProperties.isEmpty()) {
			// matches the property name as defined in the system-properties element in
			// WildFly
			try {
				String property = Constants.ENVIAFIB_PROPERTY_BASE + "properties";
				loadPropertyFile(property);
				
				String propertySystem = Constants.ENVIAFIB_PROPERTY_BASE + "system.properties";
				loadPropertyFile(propertySystem);
				
			} catch (FileNotFoundException e) {
				LOG.error("El fitxer de propietats no esta definit", e);

			} catch (NullPointerException e) {
				LOG.error("Propietat sense valor", e);
				
			} catch (IOException e) {
				LOG.error("No es pot carregar algun dels fitxers de propietats ... ", e);
			}
        }
        
        return fileProperties;

    }

	public static void loadPropertyFile(String property) throws FileNotFoundException, IOException {
		String propertyFile = System.getProperty(property);

		if (propertyFile.equals("")) {
			throw new NullPointerException("No esta definida la propietat: " + property);
		}

		File File = new File(propertyFile);
		if (!File.exists()) {
			throw new FileNotFoundException(File.getAbsolutePath());
		}
		fileProperties.load(new FileInputStream(File));
	}

    
    public static Properties getSystemAndFileProperties() {

        if (fileAndSystemProperties.isEmpty()) {
            fileAndSystemProperties.putAll(getFilesProperties());
            fileAndSystemProperties.putAll(System.getProperties());
        }
        return fileAndSystemProperties;
    }

    public static String getProperty(String key) {

        return  getFilesProperties().getProperty(key);

    }

    public static String getProperty(String key, String def) {
        
        return getFilesProperties().getProperty(key, def);

    }

    public static boolean isDesenvolupament() {

        return Boolean.parseBoolean(getProperty(ENVIAFIB_PROPERTY_BASE + "development"));
    }

    public static boolean isCAIB() {
        return Boolean.parseBoolean(getProperty(ENVIAFIB_PROPERTY_BASE + "iscaib"));
    }

    public static String getAppUrl() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "url");
    }

    public static String getAppEmail() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "email.from");
    }

    public static String getAppName() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "name", "EnviaFIB");
    }

    public static String getDefaultLanguage() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "defaultlanguage", "ca");
    }

    public static byte[] getEncryptKey() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "encryptkey", "0123456789123456").getBytes();
    }

    public static Long getMaxUploadSizeInBytes() {
        return Long.getLong(ENVIAFIB_PROPERTY_BASE + "maxuploadsizeinbytes");
    }

    public static Long getMaxFitxerAdaptatSizeInBytes() {
        return Long.getLong(ENVIAFIB_PROPERTY_BASE + "maxfitxeradaptatsizeinbytes");
    }

    public static File getFilesDirectory() {
    	
        String path = getProperty(ENVIAFIB_PROPERTY_BASE + "filesdirectory");
        if(path == null) {
        	throw new RuntimeException("No existeix la propietat 'filesdirectory' al fitxer 'system.properties'. \n"
        			+ "S'hauria d'anar al fitxer '.system.properties' de JBoss standalone/deployments i incloure la propietat 'filesdirectory' amb una ruta al directori on l'aplició gestionara els fitxers.");
        }else if(path.isEmpty()){
        	throw new RuntimeException("No s'ha definit la propietat 'filesdirectory' al fitxer 'system.properties'. \n"
        			+ "S'hauria d'anar al fitxer '.system.properties' de JBoss standalone/deployments i donar valor a la propietat 'filesdirectory' amb una ruta al directori on l'aplició gestionara els fitxers.");
        }
        
        File filesFolder = new File(path);
        
        if(!filesFolder.exists()) {
        	throw new RuntimeException("El directori indicat a la propietat 'filesdirectory' del fitxer 'system.properties' no existeix. \n "
        			+ "S'hauria de modificar la ruta indicada al fitxer '.system.properties' de JBoss standalone/deployments per la d'un directori existent, o crear un directori amb la ruta: " + path);
        }else if(!filesFolder.isDirectory()) {
        	throw new RuntimeException("El directori indicat a la propietat 'filesdirectory' del fitxer 'system.properties' no es un directori, probablement es tracti d'un fitxer. \n"
        			+ "S'hauria de modificar la ruta indicada al fitxer '.system.properties' de JBoss standalone/deployments per la d'un directori existent.");
        }
        
        return new File(path);
    	
    }

    public static String getFileSystemManager() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "filesystemmanagerclass");
    }
    
    public static String getPortafibGatewayV2() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafibgatewayv2");
    }
    public static String getPortafibUsername() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafibusername");
    }
    public static String getPortafibPassword() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafibpassword");
    }

}
