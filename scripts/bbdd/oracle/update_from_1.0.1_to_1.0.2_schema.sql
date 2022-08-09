---
--- 12/07/2022 - Estat de Petició ha de ser de tipus integer en java i bbdd #125
---

ALTER TABLE efi_peticio
   MODIFY estat TYPE INTEGER;

---
--- 13/07/2022 - Afegir IdiomaId al a taula efi_usuari #103
---

ALTER TABLE efi_usuari 
ADD idiomaid VARCHAR(5) NOT NULL DEFAULT 'ca';
ALTER TABLE efi_usuari
  ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES efi_idioma (idiomaid);


---
--- 14/07/2022 - Posar el camp destinatariNif nullable #102
---
ALTER TABLE efi_peticio
   MODIFY (destinatarinif NULL);


--
-- 14/07/2022 - Afegir a base de dades camp "reason" per autofirma #101
--
ALTER TABLE efi_peticio
   ADD reason VARCHAR(255);



---
--- 15/07/2022 - Preparar la BBDD per suportar enviament a Plugin d'Arxiu #32
---

ALTER TABLE efi_peticio
  ADD arxiufuncionariusername VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuparamfuncionarinom VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuparamfuncionarinif VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuparamfuncionaridir3 VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiureqparamdocestatelabora VARCHAR(4);
ALTER TABLE efi_peticio
  ADD arxiureqparamorigen INTEGER;
ALTER TABLE efi_peticio
  ADD arxiureqparaminteressats VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiureqparamciutadanif VARCHAR(15);
ALTER TABLE efi_peticio
  ADD arxiureqparamciutadanom VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiureqparamorgans VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuoptparamprocedimentcodi VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuoptparamprocedimentnom VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuoptparamseriedocumental VARCHAR(255);
ALTER TABLE efi_peticio
  ADD arxiuoptparamexpedientid VARCHAR(255);




---
--- 27/07/2022 - Prepara BBDD per guardar informació retornada de la cridada a Arxiu #40
---

  CREATE SEQUENCE efi_infoarxiu_seq
    START WITH 1000
    INCREMENT BY 1;

  CREATE TABLE public.efi_infoarxiu
(
   infoarxiuid NUMBER(19) DEFAULT efi_infoarxiu_seq.nextval NOT NULL,
   originalfileurl VARCHAR(255), 
   csv VARCHAR(255), 
   csvgenerationdefinition VARCHAR(255), 
   csvvalidationweb VARCHAR(255), 
   arxiuexpedientid VARCHAR(255), 
   arxiudocumentid VARCHAR(255), 
   printableurl VARCHAR(255), 
   enifileurl VARCHAR(255), 
   validationfileurl VARCHAR(255)
)

ALTER TABLE efi_infoarxiu ADD CONSTRAINT efi_infoarxiu_pk PRIMARY KEY (infoarxiuid);

ALTER TABLE efi_peticio
   ADD infoarxiuid NUMBER(19);

ALTER TABLE efi_peticio
  ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES efi_infoarxiu (infoarxiuid);

CREATE INDEX efi_infoarxiu_pk_i
  ON efi_infoarxiu (infoarxiuid);

CREATE INDEX efi_usuari_idiomaid_fk_i
  ON efi_usuari (idiomaid);



---
--- 28/07/2022 - Gestió de grups d'usuaris #135
---

  CREATE SEQUENCE efi_grup_seq 
    START WITH 1000
    INCREMENT BY 1;

  CREATE TABLE efi_grup
(
   grupid NUMBER(19) DEFAULT efi_grup_seq.nextval NOT NULL,
   nom VARCHAR(255), 
   descripcio VARCHAR(255), 
);

ALTER TABLE efi_grup ADD CONSTRAINT efi_grup_pk PRIMARY KEY (grupid);

CREATE INDEX efi_grup_pk_i ON efi_grup (grupid);



CREATE SEQUENCE efi_grupusuari_seq 
   START WITH 1000
   INCREMENT BY 1;

   CREATE TABLE efi_grupusuari
(
   grupusuariid NUMBER(19) DEFAULT efi_grupusuari_seq.nextval NOT NULL,
   grupid NUMBER(19) NOT NULL, 
   usuariid NUMBER(19) NOT NULL 
);

ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_pk PRIMARY KEY (grupusuariid);

ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES efi_grup (grupid);
ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_usuari_usuar_fk FOREIGN KEY (usuariid) REFERENCES efi_usuari (usuariid);

ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_usuari_grup_uk UNIQUE (usuariid, grupid);

CREATE INDEX efi_grupusuari_pk_i ON efi_grupusuari (grupusuariid);
CREATE INDEX efi_grupusuari_grupid_fk_i ON efi_grupusuari (grupid);
CREATE INDEX efi_grupusuari_usuariid_fk_i ON efi_grupusuari (usuariid);


---
--- 04/08/2022 - Gestió de PLugins de Estructura Organitzativa #142
---

ALTER TABLE efi_plugin DROP COLUMN descripciocurtaid;
ALTER TABLE efi_plugin DROP COLUMN nomid;

ALTER TABLE efi_plugin 
   ADD COLUMN nom VARCHAR(255) NOT NULL;
ALTER TABLE efi_plugin 
   ADD COLUMN descripcio VARCHAR(255) NOT NULL;





