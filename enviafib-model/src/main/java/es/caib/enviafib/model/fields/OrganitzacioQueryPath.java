
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class OrganitzacioQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public OrganitzacioQueryPath() {
  }

  protected OrganitzacioQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField ORGANITZACIOID() {
    return new LongField(getQueryPath(), OrganitzacioFields.ORGANITZACIOID);
  }

  public StringField CODICONSELLERIA() {
    return new StringField(getQueryPath(), OrganitzacioFields.CODICONSELLERIA);
  }

  public StringField CODIDIRECCIOGENERAL() {
    return new StringField(getQueryPath(), OrganitzacioFields.CODIDIRECCIOGENERAL);
  }

  public StringField TIPUS() {
    return new StringField(getQueryPath(), OrganitzacioFields.TIPUS);
  }

  public StringField VALOR() {
    return new StringField(getQueryPath(), OrganitzacioFields.VALOR);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (OrganitzacioFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
