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

