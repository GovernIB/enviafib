package es.caib.enviafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.UsuariEntitatController;
import es.caib.enviafib.back.form.webdb.UsuariEntitatFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariEntitatForm;
import es.caib.enviafib.model.fields.UsuariEntitatFields;

/**
 * 
 * @author ptrias 14 nov 2024 14:32:58
 */

@Controller
@RequestMapping(value = UsuariEntitatAdminController.CONTEXTWEB)
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatAdminController extends UsuariEntitatController {

	public static final String CONTEXTWEB = "/admin/usuariEntitat";

	@Override
	public String getTileForm() {
		return "usuariEntitatFormAdmin";
	}

	@Override
	public String getTileList() {
		return "usuariEntitatListAdmin";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "usuariEntitatListAdmin_FilterForm";
	}

	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		String entitatID = (String) request.getSession().getAttribute("entitatID");
		Where wEntitat = UsuariEntitatFields.ENTITATID.equal(entitatID);

		return Where.AND(super.getAdditionalCondition(request), wEntitat);
	}

	@Override
	public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
			HttpServletRequest request) throws I18NException {

		UsuariEntitatFilterForm filterForm = super.getUsuariEntitatFilterForm(pagina, mav, request);

		String entitatID = (String) request.getSession().getAttribute("entitatID");
		log.info("getUsuariEntitatFilterForm:: entitatID: " + entitatID);

		return filterForm;
	}
}
