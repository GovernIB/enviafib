package es.caib.enviafib.logic.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleDocumentTypeInformation;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
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
		//Obtenir els tipus documentals de PortaFIB. Si ja s'han obtingut en els últims 30 minuts, es retorna la cache.
		
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
	
	public static List<FirmaAsyncSimpleDocumentTypeInformation> getTipusDocumentalsBase(String lang)
			 {

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

}
