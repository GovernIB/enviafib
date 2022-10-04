package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.PluginController;
import es.caib.enviafib.back.form.webdb.PluginFilterForm;
import es.caib.enviafib.back.form.webdb.PluginForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PluginService;
import es.caib.enviafib.model.entity.Plugin;
import es.caib.enviafib.model.fields.PluginFields;
import es.caib.enviafib.persistence.PluginJPA;

/**
 * 
 * @author anadal(u80067)
 *
 */
public abstract class AbstractPluginAdminController extends PluginController {

    @EJB(mappedName = PluginService.JNDI_NAME)
    protected PluginService pluginLogicaEjb;

    public abstract String getContextWebPlugin();

    public abstract int getTipusDePlugin();

    public abstract String getCodeName();

    @Override
    public String getSessionAttributeFilterForm() {
        return getClass().getName() + "_FilterForm";
    }

    @Override
    public final Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        return TIPUS.equal(getTipusDePlugin());
    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm;
        pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

        if (pluginFilterForm.isNou()) {

            pluginFilterForm.getDefaultGroupByFields().remove(TIPUS);

//            pluginFilterForm.addHiddenField(PluginFields.PLUGINID);
//            pluginFilterForm.addHiddenField(PluginFields.NOMID);
//            pluginFilterForm.addHiddenField(PluginFields.DESCRIPCIO);
//            pluginFilterForm.addHiddenField(PluginFields.CLASSE);
            pluginFilterForm.addHiddenField(PluginFields.PROPERTIES);
//            pluginFilterForm.addHiddenField(PluginFields.TIPUS);
//            pluginFilterForm.addHiddenField(PluginFields.ACTIU);

            pluginFilterForm.setOrderBy(NOM.javaName);
            pluginFilterForm.setOrderAsc(true);

            OrderBy[] orderby = { new OrderBy(PluginFields.NOM) };
            pluginFilterForm.setDefaultOrderBy(orderby);
        }

        return pluginFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PluginFilterForm filterForm, List<Plugin> list)
            throws I18NException {

        filterForm.getAdditionalButtonsByPK().clear();

        for (Plugin p : list) {
            long pluginID = p.getPluginID();
            if (p.isActiu()) {
                filterForm.addAdditionalButtonByPK(pluginID, new AdditionalButton("fas fa-ban icon-white",
                        "plugin.desactivar", getContextWebPlugin() + "/desactivarplugin/" + pluginID, "btn-warning"));
            } else {
                filterForm.addAdditionalButtonByPK(pluginID, new AdditionalButton("far fa-check-circle icon-white",
                        "plugin.activar", getContextWebPlugin() + "/activarplugin/" + p.getPluginID(), "btn-success"));
            }

        }
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm = super.getPluginForm(_jpa, __isView, request, mav);

        PluginJPA p = pluginForm.getPlugin();

        if (pluginForm.isNou()) {
            p.setActiu(true);
            p.setTipus(getTipusDePlugin());

        } else {
            pluginForm.addReadOnlyField(ACTIU);
        }

        pluginForm.addHiddenField(TIPUS);

        return pluginForm;
    }

    @RequestMapping(value = "/activarplugin/{pluginid}", method = RequestMethod.GET)
    public String pluginActivate(HttpServletRequest request, HttpServletResponse response, @PathVariable Long pluginid)
            throws I18NException {

        PluginJPA p = pluginLogicaEjb.findByPrimaryKey(pluginid);

        p.setActiu(true);
        pluginLogicaEjb.update(p);

        String msg = I18NUtils.tradueix("success.activarplugin", String.valueOf(p.getPluginID()));
        HtmlUtils.saveMessageSuccess(request, msg);

        return "redirect:" + getContextWebPlugin() + "/list/";
    }

    @RequestMapping(value = "/desactivarplugin/{pluginid}", method = RequestMethod.GET)
    public String pluginDeactivate(HttpServletRequest request, HttpServletResponse response,
            @PathVariable Long pluginid) throws I18NException {

        // TODO: Modificar métode per les necessitas que facin falta

        PluginJPA p = pluginLogicaEjb.findByPrimaryKey(pluginid);

        int tipusPlugin = getTipusDePlugin();

        switch (tipusPlugin) {
            case Constants.TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA:

                // Controla si és l'unic Plugin Actiu disponible i mostrar missatge
                List<Long> llistaPlugins = pluginLogicaEjb.executeQuery(PluginFields.PLUGINID,
                        Where.AND(PluginFields.TIPUS.equal(tipusPlugin), PluginFields.ACTIU.equal(true)));

                if (llistaPlugins.size() == 1 && llistaPlugins.get(0) == pluginid) {
                    String plugin = I18NUtils.tradueix("estructuraorganitzativaplugin");
                    String msg = I18NUtils.tradueix("unic.plugin.actiu", plugin);
                    HtmlUtils.saveMessageSuccess(request, msg);
                }
            break;

            default:
                //String msg = I18NUtils.tradueix("plugin.tipus.notrobat", String.valueOf(tipusPlugin));
                //throw new I18NException("genapp.comodi", msg);
                throw new I18NException("plugin.tipus.notrobat", new I18NArgumentString(String.valueOf(tipusPlugin)));
        }

        p.setActiu(false);
        pluginLogicaEjb.update(p);
        String msg = I18NUtils.tradueix("success.desactivarplugin", p.getClasse());

        HtmlUtils.saveMessageSuccess(request, msg);

        return "redirect:" + getContextWebPlugin() + "/list/";
    }

    @Override
    public String getEntityNameCode() {
        return getCodeName();
    }

    @Override
    public String getEntityNameCodePlural() {
        return getCodeName() + ".plural";
    }

    @Override
    public PluginJPA update(HttpServletRequest request, PluginJPA plugin)
            throws I18NException, I18NValidationException {
        return (PluginJPA) pluginEjb.update(plugin);
    }

    @Override
    public void delete(HttpServletRequest request, Plugin plugin) throws I18NException {
        pluginEjb.delete(plugin);
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("" + Constants.TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA,
                "PLUGIN ESTRUCTURA ORGANITZATIVA"));
        return __tmp;
    }

}
