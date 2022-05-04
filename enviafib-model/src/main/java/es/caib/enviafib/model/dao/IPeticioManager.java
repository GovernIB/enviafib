package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPeticioManager extends org.fundaciobit.genapp.common.query.ITableManager<Peticio, Long> {


	public Peticio create( long _titolID_, java.sql.Timestamp _datacreacio_, long _fitxerID_, long _solicitantID_, java.lang.String _idiomaID_, java.lang.String _destinatarinif_) throws I18NException;

	public Peticio findByPrimaryKey(long _peticioID_);

	public void delete(long _peticioID_);

}
