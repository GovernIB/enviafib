package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ITraduccioManager extends org.fundaciobit.genapp.common.query.ITableManager<Traduccio, Long> {


	public Traduccio create() throws I18NException;

	public Traduccio findByPrimaryKey(long _traduccioID_);

	public void delete(long _traduccioID_);

}
