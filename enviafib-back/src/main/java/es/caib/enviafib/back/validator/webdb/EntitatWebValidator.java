package es.caib.enviafib.back.validator.webdb;

import org.apache.log4j.Logger;

import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import java.util.List;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.validation.WebValidationResult;
import es.caib.enviafib.model.fields.*;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import es.caib.enviafib.persistence.validator.EntitatValidator;

import es.caib.enviafib.back.form.webdb.EntitatForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Entitat;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class EntitatWebValidator extends AbstractWebValidator<EntitatForm, Entitat>
     implements Validator, EntitatFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected EntitatValidator<Entitat> validator = new EntitatValidator<Entitat>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.EntitatService.JNDI_NAME)
  protected es.caib.enviafib.ejb.EntitatService entitatEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.TraduccioService traduccioEjb;



  public EntitatWebValidator() {
    super();    
  }
  
  @Override
  public Entitat getBeanOfForm(EntitatForm form) {
    return  form.getEntitat();
  }

  @Override
  public Class<EntitatForm> getClassOfForm() {
    return EntitatForm.class;
  }

  @Override
  public void validate(EntitatForm __form, Entitat __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(MOTIUDELEGACIOID);
    WebValidationResult<EntitatForm> wvr;
    wvr = new WebValidationResult<EntitatForm>(errors, _ignoreFields);

    boolean isNou;
    {
        Object objNou = errors.getFieldValue("nou");
        if (objNou == null) {
            isNou = false;
        } else { 
         Boolean nou = Boolean.parseBoolean(String.valueOf(objNou));
         isNou =  nou != null && nou.booleanValue();
        }
    }

    validate(__form, __bean , errors, wvr, isNou);
  }


  public void validate(EntitatForm __form, Entitat __bean, Errors errors,
    WebValidationResult<EntitatForm> wvr, boolean isNou) {

  {
      es.caib.enviafib.persistence.EntitatJPA __jpa;
      __jpa = (es.caib.enviafib.persistence.EntitatJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.enviafib.persistence.TraduccioJPA tradJPA = __jpa.getMotiudelegacio();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.enviafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNull= 0;
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
            countNull++;
          } else {
            countNotNull++;
          }
        }

        if (countNull == _trad.size()) {
          // OK Tot esta buit ==> passam el camp a NULL
          __jpa.setMotiudelegacioID(null);
          __jpa.setMotiudelegacio(null);
        } else {
          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("entitat.motiudelegacio", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
                errors.rejectValue("entitat.motiudelegacio.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(MOTIUDELEGACIOID.fullName)}, null);
              }
            }
          }
        }
      } else {
          __jpa.setMotiudelegacioID(null);
          __jpa.setMotiudelegacio(null);
      }
    }

  }
    BeanValidatorResult<Entitat> __vr = new BeanValidatorResult<Entitat>();
    validator.validate(__vr, __bean,
      isNou, entitatEjb, traduccioEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FAVICONID))){
            CommonsMultipartFile faviconID = ((EntitatForm)__form).getFaviconID();
            if (faviconID == null || faviconID.isEmpty()) {
                errors.rejectValue(get(FAVICONID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FAVICONID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOWEBID))){
            CommonsMultipartFile logowebID = ((EntitatForm)__form).getLogowebID();
            if (logowebID == null || logowebID.isEmpty()) {
                errors.rejectValue(get(LOGOWEBID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOWEBPEUID))){
            CommonsMultipartFile logowebpeuID = ((EntitatForm)__form).getLogowebpeuID();
            if (logowebpeuID == null || logowebpeuID.isEmpty()) {
                errors.rejectValue(get(LOGOWEBPEUID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOWEBPEUID)) },
                null);
            }
        }

        if (!errors.hasFieldErrors(get(LOGOSEGELLID))){
            CommonsMultipartFile logosegellID = ((EntitatForm)__form).getLogosegellID();
            if (logosegellID == null || logosegellID.isEmpty()) {
                errors.rejectValue(get(LOGOSEGELLID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(LOGOSEGELLID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public EntitatValidator<Entitat> getValidator() {
    return validator;
  }

  public void setValidator(EntitatValidator<Entitat> validator) {
    this.validator = validator;
  }

}