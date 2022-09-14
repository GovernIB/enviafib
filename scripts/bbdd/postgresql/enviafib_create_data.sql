

INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('ca', 'Català', 0, true);
INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('es', 'Castellano', 1, true);
INSERT INTO efi_idioma(idiomaid, nom, ordre, suportat) VALUES ('en', 'English', 2, false);
    
    

---
--- 04/08/2022 - Gestió de PLugins de Estructura Organitzativa #142
---

INSERT INTO efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (1, 'org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin', '# Fundaciobit

# Classe org.fundaciobit.pluginsib.estructuraorganitzativa.mock.MockEstructuraOrganitzativaPlugin

es.caib.enviafib.pluginsib.estructuraorganizativa.mock.codidir3byusername=A04027052
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.gerentpresident=aroig
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.nomareaconselleriabyusername.ca=Innovació
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.nomareaconselleriabyusername.es=Innovación
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.capareaconsellerbyusername=jtramullas
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.nomdepartamentdirecciogeneralbyusername.es=Govern Digital
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.nomdepartamentdirecciogeneralbyusername.ca=Gobierno Digital
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.capdepartamentdirectorgeneralbyusername=<#switch username><#case "anadal"><#case "ptrias"><#case "fbosch"><#case "bverges">atrobat<#break><#case "jsastre">xtous<#break><#default>ellado</#switch>
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.secretaribyusername=
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.encarregatcompresbyusername=jfernandez
es.caib.enviafib.pluginsib.estructuraorganizativa.mock.recursoshumansbyusername=rdiaz', true, 1, 'Plugin Mock', 'Plugin Mock que simula entitat Fundació Bit');



---
--- 16/08/2022 Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---

INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi)
VALUES (10, 'S0001', NULL ,'Subvenciones empleo', 'organo1_PRO_123456789');



---
--- 17/08/2022 Fer que Plugin d'Arxiu llegeixi properties i classe de BBDD enlloc de Propietats de sistema #169
---
INSERT INTO public.efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (10, 'es.caib.plugins.arxiu.caib.ArxiuPluginCaib', 
'# PLUGIN ARXIU - CAIB
es.caib.enviafib.plugin.arxiu.caib.base.url=https://esbse.caib.es:4430/esb
es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi=Tests
es.caib.enviafib.plugin.arxiu.caib.usuari=username
es.caib.enviafib.plugin.arxiu.caib.contrasenya=password
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url=https://se.caib.es/concsv/rest/printable/uuid
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari=username
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya=password

# No funciona
es.caib.enviafib.plugin.arxiu.caib.original_file_url_EL=https://se.caib.es/concsv/rest/original/${csv}

# No funciona
es.caib.enviafib.plugin.arxiu.caib.printable_file_url_EL=https://se.caib.es/concsv/rest/printable/${csv}

# No funciona
es.caib.enviafib.plugin.arxiu.caib.eni_file_url_EL=https://se.caib.es/concsv/enidoc/${csv}
es.caib.enviafib.plugin.arxiu.caib.csv_validation_web_EL=http://se.caib.es/concsv/${csv}

# Valor Desconegut
es.caib.enviafib.plugin.arxiu.caib.csv.definicio=https://se.caib.es/def_csv_gen.pdf
es.caib.enviafib.plugin.arxiu.caib.validation_file_url_EL=https://se.caib.es/concsv/hash/${csv}'
, true, 2, 'Plugin Arxiu Prova', 'Plugin Arxiu Prova');

