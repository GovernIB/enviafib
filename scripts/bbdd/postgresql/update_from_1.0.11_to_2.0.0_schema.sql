-- Afegir Revisor a petició
ALTER TABLE efi_peticio
ADD COLUMN revisor character varying(255);
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
    entitatid character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    adrezahtml character varying(2000) NOT NULL,
    activa boolean NOT NULL DEFAULT true,
    suporttelefon character varying(50),
    suportweb character varying(250),
    suportemail character varying(100),
    faviconid bigint NOT NULL,
    logowebid bigint NOT NULL,
    logowebpeuid bigint NOT NULL,
    logosegellid bigint NOT NULL,
    web character varying(250) NOT NULL,
    motiudelegacioid bigint,
    segelldetempsviaweb integer NOT NULL DEFAULT 0,
    checkcanviatdocfirmat boolean NOT NULL DEFAULT true,
    propietatstaulafirmes text,
    CONSTRAINT efi_entitat_pk PRIMARY KEY (entitatid),
    CONSTRAINT efi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT efi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT efi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT efi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT efi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES efi_traduccio (traduccioid) MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION
) WITH (OIDS = FALSE);

ALTER TABLE efi_entitat OWNER TO enviafib;

CREATE INDEX efi_entitat_faviconid_fk_i ON efi_entitat USING btree (faviconid);
CREATE INDEX efi_entitat_logosegellid_fk_i ON efi_entitat USING btree (logosegellid);
CREATE INDEX efi_entitat_logowebid_fk_i ON efi_entitat USING btree (logowebid);
CREATE INDEX efi_entitat_logowebpeuid_fk_i ON efi_entitat USING btree (logowebpeuid);
CREATE INDEX efi_entitat_motiudele_fk_i ON efi_entitat USING btree (motiudelegacioid);
CREATE INDEX efi_entitat_pk_i ON efi_entitat USING btree (entitatid COLLATE pg_catalog."default");

-- Afegir entitatid a usuari, per poder fer la relació amb la taula entitat #412 20-08-2024
ALTER TABLE efi_usuari ADD COLUMN entitatid character varying(50);
ALTER TABLE efi_usuari ADD CONSTRAINT efi_usuari_entitat_fk FOREIGN KEY (entitatid) REFERENCES efi_entitat (entitatid) ON DELETE NO ACTION ON UPDATE NO ACTION;
create index efi_usuari_entitatid_fk_i on efi_usuari (entitatid);



