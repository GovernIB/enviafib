--
-- 15/06/2023 - Crear una ayuda-manual de usuario dentro de la aplicación que se pueda actualizar dinámicamente. #315
--
CREATE SEQUENCE efi_faq_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_faq (
  faqid number(19, 0) not null,
  enunciat_ca varchar2(255),
  enunciat_es varchar2(255),
  fitxer1id number(19, 0),
  fitxer2id number(19, 0),
  fitxer3id number(19, 0),
  ordre number(19, 0),
  resposta_ca clob,
  resposta_es clob,
  primary key (faqid)
);

ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer1id_fk FOREIGN KEY (fitxer1id) REFERENCES efi_fitxer (fitxerid);

ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer2id_fk FOREIGN KEY (fitxer2id) REFERENCES efi_fitxer (fitxerid);

ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer3id_fk FOREIGN KEY (fitxer3id) REFERENCES efi_fitxer (fitxerid);

CREATE INDEX efi_faq_pk_i ON efi_faq (faqid);
CREATE INDEX efi_faq_fitxer1id_fk_i ON efi_faq (fitxer1id);
CREATE INDEX efi_faq_fitxer2id_fk_i ON efi_faq (fitxer2id);
CREATE INDEX efi_faq_fitxer3id_fk_i ON efi_faq (fitxer3id);

GRANT SELECT ON efi_faq_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_faq TO www_enviafib;

-- DROP TABLE efi_faq;
-- DROP SEQUENCE efi_faq_seq;
