
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Faq;


public class FaqBean implements Faq {



	long faqID;// PK
	java.lang.String enunciat_es;
	java.lang.String enunciat_ca;
	java.lang.String resposta_es;
	java.lang.String resposta_ca;
	java.lang.Long ordre;
	java.lang.Long fitxer1ID;
	java.lang.Long fitxer2ID;
	java.lang.Long fitxer3ID;


  /** Constructor Buit */
  public FaqBean() {
  }

  /** Constructor amb tots els camps  */
  public FaqBean(long faqID , java.lang.String enunciat_es , java.lang.String enunciat_ca , java.lang.String resposta_es , java.lang.String resposta_ca , java.lang.Long ordre , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.faqID=faqID;
    this.enunciat_es=enunciat_es;
    this.enunciat_ca=enunciat_ca;
    this.resposta_es=resposta_es;
    this.resposta_ca=resposta_ca;
    this.ordre=ordre;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor sense valors autoincrementals */
  public FaqBean(java.lang.String enunciat_es , java.lang.String enunciat_ca , java.lang.String resposta_es , java.lang.String resposta_ca , java.lang.Long ordre , java.lang.Long fitxer1ID , java.lang.Long fitxer2ID , java.lang.Long fitxer3ID) {
    this.enunciat_es=enunciat_es;
    this.enunciat_ca=enunciat_ca;
    this.resposta_es=resposta_es;
    this.resposta_ca=resposta_ca;
    this.ordre=ordre;
    this.fitxer1ID=fitxer1ID;
    this.fitxer2ID=fitxer2ID;
    this.fitxer3ID=fitxer3ID;
}
  /** Constructor dels valors Not Null */
  public FaqBean(long faqID) {
    this.faqID=faqID;
}
  public FaqBean(Faq __bean) {
    this.setFaqID(__bean.getFaqID());
    this.setEnunciat_es(__bean.getEnunciat_es());
    this.setEnunciat_ca(__bean.getEnunciat_ca());
    this.setResposta_es(__bean.getResposta_es());
    this.setResposta_ca(__bean.getResposta_ca());
    this.setOrdre(__bean.getOrdre());
    this.setFitxer1ID(__bean.getFitxer1ID());
    this.setFitxer2ID(__bean.getFitxer2ID());
    this.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    this.setFitxer1(FitxerBean.toBean(__bean.getFitxer1()));
    // Fitxer
    this.setFitxer2(FitxerBean.toBean(__bean.getFitxer2()));
    // Fitxer
    this.setFitxer3(FitxerBean.toBean(__bean.getFitxer3()));
	}

	public long getFaqID() {
		return(faqID);
	};
	public void setFaqID(long _faqID_) {
		this.faqID = _faqID_;
	};

	public java.lang.String getEnunciat_es() {
		return(enunciat_es);
	};
	public void setEnunciat_es(java.lang.String _enunciat_es_) {
		this.enunciat_es = _enunciat_es_;
	};

	public java.lang.String getEnunciat_ca() {
		return(enunciat_ca);
	};
	public void setEnunciat_ca(java.lang.String _enunciat_ca_) {
		this.enunciat_ca = _enunciat_ca_;
	};

	public java.lang.String getResposta_es() {
		return(resposta_es);
	};
	public void setResposta_es(java.lang.String _resposta_es_) {
		this.resposta_es = _resposta_es_;
	};

	public java.lang.String getResposta_ca() {
		return(resposta_ca);
	};
	public void setResposta_ca(java.lang.String _resposta_ca_) {
		this.resposta_ca = _resposta_ca_;
	};

	public java.lang.Long getOrdre() {
		return(ordre);
	};
	public void setOrdre(java.lang.Long _ordre_) {
		this.ordre = _ordre_;
	};

	public java.lang.Long getFitxer1ID() {
		return(fitxer1ID);
	};
	public void setFitxer1ID(java.lang.Long _fitxer1ID_) {
		this.fitxer1ID = _fitxer1ID_;
	};

	public java.lang.Long getFitxer2ID() {
		return(fitxer2ID);
	};
	public void setFitxer2ID(java.lang.Long _fitxer2ID_) {
		this.fitxer2ID = _fitxer2ID_;
	};

	public java.lang.Long getFitxer3ID() {
		return(fitxer3ID);
	};
	public void setFitxer3ID(java.lang.Long _fitxer3ID_) {
		this.fitxer3ID = _fitxer3ID_;
	};



  // ======================================

  public static FaqBean toBean(Faq __bean) {
    if (__bean == null) { return null;}
    FaqBean __tmp = new FaqBean();
    __tmp.setFaqID(__bean.getFaqID());
    __tmp.setEnunciat_es(__bean.getEnunciat_es());
    __tmp.setEnunciat_ca(__bean.getEnunciat_ca());
    __tmp.setResposta_es(__bean.getResposta_es());
    __tmp.setResposta_ca(__bean.getResposta_ca());
    __tmp.setOrdre(__bean.getOrdre());
    __tmp.setFitxer1ID(__bean.getFitxer1ID());
    __tmp.setFitxer2ID(__bean.getFitxer2ID());
    __tmp.setFitxer3ID(__bean.getFitxer3ID());
    // Fitxer
    __tmp.setFitxer1(FitxerBean.toBean(__bean.getFitxer1()));
    // Fitxer
    __tmp.setFitxer2(FitxerBean.toBean(__bean.getFitxer2()));
    // Fitxer
    __tmp.setFitxer3(FitxerBean.toBean(__bean.getFitxer3()));
		return __tmp;
	}

  protected FitxerBean fitxer1;
  public FitxerBean getFitxer1() {
    return fitxer1;
  }
  public void setFitxer1(FitxerBean __field) {
    this. fitxer1 = __field;
  }
  protected FitxerBean fitxer2;
  public FitxerBean getFitxer2() {
    return fitxer2;
  }
  public void setFitxer2(FitxerBean __field) {
    this. fitxer2 = __field;
  }
  protected FitxerBean fitxer3;
  public FitxerBean getFitxer3() {
    return fitxer3;
  }
  public void setFitxer3(FitxerBean __field) {
    this. fitxer3 = __field;
  }


}
