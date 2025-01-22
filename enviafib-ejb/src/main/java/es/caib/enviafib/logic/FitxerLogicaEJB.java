
package es.caib.enviafib.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.FitxerEJB;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class FitxerLogicaEJB extends FitxerEJB implements FitxerLogicaService {

	@Override
	@PermitAll
	public Fitxer createPublic(Fitxer instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public FitxerJPA findByPrimaryKey(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}
	
	@Override
	@PermitAll
	public FitxerJPA findByPrimaryKeyPublic(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

}
