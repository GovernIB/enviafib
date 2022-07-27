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
import es.caib.enviafib.persistence.validator.InfoArxiuValidator;

import es.caib.enviafib.back.form.webdb.InfoArxiuForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.InfoArxiu;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class InfoArxiuWebValidator extends AbstractWebValidator<InfoArxiuForm, InfoArxiu>
     implements Validator, InfoArxiuFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected InfoArxiuValidator<InfoArxiu> validator = new InfoArxiuValidator<InfoArxiu>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.InfoArxiuService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb;



  public InfoArxiuWebValidator() {
    super();    
  }
  
  @Override
  public InfoArxiu getBeanOfForm(InfoArxiuForm form) {
    return  form.getInfoArxiu();
  }

  @Override
  public Class<InfoArxiuForm> getClassOfForm() {
    return InfoArxiuForm.class;
  }

  @Override
  public void validate(InfoArxiuForm __form, InfoArxiu __bean, Errors errors) {

    WebValidationResult<InfoArxiuForm> wvr;
    wvr = new WebValidationResult<InfoArxiuForm>(errors);

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


  public void validate(InfoArxiuForm __form, InfoArxiu __bean, Errors errors,
    WebValidationResult<InfoArxiuForm> wvr, boolean isNou) {

    BeanValidatorResult<InfoArxiu> __vr = new BeanValidatorResult<InfoArxiu>();
    validator.validate(__vr, __bean,
      isNou, infoArxiuEjb);

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

  public InfoArxiuValidator<InfoArxiu> getValidator() {
    return validator;
  }

  public void setValidator(InfoArxiuValidator<InfoArxiu> validator) {
    this.validator = validator;
  }

}