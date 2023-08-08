package es.caib.enviafib.api.interna.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author anadal
 *
 */
public class DadesObertesUtils {

    public static final String MIME_APPLICATION_JSON = "application/json";

    public static final SimpleDateFormat SDF = new SimpleDateFormat("dd-MM-yyyy");

    public static Date parseDate(final String inputDate, final String paramName) throws WebApplicationException {
        Date dateStart;
        if (StringUtils.isBlank(inputDate)) {
            dateStart = null;
        } else {

            try {
                dateStart = SDF.parse(inputDate);
            } catch (ParseException pe) {
                final String msg = "Error en el format del paràmetre " + paramName + ": " + pe.getMessage();
                throw new WebApplicationException(msg, pe, Status.BAD_REQUEST);
            }
        }
        return dateStart;
    }
}
