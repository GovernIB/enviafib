---
--- 10/08/2022 - Millorar test de Plugin d'Estructura organitzativa #148
---

ALTER TABLE efi_grup
   ALTER COLUMN nom SET NOT NULL;

   
---
--- 12/08/2022 - Revisió de Seqüències - https://github.com/GovernIB/genapp/issues/118
---
   
SELECT setval('efi_peticio_seq', 1000, true);
SELECT setval('efi_plugin_seq', 1000, true);
SELECT setval('efi_seriedocumental_seq', 1000, true);
SELECT setval('efi_usuari_seq', 1000, true);


---
--- 12/08/2022 - Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---


ALTER TABLE efi_seriedocumental
  ADD COLUMN procedimentnom character varying;
ALTER TABLE efi_seriedocumental
  ADD COLUMN procedimentcodi character varying;


UPDATE efi_seriedocumental
SET procedimentcodi = 'organo1_PRO_123456789'
WHERE procedimentcodi IS NULL;

UPDATE efi_seriedocumental
SET procedimentnom='Subvenciones empleo'
WHERE procedimentnom IS NULL;

ALTER TABLE efi_seriedocumental
   ALTER COLUMN procedimentnom SET NOT NULL;
ALTER TABLE efi_seriedocumental
   ALTER COLUMN procedimentcodi SET NOT NULL;


