package es.caib.enviafib.back.controller.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.html.IconUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.genapp.common.web.menuoptions.MenuItem;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOption;
import org.fundaciobit.genapp.common.web.menuoptions.MenuOptionManager;
import org.fundaciobit.genapp.common.web.tiles.Tile;
import org.fundaciobit.genapp.common.web.tiles.TileAttribute;
import org.fundaciobit.genapp.common.web.tiles.TileType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.utils.Tab;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.FitxerService;

/**
 * 
 * @author anadal (u80067)
 * 25 sept 2026 13:38:53
 */
@Controller
@RequestMapping(value = "/admin")

@MenuOption(
        labelCode = "=Contents of enviafib.properties file",
        order = 1000,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/properties",
        relativeLink = "",
        addSeparatorBefore = true)
@MenuOption(
        labelCode = "=Contents of enviafib.system.properties file",
        order = 1010,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/systemproperties",
        relativeLink = "")
@MenuOption(
        labelCode = "=Reload contents of property files",
        order = 1020,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/reloadproperties",
        relativeLink = "")
@MenuOption(
        labelCode = "=Edit enviafib.properties file",
        order = 1030,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/editproperties",
        relativeLink = "")

@MenuOption(
        labelCode = "=Size of database tables",
        order = 1040,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/sizeofdatabasetables",
        relativeLink = "")
@MenuOption(
        labelCode = "=WebPages list",
        order = 1050,
        group = Tab.MENU_ADMIN,
        baseLink = "/admin/webpages",
        relativeLink = "")
@Tile(
        name = AdminController.KEYVALUE_ADMIN_TILE,
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/common/keyvalue.jsp",
        attributes = { @TileAttribute(name = "titol", value = "admin.admin") },        
        type = TileType.ANOTHER)
@Tile(
        name = "editpropertiesAdmin",
        extendsTile = Tab.MENU_ADMIN,
        contentJsp = "/WEB-INF/jsp/admin/editproperties.jsp",
        attributes = { @TileAttribute(name = "titol", value = "=Editar Properties") },
        type = TileType.ANOTHER)
public class AdminController {

    public static final String KEYVALUE_ADMIN_TILE = "keyvalueAdmin";

    @EJB(mappedName = FitxerService.JNDI_NAME)
    protected FitxerService fitxerEjb;

    @RequestMapping(value = "/properties")
    public ModelAndView properties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Properties prop = Configuracio.getAppProperties();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Object key : prop.keySet()) {
            keyValuelist.add(new KeyValueItem((String) key, prop.getProperty((String) key, ""),
                    "<i class=\"" + IconUtils.ICON_INFO + "\"></i>", ""));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Item list of enviafib.app.properties file");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/systemproperties")
    public ModelAndView systemproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Properties prop = Configuracio.getAppSystemProperties();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Object key : prop.keySet()) {
            keyValuelist.add(new KeyValueItem((String) key, "***************"));
        }

        Collections.sort(keyValuelist);

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Item list of enviafib.system.properties file");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/reloadproperties")
    public String reloadproperties(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Configuracio.reloadProperties();
        
        HtmlUtils.saveMessageSuccess(request, "Les propietats de l'aplicació s'han recarregat correctament.");

        return "redirect:/admin/properties";
    }

    /**
    * Show the form to edit the contents of the enviafib.properties file.
    * 
    * @param model Model to hold attributes for the view
    * @return The name of the view to render
    */
    @RequestMapping(value = "/editproperties", method = RequestMethod.GET)
    public ModelAndView showEditForm(Model model, HttpServletRequest request) {
        StringBuilder content = new StringBuilder();

        String path = System.getProperty(Constants.ENVIAFIB_PROPERTY_BASE + "properties");

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            model.addAttribute("fileContent", content.toString());
            return new ModelAndView("editpropertiesAdmin");
        } catch (IOException e) {
            HtmlUtils.saveMessageError(request, "No es pot llegir el fitxer: " + e.getMessage());
            return new ModelAndView(new RedirectView("/canviarPipella/admin", true));
        }

    }

    @RequestMapping(value = "/editproperties", method = RequestMethod.POST)
    public String saveFile(
            @RequestParam("fileContent")
            String fileContent, RedirectAttributes redirectAttributes, HttpServletRequest request) {

        String path = System.getProperty(Constants.ENVIAFIB_PROPERTY_BASE + "properties");
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8))) {
            writer.write(fileContent);

            HtmlUtils.saveMessageSuccess(request, "El fitxer s´ha guardat correctament.");
        } catch (IOException e) {
            HtmlUtils.saveMessageError(request, "No s´ha pogut guardar el fitxer: " + e.getMessage());

        }

        return "redirect:/admin/editproperties";
    }

    @RequestMapping(value = "/sizeofdatabasetables")
    public ModelAndView tablesize(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        Map<String, Long> sizes = getTableSizes();

        List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (Map.Entry<String, Long> entry : sizes.entrySet()) {
            keyValuelist.add(new KeyValueItem((String) entry.getKey(), entry.getValue() + " bytes",
                    "<i class=\"fas fa-database\"></i>", humanReadableByteCount(entry.getValue())));
        }

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Size of database tables");
        mav.addObject("subtitle", "");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    public Map<String, Long> getTableSizes() throws I18NException {

        String dialect = Configuracio.getAppProperties()
                .getProperty(Constants.ENVIAFIB_PROPERTY_BASE + "hibernate.dialect");

        if (dialect == null) {
            throw new I18NException("genapp.comodi",
                    "No s'ha trobat la propietat de configuració per a " + Constants.ENVIAFIB_PROPERTY_BASE
                            + ".hibernate.dialect, dins del fitxer de propietats de l'aplicació.");
        }

        boolean isPostgres = dialect.toLowerCase().contains("postgres");

        if (!isPostgres) {

            boolean isOracle = dialect.toLowerCase().contains("oracle");
            if (!isOracle) {

                throw new I18NException("genapp.comodi",
                        "Dialect no suportat per a la consulta de mida de taules: " + dialect);
            }

        }

        return getTableSizes(fitxerEjb.getEntityManager(), isPostgres);
    }

    public static Map<String, Long> getTableSizes(EntityManager entityManager, boolean isPostgres) {

        Map<String, Long> tableSizes = new TreeMap<String, Long>();

        if (isPostgres) {
            List<Object[]> results = entityManager
                    .createNativeQuery("SELECT " + " tablename AS table_name, "
                            + " pg_total_relation_size(schemaname || '.' || tablename)  AS total_bytes "
                            + " FROM pg_tables " + " WHERE schemaname NOT IN ('pg_catalog', 'information_schema')")
                    .getResultList();

            for (Object[] row : results) {
                tableSizes.put((String) row[0], ((Number) row[1]).longValue());
            }

        } else {
            // Oracle: user_segments agrupa por segmento (tabla, índice, lob...)
            // Filtramos solo TABLE y sumamos para consolidar particiones si las hay
            List<Object[]> results = entityManager.createNativeQuery(
                    "SELECT " + " segment_name AS table_name, SUM(bytes) AS total_bytes " + " FROM user_segments "
                            + " WHERE segment_type IN ('TABLE', 'TABLE PARTITION', 'TABLE SUBPARTITION') "
                            + " GROUP BY segment_name ")
                    .getResultList();

            for (Object[] row : results) {

                tableSizes.put((String) row[0], ((Number) row[1]).longValue());
            }
        }

        // Ordenar el Map pels valors (de major a menor) i retornar un LinkedHashMap
        Map<String, Long> sortedTableSizes = tableSizes.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed()).collect(Collectors.toMap(
                        Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return sortedTableSizes;
    }

    @RequestMapping(value = "/webpages")
    public ModelAndView webPages(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String[] tabs = { Tab.MENU_PUBLIC_AND_COMMON, Tab.MENU_USER, Tab.MENU_ADMIN };

        java.util.List<KeyValueItem> keyValuelist = new ArrayList<KeyValueItem>();

        for (String tab : tabs) {
            java.util.List<MenuItem> items = MenuOptionManager.getMenuItems(tab);
            for (MenuItem item : items) {

                if (item == null) {
                    continue;
                }

                String label = item.getLabel();

                if (label.startsWith("=")) {
                    label = label.substring(1);
                } else {
                    label = I18NUtils.tradueix(label);
                }

                KeyValueItem keyValueItem = new KeyValueItem(label, Configuracio.getUrlBase() + item.getUrl());

                keyValueItem.setPre(tab);

                keyValuelist.add(keyValueItem);

                // System.out.println("Tab: " + tab + ", Label: " + item.getLabel() + ", Link: " + item.getLink());
            }
        }

        ModelAndView mav = new ModelAndView("keyvalueAdmin");
        mav.addObject("title", "Llistat d'Opcions de Menú");
        mav.addObject("subtitle",
                "Per descarregar les URL a les pàgines web en format pla, feis clic <a target=\"_blank\" href=\""
                        + request.getContextPath() + "/admin/webpagesplain\" >aquí");
        mav.addObject("keyValueList", keyValuelist);
        return mav;
    }

    @RequestMapping(value = "/webpagesplain")
    public void webPagesPlain(HttpSession session, HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String[] tabs = { Tab.MENU_PUBLIC_AND_COMMON, Tab.MENU_USER, Tab.MENU_ADMIN };

        // Plain text response
        response.setContentType("text/plain");

        PrintWriter writer = response.getWriter();

        String url = Configuracio.getUrlBase() + request.getContextPath();

        for (String tab : tabs) {

            List<MenuItem> items = MenuOptionManager.getMenuItems(tab);
            for (MenuItem item : items) {

                if (item == null) {
                    continue;
                }

                writer.println(url + item.getUrl());
            }
        }
        writer.flush();
        writer.close();
    }

    public class KeyValueItem implements Comparable<KeyValueItem> {
        private String key;
        private String value;
        private String pre;
        private String post;

        public KeyValueItem(String key, String value) {
            this.key = key;
            this.value = value;
        }

        public KeyValueItem(String key, String value, String pre, String post) {
            this.key = key;
            this.value = value;
            this.pre = pre;
            this.post = post;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getPre() {
            return pre;
        }

        public void setPre(String pre) {
            this.pre = pre;
        }

        public String getPost() {
            return post;
        }

        public void setPost(String post) {
            this.post = post;
        }

        @Override
        public int compareTo(KeyValueItem o2) {
            return this.getKey().compareTo(o2.getKey());
        }
    }

    public static String humanReadableByteCount(long bytes) {
        int unit = 1024;
        if (bytes < unit) {
            return bytes + " B";
        }
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        String prefix = "KMGTPE".charAt(exp - 1) + "B"; // KB, MB, GB, TB, PB, EB
        return String.format("%.2f %s", bytes / Math.pow(unit, exp), prefix);
    }

}
