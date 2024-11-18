
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface UsuariEntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_usuarientitat";


  public static final String _TABLE_MODEL = "usuariEntitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField USUARIENTITATID = new LongField(_TABLE_MODEL, "usuarientitatid", "usuarientitatid");  // PK
	 public static final LongField USUARIID = new LongField(_TABLE_MODEL, "usuariid", "usuariid");
	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatid", "entitatid");


  public static final Field<?>[] ALL_USUARIENTITAT_FIELDS = {
    USUARIENTITATID,
    USUARIID,
    ENTITATID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
USUARIENTITATID
  };
}
