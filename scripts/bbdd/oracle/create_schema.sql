create sequence efi_faq_seq start with 1000 increment by  1;
create sequence efi_fitxer_seq start with 1000 increment by  1;
create sequence efi_grup_seq start with 1000 increment by  1;
create sequence efi_grupusuari_seq start with 1000 increment by  1;
create sequence efi_infoarxiu_seq start with 1000 increment by  1;
create sequence efi_infosignatura_seq start with 1000 increment by  1;
create sequence efi_menu_seq start with 1000 increment by  1;
create sequence efi_organitzacio_seq start with 1000 increment by  1;
create sequence efi_peticio_seq start with 1000 increment by  1;
create sequence efi_plugin_seq start with 1000 increment by  1;
create sequence efi_seriedocumental_seq start with 1000 increment by  1;
create sequence efi_traduccio_seq start with 1000 increment by  1;
create sequence efi_usuari_seq start with 1000 increment by  1;

    create table efi_faq (
       faqid number(19,0) not null,
        enunciat_ca varchar2(255 char),
        enunciat_es varchar2(255 char),
        fitxer1id number(19,0),
        fitxer2id number(19,0),
        fitxer3id number(19,0),
        ordre number(19,0),
        resposta_ca long,
        resposta_es long,
        primary key (faqid)
    );

    create table efi_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null,
        primary key (fitxerid)
    );

    create table efi_grup (
       grupid number(19,0) not null,
        descripcio varchar2(255 char),
        nom varchar2(255 char) not null,
        primary key (grupid)
    );

    create table efi_grupusuari (
       grupusuariid number(19,0) not null,
        grupid number(19,0) not null,
        usuariid number(19,0) not null,
        primary key (grupusuariid)
    );

    create table efi_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) default 0 not null,
        suportat number(1,0) not null,
        primary key (idiomaid)
    );

    create table efi_infoarxiu (
       infoarxiuid number(19,0) not null,
        arxiudocumentid varchar2(255 char),
        arxiuexpedientid varchar2(255 char),
        csv varchar2(255 char),
        csvgenerationdefinition varchar2(255 char),
        csvvalidationweb varchar2(255 char),
        enifileurl varchar2(255 char),
        originalfileurl varchar2(255 char),
        printableurl varchar2(255 char),
        validationfileurl varchar2(255 char),
        primary key (infoarxiuid)
    );

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

    create table efi_menu (
       menuid number(19,0) not null,
        actiu number(1,0) not null,
        ajudamenuid number(19,0) not null,
        descripcio varchar2(255 char),
        grupid number(19,0),
        nom varchar2(255 char) not null,
        ordre number(10,0) not null,
        parametrecombo varchar2(255 char),
        parametretext long,
        tipus number(10,0) not null,
        titolmenuid number(19,0) not null,
        primary key (menuid)
    );

    create table efi_organitzacio (
       organitzacioid number(19,0) not null,
        codiconselleria varchar2(100 char),
        codidirecciogeneral varchar2(100 char),
        tipus varchar2(100 char),
        valor varchar2(255 char),
        primary key (organitzacioid)
    );

    create table efi_peticio (
       peticioid number(19,0) not null,
        arxiufuncionariusername varchar2(255 char),
        arxiuoptparamexpedientid varchar2(255 char),
        arxiuoptparamprocedimentcodi varchar2(255 char),
        arxiuoptparamprocedimentnom varchar2(255 char),
        arxiuoptparamseriedocumental varchar2(255 char),
        arxiuparamfuncionaridir3 varchar2(255 char),
        arxiuparamfuncionarinif varchar2(255 char),
        arxiuparamfuncionarinom varchar2(255 char),
        arxiureqparamciutadanif varchar2(15 char),
        arxiureqparamciutadanom varchar2(255 char),
        arxiureqparamdocestatelabora varchar2(4 char),
        arxiureqparaminteressats varchar2(255 char),
        arxiureqparamorgans varchar2(255 char),
        arxiureqparamorigen number(10,0),
        datacreacio timestamp not null,
        datafinal timestamp,
        destinatarinif varchar2(50 char),
        errorexception long,
        errormsg varchar2(255 char),
        estat number(10,0) default 1 not null,
        fitxer_firmatid number(19,0),
        fitxerid number(19,0) not null,
        idiomadoc varchar2(30 char) not null,
        idiomaid varchar2(5 char) not null,
        infoarxiuid number(19,0),
        infosignaturaid number(19,0),
        nom varchar2(255 char),
        peticioportafirmes varchar2(255 char),
        reason varchar2(255 char),
        solicitantid number(19,0) not null,
        tipus number(10,0) default 0 not null,
        tipusdocumental varchar2(100 char) not null,
        primary key (peticioid)
    );

    create table efi_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char),
        descripcio varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        properties long,
        tipus number(10,0) not null,
        primary key (pluginid)
    );

    create table efi_seriedocumental (
       seriedocumentalid number(19,0) not null,
        nom varchar2(256 char) not null,
        procedimentcodi long not null,
        procedimentnom long not null,
        tipusdocumental varchar2(256 char),
        primary key (seriedocumentalid)
    );

    create table efi_traduccio (
       traduccioid number(19,0) not null,
        primary key (traduccioid)
    );

    create table efi_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null,
        primary key (traducciomapid, idiomaid)
    );

    create table efi_usuari (
       usuariid number(19,0) not null,
        email varchar2(256 char) not null,
        idiomaid varchar2(5 char) default 'ca' not null,
        llinatge1 varchar2(256 char) not null,
        llinatge2 varchar2(256 char),
        nif varchar2(50 char) not null,
        nom varchar2(256 char) not null,
        username varchar2(100 char) not null,
        primary key (usuariid)
    );
create index efi_faq_pk_i on efi_faq (faqid);
create index efi_faq_fitxer1id_fk_i on efi_faq (fitxer1id);
create index efi_faq_fitxer2id_fk_i on efi_faq (fitxer2id);
create index efi_faq_fitxer3id_fk_i on efi_faq (fitxer3id);
create index efi_fitxer_pk_i on efi_fitxer (fitxerid);
create index efi_grup_pk_i on efi_grup (grupid);
create index efi_grupusuari_pk_i on efi_grupusuari (grupusuariid);
create index efi_grupusuari_grupid_fk_i on efi_grupusuari (grupid);
create index efi_grupusuari_usuariid_fk_i on efi_grupusuari (usuariid);

    alter table efi_grupusuari 
       add constraint efi_grupusuari_usuari_grup_uk unique (usuariid, grupid);
create index efi_idioma_pk_i on efi_idioma (idiomaid);
create index efi_infoarxiu_pk_i on efi_infoarxiu (infoarxiuid);
create index efi_infosignatura_pk_i on efi_infosignatura (infosignaturaid);
create index efi_menu_pk_i on efi_menu (menuid);
create index efi_menu_titolmenuid_fk_i on efi_menu (titolmenuid);
create index efi_menu_ajudamenuid_fk_i on efi_menu (ajudamenuid);
create index efi_menu_grupid_fk_i on efi_menu (grupid);
create index efi_organitzacio_pk_i on efi_organitzacio (organitzacioid);
create index efi_peticio_pk_i on efi_peticio (peticioid);
create index efi_peticio_fitxerid_fk_i on efi_peticio (fitxerid);
create index efi_peticio_solicitantid_fk_i on efi_peticio (solicitantid);
create index efi_peticio_idiomaid_fk_i on efi_peticio (idiomaid);
create index efi_peticio_fitxer_firma_fk_i on efi_peticio (fitxer_firmatid);
create index efi_peticio_infosignid_fk_i on efi_peticio (infosignaturaid);
create index efi_peticio_infoarxiuid_fk_i on efi_peticio (infoarxiuid);
create index efi_plugin_pk_i on efi_plugin (pluginid);
create index efi_seriedocumental_pk_i on efi_seriedocumental (seriedocumentalid);

    alter table efi_seriedocumental 
       add constraint UK_ox1aosn2t9fscv2lv01lr10tg unique (tipusdocumental);
create index efi_traduccio_pk_i on efi_traduccio (traduccioid);
create index efi_usuari_pk_i on efi_usuari (usuariid);
create index efi_usuari_idiomaid_fk_i on efi_usuari (idiomaid);

    alter table efi_usuari 
       add constraint UK_rhi053fw7q637iv8ohhiasjad unique (nif);

    alter table efi_faq 
       add constraint efi_faq_fitxer_fitxer1id_fk 
       foreign key (fitxer1id) 
       references efi_fitxer;

    alter table efi_faq 
       add constraint efi_faq_fitxer_fitxer2id_fk 
       foreign key (fitxer2id) 
       references efi_fitxer;

    alter table efi_faq 
       add constraint efi_faq_fitxer_fitxer3id_fk 
       foreign key (fitxer3id) 
       references efi_fitxer;

    alter table efi_grupusuari 
       add constraint efi_grupusuari_grup_grupid_fk 
       foreign key (grupid) 
       references efi_grup;

    alter table efi_grupusuari 
       add constraint efi_grupusuari_usuari_usuar_fk 
       foreign key (usuariid) 
       references efi_usuari;

    alter table efi_menu 
       add constraint efi_menu_traduccio_ajuda_fk 
       foreign key (ajudamenuid) 
       references efi_traduccio;

    alter table efi_menu 
       add constraint efi_menu_grup_grupid_fk 
       foreign key (grupid) 
       references efi_grup;

    alter table efi_menu 
       add constraint efi_menu_traduccio_titol_fk 
       foreign key (titolmenuid) 
       references efi_traduccio;

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
       add constraint efi_peticio_infoarxiu_infoa_fk 
       foreign key (infoarxiuid) 
       references efi_infoarxiu;

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

    alter table efi_usuari 
       add constraint efi_usuari_idioma_fk 
       foreign key (idiomaid) 
       references efi_idioma;
