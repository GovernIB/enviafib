/*
 * API REST INTERNA de EnviaFIB
 * Conjunt de Serveis REST de EnviaFIB per ser accedits des de l'interior
 *
 * OpenAPI spec version: 1.0
 * Contact: governdigital.enviafib@fundaciobit.org
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */

package es.caib.enviafib.apiinterna.client.api;

import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.model.PeticioDeFirmaPaginacio;
import es.caib.enviafib.apiinterna.client.model.TipusDocumentalsPaginacio;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Ignore;

/**
 * API tests for DadesObertesEnviaFibApi
 * @author anadal
 */
@Ignore
public class DadesObertesEnviaFibApiTest {

    public static void main(String[] args) {
        try {

            DadesObertesEnviaFibApi api = getApi();
            //new DadesObertesEnviaFibApiTest().tipusdocumentalsTest(api);
            new DadesObertesEnviaFibApiTest().peticionsdefirmaTest(api);

            System.out.println("FINAL");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ApiException e) {

            System.err.println("Class: " + e.getClass());
            System.err.println("Code:  " + e.getCode());
            System.err.println("Msg: " + e.getMessage());
            System.err.println("Body: " + e.getResponseBody());

            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    protected static DadesObertesEnviaFibApi getApi() throws IOException {

        Properties props = new Properties();
        props.load(new FileInputStream(new File("test.properties")));

        ApiClient apiclient = new ApiClient();

        apiclient.setBasePath(props.getProperty("host"));

        apiclient.setUsername(props.getProperty("username"));
        apiclient.setPassword(props.getProperty("password"));

        apiclient.setDebugging(true);

        DadesObertesEnviaFibApi api = new DadesObertesEnviaFibApi(apiclient);

        return api;
    }

    /**
     * Retorna un llistat amb la informacio de totes les peticions
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void peticionsdefirmaTest(DadesObertesEnviaFibApi api) throws ApiException {

        String inici = "11-08-2022";
        String fi = "11-12-2022";
        Integer page = 1;
        Integer pagesize = 10;
        String language = "es";

        PeticioDeFirmaPaginacio response = api.peticionsdefirma(inici, fi, page, pagesize, language);

        System.out.println(response);
    }

    /**
     * Retorna un llistat dels tipus documentals
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void tipusdocumentalsTest(DadesObertesEnviaFibApi api) throws ApiException {
        String language = "ca";
        TipusDocumentalsPaginacio response = api.tipusdocumentals(language);

        System.out.println(response);
        // TODO: test validations
    }
}
