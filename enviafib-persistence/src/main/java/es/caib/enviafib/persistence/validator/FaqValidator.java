package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.Faq;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.FaqFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class FaqValidator<I extends Faq>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements FaqFields {

    protected final Logger log = Logger.getLogger(getClass());


  public FaqValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IFaqManager __faqManager) {

    // Valors Not Null
    // Check size
    if (__vr.getFieldErrorCount(ENUNCIAT_ES) == 0) {
      java.lang.String __enunciat_es = __target__.getEnunciat_es();
      if (__enunciat_es!= null && __enunciat_es.length() > 255) {
        __vr.rejectValue(ENUNCIAT_ES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENUNCIAT_ES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENUNCIAT_CA) == 0) {
      java.lang.String __enunciat_ca = __target__.getEnunciat_ca();
      if (__enunciat_ca!= null && __enunciat_ca.length() > 255) {
        __vr.rejectValue(ENUNCIAT_CA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENUNCIAT_CA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(RESPOSTA_ES) == 0) {
      java.lang.String __resposta_es = __target__.getResposta_es();
      if (__resposta_es!= null && __resposta_es.length() > 2147483647) {
        __vr.rejectValue(RESPOSTA_ES, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTA_ES)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
      }
    }

    if (__vr.getFieldErrorCount(RESPOSTA_CA) == 0) {
      java.lang.String __resposta_ca = __target__.getResposta_ca();
      if (__resposta_ca!= null && __resposta_ca.length() > 2147483647) {
        __vr.rejectValue(RESPOSTA_CA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(RESPOSTA_CA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(2147483647)));
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