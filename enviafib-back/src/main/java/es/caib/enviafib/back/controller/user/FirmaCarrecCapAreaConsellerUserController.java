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
@RequestMapping(value = FirmaCarrecCapAreaConsellerUserController.CONTEXT_WEB)

public class FirmaCarrecCapAreaConsellerUserController extends AbtractFirmaCarrecUserController {

    public static final String CONTEXT_WEB = "/user/capareaconseller";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_CARREC_CAPAREA_CONSELLER;
    }

}
