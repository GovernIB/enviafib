package es.caib.enviafib.back.controller.user;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.AbstractPlantillaDeFluxDeFirmesController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.entity.Usuari;

/**
 * 
 * GESTIO DE FLUXES. Es fa servir Usuari per guardar Flux: * Nif => fluxID * Nom
 * => Nom del Flux * Llinatge1 => Descripció del flux * Email => Data Creació
 * 
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/user/plantillesfluxfirmes")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class PlantillesDeFluxDeFirmesUserController extends AbstractPlantillaDeFluxDeFirmesController {

    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.plural";
    }

    @Override
    public String getTileForm() {
        return "plantillesfluxfirmesFormUser";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListUser";
    }

    public String getOwner() {
        return LoginInfo.getInstance().getUsername();
    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

        if (usuariFilterForm.isNou()) {
            // BOTO PER CREAR
            usuariFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle", "nouflux",
                    getContextWeb() + "/crearflux", "btn-success"));
        }

        return usuariFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {

        super.postList(request, mav, filterForm, list);

        for (Usuari usuari : list) {
            // BOTO PER EDITAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(), new AdditionalButton("fas fa-edit",
                    "genapp.edit", getContextWeb() + "/" + usuari.getNif() + "/editar", "btn-warning"));
        }
    }

    @RequestMapping(value = "/{fluxID}/editar")
    public ModelAndView editarFlux(@PathVariable("fluxID") java.lang.String fluxID, HttpServletRequest request,
            HttpServletResponse response) {

        ApiFlowTemplateSimple api = null;
        try {
            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            api = FluxFirmaUserController.getApiFlowTemplateSimple();

            // Comprova que és de la nostra propietat
            FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
            flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, fluxID);

            FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

            String description = flux.getDescription();

            if (description.indexOf("{owner=" + getOwner() + "}") != -1) {
                log.info("El flux es de la nostra propietat");
            } else {
                log.error("El flux NO es de la nostra propietat");
            }

            final String callBackUrl = AbstractFirmaUserController.getAbsoluteControllerBase(request, getContextWeb())
                    + "/finalEdicio";

            FlowTemplateSimpleEditFlowTemplateRequest transactionRequest;
            transactionRequest = new FlowTemplateSimpleEditFlowTemplateRequest(languageUI, fluxID, callBackUrl);

            // Enviam informació bàsica
            String redirectUrl = api.getUrlToEditFlowTemplate(transactionRequest);

            log.info("Edit RedirectUrl Flow = " + redirectUrl);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject("urlflow", redirectUrl);
            return mav;

        } catch (AbstractApisIBException aaie) {
            String msg = I18NUtils.tradueix("error.flux.edicio", aaie.getMessage());
            log.error(msg, aaie);
            return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        }
    }

    @RequestMapping(value = "/finalEdicio", method = RequestMethod.GET)
    public ModelAndView edicioFluxFinal(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("finaliframe");

        mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/list");
        return mav;
    }

    @RequestMapping(value = "/crearflux", method = RequestMethod.GET)
    public ModelAndView crearFlux(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("transactionID");

        ApiFlowTemplateSimple api = null;
        String transactionID = null;
        try {

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            api = FluxFirmaUserController.getApiFlowTemplateSimple();

            // Crear Flux
            String name = "Plantilla de Flux de Firma  - " + System.currentTimeMillis();
            String descr = FluxFirmaUserController.generateDescription(getOwner(), true);

            final boolean saveOnServer = true;
            final boolean visibleDescription = false;

            FlowTemplateSimpleGetTransactionIdRequest transactionRequest;
            transactionRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI, saveOnServer, name, descr,
                    visibleDescription);

            // Enviam informació bàsica
            transactionID = api.getTransactionID(transactionRequest);

            log.info("Language      = |" + languageUI + "|");
            log.info("SaveOnServer  = |" + saveOnServer + "|");
            log.info("TransactionID = |" + transactionID + "|");

            final String callBackUrl = AbstractFirmaUserController.getAbsoluteControllerBase(request, getContextWeb())
                    + "/callbackflux/" + transactionID;

            // Per ara només suportam FULLVIEW
            FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID, callBackUrl);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            log.info("RedirectUrl Flow = " + redirectUrl);

            request.getSession().setAttribute("transactionID", transactionID);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject("urlflow", redirectUrl);
            return mav;

        } catch (AbstractApisIBException aaie) {
            String msg = I18NUtils.tradueix("error.flux.creacio", aaie.getMessage());
            log.error(msg, aaie);

            final String intermediateID = null;
            FluxFirmaUserController.cleanFlux(api, transactionID, intermediateID, log);

            return new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        }
    }

    @RequestMapping(value = "/callbackflux/{transactionID}")
    public ModelAndView finalProcesDeFlux(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID) {

        log.info("CallBackFlux transactionID[" + transactionID + "] dins mapping ...");

        // public String testCrearFluxDeFirma(ApiFlowTemplateSimple api, String
        // languageUI, boolean saveOnServer) throws I18NException {
        ApiFlowTemplateSimple api = null;
        String error = null;
        try {

            api = FluxFirmaUserController.getApiFlowTemplateSimple();

            FlowTemplateSimpleGetFlowResultResponse fullResult = api.getFlowTemplateResult(transactionID);

            FlowTemplateSimpleStatus transactionStatus = fullResult.getStatus();

            int status = transactionStatus.getStatus();

            switch (status) {

                case FlowTemplateSimpleStatus.STATUS_INITIALIZING: // = 0;
                    error = I18NUtils.tradueix("procesdeflux.status.initializing");
                break;

                case FlowTemplateSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    error = I18NUtils.tradueix("procesdeflux.status.inprogress");
                break;

                case FlowTemplateSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                {
                    error = I18NUtils.tradueix("procesdeflux.status.finalerror", transactionStatus.getErrorMessage());
                    String desc = transactionStatus.getErrorStackTrace();
                    if (desc != null) {
                        log.error(error + "\n" + desc);
                    }

                }
                break;

                case FlowTemplateSimpleStatus.STATUS_CANCELLED: // = -2;
                    error = I18NUtils.tradueix("procesdeflux.status.canceled");
                break;

                case FlowTemplateSimpleStatus.STATUS_FINAL_OK: // = 2;
                {
                    FlowTemplateSimpleFlowTemplate flux = fullResult.getFlowInfo();

                    // XYZ ZZZ TRA Debug a partir de setembre
                    log.info(" ======= FLUX ========= ");
                    log.info(FlowTemplateSimpleFlowTemplate.toString(flux));
                    log.info(" ---------------------- ");

                    log.info(" INTERMEDIATE =====>  |" + flux.getIntermediateServerFlowTemplateId() + "|");

                    // request.getSession().setAttribute("flux", flux);
                    // fluxInfoByTransactonID.put(transactionID, flux);

                    ModelAndView mav = new ModelAndView("finaliframe");

                    mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/list");

                    String msg = I18NUtils.tradueix("plantillaflux.creada.ok");
                    HtmlUtils.saveMessageSuccess(request, msg);

                    return mav;

                } // Final Case Firma OK

                default: {
                    error = I18NUtils.tradueix("procesdeflux.status.default", String.valueOf(status));
                }

            } // Final Switch Firma

        } catch (AbstractApisIBException aaie) {

            error = I18NUtils.tradueix("error.error.flux.creacioflux", aaie.getMessage());
            log.error(error, aaie);
            FluxFirmaUserController.cleanFlux(api, transactionID, null, log);
        }

        log.error(error);

        HtmlUtils.saveMessageError(request, error);

        ModelAndView mav = new ModelAndView("finaliframe");

        mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/list");
        return mav;

    }

    @Override
    public FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(String languageUI) {
        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        filter.setDescriptionFilter(FluxFirmaUserController.getFluxFilterByUserName(getOwner()));
        return filter;
    }

}
