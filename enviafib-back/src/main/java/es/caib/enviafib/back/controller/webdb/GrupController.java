package es.caib.enviafib.back.controller.webdb;

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
import es.caib.enviafib.back.form.webdb.GrupForm;

import es.caib.enviafib.back.validator.webdb.GrupWebValidator;

import es.caib.enviafib.persistence.GrupJPA;
import es.caib.enviafib.model.entity.Grup;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Grup
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/grup")
@SessionAttributes(types = { GrupForm.class, GrupFilterForm.class })
public class GrupController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Grup, java.lang.Long> implements GrupFields {

  @EJB(mappedName = es.caib.enviafib.ejb.GrupService.JNDI_NAME)
  protected es.caib.enviafib.ejb.GrupService grupEjb;

  @Autowired
  private GrupWebValidator grupWebValidator;

  @Autowired
  protected GrupRefList grupRefList;

  /**
   * Llistat de totes Grup
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    GrupFilterForm ff;
    ff = (GrupFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Grup de forma paginada
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
    llistat(mav, request, getGrupFilterForm(pagina, mav, request));
    return mav;
  }

  public GrupFilterForm getGrupFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    GrupFilterForm grupFilterForm;
    grupFilterForm = (GrupFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(grupFilterForm == null) {
      grupFilterForm = new GrupFilterForm();
      grupFilterForm.setContexte(getContextWeb());
      grupFilterForm.setEntityNameCode(getEntityNameCode());
      grupFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      grupFilterForm.setNou(true);
    } else {
      grupFilterForm.setNou(false);
    }
    grupFilterForm.setPage(pagina == null ? 1 : pagina);
    return grupFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Grup de forma paginada
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
      @ModelAttribute GrupFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getGrupFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Grup de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Grup> llistat(ModelAndView mav, HttpServletRequest request,
     GrupFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Grup> grup = processarLlistat(grupEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("grupItems", grup);

    mav.addObject("grupFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, grup, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, grup);

    return grup;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(GrupFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Grup> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    GrupFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Grup> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_GRUP_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Grup
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearGrupGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    GrupForm grupForm = getGrupForm(null, false, request, mav);
    mav.addObject("grupForm" ,grupForm);
    fillReferencesForForm(grupForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public GrupForm getGrupForm(GrupJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    GrupForm grupForm;
    if(_jpa == null) {
      grupForm = new GrupForm(new GrupJPA(), true);
    } else {
      grupForm = new GrupForm(_jpa, false);
      grupForm.setView(__isView);
    }
    grupForm.setContexte(getContextWeb());
    grupForm.setEntityNameCode(getEntityNameCode());
    grupForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return grupForm;
  }

  public void fillReferencesForForm(GrupForm grupForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Grup
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearGrupPost(@ModelAttribute GrupForm grupForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    GrupJPA grup = grupForm.getGrup();

    try {
      preValidate(request, grupForm, result);
      getWebValidator().validate(grupForm, result);
      postValidate(request,grupForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grup = create(request, grup);
        createMessageSuccess(request, "success.creation", grup.getGrupID());
        grupForm.setGrup(grup);
        return getRedirectWhenCreated(request, grupForm);
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

  @RequestMapping(value = "/view/{grupID}", method = RequestMethod.GET)
  public ModelAndView veureGrupGet(@PathVariable("grupID") java.lang.Long grupID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupGet(grupID,
        request, response, true);
  }


  protected ModelAndView editAndViewGrupGet(@PathVariable("grupID") java.lang.Long grupID,
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
    GrupJPA grup = findByPrimaryKey(request, grupID);

    if (grup == null) {
      createMessageWarning(request, "error.notfound", grupID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, grupID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      GrupForm grupForm = getGrupForm(grup, __isView, request, mav);
      grupForm.setView(__isView);
      if(__isView) {
        grupForm.setAllFieldsReadOnly(ALL_GRUP_FIELDS);
        grupForm.setSaveButtonVisible(false);
        grupForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(grupForm, request, mav);
      mav.addObject("grupForm", grupForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Grup existent
   */
  @RequestMapping(value = "/{grupID}/edit", method = RequestMethod.GET)
  public ModelAndView editarGrupGet(@PathVariable("grupID") java.lang.Long grupID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewGrupGet(grupID,
        request, response, false);
  }



  /**
   * Editar un Grup existent
   */
  @RequestMapping(value = "/{grupID}/edit", method = RequestMethod.POST)
  public String editarGrupPost(@ModelAttribute GrupForm grupForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    GrupJPA grup = grupForm.getGrup();

    try {
      preValidate(request, grupForm, result);
      getWebValidator().validate(grupForm, result);
      postValidate(request, grupForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        grup = update(request, grup);
        createMessageSuccess(request, "success.modification", grup.getGrupID());
        status.setComplete();
        return getRedirectWhenModified(request, grupForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          grup.getGrupID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, grupForm, __e);
    }

  }


  /**
   * Eliminar un Grup existent
   */
  @RequestMapping(value = "/{grupID}/delete")
  public String eliminarGrup(@PathVariable("grupID") java.lang.Long grupID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Grup grup = grupEjb.findByPrimaryKey(grupID);
      if (grup == null) {
        String __msg =createMessageError(request, "error.notfound", grupID);
        return getRedirectWhenDelete(request, grupID, new Exception(__msg));
      } else {
        delete(request, grup);
        createMessageSuccess(request, "success.deleted", grupID);
        return getRedirectWhenDelete(request, grupID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", grupID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, grupID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute GrupFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarGrup(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __grupID, Throwable e) {
    java.lang.Long grupID = (java.lang.Long)__grupID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (grupID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(grupID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "grup.grup";
  }

  public String getEntityNameCodePlural() {
    return "grup.grup.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("grup.grupID");
  }

  @InitBinder("grupFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("grupForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "grup.grupID");
  }

  public GrupWebValidator getWebValidator() {
    return grupWebValidator;
  }


  public void setWebValidator(GrupWebValidator __val) {
    if (__val != null) {
      this.grupWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Grup
   */
  @RequestMapping(value = "/{grupID}/cancel")
  public String cancelGrup(@PathVariable("grupID") java.lang.Long grupID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, grupID);
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


  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public void preValidate(HttpServletRequest request,GrupForm grupForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,GrupForm grupForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, GrupFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, GrupFilterForm filterForm,  List<Grup> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, GrupForm grupForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, GrupForm grupForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long grupID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long grupID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "grupFormWebDB";
  }

  public String getTileList() {
    return "grupListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "GrupWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public GrupJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long grupID) throws I18NException {
    return (GrupJPA) grupEjb.findByPrimaryKey(grupID);
  }


  public GrupJPA create(HttpServletRequest request, GrupJPA grup)
    throws I18NException, I18NValidationException {
    return (GrupJPA) grupEjb.create(grup);
  }


  public GrupJPA update(HttpServletRequest request, GrupJPA grup)
    throws I18NException, I18NValidationException {
    return (GrupJPA) grupEjb.update(grup);
  }


  public void delete(HttpServletRequest request, Grup grup) throws I18NException {
    grupEjb.delete(grup);
  }

} // Final de Classe

