package es.caib.enviafib.back.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = FirmaCarrecDirectorUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class FirmaCarrecDirectorUserController extends AbtractFirmaCarrecUserController {

    public static final String CONTEXT_WEB = "/user/firmadirector";

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        if (peticioForm.isNou()) {
        //    peticioForm.setTitleCode("title.firma.director");
        }
        return peticioForm;
    }

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_DIRECTOR;
    }

    @Override
    public String getTitolCode() {
        return Constants.CODI_PETICIO_DIRECTOR;
    }

    
    
    @Override
    public int getCarrec() {
        return Constants.CARREC_CAP_DEPARTAMENT_DIRECTOR_GENERAL;
    }
}
