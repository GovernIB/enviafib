package es.caib.enviafib.api.interna.secure.mobilenotification;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(name="SendMessageResult")
public class SendMessageResult {
    
    protected SendMessageResultCode code;

    protected String message;

    public SendMessageResult() {
        super();
    }

    public SendMessageResult(SendMessageResultCode code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    @Schema(description = "Codi resultat de l'operació")
    public SendMessageResultCode getCode() {
        return code;
    }

    public void setCode(SendMessageResultCode code) {
        this.code = code;
    }

    @Schema(description = "Missatge si hi ha error")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
