package es.caib.enviafib.logic.utils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import javax.ejb.*;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
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
import java.util.Locale;

@Singleton
@Startup
public class SchedulerReintentarArxivat {

	public final Logger log = Logger.getLogger(this.getClass());

	final long TRANSACTION_EXIT_IN_MILI = 4 * 60 * 1000; // 4 minuts
	final long MAX_REINTENTS = Long.valueOf(Configuracio.getMaxIntentsArxivatScheduler());
	final String NOM_SCHEDULER = "reintentarArxivarTotes";

	@Resource
	private TimerService timerService;

	@EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuLogicaEjb;

	@EJB(mappedName = es.caib.enviafib.logic.InfoArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.InfoArxiuLogicaService infoArxiuLogicEjb;

	@EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

	@EJB(mappedName = es.caib.enviafib.logic.InfoSignaturaLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.InfoSignaturaLogicaService infoSignaturaLogicaEjb;

	
	@PostConstruct
	public void init() {
		// Configurar la tarea con valores dinámicos

		String horaStr = Configuracio.getHoraReintentArxivatScheduler(); // 14
		String nHoresStr = Configuracio.getNhoresReintentArxivatScheduler(); // 2

		int nHores = Integer.parseInt(nHoresStr);
		if (nHores > 1) {
			int hores = Integer.parseInt(horaStr);
			horaStr += "-" + (hores + nHores - 1);
		}

		log.info("initScheduler:: " + NOM_SCHEDULER +" a les " + horaStr + " hores");
		scheduleTask(horaStr);
	}

	public void scheduleTask(String horaStr) {

		// Limpiar timers anteriores
		for (Timer timer : timerService.getTimers()) {
			timer.cancel();
		}
		ScheduleExpression schedule = new ScheduleExpression();
		schedule.hour(horaStr);
		schedule.minute("*/5");

		Timer newTimer = timerService.createCalendarTimer(schedule);
		System.out.println("CREAT Schedule per " + NOM_SCHEDULER + ": " + newTimer.getNextTimeout());
	}

	@Timeout
	public void onTimeout(Timer timer) {
		log.info("Inici " + NOM_SCHEDULER + "()");

		long startTime = System.currentTimeMillis();

		// El timeout de EJB son 5 minuts, li direm que als 4 minuts surti.
		try {

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
				if ((System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI) {
					log.warn("Timeout. Hem processat " + i + " expedients");
					break;
				}
				i++;
			}
		} catch (I18NException e) {

			final String languageUI = "ca";

			final String msg = "Error obtenint llistat de fitxersFirmatsID durant el cron nocturn: "
					+ I18NCommonUtils.getMessage(e, new Locale(languageUI));
			log.error(msg, e);
		}

		long endTime = System.currentTimeMillis();
		log.info("Total time: " + (endTime - startTime));
		log.info("Acaba " + NOM_SCHEDULER + "()");
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
