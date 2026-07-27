package es.caib.enviafib.logic.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.ApiFlowTemplateSimple;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFilterGetAllByFilter;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplate;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateList;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleFlowTemplateRequest;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.beans.FlowTemplateSimpleKeyValue;
import org.fundaciobit.apisib.apiflowtemplatesimple.v1.jersey.ApiFlowTemplateSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;

public class PortafibUtils {

	private static List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocumentals = new ArrayList<FirmaAsyncSimpleDocumentTypeInformation>();
	private static long lastRefresh = 0;

	private static final long MITJA_HORA = 30 * 60 * 1000;
	protected static final Logger log = Logger.getLogger(PortafibUtils.class);

	public static synchronized List<FirmaAsyncSimpleDocumentTypeInformation> getTipusDocumentalsAll(String lang) {
		// Obtenir els tipus documentals de PortaFIB. Si ja s'han obtingut en els últims
		// 30 minuts, es retorna la cache.

		if ((lastRefresh + MITJA_HORA) < System.currentTimeMillis()) {

			try {
				ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

				tipusDocumentals = api.getAvailableTypesOfDocuments(lang);
				lastRefresh = System.currentTimeMillis();

				log.info("Obtenint tipus documentals de PortaFIB. " + tipusDocumentals.size() + " elements.");
			} catch (Throwable e) {
				log.error("Error obtenint tipus documentals de PortaFIB: " + e.getMessage(), e);
//			throw new I18NException("error.portafib.tipusdocumental", e.getMessage());
			}
		} else {
			log.info("Utilitzant cache de tipus documentals. " + tipusDocumentals.size() + " elements.");
		}

		return tipusDocumentals;
	}

	public static List<FirmaAsyncSimpleDocumentTypeInformation> getTipusDocumentalsBase(String lang) {

		List<FirmaAsyncSimpleDocumentTypeInformation> allTipusDoc = getTipusDocumentalsAll(lang);
		List<FirmaAsyncSimpleDocumentTypeInformation> tipusDocsBase = new java.util.ArrayList<FirmaAsyncSimpleDocumentTypeInformation>();

		for (FirmaAsyncSimpleDocumentTypeInformation tipusDoc : allTipusDoc) {
			if (tipusDoc.getDocumentType() == tipusDoc.getDocumentTypeBase()) {
				tipusDocsBase.add(tipusDoc);
			}
		}

		return tipusDocsBase;
	}

	// XYZ XXX Pasar mètode a classe PortafibUtils
	public static ApiFirmaAsyncSimple getApiFirmaAsyncSimple() throws I18NException {

		String host = Configuracio.getPortafibGatewayV2();
		String username = Configuracio.getPortafibUsername();
		String password = Configuracio.getPortafibPassword();

		ApiFirmaAsyncSimpleJersey api;

		try {
			new URL(host);
			api = new ApiFirmaAsyncSimpleJersey(host, username, password);

		} catch (MalformedURLException urle) {
			String errorMsg = "Error a la URL de conexió amb PortaFIB. Revisar la URL de la propietat "
					+ Constants.ENVIAFIB_PROPERTY_BASE + "portafib.apifirmaasync.url" + " de l'arxiu: "
					+ Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.";

			throw new I18NException(errorMsg + "   -   " + urle.getMessage());
		} catch (Exception e) {
			throw new I18NException("error.portafib.conexio.api",
					Constants.ENVIAFIB_PROPERTY_BASE + "system.properties.", e.getMessage());
		}

		// api.setConnectionTimeoutMs(20000); // 20 segons
		// api.setReadTimeoutMs(20000); // 20 segons

		return api;
	}

	public static ApiFlowTemplateSimple getApiFlowTemplateSimple() {

		String url = Configuracio.getPortaFIBApiFlowUrl();
		String username = Configuracio.getPortaFIBApiFlowUsername();
		String password = Configuracio.getPortaFIBApiFlowPassword();
		// log.info(" Connectant amb " + url + " emprant l'usuari " + username);

		return new ApiFlowTemplateSimpleJersey(url, username, password);
	}

	public static List<FluxInfo> getPlantillesFluxByUsername(String userID) {

		// Obtenir les plantilles de flux de PortaFIB per usuari
		// Primer cercam si el tenim a la cache.
		// Si no el tenim a la cache, cercam a PortaFIB

		if (fluxosCache.containsKey(userID)) {
			log.info("Retornant plantilles de flux de PortaFIB des de la cache per usuari " + userID);
			return fluxosCache.get(userID);
		}

		final String usrapp = Configuracio.getPortaFIBApiFlowUsername();

		List<FlowTemplateSimpleKeyValue> plantilles = new ArrayList<FlowTemplateSimpleKeyValue>();

		List<FluxInfo> llistat = new ArrayList<FluxInfo>();
		try {
			ApiFlowTemplateSimple api = PortafibUtils.getApiFlowTemplateSimple();
			final String languageUI = "ca";

			FlowTemplateSimpleFilterGetAllByFilter filter = new FlowTemplateSimpleFilterGetAllByFilter();
			filter.setLanguageUI(languageUI);

			// Cercam per usuari aplicació i despres ja cercarem per {temporal=true}
			String descriptionFilter = "{usrapp=" + usrapp + "}" + (userID == null ? "" : "{owner=" + userID + "}");
			filter.setDescriptionFilter(descriptionFilter);

			log.info("Obtenint plantilles de flux de PortaFIB per usuari " + userID);
			FlowTemplateSimpleFlowTemplateList list = api.getAllFlowTemplatesByFilter(filter);

			plantilles = list.getList();

			for (FlowTemplateSimpleKeyValue flowKeyValue : plantilles) {
				log.debug("Plantilla de flux trobada: " + flowKeyValue.getKey() + " - " + flowKeyValue.getValue());

				FluxInfo info = crearFluxInfoFromFlowTemplate(api, flowKeyValue, userID);
				llistat.add(info);
			}
			
			// Guardam a la cache
			fluxosCache.put(userID, llistat);
			log.info("Retornant plantilles de flux de PortaFIB per usuari " + userID + ". " + llistat.size()
					+ " plantilles trobades.");

		} catch (Throwable e) {
			log.error("Error obtenint les plantilles de flux de PortaFIB: " + e.getMessage(), e);
		}

		return llistat;
	}

	private static FluxInfo crearFluxInfoFromFlowTemplate(ApiFlowTemplateSimple api,
			FlowTemplateSimpleKeyValue flowKeyValue, String userID) throws AbstractApisIBException {

		String flowTemplateId = flowKeyValue.getKey();

		FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
		flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest("ca", flowTemplateId);

		log.info("Obtenint informació de la plantilla de flux " + flowTemplateId);
		FlowTemplateSimpleFlowTemplate flux = api.getFlowInfoByFlowTemplateID(flowTemplateRequest);

		// Crear un objecte FluxInfo a partir d'un FlowTemplateSimpleKeyValue

		String fluxID = flowKeyValue.getKey();
		String nom = flowKeyValue.getValue();
		String desc = flux.getDescription();
		long dataCad = 0;

		FluxInfo info = new FluxInfo(fluxID, nom, desc, dataCad, userID);

		return info;
	}
	
	public static boolean esborrarFlux(FluxInfo flux) throws AbstractApisIBException {
		final String languageUI = "ca";

		ApiFlowTemplateSimple api = PortafibUtils.getApiFlowTemplateSimple();

		String flowTemplateId = flux.getFluxID();

		FlowTemplateSimpleFlowTemplateRequest flowTemplateRequest;
		flowTemplateRequest = new FlowTemplateSimpleFlowTemplateRequest(languageUI, flowTemplateId);

		boolean esborrat = api.deleteFlowTemplate(flowTemplateRequest);

		if (esborrat) {
			// Si s'ha esborrat, l'eliminam de la cache
			List<FluxInfo> llistatUsuari = fluxosCache.get(flux.getOwner());

			for (FluxInfo f : llistatUsuari) {
				if (f.getFluxID().equals(flux.getFluxID())) {
					llistatUsuari.remove(f);
					log.info("Eliminada plantilla de flux " + flux.getFluxID() + " de la cache d'usuari "
							+ flux.getOwner());
					break;
				}
			}
		}

		return esborrat;
	}
	
	public static FluxInfo getFluxByID(String userID, String fluxID) {
		// Cercam el flux a la cache
		if (fluxosCache.containsKey(userID)) {
			List<FluxInfo> llistat = fluxosCache.get(userID);
			for (FluxInfo f : llistat) {
				if (f.getFluxID().equals(fluxID)) {
					return f;
				}
			}
		}

		return null;
	}
	
	public static FluxInfo creaFluxInfo(String userID, String fluxID, String nom, String desc) {
		FluxInfo info = new FluxInfo(fluxID, nom, desc, 0, userID);

		// Afegim a la cache
		if (fluxosCache.containsKey(userID)) {
			List<FluxInfo> llistat = fluxosCache.get(userID);
			llistat.add(info);
		} else {
			List<FluxInfo> llistat = new java.util.ArrayList<FluxInfo>();
			llistat.add(info);
			fluxosCache.put(userID, llistat);
		}

		return info;
	}

	final static Map<String, List<FluxInfo>> fluxosCache = new java.util.HashMap<String, List<FluxInfo>>();

	public static class FluxInfo {
		private long dataCaducitat;
		private String fluxID;
		private String nom;
		private String desc;
		private String owner;
		Timestamp dataCreacio;

		public FluxInfo(String fluxID, String nom, String desc, long dataCaducitat, String owner) {
			this.fluxID = fluxID;
			this.nom = nom;
			this.desc = desc;
			this.dataCaducitat = dataCaducitat;
			this.owner = owner;
		}

		public long getDataCaducitat() {
			return dataCaducitat;
		}

		public String getFluxID() {
			return fluxID;
		}

		public String getNom() {
			return nom;
		}

		public String getDescription() {
			return desc;
		}

		public String getOwner() {
			return owner;
		}
		
		
		public void setDataCaducitat(long dataCaducitat) {
			this.dataCaducitat = dataCaducitat;
		}

		public void setFluxID(String fluxID) {
			this.fluxID = fluxID;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}
	}

	public static void actualizarDadesFlux(String owner, String fluxID) {
		
		// Actualitzar les dades del flux a la cache
		
		if (fluxosCache.containsKey(owner)) {
			List<FluxInfo> llistat = fluxosCache.get(owner);
			for (FluxInfo f : llistat) {
				if (f.getFluxID().equals(fluxID)) {
					try {
						FluxInfo info = crearFluxInfoFromFlowTemplate(getApiFlowTemplateSimple(),
								new FlowTemplateSimpleKeyValue(fluxID, f.getNom()), owner);

						llistat.remove(f);
						llistat.add(info);
						
						log.info("Actualitzades dades de la plantilla de flux " + fluxID + " de la cache d'usuari "
								+ owner);
					} catch (AbstractApisIBException e) {
						log.error("Error actualitzant les dades de la plantilla de flux " + fluxID
								+ " de la cache d'usuari " + owner + ": " + e.getMessage(), e);
					}
					break;
				}
			}
		}

		
	}
	
}
