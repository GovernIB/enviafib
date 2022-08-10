
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "InfoArxiuJPA")
@Table(name = "efi_infoarxiu" )
@SequenceGenerator(name="INFOARXIU_SEQ", sequenceName="efi_infoarxiu_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoArxiuJPA implements InfoArxiu {



private static final long serialVersionUID = -1940754442L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOARXIU_SEQ")
    @Column(name="infoarxiuid",nullable = false,length = 19)
    long infoArxiuID;

    @Column(name="originalfileurl",length = 255)
    java.lang.String originalFileUrl;

    @Column(name="csv",length = 255)
    java.lang.String csv;

    @Column(name="csvgenerationdefinition",length = 255)
    java.lang.String csvGenerationDefinition;

    @Column(name="csvvalidationweb",length = 255)
    java.lang.String csvValidationWeb;

    @Column(name="arxiuexpedientid",length = 255)
    java.lang.String arxiuExpedientID;

    @Column(name="arxiudocumentid",length = 255)
    java.lang.String arxiuDocumentID;

    @Column(name="printableurl",length = 255)
    java.lang.String printableUrl;

    @Column(name="enifileurl",length = 255)
    java.lang.String eniFileUrl;

    @Column(name="validationfileurl",length = 255)
    java.lang.String validationFileUrl;



  /** Constructor Buit */
  public InfoArxiuJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoArxiuJPA(long infoArxiuID , java.lang.String originalFileUrl , java.lang.String csv , java.lang.String csvGenerationDefinition , java.lang.String csvValidationWeb , java.lang.String arxiuExpedientID , java.lang.String arxiuDocumentID , java.lang.String printableUrl , java.lang.String eniFileUrl , java.lang.String validationFileUrl) {
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
  public InfoArxiuJPA(java.lang.String originalFileUrl , java.lang.String csv , java.lang.String csvGenerationDefinition , java.lang.String csvValidationWeb , java.lang.String arxiuExpedientID , java.lang.String arxiuDocumentID , java.lang.String printableUrl , java.lang.String eniFileUrl , java.lang.String validationFileUrl) {
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
  public InfoArxiuJPA(long infoArxiuID) {
    this.infoArxiuID=infoArxiuID;
}
  public InfoArxiuJPA(InfoArxiu __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof InfoArxiu) {
      InfoArxiu __instance = (InfoArxiu)__obj;
      __result = true;
      __result = __result && (this.getInfoArxiuID() == __instance.getInfoArxiuID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:infoarxiuid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "infoArxiu")
    private Set<PeticioJPA> peticios = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticios() {
    return this.peticios;
  }

    public void setPeticios(Set<PeticioJPA> peticios) {
      this.peticios = peticios;
    }



 // ---------------  STATIC METHODS ------------------
  public static InfoArxiuJPA toJPA(InfoArxiu __bean) {
    if (__bean == null) { return null;}
    InfoArxiuJPA __tmp = new InfoArxiuJPA();
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


  public static InfoArxiuJPA copyJPA(InfoArxiuJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<InfoArxiuJPA> copyJPA(java.util.Set<InfoArxiuJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<InfoArxiuJPA> __tmpSet = (java.util.Set<InfoArxiuJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<InfoArxiuJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (InfoArxiuJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static InfoArxiuJPA copyJPA(InfoArxiuJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    InfoArxiuJPA __tmp = (InfoArxiuJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticios) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticios())) ) {
      __tmp.setPeticios(PeticioJPA.copyJPA(__jpa.getPeticios(), __alreadyCopied,"InfoArxiuJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
