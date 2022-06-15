package es.caib.enviafib.model.entity;

public interface InfoSignatura extends org.fundaciobit.genapp.common.IGenAppEntity {

	public long getInfosignaturaid();
	public void setInfosignaturaid(long _infosignaturaid_);

	public int getSignoperation();
	public void setSignoperation(int _signoperation_);

	public java.lang.String getSigntype();
	public void setSigntype(java.lang.String _signtype_);

	public java.lang.String getSignalgorithm();
	public void setSignalgorithm(java.lang.String _signalgorithm_);

	public java.lang.Integer getSignmode();
	public void setSignmode(java.lang.Integer _signmode_);

	public java.lang.Integer getSignaturestablelocation();
	public void setSignaturestablelocation(java.lang.Integer _signaturestablelocation_);

	public java.lang.Boolean getTimestampincluded();
	public void setTimestampincluded(java.lang.Boolean _timestampincluded_);

	public java.lang.Boolean getPolicyincluded();
	public void setPolicyincluded(java.lang.Boolean _policyincluded_);

	public java.lang.String getEnitipofirma();
	public void setEnitipofirma(java.lang.String _enitipofirma_);

	public java.lang.String getEniperfilfirma();
	public void setEniperfilfirma(java.lang.String _eniperfilfirma_);

	public java.lang.String getEnirolfirma();
	public void setEnirolfirma(java.lang.String _enirolfirma_);

	public java.lang.String getEnisignername();
	public void setEnisignername(java.lang.String _enisignername_);

	public java.lang.String getEnisigneradministrationid();
	public void setEnisigneradministrationid(java.lang.String _enisigneradministrationid_);

	public java.lang.String getEnisignlevel();
	public void setEnisignlevel(java.lang.String _enisignlevel_);

	public java.lang.Boolean getCheckadministrationidofsigner();
	public void setCheckadministrationidofsigner(java.lang.Boolean _checkadministrationidofsigner_);

	public java.lang.Boolean getCheckdocumentmodifications();
	public void setCheckdocumentmodifications(java.lang.Boolean _checkdocumentmodifications_);

	public java.lang.Boolean getCheckvalidationsignature();
	public void setCheckvalidationsignature(java.lang.Boolean _checkvalidationsignature_);



  // ======================================

}
