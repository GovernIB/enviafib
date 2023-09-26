package es.caib.enviafib.logic;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.OrderBy;
import org.fundaciobit.genapp.common.query.SubQuery;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.commons.utils.Constants;
import es.caib.enviafib.ejb.MenuEJB;
import es.caib.enviafib.model.entity.GrupUsuari;
import es.caib.enviafib.model.entity.Menu;
import es.caib.enviafib.model.fields.GrupUsuariFields;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class MenuLogicaEJB extends MenuEJB implements MenuLogicaService {

    @EJB(mappedName = es.caib.enviafib.ejb.GrupUsuariService.JNDI_NAME)
    protected es.caib.enviafib.ejb.GrupUsuariService grupUsuariEjb;

    @Override
    public List<Menu> getAllOptionMenusByUsername(Long userID) throws I18NException {
        
        // Menus per tothom
        Where w1 = GRUPID.isNull();

        // Menus que tenen grup en el que apareix l'usuari userID

        SubQuery<GrupUsuari, Long> subQuery;

        subQuery = grupUsuariEjb.getSubQuery(GrupUsuariFields.GRUPID, GrupUsuariFields.USUARIID.equal(userID));

        Where w2 = GRUPID.in(subQuery);

        Where where = Where.AND(ACTIU.equal(true), Where.OR(w1, w2));

        return this.select(where, new OrderBy(ORDRE));
    }

    
    @Override
    public List<Menu> getOptionMenusSenseDir3() throws I18NException {

        Integer[] vals = { 
                Constants.MENU_FIRMA_TIPUS_AUTOFIRMA,
                //Constants.MENU_FIRMA_TIPUS_PER_NIF,
                Constants.MENU_FIRMA_TIPUS_FLUX,
                //Constants.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_USUARI,
                //Constants.MENU_FIRMA_TIPUS_PLANTILLES_FLUX_ENTITAT,
                //Constants.MENU_FIRMA_TIPUS_FLUX_SIMPLE_TEXT,
                //Constants.MENU_FIRMA_TIPUS_FLUX_COMPLEX_JSON,
                //Constants.MENU_FIRMA_TIPUS_CARREC
        };
        Where where = TIPUS.in(vals);

        return this.select(Where.AND(where, ACTIU.equal(true), GRUPID.isNull()), new OrderBy(ORDRE));
    }

}
