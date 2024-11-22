
package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.UsuariEntitatService;
import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.persistence.UsuariEntitatJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface UsuariEntitatLogicaService extends UsuariEntitatService {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/UsuariEntitatLogicaEJB!es.caib.enviafib.logic.UsuariEntitatLogicaService";

    
    public UsuariEntitat createPublic(UsuariEntitat instance) throws I18NException;

    public UsuariEntitatJPA findByPrimaryKeyPublic(Long _ID_);
}
