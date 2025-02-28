package es.caib.enviafib.back.controller.aden;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.SerieDocumentalController;
import es.caib.enviafib.back.form.webdb.SerieDocumentalFilterForm;
import es.caib.enviafib.back.form.webdb.SerieDocumentalForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.logic.PeticioLogicaService;
import es.caib.enviafib.logic.SerieDocumentalLogicaService;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.persistence.SerieDocumentalJPA;

/**
 * 
 * @author ptrias 15 nov 2024 13:30:36
 */

@Controller
@RequestMapping(value = SerieDocumentalAdenController.CONTEXTWEB)
@SessionAttributes(types = { SerieDocumentalFilterForm.class, SerieDocumentalForm.class })
public class SerieDocumentalAdenController extends SerieDocumentalController {

	public static final String CONTEXTWEB = "/aden/serieDocumental";

	@EJB(mappedName = SerieDocumentalLogicaService.JNDI_NAME)
	protected SerieDocumentalLogicaService serieDocumentalLogicaEJB;

	@Override
	public String getTileForm() {
		return "serieDocumentalFormAden";
	}

	@Override
	public String getTileList() {
		return "serieDocumentalListAden";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "SerieDocumentalAden_FilterForm";
	}

	@Override
	public SerieDocumentalFilterForm getSerieDocumentalFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {
		SerieDocumentalFilterForm filterForm = super.getSerieDocumentalFilterForm(pagina, mav, request);

		if (filterForm.isNou()) {
			log.info("isNou : " + filterForm.isNou());
			filterForm.addHiddenField(SerieDocumentalFields.ENTITATID);
		}

		return filterForm;
	}

	@Override
	public SerieDocumentalForm getSerieDocumentalForm(SerieDocumentalJPA _jpa, boolean __isView,
			HttpServletRequest request, ModelAndView mav) throws I18NException {
		SerieDocumentalForm serieDocumentalForm = super.getSerieDocumentalForm(_jpa, __isView, request, mav);

		SerieDocumental serieDoc = serieDocumentalForm.getSerieDocumental();

		String entitatID = LoginInfo.getInstance().getEntitatRolsActual().getEntitat().getEntitatid();
		serieDoc.setEntitatID(entitatID);

		serieDocumentalForm.addHiddenField(SerieDocumentalFields.ENTITATID);

		return serieDocumentalForm;
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		String entitatID = LoginInfo.getInstance().getEntitatRolsActual().getEntitat().getEntitatid();

		Where wEntitat = SerieDocumentalFields.ENTITATID.equal(entitatID);

		return Where.AND(super.getAdditionalCondition(request), wEntitat);
	}

	@Override
	public SerieDocumentalJPA update(HttpServletRequest request, SerieDocumentalJPA serieDocumental)
			throws I18NException, I18NValidationException {

		return (SerieDocumentalJPA) serieDocumentalLogicaEJB.update(serieDocumental);
	}

	@Override
	public SerieDocumentalJPA findByPrimaryKey(HttpServletRequest request, Long serieDocumentalID)
			throws I18NException {
		return (SerieDocumentalJPA) serieDocumentalLogicaEJB.findByPrimaryKeyPublic(serieDocumentalID);
	}

	@Override
	public SerieDocumentalJPA create(HttpServletRequest request, SerieDocumentalJPA serieDocumental)
			throws I18NException, I18NValidationException {
		return (SerieDocumentalJPA) serieDocumentalLogicaEJB.createPublic(serieDocumental);
	}

	@Override
	public void delete(HttpServletRequest request, SerieDocumental serieDocumental) throws I18NException {
		serieDocumentalLogicaEJB.delete(serieDocumental);
	}

	@Override
	public List<StringKeyValue> getReferenceListForTipusDocumental(HttpServletRequest request, ModelAndView mav,
			Where where) throws I18NException {

		// S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
		// de PortaFIB per obtenir els tipus de documents que gestiona:

		List<StringKeyValue> tmpList = null;

		boolean conSerieDocumental = true;
		log.info("where: " + where);
		if (where != null) {
			log.info("where: " + where.toSQL());
			log.info("where: " + where.toString());
			conSerieDocumental = false;
		}

		String lang = LocaleContextHolder.getLocale().getLanguage();
		String entitatID = LoginInfo.getInstance().getEntitatRolsActual().getEntitat().getEntitatid();

		tmpList = serieDocumentalLogicaEJB.getTipusDocumentalsBase(lang);
		if (tmpList.isEmpty()) {
			HtmlUtils.saveMessageError(request, "No hi ha tipus documentals. Pot haver un problema amb el servidor de PortaFIB");
		} else {
			tmpList.add(new StringKeyValue("", I18NUtils.tradueix("seriedocumental.qualsevol")));
		}

		return tmpList;

	}
}
