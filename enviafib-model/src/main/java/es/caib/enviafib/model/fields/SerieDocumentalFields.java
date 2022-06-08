
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SerieDocumentalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_seriedocumental";


  public static final String _TABLE_MODEL = "serieDocumental";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SERIEDOCUID = new LongField(_TABLE_MODEL, "serieDocuID", "seriedocumentalid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField TIPUSDOCUMENTAL = new StringField(_TABLE_MODEL, "tipusDocumental", "tipusdocumental");


  public static final Field<?>[] ALL_SERIEDOCUMENTAL_FIELDS = {
    SERIEDOCUID,
    NOM,
    TIPUSDOCUMENTAL
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SERIEDOCUID
  };
}
