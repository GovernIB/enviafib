package es.caib.enviafib.back.controller.all;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsController;
import es.caib.enviafib.back.controller.user.LlistatPeticionsUserController;
import es.caib.enviafib.back.controller.user.LlistatPeticionsUserController.TipusFile;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class DescarregarImprimiblePublicController {

    public static final String CONTEXT_WEB = "/public/emailfile";

    @EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.InfoArxiuService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.PeticioService.JNDI_NAME)
    protected es.caib.enviafib.ejb.PeticioService peticioEjb;

    protected static final Logger log = Logger.getLogger(DescarregarImprimiblePublicController.class);

    @RequestMapping(value = CONTEXT_WEB + "/{csv}", method = RequestMethod.GET)
    public void descarregarFitxerArxiu(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("csv") String csv) throws I18NException, IOException {
        final String format = "PDF";
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;
        final String docName = "_imprimible";

        AbstractLlistatPeticionsController.internalDownload(csv, response, format, docName, tipusFile, infoArxiuEjb,
                pluginArxiuEjb, peticioEjb, log);
    }

}
