
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface InfoAnexFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_infoanex";


  public static final String _TABLE_MODEL = "infoAnex";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INFOANEXID = new LongField(_TABLE_MODEL, "infoanexid", "infoanexid");  // PK
	 public static final LongField PETICIOID = new LongField(_TABLE_MODEL, "peticioID", "peticioid");
	 public static final LongField ANEXID = new LongField(_TABLE_MODEL, "anexID", "anexid");


  public static final Field<?>[] ALL_INFOANEX_FIELDS = {
    INFOANEXID,
    PETICIOID,
    ANEXID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INFOANEXID
  };
}
