package es.caib.enviafib.model.entity;

public interface Peticio extends org.fundaciobit.genapp.common.IGenAppEntity {

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

	public java.lang.String getReason();
	public void setReason(java.lang.String _reason_);

	public java.lang.String getArxiuFuncionariUsername();
	public void setArxiuFuncionariUsername(java.lang.String _arxiuFuncionariUsername_);

	public java.lang.String getArxiuParamFuncionariNom();
	public void setArxiuParamFuncionariNom(java.lang.String _arxiuParamFuncionariNom_);

	public java.lang.String getArxiuParamFuncionariNif();
	public void setArxiuParamFuncionariNif(java.lang.String _arxiuParamFuncionariNif_);

	public java.lang.String getArxiuParamFuncionariDir3();
	public void setArxiuParamFuncionariDir3(java.lang.String _arxiuParamFuncionariDir3_);

	public java.lang.String getArxiuReqParamDocEstatElabora();
	public void setArxiuReqParamDocEstatElabora(java.lang.String _arxiuReqParamDocEstatElabora_);

	public java.lang.String getArxiuReqParamInteressats();
	public void setArxiuReqParamInteressats(java.lang.String _arxiuReqParamInteressats_);

	public java.lang.String getArxiuReqParamCiutadaNif();
	public void setArxiuReqParamCiutadaNif(java.lang.String _arxiuReqParamCiutadaNif_);

	public java.lang.String getArxiuReqParamCiutadaNom();
	public void setArxiuReqParamCiutadaNom(java.lang.String _arxiuReqParamCiutadaNom_);

	public java.lang.String getArxiuReqParamOrgans();
	public void setArxiuReqParamOrgans(java.lang.String _arxiuReqParamOrgans_);

	public java.lang.String getArxiuOptParamProcedimentCodi();
	public void setArxiuOptParamProcedimentCodi(java.lang.String _arxiuOptParamProcedimentCodi_);

	public java.lang.String getArxiuOptParamProcedimentNom();
	public void setArxiuOptParamProcedimentNom(java.lang.String _arxiuOptParamProcedimentNom_);

	public java.lang.String getArxiuOptParamSerieDocumental();
	public void setArxiuOptParamSerieDocumental(java.lang.String _arxiuOptParamSerieDocumental_);

	public java.lang.String getArxiuOptParamExpedientId();
	public void setArxiuOptParamExpedientId(java.lang.String _arxiuOptParamExpedientId_);

	public java.lang.Integer getArxiuReqParamOrigen();
	public void setArxiuReqParamOrigen(java.lang.Integer _arxiuReqParamOrigen_);

	public java.lang.Long getInfoArxiuID();
	public void setInfoArxiuID(java.lang.Long _infoArxiuID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

  // Fitxer
  public <F extends Fitxer> F getFitxer();
  // Fitxer
  public <F extends Fitxer> F getFitxerFirmat();


  // ======================================

}
