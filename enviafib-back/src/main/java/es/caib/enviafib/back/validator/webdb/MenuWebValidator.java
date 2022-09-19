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
import es.caib.enviafib.persistence.validator.MenuValidator;

import es.caib.enviafib.back.form.webdb.MenuForm;
import org.fundaciobit.genapp.common.web.validation.AbstractWebValidator;
import es.caib.enviafib.model.entity.Menu;


/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author anadal
 */
@Component
public class MenuWebValidator extends AbstractWebValidator<MenuForm, Menu>
     implements Validator, MenuFields {

     protected final Logger log = Logger.getLogger(getClass());

  protected MenuValidator<Menu> validator = new MenuValidator<Menu>();

  // EJB's
  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.GrupService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupService grupEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.MenuService.JNDI_NAME)
  protected es.caib.enviafib.ejb.MenuService menuEjb;

  @javax.ejb.EJB(mappedName = es.caib.enviafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.TraduccioService traduccioEjb;



  public MenuWebValidator() {
    super();    
  }
  
  @Override
  public Menu getBeanOfForm(MenuForm form) {
    return  form.getMenu();
  }

  @Override
  public Class<MenuForm> getClassOfForm() {
    return MenuForm.class;
  }

  @Override
  public void validate(MenuForm __form, Menu __bean, Errors errors) {

java.util.List<Field<?>> _ignoreFields = new java.util.ArrayList<Field<?>>();
_ignoreFields.add(TITOLMENUID);
_ignoreFields.add(AJUDAMENUID);
    WebValidationResult<MenuForm> wvr;
    wvr = new WebValidationResult<MenuForm>(errors, _ignoreFields);

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


  public void validate(MenuForm __form, Menu __bean, Errors errors,
    WebValidationResult<MenuForm> wvr, boolean isNou) {

  {
      es.caib.enviafib.persistence.MenuJPA __jpa;
      __jpa = (es.caib.enviafib.persistence.MenuJPA)__bean;
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.enviafib.persistence.TraduccioJPA tradJPA = __jpa.getTitolMenu();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.enviafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("menu.titolMenu", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLMENUID.fullName)}, null);
                errors.rejectValue("menu.titolMenu.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLMENUID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("menu.titolMenu", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(TITOLMENUID.fullName)}, null);
      }
    }
    {
      // IF CAMP ES NOT NULL verificar que totes les traduccions son not null
      es.caib.enviafib.persistence.TraduccioJPA tradJPA = __jpa.getAjudaMenu();
      if (tradJPA != null) {
        // TODO ERROR
        java.util.Map<String,es.caib.enviafib.persistence.TraduccioMapJPA> _trad = tradJPA.getTraduccions();
        int countNotNull = 0;
        for (String _idioma : _trad.keySet()) {
          es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
          if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
          } else {
            countNotNull++;
          }
        }

          if (countNotNull  == _trad.size()) {
            // OK Tot esta ple
          } else {
            for (String _idioma : _trad.keySet()) {
              es.caib.enviafib.persistence.TraduccioMapJPA _map = _trad.get(_idioma);
              if (_map == null || (_map.getValor() == null || _map.getValor().length() == 0 )) {
                errors.rejectValue("menu.ajudaMenu", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(AJUDAMENUID.fullName)}, null);
                errors.rejectValue("menu.ajudaMenu.traduccions["+ _idioma +"].valor",
                  "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(AJUDAMENUID.fullName)}, null);
              }
            }
          }
      } else {
        errors.rejectValue("menu.ajudaMenu", "genapp.validation.required", new String[] {org.fundaciobit.genapp.common.web.i18n.I18NUtils.tradueix(AJUDAMENUID.fullName)}, null);
      }
    }

  }
    BeanValidatorResult<Menu> __vr = new BeanValidatorResult<Menu>();
    validator.validate(__vr, __bean,
      isNou, grupEjb, menuEjb, traduccioEjb);

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

  public MenuValidator<Menu> getValidator() {
    return validator;
  }

  public void setValidator(MenuValidator<Menu> validator) {
    this.validator = validator;
  }

}