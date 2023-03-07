
package es.caib.enviafib.logic;

import javax.ejb.Stateless;
import javax.annotation.security.PermitAll;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.ejb.InfoSignaturaEJB;

/**
 * 
 * @author ptrias
 *
 */
@Stateless
public class InfoSignaturaLogicaEJB extends InfoSignaturaEJB implements InfoSignaturaLogicaService {

    @Override
    @PermitAll
    public void deletePublic(InfoSignatura instance) {
        super.delete(instance);
    }

    @Override
    @PermitAll
    public InfoSignatura createPublic(InfoSignatura instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public InfoSignaturaJPA findByPrimaryKeyPublic(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }
    
}
