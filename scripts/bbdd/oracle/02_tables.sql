
    create table efi_fitxer (
       fitxerid number(19,0) not null,
        descripcio varchar2(1000 char),
        mime varchar2(255 char) not null,
        nom varchar2(255 char) not null,
        tamany number(19,0) not null
    );

    create table efi_idioma (
       idiomaid varchar2(5 char) not null,
        nom varchar2(50 char) not null,
        ordre number(10,0) not null,
        suportat number(1,0) not null
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
        timestampincluded number(1,0)
    );

    create table efi_peticio (
       peticioid number(19,0) not null,
        datacreacio timestamp not null,
        datafinal timestamp,
        destinatarinif varchar2(50 char) not null,
        errorexception clob,
        errormsg varchar2(255 char),
        estat number(19,0) not null,
        fitxer_firmatid number(19,0),
        fitxerid number(19,0) not null,
        idiomadoc varchar2(30 char) not null,
        idiomaid varchar2(5 char) not null,
        infosignaturaid number(19,0),
        nom varchar2(255 char),
        peticioportafirmes varchar2(255 char),
        solicitantid number(19,0) not null,
        tipus number(10,0) not null,
        tipusdocumental varchar2(100 char) not null
    );

    create table efi_plugin (
       pluginid number(19,0) not null,
        actiu number(1,0) not null,
        classe varchar2(255 char),
        descripciocurtaid number(19,0) not null,
        nomid number(19,0) not null,
        properties clob,
        tipus number(10,0) not null
    );

    create table efi_seriedocumental (
       seriedocumentalid number(19,0) not null,
        nom varchar2(256 char) not null,
        tipusdocumental varchar2(256 char)
    );

    create table efi_traduccio (
       traduccioid number(19,0) not null
    );

    create table efi_traducciomap (
       traducciomapid number(19,0) not null,
        valor varchar2(4000 char),
        idiomaid varchar2(255 char) not null
    );

    create table efi_usuari (
       usuariid number(19,0) not null,
        email varchar2(256 char) not null,
        llinatge1 varchar2(256 char) not null,
        llinatge2 varchar2(256 char),
        nif varchar2(50 char) not null,
        nom varchar2(256 char) not null,
        username varchar2(100 char) not null
    );



