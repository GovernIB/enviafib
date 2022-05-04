package es.caib.enviafib.model.entity;

public interface Fitxer extends org.fundaciobit.genapp.common.filesystem.IFile {

	public long getFitxerID();
	public void setFitxerID(long _fitxerID_);

	public java.lang.String getNom();
	public void setNom(java.lang.String _nom_);

	public java.lang.String getMime();
	public void setMime(java.lang.String _mime_);

	public long getTamany();
	public void setTamany(long _tamany_);

	public java.lang.String getDescripcio();
	public void setDescripcio(java.lang.String _descripcio_);

  // DataHandler
  javax.activation.DataHandler getData();

  // Encrypted FileID
  public String getEncryptedFileID();

  public void setEncryptedFileID(String encryptedFileID);



  // ======================================

}
