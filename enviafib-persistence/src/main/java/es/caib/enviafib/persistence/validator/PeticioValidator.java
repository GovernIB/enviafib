package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.Peticio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.IdiomaFields;
import es.caib.enviafib.model.fields.InfoArxiuFields;
import es.caib.enviafib.model.fields.InfoSignaturaFields;
import es.caib.enviafib.model.fields.UsuariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class PeticioValidator<I extends Peticio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements PeticioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public PeticioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IIdiomaManager __idiomaManager
    ,es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager
    ,es.caib.enviafib.model.dao.IInfoSignaturaManager __infoSignaturaManager
    ,es.caib.enviafib.model.dao.IPeticioManager __peticioManager
    ,es.caib.enviafib.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,DATACREACIO, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DATACREACIO)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SOLICITANTID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SOLICITANTID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMAID, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ESTAT, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ESTAT)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUSDOCUMENTAL, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)));

    __vr.rejectIfEmptyOrWhitespace(__target__,IDIOMADOC, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMADOC)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    // Check size
    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINIF) == 0) {
      java.lang.String __destinatarinif = __target__.getDestinatariNif();
      if (__destinatarinif!= null && __destinatarinif.length() > 50) {
        __vr.rejectValue(DESTINATARINIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINIF) == 0) {
      String val = __target__.getDestinatariNif();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(DESTINATARINIF, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)));
        }
      }
    }

    if (__vr.getFieldErrorCount(TIPUSDOCUMENTAL) == 0) {
      java.lang.String __tipusdocumental = __target__.getTipusDocumental();
      if (__tipusdocumental!= null && __tipusdocumental.length() > 100) {
        __vr.rejectValue(TIPUSDOCUMENTAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(IDIOMADOC) == 0) {
      java.lang.String __idiomadoc = __target__.getIdiomaDoc();
      if (__idiomadoc!= null && __idiomadoc.length() > 30) {
        __vr.rejectValue(IDIOMADOC, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMADOC)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__vr.getFieldErrorCount(ERRORMSG) == 0) {
      java.lang.String __errormsg = __target__.getErrorMsg();
      if (__errormsg!= null && __errormsg.length() > 255) {
        __vr.rejectValue(ERRORMSG, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ERRORMSG)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ERROREXCEPTION) == 0) {
      java.lang.String __errorexception = __target__.getErrorException();
      if (__errorexception!= null && __errorexception.length() > 2147483647) {
        __vr.rejectValue(ERROREXCEPTION, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ERROREXCEPTION)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(PETICIOPORTAFIRMES) == 0) {
      java.lang.String __peticioportafirmes = __target__.getPeticioPortafirmes();
      if (__peticioportafirmes!= null && __peticioportafirmes.length() > 255) {
        __vr.rejectValue(PETICIOPORTAFIRMES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PETICIOPORTAFIRMES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(REASON) == 0) {
      java.lang.String __reason = __target__.getReason();
      if (__reason!= null && __reason.length() > 255) {
        __vr.rejectValue(REASON, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(REASON)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUFUNCIONARIUSERNAME) == 0) {
      java.lang.String __arxiufuncionariusername = __target__.getArxiuFuncionariUsername();
      if (__arxiufuncionariusername!= null && __arxiufuncionariusername.length() > 255) {
        __vr.rejectValue(ARXIUFUNCIONARIUSERNAME, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUFUNCIONARIUSERNAME)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUPARAMFUNCIONARINOM) == 0) {
      java.lang.String __arxiuparamfuncionarinom = __target__.getArxiuParamFuncionariNom();
      if (__arxiuparamfuncionarinom!= null && __arxiuparamfuncionarinom.length() > 255) {
        __vr.rejectValue(ARXIUPARAMFUNCIONARINOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUPARAMFUNCIONARINOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUPARAMFUNCIONARINIF) == 0) {
      java.lang.String __arxiuparamfuncionarinif = __target__.getArxiuParamFuncionariNif();
      if (__arxiuparamfuncionarinif!= null && __arxiuparamfuncionarinif.length() > 255) {
        __vr.rejectValue(ARXIUPARAMFUNCIONARINIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUPARAMFUNCIONARINIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUPARAMFUNCIONARIDIR3) == 0) {
      java.lang.String __arxiuparamfuncionaridir3 = __target__.getArxiuParamFuncionariDir3();
      if (__arxiuparamfuncionaridir3!= null && __arxiuparamfuncionaridir3.length() > 255) {
        __vr.rejectValue(ARXIUPARAMFUNCIONARIDIR3, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUPARAMFUNCIONARIDIR3)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUREQPARAMDOCESTATELABORA) == 0) {
      java.lang.String __arxiureqparamdocestatelabora = __target__.getArxiuReqParamDocEstatElabora();
      if (__arxiureqparamdocestatelabora!= null && __arxiureqparamdocestatelabora.length() > 4) {
        __vr.rejectValue(ARXIUREQPARAMDOCESTATELABORA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUREQPARAMDOCESTATELABORA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(4)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUREQPARAMINTERESSATS) == 0) {
      java.lang.String __arxiureqparaminteressats = __target__.getArxiuReqParamInteressats();
      if (__arxiureqparaminteressats!= null && __arxiureqparaminteressats.length() > 255) {
        __vr.rejectValue(ARXIUREQPARAMINTERESSATS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUREQPARAMINTERESSATS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUREQPARAMCIUTADANIF) == 0) {
      java.lang.String __arxiureqparamciutadanif = __target__.getArxiuReqParamCiutadaNif();
      if (__arxiureqparamciutadanif!= null && __arxiureqparamciutadanif.length() > 15) {
        __vr.rejectValue(ARXIUREQPARAMCIUTADANIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUREQPARAMCIUTADANIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(15)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUREQPARAMCIUTADANOM) == 0) {
      java.lang.String __arxiureqparamciutadanom = __target__.getArxiuReqParamCiutadaNom();
      if (__arxiureqparamciutadanom!= null && __arxiureqparamciutadanom.length() > 255) {
        __vr.rejectValue(ARXIUREQPARAMCIUTADANOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUREQPARAMCIUTADANOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUREQPARAMORGANS) == 0) {
      java.lang.String __arxiureqparamorgans = __target__.getArxiuReqParamOrgans();
      if (__arxiureqparamorgans!= null && __arxiureqparamorgans.length() > 255) {
        __vr.rejectValue(ARXIUREQPARAMORGANS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUREQPARAMORGANS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUOPTPARAMPROCEDIMENTCODI) == 0) {
      java.lang.String __arxiuoptparamprocedimentcodi = __target__.getArxiuOptParamProcedimentCodi();
      if (__arxiuoptparamprocedimentcodi!= null && __arxiuoptparamprocedimentcodi.length() > 255) {
        __vr.rejectValue(ARXIUOPTPARAMPROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUOPTPARAMPROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUOPTPARAMPROCEDIMENTNOM) == 0) {
      java.lang.String __arxiuoptparamprocedimentnom = __target__.getArxiuOptParamProcedimentNom();
      if (__arxiuoptparamprocedimentnom!= null && __arxiuoptparamprocedimentnom.length() > 255) {
        __vr.rejectValue(ARXIUOPTPARAMPROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUOPTPARAMPROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUOPTPARAMSERIEDOCUMENTAL) == 0) {
      java.lang.String __arxiuoptparamseriedocumental = __target__.getArxiuOptParamSerieDocumental();
      if (__arxiuoptparamseriedocumental!= null && __arxiuoptparamseriedocumental.length() > 255) {
        __vr.rejectValue(ARXIUOPTPARAMSERIEDOCUMENTAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUOPTPARAMSERIEDOCUMENTAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ARXIUOPTPARAMEXPEDIENTID) == 0) {
      java.lang.String __arxiuoptparamexpedientid = __target__.getArxiuOptParamExpedientId();
      if (__arxiuoptparamexpedientid!= null && __arxiuoptparamexpedientid.length() > 255) {
        __vr.rejectValue(ARXIUOPTPARAMEXPEDIENTID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ARXIUOPTPARAMEXPEDIENTID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
    if (__vr.getFieldErrorCount(FITXERID) == 0) { // FITXER
      Object __value = __vr.getFieldValue(__target__,FITXERID);
      if (__value == null || String.valueOf(__value).trim().length() == 0 || String.valueOf(__value).trim().equals("0") ) {
          __vr.rejectValue(FITXERID, "genapp.validation.required",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(FITXERID)));
      }
    }

      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(SOLICITANTID) == 0) {
      java.lang.Long __solicitantid = __target__.getSolicitantID();
      Long __count_ = null;
      try { __count_ = __usuariManager.count(UsuariFields.USUARIID.equal(__solicitantid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(SOLICITANTID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__solicitantid)));
      }
    }

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      Long __count_ = null;
      try { __count_ = __idiomaManager.count(IdiomaFields.IDIOMAID.equal(__idiomaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
      if (__count_ == null || __count_ == 0) {        
        __vr.rejectValue(IDIOMAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idioma"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("idioma.idiomaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__idiomaid)));
      }
    }

    if (__vr.getFieldErrorCount(INFOSIGNATURAID) == 0) {
      java.lang.Long __infosignaturaid = __target__.getInfoSignaturaID();
      if (__infosignaturaid != null ) {
        Long __count_ = null;
        try { __count_ = __infoSignaturaManager.count(InfoSignaturaFields.INFOSIGNATURAID.equal(__infosignaturaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(INFOSIGNATURAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoSignatura.infoSignatura"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoSignatura.infoSignaturaID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__infosignaturaid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(INFOARXIUID) == 0) {
      java.lang.Long __infoarxiuid = __target__.getInfoArxiuID();
      if (__infoarxiuid != null ) {
        Long __count_ = null;
        try { __count_ = __infoArxiuManager.count(InfoArxiuFields.INFOARXIUID.equal(__infoarxiuid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(INFOARXIUID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoArxiu.infoArxiu"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoArxiu.infoArxiuID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__infoarxiuid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}