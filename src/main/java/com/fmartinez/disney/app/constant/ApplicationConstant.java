package com.fmartinez.disney.app.constant;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;

public class ApplicationConstant {
    public static final String CHARACTER_PATH = "/api/characters";
    public static final String GENRE_PATH = "/api/genre";
    public static final String MOVIE_SERIE_PATH = "/api/movies";
    public static final String AUDIT_PATH = "/api/audit";
    public static final String CHARACTER_NOT_FOUND = "The client is was found in the system.";
    public static final String GENRE_NOT_FOUND = "The genre is was found in the system.";
    public static final String MOVIE_SERIE_NOT_FOUND = "The genre was not found in the system.";
    public static final String MAIL_SENDER = "spunki314@hotmail.com";
    public static final String MAIL_SUBJECT = "Welcome to Disney Movie-Managment App";
    public static final String AUTH_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);

}

