
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class FaqJPAManager
         extends AbstractJPAManager<Faq, Long>
         implements FaqIJPAManager, IFaqManager, FaqFields {



    public static final TableName<Faq> _TABLENAME =  new TableName<Faq>("FaqJPA");


    @PersistenceContext
    protected EntityManager __em;

    public FaqJPAManager() {
    }

    protected FaqJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return FaqJPA. class;
    }



    public TableName<Faq> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Faq[] listToArray(List<Faq> list)  {
        if(list == null) { return null; };
        return list.toArray(new Faq[list.size()]);
    };

    public Faq create( java.lang.Long _ordre_, java.lang.String _enunciat_es_, java.lang.String _enunciat_ca_, java.lang.String _resposta_es_, java.lang.String _resposta_ca_, java.lang.Long _fitxer1ID_, java.lang.Long _fitxer2ID_, java.lang.Long _fitxer3ID_) throws I18NException {
        FaqJPA __bean =  new FaqJPA(_ordre_,_enunciat_es_,_enunciat_ca_,_resposta_es_,_resposta_ca_,_fitxer1ID_,_fitxer2ID_,_fitxer3ID_);
        return create(__bean);
    }



 public void delete(long _faqID_) {
   delete(findByPrimaryKey(_faqID_));
 }




    public Faq findByPrimaryKey(long _faqID_) {
        return __em.find(FaqJPA.class, _faqID_);  
    }
    @Override
    protected Faq getJPAInstance(Faq __bean) {
        return convertToJPA(__bean);
    }


    public static FaqJPA convertToJPA(Faq __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof FaqJPA) {
        return (FaqJPA)__bean;
      }
      
      return FaqJPA.toJPA(__bean);
    }


}