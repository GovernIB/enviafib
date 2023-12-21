
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.InfoAnexJPA;
import es.caib.enviafib.persistence.InfoAnexIJPAManager;
import es.caib.enviafib.model.dao.IInfoAnexManager;

import es.caib.enviafib.model.entity.InfoAnex;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface InfoAnexService extends InfoAnexIJPAManager,IInfoAnexManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoAnexEJB!es.caib.enviafib.ejb.InfoAnexService";

    public InfoAnexJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(InfoAnex instance, FitxerService fitxerEjb) throws I18NException;
}
