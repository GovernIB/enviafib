
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class OrganitzacioJPAManager
         extends AbstractJPAManager<Organitzacio, Long>
         implements OrganitzacioIJPAManager, IOrganitzacioManager, OrganitzacioFields {



    public static final TableName<Organitzacio> _TABLENAME =  new TableName<Organitzacio>("OrganitzacioJPA");


    @PersistenceContext
    protected EntityManager __em;

    public OrganitzacioJPAManager() {
    }

    protected OrganitzacioJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return OrganitzacioJPA. class;
    }



    public TableName<Organitzacio> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Organitzacio[] listToArray(List<Organitzacio> list)  {
        if(list == null) { return null; };
        return list.toArray(new Organitzacio[list.size()]);
    };

    public Organitzacio create( java.lang.String _codiConselleria_, java.lang.String _codiDireccioGeneral_, java.lang.String _tipus_, java.lang.String _valor_) throws I18NException {
        OrganitzacioJPA __bean =  new OrganitzacioJPA(_codiConselleria_,_codiDireccioGeneral_,_tipus_,_valor_);
        return create(__bean);
    }



 public void delete(long _organitzacioID_) {
   delete(findByPrimaryKey(_organitzacioID_));
 }




    public Organitzacio findByPrimaryKey(long _organitzacioID_) {
        return __em.find(OrganitzacioJPA.class, _organitzacioID_);  
    }
    @Override
    protected Organitzacio getJPAInstance(Organitzacio __bean) {
        return convertToJPA(__bean);
    }


    public static OrganitzacioJPA convertToJPA(Organitzacio __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof OrganitzacioJPA) {
        return (OrganitzacioJPA)__bean;
      }
      
      return OrganitzacioJPA.toJPA(__bean);
    }


}