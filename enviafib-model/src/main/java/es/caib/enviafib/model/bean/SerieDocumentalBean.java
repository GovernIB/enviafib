
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.SerieDocumental;


public class SerieDocumentalBean implements SerieDocumental {



	long serieDocumentalID;// PK
	java.lang.String nom;
	java.lang.String tipusDocumental;
	java.lang.String procedimentNom;
	java.lang.String procedimentCodi;


  /** Constructor Buit */
  public SerieDocumentalBean() {
  }

  /** Constructor amb tots els camps  */
  public SerieDocumentalBean(long serieDocumentalID , java.lang.String nom , java.lang.String tipusDocumental , java.lang.String procedimentNom , java.lang.String procedimentCodi) {
    this.serieDocumentalID=serieDocumentalID;
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
    this.procedimentNom=procedimentNom;
    this.procedimentCodi=procedimentCodi;
}
  /** Constructor sense valors autoincrementals */
  public SerieDocumentalBean(java.lang.String nom , java.lang.String tipusDocumental , java.lang.String procedimentNom , java.lang.String procedimentCodi) {
    this.nom=nom;
    this.tipusDocumental=tipusDocumental;
    this.procedimentNom=procedimentNom;
    this.procedimentCodi=procedimentCodi;
}
  public SerieDocumentalBean(SerieDocumental __bean) {
    this.setSerieDocumentalID(__bean.getSerieDocumentalID());
    this.setNom(__bean.getNom());
    this.setTipusDocumental(__bean.getTipusDocumental());
    this.setProcedimentNom(__bean.getProcedimentNom());
    this.setProcedimentCodi(__bean.getProcedimentCodi());
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

	public java.lang.String getProcedimentNom() {
		return(procedimentNom);
	};
	public void setProcedimentNom(java.lang.String _procedimentNom_) {
		this.procedimentNom = _procedimentNom_;
	};

	public java.lang.String getProcedimentCodi() {
		return(procedimentCodi);
	};
	public void setProcedimentCodi(java.lang.String _procedimentCodi_) {
		this.procedimentCodi = _procedimentCodi_;
	};



  // ======================================

  public static SerieDocumentalBean toBean(SerieDocumental __bean) {
    if (__bean == null) { return null;}
    SerieDocumentalBean __tmp = new SerieDocumentalBean();
    __tmp.setSerieDocumentalID(__bean.getSerieDocumentalID());
    __tmp.setNom(__bean.getNom());
    __tmp.setTipusDocumental(__bean.getTipusDocumental());
    __tmp.setProcedimentNom(__bean.getProcedimentNom());
    __tmp.setProcedimentCodi(__bean.getProcedimentCodi());
		return __tmp;
	}



}
