
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Entitat;


public class EntitatBean implements Entitat {



	java.lang.String entitatid;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.String adrezahtml;
	boolean activa;
	java.lang.String suporttelefon;
	java.lang.String suportweb;
	java.lang.String suportemail;
	long faviconID;
	long logowebID;
	long logowebpeuID;
	long logosegellID;
	java.lang.String web;
	java.lang.Long motiudelegacioID;
	int segelldetempsviaweb;
	boolean checkcanviatdocfirmat;
	java.lang.String propietatstaulafirmes;


  /** Constructor Buit */
  public EntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public EntitatBean(java.lang.String entitatid , java.lang.String nom , java.lang.String descripcio , java.lang.String adrezahtml , boolean activa , java.lang.String suporttelefon , java.lang.String suportweb , java.lang.String suportemail , long faviconID , long logowebID , long logowebpeuID , long logosegellID , java.lang.String web , java.lang.Long motiudelegacioID , int segelldetempsviaweb , boolean checkcanviatdocfirmat , java.lang.String propietatstaulafirmes) {
    this.entitatid=entitatid;
    this.nom=nom;
    this.descripcio=descripcio;
    this.adrezahtml=adrezahtml;
    this.activa=activa;
    this.suporttelefon=suporttelefon;
    this.suportweb=suportweb;
    this.suportemail=suportemail;
    this.faviconID=faviconID;
    this.logowebID=logowebID;
    this.logowebpeuID=logowebpeuID;
    this.logosegellID=logosegellID;
    this.web=web;
    this.motiudelegacioID=motiudelegacioID;
    this.segelldetempsviaweb=segelldetempsviaweb;
    this.checkcanviatdocfirmat=checkcanviatdocfirmat;
    this.propietatstaulafirmes=propietatstaulafirmes;
}
  /** Constructor dels valors Not Null */
  public EntitatBean(java.lang.String entitatid , java.lang.String nom , java.lang.String adrezahtml , boolean activa , long faviconID , long logowebID , long logowebpeuID , long logosegellID , java.lang.String web , int segelldetempsviaweb , boolean checkcanviatdocfirmat) {
    this.entitatid=entitatid;
    this.nom=nom;
    this.adrezahtml=adrezahtml;
    this.activa=activa;
    this.faviconID=faviconID;
    this.logowebID=logowebID;
    this.logowebpeuID=logowebpeuID;
    this.logosegellID=logosegellID;
    this.web=web;
    this.segelldetempsviaweb=segelldetempsviaweb;
    this.checkcanviatdocfirmat=checkcanviatdocfirmat;
}
  public EntitatBean(Entitat __bean) {
    this.setEntitatid(__bean.getEntitatid());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setAdrezahtml(__bean.getAdrezahtml());
    this.setActiva(__bean.isActiva());
    this.setSuporttelefon(__bean.getSuporttelefon());
    this.setSuportweb(__bean.getSuportweb());
    this.setSuportemail(__bean.getSuportemail());
    this.setFaviconID(__bean.getFaviconID());
    this.setLogowebID(__bean.getLogowebID());
    this.setLogowebpeuID(__bean.getLogowebpeuID());
    this.setLogosegellID(__bean.getLogosegellID());
    this.setWeb(__bean.getWeb());
    this.setMotiudelegacioID(__bean.getMotiudelegacioID());
    this.setSegelldetempsviaweb(__bean.getSegelldetempsviaweb());
    this.setCheckcanviatdocfirmat(__bean.isCheckcanviatdocfirmat());
    this.setPropietatstaulafirmes(__bean.getPropietatstaulafirmes());
    // Fitxer
    this.setFavicon(FitxerBean.toBean(__bean.getFavicon()));
    // Fitxer
    this.setLogoweb(FitxerBean.toBean(__bean.getLogoweb()));
    // Fitxer
    this.setLogowebpeu(FitxerBean.toBean(__bean.getLogowebpeu()));
    // Fitxer
    this.setLogosegell(FitxerBean.toBean(__bean.getLogosegell()));
	}

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};

	public java.lang.String getAdrezahtml() {
		return(adrezahtml);
	};
	public void setAdrezahtml(java.lang.String _adrezahtml_) {
		this.adrezahtml = _adrezahtml_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.String getSuporttelefon() {
		return(suporttelefon);
	};
	public void setSuporttelefon(java.lang.String _suporttelefon_) {
		this.suporttelefon = _suporttelefon_;
	};

	public java.lang.String getSuportweb() {
		return(suportweb);
	};
	public void setSuportweb(java.lang.String _suportweb_) {
		this.suportweb = _suportweb_;
	};

	public java.lang.String getSuportemail() {
		return(suportemail);
	};
	public void setSuportemail(java.lang.String _suportemail_) {
		this.suportemail = _suportemail_;
	};

	public long getFaviconID() {
		return(faviconID);
	};
	public void setFaviconID(long _faviconID_) {
		this.faviconID = _faviconID_;
	};

	public long getLogowebID() {
		return(logowebID);
	};
	public void setLogowebID(long _logowebID_) {
		this.logowebID = _logowebID_;
	};

	public long getLogowebpeuID() {
		return(logowebpeuID);
	};
	public void setLogowebpeuID(long _logowebpeuID_) {
		this.logowebpeuID = _logowebpeuID_;
	};

	public long getLogosegellID() {
		return(logosegellID);
	};
	public void setLogosegellID(long _logosegellID_) {
		this.logosegellID = _logosegellID_;
	};

	public java.lang.String getWeb() {
		return(web);
	};
	public void setWeb(java.lang.String _web_) {
		this.web = _web_;
	};

	public java.lang.Long getMotiudelegacioID() {
		return(motiudelegacioID);
	};
	public void setMotiudelegacioID(java.lang.Long _motiudelegacioID_) {
		this.motiudelegacioID = _motiudelegacioID_;
	};

	public int getSegelldetempsviaweb() {
		return(segelldetempsviaweb);
	};
	public void setSegelldetempsviaweb(int _segelldetempsviaweb_) {
		this.segelldetempsviaweb = _segelldetempsviaweb_;
	};

	public boolean isCheckcanviatdocfirmat() {
		return(checkcanviatdocfirmat);
	};
	public void setCheckcanviatdocfirmat(boolean _checkcanviatdocfirmat_) {
		this.checkcanviatdocfirmat = _checkcanviatdocfirmat_;
	};

	public java.lang.String getPropietatstaulafirmes() {
		return(propietatstaulafirmes);
	};
	public void setPropietatstaulafirmes(java.lang.String _propietatstaulafirmes_) {
		this.propietatstaulafirmes = _propietatstaulafirmes_;
	};



  // ======================================

  public static EntitatBean toBean(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatBean __tmp = new EntitatBean();
    __tmp.setEntitatid(__bean.getEntitatid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setAdrezahtml(__bean.getAdrezahtml());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setSuporttelefon(__bean.getSuporttelefon());
    __tmp.setSuportweb(__bean.getSuportweb());
    __tmp.setSuportemail(__bean.getSuportemail());
    __tmp.setFaviconID(__bean.getFaviconID());
    __tmp.setLogowebID(__bean.getLogowebID());
    __tmp.setLogowebpeuID(__bean.getLogowebpeuID());
    __tmp.setLogosegellID(__bean.getLogosegellID());
    __tmp.setWeb(__bean.getWeb());
    __tmp.setMotiudelegacioID(__bean.getMotiudelegacioID());
    __tmp.setSegelldetempsviaweb(__bean.getSegelldetempsviaweb());
    __tmp.setCheckcanviatdocfirmat(__bean.isCheckcanviatdocfirmat());
    __tmp.setPropietatstaulafirmes(__bean.getPropietatstaulafirmes());
    // Fitxer
    __tmp.setFavicon(FitxerBean.toBean(__bean.getFavicon()));
    // Fitxer
    __tmp.setLogoweb(FitxerBean.toBean(__bean.getLogoweb()));
    // Fitxer
    __tmp.setLogowebpeu(FitxerBean.toBean(__bean.getLogowebpeu()));
    // Fitxer
    __tmp.setLogosegell(FitxerBean.toBean(__bean.getLogosegell()));
		return __tmp;
	}

  protected FitxerBean favicon;
  public FitxerBean getFavicon() {
    return favicon;
  }
  public void setFavicon(FitxerBean __field) {
    this. favicon = __field;
  }
  protected FitxerBean logoweb;
  public FitxerBean getLogoweb() {
    return logoweb;
  }
  public void setLogoweb(FitxerBean __field) {
    this. logoweb = __field;
  }
  protected FitxerBean logowebpeu;
  public FitxerBean getLogowebpeu() {
    return logowebpeu;
  }
  public void setLogowebpeu(FitxerBean __field) {
    this. logowebpeu = __field;
  }
  protected FitxerBean logosegell;
  public FitxerBean getLogosegell() {
    return logosegell;
  }
  public void setLogosegell(FitxerBean __field) {
    this. logosegell = __field;
  }


}
