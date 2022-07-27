package es.caib.enviafib.ejb;

import javax.ejb.Local;

import es.caib.enviafib.model.dao.IPluginManager;
import es.caib.enviafib.persistence.PluginJPA;

/**
 * 
 * @author fbosch
 *
 */
@Local
public interface PluginLocal extends IPluginManager {

  public static final String JNDI_NAME = "enviafib/PluginEJB/local";
 
  public PluginJPA findByPrimaryKey(Long _ID_);
}
