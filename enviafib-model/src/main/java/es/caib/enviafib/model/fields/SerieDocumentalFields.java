
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface SerieDocumentalFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_seriedocumental";


  public static final String _TABLE_MODEL = "serieDocumental";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField SERIEDOCUMENTALID = new LongField(_TABLE_MODEL, "serieDocumentalID", "seriedocumentalid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField TIPUSDOCUMENTAL = new StringField(_TABLE_MODEL, "tipusDocumental", "tipusdocumental");
	 public static final StringField PROCEDIMENTNOM = new StringField(_TABLE_MODEL, "procedimentNom", "procedimentnom");
	 public static final StringField PROCEDIMENTCODI = new StringField(_TABLE_MODEL, "procedimentCodi", "procedimentcodi");


  public static final Field<?>[] ALL_SERIEDOCUMENTAL_FIELDS = {
    SERIEDOCUMENTALID,
    NOM,
    TIPUSDOCUMENTAL,
    PROCEDIMENTNOM,
    PROCEDIMENTCODI
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
SERIEDOCUMENTALID
  };
}
