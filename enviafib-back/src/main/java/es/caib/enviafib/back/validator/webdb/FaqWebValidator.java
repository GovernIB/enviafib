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
import es.caib.enviafib.persistence.validator.FaqValidator;

import es.caib.enviafib.back.form.webdb.FaqForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Faq;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class FaqWebValidator extends AbstractWebValidator<FaqForm, Faq>
     implements Validator, FaqFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected FaqValidator<Faq> validator = new FaqValidator<Faq>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.FaqService.JNDI_NAME)
  protected es.caib.enviafib.ejb.FaqService faqEjb;



  public FaqWebValidator() {
    super();    
  }
  
  @Override
  public Faq getBeanOfForm(FaqForm form) {
    return  form.getFaq();
  }

  @Override
  public Class<FaqForm> getClassOfForm() {
    return FaqForm.class;
  }

  @Override
  public void validate(FaqForm __form, Faq __bean, Errors errors) {

    WebValidationResult<FaqForm> wvr;
    wvr = new WebValidationResult<FaqForm>(errors);

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


  public void validate(FaqForm __form, Faq __bean, Errors errors,
    WebValidationResult<FaqForm> wvr, boolean isNou) {

    BeanValidatorResult<Faq> __vr = new BeanValidatorResult<Faq>();
    validator.validate(__vr, __bean,
      isNou, faqEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public FaqValidator<Faq> getValidator() {
    return validator;
  }

  public void setValidator(FaqValidator<Faq> validator) {
    this.validator = validator;
  }

}