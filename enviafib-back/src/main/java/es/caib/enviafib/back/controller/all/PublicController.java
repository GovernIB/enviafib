package es.caib.enviafib.back.controller.all;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.utils.Tab;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.StaticVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * @author anadal (u80067)
 * 25 sept 2026 13:38:42
 */
@Tile(
        name = "acessibilitat",
        extendsTile = Tab.MENU_PUBLIC_AND_COMMON,
        type = TileType.ANOTHER,
        contentJsp = "/WEB-INF/jsp/all/acessibilitat.jsp")
@Controller
public class PublicController {

    protected final Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "/public/versio")
    public void versio(HttpServletResponse response) throws Exception {
        response.getWriter().write(StaticVersion.VERSION);
        response.getWriter().flush();
        response.getWriter().close();
    }

    @RequestMapping(value = "/public/accessibilitat")
    public ModelAndView accessibilitat(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("acessibilitat");
        mav.addObject("backurl", Configuracio.getUrlBase());
        return mav;
    }
}
