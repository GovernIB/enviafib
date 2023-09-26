package es.caib.enviafib.back.preparer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.request.Request;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.MenuLogicaService;
import es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService;
import es.caib.enviafib.model.entity.Menu;

/**
 * @author anadal
 *
 */
@RunAs(Constants.EFI_USER)
@Component
@Controller
public class CapPreparer extends MenuPreparer {

    protected final Logger log = Logger.getLogger(getClass());

    //	@EJB(mappedName = es.caib.enviafib.logic.MenuLogicaService.JNDI_NAME)
    protected MenuLogicaService menuLogicaEjb;

    //	@EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

        log.info("HOLA ESTAMOS AQUI:: CapPreparer");
        Map<String, Object> request = tilesRequest.getContext("request");

        Object pipella = attributeContext.getAttribute("pipella");
        //        super.execute(tilesRequest, attributeContext);
        request.put("url_sortida", Configuracio.getSortirURL());
        request.put("menus", getMenuUser());
    }

    public List<Menu> getMenuUser() {
        List<Menu> menus;
        try {

            log.info("menuLogicaEjb => " + menuLogicaEjb);

            if (menuLogicaEjb == null) {
                menuLogicaEjb = (MenuLogicaService) new InitialContext().lookup(MenuLogicaService.JNDI_NAME);
            }

            log.info("pluginEstructuraOrganitzativaEjb => " + pluginEstructuraOrganitzativaEjb);
            if (pluginEstructuraOrganitzativaEjb == null) {
                pluginEstructuraOrganitzativaEjb = (PluginEstructuraOrganitzativaLogicaService) new InitialContext()
                        .lookup(PluginEstructuraOrganitzativaLogicaService.JNDI_NAME);
            }

            String username = LoginInfo.getInstance().getUsuari().getUsername();
            String dir3 = getCodiDIR3(username);

            if (dir3 == null) {
                menus = menuLogicaEjb.getOptionMenusSenseDir3();
            } else {
                menus = menuLogicaEjb.getAllOptionMenusByUsername(LoginInfo.getInstance().getUsuari().getUsuariID());
            }

        } catch (LoginException e) {
            log.error("Error intentant obtenir l'ID de l'usuari de BBDD: " + e.getMessage(), e);
            menus = new ArrayList<Menu>();
        } catch (I18NException e) {
            log.error("Error intentant obtenir els menus de firma l'ID de l'usuari de BBDD: " + e.getMessage(), e);
            menus = new ArrayList<Menu>();
        } catch (NamingException e) {
            log.error("Error intentant obtenir una instancia de MenuLogicaService:" + e.getMessage(), e);
            menus = new ArrayList<Menu>();
        }
        return menus;
    }

    public String getCodiDIR3(String username) {

        log.info("Aquest mètode es per cercar el dir3");
        try {
            IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

            String codiDIR3;
            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
            log.info("El codiDIR3 de " + username + " es: " + codiDIR3);
            return codiDIR3;

        } catch (Throwable e) {
            log.error(e.getMessage(), e);

            log.info("No hem trobat el DIR3 de " + username);
            return null;
            //            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
        }
    }
}
