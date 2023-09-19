
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "FaqJPA")
@Table(name = "efi_faq" , indexes = { 
        @Index(name="efi_faq_pk_i", columnList = "faqid"),
        @Index(name="efi_faq_fitxer1id_fk_i", columnList = "fitxer1id"),
        @Index(name="efi_faq_fitxer2id_fk_i", columnList = "fitxer2id"),
        @Index(name="efi_faq_fitxer3id_fk_i", columnList = "fitxer3id")})
@SequenceGenerator(name="FAQ_SEQ", sequenceName="efi_faq_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class FaqJPA implements Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="FAQ_SEQ")
    @Column(name="faqid",nullable = false,length = 19)
    long faqID;

    @Column(name="ordre",length = 19)
    java.lang.Long ordre;

    @Column(name="enunciat_es",length = 255)
    java.lang.String enunciat_es;

    @Column(name="enunciat_ca",length = 255)
    java.lang.String enunciat_ca;

    @Column(name="resposta_es",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String resposta_es;

    @Column(name="resposta_ca",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String resposta_ca;

    @Column(name="fitxer1id",length = 19)
    java.lang.Long fitxer1ID;

    @Column(name="fitxer2id",length = 19)
    java.lang.Long fitxer2ID;

    @Column(name="fitxer3id",length = 19)
    java.lang.Long fitxer3ID;



  /** Constructor Buit */
  public FaqJPA() {
  }

  /** Constructor amb tots els camps  */
  public FaqJPA(long faqID , java.lang.Long ordre , java.lang.String enunciat_es , java.lang.String enunciat_ca , java.lang.String resposta_es , java.lang.String resposta_ca , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.faqID=faqID;
    this.ordre=ordre;
    this.enunciat_es=enunciat_es;
    this.enunciat_ca=enunciat_ca;
    this.resposta_es=resposta_es;
    this.resposta_ca=resposta_ca;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor sense valors autoincrementals */
  public FaqJPA(java.lang.Long ordre , java.lang.String enunciat_es , java.lang.String enunciat_ca , java.lang.String resposta_es , java.lang.String resposta_ca , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.ordre=ordre;
    this.enunciat_es=enunciat_es;
    this.enunciat_ca=enunciat_ca;
    this.resposta_es=resposta_es;
    this.resposta_ca=resposta_ca;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor dels valors Not Null */
  public FaqJPA(long faqID) {
    this.faqID=faqID;
}
  public FaqJPA(Faq __bean) {
    this.setFaqID(__bean.getFaqID());
    this.setOrdre(__bean.getOrdre());
    this.setEnunciat_es(__bean.getEnunciat_es());
    this.setEnunciat_ca(__bean.getEnunciat_ca());
    this.setResposta_es(__bean.getResposta_es());
    this.setResposta_ca(__bean.getResposta_ca());
    this.setFitxer1ID(__bean.getFitxer1ID());
    this.setFitxer2ID(__bean.getFitxer2ID());
    this.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    this.setFitxer1(FitxerJPA.toJPA(__bean.getFitxer1()));
    // Fitxer
    this.setFitxer2(FitxerJPA.toJPA(__bean.getFitxer2()));
    // Fitxer
    this.setFitxer3(FitxerJPA.toJPA(__bean.getFitxer3()));
	}

	public long getFaqID() {
		return(faqID);
	};
	public void setFaqID(long _faqID_) {
		this.faqID = _faqID_;
	};

	public java.lang.Long getOrdre() {
		return(ordre);
	};
	public void setOrdre(java.lang.Long _ordre_) {
		this.ordre = _ordre_;
	};

	public java.lang.String getEnunciat_es() {
		return(enunciat_es);
	};
	public void setEnunciat_es(java.lang.String _enunciat_es_) {
		this.enunciat_es = _enunciat_es_;
	};

	public java.lang.String getEnunciat_ca() {
		return(enunciat_ca);
	};
	public void setEnunciat_ca(java.lang.String _enunciat_ca_) {
		this.enunciat_ca = _enunciat_ca_;
	};

	public java.lang.String getResposta_es() {
		return(resposta_es);
	};
	public void setResposta_es(java.lang.String _resposta_es_) {
		this.resposta_es = _resposta_es_;
	};

	public java.lang.String getResposta_ca() {
		return(resposta_ca);
	};
	public void setResposta_ca(java.lang.String _resposta_ca_) {
		this.resposta_ca = _resposta_ca_;
	};

	public java.lang.Long getFitxer1ID() {
		return(fitxer1ID);
	};
	public void setFitxer1ID(java.lang.Long _fitxer1ID_) {
		this.fitxer1ID = _fitxer1ID_;
	};

	public java.lang.Long getFitxer2ID() {
		return(fitxer2ID);
	};
	public void setFitxer2ID(java.lang.Long _fitxer2ID_) {
		this.fitxer2ID = _fitxer2ID_;
	};

	public java.lang.Long getFitxer3ID() {
		return(fitxer3ID);
	};
	public void setFitxer3ID(java.lang.Long _fitxer3ID_) {
		this.fitxer3ID = _fitxer3ID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Faq) {
      Faq __instance = (Faq)__obj;
      __result = true;
      __result = __result && (this.getFaqID() == __instance.getFaqID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxer1id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_faq_fitxer_fitxer1id_fk"))
    private FitxerJPA fitxer1;

    public FitxerJPA getFitxer1() {
    return this.fitxer1;
  }

    public  void setFitxer1(FitxerJPA fitxer1) {
    this.fitxer1 = fitxer1;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxer2id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_faq_fitxer_fitxer2id_fk"))
    private FitxerJPA fitxer2;

    public FitxerJPA getFitxer2() {
    return this.fitxer2;
  }

    public  void setFitxer2(FitxerJPA fitxer2) {
    this.fitxer2 = fitxer2;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxer3id", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_faq_fitxer_fitxer3id_fk"))
    private FitxerJPA fitxer3;

    public FitxerJPA getFitxer3() {
    return this.fitxer3;
  }

    public  void setFitxer3(FitxerJPA fitxer3) {
    this.fitxer3 = fitxer3;
  }


 // ---------------  STATIC METHODS ------------------
  public static FaqJPA toJPA(Faq __bean) {
    if (__bean == null) { return null;}
    FaqJPA __tmp = new FaqJPA();
    __tmp.setFaqID(__bean.getFaqID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setEnunciat_es(__bean.getEnunciat_es());
    __tmp.setEnunciat_ca(__bean.getEnunciat_ca());
    __tmp.setResposta_es(__bean.getResposta_es());
    __tmp.setResposta_ca(__bean.getResposta_ca());
    __tmp.setFitxer1ID(__bean.getFitxer1ID());
    __tmp.setFitxer2ID(__bean.getFitxer2ID());
    __tmp.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    __tmp.setFitxer1(FitxerJPA.toJPA(__bean.getFitxer1()));
    // Fitxer
    __tmp.setFitxer2(FitxerJPA.toJPA(__bean.getFitxer2()));
    // Fitxer
    __tmp.setFitxer3(FitxerJPA.toJPA(__bean.getFitxer3()));
		return __tmp;
	}


  public static FaqJPA copyJPA(FaqJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<FaqJPA> copyJPA(java.util.Set<FaqJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<FaqJPA> __tmpSet = (java.util.Set<FaqJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<FaqJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (FaqJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static FaqJPA copyJPA(FaqJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    FaqJPA __tmp = (FaqJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
