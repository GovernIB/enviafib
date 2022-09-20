package es.caib.enviafib.persistence.test;

import java.util.List;
import java.util.Properties;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.FlushModeType;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.persistence.EnviaFIBJPADaoManagers;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.EnviaFIBDaoManager;
import es.caib.enviafib.model.dao.IFitxerManager;
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

    @javax.annotation.Resource
    protected javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

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

            eliminarFitxersSignatsDeLocal();
            //consultaNocturna();

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
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void eliminarFitxersSignatsDeLocal() {
        try {
            IPeticioManager peticionsDAO = EnviaFIBDaoManager.getDaoManagers().getPeticioManager();
            IFitxerManager fitxerDAO = EnviaFIBDaoManager.getDaoManagers().getFitxerManager();

            //Llistat de peticions arxivades: 
            List<Long> fitxersFirmatsID = peticionsDAO.executeQuery(PeticioFields.FITXERFIRMATID,
                    Where.AND(PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA),
                            PeticioFields.FITXERFIRMATID.isNotNull()));

            java.util.Set<Long> fitxersEsborrar = new java.util.HashSet<Long>();

            // Borram fitxers a BD
            for (Long fitxerFirmatID : fitxersFirmatsID ) {

                
                Query query = peticionsDAO.getEntityManager().createQuery("update " + PeticioJPA.class.getSimpleName()
                        + " set " + PeticioFields.FITXERFIRMATID.javaName + " = null" + " where "
                        + PeticioFields.FITXERFIRMATID.javaName + " = ?0");
                
                query.setParameter(0, fitxerFirmatID);
//                query.executeUpdate();

                /*
                1019 => dcdcdc => 1233
                91 => nom => 1197
                1020 => Demode => 1237
                 */

                System.out.println("Esborrant fitxer " + fitxerFirmatID + " a BBDD");
                fitxerDAO.delete(fitxerFirmatID);
                fitxersEsborrar.add(fitxerFirmatID);
            }

            if (fitxersEsborrar.size() == 0) {
                System.out.println("No hi ha fitxers per esborrar!");
            } else {
                for (Long fitxerID : fitxersEsborrar) {
                    System.out.println("Fitxer " + fitxerID + " per esborrar fisic");
                }
            }

            /*
            // Borram fitxers fisic
            __tsRegistry.registerInterposedSynchronization(
                    new es.caib.enviafib.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));
             */

        } catch (I18NException e) {
            String msg = e.getMessage();
            System.out.println();
            log.error(msg, e);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
        }
    }

    protected static void consultaNocturna() throws Exception, I18NException {
        IPeticioManager peticionsDAO = EnviaFIBDaoManager.getDaoManagers().getPeticioManager();

        Where w1 = PeticioFields.TIPUS.notEqual(Constants.TIPUS_PETICIO_AUTOFIRMA);

        Where w2 = Where.OR(PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA),
                PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_ERROR));

        Where w3 = PeticioFields.PETICIOID.notIn(peticionsDAO.getSubQuery(PeticioFields.PETICIOID,
                PeticioFields.PETICIOPORTAFIRMES.like("JAESBORRAT%")));

        Where where = Where.AND(w1, w2, w3); // ?????
        List<Peticio> peticions = peticionsDAO.select(where, new OrderBy(PeticioFields.PETICIOID));

        for (Peticio peticio : peticions) {
            System.out.println(
                    peticio.getPeticioID() + " => " + peticio.getNom() + " => " + peticio.getPeticioPortafirmes());
        }
    }

}
