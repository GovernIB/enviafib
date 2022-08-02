package es.caib.enviafib.back.controller.user;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Select;
import org.fundaciobit.genapp.common.query.SelectConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.webdb.GrupUsuariController;
import es.caib.enviafib.back.form.webdb.GrupUsuariFilterForm;
import es.caib.enviafib.back.form.webdb.GrupUsuariForm;
import es.caib.enviafib.back.form.webdb.UsuariRefList;
import es.caib.enviafib.model.fields.GrupUsuariFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.GrupUsuariJPA;

/**
 * 
 * @author ptrias
 *
 */

@Controller
@RequestMapping(value = GrupUsuariUserController.CONTEXT_WEB)
@SessionAttributes(types = { GrupUsuariForm.class, GrupUsuariFilterForm.class })
public class GrupUsuariUserController extends GrupUsuariController {

    public static final String CONTEXT_WEB = "/user/grupusuari";

    private static final String GRUPID_DE_GRUPUSUARI_A_CREAR = "GRUPID_DE_GRUPUSUARI_A_CREAR";

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
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
        return false;
    }

    @Override
    public String getTileForm() {
        return "grupUsuariFormUser";
    }

    @Override
    public GrupUsuariForm getGrupUsuariForm(GrupUsuariJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        GrupUsuariForm grupUsuariForm = super.getGrupUsuariForm(_jpa, __isView, request, mav);

        // if (grupUsuariForm.isNou()) {

        log.info("Hola, hauria de sortir aquest missatge");
        Long grupID = (Long) request.getSession().getAttribute(GRUPID_DE_GRUPUSUARI_A_CREAR);

        if (grupID == null) {
            mav.setView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list"));
        }
        log.info("GRUPID_DE_GRUPUSUARI_A_CREAR: " + grupID);

        grupUsuariForm.getGrupUsuari().setGrupID(grupID);
        grupUsuariForm.addReadOnlyField(GrupUsuariFields.GRUPID);

        // }

        return grupUsuariForm;
    }

    @RequestMapping(value = "/afegirUsuariGrup/{grupID}")
    public String afegirUsuariGrup(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("grupID") Long grupID) {

        request.getSession().setAttribute(GRUPID_DE_GRUPUSUARI_A_CREAR, grupID);
        return "redirect:" + getContextWeb() + "/new";
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, GrupUsuariForm grupUsuariForm) {
        return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupUsuariID) {
        return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
    }

    @PostConstruct
    public void init() {
        usuariRefList = new UsuariRefList(usuariRefList);

        usuariRefList.setSelects(
          new Select<?>[] { 
              UsuariFields.NOM.select, 
              UsuariFields.LLINATGE1.select,  
              new SelectConstant("-"),
              UsuariFields.NIF.select
          });
        usuariRefList.setSeparator(" ");
    }
}
