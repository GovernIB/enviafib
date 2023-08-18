package es.caib.enviafib.apiinterna.client.api;

import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.enviafib.apiinterna.client.model.PeticioDeFirmaPaginacio;
import es.caib.enviafib.apiinterna.client.model.RestExceptionInfo;
import es.caib.enviafib.apiinterna.client.model.TipusDocumentalsPaginacio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   * @param inici Data d&#x27;inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades (optional)
   * @param fi Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades (optional)
   * @param page Pàgina de la que es vol obtenir les dades (optional)
   * @param pagesize Elements retornats per la pàgina (optional)
   * @param language Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) (optional)
   * @return PeticioDeFirmaPaginacio
   * @throws ApiException if fails to make API call
   */
  public PeticioDeFirmaPaginacio peticionsdefirma(String inici, String fi, Integer page, Integer pagesize, String language) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/secure/dadesobertes/peticionsdefirma".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inici", inici));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "fi", fi));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", page));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "pagesize", pagesize));
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
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
  /**
   * Retorna un llistat dels tipus documentals
   * 
   * @param language Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) (optional)
   * @return TipusDocumentalsPaginacio
   * @throws ApiException if fails to make API call
   */
  public TipusDocumentalsPaginacio tipusdocumentals(String language) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/secure/dadesobertes/tipusdocumentals".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
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

    GenericType<TipusDocumentalsPaginacio> localVarReturnType = new GenericType<TipusDocumentalsPaginacio>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
