package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EnviaFIBPluginsManager;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PluginFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaSecretariUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaSecretariUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/firmasecretari";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(DESTINATARINIF);

        if (peticioForm.isNou()) {

            try {
                
                String secretariNIF = getCarrecNIF(Constants.CARREC_SECRETARI);
                peticioForm.getPeticio().setDestinatariNif(secretariNIF);
                peticioForm.addReadOnlyField(PeticioFields.DESTINATARINIF);

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }

            peticioForm.setTitleCode("title.firma.secretari");
            peticioForm.addLabel(PeticioFields.DESTINATARINIF, "firmasecretari.destinatarinif");

        }

        return peticioForm;

    }

    @Override
    public int getTipusPeticio() {
        return TIPUS_PETICIO_SECRETARI;
    }


}
