package es.caib.enviafib.back.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.user.FirmaFluxUserController;
import es.caib.enviafib.back.controller.webdb.UsuariController;
import es.caib.enviafib.back.form.webdb.UsuariFilterForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.FitxerFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.UsuariFields;
import es.caib.enviafib.persistence.UsuariJPA;

/**
 * 
 * @author ptrias
 *
 */

public abstract class AbstractPlantillaDeFluxDeFirmesController extends UsuariController {

    protected final static Pattern PATTERN = Pattern.compile("\\{creation=(\\d+)\\}");

    protected final static SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy HH:mm");

    public abstract FlowTemplateSimpleFilterGetAllByFilter getFilterPlantillaFluxFirma(final String languageUI);
    
    /**
     * 
     * @return true Nomes Palntilles
     *         false Només Temporal
     *         null  Plantilles i temporal
     */
    public abstract Boolean onlyAcceptTemplates();

    @Override
    public String getSessionAttributeFilterForm() {
        return "plantillesfluxfirmes_FilterForm_" + this.getClass().getSimpleName();
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveDelete() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public UsuariFilterForm getUsuariFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        UsuariFilterForm usuariFilterForm = super.getUsuariFilterForm(pagina, mav, request);

        if (usuariFilterForm.isNou()) {

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(UsuariFields.ALL_USUARI_FIELDS));

            hiddens.remove(UsuariFields.NOM);

            usuariFilterForm.addLabel(UsuariFields.LLINATGE1, FitxerFields.DESCRIPCIO.fullName);
            hiddens.remove(UsuariFields.LLINATGE1);

            usuariFilterForm.addLabel(UsuariFields.EMAIL, PeticioFields.DATACREACIO.fullName);
            hiddens.remove(UsuariFields.EMAIL);

            if (Configuracio.isDesenvolupament()) {
                usuariFilterForm.addLabel(UsuariFields.NIF, UsuariFields.USUARIID.fullName);
                hiddens.remove(UsuariFields.NIF);
            }

            usuariFilterForm.setHiddenFields(hiddens);

            usuariFilterForm.setItemsPerPage(-1);
            usuariFilterForm.setAllItemsPerPage(new int[] { -1 });
            usuariFilterForm.setAddButtonVisible(false);
            usuariFilterForm.setEditButtonVisible(false);
            usuariFilterForm.setDeleteButtonVisible(false);
            usuariFilterForm.setVisibleMultipleSelection(false);
            usuariFilterForm.setDeleteSelectedButtonVisible(false);

            usuariFilterForm.setVisibleExportList(false);
        }

        return usuariFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, UsuariFilterForm filterForm, List<Usuari> list)
            throws I18NException {

        super.postList(request, mav, filterForm, list);

        filterForm.getAdditionalButtonsByPK().clear();

        for (Usuari usuari : list) {
            // BOTO PER ESBORRAR
            filterForm.addAdditionalButtonByPK((long) usuari.getNif().hashCode(),
                    new AdditionalButton("fas fa-trash icon-white", "genapp.delete", "javascript: openModal('"
                            + request.getContextPath() + getContextWeb() + "/" + usuari.getNif() + "/esborrar','show')",
                            "btn-danger"));

        }
    }

    @RequestMapping(value = "/{fluxID}/esborrar")
    public String esborrarFlux(@PathVariable("fluxID")
    java.lang.String fluxID, HttpServletRequest request, HttpServletResponse response) {

        final String languageUI = "ca";

        // Comprovam que és de la nostra propietat
        try {

            ApiFlowTemplateSimple api = FirmaFluxUserController.getApiFlowTemplateSimple();

            FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
            flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, fluxID);

            FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

            String description = flux.getDescription();

            String owner = LoginInfo.getInstance().getUsername();
            if (description.indexOf("{owner=" + owner + "}") != -1) {
                FlowTemplateSimpleFlowTemplateRequest r = new FlowTemplateSimpleFlowTemplateRequest(languageUI, fluxID);
                if (api.deleteFlowTemplate(r)) {
                    String msg = I18NUtils.tradueix("plantillaflux.esborrar.ok", fluxID);
                    HtmlUtils.saveMessageSuccess(request, msg);
                } else {
                    String msg = I18NUtils.tradueix("plantillaflux.esborrar.error", fluxID, null);
                    HtmlUtils.saveMessageError(request, msg);
                }
                log.info("El flux es de la nostra propietat");
            } else {
                String msg = I18NUtils.tradueix("plantillaflux.esborrar.nopropietari", fluxID);
                HtmlUtils.saveMessageError(request, msg);
            }
        } catch (AbstractApisIBException e) {
            String msg = I18NUtils.tradueix("plantillaflux.esborrar.error", fluxID, e.getMessage());
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return getRedirectWhenCancel(request, 0L);
    }

    @Override
    public List<Usuari> executeSelect(ITableManager<Usuari, Long> ejb, Where where, final OrderBy[] orderBy,
            final Integer itemsPerPage, final int inici) throws I18NException {

        ApiFlowTemplateSimple api = FirmaFluxUserController.getApiFlowTemplateSimple();

        final String languageUI = "ca";

        FlowTemplateSimpleFilterGetAllByFilter filter = getFilterPlantillaFluxFirma(languageUI);

        try {
            FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplatesByFilter(filter);

            List<FlowTemplateSimpleKeyValue> plantilles = list.getList();

            log.info(" PLANTILLES OBTINGUDES: " + plantilles.size());

            List<Usuari> usuaris = new ArrayList<Usuari>();

            for (FlowTemplateSimpleKeyValue flowKeyValue : plantilles) {

                String flowTemplateId = flowKeyValue.getKey();

                FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
                flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

                FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);
                
                
                String descrOriginal = flux.getDescription();
                        
                if (onlyAcceptTemplates() == null) {
                    // Accept All
                } else if (onlyAcceptTemplates() ==  true) {
                    // Templates
                    if (descrOriginal.indexOf("{template=true}") == -1) {
                        continue;
                    }
                } else {
                    // Temporal
                    if (descrOriginal.indexOf("{temporal=true}") == -1) {
                        continue;
                    }
                }
                

                String description = descrOriginal.replace("}\n{", "}<br/>{").replace("}\r\n{", "}<br/>{")
                        .replace("}{", "}<br/>{");

                Usuari usuari = new UsuariJPA();
                usuari.setUsuariID((long) flowTemplateId.hashCode());
                usuari.setNif(flowTemplateId);
                usuari.setNom(flowKeyValue.getValue());
                usuari.setLlinatge1(description);
                usuari.setEmail(getCreationDate(description));
                usuaris.add(usuari);
            }

            log.info("OrderBy " + orderBy);
            if (orderBy != null) {
                for (OrderBy o : orderBy) {
                    log.info("OrderBy[" + o.javaName + " => " + o.orderType);

                    // Data Creació
                    if (o.javaName.equals(EMAIL.javaName)) {
                        Collections.sort(usuaris, new ComparatorByCreationDate(o.orderType));
                        break;
                    }

                    // Nom
                    if (o.javaName.equals(NOM.javaName)) {
                        Collections.sort(usuaris, new ComparatorByName(o.orderType));
                        break;
                    }
                }
            }

            return usuaris;

        } catch (AbstractApisIBException e) {
            String msg = "Error consultant API de Plantilles de Flux per username: " + e.getMessage();
            log.error(msg, e);
            throw new I18NException("genapp.comodi", msg);
        }

    }

    /**
     * 
     * @author anadal
     *
     */
    public class ComparatorByCreationDate implements Comparator<Usuari> {

        protected final int sort;

        public ComparatorByCreationDate(OrderType order) {
            sort = order.equals(OrderType.ASC) ? +1 : -1;
        }

        @Override
        public int compare(Usuari o1, Usuari o2) {
            Long d1 = getCreationDateLong(o1.getLlinatge1());
            Long d2 = getCreationDateLong(o2.getLlinatge1());
            return sort * Long.compare(d1, d2);
        }

    }

    public class ComparatorByName implements Comparator<Usuari> {

        protected final int sort;

        public ComparatorByName(OrderType order) {
            sort = order.equals(OrderType.ASC) ? +1 : -1;
        }

        @Override
        public int compare(Usuari o1, Usuari o2) {
            return sort * o1.getNom().compareToIgnoreCase(o2.getNom());
        }

    }

    protected Long getCreationDateLong(String description) {

        final Matcher matcher = PATTERN.matcher(description);

        if (matcher.find()) {

            if (matcher.group(1) != null) {
                String datastr = matcher.group(1);
                return Long.valueOf(datastr);
            }
        }

        log.error("No s'ha pogut extreure atribut Creation de la descripcio");
        return null;

    }

    protected String getCreationDate(String description) {

        Long dataLong = getCreationDateLong(description);

        if (dataLong != null) {
            Date data = new Date(dataLong);
            return SDF.format(data);
        } else {
            return null;
        }

    }

}
