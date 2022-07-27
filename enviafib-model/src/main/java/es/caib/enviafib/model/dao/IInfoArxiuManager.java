package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoArxiuManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoArxiu, Long> {


	public InfoArxiu create( java.lang.String _originalfileurl_, java.lang.String _csv_, java.lang.String _csvgenerationdefinition_, java.lang.String _csvvalidationweb_, java.lang.String _arxiuexpedientid_, java.lang.String _arxiudocumentid_, java.lang.String _printableurl_, java.lang.String _enifileurl_, java.lang.String _validationfileurl_) throws I18NException;

	public InfoArxiu findByPrimaryKey(long _infoArxiuID_);

	public void delete(long _infoArxiuID_);

}
