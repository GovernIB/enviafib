package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.InfoCustodyJPA;
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
public class InfoCustodyBeanValidator 
      extends AbstractBeanValidator<InfoCustodyJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IInfoCustodyManager __infoCustodyManager;

  protected final es.caib.enviafib.model.dao.IPeticioManager __peticioManager;


  public final InfoCustodyValidator<InfoCustodyJPA> _validator;


  public InfoCustodyBeanValidator(es.caib.enviafib.model.dao.IInfoCustodyManager __infoCustodyManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager) { 
    this.__infoCustodyManager = __infoCustodyManager;
    this.__peticioManager = __peticioManager;
    _validator = new InfoCustodyValidator<InfoCustodyJPA>();
  }

  public InfoCustodyBeanValidator(InfoCustodyValidator<InfoCustodyJPA> _validator,
     es.caib.enviafib.model.dao.IInfoCustodyManager __infoCustodyManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager) {
    this.__infoCustodyManager = __infoCustodyManager;
    this.__peticioManager = __peticioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(InfoCustodyJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<InfoCustodyJPA> _bvr_ = new BeanValidatorResult<InfoCustodyJPA>();
    _validator.validate(_bvr_, target, isNou, __infoCustodyManager, __peticioManager);
    return _bvr_.getErrors();
  }
}
