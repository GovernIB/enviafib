package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IUsuariManager extends org.fundaciobit.genapp.common.query.ITableManager<Usuari, Long> {


	public Usuari create( java.lang.String _username_, java.lang.String _nom_, java.lang.String _llinatge1_, java.lang.String _llinatge2_, java.lang.String _nif_, java.lang.String _email_) throws I18NException;

	public Usuari findByPrimaryKey(long _usuariID_);

	public void delete(long _usuariID_);

}
