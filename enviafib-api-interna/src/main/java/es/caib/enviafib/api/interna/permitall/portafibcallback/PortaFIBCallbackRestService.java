package es.caib.enviafib.api.interna.permitall.portafibcallback;

import java.util.Locale;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.utils.ConstantsV2;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

/**
 * 
 * @author fbosch
 * @author ptrias
 * 
 */
@Path("/public/cbrest/v1")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@OpenAPIDefinition(tags = @Tag(name = "Callback", description = "Callback de PortaFIB"))
public class PortaFIBCallbackRestService {

    protected static final Logger log = Logger.getLogger(PortaFIBCallbackRestService.class);

    @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

    @Operation(tags = "Callback", operationId = "event", summary = "Reb l'event de portafib realitza les accions corresponents", method = "post")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Paràmetres incorrectes", content = @Content(mediaType = MediaType.APPLICATION_JSON)),
            @ApiResponse(responseCode = "200", description = "Callback PortaFIB", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(implementation = PortaFIBEvent.class))) })

    @GET
    @Path("/versio")
    public String getVersio() {
        log.info("URL de CallBack Validada");
        return "1";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/event")
    public Response event(@RequestBody PortaFIBEvent event) {
        try {
            long startTime = System.currentTimeMillis();

            int eventID = event.getEventTypeID();

            switch (eventID) {
                case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR: {
                    log.info("NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR: {
                    log.info("NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR: {
                    log.info("NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR: {
                    log.info("NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_VALIDAT: {
                    log.info("NOTIFICACIOAVIS_VALIDAT = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_INVALIDAT: {
                    log.info("NOTIFICACIOAVIS_INVALIDAT = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL: {
                    log.info("NOTIFICACIOAVIS_FIRMA_PARCIAL = " + eventID);
                }
                break;

                case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA: {
                    log.info("NOTIFICACIOAVIS_PETICIO_FIRMADA = " + eventID);

                    String languageUI = "ca";
                    Long portafibID = event.getSigningRequest().getID();

                    InfoSignatura infoSignatura;
                    infoSignatura = peticioLogicaEjb.cosesAFerPeticioFirmadaPart1(portafibID, languageUI);

                    // ASYNCHRONOUS Funcionalitat de guardar document a Arxiu amb la API
                    peticioLogicaEjb.cosesAFerPeticioFirmadaPart2(portafibID, languageUI, infoSignatura);
                    
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA: {
                    log.info("NOTIFICACIOAVIS_PETICIO_REBUTJADA = " + eventID);

                    String languageUI = "ca";
                    Long portafibID = event.getSigningRequest().getID();
                    String motiuRebuig = event.getSigningRequest().getRejectionReason();

                    peticioLogicaEjb.cosesAFerPeticioRebutjada(portafibID, languageUI, motiuRebuig);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA: {
                    log.info("NOTIFICACIOAVIS_PETICIO_PAUSADA = " + eventID);
                }
                break;
                case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_REVISAR: {
                    log.info("NOTIFICACIOAVIS_REQUERIT_PER_REVISAR = " + eventID);

                }
                break;
            }


            // Assignacio de l'estat a la peticio corresponent.
            //if (event != null && event.getSign() != null) {
            //    
            //}

            // Actualitzacio del pintat de la pagina

            long endTime = System.currentTimeMillis();
            log.info("Event processat. Temps: " + (endTime - startTime));
            log.info("CALLBACK -> OK");

            return Response.status(Response.Status.OK).entity("OK").build();
        } catch (I18NException e) {
            log.error("CALLBACK -> ERROR");
            String msg = I18NCommonUtils.getMessage(e, new Locale("ca"));
            log.error(msg, e);
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();

        } catch (Throwable th) {
            log.error("CALLBACK -> ERROR");
            String msg = "Error desconegut processant event de Peticio de Firma REST: " + th.getMessage();
            log.error(msg, th);
            return Response.status(Response.Status.BAD_REQUEST).entity(msg).build();
        }

    }

    // @GetMapping("/versio")
//    public String getVersio() {
//        log.info("URL de CallBack Validada");
//        return "1";
//    }

    // TODO: Ficar dins funcions:
    /*
     * //Info document firmat FirmaAsyncSimpleSignedFile signedFileFull;
     * signedFileFull = api.getSignedFileOfSignatureRequest(rinfo);
     * 
     * // Imprimir Informacio
     * 
     * System.out.println(" === INFO ==="); FirmaAsyncSimpleSignedFileInfo info =
     * signedFileFull.getSignedFileInfo();
     * 
     * System.out.println(FirmaAsyncSimpleSignedFileInfo.toString(info));
     * 
     * // Obtenir document signat FirmaAsyncSimpleFile firma =
     * signedFileFull.getSignedFile();
     * 
     * byte[] data = firma.getData(); log.info("Tamany del fitxer: " + data.length);
     * 
     * String postFix; String signType = info.getSignType(); if
     * (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_PADES.equals(signType)) { postFix =
     * "_signed.pdf"; } else if
     * (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_CADES.equals(signType)) { postFix =
     * "_signed.csig"; } else if
     * (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_XADES.equals(signType)) { postFix =
     * "_signed.xsig"; } else { postFix = "_signed.unknown_extension_for_sign_type_"
     * + signType; }
     * 
     * File fitxerFirmat = new File(firma.getNom() + postFix); FileOutputStream fos
     * = new FileOutputStream(fitxerFirmat); fos.write(data); fos.flush();
     * fos.close();
     * 
     * System.out.println(" === FILE ===");
     * System.out.println("El fitxer firmat s'ha guardat a " +
     * fitxerFirmat.getAbsolutePath());
     * 
     * 
     */
    /*
     * 
     * //Info document original FirmaAsyncSimpleFile originalFile =
     * api.getOriginalFileOfSignatureRequest(rinfo);
     * 
     * // Imprimir Informacio System.out.println(" === ORIGINAL FILE  ===");
     * 
     * byte[] data = originalFile.getData();
     * System.out.println("Tamany del fitxer: " + data.length);
     * 
     * String prefix = "original_" + rinfo.getSignatureRequestID() + "_"; File
     * fitxerOriginal = new File(prefix + originalFile.getNom()); FileOutputStream
     * fos = new FileOutputStream(fitxerOriginal); fos.write(data); fos.flush();
     * fos.close();
     * 
     * System.out.println("El fitxer original s'ha guardat a " +
     * fitxerOriginal.getAbsolutePath());
     */

}
