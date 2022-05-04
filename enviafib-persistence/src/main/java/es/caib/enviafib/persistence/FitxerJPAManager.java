
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FitxerJPAManager
         extends AbstractJPAManager<Fitxer, Long>
         implements FitxerIJPAManager, IFitxerManager, FitxerFields {




    private static final long serialVersionUID = -310054906L;

    public static final TableName<Fitxer> _TABLENAME =  new TableName<Fitxer>("FitxerJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FitxerJPAManager() {
    }

    protected FitxerJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FitxerJPA. class;
    }



    public TableName<Fitxer> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Fitxer[] listToArray(List<Fitxer> list)  {
        if(list == null) { return null; };
        return list.toArray(new Fitxer[list.size()]);
    };

    public synchronized Fitxer create( java.lang.String _nom_, java.lang.String _mime_, long _tamany_, java.lang.String _descripcio_) throws I18NException {
        FitxerJPA __bean =  new FitxerJPA(_nom_,_mime_,_tamany_,_descripcio_);
        return create(__bean);
    }



 public void delete(long _fitxerID_) {
   delete(findByPrimaryKey(_fitxerID_));
 }




    public Fitxer findByPrimaryKey(long _fitxerID_) {
        return __em.find(FitxerJPA.class, _fitxerID_);  
    }
    @Override
    protected Fitxer getJPAInstance(Fitxer __bean) {
        return convertToJPA(__bean);
    }


    public static FitxerJPA convertToJPA(Fitxer __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FitxerJPA) {
        return (FitxerJPA)__bean;
      }
      
      return FitxerJPA.toJPA(__bean);
    }


}