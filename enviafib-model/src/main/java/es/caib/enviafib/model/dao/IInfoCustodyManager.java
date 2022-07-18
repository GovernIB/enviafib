package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoCustodyManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoCustody, Long> {


	public InfoCustody create( java.lang.String _custodyid_, java.lang.String _originalfileurl_, java.lang.String _csv_, java.lang.String _csvgenerationdefinition_, java.lang.String _csvvalidationweb_, java.lang.String _arxiuexpedientid_, java.lang.String _arxiudocumentid_, java.lang.String _printableurl_, java.lang.String _enifileurl_, java.lang.String _validationfileurl_, java.lang.Long _peticioid_) throws I18NException;

	public InfoCustody findByPrimaryKey(long _infocustodyid_);

	public void delete(long _infocustodyid_);

}
