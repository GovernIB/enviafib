package es.caib.enviafib.back.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaPlantillaFluxUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaPlantillaFluxUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/firmaplantillaflux";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(PeticioFields.PETICIOPORTAFIRMES);
        peticioForm.addLabel(PeticioFields.PETICIOPORTAFIRMES, "plantilla.flux");

        if (peticioForm.isNou()) {

            List<FlowTemplateSimpleKeyValue> llistatPlantilles = getPlantillesFluxFirma();

            if (llistatPlantilles.size() > 0) {
                mav.addObject("plantillaflux", true);
                mav.addObject("plantillesUsuari", llistatPlantilles);

            } else {
                String msg = "TODO: No hi ha plantilles per aquest usuari";
                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }
        }

        peticioForm.setAttachedAdditionalJspCode(true);

        return peticioForm;
    }

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_PLANTILLAFLUX;
    }

    public List<FlowTemplateSimpleKeyValue> getPlantillesFluxFirma() throws I18NException {

        ApiFlowTemplateSimple api = FluxFirmaUserController.getApiFlowTemplateSimple();

        String username = LoginInfo.getInstance().getUsername();
        final String languageUI = "ca";

        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        filter.setDescriptionFilter(FluxFirmaUserController.getFluxFilterByUserName(username));

        try {
            FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplatesByFilter(filter);

            List<FlowTemplateSimpleKeyValue> plantilles = list.getList();

            log.info(" PLANTILLES OBTINGUDES: " + plantilles.size());

            return plantilles;

        } catch (AbstractApisIBException e) {
            String msg = "Error consultant API de Plantilles de Flux per username: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }

}
