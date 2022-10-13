package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NArgument;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.user.FirmaPerFluxFirmaSimpleUserController;
import es.caib.enviafib.back.controller.webdb.MenuController;
import es.caib.enviafib.back.form.webdb.MenuFilterForm;
import es.caib.enviafib.back.form.webdb.MenuForm;
import es.caib.enviafib.back.security.LoginInfo;
/**
 * 
 * @author anadal
 *
 */
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.persistence.MenuJPA;

@Controller
@RequestMapping(value = "/admin/menu")
@SessionAttributes(types = { MenuForm.class, MenuFilterForm.class })
public class MenuAdminController extends MenuController implements Constants {
    
    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public String getTileForm() {
        return "menuFormAdmin";
    }

    @Override
    public String getTileList() {
        return "menuListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "MenuAdmin_FilterForm";
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int id : Constants.MENU_FIRMA_TIPUS) {
            __tmp.add(new StringKeyValue("" + id, I18NUtils.tradueix("menu.firma." + id)));
        }

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForParametreCombo(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int id : Constants.CARRECS) {
            __tmp.add(new StringKeyValue("" + id, I18NUtils.tradueix("estructuraorganitzativa." + id + ".nom")));
        }

        return __tmp;
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearMenuGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {
        if (request.getParameter("menuID") == null) {
            return new ModelAndView("noumenu");
        } else {
            return super.crearMenuGet(request, response);
        }
    }

    @Override
    public MenuFilterForm getMenuFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        MenuFilterForm menuFilterForm = super.getMenuFilterForm(pagina, mav, request);
        if (menuFilterForm.isNou()) {

            menuFilterForm.addHiddenField(MENUID);
            menuFilterForm.addHiddenField(TITOLMENUID);
            menuFilterForm.addHiddenField(AJUDAMENUID);
            menuFilterForm.addHiddenField(GRUPID);
            menuFilterForm.addHiddenField(PARAMETRECOMBO);
            menuFilterForm.addHiddenField(PARAMETRETEXT);
            
            
            menuFilterForm.setOrderBy(ORDRE.javaName);
        }
        return menuFilterForm;
    }

    @Override
    public MenuForm getMenuForm(MenuJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        MenuForm menuForm = super.getMenuForm(_jpa, __isView, request, mav);

        MenuJPA menu = menuForm.getMenu();

        if (menuForm.isNou()) {

            Integer menuID = null;
            try {
                menuID = Integer.parseInt(request.getParameter("menuID"));
            } catch (Exception e) {
                mav.setView(new RedirectView(getContextWeb() + "/new", true));
                return menuForm;
            }

            menu.setTipus(menuID);

        }

        menuForm.setTitleCode("menu.firma." + menu.getTipus());
        menuForm.addHiddenField(TIPUS);

        switch (menu.getTipus()) {

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI:
                menu.setDescripcio(
                        "L'usuari seleccionarà entre les seves plantilles de flux de firmes. Això implica que podrà crear Flux de Firma que només veurà ell.");
            case MENU_FIRMA_TIPUS_AUTOFIRMA:
            case MENU_FIRMA_TIPUS_PER_NIF:
            case MENU_FIRMA_TIPUS_FLUX:
                menuForm.addHiddenField(PARAMETRECOMBO);
                menuForm.addHiddenField(PARAMETRETEXT);
            break;

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT:
                // XYZ ZZZ
                // Falta Omplir PARAMETRETEXT amb llistat de IDs de 
                menuForm.addHiddenField(PARAMETRECOMBO);
                menu.setParametreText("# Aquí anirà la llista de CODIS de de PLANTILLES DE L'ENTITAT. \n"
                        + "# L'Administrador triarà quines vol que vegi l'usuari.\n"
                        + "# Si no en defineix cap llavors es mostraran totes.\n");
            break;

            case MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT:
                menuForm.addHiddenField(PARAMETRECOMBO);
                if (menuForm.isNou()) {
                  menu.setParametreText(FirmaPerFluxFirmaSimpleUserController.AJUDA);
                }

            break;

            case MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON:
                menuForm.addHiddenField(PARAMETRECOMBO);
                menu.setParametreText("#  Per exemple es podrà crear un flux amb el cap i el gerent."
                        + "# Crear codis de substitucions. El format serà:");
            break;

            case MENU_FIRMA_TIPUS_CARREC:
                menuForm.addHiddenField(PARAMETRETEXT);
            break;

            default:
                // XYZ ZZZ ERROR 

        }

        return menuForm;
    }

    
    
    @Override
    public void postValidate(HttpServletRequest request, MenuForm menuForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, menuForm, result);
        
       if ( menuForm.getMenu().getTipus() == MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT) {
        
            final String fluxSimple = menuForm.getMenu().getParametreText();
            
            final IEstructuraOrganitzativaPlugin plugin = pluginEstructuraOrganitzativaEjb.getInstance();
            
            final String loginUsername =  LoginInfo.getInstance().getUsername();

            try {
                // Només serveix per fer CHECK
                FirmaPerFluxFirmaSimpleUserController.getFluxFromFluxSimple(fluxSimple, plugin, loginUsername);    
            } catch (I18NException e) {
                
                String msg = I18NUtils.getMessage(e);
                
                log.error(msg, e);
                
                String code = e.getTraduccio().getCode();
                
                I18NArgument[] arguments = e.getTraduccio().getArgs();
                

                Object[] params = new Object[arguments.length];
                
                for (int i = 0; i < arguments.length; i++) {
                    if (arguments[i] instanceof I18NArgumentCode) {
                        params[i] = I18NUtils.tradueix(arguments[i].getValue());
                    } else {
                        params[i] = arguments[i].getValue();
                    }
                }

                result.rejectValue(get(PARAMETRETEXT), code, params, null);
            }
            
       }
        
        
        
    }
    
}
