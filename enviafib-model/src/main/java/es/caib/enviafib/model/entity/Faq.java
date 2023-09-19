package es.caib.enviafib.model.entity;

public interface Faq extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getFaqID();
	public void setFaqID(long _faqID_);

	public java.lang.Long getOrdre();
	public void setOrdre(java.lang.Long _ordre_);

	public java.lang.String getEnunciat_es();
	public void setEnunciat_es(java.lang.String _enunciat_es_);

	public java.lang.String getEnunciat_ca();
	public void setEnunciat_ca(java.lang.String _enunciat_ca_);

	public java.lang.String getResposta_es();
	public void setResposta_es(java.lang.String _resposta_es_);

	public java.lang.String getResposta_ca();
	public void setResposta_ca(java.lang.String _resposta_ca_);

	public java.lang.Long getFitxer1ID();
	public void setFitxer1ID(java.lang.Long _fitxer1ID_);

	public java.lang.Long getFitxer2ID();
	public void setFitxer2ID(java.lang.Long _fitxer2ID_);

	public java.lang.Long getFitxer3ID();
	public void setFitxer3ID(java.lang.Long _fitxer3ID_);

  // Fitxer
  public <F extends Fitxer> F getFitxer1();
  // Fitxer
  public <F extends Fitxer> F getFitxer2();
  // Fitxer
  public <F extends Fitxer> F getFitxer3();


  // ======================================

}
