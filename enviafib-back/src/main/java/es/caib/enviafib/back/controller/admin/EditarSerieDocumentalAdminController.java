package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.SerieDocumentalController;
import es.caib.enviafib.back.form.webdb.SerieDocumentalFilterForm;
import es.caib.enviafib.back.form.webdb.SerieDocumentalForm;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/admin/serieDocumental")
@SessionAttributes(types = { SerieDocumentalForm.class, SerieDocumentalFilterForm.class })
public class EditarSerieDocumentalAdminController extends SerieDocumentalController {

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @Override
    public String getTileForm() {
        return "serieDocumentalFormAdmin";
    }

    @Override
    public String getTileList() {
        return "serieDocumentalListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "SerieDocumentalAdmin_FilterForm";
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusDocumental(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {

        // S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
        // de PortaFIB per obtenir els tipus de documents que gestiona:
        List<StringKeyValue> tmpList = null;

        String lang = LocaleContextHolder.getLocale().getLanguage();
        boolean obtenerTodos = true;
        
        tmpList = peticioLogicaEjb.getTipusDocumentals(lang, obtenerTodos);
        tmpList.add(new StringKeyValue("", I18NUtils.tradueix("seriedocumental.qualsevol")));

        return tmpList;
    }
    
    @Override
    public void postValidate(HttpServletRequest request, SerieDocumentalForm serieDocumentalForm, BindingResult result)
            throws I18NException {
        super.postValidate(request, serieDocumentalForm, result);
        
        String tipusDoc = serieDocumentalForm.getSerieDocumental().getTipusDocumental();
        if (tipusDoc == null || tipusDoc.trim().length() == 0) {
            result.rejectValue(get(TIPUSDOCUMENTAL), "genapp.validation.required",
                    new String[] { I18NUtils.tradueix(TIPUSDOCUMENTAL.fullName) }, null);
        }

        
        
    }
}
