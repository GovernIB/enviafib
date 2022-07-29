
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupUsuariQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupUsuariQueryPath() {
  }

  protected GrupUsuariQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPUSUARIID() {
    return new LongField(getQueryPath(), GrupUsuariFields.GRUPUSUARIID);
  }

  public LongField GRUPID() {
    return new LongField(getQueryPath(), GrupUsuariFields.GRUPID);
  }

  public LongField USUARIID() {
    return new LongField(getQueryPath(), GrupUsuariFields.USUARIID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupUsuariFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public GrupQueryPath GRUP() {
    return new GrupQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupUsuariQueryPath.this.getQueryPath() + "grup" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupUsuariQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

}
