--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2025-02-06 11:19:16

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2187 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 201 (class 1259 OID 354108)
-- Name: efi_entitat; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_entitat (
    entitatid character varying(50) NOT NULL,
    nom character varying(50) NOT NULL,
    descripcio character varying(255) DEFAULT NULL::character varying,
    adrezahtml character varying(2000) NOT NULL,
    activa boolean DEFAULT true NOT NULL,
    suporttelefon character varying(50),
    suportweb character varying(250),
    suportemail character varying(100),
    faviconid bigint NOT NULL,
    logowebid bigint NOT NULL,
    logowebpeuid bigint NOT NULL,
    logosegellid bigint NOT NULL,
    web character varying(250) NOT NULL,
    motiudelegacioid bigint,
    segelldetempsviaweb integer DEFAULT 0 NOT NULL,
    checkcanviatdocfirmat boolean DEFAULT true NOT NULL,
    propietatstaulafirmes text,
    dir3 character varying(50)
);


ALTER TABLE public.efi_entitat OWNER TO enviafib2;

--
-- TOC entry 171 (class 1259 OID 337032)
-- Name: efi_faq_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_faq_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_faq_seq OWNER TO enviafib2;

--
-- TOC entry 172 (class 1259 OID 337034)
-- Name: efi_faq; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_faq (
    faqid bigint DEFAULT nextval('public.efi_faq_seq'::regclass) NOT NULL,
    enunciat_es character varying(255),
    enunciat_ca character varying(255),
    resposta_es text,
    resposta_ca text,
    ordre bigint,
    fitxer1id bigint,
    fitxer2id bigint,
    fitxer3id bigint
);


ALTER TABLE public.efi_faq OWNER TO enviafib2;

--
-- TOC entry 173 (class 1259 OID 337041)
-- Name: efi_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_fitxer_seq OWNER TO enviafib2;

--
-- TOC entry 174 (class 1259 OID 337043)
-- Name: efi_fitxer; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_fitxer (
    fitxerid bigint DEFAULT nextval('public.efi_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.efi_fitxer OWNER TO enviafib2;

--
-- TOC entry 175 (class 1259 OID 337051)
-- Name: efi_grup_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_grup_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_grup_seq OWNER TO enviafib2;

--
-- TOC entry 176 (class 1259 OID 337053)
-- Name: efi_grup; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_grup (
    grupid bigint DEFAULT nextval('public.efi_grup_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255)
);


ALTER TABLE public.efi_grup OWNER TO enviafib2;

--
-- TOC entry 177 (class 1259 OID 337060)
-- Name: efi_grupusuari_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_grupusuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_grupusuari_seq OWNER TO enviafib2;

--
-- TOC entry 178 (class 1259 OID 337062)
-- Name: efi_grupusuari; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_grupusuari (
    grupusuariid bigint DEFAULT nextval('public.efi_grupusuari_seq'::regclass) NOT NULL,
    grupid bigint NOT NULL,
    usuariid bigint NOT NULL
);


ALTER TABLE public.efi_grupusuari OWNER TO enviafib2;

--
-- TOC entry 179 (class 1259 OID 337066)
-- Name: efi_idioma; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.efi_idioma OWNER TO enviafib2;

--
-- TOC entry 180 (class 1259 OID 337071)
-- Name: efi_infoanex_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_infoanex_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_infoanex_seq OWNER TO enviafib2;

--
-- TOC entry 181 (class 1259 OID 337073)
-- Name: efi_infoanex; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_infoanex (
    infoanexid bigint DEFAULT nextval('public.efi_infoanex_seq'::regclass) NOT NULL,
    peticioid bigint,
    anexid bigint
);


ALTER TABLE public.efi_infoanex OWNER TO enviafib2;

--
-- TOC entry 182 (class 1259 OID 337077)
-- Name: efi_infoarxiu_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_infoarxiu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_infoarxiu_seq OWNER TO enviafib2;

--
-- TOC entry 183 (class 1259 OID 337079)
-- Name: efi_infoarxiu; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_infoarxiu (
    infoarxiuid bigint DEFAULT nextval('public.efi_infoarxiu_seq'::regclass) NOT NULL,
    originalfileurl character varying(255),
    csv character varying(255),
    csvgenerationdefinition character varying(255),
    csvvalidationweb character varying(255),
    arxiuexpedientid character varying(255),
    arxiudocumentid character varying(255),
    printableurl character varying(255),
    enifileurl character varying(255),
    validationfileurl character varying(255)
);


ALTER TABLE public.efi_infoarxiu OWNER TO enviafib2;

--
-- TOC entry 184 (class 1259 OID 337088)
-- Name: efi_infosignatura_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_infosignatura_seq OWNER TO enviafib2;

--
-- TOC entry 185 (class 1259 OID 337090)
-- Name: efi_infosignatura; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_infosignatura (
    infosignaturaid bigint DEFAULT nextval('public.efi_infosignatura_seq'::regclass) NOT NULL,
    signoperation integer NOT NULL,
    signtype character varying(255) NOT NULL,
    signalgorithm character varying(255),
    signmode integer,
    signaturestablelocation integer,
    timestampincluded boolean,
    policyincluded boolean,
    enitipofirma character varying(255),
    eniperfilfirma character varying(255),
    enirolfirma character varying(255),
    enisignername character varying(255),
    enisigneradministrationid character varying(255),
    enisignlevel character varying(255),
    checkadministrationidofsigner boolean,
    checkdocumentmodifications boolean,
    checkvalidationsignature boolean
);


ALTER TABLE public.efi_infosignatura OWNER TO enviafib2;

--
-- TOC entry 186 (class 1259 OID 337097)
-- Name: efi_menu_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_menu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_menu_seq OWNER TO enviafib2;

--
-- TOC entry 187 (class 1259 OID 337099)
-- Name: efi_menu; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_menu (
    menuid bigint DEFAULT nextval('public.efi_menu_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255),
    titolmenuid bigint NOT NULL,
    ajudamenuid bigint NOT NULL,
    ordre integer NOT NULL,
    tipus integer NOT NULL,
    grupid bigint,
    parametretext text,
    parametrecombo character varying(255),
    actiu boolean DEFAULT false NOT NULL
);


ALTER TABLE public.efi_menu OWNER TO enviafib2;

--
-- TOC entry 188 (class 1259 OID 337107)
-- Name: efi_organitzacio_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_organitzacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_organitzacio_seq OWNER TO enviafib2;

--
-- TOC entry 189 (class 1259 OID 337109)
-- Name: efi_organitzacio; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_organitzacio (
    organitzacioid bigint DEFAULT nextval('public.efi_organitzacio_seq'::regclass) NOT NULL,
    codiconselleria character varying(100),
    codidirecciogeneral character varying(100),
    tipus character varying(100),
    valor character varying(255)
);


ALTER TABLE public.efi_organitzacio OWNER TO enviafib2;

--
-- TOC entry 190 (class 1259 OID 337116)
-- Name: efi_peticio_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_peticio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_peticio_seq OWNER TO enviafib2;

--
-- TOC entry 191 (class 1259 OID 337118)
-- Name: efi_peticio; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_peticio (
    peticioid bigint DEFAULT nextval('public.efi_peticio_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    solicitantid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    destinatarinif character varying(50),
    estat integer DEFAULT 1 NOT NULL,
    fitxer_firmatid bigint,
    tipusdocumental character varying(100) NOT NULL,
    idiomadoc character varying(30) NOT NULL,
    infosignaturaid bigint,
    tipus integer DEFAULT 0 NOT NULL,
    errormsg character varying(255),
    errorexception text,
    datafinal timestamp without time zone,
    peticioportafirmes character varying(255),
    nom character varying(255),
    reason character varying(255),
    arxiufuncionariusername character varying(255),
    arxiuparamfuncionarinom character varying(255),
    arxiuparamfuncionarinif character varying(255),
    arxiuparamfuncionaridir3 character varying(255),
    arxiureqparamdocestatelabora character varying(4),
    arxiureqparamorigen integer,
    arxiureqparaminteressats character varying(255),
    arxiureqparamciutadanif character varying(15),
    arxiureqparamciutadanom character varying(255),
    arxiureqparamorgans character varying(255),
    arxiuoptparamprocedimentcodi character varying(255),
    arxiuoptparamprocedimentnom character varying(255),
    arxiuoptparamseriedocumental character varying(255),
    arxiuoptparamexpedientid character varying(255),
    infoarxiuid bigint,
    revisor character varying(255)
);


ALTER TABLE public.efi_peticio OWNER TO enviafib2;

--
-- TOC entry 2188 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN efi_peticio.peticioportafirmes; Type: COMMENT; Schema: public; Owner: enviafib2
--

COMMENT ON COLUMN public.efi_peticio.peticioportafirmes IS 'Identificador de la petició dins el sistema de portafirmes';


--
-- TOC entry 2189 (class 0 OID 0)
-- Dependencies: 191
-- Name: COLUMN efi_peticio.nom; Type: COMMENT; Schema: public; Owner: enviafib2
--

COMMENT ON COLUMN public.efi_peticio.nom IS 'Nom de la peticio a PortaFIB.';


--
-- TOC entry 192 (class 1259 OID 337127)
-- Name: efi_plugin_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_plugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_plugin_seq OWNER TO enviafib2;

--
-- TOC entry 193 (class 1259 OID 337129)
-- Name: efi_plugin; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_plugin (
    pluginid bigint DEFAULT nextval('public.efi_plugin_seq'::regclass) NOT NULL,
    classe character varying(255),
    properties text,
    actiu boolean NOT NULL,
    tipus integer NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255) NOT NULL
);


ALTER TABLE public.efi_plugin OWNER TO enviafib2;

--
-- TOC entry 194 (class 1259 OID 337136)
-- Name: efi_seriedocumental_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_seriedocumental_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_seriedocumental_seq OWNER TO enviafib2;

--
-- TOC entry 195 (class 1259 OID 337138)
-- Name: efi_seriedocumental; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_seriedocumental (
    seriedocumentalid bigint DEFAULT nextval('public.efi_seriedocumental_seq'::regclass) NOT NULL,
    nom character varying(256) NOT NULL,
    tipusdocumental character varying(256),
    procedimentnom character varying NOT NULL,
    procedimentcodi character varying NOT NULL,
    entitatid character varying(50)
);


ALTER TABLE public.efi_seriedocumental OWNER TO enviafib2;

--
-- TOC entry 2190 (class 0 OID 0)
-- Dependencies: 195
-- Name: TABLE efi_seriedocumental; Type: COMMENT; Schema: public; Owner: enviafib2
--

COMMENT ON TABLE public.efi_seriedocumental IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';


--
-- TOC entry 196 (class 1259 OID 337145)
-- Name: efi_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_traduccio_seq OWNER TO enviafib2;

--
-- TOC entry 197 (class 1259 OID 337147)
-- Name: efi_traduccio; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_traduccio (
    traduccioid bigint DEFAULT nextval('public.efi_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.efi_traduccio OWNER TO enviafib2;

--
-- TOC entry 198 (class 1259 OID 337151)
-- Name: efi_traducciomap; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.efi_traducciomap OWNER TO enviafib2;

--
-- TOC entry 199 (class 1259 OID 337157)
-- Name: efi_usuari_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_usuari_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_usuari_seq OWNER TO enviafib2;

--
-- TOC entry 200 (class 1259 OID 337159)
-- Name: efi_usuari; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_usuari (
    usuariid bigint DEFAULT nextval('public.efi_usuari_seq'::regclass) NOT NULL,
    username character varying(100) NOT NULL,
    nom character varying(256) NOT NULL,
    llinatge1 character varying(256) NOT NULL,
    llinatge2 character varying(256),
    nif character varying(50) NOT NULL,
    email character varying(256) NOT NULL,
    idiomaid character varying(5) DEFAULT 'ca'::character varying NOT NULL,
    entitatid character varying(50) NOT NULL
);


ALTER TABLE public.efi_usuari OWNER TO enviafib2;

--
-- TOC entry 202 (class 1259 OID 362453)
-- Name: efi_usuarientitat_seq; Type: SEQUENCE; Schema: public; Owner: enviafib2
--

CREATE SEQUENCE public.efi_usuarientitat_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_usuarientitat_seq OWNER TO enviafib2;

--
-- TOC entry 203 (class 1259 OID 362455)
-- Name: efi_usuarientitat; Type: TABLE; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE TABLE public.efi_usuarientitat (
    usuarientitatid bigint DEFAULT nextval('public.efi_usuarientitat_seq'::regclass) NOT NULL,
    usuariid bigint NOT NULL,
    entitatid character varying(50) NOT NULL
);


ALTER TABLE public.efi_usuarientitat OWNER TO enviafib2;

--
-- TOC entry 2038 (class 2606 OID 354125)
-- Name: efi_entitat_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_pk PRIMARY KEY (entitatid);


--
-- TOC entry 1962 (class 2606 OID 337277)
-- Name: efi_faq_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_pk PRIMARY KEY (faqid);


--
-- TOC entry 1965 (class 2606 OID 337279)
-- Name: efi_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_fitxer
    ADD CONSTRAINT efi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1968 (class 2606 OID 337281)
-- Name: efi_grup_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_grup
    ADD CONSTRAINT efi_grup_pk PRIMARY KEY (grupid);


--
-- TOC entry 1972 (class 2606 OID 337283)
-- Name: efi_grupusuari_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_pk PRIMARY KEY (grupusuariid);


--
-- TOC entry 1975 (class 2606 OID 337285)
-- Name: efi_grupusuari_usuari_grup_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_grup_uk UNIQUE (usuariid, grupid);


--
-- TOC entry 1978 (class 2606 OID 337287)
-- Name: efi_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_idioma
    ADD CONSTRAINT efi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1983 (class 2606 OID 337289)
-- Name: efi_infoanex_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_infoanex
    ADD CONSTRAINT efi_infoanex_pk PRIMARY KEY (infoanexid);


--
-- TOC entry 1986 (class 2606 OID 337291)
-- Name: efi_infoarxiu_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_infoarxiu
    ADD CONSTRAINT efi_infoarxiu_pk PRIMARY KEY (infoarxiuid);


--
-- TOC entry 1989 (class 2606 OID 337293)
-- Name: efi_infosignatura_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_infosignatura
    ADD CONSTRAINT efi_infosignatura_pk PRIMARY KEY (infosignaturaid);


--
-- TOC entry 1994 (class 2606 OID 337295)
-- Name: efi_menu_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_pk PRIMARY KEY (menuid);


--
-- TOC entry 1998 (class 2606 OID 337297)
-- Name: efi_organitzacio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_organitzacio
    ADD CONSTRAINT efi_organitzacio_pk PRIMARY KEY (organitzacioid);


--
-- TOC entry 2006 (class 2606 OID 337299)
-- Name: efi_peticio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_pk PRIMARY KEY (peticioid);


--
-- TOC entry 2010 (class 2606 OID 337301)
-- Name: efi_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_plugin
    ADD CONSTRAINT efi_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 2014 (class 2606 OID 362496)
-- Name: efi_seriedocu_td_ent_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocu_td_ent_uk UNIQUE (tipusdocumental, entitatid);


--
-- TOC entry 2016 (class 2606 OID 337305)
-- Name: efi_seriedocumental_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocumental_pk PRIMARY KEY (seriedocumentalid);


--
-- TOC entry 2019 (class 2606 OID 337307)
-- Name: efi_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_traduccio
    ADD CONSTRAINT efi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 2024 (class 2606 OID 337309)
-- Name: efi_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 2028 (class 2606 OID 337311)
-- Name: efi_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 2030 (class 2606 OID 337313)
-- Name: efi_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 2042 (class 2606 OID 362460)
-- Name: efi_usuarientitat_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib2; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuarientitat
    ADD CONSTRAINT efi_usuarientitat_pk PRIMARY KEY (usuarientitatid);


--
-- TOC entry 2032 (class 1259 OID 354162)
-- Name: efi_entitat_faviconid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_faviconid_fk_i ON public.efi_entitat USING btree (faviconid);


--
-- TOC entry 2033 (class 1259 OID 354164)
-- Name: efi_entitat_logosegellid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_logosegellid_fk_i ON public.efi_entitat USING btree (logosegellid);


--
-- TOC entry 2034 (class 1259 OID 354165)
-- Name: efi_entitat_logowebid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_logowebid_fk_i ON public.efi_entitat USING btree (logowebid);


--
-- TOC entry 2035 (class 1259 OID 354166)
-- Name: efi_entitat_logowebpeuid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_logowebpeuid_fk_i ON public.efi_entitat USING btree (logowebpeuid);


--
-- TOC entry 2036 (class 1259 OID 354167)
-- Name: efi_entitat_motiudele_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_motiudele_fk_i ON public.efi_entitat USING btree (motiudelegacioid);


--
-- TOC entry 2039 (class 1259 OID 354169)
-- Name: efi_entitat_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_entitat_pk_i ON public.efi_entitat USING btree (entitatid);


--
-- TOC entry 1958 (class 1259 OID 337314)
-- Name: efi_faq_fitxer1id_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_faq_fitxer1id_fk_i ON public.efi_faq USING btree (fitxer1id);


--
-- TOC entry 1959 (class 1259 OID 337315)
-- Name: efi_faq_fitxer2id_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_faq_fitxer2id_fk_i ON public.efi_faq USING btree (fitxer2id);


--
-- TOC entry 1960 (class 1259 OID 337316)
-- Name: efi_faq_fitxer3id_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_faq_fitxer3id_fk_i ON public.efi_faq USING btree (fitxer3id);


--
-- TOC entry 1963 (class 1259 OID 337317)
-- Name: efi_faq_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_faq_pk_i ON public.efi_faq USING btree (faqid);


--
-- TOC entry 1966 (class 1259 OID 337318)
-- Name: efi_fitxer_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_fitxer_pk_i ON public.efi_fitxer USING btree (fitxerid);


--
-- TOC entry 1969 (class 1259 OID 337319)
-- Name: efi_grup_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_grup_pk_i ON public.efi_grup USING btree (grupid);


--
-- TOC entry 1970 (class 1259 OID 337320)
-- Name: efi_grupusuari_grupid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_grupusuari_grupid_fk_i ON public.efi_grupusuari USING btree (grupid);


--
-- TOC entry 1973 (class 1259 OID 337321)
-- Name: efi_grupusuari_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_grupusuari_pk_i ON public.efi_grupusuari USING btree (grupusuariid);


--
-- TOC entry 1976 (class 1259 OID 337322)
-- Name: efi_grupusuari_usuariid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_grupusuari_usuariid_fk_i ON public.efi_grupusuari USING btree (usuariid);


--
-- TOC entry 1979 (class 1259 OID 337323)
-- Name: efi_idioma_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_idioma_pk_i ON public.efi_idioma USING btree (idiomaid);


--
-- TOC entry 1980 (class 1259 OID 337324)
-- Name: efi_infoanex_anexid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_infoanex_anexid_fk_i ON public.efi_infoanex USING btree (anexid);


--
-- TOC entry 1981 (class 1259 OID 337325)
-- Name: efi_infoanex_peticioid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_infoanex_peticioid_fk_i ON public.efi_infoanex USING btree (peticioid);


--
-- TOC entry 1984 (class 1259 OID 337326)
-- Name: efi_infoanex_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_infoanex_pk_i ON public.efi_infoanex USING btree (infoanexid);


--
-- TOC entry 1987 (class 1259 OID 337327)
-- Name: efi_infoarxiu_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_infoarxiu_pk_i ON public.efi_infoarxiu USING btree (infoarxiuid);


--
-- TOC entry 1990 (class 1259 OID 337328)
-- Name: efi_infosignatura_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_infosignatura_pk_i ON public.efi_infosignatura USING btree (infosignaturaid);


--
-- TOC entry 1991 (class 1259 OID 337329)
-- Name: efi_menu_ajudamenuid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_menu_ajudamenuid_fk_i ON public.efi_menu USING btree (ajudamenuid);


--
-- TOC entry 1992 (class 1259 OID 337330)
-- Name: efi_menu_grupid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_menu_grupid_fk_i ON public.efi_menu USING btree (grupid);


--
-- TOC entry 1995 (class 1259 OID 337331)
-- Name: efi_menu_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_menu_pk_i ON public.efi_menu USING btree (menuid);


--
-- TOC entry 1996 (class 1259 OID 337332)
-- Name: efi_menu_titolmenuid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_menu_titolmenuid_fk_i ON public.efi_menu USING btree (titolmenuid);


--
-- TOC entry 1999 (class 1259 OID 337333)
-- Name: efi_organitzacio_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_organitzacio_pk_i ON public.efi_organitzacio USING btree (organitzacioid);


--
-- TOC entry 2000 (class 1259 OID 337334)
-- Name: efi_peticio_fitxer_firma_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_fitxer_firma_fk_i ON public.efi_peticio USING btree (fitxer_firmatid);


--
-- TOC entry 2001 (class 1259 OID 337335)
-- Name: efi_peticio_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_fitxerid_fk_i ON public.efi_peticio USING btree (fitxerid);


--
-- TOC entry 2002 (class 1259 OID 337336)
-- Name: efi_peticio_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_idiomaid_fk_i ON public.efi_peticio USING btree (idiomaid);


--
-- TOC entry 2003 (class 1259 OID 337337)
-- Name: efi_peticio_infoarxiuid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_infoarxiuid_fk_i ON public.efi_peticio USING btree (infoarxiuid);


--
-- TOC entry 2004 (class 1259 OID 337338)
-- Name: efi_peticio_infosignid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_infosignid_fk_i ON public.efi_peticio USING btree (infosignaturaid);


--
-- TOC entry 2007 (class 1259 OID 337339)
-- Name: efi_peticio_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_pk_i ON public.efi_peticio USING btree (peticioid);


--
-- TOC entry 2008 (class 1259 OID 337340)
-- Name: efi_peticio_solicitantid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_peticio_solicitantid_fk_i ON public.efi_peticio USING btree (solicitantid);


--
-- TOC entry 2011 (class 1259 OID 337341)
-- Name: efi_plugin_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_plugin_pk_i ON public.efi_plugin USING btree (pluginid);


--
-- TOC entry 2012 (class 1259 OID 362489)
-- Name: efi_seriedocu_entitatid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_seriedocu_entitatid_fk_i ON public.efi_seriedocumental USING btree (entitatid);


--
-- TOC entry 2017 (class 1259 OID 337342)
-- Name: efi_seriedocumental_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_seriedocumental_pk_i ON public.efi_seriedocumental USING btree (seriedocumentalid);


--
-- TOC entry 2020 (class 1259 OID 337343)
-- Name: efi_traduccio_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_traduccio_pk_i ON public.efi_traduccio USING btree (traduccioid);


--
-- TOC entry 2021 (class 1259 OID 337344)
-- Name: efi_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_traducciomap_idiomaid_fk_i ON public.efi_traducciomap USING btree (idiomaid);


--
-- TOC entry 2022 (class 1259 OID 337345)
-- Name: efi_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_traducciomap_pk_i ON public.efi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 2025 (class 1259 OID 354175)
-- Name: efi_usuari_entitatid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuari_entitatid_fk_i ON public.efi_usuari USING btree (entitatid);


--
-- TOC entry 2026 (class 1259 OID 337346)
-- Name: efi_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuari_idiomaid_fk_i ON public.efi_usuari USING btree (idiomaid);


--
-- TOC entry 2031 (class 1259 OID 337347)
-- Name: efi_usuari_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuari_pk_i ON public.efi_usuari USING btree (usuariid);


--
-- TOC entry 2040 (class 1259 OID 362473)
-- Name: efi_usuarientitat_entitat_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuarientitat_entitat_fk_i ON public.efi_usuarientitat USING btree (entitatid);


--
-- TOC entry 2043 (class 1259 OID 362471)
-- Name: efi_usuarientitat_pk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuarientitat_pk_i ON public.efi_usuarientitat USING btree (usuarientitatid);


--
-- TOC entry 2044 (class 1259 OID 362472)
-- Name: efi_usuarientitat_usuari_fk_i; Type: INDEX; Schema: public; Owner: enviafib2; Tablespace: 
--

CREATE INDEX efi_usuarientitat_usuari_fk_i ON public.efi_usuarientitat USING btree (usuariid);


--
-- TOC entry 2065 (class 2606 OID 354126)
-- Name: efi_entitat_fitxer_icon_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_fitxer_icon_fk FOREIGN KEY (faviconid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2066 (class 2606 OID 354131)
-- Name: efi_entitat_fitxer_loca_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_fitxer_loca_fk FOREIGN KEY (logowebid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2067 (class 2606 OID 354136)
-- Name: efi_entitat_fitxer_lope_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_fitxer_lope_fk FOREIGN KEY (logowebpeuid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2068 (class 2606 OID 354141)
-- Name: efi_entitat_fitxer_lose_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_fitxer_lose_fk FOREIGN KEY (logosegellid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2069 (class 2606 OID 354156)
-- Name: efi_entitat_traduccio_moti_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_entitat
    ADD CONSTRAINT efi_entitat_traduccio_moti_fk FOREIGN KEY (motiudelegacioid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2045 (class 2606 OID 337348)
-- Name: efi_faq_fitxer_fitxer1id_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer1id_fk FOREIGN KEY (fitxer1id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2046 (class 2606 OID 337353)
-- Name: efi_faq_fitxer_fitxer2id_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer2id_fk FOREIGN KEY (fitxer2id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2047 (class 2606 OID 337358)
-- Name: efi_faq_fitxer_fitxer3id_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer3id_fk FOREIGN KEY (fitxer3id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2048 (class 2606 OID 337363)
-- Name: efi_grupusuari_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 2049 (class 2606 OID 337368)
-- Name: efi_grupusuari_usuari_usuar_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_usuar_fk FOREIGN KEY (usuariid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2050 (class 2606 OID 337373)
-- Name: efi_infoanex_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_infoanex
    ADD CONSTRAINT efi_infoanex_fitxer_fk FOREIGN KEY (anexid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2051 (class 2606 OID 337378)
-- Name: efi_infoanex_peticio_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_infoanex
    ADD CONSTRAINT efi_infoanex_peticio_fk FOREIGN KEY (peticioid) REFERENCES public.efi_peticio(peticioid);


--
-- TOC entry 2052 (class 2606 OID 337383)
-- Name: efi_menu_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 2053 (class 2606 OID 337388)
-- Name: efi_menu_traduccio_ajuda_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2054 (class 2606 OID 337393)
-- Name: efi_menu_traduccio_titol_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2055 (class 2606 OID 337398)
-- Name: efi_peticio_fitxer_ffirm_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_ffirm_fk FOREIGN KEY (fitxer_firmatid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2056 (class 2606 OID 337403)
-- Name: efi_peticio_fitxer_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2057 (class 2606 OID 337408)
-- Name: efi_peticio_idioma_idiid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_idioma_idiid_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- TOC entry 2058 (class 2606 OID 337413)
-- Name: efi_peticio_infoarxiu_infoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES public.efi_infoarxiu(infoarxiuid);


--
-- TOC entry 2059 (class 2606 OID 337418)
-- Name: efi_peticio_infosign_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES public.efi_infosignatura(infosignaturaid);


--
-- TOC entry 2060 (class 2606 OID 337423)
-- Name: efi_peticio_usuari_soli_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_usuari_soli_fk FOREIGN KEY (solicitantid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2061 (class 2606 OID 362490)
-- Name: efi_seriedocu_entitat_entit_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocu_entitat_entit_fk FOREIGN KEY (entitatid) REFERENCES public.efi_entitat(entitatid);


--
-- TOC entry 2062 (class 2606 OID 337428)
-- Name: efi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2071 (class 2606 OID 362479)
-- Name: efi_usrent_entitat_entitati_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_usuarientitat
    ADD CONSTRAINT efi_usrent_entitat_entitati_fk FOREIGN KEY (entitatid) REFERENCES public.efi_entitat(entitatid);


--
-- TOC entry 2070 (class 2606 OID 362474)
-- Name: efi_usrent_usuari_usuariid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_usuarientitat
    ADD CONSTRAINT efi_usrent_usuari_usuariid_fk FOREIGN KEY (usuariid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2064 (class 2606 OID 354170)
-- Name: efi_usuari_entitat_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_entitat_fk FOREIGN KEY (entitatid) REFERENCES public.efi_entitat(entitatid);


--
-- TOC entry 2063 (class 2606 OID 337433)
-- Name: efi_usuari_idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib2
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- TOC entry 2186 (class 0 OID 0)
-- Dependencies: 7
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2025-02-06 11:19:17

--
-- PostgreSQL database dump complete
--

