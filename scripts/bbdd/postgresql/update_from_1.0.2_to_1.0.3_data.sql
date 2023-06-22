---
--- 16/08/2022 Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---

INSERT INTO efi_seriedocumental
(seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi)
VALUES
(10, 'S0001', NULL ,'Subvenciones empleo', 'organo1_PRO_123456789');



---
--- 17/08/2022 Fer que Plugin d'Arxiu llegeixi properties i classe de BBDD enlloc de Propietats de sistema #169
---
INSERT INTO public.efi_plugin (pluginid, classe, properties, actiu, tipus, nom, descripcio) VALUES (10, 'es.caib.plugins.arxiu.caib.ArxiuPluginCaib', 
'# PLUGIN ARXIU - CAIB
es.caib.enviafib.plugin.arxiu.caib.base.url=[=SP["es.caib.enviafib.plugin.arxiu.caib.base.url"]]
es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi=[=SP["es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi"]]
es.caib.enviafib.plugin.arxiu.caib.usuari=[=SP["es.caib.enviafib.plugin.arxiu.caib.usuari"]]
es.caib.enviafib.plugin.arxiu.caib.contrasenya=[=SP["es.caib.enviafib.plugin.arxiu.caib.contrasenya"]]

es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url"]]
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari"]]
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya=[=SP["es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya"]]

es.caib.enviafib.plugin.arxiu.caib.original_file_url_EL=https://se.caib.es/concsvapi/interna/original/${csv}
es.caib.enviafib.plugin.arxiu.caib.printable_file_url_EL=https://se.caib.es/concsvapi/interna/printable/${csv}
es.caib.enviafib.plugin.arxiu.caib.eni_file_url_EL=https://se.caib.es/concsvapi/enidoc/${csv}
es.caib.enviafib.plugin.arxiu.caib.csv_validation_web_EL=https://se.caib.es/concsvfront/

# Valor a definir per cada entiat
es.caib.enviafib.plugin.arxiu.caib.csv.definicio=https://se.caib.es/def_csv_gen.pdf
es.caib.enviafib.plugin.arxiu.caib.validation_file_url_EL=https://se.caib.es/concsvapi/hash/${csv}
'
, true, 2, 'Plugin Arxiu Prova', 'Plugin Arxiu Prova');
