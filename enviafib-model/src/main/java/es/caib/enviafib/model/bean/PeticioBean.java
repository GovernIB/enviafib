
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Peticio;


public class PeticioBean implements Peticio {



	long peticioID;// PK
	java.lang.String nom;
	java.sql.Timestamp dataCreacio;
	java.sql.Timestamp dataFinal;
	long fitxerID;
	long solicitantID;
	java.lang.String idiomaID;
	java.lang.String destinatariNif;
	int estat;
	java.lang.Long fitxerFirmatID;
	java.lang.String tipusDocumental;
	java.lang.String idiomaDoc;
	java.lang.Long infoSignaturaID;
	int tipus;
	java.lang.String errorMsg;
	java.lang.String errorException;
	java.lang.String peticioPortafirmes;
	java.lang.String reason;
	java.lang.String arxiuFuncionariUsername;
	java.lang.String arxiuParamFuncionariNom;
	java.lang.String arxiuParamFuncionariNif;
	java.lang.String arxiuParamFuncionariDir3;
	java.lang.String arxiuReqParamDocEstatElabora;
	java.lang.String arxiuReqParamInteressats;
	java.lang.String arxiuReqParamCiutadaNif;
	java.lang.String arxiuReqParamCiutadaNom;
	java.lang.String arxiuReqParamOrgans;
	java.lang.String arxiuOptParamProcedimentCodi;
	java.lang.String arxiuOptParamProcedimentNom;
	java.lang.String arxiuOptParamSerieDocumental;
	java.lang.String arxiuOptParamExpedientId;
	java.lang.Integer arxiuReqParamOrigen;
	java.lang.Long infoArxiuID;


  /** Constructor Buit */
  public PeticioBean() {
  }

  /** Constructor amb tots els camps  */
  public PeticioBean(long peticioID , java.lang.String nom , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataFinal , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatariNif , int estat , java.lang.Long fitxerFirmatID , java.lang.String tipusDocumental , java.lang.String idiomaDoc , java.lang.Long infoSignaturaID , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.lang.String peticioPortafirmes , java.lang.String reason , java.lang.String arxiuFuncionariUsername , java.lang.String arxiuParamFuncionariNom , java.lang.String arxiuParamFuncionariNif , java.lang.String arxiuParamFuncionariDir3 , java.lang.String arxiuReqParamDocEstatElabora , java.lang.String arxiuReqParamInteressats , java.lang.String arxiuReqParamCiutadaNif , java.lang.String arxiuReqParamCiutadaNom , java.lang.String arxiuReqParamOrgans , java.lang.String arxiuOptParamProcedimentCodi , java.lang.String arxiuOptParamProcedimentNom , java.lang.String arxiuOptParamSerieDocumental , java.lang.String arxiuOptParamExpedientId , java.lang.Integer arxiuReqParamOrigen , java.lang.Long infoArxiuID) {
    this.peticioID=peticioID;
    this.nom=nom;
    this.dataCreacio=dataCreacio;
    this.dataFinal=dataFinal;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatariNif=destinatariNif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.infoSignaturaID=infoSignaturaID;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.peticioPortafirmes=peticioPortafirmes;
    this.reason=reason;
    this.arxiuFuncionariUsername=arxiuFuncionariUsername;
    this.arxiuParamFuncionariNom=arxiuParamFuncionariNom;
    this.arxiuParamFuncionariNif=arxiuParamFuncionariNif;
    this.arxiuParamFuncionariDir3=arxiuParamFuncionariDir3;
    this.arxiuReqParamDocEstatElabora=arxiuReqParamDocEstatElabora;
    this.arxiuReqParamInteressats=arxiuReqParamInteressats;
    this.arxiuReqParamCiutadaNif=arxiuReqParamCiutadaNif;
    this.arxiuReqParamCiutadaNom=arxiuReqParamCiutadaNom;
    this.arxiuReqParamOrgans=arxiuReqParamOrgans;
    this.arxiuOptParamProcedimentCodi=arxiuOptParamProcedimentCodi;
    this.arxiuOptParamProcedimentNom=arxiuOptParamProcedimentNom;
    this.arxiuOptParamSerieDocumental=arxiuOptParamSerieDocumental;
    this.arxiuOptParamExpedientId=arxiuOptParamExpedientId;
    this.arxiuReqParamOrigen=arxiuReqParamOrigen;
    this.infoArxiuID=infoArxiuID;
}
  /** Constructor sense valors autoincrementals */
  public PeticioBean(java.lang.String nom , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataFinal , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatariNif , int estat , java.lang.Long fitxerFirmatID , java.lang.String tipusDocumental , java.lang.String idiomaDoc , java.lang.Long infoSignaturaID , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.lang.String peticioPortafirmes , java.lang.String reason , java.lang.String arxiuFuncionariUsername , java.lang.String arxiuParamFuncionariNom , java.lang.String arxiuParamFuncionariNif , java.lang.String arxiuParamFuncionariDir3 , java.lang.String arxiuReqParamDocEstatElabora , java.lang.String arxiuReqParamInteressats , java.lang.String arxiuReqParamCiutadaNif , java.lang.String arxiuReqParamCiutadaNom , java.lang.String arxiuReqParamOrgans , java.lang.String arxiuOptParamProcedimentCodi , java.lang.String arxiuOptParamProcedimentNom , java.lang.String arxiuOptParamSerieDocumental , java.lang.String arxiuOptParamExpedientId , java.lang.Integer arxiuReqParamOrigen , java.lang.Long infoArxiuID) {
    this.nom=nom;
    this.dataCreacio=dataCreacio;
    this.dataFinal=dataFinal;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatariNif=destinatariNif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.infoSignaturaID=infoSignaturaID;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.peticioPortafirmes=peticioPortafirmes;
    this.reason=reason;
    this.arxiuFuncionariUsername=arxiuFuncionariUsername;
    this.arxiuParamFuncionariNom=arxiuParamFuncionariNom;
    this.arxiuParamFuncionariNif=arxiuParamFuncionariNif;
    this.arxiuParamFuncionariDir3=arxiuParamFuncionariDir3;
    this.arxiuReqParamDocEstatElabora=arxiuReqParamDocEstatElabora;
    this.arxiuReqParamInteressats=arxiuReqParamInteressats;
    this.arxiuReqParamCiutadaNif=arxiuReqParamCiutadaNif;
    this.arxiuReqParamCiutadaNom=arxiuReqParamCiutadaNom;
    this.arxiuReqParamOrgans=arxiuReqParamOrgans;
    this.arxiuOptParamProcedimentCodi=arxiuOptParamProcedimentCodi;
    this.arxiuOptParamProcedimentNom=arxiuOptParamProcedimentNom;
    this.arxiuOptParamSerieDocumental=arxiuOptParamSerieDocumental;
    this.arxiuOptParamExpedientId=arxiuOptParamExpedientId;
    this.arxiuReqParamOrigen=arxiuReqParamOrigen;
    this.infoArxiuID=infoArxiuID;
}
  /** Constructor dels valors Not Null */
  public PeticioBean(long peticioID , java.sql.Timestamp dataCreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , int estat , java.lang.String tipusDocumental , java.lang.String idiomaDoc , int tipus) {
    this.peticioID=peticioID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.estat=estat;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.tipus=tipus;
}
  public PeticioBean(Peticio __bean) {
    this.setPeticioID(__bean.getPeticioID());
    this.setNom(__bean.getNom());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataFinal(__bean.getDataFinal());
    this.setFitxerID(__bean.getFitxerID());
    this.setSolicitantID(__bean.getSolicitantID());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDestinatariNif(__bean.getDestinatariNif());
    this.setEstat(__bean.getEstat());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setTipusDocumental(__bean.getTipusDocumental());
    this.setIdiomaDoc(__bean.getIdiomaDoc());
    this.setInfoSignaturaID(__bean.getInfoSignaturaID());
    this.setTipus(__bean.getTipus());
    this.setErrorMsg(__bean.getErrorMsg());
    this.setErrorException(__bean.getErrorException());
    this.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
    this.setReason(__bean.getReason());
    this.setArxiuFuncionariUsername(__bean.getArxiuFuncionariUsername());
    this.setArxiuParamFuncionariNom(__bean.getArxiuParamFuncionariNom());
    this.setArxiuParamFuncionariNif(__bean.getArxiuParamFuncionariNif());
    this.setArxiuParamFuncionariDir3(__bean.getArxiuParamFuncionariDir3());
    this.setArxiuReqParamDocEstatElabora(__bean.getArxiuReqParamDocEstatElabora());
    this.setArxiuReqParamInteressats(__bean.getArxiuReqParamInteressats());
    this.setArxiuReqParamCiutadaNif(__bean.getArxiuReqParamCiutadaNif());
    this.setArxiuReqParamCiutadaNom(__bean.getArxiuReqParamCiutadaNom());
    this.setArxiuReqParamOrgans(__bean.getArxiuReqParamOrgans());
    this.setArxiuOptParamProcedimentCodi(__bean.getArxiuOptParamProcedimentCodi());
    this.setArxiuOptParamProcedimentNom(__bean.getArxiuOptParamProcedimentNom());
    this.setArxiuOptParamSerieDocumental(__bean.getArxiuOptParamSerieDocumental());
    this.setArxiuOptParamExpedientId(__bean.getArxiuOptParamExpedientId());
    this.setArxiuReqParamOrigen(__bean.getArxiuReqParamOrigen());
    this.setInfoArxiuID(__bean.getInfoArxiuID());
    // Fitxer
    this.setFitxer(FitxerBean.toBean(__bean.getFitxer()));
    // Fitxer
    this.setFitxerFirmat(FitxerBean.toBean(__bean.getFitxerFirmat()));
	}

	public long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataFinal() {
		return(dataFinal);
	};
	public void setDataFinal(java.sql.Timestamp _dataFinal_) {
		this.dataFinal = _dataFinal_;
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

	public java.lang.String getDestinatariNif() {
		return(destinatariNif);
	};
	public void setDestinatariNif(java.lang.String _destinatariNif_) {
		this.destinatariNif = _destinatariNif_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.String getTipusDocumental() {
		return(tipusDocumental);
	};
	public void setTipusDocumental(java.lang.String _tipusDocumental_) {
		this.tipusDocumental = _tipusDocumental_;
	};

	public java.lang.String getIdiomaDoc() {
		return(idiomaDoc);
	};
	public void setIdiomaDoc(java.lang.String _idiomaDoc_) {
		this.idiomaDoc = _idiomaDoc_;
	};

	public java.lang.Long getInfoSignaturaID() {
		return(infoSignaturaID);
	};
	public void setInfoSignaturaID(java.lang.Long _infoSignaturaID_) {
		this.infoSignaturaID = _infoSignaturaID_;
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

	public java.lang.String getPeticioPortafirmes() {
		return(peticioPortafirmes);
	};
	public void setPeticioPortafirmes(java.lang.String _peticioPortafirmes_) {
		this.peticioPortafirmes = _peticioPortafirmes_;
	};

	public java.lang.String getReason() {
		return(reason);
	};
	public void setReason(java.lang.String _reason_) {
		this.reason = _reason_;
	};

	public java.lang.String getArxiuFuncionariUsername() {
		return(arxiuFuncionariUsername);
	};
	public void setArxiuFuncionariUsername(java.lang.String _arxiuFuncionariUsername_) {
		this.arxiuFuncionariUsername = _arxiuFuncionariUsername_;
	};

	public java.lang.String getArxiuParamFuncionariNom() {
		return(arxiuParamFuncionariNom);
	};
	public void setArxiuParamFuncionariNom(java.lang.String _arxiuParamFuncionariNom_) {
		this.arxiuParamFuncionariNom = _arxiuParamFuncionariNom_;
	};

	public java.lang.String getArxiuParamFuncionariNif() {
		return(arxiuParamFuncionariNif);
	};
	public void setArxiuParamFuncionariNif(java.lang.String _arxiuParamFuncionariNif_) {
		this.arxiuParamFuncionariNif = _arxiuParamFuncionariNif_;
	};

	public java.lang.String getArxiuParamFuncionariDir3() {
		return(arxiuParamFuncionariDir3);
	};
	public void setArxiuParamFuncionariDir3(java.lang.String _arxiuParamFuncionariDir3_) {
		this.arxiuParamFuncionariDir3 = _arxiuParamFuncionariDir3_;
	};

	public java.lang.String getArxiuReqParamDocEstatElabora() {
		return(arxiuReqParamDocEstatElabora);
	};
	public void setArxiuReqParamDocEstatElabora(java.lang.String _arxiuReqParamDocEstatElabora_) {
		this.arxiuReqParamDocEstatElabora = _arxiuReqParamDocEstatElabora_;
	};

	public java.lang.String getArxiuReqParamInteressats() {
		return(arxiuReqParamInteressats);
	};
	public void setArxiuReqParamInteressats(java.lang.String _arxiuReqParamInteressats_) {
		this.arxiuReqParamInteressats = _arxiuReqParamInteressats_;
	};

	public java.lang.String getArxiuReqParamCiutadaNif() {
		return(arxiuReqParamCiutadaNif);
	};
	public void setArxiuReqParamCiutadaNif(java.lang.String _arxiuReqParamCiutadaNif_) {
		this.arxiuReqParamCiutadaNif = _arxiuReqParamCiutadaNif_;
	};

	public java.lang.String getArxiuReqParamCiutadaNom() {
		return(arxiuReqParamCiutadaNom);
	};
	public void setArxiuReqParamCiutadaNom(java.lang.String _arxiuReqParamCiutadaNom_) {
		this.arxiuReqParamCiutadaNom = _arxiuReqParamCiutadaNom_;
	};

	public java.lang.String getArxiuReqParamOrgans() {
		return(arxiuReqParamOrgans);
	};
	public void setArxiuReqParamOrgans(java.lang.String _arxiuReqParamOrgans_) {
		this.arxiuReqParamOrgans = _arxiuReqParamOrgans_;
	};

	public java.lang.String getArxiuOptParamProcedimentCodi() {
		return(arxiuOptParamProcedimentCodi);
	};
	public void setArxiuOptParamProcedimentCodi(java.lang.String _arxiuOptParamProcedimentCodi_) {
		this.arxiuOptParamProcedimentCodi = _arxiuOptParamProcedimentCodi_;
	};

	public java.lang.String getArxiuOptParamProcedimentNom() {
		return(arxiuOptParamProcedimentNom);
	};
	public void setArxiuOptParamProcedimentNom(java.lang.String _arxiuOptParamProcedimentNom_) {
		this.arxiuOptParamProcedimentNom = _arxiuOptParamProcedimentNom_;
	};

	public java.lang.String getArxiuOptParamSerieDocumental() {
		return(arxiuOptParamSerieDocumental);
	};
	public void setArxiuOptParamSerieDocumental(java.lang.String _arxiuOptParamSerieDocumental_) {
		this.arxiuOptParamSerieDocumental = _arxiuOptParamSerieDocumental_;
	};

	public java.lang.String getArxiuOptParamExpedientId() {
		return(arxiuOptParamExpedientId);
	};
	public void setArxiuOptParamExpedientId(java.lang.String _arxiuOptParamExpedientId_) {
		this.arxiuOptParamExpedientId = _arxiuOptParamExpedientId_;
	};

	public java.lang.Integer getArxiuReqParamOrigen() {
		return(arxiuReqParamOrigen);
	};
	public void setArxiuReqParamOrigen(java.lang.Integer _arxiuReqParamOrigen_) {
		this.arxiuReqParamOrigen = _arxiuReqParamOrigen_;
	};

	public java.lang.Long getInfoArxiuID() {
		return(infoArxiuID);
	};
	public void setInfoArxiuID(java.lang.Long _infoArxiuID_) {
		this.infoArxiuID = _infoArxiuID_;
	};



  // ======================================

  public static PeticioBean toBean(Peticio __bean) {
    if (__bean == null) { return null;}
    PeticioBean __tmp = new PeticioBean();
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setSolicitantID(__bean.getSolicitantID());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDestinatariNif(__bean.getDestinatariNif());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setTipusDocumental(__bean.getTipusDocumental());
    __tmp.setIdiomaDoc(__bean.getIdiomaDoc());
    __tmp.setInfoSignaturaID(__bean.getInfoSignaturaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setErrorMsg(__bean.getErrorMsg());
    __tmp.setErrorException(__bean.getErrorException());
    __tmp.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
    __tmp.setReason(__bean.getReason());
    __tmp.setArxiuFuncionariUsername(__bean.getArxiuFuncionariUsername());
    __tmp.setArxiuParamFuncionariNom(__bean.getArxiuParamFuncionariNom());
    __tmp.setArxiuParamFuncionariNif(__bean.getArxiuParamFuncionariNif());
    __tmp.setArxiuParamFuncionariDir3(__bean.getArxiuParamFuncionariDir3());
    __tmp.setArxiuReqParamDocEstatElabora(__bean.getArxiuReqParamDocEstatElabora());
    __tmp.setArxiuReqParamInteressats(__bean.getArxiuReqParamInteressats());
    __tmp.setArxiuReqParamCiutadaNif(__bean.getArxiuReqParamCiutadaNif());
    __tmp.setArxiuReqParamCiutadaNom(__bean.getArxiuReqParamCiutadaNom());
    __tmp.setArxiuReqParamOrgans(__bean.getArxiuReqParamOrgans());
    __tmp.setArxiuOptParamProcedimentCodi(__bean.getArxiuOptParamProcedimentCodi());
    __tmp.setArxiuOptParamProcedimentNom(__bean.getArxiuOptParamProcedimentNom());
    __tmp.setArxiuOptParamSerieDocumental(__bean.getArxiuOptParamSerieDocumental());
    __tmp.setArxiuOptParamExpedientId(__bean.getArxiuOptParamExpedientId());
    __tmp.setArxiuReqParamOrigen(__bean.getArxiuReqParamOrigen());
    __tmp.setInfoArxiuID(__bean.getInfoArxiuID());
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
