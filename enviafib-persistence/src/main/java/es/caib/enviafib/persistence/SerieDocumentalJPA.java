
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;


@Entity(name = "SerieDocumentalJPA")
@Table(name = "efi_seriedocu" )
@SequenceGenerator(name="SERIEDOCUMENTAL_SEQ", sequenceName="efi_seriedocu_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SerieDocumentalJPA implements SerieDocumental {



private static final long serialVersionUID = -2030187655L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SERIEDOCUMENTAL_SEQ")
    @Column(name="seriedocuid",nullable = false,length = 19)
    long seriedocuid;

    @Column(name="nom",nullable = false,length = 256)
    java.lang.String nom;

    @Column(name="tipusdocu",unique = true,length = 256)
    java.lang.String tipusdocu;



  /** Constructor Buit */
  public SerieDocumentalJPA() {
  }

  /** Constructor amb tots els camps  */
  public SerieDocumentalJPA(long seriedocuid , java.lang.String nom , java.lang.String tipusdocu) {
    this.seriedocuid=seriedocuid;
    this.nom=nom;
    this.tipusdocu=tipusdocu;
}
  /** Constructor sense valors autoincrementals */
  public SerieDocumentalJPA(java.lang.String nom , java.lang.String tipusdocu) {
    this.nom=nom;
    this.tipusdocu=tipusdocu;
}
  public SerieDocumentalJPA(SerieDocumental __bean) {
    this.setSeriedocuid(__bean.getSeriedocuid());
    this.setNom(__bean.getNom());
    this.setTipusdocu(__bean.getTipusdocu());
	}

	public long getSeriedocuid() {
		return(seriedocuid);
	};
	public void setSeriedocuid(long _seriedocuid_) {
		this.seriedocuid = _seriedocuid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getTipusdocu() {
		return(tipusdocu);
	};
	public void setTipusdocu(java.lang.String _tipusdocu_) {
		this.tipusdocu = _tipusdocu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof SerieDocumental) {
      SerieDocumental __instance = (SerieDocumental)__obj;
      __result = true;
      __result = __result && (this.getSeriedocuid() == __instance.getSeriedocuid()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static SerieDocumentalJPA toJPA(SerieDocumental __bean) {
    if (__bean == null) { return null;}
    SerieDocumentalJPA __tmp = new SerieDocumentalJPA();
    __tmp.setSeriedocuid(__bean.getSeriedocuid());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusdocu(__bean.getTipusdocu());
		return __tmp;
	}


  public static SerieDocumentalJPA copyJPA(SerieDocumentalJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<SerieDocumentalJPA> copyJPA(java.util.Set<SerieDocumentalJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<SerieDocumentalJPA> __tmpSet = (java.util.Set<SerieDocumentalJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<SerieDocumentalJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (SerieDocumentalJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static SerieDocumentalJPA copyJPA(SerieDocumentalJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    SerieDocumentalJPA __tmp = (SerieDocumentalJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
