
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Menu;


public class MenuBean implements Menu {



	long menuID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	long titolMenuID;
	long ajudaMenuID;
	int ordre;
	int tipus;
	java.lang.Long grupID;
	java.lang.String parametreCombo;
	java.lang.String parametreText;
	boolean actiu;


  /** Constructor Buit */
  public MenuBean() {
  }

  /** Constructor amb tots els camps  */
  public MenuBean(long menuID , java.lang.String nom , java.lang.String descripcio , long titolMenuID , long ajudaMenuID , int ordre , int tipus , java.lang.Long grupID , java.lang.String parametreCombo , java.lang.String parametreText , boolean actiu) {
    this.menuID=menuID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.grupID=grupID;
    this.parametreCombo=parametreCombo;
    this.parametreText=parametreText;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public MenuBean(java.lang.String nom , java.lang.String descripcio , long titolMenuID , long ajudaMenuID , int ordre , int tipus , java.lang.Long grupID , java.lang.String parametreCombo , java.lang.String parametreText , boolean actiu) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.grupID=grupID;
    this.parametreCombo=parametreCombo;
    this.parametreText=parametreText;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public MenuBean(long menuID , java.lang.String nom , long titolMenuID , long ajudaMenuID , int ordre , int tipus , boolean actiu) {
    this.menuID=menuID;
    this.nom=nom;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.actiu=actiu;
}
  public MenuBean(Menu __bean) {
    this.setMenuID(__bean.getMenuID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setTitolMenuID(__bean.getTitolMenuID());
    this.setAjudaMenuID(__bean.getAjudaMenuID());
    this.setOrdre(__bean.getOrdre());
    this.setTipus(__bean.getTipus());
    this.setGrupID(__bean.getGrupID());
    this.setParametreCombo(__bean.getParametreCombo());
    this.setParametreText(__bean.getParametreText());
    this.setActiu(__bean.isActiu());
	}

	public long getMenuID() {
		return(menuID);
	};
	public void setMenuID(long _menuID_) {
		this.menuID = _menuID_;
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

	public long getTitolMenuID() {
		return(titolMenuID);
	};
	public void setTitolMenuID(long _titolMenuID_) {
		this.titolMenuID = _titolMenuID_;
	};

	public long getAjudaMenuID() {
		return(ajudaMenuID);
	};
	public void setAjudaMenuID(long _ajudaMenuID_) {
		this.ajudaMenuID = _ajudaMenuID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.Long getGrupID() {
		return(grupID);
	};
	public void setGrupID(java.lang.Long _grupID_) {
		this.grupID = _grupID_;
	};

	public java.lang.String getParametreCombo() {
		return(parametreCombo);
	};
	public void setParametreCombo(java.lang.String _parametreCombo_) {
		this.parametreCombo = _parametreCombo_;
	};

	public java.lang.String getParametreText() {
		return(parametreText);
	};
	public void setParametreText(java.lang.String _parametreText_) {
		this.parametreText = _parametreText_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  // ======================================

  public static MenuBean toBean(Menu __bean) {
    if (__bean == null) { return null;}
    MenuBean __tmp = new MenuBean();
    __tmp.setMenuID(__bean.getMenuID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTitolMenuID(__bean.getTitolMenuID());
    __tmp.setAjudaMenuID(__bean.getAjudaMenuID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setParametreCombo(__bean.getParametreCombo());
    __tmp.setParametreText(__bean.getParametreText());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}



}
