package es.caib.enviafib.model.entity;

public interface Peticio extends org.fundaciobit.genapp.common.IGenAppEntity {

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public long getPeticioID();
	public void setPeticioID(long _peticioID_);

	public java.sql.Timestamp getDataCreacio();
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_);

	public java.sql.Timestamp getDataFinal();
	public void setDataFinal(java.sql.Timestamp _dataFinal_);

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public long getSolicitantID();
	public void setSolicitantID(long _solicitantID_);

	public java.lang.String getIdiomaID();
	public void setIdiomaID(java.lang.String _idiomaID_);

	public java.lang.String getDestinatariNif();
	public void setDestinatariNif(java.lang.String _destinatariNif_);

	public int getEstat();
	public void setEstat(int _estat_);

	public java.lang.Long getFitxerFirmatID();
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_);

	public java.lang.String getTipusDocumental();
	public void setTipusDocumental(java.lang.String _tipusDocumental_);

	public java.lang.String getIdiomaDoc();
	public void setIdiomaDoc(java.lang.String _idiomaDoc_);

	public java.lang.Long getInfoSignaturaID();
	public void setInfoSignaturaID(java.lang.Long _infoSignaturaID_);

	public int getTipus();
	public void setTipus(int _tipus_);

	public java.lang.String getErrorMsg();
	public void setErrorMsg(java.lang.String _errorMsg_);

	public java.lang.String getErrorException();
	public void setErrorException(java.lang.String _errorException_);

	public java.lang.String getPeticioPortafirmes();
	public void setPeticioPortafirmes(java.lang.String _peticioPortafirmes_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();
  // Fitxer
  public <F extends Fitxer> F getFitxerFirmat();


  // ======================================

}
