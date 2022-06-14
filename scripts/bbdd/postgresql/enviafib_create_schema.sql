--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.25
-- Dumped by pg_dump version 9.3.25
-- Started on 2022-06-07 11:53:56

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET client_min_messages = warning;




--
-- TOC entry 174 (class 1259 OID 24828)
-- Name: efi_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_fitxer_seq OWNER TO enviafib;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 176 (class 1259 OID 24832)
-- Name: efi_fitxer; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_fitxer (
    fitxerid bigint DEFAULT nextval('public.efi_fitxer_seq'::regclass) NOT NULL,
    descripcio character varying(1000) DEFAULT NULL::character varying,
    mime character varying(255) NOT NULL,
    nom character varying(255) NOT NULL,
    tamany bigint NOT NULL
);


ALTER TABLE public.efi_fitxer OWNER TO enviafib;

--
-- TOC entry 177 (class 1259 OID 24840)
-- Name: efi_idioma; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_idioma (
    idiomaid character varying(5) NOT NULL,
    nom character varying(50) NOT NULL,
    suportat boolean DEFAULT true NOT NULL,
    ordre integer DEFAULT 0 NOT NULL
);


ALTER TABLE public.efi_idioma OWNER TO enviafib;

--
-- TOC entry 171 (class 1259 OID 24727)
-- Name: efi_peticio_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_peticio_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_peticio_seq OWNER TO enviafib;

--
-- TOC entry 182 (class 1259 OID 24915)
-- Name: efi_peticio; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_peticio (
    peticioid bigint DEFAULT nextval('public.efi_peticio_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    titolid bigint NOT NULL,
    solicitantid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    destinatarinif character varying(50) NOT NULL,
    estat bigint DEFAULT 1 NOT NULL,
    fitxer_firmatid bigint,
    peticioportafib bigint,
    tipusdocumental character varying(100) NOT NULL,
    idiomadoc character varying(30) NOT NULL
);


ALTER TABLE public.efi_peticio OWNER TO enviafib;

--
-- TOC entry 2031 (class 0 OID 0)
-- Dependencies: 182
-- Name: COLUMN efi_peticio.peticioportafib; Type: COMMENT; Schema: public; Owner: enviafib
--

COMMENT ON COLUMN public.efi_peticio.peticioportafib IS 'Identificador de la peticio dins el sistema de Portafib.';


--
-- TOC entry 172 (class 1259 OID 24729)
-- Name: efi_plugin_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_plugin_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_plugin_seq OWNER TO enviafib;

--
-- TOC entry 181 (class 1259 OID 24906)
-- Name: efi_plugin; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_plugin (
    pluginid bigint DEFAULT nextval('public.efi_plugin_seq'::regclass) NOT NULL,
    nomid bigint NOT NULL,
    descripciocurtaid bigint NOT NULL,
    classe character varying(255),
    properties text,
    actiu boolean NOT NULL,
    tipus integer NOT NULL
);


ALTER TABLE public.efi_plugin OWNER TO enviafib;

--
-- TOC entry 183 (class 1259 OID 32897)
-- Name: efi_seriedocu_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_seriedocu_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_seriedocu_seq OWNER TO enviafib;

--
-- TOC entry 184 (class 1259 OID 32916)
-- Name: efi_seriedocu; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_seriedocu (
    seriedocuid bigint DEFAULT nextval('public.efi_seriedocu_seq'::regclass) NOT NULL,
    nom character varying(256) NOT NULL,
    tipusdocu character varying(256)
);


ALTER TABLE public.efi_seriedocu OWNER TO enviafib;

--
-- TOC entry 2032 (class 0 OID 0)
-- Dependencies: 184
-- Name: TABLE efi_seriedocu; Type: COMMENT; Schema: public; Owner: enviafib
--

COMMENT ON TABLE public.efi_seriedocu IS 'Taula de relacio de Series documentals amb Tipus documentals d''Arxiu.';


--
-- TOC entry 175 (class 1259 OID 24830)
-- Name: efi_traduccio_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_traduccio_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_traduccio_seq OWNER TO enviafib;

--
-- TOC entry 178 (class 1259 OID 24845)
-- Name: efi_traduccio; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_traduccio (
    traduccioid bigint DEFAULT nextval('public.efi_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.efi_traduccio OWNER TO enviafib;

--
-- TOC entry 179 (class 1259 OID 24849)
-- Name: efi_traducciomap; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.efi_traducciomap OWNER TO enviafib;

--
-- TOC entry 173 (class 1259 OID 24733)
-- Name: efi_usuari_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_usuari_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_usuari_seq OWNER TO enviafib;

--
-- TOC entry 180 (class 1259 OID 24894)
-- Name: efi_usuari; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_usuari (
    usuariid bigint DEFAULT nextval('public.efi_usuari_seq'::regclass) NOT NULL,
    username character varying(100) NOT NULL,
    nom character varying(256) NOT NULL,
    llinatge1 character varying(256) NOT NULL,
    llinatge2 character varying(256),
    nif character varying(50) NOT NULL,
    email character varying(256) NOT NULL
);


ALTER TABLE public.efi_usuari OWNER TO enviafib;

--
-- TOC entry 1877 (class 2606 OID 24856)
-- Name: efi_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_fitxer
    ADD CONSTRAINT efi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- TOC entry 1880 (class 2606 OID 24858)
-- Name: efi_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_idioma
    ADD CONSTRAINT efi_idioma_pk PRIMARY KEY (idiomaid);


--
-- TOC entry 1900 (class 2606 OID 24920)
-- Name: efi_peticio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_pk PRIMARY KEY (peticioid);


--
-- TOC entry 1895 (class 2606 OID 24914)
-- Name: efi_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_plugin
    ADD CONSTRAINT efi_plugin_pk PRIMARY KEY (pluginid);


--
-- TOC entry 1905 (class 2606 OID 32924)
-- Name: efi_seriedocu_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_seriedocu
    ADD CONSTRAINT efi_seriedocu_pk PRIMARY KEY (seriedocuid);


--
-- TOC entry 1907 (class 2606 OID 33041)
-- Name: efi_seriedocu_tipusdocu_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_seriedocu
    ADD CONSTRAINT efi_seriedocu_tipusdocu_uk UNIQUE (tipusdocu);


--
-- TOC entry 1883 (class 2606 OID 24860)
-- Name: efi_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_traduccio
    ADD CONSTRAINT efi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- TOC entry 1888 (class 2606 OID 24862)
-- Name: efi_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- TOC entry 1890 (class 2606 OID 24904)
-- Name: efi_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_nif_uk UNIQUE (nif);


--
-- TOC entry 1892 (class 2606 OID 24902)
-- Name: efi_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_pk PRIMARY KEY (usuariid);


--
-- TOC entry 1878 (class 1259 OID 24863)
-- Name: efi_fitxer_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_fitxer_pk_i ON public.efi_fitxer USING btree (fitxerid);


--
-- TOC entry 1881 (class 1259 OID 24864)
-- Name: efi_idioma_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_idioma_pk_i ON public.efi_idioma USING btree (idiomaid);


--
-- TOC entry 1897 (class 1259 OID 24941)
-- Name: efi_peticio_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_fitxerid_fk_i ON public.efi_peticio USING btree (fitxerid);


--
-- TOC entry 1898 (class 1259 OID 24942)
-- Name: efi_peticio_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_idiomaid_fk_i ON public.efi_peticio USING btree (idiomaid);


--
-- TOC entry 1901 (class 1259 OID 24943)
-- Name: efi_peticio_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_pk_i ON public.efi_peticio USING btree (peticioid);


--
-- TOC entry 1902 (class 1259 OID 24944)
-- Name: efi_peticio_solicitantid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_solicitantid_fk_i ON public.efi_peticio USING btree (solicitantid);


--
-- TOC entry 1903 (class 1259 OID 24945)
-- Name: efi_peticio_titolid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_titolid_fk_i ON public.efi_peticio USING btree (titolid);


--
-- TOC entry 1896 (class 1259 OID 24947)
-- Name: efi_plugin_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_plugin_pk_i ON public.efi_plugin USING btree (pluginid);


--
-- TOC entry 1884 (class 1259 OID 24865)
-- Name: efi_traduccio_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traduccio_pk_i ON public.efi_traduccio USING btree (traduccioid);


--
-- TOC entry 1885 (class 1259 OID 24866)
-- Name: efi_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traducciomap_idiomaid_fk_i ON public.efi_traducciomap USING btree (idiomaid);


--
-- TOC entry 1886 (class 1259 OID 24867)
-- Name: efi_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traducciomap_pk_i ON public.efi_traducciomap USING btree (traducciomapid);


--
-- TOC entry 1893 (class 1259 OID 24905)
-- Name: efi_usuari_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_usuari_pk_i ON public.efi_usuari USING btree (usuariid);


--
-- TOC entry 1913 (class 2606 OID 24981)
-- Name: efi_peticio_fitxer_ffirm_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_ffirm_fk FOREIGN KEY (fitxer_firmatid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 1909 (class 2606 OID 24921)
-- Name: efi_peticio_fitxer_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES public.efi_fitxer(fitxerid);


--
-- TOC entry 1910 (class 2606 OID 24926)
-- Name: efi_peticio_idioma_idiid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_idioma_idiid_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- TOC entry 1911 (class 2606 OID 24931)
-- Name: efi_peticio_traduccio_titl_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_traduccio_titl_fk FOREIGN KEY (titolid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 1912 (class 2606 OID 24936)
-- Name: efi_peticio_usuari_soli_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_usuari_soli_fk FOREIGN KEY (solicitantid) REFERENCES public.efi_usuari(usuariid);


--
-- TOC entry 1908 (class 2606 OID 24868)
-- Name: efi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.efi_traduccio(traduccioid);


--
-- TOC entry 2029 (class 0 OID 0)
-- Dependencies: 6
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


-- Completed on 2022-06-07 11:53:56

--
-- PostgreSQL database dump complete
--

