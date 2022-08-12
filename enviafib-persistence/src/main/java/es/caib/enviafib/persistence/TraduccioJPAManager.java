
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class TraduccioJPAManager
         extends AbstractJPAManager<Traduccio, Long>
         implements TraduccioIJPAManager, ITraduccioManager, TraduccioFields {



    public static final TableName<Traduccio> _TABLENAME =  new TableName<Traduccio>("TraduccioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public TraduccioJPAManager() {
    }

    protected TraduccioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return TraduccioJPA. class;
    }



    public TableName<Traduccio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Traduccio[] listToArray(List<Traduccio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Traduccio[list.size()]);
    };

    public Traduccio create() throws I18NException {
        TraduccioJPA __bean =  new TraduccioJPA();
        return create(__bean);
    }



 public void delete(long _traduccioID_) {
   delete(findByPrimaryKey(_traduccioID_));
 }




    public Traduccio findByPrimaryKey(long _traduccioID_) {
        return __em.find(TraduccioJPA.class, _traduccioID_);  
    }
    @Override
    protected Traduccio getJPAInstance(Traduccio __bean) {
        return convertToJPA(__bean);
    }


    public static TraduccioJPA convertToJPA(Traduccio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof TraduccioJPA) {
        return (TraduccioJPA)__bean;
      }
      
      return TraduccioJPA.toJPA(__bean);
    }


}