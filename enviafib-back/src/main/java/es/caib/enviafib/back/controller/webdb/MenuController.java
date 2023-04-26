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
import es.caib.enviafib.back.form.webdb.MenuForm;

import es.caib.enviafib.back.validator.webdb.MenuWebValidator;

import es.caib.enviafib.persistence.MenuJPA;
import es.caib.enviafib.model.entity.Menu;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Menu
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/menu")
@SessionAttributes(types = { MenuForm.class, MenuFilterForm.class })
public class MenuController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Menu, java.lang.Long> implements MenuFields {

  @EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;

  @EJB(mappedName = es.caib.enviafib.ejb.MenuService.JNDI_NAME)
  protected es.caib.enviafib.ejb.MenuService menuEjb;

  @Autowired
  private MenuWebValidator menuWebValidator;

  @Autowired
  protected MenuRefList menuRefList;

  // References 
  @Autowired
  protected TraduccioRefList traduccioRefList;

  // References 
  @Autowired
  protected GrupRefList grupRefList;

  /**
   * Llistat de totes Menu
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    MenuFilterForm ff;
    ff = (MenuFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Menu de forma paginada
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
    llistat(mav, request, getMenuFilterForm(pagina, mav, request));
    return mav;
  }

  public MenuFilterForm getMenuFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    MenuFilterForm menuFilterForm;
    menuFilterForm = (MenuFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(menuFilterForm == null) {
      menuFilterForm = new MenuFilterForm();
      menuFilterForm.setContexte(getContextWeb());
      menuFilterForm.setEntityNameCode(getEntityNameCode());
      menuFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      menuFilterForm.setNou(true);
    } else {
      menuFilterForm.setNou(false);
    }
    menuFilterForm.setPage(pagina == null ? 1 : pagina);
    return menuFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Menu de forma paginada
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
      @ModelAttribute MenuFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getMenuFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Menu de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Menu> llistat(ModelAndView mav, HttpServletRequest request,
     MenuFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Menu> menu = processarLlistat(menuEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("menuItems", menu);

    mav.addObject("menuFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, menu, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, menu);

    return menu;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(MenuFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Menu> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field titolMenuID
    {
      _listSKV = getReferenceListForTitolMenuID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForTitolMenuID(_tmp);
      if (filterForm.getGroupByFields().contains(TITOLMENUID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TITOLMENUID, false);
      };
    }

    // Field ajudaMenuID
    {
      _listSKV = getReferenceListForAjudaMenuID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfTraduccioForAjudaMenuID(_tmp);
      if (filterForm.getGroupByFields().contains(AJUDAMENUID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, AJUDAMENUID, false);
      };
    }

    // Field tipus
    {
      _listSKV = getReferenceListForTipus(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForTipus(_tmp);
      if (filterForm.getGroupByFields().contains(TIPUS)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, TIPUS, false);
      };
    }

    // Field grupID
    {
      _listSKV = getReferenceListForGrupID(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfGrupForGrupID(_tmp);
      if (filterForm.getGroupByFields().contains(GRUPID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, GRUPID, false);
      };
    }

    // Field parametreCombo
    {
      _listSKV = getReferenceListForParametreCombo(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfValuesForParametreCombo(_tmp);
      if (filterForm.getGroupByFields().contains(PARAMETRECOMBO)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PARAMETRECOMBO, false);
      };
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, ACTIU);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    MenuFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Menu> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_MENU_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(TITOLMENUID, filterForm.getMapOfTraduccioForTitolMenuID());
    __mapping.put(AJUDAMENUID, filterForm.getMapOfTraduccioForAjudaMenuID());
    __mapping.put(TIPUS, filterForm.getMapOfValuesForTipus());
    __mapping.put(GRUPID, filterForm.getMapOfGrupForGrupID());
    __mapping.put(PARAMETRECOMBO, filterForm.getMapOfValuesForParametreCombo());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Menu
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearMenuGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    MenuForm menuForm = getMenuForm(null, false, request, mav);
    
    if (menuForm.getMenu().getTitolMenu() == null){
      es.caib.enviafib.persistence.TraduccioJPA trad = new es.caib.enviafib.persistence.TraduccioJPA();
      for (es.caib.enviafib.model.entity.Idioma idioma : menuForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.enviafib.persistence.TraduccioMapJPA());
      }
      menuForm.getMenu().setTitolMenu(trad);
    }

    
    if (menuForm.getMenu().getAjudaMenu() == null){
      es.caib.enviafib.persistence.TraduccioJPA trad = new es.caib.enviafib.persistence.TraduccioJPA();
      for (es.caib.enviafib.model.entity.Idioma idioma : menuForm.getIdiomesTraduccio()) {
        trad.addTraduccio(idioma.getIdiomaID(), new es.caib.enviafib.persistence.TraduccioMapJPA());
      }
      menuForm.getMenu().setAjudaMenu(trad);
    }

    mav.addObject("menuForm" ,menuForm);
    fillReferencesForForm(menuForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public MenuForm getMenuForm(MenuJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    MenuForm menuForm;
    if(_jpa == null) {
      menuForm = new MenuForm(new MenuJPA(), true);
    } else {
      menuForm = new MenuForm(_jpa, false);
      menuForm.setView(__isView);
    }
    menuForm.setContexte(getContextWeb());
    menuForm.setEntityNameCode(getEntityNameCode());
    menuForm.setEntityNameCodePlural(getEntityNameCodePlural());
    menuForm.setIdiomesTraduccio(getIdiomesSuportats());
    return menuForm;
  }

  public void fillReferencesForForm(MenuForm menuForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (menuForm.getListOfValuesForTipus() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForTipus(request, mav, menuForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      menuForm.setListOfValuesForTipus(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (menuForm.getListOfGrupForGrupID() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForGrupID(request, mav, menuForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      menuForm.setListOfGrupForGrupID(_listSKV);
    }
    // Comprovam si ja esta definida la llista
    if (menuForm.getListOfValuesForParametreCombo() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForParametreCombo(request, mav, menuForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      menuForm.setListOfValuesForParametreCombo(_listSKV);
    }
    
  }


  public List<es.caib.enviafib.model.entity.Idioma> getIdiomesSuportats() throws I18NException {
    List<es.caib.enviafib.model.entity.Idioma> idiomes = idiomaEjb.select(es.caib.enviafib.model.fields.IdiomaFields.SUPORTAT.equal(true));
    return idiomes;
  }


  /**
   * Guardar un nou Menu
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearMenuPost(@ModelAttribute MenuForm menuForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    MenuJPA menu = menuForm.getMenu();

    try {
      preValidate(request, menuForm, result);
      getWebValidator().validate(menuForm, result);
      postValidate(request,menuForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        menu = create(request, menu);
        createMessageSuccess(request, "success.creation", menu.getMenuID());
        menuForm.setMenu(menu);
        return getRedirectWhenCreated(request, menuForm);
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

  @RequestMapping(value = "/view/{menuID}", method = RequestMethod.GET)
  public ModelAndView veureMenuGet(@PathVariable("menuID") java.lang.Long menuID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewMenuGet(menuID,
        request, response, true);
  }


  protected ModelAndView editAndViewMenuGet(@PathVariable("menuID") java.lang.Long menuID,
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
    MenuJPA menu = findByPrimaryKey(request, menuID);

    if (menu == null) {
      createMessageWarning(request, "error.notfound", menuID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, menuID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      MenuForm menuForm = getMenuForm(menu, __isView, request, mav);
      menuForm.setView(__isView);
      if(__isView) {
        menuForm.setAllFieldsReadOnly(ALL_MENU_FIELDS);
        menuForm.setSaveButtonVisible(false);
        menuForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(menuForm, request, mav);
      mav.addObject("menuForm", menuForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Menu existent
   */
  @RequestMapping(value = "/{menuID}/edit", method = RequestMethod.GET)
  public ModelAndView editarMenuGet(@PathVariable("menuID") java.lang.Long menuID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewMenuGet(menuID,
        request, response, false);
  }



  /**
   * Editar un Menu existent
   */
  @RequestMapping(value = "/{menuID}/edit", method = RequestMethod.POST)
  public String editarMenuPost(@ModelAttribute MenuForm menuForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    MenuJPA menu = menuForm.getMenu();

    try {
      preValidate(request, menuForm, result);
      getWebValidator().validate(menuForm, result);
      postValidate(request, menuForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        menu = update(request, menu);
        createMessageSuccess(request, "success.modification", menu.getMenuID());
        status.setComplete();
        return getRedirectWhenModified(request, menuForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          menu.getMenuID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, menuForm, __e);
    }

  }


  /**
   * Eliminar un Menu existent
   */
  @RequestMapping(value = "/{menuID}/delete")
  public String eliminarMenu(@PathVariable("menuID") java.lang.Long menuID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Menu menu = this.findByPrimaryKey(request, menuID);
      if (menu == null) {
        String __msg = createMessageError(request, "error.notfound", menuID);
        return getRedirectWhenDelete(request, menuID, new Exception(__msg));
      } else {
        delete(request, menu);
        createMessageSuccess(request, "success.deleted", menuID);
        return getRedirectWhenDelete(request, menuID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", menuID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, menuID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute MenuFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarMenu(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __menuID, Throwable e) {
    java.lang.Long menuID = (java.lang.Long)__menuID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (menuID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(menuID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "menu.menu";
  }

  public String getEntityNameCodePlural() {
    return "menu.menu.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("menu.menuID");
  }

  @InitBinder("menuFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("menuForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "menu.menuID");
  }

  public MenuWebValidator getWebValidator() {
    return menuWebValidator;
  }


  public void setWebValidator(MenuWebValidator __val) {
    if (__val != null) {
      this.menuWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Menu
   */
  @RequestMapping(value = "/{menuID}/cancel")
  public String cancelMenu(@PathVariable("menuID") java.lang.Long menuID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, menuID);
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

  public List<StringKeyValue> getReferenceListForTitolMenuID(HttpServletRequest request,
       ModelAndView mav, MenuFilterForm menuFilterForm,
       List<Menu> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (menuFilterForm.isHiddenField(TITOLMENUID)
       && !menuFilterForm.isGroupByField(TITOLMENUID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(TITOLMENUID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Menu _item : list) {
        _pkList.add(_item.getTitolMenuID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForTitolMenuID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTitolMenuID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }

  public List<StringKeyValue> getReferenceListForAjudaMenuID(HttpServletRequest request,
       ModelAndView mav, MenuFilterForm menuFilterForm,
       List<Menu> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (menuFilterForm.isHiddenField(AJUDAMENUID)
       && !menuFilterForm.isGroupByField(AJUDAMENUID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(AJUDAMENUID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Menu _item : list) {
        _pkList.add(_item.getAjudaMenuID());
        }
        _w = TraduccioFields.TRADUCCIOID.in(_pkList);
      }
    return getReferenceListForAjudaMenuID(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForAjudaMenuID(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return traduccioRefList.getReferenceList(TraduccioFields.TRADUCCIOID, where );
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, MenuForm menuForm, Where where)  throws I18NException {
    if (menuForm.isHiddenField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForTipus(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, MenuFilterForm menuFilterForm,
       List<Menu> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (menuFilterForm.isHiddenField(TIPUS)
       && !menuFilterForm.isGroupByField(TIPUS)
       && !menuFilterForm.isFilterByField(TIPUS)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForTipus(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForTipus(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    __tmp.add(new StringKeyValue("78" , "78"));
    __tmp.add(new StringKeyValue("9" , "9"));
    __tmp.add(new StringKeyValue("10" , "10"));
    __tmp.add(new StringKeyValue("11" , "11"));
    __tmp.add(new StringKeyValue("12" , "12"));
    __tmp.add(new StringKeyValue("13" , "13"));
    return __tmp;
  }


  public List<StringKeyValue> getReferenceListForGrupID(HttpServletRequest request,
       ModelAndView mav, MenuForm menuForm, Where where)  throws I18NException {
    if (menuForm.isHiddenField(GRUPID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (menuForm.isReadOnlyField(GRUPID)) {
      _where = GrupFields.GRUPID.equal(menuForm.getMenu().getGrupID());
    }
    return getReferenceListForGrupID(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForGrupID(HttpServletRequest request,
       ModelAndView mav, MenuFilterForm menuFilterForm,
       List<Menu> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (menuFilterForm.isHiddenField(GRUPID)
       && !menuFilterForm.isGroupByField(GRUPID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(GRUPID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (Menu _item : list) {
        if(_item.getGrupID() == null) { continue; };
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


  public List<StringKeyValue> getReferenceListForParametreCombo(HttpServletRequest request,
       ModelAndView mav, MenuForm menuForm, Where where)  throws I18NException {
    if (menuForm.isHiddenField(PARAMETRECOMBO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    return getReferenceListForParametreCombo(request, mav, where);
  }


  public List<StringKeyValue> getReferenceListForParametreCombo(HttpServletRequest request,
       ModelAndView mav, MenuFilterForm menuFilterForm,
       List<Menu> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (menuFilterForm.isHiddenField(PARAMETRECOMBO)
       && !menuFilterForm.isGroupByField(PARAMETRECOMBO)
       && !menuFilterForm.isFilterByField(PARAMETRECOMBO)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    return getReferenceListForParametreCombo(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForParametreCombo(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    List<StringKeyValue> __tmp = new java.util.ArrayList<StringKeyValue>();
    __tmp.add(new StringKeyValue("1" , "1"));
    __tmp.add(new StringKeyValue("2" , "2"));
    __tmp.add(new StringKeyValue("3" , "3"));
    __tmp.add(new StringKeyValue("4" , "4"));
    __tmp.add(new StringKeyValue("5" , "5"));
    __tmp.add(new StringKeyValue("6" , "6"));
    return __tmp;
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,MenuForm menuForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,MenuForm menuForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, MenuFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, MenuFilterForm filterForm,  List<Menu> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, MenuForm menuForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, MenuForm menuForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long menuID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long menuID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "menuFormWebDB";
  }

  public String getTileList() {
    return "menuListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "MenuWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public MenuJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long menuID) throws I18NException {
    return (MenuJPA) menuEjb.findByPrimaryKey(menuID);
  }


  public MenuJPA create(HttpServletRequest request, MenuJPA menu)
    throws I18NException, I18NValidationException {
    return (MenuJPA) menuEjb.create(menu);
  }


  public MenuJPA update(HttpServletRequest request, MenuJPA menu)
    throws I18NException, I18NValidationException {
    return (MenuJPA) menuEjb.update(menu);
  }


  public void delete(HttpServletRequest request, Menu menu) throws I18NException {
    menuEjb.delete(menu);
  }

} // Final de Classe

