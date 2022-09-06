package es.caib.enviafib.logic;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.Metadata;
import org.fundaciobit.pluginsib.core.utils.MetadataConstants;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.I18NLogicUtils;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.plugins.arxiu.api.ArxiuException;
import es.caib.plugins.arxiu.api.ArxiuNotFoundException;
import es.caib.plugins.arxiu.api.ConsultaFiltre;
import es.caib.plugins.arxiu.api.ConsultaOperacio;
import es.caib.plugins.arxiu.api.ConsultaResultat;
import es.caib.plugins.arxiu.api.ContingutArxiu;
import es.caib.plugins.arxiu.api.ContingutOrigen;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.DocumentContingut;
import es.caib.plugins.arxiu.api.DocumentEstat;
import es.caib.plugins.arxiu.api.DocumentEstatElaboracio;
import es.caib.plugins.arxiu.api.DocumentExtensio;
import es.caib.plugins.arxiu.api.DocumentFormat;
import es.caib.plugins.arxiu.api.DocumentMetadades;
import es.caib.plugins.arxiu.api.DocumentTipus;
import es.caib.plugins.arxiu.api.Expedient;
import es.caib.plugins.arxiu.api.ExpedientMetadades;
import es.caib.plugins.arxiu.api.Firma;
import es.caib.plugins.arxiu.api.FirmaPerfil;
import es.caib.plugins.arxiu.api.FirmaTipus;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * 
 * @author anadal
 *
 */
@Stateless(name = "PluginArxiuLogicaEJB")
public class PluginArxiuLogicaEJB extends AbstractPluginLogicaEJB<IArxiuPlugin> implements PluginArxiuLogicaService {

    @EJB(mappedName = InfoArxiuLogicaService.JNDI_NAME)
    protected InfoArxiuLogicaService infoArxiuEjb;

    @EJB(mappedName = PeticioLogicaService.JNDI_NAME)
    protected PeticioLogicaService peticioLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @Override
    protected int getTipusDePlugin() {
        return Constants.TIPUS_PLUGIN_ARXIU;
    }

    @Override
    protected String getName() {
        return "Arxiu";
    }

    @PermitAll
    @Override
    public InfoArxiuJPA custodiaAmbApiArxiu(Peticio peticio, Locale locale, InfoSignaturaJPA infoSignatura) {

        IArxiuPlugin plugin;

        try {
            plugin = getInstance();

        } catch (I18NException e1) {

            final String msg = I18NLogicUtils.tradueix(locale, "error.plugin.instance",
                    I18NLogicUtils.getMessage(e1, locale));

            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR_ARXIVANT);
            peticio.setErrorMsg(LogicUtils.split255(msg));
            peticio.setErrorException(LogicUtils.stackTrace2String(e1));

            return null;
        }

        String expedientId = null;
        InfoArxiuJPA infoCust = null;

        try {

            // ============ CALCULATS
            // ----- Format i Extensio
            DocumentFormat documentFormat;
            DocumentExtensio documentExtensio;
            documentFormat = DocumentFormat.PDF;
            documentExtensio = DocumentExtensio.PDF;

            // XYZ ZZZ S'ha de controlar el format del document
            /*
             * { switch (peticio.getPerfil().getScanFormatFitxer()) {
             * 
             * case Constants.FORMAT_FILE_PDF: documentFormat = DocumentFormat.PDF;
             * documentExtensio = DocumentExtensio.PDF; break;
             * 
             * case Constants.FORMAT_FILE_JPG: documentFormat = DocumentFormat.JPEG;
             * documentExtensio = DocumentExtensio.JPEG; break;
             * 
             * case Constants.FORMAT_FILE_TIFF: documentFormat = DocumentFormat.TIFF;
             * documentExtensio = DocumentExtensio.TIFF; break;
             * 
             * case Constants.FORMAT_FILE_PNG: documentFormat = DocumentFormat.PNG;
             * documentExtensio = DocumentExtensio.PNG; break;
             * 
             * case Constants.FORMAT_FILE_GIF: // XYZ ZZZ final String msg1 =
             * "XYZ ZZZ Format de Fitxer GIF no està suportat per l'ARXIU ";
             * 
             * transaccio.setEstatCodi(ScanWebSimpleStatus.STATUS_FINAL_ERROR);
             * transaccio.setEstatMissatge(msg1);
             * 
             * return null;
             * 
             * default: final String msg =
             * "XYZ ZZZ identificador de Format de Fitxer escanejat desconegut (" +
             * transaccio.getPerfil().getScanFormatFitxer() + ").";
             * transaccio.setEstatCodi(ScanWebSimpleStatus.STATUS_FINAL_ERROR);
             * transaccio.setEstatMissatge(msg); return null; }
             */

            // String procedimentNom = peticio.getArxiuOptParamProcedimentNom(); //
            // "Subvenciones
            String procedimentCodi = peticio.getArxiuOptParamProcedimentCodi();

            // No hauria de ser null
            List<String> organs;
            {
                final String organsStr = peticio.getArxiuReqParamOrgans(); // "A04013511";

                if (organsStr == null) {
                    organs = null;
                } else if (organsStr.trim().length() == 0) {
                    organs = null;
                } else {
                    List<String> tmp = LogicUtils.stringToListString(organsStr);
                    organs = new ArrayList<String>();
                    for (String organ : tmp) {
                        if (organ.trim().length() != 0) {
                            organs.add(organ);
                        }
                    }
                    if (organs.size() == 0) {
                        organs = null;
                    }
                }
            }

            if (peticio.getArxiuParamFuncionariDir3() != null) {
                if (organs == null) {
                    organs = new ArrayList<String>();
                }
                organs.add(peticio.getArxiuParamFuncionariDir3());
            }

            if (organs == null || organs.size() == 0) {
                log.error("\n\n ================ ORGANS VAL NULL ====================\n\n");
            }

            String serieDocumental = peticio.getArxiuOptParamSerieDocumental(); // "S0001";

            // XYZ ZZZ TRA - ISSUE
            // TODO: Fer un tiquet per posar-ho en una propietat del PLugin ????

            // Això és per quan l'usuari pugui indicar el nom de l'expedient on vol
            // el document String custodyOrExpedientID = prop
            // .getProperty(TransaccioFields.ARXIUOPTPARAMCUSTODYOREXPEDIENTID.javaName);
            final String interessatsStr = peticio.getArxiuReqParamInteressats();

            final String nomExpedient = "EnviaFIB_" + peticio.getPeticioID() + "_EXP";

            ExpedientMetadades expedientMetadades = new ExpedientMetadades();
            expedientMetadades.setClassificacio(procedimentCodi);
            expedientMetadades.setDataObertura(new Date());

            expedientMetadades.setInteressats(LogicUtils.stringToListString(interessatsStr));
            expedientMetadades.setMetadadesAddicionals(null);

            expedientMetadades.setOrgans(organs);
            expedientMetadades.setSerieDocumental(serieDocumental);
            // expedientMetadades.setVersioNti(versioNti);

            Expedient expedient = new Expedient();
            expedient.setNom(nomExpedient);

            expedient.setMetadades(expedientMetadades);

            if (peticio.getArxiuOptParamExpedientId() != null) {
                // XYZ ZZZ TRA
                // TODO: Falta implementar que s'hagi definit un EXPEDIENT
                log.error(
                        "\n\n Falta implementar que s'hagi definit un EXPEDIENT (no s'hauria de fer creacio d'expedient) \n\n",
                        new Exception());
            }

            log.info("XYZ ZZZ TMP Creant expedient... ");

            // ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);
            ContingutArxiu expedientCreat;

            try {
                expedientCreat = plugin.expedientCrear(expedient);
                expedientId = expedientCreat.getIdentificador();
                log.info("XYZ ZZZ TMP Creat expedient amd ID = " + expedientId);
            } catch (Throwable th) {

                log.error(
                        " Error Creant Expedient: " + th.getMessage() + ".Consultam si l'expedient ja està creat ...");

                // Comprovar si l'expedient ja existeix
                ConsultaResultat resultat;
                resultat = plugin.expedientConsulta(getLlistaFiltresExpedienteMetadatos(nomExpedient, serieDocumental),
                        0, 111);

                if (resultat.getResultats() != null && resultat.getResultats().size() != 0) {

                    for (ContingutArxiu ca : resultat.getResultats()) {

                        if (nomExpedient.equals(ca.getNom())) {
                            expedientId = ca.getIdentificador();
                            log.info("XYZ ZZZ TMP Expedient ja existia (ID = " + expedientId + ")");
                        }
                    }
                }

                if (expedientId == null) {
                    log.error("No hem trobat expedient amb nom " + nomExpedient + ". Llançan excepció original.");
                    throw th;
                }

            }

            log.info("XYZ ZZZ TMP Creant document ... ");
            final DocumentMetadades documentMetadades = new DocumentMetadades();

            documentMetadades.setOrgans(organs);
            documentMetadades.setDataCaptura(new Date());

            String elabora = peticio.getArxiuReqParamDocEstatElabora();

            documentMetadades.setEstatElaboracio(DocumentEstatElaboracio.toEnum(elabora));
            documentMetadades.setTipusDocumental(getDocumentTipusEnum(peticio.getTipusDocumental()));
            documentMetadades.setFormat(documentFormat);
            documentMetadades.setExtensio(documentExtensio);

            final ContingutOrigen origen;
            if (elabora.equals("EE02") || elabora.equals("EE04")) {
                origen = null;
            } else {
                origen = (peticio.getArxiuReqParamOrigen() == Constants.ORIGEN_ADMINISTRACIO)
                        ? ContingutOrigen.ADMINISTRACIO
                        : ContingutOrigen.CIUTADA;

            }
            documentMetadades.setOrigen(origen);

            // ================== METADADES ==================

            // Si posam alguna cosa llavors peta [HTTP_500, COD_021] nodeId is not valid
            // (unknown)
            // El plugin internament ja actualitza aquesta dada
            java.lang.String csvGenerationDefinition = null;

            Map<String, Object> metadadesAddicionals = new HashMap<String, Object>();

            {
                List<Metadata> metadades = generaMetadades(peticio, csvGenerationDefinition, log);
                if (metadades != null && metadades.size() != 0) {
                    for (Metadata metadata : metadades) {
                        metadadesAddicionals.put(metadata.getKey(), metadata.getValue());
                    }
                }
            }

            documentMetadades.setMetadadesAddicionals(metadadesAddicionals);

            final FirmaTipus firmaTipus;
            final FirmaPerfil firmaPerfil;

            final String commonError = I18NLogicUtils.tradueix(locale, "procesfirma.validacio");

            if (infoSignatura == null) {
                firmaTipus = null;
                firmaPerfil = null;
                throw new I18NException("infosignatura.notfound", commonError);
            } else {

                /*
                 * CSV("TF01"), XADES_DET("TF02"), XADES_ENV("TF03"), CADES_DET("TF04"),
                 * CADES_ATT("TF05"), PADES("TF06"), SMIME("TF07"), ODT("TF08"), OOXML("TF09");
                 */
                firmaTipus = FirmaTipus.toEnum(infoSignatura.getEniTipoFirma());

                firmaPerfil = firmaPerfilToEnum(infoSignatura.getEniPerfilFirma());

            }

            if (firmaTipus == null) {
                String msg = "FirmaTipus val null (infoSignatura.getEniTipoFirma() == "
                        + infoSignatura.getEniTipoFirma() + " )" + commonError;
                log.error(msg, new Exception());

                throw new I18NException("firmatipus.isnull", infoSignatura.getEniTipoFirma(), commonError);
            }

            if (firmaPerfil == null) {
                String msg = "FirmaPerfil val null (infoSignatura.getEniPerfilFirma() == "
                        + infoSignatura.getEniPerfilFirma() + " )" + commonError;
                log.error(msg, new Exception());

                throw new I18NException("firmaperfil.isnull", infoSignatura.getEniPerfilFirma(), commonError);
            }

            final boolean esDetached;
            esDetached = (infoSignatura.getSignMode() == FirmaSimpleSignedFileInfo.SIGN_MODE_EXPLICIT_DETACHED);

            DocumentContingut documentContingut;
            if (esDetached) {

                // ES DETACHED

                // FITXER PLA

                FitxerJPA fitxerEscanejat = peticio.getFitxer();
                byte[] plainData = FileSystemManager.getFileContent(fitxerEscanejat.getFitxerID());

                documentContingut = new DocumentContingut();
                documentContingut.setArxiuNom(fitxerEscanejat.getNom());
                documentContingut.setContingut(plainData);
                documentContingut.setTamany(plainData.length);
                documentContingut.setTipusMime(fitxerEscanejat.getMime());

                // nomDocument = fitxerEscanejat.getNom();

            } else {
                // nomDocument = null;
                documentContingut = null;
            }

            // FITXER SIGNAT
            long fitxerFirmatID = peticio.getFitxerFirmatID();

            Fitxer fitxerFirmat = fitxerEjb.findByPrimaryKey(fitxerFirmatID);

            byte[] signedData = FileSystemManager.getFileContent(fitxerFirmat.getFitxerID());

            // final String signatureType = infoSignatura.getSignType();
            String nomDocument = fitxerFirmat.getNom();
            Firma firma;
            firma = new Firma();
            firma.setContingut(signedData);
            // firma.setCsvRegulacio(csvRegulacio);
            firma.setFitxerNom(nomDocument);

            firma.setTipus(firmaTipus);
            firma.setPerfil(firmaPerfil);

            firma.setTamany(signedData.length);

            firma.setTipusMime(fitxerFirmat.getMime());

            final Document documentPerCrear = new Document();
            documentPerCrear.setContingut(documentContingut);
            documentPerCrear.setEstat(DocumentEstat.DEFINITIU);

            documentPerCrear.setFirmes(Arrays.asList(firma));

            // documentPerCrear.setIdentificador(identificador);

            documentPerCrear.setMetadades(documentMetadades);

            documentPerCrear.setNom(nomDocument);

            log.info("XYZ ZZZ TMP Creant document ... ");

            ContingutArxiu documentCreat = plugin.documentCrear(documentPerCrear, expedientId);

            log.info("XYZ ZZZ TMP Creat document ... ");

            log.info("XYZ ZZZ TMP Tancar Expedient ... ");

            final String uuidDoc = documentCreat.getIdentificador();

            try {
                // if (true) {
                // throw new Exception("Error desconegut tancant Expedient !!!!!");
                // }

                plugin.expedientTancar(expedientId);
                log.info("XYZ ZZZ TMP Tancat Expedient ... ");
            } catch (Throwable th) {
                // XYZ ZZZ TMP
                log.error("Error tancant Expedient " + expedientId + ": " + th.getMessage(), th);
                peticio.setEstat(Constants.ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT);
                throw th;
            }


            log.info("\n FINAL \n");

            infoCust = null;
            // Hi ha un error "Contingut no trobat" que és "fals", per això hem de
            // reintentar
            int i = 0;
            do {
                try {
                    final String originalFileUrl = plugin.getOriginalFileUrl(documentCreat);
                    final String printableFileUrl = plugin.getPrintableFileUrl(documentCreat);
                    final String eniFileUrl = plugin.getEniFileUrl(documentCreat);

                    // String csv = plugin.getCsv(uuidDoc);
                    final String csv = documentCreat.getDocumentMetadades().getCsv();

                    final String csvValidationWeb = plugin.getCsvValidationWeb(documentCreat);

                    final String validationFileUrl = plugin.getValidationFileUrl(documentCreat);

                    // infoCust = new InfoCustodyJPA(custodyID, expedientId, uuidDoc, csv,
                    // originalFileUrl,
                    // csvValidationWeb, csvGenerationDefinition, printableFileUrl, eniFileUrl,
                    // validationFileUrl);

                    infoCust = new InfoArxiuJPA(originalFileUrl, csv, csvGenerationDefinition, csvValidationWeb,
                            expedientId, uuidDoc, printableFileUrl, eniFileUrl, validationFileUrl);
                    break;
                } catch (ArxiuNotFoundException anfe) {
                    if (i > 2) {
                        throw anfe;
                    }
                } catch (ArxiuException ae) {
                    if (i > 2) {
                        throw ae;
                    }
                }
                i++;
            } while (i < 4);

            infoCust = (InfoArxiuJPA) infoArxiuEjb.createPublic(infoCust);

            peticio.setInfoArxiuID(infoCust.getInfoArxiuID());

            log.info("XYZ ZZZ  Guardada Informació Document Arxivat ... ");

        } catch (Throwable e) {
            final String msg;

            // ZYZ ZZZ TRAD - DONE
            if (e instanceof I18NException) {
                msg = I18NLogicUtils.tradueix(locale, "error.custodiant.fitxer.firmat", "I18NException",
                        I18NLogicUtils.getMessage((I18NException) e, locale));
            } else if (e instanceof ArxiuException) {
                msg = I18NLogicUtils.tradueix(locale, "error.custodiant.fitxer.firmat", "ArxiuException",
                        e.getMessage());
            } else {
                msg = I18NLogicUtils.tradueix(locale, "error.custodiant.fitxer.firmat", e.getClass().toString(),
                        e.getMessage());
            }

            log.error("Error intenant enviar a API d'Arxiu: " + msg, e);

            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR_ARXIVANT);
            peticio.setErrorMsg(LogicUtils.split255(msg));
            peticio.setErrorException(LogicUtils.stackTrace2String(e));

            // Cridades de Plugin
            // pluginCridada.postCridadaError(monitorIntegracions, msg + "\n\n" +
            // peticio.getEstatExcepcio());

            return null; // Indicam un error
        }

        boolean tancatExpedient;

        tancatExpedient = tancarExpedient(peticio, plugin, expedientId);

        if (!tancatExpedient) {
            return null;
        }

        // XYZ Cridades de Plugin: No esta implementat?
        // peticioLogicaEjb.postCridadaOK(monitor, "expedientID=" + expedientId +
        // "\nDocumentID=" + uuidDoc);

        log.info("\n FINAL \n");

        return infoCust;
    }

    @Override
    public boolean tancarExpedient(Peticio peticio, String expedientID) {

        IArxiuPlugin plugin;

        try {
            plugin = getInstance();
        } catch (I18NException e1) {

            // XYZ ZZZ
            Locale locale = new Locale("ca");
            final String msg = "XYZ ZZZ Error Instanciant Plugins de Arxiu: " + I18NLogicUtils.getMessage(e1, locale);

            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT);
            peticio.setErrorMsg(LogicUtils.split255(msg));
            peticio.setErrorException(LogicUtils.stackTrace2String(e1));

            return false;
        }

        return tancarExpedient(peticio, plugin, expedientID);

    }

    protected boolean tancarExpedient(Peticio peticio, IArxiuPlugin plugin, String expedientId) {
        boolean tancatExpedient;
        log.info("XYZ ZZZ  Tancant Expedient ... ");
        // S'utilitza per gestionar quan l'expedient no s'ha pogut tancar.

        try {
            // if (true) {
            // throw new Exception("Error desconegut tancant Expedient !!!!!");
            // }

            plugin.expedientTancar(expedientId);
            log.info("XYZ ZZZ  Tancat Expedient ... ");

            tancatExpedient = true;

        } catch (Throwable th) {

            final String msg = "Error Tancant Expedient " + expedientId + ": " + th.getMessage();
            log.error(msg, th);

            peticio.setErrorException(LogicUtils.stackTrace2String(th));
            peticio.setErrorMsg(LogicUtils.split255(msg));
            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT);
            tancatExpedient = false; // Indicam un error
        }
        return tancatExpedient;
    }

    protected DocumentTipus getDocumentTipusEnum(String tipusDocumental) {

        switch (tipusDocumental) {

            case "1":
                return DocumentTipus.RESOLUCIO;
            case "2":
                return DocumentTipus.ACORD;
            case "3":
                return DocumentTipus.CONTRACTE;
            case "4":
                return DocumentTipus.CONVENI;
            case "5":
                return DocumentTipus.DECLARACIO;
            case "6":
                return DocumentTipus.COMUNICACIO;
            case "7":
                return DocumentTipus.NOTIFICACIO;
            case "8":
                return DocumentTipus.PUBLICACIO;
            case "9":
                return DocumentTipus.JUSTIFICANT_RECEPCIO;
            case "10":
                return DocumentTipus.ACTA;
            case "11":
                return DocumentTipus.CERTIFICAT;
            case "12":
                return DocumentTipus.DILIGENCIA;
            case "13":
                return DocumentTipus.INFORME;
            case "14":
                return DocumentTipus.SOLICITUD;
            case "15":
                return DocumentTipus.DENUNCIA;
            case "16":
                return DocumentTipus.ALEGACIO;
            case "17":
                return DocumentTipus.RECURS;
            case "18":
                return DocumentTipus.COMUNICACIO_CIUTADA;
            case "19":
                return DocumentTipus.FACTURA;
            case "20":
                return DocumentTipus.ALTRES_INCAUTATS;
            case "51":
                return DocumentTipus.LLEI;
            case "52":
                return DocumentTipus.MOCIO;
            case "53":
                return DocumentTipus.INSTRUCCIO;
            case "54":
                return DocumentTipus.CONVOCATORIA;
            case "55":
                return DocumentTipus.ORDRE_DIA;
            case "56":
                return DocumentTipus.INFORME_PONENCIA;
            case "57":
                return DocumentTipus.DICTAMEN_COMISSIO;
            case "58":
                return DocumentTipus.INICIATIVA_LEGISLATIVA;
            case "59":
                return DocumentTipus.PREGUNTA;
            case "60":
                return DocumentTipus.INTERPELACIO;
            case "61":
                return DocumentTipus.RESPOSTA;
            case "62":
                return DocumentTipus.PROPOSICIO_NO_LLEI;
            case "64":
                return DocumentTipus.PROPOSTA_RESOLUCIO;
            case "65":
                return DocumentTipus.COMPAREIXENSA;
            case "66":
                return DocumentTipus.SOLICITUD_INFORMACIO;
            case "67":
                return DocumentTipus.ESCRIT;
            case "68":
                return DocumentTipus.INICIATIVA_LEGISLATIVA;
            case "69":
                return DocumentTipus.PETICIO;

            case "99":
            default:
                return DocumentTipus.ALTRES; // DocumentTipus.Otros tipus de documentos;

        }
    }

    protected FirmaPerfil firmaPerfilToEnum(String perfil) throws I18NException {

        if (perfil == null || perfil.trim().length() == 0) {
            log.warn("Perfil de Firma val null. Retornam BES.");
            return FirmaPerfil.BES;
        }

        perfil = perfil.trim();

        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_BES)) {
            return FirmaPerfil.BES;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_EPES)) {
            return FirmaPerfil.EPES;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_T)) {
            return FirmaPerfil.T;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_C)) {
            return FirmaPerfil.C;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_X)) {
            return FirmaPerfil.X;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_X1)) {
            return FirmaPerfil.X;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_X2)) {
            return FirmaPerfil.X;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_XL)) {
            return FirmaPerfil.XL;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_XL1)) {
            return FirmaPerfil.XL;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_XL2)) {
            return FirmaPerfil.XL;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_A)) {
            return FirmaPerfil.A;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_PADES_LTV)) {
            return FirmaPerfil.LTV;
        }
        if (perfil.equals(FirmaSimpleSignedFileInfo.SIGNPROFILE_PADES_BASIC)) {
            return FirmaPerfil.BASIC; // o FirmaPerfil.Basic;
        } else {
            // Cercar traducció
            throw new I18NException("genapp.comodi", "S'ha rebut un perfil de firma de l'acció de firma ]" + perfil
                    + "[ però no s'ha trobat l'equivalent en FirmaPerfil de l'API d'arxiu");
        }

    }

//    protected IArxiuPlugin getInstancePluginArxiu() throws I18NException {
//
//        Long pluginID = this.executeQueryOne(PluginFields.PLUGINID,
//                PluginFields.ACTIU.equal(true));
//        
//        if (pluginID != null) {
//            IArxiuPlugin plugin = getInstanceByPluginID(pluginID);
//            return plugin;
//        }
//        return null;
//    }

    private static List<Metadata> generaMetadades(Peticio peticio, String csvGenerationDefinition, Logger log) {
        List<Metadata> metadadesAddicionals = new ArrayList<Metadata>();

        // Resolució
        /*
         * Integer resolucio = peticio.getInfoScanResolucioPpp(); if (resolucio != null)
         * { //log.info("\n\n  RESOLUCIO: " + resolucio ); metadadesAddicionals.add(new
         * Metadata("eni:resolucion", resolucio)); }
         */
        // Idioma del Document
        String languageDoc = peticio.getIdiomaDoc();
        if (languageDoc != null) {
            // log.info("\n\n LANGUAGEDOC: " + languageDoc );
            metadadesAddicionals.add(new Metadata("eni:idioma", languageDoc));
        }
        // Profunditat de Color
        /*
         * Integer profundidad_color = peticio.getInfoScanPixelType(); if
         * (profundidad_color != null) { metadadesAddicionals.add(new
         * Metadata("eni:profundidad_color", profundidad_color)); }
         */

        // Identificador único del procedimiento administrativo con el que se relaciona
        // el expediente.
        {
            String procCodi = peticio.getArxiuOptParamProcedimentCodi();
            if (procCodi != null && procCodi.trim().length() != 0) {
                metadadesAddicionals.add(new Metadata("eni:id_tramite", procCodi));
            }
        }

        metadadesAddicionals
                .add(new Metadata("eni:tamano_logico", FileSystemManager.getFile(peticio.getFitxerID()).length()));

        metadadesAddicionals.add(new Metadata("eni:unidades", "bytes"));

        // Referencia a la disposición normativa que define la creación y uso del CSV
        // correspondiente.
        if (csvGenerationDefinition != null && csvGenerationDefinition.trim().length() != 0) {
            metadadesAddicionals.add(new Metadata("eni:def_csv", csvGenerationDefinition));
        }

        String estatElabora = peticio.getArxiuReqParamDocEstatElabora();

        if (estatElabora != null && (
        // EE02
        MetadataConstants.EstadoElaboracionConstants.ESTADO_ELABORACION_COPIA_CF.equals(estatElabora)
                // EE03
                || MetadataConstants.EstadoElaboracionConstants.ESTADO_ELABORACION_COPIA_DP.equals(estatElabora)
                // EE04
                || MetadataConstants.EstadoElaboracionConstants.ESTADO_ELABORACION_COPIA_PR.equals(estatElabora))) {

            // ES_<Órgano>_<AAAA>_<ID_específico> ==> "ES_3456789_2020_ES"
            // No hauria de ser null
            List<String> organs;
            String firstOrgan;
            {
                final String organsStr = peticio.getArxiuReqParamOrgans(); // "A04013511";

                if (organsStr == null) {
                    organs = null;
                } else if (organsStr.trim().length() == 0) {
                    organs = null;
                } else {
                    List<String> tmp = LogicUtils.stringToListString(organsStr);
                    organs = new ArrayList<String>();
                    for (String organ : tmp) {
                        if (organ.trim().length() != 0) {
                            organs.add(organ);
                        }
                    }
                    if (organs.size() == 0) {
                        organs = null;
                    }
                }
                firstOrgan = (organs == null) ? null : organs.get(0);
            }

            if (firstOrgan != null) {
                String idOrigen = "ES_" + firstOrgan + "_" + Calendar.getInstance().get(Calendar.YEAR) + "_"
                        + Constants.PREFIX + peticio.getPeticioID();
                metadadesAddicionals.add(new Metadata("eni:id_origen", idOrigen));
            }
        }

        return metadadesAddicionals;
    }

    private static List<ConsultaFiltre> getLlistaFiltresExpedienteMetadatos(String expedientNom,
            String serieDocumental) {
        List<ConsultaFiltre> listaFiltros = new ArrayList<>();
        ConsultaFiltre filtro = null;

        /*
         * filtro = new ConsultaFiltre(); filtro.setMetadada("eni:organo");
         * filtro.setOperacio(ConsultaOperacio.IGUAL);
         * filtro.setValorOperacio1("A04019281"); listaFiltros.add(filtro);
         */

        filtro = new ConsultaFiltre();
        filtro.setMetadada("name");
        filtro.setOperacio(ConsultaOperacio.IGUAL);
        filtro.setValorOperacio1(expedientNom);
        listaFiltros.add(filtro);

        filtro = new ConsultaFiltre();
        filtro.setMetadada("eni:cod_clasificacion");
        filtro.setOperacio(ConsultaOperacio.IGUAL);
        filtro.setValorOperacio1(serieDocumental);
        listaFiltros.add(filtro);
        /*
         * filtro = new ConsultaFiltre(); filtro.setMetadada("eni:fecha_inicio");
         * filtro.setOperacio(ConsultaOperacio.ENTRE);
         * filtro.setValorOperacio1(getStringDatetoStringISO8601("01/10/2021"));
         * filtro.setValorOperacio2(getStringDatetoStringISO8601("30/11/2021"));
         * listaFiltros.add(filtro);
         */
        return listaFiltros;
    }

    /*
     * public void crearExpedient() {
     * 
     * 
     * 
     * MetadatosExpediente.CODIGO_APLICACION_TRAMITE, aplicacioCodi);
     * 
     * 
     * //ExpedientMetadades
     * 
     * // Només miram si existeix l'expedient Expediente expedientCercat = null;
     * 
     * if (isSearchIfExpedientExistsInReserve()) {
     * 
     * FiltroBusquedaFacilExpedientes filtrosRequeridos = new
     * FiltroBusquedaFacilExpedientes(); filtrosRequeridos.setName(nomExpedient);
     * filtrosRequeridos.setAppName(getPropertyCodiAplicacio()); String
     * serieDocumental = processEL(getPropertySerieDocumentalEL(),
     * custodyParameters); filtrosRequeridos.setDocSeries(serieDocumental);
     * 
     * if (debug) { log.info(" CERCA[Name] => " + filtrosRequeridos.getName() );
     * log.info(" CERCA[AppName] => " + filtrosRequeridos.getAppName() );
     * log.info(" CERCA[serieDocumental] => " + filtrosRequeridos.getDocSeries()); }
     * 
     * ResultadoBusqueda<Expediente> res; res =
     * api.busquedaFacilExpedientes(filtrosRequeridos, null, 0); if
     * (hiHaErrorEnCerca(res.getCodigoResultado())) { throw new
     * CustodyException("Error Consultant si Expedient " + nomExpedient +
     * " existeix: " + res.getCodigoResultado() + "-" + res.getMsjResultado()); }
     * 
     * List<Expediente> llista2 = res.getListaResultado();
     * 
     * if (llista2 == null || llista2.size() == 0) {
     * log.info(" CERCA[].size() = Llista null o buida (" + llista2.size() + ")");
     * expedientCercat = null; } else { log.info(" CERCA[].size() = " +
     * llista2.size()); // TODO la cerca es fa del nom parescut al fitxer, per
     * exemple // si cerques "Registre_20" et pot trobar Registre_20, //
     * Registre_200, Registre_202, ... int countTrobats = 0; final int total =
     * res.getNumeroTotalResultados(); int parcial = 0; int pagina = 0; do { for
     * (Expediente expediente : llista2) { parcial++; if
     * (nomExpedient.equals(expediente.getName())) { countTrobats++; if
     * (countTrobats > 1) { log.error(" S'ha trobat coincidencia multiple " +
     * expediente.getName() + " (" + expediente.getId() +
     * ") per la cerca de nomExpedient " + nomExpedient + ")"); } else {
     * expedientCercat = expediente; } } }
     * 
     * if (countTrobats != 0) { break; }
     * 
     * if (parcial <= total) { break; } pagina++; res =
     * api.busquedaFacilExpedientes(filtrosRequeridos, null, pagina); if
     * (hiHaErrorEnCerca(res.getCodigoResultado())) { throw new
     * CustodyException("Error Consultant si Expedient " + nomExpedient +
     * " existeix: " + res.getCodigoResultado() + "-" + res.getMsjResultado()); }
     * 
     * llista2 = res.getListaResultado(); } while(true);
     * 
     * if (countTrobats == 0) { expedientCercat = null; } else if (countTrobats ==
     * 1) { // expedientCercat ja conté el valor } else if (countTrobats > 1) { //
     * Hi ha multiple instancies que s'ajusten. No se quin triar String msg =
     * "S'ha trobat coincidencia multiple " + expedientCercat.getName() + " (" +
     * expedientCercat.getId() + ") per la cerca de nomExpedient " + nomExpedient +
     * "). Veure logs per la resta de coincidències."; log.error(msg); throw new
     * CustodyException(msg); } } }
     * 
     * }
     */
}
