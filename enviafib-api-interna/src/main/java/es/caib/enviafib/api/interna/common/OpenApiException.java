package es.caib.enviafib.api.interna.common;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * 
 * @author anadal
 *
 */
public class OpenApiException extends WebApplicationException {

    public static boolean exportStackTrace = false;

    public static boolean exportCause = false;

    public OpenApiException() {
        super();

    }

    public OpenApiException(String message, int status) {
        super(message, status);

    }

    public OpenApiException(String message, Status status) {
        super(message, status);

    }

    public OpenApiException(String message, Throwable cause, int status) {
        super(message, cause, status);

    }

    public OpenApiException(String message, Throwable cause, Status status) throws IllegalArgumentException {
        super(message, cause, status);

    }

    public OpenApiException(String message, Throwable cause) {
        super(message, cause);

    }

    public OpenApiException(String message) {
        super(message);

    }

    @Override
    public Response getResponse() {
        OpenApiExceptionInfo info = getOpenApiExceptionInfo();
        return Response.status(info.getCode()).entity(info).build();
    }

    public OpenApiExceptionInfo getOpenApiExceptionInfo() {

        OpenApiExceptionInfo info = new OpenApiExceptionInfo();

        info.setCode(super.getResponse().getStatus());
        info.setErrorMessage(this.getMessage());

        if (exportStackTrace) {
            info.setStackTrace(ExceptionUtils.getStackTrace(this));
        }

        Throwable cause = this.getCause();

        if (cause != null && exportCause) {
            info.setCauseException(cause.getClass().getName());
            if (exportStackTrace) {
                info.setCauseStackTrace(ExceptionUtils.getStackTrace(cause));
            }
        }

        return info;

    }

}
