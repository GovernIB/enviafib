
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class InfoSignaturaJPAManager
         extends AbstractJPAManager<InfoSignatura, Long>
         implements InfoSignaturaIJPAManager, IInfoSignaturaManager, InfoSignaturaFields {




    private static final long serialVersionUID = 1055205240L;

    public static final TableName<InfoSignatura> _TABLENAME =  new TableName<InfoSignatura>("InfoSignaturaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public InfoSignaturaJPAManager() {
    }

    protected InfoSignaturaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return InfoSignaturaJPA. class;
    }



    public TableName<InfoSignatura> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public InfoSignatura[] listToArray(List<InfoSignatura> list)  {
        if(list == null) { return null; };
        return list.toArray(new InfoSignatura[list.size()]);
    };

    public InfoSignatura create( int _signoperation_, java.lang.String _signtype_, java.lang.String _signalgorithm_, java.lang.Integer _signmode_, java.lang.Integer _signaturestablelocation_, java.lang.Boolean _timestampincluded_, java.lang.Boolean _policyincluded_, java.lang.String _enitipofirma_, java.lang.String _eniperfilfirma_, java.lang.String _enirolfirma_, java.lang.String _enisignername_, java.lang.String _enisigneradministrationid_, java.lang.String _enisignlevel_, java.lang.Boolean _checkadministrationidofsigner_, java.lang.Boolean _checkdocumentmodifications_, java.lang.Boolean _checkvalidationsignature_) throws I18NException {
        InfoSignaturaJPA __bean =  new InfoSignaturaJPA(_signoperation_,_signtype_,_signalgorithm_,_signmode_,_signaturestablelocation_,_timestampincluded_,_policyincluded_,_enitipofirma_,_eniperfilfirma_,_enirolfirma_,_enisignername_,_enisigneradministrationid_,_enisignlevel_,_checkadministrationidofsigner_,_checkdocumentmodifications_,_checkvalidationsignature_);
        return create(__bean);
    }



 public void delete(long _infosignaturaid_) {
   delete(findByPrimaryKey(_infosignaturaid_));
 }




    public InfoSignatura findByPrimaryKey(long _infosignaturaid_) {
        return __em.find(InfoSignaturaJPA.class, _infosignaturaid_);  
    }
    @Override
    protected InfoSignatura getJPAInstance(InfoSignatura __bean) {
        return convertToJPA(__bean);
    }


    public static InfoSignaturaJPA convertToJPA(InfoSignatura __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof InfoSignaturaJPA) {
        return (InfoSignaturaJPA)__bean;
      }
      
      return InfoSignaturaJPA.toJPA(__bean);
    }


}