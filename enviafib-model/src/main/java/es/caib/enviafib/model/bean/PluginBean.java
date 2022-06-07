
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Plugin;


public class PluginBean implements Plugin {



private static final long serialVersionUID = 1605894563L;

	long pluginID;// PK
	long nomid;
	long descripciocurtaid;
	java.lang.String classe;
	java.lang.String properties;
	boolean actiu;
	int tipus;


  /** Constructor Buit */
  public PluginBean() {
  }

  /** Constructor amb tots els camps  */
  public PluginBean(long pluginID , long nomid , long descripciocurtaid , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor sense valors autoincrementals */
  public PluginBean(long nomid , long descripciocurtaid , java.lang.String classe , java.lang.String properties , boolean actiu , int tipus) {
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.classe=classe;
    this.properties=properties;
    this.actiu=actiu;
    this.tipus=tipus;
}
  /** Constructor dels valors Not Null */
  public PluginBean(long pluginID , long nomid , long descripciocurtaid , boolean actiu , int tipus) {
    this.pluginID=pluginID;
    this.nomid=nomid;
    this.descripciocurtaid=descripciocurtaid;
    this.actiu=actiu;
    this.tipus=tipus;
}
  public PluginBean(Plugin __bean) {
    this.setPluginID(__bean.getPluginID());
    this.setNomid(__bean.getNomid());
    this.setDescripciocurtaid(__bean.getDescripciocurtaid());
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



  // ======================================

  public static PluginBean toBean(Plugin __bean) {
    if (__bean == null) { return null;}
    PluginBean __tmp = new PluginBean();
    __tmp.setPluginID(__bean.getPluginID());
    __tmp.setNomid(__bean.getNomid());
    __tmp.setDescripciocurtaid(__bean.getDescripciocurtaid());
    __tmp.setClasse(__bean.getClasse());
    __tmp.setProperties(__bean.getProperties());
    __tmp.setActiu(__bean.isActiu());
    __tmp.setTipus(__bean.getTipus());
		return __tmp;
	}



}
