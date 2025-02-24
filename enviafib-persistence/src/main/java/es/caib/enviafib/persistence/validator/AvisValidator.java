package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.Avis;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.AvisFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class AvisValidator<I extends Avis>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements AvisFields {

    protected final Logger log = Logger.getLogger(getClass());


  public AvisValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IAvisManager __avisManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,MISSATGE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGE)));

    __vr.rejectIfEmptyOrWhitespace(__target__,ACTIU, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ACTIU)));

    __vr.rejectIfEmptyOrWhitespace(__target__,TIPUS, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)));

    // Check size
    if (__vr.getFieldErrorCount(MISSATGE) == 0) {
      java.lang.String __missatge = __target__.getMissatge();
      if (__missatge!= null && __missatge.length() > 500) {
        __vr.rejectValue(MISSATGE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(MISSATGE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(500)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUS) == 0) {
      java.lang.String __tipus = __target__.getTipus();
      if (__tipus!= null && __tipus.length() > 30) {
        __vr.rejectValue(TIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(30)));
      }
    }

    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
    }

    // Fields with References to Other tables 
  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}