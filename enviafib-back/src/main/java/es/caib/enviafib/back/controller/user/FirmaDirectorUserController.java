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

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
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
@RequestMapping(value = FirmaDirectorUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaDirectorUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/firmadirector";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(DESTINATARINIF);

        if (peticioForm.isNou()) {

            try {
                String directorNIF = getDirectorNIF();
                peticioForm.getPeticio().setDestinatariNif(directorNIF);
                peticioForm.addReadOnlyField(PeticioFields.DESTINATARINIF);

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                HtmlUtils.saveMessageWarning(request, msg);
                HtmlUtils.saveMessageWarning(request, I18NUtils.tradueix("user.error.directornotrobat"));
            }

            peticioForm.setTitleCode("title.firma.director");
            peticioForm.addLabel(PeticioFields.DESTINATARINIF, "firmadirector.destinatarinif");

        }

        return peticioForm;

    }

    @Override
    public int getTipusPeticio() {
        return TIPUS_PETICIO_DIRECTOR;
    }

    public String getDirectorNIF() throws I18NException {

        Long pluginID = pluginEstructuraOrganitzativaEjb.executeQueryOne(PluginFields.PLUGINID,
                PluginFields.ACTIU.equal(true));
        
        if (pluginID == null) {
            throw new I18NException("error.plugin.estructuraorganitzativa.noactiu", "Director");
        }

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstanceByPluginID(pluginID);

        String CapDepartamentDirectorGeneral;
        try {
            String username = LoginInfo.getInstance().getUsername();
            CapDepartamentDirectorGeneral = instance.getCapDepartamentDirectorGeneralByUsername(username);
        } catch (Exception e) {
            throw new I18NException("error.plugin.estructuraorganitzativa", "Director", e.getMessage());
        }
        log.info("El meu cap es: " + CapDepartamentDirectorGeneral);

        String directorNIF;

        // Provam a BBDD a veure si està el NIF
        directorNIF = usuariEjb.executeQueryOne(UsuariFields.NIF,
                UsuariFields.USERNAME.equal(CapDepartamentDirectorGeneral));
        if (directorNIF != null) {
            return directorNIF;
        }

        // Si no hi es, provam amb Plugin de UserInformation
        IUserInformationPlugin plugin = EnviaFIBPluginsManager.getUserInformationPluginInstance();
        UserInfo infoDirector;
        try {
            infoDirector = plugin.getUserInfoByUserName(CapDepartamentDirectorGeneral);
            log.info("infoDirector :" + infoDirector);
            directorNIF = infoDirector.getAdministrationID();

        } catch (Exception e) {
            throw new I18NException("error.logininfo.usuarinotfound", CapDepartamentDirectorGeneral);
        }

        if (directorNIF == null) {
            throw new I18NException("error.logininfo.NIFnotfound", CapDepartamentDirectorGeneral);
        }
        log.info("El NIF del meu cap es: " + directorNIF);
        return directorNIF;
    }
}
