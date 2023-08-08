package es.caib.enviafib.api.interna.common;

import javax.validation.constraints.NotNull;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
// Això serveix per evitar la impressió de valors nulls
@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class OpenApiExceptionInfo {

    @NotNull
    @Schema(required = true, description = "")
    protected int code;

    @NotNull
    @Schema(required = true, description = "")
    protected String errorMessage;

    @Schema(nullable = false)
    protected String stackTrace;

    @Schema(nullable = false)
    protected String causeException;

    @Schema(nullable = false)
    protected String causeStackTrace;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public String getCauseException() {
        return causeException;
    }

    public void setCauseException(String causeException) {
        this.causeException = causeException;
    }

    public String getCauseStackTrace() {
        return causeStackTrace;
    }

    public void setCauseStackTrace(String causeStackTrace) {
        this.causeStackTrace = causeStackTrace;
    }

}
