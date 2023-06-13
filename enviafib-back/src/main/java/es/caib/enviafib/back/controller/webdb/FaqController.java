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
import es.caib.enviafib.back.form.webdb.FaqForm;

import es.caib.enviafib.back.validator.webdb.FaqWebValidator;

import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.persistence.FitxerJPA;
import org.fundaciobit.genapp.common.web.controller.FilesFormManager;
import es.caib.enviafib.persistence.FaqJPA;
import es.caib.enviafib.model.entity.Faq;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Faq
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/faq")
@SessionAttributes(types = { FaqForm.class, FaqFilterForm.class })
public class FaqController
    extends es.caib.enviafib.back.controller.EnviaFIBFilesBaseController<Faq, java.lang.Long, FaqForm> implements FaqFields {

  @EJB(mappedName = es.caib.enviafib.ejb.FaqService.JNDI_NAME)
  protected es.caib.enviafib.ejb.FaqService faqEjb;

  @Autowired
  private FaqWebValidator faqWebValidator;

  @Autowired
  protected FaqRefList faqRefList;

  /**
   * Llistat de totes Faq
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    FaqFilterForm ff;
    ff = (FaqFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Faq de forma paginada
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
    llistat(mav, request, getFaqFilterForm(pagina, mav, request));
    return mav;
  }

  public FaqFilterForm getFaqFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    FaqFilterForm faqFilterForm;
    faqFilterForm = (FaqFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(faqFilterForm == null) {
      faqFilterForm = new FaqFilterForm();
      faqFilterForm.setContexte(getContextWeb());
      faqFilterForm.setEntityNameCode(getEntityNameCode());
      faqFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      faqFilterForm.setNou(true);
    } else {
      faqFilterForm.setNou(false);
    }
    faqFilterForm.setPage(pagina == null ? 1 : pagina);
    return faqFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Faq de forma paginada
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
      @ModelAttribute FaqFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getFaqFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Faq de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Faq> llistat(ModelAndView mav, HttpServletRequest request,
     FaqFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Faq> faq = processarLlistat(faqEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("faqItems", faq);

    mav.addObject("faqFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, faq, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, faq);

    return faq;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(FaqFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Faq> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    FaqFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Faq> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_FAQ_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Faq
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearFaqGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    FaqForm faqForm = getFaqForm(null, false, request, mav);
    mav.addObject("faqForm" ,faqForm);
    fillReferencesForForm(faqForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public FaqForm getFaqForm(FaqJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    FaqForm faqForm;
    if(_jpa == null) {
      faqForm = new FaqForm(new FaqJPA(), true);
    } else {
      faqForm = new FaqForm(_jpa, false);
      faqForm.setView(__isView);
    }
    faqForm.setContexte(getContextWeb());
    faqForm.setEntityNameCode(getEntityNameCode());
    faqForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return faqForm;
  }

  public void fillReferencesForForm(FaqForm faqForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Faq
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearFaqPost(@ModelAttribute FaqForm faqForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    FaqJPA faq = faqForm.getFaq();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE

    try {
      this.setFilesFormToEntity(afm, faq, faqForm); // FILE
      preValidate(request, faqForm, result);
      getWebValidator().validate(faqForm, result);
      postValidate(request,faqForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        faq = create(request, faq);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.creation", faq.getFaqID());
        faqForm.setFaq(faq);
        return getRedirectWhenCreated(request, faqForm);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.creation", null, __e);
      log.error(msg, __e);
      return getTileForm();
    }
  }

  @RequestMapping(value = "/view/{faqID}", method = RequestMethod.GET)
  public ModelAndView veureFaqGet(@PathVariable("faqID") java.lang.Long faqID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFaqGet(faqID,
        request, response, true);
  }


  protected ModelAndView editAndViewFaqGet(@PathVariable("faqID") java.lang.Long faqID,
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
    FaqJPA faq = findByPrimaryKey(request, faqID);

    if (faq == null) {
      createMessageWarning(request, "error.notfound", faqID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, faqID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      FaqForm faqForm = getFaqForm(faq, __isView, request, mav);
      faqForm.setView(__isView);
      if(__isView) {
        faqForm.setAllFieldsReadOnly(ALL_FAQ_FIELDS);
        faqForm.setSaveButtonVisible(false);
        faqForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(faqForm, request, mav);
      mav.addObject("faqForm", faqForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Faq existent
   */
  @RequestMapping(value = "/{faqID}/edit", method = RequestMethod.GET)
  public ModelAndView editarFaqGet(@PathVariable("faqID") java.lang.Long faqID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewFaqGet(faqID,
        request, response, false);
  }



  /**
   * Editar un Faq existent
   */
  @RequestMapping(value = "/{faqID}/edit", method = RequestMethod.POST)
  public String editarFaqPost(@ModelAttribute FaqForm faqForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    FaqJPA faq = faqForm.getFaq();

    FilesFormManager<Fitxer> afm = getFilesFormManager(); // FILE
    try {
      this.setFilesFormToEntity(afm, faq, faqForm); // FILE
      preValidate(request, faqForm, result);
      getWebValidator().validate(faqForm, result);
      postValidate(request, faqForm, result);

      if (result.hasErrors()) {
        afm.processErrorFilesWithoutThrowException(); // FILE
        result.reject("error.form");
        return getTileForm();
      } else {
        faq = update(request, faq);
        afm.postPersistFiles(); // FILE
        createMessageSuccess(request, "success.modification", faq.getFaqID());
        status.setComplete();
        return getRedirectWhenModified(request, faqForm, null);
      }
    } catch (Throwable __e) {
      afm.processErrorFilesWithoutThrowException(); // FILE
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          faq.getFaqID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, faqForm, __e);
    }

  }


  /**
   * Eliminar un Faq existent
   */
  @RequestMapping(value = "/{faqID}/delete")
  public String eliminarFaq(@PathVariable("faqID") java.lang.Long faqID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Faq faq = this.findByPrimaryKey(request, faqID);
      if (faq == null) {
        String __msg = createMessageError(request, "error.notfound", faqID);
        return getRedirectWhenDelete(request, faqID, new Exception(__msg));
      } else {
        delete(request, faq);
        createMessageSuccess(request, "success.deleted", faqID);
        return getRedirectWhenDelete(request, faqID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", faqID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, faqID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute FaqFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarFaq(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __faqID, Throwable e) {
    java.lang.Long faqID = (java.lang.Long)__faqID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (faqID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(faqID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "faq.faq";
  }

  public String getEntityNameCodePlural() {
    return "faq.faq.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("faq.faqID");
  }

  @InitBinder("faqFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("faqForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "faq.faqID");
  }

  public FaqWebValidator getWebValidator() {
    return faqWebValidator;
  }


  public void setWebValidator(FaqWebValidator __val) {
    if (__val != null) {
      this.faqWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Faq
   */
  @RequestMapping(value = "/{faqID}/cancel")
  public String cancelFaq(@PathVariable("faqID") java.lang.Long faqID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, faqID);
  }

  @Override
  public String getTableModelName() {
    return _TABLE_MODEL;
  }

  // FILE
  @Override
  public void setFilesFormToEntity(FilesFormManager<Fitxer> afm, Faq faq,
      FaqForm form) throws I18NException {

    FitxerJPA f;
    f = (FitxerJPA)afm.preProcessFile(form.getFitxer1ID(), form.isFitxer1IDDelete(),
        form.isNou()? null : faq.getFitxer1());
    ((FaqJPA)faq).setFitxer1(f);
    if (f != null) { 
      faq.setFitxer1ID(f.getFitxerID());
    } else {
      faq.setFitxer1ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxer2ID(), form.isFitxer2IDDelete(),
        form.isNou()? null : faq.getFitxer2());
    ((FaqJPA)faq).setFitxer2(f);
    if (f != null) { 
      faq.setFitxer2ID(f.getFitxerID());
    } else {
      faq.setFitxer2ID(null);
    }


    f = (FitxerJPA)afm.preProcessFile(form.getFitxer3ID(), form.isFitxer3IDDelete(),
        form.isNou()? null : faq.getFitxer3());
    ((FaqJPA)faq).setFitxer3(f);
    if (f != null) { 
      faq.setFitxer3ID(f.getFitxerID());
    } else {
      faq.setFitxer3ID(null);
    }


  }

  // FILE
  @Override
  public void deleteFiles(Faq faq) {
    deleteFile(faq.getFitxer1ID());
    deleteFile(faq.getFitxer2ID());
    deleteFile(faq.getFitxer3ID());
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

  public void preValidate(HttpServletRequest request,FaqForm faqForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,FaqForm faqForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, FaqFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, FaqFilterForm filterForm,  List<Faq> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, FaqForm faqForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, FaqForm faqForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long faqID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long faqID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "faqFormWebDB";
  }

  public String getTileList() {
    return "faqListWebDB";
  }

  public String getSessionAttributeFilterForm() {
    return "FaqWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public FaqJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long faqID) throws I18NException {
    return (FaqJPA) faqEjb.findByPrimaryKey(faqID);
  }


  public FaqJPA create(HttpServletRequest request, FaqJPA faq)
    throws I18NException, I18NValidationException {
    return (FaqJPA) faqEjb.create(faq);
  }


  public FaqJPA update(HttpServletRequest request, FaqJPA faq)
    throws I18NException, I18NValidationException {
    return (FaqJPA) faqEjb.update(faq);
  }


  public void delete(HttpServletRequest request, Faq faq) throws I18NException {
    faqEjb.delete(faq);
  }

} // Final de Classe

