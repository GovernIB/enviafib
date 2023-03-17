package es.caib.enviafib.back.controller.admin;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.core.utils.PluginsManager;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.webdb.OrganitzacioController;
import es.caib.enviafib.back.form.webdb.OrganitzacioFilterForm;
import es.caib.enviafib.back.form.webdb.OrganitzacioForm;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Plugin;
import es.caib.enviafib.persistence.OrganitzacioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = "/admin/organitzacio")
@SessionAttributes(types = { OrganitzacioForm.class, OrganitzacioFilterForm.class })
public class EstructuraOrganitzativaAdminController extends OrganitzacioController {

    public static final String TIPUS_CARREC1_NAME = "CARREC1_NAME";
    public static final String TIPUS_CARREC1_USERNAME = "CARREC1_USERNAME";
    public static final String TIPUS_CARREC1_POSITION = "CARREC1_POSITION";

    public static final String TIPUS_CARREC2_NAME = "CARREC2_NAME";
    public static final String TIPUS_CARREC2_USERNAME = "CARREC2_USERNAME";
    public static final String TIPUS_CARREC2_POSITION = "CARREC2_POSITION";

    public static final String TIPUS_RECURSOSHUMANS_NAME = "RRHH_NAME";
    public static final String TIPUS_RECURSOSHUMANS_USERNAME = "RRHH_USERNAME";

    public static final String TIPUS_COMPRES_NAME = "COMPRES_NAME";
    public static final String TIPUS_COMPRES_USERNAME = "COMPRES_USERNAME";

    public static final String TIPUS_SECRETARI_NAME = "SEC_NAME";
    public static final String TIPUS_SECRETARI_USERNAME = "SEC_USERNAME";
    public static final String TIPUS_SECRETARI_NIF = "SEC_NIF";

    public static final String TIPUS_CAP_NAME = "CAP_NAME";
    public static final String TIPUS_CAP_USERNAME = "CAP_USERNAME";
    public static final String TIPUS_CAP_NIF = "CAP_NIF";

    public static final String TIPUS_NAME = "NAME";
    public static final String TIPUS_DIR3 = "DIR3";
    public static final String TIPUS_NIF = "NIF";

    @EJB(mappedName = es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PluginEstructuraOrganitzativaLogicaService pluginEstructuraOrganitzativaEjb;

    @Override
    public String getTileForm() {
        return "organitzacioFormAdmin";
    }

    @Override
    public String getTileList() {
        return "organitzacioListAdmin";
    }

    @Override
    public String getSessionAttributeFilterForm() {
        return "OrganitzacioAdmin_FilterForm";
    }

    @Override
    public String getEntityNameCode() {
        return "info.organitzacio.item";
    }

    @Override
    public OrganitzacioFilterForm getOrganitzacioFilterForm(Integer pagina, ModelAndView mav,
            HttpServletRequest request) throws I18NException {
        OrganitzacioFilterForm organitzacioFilterForm = super.getOrganitzacioFilterForm(pagina, mav, request);
        if (organitzacioFilterForm.isNou()) {

            organitzacioFilterForm.addHiddenField(ORGANITZACIOID);

            organitzacioFilterForm.setItemsPerPage(-1);
            organitzacioFilterForm.setAllItemsPerPage(new int[] { -1 });

            organitzacioFilterForm.setTitleCode("info.organitzacio.titol");

            // REGENERAR
            organitzacioFilterForm.addAdditionalButton(new AdditionalButton("fas fa-cogs icon-white",
                    "info.organitzacio.regenerar", "javascript:regenerar()", "btn-warning"));

            // ESBORRAR TOT
            organitzacioFilterForm.addAdditionalButton(new AdditionalButton(
                    IconUtils.getWhite(IconUtils.ICON_TRASH), "info.organitzacio.esborrartot", "javascript:openModal('"
                            + request.getContextPath() + this.getContextWeb() + "/esborrartot','show');",
                    "btn-danger"));

            // AFEGIR
            organitzacioFilterForm
                    .addAdditionalButton(new AdditionalButton(IconUtils.getWhite(IconUtils.ICON_PLUS_SIGN),
                            "info.organitzacio.afegir", getContextWeb() + "/new", "btn-warning"));

            organitzacioFilterForm.setDeleteSelectedButtonVisible(false);
            organitzacioFilterForm.setVisibleMultipleSelection(false);
            organitzacioFilterForm.setAddButtonVisible(false);
            
            organitzacioFilterForm.setAttachedAdditionalJspCode(true);

        }
        return organitzacioFilterForm;
    }

    @RequestMapping(value = "/esborrartot", method = RequestMethod.GET)
    public String esborrartot(HttpServletRequest request, HttpServletResponse response) {

        try {
            // Esborram tot
            this.organitzacioEjb.delete((Where) null);
        } catch (Exception e) {
            // TODO
            // XYZ ZZZ Controlar I18N Exption
            String msg = "Error esborrant Estructura organitzativa: " + e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/regenerar/{dir3company}", method = RequestMethod.GET)
    public String regenerar(HttpServletRequest request, HttpServletResponse response,
            @PathVariable String dir3company) {

        try {

            // Esborram tot
            this.organitzacioEjb.delete((Where) null);

            // Afegim tots de NOU
            List<OrganitzacioJPA> list = internalGenerator(dir3company);

            for (OrganitzacioJPA organitzacioJPA : list) {
                this.organitzacioEjb.create(organitzacioJPA);
            }

            // TODO
            HtmlUtils.saveMessageSuccess(request,
                    "XYZ ZZZ Afegides " + list.size() + " entrades a l'Estructura Organitzativa.");

        } catch (Exception e) {

            // TODO
            // XYZ ZZZ Controlar I18N Exption
            String msg = "Error regenerant Estructura organitzativa: " + e.getMessage();
            log.error(msg, e);

            HtmlUtils.saveMessageError(request, msg);

        }

        return "redirect:" + getContextWeb() + "/list";

    }

    final String EOPB = Constants.ENVIAFIB_PROPERTY_BASE + "pluginsib.estructuraorganitzativa.database.";

    protected List<OrganitzacioJPA> internalGenerator(String dir3company) throws Exception {

        // Informació de generació:

        Properties propGenerals = new Properties();
        {
            String generadorProperties = "gerentpresident.name=Nom del President/Gerent\r\n"
                    + "gerentpresident.username=username_del_president_gerent\r\n" + "\r\n"
                    + "organitzacioempresa.name.ca=Govern de les Illes Balears\r\n"
                    + "organitzacioempresa.name.es=Gobierno de las Illes Balears\r\n" + "organitzacioempresa.dir3="
                    + dir3company + "\r\n" + "organitzacioempresa.nif=S0711001H\r\n";
            propGenerals.load(new ByteArrayInputStream(generadorProperties.getBytes()));
        }

        Long pluginDatabaseID = pluginEstructuraOrganitzativaEjb.getCurrentPluginID();

        Plugin pluginDatabase = pluginEstructuraOrganitzativaEjb.findByPrimaryKey(pluginDatabaseID);

        final Properties propDatabase = new Properties();
        propDatabase.load(new StringReader(pluginDatabase.getProperties()));

        // RolCapArea
        String ROL_CAP_AREA = propDatabase.getProperty(EOPB + "userinformation.rolcaparea");
        // RolCapDepartament
        String ROL_CAP_DEPARTAMENT = propDatabase.getProperty(EOPB + "userinformation.rolcapdepartament");
        // RolSecretari
        String ROL_SECRETARI = propDatabase.getProperty(EOPB + "userinformation.rolsecretari");

        GeneradorSql generador = new GeneradorSql();

        {
            generador.addComment(" ==========================================");
            generador.addComment("/** =  ENTITAT - ORGANITZACIÓ - EMPRESA = */");
            generador.addComment(" ==========================================\n");

            generador.addComment(
                    " getGerentPresidentName(), GetGerentPresidentUsername(), getNameOrganitzacioEmpresa(lang),");
            generador.addComment(" getDir3OrganitzacioEmpresa() i getNifOrganitzacioEmpresa()");

            String[][] valors = {
                    { "getGerentPresidentName()", TIPUS_CAP_NAME, propGenerals.getProperty("gerentpresident.name"),
                            null },
                    { "getGerentPresidentUsername()", TIPUS_CAP_USERNAME,
                            propGenerals.getProperty("gerentpresident.username"), null },
                    { "getNameOrganitzacioEmpresa(ca)", TIPUS_NAME,
                            propGenerals.getProperty("organitzacioempresa.name.ca"), "ca" },
                    { "getNameOrganitzacioEmpresa(es)", TIPUS_NAME,
                            propGenerals.getProperty("organitzacioempresa.name.es"), "es" },
                    { "getDir3OrganitzacioEmpresa()", TIPUS_DIR3, propGenerals.getProperty("organitzacioempresa.dir3"),
                            null },
                    { "getNifOrganitzacioEmpresa()", TIPUS_NIF, propGenerals.getProperty("organitzacioempresa.nif"),
                            null } };

            for (int i = 0; i < valors.length; i++) {
                String[] valor = valors[i];
                generador.addComment(valor[0]);
                generador.generaSql(valor[1], valor[2], valor[3]);
            }

        }

        IUserInformationPlugin pluginUserInformation = getPluginUserInformation(propDatabase);

        generateUsingAreaAndDepartment(pluginUserInformation, generador, ROL_CAP_AREA, ROL_CAP_DEPARTAMENT,
                ROL_SECRETARI);

        return generador.getInfoOrganitzacio();

    }


    /**
     * Retorna errors
     * @param pluginUserInformation
     * @param generador
     * @param ROL_CAP_AREA
     * @param ROL_CAP_DEPARTAMENT
     * @param ROL_SECRETARI
     * @return
     * @throws Exception
     */
    protected List<String> generateUsingAreaAndDepartment(IUserInformationPlugin pluginUserInformation,
            GeneradorSql generador, String ROL_CAP_AREA, String ROL_CAP_DEPARTAMENT, String ROL_SECRETARI)
            throws Exception {
        ArrayList<String> errors = new ArrayList<String>();
        {

            generador.addComment(" ==========================================");
            generador.addComment("        /** =  ÀREA - CONSELLERIA = */");
            generador.addComment(" ==========================================\n");

            // TODO XYZ ZZZ  Falta
            // getNameAreaConselleria(String username, String lang) =>
            // getDir3AreaConselleria(String username) =>
            // getCodeAreaConselleria(String username) =>

            generador.addComment(
                    "getCapAreaConsellerUsername(String username) i getCapAreaConsellerName(String username)");

            String[] consellers = pluginUserInformation.getUsernamesByRol(ROL_CAP_AREA);
            if (consellers == null || consellers.length == 0) {
                final String msg = " Error: No s'han trobat Caps d'Area/Consellers. ";
                generador.addComment(msg);
                errors.add(msg);
            } else {
                //System.out.println(" #Consellers = " + consellers.length);
                for (int i = 0; i < consellers.length; i++) {

                    UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(consellers[i]);

                    //System.out.println(" - " + consellers[i] +  " (" + userInfo.getCompanyArea() + " | " + userInfo.getCompanyDepartment() + ")");

                    final String area = userInfo.getCompanyArea();

                    generador.generaSql(area, null, TIPUS_CAP_USERNAME, userInfo.getUsername(), null);
                    generador.generaSql(area, null, TIPUS_CAP_NAME, userInfo.getFullName(), null);

                    if (userInfo.getDir3() != null) {
                        generador.generaSql(area, null, TIPUS_DIR3, userInfo.getDir3(), null);
                    }

                    if (userInfo.getAdministrationID() != null) {
                        generador.generaSql(area, null, TIPUS_CAP_NIF, userInfo.getAdministrationID(), null);
                    }
                }
            }
        }

        {
            generador.addComment(" ==========================================");
            generador.addComment(" /** =   DEPARTAMENT - DIRECCIÓ GENERAL = */");
            generador.addComment(" ==========================================\n");
            // TODO XYZ ZZZ  Falta
            // getNameDepartamentDireccioGeneral(username, lang)
            // getDir3DepartamentDireccioGeneral(String username) =>
            // getCodeDepartamentDireccioGeneral(String username)

            generador.addComment(
                    "getCapDepartamentDirectorGeneralUsername(username) i getCapDepartamentDirectorGeneralName(username)");

            // Carregar tots els Directors Generals ( Cap de Departaments)

            String[] directorsGenerals = pluginUserInformation.getUsernamesByRol(ROL_CAP_DEPARTAMENT);
            if (directorsGenerals == null || directorsGenerals.length == 0) {
                final String msg = " Error: No s'han trobat Caps de Departament/Directors Generals.";
                generador.addComment(msg);
                errors.add(msg);
            } else {
                //System.out.println(" #directorsGenerals = " + directorsGenerals.length);
                for (int i = 0; i < directorsGenerals.length; i++) {

                    UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(directorsGenerals[i]);

                    final String area = userInfo.getCompanyArea();
                    final String dep = userInfo.getCompanyDepartment();

                    generador.generaSql(area, dep, TIPUS_CAP_USERNAME, userInfo.getUsername(), null);
                    generador.generaSql(area, dep, TIPUS_CAP_NAME, userInfo.getFullName(), null);
                    String dir3 = userInfo.getDir3();
                    if (dir3 != null) {
                        generador.generaSql(area, dep, TIPUS_DIR3, dir3, null);
                    }
                    if (userInfo.getAdministrationID() != null) {
                        generador.generaSql(area, dep, TIPUS_CAP_NIF, userInfo.getAdministrationID(), null);
                    }
                }
            }
        }

        /** =  DADES GENERALS = */
        {
            generador.addComment(" ==========================================");
            generador.addComment(" /** ==========   SECRETARIS =========== */");
            generador.addComment(" ==========================================\n");

            generador.addComment("getSecretariUsername(String username) i getSecretariName(String username)");

            String[] secretaris = pluginUserInformation.getUsernamesByRol(ROL_SECRETARI);
            if (secretaris == null || secretaris.length == 0) {
                final String msg = " Error: No s'han trobat Secretaris.";
                generador.addComment(msg);
                errors.add(msg);
            } else {
                //System.out.println(" #directorsGenerals = " + directorsGenerals.length);
                for (int i = 0; i < secretaris.length; i++) {

                    UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(secretaris[i]);

                    final String area = userInfo.getCompanyArea();
                    final String dep = userInfo.getCompanyDepartment();

                    generador.generaSql(area, dep, TIPUS_SECRETARI_USERNAME, userInfo.getUsername(), null);
                    generador.generaSql(area, dep, TIPUS_SECRETARI_NAME, userInfo.getFullName(), null);
                    String dir3 = userInfo.getDir3();
                    if (dir3 != null) {
                        generador.generaSql(area, dep, TIPUS_DIR3, dir3, null);
                    }
                    if (userInfo.getAdministrationID() != null) {
                        generador.generaSql(area, dep, TIPUS_SECRETARI_NIF, userInfo.getAdministrationID(), null);
                    }
                }
            }
        }

        return errors;
    }

    public IUserInformationPlugin getPluginUserInformation(Properties database) throws Exception {
        {
            // Consultar Plugin de UserInformation
            String classUserInfo = database.getProperty(EOPB + "userinformation.class");
            String propertyBase = database.getProperty(EOPB + "userinformation.propertybase");

            String propertiesFile = database.getProperty(EOPB + "userinformation.propertiesfile");

            Properties props;
            if (propertiesFile == null) {
                props = System.getProperties();
            } else {
                props = new Properties();
                byte[] data = FileUtils.readFromFile(new File(propertiesFile));
                props.load(new ByteArrayInputStream(data));
            }

            IUserInformationPlugin pluginUserInformation = (IUserInformationPlugin) PluginsManager
                    .instancePluginByClassName(classUserInfo, propertyBase, props);

            return pluginUserInformation;
        }

    }

    public static class GeneradorSql {

        protected List<OrganitzacioJPA> infoOrganitzacio = new ArrayList<OrganitzacioJPA>();

        public GeneradorSql() {
            super();
        }

        public void addComment(String comment) {
            //builder.append("-- ").append(comment).append("\n");
        }

        public void generaSql(String tipus, String valor, String lang) {
            final String area = null;
            final String departament = null;
            generaSql(area, departament, tipus, valor, lang);
        }

        public void generaSql(String area, String departament, String tipus, String valor, String lang) {
            if (lang == null) {
                lang = "";
            } else {
                lang = "." + lang;
            }

            tipus = tipus + lang;

            OrganitzacioJPA o = new OrganitzacioJPA(area, departament, tipus, valor);

            infoOrganitzacio.add(o);

        }

        public List<OrganitzacioJPA> getInfoOrganitzacio() {
            return infoOrganitzacio;
        }

    }

}
