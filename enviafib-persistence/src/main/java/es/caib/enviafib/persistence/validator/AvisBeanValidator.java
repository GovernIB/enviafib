package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.AvisJPA;
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
public class AvisBeanValidator 
      extends AbstractBeanValidator<AvisJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IAvisManager __avisManager;


  public final AvisValidator<AvisJPA> _validator;


  public AvisBeanValidator(es.caib.enviafib.model.dao.IAvisManager __avisManager) { 
    this.__avisManager = __avisManager;
    _validator = new AvisValidator<AvisJPA>();
  }

  public AvisBeanValidator(AvisValidator<AvisJPA> _validator,
     es.caib.enviafib.model.dao.IAvisManager __avisManager) {
    this.__avisManager = __avisManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(AvisJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<AvisJPA> _bvr_ = new BeanValidatorResult<AvisJPA>();
    _validator.validate(_bvr_, target, isNou, __avisManager);
    return _bvr_.getErrors();
  }
}
