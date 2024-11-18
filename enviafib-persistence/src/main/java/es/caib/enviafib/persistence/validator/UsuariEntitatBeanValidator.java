package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.UsuariEntitatJPA;
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
public class UsuariEntitatBeanValidator 
      extends AbstractBeanValidator<UsuariEntitatJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IEntitatManager __entitatManager;

  protected final es.caib.enviafib.model.dao.IUsuariManager __usuariManager;

  protected final es.caib.enviafib.model.dao.IUsuariEntitatManager __usuariEntitatManager;


  public final UsuariEntitatValidator<UsuariEntitatJPA> _validator;


  public UsuariEntitatBeanValidator(es.caib.enviafib.model.dao.IEntitatManager __entitatManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager,
     es.caib.enviafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) { 
    this.__entitatManager = __entitatManager;
    this.__usuariManager = __usuariManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    _validator = new UsuariEntitatValidator<UsuariEntitatJPA>();
  }

  public UsuariEntitatBeanValidator(UsuariEntitatValidator<UsuariEntitatJPA> _validator,
     es.caib.enviafib.model.dao.IEntitatManager __entitatManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager,
     es.caib.enviafib.model.dao.IUsuariEntitatManager __usuariEntitatManager) {
    this.__entitatManager = __entitatManager;
    this.__usuariManager = __usuariManager;
    this.__usuariEntitatManager = __usuariEntitatManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(UsuariEntitatJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<UsuariEntitatJPA> _bvr_ = new BeanValidatorResult<UsuariEntitatJPA>();
    _validator.validate(_bvr_, target, isNou, __entitatManager, __usuariManager, __usuariEntitatManager);
    return _bvr_.getErrors();
  }
}
