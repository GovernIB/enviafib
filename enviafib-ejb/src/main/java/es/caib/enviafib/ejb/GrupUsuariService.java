
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.GrupUsuariJPA;
import es.caib.enviafib.persistence.GrupUsuariIJPAManager;
import es.caib.enviafib.model.dao.IGrupUsuariManager;

@Local
public interface GrupUsuariService extends GrupUsuariIJPAManager,IGrupUsuariManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/GrupUsuariEJB!es.caib.enviafib.ejb.GrupUsuariService";

    public GrupUsuariJPA findByPrimaryKey(Long _ID_);
}
