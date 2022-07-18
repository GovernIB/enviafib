
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class InfoCustodyQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public InfoCustodyQueryPath() {
  }

  protected InfoCustodyQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField INFOCUSTODYID() {
    return new LongField(getQueryPath(), InfoCustodyFields.INFOCUSTODYID);
  }

  public StringField CUSTODYID() {
    return new StringField(getQueryPath(), InfoCustodyFields.CUSTODYID);
  }

  public StringField ORIGINALFILEURL() {
    return new StringField(getQueryPath(), InfoCustodyFields.ORIGINALFILEURL);
  }

  public StringField CSV() {
    return new StringField(getQueryPath(), InfoCustodyFields.CSV);
  }

  public StringField CSVGENERATIONDEFINITION() {
    return new StringField(getQueryPath(), InfoCustodyFields.CSVGENERATIONDEFINITION);
  }

  public StringField CSVVALIDATIONWEB() {
    return new StringField(getQueryPath(), InfoCustodyFields.CSVVALIDATIONWEB);
  }

  public StringField ARXIUEXPEDIENTID() {
    return new StringField(getQueryPath(), InfoCustodyFields.ARXIUEXPEDIENTID);
  }

  public StringField ARXIUDOCUMENTID() {
    return new StringField(getQueryPath(), InfoCustodyFields.ARXIUDOCUMENTID);
  }

  public StringField PRINTABLEURL() {
    return new StringField(getQueryPath(), InfoCustodyFields.PRINTABLEURL);
  }

  public StringField ENIFILEURL() {
    return new StringField(getQueryPath(), InfoCustodyFields.ENIFILEURL);
  }

  public StringField VALIDATIONFILEURL() {
    return new StringField(getQueryPath(), InfoCustodyFields.VALIDATIONFILEURL);
  }

  public LongField PETICIOID() {
    return new LongField(getQueryPath(), InfoCustodyFields.PETICIOID);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (InfoCustodyFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


  public PeticioQueryPath PETICIO() {
    return new PeticioQueryPath(new QueryPath() {
      public String getQueryPath() {
          return InfoCustodyQueryPath.this.getQueryPath() + "peticio" + ".";
      }
    });
  }

}
