package es.caib.enviafib.persistence;

import es.caib.enviafib.model.*;
import es.caib.enviafib.model.dao.*;
import javax.persistence.EntityManager;

public final class EnviaFIBJPADaoManagers implements IEnviaFIBDaoManagers{

   private final FitxerJPAManager efi_fitxer;
   private final IdiomaJPAManager efi_idioma;
   private final PeticioJPAManager efi_peticio;
   private final PluginJPAManager efi_plugin;
   private final SerieDocumentalJPAManager efi_seriedocumental;
   private final TraduccioJPAManager efi_traduccio;
   private final UsuariJPAManager efi_usuari;

  public  EnviaFIBJPADaoManagers(EntityManager __em) {
    this.efi_fitxer = new FitxerJPAManager(__em);
    this.efi_idioma = new IdiomaJPAManager(__em);
    this.efi_peticio = new PeticioJPAManager(__em);
    this.efi_plugin = new PluginJPAManager(__em);
    this.efi_seriedocumental = new SerieDocumentalJPAManager(__em);
    this.efi_traduccio = new TraduccioJPAManager(__em);
    this.efi_usuari = new UsuariJPAManager(__em);
  }

    public IFitxerManager getFitxerManager() {
        return this.efi_fitxer;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.efi_idioma;
    };

    public IPeticioManager getPeticioManager() {
        return this.efi_peticio;
    };

    public IPluginManager getPluginManager() {
        return this.efi_plugin;
    };

    public ISerieDocumentalManager getSerieDocumentalManager() {
        return this.efi_seriedocumental;
    };

    public ITraduccioManager getTraduccioManager() {
        return this.efi_traduccio;
    };

    public IUsuariManager getUsuariManager() {
        return this.efi_usuari;
    };


}