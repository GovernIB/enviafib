
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PeticioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PeticioQueryPath() {
  }

  protected PeticioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField TITOLID() {
    return new LongField(getQueryPath(), PeticioFields.TITOLID);
  }

  public LongField PETICIOID() {
    return new LongField(getQueryPath(), PeticioFields.PETICIOID);
  }

  public TimestampField DATACREACIO() {
    return new TimestampField(getQueryPath(), PeticioFields.DATACREACIO);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), PeticioFields.FITXERID);
  }

  public LongField SOLICITANTID() {
    return new LongField(getQueryPath(), PeticioFields.SOLICITANTID);
  }

  public StringField IDIOMAID() {
    return new StringField(getQueryPath(), PeticioFields.IDIOMAID);
  }

  public StringField DESTINATARINIF() {
    return new StringField(getQueryPath(), PeticioFields.DESTINATARINIF);
  }

  public ShortField ESTAT() {
    return new ShortField(getQueryPath(), PeticioFields.ESTAT);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), PeticioFields.FITXERFIRMATID);
  }

  public LongField PORTAFIBID() {
    return new LongField(getQueryPath(), PeticioFields.PORTAFIBID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PeticioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public TraduccioQueryPath TITOL() {
    return new TraduccioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "titol" + ".";
      }
    });
  }

  public FitxerQueryPath FITXER() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "fitxer" + ".";
      }
    });
  }

  public UsuariQueryPath USUARI() {
    return new UsuariQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "usuari" + ".";
      }
    });
  }

  public IdiomaQueryPath IDIOMA() {
    return new IdiomaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "idioma" + ".";
      }
    });
  }

  public FitxerQueryPath FITXERFIRMAT() {
    return new FitxerQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "fitxerFirmat" + ".";
      }
    });
  }

}
