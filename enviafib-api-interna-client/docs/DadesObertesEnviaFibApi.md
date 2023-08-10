# DadesObertesEnviaFibApi

All URIs are relative to */enviafibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**peticionsdefirma**](DadesObertesEnviaFibApi.md#peticionsdefirma) | **GET** /secure/dadesobertes/peticionsdefirma | Retorna un llistat amb la informacio de les peticions de firma
[**tipusdocumentals**](DadesObertesEnviaFibApi.md#tipusdocumentals) | **GET** /secure/dadesobertes/tipusdocumentals | Retorna un llistat dels tipus documentals

<a name="peticionsdefirma"></a>
# **peticionsdefirma**
> PeticioDeFirmaPaginacio peticionsdefirma(inici, fi, page, pagesize, language)

Retorna un llistat amb la informacio de les peticions de firma

### Example
```java
// Import classes:
//import es.caib.enviafib.apiinterna.client.services.ApiClient;
//import es.caib.enviafib.apiinterna.client.services.ApiException;
//import es.caib.enviafib.apiinterna.client.services.Configuration;
//import es.caib.enviafib.apiinterna.client.services.auth.*;
//import es.caib.enviafib.apiinterna.client.api.DadesObertesEnviaFibApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

DadesObertesEnviaFibApi apiInstance = new DadesObertesEnviaFibApi();
String inici = "inici_example"; // String | Data d'inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades
String fi = "fi_example"; // String | Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades
Integer page = 56; // Integer | Pàgina de la que es vol obtenir les dades
Integer pagesize = 56; // Integer | Elements retornats per la pàgina
String language = "language_example"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
try {
    PeticioDeFirmaPaginacio result = apiInstance.peticionsdefirma(inici, fi, page, pagesize, language);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DadesObertesEnviaFibApi#peticionsdefirma");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **inici** | **String**| Data d&#x27;inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades | [optional]
 **fi** | **String**| Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades | [optional]
 **page** | **Integer**| Pàgina de la que es vol obtenir les dades | [optional]
 **pagesize** | **Integer**| Elements retornats per la pàgina | [optional]
 **language** | **String**| Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) | [optional]

### Return type

[**PeticioDeFirmaPaginacio**](PeticioDeFirmaPaginacio.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="tipusdocumentals"></a>
# **tipusdocumentals**
> TipusDocumentalsPaginacio tipusdocumentals(language)

Retorna un llistat dels tipus documentals

### Example
```java
// Import classes:
//import es.caib.enviafib.apiinterna.client.services.ApiClient;
//import es.caib.enviafib.apiinterna.client.services.ApiException;
//import es.caib.enviafib.apiinterna.client.services.Configuration;
//import es.caib.enviafib.apiinterna.client.services.auth.*;
//import es.caib.enviafib.apiinterna.client.api.DadesObertesEnviaFibApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();
// Configure HTTP basic authorization: BasicAuth
HttpBasicAuth BasicAuth = (HttpBasicAuth) defaultClient.getAuthentication("BasicAuth");
BasicAuth.setUsername("YOUR USERNAME");
BasicAuth.setPassword("YOUR PASSWORD");

DadesObertesEnviaFibApi apiInstance = new DadesObertesEnviaFibApi();
String language = "language_example"; // String | Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')
try {
    TipusDocumentalsPaginacio result = apiInstance.tipusdocumentals(language);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DadesObertesEnviaFibApi#tipusdocumentals");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **language** | **String**| Idioma en que s&#x27;han de retornar les dades(Només suportat &#x27;ca&#x27; o &#x27;es&#x27;) | [optional]

### Return type

[**TipusDocumentalsPaginacio**](TipusDocumentalsPaginacio.md)

### Authorization

[BasicAuth](../README.md#BasicAuth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

