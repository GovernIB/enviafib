
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.Traduccio;
import es.caib.enviafib.persistence.TraduccioJPA;
import es.caib.enviafib.persistence.TraduccioJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class TraduccioEJB extends TraduccioJPAManager implements TraduccioService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Traduccio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Traduccio create(Traduccio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Traduccio update(Traduccio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public TraduccioJPA findByPrimaryKey(Long _ID_) {
        return (TraduccioJPA)super.findByPrimaryKey(_ID_);
    }

}
