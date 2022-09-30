
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Organitzacio;


public class OrganitzacioBean implements Organitzacio {



	long organitzacioID;// PK
	java.lang.String codiConselleria;
	java.lang.String codiDireccioGeneral;
	java.lang.String tipus;
	java.lang.String valor;


  /** Constructor Buit */
  public OrganitzacioBean() {
  }

  /** Constructor amb tots els camps  */
  public OrganitzacioBean(long organitzacioID , java.lang.String codiConselleria , java.lang.String codiDireccioGeneral , java.lang.String tipus , java.lang.String valor) {
    this.organitzacioID=organitzacioID;
    this.codiConselleria=codiConselleria;
    this.codiDireccioGeneral=codiDireccioGeneral;
    this.tipus=tipus;
    this.valor=valor;
}
  /** Constructor sense valors autoincrementals */
  public OrganitzacioBean(java.lang.String codiConselleria , java.lang.String codiDireccioGeneral , java.lang.String tipus , java.lang.String valor) {
    this.codiConselleria=codiConselleria;
    this.codiDireccioGeneral=codiDireccioGeneral;
    this.tipus=tipus;
    this.valor=valor;
}
  /** Constructor dels valors Not Null */
  public OrganitzacioBean(long organitzacioID) {
    this.organitzacioID=organitzacioID;
}
  public OrganitzacioBean(Organitzacio __bean) {
    this.setOrganitzacioID(__bean.getOrganitzacioID());
    this.setCodiConselleria(__bean.getCodiConselleria());
    this.setCodiDireccioGeneral(__bean.getCodiDireccioGeneral());
    this.setTipus(__bean.getTipus());
    this.setValor(__bean.getValor());
	}

	public long getOrganitzacioID() {
		return(organitzacioID);
	};
	public void setOrganitzacioID(long _organitzacioID_) {
		this.organitzacioID = _organitzacioID_;
	};

	public java.lang.String getCodiConselleria() {
		return(codiConselleria);
	};
	public void setCodiConselleria(java.lang.String _codiConselleria_) {
		this.codiConselleria = _codiConselleria_;
	};

	public java.lang.String getCodiDireccioGeneral() {
		return(codiDireccioGeneral);
	};
	public void setCodiDireccioGeneral(java.lang.String _codiDireccioGeneral_) {
		this.codiDireccioGeneral = _codiDireccioGeneral_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};



  // ======================================

  public static OrganitzacioBean toBean(Organitzacio __bean) {
    if (__bean == null) { return null;}
    OrganitzacioBean __tmp = new OrganitzacioBean();
    __tmp.setOrganitzacioID(__bean.getOrganitzacioID());
    __tmp.setCodiConselleria(__bean.getCodiConselleria());
    __tmp.setCodiDireccioGeneral(__bean.getCodiDireccioGeneral());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setValor(__bean.getValor());
		return __tmp;
	}



}
