package es.caib.enviafib.back.controller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.AvisController;
import es.caib.enviafib.back.form.webdb.AvisFilterForm;
import es.caib.enviafib.back.form.webdb.AvisForm;
import es.caib.enviafib.model.fields.AvisFields;
import es.caib.enviafib.persistence.AvisJPA;

@Controller
@RequestMapping(value = AvisAdminController.CONTEXTWEB)
@SessionAttributes(types = { AvisFilterForm.class, AvisForm.class })
public class AvisAdminController extends AvisController {

	public static final String CONTEXTWEB = "/admin/avis";

	@Override
	public String getTileForm() {
		return "avisFormAdmin";
	}

	@Override
	public String getTileList() {
		return "avisListAdmin";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "avisListAdmin_FilterForm";
	}

	@Override
	public AvisFilterForm getAvisFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		AvisFilterForm avisFilterForm = super.getAvisFilterForm(pagina, mav, request);

		if (avisFilterForm.isNou()) {
			avisFilterForm.addHiddenField(AVISID);
		}
		
//		Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(AvisFields.ALL_AVIS_FIELDS));
		return avisFilterForm;
	}

	@Override
	public AvisForm getAvisForm(AvisJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {
		AvisForm avisForm = super.getAvisForm(_jpa, __isView, request, mav);
		avisForm.addHiddenField(AVISID);

		avisForm.setAttachedAdditionalJspCode(true);

		return avisForm;
	}

	  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
		       ModelAndView mav, Where where)  throws I18NException {
		  
		    final String ERROR = "danger";
		    final String WARN = "warning";
		    final String SUCCESS = "success";
		    final String INFO = "info";

		    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
		    __tmp.add(new StringKeyValue(ERROR , "Error"));
		    __tmp.add(new StringKeyValue(WARN , "Warning"));
		    __tmp.add(new StringKeyValue(SUCCESS , "Success"));
		    __tmp.add(new StringKeyValue(INFO , "Info"));
		    return __tmp;
		  }	
	  
	  @Override
	public void preValidate(HttpServletRequest request, AvisForm avisForm, BindingResult result) throws I18NException {
		// TODO Auto-generated method stub
		super.preValidate(request, avisForm, result);
		
		AvisJPA avis = avisForm.getAvis();

		//Si les dues dates tenen valor, comprovam que la data d'inici no sigui posterior a la data de fi
		if (avis.getDatafi() != null && avis.getDatainici() != null && avis.getDatafi().before(avis.getDatainici())) {
			result.rejectValue(get(AvisFields.DATAINICI), "error.datainici", "La data d'inici no pot ser posterior a la data de fi");
		}		
		
	}
}
