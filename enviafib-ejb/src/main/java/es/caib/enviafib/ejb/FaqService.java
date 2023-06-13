
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.FaqJPA;
import es.caib.enviafib.persistence.FaqIJPAManager;
import es.caib.enviafib.model.dao.IFaqManager;

import es.caib.enviafib.model.entity.Faq;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface FaqService extends FaqIJPAManager,IFaqManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/FaqEJB!es.caib.enviafib.ejb.FaqService";

    public FaqJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Faq instance, FitxerService fitxerEjb) throws I18NException;
}
