package es.caib.enviafib.api.interna.secure.dadesobertes;

import javax.annotation.security.RolesAllowed;

import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.OrderType;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.pluginsib.utils.rest.GenAppEntityConverter;
import org.fundaciobit.pluginsib.utils.rest.GenAppRangeOfDates;
import org.fundaciobit.pluginsib.utils.rest.GenAppRestUtils;
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
import java.util.Map;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.PeticioLogicaService;
import es.caib.enviafib.model.fields.PeticioFields;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.QueryParam;
import javax.ws.rs.GET;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.Path;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;

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
@Path(DadesObertesService.PATH)
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

    public static final String PATH = "/secure/dadesobertes";

    // No modificar !!!
    protected static final String TAG_NAME = "DadesObertesEnviaFib";

    protected static final String SECURITY_NAME = "BasicAuth";

    protected static final int DEFAULT_PAGESIZE = 10;

    protected Logger log = Logger.getLogger(DadesObertesService.class);

    protected static final Map<String, String> MAP_TIPUS_PETICIO = new HashMap<String, String>();

    protected static final Map<String, List<TipusDocumental>> MAP_TIPUS_DOCUMENTAL_BY_LANG = new HashMap<String, List<TipusDocumental>>();

    protected static final Map<String, Map<String, String>> MAP_TIPUS_DOCUMENTAL_BY_CODE = new HashMap<String, Map<String, String>>();

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

        {
            List<TipusDocumental> llista = new ArrayList<TipusDocumental>();
            llista.add(new TipusDocumental("TD01", "Resolució"));
            llista.add(new TipusDocumental("TD02", "Acord"));
            llista.add(new TipusDocumental("TD03", "Contracte"));
            llista.add(new TipusDocumental("TD04", "Conveni"));
            llista.add(new TipusDocumental("TD05", "Declaració"));
            llista.add(new TipusDocumental("TD06", "Comunicació"));
            llista.add(new TipusDocumental("TD07", "Notificació"));
            llista.add(new TipusDocumental("TD08", "Publicació"));
            llista.add(new TipusDocumental("TD09", "Justificant de recepció"));
            llista.add(new TipusDocumental("TD10", "Acta"));
            llista.add(new TipusDocumental("TD11", "Certificat"));
            llista.add(new TipusDocumental("TD12", "Diligència"));
            llista.add(new TipusDocumental("TD13", "Informe"));
            llista.add(new TipusDocumental("TD14", "Sol·licitud"));
            llista.add(new TipusDocumental("TD15", "Denúncia"));
            llista.add(new TipusDocumental("TD16", "Al·legació"));
            llista.add(new TipusDocumental("TD17", "Recursos"));
            llista.add(new TipusDocumental("TD18", "Comunicació ciutadà"));
            llista.add(new TipusDocumental("TD19", "Factura"));
            llista.add(new TipusDocumental("TD20", "Uns altres confiscats"));
            llista.add(new TipusDocumental("TD51", "Llei"));
            llista.add(new TipusDocumental("TD52", "Moció"));
            llista.add(new TipusDocumental("TD53", "Instrucció"));
            llista.add(new TipusDocumental("TD54", "Convocatòria"));
            llista.add(new TipusDocumental("TD55", "Ordre del dia"));
            llista.add(new TipusDocumental("TD56", "Informe de Ponència"));
            llista.add(new TipusDocumental("TD57", "Dictamen de Comissió"));
            llista.add(new TipusDocumental("TD58", "Iniciativa legislativa"));
            llista.add(new TipusDocumental("TD59", "Pregunta"));
            llista.add(new TipusDocumental("TD60", "Interpel·lació"));
            llista.add(new TipusDocumental("TD61", "Resposta"));
            llista.add(new TipusDocumental("TD62", "Proposició no de llei"));
            llista.add(new TipusDocumental("TD63", "Esmena"));
            llista.add(new TipusDocumental("TD64", "Proposada de resolució"));
            llista.add(new TipusDocumental("TD65", "Compareixença"));
            llista.add(new TipusDocumental("TD66", "Sol·licitud d'informació"));
            llista.add(new TipusDocumental("TD67", "Escrit"));
            llista.add(new TipusDocumental("TD68", "Iniciativa legislativa"));
            llista.add(new TipusDocumental("TD69", "Petició"));
            llista.add(new TipusDocumental("TD99", "Altres tipus de documents"));

            MAP_TIPUS_DOCUMENTAL_BY_LANG.put("ca", llista);

            Map<String, String> code = new HashMap<String, String>();
            for (TipusDocumental tipusDocumental : llista) {
                code.put(tipusDocumental.getCode(), tipusDocumental.getName());
            }

            MAP_TIPUS_DOCUMENTAL_BY_CODE.put("ca", code);

        }

        {
            List<TipusDocumental> llista = new ArrayList<TipusDocumental>();

            llista.add(new TipusDocumental("TD01", "Resolución"));
            llista.add(new TipusDocumental("TD02", "Acuerdo"));
            llista.add(new TipusDocumental("TD03", "Contrato"));
            llista.add(new TipusDocumental("TD04", "Convenio"));
            llista.add(new TipusDocumental("TD05", "Declaración"));
            llista.add(new TipusDocumental("TD06", "Comunicación"));
            llista.add(new TipusDocumental("TD07", "Notificación"));
            llista.add(new TipusDocumental("TD08", "Publicación"));
            llista.add(new TipusDocumental("TD09", "Justificante de recepción"));
            llista.add(new TipusDocumental("TD10", "Acta"));
            llista.add(new TipusDocumental("TD11", "Certificado"));
            llista.add(new TipusDocumental("TD12", "Diligencia"));
            llista.add(new TipusDocumental("TD13", "Informe"));
            llista.add(new TipusDocumental("TD14", "Solicitud"));
            llista.add(new TipusDocumental("TD15", "Denuncia"));
            llista.add(new TipusDocumental("TD16", "Alegación"));
            llista.add(new TipusDocumental("TD17", "Recursos"));
            llista.add(new TipusDocumental("TD18", "Comunicación ciudadano"));
            llista.add(new TipusDocumental("TD19", "Factura"));
            llista.add(new TipusDocumental("TD20", "Otros incautados"));
            llista.add(new TipusDocumental("TD51", "Ley"));
            llista.add(new TipusDocumental("TD52", "Moción"));
            llista.add(new TipusDocumental("TD53", "Instrucción"));
            llista.add(new TipusDocumental("TD54", "Convocatoria"));
            llista.add(new TipusDocumental("TD55", "Orden del día"));
            llista.add(new TipusDocumental("TD56", "Informe de Ponencia"));
            llista.add(new TipusDocumental("TD57", "Dictamen de Comisión"));
            llista.add(new TipusDocumental("TD58", "Iniciativa legislativa"));
            llista.add(new TipusDocumental("TD59", "Pregunta"));
            llista.add(new TipusDocumental("TD60", "Interpelación"));
            llista.add(new TipusDocumental("TD61", "Respuesta"));
            llista.add(new TipusDocumental("TD62", "Proposición no de ley"));
            llista.add(new TipusDocumental("TD63", "Enmienda"));
            llista.add(new TipusDocumental("TD64", "Propuesta de resolución"));
            llista.add(new TipusDocumental("TD65", "Comparecencia"));
            llista.add(new TipusDocumental("TD66", "Solicitud de información"));
            llista.add(new TipusDocumental("TD67", "Escrito"));
            llista.add(new TipusDocumental("TD68", "Iniciativa legislativa"));
            llista.add(new TipusDocumental("TD69", "Petición"));
            llista.add(new TipusDocumental("TD99", "Otros tipos de documentos"));

            MAP_TIPUS_DOCUMENTAL_BY_LANG.put("es", llista);

            Map<String, String> code = new HashMap<String, String>();
            for (TipusDocumental tipusDocumental : llista) {
                code.put(tipusDocumental.getCode(), tipusDocumental.getName());
            }

            MAP_TIPUS_DOCUMENTAL_BY_CODE.put("es", code);
        }

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
    public PeticioDeFirmaPaginacio getPeticionsDeFirma(@Parameter(
            name = "startdate",
            description = "Data d'inici, en format yyyy-MM-dd (ISO 8601), a partir de la qual volem obtenir dades",
            in = ParameterIn.QUERY,
            required = false,
            example = "2022-08-29",
            schema = @Schema(
                    implementation = String.class,
                    pattern = DATE_PATTERN_ISO8601_ONLYDATE)) @QueryParam("startdate") final String startdate,
            @Parameter(
                    name = "enddate",
                    description = "Data fi, en format yyyy-MM-dd (ISO 8601), fins la qual volem tenir dades",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "2023-12-31",
                    schema = @Schema(
                            implementation = String.class,
                            pattern = DATE_PATTERN_ISO8601_ONLYDATE)) @QueryParam("enddate") final String enddate,
            @Parameter(
                    name = "page",
                    description = "Pàgina de la que es vol obtenir les dades. Comença en 1.",
                    in = ParameterIn.QUERY,
                    required = false,
                    example = "1") @QueryParam("page") Integer page,
            @Parameter(
                    name = "page-size",
                    description = "Número d'elements a retornar per pàgina. Opcional. Per defecte " + DEFAULT_PAGESIZE,
                    required = false,
                    in = ParameterIn.QUERY,
                    schema = @Schema(implementation = Integer.class)) @QueryParam("page-size") Integer pagesize,
            @Parameter(
                    name = "language",
                    description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
                    in = ParameterIn.QUERY,
                    required = false,
                    examples = { @ExampleObject(name = "Català", value = "ca"),
                            @ExampleObject(name = "Castellano", value = "es") },
                    schema = @Schema(
                            defaultValue = "ca",
                            implementation = String.class)) @QueryParam("language") String language,
            @Parameter(hidden = true) @Context HttpServletRequest request) throws RestException {

        // Check de page i pagesize
        if (page == null || page <= 0) {
            page = 1;
        }
        if (pagesize == null || pagesize < 1) {
            pagesize = DEFAULT_PAGESIZE;
        }

        StringBuilder nextQuery = new StringBuilder(
                ((Configuracio.getUrlBase().replace("enviafibback", "") + request.getContextPath()).replace("//", "/"))
                        + PATH + "/peticionsdefirma?" + "page=" + (int) (page + 1) + "&pagesize=" + pagesize);

        // Check de language
        language = checkLanguage(language);

        // Convertir Data en format dd/MM/yyyy a tipus Date
        // i check de dates
        GenAppRangeOfDates grod = GenAppRestUtils.checkRangeOfOnlyDates(startdate, "startdate", enddate, "enddate",
                PeticioFields.DATACREACIO, nextQuery, language);

        // Realitzar Consulta
        try {

            final Where w = grod.getWhere();

            Map<String, String> mapTD = MAP_TIPUS_DOCUMENTAL_BY_CODE.get(language);
            if (mapTD == null) {
                mapTD = MAP_TIPUS_DOCUMENTAL_BY_CODE.get("ca");
            }

            final OrderBy orderBy = new OrderBy(PeticioFields.DATACREACIO, OrderType.DESC);

            PeticioToPeticioDeFirmaConverter converter = new PeticioToPeticioDeFirmaConverter(mapTD, language);

            final String name;
            if ("es".equals(language)) {
                name = "Lista parcial de peticiones de firma creadas por EnviaFIB.";
            } else {
                name = "Llista parcial de peticions de Firma creades per EnviaFIB.";
            }

            PeticioDeFirmaPaginacio paginacio = GenAppRestUtils.createRestPagination(PeticioDeFirmaPaginacio.class,
                    this.peticioLogicaEjb, (int) page, (int) pagesize, w, orderBy, converter, name, nextQuery);

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

    /**
     * 
     * @author anadal
     *
     */
    public class PeticioToPeticioDeFirmaConverter implements GenAppEntityConverter<Peticio, PeticioDeFirma> {

        protected final Map<String, String> mapTD;

        protected final String language;

        public PeticioToPeticioDeFirmaConverter(Map<String, String> mapTD, String language) {
            super();
            this.mapTD = mapTD;
            this.language = language;
        }

        @Override
        public PeticioDeFirma convert(Peticio peticio) throws RestException {
            final String nif = peticio.getDestinatariNif();
            final String titol = peticio.getNom();
            final String idiomaCode = peticio.getIdiomaID();
            final String idiomaDescription = MAP_IDIOMA.get(idiomaCode + "_" + language);
            final String dir3 = peticio.getArxiuParamFuncionariDir3();

            final int tipusPeticioCode = peticio.getTipus();
            final String tipusPeticioDescription = MAP_TIPUS_PETICIO.get(peticio.getTipus() + "_" + language);

            String tipusDocumentalCode = peticio.getTipusDocumental();
            tipusDocumentalCode = "TD" + (tipusDocumentalCode.length() == 1 ? "0" : "") + tipusDocumentalCode;

            final String tipusDocumentalDescription = mapTD.get(tipusDocumentalCode);

            final Timestamp creada = peticio.getDataCreacio();
            final Timestamp finalitzada = peticio.getDataFinal();

            return new PeticioDeFirma(nif, titol, creada, finalitzada, idiomaCode, idiomaDescription,
                    tipusDocumentalCode, tipusDocumentalDescription, tipusPeticioCode, tipusPeticioDescription, dir3);
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
            summary = "Retorna un llistat de tots els tipus documentals")
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
                            schema = @Schema(implementation = TipusDocumentalsAllElements.class)) }) })
    public TipusDocumentalsAllElements getTipusDocumentals(@Parameter(
            name = "language",
            description = "Idioma en que s'han de retornar les dades(Només suportat 'ca' o 'es')",
            in = ParameterIn.QUERY,
            required = false,
            examples = { @ExampleObject(name = "Català", value = "ca"),
                    @ExampleObject(name = "Castellano", value = "es") },
            schema = @Schema(
                    defaultValue = "ca",
                    implementation = String.class)) @QueryParam("language") String language) {

        language = checkLanguage(language);

        List<TipusDocumental> tipusDocumentals = MAP_TIPUS_DOCUMENTAL_BY_LANG.get(language);
        if (tipusDocumentals == null) {
            tipusDocumentals = MAP_TIPUS_DOCUMENTAL_BY_LANG.get("ca");
        }

        final String dateDownload = convertDateToDateTimeISO8601(new Date());

        final String name;
        if ("es".equals(language)) {
            name = "Lista de todos los Tipos Documentales de EnviaFIB.";
        } else {
            name = "Llista de tots els Tipus Documentals d´EnviaFIB.";
        }

        TipusDocumentalsAllElements dades = new TipusDocumentalsAllElements(tipusDocumentals, tipusDocumentals.size(),
                dateDownload, name);

        return dades;
    }

}
