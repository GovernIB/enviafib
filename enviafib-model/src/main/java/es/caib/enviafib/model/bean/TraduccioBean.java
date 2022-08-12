
package es.caib.enviafib.model.bean;

import es.caib.enviafib.model.entity.Traduccio;


public class TraduccioBean implements Traduccio {



	long traduccioID;// PK


  /** Constructor Buit */
  public TraduccioBean() {
  }

  /** Constructor amb tots els camps  */
  public TraduccioBean(long traduccioID) {
    this.traduccioID=traduccioID;
}
  public TraduccioBean(Traduccio __bean) {
    this.setTraduccioID(__bean.getTraduccioID());
	}

	public long getTraduccioID() {
		return(traduccioID);
	};
	public void setTraduccioID(long _traduccioID_) {
		this.traduccioID = _traduccioID_;
	};



  // ======================================

  public static TraduccioBean toBean(Traduccio __bean) {
    if (__bean == null) { return null;}
    TraduccioBean __tmp = new TraduccioBean();
    __tmp.setTraduccioID(__bean.getTraduccioID());
		return __tmp;
	}



}
