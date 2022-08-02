package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IGrupUsuariManager extends org.fundaciobit.genapp.common.query.ITableManager<GrupUsuari, Long> {


	public GrupUsuari create( long _grupID_, long _usuariID_) throws I18NException;

	public GrupUsuari findByPrimaryKey(long _grupUsuariID_);

	public void delete(long _grupUsuariID_);

}
