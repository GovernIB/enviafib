package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;

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
        
        Where defaultCondition = super.getAdditionalCondition(request);
        Where getFirmadesCondition = ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA);
        
        return Where.AND(defaultCondition, getFirmadesCondition);
    }
    
    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        peticioFilterForm.setTitleCode("peticio.list.firmades.title");
        return peticioFilterForm;
    }
    
}
