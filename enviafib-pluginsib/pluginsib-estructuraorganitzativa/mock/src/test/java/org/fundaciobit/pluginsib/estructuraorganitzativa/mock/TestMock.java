package org.fundaciobit.pluginsib.estructuraorganitzativa.mock;

import java.io.FileInputStream;
import java.util.Properties;

import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;

/**
 * 
 * @author anadal
 *
 */
public class TestMock {

    public static void main(String[] args) {

        try {

            Properties prop = new Properties();

            prop.load(new FileInputStream("mock.properties"));

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
