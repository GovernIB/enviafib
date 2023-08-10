package es.caib.enviafib.api.interna.common;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;

import org.codehaus.jackson.JsonProcessingException;

import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @author anadal
 *
 */
public class ISO8601DateTimeSerializer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jgen, SerializerProvider provider)
            throws IOException, JsonProcessingException {

        if (date == null) {
            jgen.writeNull();
        } else {
            String s = OpenApiUtils.convertToDateTimeToISO8601(date);
            jgen.writeString(s);
        }

    }

}
