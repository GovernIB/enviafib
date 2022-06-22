package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleViewFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FluxFirmaUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FluxFirmaUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/flux";

    @Override
    public int getTipusPeticio() {

        return TIPUS_PETICIO_FLUX;
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);
        // XYZ ZZZ Posam el de la persona que ho ha solicitat per a que no falli
        peticioForm.getPeticio().setDestinatarinif(LoginInfo.getInstance().getUsuari().getNif());
        peticioForm.addHiddenField(DESTINATARINIF);

        return peticioForm;

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
            final boolean saveOnServer = false;

            String name = "Flux de Firma  - " + System.currentTimeMillis();
            String descr = "SaveOnServer  = |" + saveOnServer + "|";
            final boolean visibleDescription = false;

            FlowTemplateSimpleGetTransactionIdRequest transactionRequest;
            transactionRequest = new FlowTemplateSimpleGetTransactionIdRequest(languageUI,
                    saveOnServer, name, descr, visibleDescription);

            // Enviam informació bàsica
            transactionID = api.getTransactionID(transactionRequest);

            log.info("Language      = |" + languageUI + "|");
            log.info("SaveOnServer  = |" + saveOnServer + "|");
            log.info("TransactionID = |" + transactionID + "|");

            final String callBackUrl = getAbsoluteControllerBase(request, getContextWeb())
                    + "/callbackflux/" + transactionID;

            // Per ara només suportam FULLVIEW
            FlowTemplateSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FlowTemplateSimpleStartTransactionRequest(transactionID,
                    callBackUrl);

            String redirectUrl = api.startTransaction(startTransactionInfo);

            log.info("RedirectUrl Flow = " + redirectUrl);

            request.getSession().setAttribute("transactionID", transactionID);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject("urlflow", redirectUrl);
            return mav;

        } catch (AbstractApisIBException aaie) {

            // XYZ ZZZ TRA
            String msg = "Error desconegut durant la creació del Flux de Firma: "
                    + aaie.getMessage();
            log.error(msg, aaie);

            if (api != null && transactionID != null) {
                try {
                    api.closeTransaction(transactionID);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

            return new ModelAndView(getRedirectToList());

        }

    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearPeticioGet(HttpServletRequest request, HttpServletResponse response)
            throws I18NException {

        ModelAndView mav = super.crearPeticioGet(request, response);

        ApiFlowTemplateSimple api = getApiFlowTemplateSimple();
        String transactionID = request.getParameter("transactionID");

        if (api != null && transactionID != null) {
            try {
                api.closeTransaction(transactionID);
            } catch (Throwable th) {
                /// XYZ ZZZZ
                th.printStackTrace();
            }

        }

        return mav;
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

            api = getApiFlowTemplateSimple();

            FlowTemplateSimpleGetFlowResultResponse fullResult = api
                    .getFlowTemplateResult(transactionID);

            FlowTemplateSimpleStatus transactionStatus = fullResult.getStatus();

            int status = transactionStatus.getStatus();

            switch (status) {

                case FlowTemplateSimpleStatus.STATUS_INITIALIZING: // = 0;
                    // XYZ ZZZ TRA
                    error = "S'ha rebut un estat inconsistent del procés de construcció del flux."
                            + " (Inialitzant). Consulti amb el seu administrador.";
                break;

                case FlowTemplateSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                    // XYZ ZZZ TRA
                    error = "S'ha rebut un estat inconsistent de construcció del flux "
                            + " (En Progrés). Consulti amb el seu administrador.";

                break;

                case FlowTemplateSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                {
                    // XYZ ZZZ TRA
                    error = "Error durant la construcció del flux: "
                            + transactionStatus.getErrorMessage();

                    String desc = transactionStatus.getErrorStackTrace();
                    if (desc != null) {
                        log.error(error + "\n" + desc);
                    }

                }
                break;

                case FlowTemplateSimpleStatus.STATUS_CANCELLED: // = -2;

                    // XYZ ZZZ TRA
                    error = "L'usuari ha cancelat la construcció del flux";
                break;

                case FlowTemplateSimpleStatus.STATUS_FINAL_OK: // = 2;
                {
                    FlowTemplateSimpleFlowTemplate flux = fullResult.getFlowInfo();

                    // XYZ ZZZ DEBUG
                    log.info(FlowTemplateSimpleFlowTemplate.toString(flux));

                    request.getSession().setAttribute("flux", flux);

                    ModelAndView mav = new ModelAndView("finaliframe");

                    mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb()
                            + "/mostrarflux/" + transactionID);
                    return mav;

                } // Final Case Firma OK

                default: {
                    // XYZ ZZZ TRA
                    error = "Codi d'estat de finalització desconegut (" + status + ")";
                }

            } // Final Switch Firma

        } catch (AbstractApisIBException aaie) {

            // XYZ ZZZ TRA
            error = "Error desconegut durant la creació del Flux de Firma: " + aaie.getMessage();
            log.error(error, aaie);
            if (api != null && transactionID != null) {
                try {
                    api.closeTransaction(transactionID);
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }

        }

        log.error(error);

        HtmlUtils.saveMessageError(request, error);
        
        
        ModelAndView mav = new ModelAndView("finaliframe");

        mav.addObject("URL_FINAL", request.getContextPath() + getRedirectToList().replace("redirect:", ""));
        return mav;

    }

    @RequestMapping(value = "/mostrarflux/{transactionID}")
    public ModelAndView mostrarflux(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID")
            String transactionID) {

        ApiFlowTemplateSimple api = null;

        try {

            api = getApiFlowTemplateSimple();

            final String languageUI = LocaleContextHolder.getLocale().getLanguage();

            FlowTemplateSimpleViewFlowTemplateRequest viewFlowRequest;
            viewFlowRequest = new FlowTemplateSimpleViewFlowTemplateRequest(languageUI,
                    transactionID);
            String url = api.getUrlToViewFlowTemplate(viewFlowRequest);

            log.info("View Flow Template Url = " + url);

            ModelAndView mav = new ModelAndView("flowview");
            mav.addObject("title", "Vista de Flux XYZ");
            mav.addObject("urlflow", url);
            mav.addObject("continueUrl", getContextWeb() + "/new?transactionId=" + transactionID);
            mav.addObject("cancelUrl", getRedirectToList().replace("redirect:", ""));
            return mav;

        } catch (AbstractApisIBException aaie) {

            // XYZ ZZZ TRA
            String error = "Error desconegut durant la creació del Flux de Firma: "
                    + aaie.getMessage();
            log.error(error, aaie);

            HtmlUtils.saveMessageError(request, error);

            return new ModelAndView(getRedirectToList());

        }

    }

    @Override
    public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
            throws I18NException, I18NValidationException {
        PeticioJPA p = super.create(request, peticio);

        try {
            // XYZ ZZZ Falta provar
            peticioLogicaEjb.arrancarPeticioFlux(peticio.getPeticioID(),
                    LocaleContextHolder.getLocale().getLanguage(),
                    (FlowTemplateSimpleFlowTemplate) request.getSession().getAttribute("flux"));
        } catch (I18NException e) {
            p.setEstat(ESTAT_PETICIO_REBUTJADA);
            // XYZ ZZZZZ TRA
            p.setErrorMsg("Error arrancant petició a partir de flux: " + I18NUtils.getMessage(e));
            peticioLogicaEjb.update(p);
        }

        return p;

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
    protected ApiFlowTemplateSimple getApiFlowTemplateSimple() {

        String url = Configuracio.getPortaFIBApiFlowUrl();
        String username = Configuracio.getPortaFIBApiFlowUsername();
        String password = Configuracio.getPortaFIBApiFlowPassword();
        log.info(" Connectant amb " + url + " emprant l'usuari " + username);

        return new ApiFlowTemplateSimpleJersey(url, username, password);

    }

}
