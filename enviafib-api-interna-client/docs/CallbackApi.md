# CallbackApi

All URIs are relative to *http://localhost:8080/enviafibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**event1**](CallbackApi.md#event1) | **GET** /public/cbrest/v1/versio | Reb l&#x27;event de portafib realitza les accions corresponents

<a name="event1"></a>
# **event1**
> PortaFIBEvent event1()

Reb l&#x27;event de portafib realitza les accions corresponents

### Example
```java
// Import classes:
//import es.caib.enviafib.apiinterna.client.services.ApiException;
//import es.caib.enviafib.apiinterna.client.api.CallbackApi;


CallbackApi apiInstance = new CallbackApi();
try {
    PortaFIBEvent result = apiInstance.event1();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling CallbackApi#event1");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PortaFIBEvent**](PortaFIBEvent.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

