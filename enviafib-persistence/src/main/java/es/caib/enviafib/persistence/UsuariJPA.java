
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "UsuariJPA")
@Table(name = "efi_usuari" , indexes = { 
        @Index(name="efi_usuari_pk_i", columnList = "usuariid"),
        @Index(name="efi_usuari_idiomaid_fk_i", columnList = "idiomaid")})
@SequenceGenerator(name="USUARI_SEQ", sequenceName="efi_usuari_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariJPA implements Usuari {



private static final long serialVersionUID = -1105822054L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARI_SEQ")
    @Column(name="usuariid",nullable = false,length = 19)
    long usuariID;

    @Column(name="username",nullable = false,length = 100)
    java.lang.String username;

    @Column(name="nom",nullable = false,length = 256)
    java.lang.String nom;

    @Column(name="llinatge1",nullable = false,length = 256)
    java.lang.String llinatge1;

    @Column(name="llinatge2",length = 256)
    java.lang.String llinatge2;

    @Column(name="nif",nullable = false,unique = true,length = 50)
    java.lang.String nif;

    @Column(name="email",nullable = false,length = 256)
    java.lang.String email;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;



  /** Constructor Buit */
  public UsuariJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariJPA(long usuariID , java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email , java.lang.String idiomaID) {
    this.usuariID=usuariID;
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
    this.idiomaID=idiomaID;
}
  /** Constructor sense valors autoincrementals */
  public UsuariJPA(java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email , java.lang.String idiomaID) {
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
    this.idiomaID=idiomaID;
}
  public UsuariJPA(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setUsername(__bean.getUsername());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNif(__bean.getNif());
    this.setEmail(__bean.getEmail());
    this.setIdiomaID(__bean.getIdiomaID());
	}

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};

	public java.lang.String getUsername() {
		return(username);
	};
	public void setUsername(java.lang.String _username_) {
		this.username = _username_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getLlinatge1() {
		return(llinatge1);
	};
	public void setLlinatge1(java.lang.String _llinatge1_) {
		this.llinatge1 = _llinatge1_;
	};

	public java.lang.String getLlinatge2() {
		return(llinatge2);
	};
	public void setLlinatge2(java.lang.String _llinatge2_) {
		this.llinatge2 = _llinatge2_;
	};

	public java.lang.String getNif() {
		return(nif);
	};
	public void setNif(java.lang.String _nif_) {
		this.nif = _nif_;
	};

	public java.lang.String getEmail() {
		return(email);
	};
	public void setEmail(java.lang.String _email_) {
		this.email = _email_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Usuari) {
      Usuari __instance = (Usuari)__obj;
      __result = true;
      __result = __result && (this.getUsuariID() == __instance.getUsuariID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:usuariid | Table: efi_grupusuari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<GrupUsuariJPA> grupUsuaris = new HashSet<GrupUsuariJPA>(0);
    public  Set<GrupUsuariJPA> getGrupUsuaris() {
    return this.grupUsuaris;
  }

    public void setGrupUsuaris(Set<GrupUsuariJPA> grupUsuaris) {
      this.grupUsuaris = grupUsuaris;
    }


// EXP  Field:solicitantid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<PeticioJPA> peticios = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticios() {
    return this.peticios;
  }

    public void setPeticios(Set<PeticioJPA> peticios) {
      this.peticios = peticios;
    }


// IMP Field:idiomaid | Table: efi_idioma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_usuari_idioma_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariJPA toJPA(Usuari __bean) {
    if (__bean == null) { return null;}
    UsuariJPA __tmp = new UsuariJPA();
    __tmp.setUsuariID(__bean.getUsuariID());
    __tmp.setUsername(__bean.getUsername());
    __tmp.setNom(__bean.getNom());
    __tmp.setLlinatge1(__bean.getLlinatge1());
    __tmp.setLlinatge2(__bean.getLlinatge2());
    __tmp.setNif(__bean.getNif());
    __tmp.setEmail(__bean.getEmail());
    __tmp.setIdiomaID(__bean.getIdiomaID());
		return __tmp;
	}


  public static UsuariJPA copyJPA(UsuariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariJPA> copyJPA(java.util.Set<UsuariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariJPA> __tmpSet = (java.util.Set<UsuariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariJPA copyJPA(UsuariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariJPA __tmp = (UsuariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"GrupUsuariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupUsuaris) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupUsuaris())) ) {
      __tmp.setGrupUsuaris(GrupUsuariJPA.copyJPA(__jpa.getGrupUsuaris(), __alreadyCopied,"UsuariJPA"));
    }
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticios) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticios())) ) {
      __tmp.setPeticios(PeticioJPA.copyJPA(__jpa.getPeticios(), __alreadyCopied,"UsuariJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"UsuariJPA"));
    }

    return __tmp;
  }




}
