--liquibase formatted sql

--changeset Jack:InitialSchemaCreation(dbms:postgresql failOnError:true splitStatements:false)

CREATE TABLE car (
  carsid       BIGSERIAL PRIMARY KEY,
  make        TEXT,
  model       TEXT,
  color       TEXT,
  vin         BIGINT,
  oil_type    TEXT,
  oil_capacity  REAL,
  coolant_capacity REAL,
  oil_filter_type TEXT,
  oil_filter_model TEXT,
  air_filter_model TEXT,
  cabin_filter_model TEXT,
  battery_model TEXT
);

CREATE TABLE maintenance_record (
  recordid    BIGSERIAL PRIMARY KEY,
  car        BIGINT REFERENCES car (carsid),
  old_value   TEXT,
  new_value   TEXT,
  fieldname   TEXT,
  notes       TEXT,
  "timestamp"   DATE
);

CREATE TABLE users (
  userid      BIGSERIAL PRIMARY KEY,
  username    VARCHAR(30),
  "password"  TEXT,
  enabled     BOOLEAN,
  creation_time DATE,
  modification_time DATE
);

--rollback SELECT 1;