package es.caib.enviafib.ejb.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

import org.fundaciobit.genapp.common.i18n.I18NException;

import es.caib.enviafib.logic.utils.EmailUtil;


/**
 * 
 * @author fbosch
 *
 */

public class CridaEmail {

    public static void main(String[] args) throws FileNotFoundException, I18NException {
        provaEmail();
    }

    public static void provaEmail() throws I18NException, FileNotFoundException {

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(new File("email.properties")));

            String subject = "Email de test";
            String message = "Missatge de test de EnviaFIB";
            
            EmailUtil.postMail(subject, message, false, message, prop.getProperty("sender"), prop.getProperty("destinatari"));

        }catch(FileNotFoundException ex) {
            String msg = "Error de TEST en el servei de email. Comprova que el fitxer de propietats /enviafib/enviafib-ejb/email.properties existeix i te permisos de lectura.";
            throw new FileNotFoundException(msg);
        }catch (Exception e) {
            String msg = "Error de TEST en el servei de email. Pareix que hi ha hagut algún error en l'enviament del email. "
                    + "Comprova que les dades de configuració de l'arxiu /enviafib/enviafib-ejb/email.properties son correctes.";
            throw new I18NException(msg);
            
        }
//      asientoApi.obtenerAsientoRegistral("A04003003","INNO-E-352/2019", "INNO", 1, 0));
    }

}