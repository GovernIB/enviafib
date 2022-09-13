package es.caib.enviafib.back.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.controller.user.FirmaPlantillaFluxEntitatUserController;
import es.caib.enviafib.back.controller.user.PlantillesDeFluxDeFirmesUserController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;


/**
 * 
 * @author fbosch
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/plantillesfluxfirmes")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class PlantillesDeFluxDeFirmesAdminController extends PlantillesDeFluxDeFirmesUserController {

    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes.admin";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.admin.plural";
    }

    @Override
    public String getTileForm() {
        return "plantillesfluxfirmesFormAdmin";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListAdmin";
    }

    @Override
    public String getOwner() {
        return FirmaPlantillaFluxEntitatUserController.OWNER_PLANTILLES_DE_LA_ENTITAT;
    }
}   
