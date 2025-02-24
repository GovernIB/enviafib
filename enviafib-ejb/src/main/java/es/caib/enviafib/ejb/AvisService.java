
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.AvisJPA;
import es.caib.enviafib.persistence.AvisIJPAManager;
import es.caib.enviafib.model.dao.IAvisManager;

import es.caib.enviafib.model.entity.Avis;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface AvisService extends AvisIJPAManager,IAvisManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/AvisEJB!es.caib.enviafib.ejb.AvisService";

    public AvisJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Avis instance, FitxerService fitxerEjb) throws I18NException;
}
