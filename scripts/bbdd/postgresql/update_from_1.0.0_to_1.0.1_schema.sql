---
--- 08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
---
ALTER TABLE efi_seriedocu RENAME seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME tipusdocu  TO tipusdocumental;
ALTER TABLE efi_seriedocu RENAME TO efi_seriedocumental;
ALTER SEQUENCE efi_seriedocu_seq RENAME TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental ALTER COLUMN seriedocumentalid SET DEFAULT nextval('efi_seriedocumental_seq');
