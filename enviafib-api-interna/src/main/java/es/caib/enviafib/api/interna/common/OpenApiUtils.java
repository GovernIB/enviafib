package es.caib.enviafib.api.interna.common;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import javax.ws.rs.core.Response.Status;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author anadal
 *
 */
public class OpenApiUtils {

    public static final String MIME_APPLICATION_JSON = "application/json";

    public static String convertToDateTimeToISO8601(Date dateToConvert) {

        if (dateToConvert == null) {
            return null;
        }

        LocalDateTime ldt = Instant.ofEpochMilli(dateToConvert.getTime()).atZone(ZoneId.systemDefault())
                .truncatedTo(ChronoUnit.SECONDS).toLocalDateTime();

        return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

    }

    public static Date parseDate(final String inputDate, final String paramName) throws OpenApiException {
        Date dateStart;
        if (StringUtils.isBlank(inputDate)) {
            dateStart = null;
        } else {

            try {

                LocalDate myLocalDate = LocalDate.parse(inputDate);

                ZonedDateTime zdt = myLocalDate.atStartOfDay(ZoneId.systemDefault());

                Instant instant = zdt.toInstant();
                dateStart = java.util.Date.from(instant);

            } catch (Throwable pe) {
                final String msg = "Error en el format del paràmetre de tipus date amb nom " + paramName + ": "
                        + pe.getMessage();
                throw new OpenApiException(msg, pe, Status.BAD_REQUEST);
            }
        }
        return dateStart;
    }

    public static void main(String[] args) {
        try {

            System.out.println(convertToDateTimeToISO8601(new Date()));

            Date date = parseDate("2023-02-23", "prova");
            System.out.println(date);

            //            date = parseDate("12-02-2023", "prova");
            //            System.err.println(date);

        } catch (Throwable th) {
            th.printStackTrace(System.err);
        }
        System.out.println("FINAL");
    }
}
