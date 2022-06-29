package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * Coid comú per formulari dels diferents tipus de peticions.
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
        return true;
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

                case Constants.ESTAT_PETICIO_CREADA:
                break;
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
                break;
            }

        }

        peticioForm.setHiddenFields(hiddens);

        if (peticioForm.isNou()) {

            Peticio peticio = peticioForm.getPeticio();

            peticio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_CREADA);

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

        return request.getScheme() + "://" + request.getServerName() + ":" + +request.getServerPort()
                + request.getContextPath() + webContext;
    }

}
