package es.caib.enviafib.ejb.utils;

import java.util.Arrays;
import java.util.Set;

import javax.transaction.Status;
import javax.transaction.Synchronization;

import org.apache.log4j.Logger;
import org.fundaciobit.genapp.common.filesystem.FileSystemManager;

public class CleanFilesSynchronization implements Synchronization {

    protected final Logger log = Logger.getLogger(this.getClass());

    protected final Set<Long> files;

    public CleanFilesSynchronization(Set<Long> filesToDelete) {
        this.files = filesToDelete;
    }

    @Override
    public void beforeCompletion() {
    }

    @Override
    public void afterCompletion(int status) {

        if (status == Status.STATUS_COMMITTED) {
            if (!FileSystemManager.eliminarArxius(files)) {
                log.error("No s'ha pogut esborrar alguns dels següents fitxers: " + Arrays.toString(files.toArray()));
            }else {
                log.info("S'ha pogut esborrar tot correctament");
            }
        } else {
            log.info("NO S'HA FET COMMIT!! -> ROLLBACK");
        }
    }
};
