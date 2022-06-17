
package es.caib.enviafib.logic;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.persistence.InfoSignaturaJPA;
import es.caib.enviafib.ejb.InfoSignaturaService;
import es.caib.enviafib.model.entity.InfoSignatura;

@Local
public interface InfoSignaturaLogicService extends InfoSignaturaService{

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoSignaturaLogicEJB!es.caib.enviafib.logic.InfoSignaturaLogicService";

    public InfoSignaturaJPA findByPrimaryKey(Long _ID_);
    
    public InfoSignatura createPublic(InfoSignatura instance) throws I18NException;

    public void deletePublic(InfoSignatura instance);
}
