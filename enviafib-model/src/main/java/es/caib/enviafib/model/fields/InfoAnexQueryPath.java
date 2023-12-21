
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class InfoAnexQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public InfoAnexQueryPath() {
  }

  protected InfoAnexQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INFOANEXID() {
    return new LongField(getQueryPath(), InfoAnexFields.INFOANEXID);
  }

  public LongField PETICIOID() {
    return new LongField(getQueryPath(), InfoAnexFields.PETICIOID);
  }

  public LongField ANEXID() {
    return new LongField(getQueryPath(), InfoAnexFields.ANEXID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (InfoAnexFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PeticioQueryPath PETICIO() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoAnexQueryPath.this.getQueryPath() + "peticio" + ".";
      }
    });
  }

  public FitxerQueryPath ANEX() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoAnexQueryPath.this.getQueryPath() + "anex" + ".";
      }
    });
  }

}
