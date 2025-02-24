package es.caib.enviafib.model.entity;

public interface Avis extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getAvisID();
	public void setAvisID(long _avisID_);

	public java.lang.String getMissatge();
	public void setMissatge(java.lang.String _missatge_);

	public java.sql.Timestamp getDatainici();
	public void setDatainici(java.sql.Timestamp _datainici_);

	public java.sql.Timestamp getDatafi();
	public void setDatafi(java.sql.Timestamp _datafi_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public java.lang.String getTipus();
	public void setTipus(java.lang.String _tipus_);



  // ======================================

}
