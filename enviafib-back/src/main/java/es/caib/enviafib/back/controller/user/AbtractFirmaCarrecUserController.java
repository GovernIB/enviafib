package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsUserController;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EnviaFIBPluginsManager;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

public abstract class AbtractFirmaCarrecUserController extends AbstractFirmaUserController {

    public abstract int getCarrec();

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(DESTINATARINIF);

        if (peticioForm.isNou()) {

            try {
                String carrecNIF = getCarrecNIF();
                peticioForm.getPeticio().setDestinatariNif(carrecNIF);
                peticioForm.addReadOnlyField(PeticioFields.DESTINATARINIF);

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(AbstractLlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }
        }
        return peticioForm;
    }

    public String getCarrecNIF() throws I18NException {

        int tipusCarrec = getCarrec();

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

        String carrec = null;
        String carrecUsername = null;
        try {
            String username = LoginInfo.getInstance().getUsername();

            switch (tipusCarrec) {
                case Constants.CARREC_GERENT_PRESIDENT:
                    carrec = "estructuraorganitzativa.gerent.nom";
                    carrecUsername = instance.getGerentPresident();
                break;
                case Constants.CARREC_CAP_AREA_CONSELLER:
                    carrec = "estructuraorganitzativa.caparea.nom";
                    carrecUsername = instance.getCapAreaConsellerByUsername(username);
                break;
                case Constants.CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL:
                    carrec = "estructuraorganitzativa.capdepartament.nom";
                    carrecUsername = instance.getCapDepartamentDirectorGeneralByUsername(username);
                break;
                case Constants.CARREC_SECRETARI:
                    carrec = "estructuraorganitzativa.secretari.nom";
                    carrecUsername = instance.getSecretariByUsername(username);
                break;
                case Constants.CARREC_ENCARREGAT_COMPRES:
                    carrec = "estructuraorganitzativa.encarregatcompres.nom";
                    carrecUsername = instance.getEncarregatCompresByUsername(username);
                break;
                case Constants.CARREC_RECURSOS_HUMANS:
                    carrec = "estructuraorganitzativa.recursoshumans.nom";
                    carrecUsername = instance.getRecursosHumansByUsername(username);
                break;

                default:

                break;
            }
        } catch (Exception e) {
            String error = e.getMessage();

            log.info("ERRROR ]" + e + "[");
            throw new I18NException("error.plugin.estructuraorganitzativa", new I18NArgumentCode(carrec),
                    new I18NArgumentString(error));
        }

        log.info("El meu " + I18NUtils.tradueix(carrec) + " es: " + carrecUsername);

        if (carrecUsername == null) {
            throw new I18NException("plugin.estructuraorganitzativa.noasignat", new I18NArgumentCode(carrec));
        }
        String carrecNIF;

        // Provam a BBDD a veure si està el NIF
        carrecNIF = usuariEjb.executeQueryOne(UsuariFields.NIF, UsuariFields.USERNAME.equal(carrecUsername));
        if (carrecNIF != null) {
            return carrecNIF;
        }

        // Si no hi es, provam amb Plugin de UserInformation
        IUserInformationPlugin plugin = EnviaFIBPluginsManager.getUserInformationPluginInstance();
        UserInfo infoCarrec;
        try {
            infoCarrec = plugin.getUserInfoByUserName(carrecUsername);
            log.info("infoCarrec: ]" + infoCarrec + "[");
            if (infoCarrec == null) {
                throw new Exception(I18NUtils.tradueix("userinfoisnull"));
            }

        } catch (Exception e) {
            String error = e.getMessage();
            throw new I18NException("error.logininfo.usuarinotfound", new I18NArgumentCode(carrec),
                    new I18NArgumentString(carrecUsername), new I18NArgumentString(error));
        }

        carrecNIF = infoCarrec.getAdministrationID();
        if (carrecNIF == null) {
            throw new I18NException("error.logininfo", new I18NArgumentCode(UsuariFields.NIF.codeLabel),
                    new I18NArgumentCode(carrec), new I18NArgumentString(carrecUsername),
                    new I18NArgumentCode("nifisnull"));
        }
        log.info("El NIF del meu " + I18NUtils.tradueix(carrec) + " (" + carrecUsername + ") es: " + carrecNIF);
        return carrecNIF;
    }

}
