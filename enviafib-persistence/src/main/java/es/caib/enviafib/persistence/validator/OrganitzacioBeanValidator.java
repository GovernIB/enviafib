package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.OrganitzacioJPA;
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
public class OrganitzacioBeanValidator 
      extends AbstractBeanValidator<OrganitzacioJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.IOrganitzacioManager __organitzacioManager;


  public final OrganitzacioValidator<OrganitzacioJPA> _validator;


  public OrganitzacioBeanValidator(es.caib.enviafib.model.dao.IOrganitzacioManager __organitzacioManager) { 
    this.__organitzacioManager = __organitzacioManager;
    _validator = new OrganitzacioValidator<OrganitzacioJPA>();
  }

  public OrganitzacioBeanValidator(OrganitzacioValidator<OrganitzacioJPA> _validator,
     es.caib.enviafib.model.dao.IOrganitzacioManager __organitzacioManager) {
    this.__organitzacioManager = __organitzacioManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(OrganitzacioJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<OrganitzacioJPA> _bvr_ = new BeanValidatorResult<OrganitzacioJPA>();
    _validator.validate(_bvr_, target, isNou, __organitzacioManager);
    return _bvr_.getErrors();
  }
}
