
package es.caib.enviafib.persistence;
import es.caib.enviafib.model.entity.*;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Index;
import javax.persistence.GeneratedValue;
import javax.persistence.SequenceGenerator;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.FetchType;
import org.hibernate.annotations.Type;
import javax.persistence.Id;


@Entity(name = "PeticioJPA")
@Table(name = "efi_peticio" , indexes = { 
        @Index(name="efi_peticio_pk_i", columnList = "peticioid"),
        @Index(name="efi_peticio_fitxerid_fk_i", columnList = "fitxerid"),
        @Index(name="efi_peticio_solicitantid_fk_i", columnList = "solicitantid"),
        @Index(name="efi_peticio_idiomaid_fk_i", columnList = "idiomaid"),
        @Index(name="efi_peticio_fitxer_firma_fk_i", columnList = "fitxer_firmatid"),
        @Index(name="efi_peticio_infosignid_fk_i", columnList = "infosignaturaid"),
        @Index(name="efi_peticio_infoarxiuid_fk_i", columnList = "infoarxiuid")})
@SequenceGenerator(name="PETICIO_SEQ", sequenceName="efi_peticio_seq", allocationSize=1, initialValue=1000)
@javax.xml.bind.annotation.XmlRootElement
public class PeticioJPA implements Peticio {



private static final long serialVersionUID = 1230292508L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="PETICIO_SEQ")
    @Column(name="peticioid",nullable = false,length = 19)
    long peticioID;

  /** Nom de la peticio a PortaFIB. */
    @Column(name="nom",length = 255)
    java.lang.String nom;

    @Column(name="datacreacio",nullable = false,length = 29,precision = 6)
    java.sql.Timestamp dataCreacio;

    @Column(name="datafinal",length = 29,precision = 6)
    java.sql.Timestamp dataFinal;

    @Column(name="fitxerid",nullable = false,length = 19)
    long fitxerID;

    @Column(name="solicitantid",nullable = false,length = 19)
    long solicitantID;

    @Column(name="idiomaid",nullable = false,length = 5)
    java.lang.String idiomaID;

    @Column(name="destinatarinif",length = 50)
    java.lang.String destinatariNif;

    @Column(name="estat",nullable = false,length = 10)
    int estat;

    @Column(name="fitxer_firmatid",length = 19)
    java.lang.Long fitxerFirmatID;

    @Column(name="tipusdocumental",nullable = false,length = 100)
    java.lang.String tipusDocumental;

    @Column(name="idiomadoc",nullable = false,length = 30)
    java.lang.String idiomaDoc;

    @Column(name="infosignaturaid",length = 19)
    java.lang.Long infoSignaturaID;

    @Column(name="tipus",nullable = false,length = 10)
    int tipus;

    @Column(name="errormsg",length = 255)
    java.lang.String errorMsg;

    @Column(name="errorexception",length = 2147483647)
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    java.lang.String errorException;

  /** Identificador de la petició dins el sistema de portafirmes */
    @Column(name="peticioportafirmes",length = 255)
    java.lang.String peticioPortafirmes;

    @Column(name="reason",length = 255)
    java.lang.String reason;

    @Column(name="arxiufuncionariusername",length = 255)
    java.lang.String arxiuFuncionariUsername;

    @Column(name="arxiuparamfuncionarinom",length = 255)
    java.lang.String arxiuParamFuncionariNom;

    @Column(name="arxiuparamfuncionarinif",length = 255)
    java.lang.String arxiuParamFuncionariNif;

    @Column(name="arxiuparamfuncionaridir3",length = 255)
    java.lang.String arxiuParamFuncionariDir3;

    @Column(name="arxiureqparamdocestatelabora",length = 4)
    java.lang.String arxiuReqParamDocEstatElabora;

    @Column(name="arxiureqparaminteressats",length = 255)
    java.lang.String arxiuReqParamInteressats;

    @Column(name="arxiureqparamciutadanif",length = 15)
    java.lang.String arxiuReqParamCiutadaNif;

    @Column(name="arxiureqparamciutadanom",length = 255)
    java.lang.String arxiuReqParamCiutadaNom;

    @Column(name="arxiureqparamorgans",length = 255)
    java.lang.String arxiuReqParamOrgans;

    @Column(name="arxiuoptparamprocedimentcodi",length = 255)
    java.lang.String arxiuOptParamProcedimentCodi;

    @Column(name="arxiuoptparamprocedimentnom",length = 255)
    java.lang.String arxiuOptParamProcedimentNom;

    @Column(name="arxiuoptparamseriedocumental",length = 255)
    java.lang.String arxiuOptParamSerieDocumental;

    @Column(name="arxiuoptparamexpedientid",length = 255)
    java.lang.String arxiuOptParamExpedientId;

    @Column(name="arxiureqparamorigen",length = 10)
    java.lang.Integer arxiuReqParamOrigen;

    @Column(name="infoarxiuid",length = 19)
    java.lang.Long infoArxiuID;



  /** Constructor Buit */
  public PeticioJPA() {
  }

  /** Constructor amb tots els camps  */
  public PeticioJPA(long peticioID , java.lang.String nom , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataFinal , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatariNif , int estat , java.lang.Long fitxerFirmatID , java.lang.String tipusDocumental , java.lang.String idiomaDoc , java.lang.Long infoSignaturaID , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.lang.String peticioPortafirmes , java.lang.String reason , java.lang.String arxiuFuncionariUsername , java.lang.String arxiuParamFuncionariNom , java.lang.String arxiuParamFuncionariNif , java.lang.String arxiuParamFuncionariDir3 , java.lang.String arxiuReqParamDocEstatElabora , java.lang.String arxiuReqParamInteressats , java.lang.String arxiuReqParamCiutadaNif , java.lang.String arxiuReqParamCiutadaNom , java.lang.String arxiuReqParamOrgans , java.lang.String arxiuOptParamProcedimentCodi , java.lang.String arxiuOptParamProcedimentNom , java.lang.String arxiuOptParamSerieDocumental , java.lang.String arxiuOptParamExpedientId , java.lang.Integer arxiuReqParamOrigen , java.lang.Long infoArxiuID) {
    this.peticioID=peticioID;
    this.nom=nom;
    this.dataCreacio=dataCreacio;
    this.dataFinal=dataFinal;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatariNif=destinatariNif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.infoSignaturaID=infoSignaturaID;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.peticioPortafirmes=peticioPortafirmes;
    this.reason=reason;
    this.arxiuFuncionariUsername=arxiuFuncionariUsername;
    this.arxiuParamFuncionariNom=arxiuParamFuncionariNom;
    this.arxiuParamFuncionariNif=arxiuParamFuncionariNif;
    this.arxiuParamFuncionariDir3=arxiuParamFuncionariDir3;
    this.arxiuReqParamDocEstatElabora=arxiuReqParamDocEstatElabora;
    this.arxiuReqParamInteressats=arxiuReqParamInteressats;
    this.arxiuReqParamCiutadaNif=arxiuReqParamCiutadaNif;
    this.arxiuReqParamCiutadaNom=arxiuReqParamCiutadaNom;
    this.arxiuReqParamOrgans=arxiuReqParamOrgans;
    this.arxiuOptParamProcedimentCodi=arxiuOptParamProcedimentCodi;
    this.arxiuOptParamProcedimentNom=arxiuOptParamProcedimentNom;
    this.arxiuOptParamSerieDocumental=arxiuOptParamSerieDocumental;
    this.arxiuOptParamExpedientId=arxiuOptParamExpedientId;
    this.arxiuReqParamOrigen=arxiuReqParamOrigen;
    this.infoArxiuID=infoArxiuID;
}
  /** Constructor sense valors autoincrementals */
  public PeticioJPA(java.lang.String nom , java.sql.Timestamp dataCreacio , java.sql.Timestamp dataFinal , long fitxerID , long solicitantID , java.lang.String idiomaID , java.lang.String destinatariNif , int estat , java.lang.Long fitxerFirmatID , java.lang.String tipusDocumental , java.lang.String idiomaDoc , java.lang.Long infoSignaturaID , int tipus , java.lang.String errorMsg , java.lang.String errorException , java.lang.String peticioPortafirmes , java.lang.String reason , java.lang.String arxiuFuncionariUsername , java.lang.String arxiuParamFuncionariNom , java.lang.String arxiuParamFuncionariNif , java.lang.String arxiuParamFuncionariDir3 , java.lang.String arxiuReqParamDocEstatElabora , java.lang.String arxiuReqParamInteressats , java.lang.String arxiuReqParamCiutadaNif , java.lang.String arxiuReqParamCiutadaNom , java.lang.String arxiuReqParamOrgans , java.lang.String arxiuOptParamProcedimentCodi , java.lang.String arxiuOptParamProcedimentNom , java.lang.String arxiuOptParamSerieDocumental , java.lang.String arxiuOptParamExpedientId , java.lang.Integer arxiuReqParamOrigen , java.lang.Long infoArxiuID) {
    this.nom=nom;
    this.dataCreacio=dataCreacio;
    this.dataFinal=dataFinal;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.destinatariNif=destinatariNif;
    this.estat=estat;
    this.fitxerFirmatID=fitxerFirmatID;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.infoSignaturaID=infoSignaturaID;
    this.tipus=tipus;
    this.errorMsg=errorMsg;
    this.errorException=errorException;
    this.peticioPortafirmes=peticioPortafirmes;
    this.reason=reason;
    this.arxiuFuncionariUsername=arxiuFuncionariUsername;
    this.arxiuParamFuncionariNom=arxiuParamFuncionariNom;
    this.arxiuParamFuncionariNif=arxiuParamFuncionariNif;
    this.arxiuParamFuncionariDir3=arxiuParamFuncionariDir3;
    this.arxiuReqParamDocEstatElabora=arxiuReqParamDocEstatElabora;
    this.arxiuReqParamInteressats=arxiuReqParamInteressats;
    this.arxiuReqParamCiutadaNif=arxiuReqParamCiutadaNif;
    this.arxiuReqParamCiutadaNom=arxiuReqParamCiutadaNom;
    this.arxiuReqParamOrgans=arxiuReqParamOrgans;
    this.arxiuOptParamProcedimentCodi=arxiuOptParamProcedimentCodi;
    this.arxiuOptParamProcedimentNom=arxiuOptParamProcedimentNom;
    this.arxiuOptParamSerieDocumental=arxiuOptParamSerieDocumental;
    this.arxiuOptParamExpedientId=arxiuOptParamExpedientId;
    this.arxiuReqParamOrigen=arxiuReqParamOrigen;
    this.infoArxiuID=infoArxiuID;
}
  /** Constructor dels valors Not Null */
  public PeticioJPA(long peticioID , java.sql.Timestamp dataCreacio , long fitxerID , long solicitantID , java.lang.String idiomaID , int estat , java.lang.String tipusDocumental , java.lang.String idiomaDoc , int tipus) {
    this.peticioID=peticioID;
    this.dataCreacio=dataCreacio;
    this.fitxerID=fitxerID;
    this.solicitantID=solicitantID;
    this.idiomaID=idiomaID;
    this.estat=estat;
    this.tipusDocumental=tipusDocumental;
    this.idiomaDoc=idiomaDoc;
    this.tipus=tipus;
}
  public PeticioJPA(Peticio __bean) {
    this.setPeticioID(__bean.getPeticioID());
    this.setNom(__bean.getNom());
    this.setDataCreacio(__bean.getDataCreacio());
    this.setDataFinal(__bean.getDataFinal());
    this.setFitxerID(__bean.getFitxerID());
    this.setSolicitantID(__bean.getSolicitantID());
    this.setIdiomaID(__bean.getIdiomaID());
    this.setDestinatariNif(__bean.getDestinatariNif());
    this.setEstat(__bean.getEstat());
    this.setFitxerFirmatID(__bean.getFitxerFirmatID());
    this.setTipusDocumental(__bean.getTipusDocumental());
    this.setIdiomaDoc(__bean.getIdiomaDoc());
    this.setInfoSignaturaID(__bean.getInfoSignaturaID());
    this.setTipus(__bean.getTipus());
    this.setErrorMsg(__bean.getErrorMsg());
    this.setErrorException(__bean.getErrorException());
    this.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
    this.setReason(__bean.getReason());
    this.setArxiuFuncionariUsername(__bean.getArxiuFuncionariUsername());
    this.setArxiuParamFuncionariNom(__bean.getArxiuParamFuncionariNom());
    this.setArxiuParamFuncionariNif(__bean.getArxiuParamFuncionariNif());
    this.setArxiuParamFuncionariDir3(__bean.getArxiuParamFuncionariDir3());
    this.setArxiuReqParamDocEstatElabora(__bean.getArxiuReqParamDocEstatElabora());
    this.setArxiuReqParamInteressats(__bean.getArxiuReqParamInteressats());
    this.setArxiuReqParamCiutadaNif(__bean.getArxiuReqParamCiutadaNif());
    this.setArxiuReqParamCiutadaNom(__bean.getArxiuReqParamCiutadaNom());
    this.setArxiuReqParamOrgans(__bean.getArxiuReqParamOrgans());
    this.setArxiuOptParamProcedimentCodi(__bean.getArxiuOptParamProcedimentCodi());
    this.setArxiuOptParamProcedimentNom(__bean.getArxiuOptParamProcedimentNom());
    this.setArxiuOptParamSerieDocumental(__bean.getArxiuOptParamSerieDocumental());
    this.setArxiuOptParamExpedientId(__bean.getArxiuOptParamExpedientId());
    this.setArxiuReqParamOrigen(__bean.getArxiuReqParamOrigen());
    this.setInfoArxiuID(__bean.getInfoArxiuID());
    // Fitxer
    this.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    this.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
	}

	public long getPeticioID() {
		return(peticioID);
	};
	public void setPeticioID(long _peticioID_) {
		this.peticioID = _peticioID_;
	};

	public java.lang.String getNom() {
		return(nom);
	};
	public void setNom(java.lang.String _nom_) {
		this.nom = _nom_;
	};

	public java.sql.Timestamp getDataCreacio() {
		return(dataCreacio);
	};
	public void setDataCreacio(java.sql.Timestamp _dataCreacio_) {
		this.dataCreacio = _dataCreacio_;
	};

	public java.sql.Timestamp getDataFinal() {
		return(dataFinal);
	};
	public void setDataFinal(java.sql.Timestamp _dataFinal_) {
		this.dataFinal = _dataFinal_;
	};

	public long getFitxerID() {
		return(fitxerID);
	};
	public void setFitxerID(long _fitxerID_) {
		this.fitxerID = _fitxerID_;
	};

	public long getSolicitantID() {
		return(solicitantID);
	};
	public void setSolicitantID(long _solicitantID_) {
		this.solicitantID = _solicitantID_;
	};

	public java.lang.String getIdiomaID() {
		return(idiomaID);
	};
	public void setIdiomaID(java.lang.String _idiomaID_) {
		this.idiomaID = _idiomaID_;
	};

	public java.lang.String getDestinatariNif() {
		return(destinatariNif);
	};
	public void setDestinatariNif(java.lang.String _destinatariNif_) {
		this.destinatariNif = _destinatariNif_;
	};

	public int getEstat() {
		return(estat);
	};
	public void setEstat(int _estat_) {
		this.estat = _estat_;
	};

	public java.lang.Long getFitxerFirmatID() {
		return(fitxerFirmatID);
	};
	public void setFitxerFirmatID(java.lang.Long _fitxerFirmatID_) {
		this.fitxerFirmatID = _fitxerFirmatID_;
	};

	public java.lang.String getTipusDocumental() {
		return(tipusDocumental);
	};
	public void setTipusDocumental(java.lang.String _tipusDocumental_) {
		this.tipusDocumental = _tipusDocumental_;
	};

	public java.lang.String getIdiomaDoc() {
		return(idiomaDoc);
	};
	public void setIdiomaDoc(java.lang.String _idiomaDoc_) {
		this.idiomaDoc = _idiomaDoc_;
	};

	public java.lang.Long getInfoSignaturaID() {
		return(infoSignaturaID);
	};
	public void setInfoSignaturaID(java.lang.Long _infoSignaturaID_) {
		this.infoSignaturaID = _infoSignaturaID_;
	};

	public int getTipus() {
		return(tipus);
	};
	public void setTipus(int _tipus_) {
		this.tipus = _tipus_;
	};

	public java.lang.String getErrorMsg() {
		return(errorMsg);
	};
	public void setErrorMsg(java.lang.String _errorMsg_) {
		this.errorMsg = _errorMsg_;
	};

	public java.lang.String getErrorException() {
		return(errorException);
	};
	public void setErrorException(java.lang.String _errorException_) {
		this.errorException = _errorException_;
	};

	public java.lang.String getPeticioPortafirmes() {
		return(peticioPortafirmes);
	};
	public void setPeticioPortafirmes(java.lang.String _peticioPortafirmes_) {
		this.peticioPortafirmes = _peticioPortafirmes_;
	};

	public java.lang.String getReason() {
		return(reason);
	};
	public void setReason(java.lang.String _reason_) {
		this.reason = _reason_;
	};

	public java.lang.String getArxiuFuncionariUsername() {
		return(arxiuFuncionariUsername);
	};
	public void setArxiuFuncionariUsername(java.lang.String _arxiuFuncionariUsername_) {
		this.arxiuFuncionariUsername = _arxiuFuncionariUsername_;
	};

	public java.lang.String getArxiuParamFuncionariNom() {
		return(arxiuParamFuncionariNom);
	};
	public void setArxiuParamFuncionariNom(java.lang.String _arxiuParamFuncionariNom_) {
		this.arxiuParamFuncionariNom = _arxiuParamFuncionariNom_;
	};

	public java.lang.String getArxiuParamFuncionariNif() {
		return(arxiuParamFuncionariNif);
	};
	public void setArxiuParamFuncionariNif(java.lang.String _arxiuParamFuncionariNif_) {
		this.arxiuParamFuncionariNif = _arxiuParamFuncionariNif_;
	};

	public java.lang.String getArxiuParamFuncionariDir3() {
		return(arxiuParamFuncionariDir3);
	};
	public void setArxiuParamFuncionariDir3(java.lang.String _arxiuParamFuncionariDir3_) {
		this.arxiuParamFuncionariDir3 = _arxiuParamFuncionariDir3_;
	};

	public java.lang.String getArxiuReqParamDocEstatElabora() {
		return(arxiuReqParamDocEstatElabora);
	};
	public void setArxiuReqParamDocEstatElabora(java.lang.String _arxiuReqParamDocEstatElabora_) {
		this.arxiuReqParamDocEstatElabora = _arxiuReqParamDocEstatElabora_;
	};

	public java.lang.String getArxiuReqParamInteressats() {
		return(arxiuReqParamInteressats);
	};
	public void setArxiuReqParamInteressats(java.lang.String _arxiuReqParamInteressats_) {
		this.arxiuReqParamInteressats = _arxiuReqParamInteressats_;
	};

	public java.lang.String getArxiuReqParamCiutadaNif() {
		return(arxiuReqParamCiutadaNif);
	};
	public void setArxiuReqParamCiutadaNif(java.lang.String _arxiuReqParamCiutadaNif_) {
		this.arxiuReqParamCiutadaNif = _arxiuReqParamCiutadaNif_;
	};

	public java.lang.String getArxiuReqParamCiutadaNom() {
		return(arxiuReqParamCiutadaNom);
	};
	public void setArxiuReqParamCiutadaNom(java.lang.String _arxiuReqParamCiutadaNom_) {
		this.arxiuReqParamCiutadaNom = _arxiuReqParamCiutadaNom_;
	};

	public java.lang.String getArxiuReqParamOrgans() {
		return(arxiuReqParamOrgans);
	};
	public void setArxiuReqParamOrgans(java.lang.String _arxiuReqParamOrgans_) {
		this.arxiuReqParamOrgans = _arxiuReqParamOrgans_;
	};

	public java.lang.String getArxiuOptParamProcedimentCodi() {
		return(arxiuOptParamProcedimentCodi);
	};
	public void setArxiuOptParamProcedimentCodi(java.lang.String _arxiuOptParamProcedimentCodi_) {
		this.arxiuOptParamProcedimentCodi = _arxiuOptParamProcedimentCodi_;
	};

	public java.lang.String getArxiuOptParamProcedimentNom() {
		return(arxiuOptParamProcedimentNom);
	};
	public void setArxiuOptParamProcedimentNom(java.lang.String _arxiuOptParamProcedimentNom_) {
		this.arxiuOptParamProcedimentNom = _arxiuOptParamProcedimentNom_;
	};

	public java.lang.String getArxiuOptParamSerieDocumental() {
		return(arxiuOptParamSerieDocumental);
	};
	public void setArxiuOptParamSerieDocumental(java.lang.String _arxiuOptParamSerieDocumental_) {
		this.arxiuOptParamSerieDocumental = _arxiuOptParamSerieDocumental_;
	};

	public java.lang.String getArxiuOptParamExpedientId() {
		return(arxiuOptParamExpedientId);
	};
	public void setArxiuOptParamExpedientId(java.lang.String _arxiuOptParamExpedientId_) {
		this.arxiuOptParamExpedientId = _arxiuOptParamExpedientId_;
	};

	public java.lang.Integer getArxiuReqParamOrigen() {
		return(arxiuReqParamOrigen);
	};
	public void setArxiuReqParamOrigen(java.lang.Integer _arxiuReqParamOrigen_) {
		this.arxiuReqParamOrigen = _arxiuReqParamOrigen_;
	};

	public java.lang.Long getInfoArxiuID() {
		return(infoArxiuID);
	};
	public void setInfoArxiuID(java.lang.Long _infoArxiuID_) {
		this.infoArxiuID = _infoArxiuID_;
	};



  @Override
  public boolean equals(Object __obj) {
  boolean __result;
    if (__obj != null && __obj instanceof Peticio) {
      Peticio __instance = (Peticio)__obj;
      __result = true;
      __result = __result && (this.getPeticioID() == __instance.getPeticioID()) ;
    } else {
      __result = false;
    }
    return __result;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxerid", referencedColumnName ="fitxerID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_fitxer_fitxer_fk"))
    private FitxerJPA fitxer;

    public FitxerJPA getFitxer() {
    return this.fitxer;
  }

    public  void setFitxer(FitxerJPA fitxer) {
    this.fitxer = fitxer;
  }

// IMP Field:usuariid | Table: efi_usuari | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "solicitantid", referencedColumnName ="usuariID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_usuari_soli_fk"))
    private UsuariJPA usuari;

    public UsuariJPA getUsuari() {
    return this.usuari;
  }

    public  void setUsuari(UsuariJPA usuari) {
    this.usuari = usuari;
  }

// IMP Field:idiomaid | Table: efi_idioma | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idiomaid", referencedColumnName ="idiomaID", nullable = false, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_idioma_idiid_fk"))
    private IdiomaJPA idioma;

    public IdiomaJPA getIdioma() {
    return this.idioma;
  }

    public  void setIdioma(IdiomaJPA idioma) {
    this.idioma = idioma;
  }

// IMP Field:fitxerid | Table: efi_fitxer | Type: 1  

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fitxer_firmatid", referencedColumnName ="fitxerID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_fitxer_ffirm_fk"))
    private FitxerJPA fitxerFirmat;

    public FitxerJPA getFitxerFirmat() {
    return this.fitxerFirmat;
  }

    public  void setFitxerFirmat(FitxerJPA fitxerFirmat) {
    this.fitxerFirmat = fitxerFirmat;
  }

// IMP Field:infosignaturaid | Table: efi_infosignatura | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infosignaturaid", referencedColumnName ="infoSignaturaID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_infosign_fk"))
    private InfoSignaturaJPA infoSignatura;

    public InfoSignaturaJPA getInfoSignatura() {
    return this.infoSignatura;
  }

    public  void setInfoSignatura(InfoSignaturaJPA infoSignatura) {
    this.infoSignatura = infoSignatura;
  }

// IMP Field:infoarxiuid | Table: efi_infoarxiu | Type: 1  

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "infoarxiuid", referencedColumnName ="infoArxiuID", nullable = true, insertable=false, updatable=false, foreignKey=@ForeignKey(name="efi_peticio_infoarxiu_infoa_fk"))
    private InfoArxiuJPA infoArxiu;

    public InfoArxiuJPA getInfoArxiu() {
    return this.infoArxiu;
  }

    public  void setInfoArxiu(InfoArxiuJPA infoArxiu) {
    this.infoArxiu = infoArxiu;
  }


 // ---------------  STATIC METHODS ------------------
  public static PeticioJPA toJPA(Peticio __bean) {
    if (__bean == null) { return null;}
    PeticioJPA __tmp = new PeticioJPA();
    __tmp.setPeticioID(__bean.getPeticioID());
    __tmp.setNom(__bean.getNom());
    __tmp.setDataCreacio(__bean.getDataCreacio());
    __tmp.setDataFinal(__bean.getDataFinal());
    __tmp.setFitxerID(__bean.getFitxerID());
    __tmp.setSolicitantID(__bean.getSolicitantID());
    __tmp.setIdiomaID(__bean.getIdiomaID());
    __tmp.setDestinatariNif(__bean.getDestinatariNif());
    __tmp.setEstat(__bean.getEstat());
    __tmp.setFitxerFirmatID(__bean.getFitxerFirmatID());
    __tmp.setTipusDocumental(__bean.getTipusDocumental());
    __tmp.setIdiomaDoc(__bean.getIdiomaDoc());
    __tmp.setInfoSignaturaID(__bean.getInfoSignaturaID());
    __tmp.setTipus(__bean.getTipus());
    __tmp.setErrorMsg(__bean.getErrorMsg());
    __tmp.setErrorException(__bean.getErrorException());
    __tmp.setPeticioPortafirmes(__bean.getPeticioPortafirmes());
    __tmp.setReason(__bean.getReason());
    __tmp.setArxiuFuncionariUsername(__bean.getArxiuFuncionariUsername());
    __tmp.setArxiuParamFuncionariNom(__bean.getArxiuParamFuncionariNom());
    __tmp.setArxiuParamFuncionariNif(__bean.getArxiuParamFuncionariNif());
    __tmp.setArxiuParamFuncionariDir3(__bean.getArxiuParamFuncionariDir3());
    __tmp.setArxiuReqParamDocEstatElabora(__bean.getArxiuReqParamDocEstatElabora());
    __tmp.setArxiuReqParamInteressats(__bean.getArxiuReqParamInteressats());
    __tmp.setArxiuReqParamCiutadaNif(__bean.getArxiuReqParamCiutadaNif());
    __tmp.setArxiuReqParamCiutadaNom(__bean.getArxiuReqParamCiutadaNom());
    __tmp.setArxiuReqParamOrgans(__bean.getArxiuReqParamOrgans());
    __tmp.setArxiuOptParamProcedimentCodi(__bean.getArxiuOptParamProcedimentCodi());
    __tmp.setArxiuOptParamProcedimentNom(__bean.getArxiuOptParamProcedimentNom());
    __tmp.setArxiuOptParamSerieDocumental(__bean.getArxiuOptParamSerieDocumental());
    __tmp.setArxiuOptParamExpedientId(__bean.getArxiuOptParamExpedientId());
    __tmp.setArxiuReqParamOrigen(__bean.getArxiuReqParamOrigen());
    __tmp.setInfoArxiuID(__bean.getInfoArxiuID());
    // Fitxer
    __tmp.setFitxer(FitxerJPA.toJPA(__bean.getFitxer()));
    // Fitxer
    __tmp.setFitxerFirmat(FitxerJPA.toJPA(__bean.getFitxerFirmat()));
		return __tmp;
	}


  public static PeticioJPA copyJPA(PeticioJPA __jpa) {
    return copyJPA(__jpa,new java.util.HashMap<Object,Object>(), null);
  }

  static java.util.Set<PeticioJPA> copyJPA(java.util.Set<PeticioJPA> __jpaSet,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpaSet == null) { return null; }
    java.util.Set<PeticioJPA> __tmpSet = (java.util.Set<PeticioJPA>) __alreadyCopied.get(__jpaSet);
    if (__tmpSet != null) { return __tmpSet; };
    __tmpSet = new java.util.HashSet<PeticioJPA>(__jpaSet.size());
    __alreadyCopied.put(__jpaSet, __tmpSet);
    for (PeticioJPA __jpa : __jpaSet) {
      __tmpSet.add(copyJPA(__jpa, __alreadyCopied, origenJPA));
    }
    return __tmpSet;
  }

  static PeticioJPA copyJPA(PeticioJPA __jpa,
    java.util.Map<Object,Object> __alreadyCopied, String origenJPA) {
    if (__jpa == null) { return null; }
    PeticioJPA __tmp = (PeticioJPA) __alreadyCopied.get(__jpa);
    if (__tmp != null) { return __tmp; };
    __tmp = toJPA(__jpa);
    __alreadyCopied.put(__jpa, __tmp);
    // Copia de beans complexes (EXP)
    // Copia de beans complexes (IMP)
    if(!"IdiomaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.idioma) || org.hibernate.Hibernate.isInitialized(__jpa.getIdioma()) ) ) {
      __tmp.setIdioma(IdiomaJPA.copyJPA(__jpa.getIdioma(), __alreadyCopied,"PeticioJPA"));
    }
    if(!"InfoArxiuJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.infoArxiu) || org.hibernate.Hibernate.isInitialized(__jpa.getInfoArxiu()) ) ) {
      __tmp.setInfoArxiu(InfoArxiuJPA.copyJPA(__jpa.getInfoArxiu(), __alreadyCopied,"PeticioJPA"));
    }
    if(!"UsuariJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.usuari) || org.hibernate.Hibernate.isInitialized(__jpa.getUsuari()) ) ) {
      __tmp.setUsuari(UsuariJPA.copyJPA(__jpa.getUsuari(), __alreadyCopied,"PeticioJPA"));
    }
    if(!"InfoSignaturaJPA".equals(origenJPA) && 
       (!org.fundaciobit.genapp.common.utils.Utils.isEmpty(__jpa.infoSignatura) || org.hibernate.Hibernate.isInitialized(__jpa.getInfoSignatura()) ) ) {
      __tmp.setInfoSignatura(InfoSignaturaJPA.copyJPA(__jpa.getInfoSignatura(), __alreadyCopied,"PeticioJPA"));
    }

    return __tmp;
  }




}
