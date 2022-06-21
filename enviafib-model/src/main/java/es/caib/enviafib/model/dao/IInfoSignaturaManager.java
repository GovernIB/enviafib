package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoSignaturaManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoSignatura, Long> {


	public InfoSignatura create( int _signOperation_, java.lang.String _signType_, java.lang.String _signAlgorithm_, java.lang.Integer _signMode_, java.lang.Integer _signaturesTableLocation_, java.lang.Boolean _timestampIncluded_, java.lang.Boolean _policyIncluded_, java.lang.String _eniTipoFirma_, java.lang.String _eniPerfilFirma_, java.lang.String _eniRolFirma_, java.lang.String _eniSignerName_, java.lang.String _eniSignerAdministrationId_, java.lang.String _eniSignLevel_, java.lang.Boolean _checkAdministrationIdOfSigner_, java.lang.Boolean _checkDocumentModifications_, java.lang.Boolean _checkValidationSignature_) throws I18NException;

	public InfoSignatura findByPrimaryKey(long _infoSignaturaID_);

	public void delete(long _infoSignaturaID_);

}
