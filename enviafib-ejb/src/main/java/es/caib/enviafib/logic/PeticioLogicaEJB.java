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
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;

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
import org.fundaciobit.apisib.core.exceptions.ApisIBServerException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.jboss.ejb3.annotation.TransactionTimeout;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PeticioQueryPath;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 * @author anadal
 * @author ptrias
 */
@Stateless(name = "PeticioLogicaEJB")
public class PeticioLogicaEJB extends PeticioEJB implements PeticioLogicaService {

    protected static final long TRANSACTION_TIMEOUT_IN_SEC = 180;

    protected static final long TRANSACTION_EXIT_IN_MILI = (TRANSACTION_TIMEOUT_IN_SEC * 2 / 3) * 1000;

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @EJB(mappedName = es.caib.enviafib.logic.InfoSignaturaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.InfoSignaturaLogicaService infoSignaturaLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.logic.InfoArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.InfoArxiuLogicaService infoArxiuLogicEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.SerieDocumentalService.JNDI_NAME)
    protected es.caib.enviafib.ejb.SerieDocumentalService serieDocEjb;

    private static HashMap<Long, String> tipusDocumentals = null;
    private static long lastRefresh = 0;

    
    protected static final Map<String, String> MAP_TIPUS_DOCUMENTAL_ES = new HashMap<String, String>();
    protected static final Map<String, String> MAP_TIPUS_DOCUMENTAL_CA= new HashMap<String, String>();

    static {
        MAP_TIPUS_DOCUMENTAL_CA.put("1", "Resolució");
        MAP_TIPUS_DOCUMENTAL_CA.put("2", "Acord");
        MAP_TIPUS_DOCUMENTAL_CA.put("3", "Contracte");
        MAP_TIPUS_DOCUMENTAL_CA.put("4", "Conveni");
        MAP_TIPUS_DOCUMENTAL_CA.put("5", "Declaració");
        MAP_TIPUS_DOCUMENTAL_CA.put("6", "Comunicació");
        MAP_TIPUS_DOCUMENTAL_CA.put("7", "Notificació");
        MAP_TIPUS_DOCUMENTAL_CA.put("8", "Publicació");
        MAP_TIPUS_DOCUMENTAL_CA.put("9", "Justificant de recepció");
        MAP_TIPUS_DOCUMENTAL_CA.put("10", "Acta");
        MAP_TIPUS_DOCUMENTAL_CA.put("11", "Certificat");
        MAP_TIPUS_DOCUMENTAL_CA.put("12", "Diligència");
        MAP_TIPUS_DOCUMENTAL_CA.put("13", "Informe");
        MAP_TIPUS_DOCUMENTAL_CA.put("14", "Sol·licitud");
        MAP_TIPUS_DOCUMENTAL_CA.put("15", "Denúncia");
        MAP_TIPUS_DOCUMENTAL_CA.put("16", "Al·legació");
        MAP_TIPUS_DOCUMENTAL_CA.put("17", "Recursos");
        MAP_TIPUS_DOCUMENTAL_CA.put("18", "Comunicació ciutadà");
        MAP_TIPUS_DOCUMENTAL_CA.put("19", "Factura");
        MAP_TIPUS_DOCUMENTAL_CA.put("20", "Altre documentació aportada");
        MAP_TIPUS_DOCUMENTAL_CA.put("51", "Llei");
        MAP_TIPUS_DOCUMENTAL_CA.put("52", "Moció");
        MAP_TIPUS_DOCUMENTAL_CA.put("53", "Instrucció");
        MAP_TIPUS_DOCUMENTAL_CA.put("54", "Convocatòria");
        MAP_TIPUS_DOCUMENTAL_CA.put("55", "Ordre del dia");
        MAP_TIPUS_DOCUMENTAL_CA.put("56", "Informe de Ponència");
        MAP_TIPUS_DOCUMENTAL_CA.put("57", "Dictamen de Comissió");
        MAP_TIPUS_DOCUMENTAL_CA.put("58", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL_CA.put("59", "Pregunta");
        MAP_TIPUS_DOCUMENTAL_CA.put("60", "Interpel·lació");
        MAP_TIPUS_DOCUMENTAL_CA.put("61", "Resposta");
        MAP_TIPUS_DOCUMENTAL_CA.put("62", "Proposició no de llei");
        MAP_TIPUS_DOCUMENTAL_CA.put("63", "Esmena");
        MAP_TIPUS_DOCUMENTAL_CA.put("64", "Proposada de resolució");
        MAP_TIPUS_DOCUMENTAL_CA.put("65", "Compareixença");
        MAP_TIPUS_DOCUMENTAL_CA.put("66", "Sol·licitud d'informació");
        MAP_TIPUS_DOCUMENTAL_CA.put("67", "Escrit");
        MAP_TIPUS_DOCUMENTAL_CA.put("68", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL_CA.put("69", "Petició");
        MAP_TIPUS_DOCUMENTAL_CA.put("99", "Altres tipus de documents");

        MAP_TIPUS_DOCUMENTAL_ES.put("1", "Resolución");
        MAP_TIPUS_DOCUMENTAL_ES.put("2", "Acuerdo");
        MAP_TIPUS_DOCUMENTAL_ES.put("3", "Contrato");
        MAP_TIPUS_DOCUMENTAL_ES.put("4", "Convenio");
        MAP_TIPUS_DOCUMENTAL_ES.put("5", "Declaración");
        MAP_TIPUS_DOCUMENTAL_ES.put("6", "Comunicación");
        MAP_TIPUS_DOCUMENTAL_ES.put("7", "Notificación");
        MAP_TIPUS_DOCUMENTAL_ES.put("8", "Publicación");
        MAP_TIPUS_DOCUMENTAL_ES.put("9", "Justificante de recepción");
        MAP_TIPUS_DOCUMENTAL_ES.put("10", "Acta");
        MAP_TIPUS_DOCUMENTAL_ES.put("11", "Certificado");
        MAP_TIPUS_DOCUMENTAL_ES.put("12", "Diligencia");
        MAP_TIPUS_DOCUMENTAL_ES.put("13", "Informe");
        MAP_TIPUS_DOCUMENTAL_ES.put("14", "Solicitud");
        MAP_TIPUS_DOCUMENTAL_ES.put("15", "Denuncia");
        MAP_TIPUS_DOCUMENTAL_ES.put("16", "Alegación");
        MAP_TIPUS_DOCUMENTAL_ES.put("17", "Recursos");
        MAP_TIPUS_DOCUMENTAL_ES.put("18", "Comunicación ciudadano");
        MAP_TIPUS_DOCUMENTAL_ES.put("19", "Factura");
        MAP_TIPUS_DOCUMENTAL_ES.put("20", "Otra documentación aportada");
        MAP_TIPUS_DOCUMENTAL_ES.put("51", "Ley");
        MAP_TIPUS_DOCUMENTAL_ES.put("52", "Moción");
        MAP_TIPUS_DOCUMENTAL_ES.put("53", "Instrucción");
        MAP_TIPUS_DOCUMENTAL_ES.put("54", "Convocatoria");
        MAP_TIPUS_DOCUMENTAL_ES.put("55", "Orden del día");
        MAP_TIPUS_DOCUMENTAL_ES.put("56", "Informe de Ponencia");
        MAP_TIPUS_DOCUMENTAL_ES.put("57", "Dictamen de Comisión");
        MAP_TIPUS_DOCUMENTAL_ES.put("58", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL_ES.put("59", "Pregunta");
        MAP_TIPUS_DOCUMENTAL_ES.put("60", "Interpelación");
        MAP_TIPUS_DOCUMENTAL_ES.put("61", "Respuesta");
        MAP_TIPUS_DOCUMENTAL_ES.put("62", "Proposición no de ley");
        MAP_TIPUS_DOCUMENTAL_ES.put("63", "Enmienda");
        MAP_TIPUS_DOCUMENTAL_ES.put("64", "Propuesta de resolución");
        MAP_TIPUS_DOCUMENTAL_ES.put("65", "Comparecencia");
        MAP_TIPUS_DOCUMENTAL_ES.put("66", "Solicitud de información");
        MAP_TIPUS_DOCUMENTAL_ES.put("67", "Escrito");
        MAP_TIPUS_DOCUMENTAL_ES.put("68", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL_ES.put("69", "Petición");
        MAP_TIPUS_DOCUMENTAL_ES.put("99", "Otros tipos de documentos");
    }
    
    @Override
    public PeticioJPA arrancarPeticio(long peticioID, String languageUI, Usuari solicitant) throws I18NException {

        Peticio peticio = this.findByPrimaryKey(peticioID);

        String nifDestinatari = peticio.getDestinatariNif();

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertNifToSignatureBlocks(nifDestinatari);

        arrancarPeticioBySignatureBlocks(peticio, languageUI, signatureBlocks, solicitant);

        return (PeticioJPA) peticio;
    }

    @Override
    public PeticioJPA arrancarPeticioFlux(long peticioID, String languageUI, FlowTemplateSimpleFlowTemplate flux, Usuari solicitant)
            throws I18NException {

        if (log.isDebugEnabled()) {
            log.debug(FlowTemplateSimpleFlowTemplate.toString(flux));
        }

        Peticio peticio = this.findByPrimaryKey(peticioID);

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = convertFluxToSignatureBlocks(flux);

        arrancarPeticioBySignatureBlocks(peticio, languageUI, signatureBlocks, solicitant);

        return (PeticioJPA) peticio;
    }

    @Override
    public void arrancarPeticioBySignatureBlocks(Peticio peticio, String languageUI,
            FirmaAsyncSimpleSignatureBlock[] signatureBlocks, Usuari solicitant ) throws I18NException {

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
                    fitxerAAnexar, tipusDoc, idiomaDoc, api, peticio.getTipus(), peticio.getNom(), solicitant);

            peticio.setPeticioPortafirmes(String.valueOf(idPortafib));
            peticio.setEstat(Constants.ESTAT_PETICIO_EN_PROCES);

        } catch (Throwable e) {
            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            //            peticio.setErrorMsg(LogicUtils.split255(e.getMessage()));
            //            peticio.setErrorException(LogicUtils.stackTrace2String(e));
            throw new I18NException("error.portafib.creacio", e.getMessage());
        }
        this.update(peticio);
    }

    protected Long createSignatureRequestAndStart(String languageUI, FirmaAsyncSimpleSignatureBlock[] signatureBlocks,
            String profileCode, FirmaAsyncSimpleFile fitxerAFirmar, FirmaAsyncSimpleFile fitxerAAnexar,
            String tipusDocumental, String idiomaDocumental, ApiFirmaAsyncSimple api, int tipusPeticio,
            String titolPeticio, Usuari solicitant) throws Exception {

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
            // XYZ ZZZ TRA
            throw new Exception("No s'ha definit fitxer a firmar");
        }

        //SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
        //sdf.format(new Date(System.currentTimeMillis()))
        
        String title = "ENVIAFIB_" + titolPeticio;
        if(title.length() > 250) {
            title = title.substring(0, 250);
        }
        final String description = "Firma des d´EnviaFIB. Tipus: " + tipusPeticio;
        final String reason = "Firma des de EnviaFIB.";
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
        
        String l1 = solicitant.getLlinatge1() == null ? "" : solicitant.getLlinatge1();
        String l2 = solicitant.getLlinatge2() == null ? "" : solicitant.getLlinatge2();
        String fullName = solicitant.getNom() + " " + l1 + " " + l2;

        String senderName = fullName;
        String senderDescription = solicitant.getUsername(); // + " - " + solicitant.getNif();

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
//        log.info("Petició de Firma emprant Blocs de Firmes");
        FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;
        signatureRequest = new FirmaAsyncSimpleSignatureRequestWithSignBlockList(signatureRequestBase, signatureBlocks);
        peticioDeFirmaID2 = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);

//        log.info("Creada peticio amb portafibID = " + peticioDeFirmaID2);

        FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
        rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioDeFirmaID2, languageUI);

        String url = api.getUrlToViewFlow(rinfo);
//        log.info("URL to view flow: " + url);
        return peticioDeFirmaID2;
    }

    @Override
    @PermitAll
    public InfoSignaturaJPA cosesAFerPeticioFirmadaPart1(long portafibID, String languageUI) throws I18NException {

        Long peticioID = getPeticioIdFromPortafibId(portafibID);

        InfoSignaturaJPA infoSignatura;

        if (peticioID == null) {
            log.error("No hi ha cap peticio amb portafibID=" + portafibID + ". ", new Exception());
            infoSignatura = null;
        } else {
            infoSignatura = guardarFitxerInfoFirma(peticioID, portafibID, languageUI);
        }

        return infoSignatura;

    }

//    @Override
//    @PermitAll
//    @Asynchronous
//    public void cosesAFerPeticioFirmadaPart2(long portaFIBID, String languageUI, InfoSignatura infoSignatura)
//            throws I18NException {
//
//        Long peticioID = getPeticioIdFromPortafibId(portaFIBID);
//
//        Peticio peticio = this.findByPrimaryKey(peticioID);
//
//        peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
//        this.update(peticio);
//
//        log.info("cosesAFerPeticioFirmada():: Guardam dins arxiu de forma asyncrona .... ");
//
//        guardarFitxerArxiuSync(peticio.getPeticioID(), languageUI, infoSignatura, Configuracio.getUrlBase());
//
//        //guardarFitxerArxiuAsync(peticioID, languageUI, infoSignatura);
//
//        log.info("cosesAFerPeticioFirmada()::  SORTIM !!!!! ");
//    }


    @Override
    public void cosesAFerPeticioFirmadaPart2(long portaFIBID, String languageUI, InfoSignatura infoSignatura)
            throws I18NException {
    
        Long peticioID = getPeticioIdFromPortafibId(portaFIBID);
        String urlBase = Configuracio.getUrlBase();
    
        log.info("cosesAFerPeticioFirmada():: Guardam dins arxiu de forma asyncrona .... ");
        Peticio peticio = findByPrimaryKey(peticioID);

        guardarPeticioArxiu(peticio, languageUI, infoSignatura, urlBase);
    

        if (peticio.getEstat() == Constants.ESTAT_PETICIO_FIRMADA) {
            log.info("S'ha arxivar correctament");
        } else {
            log.error("Error arxivant: " + peticio.getErrorMsg());
        }

        log.info("cosesAFerPeticioFirmada()::  SORTIM !!!!! ");
    }

    @Override
    @PermitAll
    public void cosesAFerPeticioRebutjada(long portafibID, String languageUI, String motiuRebuig) throws I18NException {

        List<Peticio> peticioList = this.select(PeticioFields.PETICIOPORTAFIRMES.equal(String.valueOf(portafibID)));

        if (peticioList == null || peticioList.size() != 1) {
            log.error("Event de REBUIG amb portafibid = " + portafibID + " i Petició no trobada", new Exception());
            return;
            //            throw new I18NException("error.portafib.peticionull", "REBUIG", String.valueOf(portafibID));
        }

        Peticio peticio = peticioList.get(0);
        peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
        peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);

        String msg = I18NCommonUtils.tradueix(new Locale(peticio.getIdiomaID()), "peticio.motiu.rebuig", motiuRebuig);
        peticio.setErrorMsg(LogicUtils.split255(msg));
        this.update(peticio);

        try {
            enviarMailSolicitant(peticio, Configuracio.getUrlBase());
        } catch (Exception e) {
            log.error("Error enviant correu: " + e.getMessage(), e);
        }

    }

    /*
    protected void guardarFitxerArxiu(long peticioID, String languageUI, InfoSignaturaJPA infoSignatura)
            throws I18NException {
    
        Peticio peticio = findByPrimaryKey(peticioID);
        peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
        this.update(peticio);
    
        guardarFitxerArxiuAsync(peticioID, languageUI, infoSignatura);
    
    }
    */

    @Override
    @PermitAll
    @Asynchronous
    public void guardarPeticioArxiu(Peticio peticio, String languageUI, InfoSignatura infoSignatura, String urlBase)
            throws I18NException {

        peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
        this.update(peticio);
        
        long peticioID = peticio.getPeticioID();
        try {
            log.info("Guardant Peticio " + peticioID + " dins d'Arxiu.");
            peticio = guardarFitxerArxiuSync(peticioID, languageUI, infoSignatura, urlBase);
        } catch (Exception e) {
            // XYZ ZZZ TMP
            log.error("Future.get() ha llança un error: " + e.getMessage(), e);
            peticio = null;
        }
    }

    
    @Override
    public String reintentGuardarPeticioArxiu(long peticioID, long infoSignaturaID, String languageUI, String urlBase)
            throws I18NException {

        InfoSignatura infoSignatura = infoSignaturaLogicaEjb.findByPrimaryKey(infoSignaturaID);
        Peticio peticio = findByPrimaryKey(peticioID);

        guardarPeticioArxiu(peticio, languageUI, infoSignatura, urlBase);

        if (peticio.getEstat() == Constants.ESTAT_PETICIO_FIRMADA) {
            return null;
        } else {
            return peticio.getErrorMsg();
        }
    }
    
//    @Override
//    @PermitAll
//    @Asynchronous
//    public String reintentarGuardarFitxerArxiu(long peticioID, String languageUI, String urlBase) throws I18NException {
//
//        {
//            Peticio peticio = findByPrimaryKey(peticioID);
//            peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
//            this.update(peticio);
//        }
//
//        Long infoSignaturaId = this.executeQueryOne(PeticioFields.INFOSIGNATURAID,
//                PeticioFields.PETICIOID.equal(peticioID));
//        InfoSignaturaJPA infoSignatura = infoSignaturaLogicaEjb.findByPrimaryKey(infoSignaturaId);
//
//        Peticio peticio;
//        try {
//            log.info("\n\n Reintentant el guardat de la Petició " + peticioID + " dins d'Arxiu.");
//            peticio = guardarFitxerArxiuSync(peticioID, languageUI, infoSignatura, urlBase);
//        } catch (Exception e) {
//            // XYZ ZZZ TMP
//            log.error("Future.get() ha llança un error: " + e.getMessage(), e);
//            peticio = null;
//        }
//
//        String msg;
//        if (peticio.getEstat() == Constants.ESTAT_PETICIO_FIRMADA) {
//            msg = null;
//        } else {
//            msg = peticio.getErrorMsg();
//        }
//
//        return msg;comp
//    }

    
    @Override
    public void tancarExpedientPeticio(long peticioID, String urlBase) throws I18NException {
        
        Peticio peticio = this.findByPrimaryKey(peticioID);

        String expedientID = this.executeQueryOne(new PeticioQueryPath().INFOARXIU().ARXIUEXPEDIENTID(),
                PETICIOID.equal(peticioID));

        boolean tancatExpedient = this.pluginArxiuLogicaEjb.tancarExpedient(peticio, expedientID);
        this.update(peticio);
        
        if (tancatExpedient) {
            try {
                enviarMailSolicitant(peticio, urlBase);
            } catch (Exception e) {
                log.error("Error enviant correu: " + e.getMessage(), e);
            }
        }
    }    
    
    @Override
    public void reintentarTancarExpedient(long peticioID, String urlBase) throws I18NException {

        log.info("\n\n Reintentant el tancament d'expedient de la Petició " + peticioID + " dins d'Arxiu.");
        tancarExpedientPeticio(peticioID, urlBase); 
    }

    /**
     * 
     * @param peticioID
     * @param languageUI
     * @param infoSignatura
     * @return
     * @throws I18NException
     */
    protected Peticio guardarFitxerArxiuSync(long peticioID, String languageUI, InfoSignatura infoSignatura, String urlBase)
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
            peticio.setEstat(Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT);
            peticio.setErrorMsg(null);
            peticio.setErrorException(null);

            try {
                enviarMailSolicitant(peticio, urlBase);
            } catch (Exception e) {
                log.error("Error enviant correu: " + e.getMessage(), e);
            }
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

    protected boolean esborrarPeticioPortafib(long portafibID, String languageUI) {

        try {
            FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
            rinfo = new FirmaAsyncSimpleSignatureRequestInfo(portafibID, languageUI);

            ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
            
//            rinfo.getSignatureRequestID()
            
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

    protected void enviarMailSolicitant(Peticio peticio, String baseUrl) {

        if (peticio.getTipus() != Constants.TIPUS_PETICIO_AUTOFIRMA) {

            try {
                final Locale loc = new Locale(peticio.getIdiomaID());

                String nomPeticio = peticio.getNom().replace("'", "`");
                long estatPeticio = peticio.getEstat();
                Long solicitantID = peticio.getSolicitantID();

                String subject;
                String message;
                boolean isHTML = true;
                String from = Configuracio.getAppEmail();
                String emailDestinatari = usuariEjb.executeQueryOne(UsuariFields.EMAIL,
                        UsuariFields.USUARIID.equal(solicitantID));

                if ((int) estatPeticio == Constants.ESTAT_PETICIO_FIRMADA) {
                    Map<String, Object> map = new HashMap<String, Object>();

                    map.put("nomFitxer", peticio.getFitxer().getNom());
                    map.put("numeroPeticio", "" + peticio.getPeticioID());
                    map.put("titolPeticio", peticio.getNom());
                    
                    Long infoArxiuID = peticio.getInfoArxiuID();
                    log.info("infoArxiuID :" + infoArxiuID );
                    
                    InfoArxiuJPA ia = infoArxiuLogicEjb.findByPrimaryKey(infoArxiuID );
                    String fileUrl = ia.getCsvValidationWeb() + "view.xhtml?hash=" + ia.getCsv();
                    map.put("fileUrl", fileUrl);
                    
                    subject = I18NCommonUtils.tradueix(loc, "email.peticio.finalitzada.subject");
                    message = I18NCommonUtils.tradueix(loc, "email.peticio.finalitzada.message");

                    subject = TemplateEngine.processExpressionLanguage(subject, map);
                    message = TemplateEngine.processExpressionLanguage(message, map);
                } else {

                    String code = "";
                    switch ((int) estatPeticio) {
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
                        case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT: {
                            code = "email.peticio.body.error.arxivant";
                        }
                        break;
                    }

                    subject = I18NCommonUtils.tradueix(loc, "email.peticio.subject");
                    message = I18NCommonUtils.tradueix(loc, code, nomPeticio, baseUrl);
                }

                EmailUtil.postMail(subject, message, isHTML, from, emailDestinatari);

            } catch (Throwable t) {
                log.error("EJB: Error enviant mail: " + t.getMessage());
            }
        } else {
            log.info("No enviam cap correu quan es autofirma");
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

        this.deleteIncludingFiles(instance, fitxerEjb);
        
        if (instance.getTipus() != Constants.TIPUS_PETICIO_AUTOFIRMA) {
            String portaFIBID = instance.getPeticioPortafirmes();

            long portafibID = Long.parseLong(portaFIBID);
            final String languageUI = "ca";

            if (esborrarPeticioPortafib(portafibID, languageUI)) {
                log.info("Peticio " + portafibID + "esborrada de PORTAFIB correctament");
            } else {
                log.error("No s'ha pogut esborrar la petició amb portafibID=" + portaFIBID);
            }
        } else {
            log.info("La petició " + instance.getPeticioID() + " es AutoFirma i no te res PortaFIB");
        }

        Long infoSignID = instance.getInfoSignaturaID();
        
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
    public List<StringKeyValue> getTipusDocumentals(String lang, boolean obtenerTodos) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        Map<String, String> tipusDocs;

        if (lang.equals("es")) {
            tipusDocs = MAP_TIPUS_DOCUMENTAL_ES;
        } else {
            tipusDocs = MAP_TIPUS_DOCUMENTAL_CA;
        }

        for (Map.Entry<String, String> tipusDoc : tipusDocs.entrySet()) {
            String key = tipusDoc.getKey();
            String val = tipusDoc.getValue();

            Long taulaSeriesOk = serieDocEjb.count(SerieDocumentalFields.TIPUSDOCUMENTAL.equal(key));

            if (obtenerTodos || taulaSeriesOk == 1) {
                StringKeyValue skv = new StringKeyValue(key, val);
                __tmp.add(skv);
            }
        }
        return __tmp;
    }

    @Override
    public void guardarResultatAutofirma(long peticioID, FirmaSimpleSignatureResult fssr) throws I18NException {

        log.info("Autofirma Recuperada Informació de firma: \n"
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

        //guardarFitxerArxiu(peticioID, pet.getIdiomaID(), infoSignatura);

        Peticio peticio = findByPrimaryKey(peticioID);
        peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
        this.update(peticio);

        log.info("guardarResultatAutofirma()::Autofirma => guardar dins Arxiu de forma ASYNC ...");

        guardarFitxerArxiuSync(peticioID, peticio.getIdiomaID(), infoSignatura, Configuracio.getUrlBase());

        log.info("guardarResultatAutofirma()::Autofirma => sortim");

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

    @Override
    public FirmaAsyncSimpleSignatureBlock[] convertFluxToSignatureBlocks(FlowTemplateSimpleFlowTemplate flux)
            throws I18NException {

        List<FlowTemplateSimpleBlock> blocks = flux.getBlocks();

        FirmaAsyncSimpleSignatureBlock[] signatureBlocks;
        signatureBlocks = new FirmaAsyncSimpleSignatureBlock[blocks.size()];

        int count = 0;
        for (FlowTemplateSimpleBlock blockOrigen : blocks) {

            List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
            for (FlowTemplateSimpleSignature signOrigen : blockOrigen.getSignatures()) {

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

    /**
     * 
     * @param nifDestinatari
     * @return
     * @throws I18NException
     */
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

    /**
     * Funció que s'executa cada vespre a les 4:00 i elimina peticions acabades de PortaFIB.
     */
    @TransactionTimeout(value = TRANSACTION_TIMEOUT_IN_SEC)
    @Schedule(hour = "4", persistent = false)
    protected void eliminarPeticionsPortaFIB() {
        log.info("Comença eliminarPeticionsPortaFIB()");

        final long startTime = System.currentTimeMillis();
        final String languageUI = "ca";
        final String prefixeEsborrat = "ESBORRADA%";
        try {
            Where w1 = PeticioFields.TIPUS.notEqual(Constants.TIPUS_PETICIO_AUTOFIRMA);

            Integer[] estats = { Constants.ESTAT_PETICIO_FIRMADA, Constants.ESTAT_PETICIO_ERROR };
            Where w2 = PeticioFields.ESTAT.in(estats);

            Where w3 = PeticioFields.PETICIOPORTAFIRMES.notLike(prefixeEsborrat);

            List<String> listPortaFIBIds = this.executeQuery(PeticioFields.PETICIOPORTAFIRMES, Where.AND(w1, w2, w3));

            for (String portaFIBID : listPortaFIBIds) {
                try {
                    log.info("Esborram Peticio amb PortaFIB ID: " + portaFIBID);

                    long portafibID = Long.parseLong(portaFIBID);

                    if (esborrarPeticioPortafib(portafibID, languageUI)) {
                        //                        Query query = getEntityManager().createQuery("update " + PeticioJPA.class.getSimpleName()
                        //                                + " set " + PeticioFields.PETICIOPORTAFIRMES.javaName + " = ?0" + " where "
                        //                                + PeticioFields.PETICIOPORTAFIRMES.javaName + " = ?1");
                        //                        query.setParameter(0, prefixeEsborrat + portaFIBID);
                        //                        query.setParameter(1, portaFIBID);
                        //                        query.executeUpdate();

                        this.update(PeticioFields.PETICIOPORTAFIRMES, prefixeEsborrat + portaFIBID,
                                PeticioFields.PETICIOPORTAFIRMES.equal(portaFIBID));

                    } else {
                        final String msg = "Error al mètode esborrarPeticioPortafib() amb portafibID=" + portaFIBID
                                + " durant el cron nocturn.";
                        log.error(msg);
                    }

                    //El Timeout son 3 minuts. Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
                    if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
                        log.warn("Timeout.");
                        break;
                    }

                } catch (Throwable e) {
                    final String msg = "Error pasant portafibID=" + portaFIBID
                            + "  a ESBORRADA% durant el cron nocturn: " + e.getMessage();
                    log.error(msg, e);
                }
            }

        } catch (I18NException e) {
            final String msg = "Error obtenint llistat de PortaFibIDs durant el cron nocturn: "
                    + I18NCommonUtils.getMessage(e, new Locale(languageUI));
            log.error(msg, e);
        }

        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime - startTime));
        log.info("Acaba eliminarPeticionsPortaFIB()");
    }

    /**
     * Funció que s'executa cada vespre a les 5:00 i elimina els fitxers fisics i a BBDD de peticions arxiavdes.
     */
    @TransactionTimeout(value = TRANSACTION_TIMEOUT_IN_SEC)
    @Schedule(hour = "5", persistent = false)
    protected void eliminarFitxersSignatsDeLocal() {
        log.info("Comença eliminarFitxersSignatsDeLocal()");

        long startTime = System.currentTimeMillis();
        final String languageUI = "ca";
        try {
            //Llistat de peticions arxivades: 
            List<Long> fitxersFirmatsID = this.executeQuery(PeticioFields.FITXERFIRMATID,
                    Where.AND(PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA),
                            PeticioFields.FITXERFIRMATID.isNotNull()));

            java.util.Set<Long> fitxersEsborrar = new java.util.HashSet<Long>();

            // Borram fitxers a BD
            for (Long fitxerFirmatID : fitxersFirmatsID) {
                try {
                    //                    Query query = getEntityManager().createQuery("update " + PeticioJPA.class.getSimpleName() + " set "
                    //                            + PeticioFields.FITXERFIRMATID.javaName + " = null" + " where "
                    //                            + PeticioFields.FITXERFIRMATID.javaName + " = ?0");
                    //
                    //                    query.setParameter(0, fitxerFirmatID);
                    //                    query.executeUpdate();

                    this.update(PeticioFields.FITXERFIRMATID, null, PeticioFields.FITXERFIRMATID.equal(fitxerFirmatID));

                    log.info("Esborrant fitxer " + fitxerFirmatID + " a BBDD");
                    fitxerEjb.delete(fitxerFirmatID);
                    fitxersEsborrar.add(fitxerFirmatID);

                    //El Timeout son 3 minuts. Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
                    if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
                        log.warn("Timeout.");
                        break;
                    }

                } catch (Throwable t) {
                    final String msg = "Error esborrant el fitxer firmat amb ID = " + fitxerFirmatID + " : "
                            + t.getMessage();
                    log.error(msg, t);
                }
            }

            // XYZ ZZZ TRA Debug a partir de setembre
            if (fitxersEsborrar.size() == 0) {
                log.info("No hi ha fitxers per esborrar!");
            } else {
                for (Long fitxerID : fitxersEsborrar) {
                    log.info("Fitxer " + fitxerID + " per esborrar fisic");
                }
            }

            // Borram fitxers fisic
            __tsRegistry.registerInterposedSynchronization(
                    new es.caib.enviafib.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));

        } catch (I18NException e) {

            final String msg = "Error obtenint llistat de fitxersFirmatsID durant el cron nocturn: "
                    + I18NCommonUtils.getMessage(e, new Locale(languageUI));
            log.error(msg, e);
        }

        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime - startTime));
        log.info("Acaba eliminarFitxersSignatsDeLocal()");
    }

    
    final String HORA_TANCAR_EXPEDIENTS = Configuracio.getHoraTancamentExpedientsScheduler();
    
    /**
     * Funció que s'executa cada vespre a les 12:00 i tanca tots els expedients oberts.
     */
    
    
    @Resource
    private TimerService ejbTimerService;

    @Override
    public void initScheduler() {
        ScheduleExpression schedule = new ScheduleExpression();
        
        String hora, h, m;
        try {
            hora = Configuracio.getHoraTancamentExpedientsScheduler();
            h = hora.split(":")[0];
            m = hora.split(":")[1];

            if (h == null || h.trim().length() == 0) {
                h = "4";
            }
            if (m == null || m.trim().length() == 0) {
                m = "30";
            }
        } catch (Throwable t) {
            h = "4";
            m = "30";
        }
        schedule.hour(h);
        schedule.minute(m);

        Timer timer = ejbTimerService.createCalendarTimer(schedule);

        log.info("Calendar based programmatic timer " + timer.toString() + " created");
    }

    @Timeout
    public void onTimeout(Timer timer) {
        log.info("Comença tancarTotsElsExpedients()");

        long startTime = System.currentTimeMillis();
        final String languageUI = "ca";

        Integer[] estatsPendents = { Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT,
                Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT };

        try {
            //Llistat de peticions pendents de tancar expedient: 
            List<Peticio> peticions = this.select(PeticioFields.ESTAT.in(estatsPendents));

            for (Peticio peticio : peticions) {
                Long peticioID = peticio.getPeticioID();

                String expedientID = this.executeQueryOne(new PeticioQueryPath().INFOARXIU().ARXIUEXPEDIENTID(),
                        PETICIOID.equal(peticioID));

                boolean tancatExpedient = this.pluginArxiuLogicaEjb.tancarExpedient(peticio, expedientID);
                this.update(peticio);

                if (tancatExpedient) {
                    log.info("Expedient de la petició " + peticioID + " tancat correctament");
                } else {
                    log.error("Error tancant expedient de la petició " + peticioID + ": " + peticio.getErrorMsg());

                }

                //El Timeout son 3 minuts. Si el CRON s'executa durant 2 min, surt del for i acaba la funció.
                if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
                    log.warn("Timeout.");
                    break;
                }
            }
        } catch (I18NException e) {

            final String msg = "Error obtenint llistat de fitxersFirmatsID durant el cron nocturn: "
                    + I18NCommonUtils.getMessage(e, new Locale(languageUI));
            log.error(msg, e);
        }

        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime - startTime));
        log.info("Acaba tancarTotsElsExpedients()");
    }
    
    /**
     * 
     * @param peticioPortaFIB
     * @param languageUI
     * @return
     * @throws I18NException
     */
    @Override
    public String getUrlToViewFlow(long peticioPortaFIB, String languageUI) throws I18NException {

        try {
            ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
            FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
//            log.info("peticioPortaFIB: " + peticioPortaFIB);
//            log.info("languageUI: " + languageUI);
            rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioPortaFIB, languageUI);
            String url = api.getUrlToViewFlow(rinfo);
            return url;

        } catch (I18NException e) {
            log.error("error obtenint getUrlToViewFlow: " + e);
            throw e;
        } catch (Exception e) {
            // XYZ ZZZ TRA
            String msg = "Error desconegut intentant obtenir una adreça per visualitzar l'estat del flux de firmes: "  + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }
    }

}
