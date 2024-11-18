
package es.caib.enviafib.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.StringKeyValue;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.SerieDocumentalService;
import es.caib.enviafib.model.entity.SerieDocumental;
import es.caib.enviafib.persistence.SerieDocumentalJPA;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface SerieDocumentalLogicaService extends SerieDocumentalService {

	public static final String JNDI_NAME = "java:app/enviafib-ejb/SerieDocumentalLogicaEJB!es.caib.enviafib.logic.SerieDocumentalLogicaService";

	public SerieDocumental createPublic(SerieDocumental instance) throws I18NException;

	public SerieDocumentalJPA findByPrimaryKeyPublic(Long _ID_);
	
    public List<StringKeyValue> getTipusDocumentals(String lang) throws I18NException;

}
