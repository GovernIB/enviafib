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

CREATE SEQUENCE public.efi_seriedocu_seq;
ALTER SEQUENCE public.efi_seriedocu_seq
  OWNER TO enviafib;


CREATE TABLE efi_seriedocu
(
  seriedocuid bigint NOT NULL DEFAULT nextval('efi_seriedocu_seq'::regclass),
  nom character varying(256) NOT NULL,
  tipusdocu character varying(256),
  CONSTRAINT efi_seriedocu_pk PRIMARY KEY (seriedocuid),
  CONSTRAINT efi_tipusdocu_uk UNIQUE (tipusdocu)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE efi_seriedocu
  OWNER TO enviafib;
COMMENT ON TABLE efi_seriedocu
  IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';

---
--- 03/06/2022 - Gestió de Tipus de Document #34
---

ALTER TABLE efi_peticio
   ADD COLUMN tipusdocumental character varying(100);

ALTER TABLE efi_seriedocu DROP CONSTRAINT efi_tipusdocu_uk;
ALTER TABLE efi_seriedocu ADD CONSTRAINT efi_seriedocu_tipusdocu_uk unique (tipusdocu);

ALTER TABLE efi_peticio
   ADD COLUMN idiomadoc character varying(30);



---
--- 03/06/2022 - Gestió de Tipus de Document #34 (fer el camp tipusdocumental not null)
---
UPDATE efi_peticio set tipusdocumental = 99 where tipusdocumental is null;

ALTER TABLE efi_peticio
   ALTER COLUMN tipusdocumental SET NOT NULL;

UPDATE efi_peticio set idiomadoc = 'ca' where idiomadoc is null;

ALTER TABLE efi_peticio
   ALTER COLUMN idiomadoc SET NOT NULL;

UPDATE efi_idioma set suportat = false where idiomaid = 'en';
