package es.caib.enviafib.back.controller.user;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;

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

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {

        super.postList(request, mav, filterForm, list);

        for (Peticio pf : list) {

            if (pf.getEstat() == Constants.ESTAT_PETICIO_EN_PROCES) {

                filterForm.addAdditionalButtonByPK(pf.getPeticioID(), new AdditionalButton("fas fa-user-friends",
                        "flux.info", "javascript:window.open('" + request.getContextPath() + getContextWeb() + "/fluxinfo/" + pf.getPeticioID() + "', '_blank').focus();" , "btn-info"));

            }
        }

    }

    @RequestMapping(value = "/fluxinfo/{peticioID}", method = RequestMethod.GET)
    public String fluxInfo(@PathVariable("peticioID") java.lang.Long peticioID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {

        Peticio peticio = this.peticioLogicaEjb.findByPrimaryKey(peticioID);

        long portafibID = Long.parseLong(peticio.getPeticioPortafirmes()); // XYZ ZZZ

        String url = this.peticioLogicaEjb.getUrlToViewFlow(portafibID, LocaleContextHolder.getLocale().getLanguage());

        return "redirect:" + url;

    }

}
