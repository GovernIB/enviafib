package es.caib.enviafib.back.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsUserController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaPlantillaFluxUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaPlantillaFluxUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/firmaplantillafluxusuari";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(PeticioFields.PETICIOPORTAFIRMES);
        peticioForm.addLabel(PeticioFields.PETICIOPORTAFIRMES, "plantilla.flux");

        if (peticioForm.isNou()) {

            List<Usuari> llistatPlantilles = getPlantillesFluxFirma();

            if (llistatPlantilles.size() > 0) {
                mav.addObject("plantillaflux", true);
                mav.addObject("plantillesUsuari", llistatPlantilles);

            } else {
                String msg = I18NUtils.tradueix("plantillaflux.empty.usuari", getOwner());

                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(AbstractLlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }
        }

        peticioForm.setAttachedAdditionalJspCode(true);

        return peticioForm;
    }

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_PLANTILLAFLUX_USUARI;
    }

    public List<Usuari> getPlantillesFluxFirma() throws I18NException {

        ApiFlowTemplateSimple api = FluxFirmaUserController.getApiFlowTemplateSimple();

        final String languageUI = "ca";
        FlowTemplateSimpleFilterGetAllByFilter filter = getFilterPlantillaFluxFirma(languageUI);

        try {
            FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplatesByFilter(filter);

            List<FlowTemplateSimpleKeyValue> plantilles = list.getList();

            log.info(" PLANTILLES OBTINGUDES: " + plantilles.size());

            List<Usuari> usuaris = new ArrayList<Usuari>();

            for (FlowTemplateSimpleKeyValue flowKeyValue : plantilles) {

                String flowTemplateId = flowKeyValue.getKey();

                FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

                FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

                String description = flux.getDescription().replace("}\n{", "}<br/>{").replace("}\r\n{", "}<br/>{")
                        .replace("}{", "}<br/>{");


                /* usuariID -> flowTemplateId hashed
                 * nif -> flowTemplateId
                 * nom -> value
                 * llinatge1 -> description
                 * email -> creationDate
                 */
                
                Usuari usuari = new UsuariJPA();
                usuari.setUsuariID((long) flowTemplateId.hashCode());
                usuari.setNif(flowTemplateId);
                usuari.setNom(flowKeyValue.getValue());
                usuari.setLlinatge1(description);

//                usuari.setEmail(getCreationDate(description));

                if (description.indexOf("{template=true}") != -1) {
                    usuaris.add(usuari);
                }
            }

            return usuaris;

        } catch (AbstractApisIBException e) {
            String msg = "Error consultant API de Plantilles de Flux per username: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }

    public String getOwner() {
        return LoginInfo.getInstance().getUsername();
    }

    public FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(String languageUI) {
        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        filter.setDescriptionFilter(FluxFirmaUserController.getFluxFilterByUserName(getOwner()));
        return filter;
    }
}
