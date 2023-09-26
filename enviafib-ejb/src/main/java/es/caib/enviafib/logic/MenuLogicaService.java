package es.caib.enviafib.logic;

import java.util.List;

import javax.ejb.Local;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.MenuService;
import es.caib.enviafib.model.entity.Menu;

/**
 * 
 * @author anadal
 *
 */
@Local
public interface MenuLogicaService extends MenuService {

    public static final String JNDI_NAME = "java:app/enviafib-ejb/MenuLogicaEJB!es.caib.enviafib.logic.MenuLogicaService";

    public List<Menu> getAllOptionMenusByUsername(Long userID) throws I18NException;

    public List<Menu> getOptionMenusSenseDir3() throws I18NException;

}
