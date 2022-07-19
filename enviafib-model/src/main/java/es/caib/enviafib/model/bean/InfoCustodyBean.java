
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.InfoCustody;


public class InfoCustodyBean implements InfoCustody {



private static final long serialVersionUID = -158606413L;

	long infocustodyid;// PK
	java.lang.String custodyid;
	java.lang.String originalfileurl;
	java.lang.String csv;
	java.lang.String csvgenerationdefinition;
	java.lang.String csvvalidationweb;
	java.lang.String arxiuexpedientid;
	java.lang.String arxiudocumentid;
	java.lang.String printableurl;
	java.lang.String enifileurl;
	java.lang.String validationfileurl;
	java.lang.Long peticioid;


  /** Constructor Buit */
  public InfoCustodyBean() {
  }

  /** Constructor amb tots els camps  */
  public InfoCustodyBean(long infocustodyid , java.lang.String custodyid , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl , java.lang.Long peticioid) {
    this.infocustodyid=infocustodyid;
    this.custodyid=custodyid;
    this.originalfileurl=originalfileurl;
    this.csv=csv;
    this.csvgenerationdefinition=csvgenerationdefinition;
    this.csvvalidationweb=csvvalidationweb;
    this.arxiuexpedientid=arxiuexpedientid;
    this.arxiudocumentid=arxiudocumentid;
    this.printableurl=printableurl;
    this.enifileurl=enifileurl;
    this.validationfileurl=validationfileurl;
    this.peticioid=peticioid;
}
  /** Constructor sense valors autoincrementals */
  public InfoCustodyBean(java.lang.String custodyid , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl , java.lang.Long peticioid) {
    this.custodyid=custodyid;
    this.originalfileurl=originalfileurl;
    this.csv=csv;
    this.csvgenerationdefinition=csvgenerationdefinition;
    this.csvvalidationweb=csvvalidationweb;
    this.arxiuexpedientid=arxiuexpedientid;
    this.arxiudocumentid=arxiudocumentid;
    this.printableurl=printableurl;
    this.enifileurl=enifileurl;
    this.validationfileurl=validationfileurl;
    this.peticioid=peticioid;
}
  /** Constructor dels valors Not Null */
  public InfoCustodyBean(long infocustodyid , java.lang.String custodyid , java.lang.Long peticioid) {
    this.infocustodyid=infocustodyid;
    this.custodyid=custodyid;
    this.peticioid=peticioid;
}
  public InfoCustodyBean(InfoCustody __bean) {
    this.setInfocustodyid(__bean.getInfocustodyid());
    this.setCustodyid(__bean.getCustodyid());
    this.setOriginalfileurl(__bean.getOriginalfileurl());
    this.setCsv(__bean.getCsv());
    this.setCsvgenerationdefinition(__bean.getCsvgenerationdefinition());
    this.setCsvvalidationweb(__bean.getCsvvalidationweb());
    this.setArxiuexpedientid(__bean.getArxiuexpedientid());
    this.setArxiudocumentid(__bean.getArxiudocumentid());
    this.setPrintableurl(__bean.getPrintableurl());
    this.setEnifileurl(__bean.getEnifileurl());
    this.setValidationfileurl(__bean.getValidationfileurl());
    this.setPeticioid(__bean.getPeticioid());
	}

	public long getInfocustodyid() {
		return(infocustodyid);
	};
	public void setInfocustodyid(long _infocustodyid_) {
		this.infocustodyid = _infocustodyid_;
	};

	public java.lang.String getCustodyid() {
		return(custodyid);
	};
	public void setCustodyid(java.lang.String _custodyid_) {
		this.custodyid = _custodyid_;
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

	public java.lang.Long getPeticioid() {
		return(peticioid);
	};
	public void setPeticioid(java.lang.Long _peticioid_) {
		this.peticioid = _peticioid_;
	};



  // ======================================

  public static InfoCustodyBean toBean(InfoCustody __bean) {
    if (__bean == null) { return null;}
    InfoCustodyBean __tmp = new InfoCustodyBean();
    __tmp.setInfocustodyid(__bean.getInfocustodyid());
    __tmp.setCustodyid(__bean.getCustodyid());
    __tmp.setOriginalfileurl(__bean.getOriginalfileurl());
    __tmp.setCsv(__bean.getCsv());
    __tmp.setCsvgenerationdefinition(__bean.getCsvgenerationdefinition());
    __tmp.setCsvvalidationweb(__bean.getCsvvalidationweb());
    __tmp.setArxiuexpedientid(__bean.getArxiuexpedientid());
    __tmp.setArxiudocumentid(__bean.getArxiudocumentid());
    __tmp.setPrintableurl(__bean.getPrintableurl());
    __tmp.setEnifileurl(__bean.getEnifileurl());
    __tmp.setValidationfileurl(__bean.getValidationfileurl());
    __tmp.setPeticioid(__bean.getPeticioid());
		return __tmp;
	}



}
