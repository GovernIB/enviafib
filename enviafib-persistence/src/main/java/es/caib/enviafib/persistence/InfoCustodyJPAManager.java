
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class InfoCustodyJPAManager
         extends AbstractJPAManager<InfoCustody, Long>
         implements InfoCustodyIJPAManager, IInfoCustodyManager, InfoCustodyFields {




    private static final long serialVersionUID = -359396075L;

    public static final TableName<InfoCustody> _TABLENAME =  new TableName<InfoCustody>("InfoCustodyJPA");


    @PersistenceContext
    protected EntityManager __em;

    public InfoCustodyJPAManager() {
    }

    protected InfoCustodyJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return InfoCustodyJPA. class;
    }



    public TableName<InfoCustody> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public InfoCustody[] listToArray(List<InfoCustody> list)  {
        if(list == null) { return null; };
        return list.toArray(new InfoCustody[list.size()]);
    };

    public InfoCustody create( java.lang.String _custodyid_, java.lang.String _originalfileurl_, java.lang.String _csv_, java.lang.String _csvgenerationdefinition_, java.lang.String _csvvalidationweb_, java.lang.String _arxiuexpedientid_, java.lang.String _arxiudocumentid_, java.lang.String _printableurl_, java.lang.String _enifileurl_, java.lang.String _validationfileurl_, java.lang.Long _peticioid_) throws I18NException {
        InfoCustodyJPA __bean =  new InfoCustodyJPA(_custodyid_,_originalfileurl_,_csv_,_csvgenerationdefinition_,_csvvalidationweb_,_arxiuexpedientid_,_arxiudocumentid_,_printableurl_,_enifileurl_,_validationfileurl_,_peticioid_);
        return create(__bean);
    }



 public void delete(long _infocustodyid_) {
   delete(findByPrimaryKey(_infocustodyid_));
 }




    public InfoCustody findByPrimaryKey(long _infocustodyid_) {
        return __em.find(InfoCustodyJPA.class, _infocustodyid_);  
    }
    @Override
    protected InfoCustody getJPAInstance(InfoCustody __bean) {
        return convertToJPA(__bean);
    }


    public static InfoCustodyJPA convertToJPA(InfoCustody __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof InfoCustodyJPA) {
        return (InfoCustodyJPA)__bean;
      }
      
      return InfoCustodyJPA.toJPA(__bean);
    }


}