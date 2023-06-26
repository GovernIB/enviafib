--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2023-06-26 13:06:53

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 1 (class 3079 OID 11750)
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: -
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- TOC entry 2134 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 198 (class 1259 OID 238240)
-- Name: efi_faq_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_faq_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_with_oids = false;

--
-- TOC entry 199 (class 1259 OID 238242)
-- Name: efi_faq; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 171 (class 1259 OID 57626)
-- Name: efi_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 172 (class 1259 OID 57628)
-- Name: efi_fitxer; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_fitxer (
    fitxerid bigint DEFAULT nextval('public.efi_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


--
-- TOC entry 190 (class 1259 OID 82299)
-- Name: efi_grup_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_grup_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 191 (class 1259 OID 82301)
-- Name: efi_grup; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_grup (
    grupid bigint DEFAULT nextval('public.efi_grup_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255)
);


--
-- TOC entry 192 (class 1259 OID 82319)
-- Name: efi_grupusuari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_grupusuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 193 (class 1259 OID 82328)
-- Name: efi_grupusuari; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_grupusuari (
    grupusuariid bigint DEFAULT nextval('public.efi_grupusuari_seq'::regclass) NOT NULL,
    grupid bigint NOT NULL,
    usuariid bigint NOT NULL
);


--
-- TOC entry 173 (class 1259 OID 57636)
-- Name: efi_idioma; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


--
-- TOC entry 188 (class 1259 OID 82282)
-- Name: efi_infoarxiu_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_infoarxiu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 189 (class 1259 OID 82284)
-- Name: efi_infoarxiu; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 187 (class 1259 OID 74119)
-- Name: efi_infocustody_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_infocustody_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 185 (class 1259 OID 74057)
-- Name: efi_infosignatura_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 186 (class 1259 OID 74059)
-- Name: efi_infosignatura; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 194 (class 1259 OID 123259)
-- Name: efi_menu_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_menu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 195 (class 1259 OID 123261)
-- Name: efi_menu; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 196 (class 1259 OID 123296)
-- Name: efi_organitzacio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_organitzacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 197 (class 1259 OID 123298)
-- Name: efi_organitzacio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_organitzacio (
    organitzacioid bigint DEFAULT nextval('public.efi_organitzacio_seq'::regclass) NOT NULL,
    codiconselleria character varying(100),
    codidirecciogeneral character varying(100),
    tipus character varying(100),
    valor character varying(255)
);


--
-- TOC entry 174 (class 1259 OID 57641)
-- Name: efi_peticio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_peticio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 175 (class 1259 OID 57643)
-- Name: efi_peticio; Type: TABLE; Schema: public; Owner: -
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
    infoarxiuid bigint
);


--
-- TOC entry 2135 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN efi_peticio.peticioportafirmes; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.efi_peticio.peticioportafirmes IS 'Identificador de la petició dins el sistema de portafirmes';


--
-- TOC entry 2136 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN efi_peticio.nom; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.efi_peticio.nom IS 'Nom de la peticio a PortaFIB.';


--
-- TOC entry 176 (class 1259 OID 57647)
-- Name: efi_plugin_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_plugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 177 (class 1259 OID 57649)
-- Name: efi_plugin; Type: TABLE; Schema: public; Owner: -
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


--
-- TOC entry 183 (class 1259 OID 65825)
-- Name: efi_seriedocumental_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_seriedocumental_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 184 (class 1259 OID 65827)
-- Name: efi_seriedocumental; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_seriedocumental (
    seriedocumentalid bigint DEFAULT nextval('public.efi_seriedocumental_seq'::regclass) NOT NULL,
    nom character varying(256) NOT NULL,
    tipusdocumental character varying(256),
    procedimentnom character varying NOT NULL,
    procedimentcodi character varying NOT NULL
);


--
-- TOC entry 2137 (class 0 OID 0)
-- Dependencies: 184
-- Name: TABLE efi_seriedocumental; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.efi_seriedocumental IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';


--
-- TOC entry 178 (class 1259 OID 57656)
-- Name: efi_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 179 (class 1259 OID 57658)
-- Name: efi_traduccio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_traduccio (
    traduccioid bigint DEFAULT nextval('public.efi_traduccio_seq'::regclass) NOT NULL
);


--
-- TOC entry 180 (class 1259 OID 57662)
-- Name: efi_traducciomap; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


--
-- TOC entry 181 (class 1259 OID 57668)
-- Name: efi_usuari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_usuari_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 182 (class 1259 OID 57670)
-- Name: efi_usuari; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_usuari (
    usuariid bigint DEFAULT nextval('public.efi_usuari_seq'::regclass) NOT NULL,
    username character varying(100) NOT NULL,
    nom character varying(256) NOT NULL,
    llinatge1 character varying(256) NOT NULL,
    llinatge2 character varying(256),
    nif character varying(50) NOT NULL,
    email character varying(256) NOT NULL,
    idiomaid character varying(5) DEFAULT 'ca'::character varying NOT NULL
);


--
-- TOC entry 2002 (class 2606 OID 238250)
-- Name: efi_faq_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_pk PRIMARY KEY (faqid);


--
-- TOC entry 1938 (class 2606 OID 57678)
-- Name: efi_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_fitxer
    ADD CONSTRAINT efi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1980 (class 2606 OID 82309)
-- Name: efi_grup_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grup
    ADD CONSTRAINT efi_grup_pk PRIMARY KEY (grupid);


--
-- TOC entry 1984 (class 2606 OID 82357)
-- Name: efi_grupusuari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_pk PRIMARY KEY (grupusuariid);


--
-- TOC entry 1987 (class 2606 OID 82379)
-- Name: efi_grupusuari_usuari_grup_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_grup_uk UNIQUE (usuariid, grupid);


--
-- TOC entry 1941 (class 2606 OID 57680)
-- Name: efi_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_idioma
    ADD CONSTRAINT efi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1977 (class 2606 OID 82292)
-- Name: efi_infoarxiu_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_infoarxiu
    ADD CONSTRAINT efi_infoarxiu_pk PRIMARY KEY (infoarxiuid);


--
-- TOC entry 1974 (class 2606 OID 74067)
-- Name: efi_infosignatura_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_infosignatura
    ADD CONSTRAINT efi_infosignatura_pk PRIMARY KEY (infosignaturaid);


--
-- TOC entry 1992 (class 2606 OID 123270)
-- Name: efi_menu_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_pk PRIMARY KEY (menuid);


--
-- TOC entry 1996 (class 2606 OID 123306)
-- Name: efi_organitzacio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_organitzacio
    ADD CONSTRAINT efi_organitzacio_pk PRIMARY KEY (organitzacioid);


--
-- TOC entry 1949 (class 2606 OID 57682)
-- Name: efi_peticio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_pk PRIMARY KEY (peticioid);


--
-- TOC entry 1953 (class 2606 OID 57684)
-- Name: efi_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_plugin
    ADD CONSTRAINT efi_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 1969 (class 2606 OID 65839)
-- Name: efi_seriedocu_tipusdocu_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocu_tipusdocu_uk UNIQUE (tipusdocumental);


--
-- TOC entry 1971 (class 2606 OID 65835)
-- Name: efi_seriedocumental_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocumental_pk PRIMARY KEY (seriedocumentalid);


--
-- TOC entry 1956 (class 2606 OID 57686)
-- Name: efi_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traduccio
    ADD CONSTRAINT efi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1961 (class 2606 OID 57688)
-- Name: efi_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1964 (class 2606 OID 57690)
-- Name: efi_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 1966 (class 2606 OID 57692)
-- Name: efi_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 1998 (class 1259 OID 238267)
-- Name: efi_faq_fitxer1id_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_faq_fitxer1id_fk_i ON public.efi_faq USING btree (fitxer1id);


--
-- TOC entry 1999 (class 1259 OID 238268)
-- Name: efi_faq_fitxer2id_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_faq_fitxer2id_fk_i ON public.efi_faq USING btree (fitxer2id);


--
-- TOC entry 2000 (class 1259 OID 238269)
-- Name: efi_faq_fitxer3id_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_faq_fitxer3id_fk_i ON public.efi_faq USING btree (fitxer3id);


--
-- TOC entry 2003 (class 1259 OID 238266)
-- Name: efi_faq_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_faq_pk_i ON public.efi_faq USING btree (faqid);


--
-- TOC entry 1939 (class 1259 OID 57693)
-- Name: efi_fitxer_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_fitxer_pk_i ON public.efi_fitxer USING btree (fitxerid);


--
-- TOC entry 1981 (class 1259 OID 82368)
-- Name: efi_grup_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grup_pk_i ON public.efi_grup USING btree (grupid);


--
-- TOC entry 1982 (class 1259 OID 82370)
-- Name: efi_grupusuari_grupid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_grupid_fk_i ON public.efi_grupusuari USING btree (grupid);


--
-- TOC entry 1985 (class 1259 OID 82369)
-- Name: efi_grupusuari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_pk_i ON public.efi_grupusuari USING btree (grupusuariid);


--
-- TOC entry 1988 (class 1259 OID 82371)
-- Name: efi_grupusuari_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_usuariid_fk_i ON public.efi_grupusuari USING btree (usuariid);


--
-- TOC entry 1942 (class 1259 OID 57694)
-- Name: efi_idioma_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_idioma_pk_i ON public.efi_idioma USING btree (idiomaid);


--
-- TOC entry 1978 (class 1259 OID 90474)
-- Name: efi_infoarxiu_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_infoarxiu_pk_i ON public.efi_infoarxiu USING btree (infoarxiuid);


--
-- TOC entry 1975 (class 1259 OID 74068)
-- Name: efi_infosignatura_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_infosignatura_pk_i ON public.efi_infosignatura USING btree (infosignaturaid);


--
-- TOC entry 1989 (class 1259 OID 123288)
-- Name: efi_menu_ajudamenuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_ajudamenuid_fk_i ON public.efi_menu USING btree (ajudamenuid);


--
-- TOC entry 1990 (class 1259 OID 123289)
-- Name: efi_menu_grupid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_grupid_fk_i ON public.efi_menu USING btree (grupid);


--
-- TOC entry 1993 (class 1259 OID 123286)
-- Name: efi_menu_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_pk_i ON public.efi_menu USING btree (menuid);


--
-- TOC entry 1994 (class 1259 OID 123287)
-- Name: efi_menu_titolmenuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_titolmenuid_fk_i ON public.efi_menu USING btree (titolmenuid);


--
-- TOC entry 1997 (class 1259 OID 123307)
-- Name: efi_organitzacio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_organitzacio_pk_i ON public.efi_organitzacio USING btree (organitzacioid);


--
-- TOC entry 1943 (class 1259 OID 65841)
-- Name: efi_peticio_fitxer_firma_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_fitxer_firma_fk_i ON public.efi_peticio USING btree (fitxer_firmatid);


--
-- TOC entry 1944 (class 1259 OID 57695)
-- Name: efi_peticio_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_fitxerid_fk_i ON public.efi_peticio USING btree (fitxerid);


--
-- TOC entry 1945 (class 1259 OID 57696)
-- Name: efi_peticio_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_idiomaid_fk_i ON public.efi_peticio USING btree (idiomaid);


--
-- TOC entry 1946 (class 1259 OID 82298)
-- Name: efi_peticio_infoarxiuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_infoarxiuid_fk_i ON public.efi_peticio USING btree (infoarxiuid);


--
-- TOC entry 1947 (class 1259 OID 74074)
-- Name: efi_peticio_infosignid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_infosignid_fk_i ON public.efi_peticio USING btree (infosignaturaid);


--
-- TOC entry 1950 (class 1259 OID 57697)
-- Name: efi_peticio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_pk_i ON public.efi_peticio USING btree (peticioid);


--
-- TOC entry 1951 (class 1259 OID 57698)
-- Name: efi_peticio_solicitantid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_solicitantid_fk_i ON public.efi_peticio USING btree (solicitantid);


--
-- TOC entry 1954 (class 1259 OID 57700)
-- Name: efi_plugin_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_plugin_pk_i ON public.efi_plugin USING btree (pluginid);


--
-- TOC entry 1972 (class 1259 OID 65842)
-- Name: efi_seriedocumental_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_seriedocumental_pk_i ON public.efi_seriedocumental USING btree (seriedocumentalid);


--
-- TOC entry 1957 (class 1259 OID 57701)
-- Name: efi_traduccio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traduccio_pk_i ON public.efi_traduccio USING btree (traduccioid);


--
-- TOC entry 1958 (class 1259 OID 57702)
-- Name: efi_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traducciomap_idiomaid_fk_i ON public.efi_traducciomap USING btree (idiomaid);


--
-- TOC entry 1959 (class 1259 OID 57703)
-- Name: efi_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traducciomap_pk_i ON public.efi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1962 (class 1259 OID 90475)
-- Name: efi_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_usuari_idiomaid_fk_i ON public.efi_usuari USING btree (idiomaid);


--
-- TOC entry 1967 (class 1259 OID 57704)
-- Name: efi_usuari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_usuari_pk_i ON public.efi_usuari USING btree (usuariid);


--
-- TOC entry 2017 (class 2606 OID 238251)
-- Name: efi_faq_fitxer_fitxer1id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer1id_fk FOREIGN KEY (fitxer1id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2018 (class 2606 OID 238256)
-- Name: efi_faq_fitxer_fitxer2id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer2id_fk FOREIGN KEY (fitxer2id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2019 (class 2606 OID 238261)
-- Name: efi_faq_fitxer_fitxer3id_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_faq
    ADD CONSTRAINT efi_faq_fitxer_fitxer3id_fk FOREIGN KEY (fitxer3id) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2012 (class 2606 OID 82346)
-- Name: efi_grupusuari_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 2013 (class 2606 OID 82351)
-- Name: efi_grupusuari_usuari_usuar_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_usuar_fk FOREIGN KEY (usuariid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2016 (class 2606 OID 123281)
-- Name: efi_menu_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 2015 (class 2606 OID 123276)
-- Name: efi_menu_traduccio_ajuda_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2014 (class 2606 OID 123271)
-- Name: efi_menu_traduccio_titol_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2007 (class 2606 OID 57740)
-- Name: efi_peticio_fitxer_ffirm_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_ffirm_fk FOREIGN KEY (fitxer_firmatid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2004 (class 2606 OID 57705)
-- Name: efi_peticio_fitxer_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 2005 (class 2606 OID 57710)
-- Name: efi_peticio_idioma_idiid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_idioma_idiid_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- TOC entry 2009 (class 2606 OID 82293)
-- Name: efi_peticio_infoarxiu_infoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES public.efi_infoarxiu(infoarxiuid);


--
-- TOC entry 2008 (class 2606 OID 74069)
-- Name: efi_peticio_infosign_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES public.efi_infosignatura(infosignaturaid);


--
-- TOC entry 2006 (class 2606 OID 57720)
-- Name: efi_peticio_usuari_soli_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_usuari_soli_fk FOREIGN KEY (solicitantid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2010 (class 2606 OID 57725)
-- Name: efi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2011 (class 2606 OID 74114)
-- Name: efi_usuari_idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


-- Completed on 2023-06-26 13:06:54

--
-- PostgreSQL database dump complete
--

