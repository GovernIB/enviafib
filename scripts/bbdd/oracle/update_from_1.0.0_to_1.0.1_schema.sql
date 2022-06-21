---
--- 08/06/2022 - Canviar nom de taula efi_seriedocu a efi_seriedocumental #62
---
ALTER TABLE efi_seriedocu RENAME COLUMN seriedocuid  TO seriedocumentalid;
ALTER TABLE efi_seriedocu RENAME COLUMN tipusdocu  TO tipusdocumental;
RENAME efi_seriedocu  TO efi_seriedocumental;
RENAME efi_seriedocu_seq TO efi_seriedocumental_seq;
ALTER TABLE efi_seriedocumental MODIFY seriedocumentalid DEFAULT efi_seriedocumental_seq.NEXTVAL;
RENAME efi_seriedocu_pk  TO efi_seriedocumental_pk;
CREATE INDEX efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);
CREATE INDEX efi_seriedocumental_pk_i on efi_seriedocumental (seriedocumentalid);



---
--- 14/06/2022 - Guardar informació de la firma realitzada en BBDD #41
---
CREATE SEQUENCE efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1
    NOMAXVALUE
    NOMINVALUE
    CACHE 1;

CREATE TABLE efi_infosignatura (
    infosignaturaid NUMBER(19) DEFAULT efi_infosignatura_seq.nextval NOT NULL,
    signoperation NUMBER(10)  NOT NULL,
    signtype VARCHAR2(255) NOT NULL,
    signalgorithm VARCHAR2(255),
    signmode NUMBER(10),
    signaturestablelocation NUMBER(10),
    timestampincluded NUMBER(1),
    policyincluded NUMBER(1),
    enitipofirma VARCHAR2(255),
    eniperfilfirma VARCHAR2(255),
    enirolfirma VARCHAR2(255),
    enisignername VARCHAR2(255),
    enisigneradministrationid VARCHAR2(255),
    enisignlevel VARCHAR2(255),
    checkadministrationidofsigner NUMBER(1),
    checkdocumentmodifications NUMBER(1),
    checkvalidationsignature NUMBER(1)
);

ALTER TABLE efi_infosignatura ADD CONSTRAINT efi_infosignatura_pk PRIMARY KEY (infosignaturaid);

CREATE INDEX efi_infosignatura_pk_i ON efi_infosignatura(infosignaturaid);
-- Podria ser així també...
-- CREATE INDEX efi_infosignatura_pk_i ON efi_infosignatura USING btree (infosignaturaid);

ALTER TABLE efi_peticio ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES efi_infosignatura(infosignaturaid);
CREATE INDEX efi_peticio_infosignid_fk_i on efi_peticio (infosignaturaid);


---
--- 15/06/2022 -  Firma de Documents per un Mateix #23 
---

ALTER TABLE efi_peticio ADD tipus NUMBER(10) NOT NULL DEFAULT 0;
ALTER TABLE efi_peticio ADD errormsg VARCHAR2(255);
ALTER TABLE efi_peticio ADD errorexception text;
ALTER TABLE efi_peticio ADD datafinal timestamp;
ALTER TABLE efi_peticio ADD peticioportafirmes VARCHAR2(255);
  
UPDATE efi_peticio SET  peticioportafirmes=peticioportafib WHERE peticioportafib is not null;

ALTER TABLE efi_peticio DROP COLUMN peticioportafib;

COMMENT ON COLUMN efi_peticio.peticioportafirmes IS 'Identificador de la petició dins el sistema de portafirmes';


---
--- 15/06/2022 -  Eliminar multiples titols per multiples idiomes de la pantalla de creació de petició de firma. #61
---

ALTER TABLE efi_peticio ADD nom VARCHAR2(255);

UPDATE efi_peticio SET nom=peticioid;

ALTER TABLE efi_peticio DROP column titolid;

DELETE FROM efi_traducciomap;
DELETE FROM efi_traduccio;

COMMENT ON COLUMN efi_peticio.nom IS 'Nom de la peticio a PortaFIB.';

