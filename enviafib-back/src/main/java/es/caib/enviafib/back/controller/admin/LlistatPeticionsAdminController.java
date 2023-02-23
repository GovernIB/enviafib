package es.caib.enviafib.back.controller.admin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
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
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/admin/peticio")
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class, PeticioMultipleForm.class })
public class LlistatPeticionsAdminController extends AbstractLlistatPeticionsController {

    
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
        }


        return peticioForm;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);

        if (peticioFilterForm.isNou()) {
            List<Field<?>> newGroupBy =  new ArrayList<Field<?>>(peticioFilterForm.getDefaultGroupByFields()); 
            newGroupBy.add(PeticioFields.ESTAT);
            peticioFilterForm.setGroupByFields(newGroupBy);
        }
        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {
        
        filterForm.getAdditionalButtonsByPK().clear();
        
        for (Peticio peticio : list) {
            long peticioID = peticio.getPeticioID();
            
            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", "peticio.btn.view", getContextWeb() + "/veurePeticioFull/" + peticioID, "btn-info"));
            
            if (peticio.getErrorMsg() != null) {
                filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-exclamation-circle", "peticio.btn.show.error", getContextWeb() + "/veurePeticioError/" + peticioID, "btn-danger"));
            }
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

}
