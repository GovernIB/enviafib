---
--- 08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
---
ALTER TABLE efi_seriedocu RENAME COLUMN seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME COLUMN tipusdocu  TO tipusdocumental;
RENAME efi_seriedocu  TO efi_seriedocumental;
RENAME efi_seriedocu_seq TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental MODIFY seriedocumentalid DEFAULT efi_seriedocumental_seq.NEXTVAL;
RENAME efi_seriedocu_pk  TO efi_seriedocumental_pk;
create index efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);
create index efi_seriedocumental_pk_i on efi_seriedocumental (seriedocumentalid);

---
--- 15/06/2022 -  Eliminar multiples titols per multiples idiomes de la pantalla de creació de petició de firma. #61
---

ALTER TABLE efi_peticio ADD nom VARCHAR2(255);

UPDATE efi_peticio SET nom=peticioid;

ALTER TABLE efi_peticio DROP column titolid;

DELETE FROM efi_traducciomap;
DELETE FROM efi_traduccio;

COMMENT ON COLUMN efi_peticio.nom IS 'Nom de la peticio a PortaFIB.';

