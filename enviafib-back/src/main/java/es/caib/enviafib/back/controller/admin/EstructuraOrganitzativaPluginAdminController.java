package es.caib.enviafib.back.controller.admin;

import java.util.Map;
import java.util.TreeMap;

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
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PluginFilterForm;
import es.caib.enviafib.back.form.webdb.PluginForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.PluginFields;
import es.caib.enviafib.persistence.PluginJPA;

/**
 *
 * @author ptrias
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = EstructuraOrganitzativaPluginAdminController.CONTEXTWEB)
@SessionAttributes(types = { PluginForm.class, PluginFilterForm.class })
public class EstructuraOrganitzativaPluginAdminController extends AbstractPluginAdminController {

    public static final String CONTEXTWEB = "/admin/estructuraorganitzativaplugin";

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public String getTileForm() {
        return "estructuraOrganitzativaPluginFormAdmin";
    }

    @Override
    public String getTileList() {
        return "estructuraOrganitzativaPluginListAdmin";
    }

    @Override
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
//            pluginFilterForm.addHiddenField(PluginFields.DESCRIPCIO);
            pluginFilterForm.addHiddenField(PluginFields.CLASSE);
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
    public ModelAndView provar(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pluginID") Long pluginID, @PathVariable("username") String username) {

        try {
//            String username = LoginInfo.getInstance().getUsername();
            String lang = "ca";

            IEstructuraOrganitzativaPlugin plugin = pluginEstructuraOrganitzativaEjb.getInstanceByPluginID(pluginID);
            
            Map<String, String> map;
            
            Map<String, Map<String, String>> estructura = new TreeMap<String, Map<String,String>>();
            
            map = new TreeMap<String, String>();
            estructura.put("1.-ENTITAT - ORGANITZACIÓ - EMPRESA  ", map);
            map.put("getGerentPresidentName()", plugin.getGerentPresidentName());
            map.put("getGerentPresidentUsername()", plugin.getGerentPresidentUsername());
            map.put("getNameOrganitzacioEmpresa(lang)", plugin.getNameOrganitzacioEmpresa(lang));
            map.put("getDir3OrganitzacioEmpresa()", plugin.getDir3OrganitzacioEmpresa());
            map.put("getNifOrganitzacioEmpresa()", plugin.getNifOrganitzacioEmpresa());

            map = new TreeMap<String, String>();
            estructura.put("2.- ÀREA - CONSELLERIA  ", map);

            map.put("getNameAreaConselleria(String username, String lang)"
                   , plugin.getNameAreaConselleria(username, lang));
            map.put("getDir3AreaConselleria(username)", plugin.getDir3AreaConselleria(username));
            map.put("getCodeAreaConselleria(username)", plugin.getCodeAreaConselleria(username));
            map.put("getCapAreaConsellerUsername(username)", plugin.getCapAreaConsellerUsername(username));
            map.put("getCapAreaConsellerName(username)", plugin.getCapAreaConsellerName(username));
            
            map = new TreeMap<String, String>();
            estructura.put("3.- DEPARTAMENT - DIRECCIÓ GENERAL  ", map);
            map.put("getNameDepartamentDireccioGeneral(username, lang)"
                   , plugin.getNameDepartamentDireccioGeneral(username, lang));
            map.put(
                    "getDir3DepartamentDireccioGeneral(username)", plugin.getDir3DepartamentDireccioGeneral(username));
            map.put(
                    "getCodeDepartamentDireccioGeneral(username)", plugin.getCodeDepartamentDireccioGeneral(username));
            map.put("getCapDepartamentDirectorGeneralUsername(username)"
                   , plugin.getCapDepartamentDirectorGeneralUsername(username));
            map.put("getCapDepartamentDirectorGeneralName(username)"
                   , plugin.getCapDepartamentDirectorGeneralName(username));
            
            map = new TreeMap<String, String>();
            estructura.put("4.- DADES GENERALS  ", map);
            map.put("getSecretariUsername(username)", plugin.getSecretariUsername(username));
            map.put("getSecretariName(username)", plugin.getSecretariName(username));
            map.put("getEncarregatCompresUsername(username)", plugin.getEncarregatCompresUsername(username));
            map.put("getEncarregatCompresName(username)", plugin.getEncarregatCompresName(username));
            map.put("getRecursosHumansUsername(username)", plugin.getRecursosHumansUsername(username));
            map.put("getRecursosHumansName(username)", plugin.getRecursosHumansName(username));
            map = new TreeMap<String, String>();
            estructura.put("5.- CÀRRECS ADDICIONALS  ", map);
            map.put("getCarrec1Username(username)", plugin.getCarrec1Username(username));
            map.put("getCarrec1Name(username)", plugin.getCarrec1Name(username));
            map.put("getCarrec1PositionName(username, lang)", plugin.getCarrec1PositionName(username, lang));
            map.put("getCarrec2Username(username)", plugin.getCarrec2Username(username));
            map.put("getCarrec2Name(username)", plugin.getCarrec2Name(username));
            map.put("getCarrec2PositionName(username, lang)", plugin.getCarrec2PositionName(username, lang));
       
            
            ModelAndView mav = new ModelAndView("estructuraOrganitzativaPluginProvesAdmin");
            
            mav.addObject("username", username);
            mav.addObject("estructura", estructura);
            mav.addObject("tornarurl", getContextWeb() + "/list");

            return mav;
            
/*
            String CapAreaConseller =  campBuit( instance.getCapAreaConsellerUsername(username));
            String CapDepartamentDirectorGeneral = campBuit(instance.getCapDepartamentDirectorGeneralUsername(username));
            String CodiDIR3ByUsername = campBuit(instance.getDir3DepartamentDireccioGeneral(username));
            String EncarregatCompresByUsername = campBuit(instance.getEncarregatCompresUsername(username));
            String GerentPresidentName = campBuit(instance.getGerentPresidentName());
            String GerentPresidentUsername = campBuit(instance.getGerentPresidentUsername());
            String NomAreaConselleria = campBuit(instance.getNameAreaConselleria(username, lang));
            String NomDepartamentDireccioGeneral = campBuit(instance.getNameDepartamentDireccioGeneral(username, lang));
            String RecursosHumans = campBuit(instance.getRecursosHumansUsername(username));
            String Secretari = campBuit(instance.getSecretariUsername(username));
            
            String msg = "<b>Dades organitzatives de " + username + ": </b>";
                msg += "<br>" + "Cap Area/Conseller: " + CapAreaConseller + "<br>" 
                    + "Cap Departament/Director General: " + CapDepartamentDirectorGeneral + "<br>"
                    + "Codi DIR3: " + CodiDIR3ByUsername + "<br>" 
                    + "Encarregat Compres: " + EncarregatCompresByUsername + "<br>"
                    + "Gerent/President Name: " + GerentPresidentName + "<br>"
                     + "Gerent/President Username: " + GerentPresidentUsername + "<br>"
                    + "Area/Conselleria: " + NomAreaConselleria + "<br>"
                    + "Departament/Direccio General: " + NomDepartamentDireccioGeneral + "<br>" 
                    + "Recursos Humans: " + RecursosHumans + "<br>"
                    + "Secretari: " + Secretari + "<br>";

            HtmlUtils.saveMessageInfo(request, msg);
*/
            


            
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);

        } catch (Exception e) {
            String msg = e.getMessage();
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
    }

    public String campBuit(String valor) {
        if (valor == null) {
            return "<b>No definit</b>";
        } else {
            return valor;
        }
    }
}
