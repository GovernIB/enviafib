
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "AvisJPA")
@Table(name = "efi_avis" , indexes = { 
        @Index(name="efi_avis_pk_i", columnList = "avisid")})
@SequenceGenerator(name="AVIS_SEQ", sequenceName="efi_avis_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class AvisJPA implements Avis {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="AVIS_SEQ")
    @Column(name="avisid",nullable = false,length = 19)
    long avisID;

    @Column(name="missatge",nullable = false,length = 500)
    java.lang.String missatge;

    @Column(name="datainici",length = 29,precision = 6)
    java.sql.Timestamp datainici;

    @Column(name="datafi",length = 29,precision = 6)
    java.sql.Timestamp datafi;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu = true;

    @Column(name="tipus",nullable = false,length = 30)
    java.lang.String tipus;



  /** Constructor Buit */
  public AvisJPA() {
  }

  /** Constructor amb tots els camps  */
  public AvisJPA(long avisID , java.lang.String missatge , java.sql.Timestamp datainici , java.sql.Timestamp datafi , boolean actiu , java.lang.String tipus) {
    this.avisID=avisID;
    this.missatge=missatge;
    this.datainici=datainici;
    this.datafi=datafi;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public AvisJPA(java.lang.String missatge , java.sql.Timestamp datainici , java.sql.Timestamp datafi , boolean actiu , java.lang.String tipus) {
    this.missatge=missatge;
    this.datainici=datainici;
    this.datafi=datafi;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public AvisJPA(long avisID , java.lang.String missatge , boolean actiu , java.lang.String tipus) {
    this.avisID=avisID;
    this.missatge=missatge;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public AvisJPA(Avis __bean) {
    this.setAvisID(__bean.getAvisID());
    this.setMissatge(__bean.getMissatge());
    this.setDatainici(__bean.getDatainici());
    this.setDatafi(__bean.getDatafi());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
	}

	public long getAvisID() {
		return(avisID);
	};
	public void setAvisID(long _avisID_) {
		this.avisID = _avisID_;
	};

	public java.lang.String getMissatge() {
		return(missatge);
	};
	public void setMissatge(java.lang.String _missatge_) {
		this.missatge = _missatge_;
	};

	public java.sql.Timestamp getDatainici() {
		return(datainici);
	};
	public void setDatainici(java.sql.Timestamp _datainici_) {
		this.datainici = _datainici_;
	};

	public java.sql.Timestamp getDatafi() {
		return(datafi);
	};
	public void setDatafi(java.sql.Timestamp _datafi_) {
		this.datafi = _datafi_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Avis) {
            Avis __instance = (Avis)__obj;
            __result = true;
            __result = __result && (this.getAvisID() == __instance.getAvisID()) ;
        } else {
            __result = false;
        }
        return __result;
    }


 // ---------------  STATIC METHODS ------------------
  public static AvisJPA toJPA(Avis __bean) {
    if (__bean == null) { return null;}
    AvisJPA __tmp = new AvisJPA();
    __tmp.setAvisID(__bean.getAvisID());
    __tmp.setMissatge(__bean.getMissatge());
    __tmp.setDatainici(__bean.getDatainici());
    __tmp.setDatafi(__bean.getDatafi());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}


  public static AvisJPA copyJPA(AvisJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<AvisJPA> copyJPA(java.util.Set<AvisJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<AvisJPA> __tmpSet = (java.util.Set<AvisJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<AvisJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (AvisJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static AvisJPA copyJPA(AvisJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    AvisJPA __tmp = (AvisJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
