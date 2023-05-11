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
--- 26/09/2022 -   Estudi Plugin Estructura Organitzativa en taula de BBDD #225 
---

INSERT INTO efi_plugin(pluginid, classe, properties, actiu, tipus, nom, descripcio) 
      VALUES (30, 'org.fundaciobit.pluginsib.estructuraorganitzativa.database.DatabaseEstructuraOrganitzativaPlugin', '# =================  TAULA DE BBDD =========

es.caib.enviafib.pluginsib.estructuraorganitzativa.database.datasourcejndi=java:jboss/datasources/enviafibDS
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.tablename=efi_organitzacio
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.areacodi=codiconselleria
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.departamentcodi=codidirecciogeneral
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.tipus=tipus
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.columns.valor=valor


# =================  PLUGIN DE USER INFORMATION =========


es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.class=org.fundaciobit.pluginsib.userinformation.keycloak.KeyCloakUserInformationPlugin
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.propertybase=es.caib.enviafib.
# Si la següent propietat està comentada, llavors les propietats
# del plugin de user information han d''estar al System.getProperties()
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.propertiesfile=D:\\dades\\dades\\CarpetesPersonals\\ProjecteBase\\jboss7\\standalone\\deploy_enviafib\\enviafib.properties

es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcaparea=RolCapArea
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolcapdepartament=RolCapDepartament
es.caib.enviafib.pluginsib.estructuraorganitzativa.database.userinformation.rolsecretari=RolSecretari', true, 1, 'Database', 'Recull informació de l''Estructura d''una taula de la Base de Dades');
