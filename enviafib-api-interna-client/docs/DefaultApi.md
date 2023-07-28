# DefaultApi

All URIs are relative to *http://localhost:8080/enviafibapi/interna*

Method | HTTP request | Description
------------- | ------------- | -------------
[**event**](DefaultApi.md#event) | **POST** /public/cbrest/v1/event | 

<a name="event"></a>
# **event**
> event()



### Example
```java
// Import classes:
//import es.caib.enviafib.apiinterna.client.services.ApiException;
//import es.caib.enviafib.apiinterna.client.api.DefaultApi;


DefaultApi apiInstance = new DefaultApi();
try {
    apiInstance.event();
} catch (ApiException e) {
    System.err.println("Exception when calling DefaultApi#event");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

