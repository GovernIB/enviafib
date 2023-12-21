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
import es.caib.enviafib.persistence.validator.InfoAnexValidator;

import es.caib.enviafib.back.form.webdb.InfoAnexForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.InfoAnex;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class InfoAnexWebValidator extends AbstractWebValidator<InfoAnexForm, InfoAnex>
     implements Validator, InfoAnexFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected InfoAnexValidator<InfoAnex> validator = new InfoAnexValidator<InfoAnex>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.InfoAnexService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoAnexService infoAnexEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.PeticioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.PeticioService peticioEjb;



  public InfoAnexWebValidator() {
    super();    
  }
  
  @Override
  public InfoAnex getBeanOfForm(InfoAnexForm form) {
    return  form.getInfoAnex();
  }

  @Override
  public Class<InfoAnexForm> getClassOfForm() {
    return InfoAnexForm.class;
  }

  @Override
  public void validate(InfoAnexForm __form, InfoAnex __bean, Errors errors) {

    WebValidationResult<InfoAnexForm> wvr;
    wvr = new WebValidationResult<InfoAnexForm>(errors);

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


  public void validate(InfoAnexForm __form, InfoAnex __bean, Errors errors,
    WebValidationResult<InfoAnexForm> wvr, boolean isNou) {

    BeanValidatorResult<InfoAnex> __vr = new BeanValidatorResult<InfoAnex>();
    validator.validate(__vr, __bean,
      isNou, infoAnexEjb, peticioEjb);

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

  public InfoAnexValidator<InfoAnex> getValidator() {
    return validator;
  }

  public void setValidator(InfoAnexValidator<InfoAnex> validator) {
    this.validator = validator;
  }

}