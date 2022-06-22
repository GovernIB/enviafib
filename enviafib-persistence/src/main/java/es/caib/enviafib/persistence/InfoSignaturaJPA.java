
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
    long infoSignaturaID;

    @Column(name="signoperation",nullable = false,length = 10)
    int signOperation;

    @Column(name="signtype",nullable = false,length = 255)
    java.lang.String signType;

    @Column(name="signalgorithm",length = 255)
    java.lang.String signAlgorithm;

    @Column(name="signmode",length = 10)
    java.lang.Integer signMode;

    @Column(name="signaturestablelocation",length = 10)
    java.lang.Integer signaturesTableLocation;

    @Column(name="timestampincluded",length = 1)
    java.lang.Boolean timestampIncluded;

    @Column(name="policyincluded",length = 1)
    java.lang.Boolean policyIncluded;

    @Column(name="enitipofirma",length = 255)
    java.lang.String eniTipoFirma;

    @Column(name="eniperfilfirma",length = 255)
    java.lang.String eniPerfilFirma;

    @Column(name="enirolfirma",length = 255)
    java.lang.String eniRolFirma;

    @Column(name="enisignername",length = 255)
    java.lang.String eniSignerName;

    @Column(name="enisigneradministrationid",length = 255)
    java.lang.String eniSignerAdministrationId;

    @Column(name="enisignlevel",length = 255)
    java.lang.String eniSignLevel;

    @Column(name="checkadministrationidofsigner",length = 1)
    java.lang.Boolean checkAdministrationIdOfSigner;

    @Column(name="checkdocumentmodifications",length = 1)
    java.lang.Boolean checkDocumentModifications;

    @Column(name="checkvalidationsignature",length = 1)
    java.lang.Boolean checkValidationSignature;



  /** Constructor Buit */
  public InfoSignaturaJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoSignaturaJPA(long infoSignaturaID , int signOperation , java.lang.String signType , java.lang.String signAlgorithm , java.lang.Integer signMode , java.lang.Integer signaturesTableLocation , java.lang.Boolean timestampIncluded , java.lang.Boolean policyIncluded , java.lang.String eniTipoFirma , java.lang.String eniPerfilFirma , java.lang.String eniRolFirma , java.lang.String eniSignerName , java.lang.String eniSignerAdministrationId , java.lang.String eniSignLevel , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature) {
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
  public InfoSignaturaJPA(int signOperation , java.lang.String signType , java.lang.String signAlgorithm , java.lang.Integer signMode , java.lang.Integer signaturesTableLocation , java.lang.Boolean timestampIncluded , java.lang.Boolean policyIncluded , java.lang.String eniTipoFirma , java.lang.String eniPerfilFirma , java.lang.String eniRolFirma , java.lang.String eniSignerName , java.lang.String eniSignerAdministrationId , java.lang.String eniSignLevel , java.lang.Boolean checkAdministrationIdOfSigner , java.lang.Boolean checkDocumentModifications , java.lang.Boolean checkValidationSignature) {
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
  public InfoSignaturaJPA(long infoSignaturaID , int signOperation , java.lang.String signType) {
    this.infoSignaturaID=infoSignaturaID;
    this.signOperation=signOperation;
    this.signType=signType;
}
  public InfoSignaturaJPA(InfoSignatura __bean) {
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



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof InfoSignatura) {
      InfoSignatura __instance = (InfoSignatura)__obj;
      __result = true;
      __result = __result && (this.getInfoSignaturaID() == __instance.getInfoSignaturaID()) ;
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
