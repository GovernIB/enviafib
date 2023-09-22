package es.caib.enviafib.model.dao;

import es.caib.enviafib.model.entity.*;
import org.fundaciobit.genapp.common.i18n.I18NException;


public interface IFaqManager extends org.fundaciobit.genapp.common.query.ITableManager<Faq, Long> {


	public Faq create( java.lang.Long _ordre_, java.lang.String _enunciat_es_, java.lang.String _enunciat_ca_, java.lang.String _resposta_es_, java.lang.String _resposta_ca_, java.lang.Long _fitxer1ID_, java.lang.Long _fitxer2ID_, java.lang.Long _fitxer3ID_) throws I18NException;

	public Faq findByPrimaryKey(long _faqID_);

	public void delete(long _faqID_);

}
