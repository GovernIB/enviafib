
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GrupUsuariFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_grupusuari";


  public static final String _TABLE_MODEL = "grupUsuari";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GRUPUSUARIID = new LongField(_TABLE_MODEL, "grupUsuariID", "grupusuariid");  // PK
	 public static final LongField GRUPID = new LongField(_TABLE_MODEL, "grupID", "grupid");
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariID", "usuariid");


  public static final Field<?>[] ALL_GRUPUSUARI_FIELDS = {
    GRUPUSUARIID,
    GRUPID,
    USUARIID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GRUPUSUARIID
  };
}
