package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsUserController;
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
public class LlistatPeticionsFirmadesUserController extends AbstractLlistatPeticionsUserController {
    
    public static final String CONTEXT_WEB = "/user/peticio/firmades";

    
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        
        final Where defaultCondition = super.getAdditionalCondition(request);
        
        return Where.AND(defaultCondition, ESTAT.equal(Constants.ESTAT_PETICIO_FIRMADA));
    }
    
    @Override
    protected String getTitleCode() {
        return "peticio.list.firmades.title";
    }
    
}
