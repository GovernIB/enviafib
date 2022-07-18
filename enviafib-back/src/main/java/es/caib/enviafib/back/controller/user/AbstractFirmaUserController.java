package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.utils.Utils;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * Codi comú per formulari dels diferents tipus de peticions.
 * 
 * @author anadal
 *
 */
public abstract class AbstractFirmaUserController extends AbstractPeticioUserController {

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return true;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public String getTileForm() {
        return "peticioFormUser";
    }

    public abstract int getTipusPeticio();

    /**
     * Carregar el formulari per un nou Peticio
     */
    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearPeticioGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {
        try {
            ModelAndView mav = super.crearPeticioGet(request, response);
            return mav;
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
            return new ModelAndView("redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list");
        }
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

        hiddens.remove(NOM);
        hiddens.remove(FITXERID);
        hiddens.remove(TIPUSDOCUMENTAL);
        hiddens.remove(IDIOMADOC);

        if (__isView) {
            hiddens.remove(DATACREACIO);
            hiddens.remove(TIPUS);
            hiddens.remove(IDIOMAID);
            hiddens.remove(ESTAT);
            hiddens.remove(FITXERID);

            switch ((int) peticioForm.getPeticio().getEstat()) {

                case Constants.ESTAT_PETICIO_EN_PROCES:
                break;
                case Constants.ESTAT_PETICIO_ERROR:
                    hiddens.remove(ERRORMSG);
                    if (peticioForm.getPeticio().getErrorException() != null) {
                        hiddens.remove(ERROREXCEPTION);
                    }
                    hiddens.remove(DATAFINAL);

                break;
                case Constants.ESTAT_PETICIO_FIRMADA:
                    hiddens.remove(FITXERFIRMATID);
                    hiddens.remove(DATAFINAL);

                    Long infosignaturaID = peticioForm.getPeticio().getInfoSignaturaID();
                    peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info", "user.infosignatura",
                            "/user/infoSignatura/view/" + infosignaturaID, "btn-info"));

//                    new AdditionalButton("fas fa-file", "info.signatura",
//                            "/user/infoSignatura/view/" + peticio.getInfoSignaturaID(),
//                           )

                break;
            }

        }

//        if (peticioForm.getPeticio().getTipus() == Constants.TIPUS_PETICIO_AUTOFIRMA) {
//            hiddens.remove(REASON);
//        }
        
        peticioForm.setHiddenFields(hiddens);

        if (peticioForm.isNou()) {

            Peticio peticio = peticioForm.getPeticio();

            peticio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            peticio.setErrorMsg(
                    "XYZ ZZZ Error desconegut. El procés de creació de la petició de firma s'ha aturat de forma inesperada.");

            String userName = request.getRemoteUser();
            Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
            peticio.setSolicitantID(userId);

            peticio.setTipus(getTipusPeticio());

            // Amagam el selector d'idioma a la creacio de peticio. S'agafa el del context
            // autmaticament.
            peticio.setIdiomaID(LocaleContextHolder.getLocale().getLanguage());

            // Idioma per defecte per els documents, catala.
            peticio.setIdiomaDoc("ca");
        }

        return peticioForm;
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {
        return getRedirectToList();
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, PeticioForm peticioForm, Throwable __e) {

        if (__e == null) {
            return getRedirectToList();
        } else {
            return getTileForm();
        }
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long peticioID) {
        return getRedirectToList();
    }

    public static String getRedirectToList() {
        return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
    }

    protected String getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
        return Configuracio.getUrlBase() + webContext;
    }

    @RequestMapping(value = "/veureInfoSignatura/{infoSignaturaID}", method = RequestMethod.GET)
    public ModelAndView veureInfoSignatura(@PathVariable("infoSignaturaID")
    java.lang.Long infoSignaturaID, HttpServletRequest request, HttpServletResponse response) {

        if (infoSignaturaID == null) {
            HtmlUtils.saveMessageError(request, "Error. No hi ha informació d'aquesta signatura.");

        } else {
            InfoSignatura is = infoSignaturaEjb.findByPrimaryKey(infoSignaturaID);
            log.info("      -> InfoSignaturaID: " + is.getInfoSignaturaID());

            ModelAndView mav = new ModelAndView("detallsinfosignatura");
            mav.addObject("is", is);
            mav.addObject("contexte", getContextWeb());
            return mav;
        }

        ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        return mav;

    }

    @Override
    public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
            throws I18NException, I18NValidationException {
        PeticioJPA p = super.create(request, peticio);

        final int tipus = getTipusPeticio();
        if (tipus != TIPUS_PETICIO_AUTOFIRMA) {

            try {

                if (tipus == TIPUS_PETICIO_FLUX) {
                    peticioLogicaEjb.arrancarPeticioFlux(peticio.getPeticioID(),
                            LocaleContextHolder.getLocale().getLanguage(),
                            (FlowTemplateSimpleFlowTemplate) request.getSession().getAttribute("flux"));
                } else {
                    peticioLogicaEjb.arrancarPeticio(peticio.getPeticioID(),
                            LocaleContextHolder.getLocale().getLanguage());
                }
                // XYZ ZZZ TRA
                HtmlUtils.saveMessageSuccess(request, "Peticio amb Id: " + peticio.getPeticioID() + " enviada correctament.");
            } catch (I18NException e) {

                // XYZ ZZZ Error generic
                String error = I18NUtils.tradueix("error.flux.arrancar", I18NUtils.getMessage(e));
                log.error(error, e);

                p.setEstat(ESTAT_PETICIO_ERROR);
                p.setErrorMsg(error);
                p.setErrorException(Utils.stackTrace2String(e));

                peticioLogicaEjb.update(p);
            }
        }

        return p;
    }

}
