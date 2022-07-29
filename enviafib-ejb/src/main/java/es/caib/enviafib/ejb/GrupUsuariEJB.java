
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.annotation.Resource;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.GrupUsuari;
import es.caib.enviafib.persistence.GrupUsuariJPA;
import es.caib.enviafib.persistence.GrupUsuariJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class GrupUsuariEJB extends GrupUsuariJPAManager implements GrupUsuariService {

    @Resource
    protected TransactionSynchronizationRegistry tsRegistry;
    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(GrupUsuari instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupUsuari create(GrupUsuari instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupUsuari update(GrupUsuari instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupUsuariJPA findByPrimaryKey(Long _ID_) {
        return (GrupUsuariJPA)super.findByPrimaryKey(_ID_);
    }

}
