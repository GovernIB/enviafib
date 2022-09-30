
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.OrganitzacioJPA;
import es.caib.enviafib.persistence.OrganitzacioIJPAManager;
import es.caib.enviafib.model.dao.IOrganitzacioManager;

import es.caib.enviafib.model.entity.Organitzacio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface OrganitzacioService extends OrganitzacioIJPAManager,IOrganitzacioManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/OrganitzacioEJB!es.caib.enviafib.ejb.OrganitzacioService";

    public OrganitzacioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Organitzacio instance, FitxerService fitxerEjb) throws I18NException;
}
