package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.MenuJPA;
import org.fundaciobit.genapp.common.validation.BeanValidatorResult;
import java.util.List;
import org.fundaciobit.genapp.common.i18n.I18NFieldError;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.validation.AbstractBeanValidator;

/**
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * @author GenApp
 * @author anadal
 */
public class MenuBeanValidator 
      extends AbstractBeanValidator<MenuJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IGrupManager __grupManager;

  protected final es.caib.enviafib.model.dao.IMenuManager __menuManager;

  protected final es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager;


  public final MenuValidator<MenuJPA> _validator;


  public MenuBeanValidator(es.caib.enviafib.model.dao.IGrupManager __grupManager,
     es.caib.enviafib.model.dao.IMenuManager __menuManager,
     es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager) { 
    this.__grupManager = __grupManager;
    this.__menuManager = __menuManager;
    this.__traduccioManager = __traduccioManager;
    _validator = new MenuValidator<MenuJPA>();
  }

  public MenuBeanValidator(MenuValidator<MenuJPA> _validator,
     es.caib.enviafib.model.dao.IGrupManager __grupManager,
     es.caib.enviafib.model.dao.IMenuManager __menuManager,
     es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager) {
    this.__grupManager = __grupManager;
    this.__menuManager = __menuManager;
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(MenuJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<MenuJPA> _bvr_ = new BeanValidatorResult<MenuJPA>();
    _validator.validate(_bvr_, target, isNou, __grupManager, __menuManager, __traduccioManager);
    return _bvr_.getErrors();
  }
}
