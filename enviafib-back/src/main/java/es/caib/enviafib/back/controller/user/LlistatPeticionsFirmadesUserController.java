package es.caib.enviafib.back.controller.user;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.InfoArxiuFields;
import es.caib.enviafib.model.fields.PeticioQueryPath;
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
            peticioFilterForm.setDeleteSelectedButtonVisible(false);
            
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

    @RequestMapping(value = "/descarregaroriginal/{csv}", method = RequestMethod.GET)
    public void descarregarOriginal(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "PDF";
        final String docName = "_original";
        TipusFile tipusFile = TipusFile.ORIGINAL;

        internalDownload(csv, response, format, docName, tipusFile);
    }

    @RequestMapping(value = "/descarregarenidoc/{csv}", method = RequestMethod.GET)
    public void descarregarEnidoc(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "ENI";
        final String docName = "_eni";
        TipusFile tipusFile = TipusFile.ENI_DOC;

        internalDownload(csv, response, format, docName, tipusFile);
    }

    @RequestMapping(value = "/descarregarimprimible/{csv}", method = RequestMethod.GET)
    public void descarregarVersioImprible(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = "PDF";
        final String docName = "_imprimible";
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;

        internalDownload(csv, response, format, docName, tipusFile);
    }

    protected void internalDownload(String csv, HttpServletResponse response, final String format, final String docName,
            TipusFile tipusFile) throws I18NException, IOException {

        IArxiuPlugin plugin = pluginArxiuEjb.getInstance();

        String docID = infoArxiuEjb.executeQueryOne(InfoArxiuFields.ARXIUDOCUMENTID, InfoArxiuFields.CSV.equal(csv));
        log.info("internalDownload(): -> docID: " + docID);

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

        prepareAndDownload(data, response, docName, format, csv);
    }

    private void prepareAndDownload(byte[] data, HttpServletResponse response, final String docName,
            final String format, String csv) throws I18NException {

        //      new PeticioJPA().getInfoArxiu().getCsv();
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
}
