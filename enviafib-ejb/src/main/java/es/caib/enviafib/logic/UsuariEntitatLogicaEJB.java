
package es.caib.enviafib.logic;

import javax.ejb.Stateless;
import javax.annotation.security.PermitAll;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.persistence.UsuariEntitatJPA;
import es.caib.enviafib.ejb.UsuariEntitatEJB;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariEntitatLogicaEJB extends UsuariEntitatEJB implements UsuariEntitatLogicaService {

	@Override
	@PermitAll
	public UsuariEntitat createPublic(UsuariEntitat instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public UsuariEntitatJPA findByPrimaryKeyPublic(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

	@PermitAll
	@Override
	public void delete(UsuariEntitat instance) {
		super.delete(instance);
	}
}
