
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


@Entity(name = "OrganitzacioJPA")
@Table(name = "efi_organitzacio" , indexes = { 
        @Index(name="efi_organitzacio_pk_i", columnList = "organitzacioid")})
@SequenceGenerator(name="ORGANITZACIO_SEQ", sequenceName="efi_organitzacio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class OrganitzacioJPA implements Organitzacio {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="ORGANITZACIO_SEQ")
    @Column(name="organitzacioid",nullable = false,length = 19)
    long organitzacioID;

    @Column(name="codiconselleria",length = 100)
    java.lang.String codiConselleria;

    @Column(name="codidirecciogeneral",length = 100)
    java.lang.String codiDireccioGeneral;

    @Column(name="tipus",length = 100)
    java.lang.String tipus;

    @Column(name="valor",length = 255)
    java.lang.String valor;



  /** Constructor Buit */
  public OrganitzacioJPA() {
  }

  /** Constructor amb tots els camps  */
  public OrganitzacioJPA(long organitzacioID , java.lang.String codiConselleria , java.lang.String codiDireccioGeneral , java.lang.String tipus , java.lang.String valor) {
    this.organitzacioID=organitzacioID;
    this.codiConselleria=codiConselleria;
    this.codiDireccioGeneral=codiDireccioGeneral;
    this.tipus=tipus;
    this.valor=valor;
}
  /** Constructor sense valors autoincrementals */
  public OrganitzacioJPA(java.lang.String codiConselleria , java.lang.String codiDireccioGeneral , java.lang.String tipus , java.lang.String valor) {
    this.codiConselleria=codiConselleria;
    this.codiDireccioGeneral=codiDireccioGeneral;
    this.tipus=tipus;
    this.valor=valor;
}
  /** Constructor dels valors Not Null */
  public OrganitzacioJPA(long organitzacioID) {
    this.organitzacioID=organitzacioID;
}
  public OrganitzacioJPA(Organitzacio __bean) {
    this.setOrganitzacioID(__bean.getOrganitzacioID());
    this.setCodiConselleria(__bean.getCodiConselleria());
    this.setCodiDireccioGeneral(__bean.getCodiDireccioGeneral());
    this.setTipus(__bean.getTipus());
    this.setValor(__bean.getValor());
	}

	public long getOrganitzacioID() {
		return(organitzacioID);
	};
	public void setOrganitzacioID(long _organitzacioID_) {
		this.organitzacioID = _organitzacioID_;
	};

	public java.lang.String getCodiConselleria() {
		return(codiConselleria);
	};
	public void setCodiConselleria(java.lang.String _codiConselleria_) {
		this.codiConselleria = _codiConselleria_;
	};

	public java.lang.String getCodiDireccioGeneral() {
		return(codiDireccioGeneral);
	};
	public void setCodiDireccioGeneral(java.lang.String _codiDireccioGeneral_) {
		this.codiDireccioGeneral = _codiDireccioGeneral_;
	};

	public java.lang.String getTipus() {
		return(tipus);
	};
	public void setTipus(java.lang.String _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getValor() {
		return(valor);
	};
	public void setValor(java.lang.String _valor_) {
		this.valor = _valor_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Organitzacio) {
      Organitzacio __instance = (Organitzacio)__obj;
      __result = true;
      __result = __result && (this.getOrganitzacioID() == __instance.getOrganitzacioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static OrganitzacioJPA toJPA(Organitzacio __bean) {
    if (__bean == null) { return null;}
    OrganitzacioJPA __tmp = new OrganitzacioJPA();
    __tmp.setOrganitzacioID(__bean.getOrganitzacioID());
    __tmp.setCodiConselleria(__bean.getCodiConselleria());
    __tmp.setCodiDireccioGeneral(__bean.getCodiDireccioGeneral());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setValor(__bean.getValor());
		return __tmp;
	}


  public static OrganitzacioJPA copyJPA(OrganitzacioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<OrganitzacioJPA> copyJPA(java.util.Set<OrganitzacioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<OrganitzacioJPA> __tmpSet = (java.util.Set<OrganitzacioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<OrganitzacioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (OrganitzacioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static OrganitzacioJPA copyJPA(OrganitzacioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    OrganitzacioJPA __tmp = (OrganitzacioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
