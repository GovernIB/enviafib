
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class PeticioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public PeticioQueryPath() {
  }

  protected PeticioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), PeticioFields.NOM);
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

  public LongField ESTAT() {
    return new LongField(getQueryPath(), PeticioFields.ESTAT);
  }

  public LongField FITXERFIRMATID() {
    return new LongField(getQueryPath(), PeticioFields.FITXERFIRMATID);
  }

  public StringField TIPUSDOCUMENTAL() {
    return new StringField(getQueryPath(), PeticioFields.TIPUSDOCUMENTAL);
  }

  public StringField IDIOMADOC() {
    return new StringField(getQueryPath(), PeticioFields.IDIOMADOC);
  }

  public LongField INFOSIGNATURAID() {
    return new LongField(getQueryPath(), PeticioFields.INFOSIGNATURAID);
  }

  public IntegerField TIPUS() {
    return new IntegerField(getQueryPath(), PeticioFields.TIPUS);
  }

  public StringField ERRORMSG() {
    return new StringField(getQueryPath(), PeticioFields.ERRORMSG);
  }

  public StringField ERROREXCEPTION() {
    return new StringField(getQueryPath(), PeticioFields.ERROREXCEPTION);
  }

  public TimestampField DATAFINAL() {
    return new TimestampField(getQueryPath(), PeticioFields.DATAFINAL);
  }

  public StringField PETICIOPORTAFIRMES() {
    return new StringField(getQueryPath(), PeticioFields.PETICIOPORTAFIRMES);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PeticioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
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

  public InfoSignaturaQueryPath INFOSIGNATURA() {
    return new InfoSignaturaQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "infoSignatura" + ".";
      }
    });
  }

}
