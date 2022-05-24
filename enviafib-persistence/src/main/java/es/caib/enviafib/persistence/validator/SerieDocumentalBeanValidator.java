package es.caib.enviafib.persistence.validator;

import es.caib.enviafib.persistence.SerieDocumentalJPA;
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
public class SerieDocumentalBeanValidator 
      extends AbstractBeanValidator<SerieDocumentalJPA> {


  // EJB's
  protected final es.caib.enviafib.model.dao.ISerieDocumentalManager __serieDocumentalManager;


  public final SerieDocumentalValidator<SerieDocumentalJPA> _validator;


  public SerieDocumentalBeanValidator(es.caib.enviafib.model.dao.ISerieDocumentalManager __serieDocumentalManager) { 
    this.__serieDocumentalManager = __serieDocumentalManager;
    _validator = new SerieDocumentalValidator<SerieDocumentalJPA>();
  }

  public SerieDocumentalBeanValidator(SerieDocumentalValidator<SerieDocumentalJPA> _validator,
     es.caib.enviafib.model.dao.ISerieDocumentalManager __serieDocumentalManager) {
    this.__serieDocumentalManager = __serieDocumentalManager;
    this._validator = _validator;
  }

  @Override
  public List<I18NFieldError> validate(SerieDocumentalJPA target, boolean isNou) throws I18NException {
    BeanValidatorResult<SerieDocumentalJPA> _bvr_ = new BeanValidatorResult<SerieDocumentalJPA>();
    _validator.validate(_bvr_, target, isNou, __serieDocumentalManager);
    return _bvr_.getErrors();
  }
}
