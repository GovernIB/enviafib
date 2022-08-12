
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Plugin;


public class PluginBean implements Plugin {



	long pluginID;// PK
	java.lang.String nom;
	java.lang.String descripcio;
	java.lang.String classe;
	java.lang.String properties;
	boolean actiu;
	int tipus;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , java.lang.String nom , java.lang.String descripcio , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(java.lang.String nom , java.lang.String descripcio , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.nom=nom;
    this.descripcio=descripcio;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , java.lang.String nom , java.lang.String descripcio , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nom=nom;
    this.descripcio=descripcio;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNom(__bean.getNom());
    this.setDescripcio(__bean.getDescripcio());
    this.setClasse(__bean.getClasse());
    this.setProperties(__bean.getProperties());
    this.setActiu(__bean.isActiu());
    this.setTipus(__bean.getTipus());
	}

	public long getPluginID() {
		return(pluginID);
	};
	public void setPluginID(long _pluginID_) {
		this.pluginID = _pluginID_;
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



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDescripcio(__bean.getDescripcio());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setProperties(__bean.getProperties());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}



}
