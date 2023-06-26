---
--- 15/06/2023 - Crear una ajuda-manual d'usuari a dins l'aplicació que es pugui informar dinàmicament. #315
---
CREATE SEQUENCE efi_faq_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_faq
(
  faqid number(19,0)  DEFAULT efi_faq_seq.nextval NOT NULL, 
  enunciat_es varchar2(255 char), 
  enunciat_ca varchar2(255 char), 

  resposta_es clob,
  resposta_ca clob,

  ordre number(10,0), 
  fitxer1id number(19,0), 
  fitxer2id number(19,0), 
  fitxer3id number(19,0) 
);

ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_pk PRIMARY KEY (menuid);
ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer_fitxer1id_fk FOREIGN KEY (fitxer1id) REFERENCES efi_fitxer (fitxerid); 
ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer_fitxer2id_fk FOREIGN KEY (fitxer2id) REFERENCES efi_fitxer (fitxerid); 
ALTER TABLE efi_faq ADD CONSTRAINT efi_faq_fitxer_fitxer3id_fk FOREIGN KEY (fitxer3id) REFERENCES efi_fitxer (fitxerid); 

CREATE INDEX efi_faq_pk_i ON efi_faq (faqid);
CREATE INDEX efi_faq_fitxer1id_fk_i ON efi_faq (fitxer1id);
CREATE INDEX efi_faq_fitxer2id_fk_i ON efi_faq (fitxer2id);
CREATE INDEX efi_faq_fitxer3id_fk_i ON efi_faq (fitxer3id);

