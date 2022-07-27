
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class InfoArxiuQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public InfoArxiuQueryPath() {
  }

  protected InfoArxiuQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INFOARXIUID() {
    return new LongField(getQueryPath(), InfoArxiuFields.INFOARXIUID);
  }

  public StringField ORIGINALFILEURL() {
    return new StringField(getQueryPath(), InfoArxiuFields.ORIGINALFILEURL);
  }

  public StringField CSV() {
    return new StringField(getQueryPath(), InfoArxiuFields.CSV);
  }

  public StringField CSVGENERATIONDEFINITION() {
    return new StringField(getQueryPath(), InfoArxiuFields.CSVGENERATIONDEFINITION);
  }

  public StringField CSVVALIDATIONWEB() {
    return new StringField(getQueryPath(), InfoArxiuFields.CSVVALIDATIONWEB);
  }

  public StringField ARXIUEXPEDIENTID() {
    return new StringField(getQueryPath(), InfoArxiuFields.ARXIUEXPEDIENTID);
  }

  public StringField ARXIUDOCUMENTID() {
    return new StringField(getQueryPath(), InfoArxiuFields.ARXIUDOCUMENTID);
  }

  public StringField PRINTABLEURL() {
    return new StringField(getQueryPath(), InfoArxiuFields.PRINTABLEURL);
  }

  public StringField ENIFILEURL() {
    return new StringField(getQueryPath(), InfoArxiuFields.ENIFILEURL);
  }

  public StringField VALIDATIONFILEURL() {
    return new StringField(getQueryPath(), InfoArxiuFields.VALIDATIONFILEURL);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (InfoArxiuFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


/* L'ús d'aquest camp (OneToMany) llança una exception:
 [Illegal attempt to dereference a collection]

 // TODO Solució dins el mètode testOneByOneDirect de la classe TestJPA 

  public PeticioQueryPath PETICIOS() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoArxiuQueryPath.this.getQueryPath() + "peticios" + ".";
      }
    });
  }
*/

}
