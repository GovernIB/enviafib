package org.fundaciobit.pluginsib.estructuraorganitzativa.mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;

/**
 * 
 * @author anadal
 *
 */
public class TestMock {

    public static void main(String[] args) {

        try {

            byte[] data = FileUtils.readFromFile(new File("mock.properties"));

            String propStr = new String(data);

            Map<String, Object> mapping = new HashMap<String, Object>();

            mapping.put("gerent", "aroigXXX");

            System.out.println(propStr);

            String result = TemplateEngine.processExpressionLanguageSquareBrackets(propStr, mapping, new Locale("ca"));

            System.out.println("\n\n-----------------------------------------\n\n");
            System.out.println(result);

            // System.out.println("\n\n-----------------------------------------\n\n");

            Properties prop = new Properties();

            prop.load(new ByteArrayInputStream(data));

            IEstructuraOrganitzativaPlugin plugin = new MockEstructuraOrganitzativaPlugin("es.caib.myapp.", prop);

            String[] usernames = new String[] { "anadal", "jsastre", "eaguado", "other", "ptrias", "fbosch",
                    "bverges" };

            for (String u : usernames) {
                System.out.println("username[" + u + "] => " + plugin.getCapDepartamentDirectorGeneralByUsername(u));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
