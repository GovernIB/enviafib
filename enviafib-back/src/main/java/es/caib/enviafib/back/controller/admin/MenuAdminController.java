package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.webdb.MenuController;
import es.caib.enviafib.back.form.webdb.MenuFilterForm;
import es.caib.enviafib.back.form.webdb.MenuForm;
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
                menu.setParametreText("# Aquí anirà la llista de CODIS de de PLANTILLES DE L'ENTITAT. \r\n"
                        + "# L'Administrador triarà quines vol que vegi l'usuari.\r\n"
                        + "# Si no en defineix cap llavors es mostraran totes.\r\n");
            break;

            case MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT:
                menuForm.addHiddenField(PARAMETRECOMBO);
                menu.setParametreText("# Es definirà el flux emprant la següent nomenclatura:\r\n"
                        + "# Cada Fila representa un bloc de firmes\r\n"
                        + "# Els usuaris de cada bloc es separarà pel caracter '|' o '&'\r\n"
                        + "# El usuaris es definiran pel seu username\r\n"
                        + "# Els càrrecs es definiran emprant tags Freemarker (XYZ ZZZ FALTA EL llistat\r\n"
                        + "#    Exemple:\r\n" + "#        anadal|ptrias\r\n" + "#        atrobat&fbosh\r\n"
                        + "#        ${secretari}\r\n" + "#        ${gerentpresident}\r\n" + "#\r\n"
                        + "#    Primer anadal o ptrias, despres atrobat i fbosch (ordre indistint),\r\n"
                        + "#    despres secretari i finalment gerent.");

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

}
