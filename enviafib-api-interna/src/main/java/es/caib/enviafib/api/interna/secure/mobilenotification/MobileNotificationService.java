package es.caib.enviafib.api.interna.secure.mobilenotification;

//import java.sql.Timestamp;
//import java.text.MessageFormat;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Locale;
//import java.util.Map;
//
//import javax.ejb.EJB;
//import javax.validation.constraints.NotEmpty;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.GET;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.QueryParam;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;
//import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
//import org.fundaciobit.genapp.common.i18n.I18NException;
//import org.fundaciobit.genapp.common.query.Where;

//import io.swagger.v3.oas.annotations.OpenAPIDefinition;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
//import io.swagger.v3.oas.annotations.tags.Tag;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.media.ArraySchema;
//import io.swagger.v3.oas.annotations.media.Content;

/**
 *
 * @author anadal
 *
 */
//@Path("/secure/mobilenotification")
//@OpenAPIDefinition(tags = @Tag(name = "Notificacions", description = "Notificacions a l'APP de Carpeta (missateg a Mòbil)"))
//@Produces(MediaType.APPLICATION_JSON)
//@Consumes(MediaType.APPLICATION_JSON)
//@SecurityScheme(type = SecuritySchemeType.HTTP, name = "BasicAuth", scheme = "basic")
public class MobileNotificationService {

    protected Logger log = Logger.getLogger(MobileNotificationService.class);
//
//    @EJB(mappedName = CiutadaLogicaService.JNDI_NAME)
//    protected CiutadaLogicaService ciutadaLogicaEjb;
//
//    @EJB(mappedName = NotificacioAppLogicaService.JNDI_NAME)
//    protected NotificacioAppLogicaService notificacioLogicaEjb;
//
//    @EJB(mappedName = es.caib.carpeta.ejb.PluginService.JNDI_NAME)
//    protected es.caib.carpeta.ejb.PluginService pluginEjb;
//
//    @EJB(mappedName = EntitatLogicaService.JNDI_NAME)
//    protected EntitatLogicaService entitatLogicaEjb;
//
//    @EJB(mappedName = PluginEntitatLogicaService.JNDI_NAME)
//    protected PluginEntitatLogicaService pluginEntitatEjb;
//
//    @EJB(mappedName = es.caib.carpeta.ejb.EstadisticaService.JNDI_NAME)
//    protected es.caib.carpeta.ejb.EstadisticaService estadisticaEjb;
//
//    @EJB(mappedName = es.caib.carpeta.ejb.SeccioService.JNDI_NAME)
//    protected es.caib.carpeta.ejb.SeccioService seccioEjb;
//
//    @GET
//    @Path("/sendnotificationtomobile")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Operation(tags = {
//            "Notificacions" }, operationId = "sendNotificationToMobile", summary = "Envia un missatge al mòbil del ciutada a traves de l'App de Carpeta.")
//    @SecurityRequirement(name = "BasicAuth")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "401", description = "No Autenticat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "403", description = "No Autoritzat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "400", description = "Error durant el processament o enviament del missatge", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SendMessageResult.class))),
//            @ApiResponse(responseCode = "200", description = "Enviat missatge correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = SendMessageResult.class))) })
//    public Response sendNotificationToMobile(
//            @Parameter(description = "NIF del Ciutadà o l'empresa", required = true, example = "12345678Z", schema = @Schema(implementation = String.class)) @NotEmpty @Size(min = 7, max = 20) @QueryParam("nif") String nif,
//
//            @Parameter(description = "Codi de la notificació. Demanar a l'administrador de Carpeta.", schema = @Schema(implementation = String.class), required = true, example = "CODENAME") @NotNull @QueryParam("notificationCode") String notificationCode,
//
//            @Parameter(description = "Paràmetres associats al Codi de la notificació", required = true, example = "", array = @ArraySchema(schema = @Schema(type = "string"))) @NotNull @QueryParam("notificationParameters") String[] notificationParameters,
//
//            @Parameter(description = "Idioma en que s'enviaran les notificacion", required = true, example = "ca", schema = @Schema(implementation = String.class)) @Pattern(regexp = "^ca|es$") @QueryParam("notificationLang") String notificationLang,
//
//            @Parameter(description = "Idioma en que s'enviaran els missatges d'error", required = true, example = "ca", schema = @Schema(implementation = String.class)) @Pattern(regexp = "^ca|es$") @QueryParam("langError") String langError) {
//
//        final long start = System.currentTimeMillis();
//        try {
//
//            // Check if notificationCode is empty
//            if (notificationCode == null || notificationCode.trim().length() == 0) {
//                // TODO XYZ ZZZ
//                return generateError(SendMessageResultCode.NOTIFICATION_CODE_DO_NOT_EXIST,
//                        "El codi de notificacio és null o buit. ");
//            }
//
//            // Check if notificationCode exists
//            List<NotificacioApp> nList = notificacioLogicaEjb.select(NotificacioAppFields.CODI.equal(notificationCode));
//            if (nList.size() != 1) {
//                // TODO XYZ ZZZ
//                return generateError(SendMessageResultCode.NOTIFICATION_CODE_DO_NOT_EXIST, "El codi de notificacio "
//                        + notificationCode + " no està registrat." + "Consulti amb l'administrador de Carpeta.");
//            }
//
//            // Check if Entity exists
//
//            NotificacioAppJPA notificacio = (NotificacioAppJPA) nList.get(0);
//            Long pluginID = notificacio.getFrontPluginID();
//
//            Entitat entitat = entitatLogicaEjb.findByPrimaryKey(notificacio.getEntitatID());
//
//            if (entitat == null) {
//                // TODO XYZ ZZZ
//                return generateError(SendMessageResultCode.ENTITYCODE_DO_NOT_EXIST,
//                        "No existeix cap entitat dins Carpeta amb ID  `" + notificacio.getEntitatID()
//                                + "`. Consulti amb l'administrador de Carpeta.");
//            }
//
//            if (!entitat.isActiva()) {
//                // TODO XYZ ZZZ 
//                return generateError(SendMessageResultCode.ENTITY_DISABLED,
//                        "No existeix cap entitat dins Carpeta amb codi `" + entitat.getCodi()
//                                + "`. Consulti amb l'administrador de Carpeta.");
//            }
//
//            PluginEntitat pluginEntitat = null;
//            PluginJPA plugin = null;
//
//            if (pluginID != null) {
//
//                // Check if Plugin is enabled
//                plugin = pluginEjb.findByPrimaryKey(pluginID);
//                if (!plugin.isActiu()) {
//                    // TODO XYZ ZZZ 
//                    return generateError(SendMessageResultCode.PLUGIN_DISABLED,
//                            "El plugin `" + plugin.getNom().getTraduccio(langError)
//                                    + " associat al codi de notificació  `" + notificationCode
//                                    + "` no està actiu. Consulti amb l'administrador de Carpeta.");
//                }
//
//                Where w1 = PluginEntitatFields.ENTITATID.equal(entitat.getEntitatID());
//                Where w2 = PluginEntitatFields.PLUGINID.equal(pluginID);
//                List<PluginEntitat> listPE = pluginEntitatEjb.select(Where.AND(w1, w2));
//
//                if (listPE.size() != 1) {
//                    // TODO XYZ ZZZ 
//                    return generateError(SendMessageResultCode.PLUGIN_ENTITY_DO_NOT_EXIST,
//                            "El plugin `" + plugin.getNom().getTraduccio(langError)
//                                    + " associat al codi de notificació  `" + notificationCode
//                                    + "` no existeix en l'entitat `" + entitat.getCodi()
//                                    + "`. Consulti amb l'administrador de Carpeta.");
//                }
//
//                pluginEntitat = listPE.get(0);
//
//                if (!pluginEntitat.isActiu()) {
//                    // TODO XYZ ZZZ
//                    return generateError(SendMessageResultCode.PLUGIN_ENTITY_DISABLED,
//                            "El plugin `" + plugin.getNom().getTraduccio(langError)
//                                    + " associat al codi de notificació  `" + notificationCode + "` en l'entitat `"
//                                    + entitat.getCodi() + "` no està actiu. Consulti amb l'administrador de Carpeta.");
//                }
//            }
//
//            // Check mobile of user
//            String mobileID = getMobileIdOfCiutada(nif);
//            if (mobileID == null) {
//                // TODO XYZ ZZZ 
//                return generateError(SendMessageResultCode.CITIZEN_DO_NOT_EXIST,
//                        "No es té registrat el mòbil del ciutada/empresa amb NIF " + nif);
//            }
//
//            // Process title and subtitle
//            Object[] parametres = toObjectParameters(notificationParameters);
//
//            String title = notificacio.getTitol().getTraduccio(notificationLang).getValor();
//            String titol = MessageFormat.format(title, parametres);
//
//            String message = notificacio.getMissatge().getTraduccio(notificationLang).getValor();
//            String missatge = MessageFormat.format(message, parametres);
//
//            Map<String, Object> data = new HashMap<String, Object>();
//
//            if (pluginEntitat == null) {
//                data.put("action", "NONE");
//            } else {
//                data.put("action", "SHOWPLUGIN");
//                boolean ispublic = !(plugin.getTipus() == Constants.PLUGIN_TIPUS_FRONT_PRIVAT);
//                String seccio = seccioEjb.executeQueryOne(SeccioFields.CONTEXTE,
//                        SeccioFields.SECCIOID.equal(pluginEntitat.getSeccioID()));
//
//                String url = "/#";
//                if (seccio != null) {
//                    url = url + "/seccio/" + seccio;
//                }
//
//                if (ispublic) {
//                    url = url + "/publicmodul/";
//                } else {
//                    url = url + "/modul/";
//                }
//
//                data.put("url", url + plugin.getContext());
//                data.put("ispublic", ispublic);
//            }
//
//            // Send message
//            SendNotificationResult snr;
//            snr = SendNotificationToMobile.sendMessageToMobile(mobileID, titol, missatge, data);
//            if (snr.isEstatEnviat() && snr.isEstatRebut()) {
//
//                final int elapsed = (int) (System.currentTimeMillis() - start);
//                try {
//                    EstadisticaJPA est = new EstadisticaJPA(Constants.TIPUS_ESTADISTICA_ENVIADA_NOTIFICACIO_MOBIL,
//                            new Timestamp(System.currentTimeMillis()), elapsed,
//                            pluginEntitat == null ? null : pluginEntitat.getPluginID(), entitat.getEntitatID());
//                    estadisticaEjb.create(est);
//                } catch (Throwable th) {
//                    log.error("Error crean Estadistiques de Enviada Notificacio a Mòbil: " + th.getMessage(), th);
//
//                }
//
//                SendMessageResult smr = new SendMessageResult();
//                smr.setCode(SendMessageResultCode.OK);
//                return Response.ok().entity(smr).build();
//            } else {
//                return generateError(SendMessageResultCode.ERROR_SENDING_NOTIFICATION, snr.toString());
//            }
//
//        } catch (Throwable th) {
//
//            String msg;
//            if (th instanceof I18NException) {
//                I18NException ie = (I18NException) th;
//                msg = I18NCommonUtils.getMessage(ie, new Locale(langError));
//            } else {
//                msg = th.getMessage();
//            }
//
//            log.error("Error desconegut en la cridada api rest enviar notificacions: " + msg, th);
//
//            return generateError(SendMessageResultCode.UNKNOWN_ERROR, msg);
//
//        }
//
//    }
//
//    protected Object[] toObjectParameters(String[] parameters) {
//        if (parameters == null) {
//            return null;
//        }
//        Object[] objs = new Object[parameters.length];
//        for (int i = 0; i < parameters.length; i++) {
//            objs[i] = parameters[i];
//        }
//        return objs;
//    }
//
//    protected Response generateError(SendMessageResultCode errorCode, String msg) {
//        SendMessageResult smr = new SendMessageResult();
//        smr.setCode(errorCode);
//        smr.setMessage(msg);
//        return Response.status(Response.Status.BAD_REQUEST).entity(smr).build();
//    }
//
//    protected Response generateError(String errorMsg) {
//        return Response.status(Response.Status.BAD_REQUEST).entity(errorMsg).build();
//    }
//
//    protected String getMobileIdOfCiutada(String nif) throws I18NException {
//        Where w1 = CiutadaFields.NIF.equal(nif);
//        Where w2 = CiutadaFields.REPRESENTANTNIF.isNull();
//
//        String mobileID = ciutadaLogicaEjb.executeQueryOne(CiutadaFields.MOBILEID, Where.AND(w1, w2));
//
//        if (mobileID == null) {
//            Where w3 = CiutadaFields.REPRESENTANTNIF.isNotNull();
//            mobileID = ciutadaLogicaEjb.executeQueryOne(CiutadaFields.MOBILEID, Where.AND(w1, w3));
//        }
//        return mobileID;
//    }
//
//    @Operation(tags = {
//            "Notificacions" }, operationId = "existCitizen", summary = "Consulta si tenim donat d'alta el mòbil d'un ciutadà/empresa a partir del seu NIF.")
//    @SecurityRequirement(name = "BasicAuth")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "401", description = "No Autenticat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "403", description = "No Autoritzat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "200", description = "Consulta finalitzada. Retorna true si existeix el ciutadà/empresa o false en cas contrari.", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = Boolean.class))) })
//    @GET
//    @Path("/existcitizen")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    public Response existCitizen(
//            @Parameter(description = "NIF del Ciutadà o l'empresa", required = true, example = "12345678Z", schema = @Schema(implementation = String.class)) @NotEmpty @Size(min = 7, max = 20) @QueryParam("nif") String nif,
//
//            @Parameter(description = "Codi de l'idioma", required = true, example = "ca", schema = @Schema(implementation = String.class)) @Pattern(regexp = "^ca|es$") @QueryParam("lang") String lang) {
//
//        try {
//            String mobileID = getMobileIdOfCiutada(nif);
//
//            if (mobileID == null) {
//                return Response.ok().entity(Boolean.FALSE).build();
//            } else {
//                return Response.ok().entity(Boolean.TRUE).build();
//            }
//        } catch (Throwable th) {
//
//            String msg;
//            if (th instanceof I18NException) {
//                I18NException ie = (I18NException) th;
//                msg = I18NCommonUtils.getMessage(ie, new Locale(lang));
//            } else {
//                msg = th.getMessage();
//            }
//
//            log.error("Error cridada api rest consulta de ciutadà/empresa: " + msg, th);
//            return Response.status(Response.Status.BAD_REQUEST).entity("{ \"error\" : " + "\"" + msg + "\" }").build();
//        }
//
//    }
//
//    @GET
//    @Path("/help")
//    @Produces(MediaType.APPLICATION_JSON)
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Operation(tags = {
//            "Notificacions" }, operationId = "help", summary = "Envia un missatge al mòbil del ciutada a traves de l'App de Carpeta.")
//    @SecurityRequirement(name = "BasicAuth")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "401", description = "No Autenticat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "403", description = "No Autoritzat", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
//            @ApiResponse(responseCode = "400", description = "Error", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))),
//            @ApiResponse(responseCode = "200", description = "Enviada ajuda correctament", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = String.class))) })
//    public Response help(
//
//            @Parameter(description = "Codi de la notificació. Demanar a l'administrador de Carpeta.", schema = @Schema(implementation = String.class), required = true, example = "CODENAME") @NotNull @QueryParam("notificationCode") String notificationCode,
//            @Parameter(description = "Idioma en que s'enviaran els missatges d'error", required = true, example = "ca", schema = @Schema(implementation = String.class)) @Pattern(regexp = "^ca|es$") @QueryParam("langError") String langError) {
//
//        try {
//
//            // Check if notificationCode is empty
//            if (notificationCode == null || notificationCode.trim().length() == 0) {
//                // TODO XYZ ZZZ 
//                return generateError("El codi de notificacio és null o buit. ");
//            }
//
//            // Check if notificationCode exists
//            List<NotificacioApp> nList = notificacioLogicaEjb.select(NotificacioAppFields.CODI.equal(notificationCode));
//            if (nList.size() != 1) {
//                // TODO XYZ ZZZ 
//                return generateError("El codi de notificacio " + notificationCode + " no està registrat."
//                        + "Consulti amb l'administrador de Carpeta.");
//            }
//
//            NotificacioAppJPA notificacio = (NotificacioAppJPA) nList.get(0);
//
//            String[] idiomes = new String[] { "ca", "es" };
//
//            StringBuffer result = new StringBuffer();
//
//            for (String i : idiomes) {
//                result.append(notificacio.getTitol().getTraduccio(i).getValor()).append("\n");
//            }
//
//            for (String i : idiomes) {
//                result.append(notificacio.getMissatge().getTraduccio(i).getValor()).append("\n");
//            }
//
//            result.append("\n");
//            result.append(notificacio.getAjuda());
//
//            return Response.ok().entity(result.toString()).build();
//
//        } catch (Throwable th) {
//
//            String msg;
//            if (th instanceof I18NException) {
//                I18NException ie = (I18NException) th;
//                msg = I18NCommonUtils.getMessage(ie, new Locale(langError));
//            } else {
//                msg = th.getMessage();
//            }
//
//            log.error("Error desconegut en la cridada api rest enviar notificacions: " + msg, th);
//
//            return generateError(msg);
//
//        }
//
//    }

}
