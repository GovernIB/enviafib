
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoArxiu;


public class InfoArxiuBean implements InfoArxiu {



private static final long serialVersionUID = -34062603L;

	long infoArxiuID;// PK
	java.lang.String originalfileurl;
	java.lang.String csv;
	java.lang.String csvgenerationdefinition;
	java.lang.String csvvalidationweb;
	java.lang.String arxiuexpedientid;
	java.lang.String arxiudocumentid;
	java.lang.String printableurl;
	java.lang.String enifileurl;
	java.lang.String validationfileurl;


  /** Constructor Buit */
  public InfoArxiuBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoArxiuBean(long infoArxiuID , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl) {
    this.infoArxiuID=infoArxiuID;
    this.originalfileurl=originalfileurl;
    this.csv=csv;
    this.csvgenerationdefinition=csvgenerationdefinition;
    this.csvvalidationweb=csvvalidationweb;
    this.arxiuexpedientid=arxiuexpedientid;
    this.arxiudocumentid=arxiudocumentid;
    this.printableurl=printableurl;
    this.enifileurl=enifileurl;
    this.validationfileurl=validationfileurl;
}
  /** Constructor sense valors autoincrementals */
  public InfoArxiuBean(java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl) {
    this.originalfileurl=originalfileurl;
    this.csv=csv;
    this.csvgenerationdefinition=csvgenerationdefinition;
    this.csvvalidationweb=csvvalidationweb;
    this.arxiuexpedientid=arxiuexpedientid;
    this.arxiudocumentid=arxiudocumentid;
    this.printableurl=printableurl;
    this.enifileurl=enifileurl;
    this.validationfileurl=validationfileurl;
}
  /** Constructor dels valors Not Null */
  public InfoArxiuBean(long infoArxiuID) {
    this.infoArxiuID=infoArxiuID;
}
  public InfoArxiuBean(InfoArxiu __bean) {
    this.setInfoArxiuID(__bean.getInfoArxiuID());
    this.setOriginalfileurl(__bean.getOriginalfileurl());
    this.setCsv(__bean.getCsv());
    this.setCsvgenerationdefinition(__bean.getCsvgenerationdefinition());
    this.setCsvvalidationweb(__bean.getCsvvalidationweb());
    this.setArxiuexpedientid(__bean.getArxiuexpedientid());
    this.setArxiudocumentid(__bean.getArxiudocumentid());
    this.setPrintableurl(__bean.getPrintableurl());
    this.setEnifileurl(__bean.getEnifileurl());
    this.setValidationfileurl(__bean.getValidationfileurl());
	}

	public long getInfoArxiuID() {
		return(infoArxiuID);
	};
	public void setInfoArxiuID(long _infoArxiuID_) {
		this.infoArxiuID = _infoArxiuID_;
	};

	public java.lang.String getOriginalfileurl() {
		return(originalfileurl);
	};
	public void setOriginalfileurl(java.lang.String _originalfileurl_) {
		this.originalfileurl = _originalfileurl_;
	};

	public java.lang.String getCsv() {
		return(csv);
	};
	public void setCsv(java.lang.String _csv_) {
		this.csv = _csv_;
	};

	public java.lang.String getCsvgenerationdefinition() {
		return(csvgenerationdefinition);
	};
	public void setCsvgenerationdefinition(java.lang.String _csvgenerationdefinition_) {
		this.csvgenerationdefinition = _csvgenerationdefinition_;
	};

	public java.lang.String getCsvvalidationweb() {
		return(csvvalidationweb);
	};
	public void setCsvvalidationweb(java.lang.String _csvvalidationweb_) {
		this.csvvalidationweb = _csvvalidationweb_;
	};

	public java.lang.String getArxiuexpedientid() {
		return(arxiuexpedientid);
	};
	public void setArxiuexpedientid(java.lang.String _arxiuexpedientid_) {
		this.arxiuexpedientid = _arxiuexpedientid_;
	};

	public java.lang.String getArxiudocumentid() {
		return(arxiudocumentid);
	};
	public void setArxiudocumentid(java.lang.String _arxiudocumentid_) {
		this.arxiudocumentid = _arxiudocumentid_;
	};

	public java.lang.String getPrintableurl() {
		return(printableurl);
	};
	public void setPrintableurl(java.lang.String _printableurl_) {
		this.printableurl = _printableurl_;
	};

	public java.lang.String getEnifileurl() {
		return(enifileurl);
	};
	public void setEnifileurl(java.lang.String _enifileurl_) {
		this.enifileurl = _enifileurl_;
	};

	public java.lang.String getValidationfileurl() {
		return(validationfileurl);
	};
	public void setValidationfileurl(java.lang.String _validationfileurl_) {
		this.validationfileurl = _validationfileurl_;
	};



  // ======================================

  public static InfoArxiuBean toBean(InfoArxiu __bean) {
    if (__bean == null) { return null;}
    InfoArxiuBean __tmp = new InfoArxiuBean();
    __tmp.setInfoArxiuID(__bean.getInfoArxiuID());
    __tmp.setOriginalfileurl(__bean.getOriginalfileurl());
    __tmp.setCsv(__bean.getCsv());
    __tmp.setCsvgenerationdefinition(__bean.getCsvgenerationdefinition());
    __tmp.setCsvvalidationweb(__bean.getCsvvalidationweb());
    __tmp.setArxiuexpedientid(__bean.getArxiuexpedientid());
    __tmp.setArxiudocumentid(__bean.getArxiudocumentid());
    __tmp.setPrintableurl(__bean.getPrintableurl());
    __tmp.setEnifileurl(__bean.getEnifileurl());
    __tmp.setValidationfileurl(__bean.getValidationfileurl());
		return __tmp;
	}



}
