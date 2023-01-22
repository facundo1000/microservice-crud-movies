INSERT INTO CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/175x100.png/5fa2dd/ffffff', 'Maia', 20, 83.44, 'target enterprise eyeballs');
INSERT INTO CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/149x100.png/dddddd/000000', 'Skye', 20, 29.32, 'enhance visionary functionalities');
insert into CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/185x100.png/cc0000/ffffff', 'Cass', 33, 42.44, 'transform customized infomediaries');

INSERT INTO GENRE (name, image) VALUES ('Action|Horror', 'http://dummyimage.com/204x100.png/5fa2dd/ffffff');
INSERT INTO GENRE (name, image) VALUES ('Comedy', 'http://dummyimage.com/245x100.png/cc0000/ffffff');

INSERT INTO MOVIE_SERIE (image, title, create_at,rate, gender_id) VALUES ('http://dummyimage.com/182x100.png/dddddd/000000', 'Kill Me Please', '1995-09-07',5,1);
INSERT INTO MOVIE_SERIE (image, title, create_at, rate,gender_id) VALUES ('http://dummyimage.com/243x100.png/5fa2dd/ffffff', 'Guilty Hands', '1995-11-12', 2, 2);
INSERT INTO MOVIE_SERIE (image, title, create_at, rate, gender_id) VALUES ('http://dummyimage.com/181x100.png/cc0000/ffffff', 'Carnival of Souls', '1993-03-12', 3, 1);

INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (1,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,2);

INSERT INTO USERS (username, password, enable) VALUES ('admin','2iSOJdsYo',1);
INSERT INTO USERS (username, password, enable) VALUES ('user','lwtKV0Rbn',1);

INSERT INTO AUTHORITIES( user_id,authority) VALUES (1,'ROLE_USER');
INSERT INTO AUTHORITIES( user_id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO AUTHORITIES( user_id,authority) VALUES (2,'ROLE_USER');


