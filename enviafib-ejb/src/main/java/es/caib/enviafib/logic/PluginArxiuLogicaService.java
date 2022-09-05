package es.caib.enviafib.logic;

import java.util.Locale;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.model.entity.Peticio;
import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.plugins.arxiu.api.IArxiuPlugin;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface PluginArxiuLogicaService extends AbstractPluginLogicaService<IArxiuPlugin>  {

        
    public static final String JNDI_NAME = "java:app/enviafib-ejb/PluginArxiuLogicaEJB!es.caib.enviafib.logic.PluginArxiuLogicaService";


    /**
     * 
     * @param peticio
     * @param fitxer
     * @param locale
     * @return
     */
    public InfoArxiuJPA custodiaAmbApiArxiu(Peticio peticio, Locale locale, InfoSignaturaJPA infoSignatura) throws I18NException;


    /**
     * 
     * @param transaccio
     * @param expedientID
     * @param locale
     * @throws I18NException
     */
    public void tancarExpedient(Long infoCustodyID, String expedientID, Locale locale) throws I18NException;

}
