-- Afegir Revisor a petició
ALTER TABLE efi_peticio
ADD revisor VARCHAR2(255);

--###########################################################################
--##   Actualitzar PluginsIB-Core, PluginsIB-Utils i PluginsIB a les noves versions #403   
--###########################################################################
UPDATE efi_plugin
SET classe = REPLACE(
        classe,
        'es.caib.plugins.arxiu.',
        'es.caib.pluginsib.arxiu.'
    )
WHERE tipus = 2;
UPDATE efi_plugin
SET properties = REPLACE(
        properties,
        'es.caib.enviafib.plugin.',
        'es.caib.enviafib.pluginsib.'
    )
WHERE tipus = 2;

-- Crear taula Entitat #411 20-08-2024
CREATE TABLE efi_entitat (
    entitatid VARCHAR2(50) NOT NULL,
    nom VARCHAR2(50) NOT NULL,
    descripcio VARCHAR2(255) DEFAULT NULL,
    adrezahtml VARCHAR2(2000) NOT NULL,
    activa NUMBER(1) DEFAULT 1 NOT NULL,
    suporttelefon VARCHAR2(50),
    suportweb VARCHAR2(250),
    suportemail VARCHAR2(100),
    faviconid NUMBER(19) NOT NULL,
    logowebid NUMBER(19) NOT NULL,
    logowebpeuid NUMBER(19) NOT NULL,
    logosegellid NUMBER(19) NOT NULL,
    web VARCHAR2(250) NOT NULL,
    motiudelegacioid NUMBER(19),
    segelldetempsviaweb NUMBER(10) DEFAULT 0 NOT NULL,
    checkcanviatdocfirmat NUMBER(1) DEFAULT 1 NOT NULL,
    propietatstaulafirmes CLOB,

    primary key (entitatid)
);

ALTER TABLE efi_entitat ADD CONSTRAINT efi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES efi_fitxer (fitxerid);
ALTER TABLE efi_entitat ADD CONSTRAINT efi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES efi_fitxer (fitxerid);
ALTER TABLE efi_entitat ADD CONSTRAINT efi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES efi_fitxer (fitxerid);
ALTER TABLE efi_entitat ADD CONSTRAINT efi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES efi_fitxer (fitxerid);
ALTER TABLE efi_entitat ADD CONSTRAINT efi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES efi_traduccio (traduccioid);

CREATE INDEX efi_entitat_faviconid_fk_i ON efi_entitat (faviconid);
CREATE INDEX efi_entitat_logosegellid_fk_i ON efi_entitat (logosegellid);
CREATE INDEX efi_entitat_logowebid_fk_i ON efi_entitat (logowebid);
CREATE INDEX efi_entitat_logowebpeuid_fk_i ON efi_entitat (logowebpeuid);
CREATE INDEX efi_entitat_motiudele_fk_i ON efi_entitat (motiudelegacioid);
CREATE INDEX efi_entitat_pk_i ON efi_entitat (entitatid COLLATE pg_catalog."default");

-- Afegir entitatid a usuari, per poder fer la relació amb la taula entitat #412 20-08-2024
ALTER TABLE efi_usuari 
ADD entitatid VARCHAR2(50);

ALTER TABLE efi_usuari 
ADD CONSTRAINT efi_usuari_entitat_fk FOREIGN KEY (entitatid) 
REFERENCES efi_entitat (entitatid) ON DELETE NO ACTION;

create index efi_usuari_entitatid_fk_i on efi_usuari (entitatid);