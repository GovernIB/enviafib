package  es.caib.enviafib.persistence;

import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.query.ITableManager;


/**
 * Defineix les operacions dels Data Access Object per una entitat.
 * 
 * @param <E>  Tipus de l'entitat.
 * @param <PK> Tipus de la clau primària de l'entitat.
 *
 * @author anadal
 * @author areus
 */
public interface AbstractIJPAManager<E extends IGenAppEntity, PK> extends ITableManager<E, PK> {

}