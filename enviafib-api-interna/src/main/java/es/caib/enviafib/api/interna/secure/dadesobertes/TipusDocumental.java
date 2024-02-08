package es.caib.enviafib.api.interna.secure.dadesobertes;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.v3.oas.annotations.media.Schema;

@JsonPropertyOrder({ "code", "name" })
@Schema(description = "Informació bàsica d'un Tipus Documental")
public class TipusDocumental {

    @Schema(required = true, description = "Identificador intern de l'objecte")
    @JsonProperty("code")
    protected String code;

    @Schema(required = true, description = "Nom del Tipus Documental")
    @JsonProperty("name")
    protected String name;

    public TipusDocumental() {
        super();
    }

    public TipusDocumental(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
