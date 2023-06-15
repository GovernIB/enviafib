
-- Sequence: efi_faq_seq
-- DROP SEQUENCE efi_faq_seq
CREATE SEQUENCE efi_faq_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;
ALTER TABLE efi_faq_seq
  OWNER TO enviafib;


-- Table: efi_faq
-- DROP TABLE efi_faq;
CREATE TABLE efi_faq
(
  faqid bigint NOT NULL DEFAULT nextval('efi_faq_seq'::regclass),
  enunciat_es character varying(255),
  enunciat_ca character varying(255),
  resposta_es text,
  resposta_ca text,
  ordre bigint,
  fitxer1id bigint,
  fitxer2id bigint,
  fitxer3id bigint,

  CONSTRAINT efi_faq_pk PRIMARY KEY (faqid),
  CONSTRAINT efi_faq_fitxer_fitxer1id_fk FOREIGN KEY (fitxer1id)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_faq_fitxer_fitxer2id_fk FOREIGN KEY (fitxer2id)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_faq_fitxer_fitxer3id_fk FOREIGN KEY (fitxer3id)
      REFERENCES efi_fitxer (fitxerid) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

ALTER TABLE efi_faq
  OWNER TO enviafib;

-- Index: efi_faq_pk_i
-- DROP INDEX efi_faq_pk_i;
CREATE INDEX efi_faq_pk_i
  ON efi_faq
  USING btree
  (faqid);

-- Index: efi_faq_fitxer1id_fk_i
-- DROP INDEX efi_faq_fitxer1id_fk_i;
CREATE INDEX efi_faq_fitxer1id_fk_i
  ON efi_faq
  USING btree
  (fitxer1id);

-- Index: efi_faq_fitxer2id_fk_i
-- DROP INDEX efi_faq_fitxer2id_fk_i;
CREATE INDEX efi_faq_fitxer2id_fk_i
  ON efi_faq
  USING btree
  (fitxer2id);

-- Index: efi_faq_fitxer3id_fk_i
-- DROP INDEX efi_faq_fitxer3id_fk_i;
CREATE INDEX efi_faq_fitxer3id_fk_i
  ON efi_faq
  USING btree
  (fitxer3id);
