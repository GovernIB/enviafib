package es.caib.enviafib.persistence;

import es.caib.enviafib.model.*;
import es.caib.enviafib.model.dao.*;
import javax.persistence.EntityManager;

public final class EnviaFIBJPADaoManagers implements IEnviaFIBDaoManagers{

   private final FitxerJPAManager efi_fitxer;
   private final GrupJPAManager efi_grup;
   private final GrupUsuariJPAManager efi_grupusuari;
   private final IdiomaJPAManager efi_idioma;
   private final InfoArxiuJPAManager efi_infoarxiu;
   private final InfoSignaturaJPAManager efi_infosignatura;
   private final MenuJPAManager efi_menu;
   private final OrganitzacioJPAManager efi_organitzacio;
   private final PeticioJPAManager efi_peticio;
   private final PluginJPAManager efi_plugin;
   private final SerieDocumentalJPAManager efi_seriedocumental;
   private final TraduccioJPAManager efi_traduccio;
   private final UsuariJPAManager efi_usuari;

  public  EnviaFIBJPADaoManagers(EntityManager __em) {
    this.efi_fitxer = new FitxerJPAManager(__em);
    this.efi_grup = new GrupJPAManager(__em);
    this.efi_grupusuari = new GrupUsuariJPAManager(__em);
    this.efi_idioma = new IdiomaJPAManager(__em);
    this.efi_infoarxiu = new InfoArxiuJPAManager(__em);
    this.efi_infosignatura = new InfoSignaturaJPAManager(__em);
    this.efi_menu = new MenuJPAManager(__em);
    this.efi_organitzacio = new OrganitzacioJPAManager(__em);
    this.efi_peticio = new PeticioJPAManager(__em);
    this.efi_plugin = new PluginJPAManager(__em);
    this.efi_seriedocumental = new SerieDocumentalJPAManager(__em);
    this.efi_traduccio = new TraduccioJPAManager(__em);
    this.efi_usuari = new UsuariJPAManager(__em);
  }

    public IFitxerManager getFitxerManager() {
        return this.efi_fitxer;
    };

    public IGrupManager getGrupManager() {
        return this.efi_grup;
    };

    public IGrupUsuariManager getGrupUsuariManager() {
        return this.efi_grupusuari;
    };

    public IIdiomaManager getIdiomaManager() {
        return this.efi_idioma;
    };

    public IInfoArxiuManager getInfoArxiuManager() {
        return this.efi_infoarxiu;
    };

    public IInfoSignaturaManager getInfoSignaturaManager() {
        return this.efi_infosignatura;
    };

    public IMenuManager getMenuManager() {
        return this.efi_menu;
    };

    public IOrganitzacioManager getOrganitzacioManager() {
        return this.efi_organitzacio;
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