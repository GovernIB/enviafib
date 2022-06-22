package es.caib.enviafib.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;

import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
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
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NTranslation;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 * @author anadal
 */
@Stateless(name = "PeticioLogicaEJB")
public class PeticioLogicaEJB extends PeticioEJB implements PeticioLogicaService {

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @EJB(mappedName = es.caib.enviafib.logic.InfoSignaturaLogicService.JNDI_NAME)
    protected es.caib.enviafib.logic.InfoSignaturaLogicService infoSignaturaLogicEjb;

    private static HashMap<Long, String> tipusDocumentals = null;
    private static long lastRefresh = 0;

    // public static final Map<String, InfoGlobal> peticionsByAutofirmaID = new
    // HashMap<String, InfoGlobal>();

    public PeticioLogicaEJB() {
    }

    @Override
    public void arrancarPeticio(long peticioID, String languageUI) throws I18NException {

        Peticio peticio = this.findByPrimaryKeyPublic(peticioID);

        String perfil = Configuracio.getPortafibProfile();

        String nifDestinatari = peticio.getDestinatariNif();
        FirmaAsyncSimpleFile fitxerAFirmar = getFitxer(peticio.getFitxer());

        String idiomaDoc = peticio.getIdiomaDoc();
        String tipusDoc = peticio.getTipusDocumental();

        FirmaAsyncSimpleFile fitxerAAnexar = null;
        ApiFirmaAsyncSimple api = null;
        api = getApiFirmaAsyncSimple();

        Long idPortafib;
        try {
            idPortafib = createSignatureRequestAndStart(languageUI, nifDestinatari, perfil, fitxerAFirmar,
                    fitxerAAnexar, tipusDoc, idiomaDoc, api);
        } catch (Throwable e) {
            throw new I18NException("genapp.comodi", "Error creant peticio de firma dins PortaFIB: " + e.getMessage());
        }
        peticio.setPeticioPortafirmes(String.valueOf(idPortafib));
        this.update(peticio);
    }

    protected Long createSignatureRequestAndStart(String languageUI, String nifDestinatari, String perfil,
            FirmaAsyncSimpleFile fitxerAFirmar, FirmaAsyncSimpleFile fitxerAAnexar, String tipusDocumental,
            String idiomaDocumental, ApiFirmaAsyncSimple api) throws Exception {

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

        String[][] destinataris = new String[][] { { nifDestinatari } };

        if (destinataris == null || destinataris.length == 0) {
            throw new Exception("S'ha de definir la propietat nifsDestinataris dins test.properties");
        }

        signatureBlocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

        for (int i = 0; i < destinataris.length; i++) {
            String[] destinatarisBloc = destinataris[i];
            if (destinatarisBloc == null || destinatarisBloc.length == 0) {
                throw new Exception("Els destinataris del bloc " + i + " està buit o val null");
            }
            System.out.println("BLOC[" + i + "] => Destinataris = " + Arrays.toString(destinatarisBloc));
            List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
            for (int j = 0; j < destinatarisBloc.length; j++) {

                String nif = destinatarisBloc[j].trim();

                if (nif.trim().length() == 0) {
                    throw new Exception("El destinatari " + j + " del bloc " + i + " està buit o val null");
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
            throw new I18NException("genapp.comodi",
                    "No s'ha pogut pasar a Long el tipus documental]" + tipusDocumental + "[ ");
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
        guardarFitxerInfoFirma(portafibID, languageUI);
        esborrarPeticioPortafib(portafibID, languageUI);
        enviarMailSolicitant(portafibID, "FIRMADA");
    }

    @Override
    @PermitAll
    public void cosesAFerPeticioRebutjada(long portafibID, String languageUI) throws I18NException {

        List<Peticio> peticioList = this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

        if (peticioList == null || peticioList.size() != 1) {
            String msg = "S'ha rebut un event de REBUIG amb idportafib=" + portafibID
                    + ", pero no correspón a cap peticio";
            log.error(msg);
            throw new I18NException("genapp.comodi", msg);
        }

        Peticio peticio = peticioList.get(0);
        peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
        peticio.setEstat(Constants.ESTAT_PETICIO_REBUTJADA);
        this.update(peticio);

        esborrarPeticioPortafib(portafibID, languageUI);

        enviarMailSolicitant(portafibID, "REBUTJADA");
    }

    protected void guardarFitxerInfoFirma(long portafibID, String languageUI) throws I18NException {

        List<Peticio> peticioList = this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

        if (peticioList == null || peticioList.size() != 1) {
            String msg = "S'ha rebut un event de FIRMA amb idportafib=" + portafibID
                    + ", pero no correspón a cap peticio";
            log.error(msg);
            throw new I18NException("genapp.comodi", msg);
        }

        FirmaAsyncSimpleSignedFile firma = getFitxerSignat(portafibID, languageUI);
        Peticio peticio = peticioList.get(0);

        long fitxerID = guardarFitxer(firma);
        log.info("Guardat fitxer signat (" + fitxerID + ") de la petició amb ID=" + peticio.getPeticioID()
                + " al FileSystemManager");
        peticio.setFitxerFirmatID(fitxerID);

        long infoSignaturaID = guardarInfo(firma);
        log.info("Objecte InfoSignatura creat amb ID= " + infoSignaturaID);
        peticio.setInfoSignaturaID(infoSignaturaID);

        peticio.setEstat(Constants.ESTAT_PETICIO_FIRMADA);
        peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));

        this.update(peticio);
    }

    protected void esborrarPeticioPortafib(long portafibID, String languageUI) {

        try {
            FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
            rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

            ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
            api.deleteSignatureRequest(rinfo);
        } catch (Throwable t) {
            log.error("Error esborrant petició portafib " + portafibID + ": " + t.getMessage(), t);
        }
    }

    protected void enviarMailSolicitant(long portafibID, String estat) {

        try {
            Long solicitantID = executeQueryOne(PeticioFields.SOLICITANTID,
                    PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

            String email = usuariEjb.executeQueryOne(UsuariFields.EMAIL, UsuariFields.USUARIID.equal(solicitantID));

            String subject = "Petició finalitzada";
            String message = "<h1>L'estat de la seva petició es: " + estat + " </h1>";
            boolean isHTML = true;

            String from = Configuracio.getAppEmail();

            EmailUtil.postMail(subject, message, isHTML, from, email);

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

        } catch (Throwable e) {
            String msg = "Ha hagut un error guardant el fitxer (" + fitxerID + ") al FileSystemManager"
                    + e.getMessage();
            throw new I18NException("genapp.comodi", msg);
        }

        return fitxerID;
    }

    /**
     * 
     * @param firma
     * @return InfoSignaturaID
     * @throws I18NException
     */
    protected long guardarInfo(FirmaAsyncSimpleSignedFile firma) throws I18NException {

        FirmaAsyncSimpleSignedFileInfo info = firma.getSignedFileInfo();

        int signOperation = info.getSignOperation();
        String signType = info.getSignType();
        String signAlgorithm = info.getSignAlgorithm();
        int signMode = info.getSignMode();
        int signaturesTableLocation = info.getSignaturesTableLocation();
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

        is = (InfoSignaturaJPA) infoSignaturaLogicEjb.createPublic(is);
        long infoAsignaturaID = is.getInfoSignaturaID();
        return infoAsignaturaID;
    }
    

    @Override
    @PermitAll
    public void updatePublic(Peticio peticio) throws I18NException {
        super.update(peticio);
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

        super.delete(instance);

        if (infoSignID != null) {
            InfoSignaturaJPA is = infoSignaturaLogicEjb.findByPrimaryKey(infoSignID);
            infoSignaturaLogicEjb.delete(is);
        }
    }
    

    protected FirmaAsyncSimpleFile getFitxer(Fitxer fitxer) throws I18NException {

        File f = FileSystemManager.getFile(fitxer.getFitxerID());

        if (!f.exists()) {
            throw new I18NException("genapp.comodi", "No existeix el fitxer " + f.getAbsolutePath());
        }

        byte[] data;
        try {
            data = FileUtils.readFromFile(f);
        } catch (Throwable e) {
            String msg = "No es pot llegir el fitxer " + f.getAbsolutePath() + " - " + e.getMessage();
            throw new I18NException("genapp.comodi", msg);

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
            String msg = "No es pot obtenir el fitxer signat de la petició de portafib " + portafibID + ": "
                    + t.getMessage();
            throw new I18NException("genapp.comodi", msg);
        }

        return fitxerSignat;
    }

    protected ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws I18NException {

        String host = Configuracio.getPortafibGatewayV2();
        String username = Configuracio.getPortafibUsername();
        String password = Configuracio.getPortafibPassword();

        ApiFirmaAsyncSimpleJersey api;

        try {
            URL hostUrl = new URL(host);
            api = new ApiFirmaAsyncSimpleJersey(host, username, password);

        } catch (MalformedURLException urle) {
            String errorMsg = "Error a la URL de conexió amb PortaFIB. Revisar la URL de la propietat "
                    + Constants.ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaasync.url" + " de l'arxiu: "
                    + Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.";
            throw new I18NException(errorMsg + "   -   " + urle.getMessage());
        } catch (Exception e) {
            String errorMsg = "Error de conexió amb la API de PortaFIB. Revisar la conexió amb PortaFIB i les propietats de 'apifirmaasync' de l'arxiu de propietats "
                    + Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.";
            throw new I18NException("genapp.comodi", errorMsg + "   -   " + e.getMessage());
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

    public void guardarResultatAutofirma(long peticioID, FirmaSimpleSignatureResult fssr) throws I18NException {

        log.info("Autofirma Recuperada Informació de firma: "
                + FirmaSimpleSignedFileInfo.toString(fssr.getSignedFileInfo()));

        // XYZ ZZZ Només volem saber si existeix !!!!!!
        Peticio pet = this.findByPrimaryKey(peticioID);

        FirmaSimpleFile fsf = fssr.getSignedFile();

        if (fsf == null) {
            // XYZ ZZZ
            throw new I18NException("genapp.comodi", "No s'ha pogut recuperar el fitxer signat ...");

        } else {
            guardaFitxerFirmatAutofirma(pet, fsf);

            long infoSignaturaID = guardaInformacioSignaturaAutofirma(fssr.getSignedFileInfo());
            pet.setInfoSignaturaID(infoSignaturaID);

            pet.setDataFinal(new Timestamp(System.currentTimeMillis()));
            pet.setEstat(Constants.ESTAT_PETICIO_FIRMADA);

            this.update(pet);
        }
    }

    /**
     * 
     * @param info
     * @return
     * @throws I18NException
     */
    public long guardaInformacioSignaturaAutofirma(FirmaSimpleSignedFileInfo info) throws I18NException {

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

        is = (InfoSignaturaJPA) infoSignaturaLogicEjb.createPublic(is);

        long infoSignaturaID = is.getInfoSignaturaID();
        log.info("Objecte InfoSignatura creat amb ID= " + infoSignaturaID);

        return infoSignaturaID;

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
        byte[] data = fsf.getData();
        fitxer.setTamany(data.length);

        fitxer = fitxerEjb.create(fitxer);

        pet.setFitxerFirmatID(fitxer.getFitxerID());
    }
}
