
package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.UsuariService;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UsuariLogicaService extends UsuariService {

	public static final String JNDI_NAME = "java:app/enviafib-ejb/UsuariLogicaEJB!es.caib.enviafib.logic.UsuariLogicaService";

	public Usuari createPublic(Usuari instance) throws I18NException;

	public UsuariJPA findByPrimaryKeyPublic(Long _ID_);

	Usuari getUserByUsername(String username);
}
