
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.GrupUsuariJPA;
import es.caib.enviafib.persistence.GrupUsuariIJPAManager;
import es.caib.enviafib.model.dao.IGrupUsuariManager;

import es.caib.enviafib.model.entity.GrupUsuari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface GrupUsuariService extends GrupUsuariIJPAManager,IGrupUsuariManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/GrupUsuariEJB!es.caib.enviafib.ejb.GrupUsuariService";

    public GrupUsuariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(GrupUsuari instance, FitxerService fitxerEjb) throws I18NException;
}
