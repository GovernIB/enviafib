package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.InfoAnex;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.InfoAnexFields;
import es.caib.enviafib.model.fields.PeticioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class InfoAnexValidator<I extends InfoAnex>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements InfoAnexFields {

    protected final Logger log = Logger.getLogger(getClass());


  public InfoAnexValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IInfoAnexManager __infoAnexManager
    ,es.caib.enviafib.model.dao.IPeticioManager __peticioManager) {

    // Valors Not Null
    // Check size
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
    if (__vr.getFieldErrorCount(PETICIOID) == 0) {
      java.lang.Long __peticioid = __target__.getPeticioID();
      if (__peticioid != null ) {
        Long __count_ = null;
        try { __count_ = __peticioManager.count(PeticioFields.PETICIOID.equal(__peticioid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(PETICIOID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("peticio.peticio"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("peticio.peticioID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__peticioid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}