
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface InfoArxiuFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_infoarxiu";


  public static final String _TABLE_MODEL = "infoArxiu";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INFOARXIUID = new LongField(_TABLE_MODEL, "infoArxiuID", "infoarxiuid");  // PK
	 public static final StringField ORIGINALFILEURL = new StringField(_TABLE_MODEL, "originalfileurl", "originalfileurl");
	 public static final StringField CSV = new StringField(_TABLE_MODEL, "csv", "csv");
	 public static final StringField CSVGENERATIONDEFINITION = new StringField(_TABLE_MODEL, "csvgenerationdefinition", "csvgenerationdefinition");
	 public static final StringField CSVVALIDATIONWEB = new StringField(_TABLE_MODEL, "csvvalidationweb", "csvvalidationweb");
	 public static final StringField ARXIUEXPEDIENTID = new StringField(_TABLE_MODEL, "arxiuexpedientid", "arxiuexpedientid");
	 public static final StringField ARXIUDOCUMENTID = new StringField(_TABLE_MODEL, "arxiudocumentid", "arxiudocumentid");
	 public static final StringField PRINTABLEURL = new StringField(_TABLE_MODEL, "printableurl", "printableurl");
	 public static final StringField ENIFILEURL = new StringField(_TABLE_MODEL, "enifileurl", "enifileurl");
	 public static final StringField VALIDATIONFILEURL = new StringField(_TABLE_MODEL, "validationfileurl", "validationfileurl");


  public static final Field<?>[] ALL_INFOARXIU_FIELDS = {
    INFOARXIUID,
    ORIGINALFILEURL,
    CSV,
    CSVGENERATIONDEFINITION,
    CSVVALIDATIONWEB,
    ARXIUEXPEDIENTID,
    ARXIUDOCUMENTID,
    PRINTABLEURL,
    ENIFILEURL,
    VALIDATIONFILEURL
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INFOARXIUID
  };
}
