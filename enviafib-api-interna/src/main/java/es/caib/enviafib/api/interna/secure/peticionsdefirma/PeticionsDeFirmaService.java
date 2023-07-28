package es.caib.enviafib.api.interna.secure.peticionsdefirma;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.PeticioLogicaService;
import es.caib.enviafib.logic.utils.I18NLogicUtils;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 *
 * @author ptrias
 *
 */
@Path("/secure/peticio")
@OpenAPIDefinition(tags = @Tag(name = "Peticions", description = "informacio básica de les peticions d'EnviaFIB"))
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
public class PeticionsDeFirmaService {

    protected Logger log = Logger.getLogger(PeticionsDeFirmaService.class);

    @EJB(mappedName = PeticioLogicaService.JNDI_NAME)
    protected PeticioLogicaService peticioLogicaEjb;

    //Constants.EFI_ADMIN, Constants.EFI_USER, 
    
    @Path("/peticions")
    @GET
    @RolesAllowed({ Constants.EFI_WS })
    @SecurityRequirement(name = "BasicAuth")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
            tags = {"Peticions" }, 
            operationId = "obtenirDadesPeticions", 
            summary = "Retorna un llistat amb la informacio de totes les peticions")
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "404", 
                            description = "EFIB: Paràmetres incorrectes", 
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "401", 
                            description = "EFIB: No Autenticat", 
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "403", 
                            description = "EFIB: No Autoritzat", 
                            content = @Content(mediaType = MediaType.APPLICATION_JSON)),
                    @ApiResponse(
                            responseCode = "400", 
                            description = "EFIB: Error durant el processament o enviament del missatge", 
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "200", 
                            description = "EFIB: Enviat missatge correctament", 
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON, 
                                    schema = @Schema(implementation = String.class))) })
    public Response obtenirDadesPeticions(
            @Parameter(
                    description = "Data d'inici, en format dd/MM/yyyy, a partir de la qual volem obtenir estadistiques",
                    required = false, 
                    example = "31/08/2022", 
                    schema = @Schema(implementation = String.class)) 
            @QueryParam("inici") String dataIniciRequest,

            @Parameter(
                    description = "Data fi, en format dd/MM/yyyy, fins la qual volem tenir estadistiques", 
                    required = false, 
                    example = "31/09/2022", 
                    schema = @Schema(implementation = String.class)) 
            @QueryParam("fi") String dataFiRequest) {

        final SimpleDateFormat SDF = new SimpleDateFormat("dd/MM/yyyy");

        Map<String, String> mapTipusPet = new HashMap<String, String>();
        fillTipusPet(mapTipusPet);

        Map<String, String> mapTipusDoc = new HashMap<String, String>();
        fillTipusDoc(mapTipusDoc);

        try {
            Date dateStart = SDF.parse(dataIniciRequest);
            Date dateEnd = SDF.parse(dataFiRequest);

            Timestamp from = new Timestamp(atStartOfDay(dateStart).getTime());
            Timestamp to = new Timestamp(atEndOfDay(dateEnd).getTime());
            
//            Timestamp creacioStart = new Timestamp(dateStart.getTime());
//            final Long dayInMs = 86400000L; //1000 * 60 * 60 * 24;
//            Timestamp creacioEnd = new Timestamp(dateEnd.getTime() + dayInMs);

            Where w = PeticioFields.DATACREACIO.between(from, to);
            List<Peticio> llistat = peticioLogicaEjb.select(w);

            List<PeticioDTO> peticionsInfo = new ArrayList<PeticioDTO>();

            for (Peticio item : llistat) {

                String nif = item.getDestinatariNif();
                String titol = item.getNom();
                String idioma = (item.getIdiomaID().equals("ca") ? "Català" : "Espanyol");
                String dir3 = item.getArxiuParamFuncionariDir3();

                String tipusPeticio = mapTipusPet.get(item.getTipusDocumental());
                String tipusDocumental = mapTipusDoc.get(item.getTipusDocumental());

                String creada = SDF.format(new Date(item.getDataCreacio().getTime()));

                String finalitzada = null;
                if (item.getDataFinal() != null) {
                    finalitzada = SDF.format(new Date(item.getDataFinal().getTime()));
                }

                PeticioDTO peticio = new PeticioDTO(nif, titol, creada, finalitzada, idioma, tipusDocumental,
                        tipusPeticio, dir3);
                peticionsInfo.add(peticio);
            }

            return Response.ok().entity(peticionsInfo).build();
        } catch (I18NException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return Response.ok().status(HttpStatus.SC_BAD_REQUEST).build();
        } catch (ParseException e) {
            return Response.ok().status(HttpStatus.SC_BAD_REQUEST, "Error amb format dels parametres d'entrada")
                    .build();
        }
    }

    public void fillTipusDoc(Map<String, String> map) {
        map.put("1", "Resolució");
        map.put("2", "Acord");
        map.put("3", "Contracte");
        map.put("4", "Conveni");
        map.put("5", "Declaració");
        map.put("6", "Comunicació");
        map.put("7", "Notificació");
        map.put("8", "Publicació");
        map.put("9", "Justificant de recepció");
        map.put("10", "Acta");
        map.put("11", "Certificat");
        map.put("12", "Diligència");
        map.put("13", "Informe");
        map.put("14", "Sol·licitud");
        map.put("15", "Denúncia");
        map.put("16", "Al·legació");
        map.put("17", "Recursos");
        map.put("18", "Comunicació ciutadà");
        map.put("19", "Factura");
        map.put("20", "Uns altres confiscats");
        map.put("51", "Llei.");
        map.put("52", "Moció");
        map.put("53", "Instrucció.");
        map.put("54", "Convocatòria.");
        map.put("55", "Ordre del dia.");
        map.put("56", "Informe de Ponència.");
        map.put("57", "Dictamen de Comissió.");
        map.put("58", "Iniciativa legislativa.");
        map.put("59", "Pregunta.");
        map.put("60", "Interpel·lació.");
        map.put("61", "Resposta.");
        map.put("62", "Proposició no de llei.");
        map.put("63", "Esmena.");
        map.put("64", "Proposada de resolució.");
        map.put("65", "Compareixença.");
        map.put("66", "Sol·licitud d'informació.");
        map.put("67", "Escrit.");
        map.put("68", "Iniciativa legislativa.");
        map.put("69", "Petició.");
        map.put("99", "Altres tipus de documents");
    }

    public void fillTipusPet(Map<String, String> map) {
        map.put("0", "NIF");
        map.put("1", "Autofirma");
        map.put("2", "Flux");
        map.put("5", "Plantilla Flux Usuari");
        map.put("6", "Plantilla Flux Entitat");
        map.put("7", "Flux Simple");
        map.put("8", "Flux JSON");
        map.put("11", "Gerent / President");
        map.put("12", "Cap d´Area / Conseller");
        map.put("13", "Cap de Departament / Director General");
        map.put("14", "Secretari");
        map.put("15", "Encarregat de Compres");
        map.put("16", "Recursos Humans");
        map.put("17", "Càrrec Addicional 1");
        map.put("18", "Càrrec Addicional 2");
    }

    public static Date atEndOfDay(Date date) {
        return DateUtils.addMilliseconds(DateUtils.ceiling(date, Calendar.DATE), -1);
    }

    public static Date atStartOfDay(Date date) {
        return DateUtils.truncate(date, Calendar.DATE);
    }

}
