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
    
    public static final int ESTAT_PETICIO_ARXIVANT = 5;
    public static final int ESTAT_PETICIO_ERROR_ARXIVANT = 6;
    public static final int ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT = 7;
    
    public static final int ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT = 8;
    
    public static final int[] ESTATS_PETICIO = { ESTAT_PETICIO_EN_PROCES,
            ESTAT_PETICIO_FIRMADA, ESTAT_PETICIO_ERROR, ESTAT_PETICIO_ARXIVANT, ESTAT_PETICIO_ERROR_ARXIVANT,
            ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT, ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT };

    // TIPUS DE PETICIONS DE ENVIAFIB
    public static final int TIPUS_PETICIO_NIF = 0;
    public static final int TIPUS_PETICIO_AUTOFIRMA = 1;
    public static final int TIPUS_PETICIO_FLUX = 2;
    public static final int TIPUS_PETICIO_DIRECTOR = 3;
    public static final int TIPUS_PETICIO_SECRETARI = 4;
    public static final int TIPUS_PETICIO_PLANTILLAFLUX_USUARI= 5;
    public static final int TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT= 6;
    
    public static final int[] TIPUS_PETICIONS = { TIPUS_PETICIO_NIF, TIPUS_PETICIO_AUTOFIRMA,
            TIPUS_PETICIO_FLUX, TIPUS_PETICIO_DIRECTOR, TIPUS_PETICIO_SECRETARI, 
            TIPUS_PETICIO_PLANTILLAFLUX_USUARI, TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT };
    
    public static final String CODI_PETICIO_AUTOFIRMA = "titol.autofirma";
    public static final String CODI_PETICIO_NIF = "titol.firmapernif";
    public static final String CODI_PETICIO_FLUX = "titol.firmaperflux";
    public static final String CODI_PETICIO_DIRECTOR = "titol.firmacarrec.director";
    public static final String CODI_PETICIO_SECRETARI = "titol.firmacarrec.secretari";
    public static final String CODI_PETICIO_PLANTILLAFLUX_USUARI = "titol.plantillaflux.usuari";
    public static final String CODI_PETICIO_PLANTILLAFLUX_ENTITAT = "titol.plantillaflux.entitat";
    
    
    // TIPUS DE PLUGINS DE ENVIAFIB
    public static final int TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA= 1;
    public static final int TIPUS_PLUGIN_ARXIU = 2;

    //TIPUS DE CARRECTS
    public static final int CARREC_GERENT_PRESIDENT = 1;
    public static final int CARREC_CAP_AREA_CONSELLER = 2;
    public static final int CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL = 3;
    public static final int CARREC_SECRETARI = 4;
    public static final int CARREC_ENCARREGAT_COMPRES = 5;
    public static final int CARREC_RECURSOS_HUMANS = 6;

    
    
    
    // FORMATS DE FITXERS
    public static final int FORMAT_FILE_PDF = 0; // "pdf";

    public static final int FORMAT_FILE_JPG = 1; // "jpg";

    public static final int FORMAT_FILE_TIFF = 2; // "tif";

    public static final int FORMAT_FILE_PNG = 3; // "png";

    public static final int FORMAT_FILE_GIF = 4; // "gif";
    
    
    
    public static final int ORIGEN_ADMINISTRACIO = 0;
    public static final int ORIGEN_CIUTADA = 1;

}
