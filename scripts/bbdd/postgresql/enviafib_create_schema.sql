--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2022-11-11 13:20:16

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
-- TOC entry 2115 (class 0 OID 0)
-- Dependencies: 1
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- TOC entry 171 (class 1259 OID 72983)
-- Name: efi_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_with_oids = false;

--
-- TOC entry 172 (class 1259 OID 72985)
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
-- TOC entry 189 (class 1259 OID 73211)
-- Name: efi_grup_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_grup_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 190 (class 1259 OID 73213)
-- Name: efi_grup; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_grup (
    grupid bigint DEFAULT nextval('public.efi_grup_seq'::regclass) NOT NULL,
    nom character varying(255) NOT NULL,
    descripcio character varying(255)
);


--
-- TOC entry 191 (class 1259 OID 73223)
-- Name: efi_grupusuari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_grupusuari_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 192 (class 1259 OID 73225)
-- Name: efi_grupusuari; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_grupusuari (
    grupusuariid bigint DEFAULT nextval('public.efi_grupusuari_seq'::regclass) NOT NULL,
    grupid bigint,
    usuariid bigint
);


--
-- TOC entry 173 (class 1259 OID 72993)
-- Name: efi_idioma; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


--
-- TOC entry 187 (class 1259 OID 73194)
-- Name: efi_infoarxiu_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_infoarxiu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 188 (class 1259 OID 73196)
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
-- TOC entry 185 (class 1259 OID 73110)
-- Name: efi_infosignatura_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_infosignatura_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 186 (class 1259 OID 73112)
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
-- TOC entry 193 (class 1259 OID 73359)
-- Name: efi_menu_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_menu_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 194 (class 1259 OID 73361)
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
-- TOC entry 195 (class 1259 OID 73401)
-- Name: efi_organitzacio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_organitzacio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 196 (class 1259 OID 73403)
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
-- TOC entry 174 (class 1259 OID 72998)
-- Name: efi_peticio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_peticio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 175 (class 1259 OID 73000)
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
-- TOC entry 2116 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN efi_peticio.peticioportafirmes; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.efi_peticio.peticioportafirmes IS 'Identificador de la petició dins el sistema de portafirmes';


--
-- TOC entry 2117 (class 0 OID 0)
-- Dependencies: 175
-- Name: COLUMN efi_peticio.nom; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON COLUMN public.efi_peticio.nom IS 'Nom de la peticio.';


--
-- TOC entry 176 (class 1259 OID 73005)
-- Name: efi_plugin_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_plugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 177 (class 1259 OID 73007)
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
-- TOC entry 178 (class 1259 OID 73014)
-- Name: efi_seriedocumental_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_seriedocumental_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 179 (class 1259 OID 73016)
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
-- TOC entry 2118 (class 0 OID 0)
-- Dependencies: 179
-- Name: TABLE efi_seriedocumental; Type: COMMENT; Schema: public; Owner: -
--

COMMENT ON TABLE public.efi_seriedocumental IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';


--
-- TOC entry 180 (class 1259 OID 73023)
-- Name: efi_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 181 (class 1259 OID 73025)
-- Name: efi_traduccio; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_traduccio (
    traduccioid bigint DEFAULT nextval('public.efi_traduccio_seq'::regclass) NOT NULL
);


--
-- TOC entry 182 (class 1259 OID 73029)
-- Name: efi_traducciomap; Type: TABLE; Schema: public; Owner: -
--

CREATE TABLE public.efi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


--
-- TOC entry 183 (class 1259 OID 73035)
-- Name: efi_usuari_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE public.efi_usuari_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 184 (class 1259 OID 73037)
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
-- TOC entry 1928 (class 2606 OID 73045)
-- Name: efi_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_fitxer
    ADD CONSTRAINT efi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1970 (class 2606 OID 73221)
-- Name: efi_grup_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grup
    ADD CONSTRAINT efi_grup_pk PRIMARY KEY (grupid);


--
-- TOC entry 1974 (class 2606 OID 73230)
-- Name: efi_grupusuari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_pk PRIMARY KEY (grupusuariid);


--
-- TOC entry 1977 (class 2606 OID 73242)
-- Name: efi_grupusuari_usuari_grup_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_grup_uk UNIQUE (usuariid, grupid);


--
-- TOC entry 1931 (class 2606 OID 73047)
-- Name: efi_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_idioma
    ADD CONSTRAINT efi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1967 (class 2606 OID 73204)
-- Name: efi_infoarxiu_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_infoarxiu
    ADD CONSTRAINT efi_infoarxiu_pk PRIMARY KEY (infoarxiuid);


--
-- TOC entry 1964 (class 2606 OID 73120)
-- Name: efi_infosignatura_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_infosignatura
    ADD CONSTRAINT efi_infosignatura_pk PRIMARY KEY (infosignaturaid);


--
-- TOC entry 1982 (class 2606 OID 73369)
-- Name: efi_menu_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_pk PRIMARY KEY (menuid);


--
-- TOC entry 1986 (class 2606 OID 73411)
-- Name: efi_organitzacio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_organitzacio
    ADD CONSTRAINT efi_organitzacio_pk PRIMARY KEY (organitzacioid);


--
-- TOC entry 1939 (class 2606 OID 73049)
-- Name: efi_peticio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_pk PRIMARY KEY (peticioid);


--
-- TOC entry 1943 (class 2606 OID 73051)
-- Name: efi_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_plugin
    ADD CONSTRAINT efi_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 1946 (class 2606 OID 73055)
-- Name: efi_seriedocu_tipusdocu_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocu_tipusdocu_uk UNIQUE (tipusdocumental);


--
-- TOC entry 1948 (class 2606 OID 73053)
-- Name: efi_seriedocumental_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_seriedocumental
    ADD CONSTRAINT efi_seriedocumental_pk PRIMARY KEY (seriedocumentalid);


--
-- TOC entry 1951 (class 2606 OID 73057)
-- Name: efi_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traduccio
    ADD CONSTRAINT efi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1956 (class 2606 OID 73059)
-- Name: efi_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1959 (class 2606 OID 73061)
-- Name: efi_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 1961 (class 2606 OID 73063)
-- Name: efi_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 1929 (class 1259 OID 73064)
-- Name: efi_fitxer_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_fitxer_pk_i ON public.efi_fitxer USING btree (fitxerid);


--
-- TOC entry 1971 (class 1259 OID 73222)
-- Name: efi_grup_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grup_pk_i ON public.efi_grup USING btree (grupid);


--
-- TOC entry 1972 (class 1259 OID 73244)
-- Name: efi_grupusuari_grupid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_grupid_fk_i ON public.efi_grupusuari USING btree (grupid);


--
-- TOC entry 1975 (class 1259 OID 73243)
-- Name: efi_grupusuari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_pk_i ON public.efi_grupusuari USING btree (grupusuariid);


--
-- TOC entry 1978 (class 1259 OID 73245)
-- Name: efi_grupusuari_usuariid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_grupusuari_usuariid_fk_i ON public.efi_grupusuari USING btree (usuariid);


--
-- TOC entry 1932 (class 1259 OID 73065)
-- Name: efi_idioma_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_idioma_pk_i ON public.efi_idioma USING btree (idiomaid);


--
-- TOC entry 1968 (class 1259 OID 73298)
-- Name: efi_infoarxiu_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_infoarxiu_pk_i ON public.efi_infoarxiu USING btree (infoarxiuid);


--
-- TOC entry 1965 (class 1259 OID 73121)
-- Name: efi_infosignatura_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_infosignatura_pk_i ON public.efi_infosignatura USING btree (infosignaturaid);


--
-- TOC entry 1979 (class 1259 OID 73387)
-- Name: efi_menu_ajudamenuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_ajudamenuid_fk_i ON public.efi_menu USING btree (ajudamenuid);


--
-- TOC entry 1980 (class 1259 OID 73388)
-- Name: efi_menu_grupid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_grupid_fk_i ON public.efi_menu USING btree (grupid);


--
-- TOC entry 1983 (class 1259 OID 73385)
-- Name: efi_menu_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_pk_i ON public.efi_menu USING btree (menuid);


--
-- TOC entry 1984 (class 1259 OID 73386)
-- Name: efi_menu_titolmenuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_menu_titolmenuid_fk_i ON public.efi_menu USING btree (titolmenuid);


--
-- TOC entry 1987 (class 1259 OID 73412)
-- Name: efi_organitzacio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_organitzacio_pk_i ON public.efi_organitzacio USING btree (organitzacioid);


--
-- TOC entry 1933 (class 1259 OID 73107)
-- Name: efi_peticio_fitxer_firma_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_fitxer_firma_fk_i ON public.efi_peticio USING btree (fitxer_firmatid);


--
-- TOC entry 1934 (class 1259 OID 73066)
-- Name: efi_peticio_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_fitxerid_fk_i ON public.efi_peticio USING btree (fitxerid);


--
-- TOC entry 1935 (class 1259 OID 73067)
-- Name: efi_peticio_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_idiomaid_fk_i ON public.efi_peticio USING btree (idiomaid);


--
-- TOC entry 1936 (class 1259 OID 73210)
-- Name: efi_peticio_infoarxiuid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_infoarxiuid_fk_i ON public.efi_peticio USING btree (infoarxiuid);


--
-- TOC entry 1937 (class 1259 OID 73155)
-- Name: efi_peticio_infosignid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_infosignid_fk_i ON public.efi_peticio USING btree (infosignaturaid);


--
-- TOC entry 1940 (class 1259 OID 73068)
-- Name: efi_peticio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_pk_i ON public.efi_peticio USING btree (peticioid);


--
-- TOC entry 1941 (class 1259 OID 73069)
-- Name: efi_peticio_solicitantid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_peticio_solicitantid_fk_i ON public.efi_peticio USING btree (solicitantid);


--
-- TOC entry 1944 (class 1259 OID 73071)
-- Name: efi_plugin_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_plugin_pk_i ON public.efi_plugin USING btree (pluginid);


--
-- TOC entry 1949 (class 1259 OID 73108)
-- Name: efi_seriedocumental_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_seriedocumental_pk_i ON public.efi_seriedocumental USING btree (seriedocumentalid);


--
-- TOC entry 1952 (class 1259 OID 73072)
-- Name: efi_traduccio_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traduccio_pk_i ON public.efi_traduccio USING btree (traduccioid);


--
-- TOC entry 1953 (class 1259 OID 73073)
-- Name: efi_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traducciomap_idiomaid_fk_i ON public.efi_traducciomap USING btree (idiomaid);


--
-- TOC entry 1954 (class 1259 OID 73074)
-- Name: efi_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_traducciomap_pk_i ON public.efi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1957 (class 1259 OID 73299)
-- Name: efi_usuari_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_usuari_idiomaid_fk_i ON public.efi_usuari USING btree (idiomaid);


--
-- TOC entry 1962 (class 1259 OID 73075)
-- Name: efi_usuari_pk_i; Type: INDEX; Schema: public; Owner: -
--

CREATE INDEX efi_usuari_pk_i ON public.efi_usuari USING btree (usuariid);


--
-- TOC entry 1996 (class 2606 OID 73231)
-- Name: efi_grupusuari_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 1997 (class 2606 OID 73236)
-- Name: efi_grupusuari_usuari_usuar_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_grupusuari
    ADD CONSTRAINT efi_grupusuari_usuari_usuar_fk FOREIGN KEY (usuariid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 2000 (class 2606 OID 73380)
-- Name: efi_menu_grup_grupid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_grup_grupid_fk FOREIGN KEY (grupid) REFERENCES public.efi_grup(grupid);


--
-- TOC entry 1999 (class 2606 OID 73375)
-- Name: efi_menu_traduccio_ajuda_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_ajuda_fk FOREIGN KEY (ajudamenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 1998 (class 2606 OID 73370)
-- Name: efi_menu_traduccio_titol_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_menu
    ADD CONSTRAINT efi_menu_traduccio_titol_fk FOREIGN KEY (titolmenuid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 1988 (class 2606 OID 73076)
-- Name: efi_peticio_fitxer_ffirm_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_ffirm_fk FOREIGN KEY (fitxer_firmatid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 1989 (class 2606 OID 73081)
-- Name: efi_peticio_fitxer_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 1990 (class 2606 OID 73086)
-- Name: efi_peticio_idioma_idiid_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_idioma_idiid_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- TOC entry 1993 (class 2606 OID 73205)
-- Name: efi_peticio_infoarxiu_infoa_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infoarxiu_infoa_fk FOREIGN KEY (infoarxiuid) REFERENCES public.efi_infoarxiu(infoarxiuid);


--
-- TOC entry 1992 (class 2606 OID 73122)
-- Name: efi_peticio_infosign_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_infosign_fk FOREIGN KEY (infosignaturaid) REFERENCES public.efi_infosignatura(infosignaturaid);


--
-- TOC entry 1991 (class 2606 OID 73096)
-- Name: efi_peticio_usuari_soli_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_usuari_soli_fk FOREIGN KEY (solicitantid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 1994 (class 2606 OID 73101)
-- Name: efi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 1995 (class 2606 OID 73189)
-- Name: efi_usuari_idioma_fk; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_idioma_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


-- Completed on 2022-11-11 13:20:16

--
-- PostgreSQL database dump complete
--

