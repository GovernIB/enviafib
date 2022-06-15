package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.Peticio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.IdiomaFields;
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

    __vr.rejectIfEmptyOrWhitespace(__target__,DESTINATARINIF, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)));

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
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 255) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(IDIOMAID) == 0) {
      java.lang.String __idiomaid = __target__.getIdiomaID();
      if (__idiomaid!= null && __idiomaid.length() > 5) {
        __vr.rejectValue(IDIOMAID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(IDIOMAID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(5)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINIF) == 0) {
      java.lang.String __destinatarinif = __target__.getDestinatarinif();
      if (__destinatarinif!= null && __destinatarinif.length() > 50) {
        __vr.rejectValue(DESTINATARINIF, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(50)));
      }
    }

    if (__vr.getFieldErrorCount(DESTINATARINIF) == 0) {
      String val = __target__.getDestinatarinif();
      if (val != null && val.trim().length() != 0) {
        java.util.regex.Pattern p = java.util.regex.Pattern.compile("([XYZ][0-9]{7}[A-Z])|([0-9]{8}[A-Z])");
        if (!p.matcher(val).matches()) {
          __vr.rejectValue(DESTINATARINIF, "genapp.validation.malformed",
             new org.fundaciobit.genapp.common.i18n.I18NArgumentString(val), new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(DESTINATARINIF)));
        }
      }
    }

    if (__vr.getFieldErrorCount(TIPUSDOCUMENTAL) == 0) {
      java.lang.String __tipusdocumental = __target__.getTipusdocumental();
      if (__tipusdocumental!= null && __tipusdocumental.length() > 100) {
        __vr.rejectValue(TIPUSDOCUMENTAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(IDIOMADOC) == 0) {
      java.lang.String __idiomadoc = __target__.getIdiomadoc();
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
      java.lang.Long __infosignaturaid = __target__.getInfosignaturaid();
      if (__infosignaturaid != null ) {
        Long __count_ = null;
        try { __count_ = __infoSignaturaManager.count(InfoSignaturaFields.INFOSIGNATURAID.equal(__infosignaturaid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(INFOSIGNATURAID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoSignatura.infoSignatura"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("infoSignatura.infosignaturaid"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__infosignaturaid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}