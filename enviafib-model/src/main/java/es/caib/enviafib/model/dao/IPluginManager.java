package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IPluginManager extends org.fundaciobit.genapp.common.query.ITableManager<Plugin, Long> {


	public Plugin create( java.lang.String _nom_, java.lang.String _descripcio_, java.lang.String _classe_, java.lang.String _properties_, boolean _actiu_, int _tipus_) throws I18NException;

	public Plugin findByPrimaryKey(long _pluginID_);

	public void delete(long _pluginID_);

}
