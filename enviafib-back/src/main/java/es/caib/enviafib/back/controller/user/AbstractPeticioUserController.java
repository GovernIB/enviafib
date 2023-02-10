package es.caib.enviafib.back.controller.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.PeticioController;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.IdiomaFields;

/**
 * Codi comú per llistat i per edició/vista/creació de Peticions.
 * 
 * @author anadal
 *
 */
public abstract class AbstractPeticioUserController extends PeticioController implements Constants {

    @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.UsuariService usuariEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
    protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.InfoSignaturaService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoSignaturaService infoSignaturaEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuEjb;

    @EJB(mappedName = es.caib.enviafib.ejb.InfoArxiuService.JNDI_NAME)
    protected es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb;

    public static final Map<Integer, String> firmaPathByTipus = new HashMap<Integer, String>();

    static {
        firmaPathByTipus.put(TIPUS_PETICIO_NIF, FirmaPerNifUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_AUTOFIRMA, AutoFirmaUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_FLUX, FirmaFluxUserController.CONTEXT_WEB);

        firmaPathByTipus.put(TIPUS_PETICIO_FLUX_SIMPLE, FirmaPerFluxFirmaSimpleUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_FLUX_JSON, FirmaPerFluxFirmaJsonUserController.CONTEXT_WEB);

        firmaPathByTipus.put(TIPUS_PETICIO_PLANTILLAFLUX_USUARI, FirmaPlantillaFluxUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT, FirmaPlantillaFluxEntitatUserController.CONTEXT_WEB);

        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_GERENT_PRESIDENT,
                FirmaCarrecGerentPresidentUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_CAPAREA_CONSELLER,
                FirmaCarrecCapAreaConsellerUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_CAPDEPARTAMENT_DIRECTOR,
                FirmaCarrecDirectorUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_SECRETARI, FirmaCarrecSecretariUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_ENCARREGAT_COMPRES,
                FirmaCarrecEncarregatCompresUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_RECURSOS_HUMANS, FirmaCarrecRecursosHumansUserController.CONTEXT_WEB);

        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_ADDICIONAL_1, FirmaCarrecAddicional1UserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_CARREC_ADDICIONAL_2, FirmaCarrecAddicional2UserController.CONTEXT_WEB);
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < Constants.ESTATS_PETICIO.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(Constants.ESTATS_PETICIO[i]),
                    I18NUtils.tradueix("estat." + Constants.ESTATS_PETICIO[i])));
        }
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForIdiomaDoc(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {

        return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID,
                Where.AND(where, Where.OR(IdiomaFields.IDIOMAID.equal("es"), IdiomaFields.IDIOMAID.equal("ca"))));
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusDocumental(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        // S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
        // de PortaFIB per obtenir els tipus de documents que gestiona:

        String lang = LocaleContextHolder.getLocale().getLanguage();

        List<StringKeyValue> tipusDocumentalList = new ArrayList<StringKeyValue>();
        tipusDocumentalList.add(new StringKeyValue("", I18NUtils.tradueix("tipusdocumental.seleccionar")));

        List<StringKeyValue> tmpList;
        tmpList = peticioLogicaEjb.getAvailableTipusDocumental(lang);
        
        if (tmpList != null && !tmpList.isEmpty()) {
            java.util.Collections.sort(tmpList, STRINGKEYVALUE_COMPARATOR);
        }else {
            HtmlUtils.saveMessageError(request, "No s'han pogut obtenir els tipus documentals de portafib");
        }
        tipusDocumentalList.addAll(tmpList);
        return tipusDocumentalList;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request, ModelAndView mav, Where where)
            throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        for (int i = 0; i < TIPUS_PETICIONS.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(TIPUS_PETICIONS[i]),
                    I18NUtils.tradueix("tipuspeticio." + TIPUS_PETICIONS[i])));
        }

        return __tmp;
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
        return false;
    }

    @Override
    public boolean isActiveList() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public void fillReferencesForForm(PeticioForm peticioForm, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        super.fillReferencesForForm(peticioForm, request, mav);
        List<StringKeyValue> _listSKV = getReferenceListForTipusDocumental(request, mav, peticioForm, null);
        peticioForm.setListOfValuesForTipusDocumental(_listSKV);
    }

}
