
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.PluginJPA;
import es.caib.enviafib.persistence.PluginIJPAManager;
import es.caib.enviafib.model.dao.IPluginManager;

import es.caib.enviafib.model.entity.Plugin;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/PluginEJB!es.caib.enviafib.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Plugin instance, FitxerService fitxerEjb) throws I18NException;
}
