package es.caib.enviafib.logic;

import javax.ejb.Local;

import es.caib.enviafib.ejb.PluginLocal;


/**
 * 
 * @author fbosch
 *
 */
@Local
public interface PluginLogicaLocal extends PluginLocal {
  
  public static final String JNDI_NAME = "java:app/enviafib-back/PluginLogicaEJB!es.caib.enviafib.logic.PluginLogicaLocal";

  public void clearCache();

}
