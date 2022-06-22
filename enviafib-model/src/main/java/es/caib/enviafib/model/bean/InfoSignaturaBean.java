
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoSignatura;


public class InfoSignaturaBean implements InfoSignatura {



private static final long serialVersionUID = 986528854L;

	long infoSignaturaID;// PK
	int signOperation;
	java.lang.String signType;
	java.lang.String signAlgorithm;
	java.lang.Integer signMode;
	java.lang.Integer signaturesTableLocation;
	java.lang.Boolean timestampIncluded;
	java.lang.Boolean policyIncluded;
	java.lang.String eniTipoFirma;
	java.lang.String eniPerfilFirma;
	java.lang.String eniRolFirma;
	java.lang.String eniSignerName;
	java.lang.String eniSignerAdministrationId;
	java.lang.String eniSignLevel;
	java.lang.Boolean checkAdministrationIdOfSigner;
	java.lang.Boolean checkDocumentModifications;
	java.lang.Boolean checkValidationSignature;


  /** Constructor Buit */
  public InfoSignaturaBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoSignaturaBean(long infoSignaturaID , int signOperation , java.lang.String signType , java.lang.String signAlgorithm , java.lang.Integer signMode , java.lang.Integer signaturesTableLocation , java.lang.Boolean timestampIncluded , java.lang.Boolean policyIncluded , java.lang.String eniTipoFirma , java.lang.String eniPerfilFirma , java.lang.String eniRolFirma , java.lang.String eniSignerName , java.lang.String eniSignerAdministrationId , java.lang.String eniSignLevel , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature) {
    this.infoSignaturaID=infoSignaturaID;
    this.signOperation=signOperation;
    this.signType=signType;
    this.signAlgorithm=signAlgorithm;
    this.signMode=signMode;
    this.signaturesTableLocation=signaturesTableLocation;
    this.timestampIncluded=timestampIncluded;
    this.policyIncluded=policyIncluded;
    this.eniTipoFirma=eniTipoFirma;
    this.eniPerfilFirma=eniPerfilFirma;
    this.eniRolFirma=eniRolFirma;
    this.eniSignerName=eniSignerName;
    this.eniSignerAdministrationId=eniSignerAdministrationId;
    this.eniSignLevel=eniSignLevel;
    this.checkAdministrationIdOfSigner=checkAdministrationIdOfSigner;
    this.checkDocumentModifications=checkDocumentModifications;
    this.checkValidationSignature=checkValidationSignature;
}
  /** Constructor sense valors autoincrementals */
  public InfoSignaturaBean(int signOperation , java.lang.String signType , java.lang.String signAlgorithm , java.lang.Integer signMode , java.lang.Integer signaturesTableLocation , java.lang.Boolean timestampIncluded , java.lang.Boolean policyIncluded , java.lang.String eniTipoFirma , java.lang.String eniPerfilFirma , java.lang.String eniRolFirma , java.lang.String eniSignerName , java.lang.String eniSignerAdministrationId , java.lang.String eniSignLevel , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature) {
    this.signOperation=signOperation;
    this.signType=signType;
    this.signAlgorithm=signAlgorithm;
    this.signMode=signMode;
    this.signaturesTableLocation=signaturesTableLocation;
    this.timestampIncluded=timestampIncluded;
    this.policyIncluded=policyIncluded;
    this.eniTipoFirma=eniTipoFirma;
    this.eniPerfilFirma=eniPerfilFirma;
    this.eniRolFirma=eniRolFirma;
    this.eniSignerName=eniSignerName;
    this.eniSignerAdministrationId=eniSignerAdministrationId;
    this.eniSignLevel=eniSignLevel;
    this.checkAdministrationIdOfSigner=checkAdministrationIdOfSigner;
    this.checkDocumentModifications=checkDocumentModifications;
    this.checkValidationSignature=checkValidationSignature;
}
  /** Constructor dels valors Not Null */
  public InfoSignaturaBean(long infoSignaturaID , int signOperation , java.lang.String signType) {
    this.infoSignaturaID=infoSignaturaID;
    this.signOperation=signOperation;
    this.signType=signType;
}
  public InfoSignaturaBean(InfoSignatura __bean) {
    this.setInfoSignaturaID(__bean.getInfoSignaturaID());
    this.setSignOperation(__bean.getSignOperation());
    this.setSignType(__bean.getSignType());
    this.setSignAlgorithm(__bean.getSignAlgorithm());
    this.setSignMode(__bean.getSignMode());
    this.setSignaturesTableLocation(__bean.getSignaturesTableLocation());
    this.setTimestampIncluded(__bean.getTimestampIncluded());
    this.setPolicyIncluded(__bean.getPolicyIncluded());
    this.setEniTipoFirma(__bean.getEniTipoFirma());
    this.setEniPerfilFirma(__bean.getEniPerfilFirma());
    this.setEniRolFirma(__bean.getEniRolFirma());
    this.setEniSignerName(__bean.getEniSignerName());
    this.setEniSignerAdministrationId(__bean.getEniSignerAdministrationId());
    this.setEniSignLevel(__bean.getEniSignLevel());
    this.setCheckAdministrationIdOfSigner(__bean.getCheckAdministrationIdOfSigner());
    this.setCheckDocumentModifications(__bean.getCheckDocumentModifications());
    this.setCheckValidationSignature(__bean.getCheckValidationSignature());
	}

	public long getInfoSignaturaID() {
		return(infoSignaturaID);
	};
	public void setInfoSignaturaID(long _infoSignaturaID_) {
		this.infoSignaturaID = _infoSignaturaID_;
	};

	public int getSignOperation() {
		return(signOperation);
	};
	public void setSignOperation(int _signOperation_) {
		this.signOperation = _signOperation_;
	};

	public java.lang.String getSignType() {
		return(signType);
	};
	public void setSignType(java.lang.String _signType_) {
		this.signType = _signType_;
	};

	public java.lang.String getSignAlgorithm() {
		return(signAlgorithm);
	};
	public void setSignAlgorithm(java.lang.String _signAlgorithm_) {
		this.signAlgorithm = _signAlgorithm_;
	};

	public java.lang.Integer getSignMode() {
		return(signMode);
	};
	public void setSignMode(java.lang.Integer _signMode_) {
		this.signMode = _signMode_;
	};

	public java.lang.Integer getSignaturesTableLocation() {
		return(signaturesTableLocation);
	};
	public void setSignaturesTableLocation(java.lang.Integer _signaturesTableLocation_) {
		this.signaturesTableLocation = _signaturesTableLocation_;
	};

	public java.lang.Boolean getTimestampIncluded() {
		return(timestampIncluded);
	};
	public void setTimestampIncluded(java.lang.Boolean _timestampIncluded_) {
		this.timestampIncluded = _timestampIncluded_;
	};

	public java.lang.Boolean getPolicyIncluded() {
		return(policyIncluded);
	};
	public void setPolicyIncluded(java.lang.Boolean _policyIncluded_) {
		this.policyIncluded = _policyIncluded_;
	};

	public java.lang.String getEniTipoFirma() {
		return(eniTipoFirma);
	};
	public void setEniTipoFirma(java.lang.String _eniTipoFirma_) {
		this.eniTipoFirma = _eniTipoFirma_;
	};

	public java.lang.String getEniPerfilFirma() {
		return(eniPerfilFirma);
	};
	public void setEniPerfilFirma(java.lang.String _eniPerfilFirma_) {
		this.eniPerfilFirma = _eniPerfilFirma_;
	};

	public java.lang.String getEniRolFirma() {
		return(eniRolFirma);
	};
	public void setEniRolFirma(java.lang.String _eniRolFirma_) {
		this.eniRolFirma = _eniRolFirma_;
	};

	public java.lang.String getEniSignerName() {
		return(eniSignerName);
	};
	public void setEniSignerName(java.lang.String _eniSignerName_) {
		this.eniSignerName = _eniSignerName_;
	};

	public java.lang.String getEniSignerAdministrationId() {
		return(eniSignerAdministrationId);
	};
	public void setEniSignerAdministrationId(java.lang.String _eniSignerAdministrationId_) {
		this.eniSignerAdministrationId = _eniSignerAdministrationId_;
	};

	public java.lang.String getEniSignLevel() {
		return(eniSignLevel);
	};
	public void setEniSignLevel(java.lang.String _eniSignLevel_) {
		this.eniSignLevel = _eniSignLevel_;
	};

	public java.lang.Boolean getCheckAdministrationIdOfSigner() {
		return(checkAdministrationIdOfSigner);
	};
	public void setCheckAdministrationIdOfSigner(java.lang.Boolean _checkAdministrationIdOfSigner_) {
		this.checkAdministrationIdOfSigner = _checkAdministrationIdOfSigner_;
	};

	public java.lang.Boolean getCheckDocumentModifications() {
		return(checkDocumentModifications);
	};
	public void setCheckDocumentModifications(java.lang.Boolean _checkDocumentModifications_) {
		this.checkDocumentModifications = _checkDocumentModifications_;
	};

	public java.lang.Boolean getCheckValidationSignature() {
		return(checkValidationSignature);
	};
	public void setCheckValidationSignature(java.lang.Boolean _checkValidationSignature_) {
		this.checkValidationSignature = _checkValidationSignature_;
	};



  // ======================================

  public static InfoSignaturaBean toBean(InfoSignatura __bean) {
    if (__bean == null) { return null;}
    InfoSignaturaBean __tmp = new InfoSignaturaBean();
    __tmp.setInfoSignaturaID(__bean.getInfoSignaturaID());
    __tmp.setSignOperation(__bean.getSignOperation());
    __tmp.setSignType(__bean.getSignType());
    __tmp.setSignAlgorithm(__bean.getSignAlgorithm());
    __tmp.setSignMode(__bean.getSignMode());
    __tmp.setSignaturesTableLocation(__bean.getSignaturesTableLocation());
    __tmp.setTimestampIncluded(__bean.getTimestampIncluded());
    __tmp.setPolicyIncluded(__bean.getPolicyIncluded());
    __tmp.setEniTipoFirma(__bean.getEniTipoFirma());
    __tmp.setEniPerfilFirma(__bean.getEniPerfilFirma());
    __tmp.setEniRolFirma(__bean.getEniRolFirma());
    __tmp.setEniSignerName(__bean.getEniSignerName());
    __tmp.setEniSignerAdministrationId(__bean.getEniSignerAdministrationId());
    __tmp.setEniSignLevel(__bean.getEniSignLevel());
    __tmp.setCheckAdministrationIdOfSigner(__bean.getCheckAdministrationIdOfSigner());
    __tmp.setCheckDocumentModifications(__bean.getCheckDocumentModifications());
    __tmp.setCheckValidationSignature(__bean.getCheckValidationSignature());
		return __tmp;
	}



}
