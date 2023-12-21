package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.InfoAnexJPA;
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
public class InfoAnexBeanValidator 
      extends AbstractBeanValidator<InfoAnexJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IInfoAnexManager __infoAnexManager;

  protected final es.caib.enviafib.model.dao.IPeticioManager __peticioManager;


  public final InfoAnexValidator<InfoAnexJPA> _validator;


  public InfoAnexBeanValidator(es.caib.enviafib.model.dao.IInfoAnexManager __infoAnexManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager) { 
    this.__infoAnexManager = __infoAnexManager;
    this.__peticioManager = __peticioManager;
    _validator = new InfoAnexValidator<InfoAnexJPA>();
  }

  public InfoAnexBeanValidator(InfoAnexValidator<InfoAnexJPA> _validator,
     es.caib.enviafib.model.dao.IInfoAnexManager __infoAnexManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager) {
    this.__infoAnexManager = __infoAnexManager;
    this.__peticioManager = __peticioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(InfoAnexJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<InfoAnexJPA> _bvr_ = new BeanValidatorResult<InfoAnexJPA>();
    _validator.validate(_bvr_, target, isNou, __infoAnexManager, __peticioManager);
    return _bvr_.getErrors();
  }
}
