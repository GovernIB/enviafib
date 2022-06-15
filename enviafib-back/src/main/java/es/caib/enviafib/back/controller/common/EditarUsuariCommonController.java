package es.caib.enviafib.back.controller.common;

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
import es.caib.enviafib.ejb.UsuariService;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/common/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class EditarUsuariCommonController extends UsuariController {

	@EJB(mappedName = UsuariService.JNDI_NAME)
	protected es.caib.enviafib.ejb.UsuariService usuariEjb;

	@Override
	public String getTileForm() {
		return "userFormCommon";
	}

	@Override
	public boolean isActiveList() {
		return false;
	}

	@Override
	public boolean isActiveFormNew() {
		return false;
	}

	@Override
	public boolean isActiveFormEdit() {
		return true;
	}

	@Override
	public boolean isActiveDelete() {
		return false;
	}

	@Override
	public boolean isActiveFormView() {
		return false;
	}

	@Override
	public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		UsuariForm userForm = super.getUsuariForm(_jpa, __isView, request, mav);

		userForm.setCancelButtonVisible(false);
		userForm.setDeleteButtonVisible(false);
		if(userForm.getUsuari().getUsername() != null) {
		    userForm.addReadOnlyField(USERNAME);
		}
		return userForm;
	}

	@Override
	public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
		if (__e == null) {
			return "redirect:/";
		} else {
			return getTileForm();
		}
	}

}
