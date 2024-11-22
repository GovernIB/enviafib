package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<Entitat, String> {


	public Entitat create( java.lang.String _entitatid_, java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _adrezahtml_, boolean _activa_, java.lang.String _suporttelefon_, java.lang.String _suportweb_, java.lang.String _suportemail_, long _faviconID_, long _logowebID_, long _logowebpeuID_, long _logosegellID_, java.lang.String _web_, java.lang.Long _motiudelegacioID_, int _segelldetempsviaweb_, boolean _checkcanviatdocfirmat_, java.lang.String _propietatstaulafirmes_, java.lang.String _dir3_) throws I18NException;

	public Entitat findByPrimaryKey(java.lang.String _entitatid_);

	public void delete(java.lang.String _entitatid_);

}
