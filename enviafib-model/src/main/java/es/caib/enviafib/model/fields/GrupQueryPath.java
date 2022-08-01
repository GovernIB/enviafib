
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class GrupQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public GrupQueryPath() {
  }

  protected GrupQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField GRUPID() {
    return new LongField(getQueryPath(), GrupFields.GRUPID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), GrupFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), GrupFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (GrupFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public GrupUsuariQueryPath GRUPUSUARI() {
    return new GrupUsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return GrupQueryPath.this.getQueryPath() + "grupUsuari" + ".";
      }
    });
  }

}
