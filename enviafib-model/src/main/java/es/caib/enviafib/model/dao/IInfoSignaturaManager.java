package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoSignaturaManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoSignatura, Long> {


	public InfoSignatura create( int _signoperation_, java.lang.String _signtype_, java.lang.String _signalgorithm_, java.lang.Integer _signmode_, java.lang.Integer _signaturestablelocation_, java.lang.Boolean _timestampincluded_, java.lang.Boolean _policyincluded_, java.lang.String _enitipofirma_, java.lang.String _eniperfilfirma_, java.lang.String _enirolfirma_, java.lang.String _enisignername_, java.lang.String _enisigneradministrationid_, java.lang.String _enisignlevel_, java.lang.Boolean _checkadministrationidofsigner_, java.lang.Boolean _checkdocumentmodifications_, java.lang.Boolean _checkvalidationsignature_) throws I18NException;

	public InfoSignatura findByPrimaryKey(long _infosignaturaid_);

	public void delete(long _infosignaturaid_);

}
