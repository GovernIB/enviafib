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
import es.caib.enviafib.persistence.validator.IdiomaValidator;

import es.caib.enviafib.back.form.webdb.IdiomaForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Idioma;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class IdiomaWebValidator extends AbstractWebValidator<IdiomaForm, Idioma>
     implements Validator, IdiomaFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected IdiomaValidator<Idioma> validator = new IdiomaValidator<Idioma>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;



  public IdiomaWebValidator() {
    super();    
  }
  
  @Override
  public Idioma getBeanOfForm(IdiomaForm form) {
    return  form.getIdioma();
  }

  @Override
  public Class<IdiomaForm> getClassOfForm() {
    return IdiomaForm.class;
  }

  @Override
  public void validate(IdiomaForm __form, Idioma __bean, Errors errors) {

    WebValidationResult<IdiomaForm> wvr;
    wvr = new WebValidationResult<IdiomaForm>(errors);

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


  public void validate(IdiomaForm __form, Idioma __bean, Errors errors,
    WebValidationResult<IdiomaForm> wvr, boolean isNou) {

    BeanValidatorResult<Idioma> __vr = new BeanValidatorResult<Idioma>();
    validator.validate(__vr, __bean,
      isNou, idiomaEjb);

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

  public IdiomaValidator<Idioma> getValidator() {
    return validator;
  }

  public void setValidator(IdiomaValidator<Idioma> validator) {
    this.validator = validator;
  }

}