package es.caib.enviafib.back.controller.webdb;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.utils.Utils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.GroupByItem;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.validation.ValidationWebUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import es.caib.enviafib.back.form.webdb.*;
import es.caib.enviafib.back.form.webdb.PeticioForm;

import es.caib.enviafib.back.validator.webdb.PeticioWebValidator;

import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Peticio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/peticio")
@SessionAttributes(types = { PeticioForm.class, PeticioFilterForm.class })
public class PeticioController
    extends es.caib.enviafib.back.controller.EnviaFIBFilesBaseController<Peticio, java.lang.Long, PeticioForm> implements PeticioFields {

  @EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.enviafib.ejb.PeticioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.PeticioService peticioEjb;

  @Autowired
  private PeticioWebValidator peticioWebValidator;

  @Autowired
  protected PeticioRefList peticioRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  // References 
  @Autowired
  protected IdiomaRefList idiomaRefList;

  /**
   * Llistat de totes Peticio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    PeticioFilterForm ff;
    ff = (PeticioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Peticio de forma paginada
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.GET)
  public ModelAndView llistatPaginat(HttpServletRequest request,
    HttpServletResponse response, @PathVariable Integer pagina)
      throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileList());
    llistat(mav, request, getPeticioFilterForm(pagina, mav, request));
    return mav;
  }

  public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    PeticioFilterForm peticioFilterForm;
    peticioFilterForm = (PeticioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(peticioFilterForm == null) {
      peticioFilterForm = new PeticioFilterForm();
      peticioFilterForm.setContexte(getContextWeb());
      peticioFilterForm.setEntityNameCode(getEntityNameCode());
      peticioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      peticioFilterForm.setNou(true);
    } else {
      peticioFilterForm.setNou(false);
    }
    peticioFilterForm.setPage(pagina == null ? 1 : pagina);
    return peticioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Peticio de forma paginada
   * 
   * @param request
   * @param pagina
   * @param filterForm
   * @return
   * @throws I18NException
   */
  @RequestMapping(value = "/list/{pagina}", method = RequestMethod.POST)
  public ModelAndView llistatPaginat(HttpServletRequest request,
      HttpServletResponse response,@PathVariable Integer pagina,
      @ModelAttribute PeticioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getPeticioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Peticio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Peticio> llistat(ModelAndView mav, HttpServletRequest request,
     PeticioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Peticio> peticio = processarLlistat(peticioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("peticioItems", peticio);

    mav.addObject("peticioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, peticio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, peticio);

    return peticio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(PeticioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Peticio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field titolID
    {
      _listSKV = getReferenceListForTitolID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForTitolID(_tmp);
      if (filterForm.getGroupByFields().contains(TITOLID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TITOLID, false);
      };
    }

    // Field solicitantID
    {
      _listSKV = getReferenceListForSolicitantID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariForSolicitantID(_tmp);
      if (filterForm.getGroupByFields().contains(SOLICITANTID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, SOLICITANTID, false);
      };
    }

    // Field idiomaID
    {
      _listSKV = getReferenceListForIdiomaID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfIdiomaForIdiomaID(_tmp);
      if (filterForm.getGroupByFields().contains(IDIOMAID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, IDIOMAID, false);
      };
    }

    // Field estat
    {
      _listSKV = getReferenceListForEstat(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForEstat(_tmp);
      if (filterForm.getGroupByFields().contains(ESTAT)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ESTAT, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    PeticioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Peticio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_PETICIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TITOLID, filterForm.getMapOfTraduccioForTitolID());
    __mapping.put(SOLICITANTID, filterForm.getMapOfUsuariForSolicitantID());
    __mapping.put(IDIOMAID, filterForm.getMapOfIdiomaForIdiomaID());
    __mapping.put(ESTAT, filterForm.getMapOfValuesForEstat());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Peticio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearPeticioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    PeticioForm peticioForm = getPeticioForm(null, false, request, mav);
    
    if (peticioForm.getPeticio().getTitol() == null){
      es.caib.enviafib.persistence.TraduccioJPA trad = new es.caib.enviafib.persistence.TraduccioJPA();
      for (es.caib.enviafib.model.entity.Idioma idioma : peticioForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.enviafib.persistence.TraduccioMapJPA());
      }
      peticioForm.getPeticio().setTitol(trad);
    }

    mav.addObject("peticioForm" ,peticioForm);
    fillReferencesForForm(peticioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public PeticioForm getPeticioForm(PeticioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    PeticioForm peticioForm;
    if(_jpa == null) {
      peticioForm = new PeticioForm(new PeticioJPA(), true);
    } else {
      peticioForm = new PeticioForm(_jpa, false);
      peticioForm.setView(__isView);
    }
    peticioForm.setContexte(getContextWeb());
    peticioForm.setEntityNameCode(getEntityNameCode());
    peticioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    peticioForm.setIdiomesTraduccio(getIdiomesSuportats());
    return peticioForm;
  }

  public void fillReferencesForForm(PeticioForm peticioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (peticioForm.getListOfUsuariForSolicitantID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForSolicitantID(request, mav, peticioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioForm.setListOfUsuariForSolicitantID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioForm.getListOfIdiomaForIdiomaID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForIdiomaID(request, mav, peticioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioForm.setListOfIdiomaForIdiomaID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (peticioForm.getListOfValuesForEstat() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEstat(request, mav, peticioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      peticioForm.setListOfValuesForEstat(_listSKV);
    }
    
  }


  public List<es.caib.enviafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.enviafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.enviafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Peticio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearPeticioPost(@ModelAttribute PeticioForm peticioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    PeticioJPA peticio = peticioForm.getPeticio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, peticio, peticioForm); // FILE
      preValidate(request, peticioForm, result);
      getWebValidator().validate(peticioForm, result);
      postValidate(request,peticioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        peticio = create(request, peticio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", peticio.getPeticioID());
        peticioForm.setPeticio(peticio);
        return getRedirectWhenCreated(request, peticioForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{peticioID}", method = RequestMethod.GET)
  public ModelAndView veurePeticioGet(@PathVariable("peticioID") java.lang.Long peticioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPeticioGet(peticioID,
        request, response, true);
  }


  protected ModelAndView editAndViewPeticioGet(@PathVariable("peticioID") java.lang.Long peticioID,
      HttpServletRequest request,
      HttpServletResponse response, boolean __isView) throws I18NException {
    if((!__isView) && !isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    } else {
      if(__isView && !isActiveFormView()) {
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        return null;
      }
    }
    PeticioJPA peticio = findByPrimaryKey(request, peticioID);

    if (peticio == null) {
      createMessageWarning(request, "error.notfound", peticioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, peticioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      PeticioForm peticioForm = getPeticioForm(peticio, __isView, request, mav);
      peticioForm.setView(__isView);
      if(__isView) {
        peticioForm.setAllFieldsReadOnly(ALL_PETICIO_FIELDS);
        peticioForm.setSaveButtonVisible(false);
        peticioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(peticioForm, request, mav);
      mav.addObject("peticioForm", peticioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Peticio existent
   */
  @RequestMapping(value = "/{peticioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarPeticioGet(@PathVariable("peticioID") java.lang.Long peticioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewPeticioGet(peticioID,
        request, response, false);
  }



  /**
   * Editar un Peticio existent
   */
  @RequestMapping(value = "/{peticioID}/edit", method = RequestMethod.POST)
  public String editarPeticioPost(@ModelAttribute PeticioForm peticioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    PeticioJPA peticio = peticioForm.getPeticio();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, peticio, peticioForm); // FILE
      preValidate(request, peticioForm, result);
      getWebValidator().validate(peticioForm, result);
      postValidate(request, peticioForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        peticio = update(request, peticio);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", peticio.getPeticioID());
        status.setComplete();
        return getRedirectWhenModified(request, peticioForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          peticio.getPeticioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, peticioForm, __e);
    }

  }


  /**
   * Eliminar un Peticio existent
   */
  @RequestMapping(value = "/{peticioID}/delete")
  public String eliminarPeticio(@PathVariable("peticioID") java.lang.Long peticioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Peticio peticio = peticioEjb.findByPrimaryKey(peticioID);
      if (peticio == null) {
        String __msg =createMessageError(request, "error.notfound", peticioID);
        return getRedirectWhenDelete(request, peticioID, new Exception(__msg));
      } else {
        delete(request, peticio);
        createMessageSuccess(request, "success.deleted", peticioID);
        return getRedirectWhenDelete(request, peticioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", peticioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, peticioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute PeticioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarPeticio(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.Long stringToPK(String value) {
  return java.lang.Long.parseLong(value, 10);
}

  @Override
  public String[] getArgumentsMissatge(Object __peticioID, Throwable e) {
    java.lang.Long peticioID = (java.lang.Long)__peticioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (peticioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(peticioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "peticio.peticio";
  }

  public String getEntityNameCodePlural() {
    return "peticio.peticio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("peticio.peticioID");
  }

  @InitBinder("peticioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("peticioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "peticio.peticioID");
  }

  public PeticioWebValidator getWebValidator() {
    return peticioWebValidator;
  }


  public void setWebValidator(PeticioWebValidator __val) {
    if (__val != null) {
      this.peticioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Peticio
   */
  @RequestMapping(value = "/{peticioID}/cancel")
  public String cancelPeticio(@PathVariable("peticioID") java.lang.Long peticioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, peticioID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Peticio peticio,
      PeticioForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxerID(), form.isFitxerIDDelete(),
        form.isNou()? null : peticio.getFitxer());
    ((PeticioJPA)peticio).setFitxer(f);
    if (f != null) { 
      peticio.setFitxerID(f.getFitxerID());
    } else {
      peticio.setFitxerID(0);
    }

    f = (FitxerJPA)afm.preProcessFile(form.getFitxerFirmatID(), form.isFitxerFirmatIDDelete(),
        form.isNou()? null : peticio.getFitxerFirmat());
    ((PeticioJPA)peticio).setFitxerFirmat(f);
    if (f != null) { 
      peticio.setFitxerFirmatID(f.getFitxerID());
    } else {
      peticio.setFitxerFirmatID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Peticio peticio) {
    deleteFile(peticio.getFitxerID());
    deleteFile(peticio.getFitxerFirmatID());
  }
  // Mètodes a sobreescriure 

  public boolean isActiveList() {
    return true;
  }


  public boolean isActiveFormNew() {
    return true;
  }


  public boolean isActiveFormEdit() {
    return true;
  }


  public boolean isActiveDelete() {
    return true;
  }


  public boolean isActiveFormView() {
    return isActiveFormEdit();
  }

  public List<StringKeyValue> getReferenceListForTitolID(HttpServletRequest request,
       ModelAndView mav, PeticioFilterForm peticioFilterForm,
       List<Peticio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioFilterForm.isHiddenField(TITOLID)
      && !peticioFilterForm.isGroupByField(TITOLID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TITOLID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Peticio _item : list) {
        _pkList.add(_item.getTitolID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForTitolID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTitolID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForSolicitantID(HttpServletRequest request,
       ModelAndView mav, PeticioForm peticioForm, Where where)  throws I18NException {
    if (peticioForm.isHiddenField(SOLICITANTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioForm.isReadOnlyField(SOLICITANTID)) {
      _where = UsuariFields.USUARIID.equal(peticioForm.getPeticio().getSolicitantID());
    }
    return getReferenceListForSolicitantID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForSolicitantID(HttpServletRequest request,
       ModelAndView mav, PeticioFilterForm peticioFilterForm,
       List<Peticio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioFilterForm.isHiddenField(SOLICITANTID)
      && !peticioFilterForm.isGroupByField(SOLICITANTID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(SOLICITANTID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Peticio _item : list) {
        _pkList.add(_item.getSolicitantID());
        }
        _w = UsuariFields.USUARIID.in(_pkList);
      }
    return getReferenceListForSolicitantID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForSolicitantID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariRefList.getReferenceList(UsuariFields.USUARIID, where );
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, PeticioForm peticioForm, Where where)  throws I18NException {
    if (peticioForm.isHiddenField(IDIOMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (peticioForm.isReadOnlyField(IDIOMAID)) {
      _where = IdiomaFields.IDIOMAID.equal(peticioForm.getPeticio().getIdiomaID());
    }
    return getReferenceListForIdiomaID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, PeticioFilterForm peticioFilterForm,
       List<Peticio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioFilterForm.isHiddenField(IDIOMAID)
      && !peticioFilterForm.isGroupByField(IDIOMAID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(IDIOMAID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (Peticio _item : list) {
        _pkList.add(_item.getIdiomaID());
        }
        _w = IdiomaFields.IDIOMAID.in(_pkList);
      }
    return getReferenceListForIdiomaID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForIdiomaID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return idiomaRefList.getReferenceList(IdiomaFields.IDIOMAID, where );
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PeticioForm peticioForm, Where where)  throws I18NException {
    if (peticioForm.isHiddenField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForEstat(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, PeticioFilterForm peticioFilterForm,
       List<Peticio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (peticioFilterForm.isHiddenField(ESTAT)
      && !peticioFilterForm.isGroupByField(ESTAT)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForEstat(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEstat(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    return __tmp;
  }


  public void preValidate(HttpServletRequest request,PeticioForm peticioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,PeticioForm peticioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm,  List<Peticio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, PeticioForm peticioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long peticioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long peticioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "peticioFormWebDB";
  }

  public String getTileList() {
    return "peticioListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "PeticioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public PeticioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long peticioID) throws I18NException {
    return (PeticioJPA) peticioEjb.findByPrimaryKey(peticioID);
  }


  public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
    throws Exception,I18NException, I18NValidationException {
    return (PeticioJPA) peticioEjb.create(peticio);
  }


  public PeticioJPA update(HttpServletRequest request, PeticioJPA peticio)
    throws Exception,I18NException, I18NValidationException {
    return (PeticioJPA) peticioEjb.update(peticio);
  }


  public void delete(HttpServletRequest request, Peticio peticio) throws Exception,I18NException {
    peticioEjb.delete(peticio);
  }

} // Final de Classe

