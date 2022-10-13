package es.caib.enviafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/common")
public class CommonController {

    @RequestMapping(value = "/home")
    public ModelAndView option1(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("home");
        return mav;

    }

}
