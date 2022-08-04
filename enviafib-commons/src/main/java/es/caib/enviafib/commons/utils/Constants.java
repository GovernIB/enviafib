package es.caib.enviafib.commons.utils;


/**
 *
 * @author anadal
 *
 */
public interface Constants {

    public static final String ENVIAFIB_PROPERTY_BASE = "es.caib.enviafib.";

    public static final String MAIL_SERVICE = "java:/es.caib.enviafib.mail";
    
    public static final String PREFIX = "EFI";

    // TRUE ROLES
    public static final String EFI_ADMIN = "EFI_ADMIN";
    public static final String EFI_USER = "EFI_USER";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";

    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS = EFI_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = EFI_USER;

    // ESTATS DE PETICIO DE FIRMA D'ENVIAFIB
    public static final int ESTAT_PETICIO_EN_PROCES = 2;
    public static final int ESTAT_PETICIO_FIRMADA = 3;
    public static final int ESTAT_PETICIO_ERROR = 4;
    public static final int[] ESTATS_PETICIO = { ESTAT_PETICIO_EN_PROCES,
            ESTAT_PETICIO_FIRMADA, ESTAT_PETICIO_ERROR };

    // TIPUS DE PETICIONS DE ENVIAFIB
    public static final int TIPUS_PETICIO_NIF = 0;
    public static final int TIPUS_PETICIO_AUTOFIRMA = 1;
    public static final int TIPUS_PETICIO_FLUX = 2;
    public static final int TIPUS_PETICIO_DIRECTOR = 3;
    public static final int TIPUS_PETICIO_SECRETARI = 4;
    public static final int[] TIPUS_PETICIONS = { TIPUS_PETICIO_NIF, TIPUS_PETICIO_AUTOFIRMA,
            TIPUS_PETICIO_FLUX, TIPUS_PETICIO_DIRECTOR, TIPUS_PETICIO_SECRETARI };
    
    
    // TIPUS DE PLUGINS DE ENVIAFIB
    public static final int TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA= 1;

    
    
    // FORMATS DE FITXERS
    public static final int FORMAT_FILE_PDF = 0; // "pdf";

    public static final int FORMAT_FILE_JPG = 1; // "jpg";

    public static final int FORMAT_FILE_TIFF = 2; // "tif";

    public static final int FORMAT_FILE_PNG = 3; // "png";

    public static final int FORMAT_FILE_GIF = 4; // "gif";
    
    
    
    public static final int ORIGEN_ADMINISTRACIO = 0;
    public static final int ORIGEN_CIUTADA = 1;
    
    

    //TIPUS DE PLUGINS
    
    public static final int TIPUS_PLUGIN_ARXIU = 3;

}
