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
    public static final String EFI_USER = "tothom";
    public static final String EFI_WS = "EFI_WS";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
     

    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS = EFI_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = EFI_USER;
    
    public static final String ROLE_EJB_WS_ACCESS = EFI_WS;

    // ESTATS DE PETICIO DE FIRMA D'ENVIAFIB

    //PENDENTS
    public static final int ESTAT_PETICIO_EN_PROCES = 2;
    public static final int ESTAT_PETICIO_ARXIVANT = 5;
    public static final int ESTAT_PETICIO_ERROR_ARXIVANT = 6;
    public static final int ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT = 7;
    public static final int ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT = 8;

    //FIRMADES
    public static final int ESTAT_PETICIO_FIRMADA = 3;

    //REBUTJADES
    public static final int ESTAT_PETICIO_ERROR = 4;

    public static final int[] ESTATS_PETICIO = { ESTAT_PETICIO_EN_PROCES, ESTAT_PETICIO_FIRMADA, ESTAT_PETICIO_ERROR,
            ESTAT_PETICIO_ARXIVANT, ESTAT_PETICIO_ERROR_ARXIVANT, ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT,
            ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT };

    // TIPUS DE PETICIONS DE ENVIAFIB
    public static final int TIPUS_PETICIO_NIF = 0;
    public static final int TIPUS_PETICIO_AUTOFIRMA = 1;
    public static final int TIPUS_PETICIO_FLUX = 2;
    //public static final int TIPUS_PETICIO_DIRECTOR = 3;
    //public static final int TIPUS_PETICIO_SECRETARI = 4;
    public static final int TIPUS_PETICIO_PLANTILLAFLUX_USUARI = 5;
    public static final int TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT = 6;
    public static final int TIPUS_PETICIO_FLUX_SIMPLE = 7;
    public static final int TIPUS_PETICIO_FLUX_JSON = 8;
    // Càrrecs
    public static final int TIPUS_PETICIO_CARREC_GERENT_PRESIDENT = 11;
    public static final int TIPUS_PETICIO_CARREC_CAPAREA_CONSELLER = 12;
    public static final int TIPUS_PETICIO_CARREC_CAPDEPARTAMENT_DIRECTOR = 13;
    public static final int TIPUS_PETICIO_CARREC_SECRETARI = 14;
    public static final int TIPUS_PETICIO_CARREC_ENCARREGAT_COMPRES = 15;
    public static final int TIPUS_PETICIO_CARREC_RECURSOS_HUMANS = 16;
    public static final int TIPUS_PETICIO_CARREC_ADDICIONAL_1 = 17;
    public static final int TIPUS_PETICIO_CARREC_ADDICIONAL_2 = 18;
    

    public static final int[] TIPUS_PETICIONS = { TIPUS_PETICIO_NIF, TIPUS_PETICIO_AUTOFIRMA, TIPUS_PETICIO_FLUX,
            TIPUS_PETICIO_PLANTILLAFLUX_USUARI, TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT,
            TIPUS_PETICIO_FLUX_SIMPLE, TIPUS_PETICIO_FLUX_JSON,
            TIPUS_PETICIO_CARREC_GERENT_PRESIDENT,TIPUS_PETICIO_CARREC_CAPAREA_CONSELLER,
            TIPUS_PETICIO_CARREC_CAPDEPARTAMENT_DIRECTOR,TIPUS_PETICIO_CARREC_SECRETARI,
            TIPUS_PETICIO_CARREC_ENCARREGAT_COMPRES, TIPUS_PETICIO_CARREC_RECURSOS_HUMANS,
            TIPUS_PETICIO_CARREC_ADDICIONAL_1,
            TIPUS_PETICIO_CARREC_ADDICIONAL_2};


    // TIPUS DE PLUGINS DE ENVIAFIB
    public static final int TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA = 1;
    public static final int TIPUS_PLUGIN_ARXIU = 2;

    /* ============= TIPUS DE CARRECS DE PLUGIN ESTRUCTURA ORGANITZATIVA ======== */
    public static final int CARREC_GERENT_PRESIDENT = 1;
    public static final int CARREC_CAP_AREA_CONSELLER = 2;
    public static final int CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL = 3;
    public static final int CARREC_SECRETARI = 4;
    public static final int CARREC_ENCARREGAT_COMPRES = 5;
    public static final int CARREC_RECURSOS_HUMANS = 6;
    public static final int CARREC_ADDICIONAL_1 = 7;
    public static final int CARREC_ADDICIONAL_2 = 8;
    
    public static final int[] CARRECS = {
            CARREC_GERENT_PRESIDENT, CARREC_CAP_AREA_CONSELLER,
            CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL, CARREC_SECRETARI,
            CARREC_ENCARREGAT_COMPRES, CARREC_RECURSOS_HUMANS,
            CARREC_ADDICIONAL_1, CARREC_ADDICIONAL_2
    };
    

    // FORMATS DE FITXERS
    public static final int FORMAT_FILE_PDF = 0; // "pdf";

    public static final int FORMAT_FILE_JPG = 1; // "jpg";

    public static final int FORMAT_FILE_TIFF = 2; // "tif";

    public static final int FORMAT_FILE_PNG = 3; // "png";

    public static final int FORMAT_FILE_GIF = 4; // "gif";

    public static final int ORIGEN_ADMINISTRACIO = 0;
    public static final int ORIGEN_CIUTADA = 1;

    /* ============= TIPUS DE MENU DE FIRMA ======== */

    public static final int MENU_FIRMA_TIPUS_AUTOFIRMA = 1;

    public static final int MENU_FIRMA_TIPUS_PER_NIF = 2;

    public static final int MENU_FIRMA_TIPUS_FLUX = 3; //  Firma Personalitzada"

    /**
     * 4.- Firma Asíncrona (PortaFIB) - Per Flux - Plantilles de l'usuari: 
     * Es crearà en l'issue " Afegir Petició de Firma emprant Plantilles de 
     * Flux de Firmes del propi usuari #155 ". NOTA: Si s'activa aquest menú, 
     * també s'haurà d'activa el menu actual " Plantilles de Flux de Firmes "
     */
    public static final int MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI = 4;

    /**
     *5.- Firma Asíncrona (PortaFIB) - Per Flux - Plantilles de l'entitat:
     * S'usaran les plantilles llistades aquí: 
     * Copiar opció de menu "Plantilles de Flux de Firmes" a Administrador #164. 
     * Dins parametretext s'hi ficara el llistat de Id's de Plantilles de Flux 
     * de Firmes d'Entitat disponibles. Si només hi ha una Plantilla llavors 
     * s'ocultarà el desplegable"
    */
    public static final int MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT = 5;

    /**
     * 6.- Firma Asíncrona (PortaFIB) - Per Flux Simple- Copiar dins de "paràmetres" 
     * el flux emprant la següent nomenclatura:
    * Cada Fila es un bloc de firmes
    * Els usuaris de cada bloc es separarà pel caracter '|' o '&'
    * El usuaris es definiran pel seu username
    * Els càrrecs es definiran emprant tags Freemarker
    Exemple:
    anadal|ptrias
    atrobat&fbosh
    ${secretari}
    ${gerentpresident}
    Primer anadal o ptrias, despres atrobat i fbosch (ordre indistint), despres secretari i finalment gerent 
     */
    public static final int MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT = 6;

    /**
    7.- Firma Asíncrona (PortaFIB) - Per Flux en Format JSON- Copiar dins de "paràmetres" el codi 
        JSON de la Plantilla i es podran afegir substitucions Freemarker del Plugin de Estructura organitzativa:
         Per exemple es podrà crear un flux amb el cap i el gerent. Crear codis de substitucions. El format serà:
    */
    public static final int MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON = 7;

    /**
    8.- Firma Asíncrona (PortaFIB) - Per càrrec del Plugin d'Estructura organitzativa: 
       Dins del "paràmetres" hi haurà un numero segons el càrrec que sigui:
    1.- GerentPresident
    2.- CapAreaConseller
    3.- CapDepartamentDirectorGeneral
    4.- Secretari
    5.- EncarregatCompres
    6.- RecursosHumans
    */
    public static final int MENU_FIRMA_TIPUS_CARREC = 8;

    public static final int[] MENU_FIRMA_TIPUS = { MENU_FIRMA_TIPUS_AUTOFIRMA, MENU_FIRMA_TIPUS_PER_NIF,
            MENU_FIRMA_TIPUS_FLUX, MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI, MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT,
            MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT, MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON, MENU_FIRMA_TIPUS_CARREC };

}
