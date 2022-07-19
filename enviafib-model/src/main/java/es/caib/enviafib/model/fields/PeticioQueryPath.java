
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

  public TimestampField DATAFINAL() {
    return new TimestampField(getQueryPath(), PeticioFields.DATAFINAL);
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

  public IntegerField ESTAT() {
    return new IntegerField(getQueryPath(), PeticioFields.ESTAT);
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

  public StringField PETICIOPORTAFIRMES() {
    return new StringField(getQueryPath(), PeticioFields.PETICIOPORTAFIRMES);
  }

  public StringField REASON() {
    return new StringField(getQueryPath(), PeticioFields.REASON);
  }

  public StringField ARXIUFUNCIONARIUSERNAME() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUFUNCIONARIUSERNAME);
  }

  public StringField ARXIUPARAMFUNCIONARINOM() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUPARAMFUNCIONARINOM);
  }

  public StringField ARXIUPARAMFUNCIONARINIF() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUPARAMFUNCIONARINIF);
  }

  public StringField ARXIUPARAMFUNCIONARIDIR3() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUPARAMFUNCIONARIDIR3);
  }

  public StringField ARXIUREQPARAMDOCESTATELABORA() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUREQPARAMDOCESTATELABORA);
  }

  public StringField ARXIUREQPARAMINTERESSATS() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUREQPARAMINTERESSATS);
  }

  public StringField ARXIUREQPARAMCIUTADANIF() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUREQPARAMCIUTADANIF);
  }

  public StringField ARXIUREQPARAMCIUTADANOM() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUREQPARAMCIUTADANOM);
  }

  public StringField ARXIUREQPARAMORGANS() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUREQPARAMORGANS);
  }

  public StringField ARXIUOPTPARAMPROCEDIMENTCODI() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUOPTPARAMPROCEDIMENTCODI);
  }

  public StringField ARXIUOPTPARAMPROCEDIMENTNOM() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUOPTPARAMPROCEDIMENTNOM);
  }

  public StringField ARXIUOPTPARAMSERIEDOCUMENTAL() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL);
  }

  public StringField ARXIUOPTPARAMEXPEDIENTID() {
    return new StringField(getQueryPath(), PeticioFields.ARXIUOPTPARAMEXPEDIENTID);
  }

  public IntegerField ARXIUREQPARAMORIGEN() {
    return new IntegerField(getQueryPath(), PeticioFields.ARXIUREQPARAMORIGEN);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (PeticioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public InfoCustodyQueryPath INFOCUSTODYS() {
    return new InfoCustodyQueryPath(new QueryPath() {
      public String getQueryPath() {
          return PeticioQueryPath.this.getQueryPath() + "infoCustodys" + ".";
      }
    });
  }
*/

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
