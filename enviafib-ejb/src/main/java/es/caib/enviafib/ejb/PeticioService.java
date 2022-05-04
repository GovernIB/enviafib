
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.persistence.PeticioIJPAManager;
import es.caib.enviafib.model.dao.IPeticioManager;

@Local
public interface PeticioService extends PeticioIJPAManager,IPeticioManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/PeticioEJB!es.caib.enviafib.ejb.PeticioService";

    public PeticioJPA findByPrimaryKey(Long _ID_);
}
