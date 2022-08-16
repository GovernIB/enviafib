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
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.PluginFields;
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

            pluginFilterForm.addHiddenField(PluginFields.PLUGINID);
            pluginFilterForm.addHiddenField(PluginFields.DESCRIPCIO);
//            pluginFilterForm.addHiddenField(PluginFields.CLASSE);
            pluginFilterForm.addHiddenField(PluginFields.TIPUS);

            pluginFilterForm.setTitleCode("estructuraorganitzativaplugin.title");

            pluginFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-check",
                    "estructuraorganitzativaplugin.button", "javascript: provarPlugin({0})",

                    // getContextWeb() + "/provar/{0}",
                    "btn-primary"));

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
        return Constants.TIPUS_PLUGIN_ESTRUCTURAORGANITZATIVA;
    }

    @Override
    public String getCodeName() {
        return "estructuraorganitzativaplugin";
    }

    @RequestMapping(value = "/provar/{pluginID}/{username}", method = RequestMethod.GET)
    public String arrancarPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID, @PathVariable("username") String username) {

        try {
//            String username = LoginInfo.getInstance().getUsername();
            String lang = "ca";

            IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstanceByPluginID(pluginID);

            String CapAreaConseller =  campBuit( instance.getCapAreaConsellerByUsername(username));
            String CapDepartamentDirectorGeneral = campBuit(instance.getCapDepartamentDirectorGeneralByUsername(username));
            String CodiDIR3ByUsername = campBuit(instance.getCodiDIR3ByUsername(username));
            String EncarregatCompresByUsername = campBuit(instance.getEncarregatCompresByUsername(username));
            String GerentPresident = campBuit(instance.getGerentPresident());
            String NomAreaConselleria = campBuit(instance.getNomAreaConselleriaByUsername(username, lang));
            String NomDepartamentDireccioGeneral = campBuit(instance.getNomDepartamentDireccioGeneralByUsername(username, lang));
            String RecursosHumans = campBuit(instance.getRecursosHumansByUsername(username));
            String Secretari = campBuit(instance.getSecretariByUsername(username));
            
            String msg = "<b>Dades organitzatives de " + username + ": </b>";
                msg += "<br>" + "Cap Area/Conseller: " + CapAreaConseller + "<br>" 
                    + "Cap Departament/Director General: " + CapDepartamentDirectorGeneral + "<br>"
                    + "Codi DIR3: " + CodiDIR3ByUsername + "<br>" 
                    + "Encarregat Compres: " + EncarregatCompresByUsername + "<br>"
                    + "Gerent/President: " + GerentPresident + "<br>"
                    + "Area/Conselleria: " + NomAreaConselleria + "<br>"
                    + "Departament/Direccio General: " + NomDepartamentDireccioGeneral + "<br>" 
                    + "Recursos Humans: " + RecursosHumans + "<br>"
                    + "Secretari: " + Secretari + "<br>";

            HtmlUtils.saveMessageInfo(request, msg);

            
//            
//            Map<String, String> map = new HashMap<String, String>();
//
//            map.put("Cap Area/Conseller", instance.getCapAreaConsellerByUsername(username));
//            map.put("Cap Departament/Director General", instance.getCapDepartamentDirectorGeneralByUsername(username));
//            map.put("Codi DIR3", instance.getCodiDIR3ByUsername(username));
//            map.put("Encarregat Compres", instance.getEncarregatCompresByUsername(username));
//            map.put("Gerent/President", instance.getGerentPresident());
//            map.put("Area/Conselleria", instance.getNomAreaConselleriaByUsername(username, lang));
//            map.put("Departament/Direccio General", instance.getNomDepartamentDireccioGeneralByUsername(username, lang));
//            map.put("Recursos Humans", instance.getRecursosHumansByUsername(username));
//            map.put("Secretari", instance.getSecretariByUsername(username));
//            
//            msg = "<b>Dades organitzatives de " + username + ": </b>";
//
//            for (Map.Entry<String, String> entry : map.entrySet()) {
//                msg += "<br>" + entry.getKey() + ": " + campBuit(entry.getValue());                
//            }
//            
//            HtmlUtils.saveMessageInfo(request, msg);

            
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);

        } catch (Exception e) {
            String msg = e.getMessage();
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    public String campBuit(String valor) {
        if (valor == null) {
            return "<b>No definit</b>";
        } else {
            return valor;
        }
    }
}
