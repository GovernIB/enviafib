package es.caib.enviafib.model;

import es.caib.enviafib.model.dao.*;

public interface IEnviaFIBDaoManagers {
	public IFitxerManager getFitxerManager();
	public IIdiomaManager getIdiomaManager();
	public IPeticioManager getPeticioManager();
	public IPluginManager getPluginManager();
	public ITraduccioManager getTraduccioManager();
	public IUsuariManager getUsuariManager();

}