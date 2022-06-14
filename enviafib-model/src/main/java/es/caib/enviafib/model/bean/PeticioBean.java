
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
	long estat;
	java.lang.Long fitxerFirmatID;
	java.lang.Long peticioPortafib;
	java.lang.String tipusdocumental;
	java.lang.String idiomadoc;
	java.lang.Long infosignaturaid;


  /** Constructor Buit */
  public PeticioBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioBean(long titolID , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.Long peticioPortafib , java.lang.String tipusdocumental , java.lang.String idiomadoc , java.lang.Long infosignaturaid) {
    this.titolID=titolID;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.peticioPortafib=peticioPortafib;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
    this.infosignaturaid=infosignaturaid;
}
  /** Constructor sense valors autoincrementals */
  public PeticioBean(long titolID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.Long peticioPortafib , java.lang.String tipusdocumental , java.lang.String idiomadoc , java.lang.Long infosignaturaid) {
    this.titolID=titolID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.peticioPortafib=peticioPortafib;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
    this.infosignaturaid=infosignaturaid;
}
  /** Constructor dels valors Not Null */
  public PeticioBean(long titolID , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.String tipusdocumental , java.lang.String idiomadoc) {
    this.titolID=titolID;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
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
    this.setPeticioPortafib(__bean.getPeticioPortafib());
    this.setTipusdocumental(__bean.getTipusdocumental());
    this.setIdiomadoc(__bean.getIdiomadoc());
    this.setInfosignaturaid(__bean.getInfosignaturaid());
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

	public long getEstat() {
		return(estat);
	};
	public void setEstat(long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.Long getPeticioPortafib() {
		return(peticioPortafib);
	};
	public void setPeticioPortafib(java.lang.Long _peticioPortafib_) {
		this.peticioPortafib = _peticioPortafib_;
	};

	public java.lang.String getTipusdocumental() {
		return(tipusdocumental);
	};
	public void setTipusdocumental(java.lang.String _tipusdocumental_) {
		this.tipusdocumental = _tipusdocumental_;
	};

	public java.lang.String getIdiomadoc() {
		return(idiomadoc);
	};
	public void setIdiomadoc(java.lang.String _idiomadoc_) {
		this.idiomadoc = _idiomadoc_;
	};

	public java.lang.Long getInfosignaturaid() {
		return(infosignaturaid);
	};
	public void setInfosignaturaid(java.lang.Long _infosignaturaid_) {
		this.infosignaturaid = _infosignaturaid_;
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
    __tmp.setPeticioPortafib(__bean.getPeticioPortafib());
    __tmp.setTipusdocumental(__bean.getTipusdocumental());
    __tmp.setIdiomadoc(__bean.getIdiomadoc());
    __tmp.setInfosignaturaid(__bean.getInfosignaturaid());
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
