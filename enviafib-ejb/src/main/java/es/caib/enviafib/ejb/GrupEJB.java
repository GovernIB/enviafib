
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.Grup;
import es.caib.enviafib.persistence.GrupJPA;
import es.caib.enviafib.persistence.GrupJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class GrupEJB extends GrupJPAManager implements GrupService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Grup instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Grup create(Grup instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Grup update(Grup instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Grup instance, es.caib.enviafib.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public GrupJPA findByPrimaryKey(Long _ID_) {
        return (GrupJPA)super.findByPrimaryKey(_ID_);
    }

}
