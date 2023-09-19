
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FaqQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FaqQueryPath() {
  }

  protected FaqQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FAQID() {
    return new LongField(getQueryPath(), FaqFields.FAQID);
  }

  public LongField ORDRE() {
    return new LongField(getQueryPath(), FaqFields.ORDRE);
  }

  public StringField ENUNCIAT_ES() {
    return new StringField(getQueryPath(), FaqFields.ENUNCIAT_ES);
  }

  public StringField ENUNCIAT_CA() {
    return new StringField(getQueryPath(), FaqFields.ENUNCIAT_CA);
  }

  public StringField RESPOSTA_ES() {
    return new StringField(getQueryPath(), FaqFields.RESPOSTA_ES);
  }

  public StringField RESPOSTA_CA() {
    return new StringField(getQueryPath(), FaqFields.RESPOSTA_CA);
  }

  public LongField FITXER1ID() {
    return new LongField(getQueryPath(), FaqFields.FITXER1ID);
  }

  public LongField FITXER2ID() {
    return new LongField(getQueryPath(), FaqFields.FITXER2ID);
  }

  public LongField FITXER3ID() {
    return new LongField(getQueryPath(), FaqFields.FITXER3ID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FaqFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FitxerQueryPath FITXER1() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FaqQueryPath.this.getQueryPath() + "fitxer1" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER2() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FaqQueryPath.this.getQueryPath() + "fitxer2" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER3() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FaqQueryPath.this.getQueryPath() + "fitxer3" + ".";
      }
    });
  }

}
