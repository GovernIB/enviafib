package es.caib.enviafib.ejb.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

/**
 * 
 * @author ptrias
 *
 */

public class ProvesPluginArxiu {

    public static void main(String[] args) {

        System.out.println("Hello World!");

        IArxiuPlugin plugin;
        try {

            Properties prop = new Properties();
            prop.load(new FileInputStream("arxiu.properties"));

            plugin = (IArxiuPlugin) PluginsManager.instancePluginByClassName(
                    "es.caib.plugins.arxiu.caib.ArxiuPluginCaib", Constants.ENVIAFIB_PROPERTY_BASE, prop);

            System.out.println(plugin);

            String arxiuID = "ad393c24-5ac6-4d7c-aeef-6934224cd6e0";

            System.out.println("Starting test: \n");

            String Csv = plugin.getCsv(arxiuID);
            System.out.println("Csv : " + Csv);

            String CsvGenerationDefinition = plugin.getCsvGenerationDefinition(arxiuID);
            System.out.println("CsvGenerationDefinition : " + CsvGenerationDefinition);

            String CsvValidationWeb = plugin.getCsvValidationWeb(arxiuID);
            System.out.println("CsvValidationWeb : " + CsvValidationWeb);

            String EniFileUrl = plugin.getEniFileUrl(arxiuID);
            System.out.println("EniFileUrl : " + EniFileUrl);

            String OriginalFileUrl = plugin.getOriginalFileUrl(arxiuID);
            System.out.println("OriginalFileUrl : " + OriginalFileUrl);

            String PrintableFileUrl = plugin.getPrintableFileUrl(arxiuID);
            System.out.println("PrintableFileUrl : " + PrintableFileUrl);

            String ValidationFileUrl = plugin.getValidationFileUrl(arxiuID);
            System.out.println("ValidationFileUrl : " + ValidationFileUrl);
                        
            System.out.println("ARXIU_BASE_PROPERTY : " + plugin.ARXIU_BASE_PROPERTY);
            
            
            
            
            System.out.println("\nFinal");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }
}
