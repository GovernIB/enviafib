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
import es.caib.enviafib.persistence.validator.InfoSignaturaValidator;

import es.caib.enviafib.back.form.webdb.InfoSignaturaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.InfoSignatura;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class InfoSignaturaWebValidator extends AbstractWebValidator<InfoSignaturaForm, InfoSignatura>
     implements Validator, InfoSignaturaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected InfoSignaturaValidator<InfoSignatura> validator = new InfoSignaturaValidator<InfoSignatura>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.InfoSignaturaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoSignaturaService infoSignaturaEjb;



  public InfoSignaturaWebValidator() {
    super();    
  }
  
  @Override
  public InfoSignatura getBeanOfForm(InfoSignaturaForm form) {
    return  form.getInfoSignatura();
  }

  @Override
  public Class<InfoSignaturaForm> getClassOfForm() {
    return InfoSignaturaForm.class;
  }

  @Override
  public void validate(InfoSignaturaForm __form, InfoSignatura __bean, Errors errors) {

    WebValidationResult<InfoSignaturaForm> wvr;
    wvr = new WebValidationResult<InfoSignaturaForm>(errors);

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


  public void validate(InfoSignaturaForm __form, InfoSignatura __bean, Errors errors,
    WebValidationResult<InfoSignaturaForm> wvr, boolean isNou) {

    BeanValidatorResult<InfoSignatura> __vr = new BeanValidatorResult<InfoSignatura>();
    validator.validate(__vr, __bean,
      isNou, infoSignaturaEjb);

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

  public InfoSignaturaValidator<InfoSignatura> getValidator() {
    return validator;
  }

  public void setValidator(InfoSignaturaValidator<InfoSignatura> validator) {
    this.validator = validator;
  }

}