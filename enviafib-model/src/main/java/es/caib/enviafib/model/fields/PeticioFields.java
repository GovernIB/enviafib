
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PeticioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_peticio";


  public static final String _TABLE_MODEL = "peticio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField PETICIOID = new LongField(_TABLE_MODEL, "peticioID", "peticioid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "datacreacio", "datacreacio");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField SOLICITANTID = new LongField(_TABLE_MODEL, "solicitantID", "solicitantid");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final StringField DESTINATARINIF = new StringField(_TABLE_MODEL, "destinatarinif", "destinatarinif");
	 public static final LongField ESTAT = new LongField(_TABLE_MODEL, "estat", "estat");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerFirmatID", "fitxer_firmatid");
	 public static final StringField TIPUSDOCUMENTAL = new StringField(_TABLE_MODEL, "tipusdocumental", "tipusdocumental");
	 public static final StringField IDIOMADOC = new StringField(_TABLE_MODEL, "idiomadoc", "idiomadoc");
	 public static final LongField INFOSIGNATURAID = new LongField(_TABLE_MODEL, "infosignaturaid", "infosignaturaid");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField ERRORMSG = new StringField(_TABLE_MODEL, "errorMsg", "errormsg");
	 public static final StringField ERROREXCEPTION = new StringField(_TABLE_MODEL, "errorException", "errorexception");
	 public static final TimestampField DATAFINAL = new TimestampField(_TABLE_MODEL, "dataFinal", "datafinal");
	 public static final StringField PETICIOPORTAFIRMES = new StringField(_TABLE_MODEL, "peticioPortafirmes", "peticioportafirmes");


  public static final Field<?>[] ALL_PETICIO_FIELDS = {
    NOM,
    PETICIOID,
    DATACREACIO,
    FITXERID,
    SOLICITANTID,
    IDIOMAID,
    DESTINATARINIF,
    ESTAT,
    FITXERFIRMATID,
    TIPUSDOCUMENTAL,
    IDIOMADOC,
    INFOSIGNATURAID,
    TIPUS,
    ERRORMSG,
    ERROREXCEPTION,
    DATAFINAL,
    PETICIOPORTAFIRMES
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PETICIOID
  };
}
