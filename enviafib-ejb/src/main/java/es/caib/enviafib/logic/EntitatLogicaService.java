
package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.EntitatService;
import es.caib.enviafib.model.entity.Entitat;
import es.caib.enviafib.persistence.EntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface EntitatLogicaService extends EntitatService {

	public static final String JNDI_NAME = "java:app/enviafib-ejb/EntitatLogicaEJB!es.caib.enviafib.logic.EntitatLogicaService";

	public Entitat createPublic(Entitat instance) throws I18NException;

	public EntitatJPA findByPrimaryKeyPublic(String _ID_);
}
