
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Peticio;


public class PeticioBean implements Peticio {



private static final long serialVersionUID = -515854961L;

	long peticioID;// PK
	long titolID;
	java.sql.Timestamp datacreacio;
	long fitxerID;
	long solicitantID;
	java.lang.String idiomaID;
	java.lang.String destinatarinif;
	short estat;
	java.lang.Long fitxerFirmatID;


  /** Constructor Buit */
  public PeticioBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioBean(long titolID , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , short estat , java.lang.Long fitxerFirmatID) {
    this.titolID=titolID;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
}
  /** Constructor sense valors autoincrementals */
  public PeticioBean(long titolID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , short estat , java.lang.Long fitxerFirmatID) {
    this.titolID=titolID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
}
  public PeticioBean(Peticio __bean) {
    this.setTitolID(__bean.getTitolID());
    this.setPeticioID(__bean.getPeticioID());
    this.setDatacreacio(__bean.getDatacreacio());
    this.setFitxerID(__bean.getFitxerID());
    this.setSolicitantID(__bean.getSolicitantID());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDestinatarinif(__bean.getDestinatarinif());
    this.setEstat(__bean.getEstat());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    this.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
	}

	public long getTitolID() {
		return(titolID);
	};
	public void setTitolID(long _titolID_) {
		this.titolID = _titolID_;
	};

	public long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.sql.Timestamp getDatacreacio() {
		return(datacreacio);
	};
	public void setDatacreacio(java.sql.Timestamp _datacreacio_) {
		this.datacreacio = _datacreacio_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public long getSolicitantID() {
		return(solicitantID);
	};
	public void setSolicitantID(long _solicitantID_) {
		this.solicitantID = _solicitantID_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getDestinatarinif() {
		return(destinatarinif);
	};
	public void setDestinatarinif(java.lang.String _destinatarinif_) {
		this.destinatarinif = _destinatarinif_;
	};

	public short getEstat() {
		return(estat);
	};
	public void setEstat(short _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};



  // ======================================

  public static PeticioBean toBean(Peticio __bean) {
    if (__bean == null) { return null;}
    PeticioBean __tmp = new PeticioBean();
    __tmp.setTitolID(__bean.getTitolID());
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setDatacreacio(__bean.getDatacreacio());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setSolicitantID(__bean.getSolicitantID());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDestinatarinif(__bean.getDestinatarinif());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    // Fitxer
    __tmp.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
		return __tmp;
	}

  protected FitxerBean fitxer;
  public FitxerBean getFitxer() {
    return fitxer;
  }
  public void setFitxer(FitxerBean __field) {
    this. fitxer = __field;
  }
  protected FitxerBean fitxerFirmat;
  public FitxerBean getFitxerFirmat() {
    return fitxerFirmat;
  }
  public void setFitxerFirmat(FitxerBean __field) {
    this. fitxerFirmat = __field;
  }


}
