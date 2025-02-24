
package es.caib.enviafib.logic;

import javax.ejb.Stateless;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.security.PermitAll;
import org.fundaciobit.genapp.common.i18n.I18NException;
import org.fundaciobit.genapp.common.query.Where;

import es.caib.enviafib.model.entity.Avis;
import es.caib.enviafib.model.fields.AvisFields;
import es.caib.enviafib.persistence.AvisJPA;
import es.caib.enviafib.ejb.AvisEJB;

/**
 * 
 * @author anadal
 *
 */
@Stateless
public class AvisLogicaEJB extends AvisEJB implements AvisLogicaService {



    @Override
    @PermitAll
    public Avis createPublic(Avis instance) throws I18NException {
        return super.create(instance);
    }
    
    @Override
    @PermitAll
    public AvisJPA findByPrimaryKeyPublic(Long _ID_) {
        return super.findByPrimaryKey(_ID_);
    }
    
    //Devolver un listado con los avisos activos.
    @Override
    @PermitAll
	public List<Avis> findAvisosActivos() throws I18NException {

		List<Avis> toReturn = new ArrayList<Avis>();

		Where wActivo = AvisFields.ACTIU.equal(true);
		List<Avis> avisosActivos = select(wActivo);

		// Lógica para comprovar si están en fecha.
		Date hoy = new Date();
		for (Avis a : avisosActivos) {
			Timestamp fechaFin = a.getDatafi();
			Timestamp fechaInicio = a.getDatainici();

			if (fechaInicio == null && fechaFin == null) {
				toReturn.add(a);
			} else if (fechaInicio == null) {
				if (fechaFin.after(hoy)) {
					toReturn.add(a);
				}
			} else if (fechaFin == null) {
				if (fechaInicio.before(hoy)) {
					toReturn.add(a);
				}
			} else if (fechaInicio.before(hoy) && fechaFin.after(hoy)) {
				toReturn.add(a);
			}
		}

		return toReturn;
	}

}
