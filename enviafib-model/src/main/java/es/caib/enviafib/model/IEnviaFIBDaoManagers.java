package es.caib.enviafib.model;

import es.caib.enviafib.model.dao.*;

public interface IEnviaFIBDaoManagers {
	public IFitxerManager getFitxerManager();
	public IGrupManager getGrupManager();
	public IGrupUsuariManager getGrupUsuariManager();
	public IIdiomaManager getIdiomaManager();
	public IInfoArxiuManager getInfoArxiuManager();
	public IInfoSignaturaManager getInfoSignaturaManager();
	public IMenuManager getMenuManager();
	public IPeticioManager getPeticioManager();
	public IPluginManager getPluginManager();
	public ISerieDocumentalManager getSerieDocumentalManager();
	public ITraduccioManager getTraduccioManager();
	public IUsuariManager getUsuariManager();

}