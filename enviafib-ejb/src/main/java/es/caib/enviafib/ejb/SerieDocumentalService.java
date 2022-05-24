
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.SerieDocumentalJPA;
import es.caib.enviafib.persistence.SerieDocumentalIJPAManager;
import es.caib.enviafib.model.dao.ISerieDocumentalManager;

@Local
public interface SerieDocumentalService extends SerieDocumentalIJPAManager,ISerieDocumentalManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/SerieDocumentalEJB!es.caib.enviafib.ejb.SerieDocumentalService";

    public SerieDocumentalJPA findByPrimaryKey(Long _ID_);
}
