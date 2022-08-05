
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class PluginJPAManager
         extends AbstractJPAManager<Plugin, Long>
         implements PluginIJPAManager, IPluginManager, PluginFields {




    private static final long serialVersionUID = -1591214331L;

    public static final TableName<Plugin> _TABLENAME =  new TableName<Plugin>("PluginJPA");


    @PersistenceContext
    protected EntityManager __em;

    public PluginJPAManager() {
    }

    protected PluginJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return PluginJPA. class;
    }



    public TableName<Plugin> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Plugin[] listToArray(List<Plugin> list)  {
        if(list == null) { return null; };
        return list.toArray(new Plugin[list.size()]);
    };

    public Plugin create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _classe_, java.lang.String _properties_, boolean _actiu_, int _tipus_) throws I18NException {
        PluginJPA __bean =  new PluginJPA(_nom_,_descripcio_,_classe_,_properties_,_actiu_,_tipus_);
        return create(__bean);
    }



 public void delete(long _pluginID_) {
   delete(findByPrimaryKey(_pluginID_));
 }




    public Plugin findByPrimaryKey(long _pluginID_) {
        return __em.find(PluginJPA.class, _pluginID_);  
    }
    @Override
    protected Plugin getJPAInstance(Plugin __bean) {
        return convertToJPA(__bean);
    }


    public static PluginJPA convertToJPA(Plugin __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof PluginJPA) {
        return (PluginJPA)__bean;
      }
      
      return PluginJPA.toJPA(__bean);
    }


}