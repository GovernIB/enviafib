package es.caib.enviafib.back.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractLlistatPeticionsController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginException;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;

/**
 * 
 * @author fbosch
 * @author anadal
 *
 */

@Controller
@RequestMapping(value = LlistatPeticionsUserController.CONTEXT_WEB)
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class LlistatPeticionsUserController extends AbstractLlistatPeticionsController {

    public static final String CONTEXT_WEB = "/user/peticio";

    
    public enum TipusFile {
        FIRMAT, ENI_DOC, VERSIO_IMPRIMIBLE
    }
    
    @Override
    public String getTileList() {
        return "peticioListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "PeticioUser_FilterForm_" + this.getClass().getSimpleName();
    }

    @Override
    public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
        String userName = request.getRemoteUser();
        Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
        return PeticioFields.SOLICITANTID.equal(userId);
    }



    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);

        if (peticioFilterForm.isNou()) {
            peticioFilterForm.addHiddenField(SOLICITANTID);
        }

        return peticioFilterForm;
    }


    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {
        
        filterForm.getAdditionalButtonsByPK().clear();

        for (Peticio peticio : list) {
            long peticioID = peticio.getPeticioID();

            filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-eye", "peticio.btn.view", getContextWebByTipus(peticio.getTipus()) + "/view/" + peticioID, "btn-info"));
        }

        super.postList(request, mav, filterForm, list);
    }

    @RequestMapping(value = "/arrancar/{peticioID}", method = RequestMethod.GET)
    public String arrancarPeticio(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioID") Long peticioID) {

        try {
            final Usuari solicitant = LoginInfo.getInstance().getUsuari();
            peticioLogicaEjb.arrancarPeticio(peticioID, LoginInfo.getInstance().getLanguage(), solicitant);

        } catch (LoginException e) {
            String msg = "La sessio de l'usuari ha caducat.";
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }
        log.info("Peticio enviada correctament.");

        return "redirect:" + getContextWeb() + "/list";
    }

    @Override
    public boolean isAdmin() {
        return false;
    }

    @Override
    protected List<Peticio> processarLlistat(ITableManager<Peticio, Long> ejb, BaseFilterForm filterForm, int pagina,
            Where whereAdditionalCondition, ModelAndView mav) throws I18NException {
        if (filterForm == null) {
            throw new NullPointerException("FilterForm mai pot ser NULL !!!!");
        }
        //Fer cast a PeticioFilterForm
        PeticioFilterForm peticioFilterForm = (PeticioFilterForm) filterForm;

        //Extreure de peticioFilterForm els valors del filtre estat, i afegir els de tancar expedient si conté l'estat Firmat

        List<Integer> estatsSelect = peticioFilterForm.getEstatSelect();

        if (estatsSelect != null) {
            
            List<Integer> nouEstatSelect = new ArrayList<Integer>(estatsSelect);
            for (Integer estat : estatsSelect) {
                if (estat != null) {
                    if (estat == Constants.ESTAT_PETICIO_FIRMADA) {
                        nouEstatSelect.add(Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT);
                        nouEstatSelect.add(Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT);
                    }
                    if (estat == Constants.ESTAT_PETICIO_EN_PROCES) {
                        nouEstatSelect.add(Constants.ESTAT_PETICIO_ARXIVANT);
                    }
                }
            }
            peticioFilterForm.setEstatSelect(nouEstatSelect);
        }

        List<Peticio> llistat = super.processarLlistat(ejb, peticioFilterForm, pagina, whereAdditionalCondition, mav);

        //Posar estat com estaven
        peticioFilterForm.setEstatSelect(estatsSelect);

        return llistat;
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        List<StringKeyValue> __tmp = super.getReferenceListForEstat(request, mav, where);

        List<StringKeyValue> llistat = new ArrayList<StringKeyValue>();

        for (int i = 0; i < __tmp.size(); i++) {
            Long estat = Long.parseLong(__tmp.get(i).getKey());
            if (estat != Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT
                    && estat != Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT
                    && estat != Constants.ESTAT_PETICIO_ARXIVANT) {
                llistat.add(__tmp.get(i));
            }
        }
        return llistat;
    }

}
