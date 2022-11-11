package es.caib.enviafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/common/usuarinou")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class EditarUsuariNouCommonController extends AbstractEditarUsuariCommonController {

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public UsuariForm getUsuariForm(UsuariJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        UsuariForm userForm = super.getUsuariForm(_jpa, __isView, request, mav);

        userForm.setCancelButtonVisible(false);
        userForm.setDeleteButtonVisible(false);
        if (userForm.getUsuari().getUsername() != null) {
            userForm.addReadOnlyField(USERNAME);
        }

        String nif = userForm.getUsuari().getNif();

        if (nif != null && nif.trim().length() > 0) {
            userForm.addReadOnlyField(NIF);
        }

        Usuari usuari = LoginInfo.getInstance().getUsuari();
        UsuariJPA usuariJPA = new UsuariJPA(usuari);
        userForm.setUsuari(usuariJPA);

        return userForm;
    }

    @Override
    public String getTileForm() {
        return "userFormCommon";
    }

    @RequestMapping(value = "/{usuariID}/check", method = RequestMethod.GET)
    public String checkUsuari(@PathVariable("usuariID") java.lang.Long usuariID, HttpServletRequest request,
            HttpServletResponse response) throws I18NException {
        log.info("XYZ ZZZ ENTRANT A USER CHECK");
        Usuari usuari = LoginInfo.getInstance().getUsuari();

        String redirectString;
        log.info("XYZ ZZZ usuariID = " + usuari.getUsuariID());
        log.info("XYZ ZZZ usuariNIF = " + usuari.getNif());
        if (usuari.getUsuariID() == 0 || usuari.getNif() == null) {
            redirectString = "redirect:" + getContextWeb() + "/new";
        } else {
            redirectString = "redirect:" + getContextWeb() + "/" + usuariID + "/edit";
        }
        return redirectString;
    }
    
    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, UsuariForm usuariForm) {
        return "redirect:/";
    }

}
