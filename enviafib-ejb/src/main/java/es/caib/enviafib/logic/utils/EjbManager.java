package es.caib.enviafib.logic.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.i18n.I18NArgumentString;
import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.ejb.IdiomaService;
import es.caib.enviafib.ejb.UsuariService;

/**
 * 
 * @author anadal
 * 
 */
public final class EjbManager {

	protected static final Logger log = Logger.getLogger(EjbManager.class);

	protected static UsuariService usuariEjb;

	private static void throwNewI18NException(Throwable e, String name) throws I18NException {
		throw new I18NException(e, "error.unknown",
				new I18NArgumentString("No puc instanciar " + name + ": " + e.getMessage()));
	}

	public static UsuariService getUsuariEJB() throws I18NException {

		if (usuariEjb == null) {
			try {
				usuariEjb = (UsuariService) new InitialContext().lookup(UsuariService.JNDI_NAME);
			} catch (Throwable e) {
				throwNewI18NException(e, "UsuariPersonaService");
			}
		}
		return usuariEjb;
	}
	
	
	
	protected static IdiomaService idiomaEjb;

    public static IdiomaService getIdiomaEJB() throws I18NException {

        if (idiomaEjb == null) {
            try {
                idiomaEjb = (IdiomaService) new InitialContext().lookup(IdiomaService.JNDI_NAME);
            } catch (Throwable e) {
                throwNewI18NException(e, "IdiomaService");
            }
        }
        return idiomaEjb;
    }

    
    public static Map<Class<?>, Object> servicesCache = new HashMap<Class<?>, Object>();
    
    public static <E> E getEJB(Class<E> classe) throws I18NException {

        E ejb = (E)servicesCache.get(classe); 
        
        if (ejb == null) {
            try {
                
                Field field = classe.getDeclaredField("JNDI_NAME");
                if (field == null) {
                    throw new Exception("No es troba la constant JNDI_NAME en la classe " + classe.getName());
                }
                
                idiomaEjb = (IdiomaService) new InitialContext().lookup((String)field.get(null));
                
            } catch (Throwable e) {
                throwNewI18NException(e, classe.getName());
            }
        }
        return ejb;
    }
    
    
}