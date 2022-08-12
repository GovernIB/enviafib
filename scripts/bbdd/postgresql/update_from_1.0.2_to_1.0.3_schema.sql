---
--- 10/08/2022 - Millorar test de Plugin d'Estructura organitzativa #148
---

ALTER TABLE efi_grup
   ALTER COLUMN nom SET NOT NULL;
   
   
---
--- 12/08/2022 Revisió de Seqüències - https://github.com/GovernIB/genapp/issues/118
---
   
SELECT setval('efi_peticio_seq', 1000, true);
SELECT setval('efi_plugin_seq', 1000, true);
SELECT setval('efi_seriedocumental_seq', 1000, true);
SELECT setval('efi_usuari_seq', 1000, true);
