package es.caib.enviafib.back.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaCarrecRecursosHumansUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaCarrecRecursosHumansUserController extends AbtractFirmaCarrecUserController {

    public static final String CONTEXT_WEB = "/user/recursoshumans";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_CARREC_RECURSOS_HUMANS;
    }

}
