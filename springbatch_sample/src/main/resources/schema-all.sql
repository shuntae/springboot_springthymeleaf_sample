DROP TABLE IF EXISTS fruit;

CREATE TABLE fruit (
id SERIAL NOT NULL PRIMARY KEY
 , name VARCHAR(10)
 , price INT
 , comment VARCHAR(10));
