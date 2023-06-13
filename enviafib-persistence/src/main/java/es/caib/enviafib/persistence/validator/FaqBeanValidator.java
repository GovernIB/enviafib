package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.FaqJPA;
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
public class FaqBeanValidator 
      extends AbstractBeanValidator<FaqJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IFaqManager __faqManager;


  public final FaqValidator<FaqJPA> _validator;


  public FaqBeanValidator(es.caib.enviafib.model.dao.IFaqManager __faqManager) { 
    this.__faqManager = __faqManager;
    _validator = new FaqValidator<FaqJPA>();
  }

  public FaqBeanValidator(FaqValidator<FaqJPA> _validator,
     es.caib.enviafib.model.dao.IFaqManager __faqManager) {
    this.__faqManager = __faqManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(FaqJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<FaqJPA> _bvr_ = new BeanValidatorResult<FaqJPA>();
    _validator.validate(_bvr_, target, isNou, __faqManager);
    return _bvr_.getErrors();
  }
}
