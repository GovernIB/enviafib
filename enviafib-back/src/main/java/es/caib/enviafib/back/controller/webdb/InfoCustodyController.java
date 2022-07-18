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
import es.caib.enviafib.back.form.webdb.InfoCustodyForm;

import es.caib.enviafib.back.validator.webdb.InfoCustodyWebValidator;

import es.caib.enviafib.persistence.InfoCustodyJPA;
import es.caib.enviafib.model.entity.InfoCustody;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un InfoCustody
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/infoCustody")
@SessionAttributes(types = { InfoCustodyForm.class, InfoCustodyFilterForm.class })
public class InfoCustodyController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<InfoCustody, java.lang.Long> implements InfoCustodyFields {

  @EJB(mappedName = es.caib.enviafib.ejb.InfoCustodyService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoCustodyService infoCustodyEjb;

  @Autowired
  private InfoCustodyWebValidator infoCustodyWebValidator;

  @Autowired
  protected InfoCustodyRefList infoCustodyRefList;

  // References 
  @Autowired
  protected PeticioRefList peticioRefList;

  /**
   * Llistat de totes InfoCustody
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    InfoCustodyFilterForm ff;
    ff = (InfoCustodyFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar InfoCustody de forma paginada
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
    llistat(mav, request, getInfoCustodyFilterForm(pagina, mav, request));
    return mav;
  }

  public InfoCustodyFilterForm getInfoCustodyFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    InfoCustodyFilterForm infoCustodyFilterForm;
    infoCustodyFilterForm = (InfoCustodyFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(infoCustodyFilterForm == null) {
      infoCustodyFilterForm = new InfoCustodyFilterForm();
      infoCustodyFilterForm.setContexte(getContextWeb());
      infoCustodyFilterForm.setEntityNameCode(getEntityNameCode());
      infoCustodyFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      infoCustodyFilterForm.setNou(true);
    } else {
      infoCustodyFilterForm.setNou(false);
    }
    infoCustodyFilterForm.setPage(pagina == null ? 1 : pagina);
    return infoCustodyFilterForm;
  }

  /**
   * Segona i següent peticions per llistar InfoCustody de forma paginada
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
      @ModelAttribute InfoCustodyFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getInfoCustodyFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de InfoCustody de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<InfoCustody> llistat(ModelAndView mav, HttpServletRequest request,
     InfoCustodyFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<InfoCustody> infoCustody = processarLlistat(infoCustodyEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("infoCustodyItems", infoCustody);

    mav.addObject("infoCustodyFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, infoCustody, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, infoCustody);

    return infoCustody;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(InfoCustodyFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<InfoCustody> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }

    Map<String, String> _tmp;
    List<StringKeyValue> _listSKV;

    // Field peticioid
    {
      _listSKV = getReferenceListForPeticioid(request, mav, filterForm, list, groupByItemsMap, null);
      _tmp = Utils.listToMap(_listSKV);
      filterForm.setMapOfPeticioForPeticioid(_tmp);
      if (filterForm.getGroupByFields().contains(PETICIOID)) {
        fillValuesToGroupByItems(_tmp, groupByItemsMap, PETICIOID, false);
      };
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    InfoCustodyFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<InfoCustody> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INFOCUSTODY_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    __mapping.put(PETICIOID, filterForm.getMapOfPeticioForPeticioid());
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou InfoCustody
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearInfoCustodyGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    InfoCustodyForm infoCustodyForm = getInfoCustodyForm(null, false, request, mav);
    mav.addObject("infoCustodyForm" ,infoCustodyForm);
    fillReferencesForForm(infoCustodyForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public InfoCustodyForm getInfoCustodyForm(InfoCustodyJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    InfoCustodyForm infoCustodyForm;
    if(_jpa == null) {
      infoCustodyForm = new InfoCustodyForm(new InfoCustodyJPA(), true);
    } else {
      infoCustodyForm = new InfoCustodyForm(_jpa, false);
      infoCustodyForm.setView(__isView);
    }
    infoCustodyForm.setContexte(getContextWeb());
    infoCustodyForm.setEntityNameCode(getEntityNameCode());
    infoCustodyForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return infoCustodyForm;
  }

  public void fillReferencesForForm(InfoCustodyForm infoCustodyForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    // Comprovam si ja esta definida la llista
    if (infoCustodyForm.getListOfPeticioForPeticioid() == null) {
      List<StringKeyValue> _listSKV = getReferenceListForPeticioid(request, mav, infoCustodyForm, null);

      if(_listSKV != null && !_listSKV.isEmpty()) { 
          java.util.Collections.sort(_listSKV, STRINGKEYVALUE_COMPARATOR);
      }
      infoCustodyForm.setListOfPeticioForPeticioid(_listSKV);
    }
    
  }

  /**
   * Guardar un nou InfoCustody
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearInfoCustodyPost(@ModelAttribute InfoCustodyForm infoCustodyForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    InfoCustodyJPA infoCustody = infoCustodyForm.getInfoCustody();

    try {
      preValidate(request, infoCustodyForm, result);
      getWebValidator().validate(infoCustodyForm, result);
      postValidate(request,infoCustodyForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoCustody = create(request, infoCustody);
        createMessageSuccess(request, "success.creation", infoCustody.getInfocustodyid());
        infoCustodyForm.setInfoCustody(infoCustody);
        return getRedirectWhenCreated(request, infoCustodyForm);
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

  @RequestMapping(value = "/view/{infocustodyid}", method = RequestMethod.GET)
  public ModelAndView veureInfoCustodyGet(@PathVariable("infocustodyid") java.lang.Long infocustodyid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoCustodyGet(infocustodyid,
        request, response, true);
  }


  protected ModelAndView editAndViewInfoCustodyGet(@PathVariable("infocustodyid") java.lang.Long infocustodyid,
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
    InfoCustodyJPA infoCustody = findByPrimaryKey(request, infocustodyid);

    if (infoCustody == null) {
      createMessageWarning(request, "error.notfound", infocustodyid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, infocustodyid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      InfoCustodyForm infoCustodyForm = getInfoCustodyForm(infoCustody, __isView, request, mav);
      infoCustodyForm.setView(__isView);
      if(__isView) {
        infoCustodyForm.setAllFieldsReadOnly(ALL_INFOCUSTODY_FIELDS);
        infoCustodyForm.setSaveButtonVisible(false);
        infoCustodyForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(infoCustodyForm, request, mav);
      mav.addObject("infoCustodyForm", infoCustodyForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un InfoCustody existent
   */
  @RequestMapping(value = "/{infocustodyid}/edit", method = RequestMethod.GET)
  public ModelAndView editarInfoCustodyGet(@PathVariable("infocustodyid") java.lang.Long infocustodyid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoCustodyGet(infocustodyid,
        request, response, false);
  }



  /**
   * Editar un InfoCustody existent
   */
  @RequestMapping(value = "/{infocustodyid}/edit", method = RequestMethod.POST)
  public String editarInfoCustodyPost(@ModelAttribute InfoCustodyForm infoCustodyForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    InfoCustodyJPA infoCustody = infoCustodyForm.getInfoCustody();

    try {
      preValidate(request, infoCustodyForm, result);
      getWebValidator().validate(infoCustodyForm, result);
      postValidate(request, infoCustodyForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoCustody = update(request, infoCustody);
        createMessageSuccess(request, "success.modification", infoCustody.getInfocustodyid());
        status.setComplete();
        return getRedirectWhenModified(request, infoCustodyForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          infoCustody.getInfocustodyid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, infoCustodyForm, __e);
    }

  }


  /**
   * Eliminar un InfoCustody existent
   */
  @RequestMapping(value = "/{infocustodyid}/delete")
  public String eliminarInfoCustody(@PathVariable("infocustodyid") java.lang.Long infocustodyid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      InfoCustody infoCustody = infoCustodyEjb.findByPrimaryKey(infocustodyid);
      if (infoCustody == null) {
        String __msg =createMessageError(request, "error.notfound", infocustodyid);
        return getRedirectWhenDelete(request, infocustodyid, new Exception(__msg));
      } else {
        delete(request, infoCustody);
        createMessageSuccess(request, "success.deleted", infocustodyid);
        return getRedirectWhenDelete(request, infocustodyid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", infocustodyid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, infocustodyid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute InfoCustodyFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarInfoCustody(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __infocustodyid, Throwable e) {
    java.lang.Long infocustodyid = (java.lang.Long)__infocustodyid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (infocustodyid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(infocustodyid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "infoCustody.infoCustody";
  }

  public String getEntityNameCodePlural() {
    return "infoCustody.infoCustody.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("infoCustody.infocustodyid");
  }

  @InitBinder("infoCustodyFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("infoCustodyForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "infoCustody.infocustodyid");
  }

  public InfoCustodyWebValidator getWebValidator() {
    return infoCustodyWebValidator;
  }


  public void setWebValidator(InfoCustodyWebValidator __val) {
    if (__val != null) {
      this.infoCustodyWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de InfoCustody
   */
  @RequestMapping(value = "/{infocustodyid}/cancel")
  public String cancelInfoCustody(@PathVariable("infocustodyid") java.lang.Long infocustodyid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, infocustodyid);
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


  public List<StringKeyValue> getReferenceListForPeticioid(HttpServletRequest request,
       ModelAndView mav, InfoCustodyForm infoCustodyForm, Where where)  throws I18NException {
    if (infoCustodyForm.isHiddenField(PETICIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _where = null;
    if (infoCustodyForm.isReadOnlyField(PETICIOID)) {
      _where = PeticioFields.PETICIOID.equal(infoCustodyForm.getInfoCustody().getPeticioid());
    }
    return getReferenceListForPeticioid(request, mav, Where.AND(where, _where));
  }


  public List<StringKeyValue> getReferenceListForPeticioid(HttpServletRequest request,
       ModelAndView mav, InfoCustodyFilterForm infoCustodyFilterForm,
       List<InfoCustody> list, Map<Field<?>, GroupByItem> _groupByItemsMap, Where where)  throws I18NException {
    if (infoCustodyFilterForm.isHiddenField(PETICIOID)
      && !infoCustodyFilterForm.isGroupByField(PETICIOID)) {
      return EMPTY_STRINGKEYVALUE_LIST;
    }
    Where _w = null;
    if (!_groupByItemsMap.containsKey(PETICIOID)) {
      // OBTENIR TOTES LES CLAUS (PK) i despres només cercar referències d'aquestes PK
      java.util.Set<java.lang.Long> _pkList = new java.util.HashSet<java.lang.Long>();
      for (InfoCustody _item : list) {
        _pkList.add(_item.getPeticioid());
        }
        _w = PeticioFields.PETICIOID.in(_pkList);
      }
    return getReferenceListForPeticioid(request, mav, Where.AND(where,_w));
  }


  public List<StringKeyValue> getReferenceListForPeticioid(HttpServletRequest request,
       ModelAndView mav, Where where)  throws I18NException {
    return peticioRefList.getReferenceList(PeticioFields.PETICIOID, where );
  }


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,InfoCustodyForm infoCustodyForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,InfoCustodyForm infoCustodyForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, InfoCustodyFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, InfoCustodyFilterForm filterForm,  List<InfoCustody> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, InfoCustodyForm infoCustodyForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, InfoCustodyForm infoCustodyForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long infocustodyid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infocustodyid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "infoCustodyFormWebDB";
  }

  public String getTileList() {
    return "infoCustodyListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "InfoCustodyWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public InfoCustodyJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infocustodyid) throws I18NException {
    return (InfoCustodyJPA) infoCustodyEjb.findByPrimaryKey(infocustodyid);
  }


  public InfoCustodyJPA create(HttpServletRequest request, InfoCustodyJPA infoCustody)
    throws I18NException, I18NValidationException {
    return (InfoCustodyJPA) infoCustodyEjb.create(infoCustody);
  }


  public InfoCustodyJPA update(HttpServletRequest request, InfoCustodyJPA infoCustody)
    throws I18NException, I18NValidationException {
    return (InfoCustodyJPA) infoCustodyEjb.update(infoCustody);
  }


  public void delete(HttpServletRequest request, InfoCustody infoCustody) throws I18NException {
    infoCustodyEjb.delete(infoCustody);
  }

} // Final de Classe

