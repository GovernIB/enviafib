
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


@Entity(name = "PeticioJPA")
@Table(name = "efi_peticio" , indexes = { 
        @Index(name="efi_peticio_titolid_fk_i", columnList = "titolid"),
        @Index(name="efi_peticio_pk_i", columnList = "peticioid"),
        @Index(name="efi_peticio_fitxerid_fk_i", columnList = "fitxerid"),
        @Index(name="efi_peticio_solicitantid_fk_i", columnList = "solicitantid"),
        @Index(name="efi_peticio_idiomaid_fk_i", columnList = "idiomaid")})
@SequenceGenerator(name="PETICIO_SEQ", sequenceName="efi_peticio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PeticioJPA implements Peticio {



private static final long serialVersionUID = 1230292508L;

    @Column(name="titolid",nullable = false,length = 19)
    long titolID;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PETICIO_SEQ")
    @Column(name="peticioid",nullable = false,length = 19)
    long peticioID;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp datacreacio;

    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

    @Column(name="solicitantid",nullable = false,length = 19)
    long solicitantID;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

    @Column(name="destinatarinif",nullable = false,length = 50)
    java.lang.String destinatarinif;

    @Column(name="estat",nullable = false,length = 19)
    long estat;

    @Column(name="fitxer_firmatid",length = 19)
    java.lang.Long fitxerFirmatID;

  /** Identificador de la peticio dins el sistema de Portafib. */
    @Column(name="peticioportafib",length = 19)
    java.lang.Long peticioPortafib;



  /** Constructor Buit */
  public PeticioJPA() {
  }

  /** Constructor amb tots els camps  */
  public PeticioJPA(long titolID , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.Long peticioPortafib) {
    this.titolID=titolID;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.peticioPortafib=peticioPortafib;
}
  /** Constructor sense valors autoincrementals */
  public PeticioJPA(long titolID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat , java.lang.Long fitxerFirmatID , java.lang.Long peticioPortafib) {
    this.titolID=titolID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.peticioPortafib=peticioPortafib;
}
  /** Constructor dels valors Not Null */
  public PeticioJPA(long titolID , long peticioID , java.sql.Timestamp datacreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatarinif , long estat) {
    this.titolID=titolID;
    this.peticioID=peticioID;
    this.datacreacio=datacreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatarinif=destinatarinif;
    this.estat=estat;
}
  public PeticioJPA(Peticio __bean) {
    this.setTitolID(__bean.getTitolID());
    this.setPeticioID(__bean.getPeticioID());
    this.setDatacreacio(__bean.getDatacreacio());
    this.setFitxerID(__bean.getFitxerID());
    this.setSolicitantID(__bean.getSolicitantID());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDestinatarinif(__bean.getDestinatarinif());
    this.setEstat(__bean.getEstat());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setPeticioPortafib(__bean.getPeticioPortafib());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    this.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
	}

	public long getTitolID() {
		return(titolID);
	};
	public void setTitolID(long _titolID_) {
		this.titolID = _titolID_;
	};

	public long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.sql.Timestamp getDatacreacio() {
		return(datacreacio);
	};
	public void setDatacreacio(java.sql.Timestamp _datacreacio_) {
		this.datacreacio = _datacreacio_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public long getSolicitantID() {
		return(solicitantID);
	};
	public void setSolicitantID(long _solicitantID_) {
		this.solicitantID = _solicitantID_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getDestinatarinif() {
		return(destinatarinif);
	};
	public void setDestinatarinif(java.lang.String _destinatarinif_) {
		this.destinatarinif = _destinatarinif_;
	};

	public long getEstat() {
		return(estat);
	};
	public void setEstat(long _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.Long getPeticioPortafib() {
		return(peticioPortafib);
	};
	public void setPeticioPortafib(java.lang.Long _peticioPortafib_) {
		this.peticioPortafib = _peticioPortafib_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Peticio) {
      Peticio __instance = (Peticio)__obj;
      __result = true;
      __result = __result && (this.getPeticioID() == __instance.getPeticioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: efi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "titolid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_traduccio_titl_fk"))
    private TraduccioJPA titol;

    public TraduccioJPA getTitol() {
    return this.titol;
  }

    public  void setTitol(TraduccioJPA titol) {
    this.titol = titol;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> getTitolTraduccions() {
    return this.titol.getTraduccions();
  }

  public void setTitolTraduccions(java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.titol.setTraduccions(__traduccions__);
  }


// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_fitxer_fitxer_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }

// IMP Field:usuariid | Table: efi_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitantid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_usuari_soli_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }

// IMP Field:idiomaid | Table: efi_idioma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_idioma_idiid_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxer_firmatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_fitxer_ffirm_fk"))
    private FitxerJPA fitxerFirmat;

    public FitxerJPA getFitxerFirmat() {
    return this.fitxerFirmat;
  }

    public  void setFitxerFirmat(FitxerJPA fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }


 // ---------------  STATIC METHODS ------------------
  public static PeticioJPA toJPA(Peticio __bean) {
    if (__bean == null) { return null;}
    PeticioJPA __tmp = new PeticioJPA();
    __tmp.setTitolID(__bean.getTitolID());
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setDatacreacio(__bean.getDatacreacio());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setSolicitantID(__bean.getSolicitantID());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDestinatarinif(__bean.getDestinatarinif());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setPeticioPortafib(__bean.getPeticioPortafib());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
		return __tmp;
	}


  public static PeticioJPA copyJPA(PeticioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PeticioJPA> copyJPA(java.util.Set<PeticioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PeticioJPA> __tmpSet = (java.util.Set<PeticioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PeticioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PeticioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PeticioJPA copyJPA(PeticioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PeticioJPA __tmp = (PeticioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"PeticioJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"PeticioJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.titol) || org.hibernate.Hibernate.isInitialized(__jpa.getTitol()) ) ) {
      __tmp.setTitol(TraduccioJPA.copyJPA(__jpa.getTitol(), __alreadyCopied,"PeticioJPA"));
    }

    return __tmp;
  }




}
