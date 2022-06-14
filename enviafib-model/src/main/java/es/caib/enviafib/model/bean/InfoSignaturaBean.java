
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoSignatura;


public class InfoSignaturaBean implements InfoSignatura {



private static final long serialVersionUID = 986528854L;

	long infosignaturaid;// PK
	int signoperation;
	java.lang.String signtype;
	java.lang.String signalgorithm;
	java.lang.Integer signmode;
	java.lang.Integer signaturestablelocation;
	boolean timestampincluded;
	boolean policyincluded;
	java.lang.String enitipofirma;
	java.lang.String eniperfilfirma;
	java.lang.String enirolfirma;
	java.lang.String enisignername;
	java.lang.String enisigneradministrationid;
	java.lang.String enisignlevel;
	boolean checkadministrationidofsigner;
	boolean checkdocumentmodifications;
	boolean checkvalidationsignature;


  /** Constructor Buit */
  public InfoSignaturaBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoSignaturaBean(long infosignaturaid , int signoperation , java.lang.String signtype , java.lang.String signalgorithm , java.lang.Integer signmode , java.lang.Integer signaturestablelocation , boolean timestampincluded , boolean policyincluded , java.lang.String enitipofirma , java.lang.String eniperfilfirma , java.lang.String enirolfirma , java.lang.String enisignername , java.lang.String enisigneradministrationid , java.lang.String enisignlevel , boolean checkadministrationidofsigner , boolean checkdocumentmodifications , boolean checkvalidationsignature) {
    this.infosignaturaid=infosignaturaid;
    this.signoperation=signoperation;
    this.signtype=signtype;
    this.signalgorithm=signalgorithm;
    this.signmode=signmode;
    this.signaturestablelocation=signaturestablelocation;
    this.timestampincluded=timestampincluded;
    this.policyincluded=policyincluded;
    this.enitipofirma=enitipofirma;
    this.eniperfilfirma=eniperfilfirma;
    this.enirolfirma=enirolfirma;
    this.enisignername=enisignername;
    this.enisigneradministrationid=enisigneradministrationid;
    this.enisignlevel=enisignlevel;
    this.checkadministrationidofsigner=checkadministrationidofsigner;
    this.checkdocumentmodifications=checkdocumentmodifications;
    this.checkvalidationsignature=checkvalidationsignature;
}
  /** Constructor sense valors autoincrementals */
  public InfoSignaturaBean(int signoperation , java.lang.String signtype , java.lang.String signalgorithm , java.lang.Integer signmode , java.lang.Integer signaturestablelocation , boolean timestampincluded , boolean policyincluded , java.lang.String enitipofirma , java.lang.String eniperfilfirma , java.lang.String enirolfirma , java.lang.String enisignername , java.lang.String enisigneradministrationid , java.lang.String enisignlevel , boolean checkadministrationidofsigner , boolean checkdocumentmodifications , boolean checkvalidationsignature) {
    this.signoperation=signoperation;
    this.signtype=signtype;
    this.signalgorithm=signalgorithm;
    this.signmode=signmode;
    this.signaturestablelocation=signaturestablelocation;
    this.timestampincluded=timestampincluded;
    this.policyincluded=policyincluded;
    this.enitipofirma=enitipofirma;
    this.eniperfilfirma=eniperfilfirma;
    this.enirolfirma=enirolfirma;
    this.enisignername=enisignername;
    this.enisigneradministrationid=enisigneradministrationid;
    this.enisignlevel=enisignlevel;
    this.checkadministrationidofsigner=checkadministrationidofsigner;
    this.checkdocumentmodifications=checkdocumentmodifications;
    this.checkvalidationsignature=checkvalidationsignature;
}
  /** Constructor dels valors Not Null */
  public InfoSignaturaBean(long infosignaturaid , int signoperation , java.lang.String signtype) {
    this.infosignaturaid=infosignaturaid;
    this.signoperation=signoperation;
    this.signtype=signtype;
}
  public InfoSignaturaBean(InfoSignatura __bean) {
    this.setInfosignaturaid(__bean.getInfosignaturaid());
    this.setSignoperation(__bean.getSignoperation());
    this.setSigntype(__bean.getSigntype());
    this.setSignalgorithm(__bean.getSignalgorithm());
    this.setSignmode(__bean.getSignmode());
    this.setSignaturestablelocation(__bean.getSignaturestablelocation());
    this.setTimestampincluded(__bean.isTimestampincluded());
    this.setPolicyincluded(__bean.isPolicyincluded());
    this.setEnitipofirma(__bean.getEnitipofirma());
    this.setEniperfilfirma(__bean.getEniperfilfirma());
    this.setEnirolfirma(__bean.getEnirolfirma());
    this.setEnisignername(__bean.getEnisignername());
    this.setEnisigneradministrationid(__bean.getEnisigneradministrationid());
    this.setEnisignlevel(__bean.getEnisignlevel());
    this.setCheckadministrationidofsigner(__bean.isCheckadministrationidofsigner());
    this.setCheckdocumentmodifications(__bean.isCheckdocumentmodifications());
    this.setCheckvalidationsignature(__bean.isCheckvalidationsignature());
	}

	public long getInfosignaturaid() {
		return(infosignaturaid);
	};
	public void setInfosignaturaid(long _infosignaturaid_) {
		this.infosignaturaid = _infosignaturaid_;
	};

	public int getSignoperation() {
		return(signoperation);
	};
	public void setSignoperation(int _signoperation_) {
		this.signoperation = _signoperation_;
	};

	public java.lang.String getSigntype() {
		return(signtype);
	};
	public void setSigntype(java.lang.String _signtype_) {
		this.signtype = _signtype_;
	};

	public java.lang.String getSignalgorithm() {
		return(signalgorithm);
	};
	public void setSignalgorithm(java.lang.String _signalgorithm_) {
		this.signalgorithm = _signalgorithm_;
	};

	public java.lang.Integer getSignmode() {
		return(signmode);
	};
	public void setSignmode(java.lang.Integer _signmode_) {
		this.signmode = _signmode_;
	};

	public java.lang.Integer getSignaturestablelocation() {
		return(signaturestablelocation);
	};
	public void setSignaturestablelocation(java.lang.Integer _signaturestablelocation_) {
		this.signaturestablelocation = _signaturestablelocation_;
	};

	public boolean isTimestampincluded() {
		return(timestampincluded);
	};
	public void setTimestampincluded(boolean _timestampincluded_) {
		this.timestampincluded = _timestampincluded_;
	};

	public boolean isPolicyincluded() {
		return(policyincluded);
	};
	public void setPolicyincluded(boolean _policyincluded_) {
		this.policyincluded = _policyincluded_;
	};

	public java.lang.String getEnitipofirma() {
		return(enitipofirma);
	};
	public void setEnitipofirma(java.lang.String _enitipofirma_) {
		this.enitipofirma = _enitipofirma_;
	};

	public java.lang.String getEniperfilfirma() {
		return(eniperfilfirma);
	};
	public void setEniperfilfirma(java.lang.String _eniperfilfirma_) {
		this.eniperfilfirma = _eniperfilfirma_;
	};

	public java.lang.String getEnirolfirma() {
		return(enirolfirma);
	};
	public void setEnirolfirma(java.lang.String _enirolfirma_) {
		this.enirolfirma = _enirolfirma_;
	};

	public java.lang.String getEnisignername() {
		return(enisignername);
	};
	public void setEnisignername(java.lang.String _enisignername_) {
		this.enisignername = _enisignername_;
	};

	public java.lang.String getEnisigneradministrationid() {
		return(enisigneradministrationid);
	};
	public void setEnisigneradministrationid(java.lang.String _enisigneradministrationid_) {
		this.enisigneradministrationid = _enisigneradministrationid_;
	};

	public java.lang.String getEnisignlevel() {
		return(enisignlevel);
	};
	public void setEnisignlevel(java.lang.String _enisignlevel_) {
		this.enisignlevel = _enisignlevel_;
	};

	public boolean isCheckadministrationidofsigner() {
		return(checkadministrationidofsigner);
	};
	public void setCheckadministrationidofsigner(boolean _checkadministrationidofsigner_) {
		this.checkadministrationidofsigner = _checkadministrationidofsigner_;
	};

	public boolean isCheckdocumentmodifications() {
		return(checkdocumentmodifications);
	};
	public void setCheckdocumentmodifications(boolean _checkdocumentmodifications_) {
		this.checkdocumentmodifications = _checkdocumentmodifications_;
	};

	public boolean isCheckvalidationsignature() {
		return(checkvalidationsignature);
	};
	public void setCheckvalidationsignature(boolean _checkvalidationsignature_) {
		this.checkvalidationsignature = _checkvalidationsignature_;
	};



  // ======================================

  public static InfoSignaturaBean toBean(InfoSignatura __bean) {
    if (__bean == null) { return null;}
    InfoSignaturaBean __tmp = new InfoSignaturaBean();
    __tmp.setInfosignaturaid(__bean.getInfosignaturaid());
    __tmp.setSignoperation(__bean.getSignoperation());
    __tmp.setSigntype(__bean.getSigntype());
    __tmp.setSignalgorithm(__bean.getSignalgorithm());
    __tmp.setSignmode(__bean.getSignmode());
    __tmp.setSignaturestablelocation(__bean.getSignaturestablelocation());
    __tmp.setTimestampincluded(__bean.isTimestampincluded());
    __tmp.setPolicyincluded(__bean.isPolicyincluded());
    __tmp.setEnitipofirma(__bean.getEnitipofirma());
    __tmp.setEniperfilfirma(__bean.getEniperfilfirma());
    __tmp.setEnirolfirma(__bean.getEnirolfirma());
    __tmp.setEnisignername(__bean.getEnisignername());
    __tmp.setEnisigneradministrationid(__bean.getEnisigneradministrationid());
    __tmp.setEnisignlevel(__bean.getEnisignlevel());
    __tmp.setCheckadministrationidofsigner(__bean.isCheckadministrationidofsigner());
    __tmp.setCheckdocumentmodifications(__bean.isCheckdocumentmodifications());
    __tmp.setCheckvalidationsignature(__bean.isCheckvalidationsignature());
		return __tmp;
	}



}
