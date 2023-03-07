
package es.caib.enviafib.logic;

import javax.ejb.Stateless;
import javax.annotation.security.PermitAll;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.ejb.InfoArxiuEJB;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class InfoArxiuLogicaEJB extends InfoArxiuEJB implements InfoArxiuLogicaService {



    @Override
    @PermitAll
    public InfoArxiu createPublic(InfoArxiu instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public InfoArxiuJPA findByPrimaryKeyPublic(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }

}
