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
@RequestMapping(value = FirmaPlantillaFluxEntitatUserController.CONTEXT_WEB)

public class FirmaPlantillaFluxEntitatUserController extends FirmaPlantillaFluxUserController {

    public static final String OWNER_PLANTILLES_DE_LA_ENTITAT = "+*enviafib*+";

    public static final String CONTEXT_WEB = "/user/firmaplantillafluxentitat";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT;
    }

    @Override
    public String getOwner() {
        return OWNER_PLANTILLES_DE_LA_ENTITAT;
    }
}
