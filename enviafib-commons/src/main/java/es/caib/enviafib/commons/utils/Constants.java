package es.caib.enviafib.commons.utils;

/**
 *
 * @author anadal
 *
 */
public interface Constants {

  public static final String ENVIAFIB_PROPERTY_BASE="es.caib.enviafib.";

    public static final String MAIL_SERVICE = "java:/es.caib.enviafib.mail";

    // TRUE ROLES
    public static final String EFI_ADMIN="EFI_ADMIN";
    public static final String EFI_USER="EFI_USER";

    // VIRTUAL SECURITY ROLES
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    
    // EJB HIGH LEVEL ROLES
    public static final String ROLE_EJB_FULL_ACCESS  = EFI_ADMIN;
    public static final String ROLE_EJB_BASIC_ACCESS = EFI_USER;
    
    public static final int ESTAT_PETICIO_CREADA = 1;
    public static final int ESTAT_PETICIO_EN_PROCES = 2;
    public static final int ESTAT_PETICIO_FIRMADA = 3;
    public static final int ESTAT_PETICIO_REBUTJADA = 4;
    

}
