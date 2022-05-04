
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface TraduccioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_traduccio";


  public static final String _TABLE_MODEL = "traduccio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField TRADUCCIOID = new LongField(_TABLE_MODEL, "traduccioID", "traduccioid");  // PK


  public static final Field<?>[] ALL_TRADUCCIO_FIELDS = {
    TRADUCCIOID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
TRADUCCIOID
  };
}
