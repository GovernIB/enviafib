package org.fundaciobit.pluginsib.estructuraorganitzativa.database;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.fundaciobit.pluginsib.core.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * @author anadal
 */
public class DatabaseEstructuraOrganitzativaPlugin extends AbstractPluginProperties
        implements IEstructuraOrganitzativaPlugin {

    protected Logger log = Logger.getLogger(DatabaseEstructuraOrganitzativaPlugin.class);

    public static final String DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE = ESTRUCTURAORGANITZATIVA_PROPERTY_BASE
            + "database.";

    public static final String TIPUS_CARREC1_NAME = "CARREC1_NAME";
    public static final String TIPUS_CARREC1_USERNAME = "CARREC1_USERNAME";
    public static final String TIPUS_CARREC1_POSITION = "CARREC1_POSITION";

    public static final String TIPUS_CARREC2_NAME = "CARREC2_NAME";
    public static final String TIPUS_CARREC2_USERNAME = "CARREC2_USERNAME";
    public static final String TIPUS_CARREC2_POSITION = "CARREC2_POSITION";

    public static final String TIPUS_RECURSOSHUMANS_NAME = "RRHH_NAME";
    public static final String TIPUS_RECURSOSHUMANS_USERNAME = "RRHH_USERNAME";

    public static final String TIPUS_COMPRES_NAME = "COMPRES_NAME";
    public static final String TIPUS_COMPRES_USERNAME = "COMPRES_USERNAME";

    public static final String TIPUS_SECRETARI_NAME = "SEC_NAME";
    public static final String TIPUS_SECRETARI_USERNAME = "SEC_USERNAME";

    public static final String TIPUS_CAP_NAME = "CAP_NAME";
    public static final String TIPUS_CAP_USERNAME = "CAP_USERNAME";

    public static final String TIPUS_NAME = "NAME";

    public static final String TIPUS_DIR3 = "DIR3";
    public static final String TIPUS_NIF = "NIF";

    public DatabaseEstructuraOrganitzativaPlugin() {
        super();
    }

    public DatabaseEstructuraOrganitzativaPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    public DatabaseEstructuraOrganitzativaPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    /** =================  ORGANITZACIÓ - EMPRESA ========= */

    @Override
    public String getGerentPresidentName() throws Exception {
        return consultaSqlGerentPresident(TIPUS_CAP_NAME, null);
    }

    @Override
    public String getGerentPresidentUsername() throws Exception {
        return consultaSqlGerentPresident(TIPUS_CAP_USERNAME, null);
    }

    @Override
    public String getNameOrganitzacioEmpresa(String lang) throws Exception {
        return consultaSqlGerentPresident(TIPUS_NAME, lang);

    }

    @Override
    public String getDir3OrganitzacioEmpresa() throws Exception {
        return consultaSqlGerentPresident(TIPUS_DIR3, null);
    }

    @Override
    public String getNifOrganitzacioEmpresa() throws Exception {
        return consultaSqlGerentPresident(TIPUS_NIF, null);
    }

    /**
     * 
     * @param tipus
     * @param lang
     * @return
     * @throws Exception
     */
    protected String consultaSqlGerentPresident(final String tipus, String lang) throws Exception {
        Where w = new Where(tipus);
        w.setDepartament(null);
        w.setArea(null);
        w.setLanguage(lang);
        return consultaSql(w);
    }

    /** =================  ÀREA - CONSELLERIA ========= */

    @Override
    public String getCodeAreaConselleria(String username) throws Exception {
        final boolean isDepartament = false;
        return consultaPluginUserInformation(username, isDepartament);
    }

    @Override
    public String getNameAreaConselleria(String username, String lang) throws Exception {
        return consultaSqlAreaConselleria(lang, TIPUS_NAME, username);
    }

    @Override
    public String getDir3AreaConselleria(String username) throws Exception {
        String dir3 = consultaSqlAreaConselleria(null, TIPUS_DIR3, username);
        if (dir3 == null) {
            dir3 = getDir3OrganitzacioEmpresa();
        }
        return dir3;
    }

    @Override
    public String getCapAreaConsellerUsername(String username) throws Exception {
        return consultaSqlAreaConselleria(null, TIPUS_CAP_USERNAME, username);
    }

    @Override
    public String getCapAreaConsellerName(String username) throws Exception {
        return consultaSqlAreaConselleria(null, TIPUS_CAP_NAME, username);
    }

    /**
     * 
     * @param lang
     * @param tipus
     * @param username
     * @return
     * @throws Exception
     */
    protected String consultaSqlAreaConselleria(String lang, final String tipus, String username) throws Exception {

        String codiArea = getCodeAreaConselleria(username);

        if (codiArea == null) {
            log.warn("El codiArea de l'usuari " + username + " val null");
            return null;
        }

        Where w = new Where(tipus);
        w.setLanguage(lang);
        w.setArea(codiArea);
        w.setDepartament(null);

        return consultaSql(w);
    }

    /** =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= */

    @Override
    public String getNameDepartamentDireccioGeneral(String username, String lang) throws Exception {
        return consultaSqlDepartament(username, TIPUS_NAME, lang);
    }

    @Override
    public String getDir3DepartamentDireccioGeneral(String username) throws Exception {
        String dir3 = consultaSqlDepartament(username, TIPUS_DIR3, null);
        if (dir3 == null) {
            dir3 = getDir3AreaConselleria(username);
        }
        return dir3;
    }

    @Override
    public String getCodeDepartamentDireccioGeneral(String username) throws Exception {

        final boolean searchDepartament = true;

        return consultaPluginUserInformation(username, searchDepartament);

    }

    @Override
    public String getCapDepartamentDirectorGeneralUsername(String username) throws Exception {
        return consultaSqlDepartament(username, TIPUS_CAP_USERNAME, null);
    }

    @Override
    public String getCapDepartamentDirectorGeneralName(String username) throws Exception {
        return consultaSqlDepartament(username, TIPUS_CAP_NAME, null);
    }

    protected String consultaSqlDepartament(String username, String tipus, String lang) throws Exception {
        String departamentCodi = getCodeDepartamentDireccioGeneral(username);

        if (departamentCodi == null) {
            log.warn("El departamentCodi de l'usuari " + username + " val null");
            return null;
        }

        Where w = new Where(tipus);
        w.setDepartament(departamentCodi);
        w.setLanguage(lang);

        return consultaSql(w);
    }

    /** =================  DADES GENERALS ========= */

    @Override
    public String getSecretariUsername(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_SECRETARI_USERNAME);

    };

    public String getSecretariName(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_SECRETARI_NAME);
    }

    @Override
    public String getEncarregatCompresUsername(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_COMPRES_USERNAME);
    };

    @Override
    public String getEncarregatCompresName(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_COMPRES_NAME);
    }

    @Override
    public String getRecursosHumansUsername(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_RECURSOSHUMANS_USERNAME);
    }

    @Override
    public String getRecursosHumansName(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_RECURSOSHUMANS_NAME);
    }

    /** =================  CÀRRECS ADDICIONALS ========= */

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Username(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_CARREC1_USERNAME);
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Name(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_CARREC1_NAME);
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec1PositionName(String username, String lang) throws Exception {
        return consultaSqlGerentPresident(TIPUS_CARREC1_POSITION, null);
    }

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Username(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_CARREC2_USERNAME);
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Name(String username) throws Exception {
        return consultaSqlGeneral(username, TIPUS_CARREC2_NAME);
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec2PositionName(String username, String lang) throws Exception {
        return consultaSqlGerentPresident(TIPUS_CARREC2_POSITION, null);
    }

    protected String consultaSqlGeneral(String username, final String tipus) throws Exception {
        String departamentCodi = getCodeDepartamentDireccioGeneral(username);

        if (departamentCodi == null) {

            return null;
        }

        Where w = new Where(tipus);
        w.setDepartament(departamentCodi);

        // Cercam al Departament/Direccio general
        String result = consultaSql(w);

        if (result == null) {
            // Cercam a l'Area/Conselleria
            String codiArea = getCodeAreaConselleria(username);
            w.setDepartament(null);
            w.setArea(codiArea);

            result = consultaSql(w);

            if (result == null) {
                // Cercam a tota l'entitat
                w.setArea(null);
                result = consultaSql(w);
            }

        }

        return result;
    };

    /*
     * ==========================================================================
     * ========================  UTILITATS ===========================
     * ==========================================================================
     */

    protected String consultaPluginUserInformation(String username, boolean searchByDepartament) throws Exception {
        UserInfo user = getUserInfoFromUserInformation(username);

        if (user == null) {
            log.error("La consulta al UserInformation ha retornat null per l´username '" + username + "'",
                    new Exception());
            return null;
        }

        String codi = searchByDepartament ? user.getCompanyDepartment() : user.getCompanyArea();

        if (codi == null || codi.isBlank()) {
            log.warn("La consulta al UserInformation ha retornat null en el camp "
                    + (searchByDepartament ? "CompanyDepartment" : "CompanyArea") + " per l´username '" + username
                    + "'", new Exception());
            return null;
        }

        return codi;
    }

    public UserInfo getUserInfoFromUserInformation(String username) throws Exception {
        // TODO XYZ ZZZ Falta Cache !!!!!!

        IUserInformationPlugin plugin = getPluginUserInformation();

        UserInfo user = plugin.getUserInfoByUserName(username);

        return user;
    }

    /**
     * 
     */
    protected IUserInformationPlugin pluginUserInformation = null;

    public IUserInformationPlugin getPluginUserInformation() throws Exception {
        if (pluginUserInformation == null) {
            // Consultar Plugin de UserInformation
            String classUserInfo = getPropertyRequired(
                    DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "userinformation.class");
            String propertyBase = getPropertyRequired(
                    DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "userinformation.propertybase");

            String propertiesFile = getProperty(
                    DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "userinformation.propertiesfile");

            Properties props;
            if (propertiesFile == null) {
                props = System.getProperties();
            } else {
                props = new Properties();
                byte[] data = FileUtils.readFromFile(new File(propertiesFile));
                props.load(new ByteArrayInputStream(data));
            }

            pluginUserInformation = (IUserInformationPlugin) PluginsManager.instancePluginByClassName(classUserInfo,
                    propertyBase, props);
        }
        return pluginUserInformation;
    }

    /**
     * 
     * @param propietat
     * @param username
     * @return
     * @throws Exception
     */
    protected String processarPropietat(String propietat, String username) throws Exception {

        String valorPropietat = getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + propietat);

        if (valorPropietat.trim().length() == 0) {
            return null;
        }

        Map<String, Object> map = new HashMap<String, Object>();
        if (username != null) {
            map.put("username", username);
        }

        String plantilla = valorPropietat;
        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, new Locale("ca"));

        if (generat != null && generat.trim().length() == 0) {
            return null;
        }
        return generat;
    }

    protected DataSource dataSource = null;

    // Només per tests
    protected boolean closeConnection = true;

    // Només per tests
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.closeConnection = false;
    }

    /**
     * 
     * @param where
     * @return
     * @throws Exception
     */
    protected String consultaSql(Where where) throws Exception {

        String tipus = where.getTipus();
        if (where.getLanguage() != null) {
            tipus = tipus + "." + where.getLanguage();
        }

        String[][] map = new String[][] { { getTipusColumn(), tipus },
                { getDepartmentCodeColumn(), where.getDepartament() }, { getAreaCodeColumn(), where.getArea() }, };

        StringBuilder wBuilder = new StringBuilder();
        for (String[] w : map) {

            if (w[1] == null) {
                if (wBuilder.length() != 0) {
                    wBuilder.append(" and ");
                }
                wBuilder.append(w[0]).append(" is null");
            } else if (!w[1].isEmpty()) {
                if (wBuilder.length() != 0) {
                    wBuilder.append(" and ");
                }
                wBuilder.append(w[0] + "='" + w[1].replace("'", "''") + "'");
            }

        }

        // Execute SQL
        String query = "select " + getValorColumn() + " from " + getTableName() + " where " + wBuilder.toString();

        //System.out.println("\n\n" + query);

        if (dataSource == null) {

            Context ctx = new InitialContext();

            // java:comp/env/jdbc/mydatabase
            final String jndiDataSource = getPropertyRequired(
                    DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "datasourcejndi");

            dataSource = (DataSource) ctx.lookup(jndiDataSource);

        }

        // Create connection/statement variables outside of try block
        Connection c = null;
        java.sql.Statement s = null;

        try {
            // Get Connection and Statement from DataSource
            c = dataSource.getConnection();
            s = c.createStatement();

            s.execute(query);

            // Get result set
            ResultSet r = s.getResultSet();

            // Display data
            String resultat = null;
            while (r.next()) {
                resultat = r.getString(1);
                break;
            }

            r.close();

            return resultat;

        } catch (Exception e) {

            log.error(
                    "Error consultant la taula de BBDD de informació de l'estructura organitzativa: " + e.getMessage(),
                    e);

            return null;

        } finally {

            try {
                if (s != null) {
                    s.close();
                }
            } catch (Exception e) {
            }
            try {
                if (c != null && closeConnection) {
                    c.close();
                }
            } catch (Exception e) {
            }
        }

    }

    protected String getValorColumn() throws Exception {
        return getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "columns.valor");
    }

    protected String getTipusColumn() throws Exception {
        return getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "columns.tipus");
    }

    protected String getDepartmentCodeColumn() throws Exception {
        return getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "columns.departamentcodi");
    }

    protected String getAreaCodeColumn() throws Exception {
        return getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "columns.areacodi");
    }

    protected String getTableName() throws Exception {
        return getPropertyRequired(DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + "tablename");
    }

    /**
     * 
     * @author anadal
     *
     */
    protected class Where {

        final String tipus;
        String area = "";
        String departament = "";

        String language = null;

        public Where(String tipus) {
            this.tipus = tipus;
        }

        public Where(String area, String departament, String tipus, String language) {
            super();
            this.area = area;
            this.departament = departament;
            this.tipus = tipus;
            this.language = language;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getDepartament() {
            return departament;
        }

        public void setDepartament(String departament) {
            this.departament = departament;
        }

        public String getTipus() {
            return tipus;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

    }

}
