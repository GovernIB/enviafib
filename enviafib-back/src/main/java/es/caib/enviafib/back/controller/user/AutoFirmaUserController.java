package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AutoFirmaUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class AutoFirmaUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/autofirma";

    // Sempre posarem el mateix
    public static final String SIGNID = "SignID_1";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_AUTOFIRMA;
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);
        peticioForm.getPeticio().setDestinatariNif(LoginInfo.getInstance().getUsuari().getNif());

        peticioForm.addHiddenField(DESTINATARINIF);

        return peticioForm;

    }

    @Override
    public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
            throws I18NException, I18NValidationException {
        PeticioJPA p = super.create(request, peticio);

        final String absoluteControllerBase = getAbsoluteControllerBase(request, getContextWeb());

        String[] info = autofirma(peticio, LoginInfo.getInstance().getUsuari(),
                LocaleContextHolder.getLocale().getLanguage(), absoluteControllerBase);

        String transactionID = info[0];
        String redirectUrl = info[1];

        log.info("Afegint transactionID[" + transactionID + "] => " + peticio.getPeticioID()
                + "    dins mapping");

        // peticioIdByTransactionId.put(transactionID, peticio.getPeticioID());
        p.setPeticioPortafirmes(transactionID);
        this.peticioLogicaEjb.update(p);

        request.getSession().setAttribute("redirectUrl", redirectUrl);

        return p;

    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {
        return "redirect:" + getContextWeb() + "/viewiniframe";
    }

    @RequestMapping(value = "/viewiniframe", method = RequestMethod.GET)
    public ModelAndView viewInIframe(HttpServletRequest request) throws Exception {

        String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
        if (log.isDebugEnabled()) {
            log.debug("ENTRA A /viewiniframe => redirectUrl: " + redirectUrl);
        }

        ModelAndView mav = new ModelAndView("firmasimpleweb_iframe");
        mav.addObject("urlToIFrameCode", redirectUrl);
        return mav;

    }

    @RequestMapping(value = "/finalWeb/{transactionID}")
    public ModelAndView finalProcesDeFirmaWeb(HttpServletRequest request,
            HttpServletResponse response, @PathVariable("transactionID")
            String transactionID) throws Exception {

        log.info("Final Web  Consultant transactionID[" + transactionID + "] dins Peticions ...");

        Long peticioID = peticioLogicaEjb.executeQueryOne(PeticioFields.PETICIOID,
                PeticioFields.PETICIOPORTAFIRMES.equal(transactionID));
        // peticioIdByTransactionId.get(transactionID);

        log.info("Consulta transactionID[" + transactionID + "] => " + peticioID);

        // XYZ ZZZ
        // TODO CHeck de peticioID

        String errorMsg;
        String errorException;

        ApiFirmaWebSimple api = null;
        try {
            api = getApiFirmaWebSimple();

            FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
            fullTransactionStatus = api.getTransactionStatus(transactionID);

            FirmaSimpleStatus transactionStatus = fullTransactionStatus.getTransactionStatus();

            int status = transactionStatus.getStatus();

            switch (status) {

                case FirmaSimpleStatus.STATUS_INITIALIZING: // = 0;

                {
                    errorException = "";
                    // XYZ ZZZ
                    errorMsg = "S'ha rebut un estat inconsistent del proces de firma"
                            + " (inicialitzant). Pot ser s'hagi produït un error en el Plugin de Firma."
                            + " Consulti amb el seu administrador.";
                }
                break;

                case FirmaSimpleStatus.STATUS_IN_PROGRESS: // = 1;
                {
                    errorException = "";
                    // XYZ ZZZ
                    errorMsg = "S'ha rebut un estat inconsistent del proces de firma"
                            + " (en progrés). Pot ser s'hagi produït un error en el Plugin de Firma."
                            + " Consulti amb el seu administrador.";
                }
                break;

                case FirmaSimpleStatus.STATUS_FINAL_ERROR: // = -1;
                {
                    // XYZ ZZZ
                    String msg = "Error durant la realització de les firmes de la transacció "
                            + transactionID + ": " + transactionStatus.getErrorMessage();
                    String desc = transactionStatus.getErrorStackTrace();

                    errorException = desc;
                    // XYZ ZZZ
                    errorMsg = msg;

                }
                break;

                case FirmaSimpleStatus.STATUS_CANCELLED: // = -2;
                {
                    errorException = null;
                    // XYZ ZZZ
                    errorMsg = "Durant el procés de firmes,"
                            + " l'usuari ha cancelat la transacció amb ID " + transactionID + ".";

                }
                break;

                case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
                {

                    List<FirmaSimpleSignatureStatus> results = fullTransactionStatus
                            .getSignaturesStatusList();

                    final boolean isDebug = true; // log.isDebugEnabled();
                    if (isDebug) {
                        log.debug(" ===== WEB RESULTATS [" + results.size() + "] =========");
                    }

                    FirmaSimpleSignatureResult fssr = null;

                    for (FirmaSimpleSignatureStatus signatureStatus : results) {

                        String signID = signatureStatus.getSignID();

                        if (isDebug) {
                            log.debug(" ------ WEB SIGNID ]" + signID + "[");
                        }

                        if (!SIGNID.equals(signID)) {
                            log.warn(
                                    "S'ha rebut de PortaFIB una petició de firma amb un SIGNID que no s'ha enviat ("
                                            + signID + ")");
                            continue;
                        }

                        fssr = api.getSignatureResult(
                                new FirmaSimpleGetSignatureResultRequest(transactionID, signID));

                    } // Final for de fitxers firmats

                    if (fssr != null && fssr.getSignedFileInfo() != null) {
                        peticioLogicaEjb.guardarResultatAutofirma(peticioID, fssr);

                        // XYZ ZZZ
                        HtmlUtils.saveMessageSuccess(request, "Realitzada firma correctament");

                        return new ModelAndView(
                                new RedirectView(getContextWeb() + "/view/" + peticioID, true));
                    } else {
                        errorException = null;
                        // XYZ ZZZ TRA
                        errorMsg = "No s'ha retornat cap firma amb SIGNID=" + SIGNID;
                    }

                }
                break;

                default:
                    errorException = null;
                    errorMsg = "Estat desconegut enviat per PortaIFB: " + status;

            } // Final Switch Global

        } catch (

        Exception e) {

            errorMsg = "Rebut un error intentant processar el resultat de la transacció de firma de fitxers: "
                    + e.getMessage();
            errorException = ExceptionUtils.getStackTrace(e);

        } finally {
            try {

                if (api != null && transactionID != null) {
                    try {
                        api.closeTransaction(transactionID);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        // XYZ ZZZ
        log.error(errorMsg);
        if (errorException != null) {
            log.error(errorException);
        }

        // ANAR A LLISTAT DE PETICIONS
        HtmlUtils.saveMessageError(request, errorMsg);

        // XYZ ZZZ Només volem saber si existeix !!!!!!
        Peticio pet = peticioLogicaEjb.findByPrimaryKey(peticioID);

        pet.setErrorMsg(errorMsg);
        pet.setErrorException(errorException);
        pet.setDataFinal(new Timestamp(System.currentTimeMillis()));
        pet.setEstat(Constants.ESTAT_PETICIO_ERROR);

        // XYZ ZZZ FALTA POSAR ERROR DINS PETICIO

        peticioLogicaEjb.update(pet);

        return new ModelAndView(
                new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));

    }

    /**
     * 
     * @param peticio
     * @param usuari
     * @param langUI
     * @param absoluteControllerBase
     * @return
     * @throws I18NException
     */
    protected String[] autofirma(PeticioJPA peticio, Usuari usuari, String langUI,
            String absoluteControllerBase) throws I18NException {

        ApiFirmaWebSimple apiWeb = null;
        String transactionID = null;

        try {

            apiWeb = getApiFirmaWebSimple();

            final String username = usuari.getUsername();
            final String administrationID = usuari.getNif();
            final String signerEmail = usuari.getEmail();

            log.info("Username: ]" + username + "[");
            log.info("administrationID: ]" + administrationID + "[");
            log.info("signerEmail: ]" + signerEmail + "[");

            // XYZ ZZZ
            final String reason = "Prova de reason"; // form.getMotiu();
            final String location = null; // form.getLocation();

            // Només es suporta una firma
            final int signNumber = 1;

            final String langDoc = peticio.getIdiomaDoc();

            FirmaSimpleCommonInfo commonInfoSignature;
            // XYZ ZZZ
            String signProfile = null;
            commonInfoSignature = new FirmaSimpleCommonInfo(signProfile, langUI, username,
                    administrationID, signerEmail);

            // Enviam la part comu de la transacció
            transactionID = apiWeb.getTransactionID(commonInfoSignature);
            log.info("TransactionID = |" + transactionID + "|");

            FirmaSimpleFileInfoSignature fileInfoSignature;
            {

                // No ha de contenir caracters no suportats en URLs
                final String signID = SIGNID;

                String mimeTypeFitxer = peticio.getFitxer().getMime();
                byte[] dataFitxer = FileSystemManager.getFileContent(peticio.getFitxerID());

                String nomFitxer = peticio.getFitxer().getNom();

                FirmaSimpleFile fileToSign = new FirmaSimpleFile(nomFitxer, mimeTypeFitxer,
                        dataFitxer);

                long tipusDocumentalID = Long.parseLong(peticio.getTipusDocumental()); // =TD99

                fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID,
                        fileToSign.getNom(), reason, location, signNumber, langDoc,
                        tipusDocumentalID);

                apiWeb.addFileToSign(
                        new FirmaSimpleAddFileToSignRequest(transactionID, fileInfoSignature));

            }

            // Es Web
            final String view = FirmaSimpleStartTransactionRequest.VIEW_IFRAME;
//          FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN.equals(view)

            final String returnUrl = absoluteControllerBase + "/finalWeb/" + transactionID;

            FirmaSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl,
                    view);

            String redirectUrl = apiWeb.startTransaction(startTransactionInfo);

            return new String[] { transactionID, redirectUrl };

        } catch (AbstractApisIBException e) {

            String msg = "Error cridant a PortaFIB per a la signatura immediata: " + e.getMessage();

            log.error(msg, e);

            // XYZ ZZZ TRA
            throw new I18NException(msg);

        } catch (Exception e) {

            String msg = "Error desconegut processant entrada de dades o inicialitzant el proces de firma: "
                    + e.getMessage();

            log.error(msg, e);

            try {
                // Només s'executa si es WEB
                if (transactionID != null) {
                    try {
                        apiWeb.closeTransaction(transactionID);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }

            throw new I18NException("genapp.comodi", msg);
        }
    }

    protected ApiFirmaWebSimple getApiFirmaWebSimple() throws Exception {

        String url = Configuracio.getPortaFIBApiFirmaWebUrl();
        String username = Configuracio.getPortaFIBApiFirmaWebUsername();
        String password = Configuracio.getPortaFIBApiFirmaWebPassword();

        return new ApiFirmaWebSimpleJersey(url, username, password);
    }

}
