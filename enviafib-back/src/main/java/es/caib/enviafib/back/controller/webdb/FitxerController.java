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
import es.caib.enviafib.back.form.webdb.FitxerForm;

import es.caib.enviafib.back.validator.webdb.FitxerWebValidator;

import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Fitxer
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/fitxer")
@SessionAttributes(types = { FitxerForm.class, FitxerFilterForm.class })
public class FitxerController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Fitxer, java.lang.Long> implements FitxerFields {

  @EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
  protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

  @Autowired
  private FitxerWebValidator fitxerWebValidator;

  @Autowired
  protected FitxerRefList fitxerRefList;

  /**
   * Llistat de totes Fitxer
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FitxerFilterForm ff;
    ff = (FitxerFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Fitxer de forma paginada
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
    llistat(mav, request, getFitxerFilterForm(pagina, mav, request));
    return mav;
  }

  public FitxerFilterForm getFitxerFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FitxerFilterForm fitxerFilterForm;
    fitxerFilterForm = (FitxerFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(fitxerFilterForm == null) {
      fitxerFilterForm = new FitxerFilterForm();
      fitxerFilterForm.setContexte(getContextWeb());
      fitxerFilterForm.setEntityNameCode(getEntityNameCode());
      fitxerFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      fitxerFilterForm.setNou(true);
    } else {
      fitxerFilterForm.setNou(false);
    }
    fitxerFilterForm.setPage(pagina == null ? 1 : pagina);
    return fitxerFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Fitxer de forma paginada
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
      @ModelAttribute FitxerFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFitxerFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Fitxer de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Fitxer> llistat(ModelAndView mav, HttpServletRequest request,
     FitxerFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Fitxer> fitxer = processarLlistat(fitxerEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("fitxerItems", fitxer);

    mav.addObject("fitxerFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, fitxer, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, fitxer);

    return fitxer;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FitxerFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Fitxer> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FitxerFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Fitxer> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FITXER_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Fitxer
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFitxerGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FitxerForm fitxerForm = getFitxerForm(null, false, request, mav);
    mav.addObject("fitxerForm" ,fitxerForm);
    fillReferencesForForm(fitxerForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FitxerForm getFitxerForm(FitxerJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FitxerForm fitxerForm;
    if(_jpa == null) {
      fitxerForm = new FitxerForm(new FitxerJPA(), true);
    } else {
      fitxerForm = new FitxerForm(_jpa, false);
      fitxerForm.setView(__isView);
    }
    fitxerForm.setContexte(getContextWeb());
    fitxerForm.setEntityNameCode(getEntityNameCode());
    fitxerForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return fitxerForm;
  }

  public void fillReferencesForForm(FitxerForm fitxerForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Fitxer
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFitxerPost(@ModelAttribute FitxerForm fitxerForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FitxerJPA fitxer = fitxerForm.getFitxer();

    try {
      preValidate(request, fitxerForm, result);
      getWebValidator().validate(fitxerForm, result);
      postValidate(request,fitxerForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        fitxer = create(request, fitxer);
        createMessageSuccess(request, "success.creation", fitxer.getFitxerID());
        fitxerForm.setFitxer(fitxer);
        return getRedirectWhenCreated(request, fitxerForm);
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

  @RequestMapping(value = "/view/{fitxerID}", method = RequestMethod.GET)
  public ModelAndView veureFitxerGet(@PathVariable("fitxerID") java.lang.Long fitxerID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFitxerGet(fitxerID,
        request, response, true);
  }


  protected ModelAndView editAndViewFitxerGet(@PathVariable("fitxerID") java.lang.Long fitxerID,
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
    FitxerJPA fitxer = findByPrimaryKey(request, fitxerID);

    if (fitxer == null) {
      createMessageWarning(request, "error.notfound", fitxerID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, fitxerID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FitxerForm fitxerForm = getFitxerForm(fitxer, __isView, request, mav);
      fitxerForm.setView(__isView);
      if(__isView) {
        fitxerForm.setAllFieldsReadOnly(ALL_FITXER_FIELDS);
        fitxerForm.setSaveButtonVisible(false);
        fitxerForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(fitxerForm, request, mav);
      mav.addObject("fitxerForm", fitxerForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Fitxer existent
   */
  @RequestMapping(value = "/{fitxerID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFitxerGet(@PathVariable("fitxerID") java.lang.Long fitxerID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFitxerGet(fitxerID,
        request, response, false);
  }



  /**
   * Editar un Fitxer existent
   */
  @RequestMapping(value = "/{fitxerID}/edit", method = RequestMethod.POST)
  public String editarFitxerPost(@ModelAttribute FitxerForm fitxerForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FitxerJPA fitxer = fitxerForm.getFitxer();

    try {
      preValidate(request, fitxerForm, result);
      getWebValidator().validate(fitxerForm, result);
      postValidate(request, fitxerForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        fitxer = update(request, fitxer);
        createMessageSuccess(request, "success.modification", fitxer.getFitxerID());
        status.setComplete();
        return getRedirectWhenModified(request, fitxerForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          fitxer.getFitxerID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, fitxerForm, __e);
    }

  }


  /**
   * Eliminar un Fitxer existent
   */
  @RequestMapping(value = "/{fitxerID}/delete")
  public String eliminarFitxer(@PathVariable("fitxerID") java.lang.Long fitxerID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Fitxer fitxer = fitxerEjb.findByPrimaryKey(fitxerID);
      if (fitxer == null) {
        String __msg =createMessageError(request, "error.notfound", fitxerID);
        return getRedirectWhenDelete(request, fitxerID, new Exception(__msg));
      } else {
        delete(request, fitxer);
        createMessageSuccess(request, "success.deleted", fitxerID);
        return getRedirectWhenDelete(request, fitxerID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", fitxerID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, fitxerID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FitxerFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFitxer(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __fitxerID, Throwable e) {
    java.lang.Long fitxerID = (java.lang.Long)__fitxerID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (fitxerID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(fitxerID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "fitxer.fitxer";
  }

  public String getEntityNameCodePlural() {
    return "fitxer.fitxer.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("fitxer.fitxerID");
  }

  @InitBinder("fitxerFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("fitxerForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "fitxer.fitxerID");
  }

  public FitxerWebValidator getWebValidator() {
    return fitxerWebValidator;
  }


  public void setWebValidator(FitxerWebValidator __val) {
    if (__val != null) {
      this.fitxerWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Fitxer
   */
  @RequestMapping(value = "/{fitxerID}/cancel")
  public String cancelFitxer(@PathVariable("fitxerID") java.lang.Long fitxerID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, fitxerID);
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

  public void preValidate(HttpServletRequest request,FitxerForm fitxerForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FitxerForm fitxerForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FitxerFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FitxerFilterForm filterForm,  List<Fitxer> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FitxerForm fitxerForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FitxerForm fitxerForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long fitxerID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long fitxerID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "fitxerFormWebDB";
  }

  public String getTileList() {
    return "fitxerListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "FitxerWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FitxerJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long fitxerID) throws I18NException {
    return (FitxerJPA) fitxerEjb.findByPrimaryKey(fitxerID);
  }


  public FitxerJPA create(HttpServletRequest request, FitxerJPA fitxer)
    throws I18NException, I18NValidationException {
    return (FitxerJPA) fitxerEjb.create(fitxer);
  }


  public FitxerJPA update(HttpServletRequest request, FitxerJPA fitxer)
    throws I18NException, I18NValidationException {
    return (FitxerJPA) fitxerEjb.update(fitxer);
  }


  public void delete(HttpServletRequest request, Fitxer fitxer) throws I18NException {
    fitxerEjb.delete(fitxer);
  }

} // Final de Classe

