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
import es.caib.enviafib.back.form.webdb.OrganitzacioForm;

import es.caib.enviafib.back.validator.webdb.OrganitzacioWebValidator;

import es.caib.enviafib.persistence.OrganitzacioJPA;
import es.caib.enviafib.model.entity.Organitzacio;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Organitzacio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/organitzacio")
@SessionAttributes(types = { OrganitzacioForm.class, OrganitzacioFilterForm.class })
public class OrganitzacioController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Organitzacio, java.lang.Long> implements OrganitzacioFields {

  @EJB(mappedName = es.caib.enviafib.ejb.OrganitzacioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.OrganitzacioService organitzacioEjb;

  @Autowired
  private OrganitzacioWebValidator organitzacioWebValidator;

  @Autowired
  protected OrganitzacioRefList organitzacioRefList;

  /**
   * Llistat de totes Organitzacio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    OrganitzacioFilterForm ff;
    ff = (OrganitzacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Organitzacio de forma paginada
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
    llistat(mav, request, getOrganitzacioFilterForm(pagina, mav, request));
    return mav;
  }

  public OrganitzacioFilterForm getOrganitzacioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    OrganitzacioFilterForm organitzacioFilterForm;
    organitzacioFilterForm = (OrganitzacioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(organitzacioFilterForm == null) {
      organitzacioFilterForm = new OrganitzacioFilterForm();
      organitzacioFilterForm.setContexte(getContextWeb());
      organitzacioFilterForm.setEntityNameCode(getEntityNameCode());
      organitzacioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      organitzacioFilterForm.setNou(true);
    } else {
      organitzacioFilterForm.setNou(false);
    }
    organitzacioFilterForm.setPage(pagina == null ? 1 : pagina);
    return organitzacioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Organitzacio de forma paginada
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
      @ModelAttribute OrganitzacioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getOrganitzacioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Organitzacio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Organitzacio> llistat(ModelAndView mav, HttpServletRequest request,
     OrganitzacioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Organitzacio> organitzacio = processarLlistat(organitzacioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("organitzacioItems", organitzacio);

    mav.addObject("organitzacioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, organitzacio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, organitzacio);

    return organitzacio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(OrganitzacioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Organitzacio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    OrganitzacioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Organitzacio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_ORGANITZACIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Organitzacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearOrganitzacioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    OrganitzacioForm organitzacioForm = getOrganitzacioForm(null, false, request, mav);
    mav.addObject("organitzacioForm" ,organitzacioForm);
    fillReferencesForForm(organitzacioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public OrganitzacioForm getOrganitzacioForm(OrganitzacioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    OrganitzacioForm organitzacioForm;
    if(_jpa == null) {
      organitzacioForm = new OrganitzacioForm(new OrganitzacioJPA(), true);
    } else {
      organitzacioForm = new OrganitzacioForm(_jpa, false);
      organitzacioForm.setView(__isView);
    }
    organitzacioForm.setContexte(getContextWeb());
    organitzacioForm.setEntityNameCode(getEntityNameCode());
    organitzacioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return organitzacioForm;
  }

  public void fillReferencesForForm(OrganitzacioForm organitzacioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (organitzacioForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, organitzacioForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      organitzacioForm.setListOfValuesForTipus(_listSKV);
    }
    
  }

  /**
   * Guardar un nou Organitzacio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearOrganitzacioPost(@ModelAttribute OrganitzacioForm organitzacioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    OrganitzacioJPA organitzacio = organitzacioForm.getOrganitzacio();

    try {
      preValidate(request, organitzacioForm, result);
      getWebValidator().validate(organitzacioForm, result);
      postValidate(request,organitzacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        organitzacio = create(request, organitzacio);
        createMessageSuccess(request, "success.creation", organitzacio.getOrganitzacioID());
        organitzacioForm.setOrganitzacio(organitzacio);
        return getRedirectWhenCreated(request, organitzacioForm);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{organitzacioID}", method = RequestMethod.GET)
  public ModelAndView veureOrganitzacioGet(@PathVariable("organitzacioID") java.lang.Long organitzacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOrganitzacioGet(organitzacioID,
        request, response, true);
  }


  protected ModelAndView editAndViewOrganitzacioGet(@PathVariable("organitzacioID") java.lang.Long organitzacioID,
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
    OrganitzacioJPA organitzacio = findByPrimaryKey(request, organitzacioID);

    if (organitzacio == null) {
      createMessageWarning(request, "error.notfound", organitzacioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, organitzacioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      OrganitzacioForm organitzacioForm = getOrganitzacioForm(organitzacio, __isView, request, mav);
      organitzacioForm.setView(__isView);
      if(__isView) {
        organitzacioForm.setAllFieldsReadOnly(ALL_ORGANITZACIO_FIELDS);
        organitzacioForm.setSaveButtonVisible(false);
        organitzacioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(organitzacioForm, request, mav);
      mav.addObject("organitzacioForm", organitzacioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Organitzacio existent
   */
  @RequestMapping(value = "/{organitzacioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarOrganitzacioGet(@PathVariable("organitzacioID") java.lang.Long organitzacioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewOrganitzacioGet(organitzacioID,
        request, response, false);
  }



  /**
   * Editar un Organitzacio existent
   */
  @RequestMapping(value = "/{organitzacioID}/edit", method = RequestMethod.POST)
  public String editarOrganitzacioPost(@ModelAttribute OrganitzacioForm organitzacioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    OrganitzacioJPA organitzacio = organitzacioForm.getOrganitzacio();

    try {
      preValidate(request, organitzacioForm, result);
      getWebValidator().validate(organitzacioForm, result);
      postValidate(request, organitzacioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        organitzacio = update(request, organitzacio);
        createMessageSuccess(request, "success.modification", organitzacio.getOrganitzacioID());
        status.setComplete();
        return getRedirectWhenModified(request, organitzacioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          organitzacio.getOrganitzacioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, organitzacioForm, __e);
    }

  }


  /**
   * Eliminar un Organitzacio existent
   */
  @RequestMapping(value = "/{organitzacioID}/delete")
  public String eliminarOrganitzacio(@PathVariable("organitzacioID") java.lang.Long organitzacioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Organitzacio organitzacio = organitzacioEjb.findByPrimaryKey(organitzacioID);
      if (organitzacio == null) {
        String __msg =createMessageError(request, "error.notfound", organitzacioID);
        return getRedirectWhenDelete(request, organitzacioID, new Exception(__msg));
      } else {
        delete(request, organitzacio);
        createMessageSuccess(request, "success.deleted", organitzacioID);
        return getRedirectWhenDelete(request, organitzacioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", organitzacioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, organitzacioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute OrganitzacioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarOrganitzacio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __organitzacioID, Throwable e) {
    java.lang.Long organitzacioID = (java.lang.Long)__organitzacioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (organitzacioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(organitzacioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "organitzacio.organitzacio";
  }

  public String getEntityNameCodePlural() {
    return "organitzacio.organitzacio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("organitzacio.organitzacioID");
  }

  @InitBinder("organitzacioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("organitzacioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "organitzacio.organitzacioID");
  }

  public OrganitzacioWebValidator getWebValidator() {
    return organitzacioWebValidator;
  }


  public void setWebValidator(OrganitzacioWebValidator __val) {
    if (__val != null) {
      this.organitzacioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Organitzacio
   */
  @RequestMapping(value = "/{organitzacioID}/cancel")
  public String cancelOrganitzacio(@PathVariable("organitzacioID") java.lang.Long organitzacioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, organitzacioID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
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


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, OrganitzacioForm organitzacioForm, Where where)  throws I18NException {
    if (organitzacioForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, OrganitzacioFilterForm organitzacioFilterForm,
       List<Organitzacio> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (organitzacioFilterForm.isHiddenField(TIPUS)
      && !organitzacioFilterForm.isGroupByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("CAP_NAME" , "CAP_NAME"));
    __tmp.add(new StringKeyValue("CAP_USERNAME" , "CAP_USERNAME"));
    __tmp.add(new StringKeyValue("NAME" , "NAME"));
    __tmp.add(new StringKeyValue("DIR3" , "DIR3"));
    __tmp.add(new StringKeyValue("NIF" , "NIF"));
    __tmp.add(new StringKeyValue("SEC_USERNAME" , "SEC_USERNAME"));
    __tmp.add(new StringKeyValue("SEC_NAME" , "SEC_NAME"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,OrganitzacioForm organitzacioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,OrganitzacioForm organitzacioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, OrganitzacioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, OrganitzacioFilterForm filterForm,  List<Organitzacio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, OrganitzacioForm organitzacioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, OrganitzacioForm organitzacioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long organitzacioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long organitzacioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "organitzacioFormWebDB";
  }

  public String getTileList() {
    return "organitzacioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "OrganitzacioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public OrganitzacioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long organitzacioID) throws I18NException {
    return (OrganitzacioJPA) organitzacioEjb.findByPrimaryKey(organitzacioID);
  }


  public OrganitzacioJPA create(HttpServletRequest request, OrganitzacioJPA organitzacio)
    throws I18NException, I18NValidationException {
    return (OrganitzacioJPA) organitzacioEjb.create(organitzacio);
  }


  public OrganitzacioJPA update(HttpServletRequest request, OrganitzacioJPA organitzacio)
    throws I18NException, I18NValidationException {
    return (OrganitzacioJPA) organitzacioEjb.update(organitzacio);
  }


  public void delete(HttpServletRequest request, Organitzacio organitzacio) throws I18NException {
    organitzacioEjb.delete(organitzacio);
  }

} // Final de Classe

