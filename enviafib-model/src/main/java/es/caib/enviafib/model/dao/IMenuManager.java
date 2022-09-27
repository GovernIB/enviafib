package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IMenuManager extends org.fundaciobit.genapp.common.query.ITableManager<Menu, Long> {


	public Menu create( java.lang.String _nom_, java.lang.String _descripcio_, long _titolMenuID_, long _ajudaMenuID_, int _ordre_, int _tipus_, java.lang.Long _grupID_, java.lang.String _parametreCombo_, java.lang.String _parametreText_, boolean _actiu_) throws I18NException;

	public Menu findByPrimaryKey(long _menuID_);

	public void delete(long _menuID_);

}
