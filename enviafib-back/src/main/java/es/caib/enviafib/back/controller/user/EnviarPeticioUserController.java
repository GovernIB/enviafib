package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.GroupByItem;
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

import es.caib.enviafib.back.controller.FileDownloadController;
import es.caib.enviafib.back.controller.webdb.PeticioController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/user/peticio")
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class EnviarPeticioUserController extends PeticioController {

    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @Override
    public String getTileForm() {
        return "peticioFormUser";
    }

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
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);
        peticioForm.getPeticio().setDatacreacio(new Timestamp(System.currentTimeMillis()));
        peticioForm.getPeticio().setEstat(Constants.ESTAT_PETICIO_CREADA);
        peticioForm.addReadOnlyField(PeticioFields.DATACREACIO);
        String userName = request.getRemoteUser();
        Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
        peticioForm.getPeticio().setSolicitantID(userId);
        peticioForm.addReadOnlyField(SOLICITANTID);
        peticioForm.addHiddenField(FITXERFIRMATID);
        peticioForm.addHiddenField(PETICIOPORTAFIB);
        peticioForm.addHiddenField(ESTAT);
        return peticioForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav,
            PeticioFilterForm peticioFilterForm, List<Peticio> list, Map<Field<?>, GroupByItem> _groupByItemsMap,
            Where where) throws I18NException {
        if (peticioFilterForm.isHiddenField(ESTAT) && !peticioFilterForm.isGroupByField(ESTAT)) {
            return EMPTY_STRINGKEYVALUE_LIST;
        }
        Where _w = null;
        return getReferenceListForEstat(request, mav, Where.AND(where, _w));
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < Constants.ESTATS_PETICIO.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(Constants.ESTATS_PETICIO[i]),
                    I18NUtils.tradueix("estat." + Constants.ESTATS_PETICIO[i])));
        }
        return __tmp;
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
            peticioFilterForm.addHiddenField(PETICIOPORTAFIB);

        }
        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {

        // Mostrar boto per editar usuaris que poden veure les meves plantilles

        filterForm.getAdditionalButtonsByPK().clear();

        filterForm.setEditButtonVisible(false);
        filterForm.setDeleteButtonVisible(false);

        for (Peticio peticio : list) {
            long peticioID = peticio.getPeticioID();

            switch ((int) peticio.getEstat()) {
                case Constants.ESTAT_PETICIO_CREADA:
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-edit", "genapp.edit",
                            getContextWeb() + "/" + peticioID + "/edit/", "btn-warning"));
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    "btn-danger"));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-play",
                            "peticio.posarenmarxa", getContextWeb() + "/arrancar/" + peticioID, "btn-success"));
                break;

                case Constants.ESTAT_PETICIO_EN_PROCES:
                break;
                case Constants.ESTAT_PETICIO_FIRMADA: {
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    "btn-danger"));

                    long fitxerFirmatId = peticio.getFitxerFirmatID();
                    Fitxer file = fitxerEjb.findByPrimaryKey(fitxerFirmatId);

                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-file-download",
                            "descarregar_firma", FileDownloadController.fileUrl(file), "btn-warning"));
                }
                break;

                case Constants.ESTAT_PETICIO_REBUTJADA:
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    "btn-danger"));
                break;
            }
        }
    }

    @RequestMapping(value = "/arrancar/{peticioID}", method = RequestMethod.GET)
    public String arrancarPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioID") Long peticioID) {

        try {
            peticioLogicaEjb.arrancarPeticio(peticioID, LoginInfo.getInstance().getLanguage());
            HtmlUtils.saveMessageSuccess(request, "Peticio amb Id: " + peticioID + " enviada correctament.");
        } catch (LoginException e) {
            // TODO Auto-generated catch block
            String msg = "La sessio de l'usuari ha caducat.";
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (I18NException e) {
            // TODO Auto-generated catch block
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }
        log.info("Peticio enviada correctament.");

        return "redirect:" + getContextWeb() + "/list";
    }
}
