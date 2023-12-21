
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoAnex;


public class InfoAnexBean implements InfoAnex {



	long infoanexid;// PK
	java.lang.Long peticioID;
	java.lang.Long anexID;


  /** Constructor Buit */
  public InfoAnexBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoAnexBean(long infoanexid , java.lang.Long peticioID , java.lang.Long anexID) {
    this.infoanexid=infoanexid;
    this.peticioID=peticioID;
    this.anexID=anexID;
}
  /** Constructor sense valors autoincrementals */
  public InfoAnexBean(java.lang.Long peticioID , java.lang.Long anexID) {
    this.peticioID=peticioID;
    this.anexID=anexID;
}
  /** Constructor dels valors Not Null */
  public InfoAnexBean(long infoanexid) {
    this.infoanexid=infoanexid;
}
  public InfoAnexBean(InfoAnex __bean) {
    this.setInfoanexid(__bean.getInfoanexid());
    this.setPeticioID(__bean.getPeticioID());
    this.setAnexID(__bean.getAnexID());
    // Fitxer
    this.setAnex(FitxerBean.toBean(__bean.getAnex()));
	}

	public long getInfoanexid() {
		return(infoanexid);
	};
	public void setInfoanexid(long _infoanexid_) {
		this.infoanexid = _infoanexid_;
	};

	public java.lang.Long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(java.lang.Long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.lang.Long getAnexID() {
		return(anexID);
	};
	public void setAnexID(java.lang.Long _anexID_) {
		this.anexID = _anexID_;
	};



  // ======================================

  public static InfoAnexBean toBean(InfoAnex __bean) {
    if (__bean == null) { return null;}
    InfoAnexBean __tmp = new InfoAnexBean();
    __tmp.setInfoanexid(__bean.getInfoanexid());
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setAnexID(__bean.getAnexID());
    // Fitxer
    __tmp.setAnex(FitxerBean.toBean(__bean.getAnex()));
		return __tmp;
	}

  protected FitxerBean anex;
  public FitxerBean getAnex() {
    return anex;
  }
  public void setAnex(FitxerBean __field) {
    this. anex = __field;
  }


}
