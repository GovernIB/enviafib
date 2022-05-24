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

    // Check size
    if (__vr.getFieldErrorCount(NOM) == 0) {
      java.lang.String __nom = __target__.getNom();
      if (__nom!= null && __nom.length() > 256) {
        __vr.rejectValue(NOM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(NOM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUSDOCU) == 0) {
      java.lang.String __tipusdocu = __target__.getTipusdocu();
      if (__tipusdocu!= null && __tipusdocu.length() > 256) {
        __vr.rejectValue(TIPUSDOCU, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCU)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(256)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(TIPUSDOCU) == 0) {
        java.lang.String __tipusdocu = __target__.getTipusdocu();
        Long __count_ = null;
        try { __count_ = __serieDocumentalManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCU.equal(__tipusdocu))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCU, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocu)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCU)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(TIPUSDOCU) == 0 && __vr.getFieldErrorCount(SERIEDOCUID) == 0) {
        java.lang.String __tipusdocu = __target__.getTipusdocu();
        java.lang.Long __seriedocuid = __target__.getSeriedocuid();
        Long __count_ = null;
        try { __count_ = __serieDocumentalManager.count(org.fundaciobit.genapp.common.query.Where.AND(TIPUSDOCU.equal(__tipusdocu), SERIEDOCUID.notEqual(__seriedocuid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(TIPUSDOCU, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__tipusdocu)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUSDOCU)));
        }
      }

    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}