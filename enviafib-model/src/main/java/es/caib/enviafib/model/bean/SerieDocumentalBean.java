
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.SerieDocumental;


public class SerieDocumentalBean implements SerieDocumental {



private static final long serialVersionUID = 1488475090L;

	long serieDocumentalID;// PK
	java.lang.String nom;
	java.lang.String tipusDocumental;


  /** Constructor Buit */
  public SerieDocumentalBean() {
  }

  /** Constructor amb tots els camps  */
  public SerieDocumentalBean(long serieDocumentalID , java.lang.String nom , java.lang.String tipusDocumental) {
    this.serieDocumentalID=serieDocumentalID;
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
}
  /** Constructor sense valors autoincrementals */
  public SerieDocumentalBean(java.lang.String nom , java.lang.String tipusDocumental) {
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
}
  public SerieDocumentalBean(SerieDocumental __bean) {
    this.setSerieDocumentalID(__bean.getSerieDocumentalID());
    this.setNom(__bean.getNom());
    this.setTipusDocumental(__bean.getTipusDocumental());
	}

	public long getSerieDocumentalID() {
		return(serieDocumentalID);
	};
	public void setSerieDocumentalID(long _serieDocumentalID_) {
		this.serieDocumentalID = _serieDocumentalID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.lang.String getTipusDocumental() {
		return(tipusDocumental);
	};
	public void setTipusDocumental(java.lang.String _tipusDocumental_) {
		this.tipusDocumental = _tipusDocumental_;
	};



  // ======================================

  public static SerieDocumentalBean toBean(SerieDocumental __bean) {
    if (__bean == null) { return null;}
    SerieDocumentalBean __tmp = new SerieDocumentalBean();
    __tmp.setSerieDocumentalID(__bean.getSerieDocumentalID());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusDocumental(__bean.getTipusDocumental());
		return __tmp;
	}



}
