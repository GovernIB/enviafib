package es.caib.enviafib.back.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.PeticioController;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.fields.IdiomaFields;

/**
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

    public static final Map<Integer, String> firmaPathByTipus = new HashMap<Integer, String>();

    static {
        firmaPathByTipus.put(TIPUS_PETICIO_NIF, FirmaPerNifUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_AUTOFIRMA, AutoFirmaUserController.CONTEXT_WEB);
        firmaPathByTipus.put(TIPUS_PETICIO_FLUX, FluxFirmaUserController.CONTEXT_WEB);
        // XYZ ZZZ FALTEN LA RESTA DE TIPUS
    }

    @Override
    public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        for (int i = 0; i < Constants.ESTATS_PETICIO.length; i++) {
            __tmp.add(new StringKeyValue(String.valueOf(Constants.ESTATS_PETICIO[i]),
                    I18NUtils.tradueix("estat." + Constants.ESTATS_PETICIO[i])));
        }
        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForIdiomadoc(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {

        return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, Where.AND(where,
                Where.OR(IdiomaFields.IDIOMAID.equal("es"), IdiomaFields.IDIOMAID.equal("ca"))));
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipusdocumental(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        // S'ha de cridar a: ApiFirmaAsyncSimple.getAvailableTypesOfDocuments
        // de PortaFIB per obtenir els tipus de documents que gestiona:

        String lang = LocaleContextHolder.getLocale().getLanguage();
        List<StringKeyValue> tmpList = null;
        tmpList = peticioLogicaEjb.getAvailableTipusDocumental(lang);
        
        
        // TODO: Traduir "Qualsevol valor" ->
        // tmpList.add(new StringKeyValue("","Qualsevol valor"));

        return tmpList;
    }

    @Override
    public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
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
}
