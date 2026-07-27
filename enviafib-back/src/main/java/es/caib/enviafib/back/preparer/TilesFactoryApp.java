package es.caib.enviafib.back.preparer;

import java.util.HashMap;
import java.util.Map;

import org.apache.tiles.Attribute;
import org.apache.tiles.Definition;
import org.apache.tiles.definition.DefinitionsFactory;
import org.apache.tiles.request.Request;
import es.caib.enviafib.commons.utils.Constants;
import org.fundaciobit.genapp.common.web.tiles.TileInfo;
import org.fundaciobit.genapp.common.web.tiles.TilesDiscovery;

import es.caib.enviafib.back.utils.Tab;

/**
 * 
 * @author GenApp
 * 18 mar 2025 15:06:51
 */
public class TilesFactoryApp implements DefinitionsFactory {

    private static final Map<String, Definition> definitions = new HashMap<String, Definition>();

    public static final String CONTINGUT_PROPERTY = "contingut";

    static {

        definitions.putAll(getAppDefinitions());

        String packageToScan = Constants.ENVIAFIB_PROPERTY_BASE + "back.controller";
        {
            Map<String, TileInfo> tiles = TilesDiscovery.getDefinitionsByAnnotation(packageToScan);
            TilesDiscovery.addAnnotationTilesToDefinitions(tiles, definitions);
        }

    }

    public TilesFactoryApp() {
        super();
    }

    @Override
    public Definition getDefinition(String name, Request tilesContext) {
        return definitions.get(name);
    }

    private static Map<String, Definition> getAppDefinitions() {

        HashMap<String, Definition> map = new HashMap<String, Definition>();

        /*{name=base.cap, template=/WEB-INF/jsp/moduls/cap.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.CapPreparer, attributes=null}*/
        Definition def_base_cap = new Definition("base.cap", new Attribute("/WEB-INF/jsp/moduls/cap.jsp"),
                new HashMap<String, Attribute>());
        def_base_cap.setPreparer("es.caib.enviafib.back.preparer.CapPreparer");
        map.put("base.cap", def_base_cap);
        /* ================================== */

        /*{name=base.menu_i_contingut, template=/WEB-INF/jsp/moduls/menu_i_contingut.jsp, role=null, preparerInstance=null, attributes=null}*/
        Definition def_base_menu_i_contingut = new Definition("base.menu_i_contingut",
                new Attribute("/WEB-INF/jsp/moduls/menu_i_contingut.jsp"), new HashMap<String, Attribute>());
        map.put("base.menu_i_contingut", def_base_menu_i_contingut);
        /* ================================== */

        /*{name=base.menu, template=/WEB-INF/jsp/moduls/blanc.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.MenuPreparer, attributes=null}*/
        Definition def_base_menu = new Definition("base.menu", new Attribute("/WEB-INF/jsp/moduls/blanc.jsp"),
                new HashMap<String, Attribute>());
        def_base_menu.setPreparer("es.caib.enviafib.back.preparer.MenuPreparer");
        map.put("base.menu", def_base_menu);
        /* ================================== */

        /*{name=base.contingut, template=/WEB-INF/jsp/moduls/blanc.jsp, role=null, preparerInstance=null, attributes=null}*/
        Definition def_base_contingut = new Definition("base.contingut", new Attribute("/WEB-INF/jsp/moduls/blanc.jsp"),
                new HashMap<String, Attribute>());
        map.put("base.contingut", def_base_contingut);
        /* ================================== */

        /*{name=base.peu, template=/WEB-INF/jsp/moduls/peu.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.PeuPreparer, attributes=null}*/
        Definition def_base_peu = new Definition("base.peu", new Attribute("/WEB-INF/jsp/moduls/peu.jsp"),
                new HashMap<String, Attribute>());
        def_base_peu.setPreparer("es.caib.enviafib.back.preparer.PeuPreparer");
        map.put("base.peu", def_base_peu);
        /* ================================== */

        /*{name=base.definition, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, cap=base.cap, contingut=base.contingut, menu=base.menu}}*/
        Definition def_base_definition = new Definition("base.definition",
                new Attribute("/WEB-INF/jsp/layout/layout.jsp"), new HashMap<String, Attribute>());
        def_base_definition.setPreparer("es.caib.enviafib.back.preparer.BasePreparer");
        def_base_definition.putAttribute("menu_i_contingut", new Attribute("base.menu_i_contingut"));
        def_base_definition.putAttribute("peu", new Attribute("base.peu"));
        def_base_definition.putAttribute("cap", new Attribute("base.cap"));
        def_base_definition.putAttribute("contingut", new Attribute("base.contingut"));
        def_base_definition.putAttribute("menu", new Attribute("base.menu"));
        map.put("base.definition", def_base_definition);
        /* ================================== */

        /*{name=principal.body, template=/WEB-INF/web/tiles/principal/principal.jsp, role=null, preparerInstance=null, attributes=null}*/
        Definition def_principal_body = new Definition("principal.body",
                new Attribute("/WEB-INF/web/tiles/principal/principal.jsp"), new HashMap<String, Attribute>());
        map.put("principal.body", def_principal_body);
        /* ================================== */

        /*{name=sense_enllaz, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, enlaces=, cap=base.cap, contingut=base.contingut, menu=base.menu}}*/
        Definition def_sense_enllaz = new Definition("sense_enllaz", new Attribute("/WEB-INF/jsp/layout/layout.jsp"),
                new HashMap<String, Attribute>());
        def_sense_enllaz.setExtends("base.definition");
        def_sense_enllaz.setPreparer("es.caib.enviafib.back.preparer.BasePreparer");
        def_sense_enllaz.putAttribute("menu_i_contingut", new Attribute("base.menu_i_contingut"));
        def_sense_enllaz.putAttribute("peu", new Attribute("base.peu"));
        def_sense_enllaz.putAttribute("enlaces", new Attribute(""));
        def_sense_enllaz.putAttribute("cap", new Attribute("base.cap"));
        def_sense_enllaz.putAttribute("contingut", new Attribute("base.contingut"));
        def_sense_enllaz.putAttribute("menu", new Attribute("base.menu"));
        map.put("sense_enllaz", def_sense_enllaz);
        /* ================================== */

        /*{name=exceptionTile, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/error.jsp, cap=base.cap, menu=base.menu}}*/
        Definition def_exceptionTile = new Definition("exceptionTile", new Attribute("/WEB-INF/jsp/layout/layout.jsp"),
                new HashMap<String, Attribute>());
        def_exceptionTile.setExtends("base.definition");
        def_exceptionTile.setPreparer("es.caib.enviafib.back.preparer.BasePreparer");
        def_exceptionTile.putAttribute("menu_i_contingut", new Attribute("base.menu_i_contingut"));
        def_exceptionTile.putAttribute("peu", new Attribute("base.peu"));
        def_exceptionTile.putAttribute("contingut", new Attribute("/error.jsp"));
        def_exceptionTile.putAttribute("cap", new Attribute("base.cap"));
        def_exceptionTile.putAttribute("menu", new Attribute("base.menu"));
        map.put("exceptionTile", def_exceptionTile);
        /* ================================== */

        /*{name=all, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/all/homepublic.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_all = new Definition(def_base_definition);
        def_all.setName("all");
        def_all.putAttribute("menu", new Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));
        def_all.putAttribute("contingut", new Attribute("/WEB-INF/jsp/all/homepublic.jsp"));
        map.put(def_all.getName(), def_all);
        /* ================================== */

        /*{name=avislegal_ca, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/all/avislegal_ca.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_avislegal_ca = new Definition(def_all);
        def_avislegal_ca.setName("avislegal_ca");
        def_avislegal_ca.putAttribute("contingut", new Attribute("/WEB-INF/jsp/all/avislegal_ca.jsp"));
        map.put(def_avislegal_ca.getName(), def_avislegal_ca);
        /* ================================== */

        /*{name=avislegal_es, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/all/avislegal_es.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_avislegal_es = new Definition(def_all);
        def_avislegal_es.setName("avislegal_es");
        def_avislegal_es.putAttribute("contingut", new Attribute("/WEB-INF/jsp/all/avislegal_es.jsp"));
        map.put(def_avislegal_es.getName(), def_avislegal_es);
        /* ================================== */

        /*{name=homepublic, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/all/homepublic.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_homepublic = new Definition(def_all);
        def_homepublic.setName("homepublic");
        def_homepublic.putAttribute("contingut", new Attribute("/WEB-INF/jsp/all/homepublic.jsp"));
        map.put(def_homepublic.getName(), def_homepublic);
        /* ================================== */

        /*{name=common, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/principal.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_common = new Definition(def_base_definition);
        def_common.setName(Tab.MENU_PUBLIC_AND_COMMON);
        def_common.putAttribute("menu", new Attribute("/WEB-INF/jsp/moduls/menu_inici.jsp"));
        def_common.putAttribute("contingut", new Attribute("/WEB-INF/jsp/principal.jsp"));
        map.put(def_common.getName(), def_common);
        /* ================================== */

        /*{name=principal, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, contingut=/WEB-INF/jsp/principal.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_inici.jsp}}*/
        Definition def_principal = new Definition(def_common);
        def_principal.setName("principal");
        def_principal.putAttribute("contingut", new Attribute("/WEB-INF/jsp/principal.jsp"));
        map.put(def_principal.getName(), def_principal);
        /* ================================== */

        /*{name=user, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, pipella=user, cap=base.cap, contingut=base.contingut, menu=/WEB-INF/jsp/moduls/menu_user.jsp}}*/
        Definition def_user = new Definition(def_base_definition);
        def_user.setName(Tab.MENU_USER);
        def_user.putAttribute("pipella", new Attribute(Tab.MENU_USER));
        def_user.putAttribute("menu", new Attribute("/WEB-INF/jsp/moduls/menu_user.jsp"));
        map.put(def_user.getName(), def_user);
        /* ================================== */

        /*{name=admin, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, pipella=admin, cap=base.cap, contingut=base.contingut, menu=/WEB-INF/jsp/moduls/menu_admin.jsp}}*/
        Definition def_admin = new Definition(def_base_definition);
        def_admin.setName(Tab.MENU_ADMIN);
        def_admin.putAttribute("pipella", new Attribute(Tab.MENU_ADMIN));
        def_admin.putAttribute("menu", new Attribute("/WEB-INF/jsp/moduls/menu_admin.jsp"));
        map.put(def_admin.getName(), def_admin);
        /* ================================== */

        /* ================================== */

        /*{name=desenvolupament, template=/WEB-INF/jsp/layout/layout.jsp, role=null, preparerInstance=es.caib.enviafib.back.preparer.BasePreparer, attributes={menu_i_contingut=base.menu_i_contingut, peu=base.peu, pipella=desenvolupament, contingut=/WEB-INF/jsp/desenvolupament.jsp, cap=base.cap, menu=/WEB-INF/jsp/moduls/menu_desenvolupament.jsp}}*/
        Definition def_desenvolupament = new Definition(def_base_definition);
        def_desenvolupament.setName("desenvolupament");
        def_desenvolupament.putAttribute("contingut", new Attribute("/WEB-INF/jsp/desenvolupament.jsp"));
        map.put(def_desenvolupament.getName(), def_desenvolupament);
        /* ================================== */
        
        
        Definition def_webdb = new Definition(def_base_definition);
        //"webdb", new Attribute("/WEB-INF/jsp/layout/layout.jsp"), new HashMap<String, Attribute>());
        def_webdb.setName(Tab.MENU_WEBDB);
        def_webdb.setExtends(def_base_definition.getName());
        def_webdb.putAttribute("pipella", new Attribute(Tab.MENU_WEBDB));
        def_webdb.putAttribute("menu", new Attribute("/WEB-INF/jsp/webdb/menu_webdb.jsp"));

        map.put(def_webdb.getName(), def_webdb);

        return map;

    }

}
