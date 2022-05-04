package es.caib.enviafib.back.controller.admin;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.UsuariController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/admin/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class LlistarUsuarisAdminController extends UsuariController {

	@EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
	protected es.caib.enviafib.ejb.UsuariService usuariEjb;

	@Override
	public String getTileForm() {
		return "usuariFormAdmin";
	}

	@Override
	public String getTileList() {
		return "usuariListAdmin";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "usuariListAdmin_FilterForm";
	}

	@Override
	public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);
		usuariFilterForm.addHiddenField(USUARIID);
		usuariFilterForm.addHiddenField(EMAIL);
		return usuariFilterForm;
	}
}
