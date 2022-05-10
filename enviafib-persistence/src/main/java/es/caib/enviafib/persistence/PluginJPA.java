
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "Plugin")
@Table(name = "efi_plugin" , indexes = { 
        @Index(name="efi_plugin_pk_i", columnList = "pluginid")})
@SequenceGenerator(name="PLUGIN_SEQ", sequenceName="efi_plugin_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PluginJPA implements Plugin {



private static final long serialVersionUID = 190357384L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PLUGIN_SEQ")
    @Column(name="pluginid",nullable = false,length = 19)
    long pluginid;

    @Column(name="nomid",nullable = false,length = 19)
    long nomid;

    @Column(name="descripciocurtaid",nullable = false,length = 19)
    long descripciocurtaid;

    @Column(name="classe",length = 255)
    java.lang.String classe;

    @Column(name="properties",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String properties;

    @Column(name="actiu",nullable = false,length = 1)
    boolean actiu;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;



  /** Constructor Buit */
  public PluginJPA() {
  }

  /** Constructor amb tots els camps  */
  public PluginJPA(long pluginid , long nomid , long descripciocurtaid , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.pluginid=pluginid;
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginJPA(long nomid , long descripciocurtaid , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginJPA(long pluginid , long nomid , long descripciocurtaid , boolean actiu , int tipus) {
    this.pluginid=pluginid;
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public PluginJPA(Plugin __bean) {
    this.setPluginid(__bean.getPluginid());
    this.setNomid(__bean.getNomid());
    this.setDescripciocurtaid(__bean.getDescripciocurtaid());
    this.setClasse(__bean.getClasse());
    this.setProperties(__bean.getProperties());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
	}

	public long getPluginid() {
		return(pluginid);
	};
	public void setPluginid(long _pluginid_) {
		this.pluginid = _pluginid_;
	};

	public long getNomid() {
		return(nomid);
	};
	public void setNomid(long _nomid_) {
		this.nomid = _nomid_;
	};

	public long getDescripciocurtaid() {
		return(descripciocurtaid);
	};
	public void setDescripciocurtaid(long _descripciocurtaid_) {
		this.descripciocurtaid = _descripciocurtaid_;
	};

	public java.lang.String getClasse() {
		return(classe);
	};
	public void setClasse(java.lang.String _classe_) {
		this.classe = _classe_;
	};

	public java.lang.String getProperties() {
		return(properties);
	};
	public void setProperties(java.lang.String _properties_) {
		this.properties = _properties_;
	};

	public boolean isActiu() {
		return(actiu);
	};
	public void setActiu(boolean _actiu_) {
		this.actiu = _actiu_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Plugin) {
      Plugin __instance = (Plugin)__obj;
      __result = true;
      __result = __result && (this.getPluginid() == __instance.getPluginid()) ;
    } else {
      __result = false;
    }
    return __result;
  }


 // ---------------  STATIC METHODS ------------------
  public static PluginJPA toJPA(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginJPA __tmp = new PluginJPA();
    __tmp.setPluginid(__bean.getPluginid());
    __tmp.setNomid(__bean.getNomid());
    __tmp.setDescripciocurtaid(__bean.getDescripciocurtaid());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setProperties(__bean.getProperties());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}


  public static PluginJPA copyJPA(PluginJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PluginJPA> copyJPA(java.util.Set<PluginJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PluginJPA> __tmpSet = (java.util.Set<PluginJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PluginJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PluginJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PluginJPA copyJPA(PluginJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PluginJPA __tmp = (PluginJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)

    return __tmp;
  }




}
