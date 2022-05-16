package es.caib.enviafib.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.security.PermitAll;
import javax.ejb.EJB;

/*
import es.caib.enviafib.ejb.AnnexEJB;
import es.caib.enviafib.ejb.FitxerService;
import es.caib.enviafib.persistence.AnnexJPA;
import es.caib.enviafib.model.entity.AnnexFirmat;
import es.caib.enviafib.model.fields.AnnexFields;
import es.caib.enviafib.model.fields.AnnexFirmatFields;

import org.fundaciobit.genapp.common.i18n.I18NException;

*/

import javax.ejb.Stateless;

import org.fundaciobit.apisib.apifirmaasyncsimple.v2.ApiFirmaAsyncSimple;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleAnnex;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleMetadata;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleReviser;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignature;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureBlock;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestBase;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestInfo;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignatureRequestWithSignBlockList;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSignedFile;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.beans.FirmaAsyncSimpleSigner;
import org.fundaciobit.apisib.apifirmaasyncsimple.v2.jersey.ApiFirmaAsyncSimpleJersey;
import org.fundaciobit.apisib.core.exceptions.AbstractApisIBException;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.pluginsib.core.utils.FileUtils;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.ejb.PeticioEJB;
import es.caib.enviafib.model.entity.Fitxer;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.PeticioJPA;

/**
 * 
 * @author fbosch
 *
 */
@Stateless(name = "PeticioLogicaEJB")
public class PeticioLogicaEJB extends PeticioEJB implements PeticioLogicaService {

	@EJB(mappedName = es.caib.enviafib.ejb.FitxerService.JNDI_NAME)
	protected es.caib.enviafib.ejb.FitxerService fitxerEjb;

	public PeticioLogicaEJB() {

	}

	@Override
	public void arrancarPeticio(long peticioID, String languageUI) throws I18NException {

		Peticio peticio = this.findByPrimaryKey(peticioID);

		String nifDestinatari = peticio.getDestinatarinif();
		String perfil = "ENVIAFIB_PADES";
		FirmaAsyncSimpleFile fitxerAFirmar = getFitxer(peticio.getFitxer());

		FirmaAsyncSimpleFile fitxerAAnexar = null;
		ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();

		Long idPortafib;
		try {
			idPortafib = createSignatureRequestAndStart(languageUI, nifDestinatari, perfil, fitxerAFirmar,
					fitxerAAnexar, api);
		} catch (Exception e) {
			throw new I18NException("genapp.comodi", "Error creant peticio de firma dins PortaFIB: " + e.getMessage());
		}
		peticio.setPeticioPortafib(idPortafib);
		this.update(peticio);
	}

	@Override
	public long guardarFitxerSignat(long peticioID, String languageUI ) throws I18NException, AbstractApisIBException, IOException {
		
		Peticio peticio = this.findByPrimaryKey(peticioID);
		
		FirmaAsyncSimpleFile firma = getFitxerSignat(peticioID, languageUI);
		
		String nom = firma.getNom();	
		String mime = firma.getMime();
		byte[] data = firma.getData();

		Fitxer fdb = fitxerEjb.create(nom, mime, data.length, null);
	
		Long idfitxer = fdb.getFitxerID();

		File fitxersignat = FileSystemManager.getFile(idfitxer);
		FileOutputStream fos = new FileOutputStream(fitxersignat);
		fos.write(data);
		fos.flush();
		fos.close();

		peticio.setFitxerFirmatID(idfitxer);
		this.update(peticio);

		return idfitxer;
	}

	@Override
	@PermitAll
	public void updatePublic(Peticio peticio) throws I18NException {
		super.update(peticio);
	}

	public Long createSignatureRequestAndStart(String languageUI, String nifDestinatari, String perfil,
			FirmaAsyncSimpleFile fitxerAFirmar, FirmaAsyncSimpleFile fitxerAAnexar, ApiFirmaAsyncSimple api)
			throws Exception {

		FirmaAsyncSimpleSignatureBlock[] signatureBlocks = null;

		String[][] destinataris = new String[][] { { nifDestinatari } };

		if (destinataris == null || destinataris.length == 0) {
			throw new Exception("S'ha de definir la propietat nifsDestinataris dins test.properties");
		}

		signatureBlocks = new FirmaAsyncSimpleSignatureBlock[destinataris.length];

		for (int i = 0; i < destinataris.length; i++) {
			String[] destinatarisBloc = destinataris[i];
			if (destinatarisBloc == null || destinatarisBloc.length == 0) {
				throw new Exception("Els destinataris del bloc " + i + " està buit o val null");
			}
			System.out.println("BLOC[" + i + "] => Destinataris = " + Arrays.toString(destinatarisBloc));
			List<FirmaAsyncSimpleSignature> signers = new ArrayList<FirmaAsyncSimpleSignature>();
			for (int j = 0; j < destinatarisBloc.length; j++) {

				String nif = destinatarisBloc[j].trim();

				if (nif.trim().length() == 0) {
					throw new Exception("El destinatari " + j + " del bloc " + i + " està buit o val null");
				}

				FirmaAsyncSimpleSigner personToSign;

				personToSign = new FirmaAsyncSimpleSigner();
				personToSign.setAdministrationID(nif);

				boolean required = true;
				String reason = null; // Usar la de la Petició

				// Revisors
				int minimumNumberOfRevisers;
				List<FirmaAsyncSimpleReviser> revisers;

				minimumNumberOfRevisers = 0;
				revisers = null;

				signers.add(new FirmaAsyncSimpleSignature(personToSign, required, reason, minimumNumberOfRevisers,
						revisers));

			}

			int minimumNumberOfSignaturesRequired = signers.size();
			signatureBlocks[i] = new FirmaAsyncSimpleSignatureBlock(minimumNumberOfSignaturesRequired, signers);

		}

		// Annexes
		List<FirmaAsyncSimpleAnnex> annexs = null;
		{
			FirmaAsyncSimpleFile file = fitxerAAnexar;
			if (file != null) {
				boolean attach = true;
				boolean sign = true;
				FirmaAsyncSimpleAnnex annex = new FirmaAsyncSimpleAnnex(file, attach, sign);
				annexs = new ArrayList<FirmaAsyncSimpleAnnex>();
				annexs.add(annex);
			}
		}

		// Fitxer a Firmar
		if (fitxerAFirmar == null) {
			throw new Exception("No s'ha definit fitxer a firmar");
		}

		FirmaAsyncSimpleSignatureRequestInfo rinfo = null;

		String profileCode = perfil;
		String title = "Peticio de Firma Simple Async - " + ((System.currentTimeMillis() / 1000) % 100000);
		String description = "Prova de firma - Desc";
		String reason = "Prova de firma - reason";
		FirmaAsyncSimpleFile originalDetachedSignature = null;
		long documentType = 8; // TD08 Publicación.
		String documentTypeDescription = "Publicació";
		String languageDoc = "ca";
		int priority = FirmaAsyncSimpleSignatureRequestWithSignBlockList.PRIORITY_NORMAL_NORMAL;
		String senderName = "Tester Firma Async";
		String senderDescription = "Tester Firma Async - Description";
		String expedientCode = null;
		String expedientName = null;
		String expedientUrl = null;
		String procedureCode = null;
		String procedureName = null;
		String additionalInformation = "Ninguna info";
		Double additionalInformationEvaluable = (double) System.currentTimeMillis();

		List<FirmaAsyncSimpleMetadata> metadadaList = null;

		FirmaAsyncSimpleSignatureRequestBase signatureRequestBase;
		signatureRequestBase = new FirmaAsyncSimpleSignatureRequestBase(profileCode, title, description, reason,
				fitxerAFirmar, originalDetachedSignature, documentType, documentTypeDescription, languageDoc,
				languageUI, priority, senderName, senderDescription, expedientCode, expedientName, expedientUrl,
				procedureCode, procedureName, additionalInformation, additionalInformationEvaluable, annexs,
				metadadaList);

		// Crear Peticio
		Long peticioDeFirmaID2;

		// Utilitzar Blocs de Firmes
		log.info("Petició de Firma emprant Blocs de Firmes");
		FirmaAsyncSimpleSignatureRequestWithSignBlockList signatureRequest;
		signatureRequest = new FirmaAsyncSimpleSignatureRequestWithSignBlockList(signatureRequestBase, signatureBlocks);
		peticioDeFirmaID2 = api.createAndStartSignatureRequestWithSignBlockList(signatureRequest);

		log.info("Creada peticio amb ID = " + peticioDeFirmaID2);

		rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioDeFirmaID2, languageUI);

		String url = api.getUrlToViewFlow(rinfo);
		log.info("URL to view flow: " + url);
		return peticioDeFirmaID2;

	}

	// TODO: Mostra com montar un fitxer per firmar

	protected FirmaAsyncSimpleFile getFitxer(Fitxer fitxer) throws I18NException {

		File f = FileSystemManager.getFile(fitxer.getFitxerID());

		if (!f.exists()) {
			throw new I18NException("genapp.comodi", "No existeix el fitxer " + f.getAbsolutePath());
		}

		byte[] data;
		try {
			data = FileUtils.readFromFile(f);
		} catch (Exception e) {
			throw new I18NException("genapp.comodi", "No es pot llegir el fitxer " + f.getAbsolutePath());
		}

		return new FirmaAsyncSimpleFile(fitxer.getNom(), fitxer.getMime(), data);
	}

	public FirmaAsyncSimpleFile getFitxerSignat(long peticioID, String languageUI)
			throws I18NException, AbstractApisIBException {
		FirmaAsyncSimpleSignedFile fitxerSignat = null;

		FirmaAsyncSimpleSignatureRequestInfo rinfo = null;
		
		Peticio peticio = this.findByPrimaryKey(peticioID);
		Long peticioPortafibId = peticio.getPeticioPortafib();
		
		rinfo = new FirmaAsyncSimpleSignatureRequestInfo(peticioPortafibId, languageUI);

		ApiFirmaAsyncSimple api = getApiFirmaAsyncSimple();
		
		

		fitxerSignat = api.getSignedFileOfSignatureRequest(rinfo);

		String nom = fitxerSignat.getSignedFile().getNom();
		String mime = fitxerSignat.getSignedFile().getMime();
		byte[] data = fitxerSignat.getSignedFile().getData();

		return fitxerSignat.getSignedFile();
	}

	public 
	
	public ApiFirmaAsyncSimple getApiFirmaAsyncSimple() {

		String host = Configuracio.getPortafibGatewayV2();
		String username = Configuracio.getPortafibUsername();
		String password = Configuracio.getPortafibPassword();

		System.out.println(" Connectant amb " + host + " emprant l'usuari " + username);

		ApiFirmaAsyncSimpleJersey api;

		api = new ApiFirmaAsyncSimpleJersey(host, username, password);

		// api.setConnectionTimeoutMs(20000); // 20 segons
		// api.setReadTimeoutMs(20000); // 20 segons

		return api;
	}

	// TODO: Per esborrar peticio:
	/*
	 * 
	 * // Esperam a que les notificacions s'enviin
	 * System.out.println(" Esperam a que les notificacions s'enviin ."); for (int i
	 * = 0; i < 20; i++) { System.out.print("."); Thread.sleep(500); }
	 * System.out.println();
	 * 
	 * // Esborrar la petició api.deleteSignatureRequest(rinfo); }
	 * 
	 */
}
