package es.caib.enviafib.logic.utils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.InfoArxiuFields;
import es.caib.enviafib.model.fields.PeticioFields;
import es.caib.pluginsib.arxiu.api.IArxiuPlugin;

import java.util.List;
import java.util.Locale;

@Singleton
@Startup
public class SchedulerTancarExpedients {

	public final Logger log = Logger.getLogger(this.getClass());

	final long TRANSACTION_EXIT_IN_MILI = 4 * 60 * 1000; // 4 minuts
	final long MAX_REINTENTS = Long.valueOf(Configuracio.getMaxIntentsTancamentExpedients());

	@Resource
	private TimerService timerService;

	@EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuLogicaEjb;

	@EJB(mappedName = es.caib.enviafib.logic.InfoArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.InfoArxiuLogicaService infoArxiuLogicEjb;

	@EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

	@PostConstruct
	public void init() {
		// Configurar la tarea con valores dinámicos
		
		String horaStr = Configuracio.getHoraTancamentExpedientsScheduler(); //14
		String nHoresStr = Configuracio.getNhoresTancamentExpedientsScheduler(); //2

		int nHores = Integer.parseInt(nHoresStr);
		if (nHores > 1) {
			int hores = Integer.parseInt(horaStr);
			horaStr += "-" + (hores + nHores - 1);
		}

		log.info("initScheduler:: Tancar expedients a les " + horaStr + " hores");
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
		System.out.println("CREAT Schedule per tancar expedients: " + newTimer.getNextTimeout());
	}
	
	@Timeout
	public void onTimeout(Timer timer) {
		log.info("Inici tancarTotsElsExpedients()");

		long startTime = System.currentTimeMillis();
		final String languageUI = "ca";

		// El timeout de EJB son 5 minuts, li direm que als 4 minuts surti.

		try {

			// Llistat de peticions amb error tancant expedient, que no han superat el màxim
			// de reintents.

			Where wReintentsMenysDe = PeticioFields.REINTENTSARXIU.lessThan(MAX_REINTENTS);
			Where wReintentsNull = PeticioFields.REINTENTSARXIU.isNull();
			Where wReintents = Where.OR(wReintentsMenysDe, wReintentsNull);
			
			Where wPendentTancar = PeticioFields.ESTAT.equal(Constants.ESTAT_PETICIO_PENDENT_TANCAR_EXPEDIENT);
			OrderBy orderBy = new OrderBy(PeticioFields.DATAFINAL);

			List<Peticio> peticions = peticioLogicaEjb.select(Where.AND(wPendentTancar, wReintents), orderBy);

			log.info("Expedients que s'han de tancar: " + peticions.size() + ". maxReintents: " + MAX_REINTENTS);

			IArxiuPlugin plugin = pluginArxiuLogicaEjb.getInstance();

			int i = 1;
			for (Peticio peticio : peticions) {
				Long peticioID = peticio.getPeticioID();
				log.info("Tancarem expedient " + i + " de " + peticions.size() + ". PeticioID: " + peticioID
						+ " DataFi: " + peticio.getDataFinal());

				String expedientID = infoArxiuLogicEjb.executeQueryOne(InfoArxiuFields.ARXIUEXPEDIENTID,
						InfoArxiuFields.INFOARXIUID.equal(peticio.getInfoArxiuID()));

				boolean tancatExpedient = this.pluginArxiuLogicaEjb.tancarExpedient(peticio, plugin, expedientID);
				peticioLogicaEjb.updatePublic(peticio);

				if (tancatExpedient) {
					log.info("Expedient de la petició " + peticioID + " tancat correctament. ExpedientID: "
							+ expedientID);
				} else {
					log.error("Error tancant expedient de la petició " + peticioID + ": " + peticio.getErrorMsg());

				}

				try {
					Thread.sleep(2000);
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

			final String msg = "Error obtenint llistat de fitxersFirmatsID durant el cron nocturn: "
					+ I18NCommonUtils.getMessage(e, new Locale(languageUI));
			log.error(msg, e);
		}

		long endTime = System.currentTimeMillis();
		log.info("Total time: " + (endTime - startTime));
		log.info("Acaba tancarTotsElsExpedients()");
	}
}
