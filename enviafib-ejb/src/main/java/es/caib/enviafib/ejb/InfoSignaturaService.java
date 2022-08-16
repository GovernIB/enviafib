
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.persistence.InfoSignaturaIJPAManager;
import es.caib.enviafib.model.dao.IInfoSignaturaManager;

import es.caib.enviafib.model.entity.InfoSignatura;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface InfoSignaturaService extends InfoSignaturaIJPAManager,IInfoSignaturaManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoSignaturaEJB!es.caib.enviafib.ejb.InfoSignaturaService";

    public InfoSignaturaJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(InfoSignatura instance, FitxerService fitxerEjb) throws I18NException;
}
