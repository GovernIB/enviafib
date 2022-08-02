package org.fundaciobit.pluginsib.estructuraorganitzativa.api;

import org.fundaciobit.pluginsib.core.IPlugin;

/**
 * 
 * 
 * @author anadal
 */
public interface IEstructuraOrganitzativaPlugin extends IPlugin {

    public static final String ESTRUCTURAORGANITZATIVA_PROPERTY_BASE = IPLUGINSIB_BASE_PROPERTIES + "estructuraorganizativa.";

    public String getCodiDIR3ByUsername(String username) throws Exception;

    public String getGerentPresident() throws Exception;

    public String getNomAreaConselleriaByUsername(String username, String lang) throws Exception;

    public String getCapAreaConsellerByUsername(String username) throws Exception;

    public String getNomDepartamentDireccioGeneralByUsername(String username, String lang) throws Exception;

    public String getCapDepartamentDirectorGeneralByUsername(String username) throws Exception;

    public String getSecretariByUsername(String username) throws Exception;

    public String getEncarregatCompresByUsername(String username) throws Exception;
    
    public String getRecursosHumansByUsername(String username) throws Exception;

}
