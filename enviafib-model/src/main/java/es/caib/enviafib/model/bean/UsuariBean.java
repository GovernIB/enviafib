
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Usuari;


public class UsuariBean implements Usuari {



	long usuariID;// PK
	java.lang.String username;
	java.lang.String nom;
	java.lang.String llinatge1;
	java.lang.String llinatge2;
	java.lang.String nif;
	java.lang.String email;
	java.lang.String idiomaID;


  /** Constructor Buit */
  public UsuariBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariBean(long usuariID , java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email , java.lang.String idiomaID) {
    this.usuariID=usuariID;
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
    this.idiomaID=idiomaID;
}
  /** Constructor sense valors autoincrementals */
  public UsuariBean(java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email , java.lang.String idiomaID) {
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
    this.idiomaID=idiomaID;
}
  public UsuariBean(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setUsername(__bean.getUsername());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNif(__bean.getNif());
    this.setEmail(__bean.getEmail());
    this.setIdiomaID(__bean.getIdiomaID());
	}

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};



  // ======================================

  public static UsuariBean toBean(Usuari __bean) {
    if (__bean == null) { return null;}
    UsuariBean __tmp = new UsuariBean();
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setNif(__bean.getNif());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setIdiomaID(__bean.getIdiomaID());
		return __tmp;
	}



}
