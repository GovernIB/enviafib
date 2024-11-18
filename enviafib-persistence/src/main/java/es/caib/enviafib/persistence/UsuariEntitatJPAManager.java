
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariEntitatJPAManager
         extends AbstractJPAManager<UsuariEntitat, Long>
         implements UsuariEntitatIJPAManager, IUsuariEntitatManager, UsuariEntitatFields {



    public static final TableName<UsuariEntitat> _TABLENAME =  new TableName<UsuariEntitat>("UsuariEntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UsuariEntitatJPAManager() {
    }

    protected UsuariEntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UsuariEntitatJPA. class;
    }



    public TableName<UsuariEntitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public UsuariEntitat[] listToArray(List<UsuariEntitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new UsuariEntitat[list.size()]);
    };

    public UsuariEntitat create( long _usuariid_, java.lang.String _entitatid_) throws I18NException {
        UsuariEntitatJPA __bean =  new UsuariEntitatJPA(_usuariid_,_entitatid_);
        return create(__bean);
    }



 public void delete(long _usuarientitatid_) {
   delete(findByPrimaryKey(_usuarientitatid_));
 }




    public UsuariEntitat findByPrimaryKey(long _usuarientitatid_) {
        return __em.find(UsuariEntitatJPA.class, _usuarientitatid_);  
    }
    @Override
    protected UsuariEntitat getJPAInstance(UsuariEntitat __bean) {
        return convertToJPA(__bean);
    }


    public static UsuariEntitatJPA convertToJPA(UsuariEntitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UsuariEntitatJPA) {
        return (UsuariEntitatJPA)__bean;
      }
      
      return UsuariEntitatJPA.toJPA(__bean);
    }


}