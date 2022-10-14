package es.caib.enviafib.back.controller.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaPerFluxFirmaJsonUserController.CONTEXT_WEB)
public class FirmaPerFluxFirmaJsonUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/fluxfirmajson";

    public static final String FLUX_JSON_SESSION_KEY = "__FLUX_JSON_SESSION_KEY__";

    @Override
    public int getTipusPeticio() {
        return TIPUS_PETICIO_FLUX_JSON;
    }

    public static FirmaAsyncSimpleSignatureBlock[] checkFluxJson(final String loginUsername,
            final IEstructuraOrganitzativaPlugin plugin, String fluxJson) throws I18NException {
        //  Cerca entrades del tipus ${carrec}
        Pattern regexp = Pattern.compile("(\\$\\{[A-Za-z0-9]+\\})");
        Matcher matcher = regexp.matcher(fluxJson);

        while (matcher.find()) {

            String dollarCarrec = matcher.group();
            String username = FirmaPerFluxFirmaSimpleUserController.getUsernameOfCarrec(plugin, loginUsername,
                    dollarCarrec);

            fluxJson = fluxJson.replace(dollarCarrec, username);
        }

        try {
            final FirmaAsyncSimpleSignatureBlock[] blocs;
            blocs = new Gson().fromJson(fluxJson, FirmaAsyncSimpleSignatureBlock[].class);
            return blocs;
        } catch (Exception e) {
            // XYZ ZZZ TRA
            throw new I18NException("genapp.comodi",
                    "Error parsejant el codi JSON: [" + e.getMessage() + "]. Revisi que està ben escrit i formatejat.");
        }

    }

}
