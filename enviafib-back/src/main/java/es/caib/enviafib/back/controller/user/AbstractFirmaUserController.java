package es.caib.enviafib.back.controller.user;

import java.io.ByteArrayInputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.controller.AbstractPeticioUserController;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.form.webdb.PeticioMultipleForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.commons.utils.NifUtils;
import es.caib.enviafib.commons.utils.NifUtils.CheckNifResult;
import es.caib.enviafib.commons.utils.NifUtils.NifInfo;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.SerieDocumentalFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * Codi comú per formulari dels diferents tipus de peticions.
 * 
 * @author anadal
 *
 */

@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class, PeticioMultipleForm.class })
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

    public String getTitolCode(HttpServletRequest request) {

        return "=" + request.getSession().getAttribute(MenuUserController.TITOL_PETICIO);
    }

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
        PeticioForm peticioForm2 = super.getPeticioForm(_jpa, __isView, request, mav);

        PeticioMultipleForm peticioForm = new PeticioMultipleForm(peticioForm2);

        Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

        peticioForm.setTitleParam((String) request.getSession().getAttribute(MenuUserController.TITOL_PETICIO));

        //Comprovacions de seguretat
        if (!peticioForm.isNou()) {
            String usuariDeLaPeticio = peticioForm.getPeticio().getArxiuFuncionariUsername();
            String usuariLogejat = LoginInfo.getInstance().getUsername();

            if (!usuariDeLaPeticio.equals(usuariLogejat)) {
                Long peticioID = peticioForm.getPeticio().getPeticioID();
                createMessageWarning(request, "error.notfound", peticioID);
                mav.setView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list"  , true));
                return peticioForm;
            }
        }
        
        hiddens.remove(NOM);
        hiddens.remove(FITXERID);
        hiddens.remove(TIPUSDOCUMENTAL);
        hiddens.remove(IDIOMADOC);
        hiddens.remove(ARXIUPARAMFUNCIONARIDIR3);
        hiddens.remove(ARXIUREQPARAMORIGEN);
        hiddens.remove(ARXIUREQPARAMINTERESSATS);
        hiddens.remove(ARXIUREQPARAMORGANS);

        
        if (__isView) {
            hiddens.remove(DATACREACIO);
            hiddens.remove(TIPUS);
            hiddens.remove(IDIOMAID);
            hiddens.remove(ESTAT);
            hiddens.remove(FITXERID);
            hiddens.remove(ARXIUREQPARAMDOCESTATELABORA);

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
                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:
                case Constants.ESTAT_PETICIO_REINTENTAR_TANCAR_EXPEDIENT:
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
        }

        peticioForm.setAttachedAdditionalJspCode(true);
        peticioForm.setHiddenFields(hiddens);

        if (peticioForm.isNou()) {
            
            mav.addObject("dragdrop", true);
            peticioForm.setTitleCode(getTitolCode(request));
            
            Peticio peticio = peticioForm.getPeticio();

            // COIDDIR3
            try {
                String codiDIR3 = getCodiDIR3();
                peticio.setArxiuParamFuncionariDir3(codiDIR3);
//                peticioForm.addHiddenField(PeticioFields.ARXIUPARAMFUNCIONARIDIR3);
                peticioForm.addReadOnlyField(PeticioFields.ARXIUPARAMFUNCIONARIDIR3);
                peticioForm.addLabel(ARXIUPARAMFUNCIONARIDIR3, "elmeudir3");

            } catch (I18NException e) {
                String msg = I18NUtils.getMessage(e);
                log.error(msg, e);
                mav.setViewName("errorIniciPeticioUser");
                mav.addObject("errorMsg", msg);
                mav.addObject("tornarUrl", LlistatPeticionsUserController.CONTEXT_WEB + "/list");
                return peticioForm;
            }
            
            peticio.setDataCreacio(new Timestamp(System.currentTimeMillis()));
            peticio.setEstat(Constants.ESTAT_PETICIO_ERROR);
            peticio.setErrorMsg(LogicUtils.split255(I18NUtils.tradueix("error.peticio.desconegut")));

            String userName = request.getRemoteUser();
            Long userId = usuariEjb.executeQueryOne(UsuariFields.USUARIID, UsuariFields.USERNAME.equal(userName));
            peticio.setSolicitantID(userId);

            peticio.setTipus(getTipusPeticio());

            // Amagam el selector d'idioma a la creacio de peticio. S'agafa el del context autmaticament.
            peticio.setIdiomaID(LocaleContextHolder.getLocale().getLanguage());

            // Idioma per defecte per els documents, catala.
            peticio.setIdiomaDoc("ca");

            LoginInfo li = LoginInfo.getInstance();
            peticio.setArxiuFuncionariUsername(li.getUsername());
            peticio.setArxiuReqParamDocEstatElabora("EE01");
            
            String nomcomplet = li.getUsuari().getNom() + " " + li.getUsuari().getLlinatge1();
            if (li.getUsuari().getLlinatge2() != null) {
                nomcomplet = nomcomplet + li.getUsuari().getLlinatge2();
            }
            peticio.setArxiuParamFuncionariNom(nomcomplet.trim());
            peticio.setArxiuParamFuncionariNif(li.getUsuari().getNif());
            peticio.setArxiuReqParamOrigen(Constants.ORIGEN_ADMINISTRACIO);

            // Ha d'escriure els DNIs, CIFs o NIFs de les persones interessades separats per coma.
            String NIFInteresatsAjuda = I18NUtils.tradueix("transaccio.interessats.nif.ajuda");
            peticioForm.addHelpToField(ARXIUREQPARAMINTERESSATS, NIFInteresatsAjuda);

            String DIR3InteresatsAjuda = I18NUtils.tradueix("transaccio.interessats.dir3.ajuda");
            peticioForm.addHelpToField(ARXIUREQPARAMORGANS, DIR3InteresatsAjuda);

            // Ha d'escriure la unitat DIR3 del funcionari. Pot esbrinar aquest codi accedint a la pàgina web
            // https://intranet.caib.es/dir3caib i introduint les dades requerides.";
//            String msgFD3 = I18NUtils.tradueix("transaccio.fundacionaridir3.ajuda");
//            peticioForm.addHelpToField(ARXIUPARAMFUNCIONARIDIR3, msgFD3);

            peticioForm.setSaveButtonVisible(false);
            peticioForm.addAdditionalButton(new AdditionalButton("", getSubmitLabel(),
                    "javascript:document.getElementById('" + PeticioFields._TABLE_MODEL + "Form').submit()",
                    "btn-secondary"));

            peticioForm.addAdditionalButton(new AdditionalButton("fas fa-info-circle", "advanced.show",
                    "javascript:mostrarOcultarCampsAvanzats(this)", "btn-warning"));

            peticioForm.setCancelButtonVisible(false);
            peticioForm.addAdditionalButton(
                    new AdditionalButton("", "genapp.cancel", getContextWeb() + "/0/cancel", "btn-secondary"));

        }
        return peticioForm;
    }


    private String getSubmitLabel() {
        String codeLabel;

        if (getTipusPeticio() == Constants.TIPUS_PETICIO_AUTOFIRMA) {
            codeLabel = "peticiodefirma.autofirma.continuar";
        }else {
            codeLabel = "peticiodefirma.continuar";
        }
        return codeLabel;
    }
    
    public String getCodiDIR3() throws I18NException {

        IEstructuraOrganitzativaPlugin instance = pluginEstructuraOrganitzativaEjb.getInstance();

        String codiDIR3;
        try {
            String username = LoginInfo.getInstance().getUsername();
            codiDIR3 = instance.getDir3DepartamentDireccioGeneral(username);
            log.info("El meu codiDIR3 es: " + codiDIR3);
            return codiDIR3;

        } catch (Exception e) {
            int tipus = getTipusPeticio();
            log.info("tipus: " + tipus);
            if (tipus == Constants.TIPUS_PETICIO_AUTOFIRMA || tipus == Constants.TIPUS_PETICIO_FLUX) {
                codiDIR3 = "A04003003";
                log.error("No s'ha trobat el dir3. Per ser Autofirma o Flux, s'assigna un per defecte: " + codiDIR3);
                return codiDIR3;
                
            }
            throw new I18NException("error.plugin.estructuraorganitzativa.dir3notfount", e.getMessage());
        }
    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {

        Peticio peticio = peticioForm.getPeticio();
        String peticioID = String.valueOf(peticio.getPeticioID());

        if (peticio.getEstat() == Constants.ESTAT_PETICIO_ERROR) {
            String msg = "La seva petició (" + peticioID + ") no s'ha enviat a portafib: " + peticio.getErrorMsg();
            log.error(msg);
            return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
        } else {
            String msg = I18NUtils.tradueix("procesdefirma.status.creat.enviat", peticioID);
            log.info(msg);
            return "redirect:" + LlistatPeticionsUserController.CONTEXT_WEB + "/list";
        }
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

    /*
    @Deprecated
    public static String getAbsoluteControllerBase(HttpServletRequest request, String webContext) {
        return Configuracio.getUrlBase() + webContext;
    }
    */

    @RequestMapping(value = "/veureInfoSignatura/{infoSignaturaID}", method = RequestMethod.GET)
    public ModelAndView veureInfoSignatura(@PathVariable("infoSignaturaID") java.lang.Long infoSignaturaID,
            HttpServletRequest request, HttpServletResponse response) {

        if (infoSignaturaID == null) {
            HtmlUtils.saveMessageError(request, "Error. No hi ha informació d'aquesta signatura.");

        } else {
            InfoSignatura is = infoSignaturaLogicEjb.findByPrimaryKeyPublic(infoSignaturaID);
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

        final int tipus = getTipusPeticio();
        if (tipus == Constants.TIPUS_PETICIO_AUTOFIRMA) {
            log.info("AbstractFirmaUserController:: Estam creant un AutoFirma");
        } else {
            //Enviament de la peticio a PortaFIB després de la seva creació
            try {

                final String languageUI = LocaleContextHolder.getLocale().getLanguage();
                final Usuari solicitant = LoginInfo.getInstance().getUsuari();
                final Long peticioID = peticio.getPeticioID();

                switch (tipus) {
                    case Constants.TIPUS_PETICIO_FLUX_JSON:
                    case Constants.TIPUS_PETICIO_FLUX_SIMPLE:
                        String SESSION_KEY;
                        if (tipus == Constants.TIPUS_PETICIO_FLUX_JSON) {
                            SESSION_KEY = FirmaPerFluxFirmaJsonUserController.FLUX_JSON_SESSION_KEY;
                        } else {
                            SESSION_KEY = FirmaPerFluxFirmaSimpleUserController.FLUX_SIMPLE_SESSION_KEY;
                        }
                        FirmaAsyncSimpleSignatureBlock[] signatureBlocks = (FirmaAsyncSimpleSignatureBlock[]) request
                                .getSession().getAttribute(SESSION_KEY);

                        peticioLogicaEjb.arrancarPeticioBySignatureBlocks(p, languageUI, signatureBlocks, solicitant);
                    break;
                    case Constants.TIPUS_PETICIO_FLUX:
                        FlowTemplateSimpleFlowTemplate fluxPeticio = (FlowTemplateSimpleFlowTemplate) request
                                .getSession().getAttribute(FirmaFluxUserController.FLUX_SESSION_KEY);
                        p = peticioLogicaEjb.arrancarPeticioFlux(peticioID, languageUI, fluxPeticio, solicitant);
                    break;

                    case Constants.TIPUS_PETICIO_PLANTILLAFLUX_USUARI:
                    case Constants.TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT:

                        ApiFlowTemplateSimple api = FirmaFluxUserController.getApiFlowTemplateSimple();

                        String flowTemplateId = peticio.getPeticioPortafirmes();

                        FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                        flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

                        FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

                        p = peticioLogicaEjb.arrancarPeticioFlux(peticioID, languageUI, flux, solicitant);
                    break;
                    default:
                        p = peticioLogicaEjb.arrancarPeticio(peticioID, languageUI, solicitant);
                    break;
                }

                //Peticio enviada correctament.
                String peticioIDstr = String.valueOf(p.getPeticioID());
                String portafibID = p.getPeticioPortafirmes();
                String msg = I18NUtils.tradueix("procesdefirma.status.enviadaok", peticioIDstr, portafibID);
                log.info(msg);

            } catch (Exception e) {

                String errorMsg;
                if (e instanceof I18NException) {
                    errorMsg = I18NUtils.getMessage((I18NException) e);
                } else if (e instanceof AbstractApisIBException) {
                    errorMsg = e.getMessage();
                } else {
                    errorMsg = e.getMessage();
                }

                p.setEstat(Constants.ESTAT_PETICIO_ERROR);
                p.setErrorMsg(LogicUtils.split255(errorMsg));
                p.setErrorException(LogicUtils.stackTrace2String(e));
                peticioLogicaEjb.update(p);
            }
        }
        return p;
    }

    @Override
    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public String crearPeticioPost(@ModelAttribute PeticioForm peticioForm2, BindingResult result,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        PeticioMultipleForm peticioForm = (PeticioMultipleForm) peticioForm2;

        CommonsMultipartFile[] files = peticioForm.getHiddenFile();
        String originalName = peticioForm.getPeticio().getNom();

        log.info("AbstractFirmaUserController:: files: " + files);
        
        if (files == null || files.length == 0) {
            if (peticioForm.getFitxerID().isEmpty()) {
                files = new CommonsMultipartFile[0];
                result.rejectValue(get(FITXERID), "error.fitxers.postcreate",
                        new String[] {}, null);
            } else {
                files = new CommonsMultipartFile[1];
                files[0] = peticioForm.getFitxerID();
            }
        }
        int nFitxers = files.length;

        if (peticioForm.getPeticio().getTipus() == Constants.TIPUS_PETICIO_AUTOFIRMA) {

            log.info("crearPeticioPost AutoFirma");

            int i;
            for (i = 0; i < nFitxers; i++) {

                CommonsMultipartFile file = files[i];

                FitxerJPA fitxer = new FitxerJPA();

                fitxer.setNom(file.getOriginalFilename());
                fitxer.setMime(file.getContentType());
                final byte[] data = file.getBytes();
                fitxer.setTamany(data.length);

                Fitxer f = fitxerEjb.create(fitxer);
                FileSystemManager.crearFitxer(new ByteArrayInputStream(data), f.getFitxerID());

                log.info("\n\nSTART CREATE POST:: AUTOFIRMA");
                String ret = super.crearPeticioPost(peticioForm, result, request, response);
            }

        } else {
            log.info("crearPeticioPost No-AutoFirma");

            Peticio firstPet = null;

            int PETICIONS_CREADES_OK = 0;
            List<String> missatgesErrors = new ArrayList<String>();

            int i;
            for (i = 0; i < nFitxers; i++) {

                Peticio petFor = peticioForm.getPeticio();
                peticioForm.setFitxerID(files[i]);

                petFor.setPeticioID(0);
                petFor.setEstat(Constants.ESTAT_PETICIO_ERROR);
                petFor.setErrorMsg(null);
                petFor.setErrorException(null);
                if (nFitxers > 1 && originalName != null && originalName.trim().length() > 0) {
                    petFor.setNom(originalName + "-" + files[i].getOriginalFilename());
                }

                BeanPropertyBindingResult result2 = new BeanPropertyBindingResult(peticioForm, "peticioForm");

                log.info("\n\nSTART CREATE POST");

                String ret = super.crearPeticioPost(peticioForm, result2, request, response);
                petFor = peticioForm.getPeticio();

                log.info("POST: ret=" + ret);
                if (ret == null) {
                    //Aturar
                    String msg = "POST: Error ret=null";
                    log.error(msg);
                    HtmlUtils.saveMessageError(request, msg);
                    break;
                } else {
                    if (result2.hasErrors()) {

                        showErrorInfo(result2);
                        for (ObjectError objectError : result2.getAllErrors()) {
                            result.addError(objectError);
                        }

                        int nErrorsFields = result2.getFieldErrorCount();
                        int nErrorsFitxers = result2.getFieldErrorCount(PeticioFields.FITXERID.fullName);

                        //Hi ha errors al formulari
                        if (nErrorsFitxers == nErrorsFields) {
                            //Només hi ha errors amb fitxers -> Avisar i continuar
                            FieldError fieldFileError = result2.getFieldErrors(PeticioFields.FITXERID.fullName).get(0);

                            Object[] array = fieldFileError.getArguments();
                            String[] arguments = Arrays.copyOf(array, array.length, String[].class);

                            String msgError = I18NUtils.tradueix(fieldFileError.getCode(), arguments);

                            String msg = "POST: No s'ha pogut crear la peticio amb el fitxer "
                                    + files[i].getOriginalFilename() + ": " + msgError;

                            missatgesErrors.add(msg);
                            log.warn(msg);
                            continue;
                        } else {
                            //Al formulari hi errors que no son de fitxers -> Aturar
                            //Aquest missatge sobra, genapp ja en posa un per defecte
                            String msg = "POST: Errors al formulari";
//                            HtmlUtils.saveMessageError(request, msg);
                            log.error(msg);
                            break;
                        }
                    } else {
                        //No hi ha errors al formulari
                        if (ret.equals(getTileForm())) {
                            //Posible RunTimeException -> Aturar
                            String msg = "POST: Error no controlat";
                            HtmlUtils.saveMessageError(request, msg);
                            log.error(msg);
                            break;
                        } else {
                            if (petFor.getEstat() == Constants.ESTAT_PETICIO_ERROR) {
                                if (i == 0) {
                                    firstPet = new PeticioJPA(petFor);
                                } else if (i == 1) {
                                    Peticio secondPet = new PeticioJPA(petFor);
                                    if (hasSameError(firstPet, secondPet)) {
                                        peticioLogicaEjb.deleteIncludingFiles(firstPet, fitxerEjb);
                                        peticioLogicaEjb.deleteIncludingFiles(secondPet, fitxerEjb);

                                        HtmlUtils.deleteMessages(request);
                                        String msg = "No s'ha pogut crear cap de les " + nFitxers + " peticions: "
                                                + firstPet.getErrorMsg();
                                        HtmlUtils.saveMessageError(request, msg);
                                        break;
                                    }
                                }

                                String msg = "POST: Error enviant a PortaFIB: " + petFor.getErrorMsg();
                                missatgesErrors.add(msg);
                                log.error(msg);
                            } else {
                                PETICIONS_CREADES_OK++;
                                String msg = "POST: Peticio creada i enviada amb ID=" + petFor.getPeticioID();
                                log.info(msg);
                            }
                            continue;
                        }
                    }
                }
            }

            log.warn("\n\nAtributs del result final:");
            showErrorInfo(result);

            if (i == nFitxers && i > 0) {
                HtmlUtils.deleteMessages(request);
                if (PETICIONS_CREADES_OK > 0) {
                    String msg = "S'han creat i enviat correctament " + PETICIONS_CREADES_OK + " peticions a PortaFIB";
                    HtmlUtils.saveMessageInfo(request, msg);
                } else {
                    HtmlUtils.saveMessageWarning(request, "No s'ha creat ni enviat cap peticio a PortaFIB");
                }
                for (String fileErrorMsg : missatgesErrors) {
                    HtmlUtils.saveMessageError(request, fileErrorMsg);
                }

            }
        }

        if (result.hasErrors()) {
            request.setAttribute("dragdrop", true);
            peticioForm.getPeticio().setNom(originalName);
            peticioForm.getPeticio().setFitxer(null);
            return getTileForm();
        } else {
            return getRedirectWhenCreated(request, peticioForm);
        }
    }

    public boolean hasSameError(Peticio firstPet, Peticio secondPet) {

        String error1 = firstPet.getErrorMsg();
        String error2 = secondPet.getErrorMsg();

        log.info("PeticioID: first=" + firstPet.getPeticioID() + " - petFor=" + secondPet.getPeticioID());
        log.info("fitxerID: first=" + firstPet.getFitxerID() + " - petFor=" + secondPet.getFitxerID());
        log.error(error1);
        log.error(error2);
        if (error1 == null || error2 == null) {
            return false;
        } else {
            if (error1.equals(error2)) {
                return true;
            } else {
                return false;
            }
        }
    }

    public void showErrorInfo(BindingResult result) {

        int nErrors = result.getErrorCount();
        int nErrorsFields = result.getFieldErrorCount();
        int nErrorsFitxers = result.getFieldErrorCount(PeticioFields.FITXERID.fullName);

        log.warn("nErrors: " + nErrors);
        log.warn("nErrorsFields: " + nErrorsFields);
        log.warn("nErrorsFitxers: " + nErrorsFitxers);

        for (ObjectError objectError : result.getAllErrors()) {
            log.warn("\n");

            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                log.warn("getField: " + fieldError.getField());
                log.warn("getRejectedValue: " + fieldError.getRejectedValue());
            }
            log.warn("getCode: " + objectError.getCode());
            log.warn("getObjectName: " + objectError.getObjectName());
            log.warn("getDefaultMessage: " + objectError.getDefaultMessage());

            for (String code : objectError.getCodes()) {
                log.warn("code: " + code);
            }
        }

    }

    @Override
    public void postValidate(HttpServletRequest request, PeticioForm peticioForm, BindingResult result)
            throws I18NException {

        super.postValidate(request, peticioForm, result);

        {
            switch (peticioForm.getPeticio().getTipus()) {
                case Constants.TIPUS_PETICIO_FLUX:
                case Constants.TIPUS_PETICIO_PLANTILLAFLUX_ENTITAT:
                case Constants.TIPUS_PETICIO_PLANTILLAFLUX_USUARI:
                break;

                case Constants.TIPUS_PETICIO_AUTOFIRMA:
                    //Reason ha de ser obligatori quan es AutoFirma
                    String reason = peticioForm.getPeticio().getReason();
                    if (reason == null || reason.trim().length() == 0) {
                        result.rejectValue(get(REASON), "genapp.validation.required",
                                new String[] { I18NUtils.tradueix(REASON.fullName) }, null);
                    }

                break;

                default:
                    String nif = peticioForm.getPeticio().getDestinatariNif();
                    if (nif == null) {
                        result.rejectValue(get(DESTINATARINIF), "genapp.validation.required",
                                new String[] { I18NUtils.tradueix(DESTINATARINIF.fullName) }, null);
                    } else {
                        NifInfo info = NifUtils.validateNif(nif);

                        if (!info.isValid()) {
                            //                            result.rejectValue(get(DESTINATARINIF), "genapp.validation.malformed",
                            //                                    new String[] { I18NUtils.tradueix(DESTINATARINIF.fullName) }, null);
                        }
                    }
                break;
            }
        }

        {
            Field<?>[] signFields = { PeticioFields.ARXIUPARAMFUNCIONARINIF, PeticioFields.ARXIUPARAMFUNCIONARINOM,
                    PeticioFields.ARXIUPARAMFUNCIONARIDIR3 };

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

                //                log.info("\n XXX Validar NIFs interessats ... " + interessats);

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
            Peticio peticio = peticioForm.getPeticio();

            String tipusDocumental = peticio.getTipusDocumental();

            log.info("tipusDocumental: " + tipusDocumental);
            // Mapeig de Series Documentals
            List<SerieDocumental> list = serieDocumentalEjb
                    .select(SerieDocumentalFields.TIPUSDOCUMENTAL.equal(tipusDocumental));

            // Valida que pel tipus de document hi ha seria documental
            if (list == null || list.isEmpty()) {
                list = serieDocumentalEjb.select(SerieDocumentalFields.TIPUSDOCUMENTAL.isNull());
                if (list == null || list.isEmpty()) {

                    result.rejectValue(get(PeticioFields.ARXIUOPTPARAMSERIEDOCUMENTAL), "error.tipusdocumental",
                            new String[] { tipusDocumental }, null);
                }
            }

            SerieDocumental serieDocumental = list.get(0);

            log.info("getNom: " + serieDocumental.getNom());
            log.info("getProcedimentCodi: " + serieDocumental.getProcedimentCodi());
            log.info("getProcedimentNom: " + serieDocumental.getProcedimentNom());
            
            
            peticio.setArxiuOptParamSerieDocumental(serieDocumental.getNom());
            peticio.setArxiuOptParamProcedimentCodi(serieDocumental.getProcedimentCodi());
            peticio.setArxiuOptParamProcedimentNom(serieDocumental.getProcedimentNom());
//            peticio.setArxiuOptParamSerieDocumental("S0001");
//            peticio.setArxiuOptParamProcedimentCodi("organo1_PRO_123456789");
//            peticio.setArxiuOptParamProcedimentNom("Subvenciones empleo");
        }
        
        //Validació de que el titol no es buit
        {
            Peticio peticio = peticioForm.getPeticio();
            String nom = peticio.getNom();
            if (nom == null || nom.isEmpty() || nom.trim().length() == 0 || nom.equals("null")) {
                result.rejectValue(get(NOM), "genapp.validation.required",
                        new String[] { I18NUtils.tradueix(NOM.fullName) }, null);
            }
        }
    }
}
