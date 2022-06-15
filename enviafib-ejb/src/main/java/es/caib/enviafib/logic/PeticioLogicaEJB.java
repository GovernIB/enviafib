package es.caib.enviafib.logic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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

import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
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

    @EJB(mappedName = es.caib.enviafib.ejb.InfoSignaturaService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoSignaturaService infoSignaturaEjb;

    private static HashMap<Long, String> tipusDocumentals = null;
    private static long lastRefresh = 0;

    // public static final Map<String, InfoGlobal> peticionsByAutofirmaID = new
    // HashMap<String, InfoGlobal>();

    public PeticioLogicaEJB() {
    }

    @Override
    public void arrancarPeticio(long peticioID, String languageUI) throws I18NException {

        Peticio peticio = this.findByPrimaryKeyPublic(peticioID);

        String nifDestinatari = peticio.getDestinatarinif();
        String perfil = "ENVIAFIB_PADES";
        FirmaAsyncSimpleFile fitxerAFirmar = getFitxer(peticio.getFitxer());

        String idiomaDoc = peticio.getIdiomadoc();
        String tipusDoc = peticio.getTipusdocumental();

        FirmaAsyncSimpleFile fitxerAAnexar = null;
        ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

        Long idPortafib;
        try {
            idPortafib = createSignatureRequestAndStart(languageUI, nifDestinatari, perfil, fitxerAFirmar,
                    fitxerAAnexar, tipusDoc, idiomaDoc, api);
        } catch (Exception e) {
            throw new I18NException("genapp.comodi", "Error creant peticio de firma dins PortaFIB: " + e.getMessage());
        }
        peticio.setPeticioPortafirmes(String.valueOf(idPortafib));
        this.update(peticio);
    }

    @Override
    public long guardarFitxerSignat(long peticioID, String languageUI)
            throws I18NException, AbstractApisIBException, IOException {

        Peticio peticio = this.findByPrimaryKeyPublic(peticioID);
        long portafibID = Long.valueOf(peticio.getPeticioPortafirmes());

        FirmaAsyncSimpleSignedFile firma = getFitxerSignat(portafibID, languageUI);

        String nom = firma.getSignedFile().getNom();
        String mime = firma.getSignedFile().getMime();
        byte[] data = firma.getSignedFile().getData();

        Fitxer fdb = fitxerEjb.create(nom, mime, data.length, null);

        Long idfitxer = fdb.getFitxerID();

        File fitxersignat = FileSystemManager.getFile(idfitxer);
        FileOutputStream fos = new FileOutputStream(fitxersignat);
        fos.write(data);
        fos.flush();
        fos.close();

        peticio.setFitxerFirmatID(idfitxer);
        peticio.setEstat(Constants.ESTAT_PETICIO_FIRMADA);
        this.update(peticio);

        return idfitxer;
    }

    @Override
    @PermitAll
    public long guardaInformacioSignatura(long peticioID, String languageUI)
            throws I18NException, AbstractApisIBException {

        Peticio peticio = this.findByPrimaryKeyPublic(peticioID);
        long portafibID = Long.valueOf(peticio.getPeticioPortafirmes());
        
        FirmaAsyncSimpleSignedFile firma = getFitxerSignat(portafibID, languageUI);
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
    //        eniRolFirma = signerInfo.getEniRolFirma();
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

        log.info("signOperation: " + signOperation);
        log.info("signType: " + signType);
        log.info("signAlgorithm: " + signAlgorithm);
        log.info("signMode: " + signMode);
        log.info("signaturesTableLocation: " + signaturesTableLocation);
        log.info("timestampIncluded: " + timestampIncluded);
        log.info("policyIncluded: " + policyIncluded);
        log.info("eniTipoFirma: " + eniTipoFirma);
        log.info("eniPerfilFirma: " + eniPerfilFirma);
        log.info("eniRolFirma: " + eniRolFirma);
        log.info("eniSignerName: " + eniSignerName);
        log.info("eniSignerAdministrationId: " + eniSignerAdministrationId);
        log.info("eniSignLevel: " + eniSignLevel);
        log.info("checkAdministrationIdOfSigner: " + checkAdministrationIdOfSigner);
        log.info("checkDocumentModifications: " + checkDocumentModifications);
        log.info("checkValidationSignature: " + checkValidationSignature);

        log.info("Tenim checkInfo, i feim create");

        InfoSignaturaJPA is = new InfoSignaturaJPA(signOperation, signType,
                signAlgorithm, signMode, signaturesTableLocation,
                timestampIncluded, policyIncluded, eniTipoFirma, eniPerfilFirma,
                eniRolFirma, eniSignerName, eniSignerAdministrationId,
                eniSignLevel, checkAdministrationIdOfSigner,
                checkDocumentModifications, checkValidationSignature);

        is = (InfoSignaturaJPA) infoSignaturaEjb.create(is);

//        InfoSignatura is = infoSignaturaEjb.create(
//                signOperation, 
//                signType, 
//                signAlgorithm, 
//                signMode,
//                signaturesTableLocation, 
//                timestampIncluded, 
//                policyIncluded, 
//                eniTipoFirma, 
//                eniPerfilFirma, 
//                eniRolFirma,
//                eniSignerName, 
//                eniSignerAdministrationId, 
//                eniSignLevel, 
//                checkAdministrationIdOfSigner,
//                checkDocumentModifications, 
//                checkValidationSignature);

        log.info("Create fet. ID= " + is.getInfosignaturaid());
        return is.getInfosignaturaid();
    }

    @Override
    @PermitAll
    public void esborrarPeticioPortafib(long peticioPortafibId, String languageUI) throws I18NException {

        try {
            // Peticio peticio = this.findByPrimaryKey(peticioID);
            // Long peticioPortafibId = peticio.getPeticioPortafib();

            FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
            rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioPortafibId, languageUI);

            ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
            api.deleteSignatureRequest(rinfo);
        } catch (AbstractApisIBException e) {
            throw new I18NException("portafib.error.delete", String.valueOf(peticioPortafibId));
        }
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
    @PermitAll
    public void deleteFull(Peticio instance) throws I18NException {
        Long infoSignID = instance.getInfosignaturaid();
        if (infoSignID != null) {
            infoSignaturaEjb.delete(infoSignID);
        }
        super.delete(instance);
    }

    public Long createSignatureRequestAndStart(String languageUI, String nifDestinatari, String perfil,
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
        } catch (Throwable t) {
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

    protected FirmaAsyncSimpleFile getFitxer(Fitxer fitxer) throws I18NException {

        File f = FileSystemManager.getFile(fitxer.getFitxerID());

        if (!f.exists()) {
            throw new I18NException("genapp.comodi", "No existeix el fitxer " + f.getAbsolutePath());
        }

        byte[] data;
        try {
            data = FileUtils.readFromFile(f);
        } catch (Exception e) {
            throw new I18NException("genapp.comodi", "No es pot llegir el fitxer " + f.getAbsolutePath());
        }

        return new FirmaAsyncSimpleFile(fitxer.getNom(), fitxer.getMime(), data);
    }

    public FirmaAsyncSimpleSignedFile getFitxerSignat(long portafibID, String languageUI)
            throws I18NException, AbstractApisIBException {

        FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
        rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

        ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

        FirmaAsyncSimpleSignedFile fitxerSignat = null;
        fitxerSignat = api.getSignedFileOfSignatureRequest(rinfo);

        return fitxerSignat;
    }

    protected ApiFirmaAsyncSimple getApiFirmaAsyncSimple() {

        String host = Configuracio.getPortafibGatewayV2();
        String username = Configuracio.getPortafibUsername();
        String password = Configuracio.getPortafibPassword();

        ApiFirmaAsyncSimpleJersey api;
        api = new ApiFirmaAsyncSimpleJersey(host, username, password);

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
            throw new I18NException("genapp.comodi", "Error llegint tipus documentals: " + e.getMessage());
        }

    }

}
