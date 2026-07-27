package es.caib.enviafib.back.controller.aden;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.admin.LlistarUsuarisAdminController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.fields.UsuariFields;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = LlistarUsuarisAdenController.CONTEXTWEB)
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class LlistarUsuarisAdenController extends LlistarUsuarisAdminController {

	public static final String CONTEXTWEB = "/aden/usuari";

	@Override
	public String getTileForm() {
		return "usuariFormAden";
	}

	@Override
	public String getTileList() {
		return "usuariListAden";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "usuariListAden_FilterForm";
	}

	
	@Override
	public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {

		String entitatID = LoginInfo.getInstance().getEntitatRolsActual().getEntitat().getEntitatid();
		
		Where wEntitat = UsuariFields.ENTITATID.equal(entitatID);

		return Where.AND(super.getAdditionalCondition(request), wEntitat);
	}

}
