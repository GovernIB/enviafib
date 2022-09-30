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
@RequestMapping(value = FirmaCarrecSecretariUserController.CONTEXT_WEB)

public class FirmaCarrecSecretariUserController extends AbtractFirmaCarrecUserController {

    public static final String CONTEXT_WEB = "/user/firmasecretari";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_CARREC_SECRETARI;
    }

}
