package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoArxiuManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoArxiu, Long> {


	public InfoArxiu create( java.lang.String _originalFileUrl_, java.lang.String _csv_, java.lang.String _csvGenerationDefinition_, java.lang.String _csvValidationWeb_, java.lang.String _arxiuExpedientID_, java.lang.String _arxiuDocumentID_, java.lang.String _printableUrl_, java.lang.String _eniFileUrl_, java.lang.String _validationFileUrl_) throws I18NException;

	public InfoArxiu findByPrimaryKey(long _infoArxiuID_);

	public void delete(long _infoArxiuID_);

}
