
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.UsuariEntitatJPA;
import es.caib.enviafib.persistence.UsuariEntitatIJPAManager;
import es.caib.enviafib.model.dao.IUsuariEntitatManager;

import es.caib.enviafib.model.entity.UsuariEntitat;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface UsuariEntitatService extends UsuariEntitatIJPAManager,IUsuariEntitatManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/UsuariEntitatEJB!es.caib.enviafib.ejb.UsuariEntitatService";

    public UsuariEntitatJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(UsuariEntitat instance, FitxerService fitxerEjb) throws I18NException;
}
