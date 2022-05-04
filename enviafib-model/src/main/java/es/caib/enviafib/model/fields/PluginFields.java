
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PluginFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_plugin";


  public static final String _TABLE_MODEL = "plugin";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField PLUGINID = new LongField(_TABLE_MODEL, "pluginid", "pluginid");  // PK
	 public static final LongField NOMID = new LongField(_TABLE_MODEL, "nomid", "nomid");
	 public static final LongField DESCRIPCIOCURTAID = new LongField(_TABLE_MODEL, "descripciocurtaid", "descripciocurtaid");
	 public static final StringField CLASSE = new StringField(_TABLE_MODEL, "classe", "classe");
	 public static final StringField PROPERTIES = new StringField(_TABLE_MODEL, "properties", "properties");
	 public static final BooleanField ACTIU = new BooleanField(_TABLE_MODEL, "actiu", "actiu");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");


  public static final Field<?>[] ALL_PLUGIN_FIELDS = {
    PLUGINID,
    NOMID,
    DESCRIPCIOCURTAID,
    CLASSE,
    PROPERTIES,
    ACTIU,
    TIPUS
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PLUGINID
  };
}
