package es.caib.enviafib.back.controller.admin;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.FitxerController;
import es.caib.enviafib.back.form.webdb.FitxerFilterForm;
import es.caib.enviafib.back.form.webdb.FitxerForm;
import es.caib.enviafib.logic.UsuariLogicaService;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.FitxerFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.FitxerJPA;

@Controller
@RequestMapping(value = CanviarUsernamesAdminController.CONTEXTWEB)
@SessionAttributes(types = { FitxerFilterForm.class, FitxerForm.class })

public class CanviarUsernamesAdminController extends FitxerController {

	public static final String CONTEXTWEB = "/admin/canviarusernames";

    @EJB(mappedName = UsuariLogicaService.JNDI_NAME)
    protected UsuariLogicaService usuariLogicaEjb;
	
	@Override
	public String getTileForm() {
		return "canvisUsernamesFormAdmin";
	}
	
	@Override
	public FitxerForm getFitxerForm(FitxerJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
			throws I18NException {

		FitxerForm form = super.getFitxerForm(_jpa, __isView, request, mav);
		
		Set<Field<?>> hiddenFields = new HashSet<Field<?>>(Arrays.asList(FitxerFields.ALL_FITXER_FIELDS));
		hiddenFields.remove(FitxerFields.DESCRIPCIO);
		form.setHiddenFields(hiddenFields);
		
		form.addLabel(DESCRIPCIO, "mapeig.usuaris");
		
		form.setTitleCode("admin.menu.canviarusernames");
		form.setSubTitleCode("canviarusernames.subtitol");
		
		form.setAttachedAdditionalJspCode(true);
		mav.addObject("canviarusernames", true);
		
        FitxerJPA fitxer = form.getFitxer();
        fitxer.setNom("Prova.txt");
        fitxer.setTamany(12);
        fitxer.setMime("text/x-java-properties");
        
		return form;
	}

	@RequestMapping(value = "/new", method = RequestMethod.POST)
	public String crearFitxerPost(@ModelAttribute FitxerForm fitxerForm, BindingResult result,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		String data = fitxerForm.getFitxer().getDescripcio();

		if (!testString(data)) {
			return "redirect:" + getContextWeb() + "/new";
		}
		// Processat de les dades.
		try {
			List<String> errors = canviarUsernames(data);

			if (errors.size() == 0) {
				HtmlUtils.saveMessageInfo(request, "Tots els usuaris actualzats correctament.");

			} else {
				HtmlUtils.saveMessageError(request,
						"Alguns usuaris no s'han actualitzat: <br>" + String.join("<br>", errors));

			}
		} catch (IOException e) {
			HtmlUtils.saveMessageError(request, "Error processant el mapeig d'usuaris: " + e.getMessage());
		}
		return "redirect:" + getContextWeb() + "/new";

	}
	
	private List<String> canviarUsernames(String data) throws IOException, I18NException {

		Properties prop = new Properties();
		prop.load(new StringReader(data));

		List<String> errors = new ArrayList<String>();

		for (Object usrActual : prop.keySet()) {

			String usrNou = (String) prop.get(usrActual);
			String actual = (String) usrActual;

			if (testString(usrNou) && testString(actual)) {
				List<Usuari> listUsernames = usuariLogicaEjb.select(UsuariFields.USERNAME.equal(actual));

				if (listUsernames.size() == 1) {
					Usuari user = listUsernames.get(0);
					user.setUsername(usrNou);
					usuariLogicaEjb.update(user);

					log.info("Canviam username: " + actual + " -> " + usrNou);
					continue;
				}else {
					String error = "Usuari " + actual + " no trobat.";
					errors.add(error);
					log.error(error);
				}
			}else {
				String error = "Format incorrecte: actual ]" + actual + "[ nou ]" + usrNou + "[";
				errors.add(error);
				log.error(error);
			}
		}

		return errors;
	}
	
	@Override
	public String getRedirectWhenCreated(HttpServletRequest request, FitxerForm fitxerForm) {
		return "redirect:" + getContextWeb() + "/new";
	}
	
	private boolean testString (String str) {
		
		return str != null && str.trim().length() > 0;
	}
}
