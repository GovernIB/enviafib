package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariEntitatManager extends org.fundaciobit.genapp.common.query.ITableManager<UsuariEntitat, Long> {


	public UsuariEntitat create( long _usuariid_, java.lang.String _entitatid_) throws I18NException;

	public UsuariEntitat findByPrimaryKey(long _usuarientitatid_);

	public void delete(long _usuarientitatid_);

}
