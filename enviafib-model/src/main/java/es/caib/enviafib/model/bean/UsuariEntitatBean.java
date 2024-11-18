
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.UsuariEntitat;


public class UsuariEntitatBean implements UsuariEntitat {



	long usuarientitatid;// PK
	long usuariid;
	java.lang.String entitatid;


  /** Constructor Buit */
  public UsuariEntitatBean() {
  }

  /** Constructor amb tots els camps  */
  public UsuariEntitatBean(long usuarientitatid , long usuariid , java.lang.String entitatid) {
    this.usuarientitatid=usuarientitatid;
    this.usuariid=usuariid;
    this.entitatid=entitatid;
}
  /** Constructor sense valors autoincrementals */
  public UsuariEntitatBean(long usuariid , java.lang.String entitatid) {
    this.usuariid=usuariid;
    this.entitatid=entitatid;
}
  public UsuariEntitatBean(UsuariEntitat __bean) {
    this.setUsuarientitatid(__bean.getUsuarientitatid());
    this.setUsuariid(__bean.getUsuariid());
    this.setEntitatid(__bean.getEntitatid());
	}

	public long getUsuarientitatid() {
		return(usuarientitatid);
	};
	public void setUsuarientitatid(long _usuarientitatid_) {
		this.usuarientitatid = _usuarientitatid_;
	};

	public long getUsuariid() {
		return(usuariid);
	};
	public void setUsuariid(long _usuariid_) {
		this.usuariid = _usuariid_;
	};

	public java.lang.String getEntitatid() {
		return(entitatid);
	};
	public void setEntitatid(java.lang.String _entitatid_) {
		this.entitatid = _entitatid_;
	};



  // ======================================

  public static UsuariEntitatBean toBean(UsuariEntitat __bean) {
    if (__bean == null) { return null;}
    UsuariEntitatBean __tmp = new UsuariEntitatBean();
    __tmp.setUsuarientitatid(__bean.getUsuarientitatid());
    __tmp.setUsuariid(__bean.getUsuariid());
    __tmp.setEntitatid(__bean.getEntitatid());
		return __tmp;
	}



}
