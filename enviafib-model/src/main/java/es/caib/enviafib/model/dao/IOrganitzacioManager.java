package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IOrganitzacioManager extends org.fundaciobit.genapp.common.query.ITableManager<Organitzacio, Long> {


	public Organitzacio create( java.lang.String _codiConselleria_, java.lang.String _codiDireccioGeneral_, java.lang.String _tipus_, java.lang.String _valor_) throws I18NException;

	public Organitzacio findByPrimaryKey(long _organitzacioID_);

	public void delete(long _organitzacioID_);

}
