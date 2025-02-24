
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class AvisJPAManager
         extends AbstractJPAManager<Avis, Long>
         implements AvisIJPAManager, IAvisManager, AvisFields {



    public static final TableName<Avis> _TABLENAME =  new TableName<Avis>("AvisJPA");


    @PersistenceContext
    protected EntityManager __em;

    public AvisJPAManager() {
    }

    protected AvisJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return AvisJPA. class;
    }



    public TableName<Avis> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Avis[] listToArray(List<Avis> list)  {
        if(list == null) { return null; };
        return list.toArray(new Avis[list.size()]);
    };

    public Avis create( java.lang.String _missatge_, java.sql.Timestamp _datainici_, java.sql.Timestamp _datafi_, boolean _actiu_, java.lang.String _tipus_) throws I18NException {
        AvisJPA __bean =  new AvisJPA(_missatge_,_datainici_,_datafi_,_actiu_,_tipus_);
        return create(__bean);
    }



 public void delete(long _avisID_) {
   delete(findByPrimaryKey(_avisID_));
 }




    public Avis findByPrimaryKey(long _avisID_) {
        return __em.find(AvisJPA.class, _avisID_);  
    }
    @Override
    protected Avis getJPAInstance(Avis __bean) {
        return convertToJPA(__bean);
    }


    public static AvisJPA convertToJPA(Avis __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof AvisJPA) {
        return (AvisJPA)__bean;
      }
      
      return AvisJPA.toJPA(__bean);
    }


}