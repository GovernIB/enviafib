
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class EntitatQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public EntitatQueryPath() {
  }

  protected EntitatQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField ENTITATID() {
    return new StringField(getQueryPath(), EntitatFields.ENTITATID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), EntitatFields.NOM);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), EntitatFields.DESCRIPCIO);
  }

  public StringField ADREZAHTML() {
    return new StringField(getQueryPath(), EntitatFields.ADREZAHTML);
  }

  public BooleanField ACTIVA() {
    return new BooleanField(getQueryPath(), EntitatFields.ACTIVA);
  }

  public StringField SUPORTTELEFON() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTTELEFON);
  }

  public StringField SUPORTWEB() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTWEB);
  }

  public StringField SUPORTEMAIL() {
    return new StringField(getQueryPath(), EntitatFields.SUPORTEMAIL);
  }

  public LongField FAVICONID() {
    return new LongField(getQueryPath(), EntitatFields.FAVICONID);
  }

  public LongField LOGOWEBID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOWEBID);
  }

  public LongField LOGOWEBPEUID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOWEBPEUID);
  }

  public LongField LOGOSEGELLID() {
    return new LongField(getQueryPath(), EntitatFields.LOGOSEGELLID);
  }

  public StringField WEB() {
    return new StringField(getQueryPath(), EntitatFields.WEB);
  }

  public LongField MOTIUDELEGACIOID() {
    return new LongField(getQueryPath(), EntitatFields.MOTIUDELEGACIOID);
  }

  public IntegerField SEGELLDETEMPSVIAWEB() {
    return new IntegerField(getQueryPath(), EntitatFields.SEGELLDETEMPSVIAWEB);
  }

  public BooleanField CHECKCANVIATDOCFIRMAT() {
    return new BooleanField(getQueryPath(), EntitatFields.CHECKCANVIATDOCFIRMAT);
  }

  public StringField PROPIETATSTAULAFIRMES() {
    return new StringField(getQueryPath(), EntitatFields.PROPIETATSTAULAFIRMES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (EntitatFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public FitxerQueryPath FAVICON() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "favicon" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOWEB() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logoweb" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOWEBPEU() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logowebpeu" + ".";
      }
    });
  }

  public FitxerQueryPath LOGOSEGELL() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "logosegell" + ".";
      }
    });
  }

  public TraduccioQueryPath MOTIUDELEGACIO() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return EntitatQueryPath.this.getQueryPath() + "motiudelegacio" + ".";
      }
    });
  }

}
