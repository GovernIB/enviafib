package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.TraduccioJPA;
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
public class TraduccioBeanValidator 
      extends AbstractBeanValidator<TraduccioJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager;


  public final TraduccioValidator<TraduccioJPA> _validator;


  public TraduccioBeanValidator(es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager) { 
    this.__traduccioManager = __traduccioManager;
    _validator = new TraduccioValidator<TraduccioJPA>();
  }

  public TraduccioBeanValidator(TraduccioValidator<TraduccioJPA> _validator,
     es.caib.enviafib.model.dao.ITraduccioManager __traduccioManager) {
    this.__traduccioManager = __traduccioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(TraduccioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<TraduccioJPA> _bvr_ = new BeanValidatorResult<TraduccioJPA>();
    _validator.validate(_bvr_, target, isNou, __traduccioManager);
    return _bvr_.getErrors();
  }
}
