package es.caib.enviafib.logic;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Future;

import javax.annotation.security.PermitAll;

import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;

import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.persistence.Query;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleExternalSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleValidationInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignerInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleValidationInfo;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleBlock;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleExternalSigner;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleReviser;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSignature;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleSigner;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.jboss.ejb3.annotation.TransactionTimeout;

import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PeticioQueryPath;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 * @author anadal
 * @author ptrias
 */
@Stateless(name = "PeticioLogicaEJB")
public class PeticioLogicaEJB extends PeticioEJB implements PeticioLogicaService {

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @EJB(mappedName = es.caib.enviafib.logic.InfoSignaturaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.InfoSignaturaLogicaService infoSignaturaLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuLogicaEjb;

    private static HashMap<Long, String> tipusDocumentals = null;
    private static long lastRefresh = 0;

    @Override
    public void arrancarPeticio(long peticioID, String languageUI) throws I18NException {

        Peticio peticio = this.findByPrimaryKey(peticioID);

        String nifDestinatari = peticio.getDestinatariNif();

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertNifToSignatureBlocks(nifDestinatari);

        arrancarPeticioInterna(peticio, languageUI, signatureBlocks);
    }

    @Override
    public void arrancarPeticioFlux(long peticioID, String languageUI, FlowTemplateSimpleFlowTemplate flux)
            throws I18NException {

        if (log.isDebugEnabled()) {
            log.debug(FlowTemplateSimpleFlowTemplate.toString(flux));
        }

        Peticio peticio = this.findByPrimaryKey(peticioID);

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertFluxToSignatureBlocks(flux);

        arrancarPeticioInterna(peticio, languageUI, signatureBlocks);

    }

    public void arrancarPeticioInterna(Peticio peticio, String languageUI,
            FirmaAsyncSimpleSignatureBlock[] signatureBlocks) throws I18NException {

        String perfil = Configuracio.getPortafibProfile();
        FirmaAsyncSimpleFile fitxerAFirmar = getFitxer(peticio.getFitxer());

        String idiomaDoc = peticio.getIdiomaDoc();
        String tipusDoc = peticio.getTipusDocumental();

        FirmaAsyncSimpleFile fitxerAAnexar = null;
        ApiFirmaAsyncSimple api = null;
        api = getApiFirmaAsyncSimple();

        Long idPortafib;
        try {
            idPortafib = createSignatureRequestAndStart(languageUI, signatureBlocks, perfil, fitxerAFirmar,
                    fitxerAAnexar, tipusDoc, idiomaDoc, api);
        } catch (Throwable e) {
            throw new I18NException("error.portafib.creacio", e.getMessage());
        }
        peticio.setPeticioPortafirmes(String.valueOf(idPortafib));

        peticio.setEstat(Constants.ESTAT_PETICIO_EN_PROCES);
        this.update(peticio);
    }

    protected Long createSignatureRequestAndStart(String languageUI, FirmaAsyncSimpleSignatureBlock[] signatureBlocks,
            String perfil, FirmaAsyncSimpleFile fitxerAFirmar, FirmaAsyncSimpleFile fitxerAAnexar,
            String tipusDocumental, String idiomaDocumental, ApiFirmaAsyncSimple api) throws Exception {

        // Annexes
        List<FirmaAsyncSimpleAnnex> annexs = null;
        {
            FirmaAsyncSimpleFile file = fitxerAAnexar;
            if (file != null) {
                boolean attach = true;
                boolean sign = true;
                FirmaAsyncSimpleAnnex annex = new FirmaAsyncSimpleAnnex(file, attach, sign);
                annexs = new ArrayList<FirmaAsyncSimpleAnnex>();
                annexs.add(annex);
            }
        }

        // Fitxer a Firmar
        if (fitxerAFirmar == null) {
            throw new Exception("No s'ha definit fitxer a firmar");
        }

        FirmaAsyncSimpleSignatureRequestInfo rinfo = null;

        String profileCode = perfil;
        String title = "Peticio de Firma Simple Async - " + ((System.currentTimeMillis() / 1000) % 100000);
        String description = "Prova de firma - Desc";
        String reason = "Prova de firma - reason";
        FirmaAsyncSimpleFile originalDetachedSignature = null;

        Long tipusDocumentalID = null;

        try {
            tipusDocumentalID = Long.valueOf(tipusDocumental);
        } catch (NumberFormatException t) {
            throw new I18NException("error.peticio.tipusdocumental.parselong", tipusDocumental);
        }

        String desc = null;
        long hora = 60 * 60 * 1000;

        try {
            if (tipusDocumentals == null || (lastRefresh + hora) < System.currentTimeMillis()) {
                tipusDocumentals = new HashMap<Long, String>();
                List<FirmaAsyncSimpleDocumentTypeInformation> tipus = api.getAvailableTypesOfDocuments(languageUI);

                for (FirmaAsyncSimpleDocumentTypeInformation f : tipus) {
                    tipusDocumentals.put(f.getDocumentType(), f.getName());
                }

                lastRefresh = System.currentTimeMillis();
            }

            desc = tipusDocumentals.get(tipusDocumentalID);

        } catch (Throwable t) {
            log.error("Error amb API per obtenir tipus documental ]" + tipusDocumental + "[: " + t.getMessage(), t);
        }

        String languageDoc = idiomaDocumental;

        int priority = FirmaAsyncSimpleSignatureRequestWithSignBlockList.PRIORITY_NORMAL_NORMAL;
        String senderName = "Tester Firma Async";
        String senderDescription = "Tester Firma Async - Description";
        String expedientCode = null;
        String expedientName = null;
        String expedientUrl = null;
        String procedureCode = null;
        String procedureName = null;
        String additionalInformation = "Ninguna info";
        Double additionalInformationEvaluable = (double) System.currentTimeMillis();

        List<FirmaAsyncSimpleMetadata> metadadaList = null;

        FirmaAsyncSimpleSignatureRequestBase signatureRequestBase;
        signatureRequestBase = new FirmaAsyncSimpleSignatureRequestBase(profileCode, title, description, reason,
                fitxerAFirmar, originalDetachedSignature, tipusDocumentalID, desc, languageDoc, languageUI, priority,
                senderName, senderDescription, expedientCode, expedientName, expedientUrl, procedureCode, procedureName,
                additionalInformation, additionalInformationEvaluable, annexs, metadadaList);

        // Crear Peticio
        Long peticioDeFirmaID2;

        // Utilitzar Blocs de Firmes
        log.info("Petició de Firma emprant Blocs de Firmes");
        FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;
        signatureRequest = new FirmaAsyncSimpleSignatureRequestWithSignBlockList(signatureRequestBase, signatureBlocks);
        peticioDeFirmaID2 = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);

        log.info("Creada peticio amb ID = " + peticioDeFirmaID2);

        rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioDeFirmaID2, languageUI);

        String url = api.getUrlToViewFlow(rinfo);
        log.info("URL to view flow: " + url);
        return peticioDeFirmaID2;

    }

    @Override
    @PermitAll
    public void cosesAFerPeticioFirmada(long portafibID, String languageUI) throws I18NException {

        Long peticioID = getPeticioIdFromPortafibId(portafibID);

        InfoSignaturaJPA infoSignatura = guardarFitxerInfoFirma(peticioID, portafibID, languageUI);

        // ASYNCHRONOUS Funcionalitat de guardar document a Arxiu amb la API
        guardarFitxerArxiu(peticioID, languageUI, infoSignatura);

        enviarMailSolicitant(portafibID, "FIRMADA", languageUI);

    }

    @Override
    @PermitAll
    public void cosesAFerPeticioRebutjada(long portafibID, String languageUI, String motiuRebuig) throws I18NException {

        List<Peticio> peticioList = this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

        if (peticioList == null || peticioList.size() != 1) {
            log.error("Event de REBUIG amb portafibid = " + portafibID + " i Petició no trobada");
            throw new I18NException("error.portafib.peticionull", "REBUIG", String.valueOf(portafibID));
        }

        Peticio peticio = peticioList.get(0);
        peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
        peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);

        String msg = I18NCommonUtils.tradueix(new Locale(peticio.getIdiomaID()), "peticio.motiu.rebuig", motiuRebuig);
        peticio.setErrorMsg(LogicUtils.split255(msg));
        this.update(peticio);

        enviarMailSolicitant(portafibID, "REBUTJADA", languageUI);
    }

    protected void guardarFitxerArxiu(long peticioID, String languageUI, InfoSignaturaJPA infoSignatura)
            throws I18NException {

        Peticio peticio = findByPrimaryKey(peticioID);
        peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
        this.update(peticio);

        guardarFitxerArxiuAsync(peticioID, languageUI, infoSignatura);

    }

    @Override
    public String reintentarGuardarFitxerArxiu(long peticioID, String languageUI) throws I18NException {

        {
            Peticio peticio = findByPrimaryKey(peticioID);
            peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
            this.update(peticio);
        }

        Long infoSignaturaId = this.executeQueryOne(PeticioFields.INFOSIGNATURAID,
                PeticioFields.PETICIOID.equal(peticioID));
        InfoSignaturaJPA infoSignatura = infoSignaturaLogicaEjb.findByPrimaryKey(infoSignaturaId);

        Peticio peticio;
        try {
            log.info("\n\n Reintentant el guardat de la Petició " + peticioID + " dins d'Arxiu.");
            peticio = guardarFitxerArxiuSync(peticioID, languageUI, infoSignatura);
        } catch (Exception e) {
            // XYZ ZZZ TMP
            log.error("Future.get() ha llança un error: " + e.getMessage(), e);
            peticio = null;
        }

        String msg;
        if (peticio.getEstat() == Constants.ESTAT_PETICIO_FIRMADA) {
            msg = null;
        } else {
            msg = peticio.getErrorMsg();
        }

        return msg;
    }

    @Override
    public String reintentarTancarExpedient(long peticioID) throws I18NException {

//        Long infoSignaturaId = this.executeQueryOne(PeticioFields.INFOSIGNATURAID,
//                PeticioFields.PETICIOID.equal(peticioID));
//        InfoSignaturaJPA infoSignatura = infoSignaturaLogicaEjb.findByPrimaryKey(infoSignaturaId);

        Peticio peticio = this.findByPrimaryKey(peticioID);

        String expedientID = this.executeQueryOne(new PeticioQueryPath().INFOARXIU().ARXIUEXPEDIENTID(),
                PETICIOID.equal(peticioID));

        log.info("\n\n Reintentant el tancament d'expedient de la Petició " + peticioID + " dins d'Arxiu.");
        boolean tancatExpedient = this.pluginArxiuLogicaEjb.tancarExpedient(peticio, expedientID);

        String msg;
        if (tancatExpedient) {
            peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_FIRMADA);
            peticio.setErrorMsg(null);
            peticio.setErrorException(null);
            msg = null;
        } else {
            msg = peticio.getErrorMsg();
        }

        this.update(peticio);

        return msg;
    }

    /**
     * 
     * @param portafibID
     * @param languageUI
     * @param infoSignatura
     * @throws I18NException
     */
    @Override
    @Asynchronous
    public Future<Peticio> guardarFitxerArxiuAsync(long peticioID, String languageUI, InfoSignaturaJPA infoSignatura)
            throws I18NException {

        Peticio peticio = guardarFitxerArxiuSync(peticioID, languageUI, infoSignatura);

        return new AsyncResult<Peticio>(peticio);

    }

    /**
     * 
     * @param peticioID
     * @param languageUI
     * @param infoSignatura
     * @return
     * @throws I18NException
     */
    private Peticio guardarFitxerArxiuSync(long peticioID, String languageUI, InfoSignaturaJPA infoSignatura)
            throws I18NException {
        log.info(" guardarFitxerArxiu:: START");

        Peticio peticio = null;
        long start = System.currentTimeMillis();

        peticio = findByPrimaryKey(peticioID);

        // No llança errors. Només torna InforArxiu null si hi ha hagut un error
        // Ja inicialitza Petició amb l'estat com toca i guarda resultat a InfoArxiu
        InfoArxiuJPA ia = pluginArxiuLogicaEjb.custodiaAmbApiArxiu(peticio, new Locale(languageUI), infoSignatura);

        if (ia != null) {
            peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_FIRMADA);
            peticio.setErrorMsg(null);
            peticio.setErrorException(null);
        }

        this.update(peticio);

        log.info("guardarFitxerArxiu:: END " + (System.currentTimeMillis() - start) + " ms");
        return peticio;
    }

    protected InfoSignaturaJPA guardarFitxerInfoFirma(long peticioID, long portafirmesID, String languageUI)
            throws I18NException {

        Peticio peticio = this.findByPrimaryKey(peticioID);

        FirmaAsyncSimpleSignedFile firma = getFitxerSignat(portafirmesID, languageUI);

        long fitxerID = guardarFitxer(firma);
        log.info("Guardat fitxer signat (" + fitxerID + ") de la petició amb ID=" + peticio.getPeticioID()
                + " al FileSystemManager");
        peticio.setFitxerFirmatID(fitxerID);

        InfoSignaturaJPA info = guardarInfo(firma);
        long infoSignaturaID = info.getInfoSignaturaID();
        log.info("Objecte InfoSignatura creat amb ID= " + infoSignaturaID);
        peticio.setInfoSignaturaID(infoSignaturaID);

        this.update(peticio);

        return info;
    }

    protected Long getPeticioIdFromPortafibId(long portafibID) throws I18NException {
        return this.executeQueryOne(PeticioFields.PETICIOID,
                PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));
    }

    /**
     * protected Peticio getPeticioFromPortafibId(long portafibID) throws
     * I18NException { List<Peticio> peticioList =
     * this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));
     * 
     * if (peticioList == null || peticioList.size() != 1) { log.error("Event de
     * FIRMA amb portafibid = " + portafibID + " i Petició no trobada"); throw new
     * I18NException("error.portafib.peticionull", "FIRMA",
     * String.valueOf(portafibID)); }
     * 
     * Peticio peticio = peticioList.get(0); return peticio; }
     */

    protected boolean esborrarPeticioPortafib(long portafibID, String languageUI) {

        try {
            FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
            rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

            ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
            api.deleteSignatureRequest(rinfo);
            return true;
        } catch (ApisIBServerException e) {
            log.error("Error esborrant petició portafib amb ID " + portafibID + " : Missatge: " + e.getMessage()
                    + " : Descripcio:" + e.getDescription(), e);
            return true;
        } catch (Throwable t) {
            log.error("Error esborrant petició portafib amb ID " + portafibID + " : " + t.getMessage(), t);
            return false;
        }
    }

    protected void enviarMailSolicitant(long portafibID, String estat, String idiomaId) {

        final Locale loc = new Locale(idiomaId);

        try {
            List<Peticio> peticioList = this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

            if (peticioList == null || peticioList.size() != 1) {
                log.error("Event de FIRMA amb portafibid = " + portafibID + " i Petició no trobada");
                throw new I18NException("error.portafib.peticionull", "FIRMA", String.valueOf(portafibID));
            }

            String nomPeticio = peticioList.get(0).getNom().replace("'", "`");

            long estatPeticio = peticioList.get(0).getEstat();

            Long solicitantID = executeQueryOne(PeticioFields.SOLICITANTID,
                    PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

            String urlBase = Configuracio.getUrlBase();
            String email = usuariEjb.executeQueryOne(UsuariFields.EMAIL, UsuariFields.USUARIID.equal(solicitantID));
            String subject = I18NCommonUtils.tradueix(loc, "email.peticio.subject");

            String code = "";
            switch ((int) estatPeticio) {
                case Constants.ESTAT_PETICIO_FIRMADA:
                    code = "email.peticio.body.success";
                break;
                case Constants.ESTAT_PETICIO_ERROR:
                    code = "email.peticio.body.error";
                break;
                case Constants.ESTAT_PETICIO_EN_PROCES:
                    code = "email.peticio.body.process";
                break;
                case Constants.ESTAT_PETICIO_ARXIVANT:
                    code = "email.peticio.body.arxivant";
                    break;
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:{
                    code = "email.peticio.body.error.arxivant";
                }
                break;
            }
            
            

            String message = I18NCommonUtils.tradueix(loc, code, nomPeticio, urlBase);

            boolean isHTML = true;

            String from = Configuracio.getAppEmail();

            // XYZ ZZZ TRA
            // TODO: Eliminar comentari per enviar correus
//            EmailUtil.postMail(subject, message, isHTML, from, email);

        } catch (Throwable t) {
            log.error("EJB: Error enviant mail: " + t.getMessage(), t);
        }

    }

    /**
     * 
     * @param firma
     * @return fitxerID
     * @throws I18NException
     */
    protected long guardarFitxer(FirmaAsyncSimpleSignedFile firma) throws I18NException {
        String nom = firma.getSignedFile().getNom();
        String mime = firma.getSignedFile().getMime();
        byte[] data = firma.getSignedFile().getData();

        Fitxer fdb = fitxerEjb.create(nom, mime, data.length, null);

        Long fitxerID = fdb.getFitxerID();

        try {
            File fitxersignat = FileSystemManager.getFile(fitxerID);
            FileOutputStream fos = new FileOutputStream(fitxersignat);
            fos.write(data);
            fos.flush();
            fos.close();

        } catch (Throwable t) {
            throw new I18NException("error.fitxer.guardar.fsm", String.valueOf(fitxerID), t.getMessage());
        }

        return fitxerID;
    }

    /**
     * 
     * @param firma
     * @return InfoSignaturaID
     * @throws I18NException
     */
    protected InfoSignaturaJPA guardarInfo(FirmaAsyncSimpleSignedFile firma) throws I18NException {

        FirmaAsyncSimpleSignedFileInfo info = firma.getSignedFileInfo();

        Integer signOperation = info.getSignOperation();
        String signType = info.getSignType();
        String signAlgorithm = info.getSignAlgorithm();
        Integer signMode = info.getSignMode();
        Integer signaturesTableLocation = info.getSignaturesTableLocation();
        Boolean timestampIncluded = info.getTimeStampIncluded();
        Boolean policyIncluded = info.getPolicyIncluded();
        String eniTipoFirma = info.getEniTipoFirma();
        String eniPerfilFirma = info.getEniPerfilFirma();

        String eniRolFirma = null;
        String eniSignerName = null;
        String eniSignerAdministrationId = null;
        String eniSignLevel = null;

        FirmaAsyncSimpleSignerInfo signerInfo = info.getSignersInfo().get(0);
        if (signerInfo != null) {
            eniRolFirma = signerInfo.getEniRolFirma();
            eniSignerName = signerInfo.getEniSignerName();
            eniSignerAdministrationId = signerInfo.getEniSignerAdministrationId();
            eniSignLevel = signerInfo.getEniSignLevel();
        }

        Boolean checkAdministrationIdOfSigner = null;
        Boolean checkDocumentModifications = null;
        Boolean checkValidationSignature = null;

        FirmaAsyncSimpleValidationInfo vi = info.getValidationInfo();
        if (vi != null) {
            checkAdministrationIdOfSigner = vi.getCheckAdministrationIDOfSigner();
            checkDocumentModifications = vi.getCheckDocumentModifications();
            checkValidationSignature = vi.getCheckValidationSignature();
        }

        InfoSignaturaJPA is = new InfoSignaturaJPA(signOperation, signType, signAlgorithm, signMode,
                signaturesTableLocation, timestampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, eniRolFirma,
                eniSignerName, eniSignerAdministrationId, eniSignLevel, checkAdministrationIdOfSigner,
                checkDocumentModifications, checkValidationSignature);

        is = (InfoSignaturaJPA) infoSignaturaLogicaEjb.createPublic(is);
        // long infoAsignaturaID = is.getInfoSignaturaID();
        return is;
    }

    @Override
    @PermitAll
    public void updatePublic(Peticio peticio) throws I18NException {
        this.update(peticio);
    }

    @Override
    @PermitAll
    public PeticioJPA findByPrimaryKeyPublic(Long _ID_) {
        return (PeticioJPA) super.findByPrimaryKey(_ID_);
    }

    @Override
    public void deleteFull(Peticio instance) throws I18NException {
        log.info("Borrarem peticio: " + instance.getPeticioID());
        Long infoSignID = instance.getInfoSignaturaID();

        this.deleteIncludingFiles(instance, fitxerEjb);

        if (infoSignID != null) {
            InfoSignaturaJPA is = infoSignaturaLogicaEjb.findByPrimaryKey(infoSignID);
            infoSignaturaLogicaEjb.delete(is);
        }
    }

    protected FirmaAsyncSimpleFile getFitxer(Fitxer fitxer) throws I18NException {

        File f = FileSystemManager.getFile(fitxer.getFitxerID());

        if (!f.exists()) {
            throw new I18NException("error.fitxer.noexist", f.getAbsolutePath());
        }

        byte[] data;
        try {
            data = FileUtils.readFromFile(f);
        } catch (Throwable t) {
            throw new I18NException("error.fitxer.cantread", f.getAbsolutePath(), t.getMessage());
        }

        FirmaAsyncSimpleFile file = new FirmaAsyncSimpleFile(fitxer.getNom(), fitxer.getMime(), data);
        return file;
    }

    protected FirmaAsyncSimpleSignedFile getFitxerSignat(long portafibID, String languageUI) throws I18NException {

        FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
        rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

        ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

        FirmaAsyncSimpleSignedFile fitxerSignat = null;
        try {
            fitxerSignat = api.getSignedFileOfSignatureRequest(rinfo);
        } catch (Throwable t) {
            throw new I18NException("error.portafib.fitxersignat", String.valueOf(portafibID), t.getMessage());
        }

        return fitxerSignat;
    }

    protected ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws I18NException {

        String host = Configuracio.getPortafibGatewayV2();
        String username = Configuracio.getPortafibUsername();
        String password = Configuracio.getPortafibPassword();

        ApiFirmaAsyncSimpleJersey api;

        try {
            new URL(host);
            api = new ApiFirmaAsyncSimpleJersey(host, username, password);

        } catch (MalformedURLException urle) {
            String errorMsg = "Error a la URL de conexió amb PortaFIB. Revisar la URL de la propietat "
                    + Constants.ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaasync.url" + " de l'arxiu: "
                    + Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.";

            throw new I18NException(errorMsg + "   -   " + urle.getMessage());
        } catch (Exception e) {
            throw new I18NException("error.portafib.conexio.api",
                    Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.", e.getMessage());
        }

        // api.setConnectionTimeoutMs(20000); // 20 segons
        // api.setReadTimeoutMs(20000); // 20 segons

        return api;
    }

    @Override
    public List<StringKeyValue> getAvailableTipusDocumental(String lang) throws I18NException {

        ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

        try {
            List<FirmaAsyncSimpleDocumentTypeInformation> tipus = api.getAvailableTypesOfDocuments(lang);

            List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
            for (FirmaAsyncSimpleDocumentTypeInformation t : tipus) {
                __tmp.add(new StringKeyValue(String.valueOf(t.getDocumentType()), t.getName()));
            }

            return __tmp;

        } catch (AbstractApisIBException e) {
            log.error("Error obtenint els tipus documentals: " + e.getMessage(), e);
            throw new I18NException("error.tipusdocumentals.obtencio", e.getMessage());
        }

    }

    @Override
    public void guardarResultatAutofirma(long peticioID, FirmaSimpleSignatureResult fssr) throws I18NException {

        log.info("Autofirma Recuperada Informació de firma: "
                + FirmaSimpleSignedFileInfo.toString(fssr.getSignedFileInfo()));

        Peticio pet = this.findByPrimaryKey(peticioID);

        if (pet == null) {
            throw new I18NException("error.peticio.noexist", String.valueOf(peticioID));
        }

        FirmaSimpleFile fsf = fssr.getSignedFile();
        if (fsf == null) {
            throw new I18NException("error.peticio.fitxersignat", String.valueOf(peticioID));

        }
        guardaFitxerFirmatAutofirma(pet, fsf);

        InfoSignaturaJPA infoSignatura = guardaInformacioSignaturaAutofirma(fssr.getSignedFileInfo());
        pet.setInfoSignaturaID(infoSignatura.getInfoSignaturaID());
        this.update(pet);

        guardarFitxerArxiu(peticioID, pet.getIdiomaID(), infoSignatura);

        // pet.setDataFinal(new Timestamp(System.currentTimeMillis()));
        // pet.setEstat(Constants.ESTAT_PETICIO_FIRMADA);

    }

    /**
     * 
     * @param info
     * @return
     * @throws I18NException
     */
    protected InfoSignaturaJPA guardaInformacioSignaturaAutofirma(FirmaSimpleSignedFileInfo info) throws I18NException {

        int signOperation = info.getSignOperation();
        String signType = info.getSignType();
        String signAlgorithm = info.getSignAlgorithm();
        int signMode = info.getSignMode();
        int signaturesTableLocation = info.getSignaturesTableLocation();
        Boolean timestampIncluded = null; // info.getTimeStampIncluded();
        Boolean policyIncluded = null; // info.getPolicyIncluded();
        String eniTipoFirma = info.getEniTipoFirma();
        String eniPerfilFirma = info.getEniPerfilFirma();

        String eniRolFirma = null;
        String eniSignerName = null;
        String eniSignerAdministrationId = null;
        String eniSignLevel = null;

        FirmaSimpleSignerInfo signerInfo = info.getSignerInfo();
        if (signerInfo != null) {
            eniRolFirma = signerInfo.getEniRolFirma();
            eniSignerName = signerInfo.getEniSignerName();
            eniSignerAdministrationId = signerInfo.getEniSignerAdministrationId();
            eniSignLevel = signerInfo.getEniSignLevel();
        }

        Boolean checkAdministrationIdOfSigner = null;
        Boolean checkDocumentModifications = null;
        Boolean checkValidationSignature = null;

        FirmaSimpleValidationInfo vi = info.getValidationInfo();
        if (vi != null) {
            checkAdministrationIdOfSigner = vi.getCheckAdministrationIDOfSigner();
            checkDocumentModifications = vi.getCheckDocumentModifications();
            checkValidationSignature = vi.getCheckValidationSignature();
        }

        InfoSignaturaJPA is = new InfoSignaturaJPA(signOperation, signType, signAlgorithm, signMode,
                signaturesTableLocation, timestampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma, eniRolFirma,
                eniSignerName, eniSignerAdministrationId, eniSignLevel, checkAdministrationIdOfSigner,
                checkDocumentModifications, checkValidationSignature);

        is = (InfoSignaturaJPA) infoSignaturaLogicaEjb.createPublic(is);

        long infoSignaturaID = is.getInfoSignaturaID();
        log.info("Objecte InfoSignatura creat amb ID= " + infoSignaturaID);

        return is;

    }

    /**
     * 
     * @param pet
     * @param fsf
     * @throws I18NException
     */
    protected void guardaFitxerFirmatAutofirma(Peticio pet, FirmaSimpleFile fsf) throws I18NException {
        Fitxer fitxer = new FitxerJPA();

        fitxer.setNom(fsf.getNom());
        fitxer.setMime(fsf.getMime());
        final byte[] data = fsf.getData();
        fitxer.setTamany(data.length);

        fitxer = fitxerEjb.create(fitxer);

        FileSystemManager.crearFitxer(new ByteArrayInputStream(data), fitxer.getFitxerID());

        pet.setFitxerFirmatID(fitxer.getFitxerID());
    }

    protected FirmaAsyncSimpleSignatureBlock[] convertFluxToSignatureBlocks(FlowTemplateSimpleFlowTemplate flux)
            throws I18NException {

        List<FlowTemplateSimpleBlock> blocks = flux.getBlocks();

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks;
        signatureBlocks = new FirmaAsyncSimpleSignatureBlock[blocks.size()];

        int count = 0;
        for (FlowTemplateSimpleBlock blockOrigen : blocks) {

            List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
            for (FlowTemplateSimpleSignature signOrigen : blockOrigen.getSignatures()) {

                /*
                 * String nif = destinatarisBloc[j].trim();
                 * 
                 * if (nif.trim().length() == 0) { throw new I18NException("genapp.comodi",
                 * "El destinatari " + j + " del bloc " + i + " està buit o val null"); }
                 */

                FirmaAsyncSimpleSigner personToSign = new FirmaAsyncSimpleSigner();

                FlowTemplateSimpleSigner signerOrig = signOrigen.getSigner();

                FlowTemplateSimpleExternalSigner externalOrig = signerOrig.getExternalSigner();
                if (externalOrig != null) {
                    FirmaAsyncSimpleExternalSigner destExtSigner = new FirmaAsyncSimpleExternalSigner();
                    destExtSigner.setAdministrationId(externalOrig.getAdministrationId());
                    destExtSigner.setEmail(externalOrig.getEmail());
                    destExtSigner.setLanguage(externalOrig.getLanguage());

                    destExtSigner.setName(externalOrig.getName());
                    destExtSigner.setSecurityLevel(externalOrig.getSecurityLevel());
                    destExtSigner.setSurnames(externalOrig.getSurnames());
                    ;

                    personToSign.setExternalSigner(destExtSigner);
                } else {
                    personToSign.setAdministrationID(signerOrig.getAdministrationID());
                    personToSign.setIntermediateServerUsername(signerOrig.getIntermediateServerUsername());
                    personToSign.setPositionInTheCompany(signerOrig.getPositionInTheCompany());
                    personToSign.setUsername(signerOrig.getUsername());
                }

                final boolean required = signOrigen.isRequired();
                String reason = signOrigen.getReason(); // Usar la de la Petició

                // Revisors
                int minimumNumberOfRevisers = signOrigen.getMinimumNumberOfRevisers();

                List<FirmaAsyncSimpleReviser> revisersDest;

                List<FlowTemplateSimpleReviser> revisorsOrigen = signOrigen.getRevisers();

                if (revisorsOrigen == null || revisorsOrigen.size() == 0) {
                    revisersDest = null;
                } else {
                    revisersDest = new ArrayList<FirmaAsyncSimpleReviser>();
                    for (FlowTemplateSimpleReviser revOrig : revisorsOrigen) {
                        FirmaAsyncSimpleReviser revDest = new FirmaAsyncSimpleReviser();
                        revDest.setAdministrationID(revOrig.getAdministrationID());
                        revDest.setIntermediateServerUsername(revOrig.getIntermediateServerUsername());
                        revDest.setPositionInTheCompany(revOrig.getPositionInTheCompany());
                        revDest.setRequired(revOrig.isRequired());
                        revDest.setUsername(revOrig.getUsername());
                        revisersDest.add(revDest);
                    }
                }

                signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
                        revisersDest));

            }

            int minimumNumberOfSignaturesRequired = blockOrigen.getSignatureMinimum();
            signatureBlocks[count] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);
            count++;

        }
        return signatureBlocks;
    }

    protected FirmaAsyncSimpleSignatureBlock[] convertNifToSignatureBlocks(String nifDestinatari) throws I18NException {
        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

        String[][] destinataris = new String[][] { { nifDestinatari } };

        if (destinataris == null || destinataris.length == 0) {
            throw new I18NException("error.nifdestinatari.undefined.property", "nifsDestinataris", "test.properties");
        }

        signatureBlocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

        for (int i = 0; i < destinataris.length; i++) {
            String[] destinatarisBloc = destinataris[i];
            if (destinatarisBloc == null || destinatarisBloc.length == 0) {
                throw new I18NException("error.nifdestinatari.destinatarios", String.valueOf(i));
            }
            log.info("BLOC[" + i + "] => Destinataris = " + Arrays.toString(destinatarisBloc));
            List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
            for (int j = 0; j < destinatarisBloc.length; j++) {

                String nif = destinatarisBloc[j].trim();

                if (nif.trim().length() == 0) {
                    throw new I18NException("error.nifdestinatari.destinatario", String.valueOf(i), String.valueOf(j));
                }

                FirmaAsyncSimpleSigner personToSign;

                personToSign = new FirmaAsyncSimpleSigner();
                personToSign.setAdministrationID(nif);

                boolean required = true;
                String reason = null; // Usar la de la Petició

                // Revisors
                int minimumNumberOfRevisers;
                List<FirmaAsyncSimpleReviser> revisers;

                minimumNumberOfRevisers = 0;
                revisers = null;

                signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
                        revisers));

            }

            int minimumNumberOfSignaturesRequired = signers.size();
            signatureBlocks[i] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);

        }
        return signatureBlocks;
    }
    
    //Funcio que s'executa cada vespre a les 4:00 i elimina peticions acabades de PortaFIB.
    @TransactionTimeout(value = 180)
    @Schedule(hour = "4", persistent = false)
    protected void eliminarPeticionsPortaFIB() {
        long currentTime = System.currentTimeMillis();
        final String languageUI = "ca";
        try {
            Where w1 = TIPUS.notEqual(Constants.TIPUS_PETICIO_AUTOFIRMA);

            Where w2 = Where.OR(ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA),
                    ESTAT.equal(Constants.ESTAT_PETICIO_ERROR));

            Where w3 = PETICIOID.notIn(this.getSubQuery(PETICIOID, PETICIOPORTAFIRMES.like("JAESBORRAT%")));

            List<String> listPortaFIBIds = this.executeQuery(PETICIOPORTAFIRMES, Where.AND(w1, w2, w3));

            for (String portaFIBID : listPortaFIBIds) {
                try {
                    log.info("Esborram Peticio amb PortaFIB ID: " + portaFIBID);

                    long portafibID = Long.parseLong(portaFIBID);

                    if (esborrarPeticioPortafib(portafibID, languageUI)) {
                        Query query = getEntityManager().createQuery("update " + PeticioJPA.class.getSimpleName()
                                + " set " + PeticioFields.PETICIOPORTAFIRMES.javaName + " = ?0" + " where "
                                + PeticioFields.PETICIOPORTAFIRMES.javaName + " = ?1");
                        query.setParameter(0, "ESBORRADA_" + portaFIBID);
                        query.setParameter(1, portaFIBID);
                        query.executeUpdate();
                    } else {

                        String msg = I18NCommonUtils.tradueix(new Locale(languageUI), "error.peticio.portafib.eliminacio" , portaFIBID);
                        log.error(msg);
                    }
                    
                    //El Timeout son 3 minuts. Si el CRON executa durant 2 min i 59 segons, es surt de la funció.
                    if((System.currentTimeMillis() - currentTime) > 179000) {
                        return;
                    }

                } catch (Throwable e) {
                    
                    String msg = I18NCommonUtils.tradueix(new Locale(languageUI), "error.peticio.portafib.eliminacio" , portaFIBID)+ " : " + e.getMessage();
                    log.error(msg, e);
                }

            }

        } catch (I18NException e) {
            String msg = e.getMessage();
            log.error(msg, e);
        }

    }


}
