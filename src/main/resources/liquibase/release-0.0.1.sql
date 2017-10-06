--liquibase formatted sql

--changeset Jack:InitialSchemaCreation(dbms:postgresql failOnError:true splitStatements:false)

CREATE TABLE cars (
  carsid              BIGSERIAL PRIMARY KEY,
  make                TEXT,
  model               TEXT,
  color               TEXT,
  vin                 TEXT,
  oil_type            TEXT,
  oil_capacity        REAL,
  coolant_capacity    REAL,
  oil_filter_type     TEXT,
  oil_filter_model    TEXT,
  air_filter_model    TEXT,
  cabin_filter_model  TEXT,
  battery_model       TEXT,
  dot_number          TEXT,
  dot_registered      BOOLEAN,
  plate_number        TEXT
);

CREATE TABLE maintenance_records (
  recordid            BIGSERIAL PRIMARY KEY,
  car                 BIGINT REFERENCES cars (carsid),
  miles               BIGINT,
  maintainer          TEXT,
  actiontaken         TEXT,
  notes               TEXT,
  "timestamp"         DATE
);

CREATE TABLE users (
  userid              BIGSERIAL PRIMARY KEY,
  username            VARCHAR(30) UNIQUE,
  "password"          TEXT,
  enabled             BOOLEAN,
  creation_time       DATE,
  modification_time   DATE
);

CREATE TABLE car_notes (
  noteid              BIGSERIAL PRIMARY KEY,
  car                 BIGINT REFERENCES cars (carsid),
  note                TEXT,
  "timestamp"         DATE
);

CREATE TABLE drivers (
  driverid            BIGSERIAL PRIMARY KEY,
  cars                BIGINT REFERENCES cars (carsid),
  license_number      TEXT UNIQUE,
  dl_class            TEXT,
  "name"              TEXT,
  date_of_birth       DATE

);

CREATE TABLE cars_drivers (
  cars_id             BIGINT,
  drivers_id          BIGINT
);

INSERT INTO users (userid, username, "password", enabled, creation_time, modification_time)
VALUES (0, 'gearhead', 'gearhead', true, '01-OCT-17', null);
--rollback SELECT 1;