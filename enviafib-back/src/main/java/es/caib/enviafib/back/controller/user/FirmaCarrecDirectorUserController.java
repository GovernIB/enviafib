package es.caib.enviafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import es.caib.enviafib.commons.utils.Constants;


/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaCarrecDirectorUserController.CONTEXT_WEB)

public class FirmaCarrecDirectorUserController extends AbtractFirmaCarrecUserController {

    public static final String CONTEXT_WEB = "/user/firmadirector";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_CARREC_CAPDEPARTAMENT_DIRECTOR;
    }

}
