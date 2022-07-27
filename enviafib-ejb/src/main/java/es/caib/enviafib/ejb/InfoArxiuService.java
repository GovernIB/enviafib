
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.InfoArxiuJPA;
import es.caib.enviafib.persistence.InfoArxiuIJPAManager;
import es.caib.enviafib.model.dao.IInfoArxiuManager;

@Local
public interface InfoArxiuService extends InfoArxiuIJPAManager,IInfoArxiuManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/InfoArxiuEJB!es.caib.enviafib.ejb.InfoArxiuService";

    public InfoArxiuJPA findByPrimaryKey(Long _ID_);
}
