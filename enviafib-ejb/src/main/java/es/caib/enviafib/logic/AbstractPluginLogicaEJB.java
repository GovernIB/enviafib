package es.caib.enviafib.logic;

import java.io.StringReader;
import java.util.List;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.IPlugin;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PluginEJB;
import es.caib.enviafib.model.entity.Plugin;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PluginFields;
import es.caib.enviafib.persistence.PluginJPA;

/**
 *
 * @author anadal
 *
 */
public abstract class AbstractPluginLogicaEJB<I extends IPlugin> extends PluginEJB
        implements AbstractPluginLogicaService<I> {

    protected abstract int getTipusDePlugin();

    protected abstract String getName();

    @Override
    public List<Plugin> getAllPlugins() throws I18NException {

        Where where = getWhere();

        return select(where);

    }

    @Override
    public Where getWhere() {
        return Where.AND(TIPUS.equal(getTipusDePlugin()), ACTIU.equal(true));
    }

    @Override
    public I getInstance() throws I18NException {

        List<Long> pluginList = this.executeQuery(PluginFields.PLUGINID, getWhere(), new OrderBy(PluginFields.PLUGINID));

        if (pluginList == null || pluginList.size() == 0) {
            throw new I18NException("error.plugin.noactiu",
                    new I18NArgumentCode(PeticioFields.ARXIUPARAMFUNCIONARIDIR3.codeLabel));
        }

        Long pluginID = pluginList.get(0);
        
        I instance = this.getInstanceByPluginID(pluginID);
        return instance;
    }

    @Override
    public I getInstanceByPluginID(long pluginID) throws I18NException {

        IPlugin pluginInstance = null;

        if (pluginInstance == null) {

            PluginJPA plugin = (PluginJPA) findByPrimaryKey(pluginID);

            if (plugin == null) {
                return null;
            }

            Properties prop = new Properties();

            // CODI ORIGINAL
//      if (plugin.getProperties() != null && plugin.getProperties().trim().length() != 0) {
//        try {
//
//          prop.load(new StringReader(plugin.getProperties()));
//
//        } catch (Exception e) {
//          // TODO Crec que no es cridarà mai
//        }
//      }

            if (plugin.getProperties() != null && plugin.getProperties().trim().length() != 0) {
                try {

                    // Exemple:
                    // [=SP["es.caib.digitalib.plugins.signatureserver.afirmaserver.authorization.password"]]

//                    Map<String, Object> map = new HashMap<String, Object>();
//                    map.put("SP", System.getProperties());
//
                    String plantilla = plugin.getProperties();
//                    String generat = TemplateEngine.processExpressionLanguageSquareBrackets(plantilla, map,
//                            new Locale("ca"));

                    final String generat = plantilla;
                    // log.error("PROPIETATS DESPRES DE generat:\n" + generat + "\n");

                    prop.load(new StringReader(generat));

                } catch (Exception e) {
                    throw new I18NException(e, "genapp.comodi", new I18NArgumentString(
                            "Error desconegut processant propietats del plugin " + pluginID + ": " + e.getMessage()));
                }
            }

            pluginInstance = (IPlugin) PluginsManager.instancePluginByClassName(plugin.getClasse(),
                    Constants.ENVIAFIB_PROPERTY_BASE, prop);

            if (pluginInstance == null) {
                throw new I18NException("error.plugin.donotinstantiate", getName() + " (" + plugin.getClasse() + ")");
            }

        }
        return (I) pluginInstance;
    }

}
