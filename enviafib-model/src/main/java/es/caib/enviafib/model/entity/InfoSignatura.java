package es.caib.enviafib.model.entity;

public interface InfoSignatura extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getInfoSignaturaID();
	public void setInfoSignaturaID(long _infoSignaturaID_);

	public int getSignOperation();
	public void setSignOperation(int _signOperation_);

	public java.lang.String getSignType();
	public void setSignType(java.lang.String _signType_);

	public java.lang.String getSignAlgorithm();
	public void setSignAlgorithm(java.lang.String _signAlgorithm_);

	public java.lang.Integer getSignMode();
	public void setSignMode(java.lang.Integer _signMode_);

	public java.lang.Integer getSignaturesTableLocation();
	public void setSignaturesTableLocation(java.lang.Integer _signaturesTableLocation_);

	public java.lang.Boolean getTimestampIncluded();
	public void setTimestampIncluded(java.lang.Boolean _timestampIncluded_);

	public java.lang.Boolean getPolicyIncluded();
	public void setPolicyIncluded(java.lang.Boolean _policyIncluded_);

	public java.lang.String getEniTipoFirma();
	public void setEniTipoFirma(java.lang.String _eniTipoFirma_);

	public java.lang.String getEniPerfilFirma();
	public void setEniPerfilFirma(java.lang.String _eniPerfilFirma_);

	public java.lang.String getEniRolFirma();
	public void setEniRolFirma(java.lang.String _eniRolFirma_);

	public java.lang.String getEniSignerName();
	public void setEniSignerName(java.lang.String _eniSignerName_);

	public java.lang.String getEniSignerAdministrationId();
	public void setEniSignerAdministrationId(java.lang.String _eniSignerAdministrationId_);

	public java.lang.String getEniSignLevel();
	public void setEniSignLevel(java.lang.String _eniSignLevel_);

	public java.lang.Boolean getCheckAdministrationIdOfSigner();
	public void setCheckAdministrationIdOfSigner(java.lang.Boolean _checkAdministrationIdOfSigner_);

	public java.lang.Boolean getCheckDocumentModifications();
	public void setCheckDocumentModifications(java.lang.Boolean _checkDocumentModifications_);

	public java.lang.Boolean getCheckValidationSignature();
	public void setCheckValidationSignature(java.lang.Boolean _checkValidationSignature_);



  // ======================================

}
