
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;

public class SerieDocumentalQueryPath extends org.fundaciobit.genapp.common.query.QueryPath {

  public SerieDocumentalQueryPath() {
  }

  protected SerieDocumentalQueryPath(QueryPath parentQueryPath) {
    super(parentQueryPath);
  }

  public LongField SERIEDOCUMENTALID() {
    return new LongField(getQueryPath(), SerieDocumentalFields.SERIEDOCUMENTALID);
  }

  public StringField NOM() {
    return new StringField(getQueryPath(), SerieDocumentalFields.NOM);
  }

  public StringField TIPUSDOCUMENTAL() {
    return new StringField(getQueryPath(), SerieDocumentalFields.TIPUSDOCUMENTAL);
  }

  public StringField PROCEDIMENTNOM() {
    return new StringField(getQueryPath(), SerieDocumentalFields.PROCEDIMENTNOM);
  }

  public StringField PROCEDIMENTCODI() {
    return new StringField(getQueryPath(), SerieDocumentalFields.PROCEDIMENTCODI);
  }



  @Override
  public String getQueryPath() {
    return ((this.parentQueryPath == null) ? (SerieDocumentalFields._TABLE_MODEL + ".")
        : this.parentQueryPath.getQueryPath());
  }


}
