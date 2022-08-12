
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Grup;


public class GrupBean implements Grup {



	long grupID;// PK
	java.lang.String nom;
	java.lang.String descripcio;


  /** Constructor Buit */
  public GrupBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupBean(long grupID , java.lang.String nom , java.lang.String descripcio) {
    this.grupID=grupID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public GrupBean(java.lang.String nom , java.lang.String descripcio) {
    this.nom=nom;
    this.descripcio=descripcio;
}
  public GrupBean(Grup __bean) {
    this.setGrupID(__bean.getGrupID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getGrupID() {
		return(grupID);
	};
	public void setGrupID(long _grupID_) {
		this.grupID = _grupID_;
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



  // ======================================

  public static GrupBean toBean(Grup __bean) {
    if (__bean == null) { return null;}
    GrupBean __tmp = new GrupBean();
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}



}
