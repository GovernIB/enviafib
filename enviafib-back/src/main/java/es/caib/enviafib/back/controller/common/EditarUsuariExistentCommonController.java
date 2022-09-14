package es.caib.enviafib.back.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/common/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class EditarUsuariExistentCommonController extends AbstractEditarUsuariCommonController {

	@Override
	public String getTileForm() {
		return "userFormEditCommon";
	}

}
