
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import org.hibernate.annotations.Type;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "SerieDocumentalJPA")
@Table(name = "efi_seriedocumental" , indexes = { 
        @Index(name="efi_seriedocumental_pk_i", columnList = "seriedocumentalid"),
        @Index(name="efi_seriedocu_entitatid_fk_i", columnList = "entitatid")},
           uniqueConstraints = {
            @UniqueConstraint(name="efi_seriedocu_td_ent_uk", columnNames={"tipusdocumental","entitatid"}) } )
@SequenceGenerator(name="SERIEDOCUMENTAL_SEQ", sequenceName="efi_seriedocumental_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class SerieDocumentalJPA implements SerieDocumental {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="SERIEDOCUMENTAL_SEQ")
    @Column(name="seriedocumentalid",nullable = false,length = 19)
    long serieDocumentalID;

    @Column(name="nom",nullable = false,length = 256)
    java.lang.String nom;

    @Column(name="tipusdocumental",length = 256)
    java.lang.String tipusDocumental;

    @Column(name="procedimentnom",nullable = false,length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String procedimentNom;

    @Column(name="procedimentcodi",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String procedimentCodi;

    @Column(name="entitatid",length = 50)
    java.lang.String entitatID;



  /** Constructor Buit */
  public SerieDocumentalJPA() {
  }

  /** Constructor amb tots els camps  */
  public SerieDocumentalJPA(long serieDocumentalID , java.lang.String nom , java.lang.String tipusDocumental , java.lang.String procedimentNom , java.lang.String procedimentCodi , java.lang.String entitatID) {
    this.serieDocumentalID=serieDocumentalID;
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
    this.procedimentNom=procedimentNom;
    this.procedimentCodi=procedimentCodi;
    this.entitatID=entitatID;
}
  /** Constructor sense valors autoincrementals */
  public SerieDocumentalJPA(java.lang.String nom , java.lang.String tipusDocumental , java.lang.String procedimentNom , java.lang.String procedimentCodi , java.lang.String entitatID) {
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
    this.procedimentNom=procedimentNom;
    this.procedimentCodi=procedimentCodi;
    this.entitatID=entitatID;
}
  /** Constructor dels valors Not Null */
  public SerieDocumentalJPA(long serieDocumentalID , java.lang.String nom , java.lang.String procedimentNom) {
    this.serieDocumentalID=serieDocumentalID;
    this.nom=nom;
    this.procedimentNom=procedimentNom;
}
  public SerieDocumentalJPA(SerieDocumental __bean) {
    this.setSerieDocumentalID(__bean.getSerieDocumentalID());
    this.setNom(__bean.getNom());
    this.setTipusDocumental(__bean.getTipusDocumental());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
    this.setEntitatID(__bean.getEntitatID());
	}

	public long getSerieDocumentalID() {
		return(serieDocumentalID);
	};
	public void setSerieDocumentalID(long _serieDocumentalID_) {
		this.serieDocumentalID = _serieDocumentalID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getTipusDocumental() {
		return(tipusDocumental);
	};
	public void setTipusDocumental(java.lang.String _tipusDocumental_) {
		this.tipusDocumental = _tipusDocumental_;
	};

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getProcedimentCodi() {
		return(procedimentCodi);
	};
	public void setProcedimentCodi(java.lang.String _procedimentCodi_) {
		this.procedimentCodi = _procedimentCodi_;
	};

	public java.lang.String getEntitatID() {
		return(entitatID);
	};
	public void setEntitatID(java.lang.String _entitatID_) {
		this.entitatID = _entitatID_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof SerieDocumental) {
            SerieDocumental __instance = (SerieDocumental)__obj;
            __result = true;
            __result = __result && (this.getSerieDocumentalID() == __instance.getSerieDocumentalID()) ;
        } else {
            __result = false;
        }
        return __result;
    }

// IMP Field:entitatid | Table: efi_entitat | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "entitatid", referencedColumnName ="entitatid", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_seriedocu_entitat_entit_fk"))
    private EntitatJPA entitat;

    public EntitatJPA getEntitat() {
    return this.entitat;
  }

    public  void setEntitat(EntitatJPA entitat) {
    this.entitat = entitat;
  }


 // ---------------  STATIC METHODS ------------------
  public static SerieDocumentalJPA toJPA(SerieDocumental __bean) {
    if (__bean == null) { return null;}
    SerieDocumentalJPA __tmp = new SerieDocumentalJPA();
    __tmp.setSerieDocumentalID(__bean.getSerieDocumentalID());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusDocumental(__bean.getTipusDocumental());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
    __tmp.setEntitatID(__bean.getEntitatID());
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
    if(!"EntitatJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.entitat) || org.hibernate.Hibernate.isInitialized(__jpa.getEntitat()) ) ) {
      __tmp.setEntitat(EntitatJPA.copyJPA(__jpa.getEntitat(), __alreadyCopied,"SerieDocumentalJPA"));
    }

    return __tmp;
  }




}
