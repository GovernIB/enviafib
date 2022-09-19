
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class MenuJPAManager
         extends AbstractJPAManager<Menu, Long>
         implements MenuIJPAManager, IMenuManager, MenuFields {



    public static final TableName<Menu> _TABLENAME =  new TableName<Menu>("MenuJPA");


    @PersistenceContext
    protected EntityManager __em;

    public MenuJPAManager() {
    }

    protected MenuJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return MenuJPA. class;
    }



    public TableName<Menu> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Menu[] listToArray(List<Menu> list)  {
        if(list == null) { return null; };
        return list.toArray(new Menu[list.size()]);
    };

    public Menu create( java.lang.String _nom_, java.lang.String _descripcio_, long _titolMenuID_, long _ajudaMenuID_, int _ordre_, int _tipus_, java.lang.Long _grupID_, java.lang.String _parametreCombo_, java.lang.String _parametreText_, boolean _actiu_) throws I18NException {
        MenuJPA __bean =  new MenuJPA(_nom_,_descripcio_,_titolMenuID_,_ajudaMenuID_,_ordre_,_tipus_,_grupID_,_parametreCombo_,_parametreText_,_actiu_);
        return create(__bean);
    }



 public void delete(long _menuID_) {
   delete(findByPrimaryKey(_menuID_));
 }




    public Menu findByPrimaryKey(long _menuID_) {
        return __em.find(MenuJPA.class, _menuID_);  
    }
    @Override
    protected Menu getJPAInstance(Menu __bean) {
        return convertToJPA(__bean);
    }


    public static MenuJPA convertToJPA(Menu __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof MenuJPA) {
        return (MenuJPA)__bean;
      }
      
      return MenuJPA.toJPA(__bean);
    }

  @Override
  public Menu create(Menu transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.create(transientInstance);
  }


  @Override
  public Menu update(Menu transientInstance) throws I18NException {
    processTranslations(transientInstance);
    return super.update(transientInstance);
  }


  private void processTranslations(Menu transientInstance) {
    if (transientInstance != null) {
      if (transientInstance.getTitolMenuID() == 0) {
        if (transientInstance instanceof MenuJPA) {
          MenuJPA _jpa = (MenuJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getTitolMenu();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setTitolMenuID(_trad.getTraduccioID());
          }
        }
      }
      if (transientInstance.getAjudaMenuID() == 0) {
        if (transientInstance instanceof MenuJPA) {
          MenuJPA _jpa = (MenuJPA)transientInstance;
          TraduccioJPA _trad = _jpa.getAjudaMenu();
           if (_trad != null) {
            if (_trad.getTraduccioID() == 0) {
              getEntityManager().persist(_trad);
            } 
            transientInstance.setAjudaMenuID(_trad.getTraduccioID());
          }
        }
      }
    }
  }


}