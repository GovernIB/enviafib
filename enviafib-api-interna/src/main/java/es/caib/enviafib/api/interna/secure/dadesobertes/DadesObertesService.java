package es.caib.enviafib.api.interna.secure.dadesobertes;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.security.RolesAllowed;

import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.RestException;
import org.fundaciobit.pluginsib.utils.rest.RestExceptionInfo;
import org.fundaciobit.pluginsib.utils.rest.RestUtils;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import java.util.Date;
import es.caib.enviafib.model.entity.Peticio;

import org.apache.log4j.Logger;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.PeticioLogicaService;
import es.caib.enviafib.model.fields.PeticioFields;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.ws.rs.core.Response.Status;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import javax.ejb.EJB;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;

/**
 * 
 * @author anadal
 *
 */
@Path("/secure/dadesobertes")
@OpenAPIDefinition(
        info = @Info(
                title = "Dades Obertes de EnviaFIB",
                description = "Conjunt de Serveis REST de EnviaFIB que ofereixen Dades Obertes"),
        tags = { @Tag(
                name = DadesObertesService.TAG_NAME,
                description = "Conjunt de mètodes que es poden consultar per obtenir dades obertes") })
@Produces({ RestUtils.MIME_APPLICATION_JSON })
@Consumes({ RestUtils.MIME_APPLICATION_JSON })
@SecurityScheme(type = SecuritySchemeType.HTTP, name = DadesObertesService.SECURITY_NAME, scheme = "basic")
public class DadesObertesService extends RestUtils {

    // No modificar !!!
    protected static final String TAG_NAME = "DadesObertesEnviaFib";

    protected static final String SECURITY_NAME = "BasicAuth";

    protected Logger log = Logger.getLogger(DadesObertesService.class);

    protected static final Map<String, String> MAP_TIPUS_PETICIO = new HashMap<String, String>();

    protected static final Map<String, String> MAP_TIPUS_DOCUMENTAL = new HashMap<String, String>();
    
    protected static final Map<String, String> MAP_IDIOMA = new HashMap<String, String>();

    static {
        
        
        MAP_IDIOMA.put("ca_ca", "Català");
        MAP_IDIOMA.put("es_ca", "Castellà");
        
        MAP_IDIOMA.put("ca_es", "Catalan");
        MAP_IDIOMA.put("es_es", "Castellano");

        
        MAP_TIPUS_PETICIO.put("0_ca", "NIF");
        MAP_TIPUS_PETICIO.put("1_ca", "Autofirma");
        MAP_TIPUS_PETICIO.put("2_ca", "Flux");
        MAP_TIPUS_PETICIO.put("5_ca", "Plantilla Flux Usuari");
        MAP_TIPUS_PETICIO.put("6_ca", "Plantilla Flux Entitat");
        MAP_TIPUS_PETICIO.put("7_ca", "Flux Simple");
        MAP_TIPUS_PETICIO.put("8_ca", "Flux JSON");
        MAP_TIPUS_PETICIO.put("11_ca", "Gerent / President");
        MAP_TIPUS_PETICIO.put("12_ca", "Cap d´Area / Conseller");
        MAP_TIPUS_PETICIO.put("13_ca", "Cap de Departament / Director General");
        MAP_TIPUS_PETICIO.put("14_ca", "Secretari");
        MAP_TIPUS_PETICIO.put("15_ca", "Encarregat de Compres");
        MAP_TIPUS_PETICIO.put("16_ca", "Recursos Humans");
        MAP_TIPUS_PETICIO.put("17_ca", "Càrrec Addicional 1");
        MAP_TIPUS_PETICIO.put("18_ca", "Càrrec Addicional 2");

        MAP_TIPUS_PETICIO.put("0_es", "NIF");
        MAP_TIPUS_PETICIO.put("1_es", "Autofirma");
        MAP_TIPUS_PETICIO.put("2_es", "Flujo");
        MAP_TIPUS_PETICIO.put("5_es", "Plantilla Flujo Usuario");
        MAP_TIPUS_PETICIO.put("6_es", "Plantilla Flujo Entidad");
        MAP_TIPUS_PETICIO.put("7_es", "Flujo Simple");
        MAP_TIPUS_PETICIO.put("8_es", "Flux JSON");
        MAP_TIPUS_PETICIO.put("11_es", "Gerente / Presidente");
        MAP_TIPUS_PETICIO.put("12_es", "Cap de Area / Consejero");
        MAP_TIPUS_PETICIO.put("13_es", "Jefe de Departamento / Director General");
        MAP_TIPUS_PETICIO.put("14_es", "Secretario");
        MAP_TIPUS_PETICIO.put("15_es", "Encargado de Compras");
        MAP_TIPUS_PETICIO.put("16_es", "Recursos Humanos");
        MAP_TIPUS_PETICIO.put("17_es", "Cargo Adicional 1");
        MAP_TIPUS_PETICIO.put("18_es", "Cargo Adicional 2");

        // 

        MAP_TIPUS_DOCUMENTAL.put("1_ca", "Resolució");
        MAP_TIPUS_DOCUMENTAL.put("2_ca", "Acord");
        MAP_TIPUS_DOCUMENTAL.put("3_ca", "Contracte");
        MAP_TIPUS_DOCUMENTAL.put("4_ca", "Conveni");
        MAP_TIPUS_DOCUMENTAL.put("5_ca", "Declaració");
        MAP_TIPUS_DOCUMENTAL.put("6_ca", "Comunicació");
        MAP_TIPUS_DOCUMENTAL.put("7_ca", "Notificació");
        MAP_TIPUS_DOCUMENTAL.put("8_ca", "Publicació");
        MAP_TIPUS_DOCUMENTAL.put("9_ca", "Justificant de recepció");
        MAP_TIPUS_DOCUMENTAL.put("10_ca", "Acta");
        MAP_TIPUS_DOCUMENTAL.put("11_ca", "Certificat");
        MAP_TIPUS_DOCUMENTAL.put("12_ca", "Diligència");
        MAP_TIPUS_DOCUMENTAL.put("13_ca", "Informe");
        MAP_TIPUS_DOCUMENTAL.put("14_ca", "Sol·licitud");
        MAP_TIPUS_DOCUMENTAL.put("15_ca", "Denúncia");
        MAP_TIPUS_DOCUMENTAL.put("16_ca", "Al·legació");
        MAP_TIPUS_DOCUMENTAL.put("17_ca", "Recursos");
        MAP_TIPUS_DOCUMENTAL.put("18_ca", "Comunicació ciutadà");
        MAP_TIPUS_DOCUMENTAL.put("19_ca", "Factura");
        MAP_TIPUS_DOCUMENTAL.put("20_ca", "Uns altres confiscats");
        MAP_TIPUS_DOCUMENTAL.put("51_ca", "Llei");
        MAP_TIPUS_DOCUMENTAL.put("52_ca", "Moció");
        MAP_TIPUS_DOCUMENTAL.put("53_ca", "Instrucció");
        MAP_TIPUS_DOCUMENTAL.put("54_ca", "Convocatòria");
        MAP_TIPUS_DOCUMENTAL.put("55_ca", "Ordre del dia");
        MAP_TIPUS_DOCUMENTAL.put("56_ca", "Informe de Ponència");
        MAP_TIPUS_DOCUMENTAL.put("57_ca", "Dictamen de Comissió");
        MAP_TIPUS_DOCUMENTAL.put("58_ca", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("59_ca", "Pregunta");
        MAP_TIPUS_DOCUMENTAL.put("60_ca", "Interpel·lació");
        MAP_TIPUS_DOCUMENTAL.put("61_ca", "Resposta");
        MAP_TIPUS_DOCUMENTAL.put("62_ca", "Proposició no de llei");
        MAP_TIPUS_DOCUMENTAL.put("63_ca", "Esmena");
        MAP_TIPUS_DOCUMENTAL.put("64_ca", "Proposada de resolució");
        MAP_TIPUS_DOCUMENTAL.put("65_ca", "Compareixença");
        MAP_TIPUS_DOCUMENTAL.put("66_ca", "Sol·licitud d'informació");
        MAP_TIPUS_DOCUMENTAL.put("67_ca", "Escrit");
        MAP_TIPUS_DOCUMENTAL.put("68_ca", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("69_ca", "Petició");
        MAP_TIPUS_DOCUMENTAL.put("99_ca", "Altres tipus de documents");

        MAP_TIPUS_DOCUMENTAL.put("1_es", "Resolución");
        MAP_TIPUS_DOCUMENTAL.put("2_es", "Acuerdo");
        MAP_TIPUS_DOCUMENTAL.put("3_es", "Contrato");
        MAP_TIPUS_DOCUMENTAL.put("4_es", "Convenio");
        MAP_TIPUS_DOCUMENTAL.put("5_es", "Declaración");
        MAP_TIPUS_DOCUMENTAL.put("6_es", "Comunicación");
        MAP_TIPUS_DOCUMENTAL.put("7_es", "Notificación");
        MAP_TIPUS_DOCUMENTAL.put("8_es", "Publicación");
        MAP_TIPUS_DOCUMENTAL.put("9_es", "Justificante de recepción");
        MAP_TIPUS_DOCUMENTAL.put("10_es", "Acta");
        MAP_TIPUS_DOCUMENTAL.put("11_es", "Certificado");
        MAP_TIPUS_DOCUMENTAL.put("12_es", "Diligencia");
        MAP_TIPUS_DOCUMENTAL.put("13_es", "Informe");
        MAP_TIPUS_DOCUMENTAL.put("14_es", "Solicitud");
        MAP_TIPUS_DOCUMENTAL.put("15_es", "Denuncia");
        MAP_TIPUS_DOCUMENTAL.put("16_es", "Alegación");
        MAP_TIPUS_DOCUMENTAL.put("17_es", "Recursos");
        MAP_TIPUS_DOCUMENTAL.put("18_es", "Comunicación ciudadano");
        MAP_TIPUS_DOCUMENTAL.put("19_es", "Factura");
        MAP_TIPUS_DOCUMENTAL.put("20_es", "Otros incautados");
        MAP_TIPUS_DOCUMENTAL.put("51_es", "Ley");
        MAP_TIPUS_DOCUMENTAL.put("52_es", "Moción");
        MAP_TIPUS_DOCUMENTAL.put("53_es", "Instrucción");
        MAP_TIPUS_DOCUMENTAL.put("54_es", "Convocatoria");
        MAP_TIPUS_DOCUMENTAL.put("55_es", "Orden del día");
        MAP_TIPUS_DOCUMENTAL.put("56_es", "Informe de Ponencia");
        MAP_TIPUS_DOCUMENTAL.put("57_es", "Dictamen de Comisión");
        MAP_TIPUS_DOCUMENTAL.put("58_es", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("59_es", "Pregunta");
        MAP_TIPUS_DOCUMENTAL.put("60_es", "Interpelación");
        MAP_TIPUS_DOCUMENTAL.put("61_es", "Respuesta");
        MAP_TIPUS_DOCUMENTAL.put("62_es", "Proposición no de ley");
        MAP_TIPUS_DOCUMENTAL.put("63_es", "Enmienda");
        MAP_TIPUS_DOCUMENTAL.put("64_es", "Propuesta de resolución");
        MAP_TIPUS_DOCUMENTAL.put("65_es", "Comparecencia");
        MAP_TIPUS_DOCUMENTAL.put("66_es", "Solicitud de información");
        MAP_TIPUS_DOCUMENTAL.put("67_es", "Escrito");
        MAP_TIPUS_DOCUMENTAL.put("68_es", "Iniciativa legislativa");
        MAP_TIPUS_DOCUMENTAL.put("69_es", "Petición");
        MAP_TIPUS_DOCUMENTAL.put("99_es", "Otros tipos de documentos");

    }

    @EJB(mappedName = PeticioLogicaService.JNDI_NAME)
    protected PeticioLogicaService peticioLogicaEjb;

    @Path("/peticionsdefirma")
    @GET
    @RolesAllowed({ Constants.EFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces({ MIME_APPLICATION_JSON })
    @Consumes({ MIME_APPLICATION_JSON })
    @Operation(
            tags = { DadesObertesService.TAG_NAME },
            operationId = "peticionsdefirma",
            summary = "Retorna un llistat amb la informacio de les peticions de firma")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "400",
                    description = "EFIB: Paràmetres incorrectes",
                    content = { @Content(
                            mediaType = MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(
                    responseCode = "401",
                    description = "EFIB: No Autenticat",
                    content = { @Content(
                            mediaType = MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "403",
                    description = "EFIB: No Autoritzat",
                    content = { @Content(
                            mediaType = MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "500",
                    description = "EFIB: Error durant la consulta de les dades obertes",
                    content = { @Content(
                            mediaType = MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(
                    responseCode = "200",
                    description = "EFIB: Retornades dades obertes correctament",
                    content = { @Content(
                            mediaType = MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = PeticioDeFirmaPaginacio.class)) }) })
    public PeticioDeFirmaPaginacio getPeticionsDeFirma(
            @Parameter(
                    description = "Data d'inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "2022-08-29",
                    schema = @Schema(implementation = String.class)) @QueryParam("inici") final String dataIniciRequest,
            @Parameter(
                    description = "Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "2023-12-31",
                    schema = @Schema(implementation = String.class)) @QueryParam("fi") final String dataFiRequest,
            @Parameter(
                    description = "Pàgina de la que es vol obtenir les dades",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "1") @QueryParam("page") Integer page,
            @Parameter(
                    description = "Elements retornats per la pàgina",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "10") @QueryParam("pagesize") Integer pagesize,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "ca",
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(implementation = String.class)) @QueryParam("language") String language)
            throws RestException {

        // Check de page i pagesize
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pagesize == null || pagesize < 1) {
            pagesize = 10;
        }

        // Check de language
        if (StringUtils.isBlank(language)) {
            language = "ca";
        } else {
            if (!"es".equals(language) && !"ca".equals(language)) {
                language = "ca";
            }
        }

        // Convertir Data en format dd/MM/yyyy a tipus Date
        // i check de dates

        Date dateStart = parseDate(dataIniciRequest, "inici");

        Date dateEnd = parseDate(dataFiRequest, "fi");

        if (dateStart == null) {
            Calendar cal = Calendar.getInstance();
            if (dateEnd == null) {
                dateEnd = cal.getTime();
            } else {
                cal.setTime(dateEnd);
            }
            cal.add(Calendar.MONTH, -1);
            dateStart = cal.getTime();
        } else {
            Calendar cal = Calendar.getInstance();
            if (dateEnd == null) {
                cal.setTime(dateStart);
                cal.add(Calendar.MONTH, +1);
                dateEnd = cal.getTime();
            } else {
                // OK Cap dels dos és null
            }
        }

        // Comprovar que la data d'inici és anterior a la de final

        if (dateStart.getTime() >= dateEnd.getTime()) {
            final String msg = "La data d'inici ha de ser menor que la data de fi (" + dataIniciRequest + " | "
                    + dataFiRequest + ")";

            throw new RestException(msg, Status.BAD_REQUEST);

        }

        // Realitzar Consulta
        try {

            final Timestamp from = new Timestamp(RestUtils.atStartOfDay(dateStart).getTime());
            final Timestamp to = new Timestamp(RestUtils.atEndOfDay(dateEnd).getTime());
            final Where w = PeticioFields.DATACREACIO.between(from, to);
            final OrderBy orderBy = new OrderBy(PeticioFields.DATACREACIO, OrderType.DESC);
            final int firstResult = (page - 1) * pagesize;
            final int maxResults = pagesize;
            final List<Peticio> llistat = this.peticioLogicaEjb.select(w, null, firstResult, maxResults, orderBy);
            final List<PeticioDeFirma> peticionsInfo = new ArrayList<PeticioDeFirma>();
            for (final Peticio peticio : llistat) {
                final String nif = peticio.getDestinatariNif();
                final String titol = peticio.getNom();
                final String idiomaCode = peticio.getIdiomaID();
                final String idiomaDescription = MAP_IDIOMA.get(idiomaCode + "_" + language);
                final String dir3 = peticio.getArxiuParamFuncionariDir3();
                
                
                final int tipusPeticioCode = peticio.getTipus();
                final String tipusPeticioDescription = MAP_TIPUS_PETICIO.get(peticio.getTipus() + "_" + language);
                
                String tipusDocumentalCode = peticio.getTipusDocumental();
                
                final String tipusDocumentalDescription = MAP_TIPUS_DOCUMENTAL.get(tipusDocumentalCode + "_" + language);
                
                tipusDocumentalCode = "TD" + (tipusDocumentalCode.length() == 1 ? "0" : "") + tipusDocumentalCode;
                
                final Timestamp creada =  peticio.getDataCreacio();
                final Timestamp finalitzada = peticio.getDataFinal();
                
                final PeticioDeFirma p = new PeticioDeFirma(nif, titol, creada, finalitzada, idiomaCode, idiomaDescription,
                        tipusDocumentalCode, tipusDocumentalDescription, tipusPeticioCode, tipusPeticioDescription, dir3);
                peticionsInfo.add(p);
            }

            long countTotal = this.peticioLogicaEjb.count(w);

            // PAGINACIO
            final int pageSizeOutput = pagesize;
            final int pageOutput = page;
            final int totalPages = (int) (countTotal / pagesize) + ((countTotal % pagesize == 0) ? 0 : 1);

            PeticioDeFirmaPaginacio paginacio = new PeticioDeFirmaPaginacio(pageSizeOutput, pageOutput, totalPages,
                    (int) countTotal, peticionsInfo);

            return paginacio;
        } catch (Throwable th) {

            String msg;
            if (th instanceof I18NException) {
                msg = I18NCommonUtils.getMessage((I18NException) th, new Locale(language));
            } else {
                msg = th.getMessage();
            }
            this.log.error("Error desconegut retornant dades obertes: " + msg, th);
            throw new RestException(msg, th, Status.INTERNAL_SERVER_ERROR);
        }
    }

    @Path("/tipusdocumentals")
    @GET
    @RolesAllowed({ Constants.EFI_WS })
    @SecurityRequirement(name = SECURITY_NAME)
    @Produces(RestUtils.MIME_APPLICATION_JSON)
    @Consumes(RestUtils.MIME_APPLICATION_JSON)
    @Operation(
            tags = { DadesObertesService.TAG_NAME },
            operationId = "tipusdocumentals",
            summary = "Retorna un llistat dels tipus documentals")
    @ApiResponses({ 
            @ApiResponse(
                    responseCode = "401",
                    description = "EFIB: No Autenticat",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "403",
                    description = "EFIB: No Autoritzat",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = String.class)) }),
            @ApiResponse(
                    responseCode = "500",
                    description = "EFIB: Error durant la consulta de les dades obertes",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = RestExceptionInfo.class)) }),
            @ApiResponse(
                    responseCode = "200",
                    description = "EFIB: Retornades dades obertes correctament",
                    content = { @Content(
                            mediaType = RestUtils.MIME_APPLICATION_JSON,
                            schema = @Schema(implementation = TipusDocumentalsPaginacio.class)) }) })
    public TipusDocumentalsPaginacio getTipusDocumentals(
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "ca",
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(implementation = String.class)) @QueryParam("language") String language) {

        if (StringUtils.isBlank(language)) {
            language = "ca";
        } else {
            if (!"es".equals(language) && !"ca".equals(language)) {
                language = "ca";
            }
        }

        List<String> tipusDocumentals = new ArrayList<String>();

        for (Map.Entry<String, String> entry : MAP_TIPUS_DOCUMENTAL.entrySet()) {
            if (entry.getKey().endsWith(language)) {
                tipusDocumentals.add(entry.getValue());
            }
        }

        final int pageSizeOutput = tipusDocumentals.size();
        final int pageOutput = 1;
        final int totalPages = 1;
        final int totalcount = pageSizeOutput;

        TipusDocumentalsPaginacio dades = new TipusDocumentalsPaginacio(pageSizeOutput, pageOutput, totalPages,
                totalcount, tipusDocumentals);

        return dades;
    }


}
