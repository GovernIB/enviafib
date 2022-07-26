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
import es.caib.enviafib.persistence.validator.PeticioValidator;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Peticio;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class PeticioWebValidator extends AbstractWebValidator<PeticioForm, Peticio>
     implements Validator, PeticioFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected PeticioValidator<Peticio> validator = new PeticioValidator<Peticio>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.InfoSignaturaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoSignaturaService infoSignaturaEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.PeticioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.PeticioService peticioEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
  protected es.caib.enviafib.ejb.UsuariService usuariEjb;



  public PeticioWebValidator() {
    super();    
  }
  
  @Override
  public Peticio getBeanOfForm(PeticioForm form) {
    return  form.getPeticio();
  }

  @Override
  public Class<PeticioForm> getClassOfForm() {
    return PeticioForm.class;
  }

  @Override
  public void validate(PeticioForm __form, Peticio __bean, Errors errors) {

    WebValidationResult<PeticioForm> wvr;
    wvr = new WebValidationResult<PeticioForm>(errors);

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


  public void validate(PeticioForm __form, Peticio __bean, Errors errors,
    WebValidationResult<PeticioForm> wvr, boolean isNou) {

    BeanValidatorResult<Peticio> __vr = new BeanValidatorResult<Peticio>();
    validator.validate(__vr, __bean,
      isNou, idiomaEjb, infoSignaturaEjb, peticioEjb, usuariEjb);

    if (__vr.hasErrors()) {
        List<I18NFieldError> vrErrors = __vr.getErrors();
    	   for (I18NFieldError i18nFieldError : vrErrors) {
    	       wvr.rejectValue(i18nFieldError.getField(), i18nFieldError.getTranslation().getCode(), i18nFieldError.getTranslation().getArgs());
        }
    }

    if (isNou) { // Creacio
      // ================ CREATION
      // Fitxers 
        if (!errors.hasFieldErrors(get(FITXERID))){
            CommonsMultipartFile fitxerID = ((PeticioForm)__form).getFitxerID();
            if (fitxerID == null || fitxerID.isEmpty()) {
                errors.rejectValue(get(FITXERID), "genapp.validation.required",
                new String[]{ org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(get(FITXERID)) },
                null);
            }
        }

    }

  } // Final de metode

  public String get(Field<?> field) {
    return field.fullName;
  }

  public PeticioValidator<Peticio> getValidator() {
    return validator;
  }

  public void setValidator(PeticioValidator<Peticio> validator) {
    this.validator = validator;
  }

}