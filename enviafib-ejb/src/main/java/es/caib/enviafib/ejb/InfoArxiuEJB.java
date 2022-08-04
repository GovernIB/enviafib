
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoArxiuJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class InfoArxiuEJB extends InfoArxiuJPAManager implements InfoArxiuService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(InfoArxiu instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoArxiu create(InfoArxiu instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoArxiu update(InfoArxiu instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(InfoArxiu instance, es.caib.enviafib.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public InfoArxiuJPA findByPrimaryKey(Long _ID_) {
        return (InfoArxiuJPA)super.findByPrimaryKey(_ID_);
    }

}
