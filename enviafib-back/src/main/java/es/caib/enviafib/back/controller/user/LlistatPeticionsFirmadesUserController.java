package es.caib.enviafib.back.controller.user;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.DocumentContingut;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

/**
 * 
 * @author fbosch
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = LlistatPeticionsFirmadesUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsFirmadesUserController extends LlistatPeticionsUserController {

    public static final String CONTEXT_WEB = "/user/peticio/firmades";

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        final Where defaultCondition = super.getAdditionalCondition(request);

        Integer[] estats = { Constants.ESTAT_PETICIO_FIRMADA };

        return Where.AND(defaultCondition, ESTAT.in(estats));
    }

    @Override
    protected String getTitleCode() {
        return "peticio.list.firmades.title";
    }

    protected enum TipusFile {
        ORIGINAL, ENI_DOC, VERSIO_IMPRIMIBLE
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);

        if (peticioFilterForm.isNou()) {
            peticioFilterForm.setActionsRenderer(PeticioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

//            peticioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-file-pdf",
//                    "download.arxivat.original", getContextWeb() + "/descarregaroriginal/{0}", "btn-info"));
//
//            peticioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-vote-yea",
//                    "download.arxivat.eni", getContextWeb() + "/descarregarenidoc/{0}", "btn-info"));
//
//            peticioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-print",
//                    "download.arxivat.imprimible", getContextWeb() + "/descarregarimprimible/{0}", "btn-info"));
//
//            peticioFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-envelope icon-white",
//                    "peticio.btn.sendmail", "javascript: cridaEmail({0})", "btn-success"));
        }

        return peticioFilterForm;
    }

    @RequestMapping(value = "/descarregaroriginal/{peticioID}", method = RequestMethod.GET)
    public void descarregarOriginal(@PathVariable("peticioID") java.lang.Long peticioID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "PDF";
        TipusFile tipusFile = TipusFile.ORIGINAL;

        internalDownload(peticioID, response, format, tipusFile);
    }

    @RequestMapping(value = "/descarregarenidoc/{peticioID}", method = RequestMethod.GET)
    public void descarregarEnidoc(@PathVariable("peticioID") java.lang.Long peticioID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "ENI";
        TipusFile tipusFile = TipusFile.ENI_DOC;

        internalDownload(peticioID, response, format, tipusFile);
    }

    @RequestMapping(value = "/descarregarimprimible/{peticioID}", method = RequestMethod.GET)
    public void descarregarVersioImprible(@PathVariable("peticioID") java.lang.Long peticioID,
            HttpServletRequest request, HttpServletResponse response) throws I18NException, IOException {

        final String format = "PDF";
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;

        internalDownload(peticioID, response, format, tipusFile);
    }

    protected void internalDownload(java.lang.Long peticioID, HttpServletResponse response, final String format,
            TipusFile tipusFile) throws I18NException, IOException {

        if (peticioID == null) {
            return;
        }

        PeticioJPA peticio = peticioLogicaEjb.findByPrimaryKeyPublic(peticioID);
        log.info("internalDownload(): -> peticio: " + peticio);

        InfoArxiuJPA infoArxiu = infoArxiuEjb.findByPrimaryKey(peticio.getInfoArxiuID());
        log.info("internalDownload(): -> infoArxiu: " + infoArxiu);

        String docID = infoArxiu.getArxiuDocumentID();
        log.info("internalDownload(): -> docID: " + docID);

//        String docID = infoArxiuEjb.executeQueryOne(InfoArxiuFields.ARXIUDOCUMENTID,
//                InfoArxiuFields.INFOARXIUID.equal(peticio.getInfoArxiuID()));

//        Long pluginID = 1001L;
        IArxiuPlugin plugin = pluginArxiuEjb.getInstance();

        byte[] data = null;
        switch (tipusFile) {
            case ORIGINAL:
                Document original = plugin.documentDetalls(docID, null, true);
                data = original.getContingut().getContingut();
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

        prepareAndDownload(data, response, peticio, format);
    }

    private void prepareAndDownload(byte[] data, HttpServletResponse response, PeticioJPA peticio, String format) {

        // s'eliminen els espais en el cas de noms de transaccions amb més d'una
        // paraula, així queda només el nom amb una paraula
        // motiu: no se li afegia l'extensió correctament al document descarregat
        String documentName = peticio.getNom().replace(" ", "_");

        if (format.equals("PDF")) {
            response.setContentType("application/pdf");

            if (documentName != null && !documentName.toLowerCase().endsWith(".pdf")) {
                documentName += ".pdf";
            }

        } else if (format.equals("ENI")) {
            response.setContentType("text/xml");

            if (documentName != null && !documentName.toLowerCase().endsWith(".xml")) {
                documentName += ".xml";
            }
        }
        response.setHeader("Content-disposition", "attachment; filename=" + documentName);
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
}
