---
--- 15/09/2022 -  Crear una taula efi_menu #212 
---

CREATE SEQUENCE efi_menu_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_menu
(
   menuid number(19,0)  DEFAULT efi_menu_seq.nextval NOT NULL, 
   nom varchar2(255 char) NOT NULL, 
   descripcio varchar2(255 char), 
   titolmenuid number(19,0) NOT NULL, 
   ajudamenuid number(19,0) NOT NULL, 
   ordre number(10,0) NOT NULL, 
   tipus number(10,0) NOT NULL, 
   grupid number(19,0), 
   parametretext clob, 
   parametrecombo varchar2(255 char), 
   actiu number(1,0) DEFAULT 0 NOT NULL 
);

ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_pk PRIMARY KEY (menuid);
ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES efi_traduccio (traduccioid); 
ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES efi_traduccio (traduccioid); 
ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES efi_grup (grupid);


create index efi_menu_pk_i on efi_menu (menuid);
create index efi_menu_titolmenuid_fk_i on efi_menu (titolmenuid);
create index efi_menu_ajudamenuid_fk_i on efi_menu (ajudamenuid);
create index efi_menu_grupid_fk_i on efi_menu (grupid);

