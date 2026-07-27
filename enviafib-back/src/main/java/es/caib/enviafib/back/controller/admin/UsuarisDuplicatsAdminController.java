package es.caib.enviafib.back.controller.admin;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByValueItem;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SelectGroupByAndCountForField;
import org.fundaciobit.genapp.common.query.Where;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.form.webdb.UsuariForm;
import es.caib.enviafib.logic.UsuariLogicaService;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.UsuariFields;

@Controller
@RequestMapping(value = UsuarisDuplicatsAdminController.CONTEXT_WEB)
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuarisDuplicatsAdminController extends LlistarUsuarisAdminController {

	public static final String CONTEXT_WEB = "/admin/usuarisduplicats";

	@EJB(mappedName = UsuariLogicaService.JNDI_NAME)
	protected UsuariLogicaService usuariLogicaEjb;

	@Override
	public String getSessionAttributeFilterForm() {
		return "usuarisduplicatsListAdmin_FilterForm";
	}

	@Override
	public List<Usuari> executeSelect(ITableManager<Usuari, Long> ejb, Where where, OrderBy[] orderBy,
			Integer itemsPerPage, int inici) throws I18NException {
		
		List<Usuari> duplicats = new ArrayList<Usuari>();
		
		SelectGroupByAndCountForField sgb = new SelectGroupByAndCountForField(UsuariFields.NIF);
		List<GroupByValueItem> gbi = ejb.executeQuery(sgb, where, orderBy);

		for (GroupByValueItem item : gbi) {
			String nif = item.getValue();
			Long count = item.getCount();
			
			log.info(nif + " - " + count);
			
			if (count > 1) {
				List<Usuari> usersNif = ejb.select(UsuariFields.NIF.equal(nif));			
				if (usersNif.size() > 1) {
					duplicats.addAll(usersNif);
				}
			}
		}
	
		return duplicats;
	}
	
	@Override
	public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
			throws I18NException {
		// TODO Auto-generated method stub
		UsuariFilterForm filterForm =  super.getUsuariFilterForm(pagina, mav, request);
		
		if (filterForm.isNou()) {
			filterForm.setAddButtonVisible(false);
			
		}
		
		return filterForm;
	}

}
