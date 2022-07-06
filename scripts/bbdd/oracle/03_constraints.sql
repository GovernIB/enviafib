
 -- INICI PKs
    alter table efi_fitxer add constraint efi_fitxer_pk primary key (fitxerid);

    alter table efi_idioma add constraint efi_idioma_pk primary key (idiomaid);

    alter table efi_infosignatura add constraint efi_infosignatura_pk primary key (infosignaturaid);

    alter table efi_peticio add constraint efi_peticio_pk primary key (peticioid);

    alter table efi_plugin add constraint efi_plugin_pk primary key (pluginid);

    alter table efi_seriedocumental add constraint efi_seriedocumental_pk primary key (seriedocumentalid);

    alter table efi_traduccio add constraint efi_traduccio_pk primary key (traduccioid);

    alter table efi_traducciomap add constraint efi_traducmap_pk primary key (traducciomapid, idiomaid);

    alter table efi_usuari add constraint efi_usuari_pk primary key (usuariid);

 -- FINAL PKs


 -- INICI FKs

    alter table efi_peticio 
       add constraint efi_peticio_fitxer_fitxer_fk 
       foreign key (fitxerid) 
       references efi_fitxer;

    alter table efi_peticio 
       add constraint efi_peticio_fitxer_ffirm_fk 
       foreign key (fitxer_firmatid) 
       references efi_fitxer;

    alter table efi_peticio 
       add constraint efi_peticio_idioma_idiid_fk 
       foreign key (idiomaid) 
       references efi_idioma;

    alter table efi_peticio 
       add constraint efi_peticio_infosign_fk 
       foreign key (infosignaturaid) 
       references efi_infosignatura;

    alter table efi_peticio 
       add constraint efi_peticio_usuari_soli_fk 
       foreign key (solicitantid) 
       references efi_usuari;

    alter table efi_traducciomap 
       add constraint efi_traducmap_traduccio_fk 
       foreign key (traducciomapid) 
       references efi_traduccio;
 -- FINAL FKs


 -- INICI UNIQUEs

    alter table efi_seriedocumental 
       add constraint UK_ox1aosn2t9fscv2lv01lr10tg unique (tipusdocumental);

    alter table efi_usuari 
       add constraint UK_rhi053fw7q637iv8ohhiasjad unique (nif);
 -- FINAL UNIQUEs

