
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "InfoCustodyJPA")
@Table(name = "efi_infocustody" )
@SequenceGenerator(name="INFOCUSTODY_SEQ", sequenceName="efi_infocustody_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoCustodyJPA implements InfoCustody {



private static final long serialVersionUID = -1667677320L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOCUSTODY_SEQ")
    @Column(name="infocustodyid",nullable = false,length = 19)
    long infocustodyid;

    @Column(name="custodyid",nullable = false,length = 255)
    java.lang.String custodyid;

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

    @Column(name="peticioid",nullable = false,length = 19)
    java.lang.Long peticioid;



  /** Constructor Buit */
  public InfoCustodyJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoCustodyJPA(long infocustodyid , java.lang.String custodyid , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl , java.lang.Long peticioid) {
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
  public InfoCustodyJPA(java.lang.String custodyid , java.lang.String originalfileurl , java.lang.String csv , java.lang.String csvgenerationdefinition , java.lang.String csvvalidationweb , java.lang.String arxiuexpedientid , java.lang.String arxiudocumentid , java.lang.String printableurl , java.lang.String enifileurl , java.lang.String validationfileurl , java.lang.Long peticioid) {
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
  public InfoCustodyJPA(long infocustodyid , java.lang.String custodyid , java.lang.Long peticioid) {
    this.infocustodyid=infocustodyid;
    this.custodyid=custodyid;
    this.peticioid=peticioid;
}
  public InfoCustodyJPA(InfoCustody __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof InfoCustody) {
      InfoCustody __instance = (InfoCustody)__obj;
      __result = true;
      __result = __result && (this.getInfocustodyid() == __instance.getInfocustodyid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:peticioid | Table: efi_peticio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peticioid", referencedColumnName ="peticioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_infocus_peticio_petid_fk"))
    private PeticioJPA peticio;

    public PeticioJPA getPeticio() {
    return this.peticio;
  }

    public  void setPeticio(PeticioJPA peticio) {
    this.peticio = peticio;
  }


 // ---------------  STATIC METHODS ------------------
  public static InfoCustodyJPA toJPA(InfoCustody __bean) {
    if (__bean == null) { return null;}
    InfoCustodyJPA __tmp = new InfoCustodyJPA();
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


  public static InfoCustodyJPA copyJPA(InfoCustodyJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<InfoCustodyJPA> copyJPA(java.util.Set<InfoCustodyJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<InfoCustodyJPA> __tmpSet = (java.util.Set<InfoCustodyJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<InfoCustodyJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (InfoCustodyJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static InfoCustodyJPA copyJPA(InfoCustodyJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    InfoCustodyJPA __tmp = (InfoCustodyJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"PeticioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticio) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticio()) ) ) {
      __tmp.setPeticio(PeticioJPA.copyJPA(__jpa.getPeticio(), __alreadyCopied,"InfoCustodyJPA"));
    }

    return __tmp;
  }




}
