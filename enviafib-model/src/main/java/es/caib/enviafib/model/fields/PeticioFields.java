
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface PeticioFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_peticio";


  public static final String _TABLE_MODEL = "peticio";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final LongField PETICIOID = new LongField(_TABLE_MODEL, "peticioID", "peticioid");  // PK
	 public static final TimestampField DATACREACIO = new TimestampField(_TABLE_MODEL, "dataCreacio", "datacreacio");
	 public static final TimestampField DATAFINAL = new TimestampField(_TABLE_MODEL, "dataFinal", "datafinal");
	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");
	 public static final LongField SOLICITANTID = new LongField(_TABLE_MODEL, "solicitantID", "solicitantid");
	 public static final StringField IDIOMAID = new StringField(_TABLE_MODEL, "idiomaID", "idiomaid");
	 public static final StringField DESTINATARINIF = new StringField(_TABLE_MODEL, "destinatariNif", "destinatarinif");
	 public static final IntegerField ESTAT = new IntegerField(_TABLE_MODEL, "estat", "estat");
	 public static final LongField FITXERFIRMATID = new LongField(_TABLE_MODEL, "fitxerFirmatID", "fitxer_firmatid");
	 public static final StringField TIPUSDOCUMENTAL = new StringField(_TABLE_MODEL, "tipusDocumental", "tipusdocumental");
	 public static final StringField IDIOMADOC = new StringField(_TABLE_MODEL, "idiomaDoc", "idiomadoc");
	 public static final LongField INFOSIGNATURAID = new LongField(_TABLE_MODEL, "infoSignaturaID", "infosignaturaid");
	 public static final IntegerField TIPUS = new IntegerField(_TABLE_MODEL, "tipus", "tipus");
	 public static final StringField ERRORMSG = new StringField(_TABLE_MODEL, "errorMsg", "errormsg");
	 public static final StringField ERROREXCEPTION = new StringField(_TABLE_MODEL, "errorException", "errorexception");
	 public static final StringField PETICIOPORTAFIRMES = new StringField(_TABLE_MODEL, "peticioPortafirmes", "peticioportafirmes");
	 public static final StringField REASON = new StringField(_TABLE_MODEL, "reason", "reason");
	 public static final StringField ARXIUFUNCIONARIUSERNAME = new StringField(_TABLE_MODEL, "arxiuFuncionariUsername", "arxiufuncionariusername");
	 public static final StringField ARXIUPARAMFUNCIONARINOM = new StringField(_TABLE_MODEL, "arxiuParamFuncionariNom", "arxiuparamfuncionarinom");
	 public static final StringField ARXIUPARAMFUNCIONARINIF = new StringField(_TABLE_MODEL, "arxiuParamFuncionariNif", "arxiuparamfuncionarinif");
	 public static final StringField ARXIUPARAMFUNCIONARIDIR3 = new StringField(_TABLE_MODEL, "arxiuParamFuncionariDir3", "arxiuparamfuncionaridir3");
	 public static final StringField ARXIUREQPARAMDOCESTATELABORA = new StringField(_TABLE_MODEL, "arxiuReqParamDocEstatElabora", "arxiureqparamdocestatelabora");
	 public static final StringField ARXIUREQPARAMINTERESSATS = new StringField(_TABLE_MODEL, "arxiuReqParamInteressats", "arxiureqparaminteressats");
	 public static final StringField ARXIUREQPARAMCIUTADANIF = new StringField(_TABLE_MODEL, "arxiuReqParamCiutadaNif", "arxiureqparamciutadanif");
	 public static final StringField ARXIUREQPARAMCIUTADANOM = new StringField(_TABLE_MODEL, "arxiuReqParamCiutadaNom", "arxiureqparamciutadanom");
	 public static final StringField ARXIUREQPARAMORGANS = new StringField(_TABLE_MODEL, "arxiuReqParamOrgans", "arxiureqparamorgans");
	 public static final StringField ARXIUOPTPARAMPROCEDIMENTCODI = new StringField(_TABLE_MODEL, "arxiuOptParamProcedimentCodi", "arxiuoptparamprocedimentcodi");
	 public static final StringField ARXIUOPTPARAMPROCEDIMENTNOM = new StringField(_TABLE_MODEL, "arxiuOptParamProcedimentNom", "arxiuoptparamprocedimentnom");
	 public static final StringField ARXIUOPTPARAMSERIEDOCUMENTAL = new StringField(_TABLE_MODEL, "arxiuOptParamSerieDocumental", "arxiuoptparamseriedocumental");
	 public static final StringField ARXIUOPTPARAMEXPEDIENTID = new StringField(_TABLE_MODEL, "arxiuOptParamExpedientId", "arxiuoptparamexpedientid");
	 public static final IntegerField ARXIUREQPARAMORIGEN = new IntegerField(_TABLE_MODEL, "arxiuReqParamOrigen", "arxiureqparamorigen");
	 public static final LongField INFOARXIUID = new LongField(_TABLE_MODEL, "infoArxiuID", "infoarxiuid");


  public static final Field<?>[] ALL_PETICIO_FIELDS = {
    NOM,
    PETICIOID,
    DATACREACIO,
    DATAFINAL,
    FITXERID,
    SOLICITANTID,
    IDIOMAID,
    DESTINATARINIF,
    ESTAT,
    FITXERFIRMATID,
    TIPUSDOCUMENTAL,
    IDIOMADOC,
    INFOSIGNATURAID,
    TIPUS,
    ERRORMSG,
    ERROREXCEPTION,
    PETICIOPORTAFIRMES,
    REASON,
    ARXIUFUNCIONARIUSERNAME,
    ARXIUPARAMFUNCIONARINOM,
    ARXIUPARAMFUNCIONARINIF,
    ARXIUPARAMFUNCIONARIDIR3,
    ARXIUREQPARAMDOCESTATELABORA,
    ARXIUREQPARAMINTERESSATS,
    ARXIUREQPARAMCIUTADANIF,
    ARXIUREQPARAMCIUTADANOM,
    ARXIUREQPARAMORGANS,
    ARXIUOPTPARAMPROCEDIMENTCODI,
    ARXIUOPTPARAMPROCEDIMENTNOM,
    ARXIUOPTPARAMSERIEDOCUMENTAL,
    ARXIUOPTPARAMEXPEDIENTID,
    ARXIUREQPARAMORIGEN,
    INFOARXIUID
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
PETICIOID
  };
}
