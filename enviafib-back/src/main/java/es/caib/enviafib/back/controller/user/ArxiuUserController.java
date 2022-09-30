package es.caib.enviafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = ArxiuUserController.CONTEXT_WEB)

public class ArxiuUserController {
    
    public static final String CONTEXT_WEB = "/user/arxiu";


}
