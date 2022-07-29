package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.GrupJPA;
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
public class GrupBeanValidator 
      extends AbstractBeanValidator<GrupJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IGrupManager __grupManager;


  public final GrupValidator<GrupJPA> _validator;


  public GrupBeanValidator(es.caib.enviafib.model.dao.IGrupManager __grupManager) { 
    this.__grupManager = __grupManager;
    _validator = new GrupValidator<GrupJPA>();
  }

  public GrupBeanValidator(GrupValidator<GrupJPA> _validator,
     es.caib.enviafib.model.dao.IGrupManager __grupManager) {
    this.__grupManager = __grupManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupJPA> _bvr_ = new BeanValidatorResult<GrupJPA>();
    _validator.validate(_bvr_, target, isNou, __grupManager);
    return _bvr_.getErrors();
  }
}
