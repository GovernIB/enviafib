package es.caib.enviafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractPlantillaDeFluxDeFirmesController;
import es.caib.enviafib.back.controller.user.FirmaFluxUserController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.model.entity.Usuari;

@Controller
@RequestMapping(value = "/admin/netejarplantilles")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class NetejarPlantillesDeFluxAdminController extends AbstractPlantillaDeFluxDeFirmesController {

    @Override
    public String getEntityNameCode() {
        return "plantillesfluxfirmes.obsolet";
    }

    @Override
    public String getEntityNameCodePlural() {
        return "plantillesfluxfirmes.obsolet.plural";
    }

    @Override
    public String getTileList() {
        return "plantillesfluxfirmesListAdmin";
    }

    @Override
    public FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(String languageUI) {

        FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
        filter.setLanguageUI(languageUI);
        // Cercam per usuari aplicació i despres ja cercarem per {temporal=true}
        filter.setDescriptionFilter(FirmaFluxUserController.getFluxFilterByUserName(null));

        return filter;
    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

        if (usuariFilterForm.isNou()) {
            usuariFilterForm.setTitleCode("llistatplantillesflux.netejar");
            usuariFilterForm.setAttachedAdditionalJspCode(true);
        }

        return usuariFilterForm;
    }

    @Override
    public List<Usuari> executeSelect(ITableManager<Usuari, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        List<Usuari> plantilles = super.executeSelect(ejb, where, orderBy, itemsPerPage, inici);
        List<Usuari> caducades = new ArrayList<Usuari>();

        for (Usuari plantilla : plantilles) {

            String description = plantilla.getLlinatge1();

            {

                Long creationDate = getCreationDateLong(description);
                Long currentTime = System.currentTimeMillis();

                long limit = 3 * 1000 * 3600;

                if (currentTime - creationDate > limit) {
                    caducades.add(plantilla);
                }
            }
        }

        return caducades;
    }

    @Override
    public Boolean onlyAcceptTemplates() {
        // Només Temporals
        return false;
    }
}
