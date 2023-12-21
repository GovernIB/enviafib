
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class InfoAnexJPAManager
         extends AbstractJPAManager<InfoAnex, Long>
         implements InfoAnexIJPAManager, IInfoAnexManager, InfoAnexFields {



    public static final TableName<InfoAnex> _TABLENAME =  new TableName<InfoAnex>("InfoAnexJPA");


    @PersistenceContext
    protected EntityManager __em;

    public InfoAnexJPAManager() {
    }

    protected InfoAnexJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return InfoAnexJPA. class;
    }



    public TableName<InfoAnex> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public InfoAnex[] listToArray(List<InfoAnex> list)  {
        if(list == null) { return null; };
        return list.toArray(new InfoAnex[list.size()]);
    };

    public InfoAnex create( java.lang.Long _peticioID_, java.lang.Long _anexID_) throws I18NException {
        InfoAnexJPA __bean =  new InfoAnexJPA(_peticioID_,_anexID_);
        return create(__bean);
    }



 public void delete(long _infoanexid_) {
   delete(findByPrimaryKey(_infoanexid_));
 }




    public InfoAnex findByPrimaryKey(long _infoanexid_) {
        return __em.find(InfoAnexJPA.class, _infoanexid_);  
    }
    @Override
    protected InfoAnex getJPAInstance(InfoAnex __bean) {
        return convertToJPA(__bean);
    }


    public static InfoAnexJPA convertToJPA(InfoAnex __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof InfoAnexJPA) {
        return (InfoAnexJPA)__bean;
      }
      
      return InfoAnexJPA.toJPA(__bean);
    }


}