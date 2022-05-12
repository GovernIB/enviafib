package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.List;

import javax.ejb.EJB;
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

import es.caib.enviafib.back.controller.webdb.PeticioController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.portafib.utils.ConstantsV2;

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
	  public void postList(HttpServletRequest request, ModelAndView mav, 
			  PeticioFilterForm filterForm,  List<Peticio> list)
	    throws I18NException {

	    
	    // Mostrar boto per editar usuaris que poden veure les meves plantilles
	   
	    filterForm.getAdditionalButtonsByPK().clear();

	    
	    //<a class="btn btn-warning btn-sm a_item" style="margin-bottom:5px;color: white;" href="<c:url value="${contexte}/${fitxer.fitxerID}/edit"/>" onclick="null">
        //<i class="fas fa-edit"></i>
        // <fmt:message key="genapp.edit"/>
       
        //<c:if test="${__theFilterForm.deleteButtonVisible}">
        //<a class="btn btn-danger btn-sm a_item" style="margin-bottom:5px;color: white;" href="#myModal" onclick="openModal('<c:url value="${contexte}/${fitxer.fitxerID}/delete"/>','show');">
        //<i class="fas fa-trash icon-white"></i>
         //<fmt:message key="genapp.delete"/>
         
         
         //Per fer que els botons siguin onClick, afegir -> javascript: openModal('<c:url value="${contexte}/${fitxer.fitxerID}/delete"/>','show')
     	filterForm.setEditButtonVisible(false);
    	filterForm.setDeleteButtonVisible(false);

	    
	    
	    for (Peticio peticio : list) {
	    	
	    	switch((int) peticio.getEstat()) {
	        case ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
	        	filterForm.addAdditionalButtonByPK(peticio.getPeticioID(), new AdditionalButton("fas fa-edit", "genapp.edit", getContextWeb()+"/"+peticio.getPeticioID()+"/edit/", "btn-warning"));
	        	filterForm.addAdditionalButtonByPK(peticio.getPeticioID(), new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"+getContextWeb() + "/"+peticio.getPeticioID()+"/delete','show')", "btn-danger"));
	        	filterForm.addAdditionalButtonByPK(peticio.getPeticioID(), new AdditionalButton("fas fa-play", "posar_en_martxa", getContextWeb()+"/arrancar/{0}", "btn-success"));
	      	  break;
	        case ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES:
	      	  break;
	        case ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT:
	        	filterForm.addAdditionalButtonByPK(peticio.getPeticioID(), new AdditionalButton("fas fa-play", "posar_en_martxa", getContextWeb()+"/arrancar/{0}", "btn-success"));
	      	  break;
	        case ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT:
	      	  break;
	        case ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
	      	  break;    	  	
	        }
	    }

	  }
	
	@RequestMapping(value = "/arrancar/{peticioID}", method = RequestMethod.GET)
	  public String arrancarPeticio(HttpServletRequest request,
	    HttpServletResponse response, @PathVariable("peticioID") Long peticioID){
		
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
