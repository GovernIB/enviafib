package org.fundaciobit.pluginsib.estructuraorganitzativa.mock;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.AbstractPluginProperties;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * 
 * @author anadal
 */
public class MockEstructuraOrganitzativaPlugin extends AbstractPluginProperties
        implements IEstructuraOrganitzativaPlugin {

    public static final String MOCK_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE = ESTRUCTURAORGANITZATIVA_PROPERTY_BASE
            + "mock.";

    public MockEstructuraOrganitzativaPlugin() {
        super();
    }

    public MockEstructuraOrganitzativaPlugin(String propertyKeyBase, Properties properties) {
        super(propertyKeyBase, properties);
    }

    public MockEstructuraOrganitzativaPlugin(String propertyKeyBase) {
        super(propertyKeyBase);
    }

    @Override
    public String getCodiDIR3ByUsername(String username) throws Exception {
        return processarPropietat("codidir3byusername", username);
    }

    @Override
    public String getGerentPresident() throws Exception {
        return processarPropietat("gerentpresident", null);
    };

    @Override
    public String getNomAreaConselleriaByUsername(String username, String lang) throws Exception {
        return processarPropietat("nomareaconselleriabyusername." + lang, username);
    }

    @Override
    public String getCapAreaConsellerByUsername(String username) throws Exception {
        return processarPropietat("capareaconsellerbyusername", username);
    };

    @Override
    public String getNomDepartamentDireccioGeneralByUsername(String username, String lang) throws Exception {
        return processarPropietat("nomdepartamentdirecciogeneralbyusername." + lang, username);
    };

    @Override
    public String getCapDepartamentDirectorGeneralByUsername(String username) throws Exception {
        return processarPropietat("capdepartamentdirectorgeneralbyusername", username);
    };

    @Override
    public String getSecretariByUsername(String username) throws Exception {
        return processarPropietat("secretaribyusername", username);
    };

    @Override
    public String getEncarregatCompresByUsername(String username) throws Exception {
        return processarPropietat("encarregatcompresbyusername", username);
    };

    @Override
    public String getRecursosHumansByUsername(String username) throws Exception {
        return processarPropietat("recursoshumansbyusername", username);
    }

    protected String processarPropietat(String propietat, String username) throws Exception {

        String valorPropietat = getPropertyRequired(MOCK_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + propietat);

        Map<String, Object> map = new HashMap<String, Object>();
        if (username != null) {
            map.put("username", username);
        }

        String plantilla = valorPropietat;
        String generat = TemplateEngine.processExpressionLanguage(plantilla, map, new Locale("ca"));

        if (generat != null && generat.trim().length() == 0) {
            return null;
        }
        return generat;
    }

}
