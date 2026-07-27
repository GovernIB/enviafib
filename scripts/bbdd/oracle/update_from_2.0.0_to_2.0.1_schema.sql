---
--- 12/11/2024 - Crear taula d'usuari-entitat per saber les entitats de les que un usuari es administrador.
---
CREATE SEQUENCE efi_usuarientitat_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_usuarientitat (
   usuarientitatid number(19,0) NOT NULL,
   usuariid number(19,0) NOT NULL,
   entitatid varchar2(50 char) NOT NULL,
   PRIMARY KEY (usuarientitatid)
);

ALTER TABLE efi_usuarientitat ADD CONSTRAINT efi_usuarientitat_usuari_fk FOREIGN KEY (usuariid) REFERENCES efi_usuari (usuariid);
ALTER TABLE efi_usuarientitat ADD CONSTRAINT efi_usuarientitat_entitat_fk FOREIGN KEY (entitatid) REFERENCES efi_entitat (entitatid);


-- Crear los índices en la tabla efi_usuarientitat
CREATE INDEX efi_usuarientitat_pk_i ON efi_usuarientitat (usuarientitatid);
CREATE INDEX efi_usrent_usuari_usuariid_fk ON efi_usuarientitat (usuariid);
CREATE INDEX efi_usrent_entitat_entitati_fk ON efi_usuarientitat (entitatid);

-- Otorgar permisos, ajusta el nombre del usuario según sea necesario

GRANT SELECT ON efi_usuarientitat_seq TO www_enviafib;
GRANT SELECT, INSERT, DELETE, UPDATE ON efi_usuarientitat TO www_enviafib;


---
--- 12/11/2024 - Fer que la gestió de Series Documentals es faci per entitat.
---

-- Agregar columna 'entitatid' a la tabla 'efi_seriedocumental'
ALTER TABLE efi_seriedocumental ADD (entitatid varchar2(50 char));

-- Agregar constraint de clave foránea para 'entitatid'
ALTER TABLE efi_seriedocumental ADD CONSTRAINT efi_seriedocu_entitat_entit_fk FOREIGN KEY (entitatid) REFERENCES efi_entitat (entitatid);

-- Crear índice en la columna 'entitatid'
CREATE INDEX efi_seriedocu_entitatid_fk_i ON efi_seriedocumental (entitatid);

-- Eliminar constraint única existente
ALTER TABLE efi_seriedocumental DROP CONSTRAINT efi_seriedocu_tipusdocu_uk;

-- Agregar nueva constraint única combinando 'tipusdocumental' y 'entitatid'
ALTER TABLE efi_seriedocumental
ADD CONSTRAINT efi_seriedocu_td_ent_uk UNIQUE (tipusdocumental, entitatid);

---
--- 12/11/2024 - Afegir camp codi DIR3 a una entitat #436
---

-- Agregar columna 'dir3' a la tabla 'efi_entitat'
ALTER TABLE efi_entitat ADD (dir3 varchar2(50 char));
