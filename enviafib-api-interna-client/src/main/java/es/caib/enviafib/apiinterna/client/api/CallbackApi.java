package es.caib.enviafib.apiinterna.client.api;

import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.Pair;

import javax.ws.rs.core.GenericType;

import es.caib.enviafib.apiinterna.client.model.PortaFIBEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CallbackApi {
  private ApiClient apiClient;

  public CallbackApi() {
    this(Configuration.getDefaultApiClient());
  }

  public CallbackApi(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  public ApiClient getApiClient() {
    return apiClient;
  }

  public void setApiClient(ApiClient apiClient) {
    this.apiClient = apiClient;
  }

  /**
   * Reb l&#x27;event de portafib realitza les accions corresponents
   * 
   * @return PortaFIBEvent
   * @throws ApiException if fails to make API call
   */
  public PortaFIBEvent event1() throws ApiException {
    Object localVarPostBody = null;
    // create path and map variables
    String localVarPath = "/public/cbrest/v1/versio".replaceAll("\\{format\\}","json");

    // query params
    List<Pair> localVarQueryParams = new ArrayList<Pair>();
    Map<String, String> localVarHeaderParams = new HashMap<String, String>();
    Map<String, Object> localVarFormParams = new HashMap<String, Object>();


    
    final String[] localVarAccepts = {
      "application/json"
    };
    final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

    final String[] localVarContentTypes = {
      
    };
    final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

    String[] localVarAuthNames = new String[] {  };

    GenericType<PortaFIBEvent> localVarReturnType = new GenericType<PortaFIBEvent>() {};
    return apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
  }
}
