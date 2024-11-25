---
--- 12/11/2024 - Crear taula d'usuari-entitat per saber les entitats de les que un usuari es administrador.
---
CREATE SEQUENCE efi_usuarientitat_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;

CREATE TABLE efi_usuarientitat
(
  usuarientitatid bigint NOT NULL DEFAULT nextval('efi_usuarientitat_seq'),
  usuariid bigint NOT NULL,
  entitatid character varying(50) NOT NULL,
  CONSTRAINT efi_usuarientitat_pk PRIMARY KEY (usuarientitatid),

  CONSTRAINT efi_usuarientitat_usuari_fk FOREIGN KEY (usuariid) REFERENCES efi_usuari (usuariid) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_usuarientitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES efi_entitat (entitatid) ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);  

CREATE INDEX efi_usuarientitat_pk_i ON efi_usuarientitat (usuarientitatid);
CREATE INDEX efi_usrent_usuari_usuariid_fk ON efi_usuarientitat (usuariid);
CREATE INDEX efi_usrent_entitat_entitati_fk ON efi_usuarientitat (entitatid);


---
--- 12/11/2024 - Fer que la gestió de Series Documentals es faci per entitat.
---

ALTER TABLE efi_seriedocumental ADD COLUMN entitatid character varying(50);


ALTER TABLE efi_seriedocumental
  ADD CONSTRAINT efi_seriedocu_entitat_entit_fk FOREIGN KEY (entitatid)
      REFERENCES efi_entitat (entitatid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION;

create index efi_seriedocu_entitatid_fk_i on efi_seriedocumental (entitatid);


ALTER TABLE efi_seriedocumental DROP CONSTRAINT efi_seriedocu_tipusdocu_uk;

ALTER TABLE efi_seriedocumental
  ADD CONSTRAINT efi_seriedocu_td_ent_uk UNIQUE(tipusdocumental, entitatid);

---
--- 12/11/2024 - Afegir camp codi DIR3 a una entitat #436
---
ALTER TABLE efi_entitat
  ADD COLUMN dir3 character varying(50);

