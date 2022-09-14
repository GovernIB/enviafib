package es.caib.enviafib.back.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    protected final Logger log = Logger.getLogger(getClass());

    @RequestMapping(value = "/home")
    public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        log.info("BUENAS TARDES, DAMAS Y CABALLEROS, BIENVENIDOS A TIEMPO DE JUEGO!");

        ModelAndView mav = new ModelAndView("home");
        return mav;

    }

}
