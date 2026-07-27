package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
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
import es.caib.enviafib.logic.SerieDocumentalLogicaService;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.persistence.SerieDocumentalJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/admin/serieDocumental")
@SessionAttributes(types = { SerieDocumentalForm.class, SerieDocumentalFilterForm.class })
public class EditarSerieDocumentalAdminController extends SerieDocumentalController {

	@EJB(mappedName = SerieDocumentalLogicaService.JNDI_NAME)
	protected SerieDocumentalLogicaService serieDocumentalLogicaEJB;

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
			SerieDocumentalForm serieDocumentalForm, Where where) throws I18NException {

        boolean isNou = serieDocumentalForm.isNou();
        log.info("isNou : " + isNou);

        //Si es nou, conSerieDocumental = false
        // Resta de casos, conSerieDocumental = true

        //Guardar el valor al where
		if (isNou) {
			where = SerieDocumentalFields.SERIEDOCUMENTALID.isNull();
		}

        return getReferenceListForTipusDocumental(request, mav, where);
    }

	@Override
	public List<StringKeyValue> getReferenceListForTipusDocumental(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {
		// S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
		// de PortaFIB per obtenir els tipus de documents que gestiona:

		List<StringKeyValue> tmpList = null;

		/*
		boolean conSerieDocumental = true;
		if (where != null) {
			conSerieDocumental = false;
		}
		*/
		
		String lang = LocaleContextHolder.getLocale().getLanguage();
		
		//String entitatID = null;

		tmpList = serieDocumentalLogicaEJB.getAllTipusDocumentals(lang);
		if (tmpList.isEmpty()) {
			HtmlUtils.saveMessageError(request, "No hi ha tipus documentals. Pot haver un problema amb el servidor de PortaFIB");
		} else {
			tmpList.add(new StringKeyValue("", I18NUtils.tradueix("seriedocumental.qualsevol")));
		}

		return tmpList;
	}

	@Override
	public SerieDocumentalForm getSerieDocumentalForm(SerieDocumentalJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		// TODO Auto-generated method stub
		return super.getSerieDocumentalForm(_jpa, __isView, request, mav);
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
