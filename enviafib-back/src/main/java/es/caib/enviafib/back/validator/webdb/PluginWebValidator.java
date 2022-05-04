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
import es.caib.enviafib.persistence.validator.PluginValidator;

import es.caib.enviafib.back.form.webdb.PluginForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Plugin;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PluginWebValidator extends AbstractWebValidator<PluginForm, Plugin>
     implements Validator, PluginFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PluginValidator<Plugin> validator = new PluginValidator<Plugin>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.PluginService.JNDI_NAME)
  protected es.caib.enviafib.ejb.PluginService pluginEjb;



  public PluginWebValidator() {
    super();    
  }
  
  @Override
  public Plugin getBeanOfForm(PluginForm form) {
    return  form.getPlugin();
  }

  @Override
  public Class<PluginForm> getClassOfForm() {
    return PluginForm.class;
  }

  @Override
  public void validate(PluginForm __form, Plugin __bean, Errors errors) {

    WebValidationResult<PluginForm> wvr;
    wvr = new WebValidationResult<PluginForm>(errors);

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


  public void validate(PluginForm __form, Plugin __bean, Errors errors,
    WebValidationResult<PluginForm> wvr, boolean isNou) {

    BeanValidatorResult<Plugin> __vr = new BeanValidatorResult<Plugin>();
    validator.validate(__vr, __bean,
      isNou, pluginEjb);

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

  public PluginValidator<Plugin> getValidator() {
    return validator;
  }

  public void setValidator(PluginValidator<Plugin> validator) {
    this.validator = validator;
  }

}