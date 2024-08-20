
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class EntitatJPAManager
         extends AbstractJPAManager<Entitat, String>
         implements EntitatIJPAManager, IEntitatManager, EntitatFields {



    public static final TableName<Entitat> _TABLENAME =  new TableName<Entitat>("EntitatJPA");


    @PersistenceContext
    protected EntityManager __em;

    public EntitatJPAManager() {
    }

    protected EntitatJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return EntitatJPA. class;
    }



    public TableName<Entitat> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Entitat[] listToArray(List<Entitat> list)  {
        if(list == null) { return null; };
        return list.toArray(new Entitat[list.size()]);
    };

    public Entitat create( java.lang.String _entitatid_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _adrezahtml_, boolean _activa_, java.lang.String _suporttelefon_, java.lang.String _suportweb_, java.lang.String _suportemail_, long _faviconID_, long _logowebID_, long _logowebpeuID_, long _logosegellID_, java.lang.String _web_, java.lang.Long _motiudelegacioID_, int _segelldetempsviaweb_, boolean _checkcanviatdocfirmat_, java.lang.String _propietatstaulafirmes_) throws I18NException {
        EntitatJPA __bean =  new EntitatJPA(_entitatid_,_nom_,_descripcio_,_adrezahtml_,_activa_,_suporttelefon_,_suportweb_,_suportemail_,_faviconID_,_logowebID_,_logowebpeuID_,_logosegellID_,_web_,_motiudelegacioID_,_segelldetempsviaweb_,_checkcanviatdocfirmat_,_propietatstaulafirmes_);
        return create(__bean);
    }



 public void delete(java.lang.String _entitatid_) {
   delete(findByPrimaryKey(_entitatid_));
 }




    public Entitat findByPrimaryKey(java.lang.String _entitatid_) {
        return __em.find(EntitatJPA.class, _entitatid_);  
    }
    @Override
    protected Entitat getJPAInstance(Entitat __bean) {
        return convertToJPA(__bean);
    }


    public static EntitatJPA convertToJPA(Entitat __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof EntitatJPA) {
        return (EntitatJPA)__bean;
      }
      
      return EntitatJPA.toJPA(__bean);
    }

  @Override
  public Entitat create(Entitat transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Entitat update(Entitat transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Entitat transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getMotiudelegacioID() == null) {
        if (transientInstance instanceof EntitatJPA) {
          EntitatJPA _jpa = (EntitatJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getMotiudelegacio();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setMotiudelegacioID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}