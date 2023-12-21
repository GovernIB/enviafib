package es.caib.enviafib.back.controller.user;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.form.FilterFormData;
import org.fundaciobit.genapp.common.web.form.LogicForBaseFilterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractPeticioUserController;
import es.caib.enviafib.back.controller.FileDownloadController;
import es.caib.enviafib.back.controller.admin.InfoAnnexAdminController;
import es.caib.enviafib.back.controller.webdb.InfoAnexController;
import es.caib.enviafib.back.form.webdb.InfoAnexFilterForm;
import es.caib.enviafib.back.form.webdb.InfoAnexForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoAnex;
import es.caib.enviafib.persistence.InfoAnexJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = InfoAnnexUserController.CONTEXT_WEB)
@SessionAttributes(types = { InfoAnexForm.class, InfoAnexFilterForm.class })
public class InfoAnnexUserController extends InfoAnexController {

    public static final String CONTEXT_WEB = "/user/infoAnnex";

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.InfoAnexService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoAnexService infoAnnexLogicEjb;

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
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
        return true;
    }

    @Override
    public String getTileForm() {
        return "infoAnexFormUser";
    }

    @Override
    public String getTileList() {
        return "infoAnexListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "infoAnexUser_FilterForm";
    }

    @Override
    public InfoAnexForm getInfoAnexForm(InfoAnexJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        InfoAnexForm infoAnexForm;

        infoAnexForm = super.getInfoAnexForm(_jpa, __isView, request, mav);

        return infoAnexForm;
    }

    @Override
    public InfoAnexFilterForm getInfoAnexFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        InfoAnexFilterForm infoAnexFilterForm;

        infoAnexFilterForm = super.getInfoAnexFilterForm(pagina, mav, request);
        
        String returnUrl =  (String) request.getSession().getAttribute("returnUrl");
        log.info("FilterForm:: returnUrl: " +returnUrl);
        
        if (infoAnexFilterForm.isNou()) {
            
            
            String peticioIDStr = (String) request.getSession().getAttribute("peticioID");
            Long peticioID = Long.parseLong(peticioIDStr);
            PeticioJPA peticio = peticioLogicaEjb.findByPrimaryKey(peticioID);
            
            infoAnexFilterForm.setTitleCode("llistat.annexos");
            infoAnexFilterForm.setTitleParam("<b><i> '" + peticio.getNom()  + "' (" + peticioIDStr + ") </b></i> ");

//            infoAnexFilterForm.setTitleParam("(" + peticioIDStr + "): <i>" + peticio.getNom() + "</i>");
            
            
            infoAnexFilterForm.setAddButtonVisible(false);
            infoAnexFilterForm.setDeleteButtonVisible(false);
            infoAnexFilterForm.setDeleteSelectedButtonVisible(false);
            infoAnexFilterForm.setEditButtonVisible(false);

            infoAnexFilterForm.addHiddenField(INFOANEXID);
            infoAnexFilterForm.addHiddenField(PETICIOID);
            infoAnexFilterForm.addLabel(ANEXID, "infoAnex.infoAnex.plural");
            
            infoAnexFilterForm.addAdditionalButton(new AdditionalButton("fas fa-arrow-left", "tornar", returnUrl, "btn-secondary"));
            
            infoAnexFilterForm.setAttachedAdditionalJspCode(true);
        }else {
            infoAnexFilterForm.getAdditionalButtons().get(0).setLink(returnUrl);
        }

        return infoAnexFilterForm;
    }

    public boolean isAdmin() {
        return false;
    }

    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        final Where defaultCondition = super.getAdditionalCondition(request);

        String peticioIDStr = (String) request.getSession().getAttribute("peticioID");

        log.info("peticioID: " + peticioIDStr);
        Long peticioID = Long.parseLong(peticioIDStr);

        return Where.AND(defaultCondition, PETICIOID.equal(peticioID));
    }

    @RequestMapping(value = "/mostrarAnnexes/{peticioID}/{returnTo}", method = RequestMethod.GET)
    public String mostrarAnnexes(HttpServletRequest request, @PathVariable("peticioID") java.lang.Long peticioID, @PathVariable("returnTo") java.lang.String returnTo) {
        log.info("holaaaa. PeticioID=" + peticioID + ". returnTo: " + returnTo);
        request.getSession().setAttribute("peticioID", peticioID + "");
        
        String returnUrl;
        
        if (returnTo.equals("toList")) {
            returnUrl = "/" + (isAdmin()? "admin" : "user") + "/peticio/list/1";
        }else if (returnTo.equals("toForm")){
            PeticioJPA peticio = peticioLogicaEjb.findByPrimaryKey(peticioID);
            String  contextWeb = AbstractPeticioUserController.firmaPathByTipus.get(peticio.getTipus());
            returnUrl = (isAdmin()? "/admin/peticio" : contextWeb) +  "/view/" + peticioID;
        }else {
            returnUrl = "/canviarPipella/user/" + returnTo;
        }

        log.info("mostrarAnnexes::returnUrl: " +returnUrl);
        request.getSession().setAttribute("returnUrl", returnUrl);
        String sesinonReturnUrl = (String) request.getSession().getAttribute("returnUrl");
        log.info("sesinonReturnUrl: " + sesinonReturnUrl);
        
        
        if (isAdmin()) {
            return "redirect:" + InfoAnnexAdminController.CONTEXT_WEB + "/list";
        }else {
            return "redirect:" + InfoAnnexUserController.CONTEXT_WEB + "/list";
        }
    }
    
    public void postList(HttpServletRequest request, ModelAndView mav, InfoAnexFilterForm filterForm,  List<InfoAnex> list) throws I18NException {
        
        super.postList(request, mav, filterForm, list);
        
        filterForm.getAdditionalButtonsByPK().clear();
        List<Fitxer> annexes = new ArrayList<Fitxer>();
        
        for (int i = 0; i< list.size(); i ++) {
            InfoAnex infoAnex = list.get(i);
            Long fitxerID = infoAnex.getAnexID();
            Fitxer fitxer = fitxerEjb.findByPrimaryKey(fitxerID);
            annexes.add(fitxer);
            String urlFitxer = "/enviafibback" + FileDownloadController.fileUrl(fitxer);
            fitxer.setDescripcio(urlFitxer);
            if (i == 0) {
                mav.addObject("urlFitxer", urlFitxer);
            }
            log.info("url : " + urlFitxer);
            
            filterForm.addAdditionalButtonByPK(infoAnex.getInfoanexid(), 
                    new AdditionalButton("fas fa-eye", "showannex", "javascript:mostrarDiv('" + i + "')",
                            "btn-primary"));
        }
        
        mav.addObject("annexes", annexes);
    }    
    

}
