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

    
    //XYZ Revisar si es necessari o es pot eliminar
    public static final String KEY_ERROR = "* ERROR: ";
    
    public static final String JNDI_NAME = "java:app/enviafib-ejb/PluginArxiuLogicaEJB!es.caib.enviafib.logic.PluginArxiuLogicaService";


    /**
     * 
     * @param peticio
     * @param fitxer
     * @param locale
     * @return
     */
    public InfoArxiuJPA custodiaAmbApiArxiu(Peticio peticio, Locale locale, InfoSignaturaJPA infoSignatura);


    /**
     * 
     * @param peticio
     * @param plugin
     * @param expedientId
     * @return
     */
    public boolean tancarExpedient(Peticio peticio, String expedientID);

}
