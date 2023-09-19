
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FaqFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_faq";


  public static final String _TABLE_MODEL = "faq";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FAQID = new LongField(_TABLE_MODEL, "faqID", "faqid");  // PK
	 public static final LongField ORDRE = new LongField(_TABLE_MODEL, "ordre", "ordre");
	 public static final StringField ENUNCIAT_ES = new StringField(_TABLE_MODEL, "enunciat_es", "enunciat_es");
	 public static final StringField ENUNCIAT_CA = new StringField(_TABLE_MODEL, "enunciat_ca", "enunciat_ca");
	 public static final StringField RESPOSTA_ES = new StringField(_TABLE_MODEL, "resposta_es", "resposta_es");
	 public static final StringField RESPOSTA_CA = new StringField(_TABLE_MODEL, "resposta_ca", "resposta_ca");
	 public static final LongField FITXER1ID = new LongField(_TABLE_MODEL, "fitxer1ID", "fitxer1id");
	 public static final LongField FITXER2ID = new LongField(_TABLE_MODEL, "fitxer2ID", "fitxer2id");
	 public static final LongField FITXER3ID = new LongField(_TABLE_MODEL, "fitxer3ID", "fitxer3id");


  public static final Field<?>[] ALL_FAQ_FIELDS = {
    FAQID,
    ORDRE,
    ENUNCIAT_ES,
    ENUNCIAT_CA,
    RESPOSTA_ES,
    RESPOSTA_CA,
    FITXER1ID,
    FITXER2ID,
    FITXER3ID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FAQID
  };
}
