
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


@Entity(name = "FitxerJPA")
@Table(name = "efi_fitxer" , indexes = { 
        @Index(name="efi_fitxer_pk_i", columnList = "fitxerid")})
@SequenceGenerator(name="FITXER_SEQ", sequenceName="efi_fitxer_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FitxerJPA implements Fitxer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FITXER_SEQ")
    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="mime",nullable = false,length = 255)
    java.lang.String mime;

    @Column(name="tamany",nullable = false,length = 19)
    long tamany;

    @Column(name="descripcio",length = 1000)
    java.lang.String descripcio;



  /** Constructor Buit */
  public FitxerJPA() {
  }

  /** Constructor amb tots els camps  */
  public FitxerJPA(long fitxerID , java.lang.String nom , java.lang.String mime , long tamany , java.lang.String descripcio) {
    this.fitxerID=fitxerID;
    this.nom=nom;
    this.mime=mime;
    this.tamany=tamany;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public FitxerJPA(java.lang.String nom , java.lang.String mime , long tamany , java.lang.String descripcio) {
    this.nom=nom;
    this.mime=mime;
    this.tamany=tamany;
    this.descripcio=descripcio;
}
  public FitxerJPA(Fitxer __bean) {
    this.setFitxerID(__bean.getFitxerID());
    this.setNom(__bean.getNom());
    this.setMime(__bean.getMime());
    this.setTamany(__bean.getTamany());
    this.setDescripcio(__bean.getDescripcio());
    // DataHandler
    this.setData(__bean.getData());
    // EncryptedFileID
    this.setEncryptedFileID(__bean.getEncryptedFileID());
	}

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getMime() {
		return(mime);
	};
	public void setMime(java.lang.String _mime_) {
		this.mime = _mime_;
	};

	public long getTamany() {
		return(tamany);
	};
	public void setTamany(long _tamany_) {
		this.tamany = _tamany_;
	};

	public java.lang.String getDescripcio() {
		return(descripcio);
	};
	public void setDescripcio(java.lang.String _descripcio_) {
		this.descripcio = _descripcio_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Fitxer) {
      Fitxer __instance = (Fitxer)__obj;
      __result = true;
      __result = __result && (this.getFitxerID() == __instance.getFitxerID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:fitxer_firmatid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerFirmatID")
    private Set<PeticioJPA> peticio_fitxer_firmatids = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticio_fitxer_firmatids() {
    return this.peticio_fitxer_firmatids;
  }

    public void setPeticio_fitxer_firmatids(Set<PeticioJPA> peticio_fitxer_firmatids) {
      this.peticio_fitxer_firmatids = peticio_fitxer_firmatids;
    }


// EXP  Field:fitxerid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "fitxerID")
    private Set<PeticioJPA> peticio_fitxerids = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticio_fitxerids() {
    return this.peticio_fitxerids;
  }

    public void setPeticio_fitxerids(Set<PeticioJPA> peticio_fitxerids) {
      this.peticio_fitxerids = peticio_fitxerids;
    }



  @javax.persistence.Transient
  javax.activation.DataHandler data;

  public javax.activation.DataHandler getData() {
    return data;
  }

  public void setData(javax.activation.DataHandler data) {
    this.data = data;
  }

  @javax.persistence.Transient
  String encryptedFileID;

  public String getEncryptedFileID() {
    return encryptedFileID;
  }

  public void setEncryptedFileID(String encryptedFileID) {
    this.encryptedFileID = encryptedFileID;
  }


  final static ThreadLocal<String> threadLocal = new ThreadLocal<String>();

  public static void enableEncryptedFileIDGeneration() {
    threadLocal.set("");
  }

  public static void disableEncryptedFileIDGeneration() {
    threadLocal.remove();
  }

  @javax.persistence.PostPersist
  @javax.persistence.PostLoad
  void postLoad() {
    if (threadLocal.get() != null) {
      this.encryptedFileID = es.caib.enviafib.hibernate.HibernateFileUtil.encryptFileID(fitxerID);
    }
  }


 // ---------------  STATIC METHODS ------------------
  public static FitxerJPA toJPA(Fitxer __bean) {
    if (__bean == null) { return null;}
    FitxerJPA __tmp = new FitxerJPA();
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setNom(__bean.getNom());
    __tmp.setMime(__bean.getMime());
    __tmp.setTamany(__bean.getTamany());
    __tmp.setDescripcio(__bean.getDescripcio());
    // DataHandler
    __tmp.setData(__bean.getData());
    // EncryptedFileID
    __tmp.setEncryptedFileID(__bean.getEncryptedFileID());
		return __tmp;
	}


  public static FitxerJPA copyJPA(FitxerJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FitxerJPA> copyJPA(java.util.Set<FitxerJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FitxerJPA> __tmpSet = (java.util.Set<FitxerJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FitxerJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FitxerJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FitxerJPA copyJPA(FitxerJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FitxerJPA __tmp = (FitxerJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticio_fitxerids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticio_fitxerids())) ) {
      __tmp.setPeticio_fitxerids(PeticioJPA.copyJPA(__jpa.getPeticio_fitxerids(), __alreadyCopied,"FitxerJPA"));
    }
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticio_fitxer_firmatids) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticio_fitxer_firmatids())) ) {
      __tmp.setPeticio_fitxer_firmatids(PeticioJPA.copyJPA(__jpa.getPeticio_fitxer_firmatids(), __alreadyCopied,"FitxerJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
