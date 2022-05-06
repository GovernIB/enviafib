--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;


--
-- Name: DATABASE enviafib; Type: COMMENT; Schema: -; Owner: enviafib
--

COMMENT ON DATABASE enviafib IS 'Database de desenvolupament per enviafib.';


--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;




--TODO: Revisar si es necessari afegir el create schema, search_path i alterschema.

CREATE SCHEMA enviafib;

ALTER SCHEMA enviafib OWNER TO enviafib;

SET search_path = enviafib, pg_catalog;


SET default_tablespace = '';

SET default_with_oids = false;





--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


--
-- Name: efi_fitxer_seq; Type: SEQUENCE; Schema: public; Owner: enviafib
--

CREATE SEQUENCE public.efi_fitxer_seq
    START WITH 1000
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.efi_fitxer_seq OWNER TO enviafib;



--
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
-- Name: efi_peticio; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_peticio (
    peticioid bigint DEFAULT nextval('public.efi_peticio_seq'::regclass) NOT NULL,
    fitxerid bigint NOT NULL,
    datacreacio timestamp without time zone NOT NULL,
    titolid bigint NOT NULL,
    solicitantid bigint NOT NULL,
    idiomaid character varying(5) NOT NULL,
    destinatarinif character varying(50) NOT NULL
);


ALTER TABLE public.efi_peticio OWNER TO enviafib;

--
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
-- Name: efi_traduccio; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_traduccio (
    traduccioid bigint DEFAULT nextval('public.efi_traduccio_seq'::regclass) NOT NULL
);


ALTER TABLE public.efi_traduccio OWNER TO enviafib;

--
-- Name: efi_traducciomap; Type: TABLE; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE TABLE public.efi_traducciomap (
    traducciomapid bigint NOT NULL,
    idiomaid character varying(10) NOT NULL,
    valor character varying(4000)
);


ALTER TABLE public.efi_traducciomap OWNER TO enviafib;

--
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
-- Name: efi_fitxer_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_fitxer
    ADD CONSTRAINT efi_fitxer_pk PRIMARY KEY (fitxerid);


--
-- Name: efi_idioma_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_idioma
    ADD CONSTRAINT efi_idioma_pk PRIMARY KEY (idiomaid);


--
-- Name: efi_peticio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_pk PRIMARY KEY (peticioid);


--
-- Name: efi_plugin_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_plugin
    ADD CONSTRAINT efi_plugin_pk PRIMARY KEY (pluginid);


--
-- Name: efi_traduccio_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_traduccio
    ADD CONSTRAINT efi_traduccio_pk PRIMARY KEY (traduccioid);


--
-- Name: efi_traducmap_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_pk PRIMARY KEY (traducciomapid, idiomaid);


--
-- Name: efi_usuari_nif_uk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_nif_uk UNIQUE (nif);


--
-- Name: efi_usuari_pk; Type: CONSTRAINT; Schema: public; Owner: enviafib; Tablespace: 
--

ALTER TABLE ONLY public.efi_usuari
    ADD CONSTRAINT efi_usuari_pk PRIMARY KEY (usuariid);


--
-- Name: efi_fitxer_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_fitxer_pk_i ON public.efi_fitxer USING btree (fitxerid);


--
-- Name: efi_idioma_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_idioma_pk_i ON public.efi_idioma USING btree (idiomaid);


--
-- Name: efi_peticio_fitxerid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_fitxerid_fk_i ON public.efi_peticio USING btree (fitxerid);


--
-- Name: efi_peticio_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_idiomaid_fk_i ON public.efi_peticio USING btree (idiomaid);


--
-- Name: efi_peticio_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_pk_i ON public.efi_peticio USING btree (peticioid);


--
-- Name: efi_peticio_solicitantid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_solicitantid_fk_i ON public.efi_peticio USING btree (solicitantid);


--
-- Name: efi_peticio_titolid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_peticio_titolid_fk_i ON public.efi_peticio USING btree (titolid);


--
-- Name: efi_plugin_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_plugin_pk_i ON public.efi_plugin USING btree (pluginid);


--
-- Name: efi_traduccio_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traduccio_pk_i ON public.efi_traduccio USING btree (traduccioid);


--
-- Name: efi_traducciomap_idiomaid_fk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traducciomap_idiomaid_fk_i ON public.efi_traducciomap USING btree (idiomaid);


--
-- Name: efi_traducciomap_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_traducciomap_pk_i ON public.efi_traducciomap USING btree (traducciomapid);


--
-- Name: efi_usuari_pk_i; Type: INDEX; Schema: public; Owner: enviafib; Tablespace: 
--

CREATE INDEX efi_usuari_pk_i ON public.efi_usuari USING btree (usuariid);


--
-- Name: efi_peticio_fitxer_fitxer_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_fitxer_fitxer_fk FOREIGN KEY (fitxerid) REFERENCES public.efi_fitxer(fitxerid);


--
-- Name: efi_peticio_idioma_idiid_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_idioma_idiid_fk FOREIGN KEY (idiomaid) REFERENCES public.efi_idioma(idiomaid);


--
-- Name: efi_peticio_traduccio_titl_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_traduccio_titl_fk FOREIGN KEY (titolid) REFERENCES public.efi_traduccio(traduccioid);


--
-- Name: efi_peticio_usuari_soli_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_peticio
    ADD CONSTRAINT efi_peticio_usuari_soli_fk FOREIGN KEY (solicitantid) REFERENCES public.efi_usuari(usuariid);


--
-- Name: efi_traducmap_traduccio_fk; Type: FK CONSTRAINT; Schema: public; Owner: enviafib
--

ALTER TABLE ONLY public.efi_traducciomap
    ADD CONSTRAINT efi_traducmap_traduccio_fk FOREIGN KEY (traducciomapid) REFERENCES public.efi_traduccio(traduccioid);


--
-- Name: SCHEMA public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

