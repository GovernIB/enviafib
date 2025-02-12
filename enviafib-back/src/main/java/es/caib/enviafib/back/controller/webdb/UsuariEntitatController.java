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
import es.caib.enviafib.back.form.webdb.UsuariEntitatForm;

import es.caib.enviafib.back.validator.webdb.UsuariEntitatWebValidator;

import es.caib.enviafib.persistence.UsuariEntitatJPA;
import es.caib.enviafib.model.entity.UsuariEntitat;
import es.caib.enviafib.model.fields.*;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;

/**
 * Controller per gestionar un UsuariEntitat
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@MenuOption(labelCode="usuariEntitat.usuariEntitat.plural", order=170, group="WEBDB")
@Controller
@RequestMapping(value = "/webdb/usuariEntitat")
@SessionAttributes(types = { UsuariEntitatForm.class, UsuariEntitatFilterForm.class })
public class UsuariEntitatController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<UsuariEntitat, java.lang.Long> implements UsuariEntitatFields {

  @EJB(mappedName = es.caib.enviafib.ejb.UsuariEntitatService.JNDI_NAME)
  protected es.caib.enviafib.ejb.UsuariEntitatService usuariEntitatEjb;

  @Autowired
  private UsuariEntitatWebValidator usuariEntitatWebValidator;

  @Autowired
  protected UsuariEntitatRefList usuariEntitatRefList;

  // References 
  @Autowired
  protected UsuariRefList usuariRefList;

  // References 
  @Autowired
  protected EntitatRefList entitatRefList;

  /**
   * Llistat de totes UsuariEntitat
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UsuariEntitatFilterForm ff;
    ff = (UsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar UsuariEntitat de forma paginada
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
    llistat(mav, request, getUsuariEntitatFilterForm(pagina, mav, request));
    return mav;
  }

  public UsuariEntitatFilterForm getUsuariEntitatFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UsuariEntitatFilterForm usuariEntitatFilterForm;
    usuariEntitatFilterForm = (UsuariEntitatFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(usuariEntitatFilterForm == null) {
      usuariEntitatFilterForm = new UsuariEntitatFilterForm();
      usuariEntitatFilterForm.setContexte(getContextWeb());
      usuariEntitatFilterForm.setEntityNameCode(getEntityNameCode());
      usuariEntitatFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      usuariEntitatFilterForm.setNou(true);
    } else {
      usuariEntitatFilterForm.setNou(false);
    }
    usuariEntitatFilterForm.setPage(pagina == null ? 1 : pagina);
    return usuariEntitatFilterForm;
  }

  /**
   * Segona i següent peticions per llistar UsuariEntitat de forma paginada
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
      @ModelAttribute UsuariEntitatFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUsuariEntitatFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de UsuariEntitat de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<UsuariEntitat> llistat(ModelAndView mav, HttpServletRequest request,
     UsuariEntitatFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<UsuariEntitat> usuariEntitat = processarLlistat(usuariEntitatEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("usuariEntitatItems", usuariEntitat);

    mav.addObject("usuariEntitatFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, usuariEntitat, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, usuariEntitat);

    return usuariEntitat;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariEntitatFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<UsuariEntitat> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field usuariid
    {
      _listSKV = getReferenceListForUsuariid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfUsuariForUsuariid(_tmp);
      if (filterForm.getGroupByFields().contains(USUARIID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, USUARIID, false);
      };
    }

    // Field entitatid
    {
      _listSKV = getReferenceListForEntitatid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfEntitatForEntitatid(_tmp);
      if (filterForm.getGroupByFields().contains(ENTITATID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, ENTITATID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    UsuariEntitatFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<UsuariEntitat> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_USUARIENTITAT_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(USUARIID, filterForm.getMapOfUsuariForUsuariid());
    __mapping.put(ENTITATID, filterForm.getMapOfEntitatForEntitatid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou UsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUsuariEntitatGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UsuariEntitatForm usuariEntitatForm = getUsuariEntitatForm(null, false, request, mav);
    mav.addObject("usuariEntitatForm" ,usuariEntitatForm);
    fillReferencesForForm(usuariEntitatForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UsuariEntitatForm getUsuariEntitatForm(UsuariEntitatJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariEntitatForm usuariEntitatForm;
    if(_jpa == null) {
      usuariEntitatForm = new UsuariEntitatForm(new UsuariEntitatJPA(), true);
    } else {
      usuariEntitatForm = new UsuariEntitatForm(_jpa, false);
      usuariEntitatForm.setView(__isView);
    }
    usuariEntitatForm.setContexte(getContextWeb());
    usuariEntitatForm.setEntityNameCode(getEntityNameCode());
    usuariEntitatForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return usuariEntitatForm;
  }

  public void fillReferencesForForm(UsuariEntitatForm usuariEntitatForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (usuariEntitatForm.getListOfUsuariForUsuariid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForUsuariid(request, mav, usuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariEntitatForm.setListOfUsuariForUsuariid(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (usuariEntitatForm.getListOfEntitatForEntitatid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForEntitatid(request, mav, usuariEntitatForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      usuariEntitatForm.setListOfEntitatForEntitatid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou UsuariEntitat
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUsuariEntitatPost(@ModelAttribute UsuariEntitatForm usuariEntitatForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UsuariEntitatJPA usuariEntitat = usuariEntitatForm.getUsuariEntitat();

    try {
      preValidate(request, usuariEntitatForm, result);
      getWebValidator().validate(usuariEntitatForm, result);
      postValidate(request,usuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuariEntitat = create(request, usuariEntitat);
        createMessageSuccess(request, "success.creation", usuariEntitat.getUsuarientitatid());
        usuariEntitatForm.setUsuariEntitat(usuariEntitat);
        return getRedirectWhenCreated(request, usuariEntitatForm);
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

  @RequestMapping(value = "/view/{usuarientitatid}", method = RequestMethod.GET)
  public ModelAndView veureUsuariEntitatGet(@PathVariable("usuarientitatid") java.lang.Long usuarientitatid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatGet(usuarientitatid,
        request, response, true);
  }


  protected ModelAndView editAndViewUsuariEntitatGet(@PathVariable("usuarientitatid") java.lang.Long usuarientitatid,
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
    UsuariEntitatJPA usuariEntitat = findByPrimaryKey(request, usuarientitatid);

    if (usuariEntitat == null) {
      createMessageWarning(request, "error.notfound", usuarientitatid);
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UsuariEntitatForm usuariEntitatForm = getUsuariEntitatForm(usuariEntitat, __isView, request, mav);
      usuariEntitatForm.setView(__isView);
      if(__isView) {
        usuariEntitatForm.setAllFieldsReadOnly(ALL_USUARIENTITAT_FIELDS);
        usuariEntitatForm.setSaveButtonVisible(false);
        usuariEntitatForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(usuariEntitatForm, request, mav);
      mav.addObject("usuariEntitatForm", usuariEntitatForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un UsuariEntitat existent
   */
  @RequestMapping(value = "/{usuarientitatid}/edit", method = RequestMethod.GET)
  public ModelAndView editarUsuariEntitatGet(@PathVariable("usuarientitatid") java.lang.Long usuarientitatid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariEntitatGet(usuarientitatid,
        request, response, false);
  }



  /**
   * Editar un UsuariEntitat existent
   */
  @RequestMapping(value = "/{usuarientitatid}/edit", method = RequestMethod.POST)
  public String editarUsuariEntitatPost(@ModelAttribute UsuariEntitatForm usuariEntitatForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UsuariEntitatJPA usuariEntitat = usuariEntitatForm.getUsuariEntitat();

    try {
      preValidate(request, usuariEntitatForm, result);
      getWebValidator().validate(usuariEntitatForm, result);
      postValidate(request, usuariEntitatForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuariEntitat = update(request, usuariEntitat);
        createMessageSuccess(request, "success.modification", usuariEntitat.getUsuarientitatid());
        status.setComplete();
        return getRedirectWhenModified(request, usuariEntitatForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          usuariEntitat.getUsuarientitatid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, usuariEntitatForm, __e);
    }

  }


  /**
   * Eliminar un UsuariEntitat existent
   */
  @RequestMapping(value = "/{usuarientitatid}/delete")
  public String eliminarUsuariEntitat(@PathVariable("usuarientitatid") java.lang.Long usuarientitatid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      UsuariEntitat usuariEntitat = this.findByPrimaryKey(request, usuarientitatid);
      if (usuariEntitat == null) {
        String __msg = createMessageError(request, "error.notfound", usuarientitatid);
        return getRedirectWhenDelete(request, usuarientitatid, new Exception(__msg));
      } else {
        delete(request, usuariEntitat);
        createMessageSuccess(request, "success.deleted", usuarientitatid);
        return getRedirectWhenDelete(request, usuarientitatid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", usuarientitatid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, usuarientitatid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UsuariEntitatFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUsuariEntitat(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __usuarientitatid, Throwable e) {
    java.lang.Long usuarientitatid = (java.lang.Long)__usuarientitatid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (usuarientitatid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(usuarientitatid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "usuariEntitat.usuariEntitat";
  }

  public String getEntityNameCodePlural() {
    return "usuariEntitat.usuariEntitat.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("usuariEntitat.usuarientitatid");
  }

  @InitBinder("usuariEntitatFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("usuariEntitatForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "usuariEntitat.usuarientitatid");
  }

  public UsuariEntitatWebValidator getWebValidator() {
    return usuariEntitatWebValidator;
  }


  public void setWebValidator(UsuariEntitatWebValidator __val) {
    if (__val != null) {
      this.usuariEntitatWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de UsuariEntitat
   */
  @RequestMapping(value = "/{usuarientitatid}/cancel")
  public String cancelUsuariEntitat(@PathVariable("usuarientitatid") java.lang.Long usuarientitatid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, usuarientitatid);
  }

  /**
   * Entra aqui al pitjar el boto cancel en el la creació de UsuariEntitat
   */
  @RequestMapping(value = "/cancel")
  public String cancelUsuariEntitat(HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, null);
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


  public List<StringKeyValue> getReferenceListForUsuariid(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatForm usuariEntitatForm, Where where)  throws I18NException {
    if (usuariEntitatForm.isHiddenField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariEntitatForm.isReadOnlyField(USUARIID)) {
      _where = UsuariFields.USUARIID.equal(usuariEntitatForm.getUsuariEntitat().getUsuariid());
    }
    return getReferenceListForUsuariid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForUsuariid(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFilterForm usuariEntitatFilterForm,
       List<UsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariEntitatFilterForm.isHiddenField(USUARIID)
       && !usuariEntitatFilterForm.isGroupByField(USUARIID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(USUARIID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (UsuariEntitat _item : list) {
        _pkList.add(_item.getUsuariid());
        }
        _w = UsuariFields.USUARIID.in(_pkList);
      }
    return getReferenceListForUsuariid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForUsuariid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return usuariRefList.getReferenceList(UsuariFields.USUARIID, where );
  }


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatForm usuariEntitatForm, Where where)  throws I18NException {
    if (usuariEntitatForm.isHiddenField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (usuariEntitatForm.isReadOnlyField(ENTITATID)) {
      _where = EntitatFields.ENTITATID.equal(usuariEntitatForm.getUsuariEntitat().getEntitatid());
    }
    return getReferenceListForEntitatid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, UsuariEntitatFilterForm usuariEntitatFilterForm,
       List<UsuariEntitat> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (usuariEntitatFilterForm.isHiddenField(ENTITATID)
       && !usuariEntitatFilterForm.isGroupByField(ENTITATID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(ENTITATID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.String> _pkList = new java.util.HashSet<java.lang.String>();
      for (UsuariEntitat _item : list) {
        _pkList.add(_item.getEntitatid());
        }
        _w = EntitatFields.ENTITATID.in(_pkList);
      }
    return getReferenceListForEntitatid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForEntitatid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return entitatRefList.getReferenceList(EntitatFields.ENTITATID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,UsuariEntitatForm usuariEntitatForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UsuariEntitatForm usuariEntitatForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UsuariEntitatFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UsuariEntitatFilterForm filterForm,  List<UsuariEntitat> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UsuariEntitatForm usuariEntitatForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long usuarientitatid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuarientitatid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "usuariEntitatFormWebDB";
  }

  public String getTileList() {
    return "usuariEntitatListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "UsuariEntitat_FilterForm_" + this.getClass().getName();
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UsuariEntitatJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuarientitatid) throws I18NException {
    return (UsuariEntitatJPA) usuariEntitatEjb.findByPrimaryKey(usuarientitatid);
  }


  public UsuariEntitatJPA create(HttpServletRequest request, UsuariEntitatJPA usuariEntitat)
    throws I18NException, I18NValidationException {
    return (UsuariEntitatJPA) usuariEntitatEjb.create(usuariEntitat);
  }


  public UsuariEntitatJPA update(HttpServletRequest request, UsuariEntitatJPA usuariEntitat)
    throws I18NException, I18NValidationException {
    return (UsuariEntitatJPA) usuariEntitatEjb.update(usuariEntitat);
  }


  public void delete(HttpServletRequest request, UsuariEntitat usuariEntitat) throws I18NException {
    usuariEntitatEjb.delete(usuariEntitat);
  }

} // Final de Classe

