
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface MenuFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_menu";


  public static final String _TABLE_MODEL = "menu";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField MENUID = new LongField(_TABLE_MODEL, "menuID", "menuid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final LongField TITOLMENUID = new LongField(_TABLE_MODEL, "titolMenuID", "titolmenuid");
	 public static final LongField AJUDAMENUID = new LongField(_TABLE_MODEL, "ajudaMenuID", "ajudamenuid");
	 public static final IntegerField ORDRE = new IntegerField(_TABLE_MODEL, "ordre", "ordre");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final LongField GRUPID = new LongField(_TABLE_MODEL, "grupID", "grupid");
	 public static final StringField PARAMETRECOMBO = new StringField(_TABLE_MODEL, "parametreCombo", "parametrecombo");
	 public static final StringField PARAMETRETEXT = new StringField(_TABLE_MODEL, "parametreText", "parametretext");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");


  public static final Field<?>[] ALL_MENU_FIELDS = {
    MENUID,
    NOM,
    DESCRIPCIO,
    TITOLMENUID,
    AJUDAMENUID,
    ORDRE,
    TIPUS,
    GRUPID,
    PARAMETRECOMBO,
    PARAMETRETEXT,
    ACTIU
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
MENUID
  };
}
