package es.caib.enviafib.back.controller.ajuda;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.user.LlistatPeticionsUserController;
import es.caib.enviafib.back.controller.webdb.FaqController;
import es.caib.enviafib.back.form.webdb.FaqFilterForm;
import es.caib.enviafib.back.form.webdb.FaqForm;
import es.caib.enviafib.persistence.FaqJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/ajuda/faq")
@SessionAttributes(types = { FaqForm.class, FaqFilterForm.class })
public class FaqsCommonController extends FaqController {

    @Override
    public String getTileForm() {
        return "faqCommonForm";
    }

    @Override
    public String getTileList() {
        return "faqCommonList";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "faqCommon_FilterForm";
    }

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    
    
    
    @Override
    public FaqFilterForm getFaqFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        FaqFilterForm faqFilterForm = super.getFaqFilterForm(pagina, mav, request);

        Set<Field<?>> hiddens = new HashSet<Field<?>>();
        hiddens.add(FAQID);

        String lang = LocaleContextHolder.getLocale().getLanguage();
        log.info("lang: " + lang);

        mav.addObject("lang", lang);

//        if (lang.equals("es")) {
//            hiddens.add(ENUNCIAT_CA);
//            hiddens.add(RESPOSTA_CA);
//        } else {
//            hiddens.add(ENUNCIAT_ES);
//            hiddens.add(RESPOSTA_ES);
//        }

        faqFilterForm.setHiddenFields(hiddens);

        if (faqFilterForm.isNou()) {
            faqFilterForm.setVisibleMultipleSelection(false);
            faqFilterForm.setAddButtonVisible(false);
            faqFilterForm.setDeleteButtonVisible(false);
            faqFilterForm.setDeleteSelectedButtonVisible(false);
            faqFilterForm.setEditButtonVisible(false);

            faqFilterForm.addAdditionalButton(new AdditionalButton("fas fa-list", "back.to.list",
                    LlistatPeticionsUserController.CONTEXT_WEB + "/list", "btn-primary"));
        }

        OrderBy[] ordre = { new OrderBy(ORDRE) };
        faqFilterForm.setDefaultOrderBy(ordre);

        return faqFilterForm;
    }

    @Override
    public FaqForm getFaqForm(FaqJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        FaqForm faqForm = super.getFaqForm(_jpa, __isView, request, mav);

        return faqForm;
    }

}
