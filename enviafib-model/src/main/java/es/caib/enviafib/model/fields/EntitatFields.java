
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface EntitatFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_entitat";


  public static final String _TABLE_MODEL = "entitat";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField ENTITATID = new StringField(_TABLE_MODEL, "entitatid", "entitatid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField ADREZAHTML = new StringField(_TABLE_MODEL, "adrezahtml", "adrezahtml");
	 public static final BooleanField ACTIVA = new BooleanField(_TABLE_MODEL, "activa", "activa");
	 public static final StringField SUPORTTELEFON = new StringField(_TABLE_MODEL, "suporttelefon", "suporttelefon");
	 public static final StringField SUPORTWEB = new StringField(_TABLE_MODEL, "suportweb", "suportweb");
	 public static final StringField SUPORTEMAIL = new StringField(_TABLE_MODEL, "suportemail", "suportemail");
	 public static final LongField FAVICONID = new LongField(_TABLE_MODEL, "faviconID", "faviconid");
	 public static final LongField LOGOWEBID = new LongField(_TABLE_MODEL, "logowebID", "logowebid");
	 public static final LongField LOGOWEBPEUID = new LongField(_TABLE_MODEL, "logowebpeuID", "logowebpeuid");
	 public static final LongField LOGOSEGELLID = new LongField(_TABLE_MODEL, "logosegellID", "logosegellid");
	 public static final StringField WEB = new StringField(_TABLE_MODEL, "web", "web");
	 public static final LongField MOTIUDELEGACIOID = new LongField(_TABLE_MODEL, "motiudelegacioID", "motiudelegacioid");
	 public static final IntegerField SEGELLDETEMPSVIAWEB = new IntegerField(_TABLE_MODEL, "segelldetempsviaweb", "segelldetempsviaweb");
	 public static final BooleanField CHECKCANVIATDOCFIRMAT = new BooleanField(_TABLE_MODEL, "checkcanviatdocfirmat", "checkcanviatdocfirmat");
	 public static final StringField PROPIETATSTAULAFIRMES = new StringField(_TABLE_MODEL, "propietatstaulafirmes", "propietatstaulafirmes");
	 public static final StringField DIR3 = new StringField(_TABLE_MODEL, "dir3", "dir3");


  public static final Field<?>[] ALL_ENTITAT_FIELDS = {
    ENTITATID,
    NOM,
    DESCRIPCIO,
    ADREZAHTML,
    ACTIVA,
    SUPORTTELEFON,
    SUPORTWEB,
    SUPORTEMAIL,
    FAVICONID,
    LOGOWEBID,
    LOGOWEBPEUID,
    LOGOSEGELLID,
    WEB,
    MOTIUDELEGACIOID,
    SEGELLDETEMPSVIAWEB,
    CHECKCANVIATDOCFIRMAT,
    PROPIETATSTAULAFIRMES,
    DIR3
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
ENTITATID
  };
}
