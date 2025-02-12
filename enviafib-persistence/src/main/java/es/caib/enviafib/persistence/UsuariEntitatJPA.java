
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "UsuariEntitatJPA")
@Table(name = "efi_usuarientitat" , indexes = { 
        @Index(name="efi_usuarientitat_pk_i", columnList = "usuarientitatid"),
        @Index(name="efi_usuarientitat_usuari_fk_i", columnList = "usuariid"),
        @Index(name="efi_usuarientitat_entitat_fk_i", columnList = "entitatid")})
@SequenceGenerator(name="USUARIENTITAT_SEQ", sequenceName="efi_usuarientitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class UsuariEntitatJPA implements UsuariEntitat {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="USUARIENTITAT_SEQ")
    @Column(name="usuarientitatid",nullable = false,length = 19)
    long usuarientitatid;

    @Column(name="usuariid",nullable = false,length = 19)
    long usuariid;

    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatid;



  /** Constructor Buit */
  public UsuariEntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatJPA(long usuarientitatid , long usuariid , java.lang.String entitatid) {
    this.usuarientitatid=usuarientitatid;
    this.usuariid=usuariid;
    this.entitatid=entitatid;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatJPA(long usuariid , java.lang.String entitatid) {
    this.usuariid=usuariid;
    this.entitatid=entitatid;
}
  public UsuariEntitatJPA(UsuariEntitat __bean) {
    this.setUsuarientitatid(__bean.getUsuarientitatid());
    this.setUsuariid(__bean.getUsuariid());
    this.setEntitatid(__bean.getEntitatid());
	}

	public long getUsuarientitatid() {
		return(usuarientitatid);
	};
	public void setUsuarientitatid(long _usuarientitatid_) {
		this.usuarientitatid = _usuarientitatid_;
	};

	public long getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(long _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof UsuariEntitat) {
            UsuariEntitat __instance = (UsuariEntitat)__obj;
            __result = true;
            __result = __result && (this.getUsuarientitatid() == __instance.getUsuarientitatid()) ;
        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getUsuarientitatid())).hashCode();
    }

// IMP Field:usuariid | Table: efi_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuariid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_usrent_usuari_usuariid_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }

// IMP Field:entitatid | Table: efi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatid", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_usrent_entitat_entitati_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static UsuariEntitatJPA toJPA(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatJPA __tmp = new UsuariEntitatJPA();
    __tmp.setUsuarientitatid(__bean.getUsuarientitatid());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setEntitatid(__bean.getEntitatid());
		return __tmp;
	}


  public static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<UsuariEntitatJPA> copyJPA(java.util.Set<UsuariEntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<UsuariEntitatJPA> __tmpSet = (java.util.Set<UsuariEntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<UsuariEntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (UsuariEntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static UsuariEntitatJPA copyJPA(UsuariEntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    UsuariEntitatJPA __tmp = (UsuariEntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"UsuariEntitatJPA"));
    }
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"UsuariEntitatJPA"));
    }

    return __tmp;
  }




}
