package es.caib.enviafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.user.InfoSignaturaUserController;
import es.caib.enviafib.back.form.webdb.InfoSignaturaFilterForm;
import es.caib.enviafib.back.form.webdb.InfoSignaturaForm;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/admin/infoSignatura")
@SessionAttributes(types = { InfoSignaturaForm.class, InfoSignaturaFilterForm.class })
public class InfoSignaturaAdminController extends InfoSignaturaUserController {

    @Override
    public String getTileForm() {
        return "infoSignaturaFormAdmin";
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
