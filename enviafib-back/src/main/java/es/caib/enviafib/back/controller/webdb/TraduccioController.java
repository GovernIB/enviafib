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
import es.caib.enviafib.back.form.webdb.TraduccioForm;

import es.caib.enviafib.back.validator.webdb.TraduccioWebValidator;

import es.caib.enviafib.persistence.TraduccioJPA;
import es.caib.enviafib.model.entity.Traduccio;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Traduccio
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/traduccio")
@SessionAttributes(types = { TraduccioForm.class, TraduccioFilterForm.class })
public class TraduccioController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Traduccio, java.lang.Long> implements TraduccioFields {

  @EJB(mappedName = es.caib.enviafib.ejb.TraduccioService.JNDI_NAME)
  protected es.caib.enviafib.ejb.TraduccioService traduccioEjb;

  @Autowired
  private TraduccioWebValidator traduccioWebValidator;

  @Autowired
  protected TraduccioRefList traduccioRefList;

  /**
   * Llistat de totes Traduccio
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    TraduccioFilterForm ff;
    ff = (TraduccioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Traduccio de forma paginada
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
    llistat(mav, request, getTraduccioFilterForm(pagina, mav, request));
    return mav;
  }

  public TraduccioFilterForm getTraduccioFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    TraduccioFilterForm traduccioFilterForm;
    traduccioFilterForm = (TraduccioFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(traduccioFilterForm == null) {
      traduccioFilterForm = new TraduccioFilterForm();
      traduccioFilterForm.setContexte(getContextWeb());
      traduccioFilterForm.setEntityNameCode(getEntityNameCode());
      traduccioFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      traduccioFilterForm.setNou(true);
    } else {
      traduccioFilterForm.setNou(false);
    }
    traduccioFilterForm.setPage(pagina == null ? 1 : pagina);
    return traduccioFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Traduccio de forma paginada
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
      @ModelAttribute TraduccioFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getTraduccioFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Traduccio de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Traduccio> llistat(ModelAndView mav, HttpServletRequest request,
     TraduccioFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Traduccio> traduccio = processarLlistat(traduccioEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("traduccioItems", traduccio);

    mav.addObject("traduccioFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, traduccio, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, traduccio);

    return traduccio;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(TraduccioFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Traduccio> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    TraduccioFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Traduccio> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_TRADUCCIO_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Traduccio
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearTraduccioGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    TraduccioForm traduccioForm = getTraduccioForm(null, false, request, mav);
    mav.addObject("traduccioForm" ,traduccioForm);
    fillReferencesForForm(traduccioForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public TraduccioForm getTraduccioForm(TraduccioJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    TraduccioForm traduccioForm;
    if(_jpa == null) {
      traduccioForm = new TraduccioForm(new TraduccioJPA(), true);
    } else {
      traduccioForm = new TraduccioForm(_jpa, false);
      traduccioForm.setView(__isView);
    }
    traduccioForm.setContexte(getContextWeb());
    traduccioForm.setEntityNameCode(getEntityNameCode());
    traduccioForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return traduccioForm;
  }

  public void fillReferencesForForm(TraduccioForm traduccioForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Traduccio
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearTraduccioPost(@ModelAttribute TraduccioForm traduccioForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    TraduccioJPA traduccio = traduccioForm.getTraduccio();

    try {
      preValidate(request, traduccioForm, result);
      getWebValidator().validate(traduccioForm, result);
      postValidate(request,traduccioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        traduccio = create(request, traduccio);
        createMessageSuccess(request, "success.creation", traduccio.getTraduccioID());
        traduccioForm.setTraduccio(traduccio);
        return getRedirectWhenCreated(request, traduccioForm);
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

  @RequestMapping(value = "/view/{traduccioID}", method = RequestMethod.GET)
  public ModelAndView veureTraduccioGet(@PathVariable("traduccioID") java.lang.Long traduccioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTraduccioGet(traduccioID,
        request, response, true);
  }


  protected ModelAndView editAndViewTraduccioGet(@PathVariable("traduccioID") java.lang.Long traduccioID,
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
    TraduccioJPA traduccio = findByPrimaryKey(request, traduccioID);

    if (traduccio == null) {
      createMessageWarning(request, "error.notfound", traduccioID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, traduccioID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      TraduccioForm traduccioForm = getTraduccioForm(traduccio, __isView, request, mav);
      traduccioForm.setView(__isView);
      if(__isView) {
        traduccioForm.setAllFieldsReadOnly(ALL_TRADUCCIO_FIELDS);
        traduccioForm.setSaveButtonVisible(false);
        traduccioForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(traduccioForm, request, mav);
      mav.addObject("traduccioForm", traduccioForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Traduccio existent
   */
  @RequestMapping(value = "/{traduccioID}/edit", method = RequestMethod.GET)
  public ModelAndView editarTraduccioGet(@PathVariable("traduccioID") java.lang.Long traduccioID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewTraduccioGet(traduccioID,
        request, response, false);
  }



  /**
   * Editar un Traduccio existent
   */
  @RequestMapping(value = "/{traduccioID}/edit", method = RequestMethod.POST)
  public String editarTraduccioPost(@ModelAttribute TraduccioForm traduccioForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    TraduccioJPA traduccio = traduccioForm.getTraduccio();

    try {
      preValidate(request, traduccioForm, result);
      getWebValidator().validate(traduccioForm, result);
      postValidate(request, traduccioForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        traduccio = update(request, traduccio);
        createMessageSuccess(request, "success.modification", traduccio.getTraduccioID());
        status.setComplete();
        return getRedirectWhenModified(request, traduccioForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          traduccio.getTraduccioID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, traduccioForm, __e);
    }

  }


  /**
   * Eliminar un Traduccio existent
   */
  @RequestMapping(value = "/{traduccioID}/delete")
  public String eliminarTraduccio(@PathVariable("traduccioID") java.lang.Long traduccioID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Traduccio traduccio = traduccioEjb.findByPrimaryKey(traduccioID);
      if (traduccio == null) {
        String __msg =createMessageError(request, "error.notfound", traduccioID);
        return getRedirectWhenDelete(request, traduccioID, new Exception(__msg));
      } else {
        delete(request, traduccio);
        createMessageSuccess(request, "success.deleted", traduccioID);
        return getRedirectWhenDelete(request, traduccioID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", traduccioID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, traduccioID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute TraduccioFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarTraduccio(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __traduccioID, Throwable e) {
    java.lang.Long traduccioID = (java.lang.Long)__traduccioID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (traduccioID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(traduccioID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "traduccio.traduccio";
  }

  public String getEntityNameCodePlural() {
    return "traduccio.traduccio.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("traduccio.traduccioID");
  }

  @InitBinder("traduccioFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("traduccioForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "traduccio.traduccioID");
  }

  public TraduccioWebValidator getWebValidator() {
    return traduccioWebValidator;
  }


  public void setWebValidator(TraduccioWebValidator __val) {
    if (__val != null) {
      this.traduccioWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Traduccio
   */
  @RequestMapping(value = "/{traduccioID}/cancel")
  public String cancelTraduccio(@PathVariable("traduccioID") java.lang.Long traduccioID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, traduccioID);
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

  public void preValidate(HttpServletRequest request,TraduccioForm traduccioForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,TraduccioForm traduccioForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, TraduccioFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, TraduccioFilterForm filterForm,  List<Traduccio> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, TraduccioForm traduccioForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, TraduccioForm traduccioForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long traduccioID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long traduccioID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "traduccioFormWebDB";
  }

  public String getTileList() {
    return "traduccioListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "TraduccioWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public TraduccioJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long traduccioID) throws I18NException {
    return (TraduccioJPA) traduccioEjb.findByPrimaryKey(traduccioID);
  }


  public TraduccioJPA create(HttpServletRequest request, TraduccioJPA traduccio)
    throws I18NException, I18NValidationException {
    return (TraduccioJPA) traduccioEjb.create(traduccio);
  }


  public TraduccioJPA update(HttpServletRequest request, TraduccioJPA traduccio)
    throws I18NException, I18NValidationException {
    return (TraduccioJPA) traduccioEjb.update(traduccio);
  }


  public void delete(HttpServletRequest request, Traduccio traduccio) throws I18NException {
    traduccioEjb.delete(traduccio);
  }

} // Final de Classe

