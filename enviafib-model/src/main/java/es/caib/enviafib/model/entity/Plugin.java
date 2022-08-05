package es.caib.enviafib.model.entity;

public interface Plugin extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getPluginID();
	public void setPluginID(long _pluginID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

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
