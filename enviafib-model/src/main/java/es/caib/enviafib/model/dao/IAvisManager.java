package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IAvisManager extends org.fundaciobit.genapp.common.query.ITableManager<Avis, Long> {


	public Avis create( java.lang.String _missatge_, java.sql.Timestamp _datainici_, java.sql.Timestamp _datafi_, boolean _actiu_, java.lang.String _tipus_) throws I18NException;

	public Avis findByPrimaryKey(long _avisID_);

	public void delete(long _avisID_);

}
