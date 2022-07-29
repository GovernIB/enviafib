
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.GrupUsuari;


public class GrupUsuariBean implements GrupUsuari {



private static final long serialVersionUID = -2016371145L;

	long grupUsuariID;// PK
	java.lang.Long grupID;
	java.lang.Long usuariID;


  /** Constructor Buit */
  public GrupUsuariBean() {
  }

  /** Constructor amb tots els camps  */
  public GrupUsuariBean(long grupUsuariID , java.lang.Long grupID , java.lang.Long usuariID) {
    this.grupUsuariID=grupUsuariID;
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public GrupUsuariBean(java.lang.Long grupID , java.lang.Long usuariID) {
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  /** Constructor dels valors Not Null */
  public GrupUsuariBean(long grupUsuariID) {
    this.grupUsuariID=grupUsuariID;
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

	public java.lang.Long getGrupID() {
		return(grupID);
	};
	public void setGrupID(java.lang.Long _grupID_) {
		this.grupID = _grupID_;
	};

	public java.lang.Long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(java.lang.Long _usuariID_) {
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
