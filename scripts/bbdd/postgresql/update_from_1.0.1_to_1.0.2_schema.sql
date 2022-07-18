---
--- 12/07/2022 - Estat de Petició ha de ser de tipus integer en java i bbdd #125
---

ALTER TABLE efi_peticio
   ALTER COLUMN estat TYPE integer;



---
--- 12/07/2022 - Afegir IdiomaId al a taula efi_usuari #103
---

ALTER TABLE efi_usuari
  ADD COLUMN idiomaid character varying(5) NOT NULL DEFAULT 'ca';
ALTER TABLE efi_usuari
  ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES efi_idioma (idiomaid) ON UPDATE NO ACTION ON DELETE NO ACTION;

---
--- 14/07/2022 - Posar el camp destinatariNif nullable #102
---
ALTER TABLE efi_peticio
   ALTER COLUMN destinatarinif DROP NOT NULL;

--
-- 14/07/2022 - Afegir a base de dades camp "reason" per autofirma #101
--
ALTER TABLE efi_peticio
   ADD COLUMN reason character varying(255);

---
--- 15/07/2022 - Prepara BBDD per guardar informació retornada de la cridada a Arxiu #40
---


  CREATE SEQUENCE public.efi_infocustody_seq;

  CREATE TABLE public.efi_infocustody
(
   infocustodyid bigint NOT NULL DEFAULT nextval('efi_infocustody_seq'::regclass), 
   custodyid character varying(255), 
   originalfileurl character varying(255), 
   csv character varying(255), 
   csvgenerationdefinition character varying(255), 
   csvvalidationweb character varying(255), 
   arxiuexpedientid character varying(255), 
   arxiudocumentid character varying(255), 
   printableurl character varying(255), 
   enifileurl character varying(255), 
   validationfileurl character varying(255), 
   peticioid character varying(255)
)

ALTER TABLE efi_infocustody
  ADD CONSTRAINT efi_infocustody_pk PRIMARY KEY (infocustodyid);

  ALTER TABLE efi_infocustody
  DROP COLUMN peticioid;
ALTER TABLE efi_infocustody
  ADD COLUMN peticioid bigint;
ALTER TABLE efi_infocustody
  ADD CONSTRAINT efi_infocus_peticio_petid_fk FOREIGN KEY (peticioid) REFERENCES efi_peticio (peticioid) ON UPDATE NO ACTION ON DELETE NO ACTION;






