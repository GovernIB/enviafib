package es.caib.enviafib.model.entity;

public interface Entitat extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getEntitatid();
	public void setEntitatid(java.lang.String _entitatid_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

	public java.lang.String getAdrezahtml();
	public void setAdrezahtml(java.lang.String _adrezahtml_);

	public boolean isActiva();
	public void setActiva(boolean _activa_);

	public java.lang.String getSuporttelefon();
	public void setSuporttelefon(java.lang.String _suporttelefon_);

	public java.lang.String getSuportweb();
	public void setSuportweb(java.lang.String _suportweb_);

	public java.lang.String getSuportemail();
	public void setSuportemail(java.lang.String _suportemail_);

	public long getFaviconID();
	public void setFaviconID(long _faviconID_);

	public long getLogowebID();
	public void setLogowebID(long _logowebID_);

	public long getLogowebpeuID();
	public void setLogowebpeuID(long _logowebpeuID_);

	public long getLogosegellID();
	public void setLogosegellID(long _logosegellID_);

	public java.lang.String getWeb();
	public void setWeb(java.lang.String _web_);

	public java.lang.Long getMotiudelegacioID();
	public void setMotiudelegacioID(java.lang.Long _motiudelegacioID_);

	public int getSegelldetempsviaweb();
	public void setSegelldetempsviaweb(int _segelldetempsviaweb_);

	public boolean isCheckcanviatdocfirmat();
	public void setCheckcanviatdocfirmat(boolean _checkcanviatdocfirmat_);

	public java.lang.String getPropietatstaulafirmes();
	public void setPropietatstaulafirmes(java.lang.String _propietatstaulafirmes_);

  // Fitxer
  public <F extends Fitxer> F getFavicon();
  // Fitxer
  public <F extends Fitxer> F getLogoweb();
  // Fitxer
  public <F extends Fitxer> F getLogowebpeu();
  // Fitxer
  public <F extends Fitxer> F getLogosegell();


  // ======================================

}
