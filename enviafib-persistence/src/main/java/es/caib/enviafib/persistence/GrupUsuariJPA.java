
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.UniqueConstraint;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "GrupUsuariJPA")
@Table(name = "efi_grupusuari" , indexes = { 
        @Index(name="efi_grupusuari_pk_i", columnList = "grupusuariid"),
        @Index(name="efi_grupusuari_grupid_fk_i", columnList = "grupid"),
        @Index(name="efi_grupusuari_usuariid_fk_i", columnList = "usuariid")},
           uniqueConstraints = {
            @UniqueConstraint(name="efi_grupusuari_usuari_grup_uk", columnNames={"usuariid","grupid"}) } )
@SequenceGenerator(name="GRUPUSUARI_SEQ", sequenceName="efi_grupusuari_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class GrupUsuariJPA implements GrupUsuari {



private static final long serialVersionUID = 1043341428L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="GRUPUSUARI_SEQ")
    @Column(name="grupusuariid",nullable = false,length = 19)
    long grupUsuariID;

    @Column(name="grupid",nullable = false,length = 19)
    long grupID;

    @Column(name="usuariid",nullable = false,length = 19)
    long usuariID;



  /** Constructor Buit */
  public GrupUsuariJPA() {
  }

  /** Constructor amb tots els camps  */
  public GrupUsuariJPA(long grupUsuariID , long grupID , long usuariID) {
    this.grupUsuariID=grupUsuariID;
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  /** Constructor sense valors autoincrementals */
  public GrupUsuariJPA(long grupID , long usuariID) {
    this.grupID=grupID;
    this.usuariID=usuariID;
}
  public GrupUsuariJPA(GrupUsuari __bean) {
    this.setGrupUsuariID(__bean.getGrupUsuariID());
    this.setGrupID(__bean.getGrupID());
    this.setUsuariID(__bean.getUsuariID());
	}

	public long getGrupUsuariID() {
		return(grupUsuariID);
	};
	public void setGrupUsuariID(long _grupUsuariID_) {
		this.grupUsuariID = _grupUsuariID_;
	};

	public long getGrupID() {
		return(grupID);
	};
	public void setGrupID(long _grupID_) {
		this.grupID = _grupID_;
	};

	public long getUsuariID() {
		return(usuariID);
	};
	public void setUsuariID(long _usuariID_) {
		this.usuariID = _usuariID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof GrupUsuari) {
      GrupUsuari __instance = (GrupUsuari)__obj;
      __result = true;
      __result = __result && (this.getGrupUsuariID() == __instance.getGrupUsuariID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:grupid | Table: efi_grup | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupid", referencedColumnName ="grupID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_grupusuari_grup_grupid_fk"))
    private GrupJPA grup;

    public GrupJPA getGrup() {
    return this.grup;
  }

    public  void setGrup(GrupJPA grup) {
    this.grup = grup;
  }

// IMP Field:usuariid | Table: efi_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_grupusuari_usuari_usuar_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }


 // ---------------  STATIC METHODS ------------------
  public static GrupUsuariJPA toJPA(GrupUsuari __bean) {
    if (__bean == null) { return null;}
    GrupUsuariJPA __tmp = new GrupUsuariJPA();
    __tmp.setGrupUsuariID(__bean.getGrupUsuariID());
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setUsuariID(__bean.getUsuariID());
		return __tmp;
	}


  public static GrupUsuariJPA copyJPA(GrupUsuariJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<GrupUsuariJPA> copyJPA(java.util.Set<GrupUsuariJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<GrupUsuariJPA> __tmpSet = (java.util.Set<GrupUsuariJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<GrupUsuariJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (GrupUsuariJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static GrupUsuariJPA copyJPA(GrupUsuariJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    GrupUsuariJPA __tmp = (GrupUsuariJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"GrupJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grup) || org.hibernate.Hibernate.isInitialized(__jpa.getGrup()) ) ) {
      __tmp.setGrup(GrupJPA.copyJPA(__jpa.getGrup(), __alreadyCopied,"GrupUsuariJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"GrupUsuariJPA"));
    }

    return __tmp;
  }




}
