
package es.caib.enviafib.logic;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.hibernate.Hibernate;

import es.caib.enviafib.ejb.UsuariEJB;
import es.caib.enviafib.ejb.UsuariEntitatService;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariEntitatFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariEntitatJPA;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class UsuariLogicaEJB extends UsuariEJB implements UsuariLogicaService {
	
	@EJB(mappedName = PeticioLogicaService.JNDI_NAME)
	protected PeticioLogicaService peticioLogicaEjb;

	
	@EJB(mappedName = UsuariEntitatLogicaService.JNDI_NAME)
	protected UsuariEntitatService usuariEntitatLogicaEjb;
	
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
	
	
	@Override
	public void delete(Usuari instance) {
		// Cuando se borra un usuario, borrar todas sus peticiones y sus registros en la
		// tabla usuari_entitat
		Long usuariID = instance.getUsuariID();
		try {
			List<UsuariEntitat> usuariEntitats = usuariEntitatLogicaEjb
					.select(UsuariEntitatFields.USUARIID.equal(usuariID));

			for (UsuariEntitat usuariEntitat : usuariEntitats) {
				usuariEntitatLogicaEjb.delete(usuariEntitat);
			}

		} catch (I18NException e) {
			log.error("Error al borrar en la tabla usuariEntitat" + usuariID + " : " + e.getMessage(), e);
		}

		try {
			List<Peticio> peticions = peticioLogicaEjb.select(PeticioFields.SOLICITANTID.equal(usuariID));

			for (Peticio peticio : peticions) {
				peticioLogicaEjb.delete(peticio);
			}

		} catch (I18NException e) {
			log.error("Error al borrar en la tabla peticio" + usuariID + " : " + e.getMessage(), e);
		}

		super.delete(instance);
	}

}
