

# RestExceptionInfo

Estructura de dades utilitzada per passar informació d'un error

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**code** | **Integer** | Codi de HTTP de l&#39;error. Veure https://en.wikipedia.org/wiki/List_of_HTTP_status_codes. |  |
|**errorMessage** | **String** | Missatge de l&#39;error |  |
|**stackTrace** | **String** | Stacktrace de l&#39;excepció si n&#39;hi hagués. |  [optional] |
|**causeException** | **String** | Tipus de l&#39;excepció origen (cause) si n&#39;hi hagués. |  [optional] |
|**causeStackTrace** | **String** | Stacktrace de l&#39;excepció origen (cause) si n&#39;hi hagués. |  [optional] |



