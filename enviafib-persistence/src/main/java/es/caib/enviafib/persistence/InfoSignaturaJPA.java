
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


@Entity(name = "InfoSignaturaJPA")
@Table(name = "efi_infosignatura" , indexes = { 
        @Index(name="efi_infosignatura_pk_i", columnList = "infosignaturaid")})
@SequenceGenerator(name="INFOSIGNATURA_SEQ", sequenceName="efi_infosignatura_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoSignaturaJPA implements InfoSignatura {



private static final long serialVersionUID = 170377845L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOSIGNATURA_SEQ")
    @Column(name="infosignaturaid",nullable = false,length = 19)
    long infosignaturaid;

    @Column(name="signoperation",nullable = false,length = 10)
    int signoperation;

    @Column(name="signtype",nullable = false,length = 255)
    java.lang.String signtype;

    @Column(name="signalgorithm",length = 255)
    java.lang.String signalgorithm;

    @Column(name="signmode",length = 10)
    java.lang.Integer signmode;

    @Column(name="signaturestablelocation",length = 10)
    java.lang.Integer signaturestablelocation;

    @Column(name="timestampincluded",length = 1)
    java.lang.Boolean timestampincluded;

    @Column(name="policyincluded",length = 1)
    java.lang.Boolean policyincluded;

    @Column(name="enitipofirma",length = 255)
    java.lang.String enitipofirma;

    @Column(name="eniperfilfirma",length = 255)
    java.lang.String eniperfilfirma;

    @Column(name="enirolfirma",length = 255)
    java.lang.String enirolfirma;

    @Column(name="enisignername",length = 255)
    java.lang.String enisignername;

    @Column(name="enisigneradministrationid",length = 255)
    java.lang.String enisigneradministrationid;

    @Column(name="enisignlevel",length = 255)
    java.lang.String enisignlevel;

    @Column(name="checkadministrationidofsigner",length = 1)
    java.lang.Boolean checkadministrationidofsigner;

    @Column(name="checkdocumentmodifications",length = 1)
    java.lang.Boolean checkdocumentmodifications;

    @Column(name="checkvalidationsignature",length = 1)
    java.lang.Boolean checkvalidationsignature;



  /** Constructor Buit */
  public InfoSignaturaJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoSignaturaJPA(long infosignaturaid , int signoperation , java.lang.String signtype , java.lang.String signalgorithm , java.lang.Integer signmode , java.lang.Integer signaturestablelocation , java.lang.Boolean timestampincluded , java.lang.Boolean policyincluded , java.lang.String enitipofirma , java.lang.String eniperfilfirma , java.lang.String enirolfirma , java.lang.String enisignername , java.lang.String enisigneradministrationid , java.lang.String enisignlevel , java.lang.Boolean checkadministrationidofsigner , java.lang.Boolean checkdocumentmodifications , java.lang.Boolean checkvalidationsignature) {
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
  public InfoSignaturaJPA(int signoperation , java.lang.String signtype , java.lang.String signalgorithm , java.lang.Integer signmode , java.lang.Integer signaturestablelocation , java.lang.Boolean timestampincluded , java.lang.Boolean policyincluded , java.lang.String enitipofirma , java.lang.String eniperfilfirma , java.lang.String enirolfirma , java.lang.String enisignername , java.lang.String enisigneradministrationid , java.lang.String enisignlevel , java.lang.Boolean checkadministrationidofsigner , java.lang.Boolean checkdocumentmodifications , java.lang.Boolean checkvalidationsignature) {
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
  public InfoSignaturaJPA(long infosignaturaid , int signoperation , java.lang.String signtype) {
    this.infosignaturaid=infosignaturaid;
    this.signoperation=signoperation;
    this.signtype=signtype;
}
  public InfoSignaturaJPA(InfoSignatura __bean) {
    this.setInfosignaturaid(__bean.getInfosignaturaid());
    this.setSignoperation(__bean.getSignoperation());
    this.setSigntype(__bean.getSigntype());
    this.setSignalgorithm(__bean.getSignalgorithm());
    this.setSignmode(__bean.getSignmode());
    this.setSignaturestablelocation(__bean.getSignaturestablelocation());
    this.setTimestampincluded(__bean.getTimestampincluded());
    this.setPolicyincluded(__bean.getPolicyincluded());
    this.setEnitipofirma(__bean.getEnitipofirma());
    this.setEniperfilfirma(__bean.getEniperfilfirma());
    this.setEnirolfirma(__bean.getEnirolfirma());
    this.setEnisignername(__bean.getEnisignername());
    this.setEnisigneradministrationid(__bean.getEnisigneradministrationid());
    this.setEnisignlevel(__bean.getEnisignlevel());
    this.setCheckadministrationidofsigner(__bean.getCheckadministrationidofsigner());
    this.setCheckdocumentmodifications(__bean.getCheckdocumentmodifications());
    this.setCheckvalidationsignature(__bean.getCheckvalidationsignature());
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

	public java.lang.Boolean getTimestampincluded() {
		return(timestampincluded);
	};
	public void setTimestampincluded(java.lang.Boolean _timestampincluded_) {
		this.timestampincluded = _timestampincluded_;
	};

	public java.lang.Boolean getPolicyincluded() {
		return(policyincluded);
	};
	public void setPolicyincluded(java.lang.Boolean _policyincluded_) {
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

	public java.lang.Boolean getCheckadministrationidofsigner() {
		return(checkadministrationidofsigner);
	};
	public void setCheckadministrationidofsigner(java.lang.Boolean _checkadministrationidofsigner_) {
		this.checkadministrationidofsigner = _checkadministrationidofsigner_;
	};

	public java.lang.Boolean getCheckdocumentmodifications() {
		return(checkdocumentmodifications);
	};
	public void setCheckdocumentmodifications(java.lang.Boolean _checkdocumentmodifications_) {
		this.checkdocumentmodifications = _checkdocumentmodifications_;
	};

	public java.lang.Boolean getCheckvalidationsignature() {
		return(checkvalidationsignature);
	};
	public void setCheckvalidationsignature(java.lang.Boolean _checkvalidationsignature_) {
		this.checkvalidationsignature = _checkvalidationsignature_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof InfoSignatura) {
      InfoSignatura __instance = (InfoSignatura)__obj;
      __result = true;
      __result = __result && (this.getInfosignaturaid() == __instance.getInfosignaturaid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:infosignaturaid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "infoSignatura")
    private Set<PeticioJPA> peticios = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticios() {
    return this.peticios;
  }

    public void setPeticios(Set<PeticioJPA> peticios) {
      this.peticios = peticios;
    }



 // ---------------  STATIC METHODS ------------------
  public static InfoSignaturaJPA toJPA(InfoSignatura __bean) {
    if (__bean == null) { return null;}
    InfoSignaturaJPA __tmp = new InfoSignaturaJPA();
    __tmp.setInfosignaturaid(__bean.getInfosignaturaid());
    __tmp.setSignoperation(__bean.getSignoperation());
    __tmp.setSigntype(__bean.getSigntype());
    __tmp.setSignalgorithm(__bean.getSignalgorithm());
    __tmp.setSignmode(__bean.getSignmode());
    __tmp.setSignaturestablelocation(__bean.getSignaturestablelocation());
    __tmp.setTimestampincluded(__bean.getTimestampincluded());
    __tmp.setPolicyincluded(__bean.getPolicyincluded());
    __tmp.setEnitipofirma(__bean.getEnitipofirma());
    __tmp.setEniperfilfirma(__bean.getEniperfilfirma());
    __tmp.setEnirolfirma(__bean.getEnirolfirma());
    __tmp.setEnisignername(__bean.getEnisignername());
    __tmp.setEnisigneradministrationid(__bean.getEnisigneradministrationid());
    __tmp.setEnisignlevel(__bean.getEnisignlevel());
    __tmp.setCheckadministrationidofsigner(__bean.getCheckadministrationidofsigner());
    __tmp.setCheckdocumentmodifications(__bean.getCheckdocumentmodifications());
    __tmp.setCheckvalidationsignature(__bean.getCheckvalidationsignature());
		return __tmp;
	}


  public static InfoSignaturaJPA copyJPA(InfoSignaturaJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<InfoSignaturaJPA> copyJPA(java.util.Set<InfoSignaturaJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<InfoSignaturaJPA> __tmpSet = (java.util.Set<InfoSignaturaJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<InfoSignaturaJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (InfoSignaturaJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static InfoSignaturaJPA copyJPA(InfoSignaturaJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    InfoSignaturaJPA __tmp = (InfoSignaturaJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticios) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticios())) ) {
      __tmp.setPeticios(PeticioJPA.copyJPA(__jpa.getPeticios(), __alreadyCopied,"InfoSignaturaJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
