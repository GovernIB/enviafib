package es.caib.enviafib.back.controller.admin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.EntitatController;
import es.caib.enviafib.back.form.webdb.EntitatFilterForm;
import es.caib.enviafib.back.form.webdb.EntitatForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.logic.EntitatLogicaService;
import es.caib.enviafib.model.fields.EntitatFields;
import es.caib.enviafib.persistence.EntitatJPA;

@Controller
@RequestMapping(value = InfoEntitatAdminController.CONTEXTWEB)
@SessionAttributes(types = { EntitatForm.class, EntitatFilterForm.class })
public class InfoEntitatAdminController extends EntitatController {

	public static final String CONTEXTWEB = "/admin/entitat";

	@EJB(mappedName = EntitatLogicaService.JNDI_NAME)
	protected EntitatLogicaService entitatLogicaEjb;

	@Override
	public String getTileForm() {
		return "entitatFormAdmin";
	}

	@Override
	public String getTileList() {
		return "entitatListAdmin";
	}

	@Override
	public String getSessionAttributeFilterForm() {
		return "entitatListAdmin_FilterForm";
	}

	@Override
	public EntitatFilterForm getEntitatFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		EntitatFilterForm filterForm = super.getEntitatFilterForm(pagina, mav, request);

		log.info("getEntitatFilterForm");
		if (filterForm.isNou()) {
			log.info("getEntitatFilterForm isNou");

			Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(EntitatFields.ALL_ENTITAT_FIELDS));
			hiddenFields.remove(EntitatFields.NOM);
			hiddenFields.remove(EntitatFields.ENTITATID);
			hiddenFields.remove(EntitatFields.DESCRIPCIO);
			hiddenFields.remove(EntitatFields.ACTIVA);

			filterForm.setHiddenFields(hiddenFields);

			filterForm.addAdditionalButtonForEachItem(new AdditionalButton("fas fa-users",
					"gestionar.administradors", CONTEXTWEB + "/usuarisEntitat" + "/{0}", AdditionalButtonStyle.INFO));
		}

		return filterForm;
	}

	@Override
	public EntitatForm getEntitatForm(EntitatJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {

		EntitatForm entitatForm = super.getEntitatForm(_jpa, __isView, request, mav);
		log.info("getEntitatForm");

		return entitatForm;
	}

	@Override
	public EntitatJPA findByPrimaryKey(HttpServletRequest request, String entitatid) throws I18NException {
		return (EntitatJPA) entitatLogicaEjb.findByPrimaryKeyPublic(entitatid);
	}

	@RequestMapping(value = "/dadesEntitat")
	public String dadesEntitat(HttpServletRequest request) {
		String entitatid = LoginInfo.getInstance().getUsuari().getEntitatID();
		log.info("entitatid: " + entitatid);

		return "redirect:" + CONTEXTWEB + "/" + entitatid + "/edit";
	}

	@RequestMapping(value = "/usuarisEntitat/{entitatID}")
	public String usuarisEntitat(HttpServletRequest request, @PathVariable("entitatID") String entitatID) {
		
		log.info("entitatID: " + entitatID);
		
		request.getSession().setAttribute("entitatID", entitatID);

		return "redirect:" + UsuariEntitatAdminController.CONTEXTWEB + "/list";
	}
}
