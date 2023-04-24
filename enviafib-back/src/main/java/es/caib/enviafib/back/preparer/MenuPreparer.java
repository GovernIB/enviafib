package es.caib.enviafib.back.preparer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.security.RunAs;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.log4j.Logger;
import org.apache.tiles.AttributeContext;
import org.apache.tiles.preparer.PreparerException;
import org.apache.tiles.preparer.ViewPreparer;
import org.apache.tiles.request.Request;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Component;

import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.MenuLogicaService;
import es.caib.enviafib.model.entity.Menu;

/**
 * @author anadal
 *
 */
@RunAs(Constants.EFI_USER)
@Component
public class MenuPreparer implements ViewPreparer {

    protected static final Logger log = Logger.getLogger(MenuPreparer.class);

    // NO FUNCIONA :-(  =>  @EJB(mappedName = MenuLogicaService.JNDI_NAME)
    protected static MenuLogicaService menuLogicaEjb;

    @Override
    public void execute(Request tilesRequest, AttributeContext attributeContext) throws PreparerException {

        Map<String, Object> request = tilesRequest.getContext("request");
        Object pipella = attributeContext.getAttribute("pipella");
    }

    protected boolean getAdditionalCondition(String pipella) {
        return pipella != null && pipella.equals("user");
    }

    public static List<Menu> getMenuUser() {
        List<Menu> menus;
        try {

            log.info("menuLogicaEjb => " + menuLogicaEjb);

            if (menuLogicaEjb == null) {
                menuLogicaEjb = (MenuLogicaService) new InitialContext().lookup(MenuLogicaService.JNDI_NAME);
            }

            menus = menuLogicaEjb.getAllOptionMenusByUsername(LoginInfo.getInstance().getUsuari().getUsuariID());
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
}
