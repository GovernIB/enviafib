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

	public boolean isTimestampincluded();
	public void setTimestampincluded(boolean _timestampincluded_);

	public boolean isPolicyincluded();
	public void setPolicyincluded(boolean _policyincluded_);

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

	public boolean isCheckadministrationidofsigner();
	public void setCheckadministrationidofsigner(boolean _checkadministrationidofsigner_);

	public boolean isCheckdocumentmodifications();
	public void setCheckdocumentmodifications(boolean _checkdocumentmodifications_);

	public boolean isCheckvalidationsignature();
	public void setCheckvalidationsignature(boolean _checkvalidationsignature_);



  // ======================================

}
