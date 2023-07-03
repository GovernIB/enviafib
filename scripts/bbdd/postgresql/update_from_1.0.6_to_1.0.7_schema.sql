---
--- 15/06/2023 - Crear una ajuda-manual d'usuari a dins l'aplicació que es pugui informar dinàmicament. #315
---


CREATE SEQUENCE efi_faq_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;

CREATE TABLE efi_faq
(
  faqid bigint NOT NULL DEFAULT nextval('efi_faq_seq'),
  enunciat_es character varying(255),
  enunciat_ca character varying(255),
  resposta_es text,
  resposta_ca text,
  ordre bigint,
  fitxer1id bigint,
  fitxer2id bigint,
  fitxer3id bigint,

  CONSTRAINT efi_faq_pk PRIMARY KEY (faqid),
  CONSTRAINT efi_faq_fitxer_fitxer1id_fk FOREIGN KEY (fitxer1id) REFERENCES efi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_faq_fitxer_fitxer2id_fk FOREIGN KEY (fitxer2id) REFERENCES efi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT efi_faq_fitxer_fitxer3id_fk FOREIGN KEY (fitxer3id) REFERENCES efi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);

CREATE INDEX efi_faq_pk_i ON efi_faq (faqid);
CREATE INDEX efi_faq_fitxer1id_fk_i ON efi_faq (fitxer1id);
CREATE INDEX efi_faq_fitxer2id_fk_i ON efi_faq (fitxer2id);
CREATE INDEX efi_faq_fitxer3id_fk_i ON efi_faq (fitxer3id);
