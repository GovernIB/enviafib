package es.caib.enviafib.logic;


import javax.ejb.Local;
/*
import es.caib.enviafib.ejb.AnnexService;
import es.caib.enviafib.persistence.AnnexJPA;

import org.fundaciobit.genapp.common.i18n.I18NException;
*/
/**
 * 
 * @author anadal
 *
 */
@Local
public interface SampleLogicaService /* extends AnnexService */ {
	
	public static final String JNDI_NAME = "java:app/enviafib-ejb/SampleLogicaEJB!es.caib.enviafib.logic.SampleLogicaService";

/*
  public Set<Long> deleteFull(AnnexJPA annex) throws I18NException;
  
  public AnnexJPA createFull(AnnexJPA annex) throws I18NException;
  */
}

