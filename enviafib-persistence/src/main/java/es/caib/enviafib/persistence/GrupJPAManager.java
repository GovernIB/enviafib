
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class GrupJPAManager
         extends AbstractJPAManager<Grup, Long>
         implements GrupIJPAManager, IGrupManager, GrupFields {



    public static final TableName<Grup> _TABLENAME =  new TableName<Grup>("GrupJPA");


    @PersistenceContext
    protected EntityManager __em;

    public GrupJPAManager() {
    }

    protected GrupJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return GrupJPA. class;
    }



    public TableName<Grup> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Grup[] listToArray(List<Grup> list)  {
        if(list == null) { return null; };
        return list.toArray(new Grup[list.size()]);
    };

    public Grup create( java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException {
        GrupJPA __bean =  new GrupJPA(_nom_,_descripcio_);
        return create(__bean);
    }



 public void delete(long _grupID_) {
   delete(findByPrimaryKey(_grupID_));
 }




    public Grup findByPrimaryKey(long _grupID_) {
        return __em.find(GrupJPA.class, _grupID_);  
    }
    @Override
    protected Grup getJPAInstance(Grup __bean) {
        return convertToJPA(__bean);
    }


    public static GrupJPA convertToJPA(Grup __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof GrupJPA) {
        return (GrupJPA)__bean;
      }
      
      return GrupJPA.toJPA(__bean);
    }


}