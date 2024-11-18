
package es.caib.enviafib.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.hibernate.Hibernate;

import es.caib.enviafib.ejb.UsuariEJB;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {

	@Override
	@PermitAll
	public Usuari createPublic(Usuari instance) throws I18NException {
		return super.create(instance);
	}

	@Override
	@PermitAll
	public UsuariJPA findByPrimaryKeyPublic(Long _ID_) {
		return super.findByPrimaryKey(_ID_);
	}

	@Override
	@PermitAll
	public Usuari update(Usuari instance) throws I18NException {
		// TODO Auto-generated method stub
		return super.update(instance);
	}
	
	@Override
	@PermitAll
	public Usuari getUserByUsername(String username) {

		List<Usuari> listUsuariPersona;
		try {
			// Cerca de l'usuari que es conecta
			listUsuariPersona = this.select(UsuariFields.USERNAME.equal(username));
			log.info("Llista d'usuaris amb usuariEjb: " + listUsuariPersona.size());

		} catch (I18NException e1) {
			listUsuariPersona = null;
			log.error("Error llegint usuari " + username + " : " + e1.getMessage(), e1);
		}

		UsuariJPA usuariPersona = null;

		if (listUsuariPersona != null && !listUsuariPersona.isEmpty()) {
			usuariPersona = (UsuariJPA) listUsuariPersona.get(0);

			//Hibernate.initialize(usuariPersona.getUsuariEntitats());
		}

		return usuariPersona;
	}

}
