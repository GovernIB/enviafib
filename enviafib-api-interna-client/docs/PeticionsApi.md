# PeticionsApi

All URIs are relative to */enviafibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**obtenirDadesPeticions**](PeticionsApi.md#obtenirDadesPeticions) | **GET** /secure/peticio/peticions | Retorna un llistat amb la informacio de totes les peticions

<a name="obtenirDadesPeticions"></a>
# **obtenirDadesPeticions**
> String obtenirDadesPeticions(inici, fi)

Retorna un llistat amb la informacio de totes les peticions

### Example
```java
// Import classes:
//import es.caib.enviafib.apiinterna.client.services.ApiClient;
//import es.caib.enviafib.apiinterna.client.services.ApiException;
//import es.caib.enviafib.apiinterna.client.services.Configuration;
//import es.caib.enviafib.apiinterna.client.services.auth.*;
//import es.caib.enviafib.apiinterna.client.api.PeticionsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

PeticionsApi apiInstance = new PeticionsApi();
String inici = "inici_example"; // String | Data d'inici, en format dd/MM/yyyy, a partir de la qual volem obtenir estadistiques
String fi = "fi_example"; // String | Data fi, en format dd/MM/yyyy, fins la qual volem tenir estadistiques
try {
    String result = apiInstance.obtenirDadesPeticions(inici, fi);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PeticionsApi#obtenirDadesPeticions");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **inici** | **String**| Data d&#x27;inici, en format dd/MM/yyyy, a partir de la qual volem obtenir estadistiques | [optional]
 **fi** | **String**| Data fi, en format dd/MM/yyyy, fins la qual volem tenir estadistiques | [optional]

### Return type

**String**

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

