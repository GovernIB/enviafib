package es.caib.enviafib.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.FaqController;
import es.caib.enviafib.back.form.webdb.FaqFilterForm;
import es.caib.enviafib.back.form.webdb.FaqForm;
import es.caib.enviafib.back.form.webdb.PluginFilterForm;
import es.caib.enviafib.back.form.webdb.PluginForm;
import es.caib.enviafib.model.fields.FaqFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.FaqJPA;

/**
 * 
 * @author ptrias
 *
 */

@Controller
@RequestMapping(value = FaqAdminController.CONTEXTWEB)
@SessionAttributes(types = { FaqForm.class, FaqForm.class })
public class FaqAdminController extends FaqController {

    public static final String CONTEXTWEB = "/admin/faq";

    @Override
    public String getTileForm() {
        return "faqFormAdmin";
    }

    @Override
    public String getTileList() {
        return "faqListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "faqListAdmin_FilterForm";
    }

    @Override
    public FaqFilterForm getFaqFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        FaqFilterForm faqFilterForm = super.getFaqFilterForm(pagina, mav, request);
        
        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(FaqFields.ALL_FAQ_FIELDS));
        hiddens.remove(ORDRE);
        
        String lang = LocaleContextHolder.getLocale().getLanguage();
        log.info("Idioma lang: " + lang);
        
        if (lang.equals("es")) {
            hiddens.remove(ENUNCIAT_ES);
        } else {
            hiddens.remove(ENUNCIAT_CA);
        }
        
        faqFilterForm.setHiddenFields(hiddens);

        if (faqFilterForm.isNou()) {
            OrderBy[] orderBy = { new OrderBy(ORDRE) };
            faqFilterForm.setDefaultOrderBy(orderBy);
        }
        return faqFilterForm;
    }

    @Override
    public FaqForm getFaqForm(FaqJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        FaqForm faqForm = super.getFaqForm(_jpa, __isView, request, mav);

        faqForm.setAttachedAdditionalJspCode(true);

        return faqForm;
    }

}
