package es.caib.enviafib.logic.utils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.*;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NCommonUtils;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.commons.utils.Configuracio;

import java.util.Locale;

/**
 * Clase abstracta base para los schedulers periódicos de la aplicación.
 * Proporciona la estructura común para todas las tareas programadas.
 */
@Singleton
@Startup
public abstract class AbstractScheduler {

    protected final Logger log = Logger.getLogger(this.getClass());

    
	@EJB(mappedName = es.caib.enviafib.logic.PluginArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PluginArxiuLogicaService pluginArxiuLogicaEjb;

	@EJB(mappedName = es.caib.enviafib.logic.InfoArxiuLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.InfoArxiuLogicaService infoArxiuLogicEjb;

	@EJB(mappedName = es.caib.enviafib.logic.PeticioLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.PeticioLogicaService peticioLogicaEjb;

	@EJB(mappedName = es.caib.enviafib.logic.InfoSignaturaLogicaService.JNDI_NAME)
	protected es.caib.enviafib.logic.InfoSignaturaLogicaService infoSignaturaLogicaEjb;

    @javax.ejb.EJB(mappedName = es.caib.enviafib.logic.FitxerLogicaService.JNDI_NAME)
    protected es.caib.enviafib.logic.FitxerLogicaService fitxerLogicEjb;
    
    /** Timeout de transacción: 4 minutos (el timeout de EJB es de 5 minutos) */
    protected final long TRANSACTION_EXIT_IN_MILI = 4 * 60 * 1000;

    /** Idioma por defecto para los mensajes */
    protected static final String DEFAULT_LANGUAGE = "ca";

    @Resource
    protected TimerService timerService;

    @PostConstruct
    public void init() {
        String horaStr = getConfiguredHour();
        String nHoresStr = getConfiguredHours();

        if (horaStr == null || horaStr.isEmpty()) {
            log.warn("No s'ha definit l'hora per al scheduler " + getSchedulerName());
            return;
        }

        int nHores = 1;
        try {
            nHores = Integer.parseInt(nHoresStr);
        } catch (Exception ignore) {
        }

        if (nHores > 1) {
            try {
                int hores = Integer.parseInt(horaStr);
                horaStr += "-" + (hores + nHores - 1);
            } catch (Exception e) {
                log.warn("Format d'hora invàlid per al scheduler " + getSchedulerName() + ": " + horaStr);
            }
        }

        log.info("initScheduler:: " + getSchedulerName() + " a les " + horaStr + " hores");
        scheduleTask(horaStr);
    }

    /**
     * Programa la tarea con la expresión de horario indicada.
     * 
     * @param horaStr expresión de hora (ej: "14", "14-16")
     */
    public void scheduleTask(String horaStr) {
        // Limpiar timers anteriores
        for (Timer timer : timerService.getTimers()) {
            timer.cancel();
        }

        ScheduleExpression schedule = new ScheduleExpression();
        schedule.hour(horaStr);
        schedule.minute(getScheduleMinute());

        Timer newTimer = timerService.createCalendarTimer(schedule);
        log.info("CREAT Schedule per " + getSchedulerName() + ": " + newTimer.getNextTimeout());
    }

    /**
     * Método handler para el timeout del scheduler.
     * Este método será invocado cuando se cumple la expresión de horario.
     * 
     * @param timer el Timer asociado
     */
    @Timeout
    public void onTimeout(Timer timer) {
        log.info("Inici " + getSchedulerName() + "()");

        long startTime = System.currentTimeMillis();

        try {
            executeScheduledTask(startTime);
        } catch (I18NException e) {
            final String msg = "Error durant l'execució del scheduler " + getSchedulerName() + ": "
                    + I18NCommonUtils.getMessage(e, new Locale(DEFAULT_LANGUAGE));
            log.error(msg, e);
        } catch (Exception e) {
            log.error("Error durant l'execució del scheduler " + getSchedulerName() + ": " + e.getMessage(), e);
        }

        long endTime = System.currentTimeMillis();
        log.info("Total time: " + (endTime - startTime) + " ms");
        log.info("Acaba " + getSchedulerName() + "()");
    }

    /**
     * Obtiene el nombre del scheduler. Se utiliza para logging.
     * 
     * @return nombre del scheduler
     */
    protected abstract String getSchedulerName();

    /**
     * Obtiene la hora configurada para ejecutar el scheduler.
     * Por ejemplo: "14" o "14-16"
     * 
     * @return hora configurada o null si no está configurada
     */
    protected abstract String getConfiguredHour();

    /**
     * Obtiene el número de horas consecutivas que debe ejecutarse el scheduler.
     * 
     * @return número de horas o "1" por defecto
     */
    protected abstract String getConfiguredHours();

    /**
     * Obtiene el patrón de minutos para la expresión de horario.
     * Por defecto: cada 5 minutos
     * 
     * @return patrón de minutos
     */
    protected String getScheduleMinute() {
        return "*/5";
    }

    /**
     * Ejecuta la lógica específica del scheduler.
     * Este método debe implementarse en las clases concretas.
     * 
     * @param startTime timestamp de inicio de la ejecución (en milisegundos)
     * @throws Exception en caso de error
     */
    protected abstract void executeScheduledTask(long startTime) throws Exception;

    /**
     * Comprueba si se ha alcanzado el timeout de transacción.
     * Se utiliza para salir de bucles antes de que expire el timeout EJB (5 min).
     * 
     * @param startTime timestamp de inicio
     * @return true si se ha alcanzado el timeout
     */
    protected boolean isTransactionTimeout(long startTime) {
        return (System.currentTimeMillis() - startTime) > TRANSACTION_EXIT_IN_MILI;
    }

}
