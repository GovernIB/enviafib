package es.caib.enviafib.persistence.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.fundaciobit.genapp.common.query.UpdateItem;
import org.fundaciobit.genapp.common.query.UpdateItemSql;
import org.fundaciobit.genapp.common.query.UpdateItemValue;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.model.EnviaFIBDaoManager;
import es.caib.enviafib.model.dao.IFitxerManager;
import es.caib.enviafib.model.fields.FitxerFields;

/**
 * 
 * @author anadal
 *
 */
public class TestUpdate {

    public static void main(String[] args) {

        EntityTransaction tx = null;
        try {

            EntityManager em = TestPersistenceJPA.initializeDataBase();

            IFitxerManager fitxerDAO = EnviaFIBDaoManager.getDaoManagers().getFitxerManager();

            tx = em.getTransaction();

            tx.begin();

            // Test 1-A 
            fitxerDAO.update(FitxerFields.DESCRIPCIO, "hola", null);

            // Test 1-B
            fitxerDAO.update(FitxerFields.DESCRIPCIO, null, null);

            // Test 2-A
            fitxerDAO.update(null,
                    new UpdateItemSql<String>(FitxerFields.DESCRIPCIO, FitxerFields.TAMANY.fullName + " + 4"));

            // Test 2-B
            fitxerDAO.update(FitxerFields.DESCRIPCIO, null, null);

            // Test 3-A

            {
                UpdateItem<String> u1 = new UpdateItemSql<String>(FitxerFields.DESCRIPCIO,
                        FitxerFields.TAMANY.fullName + " + 4");
                UpdateItem<String> u2 = new UpdateItemValue<String>(FitxerFields.MIME, "application/pdf2");
                Where where = FitxerFields.FITXERID.lessThan(1050L);
                fitxerDAO.update(u1, u2, where);
            }

            // Test 3-B

            {
                UpdateItem<String> u1 = new UpdateItemValue<String>(FitxerFields.DESCRIPCIO, null);
                UpdateItem<String> u2 = new UpdateItemValue<String>(FitxerFields.MIME, "application/pdf");
                Where where = FitxerFields.FITXERID.lessThan(1050L);
                fitxerDAO.update(u1, u2, where);
            }

            tx.commit();

            System.out.println("FINAL");

            System.exit(0);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();

            if (tx != null) {
                tx.rollback();
            }
        }

    }
}
