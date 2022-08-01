package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.GrupController;
import es.caib.enviafib.back.form.webdb.GrupForm;
import es.caib.enviafib.back.form.webdb.GrupFilterForm;
import es.caib.enviafib.model.entity.Grup;
import es.caib.enviafib.model.fields.GrupFields;
import es.caib.enviafib.persistence.GrupJPA;

/**
 * 
 * @author ptrias
 *
 */

@Controller
@RequestMapping(value = GrupUserController.CONTEXT_WEB)
@SessionAttributes(types = { Grup.class, Grup.class })
public class GrupUserController extends GrupController {

    public static final String CONTEXT_WEB = "/user/grup";
    
    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public String getTileForm() {
        return "grupFormUser";
    }

    @Override
    public String getTileList() {
        return "grupListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "grupUser_FilterForm";
    }

    @Override
    public GrupForm getGrupForm(GrupJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        GrupForm GrupForm = super.getGrupForm(_jpa, __isView, request, mav);
        return GrupForm;
    }

    @Override
    public GrupFilterForm getGrupFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        GrupFilterForm grupFilterForm = super.getGrupFilterForm(pagina, mav, request);

        if (grupFilterForm.isNou()) {
            grupFilterForm.addHiddenField(GrupFields.GRUPID);
            grupFilterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-user-plus", "grup.afegir.usuari",
                    getContextWeb() + "/afegirUsuariGrup/{0}", "btn-primary"));
        }
        return grupFilterForm;
    }

    @RequestMapping(value = "/afegirUsuariGrup/{grupID}")
    public String afegirUsuariGrup(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("grupID") long grupID) {

        request.getSession().setAttribute("GRUPID_DE_GRUPUSUARI_A_CREAR", grupID);

        return "redirect:" + GrupUsuariUserController.CONTEXT_WEB + "/new";
    }

}
