package es.caib.enviafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractPlantillaDeFluxDeFirmesController;
import es.caib.enviafib.back.controller.user.FirmaFluxUserController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.entity.Usuari;

@Controller
@RequestMapping(value = "/admin/netejarplantilles")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class NetejarPlantillesDeFluxAdminController extends AbstractPlantillaDeFluxDeFirmesController {

    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes.obsolet";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.obsolet.plural";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListAdmin";
    }

    @Override
    public FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(String languageUI) {

        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        // Cercam per usuari aplicació i despres ja cercarem per {temporal=true}
        filter.setDescriptionFilter(FirmaFluxUserController.getFluxFilterByUserName(null));

        return filter;
    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

        if (usuariFilterForm.isNou()) {
            usuariFilterForm.setTitleCode("llistatplantillesflux.netejar");
            usuariFilterForm.setAttachedAdditionalJspCode(true);

			usuariFilterForm.addAdditionalButton(new AdditionalButton("fas fa-cogs", "netejar",
					"javascript: openModal('" + request.getContextPath() + getContextWeb() + "/esborrarTotes','show')",
					AdditionalButtonStyle.WARNING));       

        }
        return usuariFilterForm;
    }

    @Override
    public List<Usuari> executeSelect(ITableManager<Usuari, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        List<Usuari> plantilles = super.executeSelect(ejb, where, orderBy, itemsPerPage, inici);
        List<Usuari> caducades = new ArrayList<Usuari>();

        for (Usuari plantilla : plantilles) {

            String description = plantilla.getLlinatge1();

            {

                Long creationDate = getCreationDateLong(description);
                Long currentTime = System.currentTimeMillis();

                long limit = 3 * 1000 * 3600;

                if (currentTime - creationDate > limit) {
                    caducades.add(plantilla);
                }
            }
        }

        return caducades;
    }

    @Override
    public Boolean onlyAcceptTemplates() {
        // Només Temporals
        return false;
    }
    
    
    
    @RequestMapping(value = "/esborrarTotes")
    public String esborrarFlux(HttpServletRequest request, HttpServletResponse response) {
        try {
        	Long currentTime = System.currentTimeMillis();

            ApiFlowTemplateSimple api = FirmaFluxUserController.getApiFlowTemplateSimple();
            final String languageUI = "ca";

            FlowTemplateSimpleFilterGetAllByFilter filter = getFilterPlantillaFluxFirma(languageUI);

            FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplatesByFilter(filter);

            List<FlowTemplateSimpleKeyValue> plantilles = list.getList();
            
            for (FlowTemplateSimpleKeyValue flowKeyValue : plantilles) {
                String flowTemplateId = flowKeyValue.getKey();

                FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

                FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);
                String description = flux.getDescription();
                
                if (description.indexOf("{temporal=true}") == -1) {
                	log.info("El flux " + flowTemplateId + " no es temporal");
                	continue;
                }
                
                Long creationDate = getCreationDateLong(description);
                long limit = 3 * 1000 * 3600;

                if (currentTime - creationDate < limit) {
					log.info("El flux " + flowTemplateId + " no ha caducat");
                	continue;
                }
                
                if (api.deleteFlowTemplate(flowTemplateRequest)) {
                	// Esborrat correct
                	log.info("Flux " + flowTemplateId + " esborrat correctament");
                } else {
                	// Error esborrant
                	log.error("Error esborrant el flux " + flowTemplateId);
                }
            }

        } catch (AbstractApisIBException e) {
			log.error("Error esborrant les plantilles de flux de firmes: " + e.getMessage(), e);
        }
        return getRedirectWhenCancel(request, 0L);
    }
    
}
