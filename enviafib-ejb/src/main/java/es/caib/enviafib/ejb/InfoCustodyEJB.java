
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.InfoCustody;
import es.caib.enviafib.persistence.InfoCustodyJPA;
import es.caib.enviafib.persistence.InfoCustodyJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class InfoCustodyEJB extends InfoCustodyJPAManager implements InfoCustodyService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(InfoCustody instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoCustody create(InfoCustody instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoCustody update(InfoCustody instance) throws I18NException {
         return super.update(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoCustodyJPA findByPrimaryKey(Long _ID_) {
        return (InfoCustodyJPA)super.findByPrimaryKey(_ID_);
    }

}
