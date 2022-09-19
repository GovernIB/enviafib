
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.HashMap;
import org.hibernate.annotations.Cascade;
import java.util.HashSet;
import javax.persistence.GenerationType;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import java.util.Map;
import javax.persistence.FetchType;
import javax.persistence.ElementCollection;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.Id;


@Entity(name = "TraduccioJPA")
@Table(name = "efi_traduccio" , indexes = { 
        @Index(name="efi_traduccio_pk_i", columnList = "traduccioid")})
@SequenceGenerator(name="TRADUCCIO_SEQ", sequenceName="efi_traduccio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class TraduccioJPA implements Traduccio {

  /**  */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="TRADUCCIO_SEQ")
    @Column(name="traduccioid",nullable = false,length = 19)
    long traduccioID;



  /** Constructor Buit */
  public TraduccioJPA() {
  }

  /** Constructor amb tots els camps  */
  public TraduccioJPA(long traduccioID) {
    this.traduccioID=traduccioID;
}
  public TraduccioJPA(Traduccio __bean) {
    this.setTraduccioID(__bean.getTraduccioID());
	}

	public long getTraduccioID() {
		return(traduccioID);
	};
	public void setTraduccioID(long _traduccioID_) {
		this.traduccioID = _traduccioID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Traduccio) {
      Traduccio __instance = (Traduccio)__obj;
      __result = true;
      __result = __result && (this.getTraduccioID() == __instance.getTraduccioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// EXP  Field:ajudamenuid | Table: efi_menu | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "ajudaMenuID")
    private Set<MenuJPA> menu_ajudamenuids = new HashSet<MenuJPA>(0);
    public  Set<MenuJPA> getMenu_ajudamenuids() {
    return this.menu_ajudamenuids;
  }

    public void setMenu_ajudamenuids(Set<MenuJPA> menu_ajudamenuids) {
      this.menu_ajudamenuids = menu_ajudamenuids;
    }


// EXP  Field:titolmenuid | Table: efi_menu | Type: 0  

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "titolMenuID")
    private Set<MenuJPA> menu_titolmenuids = new HashSet<MenuJPA>(0);
    public  Set<MenuJPA> getMenu_titolmenuids() {
    return this.menu_titolmenuids;
  }

    public void setMenu_titolmenuids(Set<MenuJPA> menu_titolmenuids) {
      this.menu_titolmenuids = menu_titolmenuids;
    }


  @ElementCollection(fetch= FetchType.EAGER, targetClass = es.caib.enviafib.persistence.TraduccioMapJPA.class)
  @Cascade(value=org.hibernate.annotations.CascadeType.ALL)
  @LazyCollection(value= LazyCollectionOption.FALSE)
  @JoinTable(name="efi_traducciomap",joinColumns={@JoinColumn(name="traducciomapid")}, foreignKey=@ForeignKey(name="efi_traducmap_traduccio_fk"))
  @javax.persistence.MapKeyColumn(name="idiomaid")
  private Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> traduccions =  new HashMap<String, es.caib.enviafib.persistence.TraduccioMapJPA>();

  public Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> getTraduccions() {
    return this.traduccions;
  }

  public void setTraduccions(Map<String, es.caib.enviafib.persistence.TraduccioMapJPA> _traduccions_) {
    this.traduccions = _traduccions_;
  }

  public java.util.Set<String> getIdiomes() {
    return this.traduccions.keySet();
  }
  
  public es.caib.enviafib.persistence.TraduccioMapJPA getTraduccio(String idioma) {
    return (es.caib.enviafib.persistence.TraduccioMapJPA) traduccions.get(idioma);
  }
  
  public void addTraduccio(String idioma, es.caib.enviafib.persistence.TraduccioMapJPA traduccio) {
    if (traduccio == null) {
      traduccions.remove(idioma);
    } else {
      traduccions.put(idioma, traduccio);
    }
  }

 // ---------------  STATIC METHODS ------------------
  public static TraduccioJPA toJPA(Traduccio __bean) {
    if (__bean == null) { return null;}
    TraduccioJPA __tmp = new TraduccioJPA();
    __tmp.setTraduccioID(__bean.getTraduccioID());
		return __tmp;
	}


  public static TraduccioJPA copyJPA(TraduccioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<TraduccioJPA> copyJPA(java.util.Set<TraduccioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<TraduccioJPA> __tmpSet = (java.util.Set<TraduccioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<TraduccioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (TraduccioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static TraduccioJPA copyJPA(TraduccioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    TraduccioJPA __tmp = (TraduccioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    if(!"MenuJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.menu_titolmenuids) || org.hibernate.Hibernate.isInitialized(__jpa.getMenu_titolmenuids())) ) {
      __tmp.setMenu_titolmenuids(MenuJPA.copyJPA(__jpa.getMenu_titolmenuids(), __alreadyCopied,"TraduccioJPA"));
    }
    if(!"MenuJPA".equals(origenJPA) 
       && ( !org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.menu_ajudamenuids) || org.hibernate.Hibernate.isInitialized(__jpa.getMenu_ajudamenuids())) ) {
      __tmp.setMenu_ajudamenuids(MenuJPA.copyJPA(__jpa.getMenu_ajudamenuids(), __alreadyCopied,"TraduccioJPA"));
    }
    // Copia de beans complexes (IMP)
    // Aquesta linia s'afeix de forma manual
    __tmp.setTraduccions(new HashMap<String, TraduccioMapJPA>(__jpa.getTraduccions()));

    return __tmp;
  }




}
