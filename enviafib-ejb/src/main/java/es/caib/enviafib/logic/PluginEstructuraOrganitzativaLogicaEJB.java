package es.caib.enviafib.logic;

import javax.ejb.Stateless;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import es.caib.enviafib.commons.utils.Constants;

/**
 *
 * @author anadal(u80067)
 */
@Stateless(name = "PluginEstructuraOrganitzativaLogicaEJB")
public class PluginEstructuraOrganitzativaLogicaEJB extends AbstractPluginLogicaEJB<IEstructuraOrganitzativaPlugin>
        implements PluginEstructuraOrganitzativaLogicaService {

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA;
    }

    @Override
    protected String getName() {
        return "EstructuraOrganitzativa";
    }

}
