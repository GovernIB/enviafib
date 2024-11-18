package es.caib.enviafib.back.controller.common;

import es.caib.enviafib.back.security.EntitatRoles;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.EntitatLogicaService;
import es.caib.enviafib.model.entity.Entitat;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * 
 * @autor anadal
 * 
 */
@Controller
public class PrincipalController {

	protected final Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
    protected EntitatLogicaService entitatLogicaEjb;
    
	@RequestMapping(value = "/common/principal.html")
	public ModelAndView principal(HttpSession session, HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		Boolean initialized = (Boolean) session.getAttribute("inicialitzat");

		if (initialized == null) {
			HtmlUtils.saveMessageInfo(request, "MessageInfo : Benvingut a EnviaFIB");
			session.setAttribute("inicialitzat", true);
		}

		return new ModelAndView("principal");

	}


	@RequestMapping(value = "/canviarIdioma/{idioma}", method = RequestMethod.GET)
	public ModelAndView canviarIdioma(HttpServletRequest request, HttpServletResponse response,
			@PathVariable(name = "idioma") String idioma) throws Exception {
		es.caib.enviafib.back.utils.EnviaFIBSessionLocaleResolver.setLocaleManually(request, idioma);		
		return new ModelAndView("principal");
	}


	@RequestMapping(value = "/canviarPipella", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return canviarPipella(request, response, null);
	}

	@RequestMapping(value = "/canviarPipella/{pipella}", method = RequestMethod.GET)
	public ModelAndView canviarPipella(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String pipella) throws Exception {

		if (pipella != null && pipella.trim().length() != 0) {

			// Es poden afegir altres pipelles !!!!!

			if ("admin".equals(pipella)) {
				return new ModelAndView(new RedirectView("/admin/peticio/list/1", true));
			}

			if ("user".equals(pipella)) {
				return new ModelAndView(new RedirectView("/user/peticio/list/1", true));
			}

			if ("webdb".equals(pipella)) {
				return new ModelAndView("webdb");
			}

	         if ("ajuda".equals(pipella)) {
	                return new ModelAndView(new RedirectView("/ajuda/faq/list/1", true));
            }
	         
			if ("aden".equals(pipella)) {
				return new ModelAndView(new RedirectView("/aden/usuari/list/1", true));
			}
			

			if (Configuracio.isDesenvolupament() && "desenvolupament".equals(pipella)) {
				return new ModelAndView("desenvolupament");
			}

			String missatge = "S'ha accedit a canviarPipella amb un paràmetre desconegut: " + pipella;
			log.error(missatge);
			HtmlUtils.saveMessageError(request, missatge);
		}

		return new ModelAndView(new RedirectView("/common/home", true));
	}

	
    @RequestMapping(value = "/user/home")
    public ModelAndView homeUser(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ModelAndView mav = new ModelAndView("homeuser");
        return mav;
    }
    
    
	@RequestMapping(value = "/canviarEntitat/{entitatID}", method = RequestMethod.GET)
	public ModelAndView canviarEntitat(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String entitatID) throws Exception {

		LoginInfo loginInfo = LoginInfo.getInstance();
		
		EntitatRoles entitatRols = loginInfo.getMapEntitatsAdmin().get(entitatID);

		
		loginInfo.setEntitatRolsActual(entitatRols);
		SecurityContextHolder.getContext().setAuthentication(loginInfo.generateToken());
		
		if (entitatRols.getRoles().contains(Constants.ROLE_USER)) {
			return new ModelAndView(new RedirectView("/user/peticio/list/1", true));
		}
		
		if (entitatRols.getRoles().contains(Constants.ROLE_ADEN)) {
            return new ModelAndView(new RedirectView("/aden/usuari/list/1", true));
		}
		
		return new ModelAndView(new RedirectView("/common/home", true));
	}

}
