
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.TraduccioJPA;
import es.caib.enviafib.persistence.TraduccioIJPAManager;
import es.caib.enviafib.model.dao.ITraduccioManager;

import es.caib.enviafib.model.entity.Traduccio;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface TraduccioService extends TraduccioIJPAManager,ITraduccioManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/TraduccioEJB!es.caib.enviafib.ejb.TraduccioService";

    public TraduccioJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Traduccio instance, FitxerService fitxerEjb) throws I18NException;
}
