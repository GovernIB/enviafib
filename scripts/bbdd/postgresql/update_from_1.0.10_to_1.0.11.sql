-- afegir anexes a peticions d'enviafib

CREATE SEQUENCE efi_infoanex_seq
  INCREMENT 1
  MINVALUE 1
  MAXVALUE 9223372036854775807
  START 1000
  CACHE 1;

CREATE TABLE public.efi_infoanex
(
   infoanexid bigint NOT NULL DEFAULT nextval('efi_infoanex_seq'),
   peticioid bigint, 
   anexid bigint, 

   CONSTRAINT efi_infoanex_pk PRIMARY KEY (infoanexid), 
   CONSTRAINT efi_infoanex_fitxer_fk FOREIGN KEY (anexid) REFERENCES efi_fitxer (fitxerid) ON UPDATE NO ACTION ON DELETE NO ACTION, 
   CONSTRAINT efi_infoanex_peticio_fk FOREIGN KEY (peticioid) REFERENCES efi_peticio (peticioid) ON UPDATE NO ACTION ON DELETE NO ACTION
) 
WITH (
  OIDS = FALSE
);

CREATE INDEX efi_infoanex_pk_i ON efi_infoanex (infoanexid);
CREATE INDEX efi_infoanex_anexid_fk_i ON efi_infoanex (anexid);
CREATE INDEX efi_infoanex_peticioid_fk_i ON efi_infoanex (peticioid);

