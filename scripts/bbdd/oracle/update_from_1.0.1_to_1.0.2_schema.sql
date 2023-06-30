-- 
--  12/07/2022 - Estat de Petició ha de ser de tipus integer en java i bbdd #125
-- 
ALTER TABLE efi_peticio MODIFY estat INTEGER;

-- 
--  13/07/2022 - Afegir IdiomaId al a taula efi_usuari #103
-- 
ALTER TABLE efi_usuari ADD idiomaid VARCHAR2(5) DEFAULT 'ca' NOT NULL;

ALTER TABLE efi_usuari ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES efi_idioma (idiomaid);
-- 
--  14/07/2022 - Posar el camp destinatariNif nullable #102
-- 
ALTER TABLE efi_peticio MODIFY (destinatarinif NULL);
--
-- 14/07/2022 - Afegir a base de dades camp "reason" per autofirma #101
--
ALTER TABLE efi_peticio ADD reason VARCHAR(255);
-- 
--  15/07/2022 - Preparar la BBDD per suportar enviament a Plugin d'Arxiu #32
-- 
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

-- 
--  27/07/2022 - Prepara BBDD per guardar informació retornada de la cridada a Arxiu #40
-- 
CREATE SEQUENCE efi_infoarxiu_seq START WITH 1000 INCREMENT BY 1;

create table efi_infoarxiu (
  infoarxiuid number(19, 0) not null,
  arxiudocumentid varchar2(255 char),
  arxiuexpedientid varchar2(255 char),
  csv varchar2(255 char),
  csvgenerationdefinition varchar2(255 char),
  csvvalidationweb varchar2(255 char),
  enifileurl varchar2(255 char),
  originalfileurl varchar2(255 char),
  printableurl varchar2(255 char),
  validationfileurl varchar2(255 char),
  primary key (infoarxiuid)
);

ALTER TABLE efi_peticio
ADD infoarxiuid NUMBER(19);

ALTER TABLE efi_peticio
ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES efi_infoarxiu (infoarxiuid);

CREATE INDEX efi_infoarxiu_pk_i ON efi_infoarxiu (infoarxiuid);
CREATE INDEX efi_usuari_idiomaid_fk_i ON efi_usuari (idiomaid);

GRANT SELECT ON efi_infoarxiu_seq TO www_enviafib;
GRANT SELECT,  INSERT,  DELETE,  UPDATE ON efi_infoarxiu TO www_enviafib;
-- 
--  28/07/2022 - Gestió de grups d'usuaris #135
-- 
CREATE SEQUENCE efi_grup_seq START WITH 1000 INCREMENT BY 1;

create table efi_grup (
  grupid number(19,0) not null,
  descripcio varchar2(255 char),
  nom varchar2(255 char) not null,
  primary key (grupid)
);

CREATE INDEX efi_grup_pk_i ON efi_grup (grupid);

CREATE SEQUENCE efi_grupusuari_seq START WITH 1000 INCREMENT BY 1;

create table efi_grupusuari (
  grupusuariid number(19,0) not null,
  grupid number(19,0) not null,
  usuariid number(19,0) not null,
  primary key (grupusuariid)
);

ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES efi_grup (grupid);
ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_usuari_usuar_fk FOREIGN KEY (usuariid) REFERENCES efi_usuari (usuariid);
ALTER TABLE efi_grupusuari ADD CONSTRAINT efi_grupusuari_usuari_grup_uk UNIQUE (usuariid, grupid);

CREATE INDEX efi_grupusuari_pk_i ON efi_grupusuari (grupusuariid);
CREATE INDEX efi_grupusuari_grupid_fk_i ON efi_grupusuari (grupid);
CREATE INDEX efi_grupusuari_usuariid_fk_i ON efi_grupusuari (usuariid);

GRANT SELECT ON efi_grup_seq TO www_enviafib;
GRANT SELECT ON efi_grupusuari_seq TO www_enviafib;
GRANT SELECT,  INSERT,  DELETE,  UPDATE ON efi_grup TO www_enviafib;
GRANT SELECT,  INSERT,  DELETE,  UPDATE ON efi_grupusuari TO www_enviafib;

-- 
--  04/08/2022 - Gestió de PLugins de Estructura Organitzativa #142
-- 
ALTER TABLE efi_plugin DROP COLUMN descripciocurtaid;
ALTER TABLE efi_plugin DROP COLUMN nomid;

ALTER TABLE efi_plugin ADD nom VARCHAR2(255);
UPDATE efi_plugin SET nom = '-';
ALTER TABLE efi_plugin MODIFY nom VARCHAR2(255) NOT NULL;

ALTER TABLE efi_plugin ADD descripcio VARCHAR2(255);
UPDATE efi_plugin SET descripcio = '-';
ALTER TABLE efi_plugin MODIFY descripcio VARCHAR2(255) NOT NULL;
