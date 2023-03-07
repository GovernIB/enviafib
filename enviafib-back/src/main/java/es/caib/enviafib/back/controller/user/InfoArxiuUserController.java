package es.caib.enviafib.back.controller.user;

import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Columns;
import org.fundaciobit.genapp.common.query.selectcolumn.Select2Values;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.AbstractPeticioUserController;
import es.caib.enviafib.back.controller.admin.LlistatPeticionsAdminController;
import es.caib.enviafib.back.controller.webdb.InfoArxiuController;
import es.caib.enviafib.back.form.webdb.InfoArxiuFilterForm;
import es.caib.enviafib.back.form.webdb.InfoArxiuForm;
import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.InfoArxiuJPA;

/**
 * 
 * @author fbosch
 *
 */
@Controller
@RequestMapping(value = "/user/infoArxiu")
@SessionAttributes(types = { InfoArxiuForm.class, InfoArxiuFilterForm.class })
public class InfoArxiuUserController extends InfoArxiuController {

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.logic.InfoArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.InfoArxiuLogicaService infoArxiuLogicEjb;

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return true;
    }

    @Override
    public String getTileForm() {
        return "infoArxiuFormUser";
    }

    @Override
    public String getTileList() {
        return "infoArxiuListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "infoArxiuUser_FilterForm";
    }

    @Override
    public InfoArxiuForm getInfoArxiuForm(InfoArxiuJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        InfoArxiuForm infoArxiuForm;

        if (_jpa == null) {
            infoArxiuForm = new InfoArxiuForm(new InfoArxiuJPA(), true);

        } else {
            infoArxiuForm = new InfoArxiuForm(_jpa, false);
            infoArxiuForm.setView(__isView);

        }

        infoArxiuForm.setContexte(getContextWeb());
        infoArxiuForm.setEntityNameCode(getEntityNameCode());
        infoArxiuForm.setEntityNameCodePlural(getEntityNameCodePlural());
        return infoArxiuForm;
    }

    @Override
    public InfoArxiuFilterForm getInfoArxiuFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        InfoArxiuFilterForm infoArxiuFilterForm;
        infoArxiuFilterForm = (InfoArxiuFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
        if (infoArxiuFilterForm == null) {
            infoArxiuFilterForm = new InfoArxiuFilterForm();
            infoArxiuFilterForm.setContexte(getContextWeb());
            infoArxiuFilterForm.setEntityNameCode(getEntityNameCode());
            infoArxiuFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
            infoArxiuFilterForm.setNou(true);
        } else {
            infoArxiuFilterForm.setNou(false);
        }
        infoArxiuFilterForm.setPage(pagina == null ? 1 : pagina);
        return infoArxiuFilterForm;
    }

    public boolean isAdmin() {
        return false;
    }
    
    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infoarxiuid) {

        try {
            if (isAdmin()) {
                Long peticioID = peticioLogicaEjb.executeQueryOne(PeticioFields.PETICIOID, PeticioFields.INFOARXIUID.equal(infoarxiuid));
                String context = LlistatPeticionsAdminController.CONTEXT;
                return "redirect:" + context + "/view/" + peticioID;
            } else {

                Select2Columns<Long, Integer> s2c;
                s2c = new Select2Columns<Long, Integer>(PeticioFields.PETICIOID.select, PeticioFields.TIPUS.select);
                List<Select2Values<Long, Integer>> list;
                list = peticioLogicaEjb.executeQuery(s2c, PeticioFields.INFOARXIUID.equal(infoarxiuid));

                if (list == null || list.size() != 1) {
                    //            HtmlUtils.saveMessageError(request, msg);
                    log.error("InfoArxiuUserController:: getRedirectWhenCancel: La consulta no ha retornat cap resultat",
                            new Exception());
                    return AbstractFirmaUserController.getRedirectToList();
                }

                Long peticioID = list.get(0).getValue1();
                Integer tipus = list.get(0).getValue2();
                
                String context = AbstractPeticioUserController.firmaPathByTipus.get(tipus);
                return "redirect:" + context + "/view/" + peticioID;
            }
        } catch (I18NException e) {
            log.error("InfoArrxiuUserController:: getRedirectWhenCancel: La consulta no no ha be", e);
            return AbstractFirmaUserController.getRedirectToList();
        }
    }

    @Override
    public InfoArxiuJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infoArxiuID) throws I18NException {
        return (InfoArxiuJPA) infoArxiuLogicEjb.findByPrimaryKeyPublic(infoArxiuID);
      }

}
