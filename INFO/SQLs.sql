CREATE DATABASE db_contacts;

CREATE TABLE IF NOT EXISTS contacts
( id INTEGER NOT NULL AUTO_INCREMENT,
  name VARCHAR(128) NOT NULL,
  phone VARCHAR(128) NOT NULL,
  PRIMARY KEY (id)
);

