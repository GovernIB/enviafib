package es.caib.enviafib.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginid();
	public void setPluginid(long _pluginid_);

	public long getNomid();
	public void setNomid(long _nomid_);

	public long getDescripciocurtaid();
	public void setDescripciocurtaid(long _descripciocurtaid_);

	public java.lang.String getClasse();
	public void setClasse(java.lang.String _classe_);

	public java.lang.String getProperties();
	public void setProperties(java.lang.String _properties_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);

	public int getTipus();
	public void setTipus(int _tipus_);



  // ======================================

}
