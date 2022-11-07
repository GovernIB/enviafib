package es.caib.enviafib.back.controller.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaFluxUserController.CONTEXT_WEB)

public class FirmaFluxUserController extends AbstractFirmaUserController {

    public static SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd.HH.mm");

    public static final String CONTEXT_WEB = "/user/flux";

    public static final String TITOL_FLUX_FIRMA = "titolFluxFirma";

    public static final String FLUX_SESSION_KEY = "__FLUX_SESSION_KEY__";

    private ThreadLocal<Boolean> threadLocalTileForm = new ThreadLocal<>();

    private static final Map<String, FlowTemplateSimpleFlowTemplate> fluxInfoByTransactonID = new HashMap<String, FlowTemplateSimpleFlowTemplate>();

    @Override
    public int getTipusPeticio() {
        return TIPUS_PETICIO_FLUX;
    }

    @Override
    public String getEntityNameCode() {
        return "flux.flux";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "flux.flux.plural";
    }

    @Override
    public String getTileForm() {
        if (threadLocalTileForm.get() == null) {
            return "flowview";
        } else {
            return super.getTileForm();
        }
    }

    @Override
    @RequestMapping(value = "/view/{peticioID}", method = RequestMethod.GET)
    public ModelAndView veurePeticioGet(@PathVariable("peticioID") java.lang.Long peticioID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        threadLocalTileForm.set(true);
        return super.veurePeticioGet(peticioID, request, response);
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        long solicitantID = peticioForm.getPeticio().getSolicitantID();
        String solicitantNif = usuariEjb.executeQueryOne(UsuariFields.NIF, UsuariFields.USUARIID.equal(solicitantID));
        peticioForm.getPeticio().setDestinatariNif(solicitantNif);

        String titol_flux = (String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);
        mav.addObject(TITOL_FLUX_FIRMA, titol_flux);

        peticioForm.setTitleCode("= ");

        peticioForm.addHiddenField(DESTINATARINIF);

        try {
            String intermediateID = request.getParameter("intermediateID");
            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            ApiFlowTemplateSimple api = getApiFlowTemplateSimple();

            FlowTemplateSimpleFlowTemplateRequest flowRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI,
                    intermediateID);
            FlowTemplateSimpleFlowTemplate flowInfo = api.getFlowInfoByFlowTemplateID(flowRequest);

            String fluxname = flowInfo.getName();
            peticioForm.getPeticio().setNom(fluxname);

        } catch (AbstractApisIBException aaie) {
            String error = I18NUtils.tradueix("error.flux.obtenintnom", aaie.getMessage());
            log.error(error, aaie);
            HtmlUtils.saveMessageError(request, error);

            return peticioForm;
        }
        if (!__isView) {
            //    peticioForm.setTitleCode("emptystring");
        }

        mav.addObject("wizardstep", 3);

        return peticioForm;

    }

    @Override
    public void postValidate(HttpServletRequest request, PeticioForm peticioForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, peticioForm, result);
        request.setAttribute("wizardstep", 3);
    }

    @RequestMapping(value = "/crearflux", method = RequestMethod.GET)
    public ModelAndView crearFlux(HttpServletRequest request, HttpServletResponse response) {

        request.getSession().removeAttribute("transactionID");

        ApiFlowTemplateSimple api = null;
        String transactionID = null;
        try {

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            api = getApiFlowTemplateSimple();

            // Crear Flux
            String name = "Flux de Firma  - " + System.currentTimeMillis();

            final String username = LoginInfo.getInstance().getUsername();

            String descr = generateDescription(username, false);

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

            String titol_flux = (String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject(TITOL_FLUX_FIRMA, titol_flux);
            mav.addObject("fluxname", name);
            mav.addObject("urlflow", redirectUrl);
            mav.addObject("wizardstep", 1);
            return mav;

        } catch (AbstractApisIBException aaie) {
            String msg = I18NUtils.tradueix("error.flux.creacio", aaie.getMessage());
            log.error(msg, aaie);

            final String intermediateID = null;
            cleanFlux(api, transactionID, intermediateID, log);

            return new ModelAndView(getRedirectToList());
        }
    }

    public static String generateDescription(final String username, final boolean isTemplate) {
        final long current = System.currentTimeMillis();
        final String currentStr = SDF.format(new Date(current));

        String descr = (isTemplate ? "{template=true}" : "{temporal=true}\n") + "{creation=" + current + "}\n"
                + "{creationStr=" + currentStr + "}\n" + getFluxFilterByUserName(username);
        return descr;
    }

    /**
     * 
     * @param username
     * @return
     */
    public static String getFluxFilterByUserName(String username) {
        final String usrapp = Configuracio.getPortaFIBApiFlowUsername();
        // Filtre de Flux de Firmes no filtra bé per descripció (https://github.com/GovernIB/portafib/issues/752)
        return "{usrapp=" + usrapp + "}" + (username == null ? "" : "{owner=" + username + "}");
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearPeticioGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {

        ModelAndView mav = super.crearPeticioGet(request, response);

        ApiFlowTemplateSimple api = getApiFlowTemplateSimple();
        String transactionID = request.getParameter("transactionID");

        String intermediateID = request.getParameter("intermediateID");

        log.info("**************new::transactionID= " + transactionID);
        log.info("**************new::intermediateID=" + intermediateID);

        FlowTemplateSimpleFlowTemplate flux = fluxInfoByTransactonID.get(transactionID);
        log.info("**************new::flux= " + flux);
        request.getSession().setAttribute(FLUX_SESSION_KEY, flux);
        fluxInfoByTransactonID.remove(transactionID);

        if (api != null && transactionID != null) {
            cleanFlux(api, transactionID, intermediateID, log);
        }

        return mav;
    }

    public static void cleanFlux(ApiFlowTemplateSimple api, String transactionID, String intermediateID, Logger log) {
        try {
            api.closeTransaction(transactionID);

            if (intermediateID != null) {

                FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(
                        LocaleContextHolder.getLocale().getLanguage(), intermediateID);

                boolean esborrat = api.deleteFlowTemplate(flowTemplateRequest);
                log.error("Resultat esborrat de flux de firma: " + esborrat);

            }

        } catch (Throwable th) {
            String error = I18NUtils.tradueix("error.flux.esborrar", th.getMessage());
            log.error(error, th);
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

            api = getApiFlowTemplateSimple();

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

                    fluxInfoByTransactonID.put(transactionID, flux);

                    ModelAndView mav = new ModelAndView("finaliframe");

                    mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/mostrarflux/"
                            + transactionID + "/" + flux.getIntermediateServerFlowTemplateId());

                    return mav;

                } // Final Case Firma OK

                default: {
                    error = I18NUtils.tradueix("procesdeflux.status.default", String.valueOf(status));
                }

            } // Final Switch Firma

        } catch (AbstractApisIBException aaie) {

            error = I18NUtils.tradueix("error.error.flux.creacioflux", aaie.getMessage());
            log.error(error, aaie);
            cleanFlux(api, transactionID, null, log);
        }

        log.error(error);

        HtmlUtils.saveMessageError(request, error);

        ModelAndView mav = new ModelAndView("finaliframe");

        mav.addObject("URL_FINAL", request.getContextPath() + getRedirectToList().replace("redirect:", ""));
        return mav;

    }

    @RequestMapping(value = "/mostrarflux/{transactionID}/{intermediateID}")
    public ModelAndView mostrarflux(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID,
            @PathVariable("intermediateID") String intermediateID) {

        ApiFlowTemplateSimple api = null;

        try {

            api = getApiFlowTemplateSimple();

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest;
            viewFlowRequest = new FlowTemplateSimpleViewFlowTemplateRequest(languageUI, intermediateID);
            String url = api.getUrlToViewFlowTemplate(viewFlowRequest);

            log.info("View Flow Template Url = " + url);

            String titol_flux = (String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject(TITOL_FLUX_FIRMA, titol_flux);

            mav.addObject("title", I18NUtils.tradueix("vista.flux"));
            mav.addObject("urlflow", url);

            mav.addObject("wizardstep", 2);
            mav.addObject("continueUrl",
                    getContextWeb() + "/new?transactionID=" + transactionID + "&intermediateID=" + intermediateID);

            mav.addObject("cancelUrl", getRedirectToList().replace("redirect:", ""));
            return mav;

        } catch (AbstractApisIBException aaie) {

            String error = I18NUtils.tradueix("error.flux.creacio", aaie.getMessage());
            log.error(error, aaie);
            HtmlUtils.saveMessageError(request, error);

            return new ModelAndView(getRedirectToList());
        }
    }

    /*
     * protected static void editarFluxDeFirmes(ApiFlowTemplateSimple api, final
     * String languageUI, String lastKey) throws AbstractApisIBException,
     * IOException, URISyntaxException { FlowTemplateSimpleEditFlowTemplateRequest
     * viewFlowRequest; viewFlowRequest = new
     * FlowTemplateSimpleEditFlowTemplateRequest(languageUI, lastKey,
     * "http://google.es"); String url =
     * api.getUrlToEditFlowTemplate(viewFlowRequest);
     * 
     * log.info(); log.info("Edit Flow Template Url = " + url);
     * 
     * if (Desktop.isDesktopSupported()) { Desktop.getDesktop().browse(new
     * URI(url)); } else {
     * log.info("Per favor obri un Navegador i copia-li la URL anterior ..."); } }
     * 
     * protected static void descarregarFluxDeFirmesInfo(ApiFlowTemplateSimple api,
     * final String languageUI, String lastKey) throws AbstractApisIBException,
     * IOException, URISyntaxException {
     * 
     * FlowTemplateSimpleFlowTemplateRequest request; request = new
     * FlowTemplateSimpleFlowTemplateRequest(languageUI, lastKey);
     * 
     * FlowTemplateSimpleFlowTemplate flow =
     * api.getFlowInfoByFlowTemplateID(request);
     * 
     * log.info(" Flow Template Info = " + flow.getName());
     * 
     * log.info(FlowTemplateSimpleFlowTemplate.toString(flow));
     * 
     * }
     * 
     * 
     * /* protected static void esborrarFluxDeFirmes(ApiFlowTemplateSimple api,
     * final String languageUI, String lastKey) throws AbstractApisIBException,
     * IOException, URISyntaxException {
     * 
     * FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
     * flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI,
     * lastKey);
     * 
     * boolean esborrat = api.deleteFlowTemplate(flowTemplateRequest);
     * 
     * log.info("Delete  Flow Template Info = " + esborrat);
     * 
     * }
     * 
     * 
     * 
     * /*
     * 
     * protected static String llistarFluxDeFirmesAmbFiltre(ApiFlowTemplateSimple
     * api, final String languageUI, final String name, final String description)
     * throws AbstractApisIBException {
     * 
     * 
     * FlowTemplateSimpleFilterGetAllByFilter filtre; filtre = new
     * FlowTemplateSimpleFilterGetAllByFilter(languageUI, name, description);
     * 
     * FlowTemplateSimpleFlowTemplateList listHolder =
     * api.getAllFlowTemplatesByFilter(filtre); String lastKey = null; if
     * (listHolder != null) {
     * 
     * List<FlowTemplateSimpleKeyValue> list = listHolder.getList();
     * 
     * log.info(); log.info(" ---- LLISTAT PLANTILLES DE FLUX AMB FILTRE-----");
     * 
     * for (FlowTemplateSimpleKeyValue flowTemplateSimpleKeyValue : list) {
     * log.info("   " + flowTemplateSimpleKeyValue.getKey() + "  " +
     * flowTemplateSimpleKeyValue.getValue());
     * 
     * lastKey = flowTemplateSimpleKeyValue.getKey();
     * 
     * }
     * 
     * } return lastKey; }
     * 
     * /* protected static String llistarFluxDeFirmes(ApiFlowTemplateSimple api,
     * final String languageUI) throws AbstractApisIBException {
     * FlowTemplateSimpleFlowTemplateList listHolder =
     * api.getAllFlowTemplates(languageUI); String lastKey = null; if (listHolder !=
     * null) {
     * 
     * List<FlowTemplateSimpleKeyValue> list = listHolder.getList();
     * 
     * log.info(); log.info(" ---- LLISTAT PLANTILLES DE FLUX -----");
     * 
     * for (FlowTemplateSimpleKeyValue flowTemplateSimpleKeyValue : list) {
     * log.info("   " + flowTemplateSimpleKeyValue.getKey() + "  " +
     * flowTemplateSimpleKeyValue.getValue());
     * 
     * lastKey = flowTemplateSimpleKeyValue.getKey();
     * 
     * }
     * 
     * } return lastKey; }
     */

    /**
     * 
     * @param port
     * @throws Exception
     */
    /*
     * public static void readFromSocket(int port) throws Exception {
     * 
     * ServerSocket serverSocket = new ServerSocket(port);
     * System.err.println("Servidor escoltant al PORT: " + port); { Socket
     * clientSocket = serverSocket.accept(); System.err
     * .println("Nou Client Connectat desde " +
     * clientSocket.getRemoteSocketAddress());
     * 
     * BufferedReader in = new BufferedReader( new
     * InputStreamReader(clientSocket.getInputStream())); PrintWriter out = new
     * PrintWriter( new BufferedWriter(new
     * OutputStreamWriter(clientSocket.getOutputStream())), true);
     * 
     * String s; System.err.println(" =========================== "); while ((s =
     * in.readLine()) != null) { log.info(s); break; }
     * System.err.println(" =========================== ");
     * 
     * out.println("HTTP/1.0 200 OK"); out.println("Content-Type: text/html");
     * out.println("\r\n"); out.println(
     * "<html><body>OK (Revisi consola per saber l'estat final del proc&eacute;s de Firma)</body></html>"
     * );
     * 
     * System.err.println("Connexio amb el client finalitzada."); out.flush();
     * out.close(); in.close(); clientSocket.close(); }
     * 
     * serverSocket.close(); }
     * 
     * protected static String getLanguage(Properties testProperties) { return
     * testProperties.getProperty("language"); }
     * 
     * protected static boolean isSaveOnServer(Properties testProperties) { return
     * "true".equals(testProperties.getProperty("saveonserver")); }
     */

    /**
     * 
     * @return
     * @throws Exception
     */
    public static ApiFlowTemplateSimple getApiFlowTemplateSimple() {

        String url = Configuracio.getPortaFIBApiFlowUrl();
        String username = Configuracio.getPortaFIBApiFlowUsername();
        String password = Configuracio.getPortaFIBApiFlowPassword();
        //log.info(" Connectant amb " + url + " emprant l'usuari " + username);

        return new ApiFlowTemplateSimpleJersey(url, username, password);

    }

}
