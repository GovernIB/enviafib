
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface OrganitzacioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_organitzacio";


  public static final String _TABLE_MODEL = "organitzacio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField ORGANITZACIOID = new LongField(_TABLE_MODEL, "organitzacioID", "organitzacioid");  // PK
	 public static final StringField CODICONSELLERIA = new StringField(_TABLE_MODEL, "codiConselleria", "codiconselleria");
	 public static final StringField CODIDIRECCIOGENERAL = new StringField(_TABLE_MODEL, "codiDireccioGeneral", "codidirecciogeneral");
	 public static final StringField TIPUS = new StringField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField VALOR = new StringField(_TABLE_MODEL, "valor", "valor");


  public static final Field<?>[] ALL_ORGANITZACIO_FIELDS = {
    ORGANITZACIOID,
    CODICONSELLERIA,
    CODIDIRECCIOGENERAL,
    TIPUS,
    VALOR
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ORGANITZACIOID
  };
}
