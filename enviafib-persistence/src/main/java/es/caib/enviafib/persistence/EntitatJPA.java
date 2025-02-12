
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import org.hibernate.annotations.Type;
import java.util.HashSet;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.FetchType;
import javax.persistence.Id;


@Entity(name = "EntitatJPA")
@Table(name = "efi_entitat" , indexes = { 
        @Index(name="efi_entitat_pk_i", columnList = "entitatid"),
        @Index(name="efi_entitat_faviconid_fk_i", columnList = "faviconid"),
        @Index(name="efi_entitat_logowebid_fk_i", columnList = "logowebid"),
        @Index(name="efi_entitat_logowebpeuid_fk_i", columnList = "logowebpeuid"),
        @Index(name="efi_entitat_logosegellid_fk_i", columnList = "logosegellid"),
        @Index(name="efi_entitat_motiudele_fk_i", columnList = "motiudelegacioid")})
@SequenceGenerator(name="ENTITAT_SEQ", sequenceName="efi_entitat_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class EntitatJPA implements Entitat {

    @Id
    @Column(name="entitatid",nullable = false,length = 50)
    java.lang.String entitatid;

    @Column(name="nom",nullable = false,length = 50)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="adrezahtml",nullable = false,length = 2000)
    java.lang.String adrezahtml;

    @Column(name="activa",nullable = false,length = 1)
    boolean activa = true;

    @Column(name="suporttelefon",length = 50)
    java.lang.String suporttelefon;

    @Column(name="suportweb",length = 250)
    java.lang.String suportweb;

    @Column(name="suportemail",length = 100)
    java.lang.String suportemail;

    @Column(name="faviconid",nullable = false,length = 19)
    long faviconID;

    @Column(name="logowebid",nullable = false,length = 19)
    long logowebID;

    @Column(name="logowebpeuid",nullable = false,length = 19)
    long logowebpeuID;

    @Column(name="logosegellid",nullable = false,length = 19)
    long logosegellID;

    @Column(name="web",nullable = false,length = 250)
    java.lang.String web;

    @Column(name="motiudelegacioid",length = 19)
    java.lang.Long motiudelegacioID;

    @org.hibernate.annotations.ColumnDefault("0")
    @Column(name="segelldetempsviaweb",nullable = false,length = 10)
    int segelldetempsviaweb = 0;

    @Column(name="checkcanviatdocfirmat",nullable = false,length = 1)
    boolean checkcanviatdocfirmat = true;

    @Column(name="propietatstaulafirmes",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String propietatstaulafirmes;

    @Column(name="dir3",length = 50)
    java.lang.String dir3;



  /** Constructor Buit */
  public EntitatJPA() {
  }

  /** Constructor amb tots els camps  */
  public EntitatJPA(java.lang.String entitatid , java.lang.String nom , java.lang.String descripcio , java.lang.String adrezahtml , boolean activa , java.lang.String suporttelefon , java.lang.String suportweb , java.lang.String suportemail , long faviconID , long logowebID , long logowebpeuID , long logosegellID , java.lang.String web , java.lang.Long motiudelegacioID , int segelldetempsviaweb , boolean checkcanviatdocfirmat , java.lang.String propietatstaulafirmes , java.lang.String dir3) {
    this.entitatid=entitatid;
    this.nom=nom;
    this.descripcio=descripcio;
    this.adrezahtml=adrezahtml;
    this.activa=activa;
    this.suporttelefon=suporttelefon;
    this.suportweb=suportweb;
    this.suportemail=suportemail;
    this.faviconID=faviconID;
    this.logowebID=logowebID;
    this.logowebpeuID=logowebpeuID;
    this.logosegellID=logosegellID;
    this.web=web;
    this.motiudelegacioID=motiudelegacioID;
    this.segelldetempsviaweb=segelldetempsviaweb;
    this.checkcanviatdocfirmat=checkcanviatdocfirmat;
    this.propietatstaulafirmes=propietatstaulafirmes;
    this.dir3=dir3;
}
  /** Constructor dels valors Not Null */
  public EntitatJPA(java.lang.String entitatid , java.lang.String nom , java.lang.String adrezahtml , boolean activa , long faviconID , long logowebID , long logowebpeuID , long logosegellID , java.lang.String web , int segelldetempsviaweb , boolean checkcanviatdocfirmat) {
    this.entitatid=entitatid;
    this.nom=nom;
    this.adrezahtml=adrezahtml;
    this.activa=activa;
    this.faviconID=faviconID;
    this.logowebID=logowebID;
    this.logowebpeuID=logowebpeuID;
    this.logosegellID=logosegellID;
    this.web=web;
    this.segelldetempsviaweb=segelldetempsviaweb;
    this.checkcanviatdocfirmat=checkcanviatdocfirmat;
}
  public EntitatJPA(Entitat __bean) {
    this.setEntitatid(__bean.getEntitatid());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setAdrezahtml(__bean.getAdrezahtml());
    this.setActiva(__bean.isActiva());
    this.setSuporttelefon(__bean.getSuporttelefon());
    this.setSuportweb(__bean.getSuportweb());
    this.setSuportemail(__bean.getSuportemail());
    this.setFaviconID(__bean.getFaviconID());
    this.setLogowebID(__bean.getLogowebID());
    this.setLogowebpeuID(__bean.getLogowebpeuID());
    this.setLogosegellID(__bean.getLogosegellID());
    this.setWeb(__bean.getWeb());
    this.setMotiudelegacioID(__bean.getMotiudelegacioID());
    this.setSegelldetempsviaweb(__bean.getSegelldetempsviaweb());
    this.setCheckcanviatdocfirmat(__bean.isCheckcanviatdocfirmat());
    this.setPropietatstaulafirmes(__bean.getPropietatstaulafirmes());
    this.setDir3(__bean.getDir3());
    // Fitxer
    this.setFavicon(FitxerJPA.toJPA(__bean.getFavicon()));
    // Fitxer
    this.setLogoweb(FitxerJPA.toJPA(__bean.getLogoweb()));
    // Fitxer
    this.setLogowebpeu(FitxerJPA.toJPA(__bean.getLogowebpeu()));
    // Fitxer
    this.setLogosegell(FitxerJPA.toJPA(__bean.getLogosegell()));
	}

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
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

	public java.lang.String getAdrezahtml() {
		return(adrezahtml);
	};
	public void setAdrezahtml(java.lang.String _adrezahtml_) {
		this.adrezahtml = _adrezahtml_;
	};

	public boolean isActiva() {
		return(activa);
	};
	public void setActiva(boolean _activa_) {
		this.activa = _activa_;
	};

	public java.lang.String getSuporttelefon() {
		return(suporttelefon);
	};
	public void setSuporttelefon(java.lang.String _suporttelefon_) {
		this.suporttelefon = _suporttelefon_;
	};

	public java.lang.String getSuportweb() {
		return(suportweb);
	};
	public void setSuportweb(java.lang.String _suportweb_) {
		this.suportweb = _suportweb_;
	};

	public java.lang.String getSuportemail() {
		return(suportemail);
	};
	public void setSuportemail(java.lang.String _suportemail_) {
		this.suportemail = _suportemail_;
	};

	public long getFaviconID() {
		return(faviconID);
	};
	public void setFaviconID(long _faviconID_) {
		this.faviconID = _faviconID_;
	};

	public long getLogowebID() {
		return(logowebID);
	};
	public void setLogowebID(long _logowebID_) {
		this.logowebID = _logowebID_;
	};

	public long getLogowebpeuID() {
		return(logowebpeuID);
	};
	public void setLogowebpeuID(long _logowebpeuID_) {
		this.logowebpeuID = _logowebpeuID_;
	};

	public long getLogosegellID() {
		return(logosegellID);
	};
	public void setLogosegellID(long _logosegellID_) {
		this.logosegellID = _logosegellID_;
	};

	public java.lang.String getWeb() {
		return(web);
	};
	public void setWeb(java.lang.String _web_) {
		this.web = _web_;
	};

	public java.lang.Long getMotiudelegacioID() {
		return(motiudelegacioID);
	};
	public void setMotiudelegacioID(java.lang.Long _motiudelegacioID_) {
		this.motiudelegacioID = _motiudelegacioID_;
	};

	public int getSegelldetempsviaweb() {
		return(segelldetempsviaweb);
	};
	public void setSegelldetempsviaweb(int _segelldetempsviaweb_) {
		this.segelldetempsviaweb = _segelldetempsviaweb_;
	};

	public boolean isCheckcanviatdocfirmat() {
		return(checkcanviatdocfirmat);
	};
	public void setCheckcanviatdocfirmat(boolean _checkcanviatdocfirmat_) {
		this.checkcanviatdocfirmat = _checkcanviatdocfirmat_;
	};

	public java.lang.String getPropietatstaulafirmes() {
		return(propietatstaulafirmes);
	};
	public void setPropietatstaulafirmes(java.lang.String _propietatstaulafirmes_) {
		this.propietatstaulafirmes = _propietatstaulafirmes_;
	};

	public java.lang.String getDir3() {
		return(dir3);
	};
	public void setDir3(java.lang.String _dir3_) {
		this.dir3 = _dir3_;
	};



    @Override
    public boolean equals(Object __obj) {
        boolean __result;
        if (__obj != null && __obj instanceof Entitat) {
            Entitat __instance = (Entitat)__obj;
            __result = true;
      if (this.getEntitatid() == null) {
        __result = __result && (__instance.getEntitatid() == null);
      } else {
        __result = __result && this.getEntitatid().equals(__instance.getEntitatid()) ;
      }

        } else {
            __result = false;
        }
        return __result;
    }

    @Override
    public int hashCode() {
        return (String.valueOf(this.getEntitatid())).hashCode();
    }

// EXP  Field:entitatid | Table: efi_seriedocumental | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<SerieDocumentalJPA> serieDocumentals = new HashSet<SerieDocumentalJPA>(0);
    public  Set<SerieDocumentalJPA> getSerieDocumentals() {
    return this.serieDocumentals;
  }

    public void setSerieDocumentals(Set<SerieDocumentalJPA> serieDocumentals) {
      this.serieDocumentals = serieDocumentals;
    }


// EXP  Field:entitatid | Table: efi_usuari | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariJPA> usuaris = new HashSet<UsuariJPA>(0);
    public  Set<UsuariJPA> getUsuaris() {
    return this.usuaris;
  }

    public void setUsuaris(Set<UsuariJPA> usuaris) {
      this.usuaris = usuaris;
    }


// EXP  Field:entitatid | Table: efi_usuarientitat | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "entitat")
    private Set<UsuariEntitatJPA> usuariEntitats = new HashSet<UsuariEntitatJPA>(0);
    public  Set<UsuariEntitatJPA> getUsuariEntitats() {
    return this.usuariEntitats;
  }

    public void setUsuariEntitats(Set<UsuariEntitatJPA> usuariEntitats) {
      this.usuariEntitats = usuariEntitats;
    }


// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "faviconid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_entitat_fitxer_icon_fk"))
    private FitxerJPA favicon;

    public FitxerJPA getFavicon() {
    return this.favicon;
  }

    public  void setFavicon(FitxerJPA favicon) {
    this.favicon = favicon;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logowebid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_entitat_fitxer_loca_fk"))
    private FitxerJPA logoweb;

    public FitxerJPA getLogoweb() {
    return this.logoweb;
  }

    public  void setLogoweb(FitxerJPA logoweb) {
    this.logoweb = logoweb;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logowebpeuid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_entitat_fitxer_lope_fk"))
    private FitxerJPA logowebpeu;

    public FitxerJPA getLogowebpeu() {
    return this.logowebpeu;
  }

    public  void setLogowebpeu(FitxerJPA logowebpeu) {
    this.logowebpeu = logowebpeu;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "logosegellid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_entitat_fitxer_lose_fk"))
    private FitxerJPA logosegell;

    public FitxerJPA getLogosegell() {
    return this.logosegell;
  }

    public  void setLogosegell(FitxerJPA logosegell) {
    this.logosegell = logosegell;
  }

// IMP Field:traduccioid | Table: efi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "motiudelegacioid", referencedColumnName ="traduccioID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_entitat_traduccio_moti_fk"))
    private TraduccioJPA motiudelegacio;

    public TraduccioJPA getMotiudelegacio() {
    return this.motiudelegacio;
  }

    public  void setMotiudelegacio(TraduccioJPA motiudelegacio) {
    this.motiudelegacio = motiudelegacio;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> getMotiudelegacioTraduccions() {
    return this.motiudelegacio.getTraduccions();
  }

  public void setMotiudelegacioTraduccions(java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.motiudelegacio.setTraduccions(__traduccions__);
  }



 // ---------------  STATIC METHODS ------------------
  public static EntitatJPA toJPA(Entitat __bean) {
    if (__bean == null) { return null;}
    EntitatJPA __tmp = new EntitatJPA();
    __tmp.setEntitatid(__bean.getEntitatid());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setAdrezahtml(__bean.getAdrezahtml());
    __tmp.setActiva(__bean.isActiva());
    __tmp.setSuporttelefon(__bean.getSuporttelefon());
    __tmp.setSuportweb(__bean.getSuportweb());
    __tmp.setSuportemail(__bean.getSuportemail());
    __tmp.setFaviconID(__bean.getFaviconID());
    __tmp.setLogowebID(__bean.getLogowebID());
    __tmp.setLogowebpeuID(__bean.getLogowebpeuID());
    __tmp.setLogosegellID(__bean.getLogosegellID());
    __tmp.setWeb(__bean.getWeb());
    __tmp.setMotiudelegacioID(__bean.getMotiudelegacioID());
    __tmp.setSegelldetempsviaweb(__bean.getSegelldetempsviaweb());
    __tmp.setCheckcanviatdocfirmat(__bean.isCheckcanviatdocfirmat());
    __tmp.setPropietatstaulafirmes(__bean.getPropietatstaulafirmes());
    __tmp.setDir3(__bean.getDir3());
    // Fitxer
    __tmp.setFavicon(FitxerJPA.toJPA(__bean.getFavicon()));
    // Fitxer
    __tmp.setLogoweb(FitxerJPA.toJPA(__bean.getLogoweb()));
    // Fitxer
    __tmp.setLogowebpeu(FitxerJPA.toJPA(__bean.getLogowebpeu()));
    // Fitxer
    __tmp.setLogosegell(FitxerJPA.toJPA(__bean.getLogosegell()));
		return __tmp;
	}


  public static EntitatJPA copyJPA(EntitatJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<EntitatJPA> copyJPA(java.util.Set<EntitatJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<EntitatJPA> __tmpSet = (java.util.Set<EntitatJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<EntitatJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (EntitatJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static EntitatJPA copyJPA(EntitatJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    EntitatJPA __tmp = (EntitatJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"SerieDocumentalJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.serieDocumentals) || org.hibernate.Hibernate.isInitialized(__jpa.getSerieDocumentals())) ) {
      __tmp.setSerieDocumentals(SerieDocumentalJPA.copyJPA(__jpa.getSerieDocumentals(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariEntitatJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuariEntitats) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuariEntitats())) ) {
      __tmp.setUsuariEntitats(UsuariEntitatJPA.copyJPA(__jpa.getUsuariEntitats(), __alreadyCopied,"EntitatJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuaris) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuaris())) ) {
      __tmp.setUsuaris(UsuariJPA.copyJPA(__jpa.getUsuaris(), __alreadyCopied,"EntitatJPA"));
    }
    // Copia de beans complexes (IMP)
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.motiudelegacio) || org.hibernate.Hibernate.isInitialized(__jpa.getMotiudelegacio()) ) ) {
      __tmp.setMotiudelegacio(TraduccioJPA.copyJPA(__jpa.getMotiudelegacio(), __alreadyCopied,"EntitatJPA"));
    }

    return __tmp;
  }




}
