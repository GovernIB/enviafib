package es.caib.enviafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PluginFilterForm;
import es.caib.enviafib.back.form.webdb.PluginForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.persistence.PluginJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = EstructuraOrganitzativaPluginAdminController.CONTEXTWEB)
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class EstructuraOrganitzativaPluginAdminController extends AbstractPluginAdminController {

    public static final String CONTEXTWEB = "/admin/EstructuraOrganitzativaPlugin";

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    public String getTileForm() {
        return "EstructuraOrganitzativaPluginFormAdmin";
    }

    public String getTileList() {
        return "EstructuraOrganitzativaPluginListAdmin";
    }

    public String getSessionAttributeFilterForm() {
        return "EstructuraOrganitzativaPluginListAdmin_FilterForm";
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

            pluginFilterForm.setTitleCode("EstructuraOrganitzativaPlugin.title");

            pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-check",
                    "EstructuraOrganitzativaPlugin.button", getContextWeb() + "/provar/{0}", "btn-primary"));
        }

        return pluginFilterForm;
    }

    @Override
    public String getContextWebPlugin() {
        return CONTEXTWEB;
    }

    @Override
    public int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA;
    }

    @Override
    public String getCodeName() {
        return "EstructuraOrganitzativaPlugin";
    }

    @RequestMapping(value = "/provar/{pluginID}", method = RequestMethod.GET)
    public String arrancarPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID) {

        try {
            String username = LoginInfo.getInstance().getUsername();

            IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstanceByPluginID(pluginID);

            String CapAreaConseller = instance.getCapAreaConsellerByUsername(username);
            String CapDepartamentDirectorGeneral = instance.getCapDepartamentDirectorGeneralByUsername(username);
            String CodiDIR3ByUsername = instance.getCodiDIR3ByUsername(username);
            String EncarregatCompresByUsername = instance.getEncarregatCompresByUsername(username);

            String msg = "Dades organitzatives de " + username + ": <br>"
            + "CapAreaConseller: " + CapAreaConseller + "<br>"
            + "CapDepartamentDirectorGeneral: " + CapDepartamentDirectorGeneral + "<br>"
            + "CodiDIR3ByUsername: " + CodiDIR3ByUsername + "<br>"
            + "EncarregatCompresByUsername: " + EncarregatCompresByUsername + "<br>";

            HtmlUtils.saveMessageInfo(request, msg);

        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);

        } catch (Exception e) {
//            String msg = I18NUtils.getMessage(e);
            String msg = e.getMessage();
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

}
