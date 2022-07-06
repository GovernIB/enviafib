-- create index efi_fitxer_pk_i on efi_fitxer (fitxerid);
-- create index efi_infosignatura_pk_i on efi_infosignatura (infosignaturaid);
create index efi_peticio_solicitantid_fk_i on efi_peticio (solicitantid);
create index efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);
-- create index efi_plugin_pk_i on efi_plugin (pluginid);
-- create index efi_usuari_pk_i on efi_usuari (usuariid);
