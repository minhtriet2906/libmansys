PGDMP         /                y            LibraryDatabase    13.3    13.3     �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    17252    LibraryDatabase    DATABASE     r   CREATE DATABASE "LibraryDatabase" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'Vietnamese_Vietnam.1258';
 !   DROP DATABASE "LibraryDatabase";
                postgres    false            �            1259    17253    books    TABLE       CREATE TABLE public.books (
    book_id integer NOT NULL,
    title character varying(80) NOT NULL,
    author character varying(30) NOT NULL,
    category character varying(20) NOT NULL,
    publisher character varying(30) NOT NULL,
    book_status character varying(30) NOT NULL
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    17256    books_book_id_seq    SEQUENCE     �   CREATE SEQUENCE public.books_book_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 (   DROP SEQUENCE public.books_book_id_seq;
       public          postgres    false    200            �           0    0    books_book_id_seq    SEQUENCE OWNED BY     G   ALTER SEQUENCE public.books_book_id_seq OWNED BY public.books.book_id;
          public          postgres    false    201            �            1259    17258    category    TABLE     b   CREATE TABLE public.category (
    cat_id integer NOT NULL,
    cat_name character varying(20)
);
    DROP TABLE public.category;
       public         heap    postgres    false            �            1259    17261    category_cat_id_seq    SEQUENCE     �   CREATE SEQUENCE public.category_cat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.category_cat_id_seq;
       public          postgres    false    202            �           0    0    category_cat_id_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.category_cat_id_seq OWNED BY public.category.cat_id;
          public          postgres    false    203            �            1259    17263    history    TABLE     �   CREATE TABLE public.history (
    username character varying(30),
    book_id integer,
    rented_date date,
    return_date date
);
    DROP TABLE public.history;
       public         heap    postgres    false            �            1259    17266    renting    TABLE     �   CREATE TABLE public.renting (
    book_id integer,
    username character varying(30),
    rented_date date,
    due_date date,
    is_extended boolean
);
    DROP TABLE public.renting;
       public         heap    postgres    false            �            1259    17269    users    TABLE     �  CREATE TABLE public.users (
    username character varying(32) NOT NULL,
    password character varying(32),
    name character varying(30) DEFAULT NULL::character varying,
    email character varying(100) DEFAULT NULL::character varying,
    address character varying(100) DEFAULT NULL::character varying,
    phone character varying(15),
    CONSTRAINT phone_number_check CHECK (((phone)::text !~~ '%[^0-9]%'::text)),
    CONSTRAINT users_email_check CHECK (((email)::text ~~ '%@%'::text))
);
    DROP TABLE public.users;
       public         heap    postgres    false            4           2604    17277    books book_id    DEFAULT     n   ALTER TABLE ONLY public.books ALTER COLUMN book_id SET DEFAULT nextval('public.books_book_id_seq'::regclass);
 <   ALTER TABLE public.books ALTER COLUMN book_id DROP DEFAULT;
       public          postgres    false    201    200            5           2604    17278    category cat_id    DEFAULT     r   ALTER TABLE ONLY public.category ALTER COLUMN cat_id SET DEFAULT nextval('public.category_cat_id_seq'::regclass);
 >   ALTER TABLE public.category ALTER COLUMN cat_id DROP DEFAULT;
       public          postgres    false    203    202            �          0    17253    books 
   TABLE DATA           Y   COPY public.books (book_id, title, author, category, publisher, book_status) FROM stdin;
    public          postgres    false    200          �          0    17258    category 
   TABLE DATA           4   COPY public.category (cat_id, cat_name) FROM stdin;
    public          postgres    false    202   B       �          0    17263    history 
   TABLE DATA           N   COPY public.history (username, book_id, rented_date, return_date) FROM stdin;
    public          postgres    false    204   �       �          0    17266    renting 
   TABLE DATA           X   COPY public.renting (book_id, username, rented_date, due_date, is_extended) FROM stdin;
    public          postgres    false    205   �       �          0    17269    users 
   TABLE DATA           P   COPY public.users (username, password, name, email, address, phone) FROM stdin;
    public          postgres    false    206   �       �           0    0    books_book_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.books_book_id_seq', 16, true);
          public          postgres    false    201            �           0    0    category_cat_id_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.category_cat_id_seq', 9, true);
          public          postgres    false    203            <           2606    17280    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    200            >           2606    17282    users users_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (username);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    206            ?           2606    17283    renting renting_book_id_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_book_id_fkey FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 F   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_book_id_fkey;
       public          postgres    false    205    200    2876            @           2606    17288    renting renting_username_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.renting
    ADD CONSTRAINT renting_username_fkey FOREIGN KEY (username) REFERENCES public.users(username);
 G   ALTER TABLE ONLY public.renting DROP CONSTRAINT renting_username_fkey;
       public          postgres    false    205    206    2878            �   0  x��SKn�0]ӧ�U�jP��t�(��6r�IQ���4���`H�Un�s�b�մP>mV(����HMa���j2�
.	ŰY�a���������z�F%V�z�׆�$�}S!��`��q��w�� M	�`�m�7�acK+pa��m]ը�ݒVS2��έ�\���u�Ұ~�7L0�HH^e�Xy������h��q�Ut�2�u�~c�k�o�"w���1�}�c�{��%u��+�*k`������*�2�ӣ���d1��'uIrv��?p�����>����j�`�d��E:���Tne����Hb����F#��_s�Y�lM���K��_���-I�W���?���A����P�PT��$����Q9J��y��.[�%��F=�kH^�̢��;���Mѥ:l�R�?Y����9���0�/��#!�0B��}t���$��?%����I3��?�����:�Z���u?�İ��>iaW��M[N;*B��!�!�:��6o���V�S+W���O�VH6���� A�Z%���vN�~��8��z� ��m      �   _   x�3���/K��2�tL.����2�t��MM��2�tM)MN�r�%�&r�q䧖Ur�s:���敔�rYpz��qYr'g�er��qqq �=      �      x������ � �      �      x������ � �      �   �   x�KL����L�1~pĕ�KI-.��K/�|��5O!��؆����+8�q����'���:��&f��%��rz$��g�(�e���%���H����442615�������8�I�N3K3#S3c�=... �2�     