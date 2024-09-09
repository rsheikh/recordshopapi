DROP DATABASE IF EXISTS record_shop;
CREATE DATABASE record_shop;

\c record_shop;

CREATE TYPE genre
    AS ENUM ('ROCK', 'HIPHOP', 'POP', 'COUNTRY', 'CLASSIC');

CREATE TABLE stock
(
    stock_id SERIAL PRIMARY KEY,
    quantity_in_stock INT
);

CREATE TABLE album
(
    album_id SERIAL PRIMARY KEY,
    artist VARCHAR,
    album_name VARCHAR,
    year_released INT,
    genre genre,
    stock_id INT REFERENCES stock(stock_id)
);