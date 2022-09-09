package es.caib.enviafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PluginFilterForm;
import es.caib.enviafib.back.form.webdb.PluginForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.PluginFields;
import es.caib.enviafib.persistence.PluginJPA;

@Controller
@RequestMapping(value = ArxiuPluginAdminController.CONTEXTWEB)
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class ArxiuPluginAdminController extends AbstractPluginAdminController {

    public static final String CONTEXTWEB = "/admin/arxiuplugin";

    @EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuEjb;

    public String getTileForm() {
        return "arxiupluginFormAdmin";
    }

    public String getTileList() {
        return "arxiupluginListAdmin";
    }

    public String getSessionAttributeFilterForm() {
        return "arxiupluginListAdmin_FilterForm";
    }

    @Override
    public PluginForm getPluginForm(PluginJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PluginForm pluginForm;
        pluginForm = super.getPluginForm(_jpa, __isView, request, mav);

        if (pluginForm.isNou()) {
        }

        return pluginForm;
    }

    @Override
    public PluginFilterForm getPluginFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PluginFilterForm pluginFilterForm;
        pluginFilterForm = super.getPluginFilterForm(pagina, mav, request);

        if (pluginFilterForm.isNou()) {

            pluginFilterForm.addHiddenField(PluginFields.PLUGINID);
            pluginFilterForm.addHiddenField(PluginFields.DESCRIPCIO);
            pluginFilterForm.addHiddenField(PluginFields.TIPUS);

            pluginFilterForm.setTitleCode("arxiuplugin.title");

            pluginFilterForm.setAttachedAdditionalJspCode(true);
        }

        return pluginFilterForm;
    }

    @Override
    public String getContextWebPlugin() {
        return CONTEXTWEB;
    }

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_ARXIU;
    }

    @Override
    public String getCodeName() {
        return "arxiuplugin";
    }

}
