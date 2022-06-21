
package es.caib.enviafib.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface InfoSignaturaFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "efi_infosignatura";


  public static final String _TABLE_MODEL = "infoSignatura";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField INFOSIGNATURAID = new LongField(_TABLE_MODEL, "infoSignaturaID", "infosignaturaid");  // PK
	 public static final IntegerField SIGNOPERATION = new IntegerField(_TABLE_MODEL, "signOperation", "signoperation");
	 public static final StringField SIGNTYPE = new StringField(_TABLE_MODEL, "signType", "signtype");
	 public static final StringField SIGNALGORITHM = new StringField(_TABLE_MODEL, "signAlgorithm", "signalgorithm");
	 public static final IntegerField SIGNMODE = new IntegerField(_TABLE_MODEL, "signMode", "signmode");
	 public static final IntegerField SIGNATURESTABLELOCATION = new IntegerField(_TABLE_MODEL, "signaturesTableLocation", "signaturestablelocation");
	 public static final BooleanField TIMESTAMPINCLUDED = new BooleanField(_TABLE_MODEL, "timestampIncluded", "timestampincluded");
	 public static final BooleanField POLICYINCLUDED = new BooleanField(_TABLE_MODEL, "policyIncluded", "policyincluded");
	 public static final StringField ENITIPOFIRMA = new StringField(_TABLE_MODEL, "eniTipoFirma", "enitipofirma");
	 public static final StringField ENIPERFILFIRMA = new StringField(_TABLE_MODEL, "eniPerfilFirma", "eniperfilfirma");
	 public static final StringField ENIROLFIRMA = new StringField(_TABLE_MODEL, "eniRolFirma", "enirolfirma");
	 public static final StringField ENISIGNERNAME = new StringField(_TABLE_MODEL, "eniSignerName", "enisignername");
	 public static final StringField ENISIGNERADMINISTRATIONID = new StringField(_TABLE_MODEL, "eniSignerAdministrationId", "enisigneradministrationid");
	 public static final StringField ENISIGNLEVEL = new StringField(_TABLE_MODEL, "eniSignLevel", "enisignlevel");
	 public static final BooleanField CHECKADMINISTRATIONIDOFSIGNER = new BooleanField(_TABLE_MODEL, "checkAdministrationIdOfSigner", "checkadministrationidofsigner");
	 public static final BooleanField CHECKDOCUMENTMODIFICATIONS = new BooleanField(_TABLE_MODEL, "checkDocumentModifications", "checkdocumentmodifications");
	 public static final BooleanField CHECKVALIDATIONSIGNATURE = new BooleanField(_TABLE_MODEL, "checkValidationSignature", "checkvalidationsignature");


  public static final Field<?>[] ALL_INFOSIGNATURA_FIELDS = {
    INFOSIGNATURAID,
    SIGNOPERATION,
    SIGNTYPE,
    SIGNALGORITHM,
    SIGNMODE,
    SIGNATURESTABLELOCATION,
    TIMESTAMPINCLUDED,
    POLICYINCLUDED,
    ENITIPOFIRMA,
    ENIPERFILFIRMA,
    ENIROLFIRMA,
    ENISIGNERNAME,
    ENISIGNERADMINISTRATIONID,
    ENISIGNLEVEL,
    CHECKADMINISTRATIONIDOFSIGNER,
    CHECKDOCUMENTMODIFICATIONS,
    CHECKVALIDATIONSIGNATURE
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
INFOSIGNATURAID
  };
}
