package es.caib.enviafib.back.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.enviafib.back.controller.user.AbstractPeticioUserController;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.DocumentContingut;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

public class DescarregarImprimiblePublicController extends AbstractPeticioUserController{
    
    protected enum TipusFile {
        ORIGINAL, ENI_DOC, VERSIO_IMPRIMIBLE
    }
    
    
    
    //@RequestMapping(value = "/downloadfile/{peticioId}", method = RequestMethod.GET)
    public void descarregarFitxerArxiu(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") long peticioId) throws I18NException, IOException {
        final String format = "PDF";
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;

        
        internalDownload(peticioId, response, format, tipusFile);
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
