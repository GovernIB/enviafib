
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.SerieDocumental;


public class SerieDocumentalBean implements SerieDocumental {



private static final long serialVersionUID = 1488475090L;

	long seriedocuid;// PK
	java.lang.String nom;
	java.lang.String tipusdocu;


  /** Constructor Buit */
  public SerieDocumentalBean() {
  }

  /** Constructor amb tots els camps  */
  public SerieDocumentalBean(long seriedocuid , java.lang.String nom , java.lang.String tipusdocu) {
    this.seriedocuid=seriedocuid;
    this.nom=nom;
    this.tipusdocu=tipusdocu;
}
  /** Constructor sense valors autoincrementals */
  public SerieDocumentalBean(java.lang.String nom , java.lang.String tipusdocu) {
    this.nom=nom;
    this.tipusdocu=tipusdocu;
}
  public SerieDocumentalBean(SerieDocumental __bean) {
    this.setSeriedocuid(__bean.getSeriedocuid());
    this.setNom(__bean.getNom());
    this.setTipusdocu(__bean.getTipusdocu());
	}

	public long getSeriedocuid() {
		return(seriedocuid);
	};
	public void setSeriedocuid(long _seriedocuid_) {
		this.seriedocuid = _seriedocuid_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getTipusdocu() {
		return(tipusdocu);
	};
	public void setTipusdocu(java.lang.String _tipusdocu_) {
		this.tipusdocu = _tipusdocu_;
	};



  // ======================================

  public static SerieDocumentalBean toBean(SerieDocumental __bean) {
    if (__bean == null) { return null;}
    SerieDocumentalBean __tmp = new SerieDocumentalBean();
    __tmp.setSeriedocuid(__bean.getSeriedocuid());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusdocu(__bean.getTipusdocu());
		return __tmp;
	}



}
