
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.Set;
import javax.persistence.Id;


@Entity(name = "InfoArxiuJPA")
@Table(name = "efi_infoarxiu" , indexes = { 
        @Index(name="efi_infoarxiu_pk_i", columnList = "infoarxiuid")})
@SequenceGenerator(name="INFOARXIU_SEQ", sequenceName="efi_infoarxiu_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoArxiuJPA implements InfoArxiu {



private static final long serialVersionUID = -1940754442L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOARXIU_SEQ")
    @Column(name="infoarxiuid",nullable = false,length = 19)
    long infoArxiuID;

    @Column(name="originalfileurl",length = 255)
    java.lang.String originalfileurl;

    @Column(name="csv",length = 255)
    java.lang.String csv;

    @Column(name="csvgenerationdefinition",length = 255)
    java.lang.String csvgenerationdefinition;

    @Column(name="csvvalidationweb",length = 255)
    java.lang.String csvvalidationweb;

    @Column(name="arxiuexpedientid",length = 255)
    java.lang.String arxiuexpedientid;

    @Column(name="arxiudocumentid",length = 255)
    java.lang.String arxiudocumentid;

    @Column(name="printableurl",length = 255)
    java.lang.String printableurl;

    @Column(name="enifileurl",length = 255)
    java.lang.String enifileurl;

    @Column(name="validationfileurl",length = 255)
    java.lang.String validationfileurl;



  /** Constructor Buit */
  public InfoArxiuJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoArxiuJPA(long infoArxiuID , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl) {
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
  public InfoArxiuJPA(java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl) {
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
  public InfoArxiuJPA(long infoArxiuID) {
    this.infoArxiuID=infoArxiuID;
}
  public InfoArxiuJPA(InfoArxiu __bean) {
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
