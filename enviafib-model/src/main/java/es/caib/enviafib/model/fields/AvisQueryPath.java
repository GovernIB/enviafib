
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class AvisQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public AvisQueryPath() {
  }

  protected AvisQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField AVISID() {
    return new LongField(getQueryPath(), AvisFields.AVISID);
  }

  public StringField MISSATGE() {
    return new StringField(getQueryPath(), AvisFields.MISSATGE);
  }

  public TimestampField DATAINICI() {
    return new TimestampField(getQueryPath(), AvisFields.DATAINICI);
  }

  public TimestampField DATAFI() {
    return new TimestampField(getQueryPath(), AvisFields.DATAFI);
  }

  public BooleanField ACTIU() {
    return new BooleanField(getQueryPath(), AvisFields.ACTIU);
  }

  public StringField TIPUS() {
    return new StringField(getQueryPath(), AvisFields.TIPUS);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (AvisFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
