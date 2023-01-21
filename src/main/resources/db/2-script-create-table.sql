BEGIN;

CREATE TABLE IF NOT EXISTS public.account
(
    "id" serial NOT NULL,
    "type" character varying(30) NOT NULL,
    "agency" integer NOT NULL,
    "number" integer NOT NULL,
    "balance" numeric(10, 2) NOT NULL,
    "id_associate" integer NOT NULL,
	CONSTRAINT pk_account PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.associate
(
    "id" serial NOT NULL,
    "name" character varying(200) NOT NULL,
    "cpf" character(11) NOT NULL,
    "birth_date" date NOT NULL,
    "phone" character varying(14),
    "email" character varying(30),
    "profession" character varying(60) NOT NULL,
    "salary" numeric(10, 2) NOT NULL,
    "last_paycheck" date NOT NULL,
	CONSTRAINT pk_associate PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.contract
(
    "id" serial NOT NULL,
    "value" numeric(10, 2) NOT NULL,
    "paid_off" boolean NOT NULL,
    "hire_date" date NOT NULL,
    "expiration_date" date NOT NULL,
    "installments_paid" integer NOT NULL,
    "installments_remaining" integer NOT NULL,
    "first_payment_date" date NOT NULL,
    "id_associate" integer NOT NULL,
    "id_product" integer NOT NULL,
	CONSTRAINT pk_contract PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.product
(
    "id" serial NOT NULL,
    "type" character varying(30) NOT NULL,
    "taxes" integer NOT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.transaction
(
    "id" serial NOT NULL,
    "type" character varying(30) NOT NULL,
    "value" numeric(10, 2) NOT NULL,
    "created_at" date NOT NULL,
    "id_credit_account" integer,
    "id_debit_account" integer,
    CONSTRAINT pk_transaction PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS public.address
(
    "id" serial NOT NULL,
    "id_associate" serial NOT NULL,
    "street_name" character varying(60) NOT NULL,
    "number" character varying(10) NOT NULL,
    "complement" character varying(60),
    "city" character varying(60) NOT NULL,
    "state" character varying(60) NOT NULL,
    "zip_code" character varying(11) NOT NULL,
    "country" character varying(60) NOT NULL,
    CONSTRAINT pk_address PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.account
    ADD CONSTRAINT fk_id_associate FOREIGN KEY (id_associate)
    REFERENCES public.associate (id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT fk_id_associate FOREIGN KEY (id_associate)
    REFERENCES public.associate (id) ON DELETE CASCADE;

ALTER TABLE IF EXISTS public.contract
    ADD CONSTRAINT fk_id_product FOREIGN KEY (id_product)
    REFERENCES public.product (id);

ALTER TABLE IF EXISTS public.transaction
    ADD CONSTRAINT fk_id_credit_account FOREIGN KEY (id_credit_account)
    REFERENCES public.account (id);

ALTER TABLE IF EXISTS public.transaction
    ADD CONSTRAINT fk_id_debit_account FOREIGN KEY (id_debit_account)
    REFERENCES public.account (id);

ALTER TABLE IF EXISTS public.address
    ADD CONSTRAINT "fk_id_associate" FOREIGN KEY (id_associate)
    REFERENCES public.associate (id) ON DELETE CASCADE;

END;