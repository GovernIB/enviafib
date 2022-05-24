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

---
--- 24/05/2022 - Gestió de Series Documentals #38
---

CREATE SEQUENCE public.efi_seriedocumental_seq;
ALTER SEQUENCE public.efi_seriedocumental_seq
  OWNER TO enviafib;


CREATE TABLE public.efi_seriedocu
(
   seriedocumentalid bigint NOT NULL DEFAULT nextval('efi_seriedocumental_seq'), 
   nom character varying(256) NOT NULL, 
   tipusdocumental character varying(256), 
   CONSTRAINT efi_seriedocu_pk PRIMARY KEY (seriedocuid), 
   CONSTRAINT efi_seriedocu_tipusdocu_uk UNIQUE (tipusdocu)
) 
WITH (
  OIDS = FALSE
)
;
ALTER TABLE public.efi_seriedocumental
  OWNER TO enviafib;
COMMENT ON TABLE public.efi_seriedocumental
  IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';