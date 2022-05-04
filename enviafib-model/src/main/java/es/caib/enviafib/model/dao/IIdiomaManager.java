package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IIdiomaManager extends org.fundaciobit.genapp.common.query.ITableManager<Idioma, String> {


	public Idioma create( java.lang.String _idiomaID_, java.lang.String _nom_, boolean _suportat_, int _ordre_) throws I18NException;

	public Idioma findByPrimaryKey(java.lang.String _idiomaID_);

	public void delete(java.lang.String _idiomaID_);

}
