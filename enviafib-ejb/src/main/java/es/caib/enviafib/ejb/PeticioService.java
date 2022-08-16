
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.persistence.PeticioIJPAManager;
import es.caib.enviafib.model.dao.IPeticioManager;

import es.caib.enviafib.model.entity.Peticio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PeticioService extends PeticioIJPAManager,IPeticioManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/PeticioEJB!es.caib.enviafib.ejb.PeticioService";

    public PeticioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Peticio instance, FitxerService fitxerEjb) throws I18NException;
}
