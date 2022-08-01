package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.GrupUsuariController;
import es.caib.enviafib.back.form.webdb.GrupUsuariFilterForm;
import es.caib.enviafib.back.form.webdb.GrupUsuariForm;
import es.caib.enviafib.model.entity.GrupUsuari;
import es.caib.enviafib.model.fields.GrupUsuariFields;
import es.caib.enviafib.persistence.GrupUsuariJPA;

/**
 * 
 * @author ptrias
 *
 */

@Controller
@RequestMapping(value = GrupUsuariUserController.CONTEXT_WEB)
@SessionAttributes(types = { GrupUsuari.class, GrupUsuari.class })
public class GrupUsuariUserController extends GrupUsuariController {

    public static final String CONTEXT_WEB = "/user/grupusuari";

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
        return "grupUsuariFormUser";
    }

    @Override
    public String getTileList() {
        return "grupUsuariListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "grupUsuariUser_FilterForm";
    }

    @Override
    public GrupUsuariForm getGrupUsuariForm(GrupUsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        GrupUsuariForm grupUsuariForm = super.getGrupUsuariForm(_jpa, __isView, request, mav);
        
        long grupID = (long) request.getSession().getAttribute("GRUPID_DE_GRUPUSUARI_A_CREAR");
        grupUsuariForm.getGrupUsuari().setGrupID(grupID);
        grupUsuariForm.addReadOnlyField(GrupUsuariFields.GRUPID);

        return grupUsuariForm;
    }

    public String getRedirectWhenCreated(HttpServletRequest request, GrupUsuariForm grupUsuariForm) {
      return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list/1";
    }

    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupUsuariID) {
        return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list/1";
    }
    
}
