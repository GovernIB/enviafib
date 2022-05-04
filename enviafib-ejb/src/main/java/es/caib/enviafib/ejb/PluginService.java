
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.PluginJPA;
import es.caib.enviafib.persistence.PluginIJPAManager;
import es.caib.enviafib.model.dao.IPluginManager;

@Local
public interface PluginService extends PluginIJPAManager,IPluginManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/PluginEJB!es.caib.enviafib.ejb.PluginService";

    public PluginJPA findByPrimaryKey(Long _ID_);
}
