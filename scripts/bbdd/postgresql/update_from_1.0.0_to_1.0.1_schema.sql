---
--- 08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
---
ALTER TABLE efi_seriedocu RENAME seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME tipusdocu  TO tipusdocumental;
ALTER TABLE efi_seriedocu RENAME TO efi_seriedocumental;
ALTER SEQUENCE efi_seriedocu_seq RENAME TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental ALTER COLUMN seriedocumentalid SET DEFAULT nextval('efi_seriedocumental_seq');
ALTER INDEX efi_seriedocu_pk RENAME to efi_seriedocumental_pk;
create index efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);
create index efi_seriedocumental_pk_i on efi_seriedocumental (seriedocumentalid);






---
--- 14/06/2022 - Guardar informació de la firma realitzada en BBDD #41
---
CREATE SEQUENCE efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

ALTER TABLE efi_infosignatura_seq OWNER TO enviafib;

CREATE TABLE efi_infosignatura (
    infosignaturaid bigint DEFAULT nextval('efi_infosignatura_seq'::regclass) NOT NULL,
    signoperation integer NOT NULL,
    signtype character varying(255) NOT NULL,
    signalgorithm character varying(255),
    signmode integer,
    signaturestablelocation integer,
    timestampincluded boolean,
    policyincluded boolean,
    enitipofirma character varying(255),
    eniperfilfirma character varying(255),
    enirolfirma character varying(255),
    enisignername character varying(255),
    enisigneradministrationid character varying(255),
    enisignlevel character varying(255),
    checkadministrationidofsigner boolean,
    checkdocumentmodifications boolean,
    checkvalidationsignature boolean
);

ALTER TABLE efi_infosignatura OWNER TO enviafib;
ALTER TABLE ONLY efi_infosignatura ADD CONSTRAINT efi_infosignatura_pk PRIMARY KEY (infosignaturaid);

CREATE INDEX efi_infosignatura_pk_i ON efi_infosignatura USING btree (infosignaturaid);

ALTER TABLE efi_peticio ADD COLUMN infosignaturaid bigint;
ALTER TABLE ONLY efi_peticio ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES efi_infosignatura(infosignaturaid);

