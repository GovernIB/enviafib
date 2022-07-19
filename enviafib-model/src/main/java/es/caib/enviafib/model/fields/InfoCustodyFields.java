
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface InfoCustodyFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_infocustody";


  public static final String _TABLE_MODEL = "infoCustody";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INFOCUSTODYID = new LongField(_TABLE_MODEL, "infocustodyid", "infocustodyid");  // PK
	 public static final StringField CUSTODYID = new StringField(_TABLE_MODEL, "custodyid", "custodyid");
	 public static final StringField ORIGINALFILEURL = new StringField(_TABLE_MODEL, "originalfileurl", "originalfileurl");
	 public static final StringField CSV = new StringField(_TABLE_MODEL, "csv", "csv");
	 public static final StringField CSVGENERATIONDEFINITION = new StringField(_TABLE_MODEL, "csvgenerationdefinition", "csvgenerationdefinition");
	 public static final StringField CSVVALIDATIONWEB = new StringField(_TABLE_MODEL, "csvvalidationweb", "csvvalidationweb");
	 public static final StringField ARXIUEXPEDIENTID = new StringField(_TABLE_MODEL, "arxiuexpedientid", "arxiuexpedientid");
	 public static final StringField ARXIUDOCUMENTID = new StringField(_TABLE_MODEL, "arxiudocumentid", "arxiudocumentid");
	 public static final StringField PRINTABLEURL = new StringField(_TABLE_MODEL, "printableurl", "printableurl");
	 public static final StringField ENIFILEURL = new StringField(_TABLE_MODEL, "enifileurl", "enifileurl");
	 public static final StringField VALIDATIONFILEURL = new StringField(_TABLE_MODEL, "validationfileurl", "validationfileurl");
	 public static final LongField PETICIOID = new LongField(_TABLE_MODEL, "peticioid", "peticioid");


  public static final Field<?>[] ALL_INFOCUSTODY_FIELDS = {
    INFOCUSTODYID,
    CUSTODYID,
    ORIGINALFILEURL,
    CSV,
    CSVGENERATIONDEFINITION,
    CSVVALIDATIONWEB,
    ARXIUEXPEDIENTID,
    ARXIUDOCUMENTID,
    PRINTABLEURL,
    ENIFILEURL,
    VALIDATIONFILEURL,
    PETICIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INFOCUSTODYID
  };
}
