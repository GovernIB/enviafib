
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


@Entity(name = "GrupJPA")
@Table(name = "efi_grup" , indexes = { 
        @Index(name="efi_grup_pk_i", columnList = "grupid")})
@SequenceGenerator(name="GRUP_SEQ", sequenceName="efi_grup_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class GrupJPA implements Grup {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GRUP_SEQ")
    @Column(name="grupid",nullable = false,length = 19)
    long grupID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;



  /** Constructor Buit */
  public GrupJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupJPA(long grupID , java.lang.String nom , java.lang.String descripcio) {
    this.grupID=grupID;
    this.nom=nom;
    this.descripcio=descripcio;
}
  /** Constructor sense valors autoincrementals */
  public GrupJPA(java.lang.String nom , java.lang.String descripcio) {
    this.nom=nom;
    this.descripcio=descripcio;
}
  public GrupJPA(Grup __bean) {
    this.setGrupID(__bean.getGrupID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
	}

	public long getGrupID() {
		return(grupID);
	};
	public void setGrupID(long _grupID_) {
		this.grupID = _grupID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
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
    if (__obj != null && __obj instanceof Grup) {
      Grup __instance = (Grup)__obj;
      __result = true;
      __result = __result && (this.getGrupID() == __instance.getGrupID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:grupid | Table: efi_grupusuari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "grup")
    private Set<GrupUsuariJPA> grupUsuaris = new HashSet<GrupUsuariJPA>(0);
    public  Set<GrupUsuariJPA> getGrupUsuaris() {
    return this.grupUsuaris;
  }

    public void setGrupUsuaris(Set<GrupUsuariJPA> grupUsuaris) {
      this.grupUsuaris = grupUsuaris;
    }



 // ---------------  STATIC METHODS ------------------
  public static GrupJPA toJPA(Grup __bean) {
    if (__bean == null) { return null;}
    GrupJPA __tmp = new GrupJPA();
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
		return __tmp;
	}


  public static GrupJPA copyJPA(GrupJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupJPA> copyJPA(java.util.Set<GrupJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupJPA> __tmpSet = (java.util.Set<GrupJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupJPA copyJPA(GrupJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupJPA __tmp = (GrupJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"GrupUsuariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grupUsuaris) || org.hibernate.Hibernate.isInitialized(__jpa.getGrupUsuaris())) ) {
      __tmp.setGrupUsuaris(GrupUsuariJPA.copyJPA(__jpa.getGrupUsuaris(), __alreadyCopied,"GrupJPA"));
    }
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
