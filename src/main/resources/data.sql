INSERT INTO CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/175x100.png/5fa2dd/ffffff', 'Sam Worthington', 46, 83.44, 'he becames the main character');
INSERT INTO CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/149x100.png/dddddd/000000', 'Chris Evans', 41, 90.32, 'he plays captain america');
insert into CHARACTER (image, name, age, weight, story) VALUES ('http://dummyimage.com/185x100.png/cc0000/ffffff', 'Robert Downey Jr', 33, 42.44, 'he plays iron man');

INSERT INTO GENRE (name, image) VALUES ('Action', 'http://dummyimage.com/204x100.png/5fa2dd/ffffff');
INSERT INTO GENRE (name, image) VALUES ('Drama', 'http://dummyimage.com/245x100.png/cc0000/ffffff');

INSERT INTO MOVIE_SERIE (image, title, create_at,rate, gender_id) VALUES ('http://dummyimage.com/243x100.png/5fa2dd/ffffff', 'Avatar', '2010-01-01',5,1);
INSERT INTO MOVIE_SERIE (image, title, create_at, rate,gender_id) VALUES ('http://dummyimage.com/243x100.png/5fa2dd/ffffff', 'Civil War', '2016-05-16', 2, 1);
INSERT INTO MOVIE_SERIE (image, title, create_at, rate, gender_id) VALUES ('http://dummyimage.com/181x100.png/cc0000/ffffff', 'Werewolf By Night', '2022-10-07', 3, 2);

INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (1,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,2);

INSERT INTO USERS (username, password, enable) VALUES ('admin','2iSOJdsYo',1);
INSERT INTO USERS (username, password, enable) VALUES ('user','lwtKV0Rbn',1);

INSERT INTO AUTHORITIES( user_id,authority) VALUES (1,'ROLE_USER');
INSERT INTO AUTHORITIES( user_id,authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO AUTHORITIES( user_id,authority) VALUES (2,'ROLE_USER');


