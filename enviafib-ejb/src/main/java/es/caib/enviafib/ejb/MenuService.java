
package es.caib.enviafib.ejb;

// NO MODIFICAR - DO NOT MODIFY;
import javax.ejb.Local;

import es.caib.enviafib.persistence.MenuJPA;
import es.caib.enviafib.persistence.MenuIJPAManager;
import es.caib.enviafib.model.dao.IMenuManager;

import es.caib.enviafib.model.entity.Menu;
import org.fundaciobit.genapp.common.i18n.I18NException;

@Local
public interface MenuService extends MenuIJPAManager,IMenuManager {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/MenuEJB!es.caib.enviafib.ejb.MenuService";

    public MenuJPA findByPrimaryKey(Long _ID_);

    public void deleteIncludingFiles(Menu instance, FitxerService fitxerEjb) throws I18NException;
}
