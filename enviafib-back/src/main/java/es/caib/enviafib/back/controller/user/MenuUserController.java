package es.caib.enviafib.back.controller.user;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.persistence.MenuJPA;

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
    public String show(@PathVariable("menuID") long menuID, @PathVariable("tipus") int tipus,
            HttpServletRequest request, HttpServletResponse response) throws I18NException {

        // XYZ ZZZ Check si aquest usuair pot obrir el menuID

        MenuJPA menu = (MenuJPA) menuEjb.findByPrimaryKey(menuID);

        String idioma = LocaleContextHolder.getLocale().getLanguage();

        request.getSession().setAttribute(AbtractFirmaCarrecUserController.TITOL_PETICIO,
                menu.getTitolMenu().getTraduccio(idioma).getValor());

        switch (tipus) {

            case MENU_FIRMA_TIPUS_AUTOFIRMA:
                return "redirect:" + AutoFirmaUserController.CONTEXT_WEB + "/new";

            case MENU_FIRMA_TIPUS_PER_NIF:
                return "redirect:" + FirmaPerNifUserController.CONTEXT_WEB + "/new";

            case MENU_FIRMA_TIPUS_FLUX:
                return "redirect:" + FirmaFluxUserController.CONTEXT_WEB + "/crearflux";

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI:
                return "redirect:" + FirmaPlantillaFluxUserController.CONTEXT_WEB + "/new";

            case MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT:
                return "redirect:" + FirmaPlantillaFluxEntitatUserController.CONTEXT_WEB + "/new";

            case MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT:
                // XYZ ZZZ
                HtmlUtils.saveMessageError(request, "No implementat MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT");

                return "redirect:/";

            case MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON:
                // XYZ ZZZ
                HtmlUtils.saveMessageError(request, "No implementat MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON");
                return "redirect:/";

            case MENU_FIRMA_TIPUS_CARREC:
                //String tipusCarrecStr = menuEjb.executeQueryOne(MenuFields.PARAMETRECOMBO,
                //        MenuFields.MENUID.equal(menuID));

                String tipusCarrecStr = menu.getParametreCombo();

                switch (Integer.parseInt(tipusCarrecStr)) {

                    case CARREC_GERENT_PRESIDENT:
                        return "redirect:" + FirmaCarrecGerentPresidentUserController.CONTEXT_WEB + "/new";

                    case CARREC_CAP_AREA_CONSELLER:
                        return "redirect:" + FirmaCarrecCapAreaConsellerUserController.CONTEXT_WEB + "/new";

                    case CARREC_ENCARREGAT_COMPRES:
                        return "redirect:" + FirmaCarrecEncarregatCompresUserController.CONTEXT_WEB + "/new";

                    case CARREC_RECURSOS_HUMANS:
                        return "redirect:" + FirmaCarrecRecursosHumansUserController.CONTEXT_WEB + "/new";

                    case CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL:
                        return "redirect:" + FirmaCarrecDirectorUserController.CONTEXT_WEB + "/new";

                    case CARREC_SECRETARI:
                        return "redirect:" + FirmaCarrecSecretariUserController.CONTEXT_WEB + "/new";

                    case CARREC_ADDICIONAL_1:
                        return "redirect:" + FirmaCarrecAddicional1UserController.CONTEXT_WEB + "/new";

                    case CARREC_ADDICIONAL_2:
                        return "redirect:" + FirmaCarrecAddicional2UserController.CONTEXT_WEB + "/new";

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
