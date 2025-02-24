
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface AvisFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_avis";


  public static final String _TABLE_MODEL = "avis";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField AVISID = new LongField(_TABLE_MODEL, "avisID", "avisid");  // PK
	 public static final StringField MISSATGE = new StringField(_TABLE_MODEL, "missatge", "missatge");
	 public static final TimestampField DATAINICI = new TimestampField(_TABLE_MODEL, "datainici", "datainici");
	 public static final TimestampField DATAFI = new TimestampField(_TABLE_MODEL, "datafi", "datafi");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final StringField TIPUS = new StringField(_TABLE_MODEL, "tipus", "tipus");


  public static final Field<?>[] ALL_AVIS_FIELDS = {
    AVISID,
    MISSATGE,
    DATAINICI,
    DATAFI,
    ACTIU,
    TIPUS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
AVISID
  };
}
