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
import es.caib.enviafib.persistence.validator.OrganitzacioValidator;

import es.caib.enviafib.back.form.webdb.OrganitzacioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Organitzacio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class OrganitzacioWebValidator extends AbstractWebValidator<OrganitzacioForm, Organitzacio>
     implements Validator, OrganitzacioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected OrganitzacioValidator<Organitzacio> validator = new OrganitzacioValidator<Organitzacio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.OrganitzacioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.OrganitzacioService organitzacioEjb;



  public OrganitzacioWebValidator() {
    super();    
  }
  
  @Override
  public Organitzacio getBeanOfForm(OrganitzacioForm form) {
    return  form.getOrganitzacio();
  }

  @Override
  public Class<OrganitzacioForm> getClassOfForm() {
    return OrganitzacioForm.class;
  }

  @Override
  public void validate(OrganitzacioForm __form, Organitzacio __bean, Errors errors) {

    WebValidationResult<OrganitzacioForm> wvr;
    wvr = new WebValidationResult<OrganitzacioForm>(errors);

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


  public void validate(OrganitzacioForm __form, Organitzacio __bean, Errors errors,
    WebValidationResult<OrganitzacioForm> wvr, boolean isNou) {

    BeanValidatorResult<Organitzacio> __vr = new BeanValidatorResult<Organitzacio>();
    validator.validate(__vr, __bean,
      isNou, organitzacioEjb);

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

  public OrganitzacioValidator<Organitzacio> getValidator() {
    return validator;
  }

  public void setValidator(OrganitzacioValidator<Organitzacio> validator) {
    this.validator = validator;
  }

}