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
import es.caib.enviafib.persistence.validator.InfoCustodyValidator;

import es.caib.enviafib.back.form.webdb.InfoCustodyForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.InfoCustody;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class InfoCustodyWebValidator extends AbstractWebValidator<InfoCustodyForm, InfoCustody>
     implements Validator, InfoCustodyFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected InfoCustodyValidator<InfoCustody> validator = new InfoCustodyValidator<InfoCustody>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.InfoCustodyService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoCustodyService infoCustodyEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.PeticioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.PeticioService peticioEjb;



  public InfoCustodyWebValidator() {
    super();    
  }
  
  @Override
  public InfoCustody getBeanOfForm(InfoCustodyForm form) {
    return  form.getInfoCustody();
  }

  @Override
  public Class<InfoCustodyForm> getClassOfForm() {
    return InfoCustodyForm.class;
  }

  @Override
  public void validate(InfoCustodyForm __form, InfoCustody __bean, Errors errors) {

    WebValidationResult<InfoCustodyForm> wvr;
    wvr = new WebValidationResult<InfoCustodyForm>(errors);

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


  public void validate(InfoCustodyForm __form, InfoCustody __bean, Errors errors,
    WebValidationResult<InfoCustodyForm> wvr, boolean isNou) {

    BeanValidatorResult<InfoCustody> __vr = new BeanValidatorResult<InfoCustody>();
    validator.validate(__vr, __bean,
      isNou, infoCustodyEjb, peticioEjb);

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

  public InfoCustodyValidator<InfoCustody> getValidator() {
    return validator;
  }

  public void setValidator(InfoCustodyValidator<InfoCustody> validator) {
    this.validator = validator;
  }

}