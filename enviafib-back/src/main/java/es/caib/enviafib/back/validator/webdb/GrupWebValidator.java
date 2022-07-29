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
import es.caib.enviafib.persistence.validator.GrupValidator;

import es.caib.enviafib.back.form.webdb.GrupForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Grup;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupWebValidator extends AbstractWebValidator<GrupForm, Grup>
     implements Validator, GrupFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GrupValidator<Grup> validator = new GrupValidator<Grup>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.GrupService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupService grupEjb;



  public GrupWebValidator() {
    super();    
  }
  
  @Override
  public Grup getBeanOfForm(GrupForm form) {
    return  form.getGrup();
  }

  @Override
  public Class<GrupForm> getClassOfForm() {
    return GrupForm.class;
  }

  @Override
  public void validate(GrupForm __form, Grup __bean, Errors errors) {

    WebValidationResult<GrupForm> wvr;
    wvr = new WebValidationResult<GrupForm>(errors);

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


  public void validate(GrupForm __form, Grup __bean, Errors errors,
    WebValidationResult<GrupForm> wvr, boolean isNou) {

    BeanValidatorResult<Grup> __vr = new BeanValidatorResult<Grup>();
    validator.validate(__vr, __bean,
      isNou, grupEjb);

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

  public GrupValidator<Grup> getValidator() {
    return validator;
  }

  public void setValidator(GrupValidator<Grup> validator) {
    this.validator = validator;
  }

}