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
import es.caib.enviafib.back.form.webdb.UsuariForm;

import es.caib.enviafib.back.validator.webdb.UsuariWebValidator;

import es.caib.enviafib.persistence.UsuariJPA;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.*;

/**
 * Controller per gestionar un Usuari
 *  ========= FITXER AUTOGENERAT - NO MODIFICAR !!!!! 
 * 
 * @author GenApp
 */
@Controller
@RequestMapping(value = "/webdb/usuari")
@SessionAttributes(types = { UsuariForm.class, UsuariFilterForm.class })
public class UsuariController
    extends es.caib.enviafib.back.controller.EnviaFIBBaseController<Usuari, java.lang.Long> implements UsuariFields {

  @EJB(mappedName = es.caib.enviafib.ejb.UsuariService.JNDI_NAME)
  protected es.caib.enviafib.ejb.UsuariService usuariEjb;

  @Autowired
  private UsuariWebValidator usuariWebValidator;

  @Autowired
  protected UsuariRefList usuariRefList;

  /**
   * Llistat de totes Usuari
   */
  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String llistat(HttpServletRequest request,
    HttpServletResponse response) throws I18NException {
    UsuariFilterForm ff;
    ff = (UsuariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    int pagina = (ff == null)? 1: ff.getPage();
    return "redirect:" + getContextWeb() + "/list/" + pagina;
  }

  /**
   * Primera peticio per llistar Usuari de forma paginada
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
    llistat(mav, request, getUsuariFilterForm(pagina, mav, request));
    return mav;
  }

  public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav,
    HttpServletRequest request) throws I18NException {
    UsuariFilterForm usuariFilterForm;
    usuariFilterForm = (UsuariFilterForm) request.getSession().getAttribute(getSessionAttributeFilterForm());
    if(usuariFilterForm == null) {
      usuariFilterForm = new UsuariFilterForm();
      usuariFilterForm.setContexte(getContextWeb());
      usuariFilterForm.setEntityNameCode(getEntityNameCode());
      usuariFilterForm.setEntityNameCodePlural(getEntityNameCodePlural());
      usuariFilterForm.setNou(true);
    } else {
      usuariFilterForm.setNou(false);
    }
    usuariFilterForm.setPage(pagina == null ? 1 : pagina);
    return usuariFilterForm;
  }

  /**
   * Segona i següent peticions per llistar Usuari de forma paginada
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
      @ModelAttribute UsuariFilterForm filterForm) throws I18NException {
    if(!isActiveList()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    ModelAndView mav = new ModelAndView(getTileList());

    filterForm.setPage(pagina == null ? 1 : pagina);
    // Actualitza el filter form

    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);
    filterForm = getUsuariFilterForm(pagina, mav, request);

    llistat(mav, request, filterForm);
    return mav;
  }

  /**
   * Codi centralitzat de llistat de Usuari de forma paginada.
   * 
   * @param request
   * @param filterForm
   * @param pagina
   * @return
   * @throws I18NException
   */
  protected List<Usuari> llistat(ModelAndView mav, HttpServletRequest request,
     UsuariFilterForm filterForm) throws I18NException {

    int pagina = filterForm.getPage();
    request.getSession().setAttribute(getSessionAttributeFilterForm(), filterForm);

    captureSearchByValueOfAdditionalFields(request, filterForm);

    preList(request, mav, filterForm);

    List<Usuari> usuari = processarLlistat(usuariEjb,
        filterForm, pagina, getAdditionalCondition(request), mav);

    mav.addObject("usuariItems", usuari);

    mav.addObject("usuariFilterForm", filterForm);

    fillReferencesForList(filterForm,request, mav, usuari, (List<GroupByItem>)mav.getModel().get("groupby_items"));

    postList(request, mav, filterForm, usuari);

    return usuari;
  }


  public Map<Field<?>, GroupByItem> fillReferencesForList(UsuariFilterForm filterForm,
    HttpServletRequest request, ModelAndView mav,
      List<Usuari> list, List<GroupByItem> groupItems) throws I18NException {
    Map<Field<?>, GroupByItem> groupByItemsMap = new HashMap<Field<?>, GroupByItem>();
    for (GroupByItem groupByItem : groupItems) {
      groupByItemsMap.put(groupByItem.getField(),groupByItem);
    }


    return groupByItemsMap;
  }

  @RequestMapping(value = "/export/{dataExporterID}", method = RequestMethod.POST)
  public void exportList(@PathVariable("dataExporterID") String dataExporterID,
    HttpServletRequest request, HttpServletResponse response,
    UsuariFilterForm filterForm) throws Exception, I18NException {

    ModelAndView mav = new ModelAndView(getTileList());
    List<Usuari> list = llistat(mav, request, filterForm);
    Field<?>[] allFields = ALL_USUARI_FIELDS;

    java.util.Map<Field<?>, java.util.Map<String, String>> __mapping;
    __mapping = new java.util.HashMap<Field<?>, java.util.Map<String, String>>();
    exportData(request, response, dataExporterID, filterForm,
          list, allFields, __mapping, PRIMARYKEY_FIELDS);
  }



  /**
   * Carregar el formulari per un nou Usuari
   */
  @RequestMapping(value = "/new", method = RequestMethod.GET)
  public ModelAndView crearUsuariGet(HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    ModelAndView mav = new ModelAndView(getTileForm());
    UsuariForm usuariForm = getUsuariForm(null, false, request, mav);
    mav.addObject("usuariForm" ,usuariForm);
    fillReferencesForForm(usuariForm, request, mav);
  
    return mav;
  }
  
  /**
   * 
   * @return
   * @throws Exception
   */
  public UsuariForm getUsuariForm(UsuariJPA _jpa,
       boolean __isView, HttpServletRequest request, ModelAndView mav) throws I18NException {
    UsuariForm usuariForm;
    if(_jpa == null) {
      usuariForm = new UsuariForm(new UsuariJPA(), true);
    } else {
      usuariForm = new UsuariForm(_jpa, false);
      usuariForm.setView(__isView);
    }
    usuariForm.setContexte(getContextWeb());
    usuariForm.setEntityNameCode(getEntityNameCode());
    usuariForm.setEntityNameCodePlural(getEntityNameCodePlural());
    return usuariForm;
  }

  public void fillReferencesForForm(UsuariForm usuariForm,
    HttpServletRequest request, ModelAndView mav) throws I18NException {
    
  }

  /**
   * Guardar un nou Usuari
   */
  @RequestMapping(value = "/new", method = RequestMethod.POST)
  public String crearUsuariPost(@ModelAttribute UsuariForm usuariForm,
      BindingResult result, HttpServletRequest request,
      HttpServletResponse response) throws Exception {
    if(!isActiveFormNew()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }

    UsuariJPA usuari = usuariForm.getUsuari();

    try {
      preValidate(request, usuariForm, result);
      getWebValidator().validate(usuariForm, result);
      postValidate(request,usuariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuari = create(request, usuari);
        createMessageSuccess(request, "success.creation", usuari.getUsuariID());
        usuariForm.setUsuari(usuari);
        return getRedirectWhenCreated(request, usuariForm);
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

  @RequestMapping(value = "/view/{usuariID}", method = RequestMethod.GET)
  public ModelAndView veureUsuariGet(@PathVariable("usuariID") java.lang.Long usuariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariGet(usuariID,
        request, response, true);
  }


  protected ModelAndView editAndViewUsuariGet(@PathVariable("usuariID") java.lang.Long usuariID,
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
    UsuariJPA usuari = findByPrimaryKey(request, usuariID);

    if (usuari == null) {
      createMessageWarning(request, "error.notfound", usuariID);
      new ModelAndView(new RedirectView(getRedirectWhenCancel(request, usuariID), true));
      return llistatPaginat(request, response, 1);
    } else {
      ModelAndView mav = new ModelAndView(getTileForm());
      UsuariForm usuariForm = getUsuariForm(usuari, __isView, request, mav);
      usuariForm.setView(__isView);
      if(__isView) {
        usuariForm.setAllFieldsReadOnly(ALL_USUARI_FIELDS);
        usuariForm.setSaveButtonVisible(false);
        usuariForm.setDeleteButtonVisible(false);
      }
      fillReferencesForForm(usuariForm, request, mav);
      mav.addObject("usuariForm", usuariForm);
      return mav;
    }
  }


  /**
   * Carregar el formulari per modificar un Usuari existent
   */
  @RequestMapping(value = "/{usuariID}/edit", method = RequestMethod.GET)
  public ModelAndView editarUsuariGet(@PathVariable("usuariID") java.lang.Long usuariID,
      HttpServletRequest request,
      HttpServletResponse response) throws I18NException {
      return editAndViewUsuariGet(usuariID,
        request, response, false);
  }



  /**
   * Editar un Usuari existent
   */
  @RequestMapping(value = "/{usuariID}/edit", method = RequestMethod.POST)
  public String editarUsuariPost(@ModelAttribute UsuariForm usuariForm,
      BindingResult result, SessionStatus status, HttpServletRequest request,
      HttpServletResponse response) throws I18NException {

    if(!isActiveFormEdit()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    UsuariJPA usuari = usuariForm.getUsuari();

    try {
      preValidate(request, usuariForm, result);
      getWebValidator().validate(usuariForm, result);
      postValidate(request, usuariForm, result);

      if (result.hasErrors()) {
        result.reject("error.form");
        return getTileForm();
      } else {
        usuari = update(request, usuari);
        createMessageSuccess(request, "success.modification", usuari.getUsuariID());
        status.setComplete();
        return getRedirectWhenModified(request, usuariForm, null);
      }
    } catch (Throwable __e) {
      if (__e instanceof I18NValidationException) {
        ValidationWebUtils.addFieldErrorsToBindingResult(result, (I18NValidationException)__e);
        return getTileForm();
      }
      String msg = createMessageError(request, "error.modification",
          usuari.getUsuariID(), __e);
      log.error(msg, __e);
      return getRedirectWhenModified(request, usuariForm, __e);
    }

  }


  /**
   * Eliminar un Usuari existent
   */
  @RequestMapping(value = "/{usuariID}/delete")
  public String eliminarUsuari(@PathVariable("usuariID") java.lang.Long usuariID,
      HttpServletRequest request,HttpServletResponse response) {

    if(!isActiveDelete()) {
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      return null;
    }
    try {
      Usuari usuari = usuariEjb.findByPrimaryKey(usuariID);
      if (usuari == null) {
        String __msg =createMessageError(request, "error.notfound", usuariID);
        return getRedirectWhenDelete(request, usuariID, new Exception(__msg));
      } else {
        delete(request, usuari);
        createMessageSuccess(request, "success.deleted", usuariID);
        return getRedirectWhenDelete(request, usuariID,null);
      }

    } catch (Throwable e) {
      String msg = createMessageError(request, "error.deleting", usuariID, e);
      log.error(msg, e);
      return getRedirectWhenDelete(request, usuariID, e);
    }
  }


@RequestMapping(value = "/deleteSelected", method = RequestMethod.POST)
public String deleteSelected(HttpServletRequest request,
    HttpServletResponse response,
    @ModelAttribute UsuariFilterForm filterForm) throws Exception {

  if(!isActiveDelete()) {
    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
    return null;
  }
  
  String[] seleccionats = filterForm.getSelectedItems();
  String redirect = null;
  if (seleccionats != null && seleccionats.length != 0) {
    for (int i = 0; i < seleccionats.length; i++) {
      redirect = eliminarUsuari(stringToPK(seleccionats[i]), request, response);
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
  public String[] getArgumentsMissatge(Object __usuariID, Throwable e) {
    java.lang.Long usuariID = (java.lang.Long)__usuariID;
    String exceptionMsg = "";
    if (e != null) {
      if (e instanceof I18NException) {
        exceptionMsg = I18NUtils.getMessage((I18NException)e);
      } else if (e instanceof I18NValidationException) {
      } else {
        exceptionMsg = e.getMessage();
      };
    };
    if (usuariID == null) {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
         getPrimaryKeyColumnsTranslated(), null, exceptionMsg };
    } else {
      return new String[] { I18NUtils.tradueix(getEntityNameCode()),
        getPrimaryKeyColumnsTranslated(),
         String.valueOf(usuariID),
 exceptionMsg };
    }
  }

  public String getEntityNameCode() {
    return "usuari.usuari";
  }

  public String getEntityNameCodePlural() {
    return "usuari.usuari.plural";
  }

  public String getPrimaryKeyColumnsTranslated() {
    return  I18NUtils.tradueix("usuari.usuariID");
  }

  @InitBinder("usuariFilterForm")
  public void initBinderFilterForm(WebDataBinder binder) {
    super.initBinder(binder);
  }

  @InitBinder("usuariForm")
  public void initBinderForm(WebDataBinder binder) {
    super.initBinder(binder);

    binder.setValidator(getWebValidator());


    initDisallowedFields(binder, "usuari.usuariID");
  }

  public UsuariWebValidator getWebValidator() {
    return usuariWebValidator;
  }


  public void setWebValidator(UsuariWebValidator __val) {
    if (__val != null) {
      this.usuariWebValidator= __val;
    }
  }


  /**
   * Entra aqui al pitjar el boto cancel en el llistat de Usuari
   */
  @RequestMapping(value = "/{usuariID}/cancel")
  public String cancelUsuari(@PathVariable("usuariID") java.lang.Long usuariID,
      HttpServletRequest request,HttpServletResponse response) {
     return getRedirectWhenCancel(request, usuariID);
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


  public void preValidate(HttpServletRequest request,UsuariForm usuariForm , BindingResult result)  throws I18NException {
  }

  public void postValidate(HttpServletRequest request,UsuariForm usuariForm, BindingResult result)  throws I18NException {
  }

  public void preList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm)  throws I18NException {
  }

  public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm,  List<Usuari> list) throws I18NException {
  }

  public String getRedirectWhenCreated(HttpServletRequest request, UsuariForm usuariForm) {
    return "redirect:" + getContextWeb() + "/list/1";
  }

  public String getRedirectWhenModified(HttpServletRequest request, UsuariForm usuariForm, Throwable __e) {
    if (__e == null) {
      return "redirect:" + getContextWeb() + "/list";
    } else {
      return  getTileForm();
    }
  }

  public String getRedirectWhenDelete(HttpServletRequest request, java.lang.Long usuariID, Throwable __e) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getRedirectWhenCancel(HttpServletRequest request, java.lang.Long usuariID) {
    return "redirect:" + getContextWeb() + "/list";
  }

  public String getTileForm() {
    return "usuariFormWebDB";
  }

  public String getTileList() {
    return "usuariListWebDB";
  }

  @Override
  /** Ha de ser igual que el RequestMapping de la Classe */
  public String getContextWeb() {
    RequestMapping rm = AnnotationUtils.findAnnotation(this.getClass(), RequestMapping.class);
    return rm.value()[0];
  }

  public String getSessionAttributeFilterForm() {
    return "UsuariWebDB_FilterForm";
  }



  public Where getAdditionalCondition(HttpServletRequest request) throws I18NException {
    return null;
  }


  public UsuariJPA findByPrimaryKey(HttpServletRequest request, java.lang.Long usuariID) throws I18NException {
    return (UsuariJPA) usuariEjb.findByPrimaryKey(usuariID);
  }


  public UsuariJPA create(HttpServletRequest request, UsuariJPA usuari)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariJPA) usuariEjb.create(usuari);
  }


  public UsuariJPA update(HttpServletRequest request, UsuariJPA usuari)
    throws Exception,I18NException, I18NValidationException {
    return (UsuariJPA) usuariEjb.update(usuari);
  }


  public void delete(HttpServletRequest request, Usuari usuari) throws Exception,I18NException {
    usuariEjb.delete(usuari);
  }

} // Final de Classe

