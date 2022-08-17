---
--- 16/08/2022 Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---

INSERT INTO efi_seriedocumental
(nom, procedimentnom, procedimentcodi)
VALUES
('S0001', 'Subvenciones empleo', 'organo1_PRO_123456789');


---
--- 17/08/2022 Fer que Plugin d'Arxiu llegeixi properties i classe de BBDD enlloc de Propietats de sistema #169
---
INSERT INTO public.efi_plugin VALUES (10, 'es.caib.plugins.arxiu.caib.ArxiuPluginCaib', 
'es.caib.enviafib.plugin.arxiu.caib.base.url=https://esbse.caib.es:4430/esb
es.caib.enviafib.plugin.arxiu.caib.aplicacio.codi=CODI_APP
es.caib.enviafib.plugin.arxiu.caib.usuari=username
es.caib.enviafib.plugin.arxiu.caib.contrasenya=password
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.url=https://proves.caib.es/concsv/rest/printable/uuid
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.usuari=USUARIconcsv
es.caib.enviafib.plugin.arxiu.caib.conversio.imprimible.contrasenya=PASSWDconcsv

# No funciona
# es.caib.enviafib.plugin.arxiu.caib.original_file_url_EL=https://dev.caib.es/concsv/rest/original/${csv}

# No funciona
# es.caib.enviafib.plugin.arxiu.caib.printable_file_url_EL=https://dev.caib.es/concsv/rest/printable/${csv}

# No funciona
# es.caib.enviafib.plugin.arxiu.caib.eni_file_url_EL=https://dev.caib.es/concsv/enidoc/${csv}
es.caib.enviafib.plugin.arxiu.caib.csv_validation_web_EL=http://dev.caib.es/concsv

# Valor Desconegut
es.caib.enviafib.plugin.arxiu.caib.csv.definicio=https://dev.caib.es/def_csv_gen.pdf
es.caib.enviafib.plugin.arxiu.caib.validation_file_url_EL=https://dev.caib.es/concsv/hash/${csv}'
, true, 2, 'Plugin Arxiu Prova', 'Plugin Arxiu Prova');
