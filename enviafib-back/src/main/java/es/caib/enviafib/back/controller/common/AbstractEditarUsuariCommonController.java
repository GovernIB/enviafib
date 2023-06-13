package es.caib.enviafib.back.controller.common;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.UsuariController;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author fbosch
 *
 */

public abstract class AbstractEditarUsuariCommonController extends UsuariController {

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
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

        log.info("Prova per veure si l'error està aqui");
        if (!userForm.isNou()) {
            log.info("No es un nou usuari");
            String username = userForm.getUsuari().getUsername();
            String dir3 = getCodiDIR3(request, username);
            log.info("dir3: " + dir3);
            if (dir3 != null) {
                mav.addObject("elCodiDir3", dir3);
            }
            userForm.setAttachedAdditionalJspCode(true);
        }
        return userForm;
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
        if (__e == null) {
            return "redirect:/";
        } else {
            return getTileForm();
        }
    }

    public String getCodiDIR3(HttpServletRequest request, String username) {

        log.info("Aquest mètode es per cercar el dir3");
        try {
            IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

            String codiDIR3;
            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
            log.info("El codiDIR3 de " + username + " es: " + codiDIR3);
            return codiDIR3;

        } catch (Throwable e) {
            HtmlUtils.saveMessageError(request, "No hem trobat el DIR3 d'aques usuari");
            return null;
            //            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
        }
    }
}
