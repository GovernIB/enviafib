package org.fundaciobit.pluginsib.estructuraorganitzativa.mock;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;

/**
 * 
 * @author anadal
 *
 */
public class TestMock {

    public static void main(String[] args) {

        try {

            byte[] data = FileUtils.readFromFile(new File("mock.properties"));

            Properties prop = new Properties();

            prop.load(new ByteArrayInputStream(data));

            IEstructuraOrganitzativaPlugin plugin = new MockEstructuraOrganitzativaPlugin("es.caib.myapp.", prop);

            
            provarTotsMetodes(plugin, "anadal", "es");
            
            //provarIdiomes(plugin);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected static void provarTotsMetodes(IEstructuraOrganitzativaPlugin plugin, String username, String lang)
            throws Exception {

        System.out.println(" =================  ENTITAT - ORGANITZACIÓ - EMPRESA ========= ");
        System.out.println("getGerentPresidentName() => " + plugin.getGerentPresidentName());
        System.out.println("getGerentPresidentUsername() => " + plugin.getGerentPresidentUsername());
        System.out.println("getNameOrganitzacioEmpresa(lang) => " + plugin.getNameOrganitzacioEmpresa(lang));
        System.out.println("getDir3OrganitzacioEmpresa() => " + plugin.getDir3OrganitzacioEmpresa());
        System.out.println("getNifOrganitzacioEmpresa() => " + plugin.getNifOrganitzacioEmpresa());

        System.out.println(" =================  ÀREA - CONSELLERIA ========= ");

        System.out.println("getNameAreaConselleria(String username, String lang) => "
                + plugin.getNameAreaConselleria(username, lang));
        System.out.println("getDir3AreaConselleria(username) => " + plugin.getDir3AreaConselleria(username));
        System.out.println("getCodeAreaConselleria(username) => " + plugin.getCodeAreaConselleria(username));
        System.out.println("getCapAreaConsellerUsername(username) => " + plugin.getCapAreaConsellerUsername(username));
        System.out.println("getCapAreaConsellerName(username) => " + plugin.getCapAreaConsellerName(username));
        System.out.println(" =================  DEPARTAMENT - DIRECCIÓ GENERAL ========= ");
        System.out.println("getNameDepartamentDireccioGeneral(username, lang) => "
                + plugin.getNameDepartamentDireccioGeneral(username, lang));
        System.out.println(
                "getDir3DepartamentDireccioGeneral(username) => " + plugin.getDir3DepartamentDireccioGeneral(username));
        System.out.println(
                "getCodeDepartamentDireccioGeneral(username) => " + plugin.getCodeDepartamentDireccioGeneral(username));
        System.out.println("getCapDepartamentDirectorGeneralUsername(username) => "
                + plugin.getCapDepartamentDirectorGeneralUsername(username));
        System.out.println("getCapDepartamentDirectorGeneralName(username) => "
                + plugin.getCapDepartamentDirectorGeneralName(username));
        System.out.println(" =================  DADES GENERALS ========= ");
        System.out.println("getSecretariUsername(username) => " + plugin.getSecretariUsername(username));
        System.out
                .println("getEncarregatCompresUsername(username) => " + plugin.getEncarregatCompresUsername(username));
        System.out.println("getRecursosHumansUsername(username) => " + plugin.getRecursosHumansUsername(username));
        System.out.println(" =================  CÀRRECS ADDICIONALS ========= ");
        System.out.println("getCarrec1Username(username) => " + plugin.getCarrec1Username(username));
        System.out.println("getCarrec1Name(username) => " + plugin.getCarrec1Name(username));
        System.out
                .println("getCarrec1PositionName(username, lang) => " + plugin.getCarrec1PositionName(username, lang));
        System.out.println("getCarrec2Username(username) => " + plugin.getCarrec2Username(username));
        System.out.println("getCarrec2Name(username) => " + plugin.getCarrec2Name(username));
        System.out
                .println("getCarrec2PositionName(username, lang) => " + plugin.getCarrec2PositionName(username, lang));
    }

    protected static void provarIdiomes(IEstructuraOrganitzativaPlugin plugin) throws Exception {
        String[] usernames = new String[] { "anadal", "jsastre", "eaguado", "other", "ptrias", "fbosch", "bverges" };

        for (String u : usernames) {
            System.out.println("Cap Dep. Username[" + u + "] => " + plugin.getCapDepartamentDirectorGeneralUsername(u));
            System.out.println("Cap. Dep. Name[" + u + "] => " + plugin.getCapDepartamentDirectorGeneralName(u));
        }
    }

}
