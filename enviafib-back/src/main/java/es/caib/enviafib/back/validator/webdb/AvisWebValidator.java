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
import es.caib.enviafib.persistence.validator.AvisValidator;

import es.caib.enviafib.back.form.webdb.AvisForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Avis;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class AvisWebValidator extends AbstractWebValidator<AvisForm, Avis>
     implements Validator, AvisFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected AvisValidator<Avis> validator = new AvisValidator<Avis>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.AvisService.JNDI_NAME)
  protected es.caib.enviafib.ejb.AvisService avisEjb;



  public AvisWebValidator() {
    super();    
  }
  
  @Override
  public Avis getBeanOfForm(AvisForm form) {
    return  form.getAvis();
  }

  @Override
  public Class<AvisForm> getClassOfForm() {
    return AvisForm.class;
  }

  @Override
  public void validate(AvisForm __form, Avis __bean, Errors errors) {

    WebValidationResult<AvisForm> wvr;
    wvr = new WebValidationResult<AvisForm>(errors);

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


  public void validate(AvisForm __form, Avis __bean, Errors errors,
    WebValidationResult<AvisForm> wvr, boolean isNou) {

    BeanValidatorResult<Avis> __vr = new BeanValidatorResult<Avis>();
    validator.validate(__vr, __bean,
      isNou, avisEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }


  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public AvisValidator<Avis> getValidator() {
    return validator;
  }

  public void setValidator(AvisValidator<Avis> validator) {
    this.validator = validator;
  }

}