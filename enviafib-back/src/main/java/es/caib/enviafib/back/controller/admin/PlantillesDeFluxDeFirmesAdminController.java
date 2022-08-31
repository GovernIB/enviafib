package es.caib.enviafib.back.controller.admin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleEditFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetFlowResultResponse;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleGetTransactionIdRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleStatus;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.user.AbstractFirmaUserController;
import es.caib.enviafib.back.controller.user.FluxFirmaUserController;
import es.caib.enviafib.back.controller.user.PlantillesDeFluxDeFirmesUserController;
import es.caib.enviafib.back.controller.webdb.UsuariController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.FitxerFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

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
