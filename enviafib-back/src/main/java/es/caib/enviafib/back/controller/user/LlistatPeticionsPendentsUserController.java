package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
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
@RequestMapping(value = LlistatPeticionsPendentsUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsPendentsUserController extends LlistatPeticionsUserController {

    public static final String CONTEXT_WEB = "/user/peticio/pendents";

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        if (peticioFilterForm.isNou()) {
            peticioFilterForm.addHiddenField(DATAFINAL);

            peticioFilterForm.addAdditionalButton(
                    new AdditionalButton("fas fa-info", "code.text.label", "FUNCTION", "btn-info"));

        }

        return peticioFilterForm;
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

        //        final Where defaultCondition = super.getAdditionalCondition(request);
        //        final Where getPendentsCondition = ESTAT.equal(Constants.ESTAT_PETICIO_EN_PROCES);
        //        final Where getErrorArxivantCondition = ESTAT.equal(Constants.ESTAT_PETICIO_ERROR_ARXIVANT);
        //        final Where getErrorTancantExpedientCondition = ESTAT.equal(Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT);
        //
        //        Where getPeticionsCondition = Where.OR(getPendentsCondition, getErrorArxivantCondition,
        //                getErrorTancantExpedientCondition);
        //
        //        return Where.AND(defaultCondition, getPeticionsCondition);

        final Where defaultCondition = super.getAdditionalCondition(request);

        Integer[] estats = { Constants.ESTAT_PETICIO_EN_PROCES, Constants.ESTAT_PETICIO_ARXIVANT,
                Constants.ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT, Constants.ESTAT_PETICIO_ERROR_ARXIVANT,
                Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT };

        return Where.AND(defaultCondition, ESTAT.in(estats));

    }

    @Override
    protected String getTitleCode() {
        return "peticio.list.pendents.title";
    }

}
