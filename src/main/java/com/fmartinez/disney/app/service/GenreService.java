package com.fmartinez.disney.app.service;

import com.fmartinez.disney.app.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getAllGenre();

    Genre create(Genre genre);
}
