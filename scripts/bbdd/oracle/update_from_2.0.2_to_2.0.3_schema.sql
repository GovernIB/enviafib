ALTER TABLE efi_peticio ADD (reintentsarxiu number(19,0));

-- --Afegir un sistema d'avisos per informar de possibles incidències als usuaris #423 - 21-02-2025
CREATE SEQUENCE efi_avis_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_avis (
    avisid number(19,0) NOT NULL,
    missatge varchar2(500 char) NOT NULL,
    datainici TIMESTAMP,
    datafi TIMESTAMP,
    actiu number(1,0) NOT NULL,
    tipus varchar2(30 char) NOT NULL,
    PRIMARY KEY (avisid)
);


CREATE INDEX efi_avis_pk_i ON efi_avis (avisid);

GRANT SELECT ON efi_avis_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_avis TO www_enviafib;





-- ALTER TABLE efi_peticio
--    ADD COLUMN reintentsarxiu bigint;

-- --Afegir un sistema d'avisos per informar de possibles incidències als usuaris #423 - 21-02-2025
-- CREATE SEQUENCE public.efi_avis_seq
--     START WITH 1000
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;

-- CREATE TABLE efi_avis (
--     avisid BIGINT DEFAULT nextval('public.efi_avis_seq'::regclass) NOT NULL,
--     missatge CHARACTER VARYING(500) NOT NULL,
--     datainici TIMESTAMP,
--     datafi TIMESTAMP,
--     actiu BOOLEAN NOT NULL DEFAULT TRUE,
--     tipus CHARACTER VARYING(30) NOT NULL,
--     CONSTRAINT efi_avis_pk PRIMARY KEY (avisid)
-- ) WITH (OIDS = FALSE);

-- CREATE INDEX efi_avis_pk_i ON efi_avis USING btree (avisid);
