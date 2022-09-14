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
@RequestMapping(value = LlistatPeticionsRebutjadesUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsRebutjadesUserController extends LlistatPeticionsUserController {

    public static final String CONTEXT_WEB = "/user/peticio/rebutjades";

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

//        final Where defaultCondition = super.getAdditionalCondition(request);
//        final Where getPendentsCondition = ESTAT.equal(Constants.ESTAT_PETICIO_ERROR);
//
//        return Where.AND(defaultCondition, getPendentsCondition);
//        

        final Where defaultCondition = super.getAdditionalCondition(request);

        Integer[] estats = { Constants.ESTAT_PETICIO_ERROR};

        return Where.AND(defaultCondition, ESTAT.in(estats));

    }

    @Override
    protected String getTitleCode() {
        return "peticio.list.rebutjades.title";
    }

}
