package es.caib.enviafib.back.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
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

import es.caib.enviafib.back.controller.webdb.UsuariController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.FitxerFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

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
public class PlantillesDeFluxDeFirmesUserController extends UsuariController {
    
    public static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.plural";
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public String getTileForm() {
        return "plantillesfluxfirmesFormUser";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "plantillesfluxfirmes_FilterForm";

    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

        if (usuariFilterForm.isNou()) {

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(UsuariFields.ALL_USUARI_FIELDS));

            hiddens.remove(UsuariFields.NOM);

            usuariFilterForm.addLabel(UsuariFields.LLINATGE1, FitxerFields.DESCRIPCIO.fullName);
            hiddens.remove(UsuariFields.LLINATGE1);

            hiddens.remove(UsuariFields.EMAIL);
            usuariFilterForm.addLabel(UsuariFields.EMAIL, PeticioFields.DATACREACIO.fullName);

            if (Configuracio.isDesenvolupament()) {
                usuariFilterForm.addLabel(UsuariFields.NIF, UsuariFields.USUARIID.fullName);
                hiddens.remove(UsuariFields.NIF);
            }

            usuariFilterForm.setHiddenFields(hiddens);

            usuariFilterForm.setItemsPerPage(-1);
            usuariFilterForm.setAllItemsPerPage(new int[] { -1 });
            usuariFilterForm.setAddButtonVisible(false);
            usuariFilterForm.setEditButtonVisible(false);
            usuariFilterForm.setDeleteButtonVisible(false);
            usuariFilterForm.setVisibleMultipleSelection(false);
            usuariFilterForm.setDeleteSelectedButtonVisible(false);

            usuariFilterForm.setVisibleExportList(false);

            // BOTO PER CREAR
            usuariFilterForm.addAdditionalButton(new AdditionalButton("fas fa-plus-circle", "nouflux",
                    getContextWeb() + "/crearflux", "btn-success"));

        }

        return usuariFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {

        filterForm.getAdditionalButtonsByPK().clear();

        for (Usuari usuari : list) {
            // BOTO PER EDITAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(),
                    new AdditionalButton("fas fa-edit", "genapp.edit", 
                             getContextWeb() + "/" + usuari.getNif() + "/editar",
                            "btn-warning"));

            // BOTO PER ESBORRAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(),
                    new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                            + request.getContextPath() + getContextWeb() + "/" + usuari.getNif() + "/esborrar','show')",
                            "btn-danger"));

        }
    }
    
    public String getOwner() {
        return LoginInfo.getInstance().getUsername();
    }

    @Override
    public List<Usuari> executeSelect(ITableManager<Usuari, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        log.info("OrderBy " + orderBy);
        if (orderBy != null) {
            for (OrderBy o : orderBy) {
                log.info("OrderBy[" + o.javaName + " => " + o.orderType);
            }
        }

        ApiFlowTemplateSimple api = FluxFirmaUserController.getApiFlowTemplateSimple();

        String username = getOwner();
        final String languageUI = "ca";

        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        filter.setDescriptionFilter(FluxFirmaUserController.getFluxFilterByUserName(username));

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

                Usuari usuari = new UsuariJPA();
                usuari.setUsuariID((long) flowTemplateId.hashCode());
                usuari.setNif(flowTemplateId);
                usuari.setNom(flowKeyValue.getValue());
                // XYZ ZZZ
                usuari.setLlinatge1(description); // + "\n<br/> flowTemplateId => " + flowTemplateId);
                usuari.setEmail(getCreationDate(description));

                
                usuaris.add(usuari);
            }

            return usuaris;

        } catch (AbstractApisIBException e) {
            String msg = "Error consultant API de Plantilles de Flux per username: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }
    
    private String getCreationDate(String description) {
        
        
       Pattern PATTERN = Pattern.compile("\\{creation=(\\d+)\\}");

        
        final Matcher matcher = PATTERN.matcher(description);
        
        if (matcher.find()) {
            
                if (matcher.group(1) != null) {
                    String datastr = matcher.group(1);
                    Date data = new Date(Long.valueOf(datastr));
                    return SDF.format(data);
                }
         }
        
        log.error("No s'ha pogut extreure atribut Creation de la descripcio");
        return null;
        
    }
    

    @RequestMapping(value = "/{fluxID}/esborrar")
    public String esborrarFlux(@PathVariable("fluxID")
    java.lang.String fluxID, HttpServletRequest request, HttpServletResponse response) {

        final String languageUI = "ca";

        // TODO XYZ ZZZ Comprovar que és de la nostra propietat
        try {

            ApiFlowTemplateSimple api = FluxFirmaUserController.getApiFlowTemplateSimple();

            FlowTemplateSimpleFlowTemplateRequest r = new FlowTemplateSimpleFlowTemplateRequest(languageUI, fluxID);
            if (api.deleteFlowTemplate(r)) {
                // XYZ ZZZ
                HtmlUtils.saveMessageSuccess(request, "Esborrada plantilla de Flux de Firmes Correctament");
            } else {
                // XYZ ZZZ
                HtmlUtils.saveMessageSuccess(request, "Error esborrant plantilla de Flux de Firmes amb ID " + fluxID
                        + ".Consulti amb l´administrador de l'aplicació.");
            }

        } catch (AbstractApisIBException e) {
            // XYZ ZZZ TRA
            String msg = "Error esborrarnt Plantilla de Flux amb ID " + fluxID + ": " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return getRedirectWhenCancel(request, 0L);
    }

    @RequestMapping(value = "/{fluxID}/editar")
    public ModelAndView editarFlux(@PathVariable("fluxID")
    java.lang.String fluxID, HttpServletRequest request, HttpServletResponse response) {

        // TODO XYZ ZZZ Comprovar que és de la nostra propietat

        ApiFlowTemplateSimple api = null;
        try {

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            api = FluxFirmaUserController.getApiFlowTemplateSimple();


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
            @PathVariable("transactionID")
            String transactionID) {

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

                    // XYZ Debug a partir de setembre
                    log.info(" ======= FLUX ========= ");
                    log.info(FlowTemplateSimpleFlowTemplate.toString(flux));
                    log.info(" ---------------------- ");

                    log.info(" INTERMEDIATE =====>  |" + flux.getIntermediateServerFlowTemplateId() + "|");

                    // request.getSession().setAttribute("flux", flux);
                    // fluxInfoByTransactonID.put(transactionID, flux);

                    ModelAndView mav = new ModelAndView("finaliframe");

                    mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/list");

                    // XYZ ZZZ TRA
                    String msg = "XYZ ZZZ Plantilla de Flux de Firmes creada correctament";

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

}
