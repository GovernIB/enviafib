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
import es.caib.enviafib.persistence.validator.TraduccioValidator;

import es.caib.enviafib.back.form.webdb.TraduccioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Traduccio;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class TraduccioWebValidator extends AbstractWebValidator<TraduccioForm, Traduccio>
     implements Validator, TraduccioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected TraduccioValidator<Traduccio> validator = new TraduccioValidator<Traduccio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.TraduccioService traduccioEjb;



  public TraduccioWebValidator() {
    super();    
  }
  
  @Override
  public Traduccio getBeanOfForm(TraduccioForm form) {
    return  form.getTraduccio();
  }

  @Override
  public Class<TraduccioForm> getClassOfForm() {
    return TraduccioForm.class;
  }

  @Override
  public void validate(TraduccioForm __form, Traduccio __bean, Errors errors) {

    WebValidationResult<TraduccioForm> wvr;
    wvr = new WebValidationResult<TraduccioForm>(errors);

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


  public void validate(TraduccioForm __form, Traduccio __bean, Errors errors,
    WebValidationResult<TraduccioForm> wvr, boolean isNou) {

    BeanValidatorResult<Traduccio> __vr = new BeanValidatorResult<Traduccio>();
    validator.validate(__vr, __bean,
      isNou, traduccioEjb);

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

  public TraduccioValidator<Traduccio> getValidator() {
    return validator;
  }

  public void setValidator(TraduccioValidator<Traduccio> validator) {
    this.validator = validator;
  }

}