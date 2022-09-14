package es.caib.enviafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.common.AbstractEditarUsuariCommonController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = EditarUsuariExistentUserController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class EditarUsuariExistentUserController extends AbstractEditarUsuariCommonController {

    
    public static final String CONTEXT_WEB = "/user/usuari";

	@Override
	public String getTileForm() {
		return "userFormEditUser";
	}

}
