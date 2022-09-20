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

    /** =================  ORGANITZACIÓ - EMPRESA ========= */

    @Override
    public String getGerentPresidentName() throws Exception {
        return processarPropietat("gerentpresident.name", null);
    }

    @Override
    public String getGerentPresidentUsername() throws Exception {
        return processarPropietat("gerentpresident.username", null);
    }

    @Override
    public String getNameOrganitzacioEmpresa(String lang) throws Exception {
        return processarPropietat("organitzacioempresa.name", null);
    }

    @Override
    public String getDir3OrganitzacioEmpresa() throws Exception {
        return processarPropietat("organitzacioempresa.dir3", null);
    }

    @Override
    public String getNifOrganitzacioEmpresa() throws Exception {
        return processarPropietat("organitzacioempresa.nif", null);
    }

    /** =================  ÀREA - CONSELLERIA ========= */

    @Override
    public String getNameAreaConselleria(String username, String lang) throws Exception {
        return processarPropietat("areaconselleria.name." + lang, username);
    }

    @Override
    public String getDir3AreaConselleria(String username) throws Exception {
        String tmp = processarPropietat("areaconselleria.dir3", username);
        if (tmp == null) {
            tmp = getDir3OrganitzacioEmpresa();
        }
        return tmp;
    }

    @Override
    public String getCodeAreaConselleria(String username) throws Exception {
        return processarPropietat("areaconselleria.code", username);
    }

    @Override
    public String getCapAreaConsellerUsername(String username) throws Exception {
        return processarPropietat("areaconselleria.boss.username", username);
    }

    @Override
    public String getCapAreaConsellerName(String username) throws Exception {
        return processarPropietat("areaconselleria.boss.name", username);
    }

    /** =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= */

    @Override
    public String getNameDepartamentDireccioGeneral(String username, String lang) throws Exception {
        return processarPropietat("departamentdirecciogeneral.name." + lang, username);
    }

    @Override
    public String getDir3DepartamentDireccioGeneral(String username) throws Exception {
        String tmp = processarPropietat("departamentdirecciogeneral.dir3", username);
        if (tmp == null) {
            tmp = getDir3AreaConselleria(username);
        }
        return tmp;
    }

    @Override
    public String getCodeDepartamentDireccioGeneral(String username) throws Exception {
        return processarPropietat("departamentdirecciogeneral.code", username);
    }

    @Override
    public String getCapDepartamentDirectorGeneralUsername(String username) throws Exception {
        return processarPropietat("departamentdirecciogeneral.boss.username", username);
    }

    @Override
    public String getCapDepartamentDirectorGeneralName(String username) throws Exception {
        return processarPropietat("departamentdirecciogeneral.boss.name", username);
    }

    /** =================  DADES GENERALS ========= */

    @Override
    public String getSecretariUsername(String username) throws Exception {
        return processarPropietat("secretari", username);
    };

    @Override
    public String getEncarregatCompresUsername(String username) throws Exception {
        return processarPropietat("encarregatcompres", username);
    };

    @Override
    public String getRecursosHumansUsername(String username) throws Exception {
        return processarPropietat("recursoshumans", username);
    }

    /** =================  CÀRRECS ADDICIONALS ========= */

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Username(String username) throws Exception {
        return processarPropietat("carrec1.username", username);
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec1Name(String username) throws Exception {
        return processarPropietat("carrec1.name", username);
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec1PositionName(String username, String lang) throws Exception {
        return processarPropietat("carrec1.positionname." + lang, username);
    }

    /* Username de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Username(String username) throws Exception {
        return processarPropietat("carrec2.username", username);
    }

    /* Nom complet de la persona que ocupa aquest càrrec */
    @Override
    public String getCarrec2Name(String username) throws Exception {
        return processarPropietat("carrec2.name", username);
    }

    /* Nom del càrrec que ocupa. */
    @Override
    public String getCarrec2PositionName(String username, String lang) throws Exception {
        return processarPropietat("carrec2.positionname." + lang, username);
    }

    protected String processarPropietat(String propietat, String username) throws Exception {

        String valorPropietat = getPropertyRequired(MOCK_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE + propietat);
        
        if (valorPropietat.trim().length() == 0) {
            return null;
        }

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
