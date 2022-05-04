
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.FitxerIJPAManager;
import es.caib.enviafib.model.dao.IFitxerManager;

@Local
public interface FitxerService extends FitxerIJPAManager,IFitxerManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/FitxerEJB!es.caib.enviafib.ejb.FitxerService";

    public FitxerJPA findByPrimaryKey(Long _ID_);
}
