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
import es.caib.enviafib.back.form.webdb.InfoSignaturaForm;

import es.caib.enviafib.back.validator.webdb.InfoSignaturaWebValidator;

import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un InfoSignatura
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/infoSignatura")
@SessionAttributes(types = { InfoSignaturaForm.class, InfoSignaturaFilterForm.class })
public class InfoSignaturaController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<InfoSignatura, java.lang.Long> implements InfoSignaturaFields {

  @EJB(mappedName = es.caib.enviafib.ejb.InfoSignaturaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.InfoSignaturaService infoSignaturaEjb;

  @Autowired
  private InfoSignaturaWebValidator infoSignaturaWebValidator;

  @Autowired
  protected InfoSignaturaRefList infoSignaturaRefList;

  /**
   * Llistat de totes InfoSignatura
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    InfoSignaturaFilterForm ff;
    ff = (InfoSignaturaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar InfoSignatura de forma paginada
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
    llistat(mav, request, getInfoSignaturaFilterForm(pagina, mav, request));
    return mav;
  }

  public InfoSignaturaFilterForm getInfoSignaturaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    InfoSignaturaFilterForm infoSignaturaFilterForm;
    infoSignaturaFilterForm = (InfoSignaturaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(infoSignaturaFilterForm == null) {
      infoSignaturaFilterForm = new InfoSignaturaFilterForm();
      infoSignaturaFilterForm.setContexte(getContextWeb());
      infoSignaturaFilterForm.setEntityNameCode(getEntityNameCode());
      infoSignaturaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      infoSignaturaFilterForm.setNou(true);
    } else {
      infoSignaturaFilterForm.setNou(false);
    }
    infoSignaturaFilterForm.setPage(pagina == null ? 1 : pagina);
    return infoSignaturaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar InfoSignatura de forma paginada
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
      @ModelAttribute InfoSignaturaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getInfoSignaturaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de InfoSignatura de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<InfoSignatura> llistat(ModelAndView mav, HttpServletRequest request,
     InfoSignaturaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<InfoSignatura> infoSignatura = processarLlistat(infoSignaturaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("infoSignaturaItems", infoSignatura);

    mav.addObject("infoSignaturaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, infoSignatura, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, infoSignatura);

    return infoSignatura;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(InfoSignaturaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<InfoSignatura> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, TIMESTAMPINCLUDED);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, POLICYINCLUDED);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CHECKADMINISTRATIONIDOFSIGNER);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CHECKDOCUMENTMODIFICATIONS);


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, CHECKVALIDATIONSIGNATURE);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    InfoSignaturaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<InfoSignatura> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_INFOSIGNATURA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou InfoSignatura
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearInfoSignaturaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    InfoSignaturaForm infoSignaturaForm = getInfoSignaturaForm(null, false, request, mav);
    mav.addObject("infoSignaturaForm" ,infoSignaturaForm);
    fillReferencesForForm(infoSignaturaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public InfoSignaturaForm getInfoSignaturaForm(InfoSignaturaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    InfoSignaturaForm infoSignaturaForm;
    if(_jpa == null) {
      infoSignaturaForm = new InfoSignaturaForm(new InfoSignaturaJPA(), true);
    } else {
      infoSignaturaForm = new InfoSignaturaForm(_jpa, false);
      infoSignaturaForm.setView(__isView);
    }
    infoSignaturaForm.setContexte(getContextWeb());
    infoSignaturaForm.setEntityNameCode(getEntityNameCode());
    infoSignaturaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return infoSignaturaForm;
  }

  public void fillReferencesForForm(InfoSignaturaForm infoSignaturaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou InfoSignatura
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearInfoSignaturaPost(@ModelAttribute InfoSignaturaForm infoSignaturaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    InfoSignaturaJPA infoSignatura = infoSignaturaForm.getInfoSignatura();

    try {
      preValidate(request, infoSignaturaForm, result);
      getWebValidator().validate(infoSignaturaForm, result);
      postValidate(request,infoSignaturaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoSignatura = create(request, infoSignatura);
        createMessageSuccess(request, "success.creation", infoSignatura.getInfosignaturaid());
        infoSignaturaForm.setInfoSignatura(infoSignatura);
        return getRedirectWhenCreated(request, infoSignaturaForm);
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

  @RequestMapping(value = "/view/{infosignaturaid}", method = RequestMethod.GET)
  public ModelAndView veureInfoSignaturaGet(@PathVariable("infosignaturaid") java.lang.Long infosignaturaid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoSignaturaGet(infosignaturaid,
        request, response, true);
  }


  protected ModelAndView editAndViewInfoSignaturaGet(@PathVariable("infosignaturaid") java.lang.Long infosignaturaid,
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
    InfoSignaturaJPA infoSignatura = findByPrimaryKey(request, infosignaturaid);

    if (infoSignatura == null) {
      createMessageWarning(request, "error.notfound", infosignaturaid);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, infosignaturaid), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      InfoSignaturaForm infoSignaturaForm = getInfoSignaturaForm(infoSignatura, __isView, request, mav);
      infoSignaturaForm.setView(__isView);
      if(__isView) {
        infoSignaturaForm.setAllFieldsReadOnly(ALL_INFOSIGNATURA_FIELDS);
        infoSignaturaForm.setSaveButtonVisible(false);
        infoSignaturaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(infoSignaturaForm, request, mav);
      mav.addObject("infoSignaturaForm", infoSignaturaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un InfoSignatura existent
   */
  @RequestMapping(value = "/{infosignaturaid}/edit", method = RequestMethod.GET)
  public ModelAndView editarInfoSignaturaGet(@PathVariable("infosignaturaid") java.lang.Long infosignaturaid,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewInfoSignaturaGet(infosignaturaid,
        request, response, false);
  }



  /**
   * Editar un InfoSignatura existent
   */
  @RequestMapping(value = "/{infosignaturaid}/edit", method = RequestMethod.POST)
  public String editarInfoSignaturaPost(@ModelAttribute InfoSignaturaForm infoSignaturaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    InfoSignaturaJPA infoSignatura = infoSignaturaForm.getInfoSignatura();

    try {
      preValidate(request, infoSignaturaForm, result);
      getWebValidator().validate(infoSignaturaForm, result);
      postValidate(request, infoSignaturaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        infoSignatura = update(request, infoSignatura);
        createMessageSuccess(request, "success.modification", infoSignatura.getInfosignaturaid());
        status.setComplete();
        return getRedirectWhenModified(request, infoSignaturaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          infoSignatura.getInfosignaturaid(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, infoSignaturaForm, __e);
    }

  }


  /**
   * Eliminar un InfoSignatura existent
   */
  @RequestMapping(value = "/{infosignaturaid}/delete")
  public String eliminarInfoSignatura(@PathVariable("infosignaturaid") java.lang.Long infosignaturaid,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      InfoSignatura infoSignatura = infoSignaturaEjb.findByPrimaryKey(infosignaturaid);
      if (infoSignatura == null) {
        String __msg =createMessageError(request, "error.notfound", infosignaturaid);
        return getRedirectWhenDelete(request, infosignaturaid, new Exception(__msg));
      } else {
        delete(request, infoSignatura);
        createMessageSuccess(request, "success.deleted", infosignaturaid);
        return getRedirectWhenDelete(request, infosignaturaid,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", infosignaturaid, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, infosignaturaid, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute InfoSignaturaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarInfoSignatura(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __infosignaturaid, Throwable e) {
    java.lang.Long infosignaturaid = (java.lang.Long)__infosignaturaid;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (infosignaturaid == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(infosignaturaid),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "infoSignatura.infoSignatura";
  }

  public String getEntityNameCodePlural() {
    return "infoSignatura.infoSignatura.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("infoSignatura.infosignaturaid");
  }

  @InitBinder("infoSignaturaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("infoSignaturaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "infoSignatura.infosignaturaid");
  }

  public InfoSignaturaWebValidator getWebValidator() {
    return infoSignaturaWebValidator;
  }


  public void setWebValidator(InfoSignaturaWebValidator __val) {
    if (__val != null) {
      this.infoSignaturaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de InfoSignatura
   */
  @RequestMapping(value = "/{infosignaturaid}/cancel")
  public String cancelInfoSignatura(@PathVariable("infosignaturaid") java.lang.Long infosignaturaid,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, infosignaturaid);
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

  public void preValidate(HttpServletRequest request,InfoSignaturaForm infoSignaturaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,InfoSignaturaForm infoSignaturaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, InfoSignaturaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, InfoSignaturaFilterForm filterForm,  List<InfoSignatura> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, InfoSignaturaForm infoSignaturaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, InfoSignaturaForm infoSignaturaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long infosignaturaid, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long infosignaturaid) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "infoSignaturaFormWebDB";
  }

  public String getTileList() {
    return "infoSignaturaListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "InfoSignaturaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public InfoSignaturaJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long infosignaturaid) throws I18NException {
    return (InfoSignaturaJPA) infoSignaturaEjb.findByPrimaryKey(infosignaturaid);
  }


  public InfoSignaturaJPA create(HttpServletRequest request, InfoSignaturaJPA infoSignatura)
    throws I18NException, I18NValidationException {
    return (InfoSignaturaJPA) infoSignaturaEjb.create(infoSignatura);
  }


  public InfoSignaturaJPA update(HttpServletRequest request, InfoSignaturaJPA infoSignatura)
    throws I18NException, I18NValidationException {
    return (InfoSignaturaJPA) infoSignaturaEjb.update(infoSignatura);
  }


  public void delete(HttpServletRequest request, InfoSignatura infoSignatura) throws I18NException {
    infoSignaturaEjb.delete(infoSignatura);
  }

} // Final de Classe

