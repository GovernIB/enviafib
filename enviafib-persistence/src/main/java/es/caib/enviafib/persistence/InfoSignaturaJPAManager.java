
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

    public InfoSignatura create( int _signOperation_, java.lang.String _signType_, java.lang.String _signAlgorithm_, java.lang.Integer _signMode_, java.lang.Integer _signaturesTableLocation_, java.lang.Boolean _timestampIncluded_, java.lang.Boolean _policyIncluded_, java.lang.String _eniTipoFirma_, java.lang.String _eniPerfilFirma_, java.lang.String _eniRolFirma_, java.lang.String _eniSignerName_, java.lang.String _eniSignerAdministrationId_, java.lang.String _eniSignLevel_, java.lang.Boolean _checkAdministrationIdOfSigner_, java.lang.Boolean _checkDocumentModifications_, java.lang.Boolean _checkValidationSignature_) throws I18NException {
        InfoSignaturaJPA __bean =  new InfoSignaturaJPA(_signOperation_,_signType_,_signAlgorithm_,_signMode_,_signaturesTableLocation_,_timestampIncluded_,_policyIncluded_,_eniTipoFirma_,_eniPerfilFirma_,_eniRolFirma_,_eniSignerName_,_eniSignerAdministrationId_,_eniSignLevel_,_checkAdministrationIdOfSigner_,_checkDocumentModifications_,_checkValidationSignature_);
        return create(__bean);
    }



 public void delete(long _infoSignaturaID_) {
   delete(findByPrimaryKey(_infoSignaturaID_));
 }




    public InfoSignatura findByPrimaryKey(long _infoSignaturaID_) {
        return __em.find(InfoSignaturaJPA.class, _infoSignaturaID_);  
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