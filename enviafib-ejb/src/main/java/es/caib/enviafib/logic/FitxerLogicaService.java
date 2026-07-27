
package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.FitxerService;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.persistence.FitxerJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface FitxerLogicaService extends FitxerService {

	public static final String JNDI_NAME = "java:app/enviafib-ejb/FitxerLogicaEJB!es.caib.enviafib.logic.FitxerLogicaService";

	public Fitxer createPublic(Fitxer instance) throws I18NException;

	public FitxerJPA findByPrimaryKeyPublic(Long _ID_);
}
