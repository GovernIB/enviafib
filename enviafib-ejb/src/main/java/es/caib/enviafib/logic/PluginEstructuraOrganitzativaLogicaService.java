package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;


/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginEstructuraOrganitzativaLogicaService extends AbstractPluginLogicaService<IEstructuraOrganitzativaPlugin> {

    public static final String KEY_ERROR = "* ERROR: ";
    
    public static final String JNDI_NAME = "java:app/enviafib-ejb/PluginEstructuraOrganitzativaLogicaEJB!es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService";
    
}
