
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


@Entity(name = "Usuari")
@Table(name = "efi_usuari" , indexes = { 
        @Index(name="efi_usuari_pk_i", columnList = "usuariid")})
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



  /** Constructor Buit */
  public UsuariJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariJPA(long usuariID , java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email) {
    this.usuariID=usuariID;
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
}
  /** Constructor sense valors autoincrementals */
  public UsuariJPA(java.lang.String username , java.lang.String nom , java.lang.String llinatge1 , java.lang.String llinatge2 , java.lang.String nif , java.lang.String email) {
    this.username=username;
    this.nom=nom;
    this.llinatge1=llinatge1;
    this.llinatge2=llinatge2;
    this.nif=nif;
    this.email=email;
}
  public UsuariJPA(Usuari __bean) {
    this.setUsuariID(__bean.getUsuariID());
    this.setUsername(__bean.getUsername());
    this.setNom(__bean.getNom());
    this.setLlinatge1(__bean.getLlinatge1());
    this.setLlinatge2(__bean.getLlinatge2());
    this.setNif(__bean.getNif());
    this.setEmail(__bean.getEmail());
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

// EXP  Field:solicitantid | Table: efi_peticio | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuari")
    private Set<PeticioJPA> peticios = new HashSet<PeticioJPA>(0);
    public  Set<PeticioJPA> getPeticios() {
    return this.peticios;
  }

    public void setPeticios(Set<PeticioJPA> peticios) {
      this.peticios = peticios;
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
    if(!"PeticioJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticios) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticios())) ) {
      __tmp.setPeticios(PeticioJPA.copyJPA(__jpa.getPeticios(), __alreadyCopied,"UsuariJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
