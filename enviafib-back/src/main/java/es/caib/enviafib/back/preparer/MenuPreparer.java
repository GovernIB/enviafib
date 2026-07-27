package es.caib.enviafib.back.preparer;

import javax.annotation.security.RunAs;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.springframework.stereotype.Component;

import es.caib.enviafib.commons.utils.Constants;

/**
 * @author anadal
 *
 */
@RunAs(Constants.EFI_USER)
@Component
public class MenuPreparer implements ViewPreparer {

    protected static final Logger log = Logger.getLogger(MenuPreparer.class);

    // NO FUNCIONA :-(  =>  @EJB(mappedName = MenuLogicaService.JNDI_NAME)
//    protected static MenuLogicaService menuLogicaEjb;
//    
////    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
//    protected static PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

        //Map<String, Object> request = tilesRequest.getContext("request");
        //Object pipella = attributeContext.getAttribute("pipella");
    }

    protected boolean getAdditionalCondition(String pipella) {
        return pipella != null && pipella.equals("user");
    }

//    public static List<Menu> getMenuUser() {
//        List<Menu> menus;
//        try {
//
//            log.info("menuLogicaEjb => " + menuLogicaEjb);
//
//            if (menuLogicaEjb == null) {
//                menuLogicaEjb = (MenuLogicaService) new InitialContext().lookup(MenuLogicaService.JNDI_NAME);
//            }
//
//            log.info("pluginEstructuraOrganitzativaEjb => " + pluginEstructuraOrganitzativaEjb);
//            if (pluginEstructuraOrganitzativaEjb == null) {
//                pluginEstructuraOrganitzativaEjb = (PluginEstructuraOrganitzativaLogicaService) new InitialContext().lookup(PluginEstructuraOrganitzativaLogicaService.JNDI_NAME);
//            }
//
//            String username = LoginInfo.getInstance().getUsuari().getUsername();
//            String dir3 = getCodiDIR3(username);
//
//            if (dir3 == null) {
//                menus = menuLogicaEjb.getOptionMenusSenseDir3();
//            }else {
//                menus = menuLogicaEjb.getAllOptionMenusByUsername(LoginInfo.getInstance().getUsuari().getUsuariID());
//            }
//            
//        } catch (LoginException e) {
//            log.error("Error intentant obtenir l'ID de l'usuari de BBDD: " + e.getMessage(), e);
//            menus = new ArrayList<Menu>();
//        } catch (I18NException e) {
//            log.error("Error intentant obtenir els menus de firma l'ID de l'usuari de BBDD: " + e.getMessage(), e);
//            menus = new ArrayList<Menu>();
//        } catch (NamingException e) {
//            log.error("Error intentant obtenir una instancia de MenuLogicaService:" + e.getMessage(), e);
//            menus = new ArrayList<Menu>();
//        }
//        return menus;
//    }
//    
//    public static String getCodiDIR3(String username) {
//
//        log.info("Aquest mètode es per cercar el dir3");
//        try {
//            IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();
//
//            String codiDIR3;
//            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
//            log.info("El codiDIR3 de " + username + " es: " + codiDIR3);
//            return codiDIR3;
//
//        } catch (Throwable e) {
//            log.error(e.getMessage(), e);
//            
//            log.info("No hem trobat el DIR3 de " + username);
//            return null;
//            //            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
//        }
//    }
}
