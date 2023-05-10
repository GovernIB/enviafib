package org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchResult;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.utils.ldap.LDAPConstants;
import org.fundaciobit.pluginsib.utils.ldap.LDAPUser;
import org.fundaciobit.pluginsib.utils.ldap.LDAPUserManager;

/**
 * @author anadal
 */
public class LdapCaibEstructuraOrganitzativaPlugin extends AbstractPluginProperties
        implements IEstructuraOrganitzativaPlugin {

    protected Logger log = Logger.getLogger(LdapCaibEstructuraOrganitzativaPlugin.class);

    public static final String LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE = ESTRUCTURAORGANITZATIVA_PROPERTY_BASE
            + "ldapcaib.";

    public static final String LDAP_BASE_PROPERTIES = LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE;

    public LdapCaibEstructuraOrganitzativaPlugin() {
        super();
    }

    public LdapCaibEstructuraOrganitzativaPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    public LdapCaibEstructuraOrganitzativaPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /** =================  ORGANITZACIÓ - EMPRESA ========= */

    @Override
    public String getGerentPresidentName() throws Exception {
        return getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "gerentpresident." + "nom");
    }

    @Override
    public String getGerentPresidentUsername() throws Exception {
        return getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "gerentpresident." + "username");
    }

    @Override
    public String getNameOrganitzacioEmpresa(String lang) throws Exception {
        return getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "organitzacio." + "nom");
    }

    @Override
    public String getDir3OrganitzacioEmpresa() throws Exception {
        return getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "organitzacio." + "dir3");
    }

    @Override
    public String getNifOrganitzacioEmpresa() throws Exception {
        return getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "organitzacio." + "nif");
    }

    /** =================  ÀREA - CONSELLERIA ========= */

    @Override
    public String getCodeAreaConselleria(String username) throws Exception {
        String codiDep = getCodeDepartamentDireccioGeneral(username);
        Departament dep = getDepartamentInfoByCode(codiDep);
        return dep.getDir3pare();
    }

    @Override
    public String getNameAreaConselleria(String username, String lang) throws Exception {
        String dir3Area = getCodeAreaConselleria(username);
        return getNomByDir3Code(dir3Area);
    }

    @Override
    public String getDir3AreaConselleria(String username) throws Exception {
        return getCodeAreaConselleria(username);
    }

    @Override
    public String getCapAreaConsellerUsername(String username) throws Exception {

        String dir3 = getDir3AreaConselleria(username);

        if (dir3 == null) {
            throw new Exception("No s'ha trobat el codi DIR3 de l'Area/Conselleria on es troba l'usuari " + username);
        }

        // Check de ROLE_CAPAREA o Mapping
        {
            String mapping = getProperty(
                    LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "mappingdir3consellerusername");
            if (mapping != null) {
                throw new Exception(
                        "La propietat 'mappingdir3consellerusername' ja no s'utilitza i s'ha d'esborrar de les propietats");
            }
        }
        String roleConseller = getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "rolcaparea");
        String consellerUsername = getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "conselleria." + dir3);


        if (consellerUsername != null && roleConseller != null) {
            throw new Exception(
                    "No es pot definir a la vegada les propietats 'conselleria.dir3' i 'rolcaparea'");
        }

        if (consellerUsername == null && roleConseller == null) {
            throw new Exception(
                    "S'ha de definir com a mínim una de les propietats següents: 'conselleria.dir3' o 'rolcaparea'");
        }

        if (consellerUsername != null) {
            return consellerUsername;
        } else {
            LDAPUser user = getEncarregatAssociatAlRol(username, roleConseller);

            if (user == null) {
                log.warn("No s'ha trobat el Cap d'Area/Conseller on es troba l'usuari " + username);
                return null;
            } else {
                return user.getUserName();
            }
        }
    }

    @Override
    public String getCapAreaConsellerName(String username) throws Exception {
        String usernameConseller = getCapAreaConsellerUsername(username);

        if (usernameConseller == null) {
            throw new Exception("No s'ha trobat el username del Cap d'Area/Conseller on es troba l'usuari " + username);
        }

        try {

            LDAPUser user = getLDAPUserManager().getUserByUsername(usernameConseller);

            if (user == null) {
                throw new Exception("No s'ha trobat el informació del username " + usernameConseller);
            }

            return LDAPUser.getFullName(user);

        } catch (Exception e) {
            final String msg = "No puc trobar informació del nom del conseller amb username " + usernameConseller + ":"
                    + e.getMessage();
            log.error(msg, e);
            throw new Exception(msg, e);
        }

    }

    /** =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= */

    @Override
    public String getNameDepartamentDireccioGeneral(String username, String lang) throws Exception {
        String codiDep = getCodeDepartamentDireccioGeneral(username);
        Departament dep = getDepartamentInfoByCode(codiDep);
        return dep.getNom();
    }

    @Override
    public String getDir3DepartamentDireccioGeneral(String username) throws Exception {
        String codiDep = getCodeDepartamentDireccioGeneral(username);
        Departament dep = getDepartamentInfoByCode(codiDep);
        return dep.getDir3();
    }

    @Override
    public String getCodeDepartamentDireccioGeneral(String username) throws Exception {

            String codiDep = getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "user." + username);

            if (codiDep == null) {
                LDAPUser usuari = getLDAPUserManager().getUserByUsername(username);
                if (usuari == null) {
                    log.warn("No s'ha trobat informació de l'usuari " + username + " al servidor LDAP.");
                    codiDep = null;
                } else {
                    final String codiDepOrig = usuari.getDepartment();
                    codiDep = getProperty(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "group." + codiDepOrig);

                    if (codiDep == null) {
                        codiDep = codiDepOrig;
                    }
                }
            }
            return codiDep;
    }
    
    @Override
    public String getCapDepartamentDirectorGeneralUsername(String username) throws Exception {
        LDAPUser user = getCapDepartamentDirectorGeneral(username);

        if (user == null) {
            return null;
        } else {
            return user.getUserName();
        }
    }

    @Override
    public String getCapDepartamentDirectorGeneralName(String username) throws Exception {
        LDAPUser user = getCapDepartamentDirectorGeneral(username);

        if (user == null) {
            return null;
        } else {
            return LDAPUser.getFullName(user);
        }
    }

    protected LDAPUser getCapDepartamentDirectorGeneral(String username) throws Exception {
        final String rolDirectorGeneral = getPropertyRequired(
                LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "rolcapdepartament");

        LDAPUser user = getEncarregatAssociatAlRol(username, rolDirectorGeneral);

        if (user == null) {
            log.warn("No s'ha trobat el Cap de Departament/Director General on es troba l'usuari " + username);
        }

        return user;

    }

    /** =================  DADES GENERALS ========= */

    @Override
    public String getSecretariUsername(String username) throws Exception {
        LDAPUser user = getSecretari(username);

        if (user == null) {
            return null;
        } else {
            return user.getUserName();
        }

    };

    public String getSecretariName(String username) throws Exception {
        LDAPUser user = getSecretari(username);

        if (user == null) {
            return null;
        } else {
            return LDAPUser.getFullName(user);
        }
    }

    protected LDAPUser getSecretari(String username) throws Exception {
        final String rolSecretari = getPropertyRequired(
                LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "rolsecretari");

        LDAPUser user = getEncarregatAssociatAlRol(username, rolSecretari);

        if (user == null) {
            log.warn("No s'ha trobat el Secretari on es troba l'usuari " + username);
        }

        return user;

    }

    @Override
    public String getEncarregatCompresUsername(String username) throws Exception {
        return null;
    };

    @Override
    public String getEncarregatCompresName(String username) throws Exception {
        return null;
    }

    @Override
    public String getRecursosHumansUsername(String username) throws Exception {
        return null;
    }

    @Override
    public String getRecursosHumansName(String username) throws Exception {
        return null;
    }

    /** =================  CÀRRECS ADDICIONALS ========= */

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Username(String username) throws Exception {
        return null;
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Name(String username) throws Exception {
        return null;
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec1PositionName(String username, String lang) throws Exception {
        return null;
    }

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Username(String username) throws Exception {
        return null;
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Name(String username) throws Exception {
        return null;
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec2PositionName(String username, String lang) throws Exception {
        return null;
    }

    /*
     * ===============================================================
     * ========================  UTILITATS ===========================
     * ===============================================================
     * 
     */

    private LDAPUserManager ldapUserManager = null;

    public LDAPUserManager getLDAPUserManager() {

        if (ldapUserManager == null) {

            Properties ldapProperties = new Properties();
            for (String attrib : LDAPConstants.LDAP_PROPERTIES) {
                String value = getProperty(LDAP_BASE_PROPERTIES + attrib);
                if (value == null) {
                    if (!attrib.equals(LDAPConstants.LDAP_SEARCHFILTER)
                            && !attrib.equals(LDAPConstants.LDAP_ADDITIONAL_ATTRIBUTES)) {
                        System.err.println("Property[" + LDAP_BASE_PROPERTIES + attrib + "] is NULL");
                    }
                } else {
                    ldapProperties.setProperty(attrib, value);
                }
            }

            ldapUserManager = new LDAPUserManager(ldapProperties);
        }
        return ldapUserManager;
    }

    protected LDAPUser getEncarregatAssociatAlRol(String username, String rol) throws Exception {

        LDAPUser user = null;

        String codiDep = getCodeDepartamentDireccioGeneral(username);

        // TOD XYZ ZZZ Falta CAche
        List<LDAPUser> users = getLDAPUserManager().getUsersByRol(rol);

        for (LDAPUser ldapUser : users) {
            if (codiDep.equals(ldapUser.getDepartment())) {
                user = ldapUser;
                break;
            }
        }

        return user;
    }

    public Departament getDepartamentInfoByCode(String codiDep) throws Exception {

        // TODO Cache !!!!

        try {
            String filter = "cn=" + codiDep;

            List<String> attributesToRead = new ArrayList<String>();
            attributesToRead.add("description");
            attributesToRead.add("DIR3");
            attributesToRead.add("DIR3PARE");

            Map<String, String> map = getLdapQuery(filter, attributesToRead);

            Departament dep = new Departament();
            dep.setCodi(codiDep);
            dep.setNom(map.get("description"));
            dep.setDir3(map.get("DIR3"));
            dep.setDir3pare(map.get("DIR3PARE"));

            return dep;

        } catch (Exception e) {
            final String msg = "No puc trobar informació del departament/direcció general amb codi " + codiDep + ":"
                    + e.getMessage();
            log.error(msg, e);
            throw new Exception(msg, e);
        }
    }

    public Map<String, String> getLdapQuery(String filter, List<String> attributesToRead) throws Exception {

        LDAPUserManager ldap = getLDAPUserManager();

        NamingEnumeration<SearchResult> enumeration = ldap.searchLDAP(filter);

        while (enumeration.hasMore()) {
            SearchResult sr = enumeration.next();

            Attributes attrib = sr.getAttributes();

            Map<String, String> values = new HashMap<String, String>();

            for (String atribut : attributesToRead) {
                values.put(atribut, getValueLDAP(attrib, atribut));
            }

            return values;

        }

        throw new Exception("No existeix entrada a l'LDAP");

    }

    protected String getValueLDAP(Attributes attrib, String attribute) throws NamingException {
        String nom;
        Attribute givenName = attrib.get(attribute);
        if (givenName == null) {
            nom = null;
        } else {
            nom = (String) givenName.get();
        }
        return nom;
    }

    protected String getNomByDir3Code(String dir3) throws Exception {
        // TODO Cache
        String url = getPropertyRequired(LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "dir3host")
                + "/unidad/denominacion?codigo=" + dir3 + "&cooficial=true&estado=V";
        return getTextFromURL(url);
    }

    public static String getTextFromURL(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

}
