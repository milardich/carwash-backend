--
-- PostgreSQL database dump
--

-- Dumped from database version 16.3 (Debian 16.3-1.pgdg120+1)
-- Dumped by pg_dump version 16.3 (Debian 16.3-1.pgdg120+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: station; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.station (
    station_id integer NOT NULL,
    station_name character varying(50),
    city character varying(50),
    street_name character varying(50),
    street_number character varying(5),
    country character varying(50)
);


ALTER TABLE public.station OWNER TO postgres;

--
-- Name: station_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.station_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.station_sequence OWNER TO postgres;

--
-- Name: unit; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.unit (
    unit_id integer NOT NULL,
    station_id integer,
    status character varying(20),
    coin_tray_amount integer,
    total_water_consumption double precision,
    total_detergent_consumption double precision,
    total_wax_consumption double precision
);


ALTER TABLE public.unit OWNER TO postgres;

--
-- Name: unit_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.unit_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.unit_sequence OWNER TO postgres;

--
-- Name: wash_cycle; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.wash_cycle (
    wash_cycle_id integer NOT NULL,
    unit_id integer,
    wash_cycle_date timestamp without time zone,
    water_consumption double precision,
    detergent_consumption double precision,
    wax_consumption double precision,
    coin_amount double precision
);


ALTER TABLE public.wash_cycle OWNER TO postgres;

--
-- Name: wash_cycle_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.wash_cycle_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.wash_cycle_sequence OWNER TO postgres;

--
-- Data for Name: station; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.station (station_id, station_name, city, street_name, street_number, country) FROM stdin;
1	Vinkovci-2	Vinkovci	Epic Ulica	1	Croatia
2	Osijek-1	Osijek	Epic Ulica	1	Croatia
\.


--
-- Data for Name: unit; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.unit (unit_id, station_id, status, coin_tray_amount, total_water_consumption, total_detergent_consumption, total_wax_consumption) FROM stdin;
4	1	INACTIVE	0	0	0	0
5	1	INACTIVE	0	0	0	0
6	1	INACTIVE	0	0	0	0
7	1	INACTIVE	0	0	0	0
10	2	INACTIVE	0	0	0	0
11	2	INACTIVE	0	0	0	0
12	2	INACTIVE	0	0	0	0
1	1	IN_USE	0	0	0	0
2	1	IN_USE	0	0	0	0
3	1	AVAILABLE	0	0	0	0
8	2	AVAILABLE	0	0	0	0
9	2	AVAILABLE	0	0	0	0
\.


--
-- Data for Name: wash_cycle; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.wash_cycle (wash_cycle_id, unit_id, wash_cycle_date, water_consumption, detergent_consumption, wax_consumption, coin_amount) FROM stdin;
1	1	2024-07-17 00:40:11.001653	2.6600000858306885	2.9200000762939453	2.609999895095825	2
2	1	2024-07-17 00:40:11.583715	2.6600000858306885	2.9200000762939453	2.609999895095825	2
3	1	2024-07-17 00:40:12.207716	2.6600000858306885	2.9200000762939453	2.609999895095825	2
4	2	2024-07-17 00:40:14.500273	2.6600000858306885	2.9200000762939453	2.609999895095825	2
5	2	2024-07-17 00:40:15.07479	2.6600000858306885	2.9200000762939453	2.609999895095825	2
6	2	2024-07-17 00:40:15.50829	2.6600000858306885	2.9200000762939453	2.609999895095825	2
7	3	2024-07-17 00:40:23.865543	2.6600000858306885	2.9200000762939453	2.609999895095825	2
8	3	2024-07-17 00:40:24.358101	2.6600000858306885	2.9200000762939453	2.609999895095825	2
9	3	2024-07-17 00:40:24.792217	2.6600000858306885	2.9200000762939453	2.609999895095825	2
10	4	2024-07-17 00:40:27.058565	2.6600000858306885	2.9200000762939453	2.609999895095825	2
11	4	2024-07-17 00:40:27.550615	2.6600000858306885	2.9200000762939453	2.609999895095825	2
12	4	2024-07-17 00:40:27.969613	2.6600000858306885	2.9200000762939453	2.609999895095825	2
13	4	2024-07-17 00:40:28.387657	2.6600000858306885	2.9200000762939453	2.609999895095825	2
14	4	2024-07-17 00:40:28.806641	2.6600000858306885	2.9200000762939453	2.609999895095825	2
15	4	2024-07-17 00:40:29.271641	2.6600000858306885	2.9200000762939453	2.609999895095825	2
16	5	2024-07-17 00:40:31.520342	2.6600000858306885	2.9200000762939453	2.609999895095825	2
17	5	2024-07-17 00:40:31.999343	2.6600000858306885	2.9200000762939453	2.609999895095825	2
18	6	2024-07-17 00:40:35.177509	2.6600000858306885	2.9200000762939453	2.609999895095825	2
19	6	2024-07-17 00:40:35.626542	2.6600000858306885	2.9200000762939453	2.609999895095825	2
20	6	2024-07-17 00:40:36.044622	2.6600000858306885	2.9200000762939453	2.609999895095825	2
21	6	2024-07-17 00:40:36.408173	2.6600000858306885	2.9200000762939453	2.609999895095825	2
22	6	2024-07-17 00:40:36.776188	2.6600000858306885	2.9200000762939453	2.609999895095825	2
23	7	2024-07-17 00:40:40.664517	2.6600000858306885	2.9200000762939453	2.609999895095825	2
24	7	2024-07-17 00:40:41.082517	2.6600000858306885	2.9200000762939453	2.609999895095825	2
25	7	2024-07-17 00:40:41.444017	2.6600000858306885	2.9200000762939453	2.609999895095825	2
26	7	2024-07-17 00:40:41.841557	2.6600000858306885	2.9200000762939453	2.609999895095825	2
27	7	2024-07-17 00:40:42.633055	2.6600000858306885	2.9200000762939453	2.609999895095825	2
28	7	2024-07-17 00:40:43.098072	2.6600000858306885	2.9200000762939453	2.609999895095825	2
29	7	2024-07-17 00:40:44.988937	2.6600000858306885	2.9200000762939453	2.609999895095825	2
30	7	2024-07-17 00:40:45.876437	2.6600000858306885	2.9200000762939453	2.609999895095825	2
31	7	2024-07-17 00:40:46.321436	2.6600000858306885	2.9200000762939453	2.609999895095825	2
32	7	2024-07-17 00:40:46.724472	2.6600000858306885	2.9200000762939453	2.609999895095825	2
33	7	2024-07-17 00:40:47.11147	2.6600000858306885	2.9200000762939453	2.609999895095825	2
34	7	2024-07-17 00:40:47.484971	2.6600000858306885	2.9200000762939453	2.609999895095825	2
35	7	2024-07-17 00:40:47.949003	2.6600000858306885	2.9200000762939453	2.609999895095825	2
36	1	2024-07-17 00:41:28.063461	2.6600000858306885	2.9200000762939453	2.609999895095825	2
37	1	2024-07-17 00:41:29.752647	2.6600000858306885	2.9200000762939453	2.609999895095825	2
38	2	2024-07-17 00:41:33.19892	2.6600000858306885	2.9200000762939453	2.609999895095825	2
39	3	2024-07-17 00:41:39.130756	2.6600000858306885	2.9200000762939453	2.609999895095825	2
40	3	2024-07-17 00:41:39.58174	2.6600000858306885	2.9200000762939453	2.609999895095825	2
41	3	2024-07-17 00:41:40.106789	2.6600000858306885	2.9200000762939453	2.609999895095825	2
\.


--
-- Name: station_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.station_sequence', 2, true);


--
-- Name: unit_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.unit_sequence', 12, true);


--
-- Name: wash_cycle_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.wash_cycle_sequence', 41, true);


--
-- Name: station station_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.station
    ADD CONSTRAINT station_pkey PRIMARY KEY (station_id);


--
-- Name: unit unit_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.unit
    ADD CONSTRAINT unit_pkey PRIMARY KEY (unit_id);


--
-- Name: wash_cycle wash_cycle_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.wash_cycle
    ADD CONSTRAINT wash_cycle_pkey PRIMARY KEY (wash_cycle_id);


--
-- PostgreSQL database dump complete
--

