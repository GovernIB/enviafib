package es.caib.enviafib.apiinterna.client.api;

import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.enviafib.apiinterna.client.model.PeticioDeFirmaPaginacio;
import es.caib.enviafib.apiinterna.client.model.RestExceptionInfo;
import es.caib.enviafib.apiinterna.client.model.TipusDocumentalsAllElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "org.openapitools.codegen.languages.JavaClientCodegen")
public class DadesObertesEnviaFibApi {
  private ApiClient apiClient;

  public DadesObertesEnviaFibApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DadesObertesEnviaFibApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna un llistat amb la informacio de les peticions de firma
   * 
   * @param startdate Data d&#39;inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades (optional)
   * @param enddate Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades (optional)
   * @param page Pàgina de la que es vol obtenir les dades. Comença en 1. (optional)
   * @param pageSize Número d&#39;elements a retornar per pàgina. Opcional. Per defecte 10 (optional)
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code PeticioDeFirmaPaginacio}
   * @throws ApiException if fails to make API call
   */
  public PeticioDeFirmaPaginacio peticionsdefirma(String startdate, String enddate, Integer page, Integer pageSize, String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/dadesobertes/peticionsdefirma".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "startdate", startdate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "enddate", enddate));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page-size", pageSize));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<PeticioDeFirmaPaginacio> localVarReturnType = new GenericType<PeticioDeFirmaPaginacio>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
  /**
   * Retorna un llistat de tots els tipus documentals
   * 
   * @param language Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) (optional, default to ca)
   * @return a {@code TipusDocumentalsAllElements}
   * @throws ApiException if fails to make API call
   */
  public TipusDocumentalsAllElements tipusdocumentals(String language) throws ApiException {
    Object localVarPostBody = null;
    
    // create path and map variables
    String localVarPath = "/secure/dadesobertes/tipusdocumentals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, String> localVarCookieParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "language", language));

    
    
    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<TipusDocumentalsAllElements> localVarReturnType = new GenericType<TipusDocumentalsAllElements>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
      }
}
