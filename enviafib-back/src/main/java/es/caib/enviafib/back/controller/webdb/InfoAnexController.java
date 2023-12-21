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
import es.caib.enviafib.back.form.webdb.InfoAnexForm;

import es.caib.enviafib.back.validator.webdb.InfoAnexWebValidator;

import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.enviafib.persistence.InfoAnexJPA;
import es.caib.enviafib.model.entity.InfoAnex;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un InfoAnex
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/infoAnex")
@SessionAttributes(types = { InfoAnexForm.class, InfoAnexFilterForm.class })
public class InfoAnexController
    extends es.caib.enviafib.back.controller.EnviaFIBFilesBaseController<InfoAnex, java.lang.Long, InfoAnexForm> implements InfoAnexFields {

  @EJB(mappedName = es.caib.enviafib.ejb.InfoAnexService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoAnexService infoAnexEjb;

  @Autowired
  private InfoAnexWebValidator infoAnexWebValidator;

  @Autowired
  protected InfoAnexRefList infoAnexRefList;

  // References 
  @Autowired
  protected PeticioRefList peticioRefList;

  /**
   * Llistat de totes InfoAnex
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    InfoAnexFilterForm ff;
    ff = (InfoAnexFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar InfoAnex de forma paginada
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
    llistat(mav, request, getInfoAnexFilterForm(pagina, mav, request));
    return mav;
  }

  public InfoAnexFilterForm getInfoAnexFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    InfoAnexFilterForm infoAnexFilterForm;
    infoAnexFilterForm = (InfoAnexFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(infoAnexFilterForm == null) {
      infoAnexFilterForm = new InfoAnexFilterForm();
      infoAnexFilterForm.setContexte(getContextWeb());
      infoAnexFilterForm.setEntityNameCode(getEntityNameCode());
      infoAnexFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      infoAnexFilterForm.setNou(true);
    } else {
      infoAnexFilterForm.setNou(false);
    }
    infoAnexFilterForm.setPage(pagina == null ? 1 : pagina);
    return infoAnexFilterForm;
  }

  /**
   * Segona i següent peticions per llistar InfoAnex de forma paginada
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
      @ModelAttribute InfoAnexFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getInfoAnexFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de InfoAnex de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<InfoAnex> llistat(ModelAndView mav, HttpServletRequest request,
     InfoAnexFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<InfoAnex> infoAnex = processarLlistat(infoAnexEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("infoAnexItems", infoAnex);

    mav.addObject("infoAnexFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, infoAnex, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, infoAnex);

    return infoAnex;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(InfoAnexFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<InfoAnex> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field peticioID
    {
      _listSKV = getReferenceListForPeticioID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPeticioForPeticioID(_tmp);
      if (filterForm.getGroupByFields().contains(PETICIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PETICIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    InfoAnexFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<InfoAnex> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INFOANEX_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIOID, filterForm.getMapOfPeticioForPeticioID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou InfoAnex
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearInfoAnexGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    InfoAnexForm infoAnexForm = getInfoAnexForm(null, false, request, mav);
    mav.addObject("infoAnexForm" ,infoAnexForm);
    fillReferencesForForm(infoAnexForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public InfoAnexForm getInfoAnexForm(InfoAnexJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    InfoAnexForm infoAnexForm;
    if(_jpa == null) {
      infoAnexForm = new InfoAnexForm(new InfoAnexJPA(), true);
    } else {
      infoAnexForm = new InfoAnexForm(_jpa, false);
      infoAnexForm.setView(__isView);
    }
    infoAnexForm.setContexte(getContextWeb());
    infoAnexForm.setEntityNameCode(getEntityNameCode());
    infoAnexForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return infoAnexForm;
  }

  public void fillReferencesForForm(InfoAnexForm infoAnexForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (infoAnexForm.getListOfPeticioForPeticioID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioID(request, mav, infoAnexForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      infoAnexForm.setListOfPeticioForPeticioID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou InfoAnex
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearInfoAnexPost(@ModelAttribute InfoAnexForm infoAnexForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    InfoAnexJPA infoAnex = infoAnexForm.getInfoAnex();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, infoAnex, infoAnexForm); // FILE
      preValidate(request, infoAnexForm, result);
      getWebValidator().validate(infoAnexForm, result);
      postValidate(request,infoAnexForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        infoAnex = create(request, infoAnex);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", infoAnex.getInfoanexid());
        infoAnexForm.setInfoAnex(infoAnex);
        return getRedirectWhenCreated(request, infoAnexForm);
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

  @RequestMapping(value = "/view/{infoanexid}", method = RequestMethod.GET)
  public ModelAndView veureInfoAnexGet(@PathVariable("infoanexid") java.lang.Long infoanexid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoAnexGet(infoanexid,
        request, response, true);
  }


  protected ModelAndView editAndViewInfoAnexGet(@PathVariable("infoanexid") java.lang.Long infoanexid,
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
    InfoAnexJPA infoAnex = findByPrimaryKey(request, infoanexid);

    if (infoAnex == null) {
      createMessageWarning(request, "error.notfound", infoanexid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, infoanexid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      InfoAnexForm infoAnexForm = getInfoAnexForm(infoAnex, __isView, request, mav);
      infoAnexForm.setView(__isView);
      if(__isView) {
        infoAnexForm.setAllFieldsReadOnly(ALL_INFOANEX_FIELDS);
        infoAnexForm.setSaveButtonVisible(false);
        infoAnexForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(infoAnexForm, request, mav);
      mav.addObject("infoAnexForm", infoAnexForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un InfoAnex existent
   */
  @RequestMapping(value = "/{infoanexid}/edit", method = RequestMethod.GET)
  public ModelAndView editarInfoAnexGet(@PathVariable("infoanexid") java.lang.Long infoanexid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoAnexGet(infoanexid,
        request, response, false);
  }



  /**
   * Editar un InfoAnex existent
   */
  @RequestMapping(value = "/{infoanexid}/edit", method = RequestMethod.POST)
  public String editarInfoAnexPost(@ModelAttribute InfoAnexForm infoAnexForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    InfoAnexJPA infoAnex = infoAnexForm.getInfoAnex();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, infoAnex, infoAnexForm); // FILE
      preValidate(request, infoAnexForm, result);
      getWebValidator().validate(infoAnexForm, result);
      postValidate(request, infoAnexForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        infoAnex = update(request, infoAnex);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", infoAnex.getInfoanexid());
        status.setComplete();
        return getRedirectWhenModified(request, infoAnexForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          infoAnex.getInfoanexid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, infoAnexForm, __e);
    }

  }


  /**
   * Eliminar un InfoAnex existent
   */
  @RequestMapping(value = "/{infoanexid}/delete")
  public String eliminarInfoAnex(@PathVariable("infoanexid") java.lang.Long infoanexid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      InfoAnex infoAnex = this.findByPrimaryKey(request, infoanexid);
      if (infoAnex == null) {
        String __msg = createMessageError(request, "error.notfound", infoanexid);
        return getRedirectWhenDelete(request, infoanexid, new Exception(__msg));
      } else {
        delete(request, infoAnex);
        createMessageSuccess(request, "success.deleted", infoanexid);
        return getRedirectWhenDelete(request, infoanexid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", infoanexid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, infoanexid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute InfoAnexFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarInfoAnex(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __infoanexid, Throwable e) {
    java.lang.Long infoanexid = (java.lang.Long)__infoanexid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (infoanexid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(infoanexid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "infoAnex.infoAnex";
  }

  public String getEntityNameCodePlural() {
    return "infoAnex.infoAnex.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("infoAnex.infoanexid");
  }

  @InitBinder("infoAnexFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("infoAnexForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "infoAnex.infoanexid");
  }

  public InfoAnexWebValidator getWebValidator() {
    return infoAnexWebValidator;
  }


  public void setWebValidator(InfoAnexWebValidator __val) {
    if (__val != null) {
      this.infoAnexWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de InfoAnex
   */
  @RequestMapping(value = "/{infoanexid}/cancel")
  public String cancelInfoAnex(@PathVariable("infoanexid") java.lang.Long infoanexid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, infoanexid);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, InfoAnex infoAnex,
      InfoAnexForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getAnexID(), form.isAnexIDDelete(),
        form.isNou()? null : infoAnex.getAnex());
    ((InfoAnexJPA)infoAnex).setAnex(f);
    if (f != null) { 
      infoAnex.setAnexID(f.getFitxerID());
    } else {
      infoAnex.setAnexID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(InfoAnex infoAnex) {
    deleteFile(infoAnex.getAnexID());
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


  public List<StringKeyValue> getReferenceListForPeticioID(HttpServletRequest request,
       ModelAndView mav, InfoAnexForm infoAnexForm, Where where)  throws I18NException {
    if (infoAnexForm.isHiddenField(PETICIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (infoAnexForm.isReadOnlyField(PETICIOID)) {
      _where = PeticioFields.PETICIOID.equal(infoAnexForm.getInfoAnex().getPeticioID());
    }
    return getReferenceListForPeticioID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioID(HttpServletRequest request,
       ModelAndView mav, InfoAnexFilterForm infoAnexFilterForm,
       List<InfoAnex> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (infoAnexFilterForm.isHiddenField(PETICIOID)
       && !infoAnexFilterForm.isGroupByField(PETICIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (InfoAnex _item : list) {
        if(_item.getPeticioID() == null) { continue; };
        _pkList.add(_item.getPeticioID());
        }
        _w = PeticioFields.PETICIOID.in(_pkList);
      }
    return getReferenceListForPeticioID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPeticioID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return peticioRefList.getReferenceList(PeticioFields.PETICIOID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,InfoAnexForm infoAnexForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,InfoAnexForm infoAnexForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, InfoAnexFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, InfoAnexFilterForm filterForm,  List<InfoAnex> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, InfoAnexForm infoAnexForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, InfoAnexForm infoAnexForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long infoanexid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infoanexid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "infoAnexFormWebDB";
  }

  public String getTileList() {
    return "infoAnexListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "InfoAnex_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public InfoAnexJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infoanexid) throws I18NException {
    return (InfoAnexJPA) infoAnexEjb.findByPrimaryKey(infoanexid);
  }


  public InfoAnexJPA create(HttpServletRequest request, InfoAnexJPA infoAnex)
    throws I18NException, I18NValidationException {
    return (InfoAnexJPA) infoAnexEjb.create(infoAnex);
  }


  public InfoAnexJPA update(HttpServletRequest request, InfoAnexJPA infoAnex)
    throws I18NException, I18NValidationException {
    return (InfoAnexJPA) infoAnexEjb.update(infoAnex);
  }


  public void delete(HttpServletRequest request, InfoAnex infoAnex) throws I18NException {
    infoAnexEjb.delete(infoAnex);
  }

} // Final de Classe

