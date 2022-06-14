package es.caib.enviafib.commons.utils;

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
            String property = Constants.ENVIAFIB_PROPERTY_BASE + "properties";
            loadPropertyFile(property);

            String propertySystem = Constants.ENVIAFIB_PROPERTY_BASE + "system.properties";
            loadPropertyFile(propertySystem);
        }

        return fileProperties;

    }

    public static void loadPropertyFile(String property) {

        String propertyFile = System.getProperty(property);

        if (propertyFile == null) {
            throw new RuntimeException("No existeix la propietat: " + property
                    + " al fitxer standalone.xml. S'hauria d'incloure aquesta propietat a l'etiqueta <system-properties> del fitxer standalone");
        }

        if (propertyFile.trim().length() == 0) {
            throw new RuntimeException("La propietat: " + property
                    + " del fitxer standalone.xml no te valor. Se li ha de posar el fitxer corresponent a la propietat al fitxer standalone");
        }

        File File = new File(propertyFile);
//		if (!File.exists()) {
//			throw new RuntimeException("La propietat "File.getAbsolutePath());
//		}

        try {
            fileProperties.load(new FileInputStream(File));

        } catch (FileNotFoundException e) {
            throw new RuntimeException("La propietat: " + property
                    + " del fitxer standalone apunta a un fitxer que no existeix (" + propertyFile
                    + ")");

        } catch (IOException e) {
            throw new RuntimeException(
                    "La propietat: " + property + " del fitxer standalone apunta a un fitxer("
                            + propertyFile + ") que no es pot llegir:" + e.getMessage(),
                    e);
        }
    }

    public static Properties getSystemAndFileProperties() {

        if (fileAndSystemProperties.isEmpty()) {
            fileAndSystemProperties.putAll(getFilesProperties());
            fileAndSystemProperties.putAll(System.getProperties());
        }
        return fileAndSystemProperties;
    }

    public static String getProperty(String key) {

        return getFilesProperties().getProperty(key);

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
        if (path == null) {
            throw new RuntimeException("No existeix la propietat '" + ENVIAFIB_PROPERTY_BASE
                    + "filesdirectory'" + " al fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + ". S'hauria d'anar al fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + " i incloure la propietat '" + ENVIAFIB_PROPERTY_BASE + "filesdirectory'"
                    + " amb una ruta al directori on l'aplició gestionara els fitxers.");
        }

        if (path.isEmpty()) {
            throw new RuntimeException("No s'ha definit la propietat '" + ENVIAFIB_PROPERTY_BASE
                    + "filesdirectory'" + " al fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + ". S'hauria d'anar al fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + " i donar valor a la propietat '" + ENVIAFIB_PROPERTY_BASE + "filesdirectory'"
                    + " amb una ruta al directori on l'aplició gestionara els fitxers.");
        }

        File filesFolder = new File(path);

        if (!filesFolder.exists()) {
            throw new RuntimeException("El directori indicat a la propietat '"
                    + ENVIAFIB_PROPERTY_BASE + ".filesdirectory'" + " del fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + " no existeix. S'hauria de modificar la ruta indicada per la d'un directori existent, o crear un directori amb la ruta: "
                    + path);
        }

        if (!filesFolder.isDirectory()) {
            throw new RuntimeException("El directori indicat a la propietat '"
                    + ENVIAFIB_PROPERTY_BASE + ".filesdirectory'" + " del fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + " no es un directori, probablement es tracti d'un fitxer. S'hauria de modificar la ruta indicada per la d'un directori existent.");
        }

        if (!filesFolder.canWrite()) {
            throw new RuntimeException("El directori indicat a la propietat '"
                    + ENVIAFIB_PROPERTY_BASE + ".filesdirectory'" + " del fitxer "
                    + System.getProperty(ENVIAFIB_PROPERTY_BASE + "system.properties")
                    + " es un directori sense permisos d'escriptura. S'haurien de donar permisos d'escriptura al directori, o canviar la ruta a un directori amb permisos.");
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

    public static String getPortaFIBApiFirmaWebUrl() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaweb.url");
    }

    public static String getPortaFIBApiFirmaWebUsername() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaweb.username");
    }

    public static String getPortaFIBApiFirmaWebUrlPassword() {
        return getProperty(ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaweb.password");
    }

}
