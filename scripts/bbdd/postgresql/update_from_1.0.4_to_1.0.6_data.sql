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


---
--- 26/09/2022 -   Estudi Plugin Estructura Organitzativa en taula de BBDD #225 
---

CREATE SEQUENCE efi_organitzacio_seq INCREMENT 1 MINVALUE 1 MAXVALUE 9223372036854775807  START 1000 CACHE 1;

CREATE TABLE efi_organitzacio
(
   organitzacioid bigint NOT NULL DEFAULT nextval('efi_organitzacio_seq'), 
   codiconselleria character varying(100),
   codidirecciogeneral character varying(100),
   tipus  character varying(100),
   valor character varying(255),

   CONSTRAINT efi_organitzacio_pk PRIMARY KEY (organitzacioid)
);

 create index efi_organitzacio_pk_i on efi_organitzacio (organitzacioid);
 
 


---
--- 11/05/2023 -   Revisar com s'informen les metadades mínimes obligatòries #266
---

 INSERT INTO efi_seriedocumental (seriedocumentalid, nom, tipusdocumental, procedimentnom, procedimentcodi) VALUES
    (1010, 'SD01333', '1', 'Enviafib TD01 Resolució', '---'),
    (1011, 'SD01334', '2', 'Enviafib TD02 Acords', '---'),
    (1012, 'SD01335', '3', 'Enviafib TD03 Contractes', '---'),
    (1013, 'SD01336', '4', 'Enviafib TD04 Convenis', '---'),
    (1014, 'SD01337', '5', 'Enviafib TD05 Declaracions', '---'),
    (1015, 'SD01338', '6', 'Enviafib TD06 Comunicacions', '---'),
    (1016, 'SD01339', '7', 'Enviafib TD07 Notificacions', '---'),
    (1017, 'SD01340', '8', 'Enviafib TD08 Publicacions', '---'),
    (1018, 'SD01342', '9', 'Enviafib TD09 Justificants de recepció', '---'),
    (1019, 'SD01342', '10', 'Enviafib TD10 Actes', '---'),
    (1020, 'SD01343', '11', 'Enviafib TD11 Certificats', '---'),
    (1021, 'SD01344', '12', 'Enviafib TD12 Diligències', '---'),
    (1022, 'SD01345', '13', 'Enviafib TD13 Informes', '---'),
    (1023, 'SD01346', '14', 'Enviafib TD14 Sol·licituds', '---'),
    (1024, 'SD01347', '15', 'Enviafib TD15 Denúncies', '---'),
    (1025, 'SD01348', '16', 'Enviafib TD16 Al·legacions', '---'),
    (1026, 'SD01349', '17', 'Enviafib TD17 Recursos', '---'),
    (1027, 'SD01350', '18', 'Enviafib TD18 Comunicacions ciutadà', '---'),
    (1028, 'SD01351', '19', 'Enviafib TD19 Factures', '---'),
    (1029, 'SD01352', '20', 'Enviafib TD20 Altre documentació aportada', '---'),
    (1030, 'SD01353', '99', 'Enviafib TD99 Altres', '---');
