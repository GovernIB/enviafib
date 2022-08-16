
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.IdiomaJPA;
import es.caib.enviafib.persistence.IdiomaIJPAManager;
import es.caib.enviafib.model.dao.IIdiomaManager;

import es.caib.enviafib.model.entity.Idioma;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface IdiomaService extends IdiomaIJPAManager,IIdiomaManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/IdiomaEJB!es.caib.enviafib.ejb.IdiomaService";

    public IdiomaJPA findByPrimaryKey(String _ID_);

    public void deleteIncludingFiles(Idioma instance, FitxerService fitxerEjb) throws I18NException;
}
