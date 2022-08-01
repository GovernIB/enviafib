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
import es.caib.enviafib.persistence.validator.GrupUsuariValidator;

import es.caib.enviafib.back.form.webdb.GrupUsuariForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.GrupUsuari;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class GrupUsuariWebValidator extends AbstractWebValidator<GrupUsuariForm, GrupUsuari>
     implements Validator, GrupUsuariFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected GrupUsuariValidator<GrupUsuari> validator = new GrupUsuariValidator<GrupUsuari>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.GrupService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupService grupEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.GrupUsuariService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupUsuariService grupUsuariEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
  protected es.caib.enviafib.ejb.UsuariService usuariEjb;



  public GrupUsuariWebValidator() {
    super();    
  }
  
  @Override
  public GrupUsuari getBeanOfForm(GrupUsuariForm form) {
    return  form.getGrupUsuari();
  }

  @Override
  public Class<GrupUsuariForm> getClassOfForm() {
    return GrupUsuariForm.class;
  }

  @Override
  public void validate(GrupUsuariForm __form, GrupUsuari __bean, Errors errors) {

    WebValidationResult<GrupUsuariForm> wvr;
    wvr = new WebValidationResult<GrupUsuariForm>(errors);

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


  public void validate(GrupUsuariForm __form, GrupUsuari __bean, Errors errors,
    WebValidationResult<GrupUsuariForm> wvr, boolean isNou) {

    BeanValidatorResult<GrupUsuari> __vr = new BeanValidatorResult<GrupUsuari>();
    validator.validate(__vr, __bean,
      isNou, grupEjb, grupUsuariEjb, usuariEjb);

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

  public GrupUsuariValidator<GrupUsuari> getValidator() {
    return validator;
  }

  public void setValidator(GrupUsuariValidator<GrupUsuari> validator) {
    this.validator = validator;
  }

}