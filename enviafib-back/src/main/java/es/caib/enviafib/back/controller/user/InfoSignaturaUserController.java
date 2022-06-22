package es.caib.enviafib.back.controller.user;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.InfoSignaturaController;
import es.caib.enviafib.back.form.webdb.InfoSignaturaFilterForm;
import es.caib.enviafib.back.form.webdb.InfoSignaturaForm;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.persistence.InfoSignaturaJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = "/user/infoSignatura")
@SessionAttributes(types = { InfoSignatura.class, InfoSignatura.class })
public class InfoSignaturaUserController extends InfoSignaturaController {

    @Override
    public String getTileForm() {
        return "infoSignaturaFormUser";
    }

    @Override
    public String getTileList() {
        return "infoSignaturaListUser";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "infoSignaturaUser_FilterForm";
    }

    @Override
    public InfoSignaturaForm getInfoSignaturaForm(InfoSignaturaJPA _jpa, boolean __isView, HttpServletRequest request,
            ModelAndView mav) throws I18NException {
        InfoSignaturaForm infoSignaturaForm;

        if (_jpa == null) {
            infoSignaturaForm = new InfoSignaturaForm(new InfoSignaturaJPA(), true);

        } else {
            infoSignaturaForm = new InfoSignaturaForm(_jpa, false);
            infoSignaturaForm.setView(__isView);

//            final Section seccio0 = new Section("seccio0", "infosignatura.seccio.0", InfoSignaturaFields.INFOSIGNATURAID);
//
//            final Section infosignatura = new Section("infosignatura", "infosignatura.seccio.1",
//                    InfoSignaturaFields.SIGNOPERATION, InfoSignaturaFields.SIGNTYPE, InfoSignaturaFields.SIGNALGORITHM,
//                    InfoSignaturaFields.SIGNMODE);
//
//            final Section infoadicional = new Section("infoadicional", "infosignatura.seccio.2",
//                    InfoSignaturaFields.SIGNATURESTABLELOCATION, InfoSignaturaFields.TIMESTAMPINCLUDED,
//                    InfoSignaturaFields.POLICYINCLUDED);
//
//            final Section eni = new Section("eni", "infosignatura.seccio.3", InfoSignaturaFields.ENITIPOFIRMA,
//                    InfoSignaturaFields.ENIPERFILFIRMA, InfoSignaturaFields.ENIROLFIRMA,
//                    InfoSignaturaFields.ENISIGNERNAME, InfoSignaturaFields.ENISIGNERADMINISTRATIONID,
//                    InfoSignaturaFields.ENISIGNLEVEL);
//
//            final Section checks = new Section("checks", "infosignatura.seccio.4",
//                    InfoSignaturaFields.CHECKADMINISTRATIONIDOFSIGNER, InfoSignaturaFields.CHECKDOCUMENTMODIFICATIONS,
//                    InfoSignaturaFields.CHECKVALIDATIONSIGNATURE);
//
//            infoSignaturaForm.addSection(seccio0);
//            infoSignaturaForm.addSection(infosignatura);
//            infoSignaturaForm.addSection(infoadicional);
//            infoSignaturaForm.addSection(eni);
//            infoSignaturaForm.addSection(checks);
//
        }

        infoSignaturaForm.setContexte(getContextWeb());
        infoSignaturaForm.setEntityNameCode(getEntityNameCode());
        infoSignaturaForm.setEntityNameCodePlural(getEntityNameCodePlural());
        return infoSignaturaForm;
    }

    @Override
    public InfoSignaturaFilterForm getInfoSignaturaFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        InfoSignaturaFilterForm infoSignaturaFilterForm;
        infoSignaturaFilterForm = (InfoSignaturaFilterForm) request.getSession()
                .getAttribute(getSessionAttributeFilterForm());
        if (infoSignaturaFilterForm == null) {
            infoSignaturaFilterForm = new InfoSignaturaFilterForm();
            infoSignaturaFilterForm.setContexte(getContextWeb());
            infoSignaturaFilterForm.setEntityNameCode(getEntityNameCode());
            infoSignaturaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
            infoSignaturaFilterForm.setNou(true);
        } else {
            infoSignaturaFilterForm.setNou(false);
        }
        infoSignaturaFilterForm.setPage(pagina == null ? 1 : pagina);
        return infoSignaturaFilterForm;
    }

    @Override
    public List<StringKeyValue> getReferenceListForSignOperation(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("0", I18NUtils.tradueix("signoperation.0")));
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("signoperation.1")));
        __tmp.add(new StringKeyValue("2", I18NUtils.tradueix("signoperation.2")));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForSignMode(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("0", I18NUtils.tradueix("signomode.0")));
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("signomode.1")));
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForSignaturesTableLocation(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue("0", I18NUtils.tradueix("signaturestablelocation.0")));
        __tmp.add(new StringKeyValue("1", I18NUtils.tradueix("signaturestablelocation.1")));
        __tmp.add(new StringKeyValue("-1", I18NUtils.tradueix("signaturestablelocation.-1")));
        return __tmp;
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infosignaturaid) {
        return AbstractFirmaUserController.getRedirectToList();
    }

}
