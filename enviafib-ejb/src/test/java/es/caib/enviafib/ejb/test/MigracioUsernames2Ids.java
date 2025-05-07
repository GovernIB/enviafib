package es.caib.enviafib.ejb.test;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * 
 * @author anadal
 * 7 may 2025 10:37:05
 */
public class MigracioUsernames2Ids {

    public static void main(String[] args) {
        try {

            String entorn = "pre";

            File f = new File(entorn + ".csv");
            String sufix = entorn.equals("pro") ? "" : ("_" + entorn);

            List<String> allLines = Files.readAllLines(Paths.get(f.getAbsolutePath()));

            StringBuilder sb = new StringBuilder();

            for (String line : allLines) {
                String[] parts = line.split("\t");
                String id = parts[0];
                String username = parts[1];

                sb.append("UPDATE pfi_plantillafluxdefirmes SET descripcio = REPLACE(descripcio, '{owner=" + username
                        + "}','{owner=" + id + "}') WHERE usuariaplicacioid='$enviafib_portafib" + sufix
                        + "' AND descripcio LIKE '%{owner=" + username + "}%';\n");

            }

            File out = new File(entorn + ".sql");
            FileOutputStream fout = new FileOutputStream(out);
            fout.write(sb.toString().getBytes());
            fout.flush();
            fout.close();

            System.out.println(sb.toString());

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }
}
