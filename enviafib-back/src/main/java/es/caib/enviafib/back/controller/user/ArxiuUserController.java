package es.caib.enviafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ArxiuUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class ArxiuUserController {
    
    public static final String CONTEXT_WEB = "/user/arxiu";


}
