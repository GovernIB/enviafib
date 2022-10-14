package es.caib.enviafib.back.controller.user;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.caib.enviafib.back.security.LoginInfo;
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

    protected final Logger log = Logger.getLogger(MenuUserController.class);

    @EJB(mappedName = es.caib.enviafib.ejb.MenuService.JNDI_NAME)
    protected es.caib.enviafib.ejb.MenuService menuEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @RequestMapping(value = "/show/{menuID}/{tipus}", method = RequestMethod.GET)
    public String show(@PathVariable("menuID") long menuID, @PathVariable("tipus") int tipus,
            HttpServletRequest request, HttpServletResponse response) {

        // XYZ ZZZ Check si aquest usuari té permís per obrir el menuID

        try {

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

                case MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT: {
                    final String fluxSimple = menu.getParametreText();

                    final IEstructuraOrganitzativaPlugin plugin = pluginEstructuraOrganitzativaEjb.getInstance();

                    final String loginUsername = LoginInfo.getInstance().getUsername();

                    final FirmaAsyncSimpleSignatureBlock[] blocs;

                    blocs = FirmaPerFluxFirmaSimpleUserController.getFluxFromFluxSimple(fluxSimple, plugin,
                            loginUsername);

                    request.getSession().setAttribute(FirmaPerFluxFirmaSimpleUserController.FLUX_SIMPLE_SESSION_KEY,
                            blocs);

                    return "redirect:" + FirmaPerFluxFirmaSimpleUserController.CONTEXT_WEB + "/new";
                }

                case MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON: {

                    final String loginUsername = LoginInfo.getInstance().getUsername();

                    final IEstructuraOrganitzativaPlugin plugin = pluginEstructuraOrganitzativaEjb.getInstance();

                    String fluxJson = menu.getParametreText();

                    final FirmaAsyncSimpleSignatureBlock[] blocs = FirmaPerFluxFirmaJsonUserController
                            .checkFluxJson(loginUsername, plugin, fluxJson);

                    request.getSession().setAttribute(FirmaPerFluxFirmaJsonUserController.FLUX_JSON_SESSION_KEY, blocs);

                    return "redirect:" + FirmaPerFluxFirmaJsonUserController.CONTEXT_WEB + "/new";
                }

                case MENU_FIRMA_TIPUS_CARREC:

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

                default: {
                    // XYZ ZZZ TRA ERROR 
                    String msg = "Tipus de Peticio de Firma " + tipus + " No implementada dins MenuUserController.";
                    HtmlUtils.saveMessageError(request, msg);
                    log.error(msg);
                    return "redirect:/";
                }

            }

        } catch (I18NException i18ne) {
            String msg = I18NUtils.getMessage(i18ne);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, i18ne);
            return "redirect:/";

        } catch (Exception e) {
            //XYZ ZZZ TRA
            String msg = "Error desconegut intentant redirigir al menu corresponent (menuID= " + menuID + ")(tipus="
                    + tipus + ")";
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
            return "redirect:/";
        }

    }

}
