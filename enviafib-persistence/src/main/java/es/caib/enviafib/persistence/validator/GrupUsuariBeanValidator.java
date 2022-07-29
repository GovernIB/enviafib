package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.GrupUsuariJPA;
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
public class GrupUsuariBeanValidator 
      extends AbstractBeanValidator<GrupUsuariJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IGrupManager __grupManager;

  protected final es.caib.enviafib.model.dao.IGrupUsuariManager __grupUsuariManager;

  protected final es.caib.enviafib.model.dao.IUsuariManager __usuariManager;


  public final GrupUsuariValidator<GrupUsuariJPA> _validator;


  public GrupUsuariBeanValidator(es.caib.enviafib.model.dao.IGrupManager __grupManager,
     es.caib.enviafib.model.dao.IGrupUsuariManager __grupUsuariManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager) { 
    this.__grupManager = __grupManager;
    this.__grupUsuariManager = __grupUsuariManager;
    this.__usuariManager = __usuariManager;
    _validator = new GrupUsuariValidator<GrupUsuariJPA>();
  }

  public GrupUsuariBeanValidator(GrupUsuariValidator<GrupUsuariJPA> _validator,
     es.caib.enviafib.model.dao.IGrupManager __grupManager,
     es.caib.enviafib.model.dao.IGrupUsuariManager __grupUsuariManager,
     es.caib.enviafib.model.dao.IUsuariManager __usuariManager) {
    this.__grupManager = __grupManager;
    this.__grupUsuariManager = __grupUsuariManager;
    this.__usuariManager = __usuariManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(GrupUsuariJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<GrupUsuariJPA> _bvr_ = new BeanValidatorResult<GrupUsuariJPA>();
    _validator.validate(_bvr_, target, isNou, __grupManager, __grupUsuariManager, __usuariManager);
    return _bvr_.getErrors();
  }
}
