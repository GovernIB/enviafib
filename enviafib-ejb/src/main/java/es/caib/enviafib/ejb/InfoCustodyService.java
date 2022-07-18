
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.InfoCustodyJPA;
import es.caib.enviafib.persistence.InfoCustodyIJPAManager;
import es.caib.enviafib.model.dao.IInfoCustodyManager;

@Local
public interface InfoCustodyService extends InfoCustodyIJPAManager,IInfoCustodyManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoCustodyEJB!es.caib.enviafib.ejb.InfoCustodyService";

    public InfoCustodyJPA findByPrimaryKey(Long _ID_);
}
