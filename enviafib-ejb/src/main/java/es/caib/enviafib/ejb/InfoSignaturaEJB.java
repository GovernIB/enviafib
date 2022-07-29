
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class InfoSignaturaEJB extends InfoSignaturaJPAManager implements InfoSignaturaService {

    @Resource
    protected TransactionSynchronizationRegistry tsRegistry;
    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(InfoSignatura instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoSignatura create(InfoSignatura instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoSignatura update(InfoSignatura instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoSignaturaJPA findByPrimaryKey(Long _ID_) {
        return (InfoSignaturaJPA)super.findByPrimaryKey(_ID_);
    }

}
