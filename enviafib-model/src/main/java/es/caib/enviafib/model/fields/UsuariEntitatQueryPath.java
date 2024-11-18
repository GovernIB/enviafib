
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class UsuariEntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public UsuariEntitatQueryPath() {
  }

  protected UsuariEntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField USUARIENTITATID() {
    return new LongField(getQueryPath(), UsuariEntitatFields.USUARIENTITATID);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), UsuariEntitatFields.USUARIID);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), UsuariEntitatFields.ENTITATID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (UsuariEntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

  public EntitatQueryPath ENTITAT() {
    return new EntitatQueryPath(new QueryPath() {
      public String getQueryPath() {
          return UsuariEntitatQueryPath.this.getQueryPath() + "entitat" + ".";
      }
    });
  }

}
