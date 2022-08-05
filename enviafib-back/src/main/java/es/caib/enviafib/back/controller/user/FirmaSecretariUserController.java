package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PluginFields;
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
                String secretariNIF = getSecretariNIF();
                if (secretariNIF == null) {
                    HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("user.error.secretarinotrobat"));
                } else {
                    peticioForm.getPeticio().setDestinatariNif(secretariNIF);
                    peticioForm.addReadOnlyField(PeticioFields.DESTINATARINIF);
                }
            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                HtmlUtils.saveMessageError(request, msg);
                log.error(msg, e);
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

    public String getSecretariNIF() throws I18NException {
        Long pluginID = pluginEstructuraOrganitzativaEjb.executeQueryOne(PluginFields.PLUGINID,
                PluginFields.ACTIU.equal(true));

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstanceByPluginID(pluginID);

        String username = LoginInfo.getInstance().getUsername();
        String secretari;
        try {
            secretari = instance.getSecretariByUsername(username);
        } catch (Exception e) {
            String msg = "Error obtenint NIF del secretari: " + e.getMessage();
            throw new I18NException("genapp.comodi", msg);
        }
        
//        String directorNIF = getNIFFromUsername(CapDepartamentDirectorGeneral);
        String secretariNIF;
        switch (secretari) {
            case "atrobat":
                secretariNIF = "45186147W";
            break;
            case "ellado":
                secretariNIF = "12345678Z";
            break;
            default:
                secretariNIF = "demoDNI";
            break;
        }
        return secretariNIF;
    }

}
