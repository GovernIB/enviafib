
package es.caib.enviafib.persistence;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import es.caib.enviafib.model.entity.*;
import es.caib.enviafib.model.fields.*;
import es.caib.enviafib.model.dao.*;
import org.fundaciobit.genapp.common.query.TableName;
import org.fundaciobit.genapp.common.i18n.I18NException;


public class UsuariJPAManager
         extends AbstractJPAManager<Usuari, Long>
         implements UsuariIJPAManager, IUsuariManager, UsuariFields {



    public static final TableName<Usuari> _TABLENAME =  new TableName<Usuari>("UsuariJPA");


    @PersistenceContext
    protected EntityManager __em;

    public UsuariJPAManager() {
    }

    protected UsuariJPAManager(EntityManager __em) {
      this.__em = __em;
    }

    @Override
    public EntityManager getEntityManager() {
      return this.__em;
    }
    public Class<?> getJPAClass() {
        return UsuariJPA. class;
    }



    public TableName<Usuari> getTableName() {
        return _TABLENAME;
    }


    @Override
    protected String getTableNameVariable() {
        return _TABLE_MODEL;
    }


    public Usuari[] listToArray(List<Usuari> list)  {
        if(list == null) { return null; };
        return list.toArray(new Usuari[list.size()]);
    };

    public Usuari create( java.lang.String _username_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _nif_, java.lang.String _email_, java.lang.String _idiomaID_) throws I18NException {
        UsuariJPA __bean =  new UsuariJPA(_username_,_nom_,_llinatge1_,_llinatge2_,_nif_,_email_,_idiomaID_);
        return create(__bean);
    }



 public void delete(long _usuariID_) {
   delete(findByPrimaryKey(_usuariID_));
 }




    public Usuari findByPrimaryKey(long _usuariID_) {
        return __em.find(UsuariJPA.class, _usuariID_);  
    }
    @Override
    protected Usuari getJPAInstance(Usuari __bean) {
        return convertToJPA(__bean);
    }


    public static UsuariJPA convertToJPA(Usuari __bean) {
      if (__bean == null) {
        return null;
      }
      if(__bean instanceof UsuariJPA) {
        return (UsuariJPA)__bean;
      }
      
      return UsuariJPA.toJPA(__bean);
    }


}