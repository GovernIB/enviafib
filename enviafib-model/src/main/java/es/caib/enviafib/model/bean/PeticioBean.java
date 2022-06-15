
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Peticio;


public class PeticioBean implements Peticio {



private static final long serialVersionUID = -515854961L;

	long peticioID;// PK
	java.lang.String nom;
	java.sql.Timestamp datacreacio;
	long fitxerID;
	long solicitantID;
	java.lang.String idiomaID;
	java.lang.String destinatarinif;
	long estat;
	java.lang.Long fitxerFirmatID;
	java.lang.String tipusdocumental;
	java.lang.String idiomadoc;
	java.lang.Long infosignaturaid;
	int tipus;
	java.lang.String errorMsg;
	java.lang.String errorException;
	java.sql.Timestamp dataFinal;
	java.lang.String peticioPortafirmes;


  /** Constructor Buit */
  public PeticioBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioBean(java.lang.String nom , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.String tipusdocumental , java.lang.String idiomadoc , java.lang.Long infosignaturaid , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.sql.Timestamp dataFinal , java.lang.String peticioPortafirmes) {
    this.nom=nom;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
    this.infosignaturaid=infosignaturaid;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.dataFinal=dataFinal;
    this.peticioPortafirmes=peticioPortafirmes;
}
  /** Constructor sense valors autoincrementals */
  public PeticioBean(java.lang.String nom , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.String tipusdocumental , java.lang.String idiomadoc , java.lang.Long infosignaturaid , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.sql.Timestamp dataFinal , java.lang.String peticioPortafirmes) {
    this.nom=nom;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
    this.infosignaturaid=infosignaturaid;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.dataFinal=dataFinal;
    this.peticioPortafirmes=peticioPortafirmes;
}
  /** Constructor dels valors Not Null */
  public PeticioBean(long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.String tipusdocumental , java.lang.String idiomadoc , int tipus) {
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.tipusdocumental=tipusdocumental;
    this.idiomadoc=idiomadoc;
    this.tipus=tipus;
}
  public PeticioBean(Peticio __bean) {
    this.setNom(__bean.getNom());
    this.setPeticioID(__bean.getPeticioID());
    this.setDatacreacio(__bean.getDatacreacio());
    this.setFitxerID(__bean.getFitxerID());
    this.setSolicitantID(__bean.getSolicitantID());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDestinatarinif(__bean.getDestinatarinif());
    this.setEstat(__bean.getEstat());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setTipusdocumental(__bean.getTipusdocumental());
    this.setIdiomadoc(__bean.getIdiomadoc());
    this.setInfosignaturaid(__bean.getInfosignaturaid());
    this.setTipus(__bean.getTipus());
    this.setErrorMsg(__bean.getErrorMsg());
    this.setErrorException(__bean.getErrorException());
    this.setDataFinal(__bean.getDataFinal());
    this.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    this.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
	}

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
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

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getErrorMsg() {
		return(errorMsg);
	};
	public void setErrorMsg(java.lang.String _errorMsg_) {
		this.errorMsg = _errorMsg_;
	};

	public java.lang.String getErrorException() {
		return(errorException);
	};
	public void setErrorException(java.lang.String _errorException_) {
		this.errorException = _errorException_;
	};

	public java.sql.Timestamp getDataFinal() {
		return(dataFinal);
	};
	public void setDataFinal(java.sql.Timestamp _dataFinal_) {
		this.dataFinal = _dataFinal_;
	};

	public java.lang.String getPeticioPortafirmes() {
		return(peticioPortafirmes);
	};
	public void setPeticioPortafirmes(java.lang.String _peticioPortafirmes_) {
		this.peticioPortafirmes = _peticioPortafirmes_;
	};



  // ======================================

  public static PeticioBean toBean(Peticio __bean) {
    if (__bean == null) { return null;}
    PeticioBean __tmp = new PeticioBean();
    __tmp.setNom(__bean.getNom());
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setDatacreacio(__bean.getDatacreacio());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setSolicitantID(__bean.getSolicitantID());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDestinatarinif(__bean.getDestinatarinif());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setTipusdocumental(__bean.getTipusdocumental());
    __tmp.setIdiomadoc(__bean.getIdiomadoc());
    __tmp.setInfosignaturaid(__bean.getInfosignaturaid());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setErrorMsg(__bean.getErrorMsg());
    __tmp.setErrorException(__bean.getErrorException());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
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
