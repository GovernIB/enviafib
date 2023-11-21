package es.caib.enviafib.back.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.user.LlistatPeticionsUserController.TipusFile;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.InfoArxiuFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PeticioQueryPath;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.UsuariJPA;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.DocumentContingut;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

/**
 * 
 * @author ptrias
 *
 */

public abstract class AbstractLlistatPeticionsController extends AbstractPeticioUserController {

    public static final int COLUMN_ESTAT_IMG = 1;

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
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
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        if (peticioFilterForm.isNou()) {

            peticioFilterForm.setActionsRenderer(PeticioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

            hiddens.remove(PETICIOID);
            hiddens.remove(NOM);
            hiddens.remove(DATACREACIO);
            hiddens.remove(TIPUSDOCUMENTAL);
            hiddens.remove(DATAFINAL);
            hiddens.remove(SOLICITANTID);

            peticioFilterForm.setHiddenFields(hiddens);

            peticioFilterForm.setOrderBy(DATACREACIO.javaName);
            peticioFilterForm.setOrderAsc(false);

            peticioFilterForm.setAttachedAdditionalJspCode(true);

            peticioFilterForm.setAddButtonVisible(false);
            peticioFilterForm.setEditButtonVisible(false);
            peticioFilterForm.setDeleteButtonVisible(false);

            List<Field<?>> newFilterBy = new ArrayList<Field<?>>(peticioFilterForm.getDefaultFilterByFields());
            newFilterBy.add(NOM);
            newFilterBy.add(DATACREACIO);
            newFilterBy.add(DATAFINAL);
//            newFilterBy.add(ESTAT);
            peticioFilterForm.setFilterByFields(newFilterBy);

            List<Field<?>> newGroupBy = new ArrayList<Field<?>>(peticioFilterForm.getDefaultGroupByFields());
//            newGroupBy.add(ESTAT);
            peticioFilterForm.setGroupByFields(newGroupBy);

            peticioFilterForm.setVisibleFilterBy(false);

            {
                AdditionalField<Long, String> additionalField = new AdditionalField<Long, String>();
                additionalField.setCodeName(PeticioFields.ESTAT.codeLabel);
                additionalField.setPosition(COLUMN_ESTAT_IMG);
                additionalField.setOrderBy(ESTAT);
                additionalField.setEscapeXml(false);
                // Els valors s'ompliran al mètode postList()
                additionalField.setValueMap(new HashMap<Long, String>());
                // Per ordenar feim servir el mateix camp de nom del remitent
                peticioFilterForm.addAdditionalField(additionalField);
            }

        }
        peticioFilterForm.setVisibleExportList(true);

        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {

        // Millores en els filtres dels llistat de peticions. #248: Mostrar sempre filtres
        filterForm.setVisibleFilterBy(true);

        Map<Long, String> mapRemitent = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_ESTAT_IMG)
                .getValueMap();
        mapRemitent.clear();

        for (Peticio peticio : list) {
            String color;
            ArrayList<String> iconList = new ArrayList<String>();
            int estat = (int) peticio.getEstat();

            switch (estat) {
                case Constants.ESTAT_PETICIO_FIRMADA:
                case Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT:
                    color = "green";
                    if (estat == Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT) {
                        iconList.add("fas fa-box-open");
                    }else {
                        iconList.add("fas fa-file-signature");
                    }
                break;

                case Constants.ESTAT_PETICIO_EN_PROCES:
                case Constants.ESTAT_PETICIO_ARXIVANT:
                    color = "orange";
                    if (estat == Constants.ESTAT_PETICIO_EN_PROCES) {
                        iconList.add("fas fa-user-clock");
                    } else {
                        iconList.add("fas fa-spinner");
                        iconList.add("fas fa-archive");
                    }
                break;
                case Constants.ESTAT_PETICIO_ERROR:
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:
                    color = "red";
                    iconList.add("fas fa-exclamation-triangle");
                    if (estat == Constants.ESTAT_PETICIO_ERROR_ARXIVANT) {
                        iconList.add("fas fa-archive");
                    } else if (estat == Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT) {
                        iconList.add("fas fa-lock");
                    }
                break;

                default:
                    color = "#f128";
                    iconList.add("fas fa-question");
                break;
            }

            StringBuffer iconsStr = new StringBuffer();

            for (String i : iconList) {
                String title = I18NUtils.tradueix("estat." + estat);
                iconsStr.append("<i class='" + i + "' style='color:" + color + ";' title='" + title + "'></i>");
            }

            long peticioID = peticio.getPeticioID();

            mapRemitent.put(peticioID, "<center>" + iconsStr.toString() + "</center>");

            switch (estat) {

                case Constants.ESTAT_PETICIO_EN_PROCES:
                    
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-user-friends", "flux.info",
                                    "javascript:openModalFluxInfo(" + peticioID + ");",
                                    "btn-info"));

                break;
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-redo-alt ",
                            "arxiu.reintentar", "javascript:reintentarArxivat(" + peticioID + ")", "btn-warning"));
                break;
                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-redo-alt ", "arxiu.reintentartancamentexpedient",
                                    "javascript:reintentarTancamentExpedient(" + peticioID + ")", "btn-warning"));
                    
                case Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT:
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-redo-alt ", "arxiu.tancar.expedient",
                                    "javascript:tancarExpedient(" + peticioID + ")", "btn-warning"));
                    
                case Constants.ESTAT_PETICIO_FIRMADA:
                    
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-envelope ",
                            "peticio.btn.sendmail", "javascript:cridaEmail(" + peticioID + ")", "btn-success"));

                    String csv = infoArxiuEjb.executeQueryOne(InfoArxiuFields.CSV,
                            InfoArxiuFields.INFOARXIUID.equal(peticio.getInfoArxiuID()));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fas fa-print",
                            "download.arxivat.imprimible", getContextWeb() + "/descarregarimprimible/" + csv, "btn-info"));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-file-pdf",
                            "download.arxivat.firmat", getContextWeb() + "/descarregarfirmat/" + csv, "btn-info"));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-vote-yea",
                            "download.arxivat.eni", getContextWeb() + "/descarregarenidoc/" + csv, "btn-info"));
                break;
                default:
                break;
            }

            if (peticio.getInfoArxiuID() == null) {
                filterForm.addAdditionalButtonByPK(peticioID,
                        new AdditionalButton("fas fa-trash ", "peticio.btn.delete", "javascript: openModal('"
                                + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                "btn-danger"));
            }
        }
    }

    @RequestMapping(value = "/tancarexpedient/{peticioId}/{windowUrl}", method = RequestMethod.GET)
    public String tancarExpedient(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId, @PathVariable("windowUrl") String windowUrl) {

        try {
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));
            
            peticioLogicaEjb.tancarExpedientPeticio(peticioId, Configuracio.getUrlBase(decodedUrl, request.getContextPath()));

            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.tancarexpedient.success"));

        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/reintentararxivat/{peticioId}/{windowUrl}", method = RequestMethod.GET)
    public String reintentarArxivat(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioID, @PathVariable("windowUrl") String windowUrl) {

        try {
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

            String languageUI = LocaleContextHolder.getLocale().getLanguage();
            String url = Configuracio.getUrlBase(decodedUrl, request.getContextPath());

            Long infoSignaturaID = peticioLogicaEjb.executeQueryOne(PeticioFields.INFOSIGNATURAID,
                    PeticioFields.PETICIOID.equal(peticioID));

            String msg = peticioLogicaEjb.reintentGuardarPeticioArxiu(peticioID, infoSignaturaID, languageUI, url);

            if (msg == null) {
                HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.reintent.success"));
            } else {
                HtmlUtils.saveMessageError(request, msg);
            }
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";

    }
    
    @RequestMapping(value = "/reintentartancamentexpedient/{peticioId}/{windowUrl}", method = RequestMethod.GET)
    public String reintentarTancamentExpedient(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId, @PathVariable("windowUrl") String windowUrl) {

        try {
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

            peticioLogicaEjb.reintentarTancarExpedient(peticioId, Configuracio.getUrlBase(decodedUrl, request.getContextPath()));

            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.reintent.success"));

        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/enviaremail/{peticioId}/{email}/{windowUrl}", method = RequestMethod.GET)
    public String enviarEmail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") long peticioId, @PathVariable("email") String email,
            @PathVariable("windowUrl") String windowUrl) {
        final boolean isHTML = true;

        // Decodificam el email arriba en base64
        String decodedEmail = new String(Base64.getDecoder().decode(email));

        // Decodificam la URL que arriba en base64
        String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

        try {

            // Recuperacio del fitxer firmat a partir del ID de peticio
            Peticio peticio = peticioEjb.findByPrimaryKey(peticioId);
            
            // Si a petició s'ha arxivat correctament, s'ha de passar el link amb CSV:
            if (peticio.getEstat() != Constants.ESTAT_PETICIO_FIRMADA) {
                throw new I18NException("genapp.comodi", "No e spot enviar email de peticio no finalitzada");
            }

            InfoArxiuJPA ia = infoArxiuEjb.findByPrimaryKey(peticio.getInfoArxiuID());
            String fileUrl = ia.getCsvValidationWeb() + "view.xhtml?hash=" + ia.getCsv();
            
            UsuariJPA user = usuariEjb.findByPrimaryKey((Long) peticio.getSolicitantID());
            String nomSolicitant = user.getNom() + " " + user.getLlinatge1()
                    + (user.getLlinatge2() == null ? "" : " " + user.getLlinatge2());
            
            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nomFitxer", peticio.getFitxer().getNom());
            map.put("numeroPeticio", "" + peticio.getPeticioID());
            map.put("titolPeticio", peticio.getNom());
            map.put("fileUrl", fileUrl);
            map.put("solicitantNom", nomSolicitant);

            String subject = I18NUtils.tradueix("email.download.file.subject4");
            String message = I18NUtils.tradueix("email.download.file.message4");

            subject = TemplateEngine.processExpressionLanguage(subject, map);
            message = TemplateEngine.processExpressionLanguage(message, map);
            
            EmailUtil.postMail(subject, message, isHTML, Configuracio.getAppEmail(), decodedEmail);
            String successMsg = "S'ha enviat el email correctament";
            HtmlUtils.saveMessageSuccess(request, successMsg);

        } catch (MalformedURLException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.url");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (IOException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.message");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (Exception e) {
            String msg = I18NUtils.tradueix("email.download.file.error.send");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @Override
    public void delete(HttpServletRequest request, Peticio peticio) throws I18NException {
        peticioLogicaEjb.deleteFull(peticio);
    }

    @RequestMapping(value = "/descarregarfirmat/{csv}", method = RequestMethod.GET)
    public void descarregarFirmat(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "PDF";
        final String docName = "_firmat";
        TipusFile tipusFile = TipusFile.FIRMAT;

        internalDownload(csv, response, format, docName, tipusFile, infoArxiuEjb, pluginArxiuEjb, peticioEjb, log);
    }

    @RequestMapping(value = "/descarregarenidoc/{csv}", method = RequestMethod.GET)
    public void descarregarEnidoc(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "ENI";
        final String docName = "_eni";
        TipusFile tipusFile = TipusFile.ENI_DOC;

        internalDownload(csv, response, format, docName, tipusFile, infoArxiuEjb, pluginArxiuEjb, peticioEjb, log);
    }

    @RequestMapping(value = "/descarregarimprimible/{csv}", method = RequestMethod.GET)
    public void descarregarFitxerArxiu(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("csv") String csv) throws I18NException, IOException {
        final String format = "PDF";
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;
        final String docName = "_imprimible";

        internalDownload(csv, response, format, docName, tipusFile, infoArxiuEjb, pluginArxiuEjb, peticioEjb, log);
    }

    public static void internalDownload(String csv, HttpServletResponse response, final String format,
            final String docName, TipusFile tipusFile, es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb,
            es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuEjb,
            es.caib.enviafib.ejb.PeticioService peticioEjb, Logger log) throws I18NException, IOException {

        if (csv == null) {
            return;
        }

        String docID = infoArxiuEjb.executeQueryOne(InfoArxiuFields.ARXIUDOCUMENTID, InfoArxiuFields.CSV.equal(csv));
        log.info("internalDownload(): -> docID: " + docID);

        //        Long pluginID = 1001L;
        IArxiuPlugin plugin = pluginArxiuEjb.getInstance();

        byte[] data = null;

        switch (tipusFile) {
            case FIRMAT:
                Document firmat = plugin.documentDetalls(docID, null, true);
                data = firmat.getContingut().getContingut();
            break;

            case ENI_DOC:
                String enidoc = plugin.documentExportarEni(docID);
                data = enidoc.getBytes();
            break;

            case VERSIO_IMPRIMIBLE:
                DocumentContingut imprimible = plugin.documentImprimible(docID);
                data = imprimible.getContingut();
            break;
        }

        prepareAndDownload(data, response, docName, format, csv, peticioEjb);
    }

    private static void prepareAndDownload(byte[] data, HttpServletResponse response, final String docName,
            final String format, String csv, es.caib.enviafib.ejb.PeticioService peticioEjb) throws I18NException {

        // new PeticioJPA().getInfoArxiu().getCsv();
        String fileName = peticioEjb.executeQueryOne(new PeticioQueryPath().FITXER().NOM(),
                new PeticioQueryPath().INFOARXIU().CSV().equal(csv));

        fileName = fileName.replace(" ", "_");

        if (fileName != null) {
            if (fileName.toLowerCase().endsWith(".pdf")) {
                fileName = fileName.substring(0, fileName.lastIndexOf(".pdf"));
            }
            if (fileName.toLowerCase().endsWith(".xml")) {
                fileName = fileName.substring(0, fileName.lastIndexOf(".xml"));
            }

            if (format.equals("PDF")) {
                response.setContentType("application/pdf");
                fileName += docName + ".pdf";

            } else if (format.equals("ENI")) {
                response.setContentType("text/xml");
                fileName += docName + ".xml";
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentLength(data.length);

        OutputStream out;

        try {
            out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/geturlflow/{peticioID}", method = RequestMethod.GET)
    public void getURLtoFluxInfo(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioID") Long peticioID) throws I18NException, IOException {

        Peticio peticio = this.peticioLogicaEjb.findByPrimaryKey(peticioID);
        long portafibID = Long.parseLong(peticio.getPeticioPortafirmes()); // XYZ ZZZ

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String url = this.peticioLogicaEjb.getUrlToViewFlow(portafibID, lang);

        response.getWriter().write(url);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
