package es.caib.enviafib.back.controller.all;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.caib.enviafib.commons.utils.StaticVersion;

import javax.servlet.http.HttpServletResponse;

@Controller
public class PublicController {

	protected final Logger log = Logger.getLogger(getClass());

	@RequestMapping(value = "/public/versio")
    public void versio(HttpServletResponse response)
            throws Exception {
        response.getWriter().write(StaticVersion.VERSION);
        response.getWriter().flush();
        response.getWriter().close();
    }
}
