package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPeticioManager extends org.fundaciobit.genapp.common.query.ITableManager<Peticio, Long> {


	public Peticio create( java.lang.String _nom_, java.sql.Timestamp _dataCreacio_, java.sql.Timestamp _dataFinal_, long _fitxerID_, long _solicitantID_, java.lang.String _idiomaID_, java.lang.String _destinatariNif_, int _estat_, java.lang.Long _fitxerFirmatID_, java.lang.String _tipusDocumental_, java.lang.String _idiomaDoc_, java.lang.Long _infoSignaturaID_, int _tipus_, java.lang.String _errorMsg_, java.lang.String _errorException_, java.lang.String _peticioPortafirmes_) throws I18NException;

	public Peticio findByPrimaryKey(long _peticioID_);

	public void delete(long _peticioID_);

}
