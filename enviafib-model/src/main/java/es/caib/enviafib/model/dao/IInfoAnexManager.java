package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IInfoAnexManager extends org.fundaciobit.genapp.common.query.ITableManager<InfoAnex, Long> {


	public InfoAnex create( java.lang.Long _peticioID_, java.lang.Long _anexID_) throws I18NException;

	public InfoAnex findByPrimaryKey(long _infoanexid_);

	public void delete(long _infoanexid_);

}
