---
--- 09/05/2022 - Crear columnes de gestió de fitxers firmats i resposta de PortaFIB a la taula Peticio #11
---
ALTER TABLE efi_peticio
  ADD COLUMN estat smallint NOT NULL DEFAULT 0;
ALTER TABLE efi_peticio
  ADD COLUMN fitxer_firmatid bigint;

ALTER TABLE efi_peticio
  ADD CONSTRAINT efi_peticio_fitxer_ffirm_fk FOREIGN KEY (fitxer_firmatid) REFERENCES efi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION;




---
--- 11/05/2022 - Guardar a Petició el ID de petició intern de PortaFIB. #15
---

ALTER TABLE efi_peticio
  ADD COLUMN peticioportafib bigint;
COMMENT ON COLUMN efi_peticio.peticioportafib IS 'Identificador de la peticio dins el sistema de Portafib.';


---
--- 12/05/2022 - Correcció de tipus enumerat "estat" de taula efi_peticio corregit a bigint (en vers de smallint)
---

ALTER TABLE efi_peticio
   ALTER COLUMN estat TYPE bigint;