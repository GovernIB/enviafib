
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


@Entity(name = "InfoAnexJPA")
@Table(name = "efi_infoanex" , indexes = { 
        @Index(name="efi_infoanex_pk_i", columnList = "infoanexid"),
        @Index(name="efi_infoanex_peticioid_fk_i", columnList = "peticioid"),
        @Index(name="efi_infoanex_anexid_fk_i", columnList = "anexid")})
@SequenceGenerator(name="INFOANEX_SEQ", sequenceName="efi_infoanex_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class InfoAnexJPA implements InfoAnex {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="INFOANEX_SEQ")
    @Column(name="infoanexid",nullable = false,length = 19)
    long infoanexid;

    @Column(name="peticioid",length = 19)
    java.lang.Long peticioID;

    @Column(name="anexid",length = 19)
    java.lang.Long anexID;



  /** Constructor Buit */
  public InfoAnexJPA() {
  }

  /** Constructor amb tots els camps  */
  public InfoAnexJPA(long infoanexid , java.lang.Long peticioID , java.lang.Long anexID) {
    this.infoanexid=infoanexid;
    this.peticioID=peticioID;
    this.anexID=anexID;
}
  /** Constructor sense valors autoincrementals */
  public InfoAnexJPA(java.lang.Long peticioID , java.lang.Long anexID) {
    this.peticioID=peticioID;
    this.anexID=anexID;
}
  /** Constructor dels valors Not Null */
  public InfoAnexJPA(long infoanexid) {
    this.infoanexid=infoanexid;
}
  public InfoAnexJPA(InfoAnex __bean) {
    this.setInfoanexid(__bean.getInfoanexid());
    this.setPeticioID(__bean.getPeticioID());
    this.setAnexID(__bean.getAnexID());
    // Fitxer
    this.setAnex(FitxerJPA.toJPA(__bean.getAnex()));
	}

	public long getInfoanexid() {
		return(infoanexid);
	};
	public void setInfoanexid(long _infoanexid_) {
		this.infoanexid = _infoanexid_;
	};

	public java.lang.Long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(java.lang.Long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.lang.Long getAnexID() {
		return(anexID);
	};
	public void setAnexID(java.lang.Long _anexID_) {
		this.anexID = _anexID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof InfoAnex) {
      InfoAnex __instance = (InfoAnex)__obj;
      __result = true;
      __result = __result && (this.getInfoanexid() == __instance.getInfoanexid()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:peticioid | Table: efi_peticio | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "peticioid", referencedColumnName ="peticioID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_infoanex_peticio_fk"))
    private PeticioJPA peticio;

    public PeticioJPA getPeticio() {
    return this.peticio;
  }

    public  void setPeticio(PeticioJPA peticio) {
    this.peticio = peticio;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anexid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_infoanex_fitxer_fk"))
    private FitxerJPA anex;

    public FitxerJPA getAnex() {
    return this.anex;
  }

    public  void setAnex(FitxerJPA anex) {
    this.anex = anex;
  }


 // ---------------  STATIC METHODS ------------------
  public static InfoAnexJPA toJPA(InfoAnex __bean) {
    if (__bean == null) { return null;}
    InfoAnexJPA __tmp = new InfoAnexJPA();
    __tmp.setInfoanexid(__bean.getInfoanexid());
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setAnexID(__bean.getAnexID());
    // Fitxer
    __tmp.setAnex(FitxerJPA.toJPA(__bean.getAnex()));
		return __tmp;
	}


  public static InfoAnexJPA copyJPA(InfoAnexJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<InfoAnexJPA> copyJPA(java.util.Set<InfoAnexJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<InfoAnexJPA> __tmpSet = (java.util.Set<InfoAnexJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<InfoAnexJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (InfoAnexJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static InfoAnexJPA copyJPA(InfoAnexJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    InfoAnexJPA __tmp = (InfoAnexJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"PeticioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.peticio) || org.hibernate.Hibernate.isInitialized(__jpa.getPeticio()) ) ) {
      __tmp.setPeticio(PeticioJPA.copyJPA(__jpa.getPeticio(), __alreadyCopied,"InfoAnexJPA"));
    }

    return __tmp;
  }




}
