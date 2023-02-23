package es.caib.enviafib.back.controller.user;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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

    public enum TipusFile {
        ORIGINAL, ENI_DOC, VERSIO_IMPRIMIBLE
    }
    
    @Override
    public String getEntityNameCode() {
        return "peticio.list.firmades";
    }
    
    @Override
    public String getEntityNameCodePlural() {
        return "peticio.list.firmades.plural";
    }
    

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        final Where defaultCondition = super.getAdditionalCondition(request);

        Integer[] estats = { Constants.ESTAT_PETICIO_FIRMADA };

        return Where.AND(defaultCondition, ESTAT.in(estats));
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);

        if (peticioFilterForm.isNou()) {
//            peticioFilterForm.setActionsRenderer(PeticioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);
            peticioFilterForm.setDeleteSelectedButtonVisible(false);
            peticioFilterForm.addHiddenField(DATACREACIO);
            peticioFilterForm.getFilterByFields().remove(DATACREACIO);
        }
        return peticioFilterForm;
    }

}
