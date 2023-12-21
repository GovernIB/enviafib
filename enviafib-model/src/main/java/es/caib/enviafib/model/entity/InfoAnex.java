package es.caib.enviafib.model.entity;

public interface InfoAnex extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getInfoanexid();
	public void setInfoanexid(long _infoanexid_);

	public java.lang.Long getPeticioID();
	public void setPeticioID(java.lang.Long _peticioID_);

	public java.lang.Long getAnexID();
	public void setAnexID(java.lang.Long _anexID_);

  // Fitxer
  public <F extends Fitxer> F getAnex();


  // ======================================

}
