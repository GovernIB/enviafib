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
import es.caib.enviafib.back.form.webdb.GrupUsuariForm;

import es.caib.enviafib.back.validator.webdb.GrupUsuariWebValidator;

import es.caib.enviafib.persistence.GrupUsuariJPA;
import es.caib.enviafib.model.entity.GrupUsuari;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un GrupUsuari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/grupUsuari")
@SessionAttributes(types = { GrupUsuariForm.class, GrupUsuariFilterForm.class })
public class GrupUsuariController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<GrupUsuari, java.lang.Long> implements GrupUsuariFields {

  @EJB(mappedName = es.caib.enviafib.ejb.GrupUsuariService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupUsuariService grupUsuariEjb;

  @Autowired
  private GrupUsuariWebValidator grupUsuariWebValidator;

  @Autowired
  protected GrupUsuariRefList grupUsuariRefList;

  // References 
  @Autowired
  protected GrupRefList grupRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes GrupUsuari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GrupUsuariFilterForm ff;
    ff = (GrupUsuariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar GrupUsuari de forma paginada
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
    llistat(mav, request, getGrupUsuariFilterForm(pagina, mav, request));
    return mav;
  }

  public GrupUsuariFilterForm getGrupUsuariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GrupUsuariFilterForm grupUsuariFilterForm;
    grupUsuariFilterForm = (GrupUsuariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(grupUsuariFilterForm == null) {
      grupUsuariFilterForm = new GrupUsuariFilterForm();
      grupUsuariFilterForm.setContexte(getContextWeb());
      grupUsuariFilterForm.setEntityNameCode(getEntityNameCode());
      grupUsuariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      grupUsuariFilterForm.setNou(true);
    } else {
      grupUsuariFilterForm.setNou(false);
    }
    grupUsuariFilterForm.setPage(pagina == null ? 1 : pagina);
    return grupUsuariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar GrupUsuari de forma paginada
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
      @ModelAttribute GrupUsuariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGrupUsuariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de GrupUsuari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<GrupUsuari> llistat(ModelAndView mav, HttpServletRequest request,
     GrupUsuariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<GrupUsuari> grupUsuari = processarLlistat(grupUsuariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("grupUsuariItems", grupUsuari);

    mav.addObject("grupUsuariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, grupUsuari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, grupUsuari);

    return grupUsuari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GrupUsuariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<GrupUsuari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field grupID
    {
      _listSKV = getReferenceListForGrupID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfGrupForGrupID(_tmp);
      if (filterForm.getGroupByFields().contains(GRUPID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRUPID, false);
      };
    }

    // Field usuariID
    {
      _listSKV = getReferenceListForUsuariID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariForUsuariID(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    GrupUsuariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<GrupUsuari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GRUPUSUARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(GRUPID, filterForm.getMapOfGrupForGrupID());
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariID());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou GrupUsuari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGrupUsuariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GrupUsuariForm grupUsuariForm = getGrupUsuariForm(null, false, request, mav);
    mav.addObject("grupUsuariForm" ,grupUsuariForm);
    fillReferencesForForm(grupUsuariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GrupUsuariForm getGrupUsuariForm(GrupUsuariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GrupUsuariForm grupUsuariForm;
    if(_jpa == null) {
      grupUsuariForm = new GrupUsuariForm(new GrupUsuariJPA(), true);
    } else {
      grupUsuariForm = new GrupUsuariForm(_jpa, false);
      grupUsuariForm.setView(__isView);
    }
    grupUsuariForm.setContexte(getContextWeb());
    grupUsuariForm.setEntityNameCode(getEntityNameCode());
    grupUsuariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return grupUsuariForm;
  }

  public void fillReferencesForForm(GrupUsuariForm grupUsuariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (grupUsuariForm.getListOfGrupForGrupID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupID(request, mav, grupUsuariForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      grupUsuariForm.setListOfGrupForGrupID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (grupUsuariForm.getListOfUsuariForUsuariID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariID(request, mav, grupUsuariForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      grupUsuariForm.setListOfUsuariForUsuariID(_listSKV);
    }
    
  }

  /**
   * Guardar un nou GrupUsuari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGrupUsuariPost(@ModelAttribute GrupUsuariForm grupUsuariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GrupUsuariJPA grupUsuari = grupUsuariForm.getGrupUsuari();

    try {
      preValidate(request, grupUsuariForm, result);
      getWebValidator().validate(grupUsuariForm, result);
      postValidate(request,grupUsuariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupUsuari = create(request, grupUsuari);
        createMessageSuccess(request, "success.creation", grupUsuari.getGrupUsuariID());
        grupUsuariForm.setGrupUsuari(grupUsuari);
        return getRedirectWhenCreated(request, grupUsuariForm);
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

  @RequestMapping(value = "/view/{grupUsuariID}", method = RequestMethod.GET)
  public ModelAndView veureGrupUsuariGet(@PathVariable("grupUsuariID") java.lang.Long grupUsuariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupUsuariGet(grupUsuariID,
        request, response, true);
  }


  protected ModelAndView editAndViewGrupUsuariGet(@PathVariable("grupUsuariID") java.lang.Long grupUsuariID,
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
    GrupUsuariJPA grupUsuari = findByPrimaryKey(request, grupUsuariID);

    if (grupUsuari == null) {
      createMessageWarning(request, "error.notfound", grupUsuariID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, grupUsuariID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GrupUsuariForm grupUsuariForm = getGrupUsuariForm(grupUsuari, __isView, request, mav);
      grupUsuariForm.setView(__isView);
      if(__isView) {
        grupUsuariForm.setAllFieldsReadOnly(ALL_GRUPUSUARI_FIELDS);
        grupUsuariForm.setSaveButtonVisible(false);
        grupUsuariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(grupUsuariForm, request, mav);
      mav.addObject("grupUsuariForm", grupUsuariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un GrupUsuari existent
   */
  @RequestMapping(value = "/{grupUsuariID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGrupUsuariGet(@PathVariable("grupUsuariID") java.lang.Long grupUsuariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupUsuariGet(grupUsuariID,
        request, response, false);
  }



  /**
   * Editar un GrupUsuari existent
   */
  @RequestMapping(value = "/{grupUsuariID}/edit", method = RequestMethod.POST)
  public String editarGrupUsuariPost(@ModelAttribute GrupUsuariForm grupUsuariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GrupUsuariJPA grupUsuari = grupUsuariForm.getGrupUsuari();

    try {
      preValidate(request, grupUsuariForm, result);
      getWebValidator().validate(grupUsuariForm, result);
      postValidate(request, grupUsuariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grupUsuari = update(request, grupUsuari);
        createMessageSuccess(request, "success.modification", grupUsuari.getGrupUsuariID());
        status.setComplete();
        return getRedirectWhenModified(request, grupUsuariForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          grupUsuari.getGrupUsuariID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, grupUsuariForm, __e);
    }

  }


  /**
   * Eliminar un GrupUsuari existent
   */
  @RequestMapping(value = "/{grupUsuariID}/delete")
  public String eliminarGrupUsuari(@PathVariable("grupUsuariID") java.lang.Long grupUsuariID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      GrupUsuari grupUsuari = grupUsuariEjb.findByPrimaryKey(grupUsuariID);
      if (grupUsuari == null) {
        String __msg =createMessageError(request, "error.notfound", grupUsuariID);
        return getRedirectWhenDelete(request, grupUsuariID, new Exception(__msg));
      } else {
        delete(request, grupUsuari);
        createMessageSuccess(request, "success.deleted", grupUsuariID);
        return getRedirectWhenDelete(request, grupUsuariID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", grupUsuariID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, grupUsuariID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GrupUsuariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGrupUsuari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __grupUsuariID, Throwable e) {
    java.lang.Long grupUsuariID = (java.lang.Long)__grupUsuariID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (grupUsuariID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(grupUsuariID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "grupUsuari.grupUsuari";
  }

  public String getEntityNameCodePlural() {
    return "grupUsuari.grupUsuari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("grupUsuari.grupUsuariID");
  }

  @InitBinder("grupUsuariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("grupUsuariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "grupUsuari.grupUsuariID");
  }

  public GrupUsuariWebValidator getWebValidator() {
    return grupUsuariWebValidator;
  }


  public void setWebValidator(GrupUsuariWebValidator __val) {
    if (__val != null) {
      this.grupUsuariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de GrupUsuari
   */
  @RequestMapping(value = "/{grupUsuariID}/cancel")
  public String cancelGrupUsuari(@PathVariable("grupUsuariID") java.lang.Long grupUsuariID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, grupUsuariID);
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


  public List<StringKeyValue> getReferenceListForGrupID(HttpServletRequest request,
       ModelAndView mav, GrupUsuariForm grupUsuariForm, Where where)  throws I18NException {
    if (grupUsuariForm.isHiddenField(GRUPID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (grupUsuariForm.isReadOnlyField(GRUPID)) {
      _where = GrupFields.GRUPID.equal(grupUsuariForm.getGrupUsuari().getGrupID());
    }
    return getReferenceListForGrupID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupID(HttpServletRequest request,
       ModelAndView mav, GrupUsuariFilterForm grupUsuariFilterForm,
       List<GrupUsuari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupUsuariFilterForm.isHiddenField(GRUPID)
      && !grupUsuariFilterForm.isGroupByField(GRUPID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (GrupUsuari _item : list) {
        _pkList.add(_item.getGrupID());
        }
        _w = GrupFields.GRUPID.in(_pkList);
      }
    return getReferenceListForGrupID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForGrupID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return grupRefList.getReferenceList(GrupFields.GRUPID, where );
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, GrupUsuariForm grupUsuariForm, Where where)  throws I18NException {
    if (grupUsuariForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (grupUsuariForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(grupUsuariForm.getGrupUsuari().getUsuariID());
    }
    return getReferenceListForUsuariID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, GrupUsuariFilterForm grupUsuariFilterForm,
       List<GrupUsuari> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (grupUsuariFilterForm.isHiddenField(USUARIID)
      && !grupUsuariFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (GrupUsuari _item : list) {
        _pkList.add(_item.getUsuariID());
        }
        _w = UsuariFields.USUARIID.in(_pkList);
      }
    return getReferenceListForUsuariID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariRefList.getReferenceList(UsuariFields.USUARIID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,GrupUsuariForm grupUsuariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GrupUsuariForm grupUsuariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GrupUsuariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GrupUsuariFilterForm filterForm,  List<GrupUsuari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GrupUsuariForm grupUsuariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GrupUsuariForm grupUsuariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long grupUsuariID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupUsuariID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "grupUsuariFormWebDB";
  }

  public String getTileList() {
    return "grupUsuariListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "GrupUsuariWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GrupUsuariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long grupUsuariID) throws I18NException {
    return (GrupUsuariJPA) grupUsuariEjb.findByPrimaryKey(grupUsuariID);
  }


  public GrupUsuariJPA create(HttpServletRequest request, GrupUsuariJPA grupUsuari)
    throws I18NException, I18NValidationException {
    return (GrupUsuariJPA) grupUsuariEjb.create(grupUsuari);
  }


  public GrupUsuariJPA update(HttpServletRequest request, GrupUsuariJPA grupUsuari)
    throws I18NException, I18NValidationException {
    return (GrupUsuariJPA) grupUsuariEjb.update(grupUsuari);
  }


  public void delete(HttpServletRequest request, GrupUsuari grupUsuari) throws I18NException {
    grupUsuariEjb.delete(grupUsuari);
  }

} // Final de Classe

