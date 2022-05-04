
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FitxerFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_fitxer";


  public static final String _TABLE_MODEL = "fitxer";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField MIME = new StringField(_TABLE_MODEL, "mime", "mime");
	 public static final LongField TAMANY = new LongField(_TABLE_MODEL, "tamany", "tamany");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");


  public static final Field<?>[] ALL_FITXER_FIELDS = {
    FITXERID,
    NOM,
    MIME,
    TAMANY,
    DESCRIPCIO
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FITXERID
  };
}
