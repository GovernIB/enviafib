
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class IdiomaJPAManager
         extends AbstractJPAManager<Idioma, String>
         implements IdiomaIJPAManager, IIdiomaManager, IdiomaFields {



    public static final TableName<Idioma> _TABLENAME =  new TableName<Idioma>("IdiomaJPA");


    @PersistenceContext
    protected EntityManager __em;

    public IdiomaJPAManager() {
    }

    protected IdiomaJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return IdiomaJPA. class;
    }



    public TableName<Idioma> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Idioma[] listToArray(List<Idioma> list)  {
        if(list == null) { return null; };
        return list.toArray(new Idioma[list.size()]);
    };

    public Idioma create( java.lang.String _idiomaID_, java.lang.String _nom_, boolean _suportat_, int _ordre_) throws I18NException {
        IdiomaJPA __bean =  new IdiomaJPA(_idiomaID_,_nom_,_suportat_,_ordre_);
        return create(__bean);
    }



 public void delete(java.lang.String _idiomaID_) {
   delete(findByPrimaryKey(_idiomaID_));
 }




    public Idioma findByPrimaryKey(java.lang.String _idiomaID_) {
        return __em.find(IdiomaJPA.class, _idiomaID_);  
    }
    @Override
    protected Idioma getJPAInstance(Idioma __bean) {
        return convertToJPA(__bean);
    }


    public static IdiomaJPA convertToJPA(Idioma __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof IdiomaJPA) {
        return (IdiomaJPA)__bean;
      }
      
      return IdiomaJPA.toJPA(__bean);
    }


}