
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.EntitatJPA;
import es.caib.enviafib.persistence.EntitatIJPAManager;
import es.caib.enviafib.model.dao.IEntitatManager;

import es.caib.enviafib.model.entity.Entitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface EntitatService extends EntitatIJPAManager,IEntitatManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/EntitatEJB!es.caib.enviafib.ejb.EntitatService";

    public EntitatJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Entitat instance, FitxerService fitxerEjb) throws I18NException;
}
