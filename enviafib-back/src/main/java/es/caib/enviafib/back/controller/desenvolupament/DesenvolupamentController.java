package es.caib.enviafib.back.controller.desenvolupament;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.ejb.IdiomaService;
import es.caib.enviafib.persistence.IdiomaJPA;
import es.caib.enviafib.model.entity.Idioma;
import es.caib.enviafib.back.utils.Tab;

/**
 * 
 * @author anadal (u80067)
 * 16 jun 2026 10:15:00
 */

@MenuOption(
        labelCode = "=(ROLE USER) JSP no accessible per ROLE_USER (Error 403)",
        order = 10,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/webdb/annex/list/1",
        relativeLink = "")
@MenuOption(
        labelCode = "=(ROLE USER) METODE no accesible per ROLE_DEST (javax.ejb.EJBAccessException: Caller unauthorized)",
        order = 20,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/deletemethod",
        relativeLink = "")
@MenuOption(
        labelCode = "=(ROLE ADMIN) Hibernate Error",
        order = 30,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/hibernateerror",
        relativeLink = "")
@MenuOption(
        labelCode = "=(TOTHOM) ModelAndView noexisteix",
        order = 40,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/modelandviewnoexisteix",
        relativeLink = "")
@MenuOption(
        labelCode = "=(TOTHOM) Error Call Back",
        order = 50,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/errorcallback",
        relativeLink = "")
@MenuOption(
        labelCode = "=(TOTHOM) Invalid User",
        order = 60,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/errorinvaliduser",
        relativeLink = "")
@MenuOption(
        labelCode = "=(TOTHOM) ServletException",
        order = 70,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/servletexception",
        relativeLink = "")
@MenuOption(
        labelCode = "=(TOTHOM) Error en la vista (jsp)",
        order = 80,
        group = Tab.MENU_DESENVOLUPAMENT,
        baseLink = "/desenvolupament/jspexception",
        relativeLink = "")
@Tile(name = "errorjsp_desenvolupament", extendsTile = "desenvolupament", type = TileType.ANOTHER, 
     contentJsp = "/WEB-INF/jsp/webdb/menu_desenvolupament.jsp")
@Controller
public class DesenvolupamentController {

    @EJB(mappedName = IdiomaService.JNDI_NAME)
    private IdiomaService idiomaEjb;

    @RequestMapping(value = "/desenvolupament/deletemethod")
    public ModelAndView deleteMethod(HttpServletRequest request, HttpServletResponse response) throws Exception {

        IdiomaJPA i = new IdiomaJPA();
        i.setIdiomaID("eeeee");
        idiomaEjb.delete(i);

        return new ModelAndView("desenvolupament");
    }

    @RequestMapping(value = "/desenvolupament/hibernateerror")
    public ModelAndView hibernateError(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Idioma i = new IdiomaJPA();
        idiomaEjb.delete(i);

        return new ModelAndView("desenvolupament");
    }

    @RequestMapping(value = "/desenvolupament/errorcallback")
    public ModelAndView errorcallback(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean test = true;
        if (test) {
            throw new IllegalArgumentException("Either callerSubject or callerRunAs should be non-null");
        } else {
            return new ModelAndView("desenvolupament");
        }

    }

    @RequestMapping(value = "/desenvolupament/errorinvaliduser")
    public ModelAndView errorinvaliduser(HttpServletRequest request, HttpServletResponse response) throws Exception {
        boolean test = true;
        if (test) {
            throw new EJBAccessException("Invalid User");
        } else {
            return new ModelAndView("desenvolupament");
        }

    }

    @RequestMapping(value = "/desenvolupament/modelandviewnoexisteix")
    public ModelAndView modelandviewnoexisteix(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        return new ModelAndView("modelandviewnoexisteix");

    }

    @RequestMapping(value = "/desenvolupament/servletexception")
    public ModelAndView servletexception(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return new ModelAndView("redirect:/WEB-INF/jsp/webdb/menu_desenvolupament.jsp");
    }

    @RequestMapping(value = "/desenvolupament/jspexception")
    public ModelAndView jspexception(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("errorjsp_desenvolupament");
        mv.addObject("accio", "excepcio");
        return mv;
    }

}
