package es.caib.enviafib.persistence.validator;

import org.apache.log4j.Logger;

import es.caib.enviafib.model.entity.GrupUsuari;
import org.fundaciobit.genapp.common.query.Field;
import es.caib.enviafib.model.fields.GrupUsuariFields;
import es.caib.enviafib.model.fields.GrupFields;
import es.caib.enviafib.model.fields.UsuariFields;

import org.fundaciobit.genapp.common.validation.IValidatorResult;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class GrupUsuariValidator<I extends GrupUsuari>
    extends org.fundaciobit.genapp.common.validation.AbstractValidator    implements GrupUsuariFields {

    protected final Logger log = Logger.getLogger(getClass());


  public GrupUsuariValidator() {
    super();    
  }
  

  /** Constructor */
  public void validate(IValidatorResult<I> __vr,I __target__, boolean __isNou__
    ,es.caib.enviafib.model.dao.IGrupManager __grupManager
    ,es.caib.enviafib.model.dao.IGrupUsuariManager __grupUsuariManager
    ,es.caib.enviafib.model.dao.IUsuariManager __usuariManager) {

    // Valors Not Null
    // Check size
    if (__isNou__) { // Creació
      // ================ CREATION
      // Fitxers 
      // ====== Check Unique MULTIPLES - NOU =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(GRUPID) == 0) {
        java.lang.Long __grupid = __target__.getGrupID();
        Long __count_ = null;
        try { __count_ = __grupUsuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPID.equal(__grupid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPID)));
        }
      }

      if (__vr.getFieldErrorCount(USUARIID) == 0) {
        java.lang.Long __usuariid = __target__.getUsuariID();
        Long __count_ = null;
        try { __count_ = __grupUsuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIID.equal(__usuariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)));
        }
      }

      // Check Unique - PK no AutoIncrement amb UNA SOLA PK 
    } else {
      // ================ UPDATE

      // ====== Check Unique MULTIPLES - EDIT  =======

      // Check Unique - no PK
      if (__vr.getFieldErrorCount(GRUPID) == 0 && __vr.getFieldErrorCount(GRUPUSUARIID) == 0) {
        java.lang.Long __grupid = __target__.getGrupID();
        java.lang.Long __grupusuariid = __target__.getGrupUsuariID();
        Long __count_ = null;
        try { __count_ = __grupUsuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(GRUPID.equal(__grupid), GRUPUSUARIID.notEqual(__grupusuariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(GRUPID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(GRUPID)));
        }
      }

      if (__vr.getFieldErrorCount(USUARIID) == 0 && __vr.getFieldErrorCount(GRUPUSUARIID) == 0) {
        java.lang.Long __usuariid = __target__.getUsuariID();
        java.lang.Long __grupusuariid = __target__.getGrupUsuariID();
        Long __count_ = null;
        try { __count_ = __grupUsuariManager.count(org.fundaciobit.genapp.common.query.Where.AND(USUARIID.equal(__usuariid), GRUPUSUARIID.notEqual(__grupusuariid))); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ != 0) {        
            __vr.rejectValue(USUARIID, "genapp.validation.unique",
                new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)),
                     new org.fundaciobit.genapp.common.i18n.I18NArgumentCode(get(USUARIID)));
        }
      }

    }

    // Fields with References to Other tables 
    if (__vr.getFieldErrorCount(GRUPID) == 0) {
      java.lang.Long __grupid = __target__.getGrupID();
      if (__grupid != null ) {
        Long __count_ = null;
        try { __count_ = __grupManager.count(GrupFields.GRUPID.equal(__grupid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(GRUPID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grup.grup"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("grup.grupID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__grupid)));
        }
      }
    }

    if (__vr.getFieldErrorCount(USUARIID) == 0) {
      java.lang.Long __usuariid = __target__.getUsuariID();
      if (__usuariid != null ) {
        Long __count_ = null;
        try { __count_ = __usuariManager.count(UsuariFields.USUARIID.equal(__usuariid)); } catch(org.fundaciobit.genapp.common.i18n.I18NException e) { e.printStackTrace(); };
        if (__count_ == null || __count_ == 0) {        
          __vr.rejectValue(USUARIID, "error.notfound",
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuari"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentCode("usuari.usuariID"),
         new org.fundaciobit.genapp.common.i18n.I18NArgumentString(String.valueOf(__usuariid)));
        }
      }
    }

  } // Final de mètode
  public String get(Field<?> field) {
    return field.fullName;
  }
  
}