
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class FitxerQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public FitxerQueryPath() {
  }

  protected FitxerQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField FITXERID() {
    return new LongField(getQueryPath(), FitxerFields.FITXERID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), FitxerFields.NOM);
  }

  public StringField MIME() {
    return new StringField(getQueryPath(), FitxerFields.MIME);
  }

  public LongField TAMANY() {
    return new LongField(getQueryPath(), FitxerFields.TAMANY);
  }

  public StringField DESCRIPCIO() {
    return new StringField(getQueryPath(), FitxerFields.DESCRIPCIO);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (FitxerFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FaqQueryPath FAQ_FITXER1IDS() {
    return new FaqQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "faq_fitxer1ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FaqQueryPath FAQ_FITXER2IDS() {
    return new FaqQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "faq_fitxer2ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public FaqQueryPath FAQ_FITXER3IDS() {
    return new FaqQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "faq_fitxer3ids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public InfoAnexQueryPath INFOANEXS() {
    return new InfoAnexQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "infoAnexs" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIO_FITXER_FIRMATIDS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticio_fitxer_firmatids" + ".";
      }
    });
  }
*/

/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIO_FITXERIDS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return FitxerQueryPath.this.getQueryPath() + "peticio_fitxerids" + ".";
      }
    });
  }
*/

}
