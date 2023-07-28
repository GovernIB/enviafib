package es.caib.enviafib.apiinterna.client.api;

import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeticionsApi {
  private ApiClient apiClient;

  public PeticionsApi() {
    this(Configuration.getDefaultApiClient());
  }

  public PeticionsApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Retorna un llistat amb la informacio de totes les peticions
   * 
   * @param inici Data d&#x27;inici, en format dd/MM/yyyy, a partir de la qual volem obtenir estadistiques (optional)
   * @param fi Data fi, en format dd/MM/yyyy, fins la qual volem tenir estadistiques (optional)
   * @return String
   * @throws ApiException if fails to make API call
   */
  public String obtenirDadesPeticions(String inici, String fi) throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/secure/peticio/peticions".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();

    localVarQueryParams.addAll(apiClient.parameterToPairs("", "inici", inici));
    localVarQueryParams.addAll(apiClient.parameterToPairs("", "fi", fi));

    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] { "BasicAuth" };

    GenericType<String> localVarReturnType = new GenericType<String>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
