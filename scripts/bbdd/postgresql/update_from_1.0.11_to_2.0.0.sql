
-- Afegir Revisor a petició
ALTER TABLE efi_peticio
   ADD COLUMN revisor character varying(255);
   
   
--###########################################################################
--##   Actualitzar PluginsIB-Core, PluginsIB-Utils i PluginsIB a les noves versions #403   
--###########################################################################

UPDATE efi_plugin SET classe=REPLACE(classe, 'es.caib.plugins.arxiu.', 'es.caib.pluginsib.arxiu.')  WHERE tipus=2;
UPDATE efi_plugin SET properties=REPLACE(properties, 'es.caib.enviafib.plugin.', 'es.caib.enviafib.pluginsib.')  WHERE tipus=2;


-- Crear taula Entitat #411 20-08-2024
CREATE TABLE efi_entitat
(
  entitatid character varying(50) NOT NULL,
  filtrecertificats text NOT NULL,
  nom character varying(50) NOT NULL,
  descripcio character varying(255) DEFAULT NULL::character varying,
  adrezahtml character varying(2000) NOT NULL,
  activa boolean NOT NULL DEFAULT true,
  suporttelefon character varying(50),
  suportweb character varying(250),
  suportemail character varying(100),
  pdfautoritzaciodelegacioid bigint NOT NULL,
  faviconid bigint NOT NULL,
  logowebid bigint NOT NULL,
  logowebpeuid bigint NOT NULL,
  logosegellid bigint NOT NULL,
  maxuploadsize bigint,
  maxsizefitxeradaptat bigint,
  maxfilestosignatsametime integer,
  web character varying(250) NOT NULL,
  policyidentifier character varying(100),
  policyidentifierhash text,
  policyidentifierhashalgorithm character varying(50),
  policyurldocument character varying(255),
  motiudelegacioid bigint,
  firmatperformatid bigint,
  algorismedefirmaid bigint NOT NULL DEFAULT 0,
  comprovarniffirma boolean NOT NULL DEFAULT true,
  segelldetempsviaweb integer NOT NULL DEFAULT 0,
  politicacustodia integer NOT NULL DEFAULT 0, -- 0: No permetre, 1:Només Plantilles de l'Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us)
  politicataulafirmes integer NOT NULL DEFAULT 2, -- 0 no es permet taules de firmes, 1 definit en l'entitat, 2 opcional per defecte el definit a l'entitat, 3 opcional per defecte sense taula de firmes
  posiciotaulafirmes integer NOT NULL DEFAULT 1, -- SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1; DEFINIT_EN_FIRMA(RUBRICA)=2
  checkcanviatdocfirmat boolean NOT NULL DEFAULT true,
  uspoliticadefirma integer NOT NULL DEFAULT 0, -- -1=> usar politica de firma de l'entitat, 0 => no usar politica de firma,  1=> usar politica d'aquesta configuracio, 2 => L'usuari web o usuari-app elegeixen la politica de firma
  propietatstaulafirmes text,
  validarfirma boolean NOT NULL,

  CONSTRAINT efi_entitat_pk PRIMARY KEY (entitatid),
  CONSTRAINT efi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_fitxer_pdfd_fk FOREIGN KEY (pdfautoritzaciodelegacioid)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_traduccio_firm_fk FOREIGN KEY (firmatperformatid)
      REFERENCES efi_traduccio (traduccioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid)
      REFERENCES efi_traduccio (traduccioid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE efi_entitat
  OWNER TO enviafib2;
COMMENT ON COLUMN efi_entitat.politicacustodia IS '0: No permetre, 1:Només Plantilles de l''Entitat (No editables), 2: Obligatori Plantilla Entitat, 3: Opcional plantilla Entitat (Per defecte Actiu), 4: Opcional plantilla Entitat (Per defecte NO Actiu), 5: Llibertat Total (selecció, edició i us)';
COMMENT ON COLUMN efi_entitat.politicataulafirmes IS '0 no es permet taules de firmes, 1 definit en l''entitat, 2 opcional per defecte el definit a l''entitat, 3 opcional per defecte sense taula de firmes';
COMMENT ON COLUMN efi_entitat.posiciotaulafirmes IS 'SENSETAULA = 0; PRIMERAPAGINA = 1; DARRERAPAGINA = -1; DEFINIT_EN_FIRMA(RUBRICA)=2';
COMMENT ON COLUMN efi_entitat.uspoliticadefirma IS '-1=> usar politica de firma de l''entitat, 0 => no usar politica de firma,  1=> usar politica d''aquesta configuracio, 2 => L''usuari web o usuari-app elegeixen la politica de firma';

CREATE INDEX efi_entitat_algofirma_fk_i ON efi_entitat USING btree (algorismedefirmaid);
CREATE INDEX efi_entitat_faviconid_fk_i ON efi_entitat USING btree (faviconid);
CREATE INDEX efi_entitat_firmatper_fk_i ON efi_entitat USING btree (firmatperformatid);
CREATE INDEX efi_entitat_logosegellid_fk_i ON efi_entitat USING btree (logosegellid);
CREATE INDEX efi_entitat_logowebid_fk_i ON efi_entitat USING btree (logowebid);
CREATE INDEX efi_entitat_logowebpeuid_fk_i ON efi_entitat USING btree (logowebpeuid);
CREATE INDEX efi_entitat_motiudele_fk_i ON efi_entitat USING btree (motiudelegacioid);
CREATE INDEX efi_entitat_pdfautoriid_fk_i ON efi_entitat USING btree (pdfautoritzaciodelegacioid);
CREATE INDEX efi_entitat_pk_i ON efi_entitat USING btree (entitatid COLLATE pg_catalog."default");




-- ==== ERRORS EN TAULA ]efi_entitat[:

-- El camp shortname de la taula efi_entitat ha de tenir longitud de 10 o menys caracters (efi_entitat)
-- La taula efi_entitat no té definida l'etiqueta per l'idioma  [ca]
-- La taula efi_entitat no té definida l'etiqueta per l'idioma  [es]
-- La taula efi_entitat_plural no té definida l'etiqueta per l'idioma  [ca]
-- La taula efi_entitat_plural no té definida l'etiqueta per l'idioma  [es]

-- No es faran comprobacions de Longitud de camps ni de constraints
--      (PrimaryKeys, ForeignKeys, Indexs i UNIQUEs) mentre no es
--      solventin els problemes anteriors.



 --- Les següents seqüències no estan assignades a cap taula: [efi_infocustody_seq]