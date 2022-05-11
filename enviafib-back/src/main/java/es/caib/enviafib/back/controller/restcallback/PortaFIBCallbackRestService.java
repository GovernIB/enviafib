package es.caib.enviafib.back.controller.restcallback;

import javax.ejb.EJB;
import javax.ws.rs.core.Response;

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
      
      if(event.getSigningRequest() != null) {
    	  log.info("Titol de peticio de Firma: " + event.getSigningRequest().getTitle());
          log.info("Estat de peticio de Firma: " + event.getSigningRequest().getState());
          log.info("ID de peticio de Firma: " + event.getSigningRequest().getID());
          log.info("CustodyURL de peticio de Firma: " + event.getSigningRequest().getCustodyURL());
          log.info("RejectionReason de peticio de Firma: " + event.getSigningRequest().getRejectionReason());
          log.info("Additional Information: " + event.getSigningRequest().getAdditionalInformation());
      }else {
    	  log.info(" -- No hi ha peticio de firma: La peticio no s'ha enviat correctament?");
      }
      
      
      if(event.getSign() != null) {
    	  if(event.getSign().getID() > -1) {
        	  log.info("Sign ID: " + event.getSign().getID());
    	  }
    	  if(event.getSign().getIssuer() != null) {
        	  log.info("Sign Issuer: " + event.getSign().getIssuer());
    	  }
    	  if(event.getSign().getSubject() != null) {
        	  log.info("Sign Subject: " + event.getSign().getSubject());
    	  }
    	  if(event.getSign().getSerialNumber() != null) {
        	  log.info("Sign Serial Number: " + event.getSign().getSerialNumber());
    	  }
    	  
    	  }else {
    		  log.info(" -- No s'ha firmat el document: Peticio fallida o rebutjada.");
    	  }
      log.info("XYZ **************************************************** XYZ " );
      
      //TODO: Gestionar tots els tipus de notificacions.
      
      
      if(event != null) {
    	  
    	  
    	  
    	  if(event.getSigningRequest() != null) {
        	  peticioLogicaEjb.findByPrimaryKey(event.getSigningRequest().getID());
        	  
          }
          
          switch(event.getSigningRequest().getState()) {
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_NOINICIAT:
        	  break;
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_ENPROCES:
        	  break;
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_PAUSAT:
        	  break;
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_FIRMAT:
        	  break;
          case ConstantsV2.TIPUSESTATPETICIODEFIRMA_REBUTJAT:
        	  break;
        	  
        	  
          }
      }
      
      
      
    	    
      
      //PortaFIBEventStore.addEvent(event);
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
  
  
