package es.caib.enviafib.back.controller.user;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.commons.utils.NifUtils;
import es.caib.enviafib.commons.utils.NifUtils.CheckNifResult;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * Codi comú per formulari dels diferents tipus de peticions.
 * 
 * @author anadal
 *
 */
public abstract class AbstractFirmaUserController extends AbstractPeticioUserController {

    @EJB(mappedName = es.caib.enviafib.ejb.SerieDocumentalService.JNDI_NAME)
    protected es.caib.enviafib.ejb.SerieDocumentalService serieDocumentalEjb;

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

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
        return true;
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
        return "peticioFormUser";
    }

    public abstract int getTipusPeticio();

    /**
     * Carregar el formulari per un nou Peticio
     */
    @Override
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ModelAndView crearPeticioGet(HttpServletRequest request, HttpServletResponse response) throws I18NException {
        try {
            ModelAndView mav = super.crearPeticioGet(request, response);
            return mav;
        } catch (I18NException e) {
            HtmlUtils.saveMessageError(request, I18NUtils.getMessage(e));
            return new ModelAndView("redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list");
        }
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {
        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

        hiddens.remove(NOM);
        hiddens.remove(FITXERID);
        hiddens.remove(TIPUSDOCUMENTAL);
        hiddens.remove(IDIOMADOC);
        hiddens.remove(ARXIUPARAMFUNCIONARIDIR3);
        hiddens.remove(ARXIUREQPARAMDOCESTATELABORA);
        hiddens.remove(ARXIUREQPARAMORIGEN);
        hiddens.remove(ARXIUREQPARAMINTERESSATS);
        hiddens.remove(ARXIUREQPARAMORGANS);

        if (__isView) {
            hiddens.remove(DATACREACIO);
            hiddens.remove(TIPUS);
            hiddens.remove(IDIOMAID);
            hiddens.remove(ESTAT);
            hiddens.remove(FITXERID);

            switch ((int) peticioForm.getPeticio().getEstat()) {

                case Constants.ESTAT_PETICIO_EN_PROCES:
                break;
                case Constants.ESTAT_PETICIO_ERROR:
                    hiddens.remove(DATAFINAL);
                    hiddens.remove(ERRORMSG);
                    if (peticioForm.getPeticio().getErrorException() != null) {
                        hiddens.remove(ERROREXCEPTION);
                    }
                break;
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                    hiddens.remove(ERRORMSG);
                    if (peticioForm.getPeticio().getErrorException() != null) {
                        hiddens.remove(ERROREXCEPTION);
                    }
                    hiddens.remove(FITXERFIRMATID);
                break;
                case Constants.ESTAT_PETICIO_FIRMADA:
                    hiddens.remove(FITXERFIRMATID);
                    hiddens.remove(DATAFINAL);

                    Long infosignaturaID = peticioForm.getPeticio().getInfoSignaturaID();
                    peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info", "user.infosignatura",
                            "/user/infoSignatura/view/" + infosignaturaID, "btn-info"));
                    Long infoArxiuID = peticioForm.getPeticio().getInfoArxiuID();
                    peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info-circle", "user.infoarxiu",
                            "/user/infoArxiu/view/" + infoArxiuID, "btn-info"));

                break;
            }

        } else {
            mav.addObject("dragdrop", true);
        }

        peticioForm.setAttachedAdditionalJspCode(true);

        peticioForm.setHiddenFields(hiddens);

        if (peticioForm.isNou()) {

            Peticio peticio = peticioForm.getPeticio();

            // COIDDIR3
            try {
                String codiDIR3 = getCodiDIR3();
                peticio.setArxiuParamFuncionariDir3(codiDIR3);
                peticioForm.addHiddenField(PeticioFields.ARXIUPARAMFUNCIONARIDIR3);
                peticioForm.addReadOnlyField(PeticioFields.ARXIUPARAMFUNCIONARIDIR3);

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                HtmlUtils.saveMessageWarning(request, msg);
                mav.setView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
                return peticioForm;
            }

            peticio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            peticio.setErrorMsg(LogicUtils.split255(
                    "XYZ ZZZ Error desconegut. El procés de creació de la petició de firma s'ha aturat de forma inesperada."));

            String userName = request.getRemoteUser();
            Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
            peticio.setSolicitantID(userId);

            peticio.setTipus(getTipusPeticio());

            // Amagam el selector d'idioma a la creacio de peticio. S'agafa el del context
            // autmaticament.
            peticio.setIdiomaID(LocaleContextHolder.getLocale().getLanguage());

            // Idioma per defecte per els documents, catala.
            peticio.setIdiomaDoc("ca");

            LoginInfo li = LoginInfo.getInstance();

            peticio.setArxiuFuncionariUsername(LoginInfo.getInstance().getUsername());

            String nomcomplet = li.getUsuari().getNom() + " " + li.getUsuari().getLlinatge1();
            if (li.getUsuari().getLlinatge2() != null) {
                nomcomplet = nomcomplet + li.getUsuari().getLlinatge2();
            }
            peticio.setArxiuParamFuncionariNom(nomcomplet.trim());
            peticio.setArxiuParamFuncionariNif(li.getUsuari().getNif());
            peticio.setArxiuReqParamOrigen(Constants.ORIGEN_ADMINISTRACIO);

            // Ha d'escriure els DNIs, CIFs o NIFs de les persones interessades separats per
            // coma.
            String msgIn = I18NUtils.tradueix("transaccio.interessats.ajuda");
            peticioForm.addHelpToField(ARXIUREQPARAMINTERESSATS, msgIn);

            // Ha d'escriure la unitat DIR3 del funcionari. Pot esbrinar aquest codi
            // accedint a la pàgina web
            // https://intranet.caib.es/dir3caib i introduint les dades requerides.";
            String msgFD3 = I18NUtils.tradueix("transaccio.fundacionaridir3.ajuda");
            peticioForm.addHelpToField(ARXIUPARAMFUNCIONARIDIR3, msgFD3);

        }

        return peticioForm;
    }

    public String getCodiDIR3() throws I18NException {

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

        String codiDIR3;
        try {
            String username = LoginInfo.getInstance().getUsername();
            codiDIR3 = instance.getCodiDIR3ByUsername(username);
            log.info("El meu codiDIR3 es: " + codiDIR3);
            return codiDIR3;

        } catch (Exception e) {
            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
        }
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {

        HtmlUtils.deleteMessages(request);

        String peticioID = String.valueOf(peticioForm.getPeticio().getPeticioID());
        String msg = I18NUtils.tradueix("creat.i.enviat", peticioID);
        HtmlUtils.saveMessageSuccess(request, msg);

        return getRedirectToList();
    }

    @Override
    public String getRedirectWhenModified(HttpServletRequest request, PeticioForm peticioForm, Throwable __e) {

        if (__e == null) {
            return getRedirectToList();
        } else {
            return getTileForm();
        }
    }

    @Override
    public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long peticioID) {
        return getRedirectToList();
    }

    @Override
    public List<StringKeyValue> getReferenceListForArxiuReqParamDocEstatElabora(HttpServletRequest request,
            ModelAndView mav, Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();

        __tmp.add(new StringKeyValue("EE01", I18NUtils.tradueix("estatelaboracio.ee01")));
        __tmp.add(new StringKeyValue("EE02", I18NUtils.tradueix("estatelaboracio.ee02")));
        __tmp.add(new StringKeyValue("EE03", I18NUtils.tradueix("estatelaboracio.ee03")));
        __tmp.add(new StringKeyValue("EE04", I18NUtils.tradueix("estatelaboracio.ee04")));
        __tmp.add(new StringKeyValue("EE99", I18NUtils.tradueix("estatelaboracio.ee99")));

        return __tmp;
    }

    @Override
    public List<StringKeyValue> getReferenceListForArxiuReqParamOrigen(HttpServletRequest request, ModelAndView mav,
            Where where) throws I18NException {
        List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
        __tmp.add(new StringKeyValue(String.valueOf(Constants.ORIGEN_CIUTADA), I18NUtils.tradueix("origen.ciutada")));
        __tmp.add(new StringKeyValue(String.valueOf(Constants.ORIGEN_ADMINISTRACIO),
                I18NUtils.tradueix("origen.administracio")));
        return __tmp;
    }

    public static String getRedirectToList() {
        return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
    }

    public static String getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
        return Configuracio.getUrlBase() + webContext;
    }

    @RequestMapping(value = "/veureInfoSignatura/{infoSignaturaID}", method = RequestMethod.GET)
    public ModelAndView veureInfoSignatura(@PathVariable("infoSignaturaID") java.lang.Long infoSignaturaID,
            HttpServletRequest request, HttpServletResponse response) {

        if (infoSignaturaID == null) {
            HtmlUtils.saveMessageError(request, "Error. No hi ha informació d'aquesta signatura.");

        } else {
            InfoSignatura is = infoSignaturaEjb.findByPrimaryKey(infoSignaturaID);
            log.info("      -> InfoSignaturaID: " + is.getInfoSignaturaID());

            ModelAndView mav = new ModelAndView("detallsinfosignatura");
            mav.addObject("is", is);
            mav.addObject("contexte", getContextWeb());
            return mav;
        }

        ModelAndView mav = new ModelAndView(new RedirectView(getContextWeb() + "/list", true));
        return mav;

    }

    @Override
    public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
            throws I18NException, I18NValidationException {

        PeticioJPA p = super.create(request, peticio);
        try {

            final int tipus = getTipusPeticio();
            switch (tipus) {
                case Constants.TIPUS_PETICIO_FLUX:
                    peticioLogicaEjb.arrancarPeticioFlux(peticio.getPeticioID(),
                            LocaleContextHolder.getLocale().getLanguage(),
                            (FlowTemplateSimpleFlowTemplate) request.getSession().getAttribute("flux"));
                break;

                case Constants.TIPUS_PETICIO_PLANTILLAFLUX:

                    ApiFlowTemplateSimple api = FluxFirmaUserController.getApiFlowTemplateSimple();

                    String flowTemplateId = peticio.getPeticioPortafirmes();
                    final String languageUI = "ca";

                    FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                    flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

                    FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

                    peticioLogicaEjb.arrancarPeticioFlux(peticio.getPeticioID(),
                            LocaleContextHolder.getLocale().getLanguage(), flux);
                break;

                case Constants.TIPUS_PETICIO_AUTOFIRMA:

                break;
                default:
                    peticioLogicaEjb.arrancarPeticio(peticio.getPeticioID(),
                            LocaleContextHolder.getLocale().getLanguage());
                break;
            }
            String msg = I18NUtils.tradueix("procesdefirma.status.enviadaok", String.valueOf(peticio.getPeticioID()));
            HtmlUtils.deleteMessages(request);
            HtmlUtils.saveMessageSuccess(request, msg);

            // HtmlUtils.saveMessageSuccess(request,
            // "Peticio amb Id: " + peticio.getPeticioID() + " enviada correctament.");

        } catch (I18NException e) {

            // XYZ ZZZ Error generic
            String error = I18NUtils.tradueix("error.flux.arrancar", I18NUtils.getMessage(e));
            log.error(error, e);

            p.setEstat(Constants.ESTAT_PETICIO_ERROR);
            p.setErrorMsg(LogicUtils.split255(error));
            p.setErrorException(LogicUtils.stackTrace2String(e));

            peticioLogicaEjb.update(p);
        } catch (AbstractApisIBException e) {

            String error = I18NUtils.tradueix("error.flux.arrancar", e.getMessage());
            log.error(error, e);

            p.setEstat(Constants.ESTAT_PETICIO_ERROR);
            p.setErrorMsg(LogicUtils.split255(error));
            p.setErrorException(LogicUtils.stackTrace2String(e));

            peticioLogicaEjb.update(p);
        }
        return p;
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String crearPeticioPost(@ModelAttribute PeticioForm peticioForm, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String ret = super.crearPeticioPost(peticioForm, result, request, response);

        if (result.hasErrors() && getTipusPeticio() != Constants.TIPUS_PETICIO_FLUX) {

            request.setAttribute("dragdrop", true);
        }
        return ret;
    }

    @Override
    public void postValidate(HttpServletRequest request, PeticioForm peticioForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, peticioForm, result);

        {

            // XYZ ZZZ Aqui falta el camp de DIR3Unit !!!
            Field<?>[] signFields = { ARXIUPARAMFUNCIONARINIF, ARXIUPARAMFUNCIONARINOM, ARXIUPARAMFUNCIONARIDIR3 };

            for (Field<?> field : signFields) {
                ValidationUtils.rejectIfEmptyOrWhitespace(result, field.fullName, "genapp.validation.required",
                        new Object[] { I18NUtils.tradueix(field.fullName) });
            }

        }

        {

            Field<?>[] reqFields = { ARXIUREQPARAMDOCESTATELABORA, ARXIUREQPARAMORIGEN };

            for (Field<?> field : reqFields) {
                ValidationUtils.rejectIfEmptyOrWhitespace(result, field.fullName, "genapp.validation.required",
                        new Object[] { I18NUtils.tradueix(field.fullName) });
            }

            /*
             * if (isPublic()) { // Si la petició arriba des d'APP llavors, requerim que ens
             * afegeixin quin organ ValidationUtils.rejectIfEmptyOrWhitespace(result,
             * TransaccioFields.ARXIUREQPARAMORGANS.fullName, "genapp.validation.required",
             * new Object[] { I18NUtils
             * .tradueix(TransaccioFields.ARXIUREQPARAMORGANS.fullName) }); }
             */

            if (!result.hasFieldErrors(get(ARXIUREQPARAMINTERESSATS))) {

                String interessats = peticioForm.getPeticio().getArxiuReqParamInteressats();

                log.info("\n XXX Validar NIFs interessats ... " + interessats);

                if (interessats != null) {

                    CheckNifResult cnr = NifUtils.validateNifsSeparatedByCommas(interessats);

                    peticioForm.getPeticio().setArxiuReqParamInteressats(cnr.getNifListFormatted());

                    if (!cnr.isValid()) {
                        result.rejectValue(get(ARXIUREQPARAMINTERESSATS), "error.interessats",
                                new String[] { (cnr.getNifs() == null) ? interessats : cnr.getNifs().toString() },
                                null);
                    }
                }
            }

        }
        // Validacio de serie documental
        {
            // XYZ ZZZ ZZZ S'ha d'obtenir del Mapeig de Series Documentals
            // XYZ ZZZ PostValidate validar que pel tipus de document hi ha seria documental

            Peticio peticio = peticioForm.getPeticio();

            String tipusDocumental = peticio.getTipusDocumental();
            List<SerieDocumental> list = serieDocumentalEjb
                    .select(SerieDocumentalFields.TIPUSDOCUMENTAL.equal(tipusDocumental));
            if (list == null || list.isEmpty()) {
                list = serieDocumentalEjb.select(SerieDocumentalFields.TIPUSDOCUMENTAL.isNull());
                if (list == null || list.isEmpty()) {
                    // throw new I18NException("No existeix Serie Documental amb el Tipus Documental
                    // " + tipusDocumental
                    // + " o amb tipus documental null. Consulti l'error amb el seu administrador");

                    result.rejectValue(get(PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL), "error.tipusdocumental",
                            new String[] { tipusDocumental }, null);

                }
            }

            SerieDocumental serieDocumental = list.get(0);

            // XYZ Arreglar noms Java a genapp
            peticio.setArxiuOptParamSerieDocumental(serieDocumental.getNom());
            peticio.setArxiuOptParamProcedimentCodi(serieDocumental.getProcedimentCodi());
            peticio.setArxiuOptParamProcedimentNom(serieDocumental.getProcedimentNom());
        }
    }

}
