package es.caib.enviafib.back.controller.user;

import java.util.Base64;
import java.util.Date;
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
import es.caib.enviafib.commons.utils.Configuracio;
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
            
            usuariFilterForm.setAttachedAdditionalJspCode(true);
        }

        return usuariFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {

        super.postList(request, mav, filterForm, list);

        for (Usuari usuari : list) {
            // BOTO PER EDITAR
            final String fluxID = usuari.getNif();
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(), new AdditionalButton("fas fa-edit",
                    "genapp.edit", "javascript:editarFlux('" + request.getContextPath() + getContextWeb() + "/editarflux/" + fluxID +"')", "btn-warning"));
        }
    }

    @RequestMapping(value = "/editarflux/{fluxID}/{windowUrl}")
    public ModelAndView editarFlux(@PathVariable("fluxID") java.lang.String fluxID, 
            @PathVariable("windowUrl") String windowUrl, HttpServletRequest request,
            HttpServletResponse response) {

        ApiFlowTemplateSimple api = null;
        try {
            final String languageUI = LocaleContextHolder.getLocale().getLanguage();
            
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

            api = FirmaFluxUserController.getApiFlowTemplateSimple();

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

            final String callBackUrl = Configuracio.getUrlBase(decodedUrl, request.getContextPath()) + getContextWeb() + "/finalEdicio";

            FlowTemplateSimpleEditFlowTemplateRequest transactionRequest;
            transactionRequest = new FlowTemplateSimpleEditFlowTemplateRequest(languageUI, fluxID, callBackUrl);

            // Enviam informació bàsica
            String redirectUrl = api.getUrlToEditFlowTemplate(transactionRequest);

            log.info("Edit RedirectUrl Flow = " + redirectUrl);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject("urlflow", redirectUrl);
            return mav;

        } catch (Exception aaie) {
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

            api = FirmaFluxUserController.getApiFlowTemplateSimple();

            // Crear Flux
            final String name = "ENVIAFIB_Plantilla_Flux_Firma_" + FirmaFluxUserController.SDF.format(new Date()) + "_" +getOwner();
            String descr = FirmaFluxUserController.generateDescription(getOwner(), true);

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

            final String callBackUrl = request.getSession().getAttribute(MenuUserController.URL_BASE_NAVEGADOR)
                     + getContextWeb() + "/callbackflux/" + transactionID;

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
            FirmaFluxUserController.cleanFlux(api, transactionID, intermediateID, log);

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

            api = FirmaFluxUserController.getApiFlowTemplateSimple();

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
            FirmaFluxUserController.cleanFlux(api, transactionID, null, log);
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
        filter.setDescriptionFilter(FirmaFluxUserController.getFluxFilterByUserName(getOwner()));
        return filter;
    }

    @Override
    public Boolean onlyAcceptTemplates() {
        return true;
    }

}
