
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.GrupJPA;
import es.caib.enviafib.persistence.GrupIJPAManager;
import es.caib.enviafib.model.dao.IGrupManager;

@Local
public interface GrupService extends GrupIJPAManager,IGrupManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/GrupEJB!es.caib.enviafib.ejb.GrupService";

    public GrupJPA findByPrimaryKey(Long _ID_);
}
