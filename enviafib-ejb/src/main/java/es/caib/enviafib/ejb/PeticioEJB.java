
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.persistence.PeticioJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class PeticioEJB extends PeticioJPAManager implements PeticioService {

    @Resource
    protected TransactionSynchronizationRegistry tsRegistry;
    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Peticio instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Peticio create(Peticio instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Peticio update(Peticio instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public PeticioJPA findByPrimaryKey(Long _ID_) {
        return (PeticioJPA)super.findByPrimaryKey(_ID_);
    }

}
