package es.caib.enviafib.logic.utils;

import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;
import org.fundaciobit.genapp.common.query.OrderBy;

import es.caib.enviafib.commons.utils.Configuracio;
import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.model.fields.PeticioFields;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
@Startup
public class SchedulerEsborrarFitxersArxivats extends AbstractScheduler {

    private static final String NOM_SCHEDULER = "esborrarFitxersArxivats";

    @Resource
    private javax.transaction.TransactionSynchronizationRegistry __tsRegistry;

    @Override
    protected String getSchedulerName() {
        return NOM_SCHEDULER;
    }

    @Override
    protected String getConfiguredHour() {
//        return Configuracio.getHoraEliminarFitxersArxivatsScheduler();
        return "13";
    }

    @Override
    protected String getScheduleMinute() {
   
		return "58";
	}
    
    @Override
    protected String getConfiguredHours() {
//        String nHores = Configuracio.getNhoresEliminarFitxersArxivatsScheduler();
    	String nHores = null;
        return nHores != null ? nHores : "1";
    }

    @Override
    protected void executeScheduledTask(long startTime) throws Exception {
        final String languageUI = DEFAULT_LANGUAGE;

        try {
            // Recollir totes les peticions arxivades
            List<Peticio> peticions = peticioLogicaEjb.select(
                    Where.AND(PeticioFields.INFOARXIUID.isNotNull()),
                    new OrderBy(PeticioFields.DATAFINAL));

            Set<Long> fitxersEsborrar = new HashSet<>();

            int processed = 0;

            for (Peticio peticio : peticions) {
                long peticioId = peticio.getPeticioID();
                Long fitxerOriginalId = peticio.getFitxerID() > 0 ? peticio.getFitxerID() : null;
                Long fitxerFirmatId = peticio.getFitxerFirmatID();

                try {
                    // Netejar referències a BBDD per la petició
                    if (fitxerOriginalId != null) {
                        peticioLogicaEjb.update(PeticioFields.FITXERID, null, PeticioFields.PETICIOID.equal(peticioId));
                        log.info("Esborrant fitxer original " + fitxerOriginalId + " (peticio " + peticioId + ") a BBDD");
                        fitxerLogicEjb.delete(fitxerOriginalId);
                        fitxersEsborrar.add(fitxerOriginalId);
                    }

                    if (fitxerFirmatId != null) {
                        peticioLogicaEjb.update(PeticioFields.FITXERFIRMATID, null, PeticioFields.PETICIOID.equal(peticioId));
                        log.info("Esborrant fitxer signat " + fitxerFirmatId + " (peticio " + peticioId + ") a BBDD");
                        fitxerLogicEjb.delete(fitxerFirmatId);
                        fitxersEsborrar.add(fitxerFirmatId);
                    }
                } catch (Throwable t) {
                    log.error("Error esborrant fitxers de la petició " + peticioId + ": " + t.getMessage(), t);
                }

                processed++;
                // Deixar 1 minut lliure abans que caduqui el timeout (5m -> 4m de feina)
                if (isTransactionTimeout(startTime)) {
                    log.warn("Timeout. Hem processat " + processed + " peticions");
                    break;
                }
            }

            if (fitxersEsborrar.isEmpty()) {
                log.info("No hi ha fitxers per esborrar!");
            } else {
                __tsRegistry.registerInterposedSynchronization(
                        new es.caib.enviafib.ejb.utils.CleanFilesSynchronization(fitxersEsborrar));
            }

        } catch (I18NException e) {
            throw e;
        }
    }
}
