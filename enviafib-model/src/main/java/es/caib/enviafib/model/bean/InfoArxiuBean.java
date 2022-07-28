
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoArxiu;


public class InfoArxiuBean implements InfoArxiu {



private static final long serialVersionUID = -34062603L;

	long infoArxiuID;// PK
	java.lang.String originalFileUrl;
	java.lang.String csv;
	java.lang.String csvGenerationDefinition;
	java.lang.String csvValidationWeb;
	java.lang.String arxiuExpedientID;
	java.lang.String arxiuDocumentID;
	java.lang.String printableUrl;
	java.lang.String eniFileUrl;
	java.lang.String validationFileUrl;


  /** Constructor Buit */
  public InfoArxiuBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoArxiuBean(long infoArxiuID , java.lang.String originalFileUrl , java.lang.String csv , java.lang.String csvGenerationDefinition , java.lang.String csvValidationWeb , java.lang.String arxiuExpedientID , java.lang.String arxiuDocumentID , java.lang.String printableUrl , java.lang.String eniFileUrl , java.lang.String validationFileUrl) {
    this.infoArxiuID=infoArxiuID;
    this.originalFileUrl=originalFileUrl;
    this.csv=csv;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.csvValidationWeb=csvValidationWeb;
    this.arxiuExpedientID=arxiuExpedientID;
    this.arxiuDocumentID=arxiuDocumentID;
    this.printableUrl=printableUrl;
    this.eniFileUrl=eniFileUrl;
    this.validationFileUrl=validationFileUrl;
}
  /** Constructor sense valors autoincrementals */
  public InfoArxiuBean(java.lang.String originalFileUrl , java.lang.String csv , java.lang.String csvGenerationDefinition , java.lang.String csvValidationWeb , java.lang.String arxiuExpedientID , java.lang.String arxiuDocumentID , java.lang.String printableUrl , java.lang.String eniFileUrl , java.lang.String validationFileUrl) {
    this.originalFileUrl=originalFileUrl;
    this.csv=csv;
    this.csvGenerationDefinition=csvGenerationDefinition;
    this.csvValidationWeb=csvValidationWeb;
    this.arxiuExpedientID=arxiuExpedientID;
    this.arxiuDocumentID=arxiuDocumentID;
    this.printableUrl=printableUrl;
    this.eniFileUrl=eniFileUrl;
    this.validationFileUrl=validationFileUrl;
}
  /** Constructor dels valors Not Null */
  public InfoArxiuBean(long infoArxiuID) {
    this.infoArxiuID=infoArxiuID;
}
  public InfoArxiuBean(InfoArxiu __bean) {
    this.setInfoArxiuID(__bean.getInfoArxiuID());
    this.setOriginalFileUrl(__bean.getOriginalFileUrl());
    this.setCsv(__bean.getCsv());
    this.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    this.setCsvValidationWeb(__bean.getCsvValidationWeb());
    this.setArxiuExpedientID(__bean.getArxiuExpedientID());
    this.setArxiuDocumentID(__bean.getArxiuDocumentID());
    this.setPrintableUrl(__bean.getPrintableUrl());
    this.setEniFileUrl(__bean.getEniFileUrl());
    this.setValidationFileUrl(__bean.getValidationFileUrl());
	}

	public long getInfoArxiuID() {
		return(infoArxiuID);
	};
	public void setInfoArxiuID(long _infoArxiuID_) {
		this.infoArxiuID = _infoArxiuID_;
	};

	public java.lang.String getOriginalFileUrl() {
		return(originalFileUrl);
	};
	public void setOriginalFileUrl(java.lang.String _originalFileUrl_) {
		this.originalFileUrl = _originalFileUrl_;
	};

	public java.lang.String getCsv() {
		return(csv);
	};
	public void setCsv(java.lang.String _csv_) {
		this.csv = _csv_;
	};

	public java.lang.String getCsvGenerationDefinition() {
		return(csvGenerationDefinition);
	};
	public void setCsvGenerationDefinition(java.lang.String _csvGenerationDefinition_) {
		this.csvGenerationDefinition = _csvGenerationDefinition_;
	};

	public java.lang.String getCsvValidationWeb() {
		return(csvValidationWeb);
	};
	public void setCsvValidationWeb(java.lang.String _csvValidationWeb_) {
		this.csvValidationWeb = _csvValidationWeb_;
	};

	public java.lang.String getArxiuExpedientID() {
		return(arxiuExpedientID);
	};
	public void setArxiuExpedientID(java.lang.String _arxiuExpedientID_) {
		this.arxiuExpedientID = _arxiuExpedientID_;
	};

	public java.lang.String getArxiuDocumentID() {
		return(arxiuDocumentID);
	};
	public void setArxiuDocumentID(java.lang.String _arxiuDocumentID_) {
		this.arxiuDocumentID = _arxiuDocumentID_;
	};

	public java.lang.String getPrintableUrl() {
		return(printableUrl);
	};
	public void setPrintableUrl(java.lang.String _printableUrl_) {
		this.printableUrl = _printableUrl_;
	};

	public java.lang.String getEniFileUrl() {
		return(eniFileUrl);
	};
	public void setEniFileUrl(java.lang.String _eniFileUrl_) {
		this.eniFileUrl = _eniFileUrl_;
	};

	public java.lang.String getValidationFileUrl() {
		return(validationFileUrl);
	};
	public void setValidationFileUrl(java.lang.String _validationFileUrl_) {
		this.validationFileUrl = _validationFileUrl_;
	};



  // ======================================

  public static InfoArxiuBean toBean(InfoArxiu __bean) {
    if (__bean == null) { return null;}
    InfoArxiuBean __tmp = new InfoArxiuBean();
    __tmp.setInfoArxiuID(__bean.getInfoArxiuID());
    __tmp.setOriginalFileUrl(__bean.getOriginalFileUrl());
    __tmp.setCsv(__bean.getCsv());
    __tmp.setCsvGenerationDefinition(__bean.getCsvGenerationDefinition());
    __tmp.setCsvValidationWeb(__bean.getCsvValidationWeb());
    __tmp.setArxiuExpedientID(__bean.getArxiuExpedientID());
    __tmp.setArxiuDocumentID(__bean.getArxiuDocumentID());
    __tmp.setPrintableUrl(__bean.getPrintableUrl());
    __tmp.setEniFileUrl(__bean.getEniFileUrl());
    __tmp.setValidationFileUrl(__bean.getValidationFileUrl());
		return __tmp;
	}



}
