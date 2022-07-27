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
import es.caib.enviafib.back.form.webdb.InfoArxiuForm;

import es.caib.enviafib.back.validator.webdb.InfoArxiuWebValidator;

import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un InfoArxiu
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/infoArxiu")
@SessionAttributes(types = { InfoArxiuForm.class, InfoArxiuFilterForm.class })
public class InfoArxiuController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<InfoArxiu, java.lang.Long> implements InfoArxiuFields {

  @EJB(mappedName = es.caib.enviafib.ejb.InfoArxiuService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoArxiuService infoArxiuEjb;

  @Autowired
  private InfoArxiuWebValidator infoArxiuWebValidator;

  @Autowired
  protected InfoArxiuRefList infoArxiuRefList;

  /**
   * Llistat de totes InfoArxiu
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    InfoArxiuFilterForm ff;
    ff = (InfoArxiuFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar InfoArxiu de forma paginada
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
    llistat(mav, request, getInfoArxiuFilterForm(pagina, mav, request));
    return mav;
  }

  public InfoArxiuFilterForm getInfoArxiuFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    InfoArxiuFilterForm infoArxiuFilterForm;
    infoArxiuFilterForm = (InfoArxiuFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(infoArxiuFilterForm == null) {
      infoArxiuFilterForm = new InfoArxiuFilterForm();
      infoArxiuFilterForm.setContexte(getContextWeb());
      infoArxiuFilterForm.setEntityNameCode(getEntityNameCode());
      infoArxiuFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      infoArxiuFilterForm.setNou(true);
    } else {
      infoArxiuFilterForm.setNou(false);
    }
    infoArxiuFilterForm.setPage(pagina == null ? 1 : pagina);
    return infoArxiuFilterForm;
  }

  /**
   * Segona i següent peticions per llistar InfoArxiu de forma paginada
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
      @ModelAttribute InfoArxiuFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getInfoArxiuFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de InfoArxiu de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<InfoArxiu> llistat(ModelAndView mav, HttpServletRequest request,
     InfoArxiuFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<InfoArxiu> infoArxiu = processarLlistat(infoArxiuEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("infoArxiuItems", infoArxiu);

    mav.addObject("infoArxiuFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, infoArxiu, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, infoArxiu);

    return infoArxiu;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(InfoArxiuFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<InfoArxiu> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    InfoArxiuFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<InfoArxiu> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INFOARXIU_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou InfoArxiu
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearInfoArxiuGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    InfoArxiuForm infoArxiuForm = getInfoArxiuForm(null, false, request, mav);
    mav.addObject("infoArxiuForm" ,infoArxiuForm);
    fillReferencesForForm(infoArxiuForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public InfoArxiuForm getInfoArxiuForm(InfoArxiuJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    InfoArxiuForm infoArxiuForm;
    if(_jpa == null) {
      infoArxiuForm = new InfoArxiuForm(new InfoArxiuJPA(), true);
    } else {
      infoArxiuForm = new InfoArxiuForm(_jpa, false);
      infoArxiuForm.setView(__isView);
    }
    infoArxiuForm.setContexte(getContextWeb());
    infoArxiuForm.setEntityNameCode(getEntityNameCode());
    infoArxiuForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return infoArxiuForm;
  }

  public void fillReferencesForForm(InfoArxiuForm infoArxiuForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou InfoArxiu
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearInfoArxiuPost(@ModelAttribute InfoArxiuForm infoArxiuForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    InfoArxiuJPA infoArxiu = infoArxiuForm.getInfoArxiu();

    try {
      preValidate(request, infoArxiuForm, result);
      getWebValidator().validate(infoArxiuForm, result);
      postValidate(request,infoArxiuForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoArxiu = create(request, infoArxiu);
        createMessageSuccess(request, "success.creation", infoArxiu.getInfoArxiuID());
        infoArxiuForm.setInfoArxiu(infoArxiu);
        return getRedirectWhenCreated(request, infoArxiuForm);
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

  @RequestMapping(value = "/view/{infoArxiuID}", method = RequestMethod.GET)
  public ModelAndView veureInfoArxiuGet(@PathVariable("infoArxiuID") java.lang.Long infoArxiuID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoArxiuGet(infoArxiuID,
        request, response, true);
  }


  protected ModelAndView editAndViewInfoArxiuGet(@PathVariable("infoArxiuID") java.lang.Long infoArxiuID,
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
    InfoArxiuJPA infoArxiu = findByPrimaryKey(request, infoArxiuID);

    if (infoArxiu == null) {
      createMessageWarning(request, "error.notfound", infoArxiuID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, infoArxiuID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      InfoArxiuForm infoArxiuForm = getInfoArxiuForm(infoArxiu, __isView, request, mav);
      infoArxiuForm.setView(__isView);
      if(__isView) {
        infoArxiuForm.setAllFieldsReadOnly(ALL_INFOARXIU_FIELDS);
        infoArxiuForm.setSaveButtonVisible(false);
        infoArxiuForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(infoArxiuForm, request, mav);
      mav.addObject("infoArxiuForm", infoArxiuForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un InfoArxiu existent
   */
  @RequestMapping(value = "/{infoArxiuID}/edit", method = RequestMethod.GET)
  public ModelAndView editarInfoArxiuGet(@PathVariable("infoArxiuID") java.lang.Long infoArxiuID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoArxiuGet(infoArxiuID,
        request, response, false);
  }



  /**
   * Editar un InfoArxiu existent
   */
  @RequestMapping(value = "/{infoArxiuID}/edit", method = RequestMethod.POST)
  public String editarInfoArxiuPost(@ModelAttribute InfoArxiuForm infoArxiuForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    InfoArxiuJPA infoArxiu = infoArxiuForm.getInfoArxiu();

    try {
      preValidate(request, infoArxiuForm, result);
      getWebValidator().validate(infoArxiuForm, result);
      postValidate(request, infoArxiuForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoArxiu = update(request, infoArxiu);
        createMessageSuccess(request, "success.modification", infoArxiu.getInfoArxiuID());
        status.setComplete();
        return getRedirectWhenModified(request, infoArxiuForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          infoArxiu.getInfoArxiuID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, infoArxiuForm, __e);
    }

  }


  /**
   * Eliminar un InfoArxiu existent
   */
  @RequestMapping(value = "/{infoArxiuID}/delete")
  public String eliminarInfoArxiu(@PathVariable("infoArxiuID") java.lang.Long infoArxiuID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      InfoArxiu infoArxiu = infoArxiuEjb.findByPrimaryKey(infoArxiuID);
      if (infoArxiu == null) {
        String __msg =createMessageError(request, "error.notfound", infoArxiuID);
        return getRedirectWhenDelete(request, infoArxiuID, new Exception(__msg));
      } else {
        delete(request, infoArxiu);
        createMessageSuccess(request, "success.deleted", infoArxiuID);
        return getRedirectWhenDelete(request, infoArxiuID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", infoArxiuID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, infoArxiuID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute InfoArxiuFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarInfoArxiu(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __infoArxiuID, Throwable e) {
    java.lang.Long infoArxiuID = (java.lang.Long)__infoArxiuID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (infoArxiuID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(infoArxiuID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "infoArxiu.infoArxiu";
  }

  public String getEntityNameCodePlural() {
    return "infoArxiu.infoArxiu.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("infoArxiu.infoArxiuID");
  }

  @InitBinder("infoArxiuFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("infoArxiuForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "infoArxiu.infoArxiuID");
  }

  public InfoArxiuWebValidator getWebValidator() {
    return infoArxiuWebValidator;
  }


  public void setWebValidator(InfoArxiuWebValidator __val) {
    if (__val != null) {
      this.infoArxiuWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de InfoArxiu
   */
  @RequestMapping(value = "/{infoArxiuID}/cancel")
  public String cancelInfoArxiu(@PathVariable("infoArxiuID") java.lang.Long infoArxiuID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, infoArxiuID);
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

  public void preValidate(HttpServletRequest request,InfoArxiuForm infoArxiuForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,InfoArxiuForm infoArxiuForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, InfoArxiuFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, InfoArxiuFilterForm filterForm,  List<InfoArxiu> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, InfoArxiuForm infoArxiuForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, InfoArxiuForm infoArxiuForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long infoArxiuID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infoArxiuID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "infoArxiuFormWebDB";
  }

  public String getTileList() {
    return "infoArxiuListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "InfoArxiuWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public InfoArxiuJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infoArxiuID) throws I18NException {
    return (InfoArxiuJPA) infoArxiuEjb.findByPrimaryKey(infoArxiuID);
  }


  public InfoArxiuJPA create(HttpServletRequest request, InfoArxiuJPA infoArxiu)
    throws I18NException, I18NValidationException {
    return (InfoArxiuJPA) infoArxiuEjb.create(infoArxiu);
  }


  public InfoArxiuJPA update(HttpServletRequest request, InfoArxiuJPA infoArxiu)
    throws I18NException, I18NValidationException {
    return (InfoArxiuJPA) infoArxiuEjb.update(infoArxiu);
  }


  public void delete(HttpServletRequest request, InfoArxiu infoArxiu) throws I18NException {
    infoArxiuEjb.delete(infoArxiu);
  }

} // Final de Classe

