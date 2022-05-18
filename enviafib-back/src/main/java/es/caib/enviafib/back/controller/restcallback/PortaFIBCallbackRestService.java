package es.caib.enviafib.back.controller.restcallback;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.portafib.callback.beans.v1.PortaFIBEvent;
import es.caib.portafib.utils.ConstantsV2;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



/**
 * 
 * @author fbosch
 *
 */
@RestController
@RequestMapping("/cbrest/v1")
public class PortaFIBCallbackRestService {

  private final Logger log = Logger.getLogger(getClass());
  
  @EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
  protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;


  @GetMapping("/versio")
  public String getVersio() {
    return "1";
  }

  @PostMapping("/event")
  public Response event(@RequestBody PortaFIBEvent event) {
    try {
      
      
      // TODO: Eliminar tots els log.info quan s'hagui implementat la gestió de la resposta de PortaFIB
      log.info("XYZ **************************************************** XYZ " );
      log.info("XYZ Resposta: " );
      log.info("Event type:" + event.getEventTypeID());
      log.info("Applicarion ID: " + event.getApplicationID());
      log.info("Entity ID: " + event.getEntityID());
      log.info("Version: " + event.getVersion());
      
      switch(event.getEventTypeID()) {
          case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES :{
              log.info("NOTIFICACIOAVIS_PETICIO_EN_PROCES = " + (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_EN_PROCES);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR  :{
              log.info("NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR = " + (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_VALIDAR);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR  :{
              log.info("NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR = " + (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_VALIDAR);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR  :{
              log.info("NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR = " + (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_FIRMAR);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR  :{
              log.info("NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR = " + (int) ConstantsV2.NOTIFICACIOAVIS_DESCARTAT_PER_FIRMAR);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_VALIDAT  :{
              log.info("NOTIFICACIOAVIS_VALIDAT = " + (int) ConstantsV2.NOTIFICACIOAVIS_VALIDAT);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_INVALIDAT  :{
              log.info("NOTIFICACIOAVIS_INVALIDAT = " + (int) ConstantsV2.NOTIFICACIOAVIS_INVALIDAT);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL  :{
              log.info("NOTIFICACIOAVIS_FIRMA_PARCIAL = " + (int) ConstantsV2.NOTIFICACIOAVIS_FIRMA_PARCIAL);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA  :{
              log.info("NOTIFICACIOAVIS_PETICIO_FIRMADA = " + (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_FIRMADA);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA  :{
              log.info("NOTIFICACIOAVIS_PETICIO_REBUTJADA = " + (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_REBUTJADA);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA  :{
              log.info("NOTIFICACIOAVIS_PETICIO_PAUSADA = " + (int) ConstantsV2.NOTIFICACIOAVIS_PETICIO_PAUSADA);
          }
          break;
          case (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_REVISAR  :{
              log.info("NOTIFICACIOAVIS_REQUERIT_PER_REVISAR = " + (int) ConstantsV2.NOTIFICACIOAVIS_REQUERIT_PER_REVISAR);
          }
          break;
      }
      
      log.info("XYZ **************************************************** XYZ " );
      
      //Assignacio de l'estat a la peticio corresponent.
      if(event != null && event.getSign() != null) {
          
    	  Peticio peticioTemp = peticioLogicaEjb.select(PeticioFields.PETICIOPORTAFIB.equal(event.getSigningRequest().getID())).get(0);
          peticioTemp.setEstat((short) event.getSigningRequest().getState());
          peticioLogicaEjb.updatePublic(peticioTemp);
      }
      
      //Actualitzacio del pintat de la pagina
      
      log.info("Event processat");
      return Response.status(200).entity("OK").build();
    } catch (Throwable th) {
      String msg = "Error desconegut processant event de Peticio de Firma REST: "
          + th.getMessage();

      th.printStackTrace();

      return Response.status(500).entity(msg).build();
    }
  }

  //TODO: Ficar dins funcions:
  /*
//Info document firmat
	FirmaAsyncSimpleSignedFile signedFileFull;
	signedFileFull = api.getSignedFileOfSignatureRequest(rinfo);

	// Imprimir Informacio

	System.out.println(" === INFO ===");
	FirmaAsyncSimpleSignedFileInfo info = signedFileFull.getSignedFileInfo();

	System.out.println(FirmaAsyncSimpleSignedFileInfo.toString(info));

	// Obtenir document signat
	FirmaAsyncSimpleFile firma = signedFileFull.getSignedFile();

	byte[] data = firma.getData();
	log.info("Tamany del fitxer: " + data.length);

	String postFix;
	String signType = info.getSignType();
	if (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_PADES.equals(signType)) {
		postFix = "_signed.pdf";
	} else if (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_CADES.equals(signType)) {
		postFix = "_signed.csig";
	} else if (FirmaAsyncSimpleSignedFileInfo.SIGN_TYPE_XADES.equals(signType)) {
		postFix = "_signed.xsig";
	} else {
		postFix = "_signed.unknown_extension_for_sign_type_" + signType;
	}

	File fitxerFirmat = new File(firma.getNom() + postFix);
	FileOutputStream fos = new FileOutputStream(fitxerFirmat);
	fos.write(data);
	fos.flush();
	fos.close();

	System.out.println(" === FILE ===");
	System.out.println("El fitxer firmat s'ha guardat a " + fitxerFirmat.getAbsolutePath());
  
  
  */
  /*
  
//Info document original
	FirmaAsyncSimpleFile originalFile = api.getOriginalFileOfSignatureRequest(rinfo);

	// Imprimir Informacio
	System.out.println(" === ORIGINAL FILE  ===");

	byte[] data = originalFile.getData();
	System.out.println("Tamany del fitxer: " + data.length);

	String prefix = "original_" + rinfo.getSignatureRequestID() + "_";
	File fitxerOriginal = new File(prefix + originalFile.getNom());
	FileOutputStream fos = new FileOutputStream(fitxerOriginal);
	fos.write(data);
	fos.flush();
	fos.close();

	System.out.println("El fitxer original s'ha guardat a " + fitxerOriginal.getAbsolutePath());
  */
  
  
}
  
  
