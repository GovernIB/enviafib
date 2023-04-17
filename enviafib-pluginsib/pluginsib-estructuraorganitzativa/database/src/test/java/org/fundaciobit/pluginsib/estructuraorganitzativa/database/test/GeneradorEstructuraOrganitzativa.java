package org.fundaciobit.pluginsib.estructuraorganitzativa.database.test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Properties;

import org.fundaciobit.pluginsib.core.utils.FileUtils;
import org.fundaciobit.pluginsib.estructuraorganitzativa.database.DatabaseEstructuraOrganitzativaPlugin;
import org.fundaciobit.pluginsib.userinformation.IUserInformationPlugin;
import org.fundaciobit.pluginsib.userinformation.UserInfo;

/**
 * 
 * 
 * @author anadal
 *
 */
public class GeneradorEstructuraOrganitzativa {

    public static final String PACKAGE_BASE = "es.caib.enviafib.";

    public static final String EOPB = PACKAGE_BASE
            + DatabaseEstructuraOrganitzativaPlugin.DATABASE_ESTRUCTURAORGANITZATIVA_PROPERTY_BASE;

    public static void main(String[] args) {

        try {

            // Informació de generació:

            Properties propGenerals = new Properties();
            {
                byte[] data = FileUtils.readFromFile(new File("generador.properties"));
                propGenerals.load(new ByteArrayInputStream(data));
            }

            DatabaseEstructuraOrganitzativaPlugin plugin;
            Properties propDatabase = new Properties();
            {
                byte[] data = FileUtils.readFromFile(
                        new File(propGenerals.getProperty("plugindatabaseestructuraorganitzativa.properties")));

                propDatabase.load(new ByteArrayInputStream(data));

                plugin = new DatabaseEstructuraOrganitzativaPlugin(PACKAGE_BASE, propDatabase);
            }

            // RolCapArea
            String ROL_CAP_AREA = propDatabase.getProperty(EOPB + "userinformation.rolcaparea");
            // RolCapDepartament
            String ROL_CAP_DEPARTAMENT = propDatabase.getProperty(EOPB + "userinformation.rolcapdepartament");
            // RolSecretari
            String ROL_SECRETARI = propDatabase.getProperty(EOPB + "userinformation.rolsecretari");

            GeneradorSql generador;
            {

                String valorColumn = propDatabase.getProperty(EOPB + "columns.valor");
                String tipusColumn = propDatabase.getProperty(EOPB + "columns.tipus");
                String departmentCodeColumn = propDatabase.getProperty(EOPB + "columns.departamentcodi");
                String areaCodeColumn = propDatabase.getProperty(EOPB + "columns.areacodi");
                String tableName = propDatabase.getProperty(EOPB + "tablename");
                generador = new GeneradorSql(tableName, areaCodeColumn, departmentCodeColumn, tipusColumn, valorColumn);
            }

            {
            generador.addComment(" ==========================================");
            generador.addComment("/** =  ENTITAT - ORGANITZACIÓ - EMPRESA = */");
            generador.addComment(" ==========================================\n");
            

                String[][] valors = {
                        { "getGerentPresidentName()", DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_NAME,
                                propGenerals.getProperty("gerentpresident.name"), null },
                        { "getGerentPresidentUsername()", DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_USERNAME,
                                propGenerals.getProperty("gerentpresident.username"), null },
                        { "getNameOrganitzacioEmpresa(ca)", DatabaseEstructuraOrganitzativaPlugin.TIPUS_NAME,
                                propGenerals.getProperty("organitzacioempresa.name.ca"), "ca" },
                        { "getNameOrganitzacioEmpresa(es)", DatabaseEstructuraOrganitzativaPlugin.TIPUS_NAME,
                                propGenerals.getProperty("organitzacioempresa.name.es"), "es" },
                        { "getDir3OrganitzacioEmpresa()", DatabaseEstructuraOrganitzativaPlugin.TIPUS_DIR3,
                                propGenerals.getProperty("organitzacioempresa.dir3"), null },
                        { "getNifOrganitzacioEmpresa()", DatabaseEstructuraOrganitzativaPlugin.TIPUS_NIF,
                                propGenerals.getProperty("organitzacioempresa.nif"), null } };

                for (int i = 0; i < valors.length; i++) {
                    String[] valor = valors[i];
                    generador.addComment(valor[0]);
                    generador.generaSql(valor[1], valor[2], valor[3]);
                }

            }

            IUserInformationPlugin pluginUserInformation = plugin.getPluginUserInformation();

            {

                generador.addComment(" ==========================================");
                generador.addComment("        /** =  ÀREA - CONSELLERIA = */");
                generador.addComment(" ==========================================\n");

                // TODO XYZ ZZZ  Falta
                // getNameAreaConselleria(String username, String lang) =>
                // getDir3AreaConselleria(String username) =>
                // getCodeAreaConselleria(String username) =>

                generador.addComment(
                        "getCapAreaConsellerUsername(String username) i getCapAreaConsellerName(String username)");

                String[] consellers = pluginUserInformation.getUsernamesByRol(ROL_CAP_AREA);
                if (consellers == null) {
                    generador.addComment(" Consellers es NULL");

                } else {
                    //System.out.println(" #Consellers = " + consellers.length);
                    for (int i = 0; i < consellers.length; i++) {

                        UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(consellers[i]);

                        //System.out.println(" - " + consellers[i] +  " (" + userInfo.getCompanyArea() + " | " + userInfo.getCompanyDepartment() + ")");

                        generador.generaSql(userInfo.getCompanyArea(), null,
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_USERNAME, userInfo.getUsername(), null);
                        generador.generaSql(userInfo.getCompanyArea(), null,
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_NAME, userInfo.getFullName(), null);
                    }
                }
            }

            {
                generador.addComment(" ==========================================");
                generador.addComment(" /** =   DEPARTAMENT - DIRECCIÓ GENERAL = */");
                generador.addComment(" ==========================================\n");
                // TODO XYZ ZZZ  Falta
                // getNameDepartamentDireccioGeneral(username, lang)
                // getDir3DepartamentDireccioGeneral(String username) =>
                // getCodeDepartamentDireccioGeneral(String username)

                generador.addComment(
                        "getCapDepartamentDirectorGeneralUsername(username) i getCapDepartamentDirectorGeneralName(username)");

                // Carregar tots els Directors Generals ( Cap de Departaments)

                String[] directorsGenerals = pluginUserInformation.getUsernamesByRol(ROL_CAP_DEPARTAMENT);
                if (directorsGenerals == null) {
                    generador.addComment(" directorsGenerals es NULL");
                } else {
                    //System.out.println(" #directorsGenerals = " + directorsGenerals.length);
                    for (int i = 0; i < directorsGenerals.length; i++) {

                        UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(directorsGenerals[i]);

                        generador.generaSql(userInfo.getCompanyArea(), userInfo.getCompanyDepartment(),
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_USERNAME, userInfo.getUsername(), null);
                        generador.generaSql(userInfo.getCompanyArea(), userInfo.getCompanyDepartment(),
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_CAP_NAME, userInfo.getFullName(), null);
                    }
                }
            }

            /** =  DADES GENERALS = */
            {
                generador.addComment(" ==========================================");
                generador.addComment(" /** ==========   SECRETARIS =========== */");
                generador.addComment(" ==========================================\n");

                generador.addComment("getSecretariUsername(String username) i getSecretariName(String username)");

                String[] directorsGenerals = pluginUserInformation.getUsernamesByRol(ROL_SECRETARI);
                if (directorsGenerals == null) {
                    generador.addComment(" directorsGenerals es NULL");
                } else {
                    //System.out.println(" #directorsGenerals = " + directorsGenerals.length);
                    for (int i = 0; i < directorsGenerals.length; i++) {

                        UserInfo userInfo = pluginUserInformation.getUserInfoByUserName(directorsGenerals[i]);

                        generador.generaSql(userInfo.getCompanyArea(), userInfo.getCompanyDepartment(),
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_SECRETARI_USERNAME, userInfo.getUsername(), null);
                        generador.generaSql(userInfo.getCompanyArea(), userInfo.getCompanyDepartment(),
                                DatabaseEstructuraOrganitzativaPlugin.TIPUS_SECRETARI_NAME, userInfo.getFullName(), null);
                    }
                }
            }

            System.out.println(generador.toString());

            //provarTotsMetodes(plugin, "anadal", "es");

            //provarIdiomes(plugin);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static class GeneradorSql {
        protected String tableName;
        protected String areaCodeColumn;
        protected String departmentCodeColumn;
        protected String tipusColumn;
        protected String valorColumn;

        protected StringBuilder builder = new StringBuilder();

        public GeneradorSql(String tableName, String areaCodeColumn, String departmentCodeColumn, String tipusColumn,
                String valorColumn) {
            super();
            this.tableName = tableName;
            this.areaCodeColumn = areaCodeColumn;
            this.departmentCodeColumn = departmentCodeColumn;
            this.tipusColumn = tipusColumn;
            this.valorColumn = valorColumn;
        }

        public void addComment(String comment) {
            builder.append("-- ").append(comment).append("\n");
        }

        public void generaSql(String tipus, String valor, String lang) {
            final String area = null;
            final String departament = null;
            generaSql(area, departament, tipus, valor, lang);
        }

        public void generaSql(String area, String departament, String tipus, String valor, String lang) {
            if (lang == null) {
                lang = "";
            } else {
                lang = "." + lang;
            }

            area = posaCometes(area);
            departament = posaCometes(departament);
            tipus = posaCometes(tipus + lang);
            valor = posaCometes(valor);

            builder.append("INSERT INTO " + this.tableName + "(" + this.areaCodeColumn + ", "
                    + this.departmentCodeColumn + ", " + this.tipusColumn + ", " + this.valorColumn + ")\n"
                    + "    VALUES (" + area + ", " + departament + " , " + tipus + ", " + valor + ");\n\n");

        }

        protected String posaCometes(String str) {

            return (str == null) ? str : ("'" + str.replace("'", "''") + "'");
        }

        @Override
        public String toString() {
            return this.builder.toString();
        }

    }

}
