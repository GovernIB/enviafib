-- 
--  08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
-- 
ALTER TABLE efi_seriedocu RENAME COLUMN seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME COLUMN tipusdocu  TO tipusdocumental;
RENAME efi_seriedocu  TO efi_seriedocumental;
RENAME efi_seriedocu_seq TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental MODIFY seriedocumentalid DEFAULT efi_seriedocumental_seq.NEXTVAL;
ALTER TABLE efi_seriedocumental RENAME CONSTRAINT efi_seriedocu_pk TO efi_seriedocumental_pk;
CREATE INDEX efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);



-- 
--  14/06/2022 - Guardar informació de la firma realitzada en BBDD #41
-- 
CREATE SEQUENCE efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1;

create table efi_infosignatura (
    infosignaturaid number(19,0) not null,
    checkadministrationidofsigner number(1,0),
    checkdocumentmodifications number(1,0),
    checkvalidationsignature number(1,0),
    eniperfilfirma varchar2(255 char),
    enirolfirma varchar2(255 char),
    enisignlevel varchar2(255 char),
    enisigneradministrationid varchar2(255 char),
    enisignername varchar2(255 char),
    enitipofirma varchar2(255 char),
    policyincluded number(1,0),
    signalgorithm varchar2(255 char),
    signmode number(10,0),
    signoperation number(10,0) not null,
    signtype varchar2(255 char) not null,
    signaturestablelocation number(10,0),
    timestampincluded number(1,0),
    primary key (infosignaturaid)
);

ALTER TABLE efi_peticio ADD infosignaturaid NUMBER(19);
ALTER TABLE efi_peticio ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES efi_infosignatura;
CREATE INDEX efi_peticio_infosignid_fk_i on efi_peticio (infosignaturaid);

GRANT SELECT ON efi_infosignatura_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_infosignatura TO www_enviafib;

-- 
--  15/06/2022 -  Firma de Documents per un Mateix #23 
-- 

ALTER TABLE efi_peticio ADD tipus NUMBER(10) DEFAULT 0 NOT NULL ;
ALTER TABLE efi_peticio ADD errormsg VARCHAR2(255);
ALTER TABLE efi_peticio ADD errorexception clob;
ALTER TABLE efi_peticio ADD datafinal timestamp;
ALTER TABLE efi_peticio ADD peticioportafirmes VARCHAR2(255);

UPDATE efi_peticio SET  peticioportafirmes=peticioportafib WHERE peticioportafib is not null;

ALTER TABLE efi_peticio DROP COLUMN peticioportafib;

COMMENT ON COLUMN efi_peticio.peticioportafirmes IS 'Identificador de la petició dins el sistema de portafirmes';


-- 
--  15/06/2022 -  Eliminar multiples titols per multiples idiomes de la pantalla de creació de petició de firma. #61
-- 

ALTER TABLE efi_peticio ADD nom VARCHAR2(255);

UPDATE efi_peticio SET nom=peticioid;

ALTER TABLE efi_peticio DROP column titolid;

DELETE FROM efi_traducciomap;
DELETE FROM efi_traduccio;

COMMENT ON COLUMN efi_peticio.nom IS 'Nom de la peticio a PortaFIB.';
