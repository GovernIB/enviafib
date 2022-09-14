package es.caib.enviafib.ejb.test;

import java.io.FileInputStream;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.PluginsManager;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.InfoArxiuEJB;
import es.caib.enviafib.logic.PeticioLogicaEJB;
import es.caib.enviafib.model.entity.InfoArxiu;
import es.caib.enviafib.persistence.PeticioJPA;
import es.caib.plugins.arxiu.api.Document;
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

            print("plugin", plugin);
            System.out.println("Starting test: \n");

            Long peticioID = 3011L;

            PeticioLogicaEJB peticioEjb = new PeticioLogicaEJB();
            PeticioJPA peticio = peticioEjb.findByPrimaryKeyPublic(peticioID);

            long infoArxiuID = peticio.getInfoArxiuID();
            InfoArxiuEJB infoArxiuEjb = new InfoArxiuEJB();
            InfoArxiu infoArxiu = infoArxiuEjb.findByPrimaryKey(infoArxiuID);

            String docID = infoArxiu.getArxiuDocumentID();
//            String docID = "bfe7a9ea-c392-4734-abb4-009fa2f222a4";

            Document doc = plugin.documentDetalls(docID, null, false);
            print("doc", doc);

            String csv = doc.getDocumentMetadades().getCsv();
            print("csv", csv);

            print("getOriginalFileUrl", plugin.getOriginalFileUrl(doc));
            // Demana un usuari Caib - Em posa "concsv - No té accés a l'aplicació"

            print("getPrintableFileUrl", plugin.getPrintableFileUrl(doc));
            // Demana un usuari Caib - Em posa "concsv - No té accés a l'aplicació"

            print("getEniFileUrl", plugin.getEniFileUrl(doc));
            // Error - HTTP Status 404 - The requested resource (/concsv/enidoc/${csv}) is
            // not available.

            print("getValidationFileUrl", plugin.getValidationFileUrl(doc));
            // Ok.

            print("getCsvValidationWeb", plugin.getCsvValidationWeb(doc));
            // Error - HTTP Status 404 - The requested resource (/concsv/enidoc/${csv}) is
            // not available.

            print("getCsvGenerationDefinition", plugin.getCsvGenerationDefinition(doc));
            // null

            System.out.println("\nFinal");
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void print(String s, Object o) {
        System.out.println(s + ": " + o);
    }
}
