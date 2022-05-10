package es.caib.enviafib.logic;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.PeticioService;

/**
 * 
 * @author fbosch
 *
 */
@Local
public interface PeticioLogicaService extends PeticioService {
	
	public static final String JNDI_NAME = "java:app/enviafib-ejb/PeticioLogicaEJB!es.caib.enviafib.logic.PeticioLogicaService";
	
	public void arrancarPeticio(long peticioID, String languageUI) throws I18NException;

}

