
package es.caib.enviafib.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.EntitatEJB;
import es.caib.enviafib.model.entity.Entitat;
import es.caib.enviafib.persistence.EntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class EntitatLogicaEJB extends EntitatEJB implements EntitatLogicaService {

	@Override
	@PermitAll
	public Entitat createPublic(Entitat instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public EntitatJPA findByPrimaryKeyPublic(String _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

}
