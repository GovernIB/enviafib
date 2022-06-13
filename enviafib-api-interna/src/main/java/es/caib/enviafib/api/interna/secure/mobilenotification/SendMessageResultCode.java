package es.caib.enviafib.api.interna.secure.mobilenotification;

//import com.fasterxml.jackson.annotation.JsonValue;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * 
 * @author anadal
 *
 */
@Schema(name = "SendMessageResultCode", enumAsRef = true)
public enum SendMessageResultCode {

    OK("OK"),
    UNKNOWN_ERROR("UNKNOWN_ERROR"),
    NOTIFICATION_CODE_DO_NOT_EXIST("NOTIFICATION_CODE_DO_NOT_EXIST"),
    NOTIFICATION_CODE_DISABLED("NOTIFICATION_CODE_DISABLED"),
    ENTITYCODE_DO_NOT_EXIST("ENTITYCODE_DO_NOT_EXIST"),
    ENTITY_DISABLED("ENTITY_DISABLED"),
    PLUGIN_DISABLED("PLUGIN_DISABLED"),
    PLUGIN_ENTITY_DO_NOT_EXIST("PLUGIN_ENTITY_DO_NOT_EXIST"),
    PLUGIN_ENTITY_DISABLED("PLUGIN_ENTITY_DISABLED"),
    CITIZEN_DO_NOT_EXIST("CITIZEN_DO_NOT_EXIST"),
    ERROR_SENDING_NOTIFICATION("ERROR_SENDING_NOTIFICATION");

    private String value;

    SendMessageResultCode(String value) {
      this.value = value;
    }
//
//    @JsonValue
//    public String getValue() {
//      return value;
//    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }


}
