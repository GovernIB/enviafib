
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class InfoArxiuJPAManager
         extends AbstractJPAManager<InfoArxiu, Long>
         implements InfoArxiuIJPAManager, IInfoArxiuManager, InfoArxiuFields {




    private static final long serialVersionUID = 1564833495L;

    public static final TableName<InfoArxiu> _TABLENAME =  new TableName<InfoArxiu>("InfoArxiuJPA");


    @PersistenceContext
    protected EntityManager __em;

    public InfoArxiuJPAManager() {
    }

    protected InfoArxiuJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return InfoArxiuJPA. class;
    }



    public TableName<InfoArxiu> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public InfoArxiu[] listToArray(List<InfoArxiu> list)  {
        if(list == null) { return null; };
        return list.toArray(new InfoArxiu[list.size()]);
    };

    public InfoArxiu create( java.lang.String _originalfileurl_, java.lang.String _csv_, java.lang.String _csvgenerationdefinition_, java.lang.String _csvvalidationweb_, java.lang.String _arxiuexpedientid_, java.lang.String _arxiudocumentid_, java.lang.String _printableurl_, java.lang.String _enifileurl_, java.lang.String _validationfileurl_) throws I18NException {
        InfoArxiuJPA __bean =  new InfoArxiuJPA(_originalfileurl_,_csv_,_csvgenerationdefinition_,_csvvalidationweb_,_arxiuexpedientid_,_arxiudocumentid_,_printableurl_,_enifileurl_,_validationfileurl_);
        return create(__bean);
    }



 public void delete(long _infoArxiuID_) {
   delete(findByPrimaryKey(_infoArxiuID_));
 }




    public InfoArxiu findByPrimaryKey(long _infoArxiuID_) {
        return __em.find(InfoArxiuJPA.class, _infoArxiuID_);  
    }
    @Override
    protected InfoArxiu getJPAInstance(InfoArxiu __bean) {
        return convertToJPA(__bean);
    }


    public static InfoArxiuJPA convertToJPA(InfoArxiu __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof InfoArxiuJPA) {
        return (InfoArxiuJPA)__bean;
      }
      
      return InfoArxiuJPA.toJPA(__bean);
    }


}