
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Stateless;
import java.util.ArrayList;
import javax.annotation.security.RolesAllowed;
import org.fundaciobit.genapp.common.i18n.I18NException;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.persistence.SerieDocumentalJPA;
import es.caib.enviafib.persistence.SerieDocumentalJPAManager;

import es.caib.enviafib.commons.utils.Constants;

@Stateless
public class SerieDocumentalEJB extends SerieDocumentalJPAManager implements SerieDocumentalService {

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public void delete(SerieDocumental instance) {
        super.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public SerieDocumental create(SerieDocumental instance) throws I18NException {
        return super.create(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public SerieDocumental update(SerieDocumental instance) throws I18NException {
         return super.update(instance);
    }

    public void deleteIncludingFiles(SerieDocumental instance, es.caib.enviafib.ejb.FitxerService fitxerEjb)
            throws I18NException {

        this.delete(instance);
    }

    @Override
    @RolesAllowed({Constants.ROLE_EJB_FULL_ACCESS, Constants.ROLE_EJB_BASIC_ACCESS})
    public SerieDocumentalJPA findByPrimaryKey(Long _ID_) {
        return (SerieDocumentalJPA)super.findByPrimaryKey(_ID_);
    }

}
