package es.caib.enviafib.back.controller.user;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.FileDownloadController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * @author fbosch
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = LlistatPeticionsUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsUserController extends AbstractPeticioUserController {

    public static final String CONTEXT_WEB = "/user/peticio";

    public static final String codi_enmarxa = "peticio.btn.posarenmarxa";
    public static final String codi_delete = "peticio.btn.delete";
    public static final String codi_edit = "peticio.btn.edit";
    public static final String codi_view = "peticio.btn.view";
    public static final String codi_download = "peticio.btn.download";
    public static final String codi_email = "peticio.btn.sendmail";

    @Override
    public String getTileList() {
        return "peticioListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PeticioUser_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        String userName = request.getRemoteUser();
        Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
        return PeticioFields.SOLICITANTID.equal(userId);
    }

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        if (peticioFilterForm.isNou()) {
            peticioFilterForm.setVisibleExportList(false);
            peticioFilterForm.addHiddenField(SOLICITANTID);
            peticioFilterForm.addHiddenField(PETICIOID);
            peticioFilterForm.addHiddenField(FITXERFIRMATID);
            peticioFilterForm.addHiddenField(PETICIOPORTAFIRMES);
            peticioFilterForm.addHiddenField(IDIOMADOC);
            peticioFilterForm.addHiddenField(TIPUSDOCUMENTAL);
            peticioFilterForm.addHiddenField(IDIOMAID);
            peticioFilterForm.addHiddenField(ERROREXCEPTION);

            peticioFilterForm.addHiddenField(ERRORMSG);
            peticioFilterForm.addHiddenField(INFOSIGNATURAID);
            peticioFilterForm.addHiddenField(DESTINATARINIF);
            peticioFilterForm.addHiddenField(FITXERID);

            peticioFilterForm.setOrderBy(DATACREACIO.javaName);
            peticioFilterForm.setOrderAsc(false);

            peticioFilterForm.setAttachedAdditionalJspCode(true);

            peticioFilterForm.setAddButtonVisible(false);
            peticioFilterForm.setEditButtonVisible(false);
            peticioFilterForm.setDeleteButtonVisible(false);
        }
        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {

        // Mostrar boto per editar usuaris que poden veure les meves plantilles

        filterForm.getAdditionalButtonsByPK().clear();

        for (Peticio peticio : list) {
            long peticioID = peticio.getPeticioID();

            switch (peticio.getTipus()) {
                case Constants.TIPUS_PETICIO_NIF:

                    switch ((int) peticio.getEstat()) {
                        case Constants.ESTAT_PETICIO_CREADA:
                            filterForm.addAdditionalButtonByPK(peticioID,
                                    new AdditionalButton("fas fa-edit", codi_edit,
                                            getContextWebByTipus(peticio.getTipus()) + "/" + peticioID + "/edit/",
                                            "btn-primary"));
                            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-play",
                                    codi_enmarxa, getContextWeb() + "/arrancar/" + peticioID, "btn-success"));
                            filterForm
                                    .addAdditionalButtonByPK(peticioID,
                                            new AdditionalButton("fas fa-trash icon-white", codi_delete,
                                                    "javascript: openModal('" + request.getContextPath()
                                                            + getContextWeb() + "/" + peticioID + "/delete','show')",
                                                    "btn-danger"));
                        break;
                        case Constants.ESTAT_PETICIO_EN_PROCES:
                            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", codi_view,
                                    getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));
                        break;
                        case Constants.ESTAT_PETICIO_FIRMADA: {
                            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", codi_view,
                                    getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));

                            Fitxer file = peticio.getFitxerFirmat();

                            filterForm.addAdditionalButtonByPK(peticioID,
                                    new AdditionalButton("fas fa-envelope icon-white", codi_email,
                                            "javascript: cridaEmail(" + peticioID + ")", "btn-success"));

                            if (peticio.getInfoSignaturaID() != null) {
                                filterForm.addAdditionalButtonByPK(peticioID,
                                        new AdditionalButton("fas fa-file", "info.signatura",
                                                "/user/infoSignatura/view/" + peticio.getInfoSignaturaID(),
                                                "btn-info"));
                            }
                            filterForm
                                    .addAdditionalButtonByPK(peticioID,
                                            new AdditionalButton("fas fa-trash icon-white", codi_delete,
                                                    "javascript: openModal('" + request.getContextPath()
                                                            + getContextWeb() + "/" + peticioID + "/delete','show')",
                                                    "btn-danger"));
                        }
                        break;

                        case Constants.ESTAT_PETICIO_ERROR:
                            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", codi_view,
                                    getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));
                            filterForm
                                    .addAdditionalButtonByPK(peticioID,
                                            new AdditionalButton("fas fa-trash icon-white", codi_delete,
                                                    "javascript: openModal('" + request.getContextPath()
                                                            + getContextWeb() + "/" + peticioID + "/delete','show')",
                                                    "btn-danger"));
                        break;
                    }

                break;
                case Constants.TIPUS_PETICIO_AUTOFIRMA:

                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", codi_view,
                            getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash icon-white", codi_delete, "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    "btn-danger"));

                break;
                case Constants.TIPUS_PETICIO_FLUX:

                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", codi_view,
                            getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash icon-white", codi_delete, "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    "btn-danger"));

                break;
                case Constants.TIPUS_PETICIO_DIRECTOR:
                break;
                case Constants.TIPUS_PETICIO_SECRETARI:
                break;
            }
        }
    }

    /**
     * 
     * @param tipus
     * @return
     */
    protected String getContextWebByTipus(int tipus) {
        String cw = firmaPathByTipus.get(tipus);
        if (cw == null) {
            throw new RuntimeException("S'ha de registrar el tipus de peticio " + tipus
                    + " en el bloc static {}  de la classe " + AbstractPeticioUserController.class.getSimpleName());
        }
        return cw;
    }

    @RequestMapping(value = "/enviaremail/{peticioId}/{email}/{windowUrl}", method = RequestMethod.GET)
    public String enviarEmail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") long peticioId, @PathVariable("email") String email,
            @PathVariable("windowUrl") String windowUrl) {
        boolean isHTML = true;

        // Decodificam el email arriba en base64
        String decodedEmail = new String(Base64.getDecoder().decode(email));

        // Decodificam la URL que arriba en base64
        String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));
        URL urlTmp = null;
        try {
            urlTmp = new URL(decodedUrl);

            // Recuperacio del fitxer firmat a partir del ID de peticio
            Peticio peticio = peticioEjb.findByPrimaryKey(peticioId);
            Fitxer file = fitxerEjb.findByPrimaryKey(peticio.getFitxerFirmatID());
            int port = urlTmp.getPort();

            String fileUrl = urlTmp.getProtocol() + "://" + urlTmp.getHost()
                    + ((port == -1) ? "" : (":" + urlTmp.getPort())) + request.getContextPath()
                    + FileDownloadController.fileUrl(file);

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nomFitxer", file.getNom());
            map.put("fileUrl", fileUrl);

            String subject = I18NUtils.tradueix("email.download.file.subject");
            String message = "<h4>" + I18NUtils.tradueix("email.download.file.title") + "</h4>" + "<div>" + "<p>"
                    + I18NUtils.tradueix("email.download.file.message") + "<br/><a href='${fileUrl}'>"
                    + I18NUtils.tradueix("email.download.file.linktext") + "</a>" + "</p>" + "</div>";

            subject = TemplateEngine.processExpressionLanguage(subject, map);
            message = TemplateEngine.processExpressionLanguage(message, map);

            EmailUtil.postMail(subject, message, isHTML, Configuracio.getAppEmail(), decodedEmail);
            String successMsg = "S'ha enviat el email correctament";
            HtmlUtils.saveMessageSuccess(request, successMsg);

        } catch (MalformedURLException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.url");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (IOException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.message");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (Exception e) {
            String msg = I18NUtils.tradueix("email.download.file.error.send");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/arrancar/{peticioID}", method = RequestMethod.GET)
    public String arrancarPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioID") Long peticioID) {

        try {
            peticioLogicaEjb.arrancarPeticio(peticioID, LoginInfo.getInstance().getLanguage());
            HtmlUtils.saveMessageSuccess(request, "Peticio amb Id: " + peticioID + " enviada correctament.");
        } catch (LoginException e) {
            String msg = "La sessio de l'usuari ha caducat.";
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }
        log.info("Peticio enviada correctament.");

        return "redirect:" + getContextWeb() + "/list";
    }

    @Override
    public void delete(HttpServletRequest request, Peticio peticio) throws I18NException {
        peticioLogicaEjb.deleteFull(peticio);
    }

}
