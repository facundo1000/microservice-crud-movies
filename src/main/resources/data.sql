INSERT INTO CHARACTER (image, name, age, weight, story,deleted) VALUES ('http://dummyimage.com/175x100.png/5fa2dd/ffffff', 'Sam Worthington', 46, 83.44, 'he becames the main character',0);
INSERT INTO CHARACTER (image, name, age, weight, story,deleted) VALUES ('http://dummyimage.com/149x100.png/dddddd/000000', 'Chris Evans', 41, 90.32, 'he plays captain america',0);
insert into CHARACTER (image, name, age, weight, story,deleted) VALUES ('http://dummyimage.com/185x100.png/cc0000/ffffff', 'Robert Downey Jr', 33, 42.44, 'he plays iron man',0);

INSERT INTO GENRE (name, image,deleted) VALUES ('Action', 'http://dummyimage.com/204x100.png/5fa2dd/ffffff',0);
INSERT INTO GENRE (name, image,deleted) VALUES ('Drama', 'http://dummyimage.com/245x100.png/cc0000/ffffff',0);

INSERT INTO MOVIE_SERIE (image, title, create_at, rate,gender_id,deleted) VALUES ('http://dummyimage.com/243x100.png/5fa2dd/ffffff', 'Civil War', '2016-05-16', 2, 1,0);
INSERT INTO MOVIE_SERIE (image, title, create_at, rate, gender_id,deleted) VALUES ('http://dummyimage.com/181x100.png/cc0000/ffffff', 'Werewolf By Night', '2022-10-07', 3, 2,0);

INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (1,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,1);
INSERT INTO TBL_MOVIE_CHARACTER (MOVIE_SERIE_id, CHARACTER_id) VALUES (2,2);

INSERT INTO USERS (email, password, enable) VALUES ('admin10@hotmail.com','$2a$12$43p8OiD5RS55ToBOY6RMPept/suKhW6XH4KtmaKtND9EEXCvglTMO',1);
INSERT INTO USERS (email, password, enable) VALUES ('user120@yahoo.com','$2a$12$43p8OiD5RS55ToBOY6RMPept/suKhW6XH4KtmaKtND9EEXCvglTMO',1);



