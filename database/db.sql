--
-- PostgreSQL database dump
--

-- Dumped from database version 14.7 (Homebrew)
-- Dumped by pg_dump version 14.7 (Homebrew)

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
-- Name: clients; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.clients (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255),
    org_name character varying(255) NOT NULL,
    phone character varying(255),
    user_id bigint NOT NULL
);


ALTER TABLE public.clients OWNER TO root;

--
-- Name: clients_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.clients_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_id_seq OWNER TO root;

--
-- Name: clients_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.clients_id_seq OWNED BY public.clients.id;


--
-- Name: clinics; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.clinics (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255),
    name character varying(255) NOT NULL,
    faculty_id bigint NOT NULL
);


ALTER TABLE public.clinics OWNER TO root;

--
-- Name: clinics_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.clinics_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clinics_id_seq OWNER TO root;

--
-- Name: clinics_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.clinics_id_seq OWNED BY public.clinics.id;


--
-- Name: commits; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.commits (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    commit_date date NOT NULL,
    num_differences integer NOT NULL,
    project_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE public.commits OWNER TO root;

--
-- Name: commits_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.commits_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.commits_id_seq OWNER TO root;

--
-- Name: commits_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.commits_id_seq OWNED BY public.commits.id;


--
-- Name: curators; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.curators (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255),
    phone character varying(255),
    user_id bigint NOT NULL
);


ALTER TABLE public.curators OWNER TO root;

--
-- Name: curators_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.curators_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.curators_id_seq OWNER TO root;

--
-- Name: curators_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.curators_id_seq OWNED BY public.curators.id;


--
-- Name: databasechangelog; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.databasechangelog (
    id character varying(255) NOT NULL,
    author character varying(255) NOT NULL,
    filename character varying(255) NOT NULL,
    dateexecuted timestamp without time zone NOT NULL,
    orderexecuted integer NOT NULL,
    exectype character varying(10) NOT NULL,
    md5sum character varying(35),
    description character varying(255),
    comments character varying(255),
    tag character varying(255),
    liquibase character varying(20),
    contexts character varying(255),
    labels character varying(255),
    deployment_id character varying(10)
);


ALTER TABLE public.databasechangelog OWNER TO root;

--
-- Name: databasechangeloglock; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.databasechangeloglock (
    id integer NOT NULL,
    locked boolean NOT NULL,
    lockgranted timestamp without time zone,
    lockedby character varying(255)
);


ALTER TABLE public.databasechangeloglock OWNER TO root;

--
-- Name: educational_programs; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.educational_programs (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    code character varying(255) NOT NULL,
    name character varying(255) NOT NULL,
    faculty_id bigint NOT NULL
);


ALTER TABLE public.educational_programs OWNER TO root;

--
-- Name: educational_programs_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.educational_programs_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.educational_programs_id_seq OWNER TO root;

--
-- Name: educational_programs_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.educational_programs_id_seq OWNED BY public.educational_programs.id;


--
-- Name: faculties; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.faculties (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255) NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.faculties OWNER TO root;

--
-- Name: faculties_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.faculties_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.faculties_id_seq OWNER TO root;

--
-- Name: faculties_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.faculties_id_seq OWNED BY public.faculties.id;


--
-- Name: links; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.links (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255) NOT NULL,
    project_id bigint NOT NULL,
    name character varying(255) NOT NULL
);


ALTER TABLE public.links OWNER TO root;

--
-- Name: links_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.links_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.links_id_seq OWNER TO root;

--
-- Name: links_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.links_id_seq OWNED BY public.links.id;


--
-- Name: project_client; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_client (
    project_id bigint NOT NULL,
    client_id bigint NOT NULL
);


ALTER TABLE public.project_client OWNER TO root;

--
-- Name: project_clinic; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_clinic (
    project_id bigint NOT NULL,
    clinic_id bigint NOT NULL
);


ALTER TABLE public.project_clinic OWNER TO root;

--
-- Name: project_curator; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_curator (
    project_id bigint NOT NULL,
    curator_id bigint NOT NULL
);


ALTER TABLE public.project_curator OWNER TO root;

--
-- Name: project_project; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_project (
    first_project_id bigint NOT NULL,
    second_project_id bigint NOT NULL
);


ALTER TABLE public.project_project OWNER TO root;

--
-- Name: project_student; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_student (
    project_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE public.project_student OWNER TO root;

--
-- Name: project_supervisor; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_supervisor (
    project_id bigint NOT NULL,
    supervisor_id bigint NOT NULL
);


ALTER TABLE public.project_supervisor OWNER TO root;

--
-- Name: project_tag; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.project_tag (
    project_id bigint NOT NULL,
    tag_id bigint NOT NULL
);


ALTER TABLE public.project_tag OWNER TO root;

--
-- Name: projects; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.projects (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    description text NOT NULL,
    max_students integer,
    name character varying(255) NOT NULL,
    requirements text,
    result_link character varying(255),
    start_time timestamp(6) without time zone,
    work_format character varying(255) NOT NULL,
    start_filing timestamp(6) without time zone,
    end_filing timestamp(6) without time zone,
    start_implementation timestamp(6) without time zone,
    end_implementation timestamp(6) without time zone,
    start_defense timestamp(6) without time zone,
    end_defense timestamp(6) without time zone,
    status character varying(255),
    requirements_for_performers text
);


ALTER TABLE public.projects OWNER TO root;

--
-- Name: projects_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.projects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.projects_id_seq OWNER TO root;

--
-- Name: projects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.projects_id_seq OWNED BY public.projects.id;


--
-- Name: roles_in_projects; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.roles_in_projects (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    role character varying(255) NOT NULL,
    project_id bigint NOT NULL,
    student_id bigint NOT NULL
);


ALTER TABLE public.roles_in_projects OWNER TO root;

--
-- Name: roles_in_projects_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.roles_in_projects_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.roles_in_projects_id_seq OWNER TO root;

--
-- Name: roles_in_projects_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.roles_in_projects_id_seq OWNED BY public.roles_in_projects.id;


--
-- Name: students; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.students (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    degree character varying(255) NOT NULL,
    grade integer NOT NULL,
    sex character varying(255) NOT NULL,
    educational_program_id bigint NOT NULL,
    user_id bigint NOT NULL
);


ALTER TABLE public.students OWNER TO root;

--
-- Name: students_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.students_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.students_id_seq OWNER TO root;

--
-- Name: students_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.students_id_seq OWNED BY public.students.id;


--
-- Name: supervisors; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.supervisors (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    link character varying(255),
    phone character varying(255),
    user_id bigint NOT NULL
);


ALTER TABLE public.supervisors OWNER TO root;

--
-- Name: supervisors_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.supervisors_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.supervisors_id_seq OWNER TO root;

--
-- Name: supervisors_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.supervisors_id_seq OWNED BY public.supervisors.id;


--
-- Name: tags; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.tags (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    name character varying(255) NOT NULL
);


ALTER TABLE public.tags OWNER TO root;

--
-- Name: tags_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.tags_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.tags_id_seq OWNER TO root;

--
-- Name: tags_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.tags_id_seq OWNED BY public.tags.id;


--
-- Name: users; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.users (
    id bigint NOT NULL,
    creation_time timestamp(6) without time zone,
    last_update_time timestamp(6) without time zone,
    email character varying(255),
    name character varying(255) NOT NULL,
    role character varying(255) NOT NULL
);


ALTER TABLE public.users OWNER TO root;

--
-- Name: users_id_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.users_id_seq OWNER TO root;

--
-- Name: users_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.users_id_seq OWNED BY public.users.id;


--
-- Name: clients id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clients ALTER COLUMN id SET DEFAULT nextval('public.clients_id_seq'::regclass);


--
-- Name: clinics id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clinics ALTER COLUMN id SET DEFAULT nextval('public.clinics_id_seq'::regclass);


--
-- Name: commits id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.commits ALTER COLUMN id SET DEFAULT nextval('public.commits_id_seq'::regclass);


--
-- Name: curators id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.curators ALTER COLUMN id SET DEFAULT nextval('public.curators_id_seq'::regclass);


--
-- Name: educational_programs id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.educational_programs ALTER COLUMN id SET DEFAULT nextval('public.educational_programs_id_seq'::regclass);


--
-- Name: faculties id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.faculties ALTER COLUMN id SET DEFAULT nextval('public.faculties_id_seq'::regclass);


--
-- Name: links id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.links ALTER COLUMN id SET DEFAULT nextval('public.links_id_seq'::regclass);


--
-- Name: projects id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.projects ALTER COLUMN id SET DEFAULT nextval('public.projects_id_seq'::regclass);


--
-- Name: roles_in_projects id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles_in_projects ALTER COLUMN id SET DEFAULT nextval('public.roles_in_projects_id_seq'::regclass);


--
-- Name: students id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.students ALTER COLUMN id SET DEFAULT nextval('public.students_id_seq'::regclass);


--
-- Name: supervisors id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.supervisors ALTER COLUMN id SET DEFAULT nextval('public.supervisors_id_seq'::regclass);


--
-- Name: tags id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.tags ALTER COLUMN id SET DEFAULT nextval('public.tags_id_seq'::regclass);


--
-- Name: users id; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users ALTER COLUMN id SET DEFAULT nextval('public.users_id_seq'::regclass);


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.clients (id, creation_time, last_update_time, link, org_name, phone, user_id) FROM stdin;
1	2023-05-23 22:49:29.969917	2023-05-23 22:49:29.969917	\N	Газпром	+78124137444	5
2	2023-05-23 22:49:29.969917	2023-05-23 22:49:29.969917	\N	Huawei	+74952340686	6
3	2023-05-23 22:49:29.969917	2023-05-23 22:49:29.969917	\N	SPBU	+78129146443	7
4	2023-05-23 22:49:29.969917	2023-05-23 22:49:29.969917	\N	ЦИТИК	+78005553535	8
\.


--
-- Data for Name: clinics; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.clinics (id, creation_time, last_update_time, link, name, faculty_id) FROM stdin;
1	2023-05-23 22:49:29.984689	2023-05-23 22:49:29.984689	\N	IT	1
\.


--
-- Data for Name: commits; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.commits (id, creation_time, last_update_time, commit_date, num_differences, project_id, student_id) FROM stdin;
\.


--
-- Data for Name: curators; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.curators (id, creation_time, last_update_time, link, phone, user_id) FROM stdin;
1	2023-05-23 22:49:30.006884	2023-05-23 22:49:30.006884	sdlkjgf@gmail.com	+10978513689	9
2	2023-05-23 22:49:30.006884	2023-05-23 22:49:30.006884	sdgsdgsdg@gmail.com	+1235624635	10
3	2023-06-01 16:01:38.438	2023-06-01 16:01:38.438	\N	\N	24
4	2023-06-01 16:06:07.743	2023-06-01 16:06:07.743	\N	\N	25
5	2023-06-01 16:07:29.25	2023-06-01 16:07:29.25	\N	+79112387545	26
\.


--
-- Data for Name: databasechangelog; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.databasechangelog (id, author, filename, dateexecuted, orderexecuted, exectype, md5sum, description, comments, tag, liquibase, contexts, labels, deployment_id) FROM stdin;
0	Andrey Butusov	db/changelog/db.changelog-master.xml	2023-05-23 22:49:28.668669	1	EXECUTED	8:a65a0e7f01065ca4adb7cea9e45e72b0	tagDatabase		db_init	4.20.0	\N	\N	4871368591
1	Andrey Butusov	db/changelog/v-1.0/tables/faculties/01-changeset-faculties-table.sql	2023-05-23 22:49:28.721524	2	EXECUTED	8:e90c2357bb4ea7be5238ef2533b00572	sql		\N	4.20.0	\N	\N	4871368591
2	Andrey Butusov	db/changelog/v-1.0/tables/tags/02-changeset-tags-table.sql	2023-05-23 22:49:28.74944	3	EXECUTED	8:fb390562abf525c928de45a7690061d8	sql		\N	4.20.0	\N	\N	4871368591
3	Andrey Butusov	db/changelog/v-1.0/tables/clients/03-changeset-clients-table.sql	2023-05-23 22:49:28.795938	4	EXECUTED	8:e21cbf02f8ead58adfa28308c3c81136	sql		\N	4.20.0	\N	\N	4871368591
4	Andrey Butusov	db/changelog/v-1.0/tables/educational_programs/04-changeset-educational_programs-table.sql	2023-05-23 22:49:28.863674	5	EXECUTED	8:d6d53f598e172ec97fe65698313e683b	sql		\N	4.20.0	\N	\N	4871368591
5	Andrey Butusov	db/changelog/v-1.0/tables/students/05-changeset-students-table.sql	2023-05-23 22:49:28.903197	6	EXECUTED	8:017b26f41c6be924dc9a06957a6012fc	sql		\N	4.20.0	\N	\N	4871368591
6	Andrey Butusov	db/changelog/v-1.0/tables/users/06-changeset-users-table.sql	2023-05-23 22:49:28.966986	7	EXECUTED	8:c6942e76c83c10f0e56e096c45b73034	sql		\N	4.20.0	\N	\N	4871368591
7	Andrey Butusov	db/changelog/v-1.0/tables/clinics/07-changeset-clinics-table.sql	2023-05-23 22:49:28.996298	8	EXECUTED	8:0e9452cd26633d25da1e7216ee9a4edb	sql		\N	4.20.0	\N	\N	4871368591
8	Andrey Butusov	db/changelog/v-1.0/tables/projects/08-changeset-projects-table.sql	2023-05-23 22:49:29.065651	9	EXECUTED	8:a288297fc4d1332642a474faaa574c00	sql		\N	4.20.0	\N	\N	4871368591
9	Andrey Butusov	db/changelog/v-1.0/tables/commits/09-changeset-commits-table.sql	2023-05-23 22:49:29.109034	10	EXECUTED	8:46ce0afb27ffdbbd5966f3a4d8105852	sql		\N	4.20.0	\N	\N	4871368591
10	Andrey Butusov	db/changelog/v-1.0/tables/links/10-changeset-links-table.sql	2023-05-23 22:49:29.147175	11	EXECUTED	8:1a7463df2aaf96749ffe347f701b96d0	sql		\N	4.20.0	\N	\N	4871368591
11	Andrey Butusov	db/changelog/v-1.0/tables/requirements/11-changeset-requirements-table.sql	2023-05-23 22:49:29.174228	12	EXECUTED	8:208711041962aa99c5cf6cde570ee89b	sql		\N	4.20.0	\N	\N	4871368591
12	Andrey Butusov	db/changelog/v-1.0/tables/roles_in_projects/12-changeset-roles_in_projects-table.sql	2023-05-23 22:49:29.21017	13	EXECUTED	8:7d5ba3bac6cd15746bfe187a213e5bb8	sql		\N	4.20.0	\N	\N	4871368591
13	Andrey Butusov	db/changelog/v-1.0/tables/project_clinic/13-changeset-project_clinic-table.sql	2023-05-23 22:49:29.248458	14	EXECUTED	8:5d311a78c0708d30602cffc1f94df2d4	sql		\N	4.20.0	\N	\N	4871368591
14	Andrey Butusov	db/changelog/v-1.0/tables/project_student/14-changeset-project_student-table.sql	2023-05-23 22:49:29.276788	15	EXECUTED	8:37d83db6e29c2833f51702a5cbd6bc87	sql		\N	4.20.0	\N	\N	4871368591
15	Andrey Butusov	db/changelog/v-1.0/tables/project_tag/15-changeset-project_tag-table.sql	2023-05-23 22:49:29.308986	16	EXECUTED	8:2b1112215dd3c6401bed26c187f42b94	sql		\N	4.20.0	\N	\N	4871368591
16	Andrey Butusov	db/changelog/db.changelog-master.xml	2023-05-23 22:49:29.31918	17	EXECUTED	8:8875910fb45a0fdaa593486dec99957f	tagDatabase		v-1.0	4.20.0	\N	\N	4871368591
17	Sergey Vankovich	db/changelog/v-1.1/17-changeset-clients-schema.sql	2023-05-23 22:49:29.355371	18	EXECUTED	8:9ff187b1a7f74b20439bfdf62ca49495	sql		\N	4.20.0	\N	\N	4871368591
18	Sergey Vankovich	db/changelog/v-1.1/18-changeset-students-schema.sql	2023-05-23 22:49:29.381959	19	EXECUTED	8:5f2bc5448418ed12f9715daf89563263	sql		\N	4.20.0	\N	\N	4871368591
19	Sergey Vankovich	db/changelog/v-1.1/19-changeset-users-schema.sql	2023-05-23 22:49:29.399217	20	EXECUTED	8:56e05250a3170180d6c99e6528fc32b4	sql		\N	4.20.0	\N	\N	4871368591
20	Sergey Vankovich	db/changelog/db.changelog-master.xml	2023-05-23 22:49:29.504275	21	EXECUTED	8:0895004fe1421af07dd35930fb6fa52b	tagDatabase		v-1.1	4.20.0	\N	\N	4871368591
21	Andrey Butusov	db/changelog/v-1.2/21-changeset-curators-create_table.sql	2023-05-23 22:49:29.569787	22	EXECUTED	8:89fb095269ee01294f1ce6820bc699c3	sql		\N	4.20.0	\N	\N	4871368591
22	Andrey Butusov	db/changelog/v-1.2/22-changeset-supervisors-create_table.sql	2023-05-23 22:49:29.618405	23	EXECUTED	8:a5b6017b25e3196476fd83edc61da9e1	sql		\N	4.20.0	\N	\N	4871368591
23	Andrey Butusov	db/changelog/v-1.2/23-changeset-project_client-create_table.sql	2023-05-23 22:49:29.648317	24	EXECUTED	8:be4dca6d1eca4ead045912dcff432de6	sql		\N	4.20.0	\N	\N	4871368591
24	Andrey Butusov	db/changelog/v-1.2/24-changeset-project_curator-create_table.sql	2023-05-23 22:49:29.668709	25	EXECUTED	8:155ee66eb2b56e6d769665111a697ee0	sql		\N	4.20.0	\N	\N	4871368591
25	Andrey Butusov	db/changelog/v-1.2/25-changeset-project_supervisor-create_table.sql	2023-05-23 22:49:29.69527	26	EXECUTED	8:86baa5e5263ffc93b307ab79fba73aa1	sql		\N	4.20.0	\N	\N	4871368591
26	Andrey Butusov	db/changelog/v-1.2/26-changeset-links-update.sql	2023-05-23 22:49:29.708681	27	EXECUTED	8:5fa591a1635ef203def97e9c000a59e1	sql		\N	4.20.0	\N	\N	4871368591
27	Andrey Butusov	db/changelog/v-1.2/27-changeset-project-update.sql	2023-05-23 22:49:29.75729	28	EXECUTED	8:896d85450ff65550a50edd9a239946d6	sql		\N	4.20.0	\N	\N	4871368591
28	Andrey Butusov	db/changelog/v-1.2/28-changeset-performers_requirements-drop.sql	2023-05-23 22:49:29.775783	29	EXECUTED	8:771d4747a04574f61bc6d9081296c1d3	sql		\N	4.20.0	\N	\N	4871368591
29	Andrey Butusov	db/changelog/v-1.2/29-changeset-project_project-create_table.sql	2023-05-23 22:49:29.817195	30	EXECUTED	8:58a143f1fc2ef8d2e8352ef9ff7cfbf7	sql		\N	4.20.0	\N	\N	4871368591
30	Andrey Butusov	db/changelog/db.changelog-master.xml	2023-05-23 22:49:29.832524	31	EXECUTED	8:aeb4153c16d0a3101a280c955e83f598	tagDatabase		v-1.2	4.20.0	\N	\N	4871368591
31	Sergey Vankovich	db/changelog/mock/31-changeset-tags-insert-mock.sql	2023-05-23 22:49:29.875532	32	EXECUTED	8:01c20c63680cca2b0ef182e9059a1a1a	sql		\N	4.20.0	\N	\N	4871368591
32	Sergey Vankovich	db/changelog/mock/32-changeset-users-insert-mock.sql	2023-05-23 22:49:29.924795	33	EXECUTED	8:51143a397d6ee231d10cc46f711c6f67	sql		\N	4.20.0	\N	\N	4871368591
33	Sergey Vankovich	db/changelog/mock/33-changeset-faculties-insert-mock.sql	2023-05-23 22:49:29.93861	34	EXECUTED	8:fb3e91f56ad49f9748cbf7dc36d2d1f7	sql		\N	4.20.0	\N	\N	4871368591
34	Sergey Vankovich	db/changelog/mock/34-changeset-educational_programs-insert-mock.sql	2023-05-23 22:49:29.952654	35	EXECUTED	8:a38374ba5499b8af8e33f45b36682c6e	sql		\N	4.20.0	\N	\N	4871368591
35	Sergey Vankovich	db/changelog/mock/35-changeset-students-insert-mock.sql	2023-05-23 22:49:29.96677	36	EXECUTED	8:2e71b324ff1c4274f98aabce589bc0a6	sql		\N	4.20.0	\N	\N	4871368591
36	Sergey Vankovich	db/changelog/mock/36-changeset-clients-insert-mock.sql	2023-05-23 22:49:29.980109	37	EXECUTED	8:48fb9d30fa7d66a806d43ef517c4bc1b	sql		\N	4.20.0	\N	\N	4871368591
37	Sergey Vankovich	db/changelog/mock/37-changeset-clinics-insert-mock.sql	2023-05-23 22:49:29.996485	38	EXECUTED	8:6ea82887bbd26c5cddb981df1ccb8692	sql		\N	4.20.0	\N	\N	4871368591
38	Andrey Butusov	db/changelog/mock/38-changeset-curators-insert-mock.sql	2023-05-23 22:49:30.016267	39	EXECUTED	8:7babf7dcad0c2d7e00fbeb249723ddff	sql		\N	4.20.0	\N	\N	4871368591
39	Andrey Butusov	db/changelog/mock/39-changeset-supervisors-insert-mock.sql	2023-05-23 22:49:30.027418	40	EXECUTED	8:530cb1130b0a272ac132d568f5f7a176	sql		\N	4.20.0	\N	\N	4871368591
40	Sergey Vankovich	db/changelog/mock/40-changeset-projects-insert-mock.sql	2023-05-23 22:49:30.047489	41	EXECUTED	8:0e4b21f27378d95e932c100091050d71	sql		\N	4.20.0	\N	\N	4871368591
41	Andrey Butusov	db/changelog/mock/41-changeset-links-insert-mock.sql	2023-05-23 22:49:30.101085	42	EXECUTED	8:33bfbeebb08b3ba3bc9d764d3f30c319	sql		\N	4.20.0	\N	\N	4871368591
42	Sergey Vankovich	db/changelog/mock/42-changeset-project_student-insert-mock.sql	2023-05-23 22:49:30.113807	43	EXECUTED	8:1723ce8bcbe4df69558247622363266c	sql		\N	4.20.0	\N	\N	4871368591
43	Sergey Vankovich	db/changelog/mock/43-changeset-project_clinic-insert-mock.sql	2023-05-23 22:49:30.124059	44	EXECUTED	8:2bdf00cf1515c01049b04940a3baf45c	sql		\N	4.20.0	\N	\N	4871368591
44	Sergey Vankovich	db/changelog/mock/44-changeset-project_tag-insert-mock.sql	2023-05-23 22:49:30.137825	45	EXECUTED	8:9aa0e85004baaec506269fb68be9368e	sql		\N	4.20.0	\N	\N	4871368591
45	Andrey Butusov	db/changelog/mock/45-changeset-project_curator-insert-mock.sql	2023-05-23 22:49:30.152527	46	EXECUTED	8:dab5ed0fac944d3a14e3e24e2ff8fc3c	sql		\N	4.20.0	\N	\N	4871368591
46	Andrey Butusov	db/changelog/mock/46-changeset-project_supervisor-insert-mock.sql	2023-05-23 22:49:30.162677	47	EXECUTED	8:1dfd272a5bb32f3f4c09e2f0fd9d61b4	sql		\N	4.20.0	\N	\N	4871368591
47	Andrey Butusov	db/changelog/mock/47-changeset-project_project-insert-mock.sql	2023-05-23 22:49:30.177866	48	EXECUTED	8:25dc8f8c308288fadb7dc715578d52ca	sql		\N	4.20.0	\N	\N	4871368591
48	Andrey Butusov	db/changelog/mock/48-changeset-project_client-insert-mock.sql	2023-05-23 22:49:30.191987	49	EXECUTED	8:c015eff328ef572c35887ccaddd56448	sql		\N	4.20.0	\N	\N	4871368591
49	Andrey Butusov	db/changelog/mock/49-changeset-roles_in_projects-insert-mock.sql	2023-05-23 22:49:30.208928	50	EXECUTED	8:f4f97646df98423e4e08ab6a99151794	sql		\N	4.20.0	\N	\N	4871368591
50	Sergey Vankovich	db/changelog/db.changelog-master.xml	2023-05-23 22:49:30.216279	51	EXECUTED	8:eacf5309acbedf300afa72a61dbbbdc9	tagDatabase		mock	4.20.0	\N	\N	4871368591
\.


--
-- Data for Name: databasechangeloglock; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.databasechangeloglock (id, locked, lockgranted, lockedby) FROM stdin;
1	f	\N	\N
\.


--
-- Data for Name: educational_programs; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.educational_programs (id, creation_time, last_update_time, code, name, faculty_id) FROM stdin;
1	2023-05-23 22:49:29.942212	2023-05-23 22:49:29.942212	01.03.02	Прикладная математика, фундаментальная информатика и программирование	1
2	2023-05-23 22:49:29.942212	2023-05-23 22:49:29.942212	02.03.02	Программирование и информационные технологии	1
\.


--
-- Data for Name: faculties; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.faculties (id, creation_time, last_update_time, link, name) FROM stdin;
1	2023-05-23 22:49:29.928492	2023-05-23 22:49:29.928492	https://apmath.spbu.ru	ПМ-ПУ
\.


--
-- Data for Name: links; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.links (id, creation_time, last_update_time, link, project_id, name) FROM stdin;
5	2023-05-24 23:35:05.767	2023-05-24 23:35:05.767	https://github.com/spbu-registry/Backend-registry	15	Github
6	2023-05-28 18:22:51.222	2023-05-28 18:22:51.222	https://github.com/spbu-registry/Backend-registry	15	Asana
7	2023-05-28 18:22:51.233	2023-05-28 18:22:51.233	https://github.com/spbu-registry/Backend-registry	15	Отчёт
10	2023-06-01 17:50:00.748	2023-06-01 17:50:00.748	https://github.com/spbu-registry/Backend-registry	30	GitHub
12	2023-06-01 18:00:45.157	2023-06-01 18:00:45.157	https://github.com/spbu-registry/Frontend-registry	31	GitHub
15	2023-06-01 18:07:24.127	2023-06-01 18:07:24.127	https://drive.google.com/file/d/1V7MowHgenTJziDcecP5jNdK2VhFq21h1/view?usp=sharing	32	Asana
16	2023-06-01 18:07:24.131	2023-06-01 18:07:24.131	https://github.com/spbu-registry/Analytics-registry	32	GitHub
\.


--
-- Data for Name: project_client; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_client (project_id, client_id) FROM stdin;
30	4
30	3
13	3
31	4
31	3
32	4
15	1
22	3
23	3
24	3
25	3
32	3
26	3
27	1
29	3
\.


--
-- Data for Name: project_clinic; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_clinic (project_id, clinic_id) FROM stdin;
\.


--
-- Data for Name: project_curator; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_curator (project_id, curator_id) FROM stdin;
30	2
31	2
32	2
13	1
15	2
\.


--
-- Data for Name: project_project; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_project (first_project_id, second_project_id) FROM stdin;
\.


--
-- Data for Name: project_student; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_student (project_id, student_id) FROM stdin;
30	25
30	24
15	2
15	8
15	7
15	3
15	5
30	19
30	23
30	18
31	22
31	21
31	27
31	30
31	28
32	16
32	20
32	26
32	17
32	29
\.


--
-- Data for Name: project_supervisor; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_supervisor (project_id, supervisor_id) FROM stdin;
13	1
15	2
\.


--
-- Data for Name: project_tag; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.project_tag (project_id, tag_id) FROM stdin;
15	5
15	2
15	1
\.


--
-- Data for Name: projects; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.projects (id, creation_time, last_update_time, description, max_students, name, requirements, result_link, start_time, work_format, start_filing, end_filing, start_implementation, end_implementation, start_defense, end_defense, status, requirements_for_performers) FROM stdin;
18	2023-05-25 00:05:20.336	2023-05-25 00:15:01.987	Разработка сервиса, предназначенного для использования людям с аллергией, позволяющего получать информаю о перемещении, графике цветения аллергенов, концентрации пыльцы в воздухе и профилактике.	0	AllergyЯ	Провести анализ существующих приложений для аллергиков с целью\nвыявления их достоинств и недостатков.\nИзучить актуальные российские и зарубежные медицинские протоколы по\nлечению аллергических заболеваний.\nПровести экспертное интервью с врачами-аллергологами с целью выяснения\nтого, какая информация необходима аллергикам для контроля за своим\nзаболеванием, а также какая информация может быть полезна людям, чьи\nблизкие страдают от аллергии.\nСоставить социальный портрет потенциальных пользователей приложения.\nОпределить, какие сведения нужны аллергикам, а также их близким для\nконтроля за аллергическим заболеванием, с учетом экспертной оценки и\nсоциальным портретом потребителей.	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	\N
15	2023-05-24 21:42:19.755	2023-05-28 18:24:04.584	Создать прототип игры с ИИ основанным на Conway's Game of Life.\nНельзя при этом иметь систему, которая только симулирует Conway's Game of Life визуально. Нужно чтобы игрок как-то взаимодействовал с ИИ. Дизайн и Гейм дизайн на усмотрение разработчика. 	0	Прототип игры с ИИ основанным на Conway's Game of Life	От группы ожидается разработать и собрать систему внутренней навигации с использованием одноплатных компьютеров (Raspberry PI), работающих на Ubuntu, и подключенных к ним ридеров (BLE). На базе систем типа Arduino разработать с использованием micro Python и собрать на микросборках умные устройства - биконы/ридеры и другие устройства типа умных розеток для рабочих столов. Система должна будет использовать машинное обучение для калибровки помещения, и уметь привязывать устройства к плану помещения для дальнейшего отображения его на мобильном устройстве (планшет, смартфон). Для интеграции и управления системой потребуется развернуть сервер приложения с использованием веб сервисов IoT (Azure или AWS) для интеграции систем управления помещением и связи с мобильным приложением для бронирования рабочих мест, навигации в помещении и управления статусом рабочего стола. Смартфон также должен уметь активировать токен (BLE), через взаимодействие с табличкой на двери (QR code/NFC/ NTAG/RFID) при входе в помещение. 1. Есть частично структурированные данные от устройств IoT. 2. Есть требования к представлению данных в мобильных приложениях и на консоли администратора (например, занятость рабочих мест). 3. Есть требования к системам авторизации с использованием security token. 4. Есть требования к управлению жизненным циклом токена, а также потенциальной нагрузкой на систему. 5. Необходимо разработать решение, серверное ПО с использованием Dev Tools от ведущих производителей ПО (Майкрософт, Амазон и др) и продемонстрировать на тестовом примере бронирование рабочего место, авторизацию в помещении, включения оборудования на рабочем столе, и завершения работы с расчетом времени и использованных ресурсов. Приложение должно иметь адаптивный дизайн - возможность просмотра данных на любом устройстве (смартфон, планшет, настольный ПК).	\N	2022-12-31 03:00:00	empty	2023-01-06 03:00:00	2022-12-01 03:00:00	2023-01-06 03:00:00	2023-01-06 03:00:00	2023-01-05 03:00:00	2023-01-06 03:00:00	CLOSED	Навыки работы с Unreal Engine 4, язык программирования C++
32	2023-06-01 18:02:03.15	2023-06-01 18:07:24.171	Создание удобной и функциональной информационной системы для учета проектов, которые проводятся студентами в рамках клинической практики СПбГУ. Данная система должна обеспечивать возможность отслеживания статуса проектов, а также предоставлять доступ к информации о выполненных работах.  \nЭтот сервис в первую очередь для студентов, которые были бы заинтересованы в выборе интересных им проектов в рамках учебной практики, дающей ценный опыт для всех членов команды. 	0	Реестр проектов клинической практики СПбГУ (аналитика) 	Анализ существующих решений;\nСбор и структурирование требований от представителей функционального заказчика;\nРазработка сервисов интеграции для получения данных об активности в репозиториях команд (GitHub). Если проектная команда ведёт свою деятельность в проекте (репозитории) GitHub, то для таких проектов собирается статистика об активности (Issue, кодовая база и т.д.) как в разрезе проекта в целом, так и в разрезе отдельных участников. Аналитические срезы параметризуются, т.е. пользователем задаются параметры отбора данных для анализа. В зависимости от данных необходимо предусмотреть их визуализацию в виде таблицы, статичной или интерактивной диаграммы;\nПроектирование и прототипирование ИС и её модулей;\nРазработка и поиск метрик для отслеживания эффективности работы над проектом.	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Знание:\nPython;\nFastAPI;\nPostgreSQL\n
13	2023-05-24 21:31:22.234	2023-05-25 00:12:10.686	На основе данных электронного расписания и свободного ПО с открытым исходным кодом создать сервис кэширования данных и аналитическое Web приложение. Примеры аналитических срезов: занятость аудиторий, занятость преподавателей, занятость студентов, доступные аудитории по заданным критериям.	0	Аналитика данных электронного расписания	\n1. Есть частично структурированные данные (а если точнее, описание структуры).\n2. Есть требования к аналитическим формам (репорты расписания, например, занятость аудиторий).\n3. Есть требования к доступности и производительности аналитических форм.\n4. Есть требования к минимизации количества запросов к первоисточнику данных (т.е. количество обращений к первоисточнику не должно быть равно количеству обращений к формам, а должно быть значительно ниже). Проще говоря, нужен умный кэш, который снижает нагрузку на первоисточник.\n5. Необходимо разработать решение, соблюдающее требование и тесты, доказывающие это.\n\nWeb приложение должно иметь адаптивный дизайн - возможность просмотра данных на любом устройстве (смартфон, планшет, настольный ПК).\nАналитические срезы параметризуются, т.е. пользователем задаются параметры отбора данных для анализа.\nВ зависимости от данных необходимо предусмотреть их визуализацию в виде таблицы, статичной или интерактивной диаграммы.\n	\N	\N	empty	\N	\N	\N	\N	\N	\N	ACTIVE	MS SQL Server (на уровне простых запросов и получения данных из БД для дальнейшей манипуляции ими).\nXML (манипуляция данными в XML формате).\nСредства разработки Data driven Web приложений или построения аналитических панелей с использованием соответствующего ПО\n
23	2023-06-01 15:27:41.829	2023-06-01 15:28:40.128	Создание сайта ЦИТИК предусматривает разработку нескольких компонент (разделов): информационный (новости, информация о центре, контакты и т.д.), каталог проектов (прошлые, настоящие и будущие проекты), интерактивный срез по текущим активностям (какие проекты сейчас в работе, какова активность участников, имеющиеся результаты - на основе данных систем управления проектами).	0	Сайт ЦИТИК	Разработка и внедрение средств работы с контентом (CMS).\nРазработка и внедрение средств управления базой данных проектов.\nРазработка и внедрение средств интеграции с системами управления проектов для получения метаданных и статистики по проектам.\nРазработка средств визуализации статистики по проектам.\n	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Языки программирования и инструменты для разработки Web приложений, начальные навыки развёртывания и настройки Web приложений.
24	2023-06-01 15:29:04.981	2023-06-01 15:32:21.533	Выявление показателей, которые определяют эффективность онлайн-курсов образовательных платформ “Coursera” и “Открытое образование”.	0	Анализ данных онлайн курсов	На основе данных материалов и действий учащихся онлайн-курсов выявить показатели, определяющие их эффективность. Эффективность курса состоит из двух компонент: качество образовательных материалов и коммерческий потенциал. Данные являются отчётами и логами образовательных платформ “Coursera” и “Открытое образование”.	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	\N
25	2023-06-01 15:32:42.073	2023-06-01 15:33:38.133	Задача состоит в создании системы управления рабочими местами в помещении с\nиспользованием метки-токена на смартфоне и определением ее местоположения в\nпомещении, оборудованном системой внутренней навигации. Система должна уметь\nраспознать метку, получить информацию о токене, валидировать его в системе\nуправления помещением, показать сотруднику рабочий стол на плане помещения в\nмобильном приложении, в соответствии с его токеном и включить оборудование на\nрабочем столе. По завершению работы, через подтверждение сотрудником на\nмобильном приложении, система должна вычислить суммарное время нахождения\nсотрудника на рабочем месте и передать эту информацию в мобильное приложение\nдля оплаты.	0	Система управления занятостью рабочих мест с использованием интернета вещей и токенов	От группы ожидается разработать и собрать систему внутренней навигации с\nиспользованием одноплатных компьютеров (Raspberry PI), работающих на Ubuntu, и\nподключенных к ним ридеров (BLE). На базе систем типа Arduino разработать с\nиспользованием micro Python и собрать на микросборках умные устройства -\nбиконы/ридеры и другие устройства типа умных розеток для рабочих столов. Система\nдолжна будет использовать машинное обучение для калибровки помещения, и уметь\nпривязывать устройства к плану помещения для дальнейшего отображения его на\nмобильном устройстве (планшет, смартфон).\nДля интеграции и управления системой потребуется развернуть сервер приложения с\nиспользованием веб сервисов IoT (Azure или AWS) для интеграции систем управления\nпомещением и связи с мобильным приложением для бронирования рабочих мест,\nнавигации в помещении и управления статусом рабочего стола. Смартфон также\nдолжен уметь активировать токен (BLE), через взаимодействие с табличкой на двери\n(QR code/NFC/ NTAG/RFID) при входе в помещение.\n\n1. Есть частично структурированные данные от устройств IoT.\n2. Есть требования к представлению данных в мобильных приложениях и на\nконсоли администратора (например, занятость рабочих мест).\n3. Есть требования к системам авторизации с использованием security token.\n4. Есть требования к управлению жизненным циклом токена, а также\nпотенциальной нагрузкой на систему.\n5. Необходимо разработать решение, серверное ПО с использованием Dev Tools\nот ведущих производителей ПО (Майкрософт, Амазон и др) и\nпродемонстрировать на тестовом примере бронирование рабочего место,\nавторизацию в помещении, включения оборудования на рабочем столе, и\nзавершения работы с расчетом времени и использованных ресурсов.\nПриложение должно иметь адаптивный дизайн - возможность просмотра данных на\nлюбом устройстве (смартфон, планшет, настольный ПК).	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Знание Ubuntu для программирования Raspberry PI и микросборок\nИспользование Azure IoT Hub, AWS, Node-RED (для конфигурирования облачных\nсервисов для IoT устройств).\nЗнание языка программирования Python, знание JSON, NodeJS, MQTT, HTML
22	2023-06-01 15:25:49.101	2023-06-01 15:27:15.858	Проект представляет из собой сбор и анализ системных журналов Windows для оперативного их анализа. Примеры аналитических срезов: частота использования, популярные приложения, популярные ошибки, активные пользователи.	0	Сбор и анализ логов Windows	С использованием встроенных средств ОС Windows и свободного ПО с открытым исходным кодом реализовать сбор и анализ системных журналов Windows для оперативного их анализа. Примеры аналитических срезов: частота использования, популярные приложения, популярные ошибки, активные пользователи.	\N	\N	empty	\N	\N	\N	\N	\N	\N	ACTIVE	\N
26	2023-06-01 15:34:27.341	2023-06-01 15:36:35.037	Система поддержки процесса прохождения практики СОП\n(система оптимизации практики) – это макет виртуальной\nсреды обучения и системы ведения управления в\nюридической клинике, разработанный студентами ЮФ\nСПбГУ. Он представляет из себя веб-серверное программное\nобеспечение с управлением курсами, настраиваемой открытой\nархитектурой и масштабируемым дизайном, которое\nпозволяет интегрироваться с информационными системами\nучащихся.\n\nЕго основными целями являются добавление онлайн-\nэлементов к организации практики и обучению, традиционно\nпроводимому с глазу на глаз, и разработка полностью онлайн-\nкурсов с минимальным количеством встреч или без них.	0	Система поддержки процесса прохождения практики	1) Dev-Ops\nИспользовать AWS (Amason Web Services) для размещения облачных решений.\nИспользовать AWS buckets + S3, чтобы разместить базы данных и сервера в облаке, не\nарендуя собственные хард-сервера.\n2) Backend (Сервер)\nСервер и бизнес-логика написать на TypeScript, с использованием библиотеки NodeJS,\nExpress.\nБаза данных - PostgreSQL.\nAPI сделать с использованием GraphQL\n3) Frontend (Сайт)\nСайт написать при помощи фреймворка React, на TypeScript, с использованием библиотек\nAppollo и styled-components.	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	\N
28	2023-06-01 15:38:42.729	2023-06-01 15:39:16.842	Корпоративный (не публичный) видеоархив СПбГУ	0	Видеоархив СПбГУ	Возможные функции: каталогизация, тегирование, поиск, управление доступом. Предлагается найти и настроить (адаптировать) одно из существующих решений класса Video CMS	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	\N
27	2023-06-01 15:37:05.246	2023-06-01 15:38:19.808	ПО, с помощью которого можно по фотографии керна детектировать особенности и классификации слоев породы.\n\n	0	Инструмент для автоматизации  разметки керна	Разработать ПО  для детектирования особенностей и классификации слоев породы\nпо 4 параметрам (тип породы, карбонатность, нефтенасыщенность, разрушенность) \nна фотографиях керна\n	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	\N
29	2023-06-01 15:42:05.698	2023-06-01 15:48:52.827	Создание каталога сведений об НПР	0	Каталог сведений об НПР	\N	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Знание:\nJava/Kotlin\nSpring Boot\nGraphQL\nBD\nMongoDB
31	2023-06-01 17:50:21.583	2023-06-01 18:00:45.193	Создание удобной и функциональной информационной системы для учета проектов, которые проводятся студентами в рамках клинической практики СПбГУ. Данная система должна обеспечивать возможность отслеживания статуса проектов, а также предоставлять доступ к информации о выполненных работах.  \nЭтот сервис в первую очередь для студентов, которые были бы заинтересованы в выборе интересных им проектов в рамках учебной практики, дающей ценный опыт для всех членов команды. 	0	Реестр проектов клинической практики СПбГУ (фронтенд) 	Требуется создать многомодульную ИС для регистрации, отслеживания статуса и деталей проектов, проводимых студентами в рамках клинических практик СПбГУ. Система должна иметь публичную часть для обзора истории о выполненных проектах, закрытую часть для ограниченного доступа администраторам (руководителей и сотрудников клиник), средства для сбора статистики из проектных окружений и её визуализации. \nFrontend:\nДизайн и разработка UI;\nОжидается, что все модули будут реализованы как Web-приложения, имеющие адаптивный дизайн – возможность просмотра данных на любом устройстве (смартфон, планшет, настольный ПК). 	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Знание:\nReact;\nNext.js;\nCSS-modules;\nTypeScript;\nTremor
30	2023-06-01 17:38:14.874	2023-06-01 17:50:00.786	Реестр проектов клинической практики СПбГУ — это сервис, являющийся важным инструментом для оптимизации работы системы, главной задачей которой является организация и учёт всех студенческих проектов, проводимых в рамках клинической практики. Реестр должен предоставлять студентам, преподавателям, руководителям и заказчикам доступ как к уже реализованным и утверждённым проектам, так и разрабатываемым в настоящее время задачам. 	0	Реестр проектов клинической практики СПбГУ (бэкенд)	Требуется создать многомодульную информационную систему для регистрации, отслеживания статуса и деталей проектов, проводимых студентами в рамках клинических практик СПбГУ. От системы ожидается наличие публичной части для обзора истории о выполненных проектах, закрытой части с ограниченным доступом администраторами (руководителями и сотрудниками клиник), а также средств для сбора статистики из проектных окружений и её визуализации. \nЗадачи:\nСоставление матрицы прав и ролей; \nПроектирование базы данных, реализация слоя доступа к данным; \nРазработка сервисов интеграции для получения данных об активности в репозиториях команд (GitHub); \nРазработка средств управления доступом. 	\N	\N	empty	\N	\N	\N	\N	\N	\N	CLOSED	Знание:\nJava;\nSpring Boot;\nPostgreSQL ;\nLiquibase ;\nswagger;
\.


--
-- Data for Name: roles_in_projects; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.roles_in_projects (id, creation_time, last_update_time, role, project_id, student_id) FROM stdin;
8	2023-05-24 23:35:05.778	2023-05-24 23:35:05.778	Backend-разработчик	15	8
9	2023-05-24 23:35:05.782	2023-05-24 23:35:05.782	Frontend-разработчик	15	3
10	2023-05-24 23:35:05.786	2023-05-24 23:35:05.786	Teamlead	15	7
11	2023-05-24 23:35:05.789	2023-05-24 23:35:05.789	Тестировщик	15	2
12	2023-05-24 23:35:05.792	2023-05-24 23:35:05.792	Технический писатель	15	5
24	2023-06-01 17:46:01.915	2023-06-01 17:46:01.915	Project manager	30	24
25	2023-06-01 17:46:01.919	2023-06-01 17:46:01.919	Разработчик и тестировщик	30	25
26	2023-06-01 17:46:01.923	2023-06-01 17:46:01.923	Разработчик	30	18
27	2023-06-01 17:46:01.927	2023-06-01 17:46:01.927	Разработчик	30	19
28	2023-06-01 17:46:01.93	2023-06-01 17:46:01.93	Аналитик и разработчик	30	23
34	2023-06-01 18:00:45.172	2023-06-01 18:00:45.172	Верстальщик	31	30
35	2023-06-01 18:00:45.176	2023-06-01 18:00:45.176	UI-дизайн	31	21
36	2023-06-01 18:00:45.18	2023-06-01 18:00:45.18	Верстальщик	31	22
37	2023-06-01 18:00:45.184	2023-06-01 18:00:45.184	Тимлид	31	28
38	2023-06-01 18:00:45.189	2023-06-01 18:00:45.189	Верстальщик	31	27
44	2023-06-01 18:07:24.149	2023-06-01 18:07:24.149	Backend разработчик	32	26
45	2023-06-01 18:07:24.155	2023-06-01 18:07:24.155	Аналитик	32	16
46	2023-06-01 18:07:24.159	2023-06-01 18:07:24.159	Frontend разработчик	32	17
47	2023-06-01 18:07:24.163	2023-06-01 18:07:24.163	Project Manager, аналитик	32	20
48	2023-06-01 18:07:24.167	2023-06-01 18:07:24.167	Backend разработчик	32	29
\.


--
-- Data for Name: students; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.students (id, creation_time, last_update_time, degree, grade, sex, educational_program_id, user_id) FROM stdin;
1	2023-05-23 22:49:29.957237	2023-05-23 22:49:29.957237	BACHELOR	3	MALE	1	1
2	2023-05-23 22:49:29.957237	2023-05-23 22:49:29.957237	BACHELOR	2	MALE	1	2
3	2023-05-23 22:49:29.957237	2023-05-23 22:49:29.957237	MASTER	1	FEMALE	2	3
4	2023-05-23 22:49:29.957237	2023-05-23 22:49:29.957237	MASTER	2	MALE	2	4
5	2023-05-24 13:28:34.26	2023-05-24 13:28:34.26	BACHELOR	3	MALE	1	13
6	2023-05-24 13:29:26.296	2023-05-24 13:29:26.296	BACHELOR	3	MALE	1	14
7	2023-05-24 13:29:49.131	2023-05-24 13:29:49.131	BACHELOR	2	MALE	1	15
8	2023-05-24 13:30:30.49	2023-05-24 13:30:30.49	BACHELOR	4	MALE	1	16
9	2023-05-24 13:31:37.485	2023-05-24 13:31:37.485	BACHELOR	1	MALE	1	17
10	2023-05-24 13:33:02.333	2023-05-24 13:33:02.333	MASTER	1	FEMALE	1	18
11	2023-05-24 13:34:37.607	2023-05-24 13:34:37.607	MASTER	2	FEMALE	1	19
12	2023-05-24 13:35:35.558	2023-05-24 13:35:35.558	POSTGRADUATE	2	FEMALE	1	20
13	2023-05-24 13:37:16.765	2023-05-24 13:37:16.765	SPECIALITY	1	FEMALE	1	21
14	2023-05-24 13:38:56.795	2023-05-24 13:38:56.796	BACHELOR	1	MALE	1	22
15	2023-05-24 13:42:56.722	2023-05-24 13:42:56.722	MASTER	2	FEMALE	1	23
16	2023-06-01 16:21:45.968	2023-06-01 16:21:45.968	BACHELOR	3	MALE	1	28
17	2023-06-01 16:23:05.943	2023-06-01 16:23:05.943	BACHELOR	3	MALE	1	29
18	2023-06-01 16:24:30.215	2023-06-01 16:24:30.215	BACHELOR	3	FEMALE	1	30
19	2023-06-01 16:25:21.735	2023-06-01 16:25:21.735	BACHELOR	3	MALE	1	31
20	2023-06-01 16:25:51.804	2023-06-01 16:25:51.804	BACHELOR	3	FEMALE	1	32
21	2023-06-01 16:26:34.735	2023-06-01 16:26:34.735	BACHELOR	3	FEMALE	1	33
22	2023-06-01 16:27:00.952	2023-06-01 16:27:00.952	BACHELOR	3	MALE	1	34
23	2023-06-01 16:27:25.699	2023-06-01 16:27:25.699	BACHELOR	3	MALE	1	35
24	2023-06-01 16:27:48.358	2023-06-01 16:27:48.358	BACHELOR	3	MALE	1	36
25	2023-06-01 16:28:10.485	2023-06-01 16:28:10.485	BACHELOR	3	MALE	1	37
26	2023-06-01 16:28:32.297	2023-06-01 16:28:32.297	BACHELOR	3	MALE	1	38
27	2023-06-01 16:28:59.453	2023-06-01 16:28:59.453	BACHELOR	3	MALE	1	39
28	2023-06-01 16:29:20.188	2023-06-01 16:29:20.188	BACHELOR	3	MALE	1	40
29	2023-06-01 16:29:47.658	2023-06-01 16:29:47.658	BACHELOR	3	MALE	1	41
30	2023-06-01 16:31:12.147	2023-06-01 16:31:12.147	BACHELOR	3	MALE	1	42
\.


--
-- Data for Name: supervisors; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.supervisors (id, creation_time, last_update_time, link, phone, user_id) FROM stdin;
1	2023-05-23 22:49:30.019513	2023-05-23 22:49:30.019513	asfaasff@gmail.com	+3333333333	11
2	2023-05-23 22:49:30.019513	2023-05-23 22:49:30.019513	ggggggg@gmail.com	+8734566666	12
3	2023-06-01 16:17:32.009	2023-06-01 16:17:32.009	\N	+78123636305	27
\.


--
-- Data for Name: tags; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.tags (id, creation_time, last_update_time, name) FROM stdin;
1	2023-05-23 22:49:29.838496	2023-05-23 22:49:29.838496	machine learning
2	2023-05-23 22:49:29.838496	2023-05-23 22:49:29.838496	AI
3	2023-05-23 22:49:29.838496	2023-05-23 22:49:29.838496	neural networks
4	2023-05-23 22:49:29.838496	2023-05-23 22:49:29.838496	agricultural
5	2023-05-23 22:49:29.838496	2023-05-23 22:49:29.838496	physics
\.


--
-- Data for Name: users; Type: TABLE DATA; Schema: public; Owner: root
--

COPY public.users (id, creation_time, last_update_time, email, name, role) FROM stdin;
1	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	sidorov@student.spbu.ru	Сидоров Петр Иванович	USER
2	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	petrov@student.spbu.ru	Петров Иван Васильевич	USER
3	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	ivanova@student.spbu.ru	Иванова Анастасия Павловна	USER
4	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	pupkin@student.spbu.ru	Пупкин Аркадий Петрович	USER
5	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://www.gazprom.ru	Газпром	USER
6	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://www.huawei.ru	Huawei	USER
7	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru	SPBU	USER
8	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru/studentam/praktika/praktika-po-modeli-kliniki-v-spbgu/centr-it-i-inzhenernyh-kompetenciy?ysclid=lfpaz385hh389711213	ЦИТИК	USER
9	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru	Коровкин Максим Васильевич	USER
10	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru	Блеканов Иван Станиславович	USER
11	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru	Давыденко Александр Александрович	USER
12	2023-05-23 22:49:29.879379	2023-05-23 22:49:29.879379	https://spbu.ru	Александров Александр Юрьевич	USER
13	2023-05-24 13:28:34.254	2023-05-24 13:28:34.254	arkash@gmail.com	Кондратьев Аркадий Петрович	USER
14	2023-05-24 13:29:26.291	2023-05-24 13:29:26.291	kndrt@gmail.com	Кондратьев Кондрат Кондратьевич	USER
15	2023-05-24 13:29:49.127	2023-05-24 13:29:49.127	ivanov@gmail.com	Иванов Иван Иванович	USER
16	2023-05-24 13:30:30.486	2023-05-24 13:30:30.486	pitr@gmail.com	Аркадьев Петр Кондратьевич	USER
17	2023-05-24 13:31:37.48	2023-05-24 13:31:37.48	pka@gmail.com	Петров Кондрат Аркадьевич	USER
18	2023-05-24 13:33:02.33	2023-05-24 13:33:02.33	baby@yandex.ru	Малышка Бейби Мэйбивична	USER
19	2023-05-24 13:34:37.602	2023-05-24 13:34:37.602	kedda@yandex.ru	Кондратьева Елизавета Дмитриевна	USER
20	2023-05-24 13:35:35.554	2023-05-24 13:35:35.554	chasha@yandex.ru	Чашка Рената Алексеевна	USER
21	2023-05-24 13:37:16.761	2023-05-24 13:37:16.761	pipetz@yandex.ru	Глобальная Погрешность Рунге	USER
22	2023-05-24 13:38:56.79	2023-05-24 13:38:56.79	kpop@yandex.ru	Чон Гук Петрович	USER
23	2023-05-24 13:42:56.718	2023-05-24 13:42:56.718	masha@yandex.ru	Васильева Мария Владимировна	USER
24	2023-06-01 16:01:38.426	2023-06-01 16:01:38.426	st075938@student.spbu.ru	Ермоленко Александр Андреевич	USER
25	2023-06-01 16:06:07.737	2023-06-01 16:06:07.737	a.krylatov@spbu.ru	Крылатов Александр Юрьевич	USER
26	2023-06-01 16:07:29.245	2023-06-01 16:07:29.245	\N	Баранов Павел Александрович	USER
27	2023-06-01 16:17:32	2023-06-01 16:17:32	\N	Сергей Юрьевич Севрюков	USER
28	2023-06-01 16:21:45.958	2023-06-01 16:21:45.958	st087940@student.spbu.ru	Пивнев Игорь Алексеевич	USER
29	2023-06-01 16:23:05.939	2023-06-01 16:23:05.939	st086961@student.spbu.ru	Ивашкин Роман Алексеевич	USER
30	2023-06-01 16:24:30.21	2023-06-01 16:24:30.21	st086935@student.spbu.ru	Гампер Елизавета Алексеевна	USER
31	2023-06-01 16:25:21.729	2023-06-01 16:25:21.729	st087880@student.spbu.ru	Луговский Сергей Алексеевич	USER
32	2023-06-01 16:25:51.8	2023-06-01 16:25:51.8	st087034@student.spbu.ru	Иванова Анастасия Алексеевна	USER
33	2023-06-01 16:26:34.731	2023-06-01 16:26:34.731	st089228@student.spbu.ru	Топпер Алина Михайловна	USER
34	2023-06-01 16:27:00.947	2023-06-01 16:27:00.947	st087640@student.spbu.ru	Корельский Дмитрий Сергеевич	USER
35	2023-06-01 16:27:25.694	2023-06-01 16:27:25.694	st087059@student.spbu.ru	Ванькович Сергей Витальевич	USER
36	2023-06-01 16:27:48.355	2023-06-01 16:27:48.355	st084445@student.spbu.ru	Павлов Михаэль	USER
37	2023-06-01 16:28:10.479	2023-06-01 16:28:10.479	st087263@student.spbu.ru	Бутусов Андрей Михайлович	USER
38	2023-06-01 16:28:32.289	2023-06-01 16:28:32.289	st087126@student.spbu.ru	Лурье Илья Михайлович	USER
39	2023-06-01 16:28:59.449	2023-06-01 16:28:59.449	st087794@student.spbu.ru	Наумкин Даниил Вячеславович	USER
40	2023-06-01 16:29:20.184	2023-06-01 16:29:20.184	st087812@student.spbu.ru	Лалуев Денис Витальевич	USER
41	2023-06-01 16:29:47.654	2023-06-01 16:29:47.654	st083948@student.spbu.ru	Кудайбергенов Хаби	USER
42	2023-06-01 16:31:12.143	2023-06-01 16:31:12.143	st088132@student.spbu.ru	Пономарёв Александр Сергеевич	USER
\.


--
-- Name: clients_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.clients_id_seq', 4, true);


--
-- Name: clinics_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.clinics_id_seq', 1, true);


--
-- Name: commits_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.commits_id_seq', 1, false);


--
-- Name: curators_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.curators_id_seq', 5, true);


--
-- Name: educational_programs_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.educational_programs_id_seq', 2, true);


--
-- Name: faculties_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.faculties_id_seq', 1, true);


--
-- Name: links_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.links_id_seq', 16, true);


--
-- Name: projects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.projects_id_seq', 32, true);


--
-- Name: roles_in_projects_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.roles_in_projects_id_seq', 48, true);


--
-- Name: students_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.students_id_seq', 30, true);


--
-- Name: supervisors_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.supervisors_id_seq', 3, true);


--
-- Name: tags_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.tags_id_seq', 5, true);


--
-- Name: users_id_seq; Type: SEQUENCE SET; Schema: public; Owner: root
--

SELECT pg_catalog.setval('public.users_id_seq', 42, true);


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (id);


--
-- Name: clinics clinics_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clinics
    ADD CONSTRAINT clinics_pkey PRIMARY KEY (id);


--
-- Name: commits commits_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.commits
    ADD CONSTRAINT commits_pkey PRIMARY KEY (id);


--
-- Name: curators curators_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.curators
    ADD CONSTRAINT curators_pkey PRIMARY KEY (id);


--
-- Name: databasechangeloglock databasechangeloglock_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.databasechangeloglock
    ADD CONSTRAINT databasechangeloglock_pkey PRIMARY KEY (id);


--
-- Name: educational_programs educational_programs_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.educational_programs
    ADD CONSTRAINT educational_programs_pkey PRIMARY KEY (id);


--
-- Name: faculties faculties_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.faculties
    ADD CONSTRAINT faculties_pkey PRIMARY KEY (id);


--
-- Name: links links_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.links
    ADD CONSTRAINT links_pkey PRIMARY KEY (id);


--
-- Name: project_client project_client_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_client
    ADD CONSTRAINT project_client_pkey PRIMARY KEY (project_id, client_id);


--
-- Name: project_clinic project_clinic_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_clinic
    ADD CONSTRAINT project_clinic_pkey PRIMARY KEY (project_id, clinic_id);


--
-- Name: project_curator project_curator_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_curator
    ADD CONSTRAINT project_curator_pkey PRIMARY KEY (project_id, curator_id);


--
-- Name: project_project project_project_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_project
    ADD CONSTRAINT project_project_pkey PRIMARY KEY (first_project_id, second_project_id);


--
-- Name: project_student project_student_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_student
    ADD CONSTRAINT project_student_pkey PRIMARY KEY (project_id, student_id);


--
-- Name: project_supervisor project_supervisor_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_supervisor
    ADD CONSTRAINT project_supervisor_pkey PRIMARY KEY (project_id, supervisor_id);


--
-- Name: project_tag project_tag_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_tag
    ADD CONSTRAINT project_tag_pkey PRIMARY KEY (project_id, tag_id);


--
-- Name: projects projects_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.projects
    ADD CONSTRAINT projects_pkey PRIMARY KEY (id);


--
-- Name: roles_in_projects roles_in_projects_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles_in_projects
    ADD CONSTRAINT roles_in_projects_pkey PRIMARY KEY (id);


--
-- Name: students students_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT students_pkey PRIMARY KEY (id);


--
-- Name: supervisors supervisors_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.supervisors
    ADD CONSTRAINT supervisors_pkey PRIMARY KEY (id);


--
-- Name: tags tags_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.tags
    ADD CONSTRAINT tags_pkey PRIMARY KEY (id);


--
-- Name: users users_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (id);


--
-- Name: commits fk4bmx2s5jfc072em7w0boss13t; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.commits
    ADD CONSTRAINT fk4bmx2s5jfc072em7w0boss13t FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: roles_in_projects fk54b75pf8n6ker6tp996u4nxvo; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles_in_projects
    ADD CONSTRAINT fk54b75pf8n6ker6tp996u4nxvo FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- Name: project_tag fk7arll7vppdy1j61xrux7nyy11; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_tag
    ADD CONSTRAINT fk7arll7vppdy1j61xrux7nyy11 FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: project_curator fkglfpnd731kp15j4w3txe2q75u; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_curator
    ADD CONSTRAINT fkglfpnd731kp15j4w3txe2q75u FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: project_clinic fkgxojnd462kp15j4w3txe2q75u; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_clinic
    ADD CONSTRAINT fkgxojnd462kp15j4w3txe2q75u FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: project_client fkgxolmd632kp15j4w3fkd2q63u; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_client
    ADD CONSTRAINT fkgxolmd632kp15j4w3fkd2q63u FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: commits fkjs9x65x1qrw60in148autnp92; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.commits
    ADD CONSTRAINT fkjs9x65x1qrw60in148autnp92 FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- Name: project_supervisor fkjtojnd632kp73j4w3txe2q75u; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_supervisor
    ADD CONSTRAINT fkjtojnd632kp73j4w3txe2q75u FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: project_project fkjtojnd652kp73j4w6yer2q75u; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_project
    ADD CONSTRAINT fkjtojnd652kp73j4w6yer2q75u FOREIGN KEY (second_project_id) REFERENCES public.projects(id);


--
-- Name: project_student fkkf2jffas838k4wxd9kc7xyne0; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_student
    ADD CONSTRAINT fkkf2jffas838k4wxd9kc7xyne0 FOREIGN KEY (student_id) REFERENCES public.students(id);


--
-- Name: project_client fkm4lmk528w9yfdarbejbcqklpm; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_client
    ADD CONSTRAINT fkm4lmk528w9yfdarbejbcqklpm FOREIGN KEY (client_id) REFERENCES public.clients(id);


--
-- Name: project_project fkm9sgo953w9asfkdfejnralsdg; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_project
    ADD CONSTRAINT fkm9sgo953w9asfkdfejnralsdg FOREIGN KEY (first_project_id) REFERENCES public.projects(id);


--
-- Name: project_supervisor fkm9sgo953w9yfdsdfejbcqlsdg; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_supervisor
    ADD CONSTRAINT fkm9sgo953w9yfdsdfejbcqlsdg FOREIGN KEY (supervisor_id) REFERENCES public.supervisors(id);


--
-- Name: project_clinic fkm9sgo980w9yfdarbejbcqklpm; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_clinic
    ADD CONSTRAINT fkm9sgo980w9yfdarbejbcqklpm FOREIGN KEY (clinic_id) REFERENCES public.clinics(id);


--
-- Name: project_curator fkm9sgo980w9yfdarbejblhaqol; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_curator
    ADD CONSTRAINT fkm9sgo980w9yfdarbejblhaqol FOREIGN KEY (curator_id) REFERENCES public.curators(id);


--
-- Name: roles_in_projects fkn2r9lxwnpqo2elh5qlj3dpuhx; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.roles_in_projects
    ADD CONSTRAINT fkn2r9lxwnpqo2elh5qlj3dpuhx FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: links fkoryhxcrkx9ok9oq2m2l94hhhw; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.links
    ADD CONSTRAINT fkoryhxcrkx9ok9oq2m2l94hhhw FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- Name: students fkp65plgmefx811xau7o93nw12p; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkp65plgmefx811xau7o93nw12p FOREIGN KEY (educational_program_id) REFERENCES public.educational_programs(id);


--
-- Name: supervisors fkqahpoq6417qa8n5es35omu5cf; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.supervisors
    ADD CONSTRAINT fkqahpoq6417qa8n5es35omu5cf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: educational_programs fkqaos5vh58hxb7sguhy66qnpj3; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.educational_programs
    ADD CONSTRAINT fkqaos5vh58hxb7sguhy66qnpj3 FOREIGN KEY (faculty_id) REFERENCES public.faculties(id);


--
-- Name: curators fkqasdbc5217qa8n5es37omu5cf; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.curators
    ADD CONSTRAINT fkqasdbc5217qa8n5es37omu5cf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: clients fkqvykjc6027qa8n5es37omu5cf; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT fkqvykjc6027qa8n5es37omu5cf FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: students fkqvykjc6027qa8n5es37osi7il; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.students
    ADD CONSTRAINT fkqvykjc6027qa8n5es37osi7il FOREIGN KEY (user_id) REFERENCES public.users(id);


--
-- Name: project_tag fktdpca6u6cxadjbie0iyuyt324; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_tag
    ADD CONSTRAINT fktdpca6u6cxadjbie0iyuyt324 FOREIGN KEY (tag_id) REFERENCES public.tags(id);


--
-- Name: project_student fkthoas902afruk0t7q9df8qpre; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.project_student
    ADD CONSTRAINT fkthoas902afruk0t7q9df8qpre FOREIGN KEY (project_id) REFERENCES public.projects(id);


--
-- PostgreSQL database dump complete
--

