package es.caib.enviafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.user.InfoArxiuUserController;
import es.caib.enviafib.back.form.webdb.InfoArxiuFilterForm;
import es.caib.enviafib.back.form.webdb.InfoArxiuForm;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/admin/infoArxiu")
@SessionAttributes(types = { InfoArxiuForm.class, InfoArxiuFilterForm.class })
public class InfoArxiuAdminController extends InfoArxiuUserController {

    @Override
    public String getTileForm() {
        return "infoArxiuFormAdmin";
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
