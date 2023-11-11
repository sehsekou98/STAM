CREATE TABLE student(
                        id SERIAL PRIMARY KEY ,
                        name TEXT NOT NULL ,
                        email TEXT NOT NULL ,
                        department TEXT NOT NULL ,
                        idNumber BIGSERIAL NOT NULL
);