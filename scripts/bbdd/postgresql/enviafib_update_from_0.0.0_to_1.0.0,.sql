---
--- 09/05/2022 - Crear columnes de gestió de fitxers firmats i resposta de PortaFIB a la taula Peticio #11
---
ALTER TABLE efi_peticio
  ADD COLUMN estat bigint NOT NULL DEFAULT 0;
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
--- 18/05/2022 - Canviat valor per defecte d'estat de creació de peticions a 1. Implementar botó de descarrega del fitxer firmat #20
---

ALTER TABLE efi_peticio
   ALTER COLUMN estat SET DEFAULT 1;