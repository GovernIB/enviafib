package es.caib.enviafib.back.controller.aden;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.admin.LlistatPeticionsAdminController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.form.webdb.PeticioMultipleForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = LlistatPeticionsAdenController.CONTEXT)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class, PeticioMultipleForm.class })
public class LlistatPeticionsAdenController extends LlistatPeticionsAdminController {

    public static final String CONTEXT = "/aden/peticio";
    
    @Override
    public String getTileForm() {
        return "peticioFormAden";
    }

    @Override
    public String getTileList() {
        return "peticioListAden";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "peticioListAden_FilterForm";
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    	
		String entitatID = LoginInfo.getInstance().getEntitatRolsActual().getEntitat().getEntitatid();
    	
    	//Filtram per entitat. A la taula peticio tenim usuariid, i a la taula usuari tenim l'entitat.
    	
    	//SELECT * FROM peticio WHERE solicitantid IN (SELECT usuariid FROM usuari WHERE entitatid = 'entitatId');
		List<Long> usuarisEntitat = usuariLogicaEjb.executeQuery(UsuariFields.USUARIID,
				UsuariFields.ENTITATID.equal(entitatID));
    	
    	Where wEntitat = PeticioFields.SOLICITANTID.in(usuarisEntitat);

        return Where.AND(super.getAdditionalCondition(request), wEntitat);
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
        return peticioForm;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        return peticioFilterForm;
    }

	@Override
	public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
			throws I18NException {
		super.postList(request, mav, filterForm, list);
	}

    @Override
    public boolean isAdmin() {
        return false;
    }

}
