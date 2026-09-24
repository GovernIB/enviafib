package  es.caib.enviafib.persistence;


import org.fundaciobit.genapp.common.IGenAppEntity;
import org.fundaciobit.genapp.common.query.AbstractTableManager;


/**
 * 
 * 
 * @author anadal
 *
 */
public abstract class AbstractJPAManager<E extends IGenAppEntity, PK extends Object> extends AbstractTableManager<E, PK>
		implements AbstractIJPAManager<E, PK> {

	public AbstractJPAManager() {
		super();
	}

}
