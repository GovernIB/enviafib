---
--- 15/09/2022 -  Crear una taula efi_menu #212 
---

CREATE SEQUENCE efi_menu_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807  START 1000 CACHE 1;


CREATE TABLE efi_menu
(
   menuid bigint NOT NULL DEFAULT nextval('efi_menu_seq'), 
   nom character varying(255) NOT NULL, 
   descripcio character varying(255), 
   titolmenuid bigint NOT NULL, 
   ajudamenuid bigint NOT NULL, 
   ordre integer NOT NULL, 
   tipus integer NOT NULL, 
   grupid bigint, 
   parametretext text, 
   parametrecombo character varying(255), 
   actiu boolean NOT NULL DEFAULT false,

   CONSTRAINT efi_menu_pk PRIMARY KEY (menuid), 
   CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES efi_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES efi_traduccio (traduccioid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES efi_grup (grupid) ON UPDATE NO ACTION ON DELETE NO ACTION
);

 create index efi_menu_pk_i on efi_menu (menuid);
 create index efi_menu_titolmenuid_fk_i on efi_menu (titolmenuid);
 create index efi_menu_ajudamenuid_fk_i on efi_menu (ajudamenuid);
 create index efi_menu_grupid_fk_i on efi_menu (grupid);
