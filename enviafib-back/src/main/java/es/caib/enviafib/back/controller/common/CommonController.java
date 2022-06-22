package es.caib.enviafib.back.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
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

    @RequestMapping(value = "/home.html")
    public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

        if (initialized == null) {
            HtmlUtils.saveMessageInfo(request, I18NUtils.tradueix("common.benvinguda.missatge2"));
            session.setAttribute("inicialitzat", true);
        }

        return new ModelAndView("home");

    }

}
