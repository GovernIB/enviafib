package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.Organitzacio;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.OrganitzacioFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class OrganitzacioValidator<I extends Organitzacio>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements OrganitzacioFields {

    protected final Logger log = Logger.getLogger(getClass());


  public OrganitzacioValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IOrganitzacioManager __organitzacioManager) {

    // Valors Not Null
    // Check size
    if (__vr.getFieldErrorCount(CODICONSELLERIA) == 0) {
      java.lang.String __codiconselleria = __target__.getCodiConselleria();
      if (__codiconselleria!= null && __codiconselleria.length() > 100) {
        __vr.rejectValue(CODICONSELLERIA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODICONSELLERIA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(CODIDIRECCIOGENERAL) == 0) {
      java.lang.String __codidirecciogeneral = __target__.getCodiDireccioGeneral();
      if (__codidirecciogeneral!= null && __codidirecciogeneral.length() > 100) {
        __vr.rejectValue(CODIDIRECCIOGENERAL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(CODIDIRECCIOGENERAL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(TIPUS) == 0) {
      java.lang.String __tipus = __target__.getTipus();
      if (__tipus!= null && __tipus.length() > 100) {
        __vr.rejectValue(TIPUS, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(TIPUS)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(100)));
      }
    }

    if (__vr.getFieldErrorCount(VALOR) == 0) {
      java.lang.String __valor = __target__.getValor();
      if (__valor!= null && __valor.length() > 255) {
        __vr.rejectValue(VALOR, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(VALOR)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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