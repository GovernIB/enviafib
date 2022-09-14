---
--- 10/08/2022 - Millorar test de Plugin d'Estructura organitzativa #148
---

ALTER TABLE efi_grup
   MODIFY (nom NOT NULL);


---
--- 11/08/2022 - Afegir camps Procediment Nom i Procediment codi a taula efi_seriedocumental #157
---


ALTER TABLE efi_seriedocumental
  ADD procedimentnom VARCHAR(255);
ALTER TABLE efi_seriedocumental
  ADD procedimentcodi VARCHAR(255);


UPDATE efi_seriedocumental
SET procedimentcodi = 'organo1_PRO_123456789'
WHERE procedimentcodi IS NULL;

UPDATE efi_seriedocumental
SET procedimentnom='Subvenciones empleo'
WHERE procedimentnom IS NULL;

ALTER TABLE efi_seriedocumental
   MODIFY (procedimentnom NOT NULL);
ALTER TABLE efi_seriedocumental
   MODIFY (procedimentcodi NOT NULL);


