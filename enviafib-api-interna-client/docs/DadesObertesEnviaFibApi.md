# DadesObertesEnviaFibApi

All URIs are relative to */enviafibapi/interna*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**peticionsdefirma**](DadesObertesEnviaFibApi.md#peticionsdefirma) | **GET** /secure/dadesobertes/peticionsdefirma | Retorna un llistat amb la informacio de les peticions de firma |
| [**tipusdocumentals**](DadesObertesEnviaFibApi.md#tipusdocumentals) | **GET** /secure/dadesobertes/tipusdocumentals | Retorna un llistat de tots els tipus documentals |



## peticionsdefirma

> PeticioDeFirmaPaginacio peticionsdefirma(startdate, enddate, page, pageSize, language)

Retorna un llistat amb la informacio de les peticions de firma

### Example

```java
// Import classes:
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.auth.*;
import es.caib.enviafib.apiinterna.client.services.models.*;
import es.caib.enviafib.apiinterna.client.api.DadesObertesEnviaFibApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/enviafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        DadesObertesEnviaFibApi apiInstance = new DadesObertesEnviaFibApi(defaultClient);
        String startdate = "2022-08-29"; // String | Data d'inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades
        String enddate = "2023-12-31"; // String | Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades
        Integer page = 1; // Integer | Pàgina de la que es vol obtenir les dades. Comença en 1.
        Integer pageSize = 56; // Integer | Número d'elements a retornar per pàgina. Opcional. Per defecte 10
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            PeticioDeFirmaPaginacio result = apiInstance.peticionsdefirma(startdate, enddate, page, pageSize, language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DadesObertesEnviaFibApi#peticionsdefirma");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **startdate** | **String**| Data d&#39;inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades | [optional] |
| **enddate** | **String**| Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades | [optional] |
| **page** | **Integer**| Pàgina de la que es vol obtenir les dades. Comença en 1. | [optional] |
| **pageSize** | **Integer**| Número d&#39;elements a retornar per pàgina. Opcional. Per defecte 10 | [optional] |
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**PeticioDeFirmaPaginacio**](PeticioDeFirmaPaginacio.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **400** | EFIB: Paràmetres incorrectes |  -  |
| **401** | EFIB: No Autenticat |  -  |
| **403** | EFIB: No Autoritzat |  -  |
| **500** | EFIB: Error durant la consulta de les dades obertes |  -  |
| **200** | EFIB: Retornades dades obertes correctament |  -  |


## tipusdocumentals

> TipusDocumentalsAllElements tipusdocumentals(language)

Retorna un llistat de tots els tipus documentals

### Example

```java
// Import classes:
import es.caib.enviafib.apiinterna.client.services.ApiClient;
import es.caib.enviafib.apiinterna.client.services.ApiException;
import es.caib.enviafib.apiinterna.client.services.Configuration;
import es.caib.enviafib.apiinterna.client.services.auth.*;
import es.caib.enviafib.apiinterna.client.services.models.*;
import es.caib.enviafib.apiinterna.client.api.DadesObertesEnviaFibApi;

public class Example {
    public static void main(String[] args) {
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath("/enviafibapi/interna");
        
        // Configure HTTP basic authorization: BasicAuth
        HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
        BasicAuth.setUsername("YOUR USERNAME");
        BasicAuth.setPassword("YOUR PASSWORD");

        DadesObertesEnviaFibApi apiInstance = new DadesObertesEnviaFibApi(defaultClient);
        String language = "ca"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
        try {
            TipusDocumentalsAllElements result = apiInstance.tipusdocumentals(language);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DadesObertesEnviaFibApi#tipusdocumentals");
            System.err.println("Status code: " + e.getCode());
            System.err.println("Reason: " + e.getResponseBody());
            System.err.println("Response headers: " + e.getResponseHeaders());
            e.printStackTrace();
        }
    }
}
```

### Parameters


| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **language** | **String**| Idioma en que s&#39;han de retornar les dades(Només suportat &#39;ca&#39; o &#39;es&#39;) | [optional] [default to ca] |

### Return type

[**TipusDocumentalsAllElements**](TipusDocumentalsAllElements.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

- **Content-Type**: Not defined
- **Accept**: application/json


### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **401** | EFIB: No Autenticat |  -  |
| **403** | EFIB: No Autoritzat |  -  |
| **500** | EFIB: Error durant la consulta de les dades obertes |  -  |
| **200** | EFIB: Retornades dades obertes correctament |  -  |

