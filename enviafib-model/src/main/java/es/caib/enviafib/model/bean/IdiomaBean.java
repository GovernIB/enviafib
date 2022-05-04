
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Idioma;


public class IdiomaBean implements Idioma {



private static final long serialVersionUID = -565026235L;

	java.lang.String idiomaID;// PK
	java.lang.String nom;
	boolean suportat;
	int ordre;


  /** Constructor Buit */
  public IdiomaBean() {
  }

  /** Constructor amb tots els camps  */
  public IdiomaBean(java.lang.String idiomaID , java.lang.String nom , boolean suportat , int ordre) {
    this.idiomaID=idiomaID;
    this.nom=nom;
    this.suportat=suportat;
    this.ordre=ordre;
}
  public IdiomaBean(Idioma __bean) {
    this.setIdiomaID(__bean.getIdiomaID());
    this.setNom(__bean.getNom());
    this.setSuportat(__bean.isSuportat());
    this.setOrdre(__bean.getOrdre());
	}

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public boolean isSuportat() {
		return(suportat);
	};
	public void setSuportat(boolean _suportat_) {
		this.suportat = _suportat_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};



  // ======================================

  public static IdiomaBean toBean(Idioma __bean) {
    if (__bean == null) { return null;}
    IdiomaBean __tmp = new IdiomaBean();
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setNom(__bean.getNom());
    __tmp.setSuportat(__bean.isSuportat());
    __tmp.setOrdre(__bean.getOrdre());
		return __tmp;
	}



}
