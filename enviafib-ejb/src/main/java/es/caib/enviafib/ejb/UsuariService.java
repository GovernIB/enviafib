
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.UsuariJPA;
import es.caib.enviafib.persistence.UsuariIJPAManager;
import es.caib.enviafib.model.dao.IUsuariManager;

import es.caib.enviafib.model.entity.Usuari;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariService extends UsuariIJPAManager,IUsuariManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/UsuariEJB!es.caib.enviafib.ejb.UsuariService";

    public UsuariJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Usuari instance, FitxerService fitxerEjb) throws I18NException;
}
