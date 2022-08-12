package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.SerieDocumental;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.SerieDocumentalFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class SerieDocumentalValidator<I extends SerieDocumental>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements SerieDocumentalFields {

    protected final Logger log = Logger.getLogger(getClass());


  public SerieDocumentalValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.ISerieDocumentalManager __serieDocumentalManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,NOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROCEDIMENTNOM, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)));

    __vr.rejectIfEmptyOrWhitespace(__target__,PROCEDIMENTCODI, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)));

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 256) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSDOCUMENTAL) == 0) {
      java.lang.String __tipusdocumental = __target__.getTipusDocumental();
      if (__tipusdocumental!= null && __tipusdocumental.length() > 256) {
        __vr.rejectValue(TIPUSDOCUMENTAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTNOM) == 0) {
      java.lang.String __procedimentnom = __target__.getProcedimentNom();
      if (__procedimentnom!= null && __procedimentnom.length() > 2147483647) {
        __vr.rejectValue(PROCEDIMENTNOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTNOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(PROCEDIMENTCODI) == 0) {
      java.lang.String __procedimentcodi = __target__.getProcedimentCodi();
      if (__procedimentcodi!= null && __procedimentcodi.length() > 2147483647) {
        __vr.rejectValue(PROCEDIMENTCODI, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(PROCEDIMENTCODI)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(TIPUSDOCUMENTAL) == 0) {
        java.lang.String __tipusdocumental = __target__.getTipusDocumental();
        Long __count_ = null;
        try { __count_ = __serieDocumentalManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCUMENTAL.equal(__tipusdocumental))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCUMENTAL, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumental)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(TIPUSDOCUMENTAL) == 0 && __vr.getFieldErrorCount(SERIEDOCUMENTALID) == 0) {
        java.lang.String __tipusdocumental = __target__.getTipusDocumental();
        java.lang.Long __seriedocumentalid = __target__.getSerieDocumentalID();
        Long __count_ = null;
        try { __count_ = __serieDocumentalManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCUMENTAL.equal(__tipusdocumental), SERIEDOCUMENTALID.notEqual(__seriedocumentalid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCUMENTAL, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocumental)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCUMENTAL)));
        }
      }

    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}