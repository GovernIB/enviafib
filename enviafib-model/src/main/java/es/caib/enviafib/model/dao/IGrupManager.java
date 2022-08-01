package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGrupManager extends org.fundaciobit.genapp.common.query.ITableManager<Grup, Long> {


	public Grup create( java.lang.String _nom_, java.lang.String _descripcio_) throws I18NException;

	public Grup findByPrimaryKey(long _grupID_);

	public void delete(long _grupID_);

}
