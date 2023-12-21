package es.caib.enviafib.back.controller.admin;

import java.util.Arrays;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsController;
import es.caib.enviafib.back.controller.user.MenuUserController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.form.webdb.PeticioMultipleForm;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = LlistatPeticionsAdminController.CONTEXT)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class, PeticioMultipleForm.class })
public class LlistatPeticionsAdminController extends AbstractLlistatPeticionsController {

    public static final String CONTEXT = "/admin/peticio";
    
    public static final String VISTA_FULL_VIEW = "fullView";
    public static final String VISTA_ERROR = "error";
    
    @Override
    public String getTileForm() {
        return "peticioFormAdmin";
    }

    @Override
    public String getTileList() {
        return "peticioListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "peticioListAdmin_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        //        String userName = request.getRemoteUser();
        //        Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
        //        return PeticioFields.SOLICITANTID.equal(userId);

        return super.getAdditionalCondition(request);
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
        return true;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

        peticioForm.setTitleParam((String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO));

        String tipusVista = (String) request.getSession().getAttribute("tipusVista");

        if (__isView) {
            
            if (tipusVista.equals(VISTA_FULL_VIEW)) {
                hiddens.clear();
            }else if(tipusVista.equals(VISTA_ERROR)){
                hiddens.remove(NOM);
                hiddens.remove(DATACREACIO);
                hiddens.remove(DATAFINAL);
                hiddens.remove(ESTAT);
                hiddens.remove(FITXERID);

                hiddens.remove(PeticioFields.ERRORMSG);
                hiddens.remove(PeticioFields.ERROREXCEPTION);
                
            }
            peticioForm.setHiddenFields(hiddens);

            Long peticioID = peticioForm.getPeticio().getPeticioID();
            Long annexes = infoAnexEjb.count(PETICIOID.equal(peticioID));
            if (annexes > 0) {
                peticioForm.addAdditionalButton(new AdditionalButton("fas fa-folder-open", "user.veureannexes",
                        "/admin/infoAnnex/mostrarAnnexes/" + peticioID + "/toForm", "btn-info"));
                request.getSession().setAttribute("myContext", getContextWebByTipus(peticioForm.getPeticio().getTipus()) );

            }
            Long infosignaturaID = peticioForm.getPeticio().getInfoSignaturaID();
            if (infosignaturaID != null) {
                peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info", "user.infosignatura",
                        "/admin/infoSignatura/view/" + infosignaturaID, "btn-info"));
            }
            Long infoArxiuID = peticioForm.getPeticio().getInfoArxiuID();
            if (infoArxiuID != null) {
                peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info-circle", "user.infoarxiu",
                        "/admin/infoArxiu/view/" + infoArxiuID, "btn-info"));
            }

        }


        return peticioForm;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);

        if (peticioFilterForm.isNou()) {
        }
        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {
        
        filterForm.getAdditionalButtonsByPK().clear();
        
        int numErrorsArxivant = 0;
        
        for (Peticio peticio : list) {
            long peticioID = peticio.getPeticioID();
            
            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", "peticio.btn.view", getContextWeb() + "/veurePeticioFull/" + peticioID, "btn-info"));
            
            
            if (peticio.getEstat() == Constants.ESTAT_PETICIO_ERROR_ARXIVANT) {
                numErrorsArxivant++;
            }

            if (peticio.getEstat() == Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT) {
                filterForm.addAdditionalButtonByPK(peticioID,
                        new AdditionalButton("fas fa-redo-alt ", "arxiu.reintentartancamentexpedient",
                                "javascript:reintentarTancamentExpedient(" + peticioID + ")", "btn-warning"));
            }
            if (peticio.getEstat() == Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT) {
                filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-redo-alt ",
                        "arxiu.tancar.expedient", "javascript:tancarExpedient(" + peticioID + ")", "btn-warning"));
            }            
            
            
            if (peticio.getErrorMsg() != null) {
                filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-exclamation-circle", "peticio.btn.show.error", getContextWeb() + "/veurePeticioError/" + peticioID, "btn-danger"));
            }
        }
        
        if (numErrorsArxivant > 0) {
            log.info("numErrorsArxivant=" +numErrorsArxivant + " size()=" + list.size());
        }
        
        filterForm.getAdditionalButtons().clear();
        boolean botonOculto = false;
        if (!botonOculto  && numErrorsArxivant > 0 && numErrorsArxivant == list.size()) {
            request.getSession().setAttribute("PETICIONS_REINTENTAR_ARXIU", list);
            filterForm.addAdditionalButton(new AdditionalButton("fas fa-cogs icon-white", "peticio.arxiu.reintentar.tots",
                    "javascript: reintentarArxivarTotes()",
                    "btn-warning"));
        }
        super.postList(request, mav, filterForm, list);
    }

    @RequestMapping(value = "/veurePeticioFull/{peticioId}", method = RequestMethod.GET)
    public String veurePeticioFull(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId) {
        request.getSession().setAttribute("tipusVista", VISTA_FULL_VIEW);
        return "redirect:" + getContextWeb() + "/view/" + peticioId;
    }

    @RequestMapping(value = "/veurePeticioError/{peticioId}", method = RequestMethod.GET)
    public String veurePeticioError(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId) {
        request.getSession().setAttribute("tipusVista", VISTA_ERROR);
        return "redirect:" + getContextWeb() + "/view/" + peticioId;
    }

    @RequestMapping(value = "/reintentarArxivarTotes/{windowUrl}", method = RequestMethod.GET)
    public String reintentarArxivarTotes(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("windowUrl") String windowUrl) {   
        try {
            log.info("VAMOS A REINTENTAR ARXIVAR TODAS");

            List<Peticio> list = (List<Peticio>) request.getSession().getAttribute("PETICIONS_REINTENTAR_ARXIU");

            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));
            String url = Configuracio.getUrlBase(decodedUrl, request.getContextPath());

            String languageUI = LocaleContextHolder.getLocale().getLanguage();

            long timeout = (60 + 30) * 1000; //Minut i mig en ms

            long start = System.currentTimeMillis();
            long end = System.currentTimeMillis();

            Integer peticionsArxivadesOk = 0;
            Integer peticionsPendents = list.size();
            
            for (int i = 0; end - start < timeout && i < list.size(); i++) {
                Peticio peticio = list.get(i);

                long peticioID = peticio.getPeticioID();
                long infoSignaturaID = peticio.getInfoSignaturaID();

                log.info("Intentarem arxivar la peticio amb ID=" + peticioID);
                String msg = peticioLogicaEjb.reintentGuardarPeticioArxiu(peticioID, infoSignaturaID, languageUI, url);
                peticionsPendents--;

                end = System.currentTimeMillis();
                long seg = (end - start) / 1000;
                if (msg == null) {
                    log.info("PeticioLogicaEJB:: FINAL: PeticioID " + peticioID + " arxivada al segon " + seg + "\n");
                    peticionsArxivadesOk++;
                } else {
                    log.info("PeticioLogicaEJB:: FINAL: Error arxivant PeticioID " + peticioID + " al segon " + seg + "\n");
                    HtmlUtils.saveMessageError(request, msg);
                }
            }

            if (peticionsArxivadesOk > 0) {
                HtmlUtils.saveMessageSuccess(request,
                        I18NUtils.tradueix("peticio.arxiu.reintentar.tots.success", peticionsArxivadesOk.toString()));
            }
            
            if (peticionsPendents > 0 ) {
                HtmlUtils.saveMessageWarning(request,
                        I18NUtils.tradueix("peticio.arxiu.reintentar.tots.incomplet", peticionsPendents.toString()));
            }
            
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list/";
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

}
