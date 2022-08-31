package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
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
@RequestMapping(value = LlistatPeticionsPendentsUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsPendentsUserController extends LlistatPeticionsUserController {

    public static final String CONTEXT_WEB = "/user/peticio/pendents";
    
    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        
        Where defaultCondition = super.getAdditionalCondition(request);
        Where getPendentsCondition = ESTAT.equal(Constants.ESTAT_PETICIO_EN_PROCES);
        
        return Where.AND(defaultCondition, getPendentsCondition);
    }

}
