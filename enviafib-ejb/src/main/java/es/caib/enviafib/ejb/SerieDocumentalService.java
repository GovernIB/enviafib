
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.SerieDocumentalJPA;
import es.caib.enviafib.persistence.SerieDocumentalIJPAManager;
import es.caib.enviafib.model.dao.ISerieDocumentalManager;

import es.caib.enviafib.model.entity.SerieDocumental;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface SerieDocumentalService extends SerieDocumentalIJPAManager,ISerieDocumentalManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/SerieDocumentalEJB!es.caib.enviafib.ejb.SerieDocumentalService";

    public SerieDocumentalJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(SerieDocumental instance, FitxerService fitxerEjb) throws I18NException;
}
