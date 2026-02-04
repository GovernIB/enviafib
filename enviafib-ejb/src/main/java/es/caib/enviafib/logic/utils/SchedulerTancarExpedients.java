package es.caib.enviafib.logic.utils;

import javax.ejb.*;

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

@Singleton
@Startup
public class SchedulerTancarExpedients extends AbstractScheduler {

	private static final String NOM_SCHEDULER = "tancarExpedients";
	private final long MAX_REINTENTS = Long.valueOf(Configuracio.getMaxIntentsTancamentExpedients());

	@Override
	protected String getSchedulerName() {
		return NOM_SCHEDULER;
	}

	@Override
	protected String getConfiguredHour() {
		return Configuracio.getHoraTancamentExpedientsScheduler();
	}

	@Override
	protected String getConfiguredHours() {
		String nHores = Configuracio.getNhoresTancamentExpedientsScheduler();
		return nHores != null ? nHores : "1";
	}

	@Override
	protected void executeScheduledTask(long startTime) throws Exception {
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
			if (isTransactionTimeout(startTime)) {
				log.warn("Timeout. Hem processat " + i + " expedients");
				break;
			}
			i++;
		}
	}
}
