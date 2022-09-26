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

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EnviaFIBPluginsManager;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author anadal
 *
 */
public abstract class AbtractFirmaCarrecUserController extends AbstractFirmaUserController {

    public static final String TITOL_PETICIO_CARREC = "__TITOL_PETICIO_CARREC__";

    public int getCarrec() {

        switch (getTipusPeticio()) {

            case TIPUS_PETICIO_CARREC_GERENT_PRESIDENT:
                return CARREC_GERENT_PRESIDENT;
            case TIPUS_PETICIO_CARREC_CAPAREA_CONSELLER:
                return CARREC_CAP_AREA_CONSELLER;
            case TIPUS_PETICIO_CARREC_CAPDEPARTAMENT_DIRECTOR:
                return CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL;
            case TIPUS_PETICIO_CARREC_SECRETARI:
                return CARREC_SECRETARI;
            case TIPUS_PETICIO_CARREC_ENCARREGAT_COMPRES:
                return CARREC_ENCARREGAT_COMPRES;
            case TIPUS_PETICIO_CARREC_RECURSOS_HUMANS:
                return CARREC_RECURSOS_HUMANS;
            case TIPUS_PETICIO_CARREC_ADDICIONAL_1:
                return CARREC_ADDICIONAL_1;
            case TIPUS_PETICIO_CARREC_ADDICIONAL_2:
                return CARREC_ADDICIONAL_2;

            default:
                return -1;

        }

    }

    @Override
    public String getTitolCode(HttpServletRequest request) {
        return "genapp.comodi";

    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        peticioForm.getHiddenFields().remove(DESTINATARINIF);

        peticioForm.setTitleParam((String) request.getSession().getAttribute(TITOL_PETICIO_CARREC));

        if (peticioForm.isNou()) {

            try {
                String[] carrecData = getCarrecNIF();

                String carrecNIF = carrecData[0];
                String carrec = carrecData[1];
                String carrecUsername = carrecData[2];
                String carrecName = carrecData[3];

                String msg = "El NIF del meu " + carrec + ", " + carrecName + " (" + carrecUsername + ") es: "
                        + carrecNIF;

                log.info(msg);
                peticioForm.setSubTitleCode("=" + carrecName + " (" + carrecUsername + ")");

                peticioForm.getPeticio().setDestinatariNif(carrecNIF);
                //                peticioForm.addReadOnlyField(PeticioFields.DESTINATARINIF);
                peticioForm.addHiddenField(PeticioFields.DESTINATARINIF);

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(LlistatPeticionsPendentsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }
        }
        return peticioForm;
    }

    public String[] getCarrecNIF() throws I18NException {

        String[] dadesCarrec = new String[4];
        /* dadesCarrec[0] = NIF
         * dadesCarrec[1] = carrec
         * dadesCarrec[2] = username
         * dadesCarrec[3] = name
         */

        int tipusCarrec = getCarrec();

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

        String carrec = null;
        String carrecUsername = null;
        try {
            String username = LoginInfo.getInstance().getUsername();

            carrec = "estructuraorganitzativa." + tipusCarrec + ".nom";
            switch (tipusCarrec) {
                case Constants.CARREC_GERENT_PRESIDENT:
                    carrecUsername = instance.getGerentPresidentUsername();
                break;
                case Constants.CARREC_CAP_AREA_CONSELLER:
                    carrecUsername = instance.getCapAreaConsellerUsername(username);
                break;
                case Constants.CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL:
                    carrecUsername = instance.getCapDepartamentDirectorGeneralUsername(username);
                break;
                case Constants.CARREC_SECRETARI:
                    carrecUsername = instance.getSecretariUsername(username);
                break;
                case Constants.CARREC_ENCARREGAT_COMPRES:
                    carrecUsername = instance.getEncarregatCompresUsername(username);
                break;
                case Constants.CARREC_RECURSOS_HUMANS:
                    carrecUsername = instance.getRecursosHumansUsername(username);
                break;
                case Constants.CARREC_ADDICIONAL_1:
                    carrecUsername = instance.getCarrec1Username(username);
                break;
                case Constants.CARREC_ADDICIONAL_2:
                    carrecUsername = instance.getCarrec2Username(username);
                break;

                default:
                    throw new I18NException("plugin.estructuraorganitzativa.noasignat", String.valueOf(tipusCarrec));
            }
        } catch (Exception e) {
            String error = e.getMessage();

            log.info("ERRROR ]" + e + "[");
            throw new I18NException("error.plugin.estructuraorganitzativa",
                    new I18NArgumentString(String.valueOf(tipusCarrec)), new I18NArgumentString(error));
        }

        log.info("El meu " + I18NUtils.tradueix(carrec) + " es " + carrecUsername);

        String carrecNIF;
        String carrecName;

        // Provam a BBDD a veure si està el NIF
        Long usuariID = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(carrecUsername));

        if (usuariID != null) {
            UsuariJPA usuariBD = usuariEjb.findByPrimaryKey(usuariID);

            carrecNIF = usuariBD.getNif();
            carrecName = usuariBD.getNom() + " " + usuariBD.getLlinatge1();
        } else {
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
            carrecName = infoCarrec.getFullName();
            if (carrecNIF == null) {
                throw new I18NException("error.logininfo", new I18NArgumentCode(UsuariFields.NIF.codeLabel),
                        new I18NArgumentCode(carrec), new I18NArgumentString(carrecUsername),
                        new I18NArgumentCode("nifisnull"));
            }
            log.info("El NIF del meu " + I18NUtils.tradueix(carrec) + " (" + carrecUsername + ") es: " + carrecNIF);

        }

        dadesCarrec[0] = carrecNIF;
        dadesCarrec[1] = I18NUtils.tradueix(carrec);
        dadesCarrec[2] = carrecUsername;
        dadesCarrec[3] = carrecName;
        return dadesCarrec;
    }

}
