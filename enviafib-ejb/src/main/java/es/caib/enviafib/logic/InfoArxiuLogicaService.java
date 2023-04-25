
package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.InfoArxiuService;
import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.persistence.InfoArxiuJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface InfoArxiuLogicaService extends InfoArxiuService {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoArxiuLogicaEJB!es.caib.enviafib.logic.InfoArxiuLogicaService";

    
    public InfoArxiu createPublic(InfoArxiu instance) throws I18NException;

    public InfoArxiuJPA findByPrimaryKeyPublic(Long _ID_);
}
