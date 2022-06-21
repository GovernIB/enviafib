package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.InfoSignatura;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.InfoSignaturaFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class InfoSignaturaValidator<I extends InfoSignatura>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements InfoSignaturaFields {

    protected final Logger log = Logger.getLogger(getClass());


  public InfoSignaturaValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IInfoSignaturaManager __infoSignaturaManager) {

    // Valors Not Null
    __vr.rejectIfEmptyOrWhitespace(__target__,SIGNOPERATION, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SIGNOPERATION)));

    __vr.rejectIfEmptyOrWhitespace(__target__,SIGNTYPE, 
        "genapp.validation.required",
        new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SIGNTYPE)));

    // Check size
    if (__vr.getFieldErrorCount(SIGNTYPE) == 0) {
      java.lang.String __signtype = __target__.getSignType();
      if (__signtype!= null && __signtype.length() > 255) {
        __vr.rejectValue(SIGNTYPE, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SIGNTYPE)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(SIGNALGORITHM) == 0) {
      java.lang.String __signalgorithm = __target__.getSignAlgorithm();
      if (__signalgorithm!= null && __signalgorithm.length() > 255) {
        __vr.rejectValue(SIGNALGORITHM, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(SIGNALGORITHM)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENITIPOFIRMA) == 0) {
      java.lang.String __enitipofirma = __target__.getEniTipoFirma();
      if (__enitipofirma!= null && __enitipofirma.length() > 255) {
        __vr.rejectValue(ENITIPOFIRMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENITIPOFIRMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENIPERFILFIRMA) == 0) {
      java.lang.String __eniperfilfirma = __target__.getEniPerfilFirma();
      if (__eniperfilfirma!= null && __eniperfilfirma.length() > 255) {
        __vr.rejectValue(ENIPERFILFIRMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENIPERFILFIRMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENIROLFIRMA) == 0) {
      java.lang.String __enirolfirma = __target__.getEniRolFirma();
      if (__enirolfirma!= null && __enirolfirma.length() > 255) {
        __vr.rejectValue(ENIROLFIRMA, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENIROLFIRMA)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENISIGNERNAME) == 0) {
      java.lang.String __enisignername = __target__.getEniSignerName();
      if (__enisignername!= null && __enisignername.length() > 255) {
        __vr.rejectValue(ENISIGNERNAME, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENISIGNERNAME)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENISIGNERADMINISTRATIONID) == 0) {
      java.lang.String __enisigneradministrationid = __target__.getEniSignerAdministrationId();
      if (__enisigneradministrationid!= null && __enisigneradministrationid.length() > 255) {
        __vr.rejectValue(ENISIGNERADMINISTRATIONID, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENISIGNERADMINISTRATIONID)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
      }
    }

    if (__vr.getFieldErrorCount(ENISIGNLEVEL) == 0) {
      java.lang.String __enisignlevel = __target__.getEniSignLevel();
      if (__enisignlevel!= null && __enisignlevel.length() > 255) {
        __vr.rejectValue(ENISIGNLEVEL, "genapp.validation.sizeexceeds",
            new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(ENISIGNLEVEL)), new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(255)));
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