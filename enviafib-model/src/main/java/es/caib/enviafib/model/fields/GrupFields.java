
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface GrupFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_grup";


  public static final String _TABLE_MODEL = "grup";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField GRUPID = new LongField(_TABLE_MODEL, "grupID", "grupid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_GRUP_FIELDS = {
    GRUPID,
    NOM,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
GRUPID
  };
}
