package es.caib.enviafib.back.controller.user;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.MenuFields;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/user/menu")
public class MenuUserController implements Constants {

    @EJB(mappedName = es.caib.enviafib.ejb.MenuService.JNDI_NAME)
    protected es.caib.enviafib.ejb.MenuService menuEjb;

    @RequestMapping(value = "/show/{menuID}/{tipus}", method = RequestMethod.GET)
    public String show(@PathVariable("menuID")
    long menuID, @PathVariable("tipus")
    int tipus, HttpServletRequest request, HttpServletResponse response) throws I18NException {

        // XYZ ZZZ Check si aquest usuair pot obrir el menuID

        switch (tipus) {

            case MENU_FIRMA_TIPUS_AUTOFIRMA:
                return "redirect:/user/autofirma/new";

            case MENU_FIRMA_TIPUS_PER_NIF:
                return "redirect:/user/firmapernif/new";

            case MENU_FIRMA_TIPUS_FLUX:
                return "redirect:/user/flux/crearflux";

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI:
                return "redirect:/user/firmaplantillafluxusuari/new";

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT:
                return "redirect:/user/firmaplantillafluxentitat/new";

            case MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT:
                // XYZ ZZZ
                HtmlUtils.saveMessageError(request, "No implementat MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT");

                return "redirect:/";

            case MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON:
                // XYZ ZZZ
                HtmlUtils.saveMessageError(request, "No implementat MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON");
                return "redirect:/";

            case MENU_FIRMA_TIPUS_CARREC:
                String tipusCarrecStr = menuEjb.executeQueryOne(MenuFields.PARAMETRECOMBO,
                        MenuFields.MENUID.equal(menuID));
                switch (Integer.parseInt(tipusCarrecStr)) {

                    case CARREC_GERENT_PRESIDENT:
                        // XYZ ZZZ
                        HtmlUtils.saveMessageError(request, "No implementada firma per càrrec GERENT/PRESIDENT");
                        return "redirect:/";

                    case CARREC_CAP_AREA_CONSELLER:
                        // XYZ ZZZ
                        HtmlUtils.saveMessageError(request, "No implementada firma per càrrec AP AREA/CONSELLER");
                        return "redirect:/";

                    case CARREC_ENCARREGAT_COMPRES:
                        // XYZ ZZZ
                        HtmlUtils.saveMessageError(request, "No implementada firma per càrrec ENCARREGAT DE COMPRES");
                        return "redirect:/";

                    case CARREC_RECURSOS_HUMANS:
                        // XYZ ZZZ
                        HtmlUtils.saveMessageError(request, "No implementada firma per càrrec RECURSOS_HUMANS");
                        return "redirect:/";

                    case CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL:
                        return "redirect:/user/firmadirector/new";

                    case CARREC_SECRETARI:
                        return "redirect:/user/firmasecretari/new";

                    default:
                        // XYZ ZZZ
                        HtmlUtils.saveMessageError(request,
                                "No s'ha trobat el càrrec amb ID = ]" + tipusCarrecStr + "[");
                        return "redirect:/";

                }

            default:

                // XYZ ZZZ ERROR 
                HtmlUtils.saveMessageError(request,
                        "Tipus de Peticio de Firma " + tipus + " No implementada dins MenuUserController.");
                return "redirect:/";

        }

    }

}
