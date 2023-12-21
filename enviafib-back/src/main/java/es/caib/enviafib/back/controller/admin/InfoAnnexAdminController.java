package es.caib.enviafib.back.controller.admin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.ITableManager;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.BaseFilterForm;
import org.fundaciobit.genapp.common.web.form.FilterFormData;
import org.fundaciobit.genapp.common.web.form.LogicForBaseFilterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.FileDownloadController;
import es.caib.enviafib.back.controller.user.InfoAnnexUserController;
import es.caib.enviafib.back.controller.webdb.InfoAnexController;
import es.caib.enviafib.back.form.webdb.InfoAnexFilterForm;
import es.caib.enviafib.back.form.webdb.InfoAnexForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoAnex;
import es.caib.enviafib.persistence.InfoAnexJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author ptrias
 *
 */
@Controller
@RequestMapping(value = InfoAnnexAdminController.CONTEXT_WEB)
@SessionAttributes(types = { InfoAnexForm.class, InfoAnexFilterForm.class })
public class InfoAnnexAdminController extends InfoAnnexUserController {

    public static final String CONTEXT_WEB = "/admin/infoAnnex";

    @Override
    public String getTileForm() {
        return "infoAnexFormAdmin";
    }

    @Override
    public String getTileList() {
        return "infoAnexListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "infoAnexAdmin_FilterForm";
    }

    @Override
    public boolean isAdmin() {
        return true;
    }
}
