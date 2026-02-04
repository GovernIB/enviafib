package es.caib.enviafib.logic.utils;

/**
 * ========================================================================
 * TODO: GESTIÓ DE FITXERS ARCHIVATS - ALLIBERAR ESPAI
 * ========================================================================
 * 
 * PROBLEMA:
 * Després d'arxivar a Arxiu, FitxerID i FitxerFirmatID ocupen espai inútil.
 * No podem esborrar-los perquè:
 *   1. FitxerID és NOT NULL a Peticio
 *   2. Hi ha FK Peticio → Fitxer
 * 
 * SOLUCIONS:
 * 
 * 1️⃣ BORRAT LÒGIC (RECOMANADA) ⭐
 *    - Afegir camps a Fitxer: arxivatID, dataBorrat, borratLogic
 *    - Marcar borratLogic=1 i esborrar arxiu físic
 *    - Mantenir registre BD (FK intacta, auditable, reversible)
 * 
 * 2️⃣ PERMETRE NULL
 *    - ALTER TABLE Peticio MODIFY FitxerID BIGINT NULL
 *    - Posar NULL i després esborrar Fitxer de BD
 *    - Perd trazabilitat però allibera tot
 * 
 * 3️⃣ TAULA HISTÒRIC
 *    - Crear FitxerArxivatHistoric per auditoría
 *    - Moure referències i esborrar Fitxer
 *    - Més complex però manté historial
 * 
 * IMPLEMENTAR: Modificar guardarFitxerArxiuSync() segons solució triada
 * ========================================================================
 */

import javax.annotation.security.PermitAll;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.InfoSignatura;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;

import java.sql.Timestamp;
import java.util.List;

@Singleton
@Startup
public class SchedulerReintentarArxivat extends AbstractScheduler {

	private static final String NOM_SCHEDULER = "reintentarArxivarTotes";
	private final long MAX_REINTENTS = Long.valueOf(Configuracio.getMaxIntentsArxivatScheduler());

	@Override
	protected String getSchedulerName() {
		return NOM_SCHEDULER;
	}

	@Override
	protected String getConfiguredHour() {
		return Configuracio.getHoraReintentArxivatScheduler();
	}

	@Override
	protected String getConfiguredHours() {
		String nHores = Configuracio.getNhoresReintentArxivatScheduler();
		return nHores != null ? nHores : "1";
	}

	@Override
	protected void executeScheduledTask(long startTime) throws Exception {
		// Llistat de peticions amb error tancant expedient, que no han superat el màxim
		// de reintents.
		Where wReintentsMenysDe = PeticioFields.REINTENTSARXIU.lessThan(MAX_REINTENTS);
		Where wReintentsNull = PeticioFields.REINTENTSARXIU.isNull();
		Where wReintents = Where.OR(wReintentsMenysDe, wReintentsNull);

		Where wEstats = PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_ERROR_ARXIVANT);
		OrderBy orderBy = new OrderBy(PeticioFields.DATAFINAL);

		List<Peticio> peticions = peticioLogicaEjb.select(Where.AND(wEstats, wReintents), orderBy);
		log.info("Peticions que s'han d'arxivar: " + peticions.size() + ". maxReintents: " + MAX_REINTENTS);

		int i = 1;
		String urlBase = Configuracio.getUrlBase();

		long unaHora = 1000 * 60 * 60;
		
		for (Peticio peticio : peticions) {
			Long peticioID = peticio.getPeticioID();

			log.info("Reintentant arxivat " + i + " de " + peticions.size() + ". PeticioID: " + peticioID
					+ " Reintents: " + peticio.getReintentsArxiu() + " DataFi: " + peticio.getDataFinal());

			if (peticio.getDataFinal() != null
					&& (System.currentTimeMillis() - peticio.getDataFinal().getTime()) < unaHora) {

				log.info("Ja hem intentat fa menys d'una hora.");
				i++;
				continue;
			}
			
			peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);

			InfoSignaturaJPA is = infoSignaturaLogicaEjb.findByPrimaryKeyPublic(peticio.getInfoSignaturaID());
			peticio = guardarFitxerArxiuSync(peticio, is, urlBase);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}

			// El Timeout son 5 minuts. Si el CRON s'executa durant 4 min, surt del for i
			// acaba la funció.
			if (isTransactionTimeout(startTime)) {
				log.warn("Timeout. Hem processat " + i + " expedients");
				break;
			}
			i++;
		}
	}

	@PermitAll
	@Asynchronous
	public void guardarPeticioArxiuAsync(Peticio peticio, InfoSignatura infoSignatura, String urlBase)
			throws I18NException {

		peticio.setEstat(Constants.ESTAT_PETICIO_ARXIVANT);
		peticioLogicaEjb.updatePublic(peticio);

		long peticioID = peticio.getPeticioID();
		try {
			Thread.sleep(1000);
			log.info("Guardant Peticio " + peticioID + " dins d'Arxiu.");
			peticio = guardarFitxerArxiuSync(peticio, infoSignatura, urlBase);
		} catch (Exception e) {
			// XYZ ZZZ TMP
			log.error("Future.get() ha llança un error: " + e.getMessage(), e);
			peticio = null;
		}
	}

	protected Peticio guardarFitxerArxiuSync(Peticio peticio, InfoSignatura infoSignatura,
			String urlBase) throws I18NException {
		log.info(" guardarFitxerArxiu:: START");

		long start = System.currentTimeMillis();

		// No llança errors. Només torna InforArxiu null si hi ha hagut un error
		// Ja inicialitza Petició amb l'estat com toca i guarda resultat a InfoArxiu
		InfoArxiuJPA ia = pluginArxiuLogicaEjb.custodiaAmbApiArxiu(peticio, infoSignatura);

		if (ia != null) {
			peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
//            peticio.setEstat(Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT);
			peticio.setErrorMsg(null);
			peticio.setErrorException(null);
			peticio.setReintentsArxiu(0L);

			try {
				peticioLogicaEjb.enviarMailSolicitant(peticio, urlBase);
			} catch (Exception e) {
				log.error("Error enviant correu: " + e.getMessage(), e);
			}
		} else {
			long reintents = peticio.getReintentsArxiu() == null ? 0 : peticio.getReintentsArxiu();
			reintents++;
			peticio.setReintentsArxiu(reintents);
		}

		peticio.setDataFinal(new Timestamp(System.currentTimeMillis()));
		peticioLogicaEjb.updatePublic(peticio);

		log.info("guardarFitxerArxiu:: END " + (System.currentTimeMillis() - start) + " ms");
		return peticio;
	}

}
