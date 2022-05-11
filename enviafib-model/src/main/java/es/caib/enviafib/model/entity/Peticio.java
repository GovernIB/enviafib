package es.caib.enviafib.model.entity;

public interface Peticio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getTitolID();
	public void setTitolID(long _titolID_);

	public long getPeticioID();
	public void setPeticioID(long _peticioID_);

	public java.sql.Timestamp getDatacreacio();
	public void setDatacreacio(java.sql.Timestamp _datacreacio_);

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public long getSolicitantID();
	public void setSolicitantID(long _solicitantID_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public java.lang.String getDestinatarinif();
	public void setDestinatarinif(java.lang.String _destinatarinif_);

	public short getEstat();
	public void setEstat(short _estat_);

	public java.lang.Long getFitxerFirmatID();
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_);

	public java.lang.Long getPeticioPortafib();
	public void setPeticioPortafib(java.lang.Long _peticioPortafib_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();
  // Fitxer
  public <F extends Fitxer> F getFitxerFirmat();


  // ======================================

}
