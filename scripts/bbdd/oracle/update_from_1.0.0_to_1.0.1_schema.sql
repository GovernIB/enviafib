---
--- 08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
---
ALTER TABLE efi_seriedocu RENAME COLUMN seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME COLUMN tipusdocu  TO tipusdocumental;
RENAME efi_seriedocu  TO efi_seriedocumental;
RENAME efi_seriedocu_seq TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental MODIFY seriedocumentalid DEFAULT efi_seriedocumental_seq.NEXTVAL;
