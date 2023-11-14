package es.caib.enviafib.logic.utils;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;

import org.apache.log4j.Logger;

import es.caib.enviafib.logic.PeticioLogicaService;

@Startup
@Singleton
public class EjbStartUp {

    protected final Logger log = Logger.getLogger(getClass());

    @EJB(mappedName = PeticioLogicaService.JNDI_NAME)
    protected PeticioLogicaService peticioLogicaEjb;

    @PostConstruct
    protected void init() {
        log.info("EjbStartUp:: START");
        peticioLogicaEjb.initScheduler();
        log.info("EjbStartUp:: END");
    }

}
