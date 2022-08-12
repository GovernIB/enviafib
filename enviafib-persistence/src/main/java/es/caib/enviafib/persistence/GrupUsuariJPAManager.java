
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class GrupUsuariJPAManager
         extends AbstractJPAManager<GrupUsuari, Long>
         implements GrupUsuariIJPAManager, IGrupUsuariManager, GrupUsuariFields {



    public static final TableName<GrupUsuari> _TABLENAME =  new TableName<GrupUsuari>("GrupUsuariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public GrupUsuariJPAManager() {
    }

    protected GrupUsuariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return GrupUsuariJPA. class;
    }



    public TableName<GrupUsuari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public GrupUsuari[] listToArray(List<GrupUsuari> list)  {
        if(list == null) { return null; };
        return list.toArray(new GrupUsuari[list.size()]);
    };

    public GrupUsuari create( long _grupID_, long _usuariID_) throws I18NException {
        GrupUsuariJPA __bean =  new GrupUsuariJPA(_grupID_,_usuariID_);
        return create(__bean);
    }



 public void delete(long _grupUsuariID_) {
   delete(findByPrimaryKey(_grupUsuariID_));
 }




    public GrupUsuari findByPrimaryKey(long _grupUsuariID_) {
        return __em.find(GrupUsuariJPA.class, _grupUsuariID_);  
    }
    @Override
    protected GrupUsuari getJPAInstance(GrupUsuari __bean) {
        return convertToJPA(__bean);
    }


    public static GrupUsuariJPA convertToJPA(GrupUsuari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof GrupUsuariJPA) {
        return (GrupUsuariJPA)__bean;
      }
      
      return GrupUsuariJPA.toJPA(__bean);
    }


}