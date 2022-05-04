
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PeticioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_peticio";


  public static final String _TABLE_MODEL = "peticio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TITOLID = new LongField(_TABLE_MODEL, "titolID", "titolid");
	 public static final LongField PETICIOID = new LongField(_TABLE_MODEL, "peticioID", "peticioid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "datacreacio", "datacreacio");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField SOLICITANTID = new LongField(_TABLE_MODEL, "solicitantID", "solicitantid");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final StringField DESTINATARINIF = new StringField(_TABLE_MODEL, "destinatarinif", "destinatarinif");


  public static final Field<?>[] ALL_PETICIO_FIELDS = {
    TITOLID,
    PETICIOID,
    DATACREACIO,
    FITXERID,
    SOLICITANTID,
    IDIOMAID,
    DESTINATARINIF
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PETICIOID
  };
}
