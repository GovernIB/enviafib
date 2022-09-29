package org.fundaciobit.pluginsib.estructuraorganitzativa.database.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.sql.DataSource;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.estructuraorganitzativa.database.DatabaseEstructuraOrganitzativaPlugin;
import org.hibernate.Session;
import org.junit.Test;

import es.caib.enviafib.model.EnviaFIBDaoManager;
import es.caib.enviafib.persistence.EnviaFIBJPADaoManagers;

/**
 * 
 * @author anadal
 *
 */
public class TestDatabasePlugin {

    private static final String PACKAGE_BASE = "es.caib.enviafib.";
    public static final String EOPB = PACKAGE_BASE
            + DatabaseEstructuraOrganitzativaPlugin.DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE;

    @Test
    public void executeMain() {
        main(null);
    }

    public static void main(String[] args) {

        try {
            

            Properties propGenerals = new Properties();
            {

                byte[] data = FileUtils.readFromFile(new File("generador.properties"));
                propGenerals.load(new ByteArrayInputStream(data));
            }

            DatabaseEstructuraOrganitzativaPlugin plugin;
            Properties propDatabase = new Properties();
            {
                byte[] data = FileUtils.readFromFile(new File("database.properties"));

                propDatabase.load(new ByteArrayInputStream(data));

                plugin = new DatabaseEstructuraOrganitzativaPlugin(PACKAGE_BASE, propDatabase);
            }

            // Carregar propietats de PluginUserInformation dins de System.properties
            {
                System.getProperties().putAll(propGenerals);

            }

            EntityManager entityManager = initDB();

            plugin.setDataSource(new HibernateDataSource(entityManager));

            String username = "jamer"; //"atrobat";//"ptrias"; //"fbosch"; //"anadal";

            //System.out.println("getGerentPresidentName() => " + plugin.getGerentPresidentName());

            provarTotsMetodes(plugin, username, "es");

            System.out.println(" -- FINAL --  ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void provarTotsMetodes(IEstructuraOrganitzativaPlugin plugin, String username, String lang)
            throws Exception {

        System.out.println(" =================  ENTITAT - ORGANITZACIÓ - EMPRESA ========= ");
        System.out.println("getGerentPresidentName() => " + plugin.getGerentPresidentName());
        System.out.println("getGerentPresidentUsername() => " + plugin.getGerentPresidentUsername());
        System.out.println("getNameOrganitzacioEmpresa(lang) => " + plugin.getNameOrganitzacioEmpresa(lang));
        System.out.println("getDir3OrganitzacioEmpresa() => " + plugin.getDir3OrganitzacioEmpresa());
        System.out.println("getNifOrganitzacioEmpresa() => " + plugin.getNifOrganitzacioEmpresa());

        System.out.println(" =================  ÀREA - CONSELLERIA ========= ");

        System.out.println("getNameAreaConselleria(String username, String lang) => "
                + plugin.getNameAreaConselleria(username, lang));
        System.out.println("getDir3AreaConselleria(username) => " + plugin.getDir3AreaConselleria(username));
        System.out.println("getCodeAreaConselleria(username) => " + plugin.getCodeAreaConselleria(username));
        System.out.println("getCapAreaConsellerUsername(username) => " + plugin.getCapAreaConsellerUsername(username));
        System.out.println("getCapAreaConsellerName(username) => " + plugin.getCapAreaConsellerName(username));
        System.out.println(" =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= ");
        System.out.println("getNameDepartamentDireccioGeneral(username, lang) => "
                + plugin.getNameDepartamentDireccioGeneral(username, lang));
        System.out.println(
                "getDir3DepartamentDireccioGeneral(username) => " + plugin.getDir3DepartamentDireccioGeneral(username));
        System.out.println(
                "getCodeDepartamentDireccioGeneral(username) => " + plugin.getCodeDepartamentDireccioGeneral(username));
        System.out.println("getCapDepartamentDirectorGeneralUsername(username) => "
                + plugin.getCapDepartamentDirectorGeneralUsername(username));
        System.out.println("getCapDepartamentDirectorGeneralName(username) => "
                + plugin.getCapDepartamentDirectorGeneralName(username));
        System.out.println(" =================  DADES GENERALS ========= ");
        System.out.println("getSecretariUsername(username) => " + plugin.getSecretariUsername(username));
        System.out.println("getSecretariName(username) => " + plugin.getSecretariName(username));
        System.out
                .println("getEncarregatCompresUsername(username) => " + plugin.getEncarregatCompresUsername(username));
        System.out.println("getEncarregatCompresName(username) => " + plugin.getEncarregatCompresName(username));
        System.out.println("getRecursosHumansUsername(username) => " + plugin.getRecursosHumansUsername(username));
        System.out.println("getRecursosHumansName(username) => " + plugin.getRecursosHumansName(username));
        System.out.println(" =================  CÀRRECS ADDICIONALS ========= ");
        System.out.println("getCarrec1Username(username) => " + plugin.getCarrec1Username(username));
        System.out.println("getCarrec1Name(username) => " + plugin.getCarrec1Name(username));
        System.out
                .println("getCarrec1PositionName(username, lang) => " + plugin.getCarrec1PositionName(username, lang));
        System.out.println("getCarrec2Username(username) => " + plugin.getCarrec2Username(username));
        System.out.println("getCarrec2Name(username) => " + plugin.getCarrec2Name(username));
        System.out
                .println("getCarrec2PositionName(username, lang) => " + plugin.getCarrec2PositionName(username, lang));
    }

    protected static void provarIdiomes(IEstructuraOrganitzativaPlugin plugin) throws Exception {
        String[] usernames = new String[] { "anadal", "jsastre", "eaguado", "other", "ptrias", "fbosch", "bverges" };

        for (String u : usernames) {
            System.out.println("Cap Dep. Username[" + u + "] => " + plugin.getCapDepartamentDirectorGeneralUsername(u));
            System.out.println("Cap. Dep. Name[" + u + "] => " + plugin.getCapDepartamentDirectorGeneralName(u));
        }
    }

    /**
     *   
     * @return
     */
    public static EntityManager initDB() {
        Properties prop = new Properties();

        prop.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost:5432/enviafib");
        prop.put("javax.persistence.jdbc.user", "enviafib");
        prop.put("javax.persistence.jdbc.password", "enviafib");

        prop.put("hibernate.connection.driver_class", "org.postgresql.Driver");
        prop.put("hibernate.connection.url", "jdbc:postgresql://localhost:5432/enviafib");
        prop.put("hibernate.connection.username", "enviafib");
        prop.put("hibernate.connection.password", "enviafib");

        prop.put("hibernate.show_sql", "true");

        EntityManagerFactory emf;

        // Veure persistence.xml
        emf = Persistence.createEntityManagerFactory("enviafibPULocal", prop);

        EntityManager em = emf.createEntityManager();

        em.setFlushMode(FlushModeType.AUTO);

        EnviaFIBDaoManager.setDaoManagers(new EnviaFIBJPADaoManagers(em));

        return em;
    }

    /**
     * 
     * @author anadal
     *
     */
    public static class HibernateDataSource implements DataSource {

        protected final EntityManager entityManager;

        public HibernateDataSource(EntityManager entityManager) {
            super();
            this.entityManager = entityManager;
        }

        @Override
        public java.util.logging.Logger getParentLogger() throws SQLFeatureNotSupportedException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public <T> T unwrap(Class<T> iface) throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public boolean isWrapperFor(Class<?> iface) throws SQLException {
            // TODO Auto-generated method stub
            return false;
        }

        public class InternalWork implements org.hibernate.jdbc.Work {

            Connection connection;

            @Override
            public void execute(Connection con) throws SQLException {
                // do whatever you need to do with the connection
                //System.out.println(" ============================ CON = " + con);
                this.connection = con;
            }

            public Connection getConnection() {
                return connection;
            }

            public void setConnection(Connection connection) {
                this.connection = connection;
            }

        }

        @Override
        public Connection getConnection() throws SQLException {
            // TODO Auto-generated method stub
            //return this.connection;

            Session hibernateSession = entityManager.unwrap(Session.class);

            InternalWork iw = new InternalWork();

            hibernateSession.doWork(iw);

            return iw.getConnection();

        }

        @Override
        public Connection getConnection(String username, String password) throws SQLException {
            // TODO Auto-generated method stub
            return this.getConnection();
        }

        @Override
        public PrintWriter getLogWriter() throws SQLException {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public void setLogWriter(PrintWriter out) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public void setLoginTimeout(int seconds) throws SQLException {
            // TODO Auto-generated method stub

        }

        @Override
        public int getLoginTimeout() throws SQLException {
            // TODO Auto-generated method stub
            return 0;
        }

    }

}
