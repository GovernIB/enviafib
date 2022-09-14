package es.caib.enviafib.persistence.test;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.persistence.EnviaFIBJPADaoManagers;
import es.caib.enviafib.model.EnviaFIBDaoManager;
import es.caib.enviafib.model.dao.IPeticioManager;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;

/**
 * 
 * @author anadal
 *
 */
public class TestPersistenceJPA {

    public static final Logger log = Logger.getLogger(TestPersistenceJPA.class);

    public static final void main(String[] args) {
        try {
            log.info(">>>>>>>>>>>>  Hello World!");

            // USING GENAPP
            // ============

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

            EntityTransaction tx = em.getTransaction();

            tx.begin();

            EnviaFIBDaoManager.setDaoManagers(new EnviaFIBJPADaoManagers(em));

            consultaNocturna();

            /*
             * EXEMPLE DE CRIDADA DIRECTE
             * 
             * 
             * String hsql = "SELECT " + PluginFields.NOMID.fullName + " FROM PluginJPA
             * plugin, + " ORDER BY " + PluginFields.NOMID.fullName + " DESC";
             * 
             * javax.persistence.Query qry = em.createQuery(hsql);
             * 
             * List<Object> list = qry.getResultList(); for (Object object : list) {
             * System.out.println("Object[] => " + object); }
             * 
             */

            /*
             * EXEMPLE DE LLISTAT
             * 
             * IPluginManager pluginMan =
             * EnviaFIBDaoManager.getDaoManagers().getPluginManager();
             * 
             * 
             * SelectTraduccio st = new SelectTraduccio(PluginFields.NOMID, "es");
             * 
             * List<String> noms = pluginMan.executeQuery(st, null);
             * 
             * for (String nom : noms) { System.out.println("NOM[" + nom + "]"); }
             * 
             */

            /*
             * CONSULTA IDIOMES DISPONIBLES IIdiomaManager idioma =
             * EnviaFIBDaoManager.getDaoManagers().getIdiomaManager();
             * 
             * List<Idioma> llist = idioma.select(new OrderBy(IdiomaFields.IDIOMAID,
             * OrderType.DESC));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             * System.out.println("===");
             * 
             * llist = idioma.select(IdiomaFields.NOM.like("%Cat%"));
             * 
             * for (Idioma idioma2 : llist) { System.out.println("Idoma222 = " +
             * idioma2.getIdiomaID() + " => " + idioma2.getNom()); }
             * 
             */

            tx.commit();
            log.info("<<<<<<<<<<<  Good Bye!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void consultaNocturna() throws Exception, I18NException {
        IPeticioManager peticionsDAO = EnviaFIBDaoManager.getDaoManagers().getPeticioManager();

        Where where = null; // ?????
        List<Peticio> peticions = peticionsDAO.select(where, new OrderBy(PeticioFields.PETICIOID));

        for (Peticio peticio : peticions) {
            System.out.println(peticio.getPeticioID() + " => " + peticio.getNom());
        }
    }

}
