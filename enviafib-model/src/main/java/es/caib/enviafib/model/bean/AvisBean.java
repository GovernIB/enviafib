
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Avis;


public class AvisBean implements Avis {



	long avisID;// PK
	java.lang.String missatge;
	java.sql.Timestamp datainici;
	java.sql.Timestamp datafi;
	boolean actiu;
	java.lang.String tipus;


  /** Constructor Buit */
  public AvisBean() {
  }

  /** Constructor amb tots els camps  */
  public AvisBean(long avisID , java.lang.String missatge , java.sql.Timestamp datainici , java.sql.Timestamp datafi , boolean actiu , java.lang.String tipus) {
    this.avisID=avisID;
    this.missatge=missatge;
    this.datainici=datainici;
    this.datafi=datafi;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public AvisBean(java.lang.String missatge , java.sql.Timestamp datainici , java.sql.Timestamp datafi , boolean actiu , java.lang.String tipus) {
    this.missatge=missatge;
    this.datainici=datainici;
    this.datafi=datafi;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public AvisBean(long avisID , java.lang.String missatge , boolean actiu , java.lang.String tipus) {
    this.avisID=avisID;
    this.missatge=missatge;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public AvisBean(Avis __bean) {
    this.setAvisID(__bean.getAvisID());
    this.setMissatge(__bean.getMissatge());
    this.setDatainici(__bean.getDatainici());
    this.setDatafi(__bean.getDatafi());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
	}

	public long getAvisID() {
		return(avisID);
	};
	public void setAvisID(long _avisID_) {
		this.avisID = _avisID_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.sql.Timestamp getDatainici() {
		return(datainici);
	};
	public void setDatainici(java.sql.Timestamp _datainici_) {
		this.datainici = _datainici_;
	};

	public java.sql.Timestamp getDatafi() {
		return(datafi);
	};
	public void setDatafi(java.sql.Timestamp _datafi_) {
		this.datafi = _datafi_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};



  // ======================================

  public static AvisBean toBean(Avis __bean) {
    if (__bean == null) { return null;}
    AvisBean __tmp = new AvisBean();
    __tmp.setAvisID(__bean.getAvisID());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setDatainici(__bean.getDatainici());
    __tmp.setDatafi(__bean.getDatafi());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}



}
