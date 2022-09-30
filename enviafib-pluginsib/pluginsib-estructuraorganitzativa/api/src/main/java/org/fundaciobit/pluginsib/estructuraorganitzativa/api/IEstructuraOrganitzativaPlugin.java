package org.fundaciobit.pluginsib.estructuraorganitzativa.api;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * 
 * @author anadal
 */
public interface IEstructuraOrganitzativaPlugin extends IPlugin {

    public static final String ESTRUCTURAORGANITZATIVA_PROPERTY_BASE = IPLUGINSIB_BASE_PROPERTIES
            + "estructuraorganitzativa.";

    /** =================  ENTITAT - ORGANITZACIÓ - EMPRESA ========= */

    public String getGerentPresidentName() throws Exception;

    public String getGerentPresidentUsername() throws Exception;

    public String getNameOrganitzacioEmpresa(String lang) throws Exception;

    public String getDir3OrganitzacioEmpresa() throws Exception;

    public String getNifOrganitzacioEmpresa() throws Exception;

    /** =================  ÀREA - CONSELLERIA ========= */

    public String getNameAreaConselleria(String username, String lang) throws Exception;

    public String getDir3AreaConselleria(String username) throws Exception;

    public String getCodeAreaConselleria(String username) throws Exception;

    public String getCapAreaConsellerUsername(String username) throws Exception;

    public String getCapAreaConsellerName(String username) throws Exception;

    /** =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= */

    public String getNameDepartamentDireccioGeneral(String username, String lang) throws Exception;

    public String getDir3DepartamentDireccioGeneral(String username) throws Exception;

    public String getCodeDepartamentDireccioGeneral(String username) throws Exception;

    public String getCapDepartamentDirectorGeneralUsername(String username) throws Exception;

    public String getCapDepartamentDirectorGeneralName(String username) throws Exception;

    /** =================  DADES GENERALS ========= */

    public String getSecretariUsername(String username) throws Exception;
    
    public String getSecretariName(String username) throws Exception;

    public String getEncarregatCompresUsername(String username) throws Exception;
    
    public String getEncarregatCompresName(String username) throws Exception;

    public String getRecursosHumansUsername(String username) throws Exception;
    
    public String getRecursosHumansName(String username) throws Exception;

    /** =================  CÀRRECS ADDICIONALS ========= */

    /* Username de la persona que ocupa aquest càrrec */
    public String getCarrec1Username(String username) throws Exception;

    /* Nom complet de la persona que ocupa aquest càrrec */
    public String getCarrec1Name(String username) throws Exception;

    /* Nom del càrrec que ocupa. */
    public String getCarrec1PositionName(String username, String lang) throws Exception;

    /* Username de la persona que ocupa aquest càrrec */
    public String getCarrec2Username(String username) throws Exception;

    /* Nom complet de la persona que ocupa aquest càrrec */
    public String getCarrec2Name(String username) throws Exception;

    /* Nom del càrrec que ocupa. */
    public String getCarrec2PositionName(String username, String lang) throws Exception;

}
