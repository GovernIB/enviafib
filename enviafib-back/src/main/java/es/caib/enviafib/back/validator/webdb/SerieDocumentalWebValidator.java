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
import es.caib.enviafib.persistence.validator.SerieDocumentalValidator;

import es.caib.enviafib.back.form.webdb.SerieDocumentalForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.SerieDocumental;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class SerieDocumentalWebValidator extends AbstractWebValidator<SerieDocumentalForm, SerieDocumental>
     implements Validator, SerieDocumentalFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected SerieDocumentalValidator<SerieDocumental> validator = new SerieDocumentalValidator<SerieDocumental>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.SerieDocumentalService.JNDI_NAME)
  protected es.caib.enviafib.ejb.SerieDocumentalService serieDocumentalEjb;



  public SerieDocumentalWebValidator() {
    super();    
  }
  
  @Override
  public SerieDocumental getBeanOfForm(SerieDocumentalForm form) {
    return  form.getSerieDocumental();
  }

  @Override
  public Class<SerieDocumentalForm> getClassOfForm() {
    return SerieDocumentalForm.class;
  }

  @Override
  public void validate(SerieDocumentalForm __form, SerieDocumental __bean, Errors errors) {

    WebValidationResult<SerieDocumentalForm> wvr;
    wvr = new WebValidationResult<SerieDocumentalForm>(errors);

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


  public void validate(SerieDocumentalForm __form, SerieDocumental __bean, Errors errors,
    WebValidationResult<SerieDocumentalForm> wvr, boolean isNou) {

    BeanValidatorResult<SerieDocumental> __vr = new BeanValidatorResult<SerieDocumental>();
    validator.validate(__vr, __bean,
      isNou, serieDocumentalEjb);

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

  public SerieDocumentalValidator<SerieDocumental> getValidator() {
    return validator;
  }

  public void setValidator(SerieDocumentalValidator<SerieDocumental> validator) {
    this.validator = validator;
  }

}