package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.InfoArxiuJPA;
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
public class InfoArxiuBeanValidator 
      extends AbstractBeanValidator<InfoArxiuJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager;


  public final InfoArxiuValidator<InfoArxiuJPA> _validator;


  public InfoArxiuBeanValidator(es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager) { 
    this.__infoArxiuManager = __infoArxiuManager;
    _validator = new InfoArxiuValidator<InfoArxiuJPA>();
  }

  public InfoArxiuBeanValidator(InfoArxiuValidator<InfoArxiuJPA> _validator,
     es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager) {
    this.__infoArxiuManager = __infoArxiuManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(InfoArxiuJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<InfoArxiuJPA> _bvr_ = new BeanValidatorResult<InfoArxiuJPA>();
    _validator.validate(_bvr_, target, isNou, __infoArxiuManager);
    return _bvr_.getErrors();
  }
}
