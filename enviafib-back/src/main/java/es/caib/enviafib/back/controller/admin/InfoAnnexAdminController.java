package es.caib.enviafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.user.InfoAnnexUserController;
import es.caib.enviafib.back.form.webdb.InfoAnexFilterForm;
import es.caib.enviafib.back.form.webdb.InfoAnexForm;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = InfoAnnexAdminController.CONTEXT_WEB)
@SessionAttributes(types = { InfoAnexForm.class, InfoAnexFilterForm.class })
public class InfoAnnexAdminController extends InfoAnnexUserController {

    public static final String CONTEXT_WEB = "/admin/infoAnnex";

    @Override
    public String getTileForm() {
        return "infoAnexFormAdmin";
    }

    @Override
    public String getTileList() {
        return "infoAnexListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "infoAnexAdmin_FilterForm";
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
