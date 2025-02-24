
package es.caib.enviafib.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.AvisService;
import es.caib.enviafib.model.entity.Avis;
import es.caib.enviafib.persistence.AvisJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface AvisLogicaService extends AvisService {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/AvisLogicaEJB!es.caib.enviafib.logic.AvisLogicaService";

    
    public Avis createPublic(Avis instance) throws I18NException;

    public AvisJPA findByPrimaryKeyPublic(Long _ID_);

    public List<Avis> findAvisosActivos() throws I18NException;
}
