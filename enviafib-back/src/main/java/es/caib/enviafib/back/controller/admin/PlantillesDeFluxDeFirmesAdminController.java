package es.caib.enviafib.back.controller.admin;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import es.caib.enviafib.back.controller.user.PlantillesDeFluxDeFirmesUserController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.model.entity.Usuari;

/**
 * 
 * GESTIO DE FLUXES. Es fa servir Usuari per guardar Flux: * Nif => fluxID * Nom
 * => Nom del Flux * Llinatge1 => Descripció del flux * Email => Data Creació
 * 
 * 
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
    public String getSessionAttributeFilterForm() {
        return "plantillesfluxfirmesadmin_FilterForm";

    }

    @Override
    public String getOwner() {
        return "enviafib";
    }
    
    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {

        filterForm.getAdditionalButtonsByPK().clear();

        for (Usuari usuari : list) {
            // BOTO PER EDITAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(),
                    new AdditionalButton("fas fa-edit", "genapp.edit", 
                             getContextWeb() + "/" + usuari.getNif() + "/editar",
                            "btn-warning"));

            // BOTO PER ESBORRAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(),
                    new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                            + request.getContextPath() + getContextWeb() + "/" + usuari.getNif() + "/esborrar','show')",
                            "btn-danger"));

        }
    }

}
