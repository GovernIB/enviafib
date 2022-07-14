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
import es.caib.enviafib.back.form.webdb.IdiomaForm;

import es.caib.enviafib.back.validator.webdb.IdiomaWebValidator;

import es.caib.enviafib.persistence.IdiomaJPA;
import es.caib.enviafib.model.entity.Idioma;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Idioma
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/idioma")
@SessionAttributes(types = { IdiomaForm.class, IdiomaFilterForm.class })
public class IdiomaController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Idioma, java.lang.String> implements IdiomaFields {

  @EJB(mappedName = es.caib.enviafib.ejb.IdiomaService.JNDI_NAME)
  protected es.caib.enviafib.ejb.IdiomaService idiomaEjb;

  @Autowired
  private IdiomaWebValidator idiomaWebValidator;

  @Autowired
  protected IdiomaRefList idiomaRefList;

  /**
   * Llistat de totes Idioma
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    IdiomaFilterForm ff;
    ff = (IdiomaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Idioma de forma paginada
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
    llistat(mav, request, getIdiomaFilterForm(pagina, mav, request));
    return mav;
  }

  public IdiomaFilterForm getIdiomaFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    IdiomaFilterForm idiomaFilterForm;
    idiomaFilterForm = (IdiomaFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(idiomaFilterForm == null) {
      idiomaFilterForm = new IdiomaFilterForm();
      idiomaFilterForm.setContexte(getContextWeb());
      idiomaFilterForm.setEntityNameCode(getEntityNameCode());
      idiomaFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      idiomaFilterForm.setNou(true);
    } else {
      idiomaFilterForm.setNou(false);
    }
    idiomaFilterForm.setPage(pagina == null ? 1 : pagina);
    return idiomaFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Idioma de forma paginada
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
      @ModelAttribute IdiomaFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getIdiomaFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Idioma de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Idioma> llistat(ModelAndView mav, HttpServletRequest request,
     IdiomaFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Idioma> idioma = processarLlistat(idiomaEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("idiomaItems", idioma);

    mav.addObject("idiomaFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, idioma, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, idioma);

    return idioma;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(IdiomaFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Idioma> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


      fillValuesToGroupByItemsBoolean("genapp.checkbox", groupByItemsMap, SUPORTAT);


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    IdiomaFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Idioma> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_IDIOMA_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Idioma
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearIdiomaGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    IdiomaForm idiomaForm = getIdiomaForm(null, false, request, mav);
    mav.addObject("idiomaForm" ,idiomaForm);
    fillReferencesForForm(idiomaForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public IdiomaForm getIdiomaForm(IdiomaJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    IdiomaForm idiomaForm;
    if(_jpa == null) {
      idiomaForm = new IdiomaForm(new IdiomaJPA(), true);
    } else {
      idiomaForm = new IdiomaForm(_jpa, false);
      idiomaForm.setView(__isView);
    }
    idiomaForm.setContexte(getContextWeb());
    idiomaForm.setEntityNameCode(getEntityNameCode());
    idiomaForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return idiomaForm;
  }

  public void fillReferencesForForm(IdiomaForm idiomaForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Idioma
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearIdiomaPost(@ModelAttribute IdiomaForm idiomaForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    IdiomaJPA idioma = idiomaForm.getIdioma();

    try {
      preValidate(request, idiomaForm, result);
      getWebValidator().validate(idiomaForm, result);
      postValidate(request,idiomaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        idioma = create(request, idioma);
        createMessageSuccess(request, "success.creation", idioma.getIdiomaID());
        idiomaForm.setIdioma(idioma);
        return getRedirectWhenCreated(request, idiomaForm);
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

  @RequestMapping(value = "/view/{idiomaID}", method = RequestMethod.GET)
  public ModelAndView veureIdiomaGet(@PathVariable("idiomaID") java.lang.String idiomaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewIdiomaGet(idiomaID,
        request, response, true);
  }


  protected ModelAndView editAndViewIdiomaGet(@PathVariable("idiomaID") java.lang.String idiomaID,
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
    IdiomaJPA idioma = findByPrimaryKey(request, idiomaID);

    if (idioma == null) {
      createMessageWarning(request, "error.notfound", idiomaID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, idiomaID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      IdiomaForm idiomaForm = getIdiomaForm(idioma, __isView, request, mav);
      idiomaForm.setView(__isView);
      if(__isView) {
        idiomaForm.setAllFieldsReadOnly(ALL_IDIOMA_FIELDS);
        idiomaForm.setSaveButtonVisible(false);
        idiomaForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(idiomaForm, request, mav);
      mav.addObject("idiomaForm", idiomaForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Idioma existent
   */
  @RequestMapping(value = "/{idiomaID}/edit", method = RequestMethod.GET)
  public ModelAndView editarIdiomaGet(@PathVariable("idiomaID") java.lang.String idiomaID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewIdiomaGet(idiomaID,
        request, response, false);
  }



  /**
   * Editar un Idioma existent
   */
  @RequestMapping(value = "/{idiomaID}/edit", method = RequestMethod.POST)
  public String editarIdiomaPost(@ModelAttribute IdiomaForm idiomaForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    IdiomaJPA idioma = idiomaForm.getIdioma();

    try {
      preValidate(request, idiomaForm, result);
      getWebValidator().validate(idiomaForm, result);
      postValidate(request, idiomaForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        idioma = update(request, idioma);
        createMessageSuccess(request, "success.modification", idioma.getIdiomaID());
        status.setComplete();
        return getRedirectWhenModified(request, idiomaForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          idioma.getIdiomaID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, idiomaForm, __e);
    }

  }


  /**
   * Eliminar un Idioma existent
   */
  @RequestMapping(value = "/{idiomaID}/delete")
  public String eliminarIdioma(@PathVariable("idiomaID") java.lang.String idiomaID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Idioma idioma = idiomaEjb.findByPrimaryKey(idiomaID);
      if (idioma == null) {
        String __msg =createMessageError(request, "error.notfound", idiomaID);
        return getRedirectWhenDelete(request, idiomaID, new Exception(__msg));
      } else {
        delete(request, idioma);
        createMessageSuccess(request, "success.deleted", idiomaID);
        return getRedirectWhenDelete(request, idiomaID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", idiomaID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, idiomaID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute IdiomaFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarIdioma(stringToPK(seleccionats[i]), request, response);
    }
  }
  if (redirect == null) {
    redirect = getRedirectWhenDelete(request, null,null);
  }

  return redirect;
}



public java.lang.String stringToPK(String value) {
  return value;
}

  @Override
  public String[] getArgumentsMissatge(Object __idiomaID, Throwable e) {
    java.lang.String idiomaID = (java.lang.String)__idiomaID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (idiomaID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(idiomaID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "idioma.idioma";
  }

  public String getEntityNameCodePlural() {
    return "idioma.idioma.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("idioma.idiomaID");
  }

  @InitBinder("idiomaFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("idiomaForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder);
  }

  public IdiomaWebValidator getWebValidator() {
    return idiomaWebValidator;
  }


  public void setWebValidator(IdiomaWebValidator __val) {
    if (__val != null) {
      this.idiomaWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Idioma
   */
  @RequestMapping(value = "/{idiomaID}/cancel")
  public String cancelIdioma(@PathVariable("idiomaID") java.lang.String idiomaID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, idiomaID);
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


  public void preValidate(HttpServletRequest request,IdiomaForm idiomaForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,IdiomaForm idiomaForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, IdiomaFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, IdiomaFilterForm filterForm,  List<Idioma> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, IdiomaForm idiomaForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, IdiomaForm idiomaForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.String idiomaID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.String idiomaID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "idiomaFormWebDB";
  }

  public String getTileList() {
    return "idiomaListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "IdiomaWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public IdiomaJPA findByPrimaryKey(HttpServletRequest request, java.lang.String idiomaID) throws I18NException {
    return (IdiomaJPA) idiomaEjb.findByPrimaryKey(idiomaID);
  }


  public IdiomaJPA create(HttpServletRequest request, IdiomaJPA idioma)
    throws Exception,I18NException, I18NValidationException {
    return (IdiomaJPA) idiomaEjb.create(idioma);
  }


  public IdiomaJPA update(HttpServletRequest request, IdiomaJPA idioma)
    throws Exception,I18NException, I18NValidationException {
    return (IdiomaJPA) idiomaEjb.update(idioma);
  }


  public void delete(HttpServletRequest request, Idioma idioma) throws Exception,I18NException {
    idiomaEjb.delete(idioma);
  }

} // Final de Classe

