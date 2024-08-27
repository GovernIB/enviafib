-- afegir anexes a peticions d'enviafib
CREATE SEQUENCE efi_infoanex_seq START WITH 1000 INCREMENT BY 1;

CREATE TABLE efi_infoanex (
  infoanexid number(19, 0) not null,
  peticioid number(19, 0),
  anexid number(19, 0),

  primary key (infoanexid)
);

ALTER TABLE efi_faq ADD CONSTRAINT efi_infoanex_peticio_fk FOREIGN KEY (peticioid) REFERENCES efi_peticio (peticioid);
ALTER TABLE efi_faq ADD CONSTRAINT efi_infoanex_fitxer_fk FOREIGN KEY (anexid) REFERENCES efi_fitxer (fitxerid);

CREATE INDEX efi_infoanex_pk_i ON efi_faq (infoanexid);
CREATE INDEX efi_infoanex_peticioid_fk_i ON efi_faq (peticioid);
CREATE INDEX efi_infoanex_anexid_fk_i ON efi_faq (anexid);

GRANT SELECT ON efi_infoanex_seq TO www_enviafib;
GRANT SELECT,INSERT,DELETE,UPDATE ON efi_infoanex TO www_enviafib;