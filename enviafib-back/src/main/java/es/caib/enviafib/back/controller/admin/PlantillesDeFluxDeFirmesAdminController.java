package es.caib.enviafib.back.controller.admin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import es.caib.enviafib.back.controller.user.FirmaFluxUserController;
import es.caib.enviafib.back.controller.user.FirmaPlantillaFluxEntitatUserController;
import es.caib.enviafib.back.controller.user.PlantillesDeFluxDeFirmesUserController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.model.entity.Usuari;

/**
 * 
 * @author fbosch
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/plantillesfluxfirmes")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class PlantillesDeFluxDeFirmesAdminController extends PlantillesDeFluxDeFirmesUserController {
    
    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes.admin";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.admin.plural";
    }

    @Override
    public String getTileForm() {
        return "plantillesfluxfirmesFormAdmin";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListAdmin";
    }

    @Override
    public String getOwner() {
        return FirmaPlantillaFluxEntitatUserController.OWNER_PLANTILLES_DE_LA_ENTITAT;
    }

    @RequestMapping(value = "/mostrarjson/{flowID}")
    public String mostrarJson(@PathVariable("flowID")
    String flowID, HttpServletRequest request, HttpServletResponse response) {

        ApiFlowTemplateSimple api = null;
        try {

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            api = FirmaFluxUserController.getApiFlowTemplateSimple();
            FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
            flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowID);

            FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            
            FirmaAsyncSimpleSignatureBlock[] blocks = peticioLogicaEjb.convertFluxToSignatureBlocks(flux);

            String json = gson.toJson(blocks);
            
            StringBuilder jsonStr = new StringBuilder();
            
            long lines = 0;
            String line;
            try (BufferedReader reader = new BufferedReader(new StringReader(json))) {
                while ((line=reader.readLine()) != null) {
                    lines++;
                    int pos = line.indexOf("intermediateServerUsername");
                    if (pos == -1) {
                      jsonStr.append(line);
                    } else {
                        pos = line.lastIndexOf('_');
                        int pos2 = line.lastIndexOf('"', pos);
                        String entity = line.substring(pos2 + 1, pos + 1);

                        line = line.replace("intermediateServerUsername", "username").replace(entity, "");

                        jsonStr.append(line);
                    }
                    jsonStr.append("\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            

            HtmlUtils.saveMessageInfo(request, "<textarea readonly cols=\"80\"  rows=\"" + lines + "\" style=\"font-family: monospace;\">\n"
                    +  jsonStr.toString() + "</textarea>");

        } catch (Throwable e) {

            String msg = "Error desconegut intentant obtenir el codi JSON de la plantilla amb ID " + flowID + ":";

            if (e instanceof I18NException) {
                msg = msg + I18NUtils.getMessage((I18NException) e);
            } else {
                msg = msg + e.getMessage();
            }

            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);

        }

        return "redirect:" + getContextWeb() + "/list";

    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {
        
        super.postList(request, mav, filterForm, list);
        
        for (Usuari usuari : list) {
            // BOTO PER MOSTRAR FLUX JSON
            filterForm.addAdditionalButtonByPK(usuari.getUsuariID(), new AdditionalButton("fas fa-code",
                    "json", getContextWeb() + "/mostrarjson/" + usuari.getNif(), "btn-success"));
        }

    }

}
