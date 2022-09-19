package es.caib.enviafib.model.entity;

public interface Menu extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getMenuID();
	public void setMenuID(long _menuID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public long getTitolMenuID();
	public void setTitolMenuID(long _titolMenuID_);

	public long getAjudaMenuID();
	public void setAjudaMenuID(long _ajudaMenuID_);

	public int getOrdre();
	public void setOrdre(int _ordre_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.Long getGrupID();
	public void setGrupID(java.lang.Long _grupID_);

	public java.lang.String getParametreCombo();
	public void setParametreCombo(java.lang.String _parametreCombo_);

	public java.lang.String getParametreText();
	public void setParametreText(java.lang.String _parametreText_);

	public boolean isActiu();
	public void setActiu(boolean _actiu_);



  // ======================================

}
