-- 
--  15/09/2022 -  Crear una taula efi_menu #212 
-- 
CREATE SEQUENCE efi_menu_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_menu (
   menuid number(19,0) not null,
   actiu number(1,0) not null,
   ajudamenuid number(19,0) not null,
   descripcio varchar2(255 char),
   grupid number(19,0),
   nom varchar2(255 char) not null,
   ordre number(10,0) not null,
   parametrecombo varchar2(255 char),
   parametretext long,
   tipus number(10,0) not null,
   titolmenuid number(19,0) not null,
   primary key (menuid)
);

ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES efi_traduccio (traduccioid); 
ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES efi_traduccio (traduccioid); 
ALTER TABLE efi_menu ADD CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES efi_grup (grupid);


create index efi_menu_pk_i on efi_menu (menuid);
create index efi_menu_titolmenuid_fk_i on efi_menu (titolmenuid);
create index efi_menu_ajudamenuid_fk_i on efi_menu (ajudamenuid);
create index efi_menu_grupid_fk_i on efi_menu (grupid);

GRANT SELECT ON efi_menu_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_menu TO www_enviafib;

-- 
--  26/09/2022 -   Estudi Plugin Estructura Organitzativa en taula de BBDD #225 
-- 
CREATE SEQUENCE efi_organitzacio_seq  START WITH 1000 INCREMENT BY 1;

create table efi_organitzacio (
   organitzacioid number(19,0) not null,
   codiconselleria varchar2(100 char),
   codidirecciogeneral varchar2(100 char),
   tipus varchar2(100 char),
   valor varchar2(255 char),
   primary key (organitzacioid)
);

create index efi_organitzacio_pk_i on efi_organitzacio (organitzacioid);

GRANT SELECT ON efi_organitzacio_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_organitzacio TO www_enviafib;
