package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.SerieDocumentalController;
import es.caib.enviafib.back.form.webdb.SerieDocumentalFilterForm;
import es.caib.enviafib.back.form.webdb.SerieDocumentalForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.ejb.SerieDocumentalService;

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
    public List<StringKeyValue> getReferenceListForTipusdocu(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        // S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
        // de PortaFIB per obtenir els tipus de documents que gestiona:

        List<StringKeyValue> tmpList = peticioLogicaEjb
                .getAvailableTipusDocumental(LocaleContextHolder.getLocale().getLanguage());
        tmpList.add(new StringKeyValue("", I18NUtils.tradueix("seriedocumental.qualsevol")));
        return tmpList;

    }
}
