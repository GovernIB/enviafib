package es.caib.enviafib.logic;

import java.util.List;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.ejb.PluginService;
import es.caib.enviafib.model.entity.Plugin;


/**
 * 
 * @author anadal
 *
 */
public interface AbstractPluginLogicaService<I> extends PluginService {

  public List<Plugin> getAllPlugins() throws I18NException;

  public I getInstanceByPluginID(long pluginID) throws I18NException;

  public Where getWhere();

}
