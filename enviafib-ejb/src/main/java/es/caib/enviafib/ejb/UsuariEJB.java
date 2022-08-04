
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.persistence.UsuariJPA;
import es.caib.enviafib.persistence.UsuariJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class UsuariEJB extends UsuariJPAManager implements UsuariService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(Usuari instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Usuari create(Usuari instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public Usuari update(Usuari instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(Usuari instance, es.caib.enviafib.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public UsuariJPA findByPrimaryKey(Long _ID_) {
        return (UsuariJPA)super.findByPrimaryKey(_ID_);
    }

}
