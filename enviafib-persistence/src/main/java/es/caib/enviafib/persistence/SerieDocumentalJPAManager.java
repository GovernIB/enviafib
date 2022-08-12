
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class SerieDocumentalJPAManager
         extends AbstractJPAManager<SerieDocumental, Long>
         implements SerieDocumentalIJPAManager, ISerieDocumentalManager, SerieDocumentalFields {



    public static final TableName<SerieDocumental> _TABLENAME =  new TableName<SerieDocumental>("SerieDocumentalJPA");


    @PersistenceContext
    protected EntityManager __em;

    public SerieDocumentalJPAManager() {
    }

    protected SerieDocumentalJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return SerieDocumentalJPA. class;
    }



    public TableName<SerieDocumental> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public SerieDocumental[] listToArray(List<SerieDocumental> list)  {
        if(list == null) { return null; };
        return list.toArray(new SerieDocumental[list.size()]);
    };

    public SerieDocumental create( java.lang.String _nom_, java.lang.String _tipusDocumental_, java.lang.String _procedimentNom_, java.lang.String _procedimentCodi_) throws I18NException {
        SerieDocumentalJPA __bean =  new SerieDocumentalJPA(_nom_,_tipusDocumental_,_procedimentNom_,_procedimentCodi_);
        return create(__bean);
    }



 public void delete(long _serieDocumentalID_) {
   delete(findByPrimaryKey(_serieDocumentalID_));
 }




    public SerieDocumental findByPrimaryKey(long _serieDocumentalID_) {
        return __em.find(SerieDocumentalJPA.class, _serieDocumentalID_);  
    }
    @Override
    protected SerieDocumental getJPAInstance(SerieDocumental __bean) {
        return convertToJPA(__bean);
    }


    public static SerieDocumentalJPA convertToJPA(SerieDocumental __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof SerieDocumentalJPA) {
        return (SerieDocumentalJPA)__bean;
      }
      
      return SerieDocumentalJPA.toJPA(__bean);
    }


}