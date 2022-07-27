package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.PeticioJPA;
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
public class PeticioBeanValidator 
      extends AbstractBeanValidator<PeticioJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IIdiomaManager __idiomaManager;

  protected final es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager;

  protected final es.caib.enviafib.model.dao.IInfoSignaturaManager __infoSignaturaManager;

  protected final es.caib.enviafib.model.dao.IPeticioManager __peticioManager;

  protected final es.caib.enviafib.model.dao.IUsuariManager __usuariManager;


  public final PeticioValidator<PeticioJPA> _validator;


  public PeticioBeanValidator(es.caib.enviafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager,
     es.caib.enviafib.model.dao.IInfoSignaturaManager __infoSignaturaManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager) { 
    this.__idiomaManager = __idiomaManager;
    this.__infoArxiuManager = __infoArxiuManager;
    this.__infoSignaturaManager = __infoSignaturaManager;
    this.__peticioManager = __peticioManager;
    this.__usuariManager = __usuariManager;
    _validator = new PeticioValidator<PeticioJPA>();
  }

  public PeticioBeanValidator(PeticioValidator<PeticioJPA> _validator,
     es.caib.enviafib.model.dao.IIdiomaManager __idiomaManager,
     es.caib.enviafib.model.dao.IInfoArxiuManager __infoArxiuManager,
     es.caib.enviafib.model.dao.IInfoSignaturaManager __infoSignaturaManager,
     es.caib.enviafib.model.dao.IPeticioManager __peticioManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager) {
    this.__idiomaManager = __idiomaManager;
    this.__infoArxiuManager = __infoArxiuManager;
    this.__infoSignaturaManager = __infoSignaturaManager;
    this.__peticioManager = __peticioManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(PeticioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<PeticioJPA> _bvr_ = new BeanValidatorResult<PeticioJPA>();
    _validator.validate(_bvr_, target, isNou, __idiomaManager, __infoArxiuManager, __infoSignaturaManager, __peticioManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
