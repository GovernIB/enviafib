package org.fundaciobit.pluginsib.estructuraorganitzativa.database.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Properties;

import org.apache.log4j.BasicConfigurator;
import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.api.IEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.estructuraorganitzativa.ldapcaib.LdapCaibEstructuraOrganitzativaPlugin;
import org.junit.Test;

/**
 * 
 * @author anadal
 *
 */
public class TestLdapCaibPlugin {

    private static final String PACKAGE_BASE = "es.caib.enviafib.";
    public static final String EOPB = PACKAGE_BASE
            + LdapCaibEstructuraOrganitzativaPlugin.LDAPCAIB_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE;

    @Test
    public void executeMain() {
        main(null);
    }

    public static void main(String[] args) {

        try {

            BasicConfigurator.configure();

            Properties testProperties = new Properties();
            {
                byte[] data = FileUtils.readFromFile(new File("test.properties"));

                testProperties.load(new ByteArrayInputStream(data));

            }

            LdapCaibEstructuraOrganitzativaPlugin plugin;
            Properties propDatabase = new Properties();
            {
                byte[] data = FileUtils.readFromFile(new File("ldapcaib.properties"));

                propDatabase.load(new ByteArrayInputStream(data));

                plugin = new LdapCaibEstructuraOrganitzativaPlugin(PACKAGE_BASE, propDatabase);
            }

            provarTotsMetodes(plugin, testProperties.getProperty("username"), "es");
            
            System.err.flush();

            System.out.println(" -- FINAL --  ");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void provarTotsMetodes(IEstructuraOrganitzativaPlugin plugin, String username, String lang)
            throws Exception {

        printResult("\n=================  ENTITAT - ORGANITZACIÓ - EMPRESA ========= ", "");
        printResult("getGerentPresidentName() => ", plugin.getGerentPresidentName());
        printResult("getGerentPresidentUsername() => ", plugin.getGerentPresidentUsername());
        printResult("getNameOrganitzacioEmpresa(lang) => ", plugin.getNameOrganitzacioEmpresa(lang));
        printResult("getDir3OrganitzacioEmpresa() => ", plugin.getDir3OrganitzacioEmpresa());
        printResult("getNifOrganitzacioEmpresa() => ", plugin.getNifOrganitzacioEmpresa());

        printResult("\n=================  ÀREA - CONSELLERIA ========= ", "");
        printResult("getNameAreaConselleria(String username, String lang) => ",
                plugin.getNameAreaConselleria(username, lang));
        printResult("getDir3AreaConselleria(username) => ", plugin.getDir3AreaConselleria(username));
        printResult("getCodeAreaConselleria(username) => ", plugin.getCodeAreaConselleria(username));
        printResult("getCapAreaConsellerUsername(username) => ", plugin.getCapAreaConsellerUsername(username));
        printResult("getCapAreaConsellerName(username) => ", plugin.getCapAreaConsellerName(username));

        printResult("\n=================  DEPARTAMENT - DIRECCIÓ GENERAL ========= ", "");
        printResult("getNameDepartamentDireccioGeneral(username, lang) => ",
                plugin.getNameDepartamentDireccioGeneral(username, lang));
        printResult("getDir3DepartamentDireccioGeneral(username) => ",
                plugin.getDir3DepartamentDireccioGeneral(username));
        printResult("getCodeDepartamentDireccioGeneral(username) => ",
                plugin.getCodeDepartamentDireccioGeneral(username));
        printResult("getCapDepartamentDirectorGeneralUsername(username) => ",
                plugin.getCapDepartamentDirectorGeneralUsername(username));
        printResult("getCapDepartamentDirectorGeneralName(username) => ",
                plugin.getCapDepartamentDirectorGeneralName(username));

        printResult("\n=================  SECRETARI ========= ", "");
        printResult("getSecretariUsername(username) => ", plugin.getSecretariUsername(username));
        printResult("getSecretariName(username) => ", plugin.getSecretariName(username));

        printResult("\n=================  DADES GENERALS ========= ", "");
        printResult("getEncarregatCompresUsername(username) => ", plugin.getEncarregatCompresUsername(username));
        printResult("getEncarregatCompresName(username) => ", plugin.getEncarregatCompresName(username));
        printResult("getRecursosHumansUsername(username) => ", plugin.getRecursosHumansUsername(username));
        printResult("getRecursosHumansName(username) => ", plugin.getRecursosHumansName(username));

        printResult("\n=================  CÀRRECS ADDICIONALS ========= ", "");
        printResult("getCarrec1Username(username) => ", plugin.getCarrec1Username(username));
        printResult("getCarrec1Name(username) => ", plugin.getCarrec1Name(username));
        printResult("getCarrec1PositionName(username, lang) => ", plugin.getCarrec1PositionName(username, lang));
        printResult("getCarrec2Username(username) => ", plugin.getCarrec2Username(username));
        printResult("getCarrec2Name(username) => ", plugin.getCarrec2Name(username));
        printResult("getCarrec2PositionName(username, lang) => ", plugin.getCarrec2PositionName(username, lang));
    }

    public static void printResult(String label, String value) throws InterruptedException {
        if (value == null) {
            System.err.println(label + value);
            System.err.flush();
        } else {
            System.out.println(label + value);
            System.out.flush();
        }
        Thread.sleep(12);
    }

}
