
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

    public Peticio create( long _titolID_, java.sql.Timestamp _datacreacio_, long _fitxerID_, long _solicitantID_, java.lang.String _idiomaID_, java.lang.String _destinatarinif_, long _estat_, java.lang.Long _fitxerFirmatID_, java.lang.Long _peticioPortafib_, java.lang.String _tipusdocumental_, java.lang.String _idiomadoc_, java.lang.Long _infosignaturaid_) throws I18NException {
        PeticioJPA __bean =  new PeticioJPA(_titolID_,_datacreacio_,_fitxerID_,_solicitantID_,_idiomaID_,_destinatarinif_,_estat_,_fitxerFirmatID_,_peticioPortafib_,_tipusdocumental_,_idiomadoc_,_infosignaturaid_);
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

  @Override
  public Peticio create(Peticio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Peticio update(Peticio transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Peticio transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getTitolID() == 0) {
        if (transientInstance instanceof PeticioJPA) {
          PeticioJPA _jpa = (PeticioJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getTitol();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setTitolID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}