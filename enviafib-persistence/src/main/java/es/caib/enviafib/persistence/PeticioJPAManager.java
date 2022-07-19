
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PeticioJPAManager
         extends AbstractJPAManager<Peticio, Long>
         implements PeticioIJPAManager, IPeticioManager, PeticioFields {




    private static final long serialVersionUID = 1786972657L;

    public static final TableName<Peticio> _TABLENAME =  new TableName<Peticio>("PeticioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PeticioJPAManager() {
    }

    protected PeticioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PeticioJPA. class;
    }



    public TableName<Peticio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Peticio[] listToArray(List<Peticio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Peticio[list.size()]);
    };

    public Peticio create( java.lang.String _nom_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataFinal_, long _fitxerID_, long _solicitantID_, java.lang.String _idiomaID_, java.lang.String _destinatariNif_, int _estat_, java.lang.Long _fitxerFirmatID_, java.lang.String _tipusDocumental_, java.lang.String _idiomaDoc_, java.lang.Long _infoSignaturaID_, int _tipus_, java.lang.String _errorMsg_, java.lang.String _errorException_, java.lang.String _peticioPortafirmes_, java.lang.String _reason_, java.lang.String _arxiuFuncionariUsername_, java.lang.String _arxiuParamFuncionariNom_, java.lang.String _arxiuParamFuncionariNif_, java.lang.String _arxiuParamFuncionariDir3_, java.lang.String _arxiuReqParamDocEstatElabora_, java.lang.String _arxiuReqParamInteressats_, java.lang.String _arxiuReqParamCiutadaNif_, java.lang.String _arxiuReqParamCiutadaNom_, java.lang.String _arxiuReqParamOrgans_, java.lang.String _arxiuOptParamProcedimentCodi_, java.lang.String _arxiuOptParamProcedimentNom_, java.lang.String _arxiuOptParamSerieDocumental_, java.lang.String _arxiuOptParamExpedientId_, java.lang.Integer _arxiuReqParamOrigen_) throws I18NException {
        PeticioJPA __bean =  new PeticioJPA(_nom_,_dataCreacio_,_dataFinal_,_fitxerID_,_solicitantID_,_idiomaID_,_destinatariNif_,_estat_,_fitxerFirmatID_,_tipusDocumental_,_idiomaDoc_,_infoSignaturaID_,_tipus_,_errorMsg_,_errorException_,_peticioPortafirmes_,_reason_,_arxiuFuncionariUsername_,_arxiuParamFuncionariNom_,_arxiuParamFuncionariNif_,_arxiuParamFuncionariDir3_,_arxiuReqParamDocEstatElabora_,_arxiuReqParamInteressats_,_arxiuReqParamCiutadaNif_,_arxiuReqParamCiutadaNom_,_arxiuReqParamOrgans_,_arxiuOptParamProcedimentCodi_,_arxiuOptParamProcedimentNom_,_arxiuOptParamSerieDocumental_,_arxiuOptParamExpedientId_,_arxiuReqParamOrigen_);
        return create(__bean);
    }



 public void delete(long _peticioID_) {
   delete(findByPrimaryKey(_peticioID_));
 }




    public Peticio findByPrimaryKey(long _peticioID_) {
        return __em.find(PeticioJPA.class, _peticioID_);  
    }
    @Override
    protected Peticio getJPAInstance(Peticio __bean) {
        return convertToJPA(__bean);
    }


    public static PeticioJPA convertToJPA(Peticio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PeticioJPA) {
        return (PeticioJPA)__bean;
      }
      
      return PeticioJPA.toJPA(__bean);
    }


}