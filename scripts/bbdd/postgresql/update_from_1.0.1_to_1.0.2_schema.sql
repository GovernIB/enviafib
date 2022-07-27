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
--- 15/07/2022 - Preparar la BBDD per suportar enviament a Plugin d'Arxiu #32
---

ALTER TABLE efi_peticio
  ADD COLUMN arxiufuncionariusername character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuparamfuncionarinom character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuparamfuncionarinif character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuparamfuncionaridir3 character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparamdocestatelabora character varying(4);
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparamorigen integer;
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparaminteressats character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparamciutadanif character varying(15);
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparamciutadanom character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiureqparamorgans character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuoptparamprocedimentcodi character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuoptparamprocedimentnom character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuoptparamseriedocumental character varying(255);
ALTER TABLE efi_peticio
  ADD COLUMN arxiuoptparamexpedientid character varying(255);

---
--- 27/07/2022 - Prepara BBDD per guardar informació retornada de la cridada a Arxiu #40
---

  CREATE SEQUENCE efi_infoarxiu_seq START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

  CREATE TABLE efi_infoarxiu
(
   infoarxiuid bigint NOT NULL DEFAULT nextval('efi_infoarxiu_seq'::regclass), 
   originalfileurl character varying(255), 
   csv character varying(255), 
   csvgenerationdefinition character varying(255), 
   csvvalidationweb character varying(255), 
   arxiuexpedientid character varying(255), 
   arxiudocumentid character varying(255), 
   printableurl character varying(255), 
   enifileurl character varying(255), 
   validationfileurl character varying(255) 
);

ALTER TABLE efi_infoarxiu
  ADD CONSTRAINT efi_infoarxiu_pk PRIMARY KEY (infoarxiuid);



ALTER TABLE efi_peticio ADD COLUMN infoarxiuid bigint;

ALTER TABLE efi_peticio
  ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES efi_infoarxiu (infoarxiuid) ON UPDATE NO ACTION ON DELETE NO ACTION;

create index efi_peticio_infoarxiuid_fk_i on efi_peticio (infoarxiuid);



