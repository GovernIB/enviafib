package es.caib.enviafib.logic;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignedFileInfo;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.IPlugin;
import org.fundaciobit.pluginsib.core.utils.Metadata;
import org.fundaciobit.pluginsib.core.utils.MetadataConstants;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.InfoArxiuEJB;
import es.caib.enviafib.logic.utils.I18NLogicUtils;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.plugins.arxiu.api.ArxiuException;
import es.caib.plugins.arxiu.api.ArxiuNotFoundException;
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
import java.util.Properties;

@Stateless(name = "PluginArxiuLogicaEJB")
public class PluginArxiuLogicaEJB extends InfoArxiuEJB implements PluginArxiuLogicaService {

    @EJB(mappedName = es.caib.enviafib.ejb.InfoArxiuService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb;

    @EJB(mappedName = PeticioLogicaService.JNDI_NAME)
    protected PeticioLogicaService peticioLogicaEjb;

    @Override
    public void tancarExpedient(Long infoCustodyID, String expedientID, Locale locale) throws I18NException {

        List<Peticio> peticions;
        try {
            peticions = peticioLogicaEjb.select(PeticioFields.INFOARXIUID.equal(infoCustodyID));
        } catch (Throwable e) {
            String msg = "Error cercant la transacció associada a la InfoCustody amb ID " + infoCustodyID + ": "
                    + e.getMessage();
            log.error(msg, e);
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi", msg);
        }

        if (peticions == null || peticions.size() == 0) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "InfoCustody amb ID " + infoCustodyID + " no es troba en cap transacció.");
        }

        //PeticioJPA peticio = (PeticioJPA) peticions.get(0);

        IArxiuPlugin plugin = getInstancePluginArxiu();

        plugin.expedientTancar(expedientID);

    }

    @Override
    public InfoArxiuJPA custodiaAmbApiArxiu(Peticio peticio, Fitxer fitxerFirmat, Locale locale, InfoSignaturaJPA infoSignatura) {

        IArxiuPlugin plugin;
        try {

            plugin = getInstancePluginArxiu();
            //Properties prop = Configuracio.getFilesProperties();

        } catch (I18NException e1) {

            final String msg = "XYZ ZZZ Error Instanciant PLugins de Arxiu: " + I18NLogicUtils.getMessage(e1, locale);

            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            peticio.setErrorMsg(msg);
            peticio.setErrorException(e1.getMessage());

            return null;
        }

        try {

            // ============ CALCULATS
            // ----- Format i Extensio
            DocumentFormat documentFormat;
            DocumentExtensio documentExtensio;
            documentFormat = DocumentFormat.PDF;
            documentExtensio = DocumentExtensio.PDF;

            // XYZ S'ha de controlar el format del document
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

            // XYZ ZZZ On es fica això !!!!!!
            //String procedimentNom = peticio.getArxiuOptParamProcedimentNom(); // "Subvenciones
                                                                              // empleo";
            // if (procedimentNom == null) {
            // procedimentNom =
            // prop.getProperty(TransaccioFields.ARXIUOPTPARAMPROCEDIMENTNOM.javaName);
            // }

            String procedimentCodi = peticio.getArxiuOptParamProcedimentCodi();
            // if (procedimentCodi == null) {
            // procedimentCodi =
            // prop.getProperty(TransaccioFields.ARXIUOPTPARAMPROCEDIMENTCODI.javaName);
            // }

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
            // if (serieDocumental == null) {
            // serieDocumental =
            // prop.getProperty(TransaccioFields.ARXIUOPTPARAMSERIEDOCUMENTAL.javaName);
            // }

            // XYZ ZZZ Això és per quan l'usuari pugui indicar el nom de l'expedient on vol
            // el
            // document
            // String custodyOrExpedientID = prop
            // .getProperty(TransaccioFields.ARXIUOPTPARAMCUSTODYOREXPEDIENTID.javaName);
            final String interessatsStr = peticio.getArxiuReqParamInteressats();

            // XYZ ZZZ Fer un tiquet per posar-ho en una propietat del PLugin ????
            final String nomExpedient = "EnviaFIB_" + peticio.getPeticioID();

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
            // expedient.setVersio("1.0");
            expedient.setMetadades(expedientMetadades);

            if (peticio.getArxiuOptParamExpedientId() != null) {
                // XYZ ZZZ Falta implementar que s'hagi definit un EXPEDIENT
                log.error(
                        "\n\n Falta implementar que s'hagi definit un EXPEDIENT (no s'hauria de fer creacio d'expedient) \n\n",
                        new Exception());
            }

            log.info("XYZ ZZZ  Creant expedient... ");
            ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);

            final String expedientId = expedientCreat.getIdentificador();

            log.info("XYZ ZZZ  Creat expedient amd ID = " + expedientId);

            log.info("XYZ ZZZ  Creant document ... ");
            final DocumentMetadades documentMetadades = new DocumentMetadades();

            final ContingutOrigen origen = (peticio.getArxiuReqParamOrigen() == Constants.ORIGEN_ADMINISTRACIO)
                    ? ContingutOrigen.ADMINISTRACIO
                    : ContingutOrigen.CIUTADA;
            documentMetadades.setOrigen(origen);
            documentMetadades.setOrgans(organs);
            documentMetadades.setDataCaptura(new Date());

            documentMetadades
                    .setEstatElaboracio(DocumentEstatElaboracio.toEnum(peticio.getArxiuReqParamDocEstatElabora()));
            documentMetadades.setTipusDocumental(DocumentTipus.toEnum(peticio.getTipusDocumental()));
            documentMetadades.setFormat(documentFormat);
            documentMetadades.setExtensio(documentExtensio);

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
            final Integer signMode;

            if (infoSignatura == null) {
                firmaTipus = null;
                firmaPerfil = null;
                signMode = null;
            } else {

                signMode = infoSignatura.getSignMode();

                /*
                 * CSV("TF01"), XADES_DET("TF02"), XADES_ENV("TF03"), CADES_DET("TF04"),
                 * CADES_ATT("TF05"), PADES("TF06"), SMIME("TF07"), ODT("TF08"), OOXML("TF09");
                 */
                firmaTipus = FirmaTipus.toEnum(infoSignatura.getEniTipoFirma());

                firmaPerfil = firmaPerfilToEnum(infoSignatura.getEniPerfilFirma());

            }

            // XYZ ZZZ TRA TODO
            final String commonError = "Comprovi que el procés de firma realitza la validació de la mateixa.";

            if (signMode == null) {
                // XYZ ZZZ TRA TODO
                String msg = "SignMode val null." + commonError;
                log.error(msg, new Exception());
                throw new I18NException("genapp.comodi", msg);
            }

            if (firmaTipus == null) {
                // XYZ ZZZ TRA TODO
                String msg = "FirmaTipus val null (infoSignatura.getEniTipoFirma() == "
                        + infoSignatura.getEniTipoFirma() + " )" + commonError;
                log.error(msg, new Exception());
                throw new I18NException("genapp.comodi", msg);
            }

            if (firmaPerfil == null) {
                // XYZ ZZZ TRA TODO
                String msg = "FirmaPerfil val null (infoSignatura.getEniPerfilFirma() == "
                        + infoSignatura.getEniPerfilFirma() + " )" + commonError;
                log.error(msg, new Exception());
                throw new I18NException("genapp.comodi", msg);
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

            ContingutArxiu documentCreat = plugin.documentCrear(documentPerCrear, expedientId);

            log.info("XYZ ZZZ  Creat document ... ");

            log.info("XYZ ZZZ  Tancar Expedient ... ");

            // Només per DocumentCustody, s'utilitza també per gestionar quan l'expedient no
            // s'ha pogut tancar.

            try {
                // if (true) {
                // throw new Exception("Error desconegut tancant Expedient !!!!!");
                // }

                plugin.expedientTancar(expedientId);
                log.info("XYZ ZZZ  Expedient Tancat... ");
            } catch (Throwable th) {
                log.error("Error tancant Expedient " + expedientId + ": " + th.getMessage(), th);
            }

            String uuidDoc = documentCreat.getIdentificador();

            // XYZ Cridades de Plugin: No esta implementat?
            // peticioLogicaEjb.postCridadaOK(monitor, "expedientID=" + expedientId +
            // "\nDocumentID=" + uuidDoc);

            log.info("\n FINAL \n");

            InfoArxiuJPA infoCust = null;
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

                    infoCust = new InfoArxiuJPA( originalFileUrl, csv, csvGenerationDefinition,
                            csvValidationWeb, expedientId, uuidDoc, printableFileUrl, eniFileUrl, validationFileUrl);
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

            infoCust = (InfoArxiuJPA) infoArxiuEjb.create(infoCust);

            peticio.setInfoArxiuID(infoCust.getInfoArxiuID());

            return infoCust;

        } catch (Throwable e) {
            final String msg;

            if (e instanceof I18NException) {
                msg = "XYZ ZZZ Error custodiant fitxer firmat(I18NException): "
                        + I18NLogicUtils.getMessage((I18NException) e, locale);
            } else if (e instanceof ArxiuException) {
                msg = "XYZ ZZZ Error custodiant fitxer firmat(ArxiuException): " + e.getMessage();
            } else {
                msg = "XYZ ZZZ Error custodiant fitxer firmat(" + e.getClass() + "): " + e.getMessage();
            }

            log.error("Error intenant enviar a API d'Arxiu: " + msg, e);

            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            peticio.setErrorMsg(msg);
            peticio.setErrorException(e.getMessage());

            // Cridades de Plugin
            // pluginCridada.postCridadaError(monitorIntegracions, msg + "\n\n" +
            // peticio.getEstatExcepcio());
        }

        return null;

    }

    public FirmaPerfil firmaPerfilToEnum(String perfil) throws I18NException {

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


    protected IArxiuPlugin getInstancePluginArxiu() throws I18NException {

        IPlugin pluginInstance;

        Properties prop = new Properties();
        prop = Configuracio.getFilesProperties();

        if (prop != null) {
            pluginInstance = (IPlugin) PluginsManager.instancePluginByClassName(Configuracio.getPluginArxiuClass(),
                    Constants.ENVIAFIB_PROPERTY_BASE, prop);

            return (IArxiuPlugin) pluginInstance;
        }

        return null;
    }

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

        // XYZ ZZZ ESBORRAR !!!!!
        /*
         * metadadesAddicionals.add(new Metadata("eni:subtipo_doc", "Especial CAIB"));
         */
        return metadadesAddicionals;
    }

}
