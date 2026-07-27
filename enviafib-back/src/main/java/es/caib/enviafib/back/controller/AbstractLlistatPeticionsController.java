package es.caib.enviafib.back.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Field;
import org.fundaciobit.genapp.common.web.HtmlUtils;
import org.fundaciobit.genapp.common.web.form.AdditionalButton;
import org.fundaciobit.genapp.common.web.form.AdditionalButtonStyle;
import org.fundaciobit.genapp.common.web.form.AdditionalField;
import org.fundaciobit.genapp.common.web.i18n.I18NUtils;
import org.fundaciobit.pluginsib.utils.templateengine.TemplateEngine;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import es.caib.enviafib.back.controller.user.LlistatPeticionsUserController.TipusFile;
import es.caib.enviafib.back.form.webdb.PeticioFilterForm;
import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.logic.utils.EmailUtil;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.FitxerFields;
import es.caib.enviafib.model.fields.InfoArxiuFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.model.fields.PeticioQueryPath;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.UsuariJPA;
import es.caib.pluginsib.arxiu.api.Document;
import es.caib.pluginsib.arxiu.api.DocumentContingut;
import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

/**
 * 
 * @author ptrias
 *
 */
public abstract class AbstractLlistatPeticionsController extends AbstractPeticioUserController {

    public static final int COLUMN_ESTAT_IMG = 1;

    public abstract boolean isAdmin();

    @Override
    public boolean isActiveList() {
        return true;
    }

    @Override
    public boolean isActiveDelete() {
        return true;
    }

    @Override
    public boolean isActiveFormNew() {
        return false;
    }

    @Override
    public boolean isActiveFormEdit() {
        return false;
    }

    @Override
    public boolean isActiveFormView() {
        return false;
    }

    @Override
    public PeticioFilterForm getPeticioFilterForm(Integer pagina, ModelAndView mav, HttpServletRequest request)
            throws I18NException {
        PeticioFilterForm peticioFilterForm = super.getPeticioFilterForm(pagina, mav, request);
        if (peticioFilterForm.isNou()) {

            request.getSession().setAttribute("myContext", getContextWeb());

            peticioFilterForm.setActionsRenderer(PeticioFilterForm.ACTIONS_RENDERER_DROPDOWN_BUTTON);

            Set<Field<?>> hiddens = new HashSet<Field<?>>(Arrays.asList(PeticioFields.ALL_PETICIO_FIELDS));

            hiddens.remove(PETICIOID);
            hiddens.remove(NOM);
            hiddens.remove(DATACREACIO);
            hiddens.remove(TIPUSDOCUMENTAL);
            hiddens.remove(DATAFINAL);
            hiddens.remove(SOLICITANTID);

            peticioFilterForm.setHiddenFields(hiddens);

            peticioFilterForm.setOrderBy(DATACREACIO.javaName);
            peticioFilterForm.setOrderAsc(false);

            peticioFilterForm.setAttachedAdditionalJspCode(true);

            peticioFilterForm.setAddButtonVisible(false);
            peticioFilterForm.setEditButtonVisible(false);
            peticioFilterForm.setDeleteButtonVisible(false);

            List<Field<?>> newFilterBy = new ArrayList<Field<?>>(peticioFilterForm.getDefaultFilterByFields());
            newFilterBy.add(NOM);
            newFilterBy.add(DATACREACIO);
            newFilterBy.add(DATAFINAL);
            //            newFilterBy.add(ESTAT);
            peticioFilterForm.setFilterByFields(newFilterBy);

            List<Field<?>> newGroupBy = new ArrayList<Field<?>>(peticioFilterForm.getDefaultGroupByFields());
            newGroupBy.remove(ESTAT);
            peticioFilterForm.setGroupByFields(newGroupBy);

            peticioFilterForm.setVisibleFilterBy(false);

            {
                AdditionalField<Long, String> additionalField = new AdditionalField<Long, String>();
                additionalField.setCodeName(PeticioFields.ESTAT.codeLabel);
                additionalField.setPosition(COLUMN_ESTAT_IMG);
                additionalField.setOrderBy(ESTAT);
                additionalField.setEscapeXml(false);
                // Els valors s'ompliran al mètode postList()
                additionalField.setValueMap(new HashMap<Long, String>());
                // Per ordenar feim servir el mateix camp de nom del remitent
                peticioFilterForm.addAdditionalField(additionalField);
            }

            peticioFilterForm.addAdditionalButton(new AdditionalButton("fas fa-download", "descarregar.seleccionats",
                    "javascript:downloadSelectedFiles()", AdditionalButtonStyle.PRIMARY));
        }
        
        peticioFilterForm.setVisibleExportList(true);

        return peticioFilterForm;
    }

    @Override
    public void postList(HttpServletRequest request, ModelAndView mav, PeticioFilterForm filterForm, List<Peticio> list)
            throws I18NException {

        // Millores en els filtres dels llistat de peticions. #248: Mostrar sempre filtres
        filterForm.setVisibleFilterBy(true);

        Map<Long, String> mapRemitent = (Map<Long, String>) filterForm.getAdditionalField(COLUMN_ESTAT_IMG)
                .getValueMap();
        mapRemitent.clear();

        for (Peticio peticio : list) {
            String color;
            ArrayList<String> iconList = new ArrayList<String>();
            int estat = (int) peticio.getEstat();

            switch (estat) {
                case Constants.ESTAT_PETICIO_FIRMADA:
                    color = "green";
                    iconList.add("fas fa-file-signature");
                break;

                case Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT:
                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:
                    if (isAdmin()) {
                        iconList.add("fas fa-unlock");
                        if (estat == Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT) {
                            color = "red";
                        } else {
                            color = "orange";
                        }
                    } else {
                        color = "green";
                        iconList.add("fas fa-file-signature");
                    }
                break;
                
                
                case Constants.ESTAT_PETICIO_PROCESANT_CALLBACK:
                case Constants.ESTAT_PETICIO_ERROR_CALLBACK:
                	iconList.add("fas fa-file-signature");
                	color = "orange";
                    if (isAdmin()) {
                        if (estat == Constants.ESTAT_PETICIO_ERROR_CALLBACK) {
                            color = "red";
                        }
                    }
                break;
                
                

                case Constants.ESTAT_PETICIO_EN_PROCES:
                case Constants.ESTAT_PETICIO_ARXIVANT:
                    color = "orange";
                    if (estat == Constants.ESTAT_PETICIO_EN_PROCES) {
                        iconList.add("fas fa-user-clock");
                    } else {
                        iconList.add("fas fa-spinner");
						if (estat == Constants.ESTAT_PETICIO_ARXIVANT) {
							iconList.add("fas fa-archive");
						}else {
	                        iconList.add("fas fa-file-signature");
						}
                    }
                break;
                case Constants.ESTAT_PETICIO_ERROR:
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                case Constants.ESTAT_PETICIO_REBUTJADA:
                    color = "red";
                    if (estat == Constants.ESTAT_PETICIO_ERROR_ARXIVANT) {
                        iconList.add("fas fa-archive");
					} else if (estat == Constants.ESTAT_PETICIO_REBUTJADA) {
						iconList.add("fas fa-times-circle");
					} else if (estat == Constants.ESTAT_PETICIO_ERROR) {
						iconList.add("fas fa-exclamation-triangle");
					}
                break;

                default:
                    color = "#f128";
                    iconList.add("fas fa-question");
                break;
            }

            StringBuffer iconsStr = new StringBuffer();

            String title = I18NUtils.tradueix("estat." + estat);
            for (String i : iconList) {
                iconsStr.append("<i class='" + i + "' style='color:" + color + ";' title='" + title + "'></i>");
            }

            String estatText = "<span class='estatText'>" + title + "</span>";
            
            long peticioID = peticio.getPeticioID();
            
            String background;
            
            switch (color) {
			case "red":
				background = "#ffc0c0";
				break;

			case "orange":
				background = "#fddb9b";
				break;
			case "green":
				background = "#b5ffb5";
				break;
			default:
				background = "#ddd";
				break;
			}
            
            mapRemitent.put(peticioID, "<div style=\"background: " + background + ";\" class='estatInfo'>" + iconsStr.toString() + estatText + "</div>");

            //Gestió annexos
            {
                Long annexes = infoAnexEjb.count(PETICIOID.equal(peticioID));
                if (annexes > 0) {
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-folder-open",
                            "user.veureannexes",
                            "/" + (isAdmin() ? "admin" : "user") + "/infoAnnex/mostrarAnnexes/" + peticioID + "/toList",
                            AdditionalButtonStyle.INFO));
                }
            }

            switch (estat) {

                case Constants.ESTAT_PETICIO_EN_PROCES:
				case Constants.ESTAT_PETICIO_PROCESANT_CALLBACK:

                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-user-friends",
                            "flux.info", "javascript:openModalFluxInfo(" + peticioID + ");", AdditionalButtonStyle.INFO));

                break;
                case Constants.ESTAT_PETICIO_ERROR_ARXIVANT:
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-redo-alt ",
                            "arxiu.reintentar", "javascript:reintentarArxivat(" + peticioID + ")", AdditionalButtonStyle.WARNING));
                break;

                case Constants.ESTAT_PETICIO_ERROR_TANCANT_EXPEDIENT:
                case Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT:
                case Constants.ESTAT_PETICIO_FIRMADA:

                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-envelope ",
                            "peticio.btn.sendmail", "javascript:cridaEmail(" + peticioID + ")", AdditionalButtonStyle.SUCCESS));

                    String csv = infoArxiuEjb.executeQueryOne(InfoArxiuFields.CSV,
                            InfoArxiuFields.INFOARXIUID.equal(peticio.getInfoArxiuID()));
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fas fa-print", "download.arxivat.imprimible",
                                    getContextWeb() + "/descarregarimprimible/" + csv, AdditionalButtonStyle.INFO));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-file-pdf",
                            "download.arxivat.firmat", getContextWeb() + "/descarregarfirmat/" + csv, AdditionalButtonStyle.INFO));
                    filterForm.addAdditionalButtonByPK(peticioID, new AdditionalButton("fas fa-vote-yea",
                            "download.arxivat.eni", getContextWeb() + "/descarregarenidoc/" + csv, AdditionalButtonStyle.INFO));
                break;
                default:
                break;
            }

            //Si està a Arxiu, no es pot esborrar.
            if (peticio.getInfoArxiuID() == null) {
                String reason = peticio.getReason();

                // Si la petició està en procés i ja té una firma, no es pot esborrar. Al camp reason es guarda si hi ha alguna firma
                if (estat == Constants.ESTAT_PETICIO_EN_PROCES && reason != null
                        && reason.equals(Constants.FIRMADA_PARCIAL)) {
                    log.info("La peticio " + peticio.getPeticioID() + " ja te una firma i no es pot esborrar");
                } else {
                    filterForm.addAdditionalButtonByPK(peticioID,
                            new AdditionalButton("fas fa-trash ", "peticio.btn.delete", "javascript: openModal('"
                                    + request.getContextPath() + getContextWeb() + "/" + peticioID + "/delete','show')",
                                    AdditionalButtonStyle.DANGER));
                }
            }
        }
    }

    @RequestMapping(value = "/tancarexpedient/{peticioId}/{windowUrl}", method = RequestMethod.GET)
    public String tancarExpedient(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId, @PathVariable("windowUrl") String windowUrl) {

        try {
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

            peticioLogicaEjb.tancarExpedientPeticio(peticioId,
                    Configuracio.getUrlBase(decodedUrl, request.getContextPath()));

            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.tancarexpedient.success"));

        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

	@RequestMapping(value = "/reintentararxivat/{peticioId}/{windowUrl}", method = RequestMethod.GET)
	public String reintentarArxivat(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("peticioId") Long peticioID, @PathVariable("windowUrl") String windowUrl) {

		// URL de redirección por defecto en caso de error
		String returnUrl = "redirect:" + getContextWeb() + "/list";

		try {
			// Decodificar la URL que llega codificada en base64
			String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));
			log.info("Decoded URL: " + decodedUrl);

			// Obtener la URL base para la petición
			String url = Configuracio.getUrlBase(decodedUrl, request.getContextPath());
			log.info("Base URL: " + url);

			// Obtener el ID de la firma asociada a la petición
			Long infoSignaturaID = peticioLogicaEjb.executeQueryOne(PeticioFields.INFOSIGNATURAID,
					PeticioFields.PETICIOID.equal(peticioID));

			if (infoSignaturaID == null) {
				String errorMsg = "Error: infoSignaturaID is null";
				log.error(errorMsg);
				HtmlUtils.saveMessageError(request, errorMsg);
				return returnUrl; // Retornar en caso de error
			}

			// Intentar guardar la petición de archivo
			String saveResult = peticioLogicaEjb.reintentGuardarPeticioArxiu(peticioID, infoSignaturaID, url);
			if (saveResult == null) {
				// Mensaje de éxito
				HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.reintent.success"));
			} else {
				// Mensaje de error al guardar
				HtmlUtils.saveMessageError(request, saveResult);
				return returnUrl; // Retornar en caso de error
			}

		} catch (Exception e) {
			log.error("Error: " + e.getMessage(), e);
			String msg = e instanceof I18NException ? I18NUtils.getMessage((I18NException) e) : e.getMessage();
			HtmlUtils.saveMessageError(request, msg);
		}

		return returnUrl;
	}
	
    @RequestMapping(value = "/reintentartancamentexpedient/{peticioId}/{windowUrl}", method = RequestMethod.GET)
    public String reintentarTancamentExpedient(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") Long peticioId, @PathVariable("windowUrl") String windowUrl) {

        try {
            // Decodificam la URL que arriba en base64
            String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

            peticioLogicaEjb.reintentarTancarExpedient(peticioId,
                    Configuracio.getUrlBase(decodedUrl, request.getContextPath()));

            HtmlUtils.saveMessageSuccess(request, I18NUtils.tradueix("peticio.arxiu.tancarexpedient.reintent.success"));

        } catch (I18NException e) {
            String msg = I18NUtils.getMessage(e);
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        } catch (Exception e) {
            String msg = e.getMessage();
            log.error(msg, e);
            HtmlUtils.saveMessageError(request, msg);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @RequestMapping(value = "/enviaremail/{peticioId}/{email}/{windowUrl}", method = RequestMethod.GET)
    public String enviarEmail(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioId") long peticioId, @PathVariable("email") String email,
            @PathVariable("windowUrl") String windowUrl) {
        final boolean isHTML = true;

        // Decodificam el email arriba en base64
        String decodedEmail = new String(Base64.getDecoder().decode(email));

        // Decodificam la URL que arriba en base64
        // String decodedUrl = new String(Base64.getDecoder().decode(windowUrl));

        try {

            // Recuperacio del fitxer firmat a partir del ID de peticio
            Peticio peticio = peticioLogicaEjb.findByPrimaryKeyPublic(peticioId);

            // Si a petició s'ha arxivat correctament, s'ha de passar el link amb CSV:
            if (peticio.getEstat() != Constants.ESTAT_PETICIO_FIRMADA
                    && peticio.getEstat() != Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT) {
                throw new I18NException("genapp.comodi", "No e spot enviar email de peticio no finalitzada");
            }

            InfoArxiuJPA ia = infoArxiuEjb.findByPrimaryKey(peticio.getInfoArxiuID());
            String fileUrl = ia.getCsvValidationWeb() + "view.xhtml?hash=" + ia.getCsv();

            UsuariJPA user = usuariLogicaEjb.findByPrimaryKeyPublic((Long) peticio.getSolicitantID());
            String nomSolicitant = user.getNom() + " " + user.getLlinatge1()
                    + (user.getLlinatge2() == null ? "" : " " + user.getLlinatge2());

            Map<String, Object> map = new HashMap<String, Object>();

            map.put("nomFitxer", peticio.getFitxer().getNom());
            map.put("numeroPeticio", "" + peticio.getPeticioID());
            map.put("titolPeticio", peticio.getNom());
            map.put("fileUrl", fileUrl);
            map.put("solicitantNom", nomSolicitant);

            String subject = I18NUtils.tradueix("email.download.file.subject4");
            String message = I18NUtils.tradueix("email.download.file.message4");

            subject = TemplateEngine.processExpressionLanguage(subject, map);
            message = TemplateEngine.processExpressionLanguage(message, map);

            String remitent = user.getEmail();
            
            EmailUtil.postMail(subject, message, isHTML, remitent, decodedEmail);
            String successMsg = "S'ha enviat el email correctament";
            HtmlUtils.saveMessageSuccess(request, successMsg);

        } catch (MalformedURLException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.url");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (IOException e) {
            String msg = I18NUtils.tradueix("email.download.file.error.message");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        } catch (Exception e) {
            String msg = I18NUtils.tradueix("email.download.file.error.send");
            HtmlUtils.saveMessageError(request, msg);
            log.error(msg, e);
        }

        return "redirect:" + getContextWeb() + "/list";
    }

    @Override
    public void delete(HttpServletRequest request, Peticio peticio) throws I18NException {
    	//Comprovar l'estat abans d'esborrar.
    	
    	if (peticio.getInfoArxiuID() != null) {
    		throw new I18NException("genapp.comodi", "No es pot esborrar una petició guardad a Arxiu");
		}
    
        peticioLogicaEjb.deleteFull(peticio);
    }

    final static String PDF = "PDF";
    final static String XML = "XML";
    final static String ENI = "ENI";
    
    @RequestMapping(value = "/descarregarfirmat/{csv}", method = RequestMethod.GET)
    public void descarregarFirmat(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = PDF;
        final String docName = "_firmat";
        TipusFile tipusFile = TipusFile.FIRMAT;

        prepareAndDownload(csv, response, format, docName, tipusFile);
    }

    @RequestMapping(value = "/descarregarenidoc/{csv}", method = RequestMethod.GET)
    public void descarregarEnidoc(@PathVariable("csv") String csv, HttpServletRequest request,
            HttpServletResponse response) throws I18NException, IOException {

        final String format = ENI;
        final String docName = "_eni";
        TipusFile tipusFile = TipusFile.ENI_DOC;

        prepareAndDownload(csv, response, format, docName, tipusFile);
    }

    @RequestMapping(value = "/descarregarimprimible/{csv}", method = RequestMethod.GET)
    public void descarregarFitxerArxiu(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("csv") String csv) throws I18NException, IOException {
        final String format = PDF;
        TipusFile tipusFile = TipusFile.VERSIO_IMPRIMIBLE;
        final String docName = "_imprimible";

        prepareAndDownload(csv, response, format, docName, tipusFile);
    }

	public void prepareAndDownload(String csv, HttpServletResponse response, final String format, final String docName,
			TipusFile tipusFile) throws I18NException, IOException {

        if (csv == null) {
            return;
        }

        String docID = infoArxiuEjb.executeQueryOne(InfoArxiuFields.ARXIUDOCUMENTID, InfoArxiuFields.CSV.equal(csv));
        log.info("internalDownload(): -> docID: " + docID);

        IArxiuPlugin plugin = pluginArxiuEjb.getInstance();

        byte[] data = null;

        switch (tipusFile) {
            case FIRMAT:
                Document firmat = plugin.documentDetalls(docID, null, true);
                data = firmat.getContingut().getContingut();
            break;

            case ENI_DOC:
                String enidoc = plugin.documentExportarEni(docID);
                data = enidoc.getBytes();
            break;

            case VERSIO_IMPRIMIBLE:
                DocumentContingut imprimible = plugin.documentImprimible(docID);
                data = imprimible.getContingut();
            break;
        }

        String fileName = generateFileName(csv, format, docName);
        
        downloadDocument(response, data, fileName, format);
    }

    
    private String generateFileName(String csv, String format, String docName) throws I18NException {
    	
    	String fileName = peticioLogicaEjb.executeQueryOne(new PeticioQueryPath().FITXER().NOM(),
                new PeticioQueryPath().INFOARXIU().CSV().equal(csv));

        if (fileName != null) {
        	
        	fileName = fileName.replace(" ", "_");
        	String loweCase = fileName.toLowerCase();
        	
            if (loweCase.endsWith(".pdf")) {
                fileName = fileName.substring(0, loweCase.lastIndexOf(".pdf"));
            }
            if (loweCase.endsWith(".xml")) {
                fileName = fileName.substring(0, loweCase.lastIndexOf(".xml"));
            }

            if (format.equals(PDF)) {
                fileName += docName + ".pdf";
            } else if (format.equals(ENI)) {
                fileName += docName + ".xml";
            }
            
        }else {
			fileName = "fitxer" + docName + "." + format.toLowerCase();
        }
        
        return fileName;
    }
    
    private void downloadDocument(HttpServletResponse response, byte[] data,  final String fileName, final String format) throws I18NException {

        response.setHeader("Content-disposition", "attachment; filename=" + fileName);
        response.setContentLength(data.length);

        if (format.equals(PDF)) {
            response.setContentType("application/pdf");

        } else if (format.equals(ENI)) {
            response.setContentType("text/xml");
        }
        
        OutputStream out;

        try {
            out = response.getOutputStream();
            out.write(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/geturlflow/{peticioID}", method = RequestMethod.GET)
    public void getURLtoFluxInfo(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("peticioID") Long peticioID) throws I18NException, IOException {

        Peticio peticio = this.peticioLogicaEjb.findByPrimaryKeyPublic(peticioID);
        long portafibID = Long.parseLong(peticio.getPeticioPortafirmes()); // XYZ ZZZ

        String lang = LocaleContextHolder.getLocale().getLanguage();
        String url = this.peticioLogicaEjb.getUrlToViewFlow(portafibID, lang);

        response.getWriter().write(url);
        response.getWriter().flush();
        response.getWriter().close();
    }
    
	final protected static HashMap<String, EstatTransaction> mapFitxersDescarrega = new HashMap<String, EstatTransaction>();
    //Last clean:
	private static long LAST_CLEAN = 0;
	private static final long CLEAN_INTERVAL = 1000 * 60 * 5; // 5 minuts
	
	protected static class EstatTransaction {
		private int estat;
		private String missatge;
		private final Long startTime;
		
//		public static final int ESTAT_OK = 0;
		public static final int ESTAT_ERROR = 1;
		public static final int ESTAT_PROCESSANT = 2;
		public static final int ESTAT_FINALITZAT = 3;
		
		
		public EstatTransaction() {
			this.estat = ESTAT_PROCESSANT;
			this.startTime = System.currentTimeMillis();
		}
		
		public int getEstat() {
			return estat;
		}

		public void setEstat(int estat) {
			this.estat = estat;
		}
		
		//get i set missatge
		public String getMissatge() {
			return missatge;
		}
		
		public void setMissatge(String missatge) {
			this.missatge = missatge;
		}

		public Long getStartTime() {
			return startTime;
		}
		
		
	}
    
    @GetMapping("/estatTransaction/{transactionID}")
	public void estatTransaction(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("transactionID") String transactionID) throws IOException {

    	// Retorn un status HTTP segons l'estat de EstatTransaction
		EstatTransaction et = mapFitxersDescarrega.get(transactionID);
		switch (et.getEstat()) {

		case EstatTransaction.ESTAT_ERROR:
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write(et.getMissatge());
			break;
			
		case EstatTransaction.ESTAT_PROCESSANT:
			// Si la darrera neteja fa més de 5 minuts, netejar.
			if (System.currentTimeMillis() - LAST_CLEAN > CLEAN_INTERVAL) {
				netejarTransaccionsCaducades();
			}
			
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			break;

		case EstatTransaction.ESTAT_FINALITZAT:
			response.setStatus(HttpServletResponse.SC_OK);
			break;

		default:
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			break;
		}

	}
    
	private void netejarTransaccionsCaducades() {
		//La neteja consisteix en eliminar les transaccions que no estan en proces de fa mes de 5 minuts.
		
		log.info("Netejan transaccions caducades. Transactions actuals: " + mapFitxersDescarrega.size());
		List<String> keysToRemove = new ArrayList<>();
		
		for (Map.Entry<String, EstatTransaction> entry : mapFitxersDescarrega.entrySet()) {
			EstatTransaction et = entry.getValue();
			if (et.getEstat() != EstatTransaction.ESTAT_PROCESSANT) {
				keysToRemove.add(entry.getKey());
			} else if (System.currentTimeMillis() - et.getStartTime() > CLEAN_INTERVAL) {
				keysToRemove.add(entry.getKey());
			}
		}
		
		log.info("Transaccions a eliminar: " + keysToRemove.size());
		for (String key : keysToRemove) {
			mapFitxersDescarrega.remove(key);
		}
		
		LAST_CLEAN = System.currentTimeMillis();

		log.info("Neteja finalitzada. Transactions actuals: " + mapFitxersDescarrega.size());
	}
    
    @GetMapping("/downloadSelectedFiles/{transactionID}")
	public void downloadSelectedFiles(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("transactionID") String transactionID) {

		try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ZipOutputStream zos = new ZipOutputStream(baos)) {

			mapFitxersDescarrega.put(transactionID, new EstatTransaction());

			String seleccionats = request.getParameter("selectedItems");
			log.info(seleccionats);

			if (seleccionats == null || seleccionats.isEmpty()) {
				String msg = "No hi ha fitxers seleccionats.";
				procesarError(transactionID, msg);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}

			IArxiuPlugin plugin = pluginArxiuEjb.getInstance();

//            processSelectedFiles(seleccionatsArray, zos, plugin);

			List<Peticio> perDescarregar = new ArrayList<>();

			for (String seleccionat : seleccionats.split(",")) {
				Long peticioID = stringToPK(seleccionat);
				Peticio peticio = peticioLogicaEjb.findByPrimaryKeyPublic(peticioID);

				// Controlar si la peticio es de l'usuari loguejat.

				if (peticio.getInfoArxiuID() == null) {
					log.info("La peticio " + peticioID + " no esta a l'Arxiu.");
					continue;
				}

				perDescarregar.add(peticio);
			}

			if (perDescarregar.size() == 0) {
				String msg = "Els fitxers selecionats no estan firmats. No es poden descarregar.";
				procesarError(transactionID, msg);
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
				return;
			}

			for (Peticio peticio : perDescarregar) {
				try {
					addPeticioToZip(peticio, zos, plugin);
				} catch (Exception e) {
					log.error("Error processant la peticio: " + peticio.getPeticioID(), e);
				}
			}

			zos.close();

			response.setHeader("Content-disposition", "attachment; filename=fitxers_seleccionats.zip");

			final byte[] zipData = baos.toByteArray();

			response.setContentLength(zipData.length);
			response.getOutputStream().write(zipData);

			log.info("Fitxers seleccionats descarregats correctament.");

			mapFitxersDescarrega.get(transactionID).setEstat(EstatTransaction.ESTAT_FINALITZAT);

		} catch (Exception e) {

			String msg;

			if (e instanceof I18NException) {
				msg = "Error al descarregar fitxers seleccionats: " + I18NUtils.getMessage((I18NException) e);
			} else {
				msg = "Error al crear el archivo ZIP: " + e.getMessage();
			}

			procesarError(transactionID, msg);
			log.error(msg, e);
		}
	}
    
    private void procesarError(String transactionID, String msg) {
		log.info(msg);
		EstatTransaction et = mapFitxersDescarrega.get(transactionID);
		et.setMissatge(msg);
		et.setEstat(EstatTransaction.ESTAT_ERROR);
    }
    
    private void addPeticioToZip(Peticio peticio, ZipOutputStream zos, IArxiuPlugin plugin) throws IOException, I18NException {
        String docID = infoArxiuEjb.executeQueryOne(InfoArxiuFields.ARXIUDOCUMENTID,
                InfoArxiuFields.INFOARXIUID.equal(peticio.getInfoArxiuID()));

        String nomFitxer = fitxerLogicEjb.executeQueryOne(FitxerFields.NOM,
                FitxerFields.FITXERID.equal(peticio.getFitxerID()));

        log.info("Descarregarem el fitxer de la PeticioID: " + peticio.getPeticioID() + " amb el docID: " + docID);

        DocumentContingut imprimible = plugin.documentImprimible(docID);
        byte[] data = imprimible.getContingut();

        ZipEntry entry = new ZipEntry(peticio.getPeticioID() + "_" + nomFitxer);
        zos.putNextEntry(entry);
        zos.write(data);
        zos.closeEntry();

        log.info("Fitxer de la peticioID: " + peticio.getPeticioID() + " descarregat correctament. bytes: " + data.length);
    }
}
