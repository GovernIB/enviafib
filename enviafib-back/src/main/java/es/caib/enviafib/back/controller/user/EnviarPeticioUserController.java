package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.PeticioController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/user/peticio")
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class EnviarPeticioUserController extends PeticioController {

	@EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
	protected es.caib.enviafib.ejb.UsuariService usuariEjb;

	@Override
	public String getTileForm() {
		return "peticioFormUser";
	}

	@Override
	public String getTileList() {
		return "peticioListUser";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "PeticioUser_FilterForm";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
		String userName = request.getRemoteUser();
		Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
		return PeticioFields.SOLICITANTID.equal(userId);
	}

	@Override
	public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);
		peticioForm.getPeticio().setDatacreacio(new Timestamp(System.currentTimeMillis()));
		peticioForm.addReadOnlyField(PeticioFields.DATACREACIO);
		String userName = request.getRemoteUser();
		Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
		peticioForm.getPeticio().setSolicitantID(userId);
		peticioForm.addReadOnlyField(SOLICITANTID);
		return peticioForm;
	}

	@Override
	public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
		if (peticioFilterForm.isNou()) {
			peticioFilterForm.setVisibleExportList(false);
			peticioFilterForm.addHiddenField(SOLICITANTID);
			peticioFilterForm.addHiddenField(PETICIOID);
		}
		return peticioFilterForm;
	}

}
