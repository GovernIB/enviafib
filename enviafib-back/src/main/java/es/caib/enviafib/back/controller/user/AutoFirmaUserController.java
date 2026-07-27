package es.caib.enviafib.back.controller.user;

import java.io.File;
import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmasimple.v1.ApiFirmaWebSimple;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleAddFileToSignRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleCommonInfo;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFile;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleFileInfoSignature;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetSignatureResultRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleGetTransactionStatusResponse;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureResult;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleSignatureStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStartTransactionRequest;
import org.fundaciobit.apisib.apifirmasimple.v1.beans.FirmaSimpleStatus;
import org.fundaciobit.apisib.apifirmasimple.v1.jersey.ApiFirmaWebSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NArgumentCode;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.i18n.I18NValidationException;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import es.caib.enviafib.back.form.webdb.PeticioForm;
import es.caib.enviafib.back.security.LoginInfo;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.FitxerLogicaService;
import es.caib.enviafib.logic.utils.LogicUtils;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.entity.Usuari;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.FitxerJPA;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author anadal
 *
 */
@Controller
@RequestMapping(value = AutoFirmaUserController.CONTEXT_WEB)
public class AutoFirmaUserController extends AbstractFirmaUserController {

    public static final String CONTEXT_WEB = "/user/autofirma";

    // Sempre posarem el mateix
//    public static final String SIGNID = "SignID_1";
    public static final String SIGNID_ = "SignID_";

    @Override
    public int getTipusPeticio() {
        return Constants.TIPUS_PETICIO_AUTOFIRMA;
    }

    @Override
    public PeticioForm getPeticioForm(PeticioJPA _jpa, boolean __isView, HttpServletRequest request, ModelAndView mav)
            throws I18NException {

        PeticioForm peticioForm = super.getPeticioForm(_jpa, __isView, request, mav);
        peticioForm.getPeticio().setDestinatariNif(LoginInfo.getInstance().getUsuari().getNif());

        peticioForm.addHiddenField(DESTINATARINIF);
        peticioForm.getHiddenFields().remove(PeticioFields.REASON);

        peticioForm.addLabel(REASON, "autofirma.reason.obligatori");
        return peticioForm;
    }
//
//    @Override
//    public PeticioJPA create(HttpServletRequest request, PeticioJPA peticio)
//            throws I18NException, I18NValidationException {
//        PeticioJPA p = super.create(request, peticio);
//
//        String absoluteControllerBase = request.getSession().getAttribute(MenuUserController.URL_BASE_NAVEGADOR) + getContextWeb();
//        Usuari usuari = LoginInfo.getInstance().getUsuari();
//        String lang = LocaleContextHolder.getLocale().getLanguage();
//
//        String[] info = autofirma(peticio, usuari, lang, absoluteControllerBase);
//
//        String transactionID = info[0];
//        String redirectUrl = info[1];
//
//        log.info("Afegint transactionID[" + transactionID + "] => " + peticio.getPeticioID() + "    dins mapping");
//
//        // peticioIdByTransactionId.put(transactionID, peticio.getPeticioID());
//        p.setPeticioPortafirmes(transactionID);
//
//        this.peticioLogicaEjb.update(p);
//
//        request.getSession().setAttribute("redirectUrl", redirectUrl);
//
//        return p;
//    }

    @Override
    public String getRedirectWhenCreated(HttpServletRequest request, PeticioForm peticioForm) {

    	return "redirect:" + getContextWeb() + "/viewiniframe";
//        Map<String, List<String>> missatges =  HtmlUtils.getAllMessages(request);
//        if (missatges.get(HtmlUtils.WARN) == null && missatges.get(HtmlUtils.ERROR) == null) {
//            //No ha de sortir cap missatge de INFO o SUCCES quan es autofirma, perque no s'envia res a portafib
//            //Aquest missatge te el consentiment de Toni Nadal.
//            HtmlUtils.deleteMessages(request);
//        }else {
//            //Si va malament tornam al llistat, sense acabar el proces de AutoFirma
//            return getRedirectToList();
//        }
    }

    @RequestMapping(value = "/viewiniframe", method = RequestMethod.GET)
    public ModelAndView viewInIframe(HttpServletRequest request) throws Exception {
        String redirectUrl = (String) request.getSession().getAttribute("redirectUrl");
        log.info("ENTRA A /viewiniframe => redirectUrl: " + redirectUrl);
        if (log.isDebugEnabled()) {
        }

        ModelAndView mav = new ModelAndView("firmasimpleweb_iframe");
        mav.addObject("urlToIFrameCode", redirectUrl);
        return mav;
    }

    @RequestMapping(value = "/finalWeb/{transactionID}")
    public ModelAndView finalProcesDeFirmaWeb(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID) throws Exception {
        
        ModelAndView mav = new ModelAndView("finaliframe");
        mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/finalWebEspera/" + transactionID);
//        mav.addObject("transactionID", transactionID);
        return mav;
    }

    @RequestMapping(value = "/finalWebEspera/{transactionID}")
    public ModelAndView finalProcesDeFirmaWebEspera(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("transactionID") String transactionID) throws Exception {
        
        ModelAndView mav = new ModelAndView("finaliframeespera");
        mav.addObject("URL_FINAL", request.getContextPath() + getContextWeb() + "/finalWebAuth/" + transactionID);
//        mav.addObject("transactionID", transactionID);
        return mav;
    }
    
    class ErrorInfo {
        String msg;
        String exception;
        
		public ErrorInfo(String msg, String exception) {
			this.msg = msg;
			this.exception = exception;
		}

		public ErrorInfo() {
			this.msg = null;
			this.exception = null;
		}
		
		public String getMsg() {
			return msg;
		}
		
		public String getException() {
			return exception;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}
		
		public void setMsgCode(String codi, String... args) {
			this.msg = I18NUtils.tradueix(codi, args);
		}
		
		public void setException(String exception) {
			this.exception = exception;
		}
		
    }

	@RequestMapping(value = "/finalWebAuth/{transactionID}")
	public ModelAndView finalProcesDeFirmaWebAuth(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("transactionID") String transactionID) throws Exception {

		log.info("Final Web  Consultant transactionID[" + transactionID + "] dins Peticions ...");

		List<Long> llistatPeticioID = peticioLogicaEjb.executeQuery(PeticioFields.PETICIOID,
				PeticioFields.PETICIOPORTAFIRMES.equal(transactionID));

		// peticioIdByTransactionId.get(transactionID);

		if (llistatPeticioID.size() == 0) {
			Long peticioID = 0L;
			throw new I18NException("error.notfound", new I18NArgumentCode("peticio.peticio"),
					new I18NArgumentCode("peticio.peticioID"), new I18NArgumentString(String.valueOf(peticioID)));
		}

		log.info("Consulta transactionID]" + transactionID + "[ => " + llistatPeticioID.toArray());

		ApiFirmaWebSimple api = null;
		ErrorInfo errorInfo = new ErrorInfo();

		try {
			api = getApiFirmaWebSimple();

			FirmaSimpleGetTransactionStatusResponse fullTransactionStatus;
			fullTransactionStatus = api.getTransactionStatus(transactionID);

			FirmaSimpleStatus transactionStatus = fullTransactionStatus.getTransactionStatus();

			int status = transactionStatus.getStatus();

			switch (status) {
			case FirmaSimpleStatus.STATUS_INITIALIZING:
				errorInfo.setMsgCode("procesdefirma.status.initializing");
				break;

			case FirmaSimpleStatus.STATUS_IN_PROGRESS:
				errorInfo.setMsgCode("procesdefirma.status.inprogress");
				break;

			case FirmaSimpleStatus.STATUS_FINAL_ERROR: {

				errorInfo.setMsgCode("procesdefirma.status.finalerror", transactionID,
						transactionStatus.getErrorMessage());

				String strackTrace = transactionStatus.getErrorStackTrace();
				errorInfo.setException(strackTrace);
			}
				break;

			case FirmaSimpleStatus.STATUS_CANCELLED:
				errorInfo.setMsgCode("procesdefirma.status.canceled", transactionID);
				break;

			case FirmaSimpleStatus.STATUS_FINAL_OK: {
				List<FirmaSimpleSignatureStatus> results = fullTransactionStatus.getSignaturesStatusList();
				handleFinalOk(api, request, transactionID, llistatPeticioID, results, errorInfo);
			}
			    break;
			    
			default:
				errorInfo.setMsgCode("procesdefirma.status.default", String.valueOf(status));
			}

			// Final Switch Global
		} catch (Exception e) {
			errorInfo.setMsgCode("procesdefirma.error", e.getMessage());

			String errorException = ExceptionUtils.getStackTrace(e);
			errorInfo.setException(errorException);

		} finally {
			if (api != null && transactionID != null) {
				try {
					api.closeTransaction(transactionID);
				} catch (Throwable th) {
					log.error(th.getMessage(), th);
				}
			}
		}

		if (errorInfo.getMsg() == null) {
			HtmlUtils.saveMessageSuccess(request, "procesdefirma.status.finalok");
		} else {
			assignarErrorPeticions(llistatPeticioID, errorInfo);
			HtmlUtils.saveMessageError(request, errorInfo.getMsg());
		}
		return new ModelAndView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
	}

	private void assignarErrorPeticions(List<Long> llistatPeticioID, ErrorInfo errorInfo) throws I18NException {
		String errorMsg = errorInfo.getMsg();
		String errorException = errorInfo.getException();

		log.error("errorMsg: " + errorMsg);
		if (errorException != null) {
			log.error("errorException: " + errorException);
		}

		for (Long peticioID : llistatPeticioID) {
			Peticio pet = peticioLogicaEjb.findByPrimaryKeyPublic(peticioID);
			log.info("Afegint error a peticioID[" + peticioID + "]");
			if (pet != null) {
				pet.setErrorMsg(LogicUtils.split255(errorMsg));
				pet.setErrorException(errorException);
				pet.setDataFinal(new Timestamp(System.currentTimeMillis()));
				pet.setEstat(Constants.ESTAT_PETICIO_ERROR);
				peticioLogicaEjb.update(pet);
			}
		}
	}

	private void handleFinalOk(ApiFirmaWebSimple api, HttpServletRequest request, String transactionID,
			List<Long> llistatPeticioID, List<FirmaSimpleSignatureStatus> results, ErrorInfo errorInfo)
			throws Exception {

		if (results == null || results.isEmpty()) {
			log.warn("No hi ha signatures associades a la transacció " + transactionID);
			HtmlUtils.saveMessageError(request, "procesdefirma.status.finalerror");
			return; // new ModelAndView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
		}

		if (log.isDebugEnabled()) {}
		log.info(" ===== WEB RESULTATS [" + results.size() + "] =========");

		for (FirmaSimpleSignatureStatus result : results) {
			String signID = result.getSignID();

			log.info(" ------ WEB SIGNID ]" + signID + "[");
			if (log.isDebugEnabled()) {}

			FirmaSimpleStatus fss = result.getStatus();
			int statusSign = fss.getStatus();

			switch (statusSign) {

			case FirmaSimpleStatus.STATUS_INITIALIZING:
				errorInfo.setMsgCode("procesdefirma.status.initializing");
				break;

			case FirmaSimpleStatus.STATUS_IN_PROGRESS:
				errorInfo.setMsgCode("procesdefirma.status.inprogress");
				break;

			case FirmaSimpleStatus.STATUS_FINAL_ERROR: { // = -1;
				errorInfo.setMsgCode("procesdefirma.status.finalerror", transactionID, fss.getErrorMessage());

				log.error("Error en la firma: " + errorInfo.getMsg());

				String errorException = fss.getErrorStackTrace();
				errorInfo.setException(errorException);
			}
				break;

			case FirmaSimpleStatus.STATUS_CANCELLED:
				errorInfo.setMsgCode("procesdefirma.status.canceled", transactionID);
				break;

			case FirmaSimpleStatus.STATUS_FINAL_OK: // = 2;
				FirmaSimpleSignatureResult fssr = null;
				fssr = api.getSignatureResult(new FirmaSimpleGetSignatureResultRequest(transactionID, signID));

				processSuccessfulSign(fssr, transactionID, llistatPeticioID, signID, errorInfo);
                break;
//				return new ModelAndView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
			}
		}

//		// Si arribem aquí és que tots han donat error
//		assignarErrorPeticions(llistatPeticioID, errorInfo);
//		HtmlUtils.saveMessageError(request, errorInfo.getMsg());
//		return;  new ModelAndView(new RedirectView(LlistatPeticionsUserController.CONTEXT_WEB + "/list", true));
	}

	private void processSuccessfulSign(FirmaSimpleSignatureResult fssr, String transactionID,
			List<Long> llistatPeticioID, String signID, ErrorInfo errorInfo) throws Exception {
		
		log.info("processSuccessfulSign()::Autofirma => Entram dins processSuccessfulSign ...");
		log.info("signID: " + signID);
		

		for (Long peticioID : llistatPeticioID) {
			log.info("Comprovant peticioID: " + peticioID + " amb signID: " + SIGNID_ + peticioID);
			if (signID.equals(SIGNID_ + peticioID)) {

				if (fssr != null && fssr.getSignedFileInfo() != null) {
					Thread.sleep(1000);
					// Aquest mètode pot retornar un I18NException que va directe al catch i mostra
					// l'error
					InfoSignatura is = peticioLogicaEjb.guardarResultatAutofirma(peticioID, fssr);

					log.info("guardarResultatAutofirma()::Autofirma => guardar dins Arxiu de forma ASYNC ...");

					Peticio peticio = peticioLogicaEjb.findByPrimaryKey(peticioID);

					peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
					peticioLogicaEjb.update(peticio);

					peticioLogicaEjb.guardarPeticioArxiuAsync(peticio, is, Configuracio.getUrlBase());

					log.info("guardarResultatAutofirma()::Autofirma => sortim");

				} else {
					// Error de getSignatureResult ha anat malament;
					errorInfo.setMsgCode("procesdefirma.status.final.error.portafib", transactionID, signID);
				}
			}
		}
	}
    
    /**
     * 
     * @param peticio
     * @param files 
     * @param usuari
     * @param langUI
     * @param absoluteControllerBase
     * @param log 
     * @return
     * @throws I18NException
     */
    public static String[] autofirma(PeticioJPA peticio, List<CommonsMultipartFile> files, Usuari usuari, String langUI, String absoluteControllerBase, Logger log)
    
            throws I18NException {

        ApiFirmaWebSimple apiWeb = null;
        String transactionID = null;

        try {

            apiWeb = getApiFirmaWebSimple();

            final String username = usuari.getUsername();
            final String administrationID = usuari.getNif();
            final String signerEmail = usuari.getEmail();

            log.info("Username: ]" + username + "[");
            log.info("administrationID: ]" + administrationID + "[");
            log.info("signerEmail: ]" + signerEmail + "[");

            FirmaSimpleCommonInfo commonInfoSignature;

            String signProfile = Configuracio.getPortafibProfile();
            commonInfoSignature = new FirmaSimpleCommonInfo(signProfile, langUI, username, administrationID,
                    signerEmail);

            // Enviam la part comu de la transacció
            transactionID = apiWeb.getTransactionID(commonInfoSignature);
            log.info("TransactionID = |" + transactionID + "|");

            final String location = null; // form.getLocation();
            final String langDoc = peticio.getIdiomaDoc();
            final String reason = peticio.getReason();
            final long tipusDocumentalID = Long.parseLong(peticio.getTipusDocumental()); // =TD99

            int nFitxers = 0;
            for (CommonsMultipartFile file : files) {
            	nFitxers++;
            	
                FirmaSimpleFileInfoSignature fileInfoSignature;

                // Només es suporta una firma
                final int signNumber = 1;
                
                final String signID = SIGNID_ + nFitxers;

                String nomFitxer =  file.getOriginalFilename(); // peticio.getFitxer().getNom();
                String mimeTypeFitxer = file.getContentType(); // peticio.getFitxer().getMime();
                byte[] dataFitxer = file.getBytes();

                FirmaSimpleFile fileToSign = new FirmaSimpleFile(nomFitxer, mimeTypeFitxer, dataFitxer);

                fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID, fileToSign.getNom(), reason,
                        location, signNumber, langDoc, tipusDocumentalID);

                log.info("Fitxer " + fileInfoSignature.getFileToSign().getNom());
                
                apiWeb.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, fileInfoSignature));

                
            }

            // Es Web
            final String view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;
            //          FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN.equals(view)

            final String returnUrl = absoluteControllerBase + "/finalWeb/" + transactionID;

            FirmaSimpleStartTransactionRequest startTransactionInfo;
            startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl, view);

            String redirectUrl = apiWeb.startTransaction(startTransactionInfo);

            return new String[] { transactionID, redirectUrl };

        } catch (AbstractApisIBException e) {
            log.error("Error cridant a PortaFIB per a la signatura immediata", e);
            throw new I18NException("error.signaturainmediata", e.getMessage());
        } catch (Exception e) {

            log.error("Error desconegut processant entrada de dades o inicialitzant el proces de firma ", e);
            // Només s'executa si es WEB
            if (transactionID != null) {
                try {
                    apiWeb.closeTransaction(transactionID);
                } catch (Throwable th) {
                    log.error(th.getMessage(), th);
                }
            }

            throw new I18NException("error.procesdefirma", e.getMessage());
        }
    }

    protected static ApiFirmaWebSimple getApiFirmaWebSimple() throws Exception {

        String url = Configuracio.getPortaFIBApiFirmaWebUrl();
        String username = Configuracio.getPortaFIBApiFirmaWebUsername();
        String password = Configuracio.getPortaFIBApiFirmaWebPassword();

        return new ApiFirmaWebSimpleJersey(url, username, password);
    }
    
	public static String[] autofirma2(List<PeticioJPA> peticions, String absoluteControllerBase, Logger log)

			throws I18NException {

		Usuari usuari = LoginInfo.getInstance().getUsuari();
		String langUI = LocaleContextHolder.getLocale().getLanguage();

		ApiFirmaWebSimple apiWeb = null;
		String transactionID = null;

		try {

			apiWeb = getApiFirmaWebSimple();

			final String username = usuari.getUsername();
			final String administrationID = usuari.getNif();
			final String signerEmail = usuari.getEmail();

			log.info("Username: ]" + username + "[");
			log.info("administrationID: ]" + administrationID + "[");
			log.info("signerEmail: ]" + signerEmail + "[");

			FirmaSimpleCommonInfo commonInfoSignature;

			String signProfile = Configuracio.getPortafibProfile();
			commonInfoSignature = new FirmaSimpleCommonInfo(signProfile, langUI, username, administrationID,
					signerEmail);

			// Enviam la part comu de la transacció
			transactionID = apiWeb.getTransactionID(commonInfoSignature);
			log.info("TransactionID = |" + transactionID + "|");

			for (PeticioJPA peticio : peticions) {
				final String location = null; // form.getLocation();
				final String langDoc = peticio.getIdiomaDoc();
				final String reason = peticio.getReason();
				final long tipusDocumentalID = Long.parseLong(peticio.getTipusDocumental()); // =TD99

				FirmaSimpleFileInfoSignature fileInfoSignature;

				// Només es suporta una firma
				final int signNumber = 1;

				final String signID = SIGNID_ + peticio.getPeticioID();

//				Fitxer fitxer = fitxerLogicEjb.findByPrimaryKey(peticio.getFitxerID());
				FitxerJPA fitxer = peticio.getFitxer();
				
				String nomFitxer = fitxer.getNom();
				String mimeTypeFitxer = fitxer.getMime();

				File file = FileSystemManager.getFile(peticio.getFitxerID());
				byte[] dataFitxer = FileUtils.readFileToByteArray(file);

				FirmaSimpleFile fileToSign = new FirmaSimpleFile(nomFitxer, mimeTypeFitxer, dataFitxer);

				fileInfoSignature = new FirmaSimpleFileInfoSignature(fileToSign, signID, fileToSign.getNom(), reason,
						location, signNumber, langDoc, tipusDocumentalID);

				log.info("Fitxer " + fileInfoSignature.getFileToSign().getNom());

				apiWeb.addFileToSign(new FirmaSimpleAddFileToSignRequest(transactionID, fileInfoSignature));
			}

			// Es Web
			final String view = FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN;
			// FirmaSimpleStartTransactionRequest.VIEW_FULLSCREEN.equals(view)

			final String returnUrl = absoluteControllerBase + "/finalWeb/" + transactionID;

			FirmaSimpleStartTransactionRequest startTransactionInfo;
			startTransactionInfo = new FirmaSimpleStartTransactionRequest(transactionID, returnUrl, view);

			String redirectUrl = apiWeb.startTransaction(startTransactionInfo);

			return new String[] { transactionID, redirectUrl };

		} catch (AbstractApisIBException e) {
			log.error("Error cridant a PortaFIB per a la signatura immediata", e);
			throw new I18NException("error.signaturainmediata", e.getMessage());
		} catch (Exception e) {

			log.error("Error desconegut processant entrada de dades o inicialitzant el proces de firma ", e);
			// Només s'executa si es WEB
			if (transactionID != null) {
				try {
					apiWeb.closeTransaction(transactionID);
				} catch (Throwable th) {
					log.error(th.getMessage(), th);
				}
			}

			throw new I18NException("error.procesdefirma", e.getMessage());
		}
	}

}
