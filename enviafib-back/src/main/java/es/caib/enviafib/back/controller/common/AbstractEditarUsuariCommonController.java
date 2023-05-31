package es.caib.enviafib.back.controller.common;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
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
		if(userForm.getUsuari().getUsername() != null) {
		    userForm.addReadOnlyField(USERNAME);
		}
		
		String nif = userForm.getUsuari().getNif();
		        
		if(nif != null && nif.trim().length() > 0) {
            userForm.addReadOnlyField(NIF);
        }

        String username = userForm.getUsuari().getUsername();
        mav.addObject("elCodiDir3", getCodiDIR3(username));

        userForm.setAttachedAdditionalJspCode(true);
        
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
	
    public String getCodiDIR3(String username) throws I18NException {

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

        String codiDIR3;
        try {
            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
            log.info("El codiDIR3 de " + username + " es: " + codiDIR3);
            return codiDIR3;

        } catch (Exception e) {
            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
        }
    }
}
