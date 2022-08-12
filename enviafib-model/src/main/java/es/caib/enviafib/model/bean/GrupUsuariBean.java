
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.GrupUsuari;


public class GrupUsuariBean implements GrupUsuari {



	long grupUsuariID;// PK
	long grupID;
	long usuariID;


  /** Constructor Buit */
  public GrupUsuariBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupUsuariBean(long grupUsuariID , long grupID , long usuariID) {
    this.grupUsuariID=grupUsuariID;
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public GrupUsuariBean(long grupID , long usuariID) {
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  public GrupUsuariBean(GrupUsuari __bean) {
    this.setGrupUsuariID(__bean.getGrupUsuariID());
    this.setGrupID(__bean.getGrupID());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getGrupUsuariID() {
		return(grupUsuariID);
	};
	public void setGrupUsuariID(long _grupUsuariID_) {
		this.grupUsuariID = _grupUsuariID_;
	};

	public long getGrupID() {
		return(grupID);
	};
	public void setGrupID(long _grupID_) {
		this.grupID = _grupID_;
	};

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};



  // ======================================

  public static GrupUsuariBean toBean(GrupUsuari __bean) {
    if (__bean == null) { return null;}
    GrupUsuariBean __tmp = new GrupUsuariBean();
    __tmp.setGrupUsuariID(__bean.getGrupUsuariID());
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}



}
