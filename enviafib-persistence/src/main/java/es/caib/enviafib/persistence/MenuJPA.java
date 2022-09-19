
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


@Entity(name = "MenuJPA")
@Table(name = "efi_menu" , indexes = { 
        @Index(name="efi_menu_pk_i", columnList = "menuid"),
        @Index(name="efi_menu_titolmenuid_fk_i", columnList = "titolmenuid"),
        @Index(name="efi_menu_ajudamenuid_fk_i", columnList = "ajudamenuid"),
        @Index(name="efi_menu_grupid_fk_i", columnList = "grupid")})
@SequenceGenerator(name="MENU_SEQ", sequenceName="efi_menu_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class MenuJPA implements Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="MENU_SEQ")
    @Column(name="menuid",nullable = false,length = 19)
    long menuID;

    @Column(name="nom",nullable = false,length = 255)
    java.lang.String nom;

    @Column(name="descripcio",length = 255)
    java.lang.String descripcio;

    @Column(name="titolmenuid",nullable = false,length = 19)
    long titolMenuID;

    @Column(name="ajudamenuid",nullable = false,length = 19)
    long ajudaMenuID;

    @Column(name="ordre",nullable = false,length = 10)
    int ordre;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Column(name="grupid",length = 19)
    java.lang.Long grupID;

    @Column(name="parametrecombo",length = 255)
    java.lang.String parametreCombo;

    @Column(name="parametretext",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String parametreText;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu = false;



  /** Constructor Buit */
  public MenuJPA() {
  }

  /** Constructor amb tots els camps  */
  public MenuJPA(long menuID , java.lang.String nom , java.lang.String descripcio , long titolMenuID , long ajudaMenuID , int ordre , int tipus , java.lang.Long grupID , java.lang.String parametreCombo , java.lang.String parametreText , boolean actiu) {
    this.menuID=menuID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.grupID=grupID;
    this.parametreCombo=parametreCombo;
    this.parametreText=parametreText;
    this.actiu=actiu;
}
  /** Constructor sense valors autoincrementals */
  public MenuJPA(java.lang.String nom , java.lang.String descripcio , long titolMenuID , long ajudaMenuID , int ordre , int tipus , java.lang.Long grupID , java.lang.String parametreCombo , java.lang.String parametreText , boolean actiu) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.grupID=grupID;
    this.parametreCombo=parametreCombo;
    this.parametreText=parametreText;
    this.actiu=actiu;
}
  /** Constructor dels valors Not Null */
  public MenuJPA(long menuID , java.lang.String nom , long titolMenuID , long ajudaMenuID , int ordre , int tipus , boolean actiu) {
    this.menuID=menuID;
    this.nom=nom;
    this.titolMenuID=titolMenuID;
    this.ajudaMenuID=ajudaMenuID;
    this.ordre=ordre;
    this.tipus=tipus;
    this.actiu=actiu;
}
  public MenuJPA(Menu __bean) {
    this.setMenuID(__bean.getMenuID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setTitolMenuID(__bean.getTitolMenuID());
    this.setAjudaMenuID(__bean.getAjudaMenuID());
    this.setOrdre(__bean.getOrdre());
    this.setTipus(__bean.getTipus());
    this.setGrupID(__bean.getGrupID());
    this.setParametreCombo(__bean.getParametreCombo());
    this.setParametreText(__bean.getParametreText());
    this.setActiu(__bean.isActiu());
	}

	public long getMenuID() {
		return(menuID);
	};
	public void setMenuID(long _menuID_) {
		this.menuID = _menuID_;
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

	public long getTitolMenuID() {
		return(titolMenuID);
	};
	public void setTitolMenuID(long _titolMenuID_) {
		this.titolMenuID = _titolMenuID_;
	};

	public long getAjudaMenuID() {
		return(ajudaMenuID);
	};
	public void setAjudaMenuID(long _ajudaMenuID_) {
		this.ajudaMenuID = _ajudaMenuID_;
	};

	public int getOrdre() {
		return(ordre);
	};
	public void setOrdre(int _ordre_) {
		this.ordre = _ordre_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.Long getGrupID() {
		return(grupID);
	};
	public void setGrupID(java.lang.Long _grupID_) {
		this.grupID = _grupID_;
	};

	public java.lang.String getParametreCombo() {
		return(parametreCombo);
	};
	public void setParametreCombo(java.lang.String _parametreCombo_) {
		this.parametreCombo = _parametreCombo_;
	};

	public java.lang.String getParametreText() {
		return(parametreText);
	};
	public void setParametreText(java.lang.String _parametreText_) {
		this.parametreText = _parametreText_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Menu) {
      Menu __instance = (Menu)__obj;
      __result = true;
      __result = __result && (this.getMenuID() == __instance.getMenuID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:traduccioid | Table: efi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "titolmenuid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_menu_traduccio_titol_fk"))
    private TraduccioJPA titolMenu;

    public TraduccioJPA getTitolMenu() {
    return this.titolMenu;
  }

    public  void setTitolMenu(TraduccioJPA titolMenu) {
    this.titolMenu = titolMenu;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> getTitolMenuTraduccions() {
    return this.titolMenu.getTraduccions();
  }

  public void setTitolMenuTraduccions(java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.titolMenu.setTraduccions(__traduccions__);
  }


// IMP Field:traduccioid | Table: efi_traduccio | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER, cascade=javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "ajudamenuid", referencedColumnName ="traduccioID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_menu_traduccio_ajuda_fk"))
    private TraduccioJPA ajudaMenu;

    public TraduccioJPA getAjudaMenu() {
    return this.ajudaMenu;
  }

    public  void setAjudaMenu(TraduccioJPA ajudaMenu) {
    this.ajudaMenu = ajudaMenu;
  }

  @javax.xml.bind.annotation.XmlTransient
  public java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> getAjudaMenuTraduccions() {
    return this.ajudaMenu.getTraduccions();
  }

  public void setAjudaMenuTraduccions(java.util.Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> __traduccions__) {
    this.ajudaMenu.setTraduccions(__traduccions__);
  }


// IMP Field:grupid | Table: efi_grup | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "grupid", referencedColumnName ="grupID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_menu_grup_grupid_fk"))
    private GrupJPA grup;

    public GrupJPA getGrup() {
    return this.grup;
  }

    public  void setGrup(GrupJPA grup) {
    this.grup = grup;
  }


 // ---------------  STATIC METHODS ------------------
  public static MenuJPA toJPA(Menu __bean) {
    if (__bean == null) { return null;}
    MenuJPA __tmp = new MenuJPA();
    __tmp.setMenuID(__bean.getMenuID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setTitolMenuID(__bean.getTitolMenuID());
    __tmp.setAjudaMenuID(__bean.getAjudaMenuID());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setGrupID(__bean.getGrupID());
    __tmp.setParametreCombo(__bean.getParametreCombo());
    __tmp.setParametreText(__bean.getParametreText());
    __tmp.setActiu(__bean.isActiu());
		return __tmp;
	}


  public static MenuJPA copyJPA(MenuJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<MenuJPA> copyJPA(java.util.Set<MenuJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<MenuJPA> __tmpSet = (java.util.Set<MenuJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<MenuJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (MenuJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static MenuJPA copyJPA(MenuJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    MenuJPA __tmp = (MenuJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"GrupJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.grup) || org.hibernate.Hibernate.isInitialized(__jpa.getGrup()) ) ) {
      __tmp.setGrup(GrupJPA.copyJPA(__jpa.getGrup(), __alreadyCopied,"MenuJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.titolMenu) || org.hibernate.Hibernate.isInitialized(__jpa.getTitolMenu()) ) ) {
      __tmp.setTitolMenu(TraduccioJPA.copyJPA(__jpa.getTitolMenu(), __alreadyCopied,"MenuJPA"));
    }
    if(!"TraduccioJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.ajudaMenu) || org.hibernate.Hibernate.isInitialized(__jpa.getAjudaMenu()) ) ) {
      __tmp.setAjudaMenu(TraduccioJPA.copyJPA(__jpa.getAjudaMenu(), __alreadyCopied,"MenuJPA"));
    }

    return __tmp;
  }




}
