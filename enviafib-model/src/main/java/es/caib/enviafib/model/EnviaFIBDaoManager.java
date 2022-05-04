package es.caib.enviafib.model;

public class EnviaFIBDaoManager {
  
  private static IEnviaFIBDaoManagers instance = null;
  
  public static void setDaoManagers(IEnviaFIBDaoManagers managers) {
    instance = managers;
  }
  
  public static IEnviaFIBDaoManagers getDaoManagers() throws Exception {
    if(instance == null) {
      throw new Exception("Ha de inicialitzar el sistema de Managers cridant "
          + " al mètode EnviaFIBDaoManager.setDaoManagers(...)");
    }
    return instance;
  }
  
}
