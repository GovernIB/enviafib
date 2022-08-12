package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface ISerieDocumentalManager extends org.fundaciobit.genapp.common.query.ITableManager<SerieDocumental, Long> {


	public SerieDocumental create( java.lang.String _nom_, java.lang.String _tipusDocumental_, java.lang.String _procedimentNom_, java.lang.String _procedimentCodi_) throws I18NException;

	public SerieDocumental findByPrimaryKey(long _serieDocumentalID_);

	public void delete(long _serieDocumentalID_);

}
